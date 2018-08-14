package com.fyber.ads.interstitials.p088a;

import android.content.Intent;
import android.net.Uri;
import com.fyber.p089c.p090d.C2543d;
import com.fyber.p089c.p101a.C2523a;
import com.fyber.utils.C2435a;

/* compiled from: InterstitialClickthroughBrowserListener */
public final class C2436c extends C2435a {
    protected final void mo3880a(C2543d c2543d, C2523a c2523a, Uri uri) {
        c2543d.getContext().startActivity(new Intent("android.intent.action.VIEW", uri));
    }

    protected final String mo3879a() {
        return "InterstitialClickthroughBrowserListener";
    }
}
