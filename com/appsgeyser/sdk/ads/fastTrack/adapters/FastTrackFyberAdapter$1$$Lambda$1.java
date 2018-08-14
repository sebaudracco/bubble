package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Intent;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFyberAdapter.C12381;

final /* synthetic */ class FastTrackFyberAdapter$1$$Lambda$1 implements Runnable {
    private final C12381 arg$1;
    private final Intent arg$2;

    private FastTrackFyberAdapter$1$$Lambda$1(C12381 c12381, Intent intent) {
        this.arg$1 = c12381;
        this.arg$2 = intent;
    }

    public static Runnable lambdaFactory$(C12381 c12381, Intent intent) {
        return new FastTrackFyberAdapter$1$$Lambda$1(c12381, intent);
    }

    public void run() {
        C12381.lambda$onAdAvailable$0(this.arg$1, this.arg$2);
    }
}
