package mf.org.apache.xerces.dom3.as;

public interface ASElementDeclaration extends ASObject {
    public static final short ANY_CONTENTTYPE = (short) 2;
    public static final short ELEMENTS_CONTENTTYPE = (short) 4;
    public static final short EMPTY_CONTENTTYPE = (short) 1;
    public static final short MIXED_CONTENTTYPE = (short) 3;

    void addASAttributeDecl(ASAttributeDeclaration aSAttributeDeclaration);

    ASNamedObjectMap getASAttributeDecls();

    ASContentModel getAsCM();

    short getContentType();

    ASDataType getElementType();

    boolean getIsPCDataOnly();

    boolean getStrictMixedContent();

    String getSystemId();

    ASAttributeDeclaration removeASAttributeDecl(ASAttributeDeclaration aSAttributeDeclaration);

    void setASAttributeDecls(ASNamedObjectMap aSNamedObjectMap);

    void setAsCM(ASContentModel aSContentModel);

    void setContentType(short s);

    void setElementType(ASDataType aSDataType);

    void setIsPCDataOnly(boolean z);

    void setStrictMixedContent(boolean z);

    void setSystemId(String str);
}
