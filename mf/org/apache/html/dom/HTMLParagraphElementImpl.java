package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLParagraphElement;

public class HTMLParagraphElementImpl extends HTMLElementImpl implements HTMLParagraphElement {
    private static final long serialVersionUID = 8075287150683866287L;

    public String getAlign() {
        return getAttribute("align");
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public HTMLParagraphElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
