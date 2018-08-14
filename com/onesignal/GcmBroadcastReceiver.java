package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String GCM_RECEIVE_ACTION = "com.google.android.c2dm.intent.RECEIVE";
    private static final String GCM_TYPE = "gcm";
    private static final String MESSAGE_TYPE_EXTRA_KEY = "message_type";

    private static boolean isGcmMessage(Intent intent) {
        if (!GCM_RECEIVE_ACTION.equals(intent.getAction())) {
            return false;
        }
        String messageType = intent.getStringExtra(MESSAGE_TYPE_EXTRA_KEY);
        if (messageType == null || "gcm".equals(messageType)) {
            return true;
        }
        return false;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && !"google.com/iid".equals(bundle.getString("from"))) {
            ProcessedBundleResult processedResult = processOrderBroadcast(context, intent, bundle);
            if (processedResult == null) {
                setResultCode(-1);
            } else if (processedResult.isDup || processedResult.hasExtenderService) {
                abortBroadcast();
            } else if (processedResult.isOneSignalPayload && OneSignal.getFilterOtherGCMReceivers(context)) {
                abortBroadcast();
            } else {
                setResultCode(-1);
            }
        }
    }

    private static ProcessedBundleResult processOrderBroadcast(Context context, Intent intent, Bundle bundle) {
        if (!isGcmMessage(intent)) {
            return null;
        }
        ProcessedBundleResult processedResult = NotificationBundleProcessor.processBundle(context, bundle);
        if (processedResult.processed()) {
            return processedResult;
        }
        Intent intentForService = new Intent();
        intentForService.putExtra("json_payload", NotificationBundleProcessor.bundleAsJSONObject(bundle).toString());
        intentForService.setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName()));
        startWakefulService(context, intentForService);
        return processedResult;
    }
}
