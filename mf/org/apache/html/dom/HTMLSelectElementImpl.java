package mf.org.apache.html.dom;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLOptionElement;
import mf.org.w3c.dom.html.HTMLSelectElement;

public class HTMLSelectElementImpl extends HTMLElementImpl implements HTMLSelectElement, HTMLFormControl {
    private static final long serialVersionUID = -6998282711006968187L;
    private HTMLCollection _options;

    public String getType() {
        return getAttribute("type");
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setValue(String value) {
        setAttribute(FirebaseAnalytics$Param.VALUE, value);
    }

    public int getSelectedIndex() {
        NodeList options = getElementsByTagName("OPTION");
        for (int i = 0; i < options.getLength(); i++) {
            if (((HTMLOptionElement) options.item(i)).getSelected()) {
                return i;
            }
        }
        return -1;
    }

    public void setSelectedIndex(int selectedIndex) {
        NodeList options = getElementsByTagName("OPTION");
        int i = 0;
        while (i < options.getLength()) {
            ((HTMLOptionElementImpl) options.item(i)).setSelected(i == selectedIndex);
            i++;
        }
    }

    public HTMLCollection getOptions() {
        if (this._options == null) {
            this._options = new HTMLCollectionImpl(this, (short) 6);
        }
        return this._options;
    }

    public int getLength() {
        return getOptions().getLength();
    }

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public boolean getMultiple() {
        return getBinary("multiple");
    }

    public void setMultiple(boolean multiple) {
        setAttribute("multiple", multiple);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public int getSize() {
        return getInteger(getAttribute("size"));
    }

    public void setSize(int size) {
        setAttribute("size", String.valueOf(size));
    }

    public int getTabIndex() {
        return getInteger(getAttribute("tabindex"));
    }

    public void setTabIndex(int tabIndex) {
        setAttribute("tabindex", String.valueOf(tabIndex));
    }

    public void add(HTMLElement element, HTMLElement before) {
        insertBefore(element, before);
    }

    public void remove(int index) {
        Node removed = getElementsByTagName("OPTION").item(index);
        if (removed != null) {
            removed.getParentNode().removeChild(removed);
        }
    }

    public void blur() {
    }

    public void focus() {
    }

    public NodeList getChildNodes() {
        return getChildNodesUnoptimized();
    }

    public Node cloneNode(boolean deep) {
        HTMLSelectElementImpl clonedNode = (HTMLSelectElementImpl) super.cloneNode(deep);
        clonedNode._options = null;
        return clonedNode;
    }

    public HTMLSelectElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
