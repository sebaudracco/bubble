package com.fyber.ads.interstitials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.fyber.ads.internal.C2425d;
import com.fyber.ads.interstitials.p091b.C2440a;
import com.fyber.ads.interstitials.p091b.C2441b;
import com.fyber.mediation.MediationUserActivityListener;
import com.fyber.utils.StringUtils;

public class InterstitialActivity extends Activity implements InterstitialAdListener {
    public static final String AD_STATUS = "AD_STATUS";
    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
    private MediationUserActivityListener f6074a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (C2441b.m7756a().m7675a()) {
            C2440a b = C2441b.m7761b();
            if (b != null) {
                b.m7747a((InterstitialAdListener) this);
                b.m7746a((Activity) this);
                return;
            }
            C2441b.m7759a(C2425d.READY_TO_CHECK_OFFERS);
            onAdError(null, "Unknown internal issue. Please try again later.");
            return;
        }
        onAdError(null, "There's no Ad available to be shown currently.");
    }

    protected void onPause() {
        if (this.f6074a != null) {
            this.f6074a.notifyOnUserLeft();
        }
        super.onPause();
    }

    public void setMarketPlaceInterstitialListener(MediationUserActivityListener mediationUserActivityListener) {
        if (this.f6074a == null) {
            this.f6074a = mediationUserActivityListener;
        }
    }

    protected void onUserLeaveHint() {
        if (this.f6074a != null) {
            this.f6074a.notifyOnUserLeft();
        }
        super.onUserLeaveHint();
    }

    public void onBackPressed() {
        if (this.f6074a == null || !this.f6074a.notifyOnBackPressed()) {
            super.onBackPressed();
        }
    }

    public void onAdError(InterstitialAd interstitialAd, String str) {
        finishInterstitialActivity(InterstitialAdCloseReason.ReasonError, str);
    }

    public void onAdClosed(InterstitialAd interstitialAd, InterstitialAdCloseReason interstitialAdCloseReason) {
        finishInterstitialActivity(interstitialAdCloseReason, null);
    }

    public void onAdShown(InterstitialAd interstitialAd) {
    }

    public void onAdClicked(InterstitialAd interstitialAd) {
    }

    protected void finishInterstitialActivity(InterstitialAdCloseReason interstitialAdCloseReason, String str) {
        Intent intent = new Intent();
        intent.putExtra(AD_STATUS, interstitialAdCloseReason);
        if (StringUtils.notNullNorEmpty(str)) {
            intent.putExtra(ERROR_MESSAGE, str);
        }
        setResult(-1, intent);
        finish();
    }
}
