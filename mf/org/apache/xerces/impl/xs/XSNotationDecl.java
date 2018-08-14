package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSObjectList;

public class XSNotationDecl implements XSNotationDeclaration {
    public XSObjectList fAnnotations = null;
    public String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    public String fPublicId = null;
    public String fSystemId = null;
    public String fTargetNamespace = null;

    public short getType() {
        return (short) 11;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public XSAnnotation getAnnotation() {
        return this.fAnnotations != null ? (XSAnnotation) this.fAnnotations.item(0) : null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }
}
