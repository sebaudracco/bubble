package com.onesignal;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Extender;
import com.onesignal.OneSignal.LOG_LEVEL;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class NotificationExtenderService extends IntentService {
    private OverrideSettings currentBaseOverrideSettings = null;
    private JSONObject currentJsonPayload;
    private boolean currentlyRestoring;
    private OSNotificationDisplayedResult osNotificationDisplayedResult;
    private Long restoreTimestamp;

    public static class OverrideSettings {
        public Integer androidNotificationId;
        public Extender extender;

        void override(OverrideSettings overrideSettings) {
            if (overrideSettings != null && overrideSettings.androidNotificationId != null) {
                this.androidNotificationId = overrideSettings.androidNotificationId;
            }
        }
    }

    protected abstract boolean onNotificationProcessing(OSNotificationReceivedResult oSNotificationReceivedResult);

    public NotificationExtenderService() {
        super("NotificationExtenderService");
        setIntentRedelivery(true);
    }

    protected final OSNotificationDisplayedResult displayNotification(OverrideSettings overrideSettings) {
        if (this.osNotificationDisplayedResult != null || overrideSettings == null) {
            return null;
        }
        overrideSettings.override(this.currentBaseOverrideSettings);
        this.osNotificationDisplayedResult = new OSNotificationDisplayedResult();
        NotificationGenerationJob notifJob = createNotifJobFromCurrent();
        notifJob.overrideSettings = overrideSettings;
        this.osNotificationDisplayedResult.androidNotificationId = NotificationBundleProcessor.Process(notifJob);
        return this.osNotificationDisplayedResult;
    }

    protected final void onHandleIntent(Intent intent) {
        processIntent(intent);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void processIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            OneSignal.Log(LOG_LEVEL.ERROR, "No extras sent to NotificationExtenderService in its Intent!\n" + intent);
            return;
        }
        String jsonStrPayload = bundle.getString("json_payload");
        if (jsonStrPayload == null) {
            OneSignal.Log(LOG_LEVEL.ERROR, "json_payload key is nonexistent from bundle passed to NotificationExtenderService: " + bundle);
            return;
        }
        try {
            this.currentJsonPayload = new JSONObject(jsonStrPayload);
            this.currentlyRestoring = bundle.getBoolean("restoring", false);
            if (bundle.containsKey("android_notif_id")) {
                this.currentBaseOverrideSettings = new OverrideSettings();
                this.currentBaseOverrideSettings.androidNotificationId = Integer.valueOf(bundle.getInt("android_notif_id"));
            }
            if (this.currentlyRestoring || !OneSignal.notValidOrDuplicated(this, this.currentJsonPayload)) {
                this.restoreTimestamp = Long.valueOf(bundle.getLong("timestamp"));
                processJsonObject(this.currentJsonPayload, this.currentlyRestoring);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void processJsonObject(JSONObject currentJsonPayload, boolean restoring) {
        OSNotificationReceivedResult receivedResult = new OSNotificationReceivedResult();
        receivedResult.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(currentJsonPayload);
        receivedResult.restoring = restoring;
        receivedResult.isAppInFocus = OneSignal.isAppActive();
        this.osNotificationDisplayedResult = null;
        boolean developerProcessed = false;
        try {
            developerProcessed = onNotificationProcessing(receivedResult);
        } catch (Throwable t) {
            if (this.osNotificationDisplayedResult == null) {
                OneSignal.Log(LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Displaying normal OneSignal notification.", t);
            } else {
                OneSignal.Log(LOG_LEVEL.ERROR, "onNotificationProcessing throw an exception. Extended notification displayed but custom processing did not finish.", t);
            }
        }
        if (this.osNotificationDisplayedResult == null) {
            boolean display;
            if (!developerProcessed) {
                boolean z;
                if ("".equals(currentJsonPayload.optString("alert"))) {
                    z = false;
                } else {
                    z = true;
                }
                if (NotificationBundleProcessor.shouldDisplay(z)) {
                    display = true;
                    if (!display) {
                        NotificationBundleProcessor.Process(createNotifJobFromCurrent());
                    } else if (!restoring) {
                        NotificationBundleProcessor.saveNotification((Context) this, currentJsonPayload, true, -1);
                        OneSignal.handleNotificationReceived(NotificationBundleProcessor.newJsonArray(currentJsonPayload), false, false);
                    }
                }
            }
            display = false;
            if (!display) {
                NotificationBundleProcessor.Process(createNotifJobFromCurrent());
            } else if (!restoring) {
                NotificationBundleProcessor.saveNotification((Context) this, currentJsonPayload, true, -1);
                OneSignal.handleNotificationReceived(NotificationBundleProcessor.newJsonArray(currentJsonPayload), false, false);
            }
        }
    }

    static Intent getIntent(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent().setAction("com.onesignal.NotificationExtender").setPackage(context.getPackageName());
        if (packageManager.queryIntentServices(intent, 128).size() < 1) {
            return null;
        }
        return intent;
    }

    private NotificationGenerationJob createNotifJobFromCurrent() {
        NotificationGenerationJob notifJob = new NotificationGenerationJob(this);
        notifJob.restoring = this.currentlyRestoring;
        notifJob.jsonPayload = this.currentJsonPayload;
        notifJob.shownTimeStamp = this.restoreTimestamp;
        notifJob.overrideSettings = this.currentBaseOverrideSettings;
        return notifJob;
    }
}
