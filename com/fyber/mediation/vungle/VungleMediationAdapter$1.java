package com.fyber.mediation.vungle;

import android.app.Activity;
import com.fyber.mediation.vungle.interstitial.VungleInterstitialMediationAdapter;
import com.fyber.mediation.vungle.rv.VungleVideoMediationAdapter;
import com.fyber.utils.StringUtils;
import com.vungle.publisher.VunglePub;

class VungleMediationAdapter$1 implements Runnable {
    final /* synthetic */ VungleMediationAdapter this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$appId;
    final /* synthetic */ String val$intId;
    final /* synthetic */ String[] val$placementsArray;
    final /* synthetic */ String val$rvId;

    VungleMediationAdapter$1(VungleMediationAdapter this$0, Activity activity, String str, String[] strArr, String str2, String str3) {
        this.this$0 = this$0;
        this.val$activity = activity;
        this.val$appId = str;
        this.val$placementsArray = strArr;
        this.val$rvId = str2;
        this.val$intId = str3;
    }

    public void run() {
        VunglePub vunglePub = VunglePub.getInstance();
        vunglePub.init(this.val$activity, this.val$appId, this.val$placementsArray, this.this$0);
        if (StringUtils.notNullNorEmpty(this.val$rvId)) {
            VungleMediationAdapter.access$002(this.this$0, new VungleVideoMediationAdapter(VungleMediationAdapter.access$100(this.this$0), this.val$rvId));
        }
        if (StringUtils.notNullNorEmpty(this.val$intId)) {
            VungleMediationAdapter.access$202(this.this$0, new VungleInterstitialMediationAdapter(VungleMediationAdapter.access$100(this.this$0), this.val$intId));
        }
        vunglePub.clearAndSetEventListeners(VungleMediationAdapter.access$000(this.this$0), VungleMediationAdapter.access$200(this.this$0));
        vunglePub.onResume();
        VungleMediationAdapter.access$300(this.this$0, this.val$activity.getApplication());
    }
}
