package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import java.util.Map;

public interface ReactiveVideoTracker {
    void changeTargetView(View view);

    void dispatchEvent(MoatAdEvent moatAdEvent);

    @Deprecated
    void setActivity(Activity activity);

    void setPlayerVolume(Double d);

    void stopTracking();

    boolean trackVideoAd(Map<String, String> map, Integer num, View view);
}
