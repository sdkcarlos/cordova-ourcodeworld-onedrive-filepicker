package com.ourcodeworld.plugins.onedrivefilepicker;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;// To toast
import android.widget.Toast;// ToToast
import com.microsoft.onedrivesdk.picker.*;


public class OurCodeWorldOneDriveFilePicker extends CordovaPlugin {
    private static String ONEDRIVE_APP_ID = "";
    private static final String ACTION_SHOWPICKER = "showpicker";
    private CallbackContext PUBLIC_CALLBACKS = null;
    private IPicker mPicker;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        final JSONObject arg_object = data.getJSONObject(0);
        ONEDRIVE_APP_ID = arg_object.getString("appId");
        PUBLIC_CALLBACKS = callbackContext;

        if (ACTION_SHOWPICKER.equals(action)) {
            //Intent intent = new Intent(this, DisplayMessageActivity.class);
            Intent intent = new Intent("com.ourcodeworld.plugins.onedrivefilepicker.DisplayMessageActivity");
            cordova.startActivityForResult((CordovaPlugin) this, intent, 0);

            //mPicker = Picker.createPicker(ONEDRIVE_APP_ID);
            //mPicker.startPicking(cordova.getActivity(), LinkType.DownloadLink);
            //tolog("El selector se ha abierto");
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        //tolog("onActivityResult test");
        // Get the results from the picker
        //IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        // Handle the case if nothing was picked
        if (result != null) {
            /*
            try {
                JSONObject response = new JSONObject();
                response.put("downloadlink",result.getLink());
                response.put("filename",result.getName());

                PluginResult resultA = new PluginResult(PluginResult.Status.OK, response.toString());
                resultA.setKeepCallback(true);
                PUBLIC_CALLBACKS.sendPluginResult(resultA);
            } catch (JSONException e) {
                PluginResult resultB = new PluginResult(PluginResult.Status.ERROR, e.getMessage().toString());
                resultB.setKeepCallback(true);
                PUBLIC_CALLBACKS.sendPluginResult(resultB);
            }*/
            // Do something with the picked file
            //tolog("Link to file '" + result.getName() + ": " + result.getLink());
            tolog("Hola on activity result cordova");
            return;
        }

        //do something with the result
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void tolog(String toLog){
        Context context = cordova.getActivity();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toLog, duration);
        toast.show();
    }

}
