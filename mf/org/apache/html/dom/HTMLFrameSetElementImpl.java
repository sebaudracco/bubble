package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLFrameSetElement;

public class HTMLFrameSetElementImpl extends HTMLElementImpl implements HTMLFrameSetElement {
    private static final long serialVersionUID = 8403143821972586708L;

    public String getCols() {
        return getAttribute("cols");
    }

    public void setCols(String cols) {
        setAttribute("cols", cols);
    }

    public String getRows() {
        return getAttribute("rows");
    }

    public void setRows(String rows) {
        setAttribute("rows", rows);
    }

    public HTMLFrameSetElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
