package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLAreaElement;

public class HTMLAreaElementImpl extends HTMLElementImpl implements HTMLAreaElement {
    private static final long serialVersionUID = 7164004431531608995L;

    public String getAccessKey() {
        String accessKey = getAttribute("accesskey");
        if (accessKey == null || accessKey.length() <= 1) {
            return accessKey;
        }
        return accessKey.substring(0, 1);
    }

    public void setAccessKey(String accessKey) {
        if (accessKey != null && accessKey.length() > 1) {
            accessKey = accessKey.substring(0, 1);
        }
        setAttribute("accesskey", accessKey);
    }

    public String getAlt() {
        return getAttribute("alt");
    }

    public void setAlt(String alt) {
        setAttribute("alt", alt);
    }

    public String getCoords() {
        return getAttribute("coords");
    }

    public void setCoords(String coords) {
        setAttribute("coords", coords);
    }

    public String getHref() {
        return getAttribute("href");
    }

    public void setHref(String href) {
        setAttribute("href", href);
    }

    public boolean getNoHref() {
        return getBinary("nohref");
    }

    public void setNoHref(boolean noHref) {
        setAttribute("nohref", noHref);
    }

    public String getShape() {
        return capitalize(getAttribute("shape"));
    }

    public void setShape(String shape) {
        setAttribute("shape", shape);
    }

    public int getTabIndex() {
        return getInteger(getAttribute("tabindex"));
    }

    public void setTabIndex(int tabIndex) {
        setAttribute("tabindex", String.valueOf(tabIndex));
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public void setTarget(String target) {
        setAttribute("target", target);
    }

    public HTMLAreaElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
