package com.fyber.p094b.p099c;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.p094b.C2488b;
import com.fyber.requesters.p097a.C2623c;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/* compiled from: InterstitialRequesterNetworkOperation */
public final class C2498c extends C2488b<InterstitialAd, C2440a> {
    protected final /* bridge */ /* synthetic */ Object mo3901b(IOException iOException) {
        return null;
    }

    public static Future<C2440a> m7956a(C2623c c2623c) {
        return Fyber.getConfigs().m7599a(new C2498c(c2623c));
    }

    private C2498c(C2623c c2623c) {
        super(c2623c);
        this.a = true;
    }

    @NonNull
    protected final AdFormat mo3916a() {
        return AdFormat.INTERSTITIAL;
    }

    protected final int mo3918b() {
        return 10;
    }

    protected final String b_() {
        return "InterstitialRequesterNetworkOperation";
    }

    @NonNull
    protected final /* synthetic */ C2410a mo3917a(C2623c c2623c, List list) {
        return new C2440a(c2623c, list);
    }
}
