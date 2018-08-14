package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLLinkElement;

public class HTMLLinkElementImpl extends HTMLElementImpl implements HTMLLinkElement {
    private static final long serialVersionUID = 874345520063418879L;

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public String getCharset() {
        return getAttribute("charset");
    }

    public void setCharset(String charset) {
        setAttribute("charset", charset);
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void setHref(String href) {
        setAttribute("href", href);
    }

    public String getHreflang() {
        return getAttribute("hreflang");
    }

    public void setHreflang(String hreflang) {
        setAttribute("hreflang", hreflang);
    }

    public String getMedia() {
        return getAttribute("media");
    }

    public void setMedia(String media) {
        setAttribute("media", media);
    }

    public String getRel() {
        return getAttribute("rel");
    }

    public void setRel(String rel) {
        setAttribute("rel", rel);
    }

    public String getRev() {
        return getAttribute("rev");
    }

    public void setRev(String rev) {
        setAttribute("rev", rev);
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public void setTarget(String target) {
        setAttribute("target", target);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }

    public HTMLLinkElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}