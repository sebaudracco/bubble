package mf.org.apache.xerces.xs;

public interface XSNotationDeclaration extends XSObject {
    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    String getPublicId();

    String getSystemId();
}
