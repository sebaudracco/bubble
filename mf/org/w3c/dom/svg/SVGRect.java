package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGRect {
    float getHeight();

    float getWidth();

    float getX();

    float getY();

    void setHeight(float f) throws DOMException;

    void setWidth(float f) throws DOMException;

    void setX(float f) throws DOMException;

    void setY(float f) throws DOMException;
}
