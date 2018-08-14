package com.inmobi.ads;

import com.inmobi.ads.ah.C3000a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.VastExtensionXmlManager;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* compiled from: IasVastHelper */
class C3083s {
    private static final String f7537a = C3083s.class.getSimpleName();

    C3083s() {
    }

    static void m9975a(Document document, bo boVar) {
        List<Node> a = bm.m9535a(document, VastExtensionXmlManager.AVID);
        if (a != null) {
            for (Node a2 : a) {
                C3083s.m9976a(a2, boVar);
            }
        }
    }

    private static void m9976a(Node node, bo boVar) {
        int i = 0;
        if (node.hasChildNodes()) {
            String nodeName = node.getNodeName();
            int i2 = -1;
            switch (nodeName.hashCode()) {
                case -2077435339:
                    if (nodeName.equals(VastExtensionXmlManager.AD_VERIFICATIONS)) {
                        i2 = 1;
                        break;
                    }
                    break;
                case -1320080837:
                    if (nodeName.equals(VastExtensionXmlManager.VERIFICATION)) {
                        i2 = 2;
                        break;
                    }
                    break;
                case 2021392:
                    if (nodeName.equals(VastExtensionXmlManager.AVID)) {
                        i2 = 0;
                        break;
                    }
                    break;
                case 1561251035:
                    if (nodeName.equals("JavaScriptResource")) {
                        i2 = 3;
                        break;
                    }
                    break;
            }
            Node a;
            switch (i2) {
                case 0:
                    a = bm.m9537a(node, VastExtensionXmlManager.AD_VERIFICATIONS);
                    if (a != null) {
                        C3083s.m9976a(a, boVar);
                        return;
                    }
                    return;
                case 1:
                    if (node.hasChildNodes()) {
                        NodeList childNodes = node.getChildNodes();
                        int length = childNodes.getLength();
                        while (i < length) {
                            C3083s.m9976a(childNodes.item(i), boVar);
                            i++;
                        }
                        return;
                    }
                    return;
                case 2:
                    a = bm.m9537a(node, "JavaScriptResource");
                    if (a != null) {
                        C3083s.m9976a(a, boVar);
                        return;
                    }
                    return;
                case 3:
                    String a2 = bm.m9534a(node);
                    if (a2 != null) {
                        boVar.m9568a(new ah(a2, 0, C3000a.TRACKER_EVENT_TYPE_IAS, null));
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7537a, "IAS JavaScript URL found inside VAST : " + a2);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
