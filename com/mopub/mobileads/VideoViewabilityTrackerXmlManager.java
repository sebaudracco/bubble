package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Strings;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VideoViewabilityTrackerXmlManager {
    public static final String PERCENT_VIEWABLE = "percentViewable";
    public static final String VIEWABLE_PLAYTIME = "viewablePlaytime";
    private final Node mVideoViewabilityNode;

    VideoViewabilityTrackerXmlManager(@NonNull Node videoViewabilityNode) {
        Preconditions.checkNotNull(videoViewabilityNode);
        this.mVideoViewabilityNode = videoViewabilityNode;
    }

    @Nullable
    Integer getViewablePlaytimeMS() {
        String viewablePlaytimeStr = XmlUtils.getAttributeValue(this.mVideoViewabilityNode, VIEWABLE_PLAYTIME);
        if (viewablePlaytimeStr == null) {
            return null;
        }
        Integer viewablePlaytimeMS = null;
        if (Strings.isAbsoluteTracker(viewablePlaytimeStr)) {
            try {
                viewablePlaytimeMS = Strings.parseAbsoluteOffset(viewablePlaytimeStr);
            } catch (NumberFormatException e) {
                MoPubLog.m12061d(String.format("Invalid VAST viewablePlaytime format for \"HH:MM:SS[.mmm]\": %s:", new Object[]{viewablePlaytimeStr}));
            }
        } else {
            try {
                viewablePlaytimeMS = Integer.valueOf((int) (Float.parseFloat(viewablePlaytimeStr) * 1000.0f));
            } catch (NumberFormatException e2) {
                MoPubLog.m12061d(String.format("Invalid VAST viewablePlaytime format for \"SS[.mmm]\": %s:", new Object[]{viewablePlaytimeStr}));
            }
        }
        if (viewablePlaytimeMS == null || viewablePlaytimeMS.intValue() < 0) {
            return null;
        }
        return viewablePlaytimeMS;
    }

    @Nullable
    Integer getPercentViewable() {
        String percentViewableStr = XmlUtils.getAttributeValue(this.mVideoViewabilityNode, PERCENT_VIEWABLE);
        if (percentViewableStr == null) {
            return null;
        }
        Integer percentViewable = null;
        try {
            percentViewable = Integer.valueOf((int) Float.parseFloat(percentViewableStr.replace("%", "")));
        } catch (NumberFormatException e) {
            MoPubLog.m12061d(String.format("Invalid VAST percentViewable format for \"d{1,3}%%\": %s:", new Object[]{percentViewableStr}));
        }
        if (percentViewable == null || percentViewable.intValue() < 0 || percentViewable.intValue() > 100) {
            return null;
        }
        return percentViewable;
    }

    @Nullable
    String getVideoViewabilityTrackerUrl() {
        return XmlUtils.getNodeValue(this.mVideoViewabilityNode);
    }
}
