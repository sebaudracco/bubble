package mf.org.w3c.dom.svg;

public interface SVGFEConvolveMatrixElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    public static final short SVG_EDGEMODE_DUPLICATE = (short) 1;
    public static final short SVG_EDGEMODE_NONE = (short) 3;
    public static final short SVG_EDGEMODE_UNKNOWN = (short) 0;
    public static final short SVG_EDGEMODE_WRAP = (short) 2;

    SVGAnimatedNumber getBias();

    SVGAnimatedNumber getDivisor();

    SVGAnimatedEnumeration getEdgeMode();

    SVGAnimatedNumberList getKernelMatrix();

    SVGAnimatedNumber getKernelUnitLengthX();

    SVGAnimatedNumber getKernelUnitLengthY();

    SVGAnimatedInteger getOrderX();

    SVGAnimatedInteger getOrderY();

    SVGAnimatedBoolean getPreserveAlpha();

    SVGAnimatedInteger getTargetX();

    SVGAnimatedInteger getTargetY();
}
