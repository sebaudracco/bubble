package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPointList {
    SVGPoint appendItem(SVGPoint sVGPoint) throws DOMException, SVGException;

    void clear() throws DOMException;

    SVGPoint getItem(int i) throws DOMException;

    int getNumberOfItems();

    SVGPoint initialize(SVGPoint sVGPoint) throws DOMException, SVGException;

    SVGPoint insertItemBefore(SVGPoint sVGPoint, int i) throws DOMException, SVGException;

    SVGPoint removeItem(int i) throws DOMException;

    SVGPoint replaceItem(SVGPoint sVGPoint, int i) throws DOMException, SVGException;
}
