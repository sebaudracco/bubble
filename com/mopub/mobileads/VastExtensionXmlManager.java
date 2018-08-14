package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Node;

public class VastExtensionXmlManager {
    public static final String AD_VERIFICATIONS = "AdVerifications";
    public static final String AVID = "AVID";
    public static final String ID = "id";
    public static final String MOAT = "Moat";
    public static final String TYPE = "type";
    public static final String VENDOR = "vendor";
    public static final String VERIFICATION = "Verification";
    public static final String VIDEO_VIEWABILITY_TRACKER = "MoPubViewabilityTracker";
    private final Node mExtensionNode;

    public VastExtensionXmlManager(@NonNull Node extensionNode) {
        Preconditions.checkNotNull(extensionNode);
        this.mExtensionNode = extensionNode;
    }

    @Nullable
    VideoViewabilityTracker getVideoViewabilityTracker() {
        Node videoViewabilityTrackerNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, VIDEO_VIEWABILITY_TRACKER);
        if (videoViewabilityTrackerNode == null) {
            return null;
        }
        VideoViewabilityTrackerXmlManager videoViewabilityTrackerXmlManager = new VideoViewabilityTrackerXmlManager(videoViewabilityTrackerNode);
        Integer viewablePlaytime = videoViewabilityTrackerXmlManager.getViewablePlaytimeMS();
        Integer percentViewable = videoViewabilityTrackerXmlManager.getPercentViewable();
        String videoViewabilityTrackerUrl = videoViewabilityTrackerXmlManager.getVideoViewabilityTrackerUrl();
        if (viewablePlaytime == null || percentViewable == null || TextUtils.isEmpty(videoViewabilityTrackerUrl)) {
            return null;
        }
        return new VideoViewabilityTracker(viewablePlaytime.intValue(), percentViewable.intValue(), videoViewabilityTrackerUrl);
    }

    @Nullable
    Set<String> getAvidJavaScriptResources() {
        Node avidNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, AVID);
        if (avidNode == null) {
            return null;
        }
        return new AvidBuyerTagXmlManager(avidNode).getJavaScriptResources();
    }

    @Nullable
    Set<String> getMoatImpressionPixels() {
        Node adVerification = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, AD_VERIFICATIONS);
        if (adVerification == null) {
            return null;
        }
        List<Node> moatNodes = XmlUtils.getMatchingChildNodes(adVerification, VERIFICATION, VENDOR, Collections.singletonList(MOAT));
        if (moatNodes == null || moatNodes.isEmpty()) {
            return null;
        }
        return new MoatBuyerTagXmlManager(moatNodes).getImpressionPixelsXml();
    }

    @Nullable
    String getType() {
        return XmlUtils.getAttributeValue(this.mExtensionNode, "type");
    }
}
