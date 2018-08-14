package com.mopub.mraid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.Interstitial;
import com.mopub.mobileads.MraidActivity;
import com.mopub.mobileads.ResponseBodyInterstitial;
import java.util.Map;

class MraidInterstitial extends ResponseBodyInterstitial {
    @Nullable
    protected String mHtmlData;

    MraidInterstitial() {
    }

    protected void extractExtras(Map<String, String> serverExtras) {
        this.mHtmlData = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    protected void preRenderHtml(@NonNull CustomEventInterstitialListener customEventInterstitialListener) {
        MraidActivity.preRenderHtml((Interstitial) this, this.mContext, customEventInterstitialListener, this.mHtmlData, Long.valueOf(this.mBroadcastIdentifier));
    }

    public void showInterstitial() {
        MraidActivity.start(this.mContext, this.mAdReport, this.mHtmlData, this.mBroadcastIdentifier);
    }
}
