package com.mopub.nativeads;

import android.view.View;
import android.view.View.OnClickListener;

class NativeVideoViewController$2 implements OnClickListener {
    final /* synthetic */ NativeVideoViewController this$0;

    NativeVideoViewController$2(NativeVideoViewController this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        this.this$0.applyState(NativeVideoViewController$VideoState.PAUSED, true);
        NativeVideoViewController.access$300(this.this$0).onFinish();
    }
}
