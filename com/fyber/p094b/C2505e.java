package com.fyber.p094b;

import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: InterruptibleFutureTask */
public final class C2505e<U> extends FutureTask<U> {
    private volatile Thread f6227a;
    private boolean f6228b = false;

    public C2505e(@NonNull Callable<U> callable) {
        super(callable);
    }

    public final void run() {
        this.f6227a = Thread.currentThread();
        super.run();
    }

    public final void m7971a() {
        this.f6228b = true;
        if (this.f6227a != null) {
            this.f6227a.interrupt();
        }
    }

    public final boolean isCancelled() {
        return this.f6228b;
    }
}
