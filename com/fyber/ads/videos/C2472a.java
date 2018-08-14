package com.fyber.ads.videos;

import android.app.Activity;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.internal.C2419b;
import com.fyber.ads.videos.p093a.C2469d;

/* compiled from: RewardedVideoAd */
public final class C2472a extends Ad<C2472a, C2473b> {
    public C2472a(String str, C2419b<C2473b> c2419b) {
        super(str, c2419b);
    }

    public final AdFormat getAdFormat() {
        return AdFormat.REWARDED_VIDEO;
    }

    public final boolean canStart() {
        return C2469d.f6162a.m7847c();
    }

    public final void start(Activity activity) {
    }
}
