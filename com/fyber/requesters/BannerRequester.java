package com.fyber.requesters;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.NetworkBannerSize;
import com.fyber.ads.banners.p087a.C2420a;
import com.fyber.ads.banners.p087a.C2421b;
import com.fyber.ads.internal.C2425d;
import com.fyber.p094b.p096b.C2487b.C2486a;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import java.util.ArrayList;
import java.util.List;

public class BannerRequester extends Requester<BannerRequester> {
    protected final /* bridge */ /* synthetic */ Object mo3994c() {
        return this;
    }

    public static BannerRequester create(@NonNull AdRequestCallback adRequestCallback) {
        return new BannerRequester((Callback) adRequestCallback);
    }

    public static BannerRequester from(@NonNull Requester requester) {
        return new BannerRequester(requester);
    }

    private BannerRequester(Requester requester) {
        super(requester);
    }

    private BannerRequester(@NonNull Callback callback) {
        super(callback);
    }

    protected final void mo3992a(Context context, C2623c c2623c) {
        if (C2421b.m7670a().m7676b()) {
            C2421b.m7671a(C2425d.REQUESTING_OFFERS);
            ((C2486a) ((C2486a) new C2486a().m7859a(this.a)).m7858a(c2623c)).m7904b().m7886a(context);
            return;
        }
        this.a.m8282a(RequestError.UNABLE_TO_REQUEST_ADS);
    }

    protected final C2588f<C2420a, AdFormat> mo3991a() {
        return new C2588f<C2420a, AdFormat>(this, AdRequestCallback.class) {
            final /* synthetic */ BannerRequester f6474a;

            protected final /* synthetic */ void mo3989a(Object obj) {
                ((AdRequestCallback) this.c).onAdNotAvailable((AdFormat) obj);
            }

            protected final /* synthetic */ void mo3990b(Object obj) {
                ((AdRequestCallback) this.c).onAdAvailable((BannerAd) ((C2420a) obj).m7626i());
            }
        };
    }

    protected final void mo3993b() {
        this.b.m8411b("banner").m8405a(true).m8406a(9, 8, 3, 0);
    }

    public BannerRequester withNetworkSize(@NonNull NetworkBannerSize networkBannerSize) {
        List list = (List) this.b.mo3970a("BANNER_SIZES");
        if (list == null) {
            list = new ArrayList();
            this.b.m8402a("BANNER_SIZES", (Object) list);
        }
        list.add(networkBannerSize);
        return this;
    }
}
