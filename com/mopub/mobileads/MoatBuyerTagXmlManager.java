package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.w3c.dom.Node;

class MoatBuyerTagXmlManager {
    private static final String ID = "id";
    private static final String VIEWABLE_IMPRESSION = "ViewableImpression";
    private final List<Node> mMoatVerificationNodes;

    MoatBuyerTagXmlManager(@NonNull List<Node> moatVerificationNodes) {
        Preconditions.checkNotNull(moatVerificationNodes);
        this.mMoatVerificationNodes = moatVerificationNodes;
    }

    @NonNull
    Set<String> getImpressionPixelsXml() {
        Set<String> impressionPixelsXml = new HashSet();
        for (Node verification : this.mMoatVerificationNodes) {
            if (verification != null) {
                String viewableImpressionXml = getViewableImpressionXml(XmlUtils.getFirstMatchingChildNode(verification, VIEWABLE_IMPRESSION));
                if (viewableImpressionXml != null) {
                    impressionPixelsXml.add(viewableImpressionXml);
                }
            }
        }
        return impressionPixelsXml;
    }

    @Nullable
    private String getViewableImpressionXml(@Nullable Node viewableImpression) {
        if (viewableImpression == null || !viewableImpression.hasAttributes()) {
            return null;
        }
        String idAttribute = XmlUtils.getAttributeValue(viewableImpression, "id");
        String content = XmlUtils.getNodeValue(viewableImpression);
        return String.format(Locale.US, "<ViewableImpression id=\"%s\"><![CDATA[%s]]</ViewableImpression>", new Object[]{idAttribute, content});
    }
}
