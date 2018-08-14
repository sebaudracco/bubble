package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAngle {
    public static final short SVG_ANGLETYPE_DEG = (short) 2;
    public static final short SVG_ANGLETYPE_GRAD = (short) 4;
    public static final short SVG_ANGLETYPE_RAD = (short) 3;
    public static final short SVG_ANGLETYPE_UNKNOWN = (short) 0;
    public static final short SVG_ANGLETYPE_UNSPECIFIED = (short) 1;

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
