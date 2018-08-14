package mf.org.apache.wml;

public interface WMLDoElement extends WMLElement {
    String getLabel();

    String getName();

    String getOptional();

    String getType();

    String getXmlLang();

    void setLabel(String str);

    void setName(String str);

    void setOptional(String str);

    void setType(String str);

    void setXmlLang(String str);
}
