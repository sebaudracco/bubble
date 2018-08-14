package mf.org.apache.html.dom;

import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLOptionElement;
import mf.org.w3c.dom.html.HTMLSelectElement;

public class HTMLOptionElementImpl extends HTMLElementImpl implements HTMLOptionElement {
    private static final long serialVersionUID = -4486774554137530907L;

    public boolean getDefaultSelected() {
        return getBinary("default-selected");
    }

    public void setDefaultSelected(boolean defaultSelected) {
        setAttribute("default-selected", defaultSelected);
    }

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

    public int getIndex() {
        Node parent = getParentNode();
        while (parent != null && !(parent instanceof HTMLSelectElement)) {
            parent = parent.getParentNode();
        }
        if (parent != null) {
            NodeList options = ((HTMLElement) parent).getElementsByTagName("OPTION");
            for (int i = 0; i < options.getLength(); i++) {
                if (options.item(i) == this) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void setIndex(int index) {
        Node parent = getParentNode();
        while (parent != null && !(parent instanceof HTMLSelectElement)) {
            parent = parent.getParentNode();
        }
        if (parent != null) {
            NodeList options = ((HTMLElement) parent).getElementsByTagName("OPTION");
            if (options.item(index) != this) {
                getParentNode().removeChild(this);
                Node item = options.item(index);
                item.getParentNode().insertBefore(this, item);
            }
        }
    }

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public String getLabel() {
        return capitalize(getAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL));
    }

    public void setLabel(String label) {
        setAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL, label);
    }

    public boolean getSelected() {
        return getBinary("selected");
    }

    public void setSelected(boolean selected) {
        setAttribute("selected", selected);
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setValue(String value) {
        setAttribute(FirebaseAnalytics$Param.VALUE, value);
    }

    public HTMLOptionElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
