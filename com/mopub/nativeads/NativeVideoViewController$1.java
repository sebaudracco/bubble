package com.mopub.nativeads;

import android.view.View;
import android.view.View.OnClickListener;

class NativeVideoViewController$1 implements OnClickListener {
    final /* synthetic */ NativeVideoViewController this$0;

    NativeVideoViewController$1(NativeVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (NativeVideoViewController.access$000(this.this$0)) {
            NativeVideoViewController.access$002(this.this$0, false);
            NativeVideoViewController.access$100(this.this$0).resetProgress();
            NativeVideoViewController.access$200(this.this$0).seekTo(0);
        }
        this.this$0.applyState(NativeVideoViewController$VideoState.PLAYING);
    }
}
