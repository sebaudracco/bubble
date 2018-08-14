package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLTableCaptionElement;

public class HTMLTableCaptionElementImpl extends HTMLElementImpl implements HTMLTableCaptionElement {
    private static final long serialVersionUID = 183703024771848940L;

    public String getAlign() {
        return getAttribute("align");
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public HTMLTableCaptionElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
