package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLMenuElement;

public class HTMLMenuElementImpl extends HTMLElementImpl implements HTMLMenuElement {
    private static final long serialVersionUID = -1489696654903916901L;

    public boolean getCompact() {
        return getBinary("compact");
    }

    public void setCompact(boolean compact) {
        setAttribute("compact", compact);
    }

    public HTMLMenuElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
