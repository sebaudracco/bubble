package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

abstract class VastBaseInLineWrapperXmlManager {
    private static final String COMPANION = "Companion";
    private static final String COMPANION_ADS = "CompanionAds";
    private static final String CREATIVE = "Creative";
    private static final String CREATIVES = "Creatives";
    private static final String ERROR = "Error";
    private static final String EXTENSIONS = "Extensions";
    private static final String IMPRESSION_TRACKER = "Impression";
    private static final String LINEAR = "Linear";
    @NonNull
    protected final Node mNode;

    VastBaseInLineWrapperXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mNode = node;
    }

    @NonNull
    List<VastTracker> getImpressionTrackers() {
        List<Node> impressionNodes = XmlUtils.getMatchingChildNodes(this.mNode, IMPRESSION_TRACKER);
        List<VastTracker> impressionTrackers = new ArrayList();
        for (Node impressionNode : impressionNodes) {
            String uri = XmlUtils.getNodeValue(impressionNode);
            if (!TextUtils.isEmpty(uri)) {
                impressionTrackers.add(new VastTracker(uri));
            }
        }
        return impressionTrackers;
    }

    @NonNull
    List<VastTracker> getErrorTrackers() {
        List<VastTracker> errorTrackers = new ArrayList();
        List<Node> errorNodes = XmlUtils.getMatchingChildNodes(this.mNode, ERROR);
        if (errorNodes != null) {
            for (Node error : errorNodes) {
                String tracker = XmlUtils.getNodeValue(error);
                if (!TextUtils.isEmpty(tracker)) {
                    errorTrackers.add(new VastTracker(tracker, true));
                }
            }
        }
        return errorTrackers;
    }

    @NonNull
    List<VastLinearXmlManager> getLinearXmlManagers() {
        List<VastLinearXmlManager> linearXmlManagers = new ArrayList();
        Node creativesNode = XmlUtils.getFirstMatchingChildNode(this.mNode, CREATIVES);
        if (creativesNode != null) {
            List<Node> creativeNodes = XmlUtils.getMatchingChildNodes(creativesNode, CREATIVE);
            if (creativeNodes != null) {
                for (Node creativeNode : creativeNodes) {
                    Node linearNode = XmlUtils.getFirstMatchingChildNode(creativeNode, LINEAR);
                    if (linearNode != null) {
                        linearXmlManagers.add(new VastLinearXmlManager(linearNode));
                    }
                }
            }
        }
        return linearXmlManagers;
    }

    @NonNull
    List<VastCompanionAdXmlManager> getCompanionAdXmlManagers() {
        List<VastCompanionAdXmlManager> companionAdXmlManagers = new ArrayList();
        Node creativesNode = XmlUtils.getFirstMatchingChildNode(this.mNode, CREATIVES);
        if (creativesNode != null) {
            List<Node> creativeNodes = XmlUtils.getMatchingChildNodes(creativesNode, CREATIVE);
            if (creativeNodes != null) {
                for (Node creativeNode : creativeNodes) {
                    Node companionAds = XmlUtils.getFirstMatchingChildNode(creativeNode, COMPANION_ADS);
                    if (companionAds != null) {
                        List<Node> companionAdsNodes = XmlUtils.getMatchingChildNodes(companionAds, COMPANION);
                        if (companionAdsNodes != null) {
                            for (Node companionNode : companionAdsNodes) {
                                companionAdXmlManagers.add(new VastCompanionAdXmlManager(companionNode));
                            }
                        }
                    }
                }
            }
        }
        return companionAdXmlManagers;
    }

    @Nullable
    VastExtensionParentXmlManager getVastExtensionParentXmlManager() {
        Node vastExtensionsNode = XmlUtils.getFirstMatchingChildNode(this.mNode, EXTENSIONS);
        if (vastExtensionsNode == null) {
            return null;
        }
        return new VastExtensionParentXmlManager(vastExtensionsNode);
    }
}
