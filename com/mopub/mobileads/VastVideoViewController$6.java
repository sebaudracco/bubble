package com.mopub.mobileads;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;

class VastVideoViewController$6 implements OnTouchListener {
    final /* synthetic */ VastVideoViewController this$0;

    VastVideoViewController$6(VastVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int currentPosition;
        if (VastVideoViewController.access$300(this.this$0)) {
            currentPosition = VastVideoViewController.access$400(this.this$0);
        } else {
            currentPosition = this.this$0.getCurrentPosition();
        }
        if (motionEvent.getAction() == 1) {
            VastVideoViewController.access$202(this.this$0, true);
            if (!VastVideoViewController.access$300(this.this$0)) {
                VastVideoViewController.access$100(this.this$0).recordVideoEvent(VideoEvent.AD_SKIPPED, this.this$0.getCurrentPosition());
            }
            VastVideoViewController.access$500(this.this$0).handleClose(this.this$0.getContext(), currentPosition);
            this.this$0.getBaseVideoViewControllerListener().onFinish();
        }
        return true;
    }
}
