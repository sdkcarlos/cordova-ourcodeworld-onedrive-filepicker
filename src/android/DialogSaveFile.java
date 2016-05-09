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
import com.microsoft.onedrivesdk.saver.*;
import android.net.Uri;
import java.util.Map;

public class DialogSaveFile extends Activity{
    private ISaver mSaver;

    @Override
    public void onStart() {
        super.onStart();
        if(firstTime == true){
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String appId = extras.getString("app_id");
                String filename = extras.getString("filename");
                String filepath = extras.getString("filepath");
            }
        }
    }

    public void tolog(String toLog){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Intent : "+toLog, duration);
        toast.show();
    }
}
