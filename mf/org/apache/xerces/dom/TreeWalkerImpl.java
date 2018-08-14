package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.traversal.NodeFilter;
import mf.org.w3c.dom.traversal.TreeWalker;

public class TreeWalkerImpl implements TreeWalker {
    Node fCurrentNode;
    private boolean fEntityReferenceExpansion = false;
    NodeFilter fNodeFilter;
    Node fRoot;
    private boolean fUseIsSameNode;
    int fWhatToShow = -1;

    public TreeWalkerImpl(Node root, int whatToShow, NodeFilter nodeFilter, boolean entityReferenceExpansion) {
        this.fCurrentNode = root;
        this.fRoot = root;
        this.fUseIsSameNode = useIsSameNode(root);
        this.fWhatToShow = whatToShow;
        this.fNodeFilter = nodeFilter;
        this.fEntityReferenceExpansion = entityReferenceExpansion;
    }

    public Node getRoot() {
        return this.fRoot;
    }

    public int getWhatToShow() {
        return this.fWhatToShow;
    }

    public void setWhatShow(int whatToShow) {
        this.fWhatToShow = whatToShow;
    }

    public NodeFilter getFilter() {
        return this.fNodeFilter;
    }

    public boolean getExpandEntityReferences() {
        return this.fEntityReferenceExpansion;
    }

    public Node getCurrentNode() {
        return this.fCurrentNode;
    }

    public void setCurrentNode(Node node) {
        if (node == null) {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
        this.fCurrentNode = node;
    }

    public Node parentNode() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node node = getParentNode(this.fCurrentNode);
        if (node == null) {
            return node;
        }
        this.fCurrentNode = node;
        return node;
    }

    public Node firstChild() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node node = getFirstChild(this.fCurrentNode);
        if (node == null) {
            return node;
        }
        this.fCurrentNode = node;
        return node;
    }

    public Node lastChild() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node node = getLastChild(this.fCurrentNode);
        if (node == null) {
            return node;
        }
        this.fCurrentNode = node;
        return node;
    }

    public Node previousSibling() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node node = getPreviousSibling(this.fCurrentNode);
        if (node == null) {
            return node;
        }
        this.fCurrentNode = node;
        return node;
    }

    public Node nextSibling() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node node = getNextSibling(this.fCurrentNode);
        if (node == null) {
            return node;
        }
        this.fCurrentNode = node;
        return node;
    }

    public Node previousNode() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node result = getPreviousSibling(this.fCurrentNode);
        if (result == null) {
            result = getParentNode(this.fCurrentNode);
            if (result == null) {
                return null;
            }
            this.fCurrentNode = result;
            return this.fCurrentNode;
        }
        Node lastChild = getLastChild(result);
        Node prev = lastChild;
        while (lastChild != null) {
            prev = lastChild;
            lastChild = getLastChild(prev);
        }
        lastChild = prev;
        if (lastChild != null) {
            this.fCurrentNode = lastChild;
            return this.fCurrentNode;
        } else if (result == null) {
            return null;
        } else {
            this.fCurrentNode = result;
            return this.fCurrentNode;
        }
    }

    public Node nextNode() {
        if (this.fCurrentNode == null) {
            return null;
        }
        Node result = getFirstChild(this.fCurrentNode);
        if (result != null) {
            this.fCurrentNode = result;
            return result;
        }
        result = getNextSibling(this.fCurrentNode);
        if (result != null) {
            this.fCurrentNode = result;
            return result;
        }
        Node parent = getParentNode(this.fCurrentNode);
        while (parent != null) {
            result = getNextSibling(parent);
            if (result != null) {
                this.fCurrentNode = result;
                return result;
            }
            parent = getParentNode(parent);
        }
        return null;
    }

    Node getParentNode(Node node) {
        if (node == null || isSameNode(node, this.fRoot)) {
            return null;
        }
        Node newNode = node.getParentNode();
        if (newNode == null) {
            return null;
        }
        return acceptNode(newNode) != 1 ? getParentNode(newNode) : newNode;
    }

    Node getNextSibling(Node node) {
        return getNextSibling(node, this.fRoot);
    }

    Node getNextSibling(Node node, Node root) {
        if (node == null || isSameNode(node, root)) {
            return null;
        }
        Node newNode = node.getNextSibling();
        if (newNode == null) {
            newNode = node.getParentNode();
            if (newNode == null || isSameNode(newNode, root)) {
                return null;
            }
            return acceptNode(newNode) == 3 ? getNextSibling(newNode, root) : null;
        } else {
            int accept = acceptNode(newNode);
            if (accept == 1) {
                return newNode;
            }
            if (accept != 3) {
                return getNextSibling(newNode, root);
            }
            Node fChild = getFirstChild(newNode);
            return fChild == null ? getNextSibling(newNode, root) : fChild;
        }
    }

    Node getPreviousSibling(Node node) {
        return getPreviousSibling(node, this.fRoot);
    }

    Node getPreviousSibling(Node node, Node root) {
        if (node == null || isSameNode(node, root)) {
            return null;
        }
        Node newNode = node.getPreviousSibling();
        if (newNode == null) {
            newNode = node.getParentNode();
            if (newNode == null || isSameNode(newNode, root)) {
                return null;
            }
            return acceptNode(newNode) == 3 ? getPreviousSibling(newNode, root) : null;
        } else {
            int accept = acceptNode(newNode);
            if (accept == 1) {
                return newNode;
            }
            if (accept != 3) {
                return getPreviousSibling(newNode, root);
            }
            Node fChild = getLastChild(newNode);
            return fChild == null ? getPreviousSibling(newNode, root) : fChild;
        }
    }

    Node getFirstChild(Node node) {
        if (node == null) {
            return null;
        }
        if (!this.fEntityReferenceExpansion && node.getNodeType() == (short) 5) {
            return null;
        }
        Node newNode = node.getFirstChild();
        if (newNode == null) {
            return null;
        }
        int accept = acceptNode(newNode);
        if (accept == 1) {
            return newNode;
        }
        if (accept != 3 || !newNode.hasChildNodes()) {
            return getNextSibling(newNode, node);
        }
        Node fChild = getFirstChild(newNode);
        return fChild == null ? getNextSibling(newNode, node) : fChild;
    }

    Node getLastChild(Node node) {
        if (node == null) {
            return null;
        }
        if (!this.fEntityReferenceExpansion && node.getNodeType() == (short) 5) {
            return null;
        }
        Node newNode = node.getLastChild();
        if (newNode == null) {
            return null;
        }
        int accept = acceptNode(newNode);
        if (accept == 1) {
            return newNode;
        }
        if (accept != 3 || !newNode.hasChildNodes()) {
            return getPreviousSibling(newNode, node);
        }
        Node lChild = getLastChild(newNode);
        return lChild == null ? getPreviousSibling(newNode, node) : lChild;
    }

    short acceptNode(Node node) {
        if (this.fNodeFilter != null) {
            return ((1 << (node.getNodeType() + -1)) & this.fWhatToShow) != 0 ? this.fNodeFilter.acceptNode(node) : (short) 3;
        } else if ((this.fWhatToShow & (1 << (node.getNodeType() - 1))) != 0) {
            return (short) 1;
        } else {
            return (short) 3;
        }
    }

    private boolean useIsSameNode(Node node) {
        if (node instanceof NodeImpl) {
            return false;
        }
        Document doc = node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument();
        if (doc == null || !doc.getImplementation().hasFeature("Core", "3.0")) {
            return false;
        }
        return true;
    }

    private boolean isSameNode(Node m, Node n) {
        if (this.fUseIsSameNode) {
            return m.isSameNode(n);
        }
        return m == n;
    }
}
