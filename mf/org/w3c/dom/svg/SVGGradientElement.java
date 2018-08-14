package mf.org.w3c.dom.svg;

public interface SVGGradientElement extends SVGElement, SVGURIReference, SVGExternalResourcesRequired, SVGStylable, SVGUnitTypes {
    public static final short SVG_SPREADMETHOD_PAD = (short) 1;
    public static final short SVG_SPREADMETHOD_REFLECT = (short) 2;
    public static final short SVG_SPREADMETHOD_REPEAT = (short) 3;
    public static final short SVG_SPREADMETHOD_UNKNOWN = (short) 0;

    SVGAnimatedTransformList getGradientTransform();

    SVGAnimatedEnumeration getGradientUnits();

    SVGAnimatedEnumeration getSpreadMethod();
}
