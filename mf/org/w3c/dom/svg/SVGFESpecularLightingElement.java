package mf.org.w3c.dom.svg;

public interface SVGFESpecularLightingElement extends SVGElement, SVGFilterPrimitiveStandardAttributes {
    SVGAnimatedString getIn1();

    SVGAnimatedNumber getSpecularConstant();

    SVGAnimatedNumber getSpecularExponent();

    SVGAnimatedNumber getSurfaceScale();
}
