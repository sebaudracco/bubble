package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAnimatedString {
    String getAnimVal();

    String getBaseVal();

    void setBaseVal(String str) throws DOMException;
}
