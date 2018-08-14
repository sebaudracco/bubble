package mf.org.apache.xerces.dom3.as;

public class DOMASException extends RuntimeException {
    public static final short DUPLICATE_NAME_ERR = (short) 1;
    public static final short NO_AS_AVAILABLE = (short) 3;
    public static final short TYPE_ERR = (short) 2;
    public static final short WRONG_MIME_TYPE_ERR = (short) 4;
    public short code;

    public DOMASException(short code, String message) {
        super(message);
        this.code = code;
    }
}
