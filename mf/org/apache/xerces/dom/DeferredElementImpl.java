package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.NamedNodeMap;

public class DeferredElementImpl extends ElementImpl implements DeferredNode {
    static final long serialVersionUID = -7670981133940934842L;
    protected transient int fNodeIndex;

    DeferredElementImpl(DeferredDocumentImpl ownerDoc, int nodeIndex) {
        super(ownerDoc, null);
        this.fNodeIndex = nodeIndex;
        needsSyncChildren(true);
    }

    public final int getNodeIndex() {
        return this.fNodeIndex;
    }

    protected final void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl ownerDocument = this.ownerDocument;
        boolean orig = ownerDocument.mutationEvents;
        ownerDocument.mutationEvents = false;
        this.name = ownerDocument.getNodeName(this.fNodeIndex);
        setupDefaultAttributes();
        int index = ownerDocument.getNodeExtra(this.fNodeIndex);
        if (index != -1) {
            NamedNodeMap attrs = getAttributes();
            do {
                attrs.setNamedItem((NodeImpl) ownerDocument.getNodeObject(index));
                index = ownerDocument.getPrevSibling(index);
            } while (index != -1);
        }
        ownerDocument.mutationEvents = orig;
    }

    protected final void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren((ParentNode) this, this.fNodeIndex);
    }
}
