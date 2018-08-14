package com.vungle.publisher;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.vungle.publisher.log.Logger;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/* compiled from: vungle */
public class ns extends ni<jn> {
    @Inject
    mv f3168q;
    @Inject
    C1661a f3169r;

    @Singleton
    /* compiled from: vungle */
    public static class C1662a extends me<ns> {
        private boolean f3167b;

        @Singleton
        /* compiled from: vungle */
        public static class C1661a {
            @Inject
            C1662a f3166a;

            @Inject
            C1661a() {
            }

            public C1662a m4439a(ns nsVar) {
                this.f3166a.a = nsVar;
                this.f3166a.f3167b = false;
                return this.f3166a;
            }
        }

        @Inject
        C1662a() {
        }

        public void onEvent(sj event) {
            Logger.v(Logger.EVENT_TAG, "mraidAd.onClose()");
            this.eventBus.m4568a(new si(rx.b));
            ((ns) this.a).m4374a(true, this.f3167b);
        }

        public void onEvent(sf event) {
            Logger.v(Logger.EVENT_TAG, "processing closeFlexViewAd received from API call");
            if (event.a().equals(((ns) this.a).g)) {
                ((ns) this.a).m4374a(true, false);
                this.eventBus.m4568a(new si(rx.c));
                this.eventBus.m4568a(new si(rx.b));
            }
        }

        public void onEvent(so event) {
            ((ns) this.a).m4368a(event.a());
        }

        public void onEvent(sk event) {
            this.eventBus.m4568a(new si(rx.a));
            this.f3167b = true;
        }

        public void onEvent(sl event) {
            ((ns) this.a).m4374a(false, this.f3167b);
        }

        public void onEvent(sr event) {
            try {
                ry a = event.a();
                boolean b = event.b();
                if (a != ry.c) {
                    Logger.v(Logger.EVENT_TAG, "force mraid orientation: " + a);
                    ((ns) this.a).m4366a(a.a());
                } else if (b) {
                    ((ns) this.a).m4366a(4);
                } else {
                    m4441a();
                }
            } catch (Throwable e) {
                Logger.e(Logger.EVENT_TAG, "error setting mraid orientation", e);
            }
        }

        void m4441a() {
            if (VERSION.SDK_INT >= 18) {
                ((ns) this.a).m4366a(14);
            } else {
                ((ns) this.a).m4366a(5);
            }
        }
    }

    @Inject
    ns() {
    }

    protected me<?> mo3011a() {
        return this.f3169r.m4439a(this);
    }

    public void m4447a(VungleAdActivity vungleAdActivity, jn jnVar, String str, p pVar, Bundle bundle) {
        super.mo3012a(vungleAdActivity, (dq) jnVar, str, pVar, bundle);
        vungleAdActivity.getWindow().addFlags(32);
        Display defaultDisplay = ((WindowManager) vungleAdActivity.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getRotation() == 0 || defaultDisplay.getRotation() == 2) {
            m4442b(true);
        } else {
            m4442b(false);
        }
        if (pVar.getFlexViewCloseTimeInSec() > 0) {
            Observable.interval((long) pVar.getFlexViewCloseTimeInSec(), TimeUnit.SECONDS).takeUntil(this.h).take(1).subscribe(nt.m4449a(this));
        }
    }

    /* synthetic */ void m4448a(Long l) {
        m4374a(true, false);
        this.i.m4568a(new si(rx.d));
        this.i.m4568a(new si(rx.b));
    }

    public void mo3013a(Configuration configuration) {
        super.mo3013a(configuration);
        if (configuration.orientation == 1) {
            m4442b(true);
        } else if (configuration.orientation == 2) {
            m4442b(false);
        }
    }

    private void m4442b(boolean z) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity activity = (Activity) this.b.get();
        if (activity != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (z) {
                activity.getWindow().setLayout(displayMetrics.widthPixels, (int) Math.round(((double) displayMetrics.widthPixels) * 0.5625d));
                activity.getWindow().setGravity(83);
            } else {
                activity.getWindow().setLayout((int) Math.round(((double) displayMetrics.heightPixels) * 0.5625d), displayMetrics.heightPixels);
                activity.getWindow().setGravity(5);
            }
        }
        this.f3168q.m4397a(displayMetrics, z);
    }
}
