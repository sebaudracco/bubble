package mf.org.w3c.dom.html;

public interface HTMLTableRowElement extends HTMLElement {
    void deleteCell(int i);

    String getAlign();

    String getBgColor();

    HTMLCollection getCells();

    String getCh();

    String getChOff();

    int getRowIndex();

    int getSectionRowIndex();

    String getVAlign();

    HTMLElement insertCell(int i);

    void setAlign(String str);

    void setBgColor(String str);

    void setCells(HTMLCollection hTMLCollection);

    void setCh(String str);

    void setChOff(String str);

    void setRowIndex(int i);

    void setSectionRowIndex(int i);

    void setVAlign(String str);
}
