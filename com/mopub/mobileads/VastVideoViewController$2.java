package com.mopub.mobileads;

import android.app.Activity;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class VastVideoViewController$2 implements OnGlobalLayoutListener {
    final /* synthetic */ VastVideoViewController this$0;
    final /* synthetic */ Activity val$activity;

    VastVideoViewController$2(VastVideoViewController this$0, Activity activity) {
        this.this$0 = this$0;
        this.val$activity = activity;
    }

    public void onGlobalLayout() {
        VastVideoViewController.access$602(this.this$0, this.this$0.createAdsByView(this.val$activity));
        VastVideoViewController.access$700(this.this$0).getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}
