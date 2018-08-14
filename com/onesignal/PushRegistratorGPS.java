package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.onesignal.PushRegistrator.RegisteredHandler;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;

public class PushRegistratorGPS implements PushRegistrator {
    private static int GCM_RETRY_COUNT = 5;
    private Context appContext;
    private RegisteredHandler registeredHandler;

    class C39201 implements Runnable {
        C39201() {
        }

        public void run() {
            final Activity activity = ActivityLifecycleHandler.curActivity;
            if (activity != null && !OneSignal.mInitBuilder.mDisableGmsMissingPrompt) {
                String alertBodyText = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.");
                String alertButtonUpdate = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_update", "Update");
                String alertButtonSkip = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_skip", "Skip");
                new Builder(activity).setMessage(alertBodyText).setPositiveButton(alertButtonUpdate, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            GooglePlayServicesUtil.getErrorPendingIntent(GooglePlayServicesUtil.isGooglePlayServicesAvailable(PushRegistratorGPS.this.appContext), activity, 0).send();
                        } catch (CanceledException e) {
                        } catch (Throwable e2) {
                            e2.printStackTrace();
                        }
                    }
                }).setNegativeButton(alertButtonSkip, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Editor editor = OneSignal.getGcmPreferences(activity).edit();
                        editor.putBoolean("GT_DO_NOT_SHOW_MISSING_GPS", true);
                        editor.commit();
                    }
                }).setNeutralButton(OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_close", HTTP.CONN_CLOSE), null).create().show();
            }
        }
    }

    public void registerForPush(Context context, String googleProjectNumber, RegisteredHandler callback) {
        this.appContext = context;
        this.registeredHandler = callback;
        if (googleProjectNumber == null || googleProjectNumber.equals("REMOTE")) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Missing Google Project number!\nPlease enter a Google Project number / Sender ID on under App Settings > Android > Configuration on the OneSignal dashboard.");
            this.registeredHandler.complete(null, -6);
            return;
        }
        internalRegisterForPush(googleProjectNumber);
    }

    private void internalRegisterForPush(String googleProjectNumber) {
        try {
            if (isGMSInstalledAndEnabled()) {
                registerInBackground(googleProjectNumber);
                return;
            }
            OneSignal.Log(LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
            this.registeredHandler.complete(null, -7);
        } catch (Throwable t) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Could not register with GCM due to an error with the AndroidManifest.xml file or with 'Google Play services'.", t);
            this.registeredHandler.complete(null, -8);
        }
    }

    private boolean isGooglePlayStoreInstalled() {
        try {
            PackageManager pm = this.appContext.getPackageManager();
            String label = (String) pm.getPackageInfo("com.android.vending", 1).applicationInfo.loadLabel(pm);
            if (label == null || label.equals("Market")) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private boolean isGMSInstalledAndEnabled() {
        boolean z = false;
        try {
            PackageInfo info = this.appContext.getPackageManager().getPackageInfo("com.google.android.gms", 1);
            if (!info.applicationInfo.enabled && isGooglePlayStoreInstalled()) {
                if (!OneSignal.getGcmPreferences(this.appContext).getBoolean("GT_DO_NOT_SHOW_MISSING_GPS", false)) {
                    try {
                        ShowUpdateGPSDialog();
                    } catch (Throwable th) {
                    }
                }
                return z;
            }
            z = info.applicationInfo.enabled;
        } catch (NameNotFoundException e) {
        }
        return z;
    }

    private void ShowUpdateGPSDialog() {
        OSUtils.runOnMainUIThread(new C39201());
    }

    private void registerInBackground(final String googleProjectNumber) {
        new Thread(new Runnable() {
            public void run() {
                boolean firedComplete = false;
                int currentRetry = 0;
                while (currentRetry < PushRegistratorGPS.GCM_RETRY_COUNT) {
                    try {
                        String registrationId = GoogleCloudMessaging.getInstance(PushRegistratorGPS.this.appContext).register(new String[]{googleProjectNumber});
                        OneSignal.Log(LOG_LEVEL.INFO, "Device registered, Google Registration ID = " + registrationId);
                        PushRegistratorGPS.this.registeredHandler.complete(registrationId, 1);
                        return;
                    } catch (IOException e) {
                        if (!"SERVICE_NOT_AVAILABLE".equals(e.getMessage())) {
                            OneSignal.Log(LOG_LEVEL.ERROR, "Error Getting Google Registration ID", e);
                            if (!firedComplete) {
                                PushRegistratorGPS.this.registeredHandler.complete(null, -11);
                                return;
                            }
                            return;
                        } else if (currentRetry >= PushRegistratorGPS.GCM_RETRY_COUNT - 1) {
                            OneSignal.Log(LOG_LEVEL.ERROR, "GCM_RETRY_COUNT of " + PushRegistratorGPS.GCM_RETRY_COUNT + " exceed! Could not get a Google Registration Id", e);
                        } else {
                            OneSignal.Log(LOG_LEVEL.INFO, "Google Play services returned SERVICE_NOT_AVAILABLE error. Current retry count: " + currentRetry, e);
                            if (currentRetry == 2) {
                                PushRegistratorGPS.this.registeredHandler.complete(null, -9);
                                firedComplete = true;
                            }
                            Thread.sleep((long) ((currentRetry + 1) * 10000));
                        }
                    } catch (Throwable th) {
                    }
                }
                return;
                currentRetry++;
            }
        }).start();
    }
}
