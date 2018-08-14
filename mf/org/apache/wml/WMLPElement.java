package mf.org.apache.wml;

public interface WMLPElement extends WMLElement {
    String getAlign();

    String getMode();

    String getXmlLang();

    void setAlign(String str);

    void setMode(String str);

    void setXmlLang(String str);
}
