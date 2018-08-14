package mf.org.apache.html.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import java.util.Locale;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLFormElement;

public class HTMLElementImpl extends ElementImpl implements HTMLElement {
    private static final long serialVersionUID = 5283925246324423495L;

    public HTMLElementImpl(HTMLDocumentImpl owner, String tagName) {
        super(owner, tagName.toUpperCase(Locale.ENGLISH));
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setId(String id) {
        setAttribute("id", id);
    }

    public String getTitle() {
        return getAttribute("title");
    }

    public void setTitle(String title) {
        setAttribute("title", title);
    }

    public String getLang() {
        return getAttribute("lang");
    }

    public void setLang(String lang) {
        setAttribute("lang", lang);
    }

    public String getDir() {
        return getAttribute("dir");
    }

    public void setDir(String dir) {
        setAttribute("dir", dir);
    }

    public String getClassName() {
        return getAttribute(C1484a.f2398e);
    }

    public void setClassName(String className) {
        setAttribute(C1484a.f2398e, className);
    }

    int getInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    boolean getBinary(String name) {
        return getAttributeNode(name) != null;
    }

    void setAttribute(String name, boolean value) {
        if (value) {
            setAttribute(name, name);
        } else {
            removeAttribute(name);
        }
    }

    public Attr getAttributeNode(String attrName) {
        return super.getAttributeNode(attrName.toLowerCase(Locale.ENGLISH));
    }

    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.getAttributeNode(localName.toLowerCase(Locale.ENGLISH));
        }
        return super.getAttributeNodeNS(namespaceURI, localName);
    }

    public String getAttribute(String attrName) {
        return super.getAttribute(attrName.toLowerCase(Locale.ENGLISH));
    }

    public String getAttributeNS(String namespaceURI, String localName) {
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.getAttribute(localName.toLowerCase(Locale.ENGLISH));
        }
        return super.getAttributeNS(namespaceURI, localName);
    }

    public final NodeList getElementsByTagName(String tagName) {
        return super.getElementsByTagName(tagName.toUpperCase(Locale.ENGLISH));
    }

    public final NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.getElementsByTagName(localName.toUpperCase(Locale.ENGLISH));
        }
        return super.getElementsByTagNameNS(namespaceURI, localName.toUpperCase(Locale.ENGLISH));
    }

    String capitalize(String value) {
        char[] chars = value.toCharArray();
        if (chars.length <= 0) {
            return value;
        }
        chars[0] = Character.toUpperCase(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return String.valueOf(chars);
    }

    String getCapitalized(String name) {
        String value = getAttribute(name);
        if (value == null) {
            return value;
        }
        char[] chars = value.toCharArray();
        if (chars.length <= 0) {
            return value;
        }
        chars[0] = Character.toUpperCase(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return String.valueOf(chars);
    }

    public HTMLFormElement getForm() {
        for (Node parent = getParentNode(); parent != null; parent = parent.getParentNode()) {
            if (parent instanceof HTMLFormElement) {
                return (HTMLFormElement) parent;
            }
        }
        return null;
    }
}
