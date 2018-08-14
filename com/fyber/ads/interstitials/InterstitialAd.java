package com.fyber.ads.interstitials;

import android.app.Activity;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.C2419b;
import com.fyber.ads.interstitials.p091b.C2441b;

public class InterstitialAd extends Ad<InterstitialAd, InterstitialAdListener> {
    public InterstitialAd(String str, C2419b<InterstitialAdListener> c2419b) {
        super(str, c2419b);
    }

    public AdFormat getAdFormat() {
        return AdFormat.INTERSTITIAL;
    }

    public boolean canStart() {
        return C2441b.m7760a(this);
    }

    public void start(Activity activity) {
        C2441b.m7757a(this, activity);
    }
}
