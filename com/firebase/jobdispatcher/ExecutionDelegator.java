package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.ref.WeakReference;

class ExecutionDelegator {
    @VisibleForTesting
    static final int JOB_FINISHED = 1;
    static final String TAG = "FJD.ExternalReceiver";
    private final Context context;
    private final JobFinishedCallback jobFinishedCallback;
    private final ResponseHandler responseHandler = new ResponseHandler(Looper.getMainLooper(), new WeakReference(this));
    private final SimpleArrayMap<JobInvocation, JobServiceConnection> serviceConnections = new SimpleArrayMap();

    interface JobFinishedCallback {
        void onJobFinished(@NonNull JobInvocation jobInvocation, int i);
    }

    private static class ResponseHandler extends Handler {
        private final WeakReference<ExecutionDelegator> executionDelegatorReference;

        ResponseHandler(Looper looper, WeakReference<ExecutionDelegator> executionDelegator) {
            super(looper);
            this.executionDelegatorReference = executionDelegator;
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (msg.obj instanceof JobInvocation) {
                        ExecutionDelegator delegator = (ExecutionDelegator) this.executionDelegatorReference.get();
                        if (delegator == null) {
                            Log.wtf(ExecutionDelegator.TAG, "handleMessage: service was unexpectedly GC'd, can't send job result");
                            return;
                        } else {
                            delegator.onJobFinishedMessage((JobInvocation) msg.obj, msg.arg1);
                            return;
                        }
                    }
                    Log.wtf(ExecutionDelegator.TAG, "handleMessage: unknown obj returned");
                    return;
                default:
                    Log.wtf(ExecutionDelegator.TAG, "handleMessage: unknown message type received: " + msg.what);
                    return;
            }
        }
    }

    ExecutionDelegator(Context context, JobFinishedCallback jobFinishedCallback) {
        this.context = context;
        this.jobFinishedCallback = jobFinishedCallback;
    }

    boolean executeJob(JobInvocation jobInvocation) {
        if (jobInvocation == null) {
            return false;
        }
        boolean bindService;
        JobServiceConnection conn = new JobServiceConnection(jobInvocation, this.responseHandler.obtainMessage(1));
        synchronized (this.serviceConnections) {
            if (((JobServiceConnection) this.serviceConnections.put(jobInvocation, conn)) != null) {
                Log.e(TAG, "Received execution request for already running job");
            }
            bindService = this.context.bindService(createBindIntent(jobInvocation), conn, 1);
        }
        return bindService;
    }

    @NonNull
    private Intent createBindIntent(JobParameters jobParameters) {
        Intent execReq = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        execReq.setClassName(this.context, jobParameters.getService());
        return execReq;
    }

    void stopJob(JobInvocation job) {
        synchronized (this.serviceConnections) {
            JobServiceConnection jobServiceConnection = (JobServiceConnection) this.serviceConnections.remove(job);
            if (jobServiceConnection != null) {
                jobServiceConnection.onStop();
                safeUnbindService(jobServiceConnection);
            }
        }
    }

    private void safeUnbindService(JobServiceConnection connection) {
        if (connection != null && connection.isBound()) {
            try {
                this.context.unbindService(connection);
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Error unbinding service: " + e.getMessage());
            }
        }
    }

    private void onJobFinishedMessage(JobInvocation jobInvocation, int result) {
        synchronized (this.serviceConnections) {
            safeUnbindService((JobServiceConnection) this.serviceConnections.remove(jobInvocation));
        }
        this.jobFinishedCallback.onJobFinished(jobInvocation, result);
    }
}
