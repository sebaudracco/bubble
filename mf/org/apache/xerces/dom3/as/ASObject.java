package mf.org.apache.xerces.dom3.as;

public interface ASObject {
    public static final short AS_ATTRIBUTE_DECLARATION = (short) 2;
    public static final short AS_CONTENTMODEL = (short) 5;
    public static final short AS_ELEMENT_DECLARATION = (short) 1;
    public static final short AS_ENTITY_DECLARATION = (short) 4;
    public static final short AS_MODEL = (short) 6;
    public static final short AS_NOTATION_DECLARATION = (short) 3;

    ASObject cloneASObject(boolean z);

    short getAsNodeType();

    String getLocalName();

    String getNamespaceURI();

    String getNodeName();

    ASModel getOwnerASModel();

    String getPrefix();

    void setLocalName(String str);

    void setNamespaceURI(String str);

    void setNodeName(String str);

    void setOwnerASModel(ASModel aSModel);

    void setPrefix(String str);
}
