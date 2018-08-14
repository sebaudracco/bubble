package mf.org.apache.xerces.dom;

public class DeferredCDATASectionImpl extends CDATASectionImpl implements DeferredNode {
    static final long serialVersionUID = 1983580632355645726L;
    protected transient int fNodeIndex;

    DeferredCDATASectionImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
        super(ownerDocument, null);
        this.fNodeIndex = nodeIndex;
        needsSyncData(true);
    }

    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    protected void synchronizeData() {
        needsSyncData(false);
        this.data = ((DeferredDocumentImpl) ownerDocument()).getNodeValueString(this.fNodeIndex);
    }
}
