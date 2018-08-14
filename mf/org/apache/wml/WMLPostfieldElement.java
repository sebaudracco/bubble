package mf.org.apache.wml;

public interface WMLPostfieldElement extends WMLElement {
    String getName();

    String getValue();

    void setName(String str);

    void setValue(String str);
}
