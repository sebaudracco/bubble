package com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades;

final /* synthetic */ class VungleRewardedFacade$$Lambda$1 implements Runnable {
    private final VungleRewardedFacade arg$1;

    private VungleRewardedFacade$$Lambda$1(VungleRewardedFacade vungleRewardedFacade) {
        this.arg$1 = vungleRewardedFacade;
    }

    public static Runnable lambdaFactory$(VungleRewardedFacade vungleRewardedFacade) {
        return new VungleRewardedFacade$$Lambda$1(vungleRewardedFacade);
    }

    public void run() {
        VungleRewardedFacade.lambda$init$0(this.arg$1);
    }
}
