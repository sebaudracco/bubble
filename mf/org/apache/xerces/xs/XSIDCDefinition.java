package mf.org.apache.xerces.xs;

public interface XSIDCDefinition extends XSObject {
    public static final short IC_KEY = (short) 1;
    public static final short IC_KEYREF = (short) 2;
    public static final short IC_UNIQUE = (short) 3;

    XSObjectList getAnnotations();

    short getCategory();

    StringList getFieldStrs();

    XSIDCDefinition getRefKey();

    String getSelectorStr();
}
