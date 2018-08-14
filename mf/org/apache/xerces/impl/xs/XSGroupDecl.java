package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSModelGroup;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;

public class XSGroupDecl implements XSModelGroupDefinition {
    public XSObjectList fAnnotations = null;
    public XSModelGroupImpl fModelGroup = null;
    public String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    public String fTargetNamespace = null;

    public short getType() {
        return (short) 6;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSModelGroup getModelGroup() {
        return this.fModelGroup;
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
