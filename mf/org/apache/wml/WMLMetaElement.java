package mf.org.apache.wml;

public interface WMLMetaElement extends WMLElement {
    String getContent();

    boolean getForua();

    String getHttpEquiv();

    String getName();

    String getScheme();

    void setContent(String str);

    void setForua(boolean z);

    void setHttpEquiv(String str);

    void setName(String str);

    void setScheme(String str);
}
