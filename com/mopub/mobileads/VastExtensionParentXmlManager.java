package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

public class VastExtensionParentXmlManager {
    private static final String EXTENSION = "Extension";
    @NonNull
    private final Node mVastExtensionParentNode;

    VastExtensionParentXmlManager(@NonNull Node vastExtensionParentNode) {
        Preconditions.checkNotNull(vastExtensionParentNode);
        this.mVastExtensionParentNode = vastExtensionParentNode;
    }

    @NonNull
    List<VastExtensionXmlManager> getVastExtensionXmlManagers() {
        List<VastExtensionXmlManager> vastExtensionXmlManagers = new ArrayList();
        List<Node> vastExtensionNodes = XmlUtils.getMatchingChildNodes(this.mVastExtensionParentNode, EXTENSION);
        if (vastExtensionNodes != null) {
            for (Node vastExtensionNode : vastExtensionNodes) {
                vastExtensionXmlManagers.add(new VastExtensionXmlManager(vastExtensionNode));
            }
        }
        return vastExtensionXmlManagers;
    }
}
