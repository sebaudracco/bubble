package com.inmobi.ads;

import java.util.Map;

public interface InMobiInterstitial$InterstitialAdListener2 {
    void onAdDismissed(InMobiInterstitial inMobiInterstitial);

    void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial);

    void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

    void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

    void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

    void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

    void onAdReceived(InMobiInterstitial inMobiInterstitial);

    void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

    void onAdWillDisplay(InMobiInterstitial inMobiInterstitial);

    void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
}
