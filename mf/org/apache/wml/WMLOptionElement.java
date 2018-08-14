package mf.org.apache.wml;

public interface WMLOptionElement extends WMLElement {
    String getOnPick();

    String getTitle();

    String getValue();

    String getXmlLang();

    void setOnPick(String str);

    void setTitle(String str);

    void setValue(String str);

    void setXmlLang(String str);
}
