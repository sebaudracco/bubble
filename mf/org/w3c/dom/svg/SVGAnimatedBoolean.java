package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAnimatedBoolean {
    boolean getAnimVal();

    boolean getBaseVal();

    void setBaseVal(boolean z) throws DOMException;
}
