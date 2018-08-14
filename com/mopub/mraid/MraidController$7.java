package com.mopub.mraid;

import android.util.DisplayMetrics;
import android.view.View;

class MraidController$7 implements Runnable {
    final /* synthetic */ MraidController this$0;
    final /* synthetic */ View val$currentWebView;
    final /* synthetic */ Runnable val$successRunnable;

    MraidController$7(MraidController this$0, View view, Runnable runnable) {
        this.this$0 = this$0;
        this.val$currentWebView = view;
        this.val$successRunnable = runnable;
    }

    public void run() {
        DisplayMetrics displayMetrics = MraidController.access$600(this.this$0).getResources().getDisplayMetrics();
        MraidController.access$1100(this.this$0).setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
        int[] location = new int[2];
        View rootView = MraidController.access$1200(this.this$0);
        rootView.getLocationOnScreen(location);
        MraidController.access$1100(this.this$0).setRootViewPosition(location[0], location[1], rootView.getWidth(), rootView.getHeight());
        MraidController.access$1300(this.this$0).getLocationOnScreen(location);
        MraidController.access$1100(this.this$0).setDefaultAdPosition(location[0], location[1], MraidController.access$1300(this.this$0).getWidth(), MraidController.access$1300(this.this$0).getHeight());
        this.val$currentWebView.getLocationOnScreen(location);
        MraidController.access$1100(this.this$0).setCurrentAdPosition(location[0], location[1], this.val$currentWebView.getWidth(), this.val$currentWebView.getHeight());
        MraidController.access$200(this.this$0).notifyScreenMetrics(MraidController.access$1100(this.this$0));
        if (MraidController.access$100(this.this$0).isAttached()) {
            MraidController.access$100(this.this$0).notifyScreenMetrics(MraidController.access$1100(this.this$0));
        }
        if (this.val$successRunnable != null) {
            this.val$successRunnable.run();
        }
    }
}
