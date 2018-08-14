package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLDListElement;

public class HTMLDListElementImpl extends HTMLElementImpl implements HTMLDListElement {
    private static final long serialVersionUID = -2130005642453038604L;

    public boolean getCompact() {
        return getBinary("compact");
    }

    public void setCompact(boolean compact) {
        setAttribute("compact", compact);
    }

    public HTMLDListElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
