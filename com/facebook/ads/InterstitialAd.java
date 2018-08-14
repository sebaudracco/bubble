package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.DisplayAdController;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.C2102g;
import com.facebook.ads.internal.protocol.e;
import java.util.EnumSet;

public class InterstitialAd implements Ad {
    private static final C2101d f3997a = C2101d.ADS;
    private final Context f3998b;
    private final String f3999c;
    private DisplayAdController f4000d;
    private boolean f4001e;
    private boolean f4002f;
    private InterstitialAdListener f4003g;

    class C18371 extends C1830a {
        final /* synthetic */ InterstitialAd f3996a;

        C18371(InterstitialAd interstitialAd) {
            this.f3996a = interstitialAd;
        }

        public void mo3551a() {
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onAdClicked(this.f3996a);
            }
        }

        public void mo3552a(View view) {
        }

        public void mo3553a(AdAdapter adAdapter) {
            this.f3996a.f4001e = true;
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onAdLoaded(this.f3996a);
            }
        }

        public void mo3554a(C2097a c2097a) {
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onError(this.f3996a, AdError.getAdErrorFromWrapper(c2097a));
            }
        }

        public void mo3555b() {
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onLoggingImpression(this.f3996a);
            }
        }

        public void mo3570d() {
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onInterstitialDisplayed(this.f3996a);
            }
        }

        public void mo3571e() {
            this.f3996a.f4002f = false;
            if (this.f3996a.f4000d != null) {
                this.f3996a.f4000d.m5619c();
                this.f3996a.f4000d = null;
            }
            if (this.f3996a.f4003g != null) {
                this.f3996a.f4003g.onInterstitialDismissed(this.f3996a);
            }
        }
    }

    public InterstitialAd(Context context, String str) {
        this.f3998b = context;
        this.f3999c = str;
    }

    private void m5439a(EnumSet<CacheFlag> enumSet, String str) {
        this.f4001e = false;
        if (this.f4002f) {
            throw new IllegalStateException("InterstitialAd cannot be loaded while being displayed. Make sure your adapter calls adapterListener.onInterstitialDismissed().");
        }
        if (this.f4000d != null) {
            this.f4000d.m5619c();
            this.f4000d = null;
        }
        this.f4000d = new DisplayAdController(this.f3998b, this.f3999c, C2102g.m6755a(this.f3998b.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, e.b, f3997a, 1, true, enumSet);
        this.f4000d.m5611a(new C18371(this));
        this.f4000d.m5614a(str);
    }

    public void destroy() {
        if (this.f4000d != null) {
            this.f4000d.m5618b(true);
            this.f4000d = null;
        }
    }

    public String getPlacementId() {
        return this.f3999c;
    }

    public boolean isAdLoaded() {
        return this.f4001e;
    }

    public void loadAd() {
        loadAd(EnumSet.of(CacheFlag.NONE));
    }

    public void loadAd(EnumSet<CacheFlag> enumSet) {
        m5439a((EnumSet) enumSet, null);
    }

    public void loadAdFromBid(String str) {
        m5439a(EnumSet.of(CacheFlag.NONE), str);
    }

    public void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        m5439a((EnumSet) enumSet, str);
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.f4003g = interstitialAdListener;
    }

    public boolean show() {
        if (this.f4001e) {
            this.f4000d.m5616b();
            this.f4002f = true;
            this.f4001e = false;
            return true;
        } else if (this.f4003g == null) {
            return false;
        } else {
            this.f4003g.onError(this, AdError.INTERNAL_ERROR);
            return false;
        }
    }
}
