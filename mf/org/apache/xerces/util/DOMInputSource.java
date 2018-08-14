package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.w3c.dom.Node;

public final class DOMInputSource extends XMLInputSource {
    private Node fNode;

    public DOMInputSource() {
        this(null);
    }

    public DOMInputSource(Node node) {
        super(null, getSystemIdFromNode(node), null);
        this.fNode = node;
    }

    public DOMInputSource(Node node, String systemId) {
        super(null, systemId, null);
        this.fNode = node;
    }

    public Node getNode() {
        return this.fNode;
    }

    public void setNode(Node node) {
        this.fNode = node;
    }

    private static String getSystemIdFromNode(Node node) {
        String str = null;
        if (node != null) {
            try {
                str = node.getBaseURI();
            } catch (NoSuchMethodError e) {
            } catch (Exception e2) {
            }
        }
        return str;
    }
}
