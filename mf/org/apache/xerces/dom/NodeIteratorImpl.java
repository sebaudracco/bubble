package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.traversal.NodeFilter;
import mf.org.w3c.dom.traversal.NodeIterator;

public class NodeIteratorImpl implements NodeIterator {
    private Node fCurrentNode;
    private boolean fDetach = false;
    private DocumentImpl fDocument;
    private boolean fEntityReferenceExpansion;
    private boolean fForward = true;
    private NodeFilter fNodeFilter;
    private Node fRoot;
    private int fWhatToShow = -1;

    public NodeIteratorImpl(DocumentImpl document, Node root, int whatToShow, NodeFilter nodeFilter, boolean entityReferenceExpansion) {
        this.fDocument = document;
        this.fRoot = root;
        this.fCurrentNode = null;
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

    public NodeFilter getFilter() {
        return this.fNodeFilter;
    }

    public boolean getExpandEntityReferences() {
        return this.fEntityReferenceExpansion;
    }

    public Node nextNode() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else if (this.fRoot == null) {
            return null;
        } else {
            Node nextNode = this.fCurrentNode;
            boolean accepted = false;
            while (!accepted) {
                if (!this.fForward && nextNode != null) {
                    nextNode = this.fCurrentNode;
                } else if (this.fEntityReferenceExpansion || nextNode == null || nextNode.getNodeType() != (short) 5) {
                    nextNode = nextNode(nextNode, true);
                } else {
                    nextNode = nextNode(nextNode, false);
                }
                this.fForward = true;
                if (nextNode == null) {
                    return null;
                }
                accepted = acceptNode(nextNode);
                if (accepted) {
                    this.fCurrentNode = nextNode;
                    return this.fCurrentNode;
                }
            }
            return null;
        }
    }

    public Node previousNode() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else if (this.fRoot == null || this.fCurrentNode == null) {
            return null;
        } else {
            Node previousNode = this.fCurrentNode;
            boolean accepted = false;
            while (!accepted) {
                if (!this.fForward || previousNode == null) {
                    previousNode = previousNode(previousNode);
                } else {
                    previousNode = this.fCurrentNode;
                }
                this.fForward = false;
                if (previousNode == null) {
                    return null;
                }
                accepted = acceptNode(previousNode);
                if (accepted) {
                    this.fCurrentNode = previousNode;
                    return this.fCurrentNode;
                }
            }
            return null;
        }
    }

    boolean acceptNode(Node node) {
        if (this.fNodeFilter == null) {
            if ((this.fWhatToShow & (1 << (node.getNodeType() - 1))) != 0) {
                return true;
            }
            return false;
        } else if ((this.fWhatToShow & (1 << (node.getNodeType() - 1))) == 0 || this.fNodeFilter.acceptNode(node) != (short) 1) {
            return false;
        } else {
            return true;
        }
    }

    Node matchNodeOrParent(Node node) {
        if (this.fCurrentNode == null) {
            return null;
        }
        for (Node n = this.fCurrentNode; n != this.fRoot; n = n.getParentNode()) {
            if (node == n) {
                return n;
            }
        }
        return null;
    }

    Node nextNode(Node node, boolean visitChildren) {
        if (node == null) {
            return this.fRoot;
        }
        if (visitChildren && node.hasChildNodes()) {
            return node.getFirstChild();
        }
        if (node == this.fRoot) {
            return null;
        }
        Node result = node.getNextSibling();
        if (result != null) {
            return result;
        }
        Node parent = node.getParentNode();
        while (parent != null && parent != this.fRoot) {
            result = parent.getNextSibling();
            if (result != null) {
                return result;
            }
            parent = parent.getParentNode();
        }
        return null;
    }

    Node previousNode(Node node) {
        if (node == this.fRoot) {
            return null;
        }
        Node result = node.getPreviousSibling();
        if (result == null) {
            return node.getParentNode();
        }
        if (!result.hasChildNodes()) {
            return result;
        }
        if (!this.fEntityReferenceExpansion && result != null && result.getNodeType() == (short) 5) {
            return result;
        }
        while (result.hasChildNodes()) {
            result = result.getLastChild();
        }
        return result;
    }

    public void removeNode(Node node) {
        if (node != null) {
            Node deleted = matchNodeOrParent(node);
            if (deleted == null) {
                return;
            }
            if (this.fForward) {
                this.fCurrentNode = previousNode(deleted);
                return;
            }
            Node next = nextNode(deleted, false);
            if (next != null) {
                this.fCurrentNode = next;
                return;
            }
            this.fCurrentNode = previousNode(deleted);
            this.fForward = true;
        }
    }

    public void detach() {
        this.fDetach = true;
        this.fDocument.removeNodeIterator(this);
    }
}
