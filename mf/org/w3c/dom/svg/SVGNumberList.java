package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGNumberList {
    SVGNumber appendItem(SVGNumber sVGNumber) throws DOMException, SVGException;

    void clear() throws DOMException;

    SVGNumber getItem(int i) throws DOMException;

    int getNumberOfItems();

    SVGNumber initialize(SVGNumber sVGNumber) throws DOMException, SVGException;

    SVGNumber insertItemBefore(SVGNumber sVGNumber, int i) throws DOMException, SVGException;

    SVGNumber removeItem(int i) throws DOMException;

    SVGNumber replaceItem(SVGNumber sVGNumber, int i) throws DOMException, SVGException;
}
