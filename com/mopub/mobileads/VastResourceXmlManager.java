package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VastResourceXmlManager {
    public static final String CREATIVE_TYPE = "creativeType";
    public static final String HTML_RESOURCE = "HTMLResource";
    public static final String IFRAME_RESOURCE = "IFrameResource";
    public static final String STATIC_RESOURCE = "StaticResource";
    @NonNull
    private final Node mResourceNode;

    VastResourceXmlManager(@NonNull Node resourceNode) {
        Preconditions.checkNotNull(resourceNode);
        this.mResourceNode = resourceNode;
    }

    @Nullable
    String getStaticResource() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mResourceNode, STATIC_RESOURCE));
    }

    @Nullable
    String getStaticResourceType() {
        String attribute = XmlUtils.getAttributeValue(XmlUtils.getFirstMatchingChildNode(this.mResourceNode, STATIC_RESOURCE), CREATIVE_TYPE);
        if (attribute != null) {
            return attribute.toLowerCase();
        }
        return null;
    }

    @Nullable
    String getIFrameResource() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mResourceNode, IFRAME_RESOURCE));
    }

    @Nullable
    String getHTMLResource() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mResourceNode, HTML_RESOURCE));
    }
}
