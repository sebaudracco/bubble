package mf.org.w3c.dom.svg;

public interface SVGPaint extends SVGColor {
    public static final short SVG_PAINTTYPE_CURRENTCOLOR = (short) 102;
    public static final short SVG_PAINTTYPE_NONE = (short) 101;
    public static final short SVG_PAINTTYPE_RGBCOLOR = (short) 1;
    public static final short SVG_PAINTTYPE_RGBCOLOR_ICCCOLOR = (short) 2;
    public static final short SVG_PAINTTYPE_UNKNOWN = (short) 0;
    public static final short SVG_PAINTTYPE_URI = (short) 107;
    public static final short SVG_PAINTTYPE_URI_CURRENTCOLOR = (short) 104;
    public static final short SVG_PAINTTYPE_URI_NONE = (short) 103;
    public static final short SVG_PAINTTYPE_URI_RGBCOLOR = (short) 105;
    public static final short SVG_PAINTTYPE_URI_RGBCOLOR_ICCCOLOR = (short) 106;

    short getPaintType();

    String getUri();

    void setPaint(short s, String str, String str2, String str3) throws SVGException;

    void setUri(String str);
}
