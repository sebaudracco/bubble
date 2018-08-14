package com.mopub.common;

import android.view.View;
import android.view.View.OnClickListener;

class MoPubBrowser$2 implements OnClickListener {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$2(MoPubBrowser this$0) {
        this.this$0 = this$0;
    }

    public void onClick(View v) {
        if (MoPubBrowser.access$000(this.this$0).canGoForward()) {
            MoPubBrowser.access$000(this.this$0).goForward();
        }
    }
}
