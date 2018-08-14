package com.fyber.requesters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.interstitials.InterstitialActivity;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.ads.interstitials.p091b.C2441b;
import com.fyber.p094b.p099c.C2497b.C2496a;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;

public class InterstitialRequester extends Requester<InterstitialRequester> {
    protected final /* bridge */ /* synthetic */ Object mo3994c() {
        return this;
    }

    public static InterstitialRequester create(@NonNull RequestCallback requestCallback) {
        return new InterstitialRequester(requestCallback);
    }

    public static InterstitialRequester from(@NonNull Requester requester) {
        return new InterstitialRequester(requester);
    }

    protected final void mo3992a(Context context, C2623c c2623c) {
        if (C2441b.m7756a().m7676b()) {
            C2441b.m7759a(C2425d.REQUESTING_OFFERS);
            ((C2496a) ((C2496a) new C2496a().m7859a(this.a)).m7858a(c2623c)).m7948b().m7886a(context);
            return;
        }
        this.a.m8282a(RequestError.UNABLE_TO_REQUEST_ADS);
    }

    private InterstitialRequester(RequestCallback requestCallback) {
        super((Callback) requestCallback);
    }

    private InterstitialRequester(Requester requester) {
        super(requester);
    }

    protected final C2588f<C2440a, AdFormat> mo3991a() {
        return new C2588f<C2440a, AdFormat>(this, AdRequestCallback.class, RequestCallback.class) {
            final /* synthetic */ InterstitialRequester f6478a;

            protected final /* synthetic */ void mo3989a(Object obj) {
                AdFormat adFormat = (AdFormat) obj;
                if (this.c instanceof RequestCallback) {
                    ((RequestCallback) this.c).onAdNotAvailable(adFormat);
                } else if (this.c instanceof AdRequestCallback) {
                    ((AdRequestCallback) this.c).onAdNotAvailable(adFormat);
                }
            }

            protected final /* synthetic */ void mo3990b(Object obj) {
                C2440a c2440a = (C2440a) obj;
                if (this.c instanceof RequestCallback) {
                    Context context = (Context) this.f6478a.c.get();
                    if (context != null) {
                        Intent intent = new Intent(context, InterstitialActivity.class);
                        intent.putExtra(Requester.EXTRA_AD_FORMAT, AdFormat.INTERSTITIAL);
                        intent.putExtra("REQUEST_AGENT_CACHE_KEY", this.f6478a.b.mo3972a());
                        ((RequestCallback) this.c).onAdAvailable(intent);
                        return;
                    }
                    ((RequestCallback) this.c).onAdNotAvailable(AdFormat.INTERSTITIAL);
                } else if (this.c instanceof AdRequestCallback) {
                    ((AdRequestCallback) this.c).onAdAvailable((InterstitialAd) c2440a.m7626i());
                }
            }
        };
    }

    protected final void mo3993b() {
        this.b.m8411b("interstitial").m8405a(true).m8406a(4, 9, 8, 7, 0);
    }
}
