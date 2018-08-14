package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLLabelElement;

public class HTMLLabelElementImpl extends HTMLElementImpl implements HTMLLabelElement, HTMLFormControl {
    private static final long serialVersionUID = 5774388295313199380L;

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

    public String getHtmlFor() {
        return getAttribute("for");
    }

    public void setHtmlFor(String htmlFor) {
        setAttribute("for", htmlFor);
    }

    public HTMLLabelElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
