package com.mopub.mobileads;

import android.view.View;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.JavaScriptWebViewCallbacks;
import com.mopub.mraid.MraidController$MraidListener;

class RewardedMraidActivity$1 implements MraidController$MraidListener {
    final /* synthetic */ RewardedMraidActivity this$0;
    final /* synthetic */ boolean val$shouldRewardOnClick;

    RewardedMraidActivity$1(RewardedMraidActivity this$0, boolean z) {
        this.this$0 = this$0;
        this.val$shouldRewardOnClick = z;
    }

    public void onLoaded(View view) {
        RewardedMraidActivity.access$000(this.this$0).loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
    }

    public void onFailedToLoad() {
        MoPubLog.m12061d("RewardedMraidActivity failed to load. Finishing the activity");
        BaseBroadcastReceiver.broadcastAction(this.this$0, this.this$0.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_FAIL);
        this.this$0.finish();
    }

    public void onClose() {
        RewardedMraidActivity.access$000(this.this$0).loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
        this.this$0.finish();
    }

    public void onExpand() {
    }

    public void onOpen() {
        if (this.val$shouldRewardOnClick) {
            RewardedMraidActivity.access$000(this.this$0).showPlayableCloseButton();
        }
        BaseBroadcastReceiver.broadcastAction(this.this$0, this.this$0.getBroadcastIdentifier().longValue(), IntentActions.ACTION_INTERSTITIAL_CLICK);
    }
}
