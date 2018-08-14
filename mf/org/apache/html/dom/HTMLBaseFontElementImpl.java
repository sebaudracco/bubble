package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLBaseFontElement;

public class HTMLBaseFontElementImpl extends HTMLElementImpl implements HTMLBaseFontElement {
    private static final long serialVersionUID = -3650249921091097229L;

    public String getColor() {
        return capitalize(getAttribute("color"));
    }

    public void setColor(String color) {
        setAttribute("color", color);
    }

    public String getFace() {
        return capitalize(getAttribute("face"));
    }

    public void setFace(String face) {
        setAttribute("face", face);
    }

    public String getSize() {
        return getAttribute("size");
    }

    public void setSize(String size) {
        setAttribute("size", size);
    }

    public HTMLBaseFontElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
