package mf.org.apache.xerces.xs;

public interface XSModelGroupDefinition extends XSObject {
    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    XSModelGroup getModelGroup();
}
