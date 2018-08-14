package mf.org.apache.html.dom;

import mf.org.apache.xerces.dom.DeepNodeListImpl;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.dom.NodeImpl;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import org.slf4j.Marker;

public class NameNodeListImpl extends DeepNodeListImpl implements NodeList {
    public NameNodeListImpl(NodeImpl rootNode, String tagName) {
        super(rootNode, tagName);
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
                String name = ((ElementImpl) current).getAttribute("name");
                if (name.equals(Marker.ANY_MARKER) || name.equals(this.tagName)) {
                    return current;
                }
            }
        }
        return null;
    }
}
