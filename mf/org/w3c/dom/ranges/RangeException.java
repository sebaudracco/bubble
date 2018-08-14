package mf.org.w3c.dom.ranges;

public class RangeException extends RuntimeException {
    public static final short BAD_BOUNDARYPOINTS_ERR = (short) 1;
    public static final short INVALID_NODE_TYPE_ERR = (short) 2;
    public short code;

    public RangeException(short code, String message) {
        super(message);
        this.code = code;
    }
}
