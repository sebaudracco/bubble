package mf.org.w3c.dom.xpath;

public class XPathException extends RuntimeException {
    public static final short INVALID_EXPRESSION_ERR = (short) 51;
    public static final short TYPE_ERR = (short) 52;
    public short code;

    public XPathException(short code, String message) {
        super(message);
        this.code = code;
    }
}
