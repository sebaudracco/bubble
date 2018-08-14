package com.mopub.mobileads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    private final long mBroadcastIdentifier;
    @Nullable
    private Context mContext;

    @NonNull
    public abstract IntentFilter getIntentFilter();

    public BaseBroadcastReceiver(long broadcastIdentifier) {
        this.mBroadcastIdentifier = broadcastIdentifier;
    }

    public static void broadcastAction(@NonNull Context context, long broadcastIdentifier, @NonNull String action) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Preconditions.checkNotNull(action, "action cannot be null");
        Intent intent = new Intent(action);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }

    public void register(@NonNull BroadcastReceiver broadcastReceiver, Context context) {
        this.mContext = context;
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(broadcastReceiver, getIntentFilter());
    }

    public void unregister(@Nullable BroadcastReceiver broadcastReceiver) {
        if (this.mContext != null && broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(broadcastReceiver);
            this.mContext = null;
        }
    }

    public boolean shouldConsumeBroadcast(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent, "intent cannot be null");
        return this.mBroadcastIdentifier == intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }
}
