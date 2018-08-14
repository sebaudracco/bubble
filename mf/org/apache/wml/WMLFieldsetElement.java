package mf.org.apache.wml;

public interface WMLFieldsetElement extends WMLElement {
    String getTitle();

    String getXmlLang();

    void setTitle(String str);

    void setXmlLang(String str);
}
