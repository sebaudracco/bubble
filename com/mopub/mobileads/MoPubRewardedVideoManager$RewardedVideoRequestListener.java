package com.mopub.mobileads;

import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

public class MoPubRewardedVideoManager$RewardedVideoRequestListener implements Listener {
    public final String adUnitId;
    private final MoPubRewardedVideoManager mVideoManager;

    public MoPubRewardedVideoManager$RewardedVideoRequestListener(MoPubRewardedVideoManager videoManager, String adUnitId) {
        this.adUnitId = adUnitId;
        this.mVideoManager = videoManager;
    }

    public void onSuccess(AdResponse response) {
        MoPubRewardedVideoManager.access$000(this.mVideoManager, response, this.adUnitId);
    }

    public void onErrorResponse(VolleyError volleyError) {
        MoPubRewardedVideoManager.access$100(this.mVideoManager, volleyError, this.adUnitId);
    }
}
