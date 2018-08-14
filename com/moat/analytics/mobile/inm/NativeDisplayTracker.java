package com.moat.analytics.mobile.inm;

import java.util.Map;

public interface NativeDisplayTracker {
    void stopTracking();

    boolean track(Map<String, String> map);
}
