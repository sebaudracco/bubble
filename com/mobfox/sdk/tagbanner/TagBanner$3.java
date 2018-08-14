package com.mobfox.sdk.tagbanner;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class TagBanner$3 implements OnTouchListener {
    final /* synthetic */ TagBanner this$0;
    final /* synthetic */ TagBanner val$self;

    TagBanner$3(TagBanner this$0, TagBanner tagBanner) {
        this.this$0 = this$0;
        this.val$self = tagBanner;
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.val$self.userInteraction = true;
        return false;
    }
}
