package mf.org.w3c.dom.svg;

public abstract class SVGException extends RuntimeException {
    public static final short SVG_INVALID_VALUE_ERR = (short) 1;
    public static final short SVG_MATRIX_NOT_INVERTABLE = (short) 2;
    public static final short SVG_WRONG_TYPE_ERR = (short) 0;
    public short code;

    public SVGException(short code, String message) {
        super(message);
        this.code = code;
    }
}
