package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.Node;

public interface DeferredNode extends Node {
    public static final short TYPE_NODE = (short) 20;

    int getNodeIndex();
}
