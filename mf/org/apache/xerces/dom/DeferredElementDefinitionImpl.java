package mf.org.apache.xerces.dom;

public class DeferredElementDefinitionImpl extends ElementDefinitionImpl implements DeferredNode {
    static final long serialVersionUID = 6703238199538041591L;
    protected transient int fNodeIndex;

    DeferredElementDefinitionImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
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
        this.name = this.ownerDocument.getNodeName(this.fNodeIndex);
    }

    protected void synchronizeChildren() {
        boolean orig = this.ownerDocument.getMutationEvents();
        this.ownerDocument.setMutationEvents(false);
        needsSyncChildren(false);
        DeferredDocumentImpl ownerDocument = this.ownerDocument;
        this.attributes = new NamedNodeMapImpl(ownerDocument);
        for (int nodeIndex = ownerDocument.getLastChild(this.fNodeIndex); nodeIndex != -1; nodeIndex = ownerDocument.getPrevSibling(nodeIndex)) {
            this.attributes.setNamedItem(ownerDocument.getNodeObject(nodeIndex));
        }
        ownerDocument.setMutationEvents(orig);
    }
}
