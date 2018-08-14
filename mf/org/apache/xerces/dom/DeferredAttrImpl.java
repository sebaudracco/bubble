package mf.org.apache.xerces.dom;

public final class DeferredAttrImpl extends AttrImpl implements DeferredNode {
    static final long serialVersionUID = 6903232312469148636L;
    protected transient int fNodeIndex;

    DeferredAttrImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        needsSyncData(true);
        needsSyncChildren(true);
    }

    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    protected void synchronizeData() {
        boolean z;
        boolean z2 = true;
        needsSyncData(false);
        DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl) ownerDocument();
        this.name = ownerDocument.getNodeName(this.fNodeIndex);
        int extra = ownerDocument.getNodeExtra(this.fNodeIndex);
        if ((extra & 32) != 0) {
            z = true;
        } else {
            z = false;
        }
        isSpecified(z);
        if ((extra & 512) == 0) {
            z2 = false;
        }
        isIdAttribute(z2);
        this.type = ownerDocument.getTypeInfo(ownerDocument.getLastChild(this.fNodeIndex));
    }

    protected void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren((AttrImpl) this, this.fNodeIndex);
    }
}
