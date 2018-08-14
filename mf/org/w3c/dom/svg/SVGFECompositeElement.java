package mf.org.w3c.dom.svg;

public interface SVGFECompositeElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_FECOMPOSITE_OPERATOR_ARITHMETIC = (short) 6;
    public static final short SVG_FECOMPOSITE_OPERATOR_ATOP = (short) 4;
    public static final short SVG_FECOMPOSITE_OPERATOR_IN = (short) 2;
    public static final short SVG_FECOMPOSITE_OPERATOR_OUT = (short) 3;
    public static final short SVG_FECOMPOSITE_OPERATOR_OVER = (short) 1;
    public static final short SVG_FECOMPOSITE_OPERATOR_UNKNOWN = (short) 0;
    public static final short SVG_FECOMPOSITE_OPERATOR_XOR = (short) 5;

    SVGAnimatedString getIn1();

    SVGAnimatedString getIn2();

    SVGAnimatedNumber getK1();

    SVGAnimatedNumber getK2();

    SVGAnimatedNumber getK3();

    SVGAnimatedNumber getK4();

    SVGAnimatedEnumeration getOperator();
}
