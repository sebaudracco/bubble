package com.fyber.mediation.p107a;

import android.app.Activity;
import com.adcolony.sdk.AdColonyAppOptions;
import com.fyber.Fyber;
import com.fyber.ads.banners.mediation.BannerMediationAdapter;
import com.fyber.ads.interstitials.mediation.InterstitialMediationAdapter;
import com.fyber.ads.interstitials.p088a.C2433b;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import java.util.Map;
import java.util.Set;

/* compiled from: ExchangeAdapter */
public final class C2572a extends MediationAdapter {
    private C2433b f6453a = new C2433b(this);

    public final boolean startAdapter(Activity activity, Map<String, Object> map) {
        return true;
    }

    public final String getName() {
        return AdColonyAppOptions.FYBER;
    }

    public final String getVersion() {
        return Fyber.RELEASE_VERSION_STRING;
    }

    public final RewardedVideoMediationAdapter<C2572a> getVideoMediationAdapter() {
        return null;
    }

    public final BannerMediationAdapter<C2572a> getBannerMediationAdapter() {
        return null;
    }

    protected final Set<?> getListeners() {
        return null;
    }

    public final /* bridge */ /* synthetic */ InterstitialMediationAdapter getInterstitialMediationAdapter() {
        return this.f6453a;
    }
}
