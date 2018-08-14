package com.mobfox.sdk.tagbanner;

import android.content.Context;
import com.mobfox.sdk.runnables.MobFoxRunnable;

class TagBanner$5 extends MobFoxRunnable {
    final /* synthetic */ TagBanner this$0;
    final /* synthetic */ TagBanner val$adapter;
    final /* synthetic */ String val$reason;

    TagBanner$5(TagBanner this$0, Context context, String str, TagBanner tagBanner) {
        this.this$0 = this$0;
        this.val$reason = str;
        this.val$adapter = tagBanner;
        super(context);
    }

    public void mobFoxRun() {
        if (this.val$reason.equals("No Ad Available")) {
            this.this$0.listener.onNoFill(this.val$adapter);
        } else {
            this.this$0.listener.onBannerError(this.val$adapter, this.val$reason);
        }
    }
}
