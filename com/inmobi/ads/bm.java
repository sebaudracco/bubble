package com.inmobi.ads;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.webkit.URLUtil;
import com.inmobi.ads.C3046c.C3043g;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.ads.bl.C3033a;
import com.inmobi.ads.bl.C3033a.C3032a;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.p118a.C3213c;
import com.inmobi.signals.C3276n;
import com.mopub.mobileads.VastResourceXmlManager;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* compiled from: VastHelper */
public class bm {
    private static final String f7285a = bm.class.getSimpleName();
    private static final Map<String, C3000a> f7286d = new HashMap();
    private C3043g f7287b;
    private final String f7288c = "Progressive";
    private int f7289e = 0;
    private bo f7290f;

    static {
        f7286d.put("Error", C3000a.TRACKER_EVENT_TYPE_ERROR);
        f7286d.put("Impression", C3000a.TRACKER_EVENT_TYPE_RENDER);
        f7286d.put("ClickTracking", C3000a.TRACKER_EVENT_TYPE_CLICK);
        f7286d.put("creativeView", C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW);
        f7286d.put("start", C3000a.TRACKER_EVENT_TYPE_PLAY);
        f7286d.put("firstQuartile", C3000a.TRACKER_EVENT_TYPE_Q1);
        f7286d.put("midpoint", C3000a.TRACKER_EVENT_TYPE_Q2);
        f7286d.put("thirdQuartile", C3000a.TRACKER_EVENT_TYPE_Q3);
        f7286d.put("complete", C3000a.TRACKER_EVENT_TYPE_Q4);
        f7286d.put("mute", C3000a.TRACKER_EVENT_TYPE_MUTE);
        f7286d.put("unmute", C3000a.TRACKER_EVENT_TYPE_UNMUTE);
        f7286d.put("pause", C3000a.TRACKER_EVENT_TYPE_PAUSE);
        f7286d.put("resume", C3000a.TRACKER_EVENT_TYPE_RESUME);
        f7286d.put("fullscreen", C3000a.TRACKER_EVENT_TYPE_FULLSCREEN);
        f7286d.put("exitFullscreen", C3000a.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN);
    }

    public bm(C3043g c3043g) {
        this.f7287b = c3043g;
        this.f7290f = new bo(this.f7287b);
    }

    private static C3143c m9547c(String str) {
        NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, str, false, null);
        networkRequest.m9751a(false);
        networkRequest.m9754b(true);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        C3143c a = new C3144d(networkRequest).m10357a();
        try {
            C3276n.m10977a().m10979a(networkRequest.m9772t());
            C3276n.m10977a().m10981b(a.m10356f());
            C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Error in setting request-response data size. " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return a;
    }

    private static int m9550d(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return i;
    }

    private static Document m9551e(String str) throws ParserConfigurationException, SAXException, IOException, DOMException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
    }

    static Node m9537a(Node node, String str) {
        List b = m9545b(node, str);
        if (b == null || b.size() <= 0) {
            return null;
        }
        return (Node) b.get(0);
    }

    private static List<Node> m9545b(Node node, String str) {
        List<Node> arrayList = new ArrayList();
        if (node == null || str == null) {
            return arrayList;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == (short) 1 && str.equals(item.getNodeName())) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private static String m9548c(Node node, String str) {
        return m9534a(m9537a(node, str));
    }

    private static Node m9536a(List<Node> list, String str) {
        if (list == null || str == null) {
            return null;
        }
        Node node = null;
        int i = 0;
        while (i < list.size()) {
            Node a = m9537a((Node) list.get(i), str);
            if (a != null) {
                return a;
            }
            i++;
            node = a;
        }
        return node;
    }

    static List<Node> m9535a(Document document, String str) {
        if (document == null || str == null) {
            return null;
        }
        List<Node> arrayList = new ArrayList();
        NodeList elementsByTagName = document.getElementsByTagName(str);
        for (int i = 0; i < elementsByTagName.getLength(); i++) {
            arrayList.add(elementsByTagName.item(i));
        }
        if (elementsByTagName.getLength() == 0) {
            return null;
        }
        return arrayList;
    }

    static String m9534a(Node node) {
        if (node == null) {
            return null;
        }
        String textContent;
        try {
            textContent = node.getTextContent();
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Error getting node value; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            textContent = null;
        }
        if (textContent != null) {
            return textContent.trim();
        }
        return null;
    }

    public bo m9552a(String str) {
        if (str == null || str.isEmpty()) {
            m9538a(VastErrorCode.GENERAL_WRAPPER_ERROR);
            return this.f7290f;
        }
        C3143c c = m9547c(str);
        if (c.m10351a()) {
            m9538a(VastErrorCode.VAST_URI_NETWORK_ERROR);
        } else {
            m9553b(c.m10352b());
        }
        return this.f7290f;
    }

    @Nullable
    public bo m9553b(String str) {
        Throwable e;
        if (str == null || str.isEmpty()) {
            m9538a(VastErrorCode.WRAPPER_NO_AD);
            return this.f7290f;
        }
        try {
            Document e2 = m9551e(str);
            Node a = m9537a((Node) e2, "VAST");
            if (a == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: VAST node at appropriate hierarchy not found");
                m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                return this.f7290f;
            }
            a = m9537a(a, "Ad");
            if (a == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Ad node at appropriate hierarchy not found");
                m9538a(VastErrorCode.WRAPPER_NO_AD);
                return this.f7290f;
            }
            Node a2 = m9537a(a, "Wrapper");
            List a3;
            String c;
            if (a2 != null) {
                this.f7289e++;
                if (this.f7289e > this.f7287b.m9683a()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Schema Validation Error:Max VAST wrapper limit exceeded");
                    m9538a(VastErrorCode.WRAPPER_MAX_LIMIT_EXCEEDED);
                    return this.f7290f;
                } else if (m9549c(a2)) {
                    a3 = m9535a(e2, "TrackingEvents");
                    if (a3 != null) {
                        m9540a(m9545b((Node) a3.get(0), "Tracking"));
                    }
                    C3083s.m9975a(e2, this.f7290f);
                    m9542a(e2);
                    c = m9548c(a2, "VASTAdTagURI");
                    if (c == null) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: VastAdTagUri at appropriate hierarchy  not found");
                        m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                        return this.f7290f;
                    }
                    m9552a(c);
                } else {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Impression Tracker at appropriate hierarchy not found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
            }
            a = m9537a(a, "InLine");
            if (a == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: InLine node at appropriate hierarchy  not found");
                m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                return this.f7290f;
            } else if (m9549c(a)) {
                Node a4 = m9537a(a, "Creatives");
                if (a4 == null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Creatives at appropriate hierarchy  not found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                a3 = m9545b(a4, "Creative");
                if (a3 == null || a3.isEmpty()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Creative at appropriate hierarchy  found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                a2 = m9536a(a3, "Linear");
                if (a2 == null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Linear node  at appropriate hierarchy not found");
                    m9538a(VastErrorCode.UNSUPPORTED_AD_TYPE);
                    return this.f7290f;
                }
                a3 = m9545b(a2, "Duration");
                if (a3 == null || a3.isEmpty()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema Validation Error.Duration tag not found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                c = m9534a((Node) a3.get(0));
                if (c == null || c.isEmpty() || !c.matches("\\d*:[0-5][0-9]:[0-5][0-9](:[0-9][0-9][0-9])?")) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema Validation Error. Media Duration invalid");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                this.f7290f.m9576c(c);
                a = m9537a(a2, "MediaFiles");
                if (a == null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: MediaFiles node at appropriate hierarchy not found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                Node a5 = m9537a(a2, "VideoClicks");
                this.f7290f.m9574b(m9548c(a5, "ClickThrough"));
                m9546b(a5);
                m9540a(m9545b(m9537a(a2, "TrackingEvents"), "Tracking"));
                C3083s.m9975a(e2, this.f7290f);
                List b = m9545b(a, "MediaFile");
                if (b == null || b.isEmpty()) {
                    m9538a(VastErrorCode.LINEAR_MEDIA_FILE_NOT_FOUND);
                    return this.f7290f;
                }
                String a6;
                int i;
                for (int i2 = 0; i2 < b.size(); i2++) {
                    a = (Element) b.get(i2);
                    a6 = m9534a(a);
                    if (a6 != null && !a6.trim().isEmpty()) {
                        String attribute = a.getAttribute("delivery");
                        String attribute2 = a.getAttribute("type");
                        int d = m9550d(a.getAttribute("bitrate"));
                        if (d > 0 && attribute != null && attribute.trim().equalsIgnoreCase("Progressive")) {
                            List d2 = this.f7287b.m9686d();
                            if (attribute2 != null) {
                                for (i = 0; i < d2.size(); i++) {
                                    if (attribute2.equalsIgnoreCase((String) d2.get(i))) {
                                        this.f7290f.m9570a(new bn(a6, attribute, attribute2, d));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.f7290f.mo6220d().isEmpty()) {
                    m9538a(VastErrorCode.NO_SUPPORTED_MEDIA);
                }
                a3 = m9545b(a4, "Creative");
                if (a3 == null || a3.isEmpty()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Creative at appropriate hierarchy  found");
                    m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                    return this.f7290f;
                }
                a = m9536a(a3, "CompanionAds");
                if (a == null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "No companion ads found; Returning ...");
                    return this.f7290f;
                }
                List b2 = m9545b(a, "Companion");
                for (i = 0; i < b2.size(); i++) {
                    a = (Element) b2.get(i);
                    int d3 = m9550d(a.getAttribute("width"));
                    int d4 = m9550d(a.getAttribute("height"));
                    if (!(d3 == 0 || d4 == 0)) {
                        String c2 = m9548c(a, "CompanionClickThrough");
                        if (!URLUtil.isValidUrl(c2)) {
                            c2 = null;
                        }
                        bl blVar = new bl(d3, d4, c2);
                        Node a7 = m9537a(a, VastResourceXmlManager.STATIC_RESOURCE);
                        if (a7 != null) {
                            String a8 = m9534a(a7);
                            c2 = ((Element) a7).getAttribute(VastResourceXmlManager.CREATIVE_TYPE);
                            if (!(c2 == null || c2.trim().isEmpty() || !bl.f7278a.contains(c2))) {
                                blVar.m9530a(new C3033a(C3032a.CREATIVE_TYPE_STATIC, a8));
                            }
                        }
                        a7 = m9537a(a, VastResourceXmlManager.HTML_RESOURCE);
                        if (a7 != null) {
                            blVar.m9530a(new C3033a(C3032a.CREATIVE_TYPE_HTML, m9534a(a7)));
                        }
                        a7 = m9537a(a, VastResourceXmlManager.IFRAME_RESOURCE);
                        if (a7 != null) {
                            blVar.m9530a(new C3033a(C3032a.CREATIVE_TYPE_IFRAME, m9534a(a7)));
                        }
                        m9541a(m9545b(a, "CompanionClickTracking"), blVar);
                        for (Node a9 : m9545b(m9537a(a9, "TrackingEvents"), "Tracking")) {
                            c2 = ((Element) a9).getAttribute(NotificationCompat.CATEGORY_EVENT);
                            if (f7286d.containsKey(c2)) {
                                a6 = m9534a(a9);
                                if (URLUtil.isValidUrl(a6)) {
                                    blVar.m9529a(new ah(a6, 0, (C3000a) f7286d.get(c2), null));
                                } else {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Malformed URL: " + a6 + "; Discarding this tracker");
                                }
                            }
                        }
                        this.f7290f.m9573b(blVar);
                    }
                }
                if (b2.size() > 0 && this.f7290f.mo6222f().size() == 0) {
                    m9538a(VastErrorCode.GENERAL_COMPANION_ERROR);
                }
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "VAST Schema validation error: Impression Tracker at appropriate hierarchy  not found");
                m9538a(VastErrorCode.SCHEMA_VALIDATION_ERROR);
                return this.f7290f;
            }
            return this.f7290f;
        } catch (ParserConfigurationException e3) {
            e = e3;
            m9538a(VastErrorCode.XML_PARSING_ERROR);
            C3135c.m10255a().m10279a(new C3132b(e));
            return this.f7290f;
        } catch (SAXException e4) {
            e = e4;
            m9538a(VastErrorCode.XML_PARSING_ERROR);
            C3135c.m10255a().m10279a(new C3132b(e));
            return this.f7290f;
        } catch (IOException e5) {
            e = e5;
            m9538a(VastErrorCode.XML_PARSING_ERROR);
            C3135c.m10255a().m10279a(new C3132b(e));
            return this.f7290f;
        } catch (DOMException e6) {
            e = e6;
            m9538a(VastErrorCode.XML_PARSING_ERROR);
            C3135c.m10255a().m10279a(new C3132b(e));
            return this.f7290f;
        }
    }

    private void m9541a(List<Node> list, bl blVar) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if ((short) 1 == node.getNodeType()) {
                    String a = m9534a(node);
                    if (URLUtil.isValidUrl(a)) {
                        blVar.m9529a(new ah(a, 0, C3000a.TRACKER_EVENT_TYPE_CLICK, null));
                    } else {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Malformed URL: " + a + "; Discarding this tracker");
                    }
                }
            }
        }
    }

    private void m9542a(Document document) {
        m9543a(C3000a.TRACKER_EVENT_TYPE_CLICK, m9535a(document, "ClickTracking"));
    }

    private void m9546b(Node node) {
        m9543a(C3000a.TRACKER_EVENT_TYPE_CLICK, m9545b(node, "ClickTracking"));
    }

    private boolean m9549c(Node node) {
        if (node == null) {
            return false;
        }
        m9543a(C3000a.TRACKER_EVENT_TYPE_ERROR, m9545b(node, "Error"));
        List b = m9545b(node, "Impression");
        if (b == null || b.isEmpty() || !m9543a(C3000a.TRACKER_EVENT_TYPE_RENDER, b)) {
            return false;
        }
        return true;
    }

    private void m9540a(List<Node> list) {
        if (list != null && list.size() != 0) {
            for (Node node : list) {
                String attribute = ((Element) node).getAttribute(NotificationCompat.CATEGORY_EVENT);
                if (f7286d.containsKey(attribute)) {
                    m9544a((C3000a) f7286d.get(attribute), node);
                }
            }
        }
    }

    private boolean m9543a(C3000a c3000a, List<Node> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Node node = (Node) list.get(i);
                if (node.getNodeType() == (short) 1 && !m9544a(c3000a, node)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean m9544a(C3000a c3000a, Node node) {
        String a = m9534a(node);
        if (URLUtil.isValidUrl(a)) {
            this.f7290f.m9568a(new ah(a, 0, c3000a, null));
            return true;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7285a, "Malformed URL " + a + " Discarding this tracker");
        if (c3000a == C3000a.TRACKER_EVENT_TYPE_RENDER) {
            return false;
        }
        return true;
    }

    private void m9538a(VastErrorCode vastErrorCode) {
        this.f7290f.m9567a(vastErrorCode);
        for (ah ahVar : this.f7290f.mo6221e()) {
            if (ahVar.m9297c() == C3000a.TRACKER_EVENT_TYPE_ERROR) {
                m9539a(vastErrorCode, ahVar);
            }
        }
    }

    private void m9539a(VastErrorCode vastErrorCode, ah ahVar) {
        C3213c a = C3213c.m10698a();
        String b = ahVar.m9295b();
        Map hashMap = new HashMap();
        hashMap.put("[ERRORCODE]", vastErrorCode.getId().toString());
        a.m10712a(C3155d.m10402a(b, hashMap), ahVar.m9298d(), true);
    }
}
