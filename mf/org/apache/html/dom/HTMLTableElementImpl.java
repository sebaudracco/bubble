package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLTableCaptionElement;
import mf.org.w3c.dom.html.HTMLTableElement;
import mf.org.w3c.dom.html.HTMLTableRowElement;
import mf.org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableElementImpl extends HTMLElementImpl implements HTMLTableElement {
    private static final long serialVersionUID = -1824053099870917532L;
    private HTMLCollectionImpl _bodies;
    private HTMLCollectionImpl _rows;

    public synchronized HTMLTableCaptionElement getCaption() {
        HTMLTableCaptionElement child;
        Node child2 = getFirstChild();
        while (child2 != null) {
            if ((child2 instanceof HTMLTableCaptionElement) && child2.getNodeName().equals("CAPTION")) {
                child = (HTMLTableCaptionElement) child2;
                break;
            }
            child2 = child2.getNextSibling();
        }
        child = null;
        return child;
    }

    public synchronized void setCaption(HTMLTableCaptionElement caption) {
        if (caption != null) {
            if (!caption.getTagName().equals("CAPTION")) {
                throw new IllegalArgumentException("HTM016 Argument 'caption' is not an element of type <CAPTION>.");
            }
        }
        deleteCaption();
        if (caption != null) {
            appendChild(caption);
        }
    }

    public synchronized HTMLElement createCaption() {
        Object section;
        HTMLElement section2 = getCaption();
        if (section2 != null) {
            section = section2;
        } else {
            section2 = new HTMLTableCaptionElementImpl((HTMLDocumentImpl) getOwnerDocument(), "CAPTION");
            appendChild(section2);
            HTMLElement section3 = section2;
        }
        return section;
    }

    public synchronized void deleteCaption() {
        Node old = getCaption();
        if (old != null) {
            removeChild(old);
        }
    }

    public synchronized HTMLTableSectionElement getTHead() {
        HTMLTableSectionElement child;
        Node child2 = getFirstChild();
        while (child2 != null) {
            if ((child2 instanceof HTMLTableSectionElement) && child2.getNodeName().equals("THEAD")) {
                child = (HTMLTableSectionElement) child2;
                break;
            }
            child2 = child2.getNextSibling();
        }
        child = null;
        return child;
    }

    public synchronized void setTHead(HTMLTableSectionElement tHead) {
        if (tHead != null) {
            if (!tHead.getTagName().equals("THEAD")) {
                throw new IllegalArgumentException("HTM017 Argument 'tHead' is not an element of type <THEAD>.");
            }
        }
        deleteTHead();
        if (tHead != null) {
            appendChild(tHead);
        }
    }

    public synchronized HTMLElement createTHead() {
        Object section;
        HTMLElement section2 = getTHead();
        if (section2 != null) {
            section = section2;
        } else {
            section2 = new HTMLTableSectionElementImpl((HTMLDocumentImpl) getOwnerDocument(), "THEAD");
            appendChild(section2);
            HTMLElement section3 = section2;
        }
        return section;
    }

    public synchronized void deleteTHead() {
        Node old = getTHead();
        if (old != null) {
            removeChild(old);
        }
    }

    public synchronized HTMLTableSectionElement getTFoot() {
        HTMLTableSectionElement child;
        Node child2 = getFirstChild();
        while (child2 != null) {
            if ((child2 instanceof HTMLTableSectionElement) && child2.getNodeName().equals("TFOOT")) {
                child = (HTMLTableSectionElement) child2;
                break;
            }
            child2 = child2.getNextSibling();
        }
        child = null;
        return child;
    }

    public synchronized void setTFoot(HTMLTableSectionElement tFoot) {
        if (tFoot != null) {
            if (!tFoot.getTagName().equals("TFOOT")) {
                throw new IllegalArgumentException("HTM018 Argument 'tFoot' is not an element of type <TFOOT>.");
            }
        }
        deleteTFoot();
        if (tFoot != null) {
            appendChild(tFoot);
        }
    }

    public synchronized HTMLElement createTFoot() {
        Object section;
        HTMLElement section2 = getTFoot();
        if (section2 != null) {
            section = section2;
        } else {
            section2 = new HTMLTableSectionElementImpl((HTMLDocumentImpl) getOwnerDocument(), "TFOOT");
            appendChild(section2);
            HTMLElement section3 = section2;
        }
        return section;
    }

    public synchronized void deleteTFoot() {
        Node old = getTFoot();
        if (old != null) {
            removeChild(old);
        }
    }

    public HTMLCollection getRows() {
        if (this._rows == null) {
            this._rows = new HTMLCollectionImpl(this, (short) 7);
        }
        return this._rows;
    }

    public HTMLCollection getTBodies() {
        if (this._bodies == null) {
            this._bodies = new HTMLCollectionImpl(this, (short) -2);
        }
        return this._bodies;
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

    public String getBorder() {
        return getAttribute("border");
    }

    public void setBorder(String border) {
        setAttribute("border", border);
    }

    public String getCellPadding() {
        return getAttribute("cellpadding");
    }

    public void setCellPadding(String cellPadding) {
        setAttribute("cellpadding", cellPadding);
    }

    public String getCellSpacing() {
        return getAttribute("cellspacing");
    }

    public void setCellSpacing(String cellSpacing) {
        setAttribute("cellspacing", cellSpacing);
    }

    public String getFrame() {
        return capitalize(getAttribute("frame"));
    }

    public void setFrame(String frame) {
        setAttribute("frame", frame);
    }

    public String getRules() {
        return capitalize(getAttribute("rules"));
    }

    public void setRules(String rules) {
        setAttribute("rules", rules);
    }

    public String getSummary() {
        return getAttribute("summary");
    }

    public void setSummary(String summary) {
        setAttribute("summary", summary);
    }

    public String getWidth() {
        return getAttribute("width");
    }

    public void setWidth(String width) {
        setAttribute("width", width);
    }

    public HTMLElement insertRow(int index) {
        HTMLTableRowElementImpl newRow = new HTMLTableRowElementImpl((HTMLDocumentImpl) getOwnerDocument(), "TR");
        insertRowX(index, newRow);
        return newRow;
    }

    void insertRowX(int index, HTMLTableRowElementImpl newRow) {
        Node lastSection = null;
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    insertBefore(newRow, child);
                    return;
                }
            } else if (child instanceof HTMLTableSectionElementImpl) {
                lastSection = child;
                index = ((HTMLTableSectionElementImpl) child).insertRowX(index, newRow);
                if (index < 0) {
                    return;
                }
            } else {
                continue;
            }
        }
        if (lastSection != null) {
            lastSection.appendChild(newRow);
        } else {
            appendChild(newRow);
        }
    }

    public synchronized void deleteRow(int index) {
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (!(child instanceof HTMLTableRowElement)) {
                if (child instanceof HTMLTableSectionElementImpl) {
                    index = ((HTMLTableSectionElementImpl) child).deleteRowX(index);
                    if (index < 0) {
                        break;
                    }
                }
            } else if (index == 0) {
                removeChild(child);
                break;
            } else {
                index--;
            }
        }
    }

    public Node cloneNode(boolean deep) {
        HTMLTableElementImpl clonedNode = (HTMLTableElementImpl) super.cloneNode(deep);
        clonedNode._rows = null;
        clonedNode._bodies = null;
        return clonedNode;
    }

    public HTMLTableElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
