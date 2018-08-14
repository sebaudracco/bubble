package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLHRElement;

public class HTMLHRElementImpl extends HTMLElementImpl implements HTMLHRElement {
    private static final long serialVersionUID = -4210053417678939270L;

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public boolean getNoShade() {
        return getBinary("noshade");
    }

    public void setNoShade(boolean noShade) {
        setAttribute("noshade", noShade);
    }

    public String getSize() {
        return getAttribute("size");
    }

    public void setSize(String size) {
        setAttribute("size", size);
    }

    public String getWidth() {
        return getAttribute("width");
    }

    public void setWidth(String width) {
        setAttribute("width", width);
    }

    public HTMLHRElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
