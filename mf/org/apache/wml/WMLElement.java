package mf.org.apache.wml;

import mf.org.w3c.dom.Element;

public interface WMLElement extends Element {
    String getClassName();

    String getId();

    void setClassName(String str);

    void setId(String str);
}
