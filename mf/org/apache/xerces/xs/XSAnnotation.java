package mf.org.apache.xerces.xs;

public interface XSAnnotation extends XSObject {
    public static final short SAX_CONTENTHANDLER = (short) 2;
    public static final short W3C_DOM_DOCUMENT = (short) 3;
    public static final short W3C_DOM_ELEMENT = (short) 1;

    String getAnnotationString();

    boolean writeAnnotation(Object obj, short s);
}
