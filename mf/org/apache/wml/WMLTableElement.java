package mf.org.apache.wml;

public interface WMLTableElement extends WMLElement {
    String getAlign();

    int getColumns();

    String getTitle();

    String getXmlLang();

    void setAlign(String str);

    void setColumns(int i);

    void setTitle(String str);

    void setXmlLang(String str);
}
