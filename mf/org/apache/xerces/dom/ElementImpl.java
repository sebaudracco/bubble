package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.ElementTraversal;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.TypeInfo;

public class ElementImpl extends ParentNode implements Element, ElementTraversal, TypeInfo {
    static final long serialVersionUID = 3717253516652722278L;
    protected AttributeMap attributes;
    protected String name;

    public ElementImpl(CoreDocumentImpl ownerDoc, String name) {
        super(ownerDoc);
        this.name = name;
        needsSyncData(true);
    }

    protected ElementImpl() {
    }

    void rename(String name) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (name.indexOf(58) != -1) {
                throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            } else if (!CoreDocumentImpl.isXMLName(name, this.ownerDocument.isXML11Version())) {
                throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
            }
        }
        this.name = name;
        reconcileDefaultAttributes();
    }

    public short getNodeType() {
        return (short) 1;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public NamedNodeMap getAttributes() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes;
    }

    public Node cloneNode(boolean deep) {
        ElementImpl newnode = (ElementImpl) super.cloneNode(deep);
        if (this.attributes != null) {
            newnode.attributes = (AttributeMap) this.attributes.cloneMap(newnode);
        }
        return newnode;
    }

    public String getBaseURI() {
        String str = null;
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes != null) {
            Attr attrNode = getXMLBaseAttribute();
            if (attrNode != null) {
                String uri = attrNode.getNodeValue();
                if (uri.length() != 0) {
                    try {
                        URI _uri = new URI(uri, true);
                        if (_uri.isAbsoluteURI()) {
                            return _uri.toString();
                        }
                        String parentBaseURI;
                        if (this.ownerNode != null) {
                            parentBaseURI = this.ownerNode.getBaseURI();
                        } else {
                            parentBaseURI = str;
                        }
                        if (parentBaseURI == null) {
                            return str;
                        }
                        try {
                            _uri.absolutize(new URI(parentBaseURI));
                            return _uri.toString();
                        } catch (MalformedURIException e) {
                            return str;
                        }
                    } catch (MalformedURIException e2) {
                        return str;
                    }
                }
            }
        }
        return this.ownerNode != null ? this.ownerNode.getBaseURI() : str;
    }

    protected Attr getXMLBaseAttribute() {
        return (Attr) this.attributes.getNamedItem("xml:base");
    }

    protected void setOwnerDocument(CoreDocumentImpl doc) {
        super.setOwnerDocument(doc);
        if (this.attributes != null) {
            this.attributes.setOwnerDocument(doc);
        }
    }

    public String getAttribute(String name) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        Attr attr = (Attr) this.attributes.getNamedItem(name);
        return attr == null ? "" : attr.getValue();
    }

    public Attr getAttributeNode(String name) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr) this.attributes.getNamedItem(name);
    }

    public NodeList getElementsByTagName(String tagname) {
        return new DeepNodeListImpl(this, tagname);
    }

    public String getTagName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public void normalize() {
        if (!isNormalized()) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            ChildNode kid = this.firstChild;
            while (kid != null) {
                ChildNode next = kid.nextSibling;
                if (kid.getNodeType() == (short) 3) {
                    if (next != null && next.getNodeType() == (short) 3) {
                        ((Text) kid).appendData(next.getNodeValue());
                        removeChild(next);
                        next = kid;
                    } else if (kid.getNodeValue() == null || kid.getNodeValue().length() == 0) {
                        removeChild(kid);
                    }
                } else if (kid.getNodeType() == (short) 1) {
                    kid.normalize();
                }
                kid = next;
            }
            if (this.attributes != null) {
                for (int i = 0; i < this.attributes.getLength(); i++) {
                    this.attributes.item(i).normalize();
                }
            }
            isNormalized(true);
        }
    }

    public void removeAttribute(String name) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes != null) {
            this.attributes.safeRemoveNamedItem(name);
        }
    }

    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes != null) {
            return (Attr) this.attributes.removeItem(oldAttr, true);
        }
        throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
    }

    public void setAttribute(String name, String value) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        Attr newAttr = getAttributeNode(name);
        if (newAttr == null) {
            newAttr = getOwnerDocument().createAttribute(name);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            newAttr.setNodeValue(value);
            this.attributes.setNamedItem(newAttr);
            return;
        }
        newAttr.setNodeValue(value);
    }

    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (newAttr.getOwnerDocument() != this.ownerDocument) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr) this.attributes.setNamedItem(newAttr);
    }

    public String getAttributeNS(String namespaceURI, String localName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            return "";
        }
        Attr attr = (Attr) this.attributes.getNamedItemNS(namespaceURI, localName);
        return attr == null ? "" : attr.getValue();
    }

    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        String prefix;
        String localName;
        if (needsSyncData()) {
            synchronizeData();
        }
        int index = qualifiedName.indexOf(58);
        if (index < 0) {
            prefix = null;
            localName = qualifiedName;
        } else {
            prefix = qualifiedName.substring(0, index);
            localName = qualifiedName.substring(index + 1);
        }
        Attr newAttr = getAttributeNodeNS(namespaceURI, localName);
        if (newAttr == null) {
            newAttr = getOwnerDocument().createAttributeNS(namespaceURI, qualifiedName);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            newAttr.setNodeValue(value);
            this.attributes.setNamedItemNS(newAttr);
            return;
        }
        if (newAttr instanceof AttrNSImpl) {
            AttrNSImpl attrNSImpl = (AttrNSImpl) newAttr;
            if (prefix != null) {
                localName = new StringBuilder(String.valueOf(prefix)).append(":").append(localName).toString();
            }
            attrNSImpl.name = localName;
        } else {
            newAttr = ((CoreDocumentImpl) getOwnerDocument()).createAttributeNS(namespaceURI, qualifiedName, localName);
            this.attributes.setNamedItemNS(newAttr);
        }
        newAttr.setNodeValue(value);
    }

    public void removeAttributeNS(String namespaceURI, String localName) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes != null) {
            this.attributes.safeRemoveNamedItemNS(namespaceURI, localName);
        }
    }

    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            return null;
        }
        return (Attr) this.attributes.getNamedItemNS(namespaceURI, localName);
    }

    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (newAttr.getOwnerDocument() != this.ownerDocument) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr) this.attributes.setNamedItemNS(newAttr);
    }

    protected int setXercesAttributeNode(Attr attr) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes.addItem(attr);
    }

    protected int getXercesAttribute(String namespaceURI, String localName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            return -1;
        }
        return this.attributes.getNamedItemIndex(namespaceURI, localName);
    }

    public boolean hasAttributes() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return (this.attributes == null || this.attributes.getLength() == 0) ? false : true;
    }

    public boolean hasAttribute(String name) {
        return getAttributeNode(name) != null;
    }

    public boolean hasAttributeNS(String namespaceURI, String localName) {
        return getAttributeNodeNS(namespaceURI, localName) != null;
    }

    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return new DeepNodeListImpl(this, namespaceURI, localName);
    }

    public boolean isEqualNode(Node arg) {
        if (!super.isEqualNode(arg)) {
            return false;
        }
        boolean hasAttrs = hasAttributes();
        if (hasAttrs != ((Element) arg).hasAttributes()) {
            return false;
        }
        if (hasAttrs) {
            NamedNodeMap map1 = getAttributes();
            NamedNodeMap map2 = ((Element) arg).getAttributes();
            int len = map1.getLength();
            if (len != map2.getLength()) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                Node n1 = map1.item(i);
                Node n2;
                if (n1.getLocalName() == null) {
                    n2 = map2.getNamedItem(n1.getNodeName());
                    if (n2 == null || !((NodeImpl) n1).isEqualNode(n2)) {
                        return false;
                    }
                } else {
                    n2 = map2.getNamedItemNS(n1.getNamespaceURI(), n1.getLocalName());
                    if (n2 == null || !((NodeImpl) n1).isEqualNode(n2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void setIdAttributeNode(Attr at, boolean makeId) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (at.getOwnerElement() != this) {
                throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl) at).isIdAttribute(makeId);
        if (makeId) {
            this.ownerDocument.putIdentifier(at.getValue(), this);
        } else {
            this.ownerDocument.removeIdentifier(at.getValue());
        }
    }

    public void setIdAttribute(String name, boolean makeId) {
        if (needsSyncData()) {
            synchronizeData();
        }
        Attr at = getAttributeNode(name);
        if (at == null) {
            throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (at.getOwnerElement() != this) {
                throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl) at).isIdAttribute(makeId);
        if (makeId) {
            this.ownerDocument.putIdentifier(at.getValue(), this);
        } else {
            this.ownerDocument.removeIdentifier(at.getValue());
        }
    }

    public void setIdAttributeNS(String namespaceURI, String localName, boolean makeId) {
        if (needsSyncData()) {
            synchronizeData();
        }
        Attr at = getAttributeNodeNS(namespaceURI, localName);
        if (at == null) {
            throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (at.getOwnerElement() != this) {
                throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            }
        }
        ((AttrImpl) at).isIdAttribute(makeId);
        if (makeId) {
            this.ownerDocument.putIdentifier(at.getValue(), this);
        } else {
            this.ownerDocument.removeIdentifier(at.getValue());
        }
    }

    public String getTypeName() {
        return null;
    }

    public String getTypeNamespace() {
        return null;
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return false;
    }

    public TypeInfo getSchemaTypeInfo() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this;
    }

    public void setReadOnly(boolean readOnly, boolean deep) {
        super.setReadOnly(readOnly, deep);
        if (this.attributes != null) {
            this.attributes.setReadOnly(readOnly, true);
        }
    }

    protected void synchronizeData() {
        needsSyncData(false);
        boolean orig = this.ownerDocument.getMutationEvents();
        this.ownerDocument.setMutationEvents(false);
        setupDefaultAttributes();
        this.ownerDocument.setMutationEvents(orig);
    }

    void moveSpecifiedAttributes(ElementImpl el) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (el.hasAttributes()) {
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            this.attributes.moveSpecifiedAttributes(el.attributes);
        }
    }

    protected void setupDefaultAttributes() {
        NamedNodeMapImpl defaults = getDefaultAttributes();
        if (defaults != null) {
            this.attributes = new AttributeMap(this, defaults);
        }
    }

    protected void reconcileDefaultAttributes() {
        if (this.attributes != null) {
            this.attributes.reconcileDefaults(getDefaultAttributes());
        }
    }

    protected NamedNodeMapImpl getDefaultAttributes() {
        DocumentTypeImpl doctype = (DocumentTypeImpl) this.ownerDocument.getDoctype();
        if (doctype == null) {
            return null;
        }
        ElementDefinitionImpl eldef = (ElementDefinitionImpl) doctype.getElements().getNamedItem(getNodeName());
        if (eldef != null) {
            return (NamedNodeMapImpl) eldef.getAttributes();
        }
        return null;
    }

    public final int getChildElementCount() {
        int count = 0;
        for (Element child = getFirstElementChild(); child != null; child = ((ElementImpl) child).getNextElementSibling()) {
            count++;
        }
        return count;
    }

    public final Element getFirstElementChild() {
        for (Node n = getFirstChild(); n != null; n = n.getNextSibling()) {
            switch (n.getNodeType()) {
                case (short) 1:
                    return (Element) n;
                case (short) 5:
                    Element e = getFirstElementChild(n);
                    if (e == null) {
                        break;
                    }
                    return e;
                default:
                    break;
            }
        }
        return null;
    }

    public final Element getLastElementChild() {
        for (Node n = getLastChild(); n != null; n = n.getPreviousSibling()) {
            switch (n.getNodeType()) {
                case (short) 1:
                    return (Element) n;
                case (short) 5:
                    Element e = getLastElementChild(n);
                    if (e == null) {
                        break;
                    }
                    return e;
                default:
                    break;
            }
        }
        return null;
    }

    public final Element getNextElementSibling() {
        Node n = getNextLogicalSibling(this);
        while (n != null) {
            switch (n.getNodeType()) {
                case (short) 1:
                    return (Element) n;
                case (short) 5:
                    Element e = getFirstElementChild(n);
                    if (e == null) {
                        break;
                    }
                    return e;
                default:
                    break;
            }
            n = getNextLogicalSibling(n);
        }
        return null;
    }

    public final Element getPreviousElementSibling() {
        Node n = getPreviousLogicalSibling(this);
        while (n != null) {
            switch (n.getNodeType()) {
                case (short) 1:
                    return (Element) n;
                case (short) 5:
                    Element e = getLastElementChild(n);
                    if (e == null) {
                        break;
                    }
                    return e;
                default:
                    break;
            }
            n = getPreviousLogicalSibling(n);
        }
        return null;
    }

    private Element getFirstElementChild(Node n) {
        Node top = n;
        while (n != null) {
            if (n.getNodeType() == (short) 1) {
                return (Element) n;
            }
            Node next = n.getFirstChild();
            while (next == null && top != n) {
                next = n.getNextSibling();
                if (next == null) {
                    n = n.getParentNode();
                    if (n == null) {
                        return null;
                    }
                    if (top == n) {
                        return null;
                    }
                }
            }
            n = next;
        }
        return null;
    }

    private Element getLastElementChild(Node n) {
        Node top = n;
        while (n != null) {
            if (n.getNodeType() == (short) 1) {
                return (Element) n;
            }
            Node next = n.getLastChild();
            while (next == null && top != n) {
                next = n.getPreviousSibling();
                if (next == null) {
                    n = n.getParentNode();
                    if (n == null) {
                        return null;
                    }
                    if (top == n) {
                        return null;
                    }
                }
            }
            n = next;
        }
        return null;
    }

    private Node getNextLogicalSibling(Node n) {
        Node next = n.getNextSibling();
        if (next == null) {
            Node parent = n.getParentNode();
            while (parent != null && parent.getNodeType() == (short) 5) {
                next = parent.getNextSibling();
                if (next != null) {
                    break;
                }
                parent = parent.getParentNode();
            }
        }
        return next;
    }

    private Node getPreviousLogicalSibling(Node n) {
        Node prev = n.getPreviousSibling();
        if (prev == null) {
            Node parent = n.getParentNode();
            while (parent != null && parent.getNodeType() == (short) 5) {
                prev = parent.getPreviousSibling();
                if (prev != null) {
                    break;
                }
                parent = parent.getParentNode();
            }
        }
        return prev;
    }
}
