package mf.org.w3c.dom.svg;

public interface SVGComponentTransferFunctionElement extends SVGElement {
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_DISCRETE = (short) 3;
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_GAMMA = (short) 5;
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_IDENTITY = (short) 1;
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_LINEAR = (short) 4;
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_TABLE = (short) 2;
    public static final short SVG_FECOMPONENTTRANSFER_TYPE_UNKNOWN = (short) 0;

    SVGAnimatedNumber getAmplitude();

    SVGAnimatedNumber getExponent();

    SVGAnimatedNumber getIntercept();

    SVGAnimatedNumber getOffset();

    SVGAnimatedNumber getSlope();

    SVGAnimatedNumberList getTableValues();

    SVGAnimatedEnumeration getType();
}
