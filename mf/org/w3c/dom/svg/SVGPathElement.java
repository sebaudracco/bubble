package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.events.EventTarget;

public interface SVGPathElement extends SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGTransformable, EventTarget, SVGAnimatedPathData {
    SVGPathSegArcAbs createSVGPathSegArcAbs(float f, float f2, float f3, float f4, float f5, boolean z, boolean z2);

    SVGPathSegArcRel createSVGPathSegArcRel(float f, float f2, float f3, float f4, float f5, boolean z, boolean z2);

    SVGPathSegClosePath createSVGPathSegClosePath();

    SVGPathSegCurvetoCubicAbs createSVGPathSegCurvetoCubicAbs(float f, float f2, float f3, float f4, float f5, float f6);

    SVGPathSegCurvetoCubicRel createSVGPathSegCurvetoCubicRel(float f, float f2, float f3, float f4, float f5, float f6);

    SVGPathSegCurvetoCubicSmoothAbs createSVGPathSegCurvetoCubicSmoothAbs(float f, float f2, float f3, float f4);

    SVGPathSegCurvetoCubicSmoothRel createSVGPathSegCurvetoCubicSmoothRel(float f, float f2, float f3, float f4);

    SVGPathSegCurvetoQuadraticAbs createSVGPathSegCurvetoQuadraticAbs(float f, float f2, float f3, float f4);

    SVGPathSegCurvetoQuadraticRel createSVGPathSegCurvetoQuadraticRel(float f, float f2, float f3, float f4);

    SVGPathSegCurvetoQuadraticSmoothAbs createSVGPathSegCurvetoQuadraticSmoothAbs(float f, float f2);

    SVGPathSegCurvetoQuadraticSmoothRel createSVGPathSegCurvetoQuadraticSmoothRel(float f, float f2);

    SVGPathSegLinetoAbs createSVGPathSegLinetoAbs(float f, float f2);

    SVGPathSegLinetoHorizontalAbs createSVGPathSegLinetoHorizontalAbs(float f);

    SVGPathSegLinetoHorizontalRel createSVGPathSegLinetoHorizontalRel(float f);

    SVGPathSegLinetoRel createSVGPathSegLinetoRel(float f, float f2);

    SVGPathSegLinetoVerticalAbs createSVGPathSegLinetoVerticalAbs(float f);

    SVGPathSegLinetoVerticalRel createSVGPathSegLinetoVerticalRel(float f);

    SVGPathSegMovetoAbs createSVGPathSegMovetoAbs(float f, float f2);

    SVGPathSegMovetoRel createSVGPathSegMovetoRel(float f, float f2);

    SVGAnimatedNumber getPathLength();

    int getPathSegAtLength(float f);

    SVGPoint getPointAtLength(float f);

    float getTotalLength();
}
