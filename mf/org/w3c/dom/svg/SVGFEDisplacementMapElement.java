package mf.org.w3c.dom.svg;

public interface SVGFEDisplacementMapElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_CHANNEL_A = (short) 4;
    public static final short SVG_CHANNEL_B = (short) 3;
    public static final short SVG_CHANNEL_G = (short) 2;
    public static final short SVG_CHANNEL_R = (short) 1;
    public static final short SVG_CHANNEL_UNKNOWN = (short) 0;

    SVGAnimatedString getIn1();

    SVGAnimatedString getIn2();

    SVGAnimatedNumber getScale();

    SVGAnimatedEnumeration getXChannelSelector();

    SVGAnimatedEnumeration getYChannelSelector();
}
