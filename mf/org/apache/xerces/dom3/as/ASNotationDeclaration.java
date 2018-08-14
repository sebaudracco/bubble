package mf.org.apache.xerces.dom3.as;

public interface ASNotationDeclaration extends ASObject {
    String getPublicId();

    String getSystemId();

    void setPublicId(String str);

    void setSystemId(String str);
}
