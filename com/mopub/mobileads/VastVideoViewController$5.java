package com.mopub.mobileads;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;

class VastVideoViewController$5 implements OnErrorListener {
    final /* synthetic */ VastVideoViewController this$0;

    VastVideoViewController$5(VastVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        VastVideoViewController.access$100(this.this$0).recordVideoEvent(VideoEvent.RECORD_AD_ERROR, this.this$0.getCurrentPosition());
        VastVideoViewController.access$1700(this.this$0);
        this.this$0.makeVideoInteractable();
        this.this$0.videoError(false);
        VastVideoViewController.access$1802(this.this$0, true);
        VastVideoViewController.access$500(this.this$0).handleError(this.this$0.getContext(), VastErrorCode.GENERAL_LINEAR_AD_ERROR, this.this$0.getCurrentPosition());
        return false;
    }
}
