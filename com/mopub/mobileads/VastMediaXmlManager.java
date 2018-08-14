package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

class VastMediaXmlManager {
    private static final String DELIVERY = "delivery";
    private static final String HEIGHT = "height";
    private static final String VIDEO_TYPE = "type";
    private static final String WIDTH = "width";
    @NonNull
    private final Node mMediaNode;

    VastMediaXmlManager(@NonNull Node mediaNode) {
        Preconditions.checkNotNull(mediaNode, "mediaNode cannot be null");
        this.mMediaNode = mediaNode;
    }

    @Nullable
    String getDelivery() {
        return XmlUtils.getAttributeValue(this.mMediaNode, DELIVERY);
    }

    @Nullable
    Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "width");
    }

    @Nullable
    Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "height");
    }

    @Nullable
    String getType() {
        return XmlUtils.getAttributeValue(this.mMediaNode, "type");
    }

    @Nullable
    String getMediaUrl() {
        return XmlUtils.getNodeValue(this.mMediaNode);
    }
}
