package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGMatrix {
    SVGMatrix flipX();

    SVGMatrix flipY();

    float getA();

    float getB();

    float getC();

    float getD();

    float getE();

    float getF();

    SVGMatrix inverse() throws SVGException;

    SVGMatrix multiply(SVGMatrix sVGMatrix);

    SVGMatrix rotate(float f);

    SVGMatrix rotateFromVector(float f, float f2) throws SVGException;

    SVGMatrix scale(float f);

    SVGMatrix scaleNonUniform(float f, float f2);

    void setA(float f) throws DOMException;

    void setB(float f) throws DOMException;

    void setC(float f) throws DOMException;

    void setD(float f) throws DOMException;

    void setE(float f) throws DOMException;

    void setF(float f) throws DOMException;

    SVGMatrix skewX(float f);

    SVGMatrix skewY(float f);

    SVGMatrix translate(float f, float f2);
}
