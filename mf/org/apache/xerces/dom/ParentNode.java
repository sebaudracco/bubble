package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.UserDataHandler;

public abstract class ParentNode extends ChildNode {
    static final long serialVersionUID = 2815829867152120872L;
    protected transient NodeListCache fNodeListCache = null;
    protected ChildNode firstChild = null;
    protected CoreDocumentImpl ownerDocument;

    class C46171 implements NodeList {
        C46171() {
        }

        public int getLength() {
            return ParentNode.this.nodeListGetLength();
        }

        public Node item(int index) {
            return ParentNode.this.nodeListItem(index);
        }
    }

    class UserDataRecord implements Serializable {
        private static final long serialVersionUID = 3258126977134310455L;
        Object fData;
        UserDataHandler fHandler;

        UserDataRecord(Object data, UserDataHandler handler) {
            this.fData = data;
            this.fHandler = handler;
        }
    }

    protected ParentNode(CoreDocumentImpl ownerDocument) {
        super(ownerDocument);
        this.ownerDocument = ownerDocument;
    }

    public Node cloneNode(boolean deep) {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        ParentNode newnode = (ParentNode) super.cloneNode(deep);
        newnode.ownerDocument = this.ownerDocument;
        newnode.firstChild = null;
        newnode.fNodeListCache = null;
        if (deep) {
            for (ChildNode child = this.firstChild; child != null; child = child.nextSibling) {
                newnode.appendChild(child.cloneNode(true));
            }
        }
        return newnode;
    }

    public Document getOwnerDocument() {
        return this.ownerDocument;
    }

    CoreDocumentImpl ownerDocument() {
        return this.ownerDocument;
    }

    protected void setOwnerDocument(CoreDocumentImpl doc) {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        super.setOwnerDocument(doc);
        this.ownerDocument = doc;
        for (ChildNode child = this.firstChild; child != null; child = child.nextSibling) {
            child.setOwnerDocument(doc);
        }
    }

    public boolean hasChildNodes() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.firstChild != null;
    }

    public NodeList getChildNodes() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this;
    }

    public Node getFirstChild() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.firstChild;
    }

    public Node getLastChild() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return lastChild();
    }

    final ChildNode lastChild() {
        return this.firstChild != null ? this.firstChild.previousSibling : null;
    }

    final void lastChild(ChildNode node) {
        if (this.firstChild != null) {
            this.firstChild.previousSibling = node;
        }
    }

    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        return internalInsertBefore(newChild, refChild, false);
    }

    Node internalInsertBefore(Node newChild, Node refChild, boolean replace) throws DOMException {
        boolean errorChecking = this.ownerDocument.errorChecking;
        if (newChild.getNodeType() == (short) 11) {
            if (errorChecking) {
                Node kid = newChild.getFirstChild();
                while (kid != null) {
                    if (this.ownerDocument.isKidOK(this, kid)) {
                        kid = kid.getNextSibling();
                    } else {
                        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
                    }
                }
            }
            while (newChild.hasChildNodes()) {
                insertBefore(newChild.getFirstChild(), refChild);
            }
        } else if (newChild == refChild) {
            refChild = refChild.getNextSibling();
            removeChild(newChild);
            insertBefore(newChild, refChild);
        } else {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if (errorChecking) {
                if (isReadOnly()) {
                    throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                } else if (newChild.getOwnerDocument() != this.ownerDocument && newChild != this.ownerDocument) {
                    throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                } else if (!this.ownerDocument.isKidOK(this, newChild)) {
                    throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
                } else if (refChild == null || refChild.getParentNode() == this) {
                    boolean treeSafe = true;
                    Node a = this;
                    while (treeSafe && a != null) {
                        treeSafe = newChild != a;
                        a = a.parentNode();
                    }
                    if (!treeSafe) {
                        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
                    }
                } else {
                    throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
                }
            }
            this.ownerDocument.insertingNode(this, replace);
            ChildNode newInternal = (ChildNode) newChild;
            Node oldparent = newInternal.parentNode();
            if (oldparent != null) {
                oldparent.removeChild(newInternal);
            }
            ChildNode refInternal = (ChildNode) refChild;
            newInternal.ownerNode = this;
            newInternal.isOwned(true);
            if (this.firstChild == null) {
                this.firstChild = newInternal;
                newInternal.isFirstChild(true);
                newInternal.previousSibling = newInternal;
            } else if (refInternal == null) {
                ChildNode lastChild = this.firstChild.previousSibling;
                lastChild.nextSibling = newInternal;
                newInternal.previousSibling = lastChild;
                this.firstChild.previousSibling = newInternal;
            } else if (refChild == this.firstChild) {
                this.firstChild.isFirstChild(false);
                newInternal.nextSibling = this.firstChild;
                newInternal.previousSibling = this.firstChild.previousSibling;
                this.firstChild.previousSibling = newInternal;
                this.firstChild = newInternal;
                newInternal.isFirstChild(true);
            } else {
                ChildNode prev = refInternal.previousSibling;
                newInternal.nextSibling = refInternal;
                prev.nextSibling = newInternal;
                refInternal.previousSibling = newInternal;
                newInternal.previousSibling = prev;
            }
            changed();
            if (this.fNodeListCache != null) {
                if (this.fNodeListCache.fLength != -1) {
                    NodeListCache nodeListCache = this.fNodeListCache;
                    nodeListCache.fLength++;
                }
                if (this.fNodeListCache.fChildIndex != -1) {
                    if (this.fNodeListCache.fChild == refInternal) {
                        this.fNodeListCache.fChild = newInternal;
                    } else {
                        this.fNodeListCache.fChildIndex = -1;
                    }
                }
            }
            this.ownerDocument.insertedNode(this, newInternal, replace);
            checkNormalizationAfterInsert(newInternal);
        }
        return newChild;
    }

    public Node removeChild(Node oldChild) throws DOMException {
        return internalRemoveChild(oldChild, false);
    }

    Node internalRemoveChild(Node oldChild, boolean replace) throws DOMException {
        CoreDocumentImpl ownerDocument = ownerDocument();
        if (ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (!(oldChild == null || oldChild.getParentNode() == this)) {
                throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            }
        }
        ChildNode oldInternal = (ChildNode) oldChild;
        ownerDocument.removingNode(this, oldInternal, replace);
        if (this.fNodeListCache != null) {
            NodeListCache nodeListCache;
            if (this.fNodeListCache.fLength != -1) {
                nodeListCache = this.fNodeListCache;
                nodeListCache.fLength--;
            }
            if (this.fNodeListCache.fChildIndex != -1) {
                if (this.fNodeListCache.fChild == oldInternal) {
                    nodeListCache = this.fNodeListCache;
                    nodeListCache.fChildIndex--;
                    this.fNodeListCache.fChild = oldInternal.previousSibling();
                } else {
                    this.fNodeListCache.fChildIndex = -1;
                }
            }
        }
        if (oldInternal == this.firstChild) {
            oldInternal.isFirstChild(false);
            this.firstChild = oldInternal.nextSibling;
            if (this.firstChild != null) {
                this.firstChild.isFirstChild(true);
                this.firstChild.previousSibling = oldInternal.previousSibling;
            }
        } else {
            ChildNode prev = oldInternal.previousSibling;
            ChildNode next = oldInternal.nextSibling;
            prev.nextSibling = next;
            if (next == null) {
                this.firstChild.previousSibling = prev;
            } else {
                next.previousSibling = prev;
            }
        }
        ChildNode oldPreviousSibling = oldInternal.previousSibling();
        oldInternal.ownerNode = ownerDocument;
        oldInternal.isOwned(false);
        oldInternal.nextSibling = null;
        oldInternal.previousSibling = null;
        changed();
        ownerDocument.removedNode(this, replace);
        checkNormalizationAfterRemove(oldPreviousSibling);
        return oldInternal;
    }

    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        this.ownerDocument.replacingNode(this);
        internalInsertBefore(newChild, oldChild, true);
        if (newChild != oldChild) {
            internalRemoveChild(oldChild, true);
        }
        this.ownerDocument.replacedNode(this);
        return oldChild;
    }

    public String getTextContent() throws DOMException {
        Node child = getFirstChild();
        if (child == null) {
            return "";
        }
        if (child.getNextSibling() == null) {
            return hasTextContent(child) ? ((NodeImpl) child).getTextContent() : "";
        } else {
            StringBuffer buf = new StringBuffer();
            getTextContent(buf);
            return buf.toString();
        }
    }

    void getTextContent(StringBuffer buf) throws DOMException {
        for (Node child = getFirstChild(); child != null; child = child.getNextSibling()) {
            if (hasTextContent(child)) {
                ((NodeImpl) child).getTextContent(buf);
            }
        }
    }

    final boolean hasTextContent(Node child) {
        return (child.getNodeType() == (short) 8 || child.getNodeType() == (short) 7 || (child.getNodeType() == (short) 3 && ((TextImpl) child).isIgnorableWhitespace())) ? false : true;
    }

    public void setTextContent(String textContent) throws DOMException {
        while (true) {
            Node child = getFirstChild();
            if (child == null) {
                break;
            }
            removeChild(child);
        }
        if (textContent != null && textContent.length() != 0) {
            appendChild(ownerDocument().createTextNode(textContent));
        }
    }

    private int nodeListGetLength() {
        if (this.fNodeListCache == null) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if (this.firstChild == null) {
                return 0;
            }
            if (this.firstChild == lastChild()) {
                return 1;
            }
            this.fNodeListCache = this.ownerDocument.getNodeListCache(this);
        }
        if (this.fNodeListCache.fLength == -1) {
            ChildNode n;
            int l;
            if (this.fNodeListCache.fChildIndex == -1 || this.fNodeListCache.fChild == null) {
                n = this.firstChild;
                l = 0;
            } else {
                l = this.fNodeListCache.fChildIndex;
                n = this.fNodeListCache.fChild;
            }
            while (n != null) {
                l++;
                n = n.nextSibling;
            }
            this.fNodeListCache.fLength = l;
        }
        return this.fNodeListCache.fLength;
    }

    public int getLength() {
        return nodeListGetLength();
    }

    private Node nodeListItem(int index) {
        if (this.fNodeListCache == null) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if (this.firstChild != lastChild()) {
                this.fNodeListCache = this.ownerDocument.getNodeListCache(this);
            } else if (index == 0) {
                return this.firstChild;
            } else {
                return null;
            }
        }
        int i = this.fNodeListCache.fChildIndex;
        ChildNode n = this.fNodeListCache.fChild;
        boolean firstAccess = true;
        if (i != -1 && n != null) {
            firstAccess = false;
            if (i < index) {
                while (i < index && n != null) {
                    i++;
                    n = n.nextSibling;
                }
            } else if (i > index) {
                while (i > index && n != null) {
                    i--;
                    n = n.previousSibling();
                }
            }
        } else if (index < 0) {
            return null;
        } else {
            n = this.firstChild;
            i = 0;
            while (i < index && n != null) {
                n = n.nextSibling;
                i++;
            }
        }
        if (firstAccess || !(n == this.firstChild || n == lastChild())) {
            this.fNodeListCache.fChildIndex = i;
            this.fNodeListCache.fChild = n;
        } else {
            this.fNodeListCache.fChildIndex = -1;
            this.fNodeListCache.fChild = null;
            this.ownerDocument.freeNodeListCache(this.fNodeListCache);
        }
        return n;
    }

    public Node item(int index) {
        return nodeListItem(index);
    }

    protected final NodeList getChildNodesUnoptimized() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return new C46171();
    }

    public void normalize() {
        if (!isNormalized()) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            for (ChildNode kid = this.firstChild; kid != null; kid = kid.nextSibling) {
                kid.normalize();
            }
            isNormalized(true);
        }
    }

    public boolean isEqualNode(Node arg) {
        if (!super.isEqualNode(arg)) {
            return false;
        }
        Node child1 = getFirstChild();
        Node child2 = arg.getFirstChild();
        while (child1 != null && child2 != null) {
            if (!child1.isEqualNode(child2)) {
                return false;
            }
            child1 = child1.getNextSibling();
            child2 = child2.getNextSibling();
        }
        if (child1 == child2) {
            return true;
        }
        return false;
    }

    public void setReadOnly(boolean readOnly, boolean deep) {
        super.setReadOnly(readOnly, deep);
        if (deep) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            for (ChildNode mykid = this.firstChild; mykid != null; mykid = mykid.nextSibling) {
                if (mykid.getNodeType() != (short) 5) {
                    mykid.setReadOnly(readOnly, true);
                }
            }
        }
    }

    protected void synchronizeChildren() {
        needsSyncChildren(false);
    }

    void checkNormalizationAfterInsert(ChildNode insertedChild) {
        if (insertedChild.getNodeType() == (short) 3) {
            ChildNode prev = insertedChild.previousSibling();
            ChildNode next = insertedChild.nextSibling;
            if ((prev != null && prev.getNodeType() == (short) 3) || (next != null && next.getNodeType() == (short) 3)) {
                isNormalized(false);
            }
        } else if (!insertedChild.isNormalized()) {
            isNormalized(false);
        }
    }

    void checkNormalizationAfterRemove(ChildNode previousSibling) {
        if (previousSibling != null && previousSibling.getNodeType() == (short) 3) {
            ChildNode next = previousSibling.nextSibling;
            if (next != null && next.getNodeType() == (short) 3) {
                isNormalized(false);
            }
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        needsSyncChildren(false);
    }
}
