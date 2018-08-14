package mf.org.w3c.dom.svg;

public interface SVGViewSpec extends SVGZoomAndPan, SVGFitToViewBox {
    String getPreserveAspectRatioString();

    SVGTransformList getTransform();

    String getTransformString();

    String getViewBoxString();

    SVGElement getViewTarget();

    String getViewTargetString();
}
