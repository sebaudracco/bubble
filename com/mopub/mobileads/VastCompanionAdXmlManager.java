package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastCompanionAdXmlManager {
    private static final String AD_SLOT_ID = "adSlotID";
    private static final String COMPANION_CLICK_THROUGH = "CompanionClickThrough";
    private static final String COMPANION_CLICK_TRACKING = "CompanionClickTracking";
    private static final String CREATIVE_VIEW = "creativeView";
    private static final String EVENT = "event";
    private static final String HEIGHT = "height";
    private static final String TRACKING_EVENTS = "TrackingEvents";
    private static final String VIDEO_TRACKER = "Tracking";
    private static final String WIDTH = "width";
    @NonNull
    private final Node mCompanionNode;
    @NonNull
    private final VastResourceXmlManager mResourceXmlManager;

    VastCompanionAdXmlManager(@NonNull Node companionNode) {
        Preconditions.checkNotNull(companionNode, "companionNode cannot be null");
        this.mCompanionNode = companionNode;
        this.mResourceXmlManager = new VastResourceXmlManager(companionNode);
    }

    @Nullable
    Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "width");
    }

    @Nullable
    Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "height");
    }

    @Nullable
    String getAdSlotId() {
        return XmlUtils.getAttributeValue(this.mCompanionNode, AD_SLOT_ID);
    }

    @NonNull
    VastResourceXmlManager getResourceXmlManager() {
        return this.mResourceXmlManager;
    }

    @Nullable
    String getClickThroughUrl() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, COMPANION_CLICK_THROUGH));
    }

    @NonNull
    List<VastTracker> getClickTrackers() {
        List<VastTracker> companionAdClickTrackers = new ArrayList();
        List<Node> trackerNodes = XmlUtils.getMatchingChildNodes(this.mCompanionNode, COMPANION_CLICK_TRACKING);
        if (trackerNodes != null) {
            for (Node trackerNode : trackerNodes) {
                String uri = XmlUtils.getNodeValue(trackerNode);
                if (!TextUtils.isEmpty(uri)) {
                    companionAdClickTrackers.add(new VastTracker(uri));
                }
            }
        }
        return companionAdClickTrackers;
    }

    @NonNull
    List<VastTracker> getCompanionCreativeViewTrackers() {
        List<VastTracker> companionCreativeViewTrackers = new ArrayList();
        Node node = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, TRACKING_EVENTS);
        if (node != null) {
            for (Node trackerNode : XmlUtils.getMatchingChildNodes(node, VIDEO_TRACKER, "event", Collections.singletonList(CREATIVE_VIEW))) {
                companionCreativeViewTrackers.add(new VastTracker(XmlUtils.getNodeValue(trackerNode)));
            }
        }
        return companionCreativeViewTrackers;
    }

    boolean hasResources() {
        return (TextUtils.isEmpty(this.mResourceXmlManager.getStaticResource()) && TextUtils.isEmpty(this.mResourceXmlManager.getHTMLResource()) && TextUtils.isEmpty(this.mResourceXmlManager.getIFrameResource())) ? false : true;
    }
}
