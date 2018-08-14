package mf.org.w3c.dom.svg;

public interface SVGFETurbulenceElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_STITCHTYPE_NOSTITCH = (short) 2;
    public static final short SVG_STITCHTYPE_STITCH = (short) 1;
    public static final short SVG_STITCHTYPE_UNKNOWN = (short) 0;
    public static final short SVG_TURBULENCE_TYPE_FRACTALNOISE = (short) 1;
    public static final short SVG_TURBULENCE_TYPE_TURBULENCE = (short) 2;
    public static final short SVG_TURBULENCE_TYPE_UNKNOWN = (short) 0;

    SVGAnimatedNumber getBaseFrequencyX();

    SVGAnimatedNumber getBaseFrequencyY();

    SVGAnimatedInteger getNumOctaves();

    SVGAnimatedNumber getSeed();

    SVGAnimatedEnumeration getStitchTiles();

    SVGAnimatedEnumeration getType();
}
