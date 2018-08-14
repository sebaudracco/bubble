package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPathSegList {
    SVGPathSeg appendItem(SVGPathSeg sVGPathSeg) throws DOMException, SVGException;

    void clear() throws DOMException;

    SVGPathSeg getItem(int i) throws DOMException;

    int getNumberOfItems();

    SVGPathSeg initialize(SVGPathSeg sVGPathSeg) throws DOMException, SVGException;

    SVGPathSeg insertItemBefore(SVGPathSeg sVGPathSeg, int i) throws DOMException, SVGException;

    SVGPathSeg removeItem(int i) throws DOMException;

    SVGPathSeg replaceItem(SVGPathSeg sVGPathSeg, int i) throws DOMException, SVGException;
}
