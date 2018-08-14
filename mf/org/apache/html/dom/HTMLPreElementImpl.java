package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLPreElement;

public class HTMLPreElementImpl extends HTMLElementImpl implements HTMLPreElement {
    private static final long serialVersionUID = -4195360849946217644L;

    public int getWidth() {
        return getInteger(getAttribute("width"));
    }

    public void setWidth(int width) {
        setAttribute("width", String.valueOf(width));
    }

    public HTMLPreElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
