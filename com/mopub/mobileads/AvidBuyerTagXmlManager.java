package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Node;

class AvidBuyerTagXmlManager {
    private static final String AD_VERIFICATIONS = "AdVerifications";
    private static final String JAVA_SCRIPT_RESOURCE = "JavaScriptResource";
    private static final String VERIFICATION = "Verification";
    private final Node mAvidNode;

    AvidBuyerTagXmlManager(@NonNull Node avidNode) {
        Preconditions.checkNotNull(avidNode);
        this.mAvidNode = avidNode;
    }

    @NonNull
    Set<String> getJavaScriptResources() {
        Set<String> avidJavaScriptResources = new HashSet();
        Node adVerification = XmlUtils.getFirstMatchingChildNode(this.mAvidNode, "AdVerifications");
        if (adVerification != null) {
            List<Node> verifications = XmlUtils.getMatchingChildNodes(adVerification, "Verification");
            if (verifications != null) {
                for (Node verification : verifications) {
                    Node javaScriptResource = XmlUtils.getFirstMatchingChildNode(verification, JAVA_SCRIPT_RESOURCE);
                    if (javaScriptResource != null) {
                        avidJavaScriptResources.add(XmlUtils.getNodeValue(javaScriptResource));
                    }
                }
            }
        }
        return avidJavaScriptResources;
    }
}
