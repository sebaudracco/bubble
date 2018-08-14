package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLHtmlElement;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement {
    private static final long serialVersionUID = -4489734201536616166L;

    public String getVersion() {
        return capitalize(getAttribute("version"));
    }

    public void setVersion(String version) {
        setAttribute("version", version);
    }

    public HTMLHtmlElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
