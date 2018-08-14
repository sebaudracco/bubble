package mf.org.w3c.dom.svg;

public interface SVGTextPathElement extends SVGTextContentElement, SVGURIReference {
    public static final short TEXTPATH_METHODTYPE_ALIGN = (short) 1;
    public static final short TEXTPATH_METHODTYPE_STRETCH = (short) 2;
    public static final short TEXTPATH_METHODTYPE_UNKNOWN = (short) 0;
    public static final short TEXTPATH_SPACINGTYPE_AUTO = (short) 1;
    public static final short TEXTPATH_SPACINGTYPE_EXACT = (short) 2;
    public static final short TEXTPATH_SPACINGTYPE_UNKNOWN = (short) 0;

    SVGAnimatedEnumeration getMethod();

    SVGAnimatedEnumeration getSpacing();

    SVGAnimatedLength getStartOffset();
}
