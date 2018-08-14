package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class VastManager$1 implements VideoDownloader$VideoDownloaderListener {
    final /* synthetic */ VastManager this$0;
    final /* synthetic */ VastVideoConfig val$vastVideoConfig;

    VastManager$1(VastManager this$0, VastVideoConfig vastVideoConfig) {
        this.this$0 = this$0;
        this.val$vastVideoConfig = vastVideoConfig;
    }

    public void onComplete(boolean success) {
        if (success && VastManager.access$000(this.this$0, this.val$vastVideoConfig)) {
            VastManager.access$100(this.this$0).onVastVideoConfigurationPrepared(this.val$vastVideoConfig);
            return;
        }
        MoPubLog.m12061d("Failed to download VAST video.");
        VastManager.access$100(this.this$0).onVastVideoConfigurationPrepared(null);
    }
}
