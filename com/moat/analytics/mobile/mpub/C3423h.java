package com.moat.analytics.mobile.mpub;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class C3423h {
    private static final C3423h f8704 = new C3423h();
    private ScheduledFuture<?> f8705;
    private final Map<C3431j, String> f8706 = new WeakHashMap();
    private ScheduledFuture<?> f8707;
    private final ScheduledExecutorService f8708 = Executors.newScheduledThreadPool(1);
    private final Map<d, String> f8709 = new WeakHashMap();

    static C3423h m11684() {
        return f8704;
    }

    private C3423h() {
    }

    final void m11690(final Context context, C3431j c3431j) {
        if (c3431j != null) {
            this.f8706.put(c3431j, "");
            if (this.f8705 == null || this.f8705.isDone()) {
                C3412a.m11642(3, "JSUpdateLooper", this, "Starting metadata reporting loop");
                this.f8705 = this.f8708.scheduleWithFixedDelay(new Runnable(this) {
                    private /* synthetic */ C3423h f8701;

                    public final void run() {
                        try {
                            LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_METADATA"));
                            if (this.f8701.f8706.isEmpty()) {
                                this.f8701.f8705.cancel(true);
                            }
                        } catch (Exception e) {
                            C3442o.m11756(e);
                        }
                    }
                }, 0, 50, TimeUnit.MILLISECONDS);
            }
        }
    }

    final void m11689(C3431j c3431j) {
        if (c3431j != null) {
            C3412a.m11642(3, "JSUpdateLooper", this, "removeSetupNeededBridge" + c3431j.hashCode());
            this.f8706.remove(c3431j);
        }
    }

    final void m11691(final Context context, d dVar) {
        if (dVar != null) {
            C3412a.m11642(3, "JSUpdateLooper", this, "addActiveTracker" + dVar.hashCode());
            if (!this.f8709.containsKey(dVar)) {
                this.f8709.put(dVar, "");
                if (this.f8707 == null || this.f8707.isDone()) {
                    C3412a.m11642(3, "JSUpdateLooper", this, "Starting view update loop");
                    this.f8707 = this.f8708.scheduleWithFixedDelay(new Runnable(this) {
                        private /* synthetic */ C3423h f8703;

                        public final void run() {
                            try {
                                LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(new Intent("UPDATE_VIEW_INFO"));
                                if (this.f8703.f8709.isEmpty()) {
                                    C3412a.m11642(3, "JSUpdateLooper", this.f8703, "No more active trackers");
                                    this.f8703.f8707.cancel(true);
                                }
                            } catch (Exception e) {
                                C3442o.m11756(e);
                            }
                        }
                    }, 0, (long) C3460t.m11803().f8820, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    final void m11692(d dVar) {
        if (dVar != null) {
            C3412a.m11642(3, "JSUpdateLooper", this, "removeActiveTracker" + dVar.hashCode());
            this.f8709.remove(dVar);
        }
    }
}
