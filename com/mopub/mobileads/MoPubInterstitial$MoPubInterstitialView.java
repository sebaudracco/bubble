package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.factories.CustomEventInterstitialAdapterFactory;
import java.util.Map;

public class MoPubInterstitial$MoPubInterstitialView extends MoPubView {
    final /* synthetic */ MoPubInterstitial this$0;

    public MoPubInterstitial$MoPubInterstitialView(MoPubInterstitial this$0, Context context) {
        this.this$0 = this$0;
        super(context);
        setAutorefreshEnabled(false);
    }

    @Nullable
    String getCustomEventClassName() {
        return this.mAdViewController.getCustomEventClassName();
    }

    public AdFormat getAdFormat() {
        return AdFormat.INTERSTITIAL;
    }

    protected void loadCustomEvent(String customEventClassName, Map<String, String> serverExtras) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(customEventClassName)) {
                MoPubLog.m12061d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (MoPubInterstitial.access$200(this.this$0) != null) {
                MoPubInterstitial.access$200(this.this$0).invalidate();
            }
            MoPubLog.m12061d("Loading custom event interstitial adapter.");
            MoPubInterstitial.access$202(this.this$0, CustomEventInterstitialAdapterFactory.create(this.this$0, customEventClassName, serverExtras, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport()));
            MoPubInterstitial.access$200(this.this$0).setAdapterListener(this.this$0);
            MoPubInterstitial.access$200(this.this$0).loadInterstitial();
        }
    }

    protected void trackImpression() {
        MoPubLog.m12061d("Tracking impression for interstitial.");
        if (this.mAdViewController != null) {
            this.mAdViewController.trackImpression();
        }
    }

    protected void adFailed(MoPubErrorCode errorCode) {
        MoPubInterstitial.access$300(this.this$0, MoPubInterstitial$InterstitialState.IDLE);
        if (MoPubInterstitial.access$400(this.this$0) != null) {
            MoPubInterstitial.access$400(this.this$0).onInterstitialFailed(this.this$0, errorCode);
        }
    }
}
