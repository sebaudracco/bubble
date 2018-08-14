package mf.org.apache.wml;

public interface WMLAccessElement extends WMLElement {
    String getDomain();

    String getPath();

    void setDomain(String str);

    void setPath(String str);
}
