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
import java.lang.Object;

public class DialogSaveFile extends Activity{
    private ISaver mSaver;
    private boolean firstTime = true;

    @Override
    public void onStart() {
        super.onStart();

        if(firstTime == true){
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String appId = extras.getString("app_id");
                String filename = extras.getString("filename");
                String filepath = extras.getString("filepath");
                Uri fileuri = Uri.fromFile(new File(filepath));

                mSaver = Saver.createSaver(appId);
                mSaver.startSaving(this, filename, fileuri);
            }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        firstTime = false;
        // check that the file was successfully saved to OneDrive
        try {
            mSaver.handleSave(requestCode, resultCode, data);
        } catch (final SaverException e) {
            // Log error information
            if(e.getErrorType().toString().equals("Cancelled")){
                // Cancelado
            }

            tolog(e.getErrorType().toString()); // Provides one of the SaverError enum
            tolog(e.getDebugErrorInfo()); // Detailed debug error message
        }

        finish();
    }

    public void tolog(String toLog){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Intent : "+toLog, duration);
        toast.show();
    }
}
