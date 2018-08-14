package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.events.EventTarget;

public interface SVGTextContentElement extends SVGElement, SVGTests, SVGLangSpace, SVGExternalResourcesRequired, SVGStylable, EventTarget {
    public static final short LENGTHADJUST_SPACING = (short) 1;
    public static final short LENGTHADJUST_SPACINGANDGLYPHS = (short) 2;
    public static final short LENGTHADJUST_UNKNOWN = (short) 0;

    int getCharNumAtPosition(SVGPoint sVGPoint);

    float getComputedTextLength();

    SVGPoint getEndPositionOfChar(int i) throws DOMException;

    SVGRect getExtentOfChar(int i) throws DOMException;

    SVGAnimatedEnumeration getLengthAdjust();

    int getNumberOfChars();

    float getRotationOfChar(int i) throws DOMException;

    SVGPoint getStartPositionOfChar(int i) throws DOMException;

    float getSubStringLength(int i, int i2) throws DOMException;

    SVGAnimatedLength getTextLength();

    void selectSubString(int i, int i2) throws DOMException;
}
