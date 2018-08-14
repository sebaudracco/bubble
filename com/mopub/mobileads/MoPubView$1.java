package com.mopub.mobileads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mopub.common.util.Visibility;

class MoPubView$1 extends BroadcastReceiver {
    final /* synthetic */ MoPubView this$0;

    MoPubView$1(MoPubView this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        if (Visibility.isScreenVisible(MoPubView.access$000(this.this$0)) && intent != null) {
            String action = intent.getAction();
            if ("android.intent.action.USER_PRESENT".equals(action)) {
                MoPubView.access$100(this.this$0, 0);
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                MoPubView.access$100(this.this$0, 8);
            }
        }
    }
}
