package mf.org.w3c.dom.svg;

public interface SVGMaskElement extends SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    SVGAnimatedLength getHeight();

    SVGAnimatedEnumeration getMaskContentUnits();

    SVGAnimatedEnumeration getMaskUnits();

    SVGAnimatedLength getWidth();

    SVGAnimatedLength getX();

    SVGAnimatedLength getY();
}
