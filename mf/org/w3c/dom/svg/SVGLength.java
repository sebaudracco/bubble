package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGLength {
    public static final short SVG_LENGTHTYPE_CM = (short) 6;
    public static final short SVG_LENGTHTYPE_EMS = (short) 3;
    public static final short SVG_LENGTHTYPE_EXS = (short) 4;
    public static final short SVG_LENGTHTYPE_IN = (short) 8;
    public static final short SVG_LENGTHTYPE_MM = (short) 7;
    public static final short SVG_LENGTHTYPE_NUMBER = (short) 1;
    public static final short SVG_LENGTHTYPE_PC = (short) 10;
    public static final short SVG_LENGTHTYPE_PERCENTAGE = (short) 2;
    public static final short SVG_LENGTHTYPE_PT = (short) 9;
    public static final short SVG_LENGTHTYPE_PX = (short) 5;
    public static final short SVG_LENGTHTYPE_UNKNOWN = (short) 0;

    void convertToSpecifiedUnits(short s);

    short getUnitType();

    float getValue();

    String getValueAsString();

    float getValueInSpecifiedUnits();

    void newValueSpecifiedUnits(short s, float f);

    void setValue(float f) throws DOMException;

    void setValueAsString(String str) throws DOMException;

    void setValueInSpecifiedUnits(float f) throws DOMException;
}
