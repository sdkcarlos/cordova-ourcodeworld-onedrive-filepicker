package com.ourcodeworld.plugins.onedrivefilepicker;

import org.apache.cordova.*;
import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;// To toast
import android.widget.Toast;// ToToast
import android.os.Bundle;
import com.microsoft.onedrivesdk.picker.*;

public class DialogShowPicker extends Activity{
    //private static String ONEDRIVE_APP_ID = "0000000048188688";
    private IPicker mPicker;
    private boolean firstTime = true;

    @Override
    public void onStart() {
        super.onStart();
        if(firstTime == true){
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String appId = extras.getString("app_id");
                tolog("In the onCreate() event, appid" + appId);
                mPicker = Picker.createPicker(appId);
                mPicker.startPicking(this, LinkType.DownloadLink);
            }
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        firstTime = false;
        tolog("onActivityResult test de segunda");
        // Get the results from the picker
        IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        // Handle the case if nothing was picked
        if (result != null) {
            try {
                JSONObject response = new JSONObject();
                response.put("downloadlink",result.getLink());
                response.put("filename",result.getName());
                response.put("size",result.getSize());
                response.put("linkType",result.getLinkType());
                JSONObject thumbnails = new JSONObject();


                Map<String, Uri> map = result.getThumbnailLinks();
                for (Map.Entry<String, Uri> entry : map.entrySet()) {
                    thumbnails.put(entry.getKey(),entry.getValue());
                }
                
                response.put("thumbnails",thumbnails);

                Intent intent = new Intent();
                intent.putExtra("data", response.toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (JSONException e) {

            }

            //tolog("Link to file '" + result.getName() + ": " + result.getLink());

            finish();
            return;
        }

        //do something with the result
        super.onActivityResult(requestCode, resultCode, data);

        finish();
    }

    public void tolog(String toLog){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Intent : "+toLog, duration);
        toast.show();
    }
}
