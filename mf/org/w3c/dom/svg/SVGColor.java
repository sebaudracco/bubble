package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.css.CSSValue;
import mf.org.w3c.dom.css.RGBColor;

public interface SVGColor extends CSSValue {
    public static final short SVG_COLORTYPE_CURRENTCOLOR = (short) 3;
    public static final short SVG_COLORTYPE_RGBCOLOR = (short) 1;
    public static final short SVG_COLORTYPE_RGBCOLOR_ICCCOLOR = (short) 2;
    public static final short SVG_COLORTYPE_UNKNOWN = (short) 0;

    short getColorType();

    SVGICCColor getICCColor();

    RGBColor getRGBColor();

    void setColor(short s, String str, String str2) throws SVGException;

    void setRGBColor(String str) throws SVGException;

    void setRGBColorICCColor(String str, String str2) throws SVGException;
}
