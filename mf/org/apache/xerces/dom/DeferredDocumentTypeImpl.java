package mf.org.apache.xerces.dom;

public class DeferredDocumentTypeImpl extends DocumentTypeImpl implements DeferredNode {
    static final long serialVersionUID = -2172579663227313509L;
    protected transient int fNodeIndex;

    DeferredDocumentTypeImpl(DeferredDocumentImpl ownerDocument, int nodeIndex) {
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
        this.publicID = ownerDocument.getNodeValue(this.fNodeIndex);
        this.systemID = ownerDocument.getNodeURI(this.fNodeIndex);
        this.internalSubset = ownerDocument.getNodeValue(ownerDocument.getNodeExtra(this.fNodeIndex));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void synchronizeChildren() {
        /*
        r10 = this;
        r9 = 0;
        r6 = r10.ownerDocument();
        r3 = r6.getMutationEvents();
        r6 = r10.ownerDocument();
        r6.setMutationEvents(r9);
        r10.needsSyncChildren(r9);
        r4 = r10.ownerDocument;
        r4 = (mf.org.apache.xerces.dom.DeferredDocumentImpl) r4;
        r6 = new mf.org.apache.xerces.dom.NamedNodeMapImpl;
        r6.<init>(r10);
        r10.entities = r6;
        r6 = new mf.org.apache.xerces.dom.NamedNodeMapImpl;
        r6.<init>(r10);
        r10.notations = r6;
        r6 = new mf.org.apache.xerces.dom.NamedNodeMapImpl;
        r6.<init>(r10);
        r10.elements = r6;
        r1 = 0;
        r6 = r10.fNodeIndex;
        r0 = r4.getLastChild(r6);
    L_0x0033:
        r6 = -1;
        if (r0 != r6) goto L_0x0042;
    L_0x0036:
        r6 = r10.ownerDocument();
        r6.setMutationEvents(r3);
        r6 = 1;
        r10.setReadOnly(r6, r9);
        return;
    L_0x0042:
        r2 = r4.getNodeObject(r0);
        r5 = r2.getNodeType();
        switch(r5) {
            case 1: goto L_0x0090;
            case 6: goto L_0x007e;
            case 12: goto L_0x0084;
            case 21: goto L_0x008a;
            default: goto L_0x004d;
        };
    L_0x004d:
        r6 = java.lang.System.out;
        r7 = new java.lang.StringBuilder;
        r8 = "DeferredDocumentTypeImpl#synchronizeInfo: node.getNodeType() = ";
        r7.<init>(r8);
        r8 = r2.getNodeType();
        r7 = r7.append(r8);
        r8 = ", class = ";
        r7 = r7.append(r8);
        r8 = r2.getClass();
        r8 = r8.getName();
        r7 = r7.append(r8);
        r7 = r7.toString();
        r6.println(r7);
    L_0x0079:
        r0 = r4.getPrevSibling(r0);
        goto L_0x0033;
    L_0x007e:
        r6 = r10.entities;
        r6.setNamedItem(r2);
        goto L_0x0079;
    L_0x0084:
        r6 = r10.notations;
        r6.setNamedItem(r2);
        goto L_0x0079;
    L_0x008a:
        r6 = r10.elements;
        r6.setNamedItem(r2);
        goto L_0x0079;
    L_0x0090:
        r6 = r10.getOwnerDocument();
        r6 = (mf.org.apache.xerces.dom.DocumentImpl) r6;
        r6 = r6.allowGrammarAccess;
        if (r6 == 0) goto L_0x004d;
    L_0x009a:
        r10.insertBefore(r2, r1);
        r1 = r2;
        goto L_0x0079;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.dom.DeferredDocumentTypeImpl.synchronizeChildren():void");
    }
}
