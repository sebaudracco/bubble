package mf.org.apache.xerces.dom;

import java.util.ArrayList;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import org.slf4j.Marker;

public class DeepNodeListImpl implements NodeList {
    protected int changes;
    protected boolean enableNS;
    protected ArrayList nodes;
    protected String nsName;
    protected NodeImpl rootNode;
    protected String tagName;

    public DeepNodeListImpl(NodeImpl rootNode, String tagName) {
        this.changes = 0;
        this.enableNS = false;
        this.rootNode = rootNode;
        this.tagName = tagName;
        this.nodes = new ArrayList();
    }

    public DeepNodeListImpl(NodeImpl rootNode, String nsName, String tagName) {
        this(rootNode, tagName);
        if (nsName == null || nsName.length() == 0) {
            nsName = null;
        }
        this.nsName = nsName;
        this.enableNS = true;
    }

    public int getLength() {
        item(Integer.MAX_VALUE);
        return this.nodes.size();
    }

    public Node item(int index) {
        if (this.rootNode.changes() != this.changes) {
            this.nodes = new ArrayList();
            this.changes = this.rootNode.changes();
        }
        int currentSize = this.nodes.size();
        if (index < currentSize) {
            return (Node) this.nodes.get(index);
        }
        Node thisNode;
        if (currentSize == 0) {
            thisNode = this.rootNode;
        } else {
            NodeImpl thisNode2 = (NodeImpl) this.nodes.get(currentSize - 1);
        }
        while (thisNode != null && index >= this.nodes.size()) {
            thisNode = nextMatchingElementAfter(thisNode);
            if (thisNode != null) {
                this.nodes.add(thisNode);
            }
        }
        return thisNode;
    }

    protected Node nextMatchingElementAfter(Node current) {
        while (current != null) {
            if (current.hasChildNodes()) {
                current = current.getFirstChild();
            } else {
                Node next;
                if (current != this.rootNode) {
                    next = current.getNextSibling();
                    if (next != null) {
                        current = next;
                    }
                }
                next = null;
                while (current != this.rootNode) {
                    next = current.getNextSibling();
                    if (next != null) {
                        break;
                    }
                    current = current.getParentNode();
                }
                current = next;
            }
            if (!(current == this.rootNode || current == null || current.getNodeType() != (short) 1)) {
                if (this.enableNS) {
                    ElementImpl el;
                    if (!this.tagName.equals(Marker.ANY_MARKER)) {
                        el = (ElementImpl) current;
                        if (el.getLocalName() != null && el.getLocalName().equals(this.tagName)) {
                            if (this.nsName != null && this.nsName.equals(Marker.ANY_MARKER)) {
                                return current;
                            }
                            if ((this.nsName == null && el.getNamespaceURI() == null) || (this.nsName != null && this.nsName.equals(el.getNamespaceURI()))) {
                                return current;
                            }
                        }
                    } else if (this.nsName != null && this.nsName.equals(Marker.ANY_MARKER)) {
                        return current;
                    } else {
                        el = (ElementImpl) current;
                        if ((this.nsName == null && el.getNamespaceURI() == null) || (this.nsName != null && this.nsName.equals(el.getNamespaceURI()))) {
                            return current;
                        }
                    }
                } else if (this.tagName.equals(Marker.ANY_MARKER) || ((ElementImpl) current).getTagName().equals(this.tagName)) {
                    return current;
                }
            }
        }
        return null;
    }
}
