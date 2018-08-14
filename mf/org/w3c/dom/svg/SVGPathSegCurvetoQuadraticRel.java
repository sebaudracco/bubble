package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegCurvetoQuadraticRel extends SVGPathSeg {
    float getX();

    float getX1();

    float getY();

    float getY1();

    void setX(float f) throws DOMException;

    void setX1(float f) throws DOMException;

    void setY(float f) throws DOMException;

    void setY1(float f) throws DOMException;
}
