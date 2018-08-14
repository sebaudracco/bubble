package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLTableColElement;

public class HTMLTableColElementImpl extends HTMLElementImpl implements HTMLTableColElement {
    private static final long serialVersionUID = -6189626162811911792L;

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public String getCh() {
        String ch = getAttribute("char");
        if (ch == null || ch.length() <= 1) {
            return ch;
        }
        return ch.substring(0, 1);
    }

    public void setCh(String ch) {
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        setAttribute("char", ch);
    }

    public String getChOff() {
        return getAttribute("charoff");
    }

    public void setChOff(String chOff) {
        setAttribute("charoff", chOff);
    }

    public int getSpan() {
        return getInteger(getAttribute("span"));
    }

    public void setSpan(int span) {
        setAttribute("span", String.valueOf(span));
    }

    public String getVAlign() {
        return capitalize(getAttribute("valign"));
    }

    public void setVAlign(String vAlign) {
        setAttribute("valign", vAlign);
    }

    public String getWidth() {
        return getAttribute("width");
    }

    public void setWidth(String width) {
        setAttribute("width", width);
    }

    public HTMLTableColElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
