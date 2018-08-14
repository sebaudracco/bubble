package com.mopub.mobileads;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class VastWebView$VastWebViewOnTouchListener implements OnTouchListener {
    private boolean mClickStarted;
    final /* synthetic */ VastWebView this$0;

    VastWebView$VastWebViewOnTouchListener(VastWebView this$0) {
        this.this$0 = this$0;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.mClickStarted = true;
                break;
            case 1:
                if (this.mClickStarted) {
                    this.mClickStarted = false;
                    if (this.this$0.mVastWebViewClickListener != null) {
                        this.this$0.mVastWebViewClickListener.onVastWebViewClick();
                        break;
                    }
                }
                break;
        }
        return false;
    }
}
