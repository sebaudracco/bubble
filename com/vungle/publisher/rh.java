package com.vungle.publisher;

import android.webkit.WebView;
import java.lang.invoke.LambdaForm.Hidden;

final /* synthetic */ class rh implements Runnable {
    private final rg f3311a;
    private final WebView f3312b;
    private final String f3313c;

    private rh(rg rgVar, WebView webView, String str) {
        this.f3311a = rgVar;
        this.f3312b = webView;
        this.f3313c = str;
    }

    public static Runnable m4618a(rg rgVar, WebView webView, String str) {
        return new rh(rgVar, webView, str);
    }

    @Hidden
    public void run() {
        this.f3311a.m4614a(this.f3312b, this.f3313c);
    }
}
