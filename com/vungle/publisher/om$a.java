package com.vungle.publisher;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
class om$a extends mj$b<om> {
    @Inject
    lz f3178c;
    @Inject
    lw f3179d;

    protected /* synthetic */ mj mo3014a(Context context) {
        return m4478b(context);
    }

    @Inject
    om$a() {
    }

    protected om m4478b(Context context) {
        return new om(context, null);
    }

    protected void m4477a(String str, om omVar, p pVar, boolean z, x xVar) {
        omVar.setWebViewClient(this.f3178c);
    }

    protected void m4475a(om omVar) {
        omVar.setWebChromeClient(this.f3179d);
    }
}
