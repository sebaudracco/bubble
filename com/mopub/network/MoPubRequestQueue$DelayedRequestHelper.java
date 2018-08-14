package com.mopub.network;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.volley.Request;

class MoPubRequestQueue$DelayedRequestHelper {
    final int mDelayMs;
    @NonNull
    final Runnable mDelayedRunnable;
    @NonNull
    final Handler mHandler;
    final /* synthetic */ MoPubRequestQueue this$0;

    MoPubRequestQueue$DelayedRequestHelper(@NonNull MoPubRequestQueue this$0, Request<?> request, int delayMs) {
        this(this$0, request, delayMs, new Handler());
    }

    @VisibleForTesting
    MoPubRequestQueue$DelayedRequestHelper(@NonNull final MoPubRequestQueue this$0, final Request<?> request, @NonNull int delayMs, Handler handler) {
        this.this$0 = this$0;
        this.mDelayMs = delayMs;
        this.mHandler = handler;
        this.mDelayedRunnable = new Runnable() {
            public void run() {
                MoPubRequestQueue.access$000(MoPubRequestQueue$DelayedRequestHelper.this.this$0).remove(request);
                MoPubRequestQueue$DelayedRequestHelper.this.this$0.add(request);
            }
        };
    }

    void start() {
        this.mHandler.postDelayed(this.mDelayedRunnable, (long) this.mDelayMs);
    }

    void cancel() {
        this.mHandler.removeCallbacks(this.mDelayedRunnable);
    }
}
