package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLStyleElement;

public class HTMLStyleElementImpl extends HTMLElementImpl implements HTMLStyleElement {
    private static final long serialVersionUID = -9001815754196124532L;

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public String getMedia() {
        return getAttribute("media");
    }

    public void setMedia(String media) {
        setAttribute("media", media);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }

    public HTMLStyleElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
