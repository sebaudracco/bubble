package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class JobService extends Service {
    @VisibleForTesting
    static final String ACTION_EXECUTE = "com.firebase.jobdispatcher.ACTION_EXECUTE";
    public static final int RESULT_FAIL_NORETRY = 2;
    public static final int RESULT_FAIL_RETRY = 1;
    public static final int RESULT_SUCCESS = 0;
    static final String TAG = "FJD.JobService";
    private LocalBinder binder = new LocalBinder();
    private final SimpleArrayMap<String, JobCallback> runningJobs = new SimpleArrayMap(1);

    private static final class JobCallback {
        public final Message message;

        private JobCallback(Message message) {
            this.message = message;
        }

        void sendResult(int result) {
            this.message.arg1 = result;
            this.message.sendToTarget();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JobResult {
    }

    class LocalBinder extends Binder {
        LocalBinder() {
        }

        JobService getService() {
            return JobService.this;
        }
    }

    @MainThread
    public abstract boolean onStartJob(JobParameters jobParameters);

    @MainThread
    public abstract boolean onStopJob(JobParameters jobParameters);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.MainThread
    void start(com.firebase.jobdispatcher.JobParameters r10, android.os.Message r11) {
        /*
        r9 = this;
        r3 = r9.runningJobs;
        monitor-enter(r3);
        r2 = r9.runningJobs;	 Catch:{ all -> 0x0053 }
        r4 = r10.getTag();	 Catch:{ all -> 0x0053 }
        r2 = r2.containsKey(r4);	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x002a;
    L_0x000f:
        r2 = "FJD.JobService";
        r4 = java.util.Locale.US;	 Catch:{ all -> 0x0053 }
        r5 = "Job with tag = %s was already running.";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0053 }
        r7 = 0;
        r8 = r10.getTag();	 Catch:{ all -> 0x0053 }
        r6[r7] = r8;	 Catch:{ all -> 0x0053 }
        r4 = java.lang.String.format(r4, r5, r6);	 Catch:{ all -> 0x0053 }
        android.util.Log.w(r2, r4);	 Catch:{ all -> 0x0053 }
        monitor-exit(r3);	 Catch:{ all -> 0x0053 }
    L_0x0029:
        return;
    L_0x002a:
        r2 = r9.runningJobs;	 Catch:{ all -> 0x0053 }
        r4 = r10.getTag();	 Catch:{ all -> 0x0053 }
        r5 = new com.firebase.jobdispatcher.JobService$JobCallback;	 Catch:{ all -> 0x0053 }
        r6 = 0;
        r5.<init>(r11);	 Catch:{ all -> 0x0053 }
        r2.put(r4, r5);	 Catch:{ all -> 0x0053 }
        r1 = r9.onStartJob(r10);	 Catch:{ all -> 0x0053 }
        if (r1 != 0) goto L_0x0051;
    L_0x003f:
        r2 = r9.runningJobs;	 Catch:{ all -> 0x0053 }
        r4 = r10.getTag();	 Catch:{ all -> 0x0053 }
        r0 = r2.remove(r4);	 Catch:{ all -> 0x0053 }
        r0 = (com.firebase.jobdispatcher.JobService.JobCallback) r0;	 Catch:{ all -> 0x0053 }
        if (r0 == 0) goto L_0x0051;
    L_0x004d:
        r2 = 0;
        r0.sendResult(r2);	 Catch:{ all -> 0x0053 }
    L_0x0051:
        monitor-exit(r3);	 Catch:{ all -> 0x0053 }
        goto L_0x0029;
    L_0x0053:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0053 }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.start(com.firebase.jobdispatcher.JobParameters, android.os.Message):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.MainThread
    void stop(com.firebase.jobdispatcher.JobInvocation r6) {
        /*
        r5 = this;
        r3 = r5.runningJobs;
        monitor-enter(r3);
        r2 = r5.runningJobs;	 Catch:{ all -> 0x0032 }
        r4 = r6.getTag();	 Catch:{ all -> 0x0032 }
        r0 = r2.remove(r4);	 Catch:{ all -> 0x0032 }
        r0 = (com.firebase.jobdispatcher.JobService.JobCallback) r0;	 Catch:{ all -> 0x0032 }
        if (r0 != 0) goto L_0x0026;
    L_0x0011:
        r2 = "FJD.JobService";
        r4 = 3;
        r2 = android.util.Log.isLoggable(r2, r4);	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0024;
    L_0x001b:
        r2 = "FJD.JobService";
        r4 = "Provided job has already been executed.";
        android.util.Log.d(r2, r4);	 Catch:{ all -> 0x0032 }
    L_0x0024:
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
    L_0x0025:
        return;
    L_0x0026:
        r1 = r5.onStopJob(r6);	 Catch:{ all -> 0x0032 }
        if (r1 == 0) goto L_0x0035;
    L_0x002c:
        r2 = 1;
    L_0x002d:
        r0.sendResult(r2);	 Catch:{ all -> 0x0032 }
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        goto L_0x0025;
    L_0x0032:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0032 }
        throw r2;
    L_0x0035:
        r2 = 0;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.JobService.stop(com.firebase.jobdispatcher.JobInvocation):void");
    }

    public final void jobFinished(@NonNull JobParameters job, boolean needsReschedule) {
        if (job == null) {
            Log.e(TAG, "jobFinished called with a null JobParameters");
            return;
        }
        synchronized (this.runningJobs) {
            JobCallback jobCallback = (JobCallback) this.runningJobs.remove(job.getTag());
            if (jobCallback != null) {
                jobCallback.sendResult(needsReschedule ? 1 : 0);
            }
        }
    }

    public final int onStartCommand(Intent intent, int flags, int startId) {
        stopSelf(startId);
        return 2;
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        return this.binder;
    }

    public final boolean onUnbind(Intent intent) {
        synchronized (this.runningJobs) {
            for (int i = this.runningJobs.size() - 1; i >= 0; i--) {
                JobCallback callback = (JobCallback) this.runningJobs.get(this.runningJobs.keyAt(i));
                if (callback != null) {
                    callback.sendResult(onStopJob((JobParameters) callback.message.obj) ? 1 : 2);
                }
            }
        }
        return super.onUnbind(intent);
    }

    public final void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public final void onStart(Intent intent, int startId) {
    }

    protected final void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public final void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }
}
