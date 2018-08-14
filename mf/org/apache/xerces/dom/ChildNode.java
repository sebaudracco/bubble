package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.Node;

public abstract class ChildNode extends NodeImpl {
    static final long serialVersionUID = -6112455738802414002L;
    protected ChildNode nextSibling;
    protected ChildNode previousSibling;

    protected ChildNode(CoreDocumentImpl ownerDocument) {
        super(ownerDocument);
    }

    public Node cloneNode(boolean deep) {
        ChildNode newnode = (ChildNode) super.cloneNode(deep);
        newnode.previousSibling = null;
        newnode.nextSibling = null;
        newnode.isFirstChild(false);
        return newnode;
    }

    public Node getParentNode() {
        return isOwned() ? this.ownerNode : null;
    }

    final NodeImpl parentNode() {
        return isOwned() ? this.ownerNode : null;
    }

    public Node getNextSibling() {
        return this.nextSibling;
    }

    public Node getPreviousSibling() {
        return isFirstChild() ? null : this.previousSibling;
    }

    final ChildNode previousSibling() {
        return isFirstChild() ? null : this.previousSibling;
    }
}
