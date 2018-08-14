package com.appsgeyser.sdk.vast.processor;

import android.text.TextUtils;
import com.appsgeyser.sdk.utils.vast.VASTLog;
import com.appsgeyser.sdk.vast.model.VASTMediaFile;
import com.appsgeyser.sdk.vast.model.VASTModel;
import java.util.List;
import mf.org.apache.xerces.impl.Constants;

public class VASTModelPostValidator {
    private static final String TAG = "VASTModelPostValidator";

    public static boolean validate(VASTModel model, VASTMediaPicker mediaPicker) {
        VASTLog.m2412d(TAG, Constants.DOM_VALIDATE);
        if (validateModel(model)) {
            boolean isValid = false;
            if (mediaPicker != null) {
                VASTMediaFile mediaFile = mediaPicker.pickVideo(model.getMediaFiles());
                if (mediaFile != null) {
                    String url = mediaFile.getValue();
                    if (!TextUtils.isEmpty(url)) {
                        isValid = true;
                        model.setPickedMediaFileURL(url);
                        VASTLog.m2412d(TAG, "mediaPicker selected mediaFile with URL " + url);
                    }
                }
            } else {
                VASTLog.m2417w(TAG, "mediaPicker: We don't have a compatible media file to play.");
            }
            VASTLog.m2412d(TAG, "Validator returns: " + (isValid ? "valid" : "not valid (no media file)"));
            return isValid;
        }
        VASTLog.m2412d(TAG, "Validator returns: not valid (invalid model)");
        return false;
    }

    private static boolean validateModel(VASTModel model) {
        VASTLog.m2412d(TAG, "validateModel");
        boolean isValid = true;
        List<String> impressions = model.getImpressions();
        if (impressions == null || impressions.size() == 0) {
            isValid = false;
        }
        List<VASTMediaFile> mediaFiles = model.getMediaFiles();
        if (mediaFiles != null && mediaFiles.size() != 0) {
            return isValid;
        }
        VASTLog.m2412d(TAG, "Validator error: mediaFile list invalid");
        return false;
    }
}
