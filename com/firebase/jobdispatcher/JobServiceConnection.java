package com.firebase.jobdispatcher;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.firebase.jobdispatcher.JobService.LocalBinder;

@VisibleForTesting
class JobServiceConnection implements ServiceConnection {
    private LocalBinder binder;
    private final Message jobFinishedMessage;
    private final JobInvocation jobInvocation;
    private boolean wasMessageUsed = false;

    JobServiceConnection(JobInvocation jobInvocation, Message jobFinishedMessage) {
        this.jobFinishedMessage = jobFinishedMessage;
        this.jobInvocation = jobInvocation;
        this.jobFinishedMessage.obj = this.jobInvocation;
    }

    public synchronized void onServiceConnected(ComponentName name, IBinder service) {
        if (!(service instanceof LocalBinder)) {
            Log.w("FJD.ExternalReceiver", "Unknown service connected");
        } else if (this.wasMessageUsed) {
            Log.w("FJD.ExternalReceiver", "onServiceConnected Duplicate calls. Ignored.");
        } else {
            this.wasMessageUsed = true;
            this.binder = (LocalBinder) service;
            this.binder.getService().start(this.jobInvocation, this.jobFinishedMessage);
        }
    }

    public synchronized void onServiceDisconnected(ComponentName name) {
        this.binder = null;
    }

    synchronized boolean isBound() {
        return this.binder != null;
    }

    synchronized void onStop() {
        if (isBound()) {
            this.binder.getService().stop(this.jobInvocation);
        }
    }
}
