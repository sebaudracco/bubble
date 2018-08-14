package mf.org.apache.xerces.dom;

public class DeferredEntityImpl extends EntityImpl implements DeferredNode {
    static final long serialVersionUID = 4760180431078941638L;
    protected transient int fNodeIndex;

    DeferredEntityImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        needsSyncData(true);
        needsSyncChildren(true);
    }

    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    protected void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl ownerDocument = this.ownerDocument;
        this.name = ownerDocument.getNodeName(this.fNodeIndex);
        this.publicId = ownerDocument.getNodeValue(this.fNodeIndex);
        this.systemId = ownerDocument.getNodeURI(this.fNodeIndex);
        int extraDataIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        ownerDocument.getNodeType(extraDataIndex);
        this.notationName = ownerDocument.getNodeName(extraDataIndex);
        this.version = ownerDocument.getNodeValue(extraDataIndex);
        this.encoding = ownerDocument.getNodeURI(extraDataIndex);
        int extraIndex2 = ownerDocument.getNodeExtra(extraDataIndex);
        this.baseURI = ownerDocument.getNodeName(extraIndex2);
        this.inputEncoding = ownerDocument.getNodeValue(extraIndex2);
    }

    protected void synchronizeChildren() {
        needsSyncChildren(false);
        isReadOnly(false);
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren((ParentNode) this, this.fNodeIndex);
        setReadOnly(true, true);
    }
}
