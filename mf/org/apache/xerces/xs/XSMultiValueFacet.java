package mf.org.apache.xerces.xs;

import mf.org.apache.xerces.xs.datatypes.ObjectList;

public interface XSMultiValueFacet extends XSObject {
    XSObjectList getAnnotations();

    ObjectList getEnumerationValues();

    short getFacetKind();

    StringList getLexicalFacetValues();
}
