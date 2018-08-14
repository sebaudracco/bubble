package mf.org.apache.xerces.xs;

public interface XSFacet extends XSObject {
    Object getActualFacetValue();

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getFacetKind();

    boolean getFixed();

    int getIntFacetValue();

    String getLexicalFacetValue();
}
