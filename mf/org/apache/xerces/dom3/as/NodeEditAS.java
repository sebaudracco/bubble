package mf.org.apache.xerces.dom3.as;

import mf.org.w3c.dom.Node;

public interface NodeEditAS {
    public static final short NS_WF_CHECK = (short) 2;
    public static final short PARTIAL_VALIDITY_CHECK = (short) 3;
    public static final short STRICT_VALIDITY_CHECK = (short) 4;
    public static final short WF_CHECK = (short) 1;

    boolean canAppendChild(Node node);

    boolean canInsertBefore(Node node, Node node2);

    boolean canRemoveChild(Node node);

    boolean canReplaceChild(Node node, Node node2);

    boolean isNodeValid(boolean z, short s) throws DOMASException;
}
