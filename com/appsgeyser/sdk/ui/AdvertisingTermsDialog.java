package com.appsgeyser.sdk.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Process;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.configuration.Configuration;

public class AdvertisingTermsDialog {
    private static final String PREFERENCES_PREFIX = (AdvertisingTermsDialog.class.getSimpleName() + "AlreadyShown");
    private static volatile boolean onScreen = false;
    private final AlertDialog dialog;

    class C13121 implements OnClickListener {
        C13121() {
        }

        public void onClick(DialogInterface dialog, int id) {
            AdvertisingTermsDialog.onScreen = false;
            Process.killProcess(Process.myPid());
        }
    }

    class C13132 implements OnClickListener {
        C13132() {
        }

        public void onClick(DialogInterface dialog, int id) {
            AdvertisingTermsDialog.onScreen = false;
            AdvertisingTermsDialog.this.setAlreadyShown();
            dialog.dismiss();
        }
    }

    public AdvertisingTermsDialog(Activity activity) {
        Builder alertDialogBuilder = new Builder(activity);
        alertDialogBuilder.setTitle(C1195R.string.appsgeysersdk_advertising_terms).setMessage(C1195R.string.appsgeysersdk_advertising_terms_message).setCancelable(false).setPositiveButton(C1195R.string.appsgeysersdk_accept, new C13132()).setNegativeButton(C1195R.string.appsgeysersdk_refuse, new C13121());
        this.dialog = alertDialogBuilder.create();
    }

    public void show(boolean isAdvertisingTermsDialogEnabled) {
        if (isAdvertisingTermsDialogEnabled && !isAlreadyShown()) {
            onScreen = true;
            this.dialog.show();
        }
    }

    public static boolean isOnScreen() {
        return onScreen;
    }

    private void setAlreadyShown() {
        Configuration.getInstance(this.dialog.getContext()).getSettingsCoder().savePrefBoolean(PREFERENCES_PREFIX, true);
    }

    private boolean isAlreadyShown() {
        return Configuration.getInstance(this.dialog.getContext()).getSettingsCoder().getPrefBoolean(PREFERENCES_PREFIX, false);
    }
}
