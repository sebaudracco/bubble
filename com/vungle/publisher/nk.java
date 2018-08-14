package com.vungle.publisher;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowInsets;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public class nk extends ni<dq> {
    @Inject
    C1659a f3163q;

    @Singleton
    /* compiled from: vungle */
    public static class C1660a extends me<nk> {
        private boolean f3162b;

        @Singleton
        /* compiled from: vungle */
        public static class C1659a {
            @Inject
            C1660a f3161a;

            @Inject
            C1659a() {
            }

            public C1660a m4429a(nk nkVar) {
                this.f3161a.a = nkVar;
                this.f3161a.f3162b = false;
                return this.f3161a;
            }
        }

        @Inject
        C1660a() {
        }

        public void onEvent(sj event) {
            Logger.v(Logger.EVENT_TAG, "mraidAd.onClose()");
            this.eventBus.m4568a(new si(rx.b));
            ((nk) this.a).m4374a(true, this.f3162b);
        }

        public void onEvent(so event) {
            ((nk) this.a).m4368a(event.a());
        }

        public void onEvent(sk event) {
            this.eventBus.m4568a(new si(rx.a));
            this.f3162b = true;
        }

        public void onEvent(sl event) {
            ((nk) this.a).m4374a(false, this.f3162b);
        }

        public void onEvent(sr event) {
            try {
                ry a = event.a();
                boolean b = event.b();
                if (a != ry.c) {
                    Logger.v(Logger.EVENT_TAG, "force mraid orientation: " + a);
                    ((nk) this.a).m4366a(a.a());
                } else if (b) {
                    ((nk) this.a).m4366a(4);
                } else {
                    m4431a();
                }
            } catch (Throwable e) {
                Logger.e(Logger.EVENT_TAG, "error setting mraid orientation", e);
            }
        }

        void m4431a() {
            if (VERSION.SDK_INT >= 18) {
                ((nk) this.a).m4366a(14);
            } else {
                ((nk) this.a).m4366a(5);
            }
        }
    }

    @Inject
    nk() {
    }

    public void mo3012a(VungleAdActivity vungleAdActivity, dq dqVar, String str, p pVar, Bundle bundle) {
        super.mo3012a(vungleAdActivity, dqVar, str, pVar, bundle);
        Logger.d(Logger.AD_TAG, "create mraid ad");
        if (VERSION.SDK_INT >= 24) {
            m4432e();
        }
    }

    protected me<?> mo3011a() {
        return this.f3163q.m4429a(this);
    }

    protected yj<?> mo3010b() {
        return this.n.m4915a((dq) this.a);
    }

    @RequiresApi(24)
    private void m4432e() {
        View view = (View) this.m.get();
        if (view != null) {
            view.setOnApplyWindowInsetsListener(nl.m4438a(this, view));
        }
    }

    /* synthetic */ WindowInsets m4433a(View view, View view2, WindowInsets windowInsets) {
        int i = 0;
        try {
            int stableInsetLeft;
            int stableInsetTop;
            int stableInsetBottom;
            if (this.l.get() != null && ((Activity) this.l.get()).isInMultiWindowMode() && windowInsets.hasStableInsets()) {
                stableInsetLeft = windowInsets.getStableInsetLeft();
                stableInsetTop = windowInsets.getStableInsetTop();
                i = windowInsets.getStableInsetRight();
                stableInsetBottom = windowInsets.getStableInsetBottom();
            } else {
                stableInsetBottom = 0;
                stableInsetTop = 0;
                stableInsetLeft = 0;
            }
            view.getRootView().setPadding(stableInsetLeft, stableInsetTop, i, stableInsetBottom);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "Exception setting root view padding to avoid system controls overlap", e);
        }
        return windowInsets;
    }
}
