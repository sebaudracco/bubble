package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLHeadingElement;

public class HTMLHeadingElementImpl extends HTMLElementImpl implements HTMLHeadingElement {
    private static final long serialVersionUID = 6605827989383069095L;

    public String getAlign() {
        return getCapitalized("align");
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public HTMLHeadingElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
