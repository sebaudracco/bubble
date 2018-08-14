package com.vungle.publisher;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
class bw$a extends Handler {
    final /* synthetic */ bw f9797a;

    /* compiled from: vungle */
    class C4166a implements Comparable<C4166a>, Runnable {
        final /* synthetic */ bw$a f9793a;
        private final Runnable f9794b;
        private final long f9795c;
        private final bw$b f9796d;

        public /* synthetic */ int compareTo(Object obj) {
            return m12899a((C4166a) obj);
        }

        C4166a(bw$a com_vungle_publisher_bw_a, Runnable runnable, bw$b com_vungle_publisher_bw_b) {
            this(com_vungle_publisher_bw_a, runnable, com_vungle_publisher_bw_b, -1);
        }

        C4166a(bw$a com_vungle_publisher_bw_a, Runnable runnable, bw$b com_vungle_publisher_bw_b, long j) {
            this.f9793a = com_vungle_publisher_bw_a;
            this.f9794b = runnable;
            this.f9795c = j;
            this.f9796d = com_vungle_publisher_bw_b;
        }

        public int m12899a(C4166a c4166a) {
            return this.f9796d.compareTo(c4166a.f9796d);
        }

        public void run() {
            try {
                this.f9794b.run();
                try {
                    if (this.f9795c > 0) {
                        this.f9793a.postDelayed(this, this.f9795c);
                    }
                } catch (Throwable e) {
                    Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e);
                }
            } catch (Throwable e2) {
                Logger.e(Logger.ASYNC_TAG, "error executing " + this, e2);
                try {
                    if (this.f9795c > 0) {
                        this.f9793a.postDelayed(this, this.f9795c);
                    }
                } catch (Throwable e22) {
                    Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e22);
                }
            } catch (Throwable th) {
                try {
                    if (this.f9795c > 0) {
                        this.f9793a.postDelayed(this, this.f9795c);
                    }
                } catch (Throwable e3) {
                    Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e3);
                }
            }
        }

        public boolean equals(Object object) {
            return object != null && (object instanceof C4166a) && m12900b((C4166a) object);
        }

        public boolean m12900b(C4166a c4166a) {
            return this.f9794b.equals(c4166a.f9794b);
        }

        public int hashCode() {
            return this.f9794b.hashCode();
        }

        public String toString() {
            return "{PriorityRunnable:: taskType: " + this.f9796d + ", repeatMillis: " + this.f9795c + "}";
        }
    }

    bw$a(bw bwVar, Looper looper) {
        this.f9797a = bwVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        Object obj = message.obj;
        if (obj == null || !(obj instanceof C4166a)) {
            Logger.w(Logger.ASYNC_TAG, "unhandled message " + message);
            return;
        }
        bw$c a;
        bw$b c = ((C4166a) obj).f9796d;
        if (c == null) {
            a = bw.a(this.f9797a);
        } else {
            switch (bw$1.f9792a[c.ordinal()]) {
                case 1:
                    a = bw.b(this.f9797a);
                    break;
                case 2:
                    bw.c(this.f9797a);
                    break;
            }
            a = bw.a(this.f9797a);
        }
        Logger.d(Logger.ASYNC_TAG, "processing " + obj);
        Logger.v(Logger.ASYNC_TAG, a + ", max pool size " + a.getMaximumPoolSize() + ", largest pool size " + a.getLargestPoolSize());
        a.execute((Runnable) obj);
    }
}
