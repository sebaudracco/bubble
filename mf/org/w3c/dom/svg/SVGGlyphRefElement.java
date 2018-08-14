package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGGlyphRefElement extends SVGElement, SVGURIReference, SVGStylable {
    float getDx();

    float getDy();

    String getFormat();

    String getGlyphRef();

    float getX();

    float getY();

    void setDx(float f) throws DOMException;

    void setDy(float f) throws DOMException;

    void setFormat(String str) throws DOMException;

    void setGlyphRef(String str) throws DOMException;

    void setX(float f) throws DOMException;

    void setY(float f) throws DOMException;
}
