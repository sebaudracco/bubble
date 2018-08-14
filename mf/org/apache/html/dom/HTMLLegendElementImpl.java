package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLLegendElement;

public class HTMLLegendElementImpl extends HTMLElementImpl implements HTMLLegendElement {
    private static final long serialVersionUID = -621849164029630762L;

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

    public String getAlign() {
        return getAttribute("align");
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public HTMLLegendElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
