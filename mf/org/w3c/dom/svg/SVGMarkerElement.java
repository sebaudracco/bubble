package mf.org.w3c.dom.svg;

public interface SVGMarkerElement extends SVGElement, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGFitToViewBox {
    public static final short SVG_MARKERUNITS_STROKEWIDTH = (short) 2;
    public static final short SVG_MARKERUNITS_UNKNOWN = (short) 0;
    public static final short SVG_MARKERUNITS_USERSPACEONUSE = (short) 1;
    public static final short SVG_MARKER_ORIENT_ANGLE = (short) 2;
    public static final short SVG_MARKER_ORIENT_AUTO = (short) 1;
    public static final short SVG_MARKER_ORIENT_UNKNOWN = (short) 0;

    SVGAnimatedLength getMarkerHeight();

    SVGAnimatedEnumeration getMarkerUnits();

    SVGAnimatedLength getMarkerWidth();

    SVGAnimatedAngle getOrientAngle();

    SVGAnimatedEnumeration getOrientType();

    SVGAnimatedLength getRefX();

    SVGAnimatedLength getRefY();

    void setOrientToAngle(SVGAngle sVGAngle);

    void setOrientToAuto();
}
