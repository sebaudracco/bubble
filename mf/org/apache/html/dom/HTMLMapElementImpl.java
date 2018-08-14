package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLMapElement;

public class HTMLMapElementImpl extends HTMLElementImpl implements HTMLMapElement {
    private static final long serialVersionUID = 7520887584251976392L;
    private HTMLCollection _areas;

    public HTMLCollection getAreas() {
        if (this._areas == null) {
            this._areas = new HTMLCollectionImpl(this, (short) -1);
        }
        return this._areas;
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public Node cloneNode(boolean deep) {
        HTMLMapElementImpl clonedNode = (HTMLMapElementImpl) super.cloneNode(deep);
        clonedNode._areas = null;
        return clonedNode;
    }

    public HTMLMapElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
