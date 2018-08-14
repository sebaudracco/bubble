package com.inmobi.ads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.inmobi.ads.bv.C3024a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;

/* compiled from: DecorViewVisibilityTracker */
final class C3076p extends bv {
    private static final String f7514b = C3076p.class.getSimpleName();
    @NonNull
    private OnPreDrawListener f7515c;
    @NonNull
    private final WeakReference<View> f7516d;

    /* compiled from: DecorViewVisibilityTracker */
    class C30751 implements OnPreDrawListener {
        final /* synthetic */ C3076p f7513a;

        C30751(C3076p c3076p) {
            this.f7513a = c3076p;
        }

        public boolean onPreDraw() {
            this.f7513a.m9500i();
            return true;
        }
    }

    public C3076p(@NonNull C3024a c3024a, @NonNull Activity activity) {
        super(c3024a);
        View decorView = activity.getWindow().getDecorView();
        this.f7516d = new WeakReference(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.f7515c = new C30751(this);
            viewTreeObserver.addOnPreDrawListener(this.f7515c);
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7514b, "Visibility Tracker was unable to track views because the root view tree observer was not alive");
    }

    protected int mo6211a() {
        return 100;
    }

    protected void mo6212b() {
    }

    public void mo6246c() {
        if (!m9497f()) {
            m9943k();
            super.mo6246c();
        }
    }

    public void mo6247d() {
        if (m9497f()) {
            m9942j();
            super.mo6247d();
        }
    }

    private void m9942j() {
        View view = (View) this.f7516d.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(this.f7515c);
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7514b, "Visibility Tracker was unable to track views because the root view tree observer was not alive");
            }
        }
    }

    private void m9943k() {
        View view = (View) this.f7516d.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f7515c);
            }
        }
    }

    protected void mo6248e() {
        m9943k();
        super.mo6248e();
    }
}
