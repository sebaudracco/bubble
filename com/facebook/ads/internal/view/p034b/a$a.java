package com.facebook.ads.internal.view.p034b;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.view.b.a;
import com.facebook.ads.internal.view.b.a.b;
import com.facebook.ads.internal.view.b.a.c;
import java.lang.ref.WeakReference;

public class a$a {
    private final String f2630a;
    private final WeakReference<a> f2631b;
    private final WeakReference<b> f2632c;
    private final WeakReference<com.facebook.ads.internal.r.a> f2633d;

    private a$a(a aVar, b bVar, com.facebook.ads.internal.r.a aVar2) {
        this.f2630a = a$a.class.getSimpleName();
        this.f2631b = new WeakReference(aVar);
        this.f2632c = new WeakReference(bVar);
        this.f2633d = new WeakReference(aVar2);
    }

    @JavascriptInterface
    public void alert(String str) {
        Log.e(this.f2630a, str);
    }

    @JavascriptInterface
    public String getAnalogInfo() {
        return j.a(com.facebook.ads.internal.g.a.a());
    }

    @JavascriptInterface
    public void onPageInitialized() {
        a aVar = (a) this.f2631b.get();
        if (aVar != null && !aVar.c()) {
            b bVar = (b) this.f2632c.get();
            if (bVar != null) {
                bVar.a();
            }
            new Handler(Looper.getMainLooper()).post(new c(this.f2633d));
        }
    }
}
