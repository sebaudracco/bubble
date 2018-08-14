package com.fyber.p094b.p096b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.NetworkBannerSize;
import com.fyber.ads.banners.mediation.BannerWrapper;
import com.fyber.ads.banners.p087a.C2420a;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.Offer;
import com.fyber.mediation.C2573a;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.C2492m;
import com.fyber.p094b.p096b.C2485a.C2483a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2610j.C2490b;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Future;

/* compiled from: BannerValidator */
public final class C2493d extends C2492m<BannerAd, C2420a> {

    /* compiled from: BannerValidator */
    class C24911 implements C2490b<BannerWrapper> {
        final /* synthetic */ C2493d f6222a;

        C24911(C2493d c2493d) {
            this.f6222a = c2493d;
        }

        public final /* synthetic */ void mo3919a(Object obj, Offer offer) {
            offer.getProviderRequest().m8228a("KEY_BANNER_WRAPPER", (BannerWrapper) obj);
        }
    }

    protected final /* synthetic */ void mo3923a(C2410a c2410a, Offer offer) {
        ((C2420a) c2410a).m7657a((BannerWrapper) offer.getProviderRequest().mo3970a("KEY_BANNER_WRAPPER"));
    }

    public C2493d(WeakReference<Context> weakReference) {
        super(weakReference);
    }

    @Nullable
    protected final Future<Boolean> mo3922a(C2623c c2623c, Offer offer) {
        Context context = (Context) this.a.get();
        if (context != null) {
            List list = (List) c2623c.mo3970a("BANNER_SIZES");
            String providerType = offer.getProviderType();
            Object arrayList = new ArrayList();
            if (list != null) {
                ListIterator listIterator = list.listIterator(list.size());
                while (listIterator.hasPrevious()) {
                    NetworkBannerSize networkBannerSize = (NetworkBannerSize) listIterator.previous();
                    if (providerType.equalsIgnoreCase(networkBannerSize.getNetwork())) {
                        arrayList.add(networkBannerSize.getSize());
                        break;
                    }
                }
            }
            offer.getProviderRequest().m8228a("BANNER_SIZES", arrayList);
            return C2573a.f6454a.m8216a(context, offer, new C24911(this));
        }
        FyberLogger.m8448d("BannerValidator", "There was no context. Not proceeding with the request...");
        return null;
    }

    protected final int mo3920a() {
        return 10;
    }

    protected final String mo3924b() {
        return "BannerValidator";
    }

    @NonNull
    protected final AdFormat mo3925c() {
        return AdFormat.BANNER;
    }

    protected final /* synthetic */ C2482a mo3921a(@NonNull C2423a c2423a) {
        return new C2483a(c2423a);
    }
}
