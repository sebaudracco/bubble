package mf.org.w3c.dom.xpath;

import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;

public interface XPathNamespace extends Node {
    public static final short XPATH_NAMESPACE_NODE = (short) 13;

    Element getOwnerElement();
}
