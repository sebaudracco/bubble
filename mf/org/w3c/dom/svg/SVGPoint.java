package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPoint {
    float getX();

    float getY();

    SVGPoint matrixTransform(SVGMatrix sVGMatrix);

    void setX(float f) throws DOMException;

    void setY(float f) throws DOMException;
}
