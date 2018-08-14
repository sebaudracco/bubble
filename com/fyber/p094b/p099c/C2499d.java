package com.fyber.p094b.p099c;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.mediation.C2573a;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.C2492m;
import com.fyber.p094b.p099c.C2495a.C2494a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.concurrent.Future;

/* compiled from: InterstitialValidator */
public final class C2499d extends C2492m<InterstitialAd, C2440a> {
    public C2499d(WeakReference<Context> weakReference) {
        super(weakReference);
    }

    @Nullable
    protected final Future<Boolean> mo3922a(C2623c c2623c, Offer offer) {
        Context context = (Context) this.a.get();
        if (context != null) {
            return C2573a.f6454a.m8222b(context, offer);
        }
        FyberLogger.m8448d("InterstitialValidator", "There was no context. Not proceeding with the request...");
        return null;
    }

    protected final int mo3920a() {
        return 5;
    }

    protected final String mo3924b() {
        return "InterstitialValidator";
    }

    @NonNull
    protected final AdFormat mo3925c() {
        return AdFormat.INTERSTITIAL;
    }

    protected final /* synthetic */ C2482a mo3921a(@NonNull C2423a c2423a) {
        return new C2494a(c2423a);
    }
}
