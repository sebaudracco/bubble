package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLUListElement;

public class HTMLUListElementImpl extends HTMLElementImpl implements HTMLUListElement {
    private static final long serialVersionUID = -3220401442015109211L;

    public boolean getCompact() {
        return getBinary("compact");
    }

    public void setCompact(boolean compact) {
        setAttribute("compact", compact);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }

    public HTMLUListElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
