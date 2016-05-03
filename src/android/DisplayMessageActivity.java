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

public class DisplayMessageActivity extends CordovaActivity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.init();
        // Load your application
         launchUrl = "file:///android_asset/www/index.html";
//        launchUrl = "file:///android_asset/www/index2.html";
        loadUrl(launchUrl);
   }
}
