package com.moat.analytics.mobile.mpub;

public interface TrackerListener {
    void onTrackingFailedToStart(String str);

    void onTrackingStarted(String str);

    void onTrackingStopped(String str);
}
