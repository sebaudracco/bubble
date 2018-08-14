package com.facebook.ads;

import android.view.View;
import com.facebook.ads.internal.p033n.e$d;
import com.facebook.ads.internal.view.hscroll.C2355b;

class NativeAd$2 implements e$d {
    NativeAd$2() {
    }

    public boolean mo3586a(View view) {
        return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof C2355b);
    }
}
