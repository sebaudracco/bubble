package com.fyber.p094b;

import android.os.Process;
import android.support.annotation.NonNull;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: ExecutorService */
public final class C2504d extends ThreadPoolExecutor {
    private static final int f6225a;
    private static final int f6226b;

    /* compiled from: ExecutorService */
    private static class C2500a extends Thread {
        public C2500a(Runnable runnable) {
            super(runnable);
            setName("FyberExecutorThread");
        }

        public final void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    /* compiled from: ExecutorService */
    static class C2501b implements ThreadFactory {
        C2501b() {
        }

        public final Thread newThread(@NonNull Runnable runnable) {
            return new C2500a(runnable);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f6225a = availableProcessors;
        f6226b = (availableProcessors * 2) + 1;
    }

    public C2504d() {
        super(f6226b, f6226b, 0, TimeUnit.SECONDS, new LinkedBlockingQueue(), new C2501b());
    }
}
