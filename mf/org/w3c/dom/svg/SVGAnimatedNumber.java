package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAnimatedNumber {
    float getAnimVal();

    float getBaseVal();

    void setBaseVal(float f) throws DOMException;
}
