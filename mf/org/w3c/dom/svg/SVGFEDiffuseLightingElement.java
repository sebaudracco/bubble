package mf.org.w3c.dom.svg;

public interface SVGFEDiffuseLightingElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    SVGAnimatedNumber getDiffuseConstant();

    SVGAnimatedString getIn1();

    SVGAnimatedNumber getKernelUnitLengthX();

    SVGAnimatedNumber getKernelUnitLengthY();

    SVGAnimatedNumber getSurfaceScale();
}
