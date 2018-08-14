package com.moat.analytics.mobile.inm;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import java.lang.ref.WeakReference;

class C3397w implements az<WebAdTracker> {
    final /* synthetic */ WeakReference f8625a;
    final /* synthetic */ ao f8626b;
    final /* synthetic */ C3396v f8627c;

    C3397w(C3396v c3396v, WeakReference weakReference, ao aoVar) {
        this.f8627c = c3396v;
        this.f8625a = weakReference;
        this.f8626b = aoVar;
    }

    public C3378a<WebAdTracker> mo6502a() {
        WebView webView = (WebView) this.f8625a.get();
        boolean b = this.f8626b.mo6482b();
        if (webView == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return C3378a.m11558a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
        }
        return C3378a.m11559a(new be(webView, this.f8627c.f8624b, this.f8626b));
    }
}
