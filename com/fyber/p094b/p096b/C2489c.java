package com.fyber.p094b.p096b;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.p087a.C2420a;
import com.fyber.p094b.C2488b;
import com.fyber.requesters.p097a.C2623c;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/* compiled from: BannerRequesterNetworkOperation */
public final class C2489c extends C2488b<BannerAd, C2420a> {
    protected final /* bridge */ /* synthetic */ Object mo3901b(IOException iOException) {
        return null;
    }

    public static Future<C2420a> m7918a(C2623c c2623c) {
        return Fyber.getConfigs().m7599a(new C2489c(c2623c));
    }

    private C2489c(C2623c c2623c) {
        super(c2623c);
    }

    @NonNull
    protected final AdFormat mo3916a() {
        return AdFormat.BANNER;
    }

    protected final int mo3918b() {
        return 20;
    }

    protected final String b_() {
        return "BannerRequesterNetworkOperation";
    }

    @NonNull
    protected final /* synthetic */ C2410a mo3917a(C2623c c2623c, List list) {
        return new C2420a(c2623c, list);
    }
}
