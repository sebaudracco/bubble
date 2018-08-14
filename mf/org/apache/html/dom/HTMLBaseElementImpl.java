package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLBaseElement;

public class HTMLBaseElementImpl extends HTMLElementImpl implements HTMLBaseElement {
    private static final long serialVersionUID = -396648580810072153L;

    public String getHref() {
        return getAttribute("href");
    }

    public void setHref(String href) {
        setAttribute("href", href);
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public void setTarget(String target) {
        setAttribute("target", target);
    }

    public HTMLBaseElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
