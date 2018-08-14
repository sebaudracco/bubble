package mf.org.apache.wml;

public interface WMLTimerElement extends WMLElement {
    String getName();

    String getValue();

    void setName(String str);

    void setValue(String str);
}
