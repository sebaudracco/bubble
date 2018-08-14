package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: vungle */
class bw$c extends ThreadPoolExecutor {
    final /* synthetic */ bw f9821a;

    /* compiled from: vungle */
    class C41671 implements ThreadFactory {
        int f9818a = 0;
        final /* synthetic */ bw f9819b;
        final /* synthetic */ String f9820c;

        C41671(bw bwVar, String str) {
            this.f9819b = bwVar;
            this.f9820c = str;
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder append = new StringBuilder().append(this.f9820c);
            int i = this.f9818a;
            this.f9818a = i + 1;
            String stringBuilder = append.append(i).toString();
            Logger.v(Logger.ASYNC_TAG, "starting " + stringBuilder);
            return new Thread(runnable, stringBuilder);
        }
    }

    bw$c(bw bwVar, int i, int i2, int i3, BlockingQueue<Runnable> blockingQueue, String str) {
        this.f9821a = bwVar;
        super(i, i2, (long) i3, TimeUnit.SECONDS, blockingQueue, new C41671(bwVar, str));
        allowCoreThreadTimeOut(true);
    }

    protected void afterExecute(Runnable runnable, Throwable throwable) {
        super.afterExecute(runnable, throwable);
        if (throwable != null) {
            Logger.e(Logger.ASYNC_TAG, "error after executing runnable", throwable);
        }
    }
}
