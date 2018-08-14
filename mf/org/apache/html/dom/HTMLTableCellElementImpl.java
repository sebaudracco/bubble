package mf.org.apache.html.dom;

import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLTableCellElement;
import mf.org.w3c.dom.html.HTMLTableRowElement;

public class HTMLTableCellElementImpl extends HTMLElementImpl implements HTMLTableCellElement {
    private static final long serialVersionUID = -2406518157464313922L;

    public int getCellIndex() {
        Node parent = getParentNode();
        int index = 0;
        if (parent instanceof HTMLTableRowElement) {
            for (Object child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof HTMLTableCellElement) {
                    if (child == this) {
                        return index;
                    }
                    index++;
                }
            }
        }
        return -1;
    }

    public void setCellIndex(int cellIndex) {
        Node parent = getParentNode();
        if (parent instanceof HTMLTableRowElement) {
            for (Object child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof HTMLTableCellElement) {
                    if (cellIndex != 0) {
                        cellIndex--;
                    } else if (this != child) {
                        parent.insertBefore(this, child);
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        parent.appendChild(this);
    }

    public String getAbbr() {
        return getAttribute("abbr");
    }

    public void setAbbr(String abbr) {
        setAttribute("abbr", abbr);
    }

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public String getAxis() {
        return getAttribute("axis");
    }

    public void setAxis(String axis) {
        setAttribute("axis", axis);
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

    public int getColSpan() {
        return getInteger(getAttribute("colspan"));
    }

    public void setColSpan(int colspan) {
        setAttribute("colspan", String.valueOf(colspan));
    }

    public String getHeaders() {
        return getAttribute("headers");
    }

    public void setHeaders(String headers) {
        setAttribute("headers", headers);
    }

    public String getHeight() {
        return getAttribute("height");
    }

    public void setHeight(String height) {
        setAttribute("height", height);
    }

    public boolean getNoWrap() {
        return getBinary("nowrap");
    }

    public void setNoWrap(boolean noWrap) {
        setAttribute("nowrap", noWrap);
    }

    public int getRowSpan() {
        return getInteger(getAttribute("rowspan"));
    }

    public void setRowSpan(int rowspan) {
        setAttribute("rowspan", String.valueOf(rowspan));
    }

    public String getScope() {
        return getAttribute("scope");
    }

    public void setScope(String scope) {
        setAttribute("scope", scope);
    }

    public String getVAlign() {
        return capitalize(getAttribute("valign"));
    }

    public void setVAlign(String vAlign) {
        setAttribute("valign", vAlign);
    }

    public String getWidth() {
        return getAttribute("width");
    }

    public void setWidth(String width) {
        setAttribute("width", width);
    }

    public HTMLTableCellElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
