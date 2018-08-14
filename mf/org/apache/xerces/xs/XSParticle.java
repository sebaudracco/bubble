package mf.org.apache.xerces.xs;

public interface XSParticle extends XSObject {
    XSObjectList getAnnotations();

    int getMaxOccurs();

    boolean getMaxOccursUnbounded();

    int getMinOccurs();

    XSTerm getTerm();
}
