package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegCurvetoCubicSmoothRel extends SVGPathSeg {
    float getX();

    float getX2();

    float getY();

    float getY2();

    void setX(float f) throws DOMException;

    void setX2(float f) throws DOMException;

    void setY(float f) throws DOMException;

    void setY2(float f) throws DOMException;
}
