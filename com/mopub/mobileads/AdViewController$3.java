package com.mopub.mobileads;

import android.view.View;

class AdViewController$3 implements Runnable {
    final /* synthetic */ AdViewController this$0;
    final /* synthetic */ View val$view;

    AdViewController$3(AdViewController this$0, View view) {
        this.this$0 = this$0;
        this.val$view = view;
    }

    public void run() {
        MoPubView moPubView = this.this$0.getMoPubView();
        if (moPubView != null) {
            moPubView.removeAllViews();
            moPubView.addView(this.val$view, AdViewController.access$100(this.this$0, this.val$view));
        }
    }
}
