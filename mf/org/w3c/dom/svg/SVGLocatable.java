package mf.org.w3c.dom.svg;

public interface SVGLocatable {
    SVGRect getBBox();

    SVGMatrix getCTM();

    SVGElement getFarthestViewportElement();

    SVGElement getNearestViewportElement();

    SVGMatrix getScreenCTM();

    SVGMatrix getTransformToElement(SVGElement sVGElement) throws SVGException;
}
