package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAnimatedInteger {
    int getAnimVal();

    int getBaseVal();

    void setBaseVal(int i) throws DOMException;
}
