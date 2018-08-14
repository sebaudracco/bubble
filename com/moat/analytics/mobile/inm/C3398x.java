package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.ref.WeakReference;

class C3398x implements az<WebAdTracker> {
    final /* synthetic */ WeakReference f8628a;
    final /* synthetic */ ao f8629b;
    final /* synthetic */ C3396v f8630c;

    C3398x(C3396v c3396v, WeakReference weakReference, ao aoVar) {
        this.f8630c = c3396v;
        this.f8628a = weakReference;
        this.f8629b = aoVar;
    }

    public C3378a<WebAdTracker> mo6502a() {
        ViewGroup viewGroup = (ViewGroup) this.f8628a.get();
        boolean b = this.f8629b.mo6482b();
        if (viewGroup == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return C3378a.m11558a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
        }
        C3378a a = this.f8630c.f8623a.mo6496a(viewGroup);
        boolean c = a.m11563c();
        if (b) {
            Log.e("MoatFactory", "WebView " + (c ? "" : "not ") + "found inside of ad container.");
        }
        return C3378a.m11559a(new be((WebView) a.m11562c(null), this.f8630c.f8624b, this.f8629b));
    }
}
