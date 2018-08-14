package com.mobfox.sdk.runnables;

import android.content.Context;
import android.os.Handler;
import java.util.concurrent.Callable;

public class Repeater {
    Callable action;
    Context context;
    long delay;
    Handler poster;
    MobFoxRunnable runnable;
    Boolean stop = Boolean.valueOf(false);

    public Repeater(Context context, Handler poster, long delay, Callable action) {
        this.context = context;
        this.poster = poster;
        this.delay = delay;
        this.action = action;
    }

    public void setAction(Callable action) {
        this.action = action;
    }

    public void start() {
        this.stop = Boolean.valueOf(false);
        final Callable _action = this.action;
        final Handler _poster = this.poster;
        final long _delay = this.delay;
        this.runnable = new MobFoxRunnable(this.context) {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void mobFoxRun() {
                /*
                r4 = this;
                r0 = com.mobfox.sdk.runnables.Repeater.this;
                r1 = r0.stop;
                monitor-enter(r1);
                r0 = com.mobfox.sdk.runnables.Repeater.this;	 Catch:{ all -> 0x0023 }
                r0 = r0.stop;	 Catch:{ all -> 0x0023 }
                r0 = r0.booleanValue();	 Catch:{ all -> 0x0023 }
                if (r0 == 0) goto L_0x0011;
            L_0x000f:
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
            L_0x0010:
                return;
            L_0x0011:
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
                r0 = r4;	 Catch:{ Exception -> 0x0026 }
                r0.call();	 Catch:{ Exception -> 0x0026 }
            L_0x0017:
                r0 = r5;
                r1 = com.mobfox.sdk.runnables.Repeater.this;
                r1 = r1.runnable;
                r2 = r6;
                r0.postDelayed(r1, r2);
                goto L_0x0010;
            L_0x0023:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0023 }
                throw r0;
            L_0x0026:
                r0 = move-exception;
                goto L_0x0017;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mobfox.sdk.runnables.Repeater.1.mobFoxRun():void");
            }
        };
        this.poster.postDelayed(this.runnable, this.delay);
    }

    public void stop() {
        synchronized (this.stop) {
            this.stop = Boolean.valueOf(true);
        }
    }
}
