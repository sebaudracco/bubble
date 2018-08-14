package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import mf.javax.xml.XMLConstants;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.UserDataHandler;
import mf.org.w3c.dom.events.Event;
import mf.org.w3c.dom.events.EventListener;
import mf.org.w3c.dom.events.EventTarget;

public abstract class NodeImpl implements Node, NodeList, EventTarget, Cloneable, Serializable {
    public static final short DOCUMENT_POSITION_CONTAINS = (short) 8;
    public static final short DOCUMENT_POSITION_DISCONNECTED = (short) 1;
    public static final short DOCUMENT_POSITION_FOLLOWING = (short) 4;
    public static final short DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC = (short) 32;
    public static final short DOCUMENT_POSITION_IS_CONTAINED = (short) 16;
    public static final short DOCUMENT_POSITION_PRECEDING = (short) 2;
    public static final short ELEMENT_DEFINITION_NODE = (short) 21;
    protected static final short FIRSTCHILD = (short) 16;
    protected static final short HASSTRING = (short) 128;
    protected static final short ID = (short) 512;
    protected static final short IGNORABLEWS = (short) 64;
    protected static final short NORMALIZED = (short) 256;
    protected static final short OWNED = (short) 8;
    protected static final short READONLY = (short) 1;
    protected static final short SPECIFIED = (short) 32;
    protected static final short SYNCCHILDREN = (short) 4;
    protected static final short SYNCDATA = (short) 2;
    public static final short TREE_POSITION_ANCESTOR = (short) 4;
    public static final short TREE_POSITION_DESCENDANT = (short) 8;
    public static final short TREE_POSITION_DISCONNECTED = (short) 0;
    public static final short TREE_POSITION_EQUIVALENT = (short) 16;
    public static final short TREE_POSITION_FOLLOWING = (short) 2;
    public static final short TREE_POSITION_PRECEDING = (short) 1;
    public static final short TREE_POSITION_SAME_NODE = (short) 32;
    static final long serialVersionUID = -6316591992167219696L;
    protected short flags;
    protected NodeImpl ownerNode;

    public abstract String getNodeName();

    public abstract short getNodeType();

    protected NodeImpl(CoreDocumentImpl ownerDocument) {
        this.ownerNode = ownerDocument;
    }

    public String getNodeValue() throws DOMException {
        return null;
    }

    public void setNodeValue(String x) throws DOMException {
    }

    public Node appendChild(Node newChild) throws DOMException {
        return insertBefore(newChild, null);
    }

    public Node cloneNode(boolean deep) {
        if (needsSyncData()) {
            synchronizeData();
        }
        try {
            NodeImpl newnode = (NodeImpl) clone();
            newnode.ownerNode = ownerDocument();
            newnode.isOwned(false);
            newnode.isReadOnly(false);
            ownerDocument().callUserDataHandlers(this, newnode, (short) 1);
            return newnode;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("**Internal Error**" + e);
        }
    }

    public Document getOwnerDocument() {
        if (isOwned()) {
            return this.ownerNode.ownerDocument();
        }
        return (Document) this.ownerNode;
    }

    CoreDocumentImpl ownerDocument() {
        if (isOwned()) {
            return this.ownerNode.ownerDocument();
        }
        return (CoreDocumentImpl) this.ownerNode;
    }

    protected void setOwnerDocument(CoreDocumentImpl doc) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (!isOwned()) {
            this.ownerNode = doc;
        }
    }

    protected int getNodeNumber() {
        return ((CoreDocumentImpl) getOwnerDocument()).getNodeNumber(this);
    }

    public Node getParentNode() {
        return null;
    }

    NodeImpl parentNode() {
        return null;
    }

    public Node getNextSibling() {
        return null;
    }

    public Node getPreviousSibling() {
        return null;
    }

    ChildNode previousSibling() {
        return null;
    }

    public NamedNodeMap getAttributes() {
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean hasChildNodes() {
        return false;
    }

    public NodeList getChildNodes() {
        return this;
    }

    public Node getFirstChild() {
        return null;
    }

    public Node getLastChild() {
        return null;
    }

    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }

    public Node removeChild(Node oldChild) throws DOMException {
        throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
    }

    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }

    public int getLength() {
        return 0;
    }

    public Node item(int index) {
        return null;
    }

    public void normalize() {
    }

    public boolean isSupported(String feature, String version) {
        return ownerDocument().getImplementation().hasFeature(feature, version);
    }

    public String getNamespaceURI() {
        return null;
    }

    public String getPrefix() {
        return null;
    }

    public void setPrefix(String prefix) throws DOMException {
        throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
    }

    public String getLocalName() {
        return null;
    }

    public void addEventListener(String type, EventListener listener, boolean useCapture) {
        ownerDocument().addEventListener(this, type, listener, useCapture);
    }

    public void removeEventListener(String type, EventListener listener, boolean useCapture) {
        ownerDocument().removeEventListener(this, type, listener, useCapture);
    }

    public boolean dispatchEvent(Event event) {
        return ownerDocument().dispatchEvent(this, event);
    }

    public String getBaseURI() {
        return null;
    }

    public short compareTreePosition(Node other) {
        if (this == other) {
            return (short) 48;
        }
        short thisType = getNodeType();
        short otherType = other.getNodeType();
        if (thisType == (short) 6 || thisType == (short) 12 || otherType == (short) 6 || otherType == (short) 12) {
            return (short) 0;
        }
        Node node;
        Node thisAncestor = this;
        Node otherAncestor = other;
        int thisDepth = 0;
        int otherDepth = 0;
        for (node = this; node != null; node = node.getParentNode()) {
            thisDepth++;
            if (node == other) {
                return (short) 5;
            }
            thisAncestor = node;
        }
        for (node = other; node != null; node = node.getParentNode()) {
            otherDepth++;
            if (node == this) {
                return (short) 10;
            }
            otherAncestor = node;
        }
        Node thisNode = this;
        Node otherNode = other;
        int thisAncestorType = thisAncestor.getNodeType();
        int otherAncestorType = otherAncestor.getNodeType();
        if (thisAncestorType == 2) {
            thisNode = ((AttrImpl) thisAncestor).getOwnerElement();
        }
        if (otherAncestorType == 2) {
            otherNode = ((AttrImpl) otherAncestor).getOwnerElement();
        }
        if (thisAncestorType == 2 && otherAncestorType == 2 && thisNode == otherNode) {
            return (short) 16;
        }
        if (thisAncestorType == 2) {
            thisDepth = 0;
            for (node = thisNode; node != null; node = node.getParentNode()) {
                thisDepth++;
                if (node == otherNode) {
                    return (short) 1;
                }
                thisAncestor = node;
            }
        }
        if (otherAncestorType == 2) {
            otherDepth = 0;
            for (node = otherNode; node != null; node = node.getParentNode()) {
                otherDepth++;
                if (node == thisNode) {
                    return (short) 2;
                }
                otherAncestor = node;
            }
        }
        if (thisAncestor != otherAncestor) {
            return (short) 0;
        }
        int i;
        if (thisDepth > otherDepth) {
            for (i = 0; i < thisDepth - otherDepth; i++) {
                thisNode = thisNode.getParentNode();
            }
            if (thisNode == otherNode) {
                return (short) 1;
            }
        }
        for (i = 0; i < otherDepth - thisDepth; i++) {
            otherNode = otherNode.getParentNode();
        }
        if (otherNode == thisNode) {
            return (short) 2;
        }
        Node thisNodeP = thisNode.getParentNode();
        for (Node otherNodeP = otherNode.getParentNode(); thisNodeP != otherNodeP; otherNodeP = otherNodeP.getParentNode()) {
            thisNode = thisNodeP;
            otherNode = otherNodeP;
            thisNodeP = thisNodeP.getParentNode();
        }
        for (Node current = thisNodeP.getFirstChild(); current != null; current = current.getNextSibling()) {
            if (current == otherNode) {
                return (short) 1;
            }
            if (current == thisNode) {
                return (short) 2;
            }
        }
        return (short) 0;
    }

    public short compareDocumentPosition(Node other) throws DOMException {
        if (this == other) {
            return (short) 0;
        }
        if (other == null || (other instanceof NodeImpl)) {
            Document thisOwnerDoc;
            Object otherOwnerDoc;
            if (getNodeType() == (short) 9) {
                thisOwnerDoc = (Document) this;
            } else {
                thisOwnerDoc = getOwnerDocument();
            }
            if (other.getNodeType() == (short) 9) {
                otherOwnerDoc = (Document) other;
            } else {
                otherOwnerDoc = other.getOwnerDocument();
            }
            if (thisOwnerDoc == otherOwnerDoc || thisOwnerDoc == null || otherOwnerDoc == null) {
                Node node;
                Node thisAncestor = this;
                Node otherAncestor = other;
                int thisDepth = 0;
                int otherDepth = 0;
                for (node = this; node != null; node = node.getParentNode()) {
                    thisDepth++;
                    if (node == other) {
                        return (short) 10;
                    }
                    thisAncestor = node;
                }
                for (node = other; node != null; node = node.getParentNode()) {
                    otherDepth++;
                    if (node == this) {
                        return (short) 20;
                    }
                    otherAncestor = node;
                }
                int thisAncestorType = thisAncestor.getNodeType();
                int otherAncestorType = otherAncestor.getNodeType();
                Node thisNode = this;
                Node otherNode = other;
                switch (thisAncestorType) {
                    case 2:
                        thisNode = ((AttrImpl) thisAncestor).getOwnerElement();
                        if (otherAncestorType == 2) {
                            otherNode = ((AttrImpl) otherAncestor).getOwnerElement();
                            if (otherNode == thisNode) {
                                if (((NamedNodeMapImpl) thisNode.getAttributes()).precedes(other, this)) {
                                    return (short) 34;
                                }
                                return (short) 36;
                            }
                        }
                        thisDepth = 0;
                        for (node = thisNode; node != null; node = node.getParentNode()) {
                            thisDepth++;
                            if (node == otherNode) {
                                return (short) 10;
                            }
                            thisAncestor = node;
                        }
                        break;
                    case 6:
                    case 12:
                        DocumentType container = thisOwnerDoc.getDoctype();
                        if (container != otherAncestor) {
                            switch (otherAncestorType) {
                                case 6:
                                case 12:
                                    if (thisAncestorType != otherAncestorType) {
                                        return thisAncestorType > otherAncestorType ? (short) 2 : (short) 4;
                                    } else {
                                        if (thisAncestorType == 12) {
                                            if (((NamedNodeMapImpl) container.getNotations()).precedes(otherAncestor, thisAncestor)) {
                                                return (short) 34;
                                            }
                                            return (short) 36;
                                        } else if (((NamedNodeMapImpl) container.getEntities()).precedes(otherAncestor, thisAncestor)) {
                                            return (short) 34;
                                        } else {
                                            return (short) 36;
                                        }
                                    }
                                default:
                                    Object thisAncestor2 = thisOwnerDoc;
                                    Object thisNode2 = thisOwnerDoc;
                                    break;
                            }
                        }
                        return (short) 10;
                    case 10:
                        if (otherNode == thisOwnerDoc) {
                            return (short) 10;
                        }
                        if (thisOwnerDoc != null && thisOwnerDoc == otherOwnerDoc) {
                            return (short) 4;
                        }
                }
                switch (otherAncestorType) {
                    case 2:
                        otherDepth = 0;
                        otherNode = ((AttrImpl) otherAncestor).getOwnerElement();
                        for (node = otherNode; node != null; node = node.getParentNode()) {
                            otherDepth++;
                            if (node == thisNode) {
                                return (short) 20;
                            }
                            otherAncestor = node;
                        }
                        break;
                    case 6:
                    case 12:
                        if (thisOwnerDoc.getDoctype() != this) {
                            Object otherAncestor2 = thisOwnerDoc;
                            Object otherNode2 = thisOwnerDoc;
                            break;
                        }
                        return (short) 20;
                    case 10:
                        if (thisNode == otherOwnerDoc) {
                            return (short) 20;
                        }
                        if (otherOwnerDoc != null && thisOwnerDoc == otherOwnerDoc) {
                            return (short) 2;
                        }
                }
                if (thisAncestor != otherAncestor) {
                    if (((NodeImpl) thisAncestor).getNodeNumber() > ((NodeImpl) otherAncestor).getNodeNumber()) {
                        return (short) 37;
                    }
                    return (short) 35;
                }
                int i;
                if (thisDepth > otherDepth) {
                    for (i = 0; i < thisDepth - otherDepth; i++) {
                        thisNode = thisNode.getParentNode();
                    }
                    if (thisNode == otherNode) {
                        return (short) 2;
                    }
                }
                for (i = 0; i < otherDepth - thisDepth; i++) {
                    otherNode = otherNode.getParentNode();
                }
                if (otherNode == thisNode) {
                    return (short) 4;
                }
                Node thisNodeP = thisNode.getParentNode();
                for (Node otherNodeP = otherNode.getParentNode(); thisNodeP != otherNodeP; otherNodeP = otherNodeP.getParentNode()) {
                    thisNode = thisNodeP;
                    otherNode = otherNodeP;
                    thisNodeP = thisNodeP.getParentNode();
                }
                for (Node current = thisNodeP.getFirstChild(); current != null; current = current.getNextSibling()) {
                    if (current == otherNode) {
                        return (short) 2;
                    }
                    if (current == thisNode) {
                        return (short) 4;
                    }
                }
                return (short) 0;
            } else if (((CoreDocumentImpl) otherOwnerDoc).getNodeNumber() > ((CoreDocumentImpl) thisOwnerDoc).getNodeNumber()) {
                return (short) 37;
            } else {
                return (short) 35;
            }
        }
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    void getTextContent(StringBuffer buf) throws DOMException {
        String content = getNodeValue();
        if (content != null) {
            buf.append(content);
        }
    }

    public void setTextContent(String textContent) throws DOMException {
        setNodeValue(textContent);
    }

    public boolean isSameNode(Node other) {
        return this == other;
    }

    public boolean isDefaultNamespace(String namespaceURI) {
        NodeImpl ancestor;
        switch (getNodeType()) {
            case (short) 1:
                String namespace = getNamespaceURI();
                String prefix = getPrefix();
                if (prefix != null && prefix.length() != 0) {
                    if (hasAttributes()) {
                        NodeImpl attr = (NodeImpl) ((ElementImpl) this).getAttributeNodeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE);
                        if (attr != null) {
                            String value = attr.getNodeValue();
                            if (namespaceURI != null) {
                                return namespaceURI.equals(value);
                            }
                            if (namespace != value) {
                                return false;
                            }
                            return true;
                        }
                    }
                    ancestor = (NodeImpl) getElementAncestor(this);
                    return ancestor != null ? ancestor.isDefaultNamespace(namespaceURI) : false;
                } else if (namespaceURI != null) {
                    return namespaceURI.equals(namespace);
                } else {
                    if (namespace != namespaceURI) {
                        return false;
                    }
                    return true;
                }
            case (short) 2:
                return this.ownerNode.getNodeType() == (short) 1 ? this.ownerNode.isDefaultNamespace(namespaceURI) : false;
            case (short) 6:
            case (short) 10:
            case (short) 11:
            case (short) 12:
                return false;
            case (short) 9:
                return ((NodeImpl) ((Document) this).getDocumentElement()).isDefaultNamespace(namespaceURI);
            default:
                ancestor = (NodeImpl) getElementAncestor(this);
                if (ancestor != null) {
                    return ancestor.isDefaultNamespace(namespaceURI);
                }
                return false;
        }
    }

    public String lookupPrefix(String namespaceURI) {
        if (namespaceURI == null) {
            return null;
        }
        switch (getNodeType()) {
            case (short) 1:
                getNamespaceURI();
                return lookupNamespacePrefix(namespaceURI, (ElementImpl) this);
            case (short) 2:
                if (this.ownerNode.getNodeType() == (short) 1) {
                    return this.ownerNode.lookupPrefix(namespaceURI);
                }
                return null;
            case (short) 6:
            case (short) 10:
            case (short) 11:
            case (short) 12:
                return null;
            case (short) 9:
                return ((NodeImpl) ((Document) this).getDocumentElement()).lookupPrefix(namespaceURI);
            default:
                NodeImpl ancestor = (NodeImpl) getElementAncestor(this);
                if (ancestor != null) {
                    return ancestor.lookupPrefix(namespaceURI);
                }
                return null;
        }
    }

    public String lookupNamespaceURI(String specifiedPrefix) {
        NodeImpl ancestor;
        switch (getNodeType()) {
            case (short) 1:
                String namespace = getNamespaceURI();
                String prefix = getPrefix();
                if (namespace != null) {
                    if (specifiedPrefix == null && prefix == specifiedPrefix) {
                        return namespace;
                    }
                    if (prefix != null && prefix.equals(specifiedPrefix)) {
                        return namespace;
                    }
                }
                if (hasAttributes()) {
                    NamedNodeMap map = getAttributes();
                    int length = map.getLength();
                    for (int i = 0; i < length; i++) {
                        Node attr = map.item(i);
                        String attrPrefix = attr.getPrefix();
                        String value = attr.getNodeValue();
                        namespace = attr.getNamespaceURI();
                        if (namespace != null && namespace.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
                            if (specifiedPrefix == null && attr.getNodeName().equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                                if (value.length() <= 0) {
                                    value = null;
                                }
                                return value;
                            } else if (attrPrefix != null && attrPrefix.equals(XMLConstants.XMLNS_ATTRIBUTE) && attr.getLocalName().equals(specifiedPrefix)) {
                                if (value.length() <= 0) {
                                    value = null;
                                }
                                return value;
                            }
                        }
                    }
                }
                ancestor = (NodeImpl) getElementAncestor(this);
                if (ancestor != null) {
                    return ancestor.lookupNamespaceURI(specifiedPrefix);
                }
                return null;
            case (short) 2:
                if (this.ownerNode.getNodeType() == (short) 1) {
                    return this.ownerNode.lookupNamespaceURI(specifiedPrefix);
                }
                return null;
            case (short) 6:
            case (short) 10:
            case (short) 11:
            case (short) 12:
                return null;
            case (short) 9:
                return ((NodeImpl) ((Document) this).getDocumentElement()).lookupNamespaceURI(specifiedPrefix);
            default:
                ancestor = (NodeImpl) getElementAncestor(this);
                if (ancestor != null) {
                    return ancestor.lookupNamespaceURI(specifiedPrefix);
                }
                return null;
        }
    }

    Node getElementAncestor(Node currentNode) {
        for (Node parent = currentNode.getParentNode(); parent != null; parent = parent.getParentNode()) {
            if (parent.getNodeType() == (short) 1) {
                return parent;
            }
        }
        return null;
    }

    String lookupNamespacePrefix(String namespaceURI, ElementImpl el) {
        String foundNamespace;
        String namespace = getNamespaceURI();
        String prefix = getPrefix();
        if (!(namespace == null || !namespace.equals(namespaceURI) || prefix == null)) {
            foundNamespace = el.lookupNamespaceURI(prefix);
            if (foundNamespace != null && foundNamespace.equals(namespaceURI)) {
                return prefix;
            }
        }
        if (hasAttributes()) {
            NamedNodeMap map = getAttributes();
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                Node attr = map.item(i);
                String attrPrefix = attr.getPrefix();
                String value = attr.getNodeValue();
                namespace = attr.getNamespaceURI();
                if (namespace != null && namespace.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI) && (attr.getNodeName().equals(XMLConstants.XMLNS_ATTRIBUTE) || (attrPrefix != null && attrPrefix.equals(XMLConstants.XMLNS_ATTRIBUTE) && value.equals(namespaceURI)))) {
                    String localname = attr.getLocalName();
                    foundNamespace = el.lookupNamespaceURI(localname);
                    if (foundNamespace != null && foundNamespace.equals(namespaceURI)) {
                        return localname;
                    }
                }
            }
        }
        NodeImpl ancestor = (NodeImpl) getElementAncestor(this);
        if (ancestor != null) {
            return ancestor.lookupNamespacePrefix(namespaceURI, el);
        }
        return null;
    }

    public boolean isEqualNode(Node arg) {
        if (arg == this) {
            return true;
        }
        if (arg.getNodeType() != getNodeType()) {
            return false;
        }
        if (getNodeName() == null) {
            if (arg.getNodeName() != null) {
                return false;
            }
        } else if (!getNodeName().equals(arg.getNodeName())) {
            return false;
        }
        if (getLocalName() == null) {
            if (arg.getLocalName() != null) {
                return false;
            }
        } else if (!getLocalName().equals(arg.getLocalName())) {
            return false;
        }
        if (getNamespaceURI() == null) {
            if (arg.getNamespaceURI() != null) {
                return false;
            }
        } else if (!getNamespaceURI().equals(arg.getNamespaceURI())) {
            return false;
        }
        if (getPrefix() == null) {
            if (arg.getPrefix() != null) {
                return false;
            }
        } else if (!getPrefix().equals(arg.getPrefix())) {
            return false;
        }
        if (getNodeValue() == null) {
            if (arg.getNodeValue() != null) {
                return false;
            }
            return true;
        } else if (getNodeValue().equals(arg.getNodeValue())) {
            return true;
        } else {
            return false;
        }
    }

    public Object getFeature(String feature, String version) {
        return isSupported(feature, version) ? this : null;
    }

    public Object setUserData(String key, Object data, UserDataHandler handler) {
        return ownerDocument().setUserData(this, key, data, handler);
    }

    public Object getUserData(String key) {
        return ownerDocument().getUserData(this, key);
    }

    protected Hashtable getUserDataRecord() {
        return ownerDocument().getUserDataRecord(this);
    }

    public void setReadOnly(boolean readOnly, boolean deep) {
        if (needsSyncData()) {
            synchronizeData();
        }
        isReadOnly(readOnly);
    }

    public boolean getReadOnly() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return isReadOnly();
    }

    public void setUserData(Object data) {
        ownerDocument().setUserData(this, data);
    }

    public Object getUserData() {
        return ownerDocument().getUserData(this);
    }

    protected void changed() {
        ownerDocument().changed();
    }

    protected int changes() {
        return ownerDocument().changes();
    }

    protected void synchronizeData() {
        needsSyncData(false);
    }

    protected Node getContainer() {
        return null;
    }

    final boolean isReadOnly() {
        return (this.flags & 1) != 0;
    }

    final void isReadOnly(boolean value) {
        this.flags = (short) (value ? this.flags | 1 : this.flags & -2);
    }

    final boolean needsSyncData() {
        return (this.flags & 2) != 0;
    }

    final void needsSyncData(boolean value) {
        this.flags = (short) (value ? this.flags | 2 : this.flags & -3);
    }

    final boolean needsSyncChildren() {
        return (this.flags & 4) != 0;
    }

    public final void needsSyncChildren(boolean value) {
        this.flags = (short) (value ? this.flags | 4 : this.flags & -5);
    }

    final boolean isOwned() {
        return (this.flags & 8) != 0;
    }

    final void isOwned(boolean value) {
        this.flags = (short) (value ? this.flags | 8 : this.flags & -9);
    }

    final boolean isFirstChild() {
        return (this.flags & 16) != 0;
    }

    final void isFirstChild(boolean value) {
        this.flags = (short) (value ? this.flags | 16 : this.flags & -17);
    }

    final boolean isSpecified() {
        return (this.flags & 32) != 0;
    }

    final void isSpecified(boolean value) {
        this.flags = (short) (value ? this.flags | 32 : this.flags & -33);
    }

    final boolean internalIsIgnorableWhitespace() {
        return (this.flags & 64) != 0;
    }

    final void isIgnorableWhitespace(boolean value) {
        this.flags = (short) (value ? this.flags | 64 : this.flags & -65);
    }

    final boolean hasStringValue() {
        return (this.flags & 128) != 0;
    }

    final void hasStringValue(boolean value) {
        this.flags = (short) (value ? this.flags | 128 : this.flags & -129);
    }

    final boolean isNormalized() {
        return (this.flags & 256) != 0;
    }

    final void isNormalized(boolean value) {
        if (!(value || !isNormalized() || this.ownerNode == null)) {
            this.ownerNode.isNormalized(false);
        }
        this.flags = (short) (value ? this.flags | 256 : this.flags & -257);
    }

    final boolean isIdAttribute() {
        return (this.flags & 512) != 0;
    }

    final void isIdAttribute(boolean value) {
        this.flags = (short) (value ? this.flags | 512 : this.flags & -513);
    }

    public String toString() {
        return "[" + getNodeName() + ": " + getNodeValue() + "]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        if (needsSyncData()) {
            synchronizeData();
        }
        out.defaultWriteObject();
    }
}
