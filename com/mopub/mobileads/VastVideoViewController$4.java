package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.widget.ImageView.ScaleType;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.IntentActions;

class VastVideoViewController$4 implements OnCompletionListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ VastVideoView val$videoView;

    VastVideoViewController$4(VastVideoViewController this$0, VastVideoView vastVideoView, Context context) {
        this.this$0 = this$0;
        this.val$videoView = vastVideoView;
        this.val$context = context;
    }

    public void onCompletion(MediaPlayer mp) {
        VastVideoViewController.access$1700(this.this$0);
        this.this$0.makeVideoInteractable();
        this.this$0.videoCompleted(false);
        VastVideoViewController.access$302(this.this$0, true);
        if (VastVideoViewController.access$500(this.this$0).isRewardedVideo()) {
            this.this$0.broadcastAction(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
        }
        if (!VastVideoViewController.access$1800(this.this$0) && VastVideoViewController.access$500(this.this$0).getRemainingProgressTrackerCount() == 0) {
            VastVideoViewController.access$100(this.this$0).recordVideoEvent(VideoEvent.AD_COMPLETE, this.this$0.getCurrentPosition());
            VastVideoViewController.access$500(this.this$0).handleComplete(this.this$0.getContext(), this.this$0.getCurrentPosition());
        }
        this.val$videoView.setVisibility(4);
        VastVideoViewController.access$1400(this.this$0).setVisibility(8);
        if (!VastVideoViewController.access$1100(this.this$0)) {
            VastVideoViewController.access$700(this.this$0).setVisibility(8);
        } else if (VastVideoViewController.access$1200(this.this$0).getDrawable() != null) {
            VastVideoViewController.access$1200(this.this$0).setScaleType(ScaleType.CENTER_CROP);
            VastVideoViewController.access$1200(this.this$0).setVisibility(0);
        }
        VastVideoViewController.access$1900(this.this$0).notifyVideoComplete();
        VastVideoViewController.access$2000(this.this$0).notifyVideoComplete();
        VastVideoViewController.access$2100(this.this$0).notifyVideoComplete();
        if (VastVideoViewController.access$1000(this.this$0) != null) {
            if (this.val$context.getResources().getConfiguration().orientation == 1) {
                VastVideoViewController.access$2200(this.this$0).setVisibility(0);
            } else {
                VastVideoViewController.access$2300(this.this$0).setVisibility(0);
            }
            VastVideoViewController.access$1000(this.this$0).handleImpression(this.val$context, VastVideoViewController.access$400(this.this$0));
        } else if (VastVideoViewController.access$1200(this.this$0).getDrawable() != null) {
            VastVideoViewController.access$1200(this.this$0).setVisibility(0);
        }
    }
}
