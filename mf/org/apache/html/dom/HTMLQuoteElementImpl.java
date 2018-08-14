package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLQuoteElement;

public class HTMLQuoteElementImpl extends HTMLElementImpl implements HTMLQuoteElement {
    private static final long serialVersionUID = -67544811597906132L;

    public String getCite() {
        return getAttribute("cite");
    }

    public void setCite(String cite) {
        setAttribute("cite", cite);
    }

    public HTMLQuoteElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
