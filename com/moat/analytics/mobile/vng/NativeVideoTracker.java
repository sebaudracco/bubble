package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import java.util.Map;

public interface NativeVideoTracker {
    void changeTargetView(View view);

    void dispatchEvent(MoatAdEvent moatAdEvent);

    @Deprecated
    void setActivity(Activity activity);

    void setPlayerVolume(Double d);

    void stopTracking();

    boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view);
}
