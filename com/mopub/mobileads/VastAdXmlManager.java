package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

class VastAdXmlManager {
    private static final String INLINE = "InLine";
    private static final String SEQUENCE = "sequence";
    private static final String WRAPPER = "Wrapper";
    @NonNull
    private final Node mAdNode;

    VastAdXmlManager(@NonNull Node adNode) {
        Preconditions.checkNotNull(adNode);
        this.mAdNode = adNode;
    }

    @Nullable
    VastInLineXmlManager getInLineXmlManager() {
        Node inLineNode = XmlUtils.getFirstMatchingChildNode(this.mAdNode, INLINE);
        if (inLineNode != null) {
            return new VastInLineXmlManager(inLineNode);
        }
        return null;
    }

    @Nullable
    VastWrapperXmlManager getWrapperXmlManager() {
        Node wrapperNode = XmlUtils.getFirstMatchingChildNode(this.mAdNode, WRAPPER);
        if (wrapperNode != null) {
            return new VastWrapperXmlManager(wrapperNode);
        }
        return null;
    }

    @Nullable
    String getSequence() {
        return XmlUtils.getAttributeValue(this.mAdNode, SEQUENCE);
    }
}
