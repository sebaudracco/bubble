package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegLinetoAbs extends SVGPathSeg {
    float getX();

    float getY();

    void setX(float f) throws DOMException;

    void setY(float f) throws DOMException;
}
