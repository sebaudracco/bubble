package mf.org.apache.xerces.dom3.as;

import mf.org.w3c.dom.DOMException;

public interface ASModel extends ASObject {
    void addASModel(ASModel aSModel);

    ASAttributeDeclaration createASAttributeDeclaration(String str, String str2) throws DOMException;

    ASContentModel createASContentModel(int i, int i2, short s) throws DOMASException;

    ASElementDeclaration createASElementDeclaration(String str, String str2) throws DOMException;

    ASEntityDeclaration createASEntityDeclaration(String str) throws DOMException;

    ASNotationDeclaration createASNotationDeclaration(String str, String str2, String str3, String str4) throws DOMException;

    ASObjectList getASModels();

    String getAsHint();

    String getAsLocation();

    ASNamedObjectMap getAttributeDeclarations();

    ASNamedObjectMap getContentModelDeclarations();

    ASNamedObjectMap getElementDeclarations();

    ASNamedObjectMap getEntityDeclarations();

    boolean getIsNamespaceAware();

    ASNamedObjectMap getNotationDeclarations();

    short getUsageLocation();

    void removeAS(ASModel aSModel);

    void setAsHint(String str);

    void setAsLocation(String str);

    boolean validate();
}
