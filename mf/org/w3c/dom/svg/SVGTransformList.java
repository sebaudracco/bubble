package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGTransformList {
    SVGTransform appendItem(SVGTransform sVGTransform) throws DOMException, SVGException;

    void clear() throws DOMException;

    SVGTransform consolidate();

    SVGTransform createSVGTransformFromMatrix(SVGMatrix sVGMatrix);

    SVGTransform getItem(int i) throws DOMException;

    int getNumberOfItems();

    SVGTransform initialize(SVGTransform sVGTransform) throws DOMException, SVGException;

    SVGTransform insertItemBefore(SVGTransform sVGTransform, int i) throws DOMException, SVGException;

    SVGTransform removeItem(int i) throws DOMException;

    SVGTransform replaceItem(SVGTransform sVGTransform, int i) throws DOMException, SVGException;
}
