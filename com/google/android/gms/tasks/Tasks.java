package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public final class Tasks {

    interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zza implements zzb {
        private final CountDownLatch zzfd;

        private zza() {
            this.zzfd = new CountDownLatch(1);
        }

        public final void await() throws InterruptedException {
            this.zzfd.await();
        }

        public final boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zzfd.await(j, timeUnit);
        }

        public final void onCanceled() {
            this.zzfd.countDown();
        }

        public final void onFailure(@NonNull Exception exception) {
            this.zzfd.countDown();
        }

        public final void onSuccess(Object obj) {
            this.zzfd.countDown();
        }
    }

    private static final class zzc implements zzb {
        private final Object mLock = new Object();
        private final zzu<Void> zzafh;
        @GuardedBy("mLock")
        private Exception zzagh;
        private final int zzagl;
        @GuardedBy("mLock")
        private int zzagm;
        @GuardedBy("mLock")
        private int zzagn;
        @GuardedBy("mLock")
        private int zzago;
        @GuardedBy("mLock")
        private boolean zzagp;

        public zzc(int i, zzu<Void> com_google_android_gms_tasks_zzu_java_lang_Void) {
            this.zzagl = i;
            this.zzafh = com_google_android_gms_tasks_zzu_java_lang_Void;
        }

        @GuardedBy("mLock")
        private final void zzdu() {
            if ((this.zzagm + this.zzagn) + this.zzago != this.zzagl) {
                return;
            }
            if (this.zzagh != null) {
                zzu com_google_android_gms_tasks_zzu = this.zzafh;
                int i = this.zzagn;
                com_google_android_gms_tasks_zzu.setException(new ExecutionException(i + " out of " + this.zzagl + " underlying tasks failed", this.zzagh));
            } else if (this.zzagp) {
                this.zzafh.zzdp();
            } else {
                this.zzafh.setResult(null);
            }
        }

        public final void onCanceled() {
            synchronized (this.mLock) {
                this.zzago++;
                this.zzagp = true;
                zzdu();
            }
        }

        public final void onFailure(@NonNull Exception exception) {
            synchronized (this.mLock) {
                this.zzagn++;
                this.zzagh = exception;
                zzdu();
            }
        }

        public final void onSuccess(Object obj) {
            synchronized (this.mLock) {
                this.zzagm++;
                zzdu();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object com_google_android_gms_tasks_Tasks_zza = new zza();
        zza(task, com_google_android_gms_tasks_Tasks_zza);
        com_google_android_gms_tasks_Tasks_zza.await();
        return zzb(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object com_google_android_gms_tasks_Tasks_zza = new zza();
        zza(task, com_google_android_gms_tasks_Tasks_zza);
        if (com_google_android_gms_tasks_Tasks_zza.await(j, timeUnit)) {
            return zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        Preconditions.checkNotNull(executor, "Executor must not be null");
        Preconditions.checkNotNull(callable, "Callback must not be null");
        Task com_google_android_gms_tasks_zzu = new zzu();
        executor.execute(new zzv(com_google_android_gms_tasks_zzu, callable));
        return com_google_android_gms_tasks_zzu;
    }

    public static <TResult> Task<TResult> forCanceled() {
        Task com_google_android_gms_tasks_zzu = new zzu();
        com_google_android_gms_tasks_zzu.zzdp();
        return com_google_android_gms_tasks_zzu;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exception) {
        Task com_google_android_gms_tasks_zzu = new zzu();
        com_google_android_gms_tasks_zzu.setException(exception);
        return com_google_android_gms_tasks_zzu;
    }

    public static <TResult> Task<TResult> forResult(TResult tResult) {
        Task com_google_android_gms_tasks_zzu = new zzu();
        com_google_android_gms_tasks_zzu.setResult(tResult);
        return com_google_android_gms_tasks_zzu;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        Task com_google_android_gms_tasks_zzu = new zzu();
        zzb com_google_android_gms_tasks_Tasks_zzc = new zzc(collection.size(), com_google_android_gms_tasks_zzu);
        for (Task task2 : collection) {
            zza(task2, com_google_android_gms_tasks_Tasks_zzc);
        }
        return com_google_android_gms_tasks_zzu;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll(Arrays.asList(taskArr));
    }

    public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> collection) {
        return whenAll((Collection) collection).continueWithTask(new zzx(collection));
    }

    public static Task<List<Task<?>>> whenAllComplete(Task<?>... taskArr) {
        return whenAllComplete(Arrays.asList(taskArr));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Collection<? extends Task<?>> collection) {
        return whenAll((Collection) collection).continueWith(new zzw(collection));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Task<?>... taskArr) {
        return whenAllSuccess(Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb com_google_android_gms_tasks_Tasks_zzb) {
        task.addOnSuccessListener(TaskExecutors.zzagd, com_google_android_gms_tasks_Tasks_zzb);
        task.addOnFailureListener(TaskExecutors.zzagd, com_google_android_gms_tasks_Tasks_zzb);
        task.addOnCanceledListener(TaskExecutors.zzagd, com_google_android_gms_tasks_Tasks_zzb);
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(task.getException());
    }
}
