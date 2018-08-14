package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class C3493i {
    private static final C3493i f8913a = new C3493i();
    private final Map<C3498j, String> f8914b = new WeakHashMap();
    private final Map<b, String> f8915c = new WeakHashMap();
    private final ScheduledExecutorService f8916d = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> f8917e;
    private ScheduledFuture<?> f8918f;

    private C3493i() {
    }

    static C3493i m11885a() {
        return f8913a;
    }

    private void m11887a(final Context context) {
        if (this.f8918f == null || this.f8918f.isDone()) {
            C3511p.m11976a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f8918f = this.f8916d.scheduleWithFixedDelay(new Runnable(this) {
                final /* synthetic */ C3493i f8910b;

                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                        if (this.f8910b.f8914b.isEmpty()) {
                            this.f8910b.f8918f.cancel(true);
                        }
                    } catch (Exception e) {
                        C3502m.m11942a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    private void m11889b(final Context context) {
        if (this.f8917e == null || this.f8917e.isDone()) {
            C3511p.m11976a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.f8917e = this.f8916d.scheduleWithFixedDelay(new Runnable(this) {
                final /* synthetic */ C3493i f8912b;

                public void run() {
                    try {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                        if (this.f8912b.f8915c.isEmpty()) {
                            C3511p.m11976a(3, "JSUpdateLooper", this.f8912b, "No more active trackers");
                            this.f8912b.f8917e.cancel(true);
                        }
                    } catch (Exception e) {
                        C3502m.m11942a(e);
                    }
                }
            }, 0, (long) C3532w.m12009a().f8998d, TimeUnit.MILLISECONDS);
        }
    }

    void m11892a(Context context, b bVar) {
        if (bVar != null) {
            C3511p.m11976a(3, "JSUpdateLooper", (Object) this, "addActiveTracker" + bVar.hashCode());
            if (this.f8915c != null && !this.f8915c.containsKey(bVar)) {
                this.f8915c.put(bVar, "");
                m11889b(context);
            }
        }
    }

    void m11893a(Context context, C3498j c3498j) {
        if (this.f8914b != null && c3498j != null) {
            this.f8914b.put(c3498j, "");
            m11887a(context);
        }
    }

    void m11894a(b bVar) {
        if (bVar != null) {
            C3511p.m11976a(3, "JSUpdateLooper", (Object) this, "removeActiveTracker" + bVar.hashCode());
            if (this.f8915c != null) {
                this.f8915c.remove(bVar);
            }
        }
    }

    void m11895a(C3498j c3498j) {
        if (c3498j != null) {
            C3511p.m11976a(3, "JSUpdateLooper", (Object) this, "removeSetupNeededBridge" + c3498j.hashCode());
            if (this.f8914b != null) {
                this.f8914b.remove(c3498j);
            }
        }
    }
}
