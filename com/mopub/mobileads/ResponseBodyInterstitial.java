package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.ExternalViewabilitySessionManager;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public abstract class ResponseBodyInterstitial extends CustomEventInterstitial {
    protected AdReport mAdReport;
    protected long mBroadcastIdentifier;
    private EventForwardingBroadcastReceiver mBroadcastReceiver;
    protected Context mContext;
    protected ExternalViewabilitySessionManager mExternalViewabilitySessionManager;

    protected abstract void extractExtras(Map<String, String> map);

    protected abstract void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener);

    public abstract void showInterstitial();

    public void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.mContext = context;
        if (extrasAreValid(serverExtras)) {
            extractExtras(serverExtras);
            try {
                this.mAdReport = (AdReport) localExtras.get(DataKeys.AD_REPORT_KEY);
                Long boxedBroadcastId = (Long) localExtras.get(DataKeys.BROADCAST_IDENTIFIER_KEY);
                if (boxedBroadcastId == null) {
                    MoPubLog.m12063e("Broadcast Identifier was not set in localExtras");
                    customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                    return;
                }
                this.mBroadcastIdentifier = boxedBroadcastId.longValue();
                this.mBroadcastReceiver = new EventForwardingBroadcastReceiver(customEventInterstitialListener, this.mBroadcastIdentifier);
                this.mBroadcastReceiver.register(this.mBroadcastReceiver, context);
                preRenderHtml(customEventInterstitialListener);
                return;
            } catch (ClassCastException e) {
                MoPubLog.m12063e("LocalExtras contained an incorrect type.");
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                return;
            }
        }
        customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
    }

    public void onInvalidate() {
        if (this.mBroadcastReceiver != null) {
            this.mBroadcastReceiver.unregister(this.mBroadcastReceiver);
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        return serverExtras.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
