package com.fyber.ads.interstitials.mediation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.ads.interstitials.InterstitialAdCloseReason;
import com.fyber.ads.interstitials.p091b.C2439c;
import com.fyber.exceptions.C2565a;
import com.fyber.mediation.AdFormatMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.utils.FyberLogger;
import java.util.Map;

public abstract class InterstitialMediationAdapter<V extends MediationAdapter> extends AdFormatMediationAdapter<Boolean, C2565a> {
    protected static final String CREATIVE_TYPE_KEY = "creative_type";
    public static final String ERROR_NO_PLACEMENT_ID = "no_placement_id";
    protected static final String TPN_PLACEMENT_ID_KEY = "tpn_placement_id";
    private C2439c f6083a;
    protected V adapter;

    protected abstract void checkForAds(Context context);

    protected abstract void show(Activity activity);

    public InterstitialMediationAdapter(V v) {
        this.adapter = v;
    }

    protected void setAdAvailable() {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdAvailable(Boolean.TRUE, this.request);
        } else {
            FyberLogger.m8451i("InterstitialMediationAdapter", "No provider request listener");
        }
    }

    protected void setAdNotAvailable() {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdNotAvailable(this.request);
        } else {
            FyberLogger.m8451i("InterstitialMediationAdapter", "No provider request listener");
        }
    }

    protected void setAdError(String str) {
        setAdError(str, str);
    }

    protected void setAdError(String str, String str2) {
        if (this.providerRequesterListener != null) {
            this.providerRequesterListener.setAdError(new C2565a(str, str2), this.request);
        } else {
            FyberLogger.m8451i("InterstitialMediationAdapter", "No provider request listener");
        }
    }

    public void mo3871a(Activity activity, C2439c c2439c) {
        this.f6083a = c2439c;
        show(activity);
    }

    protected void interstitialShown() {
        if (this.f6083a != null) {
            this.f6083a.mo3852k();
        }
    }

    protected void interstitialClicked() {
        if (this.f6083a != null) {
            this.f6083a.mo3891l();
        }
    }

    protected void interstitialClosed() {
        interstitialClosed(null);
    }

    protected void interstitialClosed(String str) {
        interstitialClosed(str, null);
    }

    protected void interstitialClosed(String str, InterstitialAdCloseReason interstitialAdCloseReason) {
        if (this.f6083a != null) {
            this.f6083a.mo3889a(str, interstitialAdCloseReason);
            this.f6083a = null;
        }
    }

    protected void interstitialError(String str) {
        interstitialError(str, null);
    }

    protected void interstitialError(String str, String str2) {
        interstitialError(str, str2, null);
    }

    protected void interstitialError(String str, String str2, Map<String, String> map) {
        if (this.f6083a != null) {
            this.f6083a.mo3890a(str, str2, map);
        }
        this.f6083a = null;
    }

    public void isAdAvailable(@NonNull Context context, @NonNull C2580a c2580a) {
        this.request = c2580a;
        checkForAds(context);
    }
}
