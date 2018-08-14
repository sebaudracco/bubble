package com.vungle.publisher;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class mj$b<W extends mj> {
    @Inject
    Context f3120a;
    @Inject
    qg f3121b;

    protected abstract W mo3014a(Context context);

    protected abstract void mo3015a(W w);

    protected abstract void mo3016a(String str, W w, p pVar, boolean z, x xVar);

    public W m4382a(String str, p pVar, boolean z, x xVar) {
        mj a = mo3014a(this.f3120a);
        a.a = this.f3121b;
        m4384a(a, str, pVar, z, xVar);
        return a;
    }

    protected void m4384a(W w, String str, p pVar, boolean z, x xVar) {
        WebSettings settings = w.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        w.setBackgroundColor(Color.argb(1, 0, 0, 0));
        w.setBackgroundResource(0);
        mo3016a(str, (mj) w, pVar, z, xVar);
        mo3015a((mj) w);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
}
