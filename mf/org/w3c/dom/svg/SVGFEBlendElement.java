package mf.org.w3c.dom.svg;

public interface SVGFEBlendElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_FEBLEND_MODE_DARKEN = (short) 4;
    public static final short SVG_FEBLEND_MODE_LIGHTEN = (short) 5;
    public static final short SVG_FEBLEND_MODE_MULTIPLY = (short) 2;
    public static final short SVG_FEBLEND_MODE_NORMAL = (short) 1;
    public static final short SVG_FEBLEND_MODE_SCREEN = (short) 3;
    public static final short SVG_FEBLEND_MODE_UNKNOWN = (short) 0;

    SVGAnimatedString getIn1();

    SVGAnimatedString getIn2();

    SVGAnimatedEnumeration getMode();
}
