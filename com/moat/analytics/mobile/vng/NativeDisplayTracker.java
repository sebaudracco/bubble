package com.moat.analytics.mobile.vng;

import android.app.Activity;

public interface NativeDisplayTracker {
    void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType);

    @Deprecated
    void setActivity(Activity activity);

    void startTracking();

    void stopTracking();
}
