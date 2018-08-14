package com.vungle.publisher;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.my.C1656a;
import com.vungle.publisher.yn.C1677a;
import java.lang.ref.WeakReference;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class ni<MA extends dq> extends mg<MA> {
    WeakReference<Activity> f3153l;
    WeakReference<View> f3154m;
    @Inject
    C1677a f3155n;
    @Inject
    C1656a f3156o;
    @Inject
    C1613o f3157p;
    private my f3158q;

    public void mo3012a(VungleAdActivity vungleAdActivity, MA ma, String str, p pVar, Bundle bundle) {
        Logger.d(Logger.AD_TAG, "create mraid ad");
        this.f3153l = new WeakReference(vungleAdActivity);
        this.f3154m = new WeakReference(((Activity) this.f3153l.get()).getWindow().getDecorView());
        m4424a(pVar);
        super.mo3009a(vungleAdActivity, ma, str, pVar, bundle);
        s b = this.f3157p.m3923b(str);
        boolean z = b != null && b.c;
        this.f3158q = (my) this.f3156o.a(vungleAdActivity, (String) ma.c_(), ma.s(), pVar, z, x.a(ma.s));
        vungleAdActivity.setRequestedOrientation(4);
        vungleAdActivity.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        m4427d();
    }

    void m4427d() {
        if (this.f3158q == null) {
            m4374a(true, false);
        } else {
            m4372a(this.f3158q);
        }
    }

    public void mo3008a(VungleAdActivity vungleAdActivity) {
        if (!this.f3158q.m4418c()) {
            vungleAdActivity.finish();
        }
        super.mo3008a(vungleAdActivity);
    }

    protected yj<?> mo3010b() {
        return this.f3155n.m4915a((dq) this.a);
    }

    void m4424a(p pVar) {
        View view = (View) this.f3154m.get();
        if (view != null && VERSION.SDK_INT >= 19 && pVar.isImmersiveMode()) {
            view.setSystemUiVisibility(5894);
            view.setOnSystemUiVisibilityChangeListener(nj.m4428a(this, pVar));
        }
    }

    /* synthetic */ void m4425a(p pVar, int i) {
        if ((i & 4) == 0) {
            m4424a(pVar);
        }
    }
}
