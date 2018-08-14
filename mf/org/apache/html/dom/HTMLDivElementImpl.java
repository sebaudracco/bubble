package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLDivElement;

public class HTMLDivElementImpl extends HTMLElementImpl implements HTMLDivElement {
    private static final long serialVersionUID = 2327098984177358833L;

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public HTMLDivElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
