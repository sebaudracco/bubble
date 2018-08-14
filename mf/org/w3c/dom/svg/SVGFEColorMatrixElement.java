package mf.org.w3c.dom.svg;

public interface SVGFEColorMatrixElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_FECOLORMATRIX_TYPE_HUEROTATE = (short) 3;
    public static final short SVG_FECOLORMATRIX_TYPE_LUMINANCETOALPHA = (short) 4;
    public static final short SVG_FECOLORMATRIX_TYPE_MATRIX = (short) 1;
    public static final short SVG_FECOLORMATRIX_TYPE_SATURATE = (short) 2;
    public static final short SVG_FECOLORMATRIX_TYPE_UNKNOWN = (short) 0;

    SVGAnimatedString getIn1();

    SVGAnimatedEnumeration getType();

    SVGAnimatedNumberList getValues();
}
