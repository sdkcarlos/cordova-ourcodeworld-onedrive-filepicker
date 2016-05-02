package com.ourcodeworld.plugins.onedrivefilepicker;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.microsoft.onedrivesdk.picker.*;


public class OurCodeWorldOneDriveFilePicker extends CordovaPlugin {
    private static final String ONEDRIVE_APP_ID = "0000000048188688";
    private static final String ACTION_SHOWPICKER = "showpicker";

    private IPicker mPicker;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        final JSONObject arg_object = data.getJSONObject(0);

        if (ACTION_SHOWPICKER.equals(action)) {
            mPicker = Picker.createPicker(ONEDRIVE_APP_ID);
            mPicker.startPicking(cordova.getActivity(), LinkType.DownloadLink);
        }

        return true;
    }

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        // Get the results from the picker
        IPickerResult result = mPicker.getPickerResult(requestCode, resultCode, data);
        // Handle the case if nothing was picked
        if (result != null) {
            // Do something with the picked file
            Log.d("main", "Link to file '" + result.getName() + ": " + result.getLink());
            return;
        }

        // Handle non-OneDrive picker request
        super.onActivityResult(requestCode, resultCode, data);
    }
}
