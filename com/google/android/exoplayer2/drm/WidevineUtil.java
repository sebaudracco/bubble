package com.google.android.exoplayer2.drm;

import android.util.Pair;
import java.util.Map;

public final class WidevineUtil {
    public static final String PROPERTY_LICENSE_DURATION_REMAINING = "LicenseDurationRemaining";
    public static final String PROPERTY_PLAYBACK_DURATION_REMAINING = "PlaybackDurationRemaining";

    private WidevineUtil() {
    }

    public static Pair<Long, Long> getLicenseDurationRemainingSec(DrmSession<?> drmSession) {
        Map<String, String> keyStatus = drmSession.queryKeyStatus();
        return new Pair(Long.valueOf(getDurationRemainingSec(keyStatus, "LicenseDurationRemaining")), Long.valueOf(getDurationRemainingSec(keyStatus, "PlaybackDurationRemaining")));
    }

    private static long getDurationRemainingSec(Map<String, String> keyStatus, String property) {
        if (keyStatus != null) {
            try {
                String value = (String) keyStatus.get(property);
                if (value != null) {
                    return Long.parseLong(value);
                }
            } catch (NumberFormatException e) {
            }
        }
        return -9223372036854775807L;
    }
}
