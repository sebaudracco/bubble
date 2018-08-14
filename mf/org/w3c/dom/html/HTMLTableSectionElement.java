package mf.org.w3c.dom.html;

public interface HTMLTableSectionElement extends HTMLElement {
    void deleteRow(int i);

    String getAlign();

    String getCh();

    String getChOff();

    HTMLCollection getRows();

    String getVAlign();

    HTMLElement insertRow(int i);

    void setAlign(String str);

    void setCh(String str);

    void setChOff(String str);

    void setVAlign(String str);
}
