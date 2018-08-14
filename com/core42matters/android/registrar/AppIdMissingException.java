package com.core42matters.android.registrar;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class AppIdMissingException extends Exception {
    private static final String ERROR_MESSAGE = "Can not find app id in the AndroidManifest.xml of";
    final String packageName;

    public AppIdMissingException(String packageName) {
        super(ERROR_MESSAGE + packageName);
        this.packageName = packageName;
    }

    void showToast(Context context) {
        if (context != null && (context instanceof Activity)) {
            final Activity activity = (Activity) context;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, AppIdMissingException.ERROR_MESSAGE + AppIdMissingException.this.packageName, 0).show();
                }
            });
        }
    }
}
