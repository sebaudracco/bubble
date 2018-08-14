package com.mopub.mobileads;

import android.text.TextUtils;
import com.mopub.common.MoPubReward;
import java.util.HashSet;
import java.util.Set;

class MoPubRewardedVideoManager$14 implements Runnable {
    final /* synthetic */ String val$currentlyShowingAdUnitId;
    final /* synthetic */ Class val$customEventClass;
    final /* synthetic */ MoPubReward val$moPubReward;
    final /* synthetic */ String val$thirdPartyId;

    MoPubRewardedVideoManager$14(Class cls, MoPubReward moPubReward, String str, String str2) {
        this.val$customEventClass = cls;
        this.val$moPubReward = moPubReward;
        this.val$currentlyShowingAdUnitId = str;
        this.val$thirdPartyId = str2;
    }

    public void run() {
        MoPubReward chosenReward = MoPubRewardedVideoManager.chooseReward(MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getLastShownMoPubReward(this.val$customEventClass), this.val$moPubReward);
        Set<String> rewardedIds = new HashSet();
        if (TextUtils.isEmpty(this.val$currentlyShowingAdUnitId)) {
            rewardedIds.addAll(MoPubRewardedVideoManager.access$1000(MoPubRewardedVideoManager.access$200()).getMoPubIdsForAdNetwork(this.val$customEventClass, this.val$thirdPartyId));
        } else {
            rewardedIds.add(this.val$currentlyShowingAdUnitId);
        }
        if (MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()) != null) {
            MoPubRewardedVideoManager.access$300(MoPubRewardedVideoManager.access$200()).onRewardedVideoCompleted(rewardedIds, chosenReward);
        }
    }
}
