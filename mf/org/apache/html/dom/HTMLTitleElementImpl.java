package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.html.HTMLTitleElement;

public class HTMLTitleElementImpl extends HTMLElementImpl implements HTMLTitleElement {
    private static final long serialVersionUID = 879646303512367875L;

    public String getText() {
        StringBuffer text = new StringBuffer();
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Text) {
                text.append(((Text) child).getData());
            }
        }
        return text.toString();
    }

    public void setText(String text) {
        Node child = getFirstChild();
        while (child != null) {
            Node next = child.getNextSibling();
            removeChild(child);
            child = next;
        }
        insertBefore(getOwnerDocument().createTextNode(text), getFirstChild());
    }

    public HTMLTitleElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
