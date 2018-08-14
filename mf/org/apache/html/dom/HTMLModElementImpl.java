package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLModElement;

public class HTMLModElementImpl extends HTMLElementImpl implements HTMLModElement {
    private static final long serialVersionUID = 6424581972706750120L;

    public String getCite() {
        return getAttribute("cite");
    }

    public void setCite(String cite) {
        setAttribute("cite", cite);
    }

    public String getDateTime() {
        return getAttribute("datetime");
    }

    public void setDateTime(String dateTime) {
        setAttribute("datetime", dateTime);
    }

    public HTMLModElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
