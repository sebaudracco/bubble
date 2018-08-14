package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegArcAbs extends SVGPathSeg {
    float getAngle();

    boolean getLargeArcFlag();

    float getR1();

    float getR2();

    boolean getSweepFlag();

    float getX();

    float getY();

    void setAngle(float f) throws DOMException;

    void setLargeArcFlag(boolean z) throws DOMException;

    void setR1(float f) throws DOMException;

    void setR2(float f) throws DOMException;

    void setSweepFlag(boolean z) throws DOMException;

    void setX(float f) throws DOMException;

    void setY(float f) throws DOMException;
}
