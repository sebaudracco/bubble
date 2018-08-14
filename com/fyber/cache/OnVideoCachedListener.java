package com.fyber.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class OnVideoCachedListener extends BroadcastReceiver {
    public static final String ACTION_DOWNLOAD_FINISHED = "FyberDownloadsFinished";
    public static final String EXTRA_VIDEOS_AVAILABLE = "FyberVideosAvailable";

    public abstract void onVideoCached(boolean z);

    public final void onReceive(Context context, Intent intent) {
        onVideoCached(intent.getBooleanExtra(EXTRA_VIDEOS_AVAILABLE, false));
    }
}
