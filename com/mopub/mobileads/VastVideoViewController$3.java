package com.mopub.mobileads;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

class VastVideoViewController$3 implements OnPreparedListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ VastVideoView val$videoView;

    VastVideoViewController$3(VastVideoViewController this$0, VastVideoView vastVideoView) {
        this.this$0 = this$0;
        this.val$videoView = vastVideoView;
    }

    public void onPrepared(MediaPlayer mp) {
        VastVideoViewController.access$402(this.this$0, VastVideoViewController.access$800(this.this$0).getDuration());
        VastVideoViewController.access$100(this.this$0).onVideoPrepared(this.this$0.getLayout(), VastVideoViewController.access$400(this.this$0));
        VastVideoViewController.access$900(this.this$0);
        if (VastVideoViewController.access$1000(this.this$0) == null || VastVideoViewController.access$1100(this.this$0)) {
            this.val$videoView.prepareBlurredLastVideoFrame(VastVideoViewController.access$1200(this.this$0), VastVideoViewController.access$500(this.this$0).getDiskMediaFileUrl());
        }
        VastVideoViewController.access$1400(this.this$0).calibrateAndMakeVisible(this.this$0.getDuration(), VastVideoViewController.access$1300(this.this$0));
        VastVideoViewController.access$1500(this.this$0).calibrateAndMakeVisible(VastVideoViewController.access$1300(this.this$0));
        VastVideoViewController.access$1602(this.this$0, true);
    }
}
