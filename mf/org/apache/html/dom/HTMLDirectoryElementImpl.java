package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLDirectoryElement;

public class HTMLDirectoryElementImpl extends HTMLElementImpl implements HTMLDirectoryElement {
    private static final long serialVersionUID = -1010376135190194454L;

    public boolean getCompact() {
        return getBinary("compact");
    }

    public void setCompact(boolean compact) {
        setAttribute("compact", compact);
    }

    public HTMLDirectoryElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
