package mf.org.apache.xerces.dom;

public class DeferredProcessingInstructionImpl extends ProcessingInstructionImpl implements DeferredNode {
    static final long serialVersionUID = -4643577954293565388L;
    protected transient int fNodeIndex;

    DeferredProcessingInstructionImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null, null);
        this.fNodeIndex = nodeIndex;
        needsSyncData(true);
    }

    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    protected void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl ownerDocument = (DeferredDocumentImpl) ownerDocument();
        this.target = ownerDocument.getNodeName(this.fNodeIndex);
        this.data = ownerDocument.getNodeValueString(this.fNodeIndex);
    }
}
