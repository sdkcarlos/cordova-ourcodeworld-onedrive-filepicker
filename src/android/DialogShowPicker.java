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
import android.net.Uri;
import java.util.Map;

public class DialogShowPicker extends Activity{
    private IPicker mPicker;
    private boolean firstTime = true;
    private String linkMode = "";

    @Override
    public void onStart() {
        super.onStart();
        if(firstTime == true){
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String appId = extras.getString("app_id");
                linkMode = extras.getString("link_mode");
                mPicker = Picker.createPicker(appId);

                if(linkMode.equals("view")){
                    mPicker.startPicking(this, LinkType.WebViewLink);
                }else if(linkMode.equals("download")){
                    mPicker.startPicking(this, LinkType.DownloadLink);
                }
            }
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        firstTime = false;
        // Get the results from the picker
        IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        // Handle the case if nothing was picked
        if (result != null) {
            try {
                JSONObject response = new JSONObject();
                if(linkMode.equals("view")){
                    response.put("viewLink",result.getLink());
                }else if(linkMode.equals("download")){
                    response.put("downloadlink",result.getLink());
                }
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
                intent.putExtra("callback_identifier","file_selected");
                setResult(Activity.RESULT_OK, intent);
                finish();
            } catch (JSONException e) {
                Intent intent = new Intent();
                intent.putExtra("data", e.getMessage().toString());
                intent.putExtra("callback_identifier","error");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

            finish();
            return;
        }


        //do something with the result
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    public void tolog(String toLog){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Intent : "+toLog, duration);
        toast.show();
    }
}
