package com.vungle.publisher;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.mm.a;
import com.vungle.publisher.ro.b;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class my extends mm<rf> {
    final Handler f3142n = new Handler();
    AtomicInteger f3143o = new AtomicInteger(0);
    AtomicInteger f3144p = new AtomicInteger(0);
    ro f3145q;
    og f3146r;
    @Inject
    rf$a f3147s;
    @Inject
    C1657a f3148t;
    @Inject
    rr f3149u;
    @Inject
    ro$a f3150v;
    @Inject
    og$a f3151w;

    @Singleton
    /* compiled from: vungle */
    public static class C1656a extends a<my> {
        @Inject
        Provider<my> f3139a;

        protected /* synthetic */ mm m4403a() {
            return m4405c();
        }

        @Inject
        C1656a() {
        }

        protected my m4405c() {
            return (my) this.f3139a.get();
        }

        protected String m4404b() {
            return "fullScreenMraidFragment";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class C1658b extends pi {
        my f3141a;

        @Singleton
        /* compiled from: vungle */
        public static class C1657a {
            @Inject
            C1658b f3140a;

            @Inject
            C1657a() {
            }

            public C1658b m4406a(my myVar) {
                this.f3140a.f3141a = myVar;
                return this.f3140a;
            }
        }

        @Inject
        C1658b() {
        }

        public void onEvent(st event) {
            b a = event.a();
            Logger.v(Logger.EVENT_TAG, "set close region: " + a);
            this.f3141a.m4413a(a, 2, 0);
        }

        public void onEvent(su event) {
            boolean a = event.a();
            Logger.v(Logger.EVENT_TAG, "use custom privacy icon? " + a);
            this.f3141a.m4415a(!a, 2, 0);
        }

        public void onEvent(ss event) {
            Logger.v(Logger.EVENT_TAG, "throw incentivized dialog");
            this.f3141a.m4408e();
        }

        public void onEvent(sq event) {
            this.f3141a.m4413a(b.a, 1, 500);
            this.f3141a.m4415a(true, 1, 500);
        }
    }

    protected /* synthetic */ mj mo3000a(String str, p pVar, x xVar) {
        return m4416b(str, pVar, xVar);
    }

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            if (Injector.getInstance().d()) {
                Injector.c().m4201a(this);
                this.f3148t.m4406a(this).register();
                return;
            }
            Logger.w(Logger.AD_TAG, "SDK not initialized");
            getActivity().finish();
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "exception while creating Mraid ad fragment", e);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.f3149u.m4621a(this.f);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) super.onCreateView(inflater, container, savedInstanceState);
        try {
            this.f3145q = this.f3150v.m4619a();
            relativeLayout.addView(this.f3145q);
            LayoutParams layoutParams = (LayoutParams) this.f3145q.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            this.f3150v.m4620a(this.f3145q);
            m4413a(b.a, 0, 3000);
            this.f3145q.setOnClickListener(mz.m4420a(this));
            this.f3146r = this.f3151w.m4471a(this.m, true);
            relativeLayout.addView(this.f3146r);
            layoutParams = (LayoutParams) this.f3146r.getLayoutParams();
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            m4415a(true, 0, 3000);
            relativeLayout.setBackgroundColor(0);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error creating MraidAdFragment", e);
        }
        return relativeLayout;
    }

    /* synthetic */ void m4412a(View view) {
        m4419d();
    }

    void m4413a(b bVar, int i, long j) {
        this.f3142n.postDelayed(new c(this, bVar, i), j);
    }

    void m4415a(boolean z, int i, long j) {
        this.f3142n.postDelayed(new e(this, Integer.valueOf(z ? 0 : 8), i), j);
    }

    public boolean m4418c() {
        return ((rf) this.f).b();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f3148t != null) {
            this.f3148t.m4406a(this).unregister();
        }
    }

    public void onResume() {
        try {
            super.onResume();
            this.f3149u.m4628a(true, this.f);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error resuming mraid ad", e);
        }
    }

    public void onPause() {
        try {
            super.onPause();
            this.f3149u.m4628a(false, this.f);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error pausing mraid ad", e);
        }
    }

    public void mo3002a(boolean z) {
        super.mo3002a(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    void m4419d() {
        this.k.m4568a(new sj());
    }

    protected rf m4416b(String str, p pVar, x xVar) {
        this.f3149u.m4627a(xVar);
        return (rf) this.f3147s.m4382a(str, pVar, this.c, xVar);
    }

    public void mo3001a() {
        try {
            if (!((rf) this.f).a()) {
                m4419d();
            }
        } catch (Throwable e) {
            Logger.w(Logger.AD_TAG, "exception in onBackPressed", e);
        }
    }

    public String mo3003b() {
        return "fullScreenMraidFragment";
    }

    private void m4408e() {
        this.a = m4409f();
        this.a.show();
    }

    private AlertDialog m4409f() {
        if (this.a != null) {
            return this.a;
        }
        return this.d.m4391a(getActivity(), this.h, new 1(this));
    }
}
