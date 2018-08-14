package com.fyber.mediation.admob;

import android.app.Activity;
import com.fyber.mediation.admob.banner.AdMobBannerMediationAdapter;
import com.fyber.mediation.admob.interstitial.AdMobInterstitialMediationAdapter;
import com.fyber.mediation.admob.rv.AdMobVideoMediationAdapter;
import com.fyber.utils.StringUtils;

class AdMobMediationAdapter$1 implements Runnable {
    final /* synthetic */ AdMobMediationAdapter this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$bannerAdUnitId;
    final /* synthetic */ String val$intAdUnitId;
    final /* synthetic */ String val$rvAdUnitId;

    AdMobMediationAdapter$1(AdMobMediationAdapter this$0, String str, String str2, String str3, Activity activity) {
        this.this$0 = this$0;
        this.val$intAdUnitId = str;
        this.val$bannerAdUnitId = str2;
        this.val$rvAdUnitId = str3;
        this.val$activity = activity;
    }

    public void run() {
        AdMobMediationAdapter.access$002(this.this$0, new AdMobInterstitialMediationAdapter(this.this$0, this.val$intAdUnitId));
        if (StringUtils.notNullNorEmpty(this.val$bannerAdUnitId)) {
            AdMobMediationAdapter.access$102(this.this$0, new AdMobBannerMediationAdapter(this.this$0, this.val$bannerAdUnitId));
        }
        if (StringUtils.notNullNorEmpty(this.val$rvAdUnitId)) {
            AdMobMediationAdapter.access$202(this.this$0, new AdMobVideoMediationAdapter(this.this$0, this.val$rvAdUnitId, this.val$activity));
        }
    }
}
