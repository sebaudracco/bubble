package mf.org.w3c.dom.svg;

public interface SVGPatternElement extends SVGElement, SVGURIReference, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGFitToViewBox, SVGUnitTypes {
    SVGAnimatedLength getHeight();

    SVGAnimatedEnumeration getPatternContentUnits();

    SVGAnimatedTransformList getPatternTransform();

    SVGAnimatedEnumeration getPatternUnits();

    SVGAnimatedLength getWidth();

    SVGAnimatedLength getX();

    SVGAnimatedLength getY();
}
