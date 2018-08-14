package mf.org.apache.wml;

public interface WMLSelectElement extends WMLElement {
    String getIName();

    String getIValue();

    boolean getMultiple();

    String getName();

    int getTabIndex();

    String getTitle();

    String getValue();

    String getXmlLang();

    void setIName(String str);

    void setIValue(String str);

    void setMultiple(boolean z);

    void setName(String str);

    void setTabIndex(int i);

    void setTitle(String str);

    void setValue(String str);

    void setXmlLang(String str);
}
