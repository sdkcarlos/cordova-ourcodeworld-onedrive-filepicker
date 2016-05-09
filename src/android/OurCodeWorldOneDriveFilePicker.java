package com.ourcodeworld.plugins.onedrivefilepicker;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;// To toast
import android.widget.Toast;// ToToast
import android.os.Bundle;
import com.microsoft.onedrivesdk.picker.*;

public class OurCodeWorldOneDriveFilePicker extends CordovaPlugin {
    private static final String ACTION_SHOWPICKER = "showpicker";
    private CallbackContext PUBLIC_CALLBACKS = null;
    private IPicker mPicker;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        final JSONObject arg_object = data.getJSONObject(0);
        String ONEDRIVE_APP_ID = arg_object.getString("appId");
        String LINK_MODE = arg_object.getString("linkMode");
        PUBLIC_CALLBACKS = callbackContext;

        if (ACTION_SHOWPICKER.equals(action)) {
            Intent intent = new Intent("com.ourcodeworld.plugins.onedrivefilepicker.DialogShowPicker");
            intent.putExtra("app_id", ONEDRIVE_APP_ID);
            intent.putExtra("link_mode", LINK_MODE);
            cordova.startActivityForResult((CordovaPlugin) this, intent, 0);
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if(resultCode == cordova.getActivity().RESULT_OK){
            Bundle extras = data.getExtras();
            String linkData = extras.getString("data");
            String callbackIdentifier = extras.getString("callback_identifier");

            if(callbackIdentifier.equals("error")){
                PluginResult errorResults = new PluginResult(PluginResult.Status.ERROR, linkData);
                errorResults.setKeepCallback(true);
                PUBLIC_CALLBACKS.sendPluginResult(errorResults);
                return;
            }

            PluginResult resultado = new PluginResult(PluginResult.Status.OK, linkData);
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);
            return;
        }else if(resultCode == cordova.getActivity().RESULT_CANCELED){
            PluginResult resultado = new PluginResult(PluginResult.Status.OK, "");
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void tolog(String toLog){
        Context context = cordova.getActivity();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toLog, duration);
        toast.show();
    }
}
