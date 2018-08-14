package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLTableCellElement;
import mf.org.w3c.dom.html.HTMLTableElement;
import mf.org.w3c.dom.html.HTMLTableRowElement;
import mf.org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableRowElementImpl extends HTMLElementImpl implements HTMLTableRowElement {
    private static final long serialVersionUID = 5409562635656244263L;
    HTMLCollection _cells;

    public int getRowIndex() {
        Node parent = getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            parent = parent.getParentNode();
        }
        if (parent instanceof HTMLTableElement) {
            return getRowIndex(parent);
        }
        return -1;
    }

    public void setRowIndex(int rowIndex) {
        Node parent = getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            parent = parent.getParentNode();
        }
        if (parent instanceof HTMLTableElement) {
            ((HTMLTableElementImpl) parent).insertRowX(rowIndex, this);
        }
    }

    public int getSectionRowIndex() {
        Node parent = getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            return getRowIndex(parent);
        }
        return -1;
    }

    public void setSectionRowIndex(int sectionRowIndex) {
        Node parent = getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            ((HTMLTableSectionElementImpl) parent).insertRowX(sectionRowIndex, this);
        }
    }

    int getRowIndex(Node parent) {
        NodeList rows = ((HTMLElement) parent).getElementsByTagName("TR");
        for (int i = 0; i < rows.getLength(); i++) {
            if (rows.item(i) == this) {
                return i;
            }
        }
        return -1;
    }

    public HTMLCollection getCells() {
        if (this._cells == null) {
            this._cells = new HTMLCollectionImpl(this, (short) -3);
        }
        return this._cells;
    }

    public void setCells(HTMLCollection cells) {
        Node child;
        for (child = getFirstChild(); child != null; child = child.getNextSibling()) {
            removeChild(child);
        }
        int i = 0;
        child = cells.item(0);
        while (child != null) {
            appendChild(child);
            i++;
            child = cells.item(i);
        }
    }

    public HTMLElement insertCell(int index) {
        HTMLElement newCell = new HTMLTableCellElementImpl((HTMLDocumentImpl) getOwnerDocument(), "TD");
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableCellElement) {
                if (index == 0) {
                    insertBefore(newCell, child);
                    break;
                }
                index--;
            }
        }
        appendChild(newCell);
        return newCell;
    }

    public void deleteCell(int index) {
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableCellElement) {
                if (index == 0) {
                    removeChild(child);
                    return;
                }
                index--;
            }
        }
    }

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public String getBgColor() {
        return getAttribute("bgcolor");
    }

    public void setBgColor(String bgColor) {
        setAttribute("bgcolor", bgColor);
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

    public Node cloneNode(boolean deep) {
        HTMLTableRowElementImpl clonedNode = (HTMLTableRowElementImpl) super.cloneNode(deep);
        clonedNode._cells = null;
        return clonedNode;
    }

    public HTMLTableRowElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
