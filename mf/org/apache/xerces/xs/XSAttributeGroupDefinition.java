package mf.org.apache.xerces.xs;

public interface XSAttributeGroupDefinition extends XSObject {
    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    XSObjectList getAttributeUses();

    XSWildcard getAttributeWildcard();
}
