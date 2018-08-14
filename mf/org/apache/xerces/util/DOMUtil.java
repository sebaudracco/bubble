package mf.org.apache.xerces.util;

import java.lang.reflect.Method;
import java.util.Hashtable;
import mf.org.apache.xerces.dom.AttrImpl;
import mf.org.apache.xerces.dom.DocumentImpl;
import mf.org.apache.xerces.impl.xs.opti.ElementImpl;
import mf.org.apache.xerces.impl.xs.opti.NodeImpl;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ls.LSException;

public class DOMUtil {

    static class ThrowableMethods {
        private static Method fgThrowableInitCauseMethod;
        private static boolean fgThrowableMethodsAvailable;

        static {
            fgThrowableInitCauseMethod = null;
            fgThrowableMethodsAvailable = false;
            try {
                fgThrowableInitCauseMethod = Throwable.class.getMethod("initCause", new Class[]{Throwable.class});
                fgThrowableMethodsAvailable = true;
            } catch (Exception e) {
                fgThrowableInitCauseMethod = null;
                fgThrowableMethodsAvailable = false;
            }
        }

        private ThrowableMethods() {
        }
    }

    protected DOMUtil() {
    }

    public static void copyInto(Node src, Node dest) throws DOMException {
        Document factory = dest.getOwnerDocument();
        boolean domimpl = factory instanceof DocumentImpl;
        Node start = src;
        Node parent = src;
        Node place = src;
        while (place != null) {
            Node node;
            int type = place.getNodeType();
            switch (type) {
                case 1:
                    Element element = factory.createElement(place.getNodeName());
                    Object node2 = element;
                    NamedNodeMap attrs = place.getAttributes();
                    int attrCount = attrs.getLength();
                    for (int i = 0; i < attrCount; i++) {
                        Attr attr = (Attr) attrs.item(i);
                        String attrName = attr.getNodeName();
                        element.setAttribute(attrName, attr.getNodeValue());
                        if (domimpl && !attr.getSpecified()) {
                            ((AttrImpl) element.getAttributeNode(attrName)).setSpecified(false);
                        }
                    }
                    break;
                case 3:
                    node = factory.createTextNode(place.getNodeValue());
                    break;
                case 4:
                    node = factory.createCDATASection(place.getNodeValue());
                    break;
                case 5:
                    node = factory.createEntityReference(place.getNodeName());
                    break;
                case 7:
                    node = factory.createProcessingInstruction(place.getNodeName(), place.getNodeValue());
                    break;
                case 8:
                    node = factory.createComment(place.getNodeValue());
                    break;
                default:
                    throw new IllegalArgumentException("can't copy node type, " + type + " (" + place.getNodeName() + ')');
            }
            dest.appendChild(node);
            if (place.hasChildNodes()) {
                parent = place;
                place = place.getFirstChild();
                dest = node;
            } else {
                place = place.getNextSibling();
                while (place == null && parent != start) {
                    place = parent.getNextSibling();
                    parent = parent.getParentNode();
                    dest = dest.getParentNode();
                }
            }
        }
    }

    public static Element getFirstChildElement(Node parent) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == (short) 1) {
                return (Element) child;
            }
        }
        return null;
    }

    public static Element getFirstVisibleChildElement(Node parent) {
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && !isHidden(child)) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }
        return null;
    }

    public static Element getFirstVisibleChildElement(Node parent, Hashtable hiddenNodes) {
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && !isHidden(child, hiddenNodes)) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }
        return null;
    }

    public static Element getLastChildElement(Node parent) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == (short) 1) {
                return (Element) child;
            }
        }
        return null;
    }

    public static Element getLastVisibleChildElement(Node parent) {
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && !isHidden(child)) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }
        return null;
    }

    public static Element getLastVisibleChildElement(Node parent, Hashtable hiddenNodes) {
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && !isHidden(child, hiddenNodes)) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == (short) 1) {
                return (Element) sibling;
            }
        }
        return null;
    }

    public static Element getNextVisibleSiblingElement(Node node) {
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == (short) 1 && !isHidden(sibling)) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    public static Element getNextVisibleSiblingElement(Node node, Hashtable hiddenNodes) {
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == (short) 1 && !isHidden(sibling, hiddenNodes)) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    public static void setHidden(Node node) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(true, false);
        } else if (node instanceof mf.org.apache.xerces.dom.NodeImpl) {
            ((mf.org.apache.xerces.dom.NodeImpl) node).setReadOnly(true, false);
        }
    }

    public static void setHidden(Node node, Hashtable hiddenNodes) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(true, false);
        } else {
            hiddenNodes.put(node, "");
        }
    }

    public static void setVisible(Node node) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(false, false);
        } else if (node instanceof mf.org.apache.xerces.dom.NodeImpl) {
            ((mf.org.apache.xerces.dom.NodeImpl) node).setReadOnly(false, false);
        }
    }

    public static void setVisible(Node node, Hashtable hiddenNodes) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(false, false);
        } else {
            hiddenNodes.remove(node);
        }
    }

    public static boolean isHidden(Node node) {
        if (node instanceof NodeImpl) {
            return ((NodeImpl) node).getReadOnly();
        }
        if (node instanceof mf.org.apache.xerces.dom.NodeImpl) {
            return ((mf.org.apache.xerces.dom.NodeImpl) node).getReadOnly();
        }
        return false;
    }

    public static boolean isHidden(Node node, Hashtable hiddenNodes) {
        if (node instanceof NodeImpl) {
            return ((NodeImpl) node).getReadOnly();
        }
        return hiddenNodes.containsKey(node);
    }

    public static Element getFirstChildElement(Node parent, String elemName) {
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && child.getNodeName().equals(elemName)) {
                return (Element) child;
            }
            child = child.getNextSibling();
        }
        return null;
    }

    public static Element getLastChildElement(Node parent, String elemName) {
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1 && child.getNodeName().equals(elemName)) {
                return (Element) child;
            }
            child = child.getPreviousSibling();
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node, String elemName) {
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == (short) 1 && sibling.getNodeName().equals(elemName)) {
                return (Element) sibling;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    public static Element getFirstChildElementNS(Node parent, String uri, String localpart) {
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1) {
                String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element) child;
                }
            }
            child = child.getNextSibling();
        }
        return null;
    }

    public static Element getLastChildElementNS(Node parent, String uri, String localpart) {
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1) {
                String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element) child;
                }
            }
            child = child.getPreviousSibling();
        }
        return null;
    }

    public static Element getNextSiblingElementNS(Node node, String uri, String localpart) {
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == (short) 1) {
                String siblingURI = sibling.getNamespaceURI();
                if (siblingURI != null && siblingURI.equals(uri) && sibling.getLocalName().equals(localpart)) {
                    return (Element) sibling;
                }
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    public static Element getFirstChildElement(Node parent, String[] elemNames) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == (short) 1) {
                for (Object equals : elemNames) {
                    if (child.getNodeName().equals(equals)) {
                        return (Element) child;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node parent, String[] elemNames) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == (short) 1) {
                for (Object equals : elemNames) {
                    if (child.getNodeName().equals(equals)) {
                        return (Element) child;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node, String[] elemNames) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == (short) 1) {
                for (Object equals : elemNames) {
                    if (sibling.getNodeName().equals(equals)) {
                        return (Element) sibling;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static Element getFirstChildElementNS(Node parent, String[][] elemNames) {
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1) {
                int i = 0;
                while (i < elemNames.length) {
                    String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element) child;
                    }
                    i++;
                }
                continue;
            }
            child = child.getNextSibling();
        }
        return null;
    }

    public static Element getLastChildElementNS(Node parent, String[][] elemNames) {
        Node child = parent.getLastChild();
        while (child != null) {
            if (child.getNodeType() == (short) 1) {
                int i = 0;
                while (i < elemNames.length) {
                    String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element) child;
                    }
                    i++;
                }
                continue;
            }
            child = child.getPreviousSibling();
        }
        return null;
    }

    public static Element getNextSiblingElementNS(Node node, String[][] elemNames) {
        Node sibling = node.getNextSibling();
        while (sibling != null) {
            if (sibling.getNodeType() == (short) 1) {
                int i = 0;
                while (i < elemNames.length) {
                    String uri = sibling.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && sibling.getLocalName().equals(elemNames[i][1])) {
                        return (Element) sibling;
                    }
                    i++;
                }
                continue;
            }
            sibling = sibling.getNextSibling();
        }
        return null;
    }

    public static Element getFirstChildElement(Node parent, String elemName, String attrName, String attrValue) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == (short) 1) {
                Element element = (Element) child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node parent, String elemName, String attrName, String attrValue) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == (short) 1) {
                Element element = (Element) child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node, String elemName, String attrName, String attrValue) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == (short) 1) {
                Element element = (Element) sibling;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static String getChildText(Node node) {
        if (node == null) {
            return null;
        }
        StringBuffer str = new StringBuffer();
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            short type = child.getNodeType();
            if (type == (short) 3) {
                str.append(child.getNodeValue());
            } else if (type == (short) 4) {
                str.append(getChildText(child));
            }
        }
        return str.toString();
    }

    public static String getName(Node node) {
        return node.getNodeName();
    }

    public static String getLocalName(Node node) {
        String name = node.getLocalName();
        return name != null ? name : node.getNodeName();
    }

    public static Element getParent(Element elem) {
        Node parent = elem.getParentNode();
        if (parent instanceof Element) {
            return (Element) parent;
        }
        return null;
    }

    public static Document getDocument(Node node) {
        return node.getOwnerDocument();
    }

    public static Element getRoot(Document doc) {
        return doc.getDocumentElement();
    }

    public static Attr getAttr(Element elem, String name) {
        return elem.getAttributeNode(name);
    }

    public static Attr getAttrNS(Element elem, String nsUri, String localName) {
        return elem.getAttributeNodeNS(nsUri, localName);
    }

    public static Attr[] getAttrs(Element elem) {
        NamedNodeMap attrMap = elem.getAttributes();
        Attr[] attrArray = new Attr[attrMap.getLength()];
        for (int i = 0; i < attrMap.getLength(); i++) {
            attrArray[i] = (Attr) attrMap.item(i);
        }
        return attrArray;
    }

    public static String getValue(Attr attribute) {
        return attribute.getValue();
    }

    public static String getAttrValue(Element elem, String name) {
        return elem.getAttribute(name);
    }

    public static String getAttrValueNS(Element elem, String nsUri, String localName) {
        return elem.getAttributeNS(nsUri, localName);
    }

    public static String getPrefix(Node node) {
        return node.getPrefix();
    }

    public static String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    public static String getAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getAnnotation();
        }
        return null;
    }

    public static String getSyntheticAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getSyntheticAnnotation();
        }
        return null;
    }

    public static DOMException createDOMException(short code, Throwable cause) {
        DOMException de = new DOMException(code, cause != null ? cause.getMessage() : null);
        if (cause != null && ThrowableMethods.fgThrowableMethodsAvailable) {
            try {
                ThrowableMethods.fgThrowableInitCauseMethod.invoke(de, new Object[]{cause});
            } catch (Exception e) {
            }
        }
        return de;
    }

    public static LSException createLSException(short code, Throwable cause) {
        LSException lse = new LSException(code, cause != null ? cause.getMessage() : null);
        if (cause != null && ThrowableMethods.fgThrowableMethodsAvailable) {
            try {
                ThrowableMethods.fgThrowableInitCauseMethod.invoke(lse, new Object[]{cause});
            } catch (Exception e) {
            }
        }
        return lse;
    }
}
