package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAnimatedEnumeration {
    short getAnimVal();

    short getBaseVal();

    void setBaseVal(short s) throws DOMException;
}
