package com.appsgeyser.sdk.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.app.AppCompatActivity;
import com.appsgeyser.sdk.C1195R;

public class AppsgeyserProgressDialog extends Dialog {
    public AppsgeyserProgressDialog(Context context) {
        super(context);
        setCancelable(false);
        setContentView(C1195R.layout.appsgeysersdk_progress_dialog);
    }

    public void show(Context context) {
        try {
            AppCompatActivity activity = (AppCompatActivity) context;
            if (VERSION.SDK_INT >= 17) {
                if (!activity.isDestroyed() || !activity.isFinishing()) {
                    super.show();
                }
            } else if (!activity.isFinishing()) {
                super.show();
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
