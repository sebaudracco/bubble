package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLBodyElement;

public class HTMLBodyElementImpl extends HTMLElementImpl implements HTMLBodyElement {
    private static final long serialVersionUID = 9058852459426595202L;

    public String getALink() {
        return getAttribute("alink");
    }

    public void setALink(String aLink) {
        setAttribute("alink", aLink);
    }

    public String getBackground() {
        return getAttribute("background");
    }

    public void setBackground(String background) {
        setAttribute("background", background);
    }

    public String getBgColor() {
        return getAttribute("bgcolor");
    }

    public void setBgColor(String bgColor) {
        setAttribute("bgcolor", bgColor);
    }

    public String getLink() {
        return getAttribute("link");
    }

    public void setLink(String link) {
        setAttribute("link", link);
    }

    public String getText() {
        return getAttribute("text");
    }

    public void setText(String text) {
        setAttribute("text", text);
    }

    public String getVLink() {
        return getAttribute("vlink");
    }

    public void setVLink(String vLink) {
        setAttribute("vlink", vLink);
    }

    public HTMLBodyElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
