package mf.org.apache.xerces.dom;

public final class DeferredAttrNSImpl extends AttrNSImpl implements DeferredNode {
    static final long serialVersionUID = 6074924934945957154L;
    protected transient int fNodeIndex;

    DeferredAttrNSImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
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
        int index = this.name.indexOf(58);
        if (index < 0) {
            this.localName = this.name;
        } else {
            this.localName = this.name.substring(index + 1);
        }
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
        this.namespaceURI = ownerDocument.getNodeURI(this.fNodeIndex);
        this.type = ownerDocument.getTypeInfo(ownerDocument.getLastChild(this.fNodeIndex));
    }

    protected void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren((AttrImpl) this, this.fNodeIndex);
    }
}
