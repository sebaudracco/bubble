package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;

public interface CSSPrimitiveValue extends CSSValue {
    public static final short CSS_ATTR = (short) 22;
    public static final short CSS_CM = (short) 6;
    public static final short CSS_COUNTER = (short) 23;
    public static final short CSS_DEG = (short) 11;
    public static final short CSS_DIMENSION = (short) 18;
    public static final short CSS_EMS = (short) 3;
    public static final short CSS_EXS = (short) 4;
    public static final short CSS_GRAD = (short) 13;
    public static final short CSS_HZ = (short) 16;
    public static final short CSS_IDENT = (short) 21;
    public static final short CSS_IN = (short) 8;
    public static final short CSS_KHZ = (short) 17;
    public static final short CSS_MM = (short) 7;
    public static final short CSS_MS = (short) 14;
    public static final short CSS_NUMBER = (short) 1;
    public static final short CSS_PC = (short) 10;
    public static final short CSS_PERCENTAGE = (short) 2;
    public static final short CSS_PT = (short) 9;
    public static final short CSS_PX = (short) 5;
    public static final short CSS_RAD = (short) 12;
    public static final short CSS_RECT = (short) 24;
    public static final short CSS_RGBCOLOR = (short) 25;
    public static final short CSS_S = (short) 15;
    public static final short CSS_STRING = (short) 19;
    public static final short CSS_UNKNOWN = (short) 0;
    public static final short CSS_URI = (short) 20;

    Counter getCounterValue() throws DOMException;

    float getFloatValue(short s) throws DOMException;

    short getPrimitiveType();

    RGBColor getRGBColorValue() throws DOMException;

    Rect getRectValue() throws DOMException;

    String getStringValue() throws DOMException;

    void setFloatValue(short s, float f) throws DOMException;

    void setStringValue(short s, String str) throws DOMException;
}
