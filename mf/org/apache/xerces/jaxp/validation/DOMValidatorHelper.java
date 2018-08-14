package mf.org.apache.xerces.jaxp.validation;

import java.io.IOException;
import java.util.Enumeration;
import mf.javax.xml.parsers.DocumentBuilderFactory;
import mf.javax.xml.parsers.ParserConfigurationException;
import mf.javax.xml.transform.Result;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.dom.DOMResult;
import mf.javax.xml.transform.dom.DOMSource;
import mf.org.apache.xerces.dom.NodeImpl;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.validation.EntityState;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.impl.xs.util.SimpleLocator;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Entity;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;
import org.xml.sax.SAXException;

final class DOMValidatorHelper implements ValidatorHelper, EntityState {
    private static final int CHUNK_MASK = 1023;
    private static final int CHUNK_SIZE = 1024;
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    final QName fAttributeQName = new QName();
    final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
    private final char[] fCharBuffer = new char[1024];
    private final XMLSchemaValidatorComponentManager fComponentManager;
    private Node fCurrentElement;
    private final DOMNamespaceContext fDOMNamespaceContext = new DOMNamespaceContext();
    private final DOMResultAugmentor fDOMResultAugmentor = new DOMResultAugmentor(this);
    private final DOMResultBuilder fDOMResultBuilder = new DOMResultBuilder();
    private DOMDocumentHandler fDOMValidatorHandler;
    final QName fElementQName = new QName();
    private NamedNodeMap fEntities = null;
    private final XMLErrorReporter fErrorReporter;
    private final NamespaceSupport fNamespaceContext;
    private Node fRoot;
    private final XMLSchemaValidator fSchemaValidator;
    private final SymbolTable fSymbolTable;
    final XMLString fTempString = new XMLString();
    private final ValidationManager fValidationManager;
    private final SimpleLocator fXMLLocator = new SimpleLocator(null, null, -1, -1, -1);

    final class DOMNamespaceContext implements NamespaceContext {
        protected boolean fDOMContextBuilt = false;
        protected String[] fNamespace = new String[32];
        protected int fNamespaceSize = 0;

        DOMNamespaceContext() {
        }

        public void pushContext() {
            DOMValidatorHelper.this.fNamespaceContext.pushContext();
        }

        public void popContext() {
            DOMValidatorHelper.this.fNamespaceContext.popContext();
        }

        public boolean declarePrefix(String prefix, String uri) {
            return DOMValidatorHelper.this.fNamespaceContext.declarePrefix(prefix, uri);
        }

        public String getURI(String prefix) {
            String uri = DOMValidatorHelper.this.fNamespaceContext.getURI(prefix);
            if (uri != null) {
                return uri;
            }
            if (!this.fDOMContextBuilt) {
                fillNamespaceContext();
                this.fDOMContextBuilt = true;
            }
            if (this.fNamespaceSize <= 0 || DOMValidatorHelper.this.fNamespaceContext.containsPrefix(prefix)) {
                return uri;
            }
            return getURI0(prefix);
        }

        public String getPrefix(String uri) {
            return DOMValidatorHelper.this.fNamespaceContext.getPrefix(uri);
        }

        public int getDeclaredPrefixCount() {
            return DOMValidatorHelper.this.fNamespaceContext.getDeclaredPrefixCount();
        }

        public String getDeclaredPrefixAt(int index) {
            return DOMValidatorHelper.this.fNamespaceContext.getDeclaredPrefixAt(index);
        }

        public Enumeration getAllPrefixes() {
            return DOMValidatorHelper.this.fNamespaceContext.getAllPrefixes();
        }

        public void reset() {
            this.fDOMContextBuilt = false;
            this.fNamespaceSize = 0;
        }

        private void fillNamespaceContext() {
            if (DOMValidatorHelper.this.fRoot != null) {
                for (Node currentNode = DOMValidatorHelper.this.fRoot.getParentNode(); currentNode != null; currentNode = currentNode.getParentNode()) {
                    if ((short) 1 == currentNode.getNodeType()) {
                        NamedNodeMap attributes = currentNode.getAttributes();
                        int attrCount = attributes.getLength();
                        for (int i = 0; i < attrCount; i++) {
                            Attr attr = (Attr) attributes.item(i);
                            String value = attr.getValue();
                            if (value == null) {
                                value = XMLSymbols.EMPTY_STRING;
                            }
                            DOMValidatorHelper.this.fillQName(DOMValidatorHelper.this.fAttributeQName, attr);
                            if (DOMValidatorHelper.this.fAttributeQName.uri == NamespaceContext.XMLNS_URI) {
                                if (DOMValidatorHelper.this.fAttributeQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                                    String addSymbol;
                                    String str = DOMValidatorHelper.this.fAttributeQName.localpart;
                                    if (value.length() != 0) {
                                        addSymbol = DOMValidatorHelper.this.fSymbolTable.addSymbol(value);
                                    } else {
                                        addSymbol = null;
                                    }
                                    declarePrefix0(str, addSymbol);
                                } else {
                                    declarePrefix0(XMLSymbols.EMPTY_STRING, value.length() != 0 ? DOMValidatorHelper.this.fSymbolTable.addSymbol(value) : null);
                                }
                            }
                        }
                    }
                }
            }
        }

        private void declarePrefix0(String prefix, String uri) {
            if (this.fNamespaceSize == this.fNamespace.length) {
                String[] namespacearray = new String[(this.fNamespaceSize * 2)];
                System.arraycopy(this.fNamespace, 0, namespacearray, 0, this.fNamespaceSize);
                this.fNamespace = namespacearray;
            }
            String[] strArr = this.fNamespace;
            int i = this.fNamespaceSize;
            this.fNamespaceSize = i + 1;
            strArr[i] = prefix;
            strArr = this.fNamespace;
            i = this.fNamespaceSize;
            this.fNamespaceSize = i + 1;
            strArr[i] = uri;
        }

        private String getURI0(String prefix) {
            for (int i = 0; i < this.fNamespaceSize; i += 2) {
                if (this.fNamespace[i] == prefix) {
                    return this.fNamespace[i + 1];
                }
            }
            return null;
        }
    }

    public DOMValidatorHelper(XMLSchemaValidatorComponentManager componentManager) {
        this.fComponentManager = componentManager;
        this.fErrorReporter = (XMLErrorReporter) this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fNamespaceContext = (NamespaceSupport) this.fComponentManager.getProperty(NAMESPACE_CONTEXT);
        this.fSchemaValidator = (XMLSchemaValidator) this.fComponentManager.getProperty(SCHEMA_VALIDATOR);
        this.fSymbolTable = (SymbolTable) this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fValidationManager = (ValidationManager) this.fComponentManager.getProperty(VALIDATION_MANAGER);
    }

    public void validate(Source source, Result result) throws SAXException, IOException {
        if ((result instanceof DOMResult) || result == null) {
            DOMSource domSource = (DOMSource) source;
            DOMResult domResult = (DOMResult) result;
            Node node = domSource.getNode();
            this.fRoot = node;
            if (node != null) {
                this.fComponentManager.reset();
                this.fValidationManager.setEntityState(this);
                this.fDOMNamespaceContext.reset();
                String systemId = domSource.getSystemId();
                this.fXMLLocator.setLiteralSystemId(systemId);
                this.fXMLLocator.setExpandedSystemId(systemId);
                this.fErrorReporter.setDocumentLocator(this.fXMLLocator);
                try {
                    setupEntityMap(node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument());
                    setupDOMResultHandler(domSource, domResult);
                    this.fSchemaValidator.startDocument(this.fXMLLocator, null, this.fDOMNamespaceContext, null);
                    validate(node);
                    this.fSchemaValidator.endDocument(null);
                    this.fRoot = null;
                    this.fCurrentElement = null;
                    this.fEntities = null;
                    if (this.fDOMValidatorHandler != null) {
                        this.fDOMValidatorHandler.setDOMResult(null);
                        return;
                    }
                    return;
                } catch (XMLParseException e) {
                    throw Util.toSAXParseException(e);
                } catch (XNIException e2) {
                    throw Util.toSAXException(e2);
                } catch (Throwable th) {
                    this.fRoot = null;
                    this.fCurrentElement = null;
                    this.fEntities = null;
                    if (this.fDOMValidatorHandler != null) {
                        this.fDOMValidatorHandler.setDOMResult(null);
                    }
                }
            } else {
                return;
            }
        }
        throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[]{source.getClass().getName(), result.getClass().getName()}));
    }

    public boolean isEntityDeclared(String name) {
        return false;
    }

    public boolean isEntityUnparsed(String name) {
        if (this.fEntities == null) {
            return false;
        }
        Entity entity = (Entity) this.fEntities.getNamedItem(name);
        if (entity == null || entity.getNotationName() == null) {
            return false;
        }
        return true;
    }

    private void validate(Node node) {
        Node top = node;
        boolean useIsSameNode = useIsSameNode(top);
        while (node != null) {
            beginNode(node);
            Node next = node.getFirstChild();
            while (next == null) {
                finishNode(node);
                if (top == node) {
                    break;
                }
                next = node.getNextSibling();
                if (next == null) {
                    node = node.getParentNode();
                    if (node != null) {
                        if (useIsSameNode) {
                            if (top.isSameNode(node)) {
                            }
                        } else if (top == node) {
                        }
                    }
                    if (node != null) {
                        finishNode(node);
                    }
                    next = null;
                }
            }
            node = next;
        }
    }

    private void beginNode(Node node) {
        switch (node.getNodeType()) {
            case (short) 1:
                this.fCurrentElement = node;
                this.fNamespaceContext.pushContext();
                fillQName(this.fElementQName, node);
                processAttributes(node.getAttributes());
                this.fSchemaValidator.startElement(this.fElementQName, this.fAttributes, null);
                return;
            case (short) 3:
                if (this.fDOMValidatorHandler != null) {
                    this.fDOMValidatorHandler.setIgnoringCharacters(true);
                    sendCharactersToValidator(node.getNodeValue());
                    this.fDOMValidatorHandler.setIgnoringCharacters(false);
                    this.fDOMValidatorHandler.characters((Text) node);
                    return;
                }
                sendCharactersToValidator(node.getNodeValue());
                return;
            case (short) 4:
                if (this.fDOMValidatorHandler != null) {
                    this.fDOMValidatorHandler.setIgnoringCharacters(true);
                    this.fSchemaValidator.startCDATA(null);
                    sendCharactersToValidator(node.getNodeValue());
                    this.fSchemaValidator.endCDATA(null);
                    this.fDOMValidatorHandler.setIgnoringCharacters(false);
                    this.fDOMValidatorHandler.cdata((CDATASection) node);
                    return;
                }
                this.fSchemaValidator.startCDATA(null);
                sendCharactersToValidator(node.getNodeValue());
                this.fSchemaValidator.endCDATA(null);
                return;
            case (short) 7:
                if (this.fDOMValidatorHandler != null) {
                    this.fDOMValidatorHandler.processingInstruction((ProcessingInstruction) node);
                    return;
                }
                return;
            case (short) 8:
                if (this.fDOMValidatorHandler != null) {
                    this.fDOMValidatorHandler.comment((Comment) node);
                    return;
                }
                return;
            case (short) 10:
                if (this.fDOMValidatorHandler != null) {
                    this.fDOMValidatorHandler.doctypeDecl((DocumentType) node);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void finishNode(Node node) {
        if (node.getNodeType() == (short) 1) {
            this.fCurrentElement = node;
            fillQName(this.fElementQName, node);
            this.fSchemaValidator.endElement(this.fElementQName, null);
            this.fNamespaceContext.popContext();
        }
    }

    private void setupEntityMap(Document doc) {
        if (doc != null) {
            DocumentType docType = doc.getDoctype();
            if (docType != null) {
                this.fEntities = docType.getEntities();
                return;
            }
        }
        this.fEntities = null;
    }

    private void setupDOMResultHandler(DOMSource source, DOMResult result) throws SAXException {
        if (result == null) {
            this.fDOMValidatorHandler = null;
            this.fSchemaValidator.setDocumentHandler(null);
            return;
        }
        if (source.getNode() == result.getNode()) {
            this.fDOMValidatorHandler = this.fDOMResultAugmentor;
            this.fDOMResultAugmentor.setDOMResult(result);
            this.fSchemaValidator.setDocumentHandler(this.fDOMResultAugmentor);
            return;
        }
        if (result.getNode() == null) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                result.setNode(factory.newDocumentBuilder().newDocument());
            } catch (ParserConfigurationException e) {
                throw new SAXException(e);
            }
        }
        this.fDOMValidatorHandler = this.fDOMResultBuilder;
        this.fDOMResultBuilder.setDOMResult(result);
        this.fSchemaValidator.setDocumentHandler(this.fDOMResultBuilder);
    }

    private void fillQName(QName toFill, Node node) {
        String prefix = node.getPrefix();
        String localName = node.getLocalName();
        String rawName = node.getNodeName();
        String namespace = node.getNamespaceURI();
        toFill.prefix = prefix != null ? this.fSymbolTable.addSymbol(prefix) : XMLSymbols.EMPTY_STRING;
        toFill.localpart = localName != null ? this.fSymbolTable.addSymbol(localName) : XMLSymbols.EMPTY_STRING;
        toFill.rawname = rawName != null ? this.fSymbolTable.addSymbol(rawName) : XMLSymbols.EMPTY_STRING;
        String addSymbol = (namespace == null || namespace.length() <= 0) ? null : this.fSymbolTable.addSymbol(namespace);
        toFill.uri = addSymbol;
    }

    private void processAttributes(NamedNodeMap attrMap) {
        int attrCount = attrMap.getLength();
        this.fAttributes.removeAllAttributes();
        for (int i = 0; i < attrCount; i++) {
            Attr attr = (Attr) attrMap.item(i);
            String value = attr.getValue();
            if (value == null) {
                value = XMLSymbols.EMPTY_STRING;
            }
            fillQName(this.fAttributeQName, attr);
            this.fAttributes.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, value);
            this.fAttributes.setSpecified(i, attr.getSpecified());
            if (this.fAttributeQName.uri == NamespaceContext.XMLNS_URI) {
                if (this.fAttributeQName.prefix == XMLSymbols.PREFIX_XMLNS) {
                    String addSymbol;
                    NamespaceSupport namespaceSupport = this.fNamespaceContext;
                    String str = this.fAttributeQName.localpart;
                    if (value.length() != 0) {
                        addSymbol = this.fSymbolTable.addSymbol(value);
                    } else {
                        addSymbol = null;
                    }
                    namespaceSupport.declarePrefix(str, addSymbol);
                } else {
                    this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, value.length() != 0 ? this.fSymbolTable.addSymbol(value) : null);
                }
            }
        }
    }

    private void sendCharactersToValidator(String str) {
        if (str != null) {
            int length = str.length();
            int remainder = length & CHUNK_MASK;
            if (remainder > 0) {
                str.getChars(0, remainder, this.fCharBuffer, 0);
                this.fTempString.setValues(this.fCharBuffer, 0, remainder);
                this.fSchemaValidator.characters(this.fTempString, null);
            }
            int i = remainder;
            while (i < length) {
                int i2 = i + 1024;
                str.getChars(i, i2, this.fCharBuffer, 0);
                this.fTempString.setValues(this.fCharBuffer, 0, 1024);
                this.fSchemaValidator.characters(this.fTempString, null);
                i = i2;
            }
        }
    }

    private boolean useIsSameNode(Node node) {
        if (node instanceof NodeImpl) {
            return false;
        }
        Document doc = node.getNodeType() == (short) 9 ? (Document) node : node.getOwnerDocument();
        if (doc == null || !doc.getImplementation().hasFeature("Core", "3.0")) {
            return false;
        }
        return true;
    }

    Node getCurrentElement() {
        return this.fCurrentElement;
    }
}
