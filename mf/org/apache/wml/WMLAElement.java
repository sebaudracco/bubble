package mf.org.apache.wml;

public interface WMLAElement extends WMLElement {
    String getHref();

    String getId();

    String getTitle();

    String getXmlLang();

    void setHref(String str);

    void setId(String str);

    void setTitle(String str);

    void setXmlLang(String str);
}
