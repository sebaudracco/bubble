package mf.org.apache.xml.resolver.helpers;

import mf.javax.xml.XMLConstants;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;

public class Namespaces {
    public static String getPrefix(Element element) {
        String name = element.getTagName();
        String prefix = "";
        if (name.indexOf(58) > 0) {
            return name.substring(0, name.indexOf(58));
        }
        return prefix;
    }

    public static String getLocalName(Element element) {
        String name = element.getTagName();
        if (name.indexOf(58) > 0) {
            return name.substring(name.indexOf(58) + 1);
        }
        return name;
    }

    public static String getNamespaceURI(Node node, String prefix) {
        if (node == null || node.getNodeType() != (short) 1) {
            return null;
        }
        if (!prefix.equals("")) {
            String nsattr = "xmlns:" + prefix;
            if (((Element) node).hasAttribute(nsattr)) {
                return ((Element) node).getAttribute(nsattr);
            }
        } else if (((Element) node).hasAttribute(XMLConstants.XMLNS_ATTRIBUTE)) {
            return ((Element) node).getAttribute(XMLConstants.XMLNS_ATTRIBUTE);
        }
        return getNamespaceURI(node.getParentNode(), prefix);
    }

    public static String getNamespaceURI(Element element) {
        return getNamespaceURI(element, getPrefix(element));
    }
}
