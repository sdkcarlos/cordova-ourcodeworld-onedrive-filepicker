package com.ourcodeworld.plugins.onedrivefilepicker;

import org.apache.cordova.*;
import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.content.Context;// To toast
import android.widget.Toast;// ToToast
import com.microsoft.onedrivesdk.picker.*;

public class DisplayMessageActivity extends Activity{
    private static String ONEDRIVE_APP_ID = "0000000048188688";
    private IPicker mPicker;
    private boolean firstTime = true;

    @Override
    public void onStart() {
        super.onStart();
        if(firstTime == true){
            tolog("In the onCreate() event");
            mPicker = Picker.createPicker(ONEDRIVE_APP_ID);
            mPicker.startPicking(this, LinkType.DownloadLink);
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
            tolog("Link to file '" + result.getName() + ": " + result.getLink());
            return;
        }

        //do something with the result
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void tolog(String toLog){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Intent : "+toLog, duration);
        toast.show();
    }
}
