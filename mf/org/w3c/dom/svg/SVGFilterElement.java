package mf.org.w3c.dom.svg;

public interface SVGFilterElement extends SVGElement, SVGURIReference, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    SVGAnimatedInteger getFilterResX();

    SVGAnimatedInteger getFilterResY();

    SVGAnimatedEnumeration getFilterUnits();

    SVGAnimatedLength getHeight();

    SVGAnimatedEnumeration getPrimitiveUnits();

    SVGAnimatedLength getWidth();

    SVGAnimatedLength getX();

    SVGAnimatedLength getY();

    void setFilterRes(int i, int i2);
}
