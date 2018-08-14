package com.mopub.nativeads;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

class NativeVideoViewController$3 implements OnClickListener {
    final /* synthetic */ NativeVideoViewController this$0;

    NativeVideoViewController$3(NativeVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        NativeVideoViewController.access$200(this.this$0).setPlayWhenReady(false);
        NativeVideoViewController.access$402(this.this$0, NativeVideoViewController.access$100(this.this$0).getTextureView().getBitmap());
        NativeVideoViewController.access$200(this.this$0).handleCtaClick((Activity) NativeVideoViewController.access$500(this.this$0));
    }
}
