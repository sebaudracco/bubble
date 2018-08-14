package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLTableRowElement;
import mf.org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableSectionElementImpl extends HTMLElementImpl implements HTMLTableSectionElement {
    private static final long serialVersionUID = 1016412997716618027L;
    private HTMLCollectionImpl _rows;

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public String getCh() {
        String ch = getAttribute("char");
        if (ch == null || ch.length() <= 1) {
            return ch;
        }
        return ch.substring(0, 1);
    }

    public void setCh(String ch) {
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        setAttribute("char", ch);
    }

    public String getChOff() {
        return getAttribute("charoff");
    }

    public void setChOff(String chOff) {
        setAttribute("charoff", chOff);
    }

    public String getVAlign() {
        return capitalize(getAttribute("valign"));
    }

    public void setVAlign(String vAlign) {
        setAttribute("valign", vAlign);
    }

    public HTMLCollection getRows() {
        if (this._rows == null) {
            this._rows = new HTMLCollectionImpl(this, (short) 7);
        }
        return this._rows;
    }

    public HTMLElement insertRow(int index) {
        HTMLTableRowElementImpl newRow = new HTMLTableRowElementImpl((HTMLDocumentImpl) getOwnerDocument(), "TR");
        newRow.insertCell(0);
        if (insertRowX(index, newRow) >= 0) {
            appendChild(newRow);
        }
        return newRow;
    }

    int insertRowX(int index, HTMLTableRowElementImpl newRow) {
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    insertBefore(newRow, child);
                    return -1;
                }
                index--;
            }
        }
        return index;
    }

    public void deleteRow(int index) {
        deleteRowX(index);
    }

    int deleteRowX(int index) {
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    removeChild(child);
                    return -1;
                }
                index--;
            }
        }
        return index;
    }

    public Node cloneNode(boolean deep) {
        HTMLTableSectionElementImpl clonedNode = (HTMLTableSectionElementImpl) super.cloneNode(deep);
        clonedNode._rows = null;
        return clonedNode;
    }

    public HTMLTableSectionElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
