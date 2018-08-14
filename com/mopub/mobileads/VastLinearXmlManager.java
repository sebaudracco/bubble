package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Strings;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastLinearXmlManager {
    private static final String CLICK_THROUGH = "ClickThrough";
    private static final String CLICK_TRACKER = "ClickTracking";
    private static final String CLOSE = "close";
    private static final String CLOSE_LINEAR = "closeLinear";
    private static final String COMPLETE = "complete";
    private static final String CREATIVE_VIEW = "creativeView";
    private static final int CREATIVE_VIEW_TRACKER_THRESHOLD = 0;
    private static final String EVENT = "event";
    private static final float FIRST_QUARTER_MARKER = 0.25f;
    private static final String FIRST_QUARTILE = "firstQuartile";
    public static final String ICON = "Icon";
    public static final String ICONS = "Icons";
    private static final String MEDIA_FILE = "MediaFile";
    private static final String MEDIA_FILES = "MediaFiles";
    private static final String MIDPOINT = "midpoint";
    private static final float MID_POINT_MARKER = 0.5f;
    private static final String OFFSET = "offset";
    private static final String PAUSE = "pause";
    private static final String PROGRESS = "progress";
    private static final String RESUME = "resume";
    private static final String SKIP = "skip";
    private static final String SKIP_OFFSET = "skipoffset";
    private static final String START = "start";
    private static final int START_TRACKER_THRESHOLD = 0;
    private static final float THIRD_QUARTER_MARKER = 0.75f;
    private static final String THIRD_QUARTILE = "thirdQuartile";
    private static final String TRACKING_EVENTS = "TrackingEvents";
    private static final String VIDEO_CLICKS = "VideoClicks";
    private static final String VIDEO_TRACKER = "Tracking";
    @NonNull
    private final Node mLinearNode;

    VastLinearXmlManager(@NonNull Node linearNode) {
        Preconditions.checkNotNull(linearNode);
        this.mLinearNode = linearNode;
    }

    @NonNull
    List<VastFractionalProgressTracker> getFractionalProgressTrackers() {
        List<VastFractionalProgressTracker> percentTrackers = new ArrayList();
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackersByAttribute(FIRST_QUARTILE), FIRST_QUARTER_MARKER);
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackersByAttribute(MIDPOINT), MID_POINT_MARKER);
        addQuartileTrackerWithFraction(percentTrackers, getVideoTrackersByAttribute(THIRD_QUARTILE), 0.75f);
        Node trackingEvents = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (trackingEvents != null) {
            for (Node progressNode : XmlUtils.getMatchingChildNodes(trackingEvents, VIDEO_TRACKER, "event", Collections.singletonList("progress"))) {
                String offsetString = XmlUtils.getAttributeValue(progressNode, "offset");
                if (offsetString != null) {
                    offsetString = offsetString.trim();
                    if (Strings.isPercentageTracker(offsetString)) {
                        String trackingUrl = XmlUtils.getNodeValue(progressNode);
                        try {
                            float trackingFraction = Float.parseFloat(offsetString.replace("%", "")) / 100.0f;
                            if (trackingFraction >= 0.0f) {
                                percentTrackers.add(new VastFractionalProgressTracker(trackingUrl, trackingFraction));
                            }
                        } catch (NumberFormatException e) {
                            MoPubLog.m12061d(String.format("Failed to parse VAST progress tracker %s", new Object[]{offsetString}));
                        }
                    }
                }
            }
        }
        Collections.sort(percentTrackers);
        return percentTrackers;
    }

    @NonNull
    List<VastAbsoluteProgressTracker> getAbsoluteProgressTrackers() {
        List<VastAbsoluteProgressTracker> trackers = new ArrayList();
        for (String url : getVideoTrackersByAttribute("start")) {
            trackers.add(new VastAbsoluteProgressTracker(url, 0));
        }
        Node trackingEvents = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (trackingEvents != null) {
            for (Node progressNode : XmlUtils.getMatchingChildNodes(trackingEvents, VIDEO_TRACKER, "event", Collections.singletonList("progress"))) {
                String offsetString = XmlUtils.getAttributeValue(progressNode, "offset");
                if (offsetString != null) {
                    offsetString = offsetString.trim();
                    if (Strings.isAbsoluteTracker(offsetString)) {
                        String trackingUrl = XmlUtils.getNodeValue(progressNode);
                        try {
                            Integer trackingMilliseconds = Strings.parseAbsoluteOffset(offsetString);
                            if (trackingMilliseconds != null && trackingMilliseconds.intValue() >= 0) {
                                trackers.add(new VastAbsoluteProgressTracker(trackingUrl, trackingMilliseconds.intValue()));
                            }
                        } catch (NumberFormatException e) {
                            MoPubLog.m12061d(String.format("Failed to parse VAST progress tracker %s", new Object[]{offsetString}));
                        }
                    }
                }
            }
            for (Node creativeViewNode : XmlUtils.getMatchingChildNodes(trackingEvents, VIDEO_TRACKER, "event", Collections.singletonList(CREATIVE_VIEW))) {
                String creativeNodeValue = XmlUtils.getNodeValue(creativeViewNode);
                if (creativeNodeValue != null) {
                    trackers.add(new VastAbsoluteProgressTracker(creativeNodeValue, 0));
                }
            }
        }
        Collections.sort(trackers);
        return trackers;
    }

    @NonNull
    List<VastTracker> getVideoCompleteTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers(COMPLETE);
    }

    @NonNull
    List<VastTracker> getPauseTrackers() {
        List<String> trackers = getVideoTrackersByAttribute(PAUSE);
        List<VastTracker> vastRepeatableTrackers = new ArrayList();
        for (String tracker : trackers) {
            vastRepeatableTrackers.add(new VastTracker(tracker, true));
        }
        return vastRepeatableTrackers;
    }

    @NonNull
    List<VastTracker> getResumeTrackers() {
        List<String> trackers = getVideoTrackersByAttribute(RESUME);
        List<VastTracker> vastRepeatableTrackers = new ArrayList();
        for (String tracker : trackers) {
            vastRepeatableTrackers.add(new VastTracker(tracker, true));
        }
        return vastRepeatableTrackers;
    }

    @NonNull
    List<VastTracker> getVideoCloseTrackers() {
        List<VastTracker> closeTrackers = getVideoTrackersByAttributeAsVastTrackers(CLOSE);
        closeTrackers.addAll(getVideoTrackersByAttributeAsVastTrackers(CLOSE_LINEAR));
        return closeTrackers;
    }

    @NonNull
    List<VastTracker> getVideoSkipTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers("skip");
    }

    @Nullable
    String getClickThroughUrl() {
        Node videoClicks = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, VIDEO_CLICKS);
        if (videoClicks == null) {
            return null;
        }
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(videoClicks, CLICK_THROUGH));
    }

    @NonNull
    List<VastTracker> getClickTrackers() {
        List<VastTracker> clickTrackers = new ArrayList();
        Node videoClicks = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, VIDEO_CLICKS);
        if (videoClicks != null) {
            for (Node clickTrackerNode : XmlUtils.getMatchingChildNodes(videoClicks, CLICK_TRACKER)) {
                String tracker = XmlUtils.getNodeValue(clickTrackerNode);
                if (tracker != null) {
                    clickTrackers.add(new VastTracker(tracker));
                }
            }
        }
        return clickTrackers;
    }

    @Nullable
    String getSkipOffset() {
        String skipOffsetString = XmlUtils.getAttributeValue(this.mLinearNode, SKIP_OFFSET);
        if (skipOffsetString == null || skipOffsetString.trim().isEmpty()) {
            return null;
        }
        return skipOffsetString.trim();
    }

    @NonNull
    List<VastMediaXmlManager> getMediaXmlManagers() {
        List<VastMediaXmlManager> mediaXmlManagers = new ArrayList();
        Node mediaFiles = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, MEDIA_FILES);
        if (mediaFiles != null) {
            for (Node mediaNode : XmlUtils.getMatchingChildNodes(mediaFiles, MEDIA_FILE)) {
                mediaXmlManagers.add(new VastMediaXmlManager(mediaNode));
            }
        }
        return mediaXmlManagers;
    }

    @NonNull
    List<VastIconXmlManager> getIconXmlManagers() {
        List<VastIconXmlManager> iconXmlManagers = new ArrayList();
        Node icons = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, ICONS);
        if (icons != null) {
            for (Node iconNode : XmlUtils.getMatchingChildNodes(icons, ICON)) {
                iconXmlManagers.add(new VastIconXmlManager(iconNode));
            }
        }
        return iconXmlManagers;
    }

    @NonNull
    private List<VastTracker> getVideoTrackersByAttributeAsVastTrackers(@NonNull String attributeValue) {
        List<String> trackers = getVideoTrackersByAttribute(attributeValue);
        List<VastTracker> vastTrackers = new ArrayList(trackers.size());
        for (String tracker : trackers) {
            vastTrackers.add(new VastTracker(tracker));
        }
        return vastTrackers;
    }

    @NonNull
    private List<String> getVideoTrackersByAttribute(@NonNull String attributeValue) {
        Preconditions.checkNotNull(attributeValue);
        List<String> videoTrackers = new ArrayList();
        Node trackingEvents = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, TRACKING_EVENTS);
        if (trackingEvents != null) {
            for (Node videoTrackerNode : XmlUtils.getMatchingChildNodes(trackingEvents, VIDEO_TRACKER, "event", Collections.singletonList(attributeValue))) {
                String tracker = XmlUtils.getNodeValue(videoTrackerNode);
                if (tracker != null) {
                    videoTrackers.add(tracker);
                }
            }
        }
        return videoTrackers;
    }

    private void addQuartileTrackerWithFraction(@NonNull List<VastFractionalProgressTracker> trackers, @NonNull List<String> urls, float fraction) {
        Preconditions.checkNotNull(trackers, "trackers cannot be null");
        Preconditions.checkNotNull(urls, "urls cannot be null");
        for (String url : urls) {
            trackers.add(new VastFractionalProgressTracker(url, fraction));
        }
    }
}
