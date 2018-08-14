package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.session.C3329g;

/* compiled from: InternalAvidManagedAdSession */
public abstract class C3337f extends C3333a<View> {
    private WebView f8494a;

    public C3337f(Context context, String str, C3329g c3329g) {
        super(context, str, c3329g);
        this.f8494a = new WebView(context.getApplicationContext());
        this.f8494a.getSettings().setJavaScriptEnabled(true);
    }

    public WebView mo6356u() {
        return this.f8494a;
    }

    public void mo6359k() {
        super.mo6359k();
        m11416s();
    }
}
