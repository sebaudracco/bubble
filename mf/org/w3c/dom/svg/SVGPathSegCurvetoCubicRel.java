package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegCurvetoCubicRel extends SVGPathSeg {
    float getX();

    float getX1();

    float getX2();

    float getY();

    float getY1();

    float getY2();

    void setX(float f) throws DOMException;

    void setX1(float f) throws DOMException;

    void setX2(float f) throws DOMException;

    void setY(float f) throws DOMException;

    void setY1(float f) throws DOMException;

    void setY2(float f) throws DOMException;
}
