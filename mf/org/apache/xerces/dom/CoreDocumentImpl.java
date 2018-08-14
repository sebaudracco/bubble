package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Entity;
import mf.org.w3c.dom.EntityReference;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.Notation;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.UserDataHandler;
import mf.org.w3c.dom.events.Event;
import mf.org.w3c.dom.events.EventListener;
import mf.org.w3c.dom.ls.DOMImplementationLS;
import mf.org.w3c.dom.ls.LSSerializer;

public class CoreDocumentImpl extends ParentNode implements Document {
    private static final int[] kidOK = new int[13];
    static final long serialVersionUID = 0;
    protected String actualEncoding;
    protected boolean allowGrammarAccess;
    protected int changes;
    protected ElementImpl docElement;
    protected DocumentTypeImpl docType;
    private int documentNumber;
    transient DOMNormalizer domNormalizer;
    protected String encoding;
    protected boolean errorChecking;
    transient DOMConfigurationImpl fConfiguration;
    protected String fDocumentURI;
    transient NodeListCache fFreeNLCache;
    transient Object fXPathEvaluator;
    protected Hashtable identifiers;
    private int nodeCounter;
    private Map nodeTable;
    protected boolean standalone;
    protected Map userData;
    protected String version;
    private boolean xml11Version;
    protected boolean xmlVersionChanged;

    static {
        kidOK[9] = 1410;
        int[] iArr = kidOK;
        int[] iArr2 = kidOK;
        int[] iArr3 = kidOK;
        kidOK[1] = 442;
        iArr3[5] = 442;
        iArr2[6] = 442;
        iArr[11] = 442;
        kidOK[2] = 40;
        iArr = kidOK;
        iArr2 = kidOK;
        iArr3 = kidOK;
        int[] iArr4 = kidOK;
        int[] iArr5 = kidOK;
        kidOK[12] = 0;
        iArr5[4] = 0;
        iArr4[3] = 0;
        iArr3[8] = 0;
        iArr2[7] = 0;
        iArr[10] = 0;
    }

    public CoreDocumentImpl() {
        this(false);
    }

    public CoreDocumentImpl(boolean grammarAccess) {
        super(null);
        this.domNormalizer = null;
        this.fConfiguration = null;
        this.fXPathEvaluator = null;
        this.changes = 0;
        this.errorChecking = true;
        this.xmlVersionChanged = false;
        this.documentNumber = 0;
        this.nodeCounter = 0;
        this.xml11Version = false;
        this.ownerDocument = this;
        this.allowGrammarAccess = grammarAccess;
    }

    public CoreDocumentImpl(DocumentType doctype) {
        this(doctype, false);
    }

    public CoreDocumentImpl(DocumentType doctype, boolean grammarAccess) {
        this(grammarAccess);
        if (doctype != null) {
            try {
                ((DocumentTypeImpl) doctype).ownerDocument = this;
                appendChild(doctype);
            } catch (ClassCastException e) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
    }

    public final Document getOwnerDocument() {
        return null;
    }

    public short getNodeType() {
        return (short) 9;
    }

    public String getNodeName() {
        return "#document";
    }

    public Node cloneNode(boolean deep) {
        CoreDocumentImpl newdoc = new CoreDocumentImpl();
        callUserDataHandlers(this, newdoc, (short) 1);
        cloneNode(newdoc, deep);
        return newdoc;
    }

    protected void cloneNode(CoreDocumentImpl newdoc, boolean deep) {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        if (deep) {
            HashMap reversedIdentifiers = null;
            if (this.identifiers != null) {
                reversedIdentifiers = new HashMap();
                for (Entry entry : this.identifiers.entrySet()) {
                    reversedIdentifiers.put(entry.getValue(), entry.getKey());
                }
            }
            for (ChildNode kid = this.firstChild; kid != null; kid = kid.nextSibling) {
                newdoc.appendChild(newdoc.importNode(kid, true, true, reversedIdentifiers));
            }
        }
        newdoc.allowGrammarAccess = this.allowGrammarAccess;
        newdoc.errorChecking = this.errorChecking;
    }

    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        int type = newChild.getNodeType();
        if (this.errorChecking) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if ((type == 1 && this.docElement != null) || (type == 10 && this.docType != null)) {
                throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            }
        }
        if (newChild.getOwnerDocument() == null && (newChild instanceof DocumentTypeImpl)) {
            ((DocumentTypeImpl) newChild).ownerDocument = this;
        }
        super.insertBefore(newChild, refChild);
        if (type == 1) {
            this.docElement = (ElementImpl) newChild;
        } else if (type == 10) {
            this.docType = (DocumentTypeImpl) newChild;
        }
        return newChild;
    }

    public Node removeChild(Node oldChild) throws DOMException {
        super.removeChild(oldChild);
        int type = oldChild.getNodeType();
        if (type == 1) {
            this.docElement = null;
        } else if (type == 10) {
            this.docType = null;
        }
        return oldChild;
    }

    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        if (newChild.getOwnerDocument() == null && (newChild instanceof DocumentTypeImpl)) {
            ((DocumentTypeImpl) newChild).ownerDocument = this;
        }
        if (!this.errorChecking || ((this.docType == null || oldChild.getNodeType() == (short) 10 || newChild.getNodeType() != (short) 10) && (this.docElement == null || oldChild.getNodeType() == (short) 1 || newChild.getNodeType() != (short) 1))) {
            super.replaceChild(newChild, oldChild);
            int type = oldChild.getNodeType();
            if (type == 1) {
                this.docElement = (ElementImpl) newChild;
            } else if (type == 10) {
                this.docType = (DocumentTypeImpl) newChild;
            }
            return oldChild;
        }
        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }

    public String getTextContent() throws DOMException {
        return null;
    }

    public void setTextContent(String textContent) throws DOMException {
    }

    public Object getFeature(String feature, String version) {
        boolean anyVersion = false;
        if (version == null || version.length() == 0) {
            anyVersion = true;
        }
        if (!feature.equalsIgnoreCase("+XPath") || (!anyVersion && !version.equals("3.0"))) {
            return super.getFeature(feature, version);
        }
        if (this.fXPathEvaluator != null) {
            return this.fXPathEvaluator;
        }
        try {
            Class xpathClass = ObjectFactory.findProviderClass("org.apache.xpath.domapi.XPathEvaluatorImpl", ObjectFactory.findClassLoader(), true);
            Constructor xpathClassConstr = xpathClass.getConstructor(new Class[]{Document.class});
            Class[] interfaces = xpathClass.getInterfaces();
            for (Class name : interfaces) {
                if (name.getName().equals("org.w3c.dom.xpath.XPathEvaluator")) {
                    this.fXPathEvaluator = xpathClassConstr.newInstance(new Object[]{this});
                    return this.fXPathEvaluator;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Attr createAttribute(String name) throws DOMException {
        if (!this.errorChecking || isXMLName(name, this.xml11Version)) {
            return new AttrImpl(this, name);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public CDATASection createCDATASection(String data) throws DOMException {
        return new CDATASectionImpl(this, data);
    }

    public Comment createComment(String data) {
        return new CommentImpl(this, data);
    }

    public DocumentFragment createDocumentFragment() {
        return new DocumentFragmentImpl(this);
    }

    public Element createElement(String tagName) throws DOMException {
        if (!this.errorChecking || isXMLName(tagName, this.xml11Version)) {
            return new ElementImpl(this, tagName);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public EntityReference createEntityReference(String name) throws DOMException {
        if (!this.errorChecking || isXMLName(name, this.xml11Version)) {
            return new EntityReferenceImpl(this, name);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        if (!this.errorChecking || isXMLName(target, this.xml11Version)) {
            return new ProcessingInstructionImpl(this, target, data);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public Text createTextNode(String data) {
        return new TextImpl(this, data);
    }

    public DocumentType getDoctype() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.docType;
    }

    public Element getDocumentElement() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.docElement;
    }

    public NodeList getElementsByTagName(String tagname) {
        return new DeepNodeListImpl(this, tagname);
    }

    public DOMImplementation getImplementation() {
        return CoreDOMImplementationImpl.getDOMImplementation();
    }

    public void setErrorChecking(boolean check) {
        this.errorChecking = check;
    }

    public void setStrictErrorChecking(boolean check) {
        this.errorChecking = check;
    }

    public boolean getErrorChecking() {
        return this.errorChecking;
    }

    public boolean getStrictErrorChecking() {
        return this.errorChecking;
    }

    public String getInputEncoding() {
        return this.actualEncoding;
    }

    public void setInputEncoding(String value) {
        this.actualEncoding = value;
    }

    public void setXmlEncoding(String value) {
        this.encoding = value;
    }

    public void setEncoding(String value) {
        setXmlEncoding(value);
    }

    public String getXmlEncoding() {
        return this.encoding;
    }

    public String getEncoding() {
        return getXmlEncoding();
    }

    public void setXmlVersion(String value) {
        if (value.equals("1.0") || value.equals("1.1")) {
            if (!getXmlVersion().equals(value)) {
                this.xmlVersionChanged = true;
                isNormalized(false);
                this.version = value;
            }
            if (getXmlVersion().equals("1.1")) {
                this.xml11Version = true;
                return;
            } else {
                this.xml11Version = false;
                return;
            }
        }
        throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
    }

    public void setVersion(String value) {
        setXmlVersion(value);
    }

    public String getXmlVersion() {
        return this.version == null ? "1.0" : this.version;
    }

    public String getVersion() {
        return getXmlVersion();
    }

    public void setXmlStandalone(boolean value) throws DOMException {
        this.standalone = value;
    }

    public void setStandalone(boolean value) {
        setXmlStandalone(value);
    }

    public boolean getXmlStandalone() {
        return this.standalone;
    }

    public boolean getStandalone() {
        return getXmlStandalone();
    }

    public String getDocumentURI() {
        return this.fDocumentURI;
    }

    protected boolean canRenameElements(String newNamespaceURI, String newNodeName, ElementImpl el) {
        return true;
    }

    public Node renameNode(Node n, String namespaceURI, String name) throws DOMException {
        if (!this.errorChecking || n.getOwnerDocument() == this || n == this) {
            switch (n.getNodeType()) {
                case (short) 1:
                    ElementImpl el = (ElementImpl) n;
                    if (el instanceof ElementNSImpl) {
                        if (canRenameElements(namespaceURI, name, el)) {
                            ((ElementNSImpl) el).rename(namespaceURI, name);
                            callUserDataHandlers(el, null, (short) 4);
                        } else {
                            el = replaceRenameElement(el, namespaceURI, name);
                        }
                    } else if (namespaceURI == null && canRenameElements(null, name, el)) {
                        el.rename(name);
                        callUserDataHandlers(el, null, (short) 4);
                    } else {
                        el = replaceRenameElement(el, namespaceURI, name);
                    }
                    renamedElement((Element) n, el);
                    return el;
                case (short) 2:
                    NodeImpl at = (AttrImpl) n;
                    Element el2 = at.getOwnerElement();
                    if (el2 != null) {
                        el2.removeAttributeNode(at);
                    }
                    if (n instanceof AttrNSImpl) {
                        ((AttrNSImpl) at).rename(namespaceURI, name);
                        if (el2 != null) {
                            el2.setAttributeNodeNS(at);
                        }
                        callUserDataHandlers(at, null, (short) 4);
                    } else if (namespaceURI == null) {
                        at.rename(name);
                        if (el2 != null) {
                            el2.setAttributeNode(at);
                        }
                        callUserDataHandlers(at, null, (short) 4);
                    } else {
                        NodeImpl nat = (AttrNSImpl) createAttributeNS(namespaceURI, name);
                        copyEventListeners(at, nat);
                        Hashtable data = removeUserDataTable(at);
                        for (Node child = at.getFirstChild(); child != null; child = at.getFirstChild()) {
                            at.removeChild(child);
                            nat.appendChild(child);
                        }
                        setUserDataTable(nat, data);
                        callUserDataHandlers(at, nat, (short) 4);
                        if (el2 != null) {
                            el2.setAttributeNode(nat);
                        }
                        at = nat;
                    }
                    renamedAttrNode((Attr) n, at);
                    return at;
                default:
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
            }
        }
        throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
    }

    private ElementImpl replaceRenameElement(ElementImpl el, String namespaceURI, String name) {
        ElementNSImpl nel = (ElementNSImpl) createElementNS(namespaceURI, name);
        copyEventListeners(el, nel);
        Hashtable data = removeUserDataTable(el);
        Node parent = el.getParentNode();
        Node nextSib = el.getNextSibling();
        if (parent != null) {
            parent.removeChild(el);
        }
        Node child = el.getFirstChild();
        while (child != null) {
            el.removeChild(child);
            nel.appendChild(child);
            child = el.getFirstChild();
        }
        nel.moveSpecifiedAttributes(el);
        setUserDataTable(nel, data);
        callUserDataHandlers(el, nel, (short) 4);
        if (parent != null) {
            parent.insertBefore(nel, nextSib);
        }
        return nel;
    }

    public void normalizeDocument() {
        if (!isNormalized() || isNormalizeDocRequired()) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            if (this.domNormalizer == null) {
                this.domNormalizer = new DOMNormalizer();
            }
            if (this.fConfiguration == null) {
                this.fConfiguration = new DOMConfigurationImpl();
            } else {
                this.fConfiguration.reset();
            }
            this.domNormalizer.normalizeDocument(this, this.fConfiguration);
            isNormalized(true);
            this.xmlVersionChanged = false;
        }
    }

    public DOMConfiguration getDomConfig() {
        if (this.fConfiguration == null) {
            this.fConfiguration = new DOMConfigurationImpl();
        }
        return this.fConfiguration;
    }

    public String getBaseURI() {
        if (this.fDocumentURI == null || this.fDocumentURI.length() == 0) {
            return this.fDocumentURI;
        }
        try {
            return new URI(this.fDocumentURI).toString();
        } catch (MalformedURIException e) {
            return null;
        }
    }

    public void setDocumentURI(String documentURI) {
        this.fDocumentURI = documentURI;
    }

    public boolean getAsync() {
        return false;
    }

    public void setAsync(boolean async) {
        if (async) {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
    }

    public void abort() {
    }

    public boolean load(String uri) {
        return false;
    }

    public boolean loadXML(String source) {
        return false;
    }

    public String saveXML(Node node) throws DOMException {
        if (!this.errorChecking || node == null || this == node.getOwnerDocument()) {
            LSSerializer xmlWriter = ((DOMImplementationLS) DOMImplementationImpl.getDOMImplementation()).createLSSerializer();
            if (node == null) {
                node = this;
            }
            return xmlWriter.writeToString(node);
        }
        throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
    }

    void setMutationEvents(boolean set) {
    }

    boolean getMutationEvents() {
        return false;
    }

    public DocumentType createDocumentType(String qualifiedName, String publicID, String systemID) throws DOMException {
        return new DocumentTypeImpl(this, qualifiedName, publicID, systemID);
    }

    public Entity createEntity(String name) throws DOMException {
        if (!this.errorChecking || isXMLName(name, this.xml11Version)) {
            return new EntityImpl(this, name);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public Notation createNotation(String name) throws DOMException {
        if (!this.errorChecking || isXMLName(name, this.xml11Version)) {
            return new NotationImpl(this, name);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    public ElementDefinitionImpl createElementDefinition(String name) throws DOMException {
        if (!this.errorChecking || isXMLName(name, this.xml11Version)) {
            return new ElementDefinitionImpl(this, name);
        }
        throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
    }

    protected int getNodeNumber() {
        if (this.documentNumber == 0) {
            this.documentNumber = ((CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation()).assignDocumentNumber();
        }
        return this.documentNumber;
    }

    protected int getNodeNumber(Node node) {
        if (this.nodeTable == null) {
            this.nodeTable = new WeakHashMap();
            int num = this.nodeCounter - 1;
            this.nodeCounter = num;
            this.nodeTable.put(node, new Integer(num));
            return num;
        }
        Integer n = (Integer) this.nodeTable.get(node);
        if (n != null) {
            return n.intValue();
        }
        num = this.nodeCounter - 1;
        this.nodeCounter = num;
        this.nodeTable.put(node, new Integer(num));
        return num;
    }

    public Node importNode(Node source, boolean deep) throws DOMException {
        return importNode(source, deep, false, null);
    }

    private Node importNode(Node source, boolean deep, boolean cloningDoc, HashMap reversedIdentifiers) throws DOMException {
        Node newnode;
        Hashtable userData = null;
        if (source instanceof NodeImpl) {
            userData = ((NodeImpl) source).getUserDataRecord();
        }
        Object newnode2;
        switch (source.getNodeType()) {
            case 1:
                Element newElement;
                boolean domLevel20 = source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0");
                if (!domLevel20 || source.getLocalName() == null) {
                    newElement = createElement(source.getNodeName());
                } else {
                    newElement = createElementNS(source.getNamespaceURI(), source.getNodeName());
                }
                NamedNodeMap sourceAttrs = source.getAttributes();
                if (sourceAttrs != null) {
                    int length = sourceAttrs.getLength();
                    for (int index = 0; index < length; index++) {
                        Attr attr = (Attr) sourceAttrs.item(index);
                        if (attr.getSpecified() || cloningDoc) {
                            Attr newAttr = (Attr) importNode(attr, true, cloningDoc, reversedIdentifiers);
                            if (!domLevel20 || attr.getLocalName() == null) {
                                newElement.setAttributeNode(newAttr);
                            } else {
                                newElement.setAttributeNodeNS(newAttr);
                            }
                        }
                    }
                }
                if (reversedIdentifiers != null) {
                    Object elementId = reversedIdentifiers.get(source);
                    if (elementId != null) {
                        if (this.identifiers == null) {
                            this.identifiers = new Hashtable();
                        }
                        this.identifiers.put(elementId, newElement);
                    }
                }
                newnode = newElement;
                break;
            case 2:
                if (!source.getOwnerDocument().getImplementation().hasFeature("XML", "2.0")) {
                    newnode = createAttribute(source.getNodeName());
                } else if (source.getLocalName() == null) {
                    newnode = createAttribute(source.getNodeName());
                } else {
                    newnode = createAttributeNS(source.getNamespaceURI(), source.getNodeName());
                }
                if (!(source instanceof AttrImpl)) {
                    if (source.getFirstChild() != null) {
                        deep = true;
                        break;
                    }
                    newnode.setNodeValue(source.getNodeValue());
                    deep = false;
                    break;
                }
                AttrImpl attr2 = (AttrImpl) source;
                if (!attr2.hasStringValue()) {
                    deep = true;
                    break;
                }
                ((AttrImpl) newnode).setValue(attr2.getValue());
                deep = false;
                break;
            case 3:
                newnode = createTextNode(source.getNodeValue());
                break;
            case 4:
                newnode = createCDATASection(source.getNodeValue());
                break;
            case 5:
                newnode = createEntityReference(source.getNodeName());
                deep = false;
                break;
            case 6:
                Entity srcentity = (Entity) source;
                EntityImpl newentity = (EntityImpl) createEntity(source.getNodeName());
                newentity.setPublicId(srcentity.getPublicId());
                newentity.setSystemId(srcentity.getSystemId());
                newentity.setNotationName(srcentity.getNotationName());
                newentity.isReadOnly(false);
                newnode2 = newentity;
                break;
            case 7:
                newnode = createProcessingInstruction(source.getNodeName(), source.getNodeValue());
                break;
            case 8:
                newnode = createComment(source.getNodeValue());
                break;
            case 10:
                if (cloningDoc) {
                    int i;
                    DocumentType srcdoctype = (DocumentType) source;
                    DocumentTypeImpl newdoctype = (DocumentTypeImpl) createDocumentType(srcdoctype.getNodeName(), srcdoctype.getPublicId(), srcdoctype.getSystemId());
                    newdoctype.setInternalSubset(srcdoctype.getInternalSubset());
                    NamedNodeMap smap = srcdoctype.getEntities();
                    NamedNodeMap tmap = newdoctype.getEntities();
                    if (smap != null) {
                        for (i = 0; i < smap.getLength(); i++) {
                            tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                        }
                    }
                    smap = srcdoctype.getNotations();
                    tmap = newdoctype.getNotations();
                    if (smap != null) {
                        for (i = 0; i < smap.getLength(); i++) {
                            tmap.setNamedItem(importNode(smap.item(i), true, true, reversedIdentifiers));
                        }
                    }
                    newnode2 = newdoctype;
                    break;
                }
                throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
            case 11:
                newnode = createDocumentFragment();
                break;
            case 12:
                Notation srcnotation = (Notation) source;
                NotationImpl newnotation = (NotationImpl) createNotation(source.getNodeName());
                newnotation.setPublicId(srcnotation.getPublicId());
                newnotation.setSystemId(srcnotation.getSystemId());
                newnode2 = newnotation;
                break;
            default:
                throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
        if (userData != null) {
            callUserDataHandlers(source, newnode, (short) 2, userData);
        }
        if (deep) {
            for (Node srckid = source.getFirstChild(); srckid != null; srckid = srckid.getNextSibling()) {
                newnode.appendChild(importNode(srckid, true, cloningDoc, reversedIdentifiers));
            }
        }
        if (newnode.getNodeType() == (short) 6) {
            ((NodeImpl) newnode).setReadOnly(true, true);
        }
        return newnode;
    }

    public Node adoptNode(Node source) {
        try {
            NodeImpl node = (NodeImpl) source;
            if (source == null) {
                return null;
            }
            Hashtable userData;
            if (!(source == null || source.getOwnerDocument() == null)) {
                DOMImplementation thisImpl = getImplementation();
                DOMImplementation otherImpl = source.getOwnerDocument().getImplementation();
                if (thisImpl != otherImpl) {
                    if ((thisImpl instanceof DOMImplementationImpl) && (otherImpl instanceof DeferredDOMImplementationImpl)) {
                        undeferChildren(node);
                    } else if (!((thisImpl instanceof DeferredDOMImplementationImpl) && (otherImpl instanceof DOMImplementationImpl))) {
                        return null;
                    }
                } else if (otherImpl instanceof DeferredDOMImplementationImpl) {
                    undeferChildren(node);
                }
            }
            Node parent;
            switch (node.getNodeType()) {
                case (short) 1:
                    userData = node.getUserDataRecord();
                    parent = node.getParentNode();
                    if (parent != null) {
                        parent.removeChild(source);
                    }
                    node.setOwnerDocument(this);
                    if (userData != null) {
                        setUserDataTable(node, userData);
                    }
                    ((ElementImpl) node).reconcileDefaultAttributes();
                    break;
                case (short) 2:
                    AttrImpl attr = (AttrImpl) node;
                    if (attr.getOwnerElement() != null) {
                        attr.getOwnerElement().removeAttributeNode(attr);
                    }
                    attr.isSpecified(true);
                    userData = node.getUserDataRecord();
                    attr.setOwnerDocument(this);
                    if (userData != null) {
                        setUserDataTable(node, userData);
                        break;
                    }
                    break;
                case (short) 5:
                    userData = node.getUserDataRecord();
                    parent = node.getParentNode();
                    if (parent != null) {
                        parent.removeChild(source);
                    }
                    while (true) {
                        Node child = node.getFirstChild();
                        if (child == null) {
                            node.setOwnerDocument(this);
                            if (userData != null) {
                                setUserDataTable(node, userData);
                            }
                            if (this.docType != null) {
                                Node entityNode = this.docType.getEntities().getNamedItem(node.getNodeName());
                                if (entityNode != null) {
                                    for (child = entityNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                                        node.appendChild(child.cloneNode(true));
                                    }
                                    break;
                                }
                            }
                        }
                        node.removeChild(child);
                    }
                    break;
                case (short) 6:
                case (short) 12:
                    throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                case (short) 9:
                case (short) 10:
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
                default:
                    userData = node.getUserDataRecord();
                    parent = node.getParentNode();
                    if (parent != null) {
                        parent.removeChild(source);
                    }
                    node.setOwnerDocument(this);
                    if (userData != null) {
                        setUserDataTable(node, userData);
                        break;
                    }
                    break;
            }
            if (userData == null) {
                return node;
            }
            callUserDataHandlers(source, null, (short) 5, userData);
            return node;
        } catch (ClassCastException e) {
            return null;
        }
    }

    protected void undeferChildren(Node node) {
        Node top = node;
        while (node != null) {
            if (((NodeImpl) node).needsSyncData()) {
                ((NodeImpl) node).synchronizeData();
            }
            NamedNodeMap attributes = node.getAttributes();
            if (attributes != null) {
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    undeferChildren(attributes.item(i));
                }
            }
            Node nextNode = node.getFirstChild();
            while (nextNode == null && !top.equals(node)) {
                nextNode = node.getNextSibling();
                if (nextNode == null) {
                    node = node.getParentNode();
                    if (node == null || top.equals(node)) {
                        nextNode = null;
                        break;
                    }
                }
            }
            node = nextNode;
        }
    }

    public Element getElementById(String elementId) {
        return getIdentifier(elementId);
    }

    protected final void clearIdentifiers() {
        if (this.identifiers != null) {
            this.identifiers.clear();
        }
    }

    public void putIdentifier(String idName, Element element) {
        if (element == null) {
            removeIdentifier(idName);
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        this.identifiers.put(idName, element);
    }

    public Element getIdentifier(String idName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.identifiers == null) {
            return null;
        }
        Element elem = (Element) this.identifiers.get(idName);
        if (elem != null) {
            for (Object parent = elem.getParentNode(); parent != null; parent = parent.getParentNode()) {
                if (parent == this) {
                    return elem;
                }
            }
        }
        return null;
    }

    public void removeIdentifier(String idName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.identifiers != null) {
            this.identifiers.remove(idName);
        }
    }

    public Enumeration getIdentifiers() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.identifiers == null) {
            this.identifiers = new Hashtable();
        }
        return this.identifiers.keys();
    }

    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new ElementNSImpl(this, namespaceURI, qualifiedName);
    }

    public Element createElementNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
        return new ElementNSImpl(this, namespaceURI, qualifiedName, localpart);
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new AttrNSImpl(this, namespaceURI, qualifiedName);
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
        return new AttrNSImpl(this, namespaceURI, qualifiedName, localpart);
    }

    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return new DeepNodeListImpl(this, namespaceURI, localName);
    }

    public Object clone() throws CloneNotSupportedException {
        CoreDocumentImpl newdoc = (CoreDocumentImpl) super.clone();
        newdoc.docType = null;
        newdoc.docElement = null;
        return newdoc;
    }

    public static final boolean isXMLName(String s, boolean xml11Version) {
        if (s == null) {
            return false;
        }
        if (xml11Version) {
            return XML11Char.isXML11ValidName(s);
        }
        return XMLChar.isValidName(s);
    }

    public static final boolean isValidQName(String prefix, String local, boolean xml11Version) {
        if (local == null) {
            return false;
        }
        boolean validNCName;
        if (xml11Version) {
            if ((prefix == null || XML11Char.isXML11ValidNCName(prefix)) && XML11Char.isXML11ValidNCName(local)) {
                validNCName = true;
            } else {
                validNCName = false;
            }
        } else if ((prefix == null || XMLChar.isValidNCName(prefix)) && XMLChar.isValidNCName(local)) {
            validNCName = true;
        } else {
            validNCName = false;
        }
        return validNCName;
    }

    protected boolean isKidOK(Node parent, Node child) {
        if (this.allowGrammarAccess && parent.getNodeType() == (short) 10) {
            if (child.getNodeType() == (short) 1) {
                return true;
            }
            return false;
        } else if ((kidOK[parent.getNodeType()] & (1 << child.getNodeType())) == 0) {
            return false;
        } else {
            return true;
        }
    }

    protected void changed() {
        this.changes++;
    }

    protected int changes() {
        return this.changes;
    }

    NodeListCache getNodeListCache(ParentNode owner) {
        if (this.fFreeNLCache == null) {
            return new NodeListCache(owner);
        }
        NodeListCache c = this.fFreeNLCache;
        this.fFreeNLCache = this.fFreeNLCache.next;
        c.fChild = null;
        c.fChildIndex = -1;
        c.fLength = -1;
        if (c.fOwner != null) {
            c.fOwner.fNodeListCache = null;
        }
        c.fOwner = owner;
        return c;
    }

    void freeNodeListCache(NodeListCache c) {
        c.next = this.fFreeNLCache;
        this.fFreeNLCache = c;
    }

    public Object setUserData(Node n, String key, Object data, UserDataHandler handler) {
        Hashtable t;
        if (data != null) {
            if (this.userData == null) {
                this.userData = new WeakHashMap();
                t = new Hashtable();
                this.userData.put(n, t);
            } else {
                t = (Hashtable) this.userData.get(n);
                if (t == null) {
                    t = new Hashtable();
                    this.userData.put(n, t);
                }
            }
            Object o = t.put(key, new UserDataRecord(data, handler));
            if (o != null) {
                return ((UserDataRecord) o).fData;
            }
            return null;
        } else if (this.userData == null) {
            return null;
        } else {
            t = (Hashtable) this.userData.get(n);
            if (t == null) {
                return null;
            }
            UserDataRecord o2 = t.remove(key);
            if (o2 != null) {
                return o2.fData;
            }
            return null;
        }
    }

    public Object getUserData(Node n, String key) {
        if (this.userData == null) {
            return null;
        }
        Hashtable t = (Hashtable) this.userData.get(n);
        if (t == null) {
            return null;
        }
        UserDataRecord o = t.get(key);
        if (o != null) {
            return o.fData;
        }
        return null;
    }

    protected Hashtable getUserDataRecord(Node n) {
        if (this.userData == null) {
            return null;
        }
        Hashtable t = (Hashtable) this.userData.get(n);
        if (t == null) {
            return null;
        }
        return t;
    }

    Hashtable removeUserDataTable(Node n) {
        if (this.userData == null) {
            return null;
        }
        return (Hashtable) this.userData.get(n);
    }

    void setUserDataTable(Node n, Hashtable data) {
        if (this.userData == null) {
            this.userData = new WeakHashMap();
        }
        if (data != null) {
            this.userData.put(n, data);
        }
    }

    protected void callUserDataHandlers(Node n, Node c, short operation) {
        if (this.userData != null && (n instanceof NodeImpl)) {
            Hashtable t = ((NodeImpl) n).getUserDataRecord();
            if (t != null && !t.isEmpty()) {
                callUserDataHandlers(n, c, operation, t);
            }
        }
    }

    void callUserDataHandlers(Node n, Node c, short operation, Hashtable userData) {
        if (userData != null && !userData.isEmpty()) {
            for (Entry entry : userData.entrySet()) {
                String key = (String) entry.getKey();
                UserDataRecord r = (UserDataRecord) entry.getValue();
                if (r.fHandler != null) {
                    r.fHandler.handle(operation, key, r.fData, n, c);
                }
            }
        }
    }

    protected final void checkNamespaceWF(String qname, int colon1, int colon2) {
        if (!this.errorChecking) {
            return;
        }
        if (colon1 == 0 || colon1 == qname.length() - 1 || colon2 != colon1) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
    }

    protected final void checkDOMNSErr(String prefix, String namespace) {
        if (!this.errorChecking) {
            return;
        }
        if (namespace == null) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        } else if (prefix.equals("xml") && !namespace.equals(NamespaceContext.XML_URI)) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        } else if ((prefix.equals(XMLConstants.XMLNS_ATTRIBUTE) && !namespace.equals(NamespaceContext.XMLNS_URI)) || (!prefix.equals(XMLConstants.XMLNS_ATTRIBUTE) && namespace.equals(NamespaceContext.XMLNS_URI))) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
    }

    protected final void checkQName(String prefix, String local) {
        if (this.errorChecking) {
            boolean validNCName;
            if (this.xml11Version) {
                if ((prefix == null || XML11Char.isXML11ValidNCName(prefix)) && XML11Char.isXML11ValidNCName(local)) {
                    validNCName = true;
                } else {
                    validNCName = false;
                }
            } else if ((prefix == null || XMLChar.isValidNCName(prefix)) && XMLChar.isValidNCName(local)) {
                validNCName = true;
            } else {
                validNCName = false;
            }
            if (!validNCName) {
                throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
            }
        }
    }

    boolean isXML11Version() {
        return this.xml11Version;
    }

    boolean isNormalizeDocRequired() {
        return true;
    }

    boolean isXMLVersionChanged() {
        return this.xmlVersionChanged;
    }

    protected void setUserData(NodeImpl n, Object data) {
        setUserData(n, "XERCES1DOMUSERDATA", data, null);
    }

    protected Object getUserData(NodeImpl n) {
        return getUserData(n, "XERCES1DOMUSERDATA");
    }

    protected void addEventListener(NodeImpl node, String type, EventListener listener, boolean useCapture) {
    }

    protected void removeEventListener(NodeImpl node, String type, EventListener listener, boolean useCapture) {
    }

    protected void copyEventListeners(NodeImpl src, NodeImpl tgt) {
    }

    protected boolean dispatchEvent(NodeImpl node, Event event) {
        return false;
    }

    void replacedText(CharacterDataImpl node) {
    }

    void deletedText(CharacterDataImpl node, int offset, int count) {
    }

    void insertedText(CharacterDataImpl node, int offset, int count) {
    }

    void modifyingCharacterData(NodeImpl node, boolean replace) {
    }

    void modifiedCharacterData(NodeImpl node, String oldvalue, String value, boolean replace) {
    }

    void insertingNode(NodeImpl node, boolean replace) {
    }

    void insertedNode(NodeImpl node, NodeImpl newInternal, boolean replace) {
    }

    void removingNode(NodeImpl node, NodeImpl oldChild, boolean replace) {
    }

    void removedNode(NodeImpl node, boolean replace) {
    }

    void replacingNode(NodeImpl node) {
    }

    void replacedNode(NodeImpl node) {
    }

    void replacingData(NodeImpl node) {
    }

    void replacedCharacterData(NodeImpl node, String oldvalue, String value) {
    }

    void modifiedAttrValue(AttrImpl attr, String oldvalue) {
    }

    void setAttrNode(AttrImpl attr, AttrImpl previous) {
    }

    void removedAttrNode(AttrImpl attr, NodeImpl oldOwner, String name) {
    }

    void renamedAttrNode(Attr oldAt, Attr newAt) {
    }

    void renamedElement(Element oldEl, Element newEl) {
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (this.userData != null) {
            this.userData = new WeakHashMap(this.userData);
        }
        if (this.nodeTable != null) {
            this.nodeTable = new WeakHashMap(this.nodeTable);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        Map oldUserData = this.userData;
        Map oldNodeTable = this.nodeTable;
        if (oldUserData != null) {
            try {
                this.userData = new Hashtable(oldUserData);
            } catch (Throwable th) {
                this.userData = oldUserData;
                this.nodeTable = oldNodeTable;
            }
        }
        if (oldNodeTable != null) {
            this.nodeTable = new Hashtable(oldNodeTable);
        }
        out.defaultWriteObject();
        this.userData = oldUserData;
        this.nodeTable = oldNodeTable;
    }
}
