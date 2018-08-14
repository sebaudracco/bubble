package com.mopub.mobileads;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.IntentActions;

class VastVideoViewController$1 implements OnTouchListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Activity val$activity;

    VastVideoViewController$1(VastVideoViewController this$0, Activity activity) {
        this.this$0 = this$0;
        this.val$activity = activity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && VastVideoViewController.access$000(this.this$0)) {
            VastVideoViewController.access$100(this.this$0).recordVideoEvent(VideoEvent.AD_CLICK_THRU, this.this$0.getCurrentPosition());
            VastVideoViewController.access$202(this.this$0, true);
            this.this$0.broadcastAction(IntentActions.ACTION_INTERSTITIAL_CLICK);
            VastVideoViewController.access$500(this.this$0).handleClickForResult(this.val$activity, VastVideoViewController.access$300(this.this$0) ? VastVideoViewController.access$400(this.this$0) : this.this$0.getCurrentPosition(), 1);
        }
        return true;
    }
}
