package com.vungle.publisher;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import com.vungle.publisher.rg.C1670a;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class rf$a extends mj$b<rf> {
    @Inject
    C1670a f3292c;
    @Inject
    Provider<rd> f3293d;
    @Inject
    rr f3294e;

    protected /* synthetic */ mj mo3014a(Context context) {
        return m4604b(context);
    }

    @Inject
    rf$a() {
    }

    protected rf m4604b(Context context) {
        rf rfVar = new rf(context);
        rfVar.c = this.f3294e;
        return rfVar;
    }

    protected void m4603a(String str, rf rfVar, p pVar, boolean z, x xVar) {
        WebViewClient a = this.f3292c.m4605a(str, pVar, z, xVar);
        rfVar.b = a;
        rfVar.setWebViewClient(a);
    }

    protected void m4601a(rf rfVar) {
        rfVar.setWebChromeClient((WebChromeClient) this.f3293d.get());
    }
}
