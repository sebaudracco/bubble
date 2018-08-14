package com.core42matters.android.registrar;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

class Logger {
    private static final String TAG = "Registrar";

    Logger() {
    }

    static void m3321v(String message) {
    }

    static void m3320i(String message) {
    }

    static void m3318d(String message) {
    }

    static void m3322w(String message) {
    }

    static void m3319e(String message) {
    }

    static void showToast(Context context, final String message) {
        if (context != null && (context instanceof Activity)) {
            final Activity activity = (Activity) context;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, message, 1).show();
                }
            });
        }
    }
}
