package mf.org.w3c.dom.svg;

public interface SVGFEMorphologyElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_MORPHOLOGY_OPERATOR_DILATE = (short) 2;
    public static final short SVG_MORPHOLOGY_OPERATOR_ERODE = (short) 1;
    public static final short SVG_MORPHOLOGY_OPERATOR_UNKNOWN = (short) 0;

    SVGAnimatedString getIn1();

    SVGAnimatedEnumeration getOperator();

    SVGAnimatedNumber getRadiusX();

    SVGAnimatedNumber getRadiusY();
}
