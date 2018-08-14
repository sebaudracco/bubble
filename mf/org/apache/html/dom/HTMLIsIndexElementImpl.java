package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLIsIndexElement;

public class HTMLIsIndexElementImpl extends HTMLElementImpl implements HTMLIsIndexElement {
    private static final long serialVersionUID = 3073521742049689699L;

    public String getPrompt() {
        return getAttribute("prompt");
    }

    public void setPrompt(String prompt) {
        setAttribute("prompt", prompt);
    }

    public HTMLIsIndexElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
