package com.mopub.mraid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;

@VisibleForTesting
class MraidController$OrientationBroadcastReceiver extends BroadcastReceiver {
    @Nullable
    private Context mContext;
    private int mLastRotation = -1;
    final /* synthetic */ MraidController this$0;

    MraidController$OrientationBroadcastReceiver(MraidController this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.mContext != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction())) {
            int orientation = MraidController.access$1400(this.this$0);
            if (orientation != this.mLastRotation) {
                this.mLastRotation = orientation;
                this.this$0.handleOrientationChange(this.mLastRotation);
            }
        }
    }

    public void register(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        this.mContext = context.getApplicationContext();
        if (this.mContext != null) {
            this.mContext.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        }
    }

    public void unregister() {
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this);
            this.mContext = null;
        }
    }
}
