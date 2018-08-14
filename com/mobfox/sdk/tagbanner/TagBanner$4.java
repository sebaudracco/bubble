package com.mobfox.sdk.tagbanner;

import android.content.Context;
import com.mobfox.sdk.runnables.MobFoxRunnable;

class TagBanner$4 extends MobFoxRunnable {
    final /* synthetic */ TagBanner this$0;
    final /* synthetic */ TagBanner val$self;

    TagBanner$4(TagBanner this$0, Context context, TagBanner tagBanner) {
        this.this$0 = this$0;
        this.val$self = tagBanner;
        super(context);
    }

    public void mobFoxRun() {
        this.this$0.listener.onBannerLoaded(this.val$self);
    }
}
