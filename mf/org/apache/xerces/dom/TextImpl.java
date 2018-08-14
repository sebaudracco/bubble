package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.CharacterData;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.Text;

public class TextImpl extends CharacterDataImpl implements CharacterData, Text {
    static final long serialVersionUID = -5294980852957403469L;

    public TextImpl(CoreDocumentImpl ownerDoc, String data) {
        super(ownerDoc, data);
    }

    public void setValues(CoreDocumentImpl ownerDoc, String data) {
        this.flags = (short) 0;
        this.nextSibling = null;
        this.previousSibling = null;
        setOwnerDocument(ownerDoc);
        this.data = data;
    }

    public short getNodeType() {
        return (short) 3;
    }

    public String getNodeName() {
        return "#text";
    }

    public void setIgnorableWhitespace(boolean ignore) {
        if (needsSyncData()) {
            synchronizeData();
        }
        isIgnorableWhitespace(ignore);
    }

    public boolean isElementContentWhitespace() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return internalIsIgnorableWhitespace();
    }

    public String getWholeText() {
        if (needsSyncData()) {
            synchronizeData();
        }
        StringBuffer buffer = new StringBuffer();
        if (!(this.data == null || this.data.length() == 0)) {
            buffer.append(this.data);
        }
        getWholeTextBackward(getPreviousSibling(), buffer, getParentNode());
        String temp = buffer.toString();
        buffer.setLength(0);
        getWholeTextForward(getNextSibling(), buffer, getParentNode());
        return new StringBuilder(String.valueOf(temp)).append(buffer.toString()).toString();
    }

    protected void insertTextContent(StringBuffer buf) throws DOMException {
        String content = getNodeValue();
        if (content != null) {
            buf.insert(0, content);
        }
    }

    private boolean getWholeTextForward(Node node, StringBuffer buffer, Node parent) {
        boolean inEntRef = false;
        if (parent != null) {
            inEntRef = parent.getNodeType() == (short) 5;
        }
        while (node != null) {
            short type = node.getNodeType();
            if (type == (short) 5) {
                if (getWholeTextForward(node.getFirstChild(), buffer, node)) {
                    return true;
                }
            } else if (type != (short) 3 && type != (short) 4) {
                return true;
            } else {
                ((NodeImpl) node).getTextContent(buffer);
            }
            node = node.getNextSibling();
        }
        if (!inEntRef) {
            return false;
        }
        getWholeTextForward(parent.getNextSibling(), buffer, parent.getParentNode());
        return true;
    }

    private boolean getWholeTextBackward(Node node, StringBuffer buffer, Node parent) {
        boolean inEntRef = false;
        if (parent != null) {
            inEntRef = parent.getNodeType() == (short) 5;
        }
        while (node != null) {
            short type = node.getNodeType();
            if (type == (short) 5) {
                if (getWholeTextBackward(node.getLastChild(), buffer, node)) {
                    return true;
                }
            } else if (type != (short) 3 && type != (short) 4) {
                return true;
            } else {
                ((TextImpl) node).insertTextContent(buffer);
            }
            node = node.getPreviousSibling();
        }
        if (!inEntRef) {
            return false;
        }
        getWholeTextBackward(parent.getPreviousSibling(), buffer, parent.getParentNode());
        return true;
    }

    public Text replaceWholeText(String content) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        Node parent = getParentNode();
        if (content != null && content.length() != 0) {
            Text currentNode;
            if (ownerDocument().errorChecking) {
                if (!canModifyPrev(this)) {
                    throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                } else if (!canModifyNext(this)) {
                    throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                }
            }
            if (isReadOnly()) {
                Text newNode = ownerDocument().createTextNode(content);
                if (parent == null) {
                    return newNode;
                }
                parent.insertBefore(newNode, this);
                parent.removeChild(this);
                currentNode = newNode;
            } else {
                setData(content);
                Object currentNode2 = this;
            }
            Node prev = currentNode.getPreviousSibling();
            while (prev != null && (prev.getNodeType() == (short) 3 || prev.getNodeType() == (short) 4 || (prev.getNodeType() == (short) 5 && hasTextOnlyChildren(prev)))) {
                parent.removeChild(prev);
                prev = currentNode.getPreviousSibling();
            }
            Node next = currentNode.getNextSibling();
            while (next != null && (next.getNodeType() == (short) 3 || next.getNodeType() == (short) 4 || (next.getNodeType() == (short) 5 && hasTextOnlyChildren(next)))) {
                parent.removeChild(next);
                next = currentNode.getNextSibling();
            }
            return currentNode;
        } else if (parent == null) {
            return null;
        } else {
            parent.removeChild(this);
            return null;
        }
    }

    private boolean canModifyPrev(Node node) {
        boolean textLastChild = false;
        for (Node prev = node.getPreviousSibling(); prev != null; prev = prev.getPreviousSibling()) {
            short type = prev.getNodeType();
            if (type == (short) 5) {
                Node lastChild = prev.getLastChild();
                if (lastChild == null) {
                    return false;
                }
                while (lastChild != null) {
                    short lType = lastChild.getNodeType();
                    if (lType == (short) 3 || lType == (short) 4) {
                        textLastChild = true;
                    } else if (lType == (short) 5) {
                        if (!canModifyPrev(lastChild)) {
                            return false;
                        }
                        textLastChild = true;
                    } else if (textLastChild) {
                        return false;
                    } else {
                        return true;
                    }
                    lastChild = lastChild.getPreviousSibling();
                }
                continue;
            } else if (!(type == (short) 3 || type == (short) 4)) {
                return true;
            }
        }
        return true;
    }

    private boolean canModifyNext(Node node) {
        boolean textFirstChild = false;
        for (Node next = node.getNextSibling(); next != null; next = next.getNextSibling()) {
            short type = next.getNodeType();
            if (type == (short) 5) {
                Node firstChild = next.getFirstChild();
                if (firstChild == null) {
                    return false;
                }
                while (firstChild != null) {
                    short lType = firstChild.getNodeType();
                    if (lType == (short) 3 || lType == (short) 4) {
                        textFirstChild = true;
                    } else if (lType == (short) 5) {
                        if (!canModifyNext(firstChild)) {
                            return false;
                        }
                        textFirstChild = true;
                    } else if (textFirstChild) {
                        return false;
                    } else {
                        return true;
                    }
                    firstChild = firstChild.getNextSibling();
                }
                continue;
            } else if (!(type == (short) 3 || type == (short) 4)) {
                return true;
            }
        }
        return true;
    }

    private boolean hasTextOnlyChildren(Node node) {
        Node child = node;
        if (child == null) {
            return false;
        }
        for (child = child.getFirstChild(); child != null; child = child.getNextSibling()) {
            int type = child.getNodeType();
            if (type == 5) {
                return hasTextOnlyChildren(child);
            }
            if (type != 3 && type != 4 && type != 5) {
                return false;
            }
        }
        return true;
    }

    public boolean isIgnorableWhitespace() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return internalIsIgnorableWhitespace();
    }

    public Text splitText(int offset) throws DOMException {
        if (isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (offset < 0 || offset > this.data.length()) {
            throw new DOMException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
        Text newText = getOwnerDocument().createTextNode(this.data.substring(offset));
        setNodeValue(this.data.substring(0, offset));
        Node parentNode = getParentNode();
        if (parentNode != null) {
            parentNode.insertBefore(newText, this.nextSibling);
        }
        return newText;
    }

    public void replaceData(String value) {
        this.data = value;
    }

    public String removeData() {
        String olddata = this.data;
        this.data = "";
        return olddata;
    }
}
