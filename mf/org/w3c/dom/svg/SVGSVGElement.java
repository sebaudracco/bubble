package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.css.DocumentCSS;
import mf.org.w3c.dom.css.ViewCSS;
import mf.org.w3c.dom.events.DocumentEvent;
import mf.org.w3c.dom.events.EventTarget;

public interface SVGSVGElement extends SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, SVGLocatable, SVGFitToViewBox, SVGZoomAndPan, EventTarget, DocumentEvent, ViewCSS, DocumentCSS {
    boolean animationsPaused();

    boolean checkEnclosure(SVGElement sVGElement, SVGRect sVGRect);

    boolean checkIntersection(SVGElement sVGElement, SVGRect sVGRect);

    SVGAngle createSVGAngle();

    SVGLength createSVGLength();

    SVGMatrix createSVGMatrix();

    SVGNumber createSVGNumber();

    SVGPoint createSVGPoint();

    SVGRect createSVGRect();

    SVGTransform createSVGTransform();

    SVGTransform createSVGTransformFromMatrix(SVGMatrix sVGMatrix);

    void deselectAll();

    void forceRedraw();

    String getContentScriptType();

    String getContentStyleType();

    float getCurrentScale();

    float getCurrentTime();

    SVGPoint getCurrentTranslate();

    SVGViewSpec getCurrentView();

    Element getElementById(String str);

    NodeList getEnclosureList(SVGRect sVGRect, SVGElement sVGElement);

    SVGAnimatedLength getHeight();

    NodeList getIntersectionList(SVGRect sVGRect, SVGElement sVGElement);

    float getPixelUnitToMillimeterX();

    float getPixelUnitToMillimeterY();

    float getScreenPixelToMillimeterX();

    float getScreenPixelToMillimeterY();

    boolean getUseCurrentView();

    SVGRect getViewport();

    SVGAnimatedLength getWidth();

    SVGAnimatedLength getX();

    SVGAnimatedLength getY();

    void pauseAnimations();

    void setContentScriptType(String str) throws DOMException;

    void setContentStyleType(String str) throws DOMException;

    void setCurrentScale(float f) throws DOMException;

    void setCurrentTime(float f);

    void setUseCurrentView(boolean z) throws DOMException;

    int suspendRedraw(int i);

    void unpauseAnimations();

    void unsuspendRedraw(int i) throws DOMException;

    void unsuspendRedrawAll();
}
