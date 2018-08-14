package mf.org.apache.xerces.dom3.as;

import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;

public interface ElementEditAS extends NodeEditAS {
    boolean canRemoveAttribute(String str);

    boolean canRemoveAttributeNS(String str, String str2);

    boolean canRemoveAttributeNode(Node node);

    boolean canSetAttribute(String str, String str2);

    boolean canSetAttributeNS(String str, String str2, String str3);

    boolean canSetAttributeNode(Attr attr);

    short contentType();

    NodeList getAttributeList();

    NodeList getChildElements();

    NodeList getDefinedElementTypes();

    NodeList getParentElements();

    boolean isElementDefined(String str);

    boolean isElementDefinedNS(String str, String str2, String str3);
}
