package mf.org.apache.wml;

public interface WMLGoElement extends WMLElement {
    String getAcceptCharset();

    String getHref();

    String getMethod();

    String getSendreferer();

    void setAcceptCharset(String str);

    void setHref(String str);

    void setMethod(String str);

    void setSendreferer(String str);
}
