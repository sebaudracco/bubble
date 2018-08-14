package com.moat.analytics.mobile.inm;

import android.media.MediaPlayer;
import android.view.View;
import java.util.Map;

public interface NativeVideoTracker {
    void changeTargetView(View view);

    void dispatchEvent(MoatAdEvent moatAdEvent);

    @Deprecated
    void dispatchEvent(Map<String, Object> map);

    void setDebug(boolean z);

    boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view);
}
