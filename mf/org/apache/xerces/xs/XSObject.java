package mf.org.apache.xerces.xs;

public interface XSObject {
    String getName();

    String getNamespace();

    XSNamespaceItem getNamespaceItem();

    short getType();
}
