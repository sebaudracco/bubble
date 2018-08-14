package com.fyber.ads.interstitials.p091b;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.Ad;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.C2419b;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2424c;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.interstitials.InterstitialAd;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.ads.interstitials.InterstitialAdListener;
import com.fyber.mediation.C2573a;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.p099c.C2495a.C2494a;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.requesters.p097a.p098a.C2610j;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: InterstitialAdRequestResponse */
public final class C2440a extends C2410a<InterstitialAd> implements C2419b<InterstitialAdListener>, C2439c {
    private InterstitialAdListener f6104e;
    private InterstitialAdListener f6105f;
    private Map<String, String> f6106g = new HashMap();
    private boolean f6107h = false;
    private boolean f6108i = false;

    public C2440a(C2623c c2623c, List<Offer> list) {
        super(c2623c, list);
    }

    public final void mo3852k() {
        if (!this.f6108i) {
            this.f6108i = true;
            m7616a(C2423a.ShowImpression, null, this.f6106g);
            if (this.f6104e != null) {
                this.f6104e.onAdShown((InterstitialAd) this.a);
            }
        }
    }

    public final void mo3891l() {
        if (!this.f6107h) {
            this.f6107h = true;
            m7614a(C2423a.ShowClick);
            if (this.f6104e != null) {
                this.f6104e.onAdClicked((InterstitialAd) this.a);
            }
        }
    }

    public final void mo3889a(String str, InterstitialAdCloseReason interstitialAdCloseReason) {
        C2441b.m7759a(C2425d.READY_TO_CHECK_OFFERS);
        if (interstitialAdCloseReason == null) {
            InterstitialAdCloseReason interstitialAdCloseReason2 = this.f6107h ? InterstitialAdCloseReason.ReasonUserClickedOnAd : InterstitialAdCloseReason.ReasonUserClosedAd;
            if (this.f6108i) {
                interstitialAdCloseReason = interstitialAdCloseReason2;
            } else {
                interstitialAdCloseReason = InterstitialAdCloseReason.ReasonUnknown;
            }
        }
        if (this.f6108i && !this.f6107h) {
            m7615a(C2423a.ShowClose, str);
        }
        if (this.f6105f != null) {
            this.f6105f.onAdClosed((InterstitialAd) this.a, interstitialAdCloseReason);
        }
        if (this.f6104e != null) {
            this.f6104e.onAdClosed((InterstitialAd) this.a, interstitialAdCloseReason);
        }
    }

    public final void m7749a(String str) {
        mo3890a(str, null, null);
    }

    public final void mo3890a(String str, String str2, Map<String, String> map) {
        C2441b.m7759a(C2425d.READY_TO_CHECK_OFFERS);
        m7616a(C2423a.ShowError, str2, map);
        if (this.f6105f != null) {
            this.f6105f.onAdError((InterstitialAd) this.a, str);
        }
        if (this.f6104e != null) {
            this.f6104e.onAdError((InterstitialAd) this.a, str);
        }
    }

    protected final C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3849b(@NonNull C2423a c2423a) {
        return new C2494a(c2423a);
    }

    public final void m7746a(Activity activity) {
        C2441b.m7759a(C2425d.SHOWING_OFFERS);
        C2441b.m7758a(null);
        if (this.d != null) {
            this.f6106g.putAll(C2424c.m7674a(Fyber.getConfigs().m7603d().m8388a(this.d.mo3972a())));
        }
        Offer j = m7627j();
        if (j == null) {
            mo3890a("There is no offer to show", null, this.f6106g);
            return;
        }
        C2580a providerRequest = j.getProviderRequest();
        C2610j b = C2573a.f6454a.m8221b(j.getProviderType(), AdFormat.INTERSTITIAL);
        if (b != null) {
            this.f6106g.putAll(C2424c.m7673a(2, b.m8374a(providerRequest.mo3972a())));
        }
        if (this.f6108i) {
            mo3890a("The Ad was already shown.", null, this.f6106g);
        } else if (!C2573a.f6454a.m8219a(activity, this)) {
            mo3890a("The current network is not available", null, this.f6106g);
        }
    }

    public final void m7747a(InterstitialAdListener interstitialAdListener) {
        this.f6105f = interstitialAdListener;
    }

    public final /* synthetic */ Ad mo3851h() {
        return new InterstitialAd(m7621d(), this);
    }
}
