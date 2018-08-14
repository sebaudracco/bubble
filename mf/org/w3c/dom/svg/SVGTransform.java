package mf.org.w3c.dom.svg;

public interface SVGTransform {
    public static final short SVG_TRANSFORM_MATRIX = (short) 1;
    public static final short SVG_TRANSFORM_ROTATE = (short) 4;
    public static final short SVG_TRANSFORM_SCALE = (short) 3;
    public static final short SVG_TRANSFORM_SKEWX = (short) 5;
    public static final short SVG_TRANSFORM_SKEWY = (short) 6;
    public static final short SVG_TRANSFORM_TRANSLATE = (short) 2;
    public static final short SVG_TRANSFORM_UNKNOWN = (short) 0;

    float getAngle();

    SVGMatrix getMatrix();

    short getType();

    void setMatrix(SVGMatrix sVGMatrix);

    void setRotate(float f, float f2, float f3);

    void setScale(float f, float f2);

    void setSkewX(float f);

    void setSkewY(float f);

    void setTranslate(float f, float f2);
}
