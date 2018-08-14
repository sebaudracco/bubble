package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.support.annotation.UiThread;

@UiThread
public interface NativeDisplayTracker {
    void removeListener();

    void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType);

    @Deprecated
    void setActivity(Activity activity);

    void setListener(TrackerListener trackerListener);

    void startTracking();

    void stopTracking();
}
