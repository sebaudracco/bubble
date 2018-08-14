package com.firebase.jobdispatcher;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

@TargetApi(21)
class GooglePlayMessageHandler extends Handler {
    private static final int MSG_INIT = 4;
    static final int MSG_RESULT = 3;
    static final int MSG_START_EXEC = 1;
    static final int MSG_STOP_EXEC = 2;
    private final GooglePlayReceiver googlePlayReceiver;

    public GooglePlayMessageHandler(Looper mainLooper, GooglePlayReceiver googlePlayReceiver) {
        super(mainLooper);
        this.googlePlayReceiver = googlePlayReceiver;
    }

    public void handleMessage(Message message) {
        if (message != null) {
            try {
                ((AppOpsManager) this.googlePlayReceiver.getApplicationContext().getSystemService("appops")).checkPackage(message.sendingUid, "com.google.android.gms");
                switch (message.what) {
                    case 1:
                        handleStartMessage(message);
                        return;
                    case 2:
                        handleStopMessage(message);
                        return;
                    case 4:
                        return;
                    default:
                        Log.e("FJD.GooglePlayReceiver", "Unrecognized message received: " + message);
                        return;
                }
            } catch (SecurityException e) {
                Log.e("FJD.GooglePlayReceiver", "Message was not sent from GCM.");
            }
        }
    }

    private void handleStartMessage(Message message) {
        Bundle data = message.getData();
        Messenger replyTo = message.replyTo;
        String tag = data.getString("tag");
        if (replyTo != null && tag != null) {
            this.googlePlayReceiver.getExecutionDelegator().executeJob(this.googlePlayReceiver.prepareJob(new GooglePlayMessengerCallback(replyTo, tag), data));
        } else if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
            Log.d("FJD.GooglePlayReceiver", "Invalid start execution message.");
        }
    }

    private void handleStopMessage(Message message) {
        JobInvocation$Builder builder = GooglePlayReceiver.getJobCoder().decode(message.getData());
        if (builder != null) {
            this.googlePlayReceiver.getExecutionDelegator().stopJob(builder.build());
        } else if (Log.isLoggable("FJD.GooglePlayReceiver", 3)) {
            Log.d("FJD.GooglePlayReceiver", "Invalid stop execution message.");
        }
    }
}
