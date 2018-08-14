package mf.org.apache.wml;

public interface WMLInputElement extends WMLElement {
    boolean getEmptyOk();

    String getFormat();

    int getMaxLength();

    String getName();

    int getSize();

    int getTabIndex();

    String getTitle();

    String getType();

    String getValue();

    String getXmlLang();

    void setEmptyOk(boolean z);

    void setFormat(String str);

    void setMaxLength(int i);

    void setName(String str);

    void setSize(int i);

    void setTabIndex(int i);

    void setTitle(String str);

    void setType(String str);

    void setValue(String str);

    void setXmlLang(String str);
}
