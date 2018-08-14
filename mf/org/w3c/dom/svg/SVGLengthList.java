package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGLengthList {
    SVGLength appendItem(SVGLength sVGLength) throws DOMException, SVGException;

    void clear() throws DOMException;

    SVGLength getItem(int i) throws DOMException;

    int getNumberOfItems();

    SVGLength initialize(SVGLength sVGLength) throws DOMException, SVGException;

    SVGLength insertItemBefore(SVGLength sVGLength, int i) throws DOMException, SVGException;

    SVGLength removeItem(int i) throws DOMException;

    SVGLength replaceItem(SVGLength sVGLength, int i) throws DOMException, SVGException;
}
