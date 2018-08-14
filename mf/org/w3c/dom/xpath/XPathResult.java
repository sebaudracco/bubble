package mf.org.w3c.dom.xpath;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Node;

public interface XPathResult {
    public static final short ANY_TYPE = (short) 0;
    public static final short ANY_UNORDERED_NODE_TYPE = (short) 8;
    public static final short BOOLEAN_TYPE = (short) 3;
    public static final short FIRST_ORDERED_NODE_TYPE = (short) 9;
    public static final short NUMBER_TYPE = (short) 1;
    public static final short ORDERED_NODE_ITERATOR_TYPE = (short) 5;
    public static final short ORDERED_NODE_SNAPSHOT_TYPE = (short) 7;
    public static final short STRING_TYPE = (short) 2;
    public static final short UNORDERED_NODE_ITERATOR_TYPE = (short) 4;
    public static final short UNORDERED_NODE_SNAPSHOT_TYPE = (short) 6;

    boolean getBooleanValue() throws XPathException;

    boolean getInvalidIteratorState();

    double getNumberValue() throws XPathException;

    short getResultType();

    Node getSingleNodeValue() throws XPathException;

    int getSnapshotLength() throws XPathException;

    String getStringValue() throws XPathException;

    Node iterateNext() throws XPathException, DOMException;

    Node snapshotItem(int i) throws XPathException;
}
