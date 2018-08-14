package mf.org.apache.xerces.dom3.as;

public interface ASAttributeDeclaration extends ASObject {
    public static final short VALUE_DEFAULT = (short) 1;
    public static final short VALUE_FIXED = (short) 2;
    public static final short VALUE_NONE = (short) 0;

    ASDataType getDataType();

    String getDataValue();

    short getDefaultType();

    String getEnumAttr();

    ASObjectList getOwnerElements();

    void setDataType(ASDataType aSDataType);

    void setDataValue(String str);

    void setDefaultType(short s);

    void setEnumAttr(String str);

    void setOwnerElements(ASObjectList aSObjectList);
}
