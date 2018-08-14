package mf.org.apache.wml;

public interface WMLImgElement extends WMLElement {
    String getAlign();

    String getAlt();

    String getHeight();

    String getHspace();

    String getLocalSrc();

    String getSrc();

    String getVspace();

    String getWidth();

    String getXmlLang();

    void setAlign(String str);

    void setAlt(String str);

    void setHeight(String str);

    void setHspace(String str);

    void setLocalSrc(String str);

    void setSrc(String str);

    void setVspace(String str);

    void setWidth(String str);

    void setXmlLang(String str);
}
