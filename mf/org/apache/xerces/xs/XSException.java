package mf.org.apache.xerces.xs;

public class XSException extends RuntimeException {
    public static final short INDEX_SIZE_ERR = (short) 2;
    public static final short NOT_SUPPORTED_ERR = (short) 1;
    static final long serialVersionUID = 3111893084677917742L;
    public short code;

    public XSException(short code, String message) {
        super(message);
        this.code = code;
    }
}
