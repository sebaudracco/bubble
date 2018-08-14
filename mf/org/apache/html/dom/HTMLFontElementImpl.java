package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLFontElement;

public class HTMLFontElementImpl extends HTMLElementImpl implements HTMLFontElement {
    private static final long serialVersionUID = -415914342045846318L;

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

    public HTMLFontElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
