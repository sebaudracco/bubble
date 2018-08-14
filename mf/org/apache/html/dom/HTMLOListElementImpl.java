package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLOListElement;

public class HTMLOListElementImpl extends HTMLElementImpl implements HTMLOListElement {
    private static final long serialVersionUID = 1293750546025862146L;

    public boolean getCompact() {
        return getBinary("compact");
    }

    public void setCompact(boolean compact) {
        setAttribute("compact", compact);
    }

    public int getStart() {
        return getInteger(getAttribute("start"));
    }

    public void setStart(int start) {
        setAttribute("start", String.valueOf(start));
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }

    public HTMLOListElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
