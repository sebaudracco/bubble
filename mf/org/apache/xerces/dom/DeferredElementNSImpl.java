package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.NamedNodeMap;

public class DeferredElementNSImpl extends ElementNSImpl implements DeferredNode {
    static final long serialVersionUID = -5001885145370927385L;
    protected transient int fNodeIndex;

    DeferredElementNSImpl(DeferredDocumentImpl ownerDoc, int nodeIndex) {
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
        int index = this.name.indexOf(58);
        if (index < 0) {
            this.localName = this.name;
        } else {
            this.localName = this.name.substring(index + 1);
        }
        this.namespaceURI = ownerDocument.getNodeURI(this.fNodeIndex);
        this.type = (XSTypeDefinition) ownerDocument.getTypeInfo(this.fNodeIndex);
        setupDefaultAttributes();
        int attrIndex = ownerDocument.getNodeExtra(this.fNodeIndex);
        if (attrIndex != -1) {
            NamedNodeMap attrs = getAttributes();
            boolean seenSchemaDefault = false;
            do {
                AttrImpl attr = (AttrImpl) ownerDocument.getNodeObject(attrIndex);
                if (attr.getSpecified() || (!seenSchemaDefault && (attr.getNamespaceURI() == null || attr.getNamespaceURI() == NamespaceContext.XMLNS_URI || attr.getName().indexOf(58) >= 0))) {
                    attrs.setNamedItem(attr);
                } else {
                    seenSchemaDefault = true;
                    attrs.setNamedItemNS(attr);
                }
                attrIndex = ownerDocument.getPrevSibling(attrIndex);
            } while (attrIndex != -1);
        }
        ownerDocument.mutationEvents = orig;
    }

    protected final void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren((ParentNode) this, this.fNodeIndex);
    }
}
