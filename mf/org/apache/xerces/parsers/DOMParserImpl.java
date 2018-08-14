package mf.org.apache.xerces.parsers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.DOMStringListImpl;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.jaxp.JAXPConstants;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.DOMErrorHandlerWrapper;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import mf.org.apache.xerces.xni.parser.XMLDTDSource;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMStringList;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ls.LSException;
import mf.org.w3c.dom.ls.LSInput;
import mf.org.w3c.dom.ls.LSParser;
import mf.org.w3c.dom.ls.LSParserFilter;
import mf.org.w3c.dom.ls.LSResourceResolver;

public class DOMParserImpl extends AbstractDOMParser implements LSParser, DOMConfiguration {
    protected static final boolean DEBUG = false;
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String PSVI_AUGMENT = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected static final String XMLSCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private AbortHandler abortHandler;
    private boolean abortNow;
    private Thread currentThread;
    protected boolean fBusy;
    protected boolean fNamespaceDeclarations;
    private boolean fNullFilterInUse;
    private DOMStringList fRecognizedParameters;
    private String fSchemaLocation;
    protected String fSchemaType;

    private static final class AbortHandler implements XMLDocumentHandler, XMLDTDHandler, XMLDTDContentModelHandler {
        private XMLDocumentSource documentSource;
        private XMLDTDContentModelSource dtdContentSource;
        private XMLDTDSource dtdSource;

        private AbortHandler() {
        }

        public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void comment(XMLString text, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void characters(XMLString text, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endElement(QName element, Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startCDATA(Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endCDATA(Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endDocument(Augmentations augs) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void setDocumentSource(XMLDocumentSource source) {
            this.documentSource = source;
        }

        public XMLDocumentSource getDocumentSource() {
            return this.documentSource;
        }

        public void startDTD(XMLLocator locator, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endParameterEntity(String name, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endExternalSubset(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void elementDecl(String name, String contentModel, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startAttlist(String elementName, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endAttlist(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startConditional(short type, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void ignoredCharacters(XMLString text, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endConditional(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endDTD(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void setDTDSource(XMLDTDSource source) {
            this.dtdSource = source;
        }

        public XMLDTDSource getDTDSource() {
            return this.dtdSource;
        }

        public void startContentModel(String elementName, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void any(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void empty(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void startGroup(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void pcdata(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void element(String elementName, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void separator(short separator, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void occurrence(short occurrence, Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endGroup(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void endContentModel(Augmentations augmentations) throws XNIException {
            throw Abort.INSTANCE;
        }

        public void setDTDContentModelSource(XMLDTDContentModelSource source) {
            this.dtdContentSource = source;
        }

        public XMLDTDContentModelSource getDTDContentModelSource() {
            return this.dtdContentSource;
        }
    }

    static final class NullLSParserFilter implements LSParserFilter {
        static final NullLSParserFilter INSTANCE = new NullLSParserFilter();

        private NullLSParserFilter() {
        }

        public short acceptNode(Node nodeArg) {
            return (short) 1;
        }

        public int getWhatToShow() {
            return -1;
        }

        public short startElement(Element elementArg) {
            return (short) 1;
        }
    }

    public DOMParserImpl(String configuration, String schemaType) {
        this((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", configuration));
        if (schemaType == null) {
            return;
        }
        if (schemaType.equals(Constants.NS_DTD)) {
            this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, Constants.NS_DTD);
            this.fSchemaType = Constants.NS_DTD;
        } else if (schemaType.equals(Constants.NS_XMLSCHEMA)) {
            this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, Constants.NS_XMLSCHEMA);
        }
    }

    public DOMParserImpl(XMLParserConfiguration config) {
        super(config);
        this.fNamespaceDeclarations = true;
        this.fSchemaType = null;
        this.fBusy = false;
        this.abortNow = false;
        this.fSchemaLocation = null;
        this.fNullFilterInUse = false;
        this.abortHandler = null;
        this.fConfiguration.addRecognizedFeatures(new String[]{Constants.DOM_CANONICAL_FORM, Constants.DOM_CDATA_SECTIONS, Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING, Constants.DOM_INFOSET, Constants.DOM_NAMESPACE_DECLARATIONS, Constants.DOM_SPLIT_CDATA, Constants.DOM_SUPPORTED_MEDIATYPES_ONLY, Constants.DOM_CERTIFIED, Constants.DOM_WELLFORMED, Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS});
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        this.fConfiguration.setFeature(Constants.DOM_NAMESPACE_DECLARATIONS, true);
        this.fConfiguration.setFeature(Constants.DOM_WELLFORMED, true);
        this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
        this.fConfiguration.setFeature(NAMESPACES, true);
        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
        this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
        this.fConfiguration.setFeature(Constants.DOM_CANONICAL_FORM, false);
        this.fConfiguration.setFeature(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING, true);
        this.fConfiguration.setFeature(Constants.DOM_SPLIT_CDATA, true);
        this.fConfiguration.setFeature(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY, false);
        this.fConfiguration.setFeature(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS, true);
        this.fConfiguration.setFeature(Constants.DOM_CERTIFIED, true);
        try {
            this.fConfiguration.setFeature(NORMALIZE_DATA, false);
        } catch (XMLConfigurationException e) {
        }
    }

    public DOMParserImpl(SymbolTable symbolTable) {
        this((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    public DOMParserImpl(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
    }

    public void reset() {
        super.reset();
        this.fNamespaceDeclarations = this.fConfiguration.getFeature(Constants.DOM_NAMESPACE_DECLARATIONS);
        if (this.fNullFilterInUse) {
            this.fDOMFilter = null;
            this.fNullFilterInUse = false;
        }
        if (this.fSkippedElemStack != null) {
            this.fSkippedElemStack.removeAllElements();
        }
        this.fRejectedElementDepth = 0;
        this.fFilterReject = false;
        this.fSchemaType = null;
    }

    public DOMConfiguration getDomConfig() {
        return this;
    }

    public LSParserFilter getFilter() {
        return !this.fNullFilterInUse ? this.fDOMFilter : null;
    }

    public void setFilter(LSParserFilter filter) {
        if (this.fBusy && filter == null && this.fDOMFilter != null) {
            this.fNullFilterInUse = true;
            this.fDOMFilter = NullLSParserFilter.INSTANCE;
        } else {
            this.fDOMFilter = filter;
        }
        if (this.fSkippedElemStack == null) {
            this.fSkippedElemStack = new Stack();
        }
    }

    public void setParameter(String name, Object value) throws DOMException {
        String normalizedName;
        if (value instanceof Boolean) {
            boolean state = ((Boolean) value).booleanValue();
            try {
                if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
                    this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", state);
                } else if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
                    this.fConfiguration.setFeature(NORMALIZE_DATA, state);
                } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                    this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", state);
                } else if (name.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
                    this.fConfiguration.setFeature(DISALLOW_DOCTYPE_DECL_FEATURE, state);
                } else if (name.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM)) {
                    if (state) {
                        throw newFeatureNotSupportedError(name);
                    }
                } else if (name.equalsIgnoreCase("namespaces")) {
                    this.fConfiguration.setFeature(NAMESPACES, state);
                } else if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                    if (state) {
                        this.fConfiguration.setFeature(NAMESPACES, true);
                        this.fConfiguration.setFeature(Constants.DOM_NAMESPACE_DECLARATIONS, true);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/include-comments", true);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", true);
                        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
                        this.fConfiguration.setFeature(NORMALIZE_DATA, false);
                        this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", false);
                    }
                } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                    this.fConfiguration.setFeature("http://apache.org/xml/features/create-cdata-nodes", state);
                } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                    this.fConfiguration.setFeature(Constants.DOM_NAMESPACE_DECLARATIONS, state);
                } else if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                    if (!state) {
                        throw newFeatureNotSupportedError(name);
                    }
                } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
                    this.fConfiguration.setFeature(VALIDATION_FEATURE, state);
                    if (this.fSchemaType != Constants.NS_DTD) {
                        this.fConfiguration.setFeature(XMLSCHEMA, state);
                        this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, state);
                    }
                    if (state) {
                        this.fConfiguration.setFeature(DYNAMIC_VALIDATION, false);
                    }
                } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA)) {
                    this.fConfiguration.setFeature(DYNAMIC_VALIDATION, state);
                    if (state) {
                        this.fConfiguration.setFeature(VALIDATION_FEATURE, false);
                    }
                } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
                    this.fConfiguration.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", state);
                } else if (name.equalsIgnoreCase(Constants.DOM_PSVI)) {
                    this.fConfiguration.setFeature(PSVI_AUGMENT, true);
                    this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", "mf.org.apache.xerces.dom.PSVIDocumentImpl");
                } else {
                    if (name.equalsIgnoreCase(HONOUR_ALL_SCHEMALOCATIONS)) {
                        normalizedName = HONOUR_ALL_SCHEMALOCATIONS;
                    } else if (name.equals(NAMESPACE_GROWTH)) {
                        normalizedName = NAMESPACE_GROWTH;
                    } else if (name.equals(TOLERATE_DUPLICATES)) {
                        normalizedName = TOLERATE_DUPLICATES;
                    } else {
                        normalizedName = name.toLowerCase(Locale.ENGLISH);
                    }
                    this.fConfiguration.setFeature(normalizedName, state);
                }
            } catch (XMLConfigurationException e) {
                throw newFeatureNotFoundError(name);
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
            if ((value instanceof DOMErrorHandler) || value == null) {
                try {
                    this.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler) value);
                    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fErrorHandler);
                    return;
                } catch (XMLConfigurationException e2) {
                    return;
                }
            }
            throw newTypeMismatchError(name);
        } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            if ((value instanceof LSResourceResolver) || value == null) {
                try {
                    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper((LSResourceResolver) value));
                    return;
                } catch (XMLConfigurationException e3) {
                    return;
                }
            }
            throw newTypeMismatchError(name);
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
            if (!(value instanceof String) && value != null) {
                throw newTypeMismatchError(name);
            } else if (value == null) {
                try {
                    this.fSchemaLocation = null;
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, null);
                } catch (XMLConfigurationException e4) {
                }
            } else {
                this.fSchemaLocation = (String) value;
                StringTokenizer t = new StringTokenizer(this.fSchemaLocation, " \n\t\r");
                if (t.hasMoreTokens()) {
                    ArrayList locations = new ArrayList();
                    locations.add(t.nextToken());
                    while (t.hasMoreTokens()) {
                        locations.add(t.nextToken());
                    }
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, locations.toArray());
                    return;
                }
                this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_SOURCE, value);
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
            if (!(value instanceof String) && value != null) {
                throw newTypeMismatchError(name);
            } else if (value == null) {
                try {
                    this.fConfiguration.setFeature(XMLSCHEMA, false);
                    this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, false);
                    this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, null);
                    this.fSchemaType = null;
                } catch (XMLConfigurationException e5) {
                }
            } else if (value.equals(Constants.NS_XMLSCHEMA)) {
                this.fConfiguration.setFeature(XMLSCHEMA, true);
                this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, true);
                this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, Constants.NS_XMLSCHEMA);
                this.fSchemaType = Constants.NS_XMLSCHEMA;
            } else if (value.equals(Constants.NS_DTD)) {
                this.fConfiguration.setFeature(XMLSCHEMA, false);
                this.fConfiguration.setFeature(XMLSCHEMA_FULL_CHECKING, false);
                this.fConfiguration.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, Constants.NS_DTD);
                this.fSchemaType = Constants.NS_DTD;
            }
        } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/dom/document-class-name", value);
        } else {
            normalizedName = name.toLowerCase(Locale.ENGLISH);
            try {
                this.fConfiguration.setProperty(normalizedName, value);
            } catch (XMLConfigurationException e6) {
                if (name.equalsIgnoreCase(HONOUR_ALL_SCHEMALOCATIONS)) {
                    normalizedName = HONOUR_ALL_SCHEMALOCATIONS;
                } else if (name.equals(NAMESPACE_GROWTH)) {
                    normalizedName = NAMESPACE_GROWTH;
                } else if (name.equals(TOLERATE_DUPLICATES)) {
                    normalizedName = TOLERATE_DUPLICATES;
                }
                this.fConfiguration.getFeature(normalizedName);
                throw newTypeMismatchError(name);
            } catch (XMLConfigurationException e7) {
                throw newFeatureNotFoundError(name);
            }
        }
    }

    public Object getParameter(String name) throws DOMException {
        if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
            if (this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments")) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
            if (this.fConfiguration.getFeature(NORMALIZE_DATA)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
            if (this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes")) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase("namespaces")) {
            if (this.fConfiguration.getFeature(NAMESPACES)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
            if (this.fConfiguration.getFeature(VALIDATION_FEATURE)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA)) {
            if (this.fConfiguration.getFeature(DYNAMIC_VALIDATION)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
            if (this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace")) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE)) {
            if (this.fConfiguration.getFeature(DISALLOW_DOCTYPE_DECL_FEATURE)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
            boolean infoset = this.fConfiguration.getFeature(NAMESPACES) && this.fConfiguration.getFeature(Constants.DOM_NAMESPACE_DECLARATIONS) && this.fConfiguration.getFeature("http://apache.org/xml/features/include-comments") && this.fConfiguration.getFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace") && !this.fConfiguration.getFeature(DYNAMIC_VALIDATION) && !this.fConfiguration.getFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes") && !this.fConfiguration.getFeature(NORMALIZE_DATA) && !this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes");
            if (infoset) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
            return this.fConfiguration.getFeature("http://apache.org/xml/features/create-cdata-nodes") ? Boolean.TRUE : Boolean.FALSE;
        } else {
            if (name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS)) {
                return Boolean.FALSE;
            }
            if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS) || name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA) || name.equalsIgnoreCase(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING)) {
                if (this.fConfiguration.getFeature(name.toLowerCase(Locale.ENGLISH))) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
                if (this.fErrorHandler != null) {
                    return this.fErrorHandler.getErrorHandler();
                }
                return null;
            } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                try {
                    XMLEntityResolver entityResolver = (XMLEntityResolver) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
                    if (entityResolver == null || !(entityResolver instanceof DOMEntityResolverWrapper)) {
                        return null;
                    }
                    return ((DOMEntityResolverWrapper) entityResolver).getEntityResolver();
                } catch (XMLConfigurationException e) {
                    return null;
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
                return this.fConfiguration.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
            } else {
                if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
                    return this.fSchemaLocation;
                }
                if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                    return this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/symbol-table");
                }
                if (name.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
                    return this.fConfiguration.getProperty("http://apache.org/xml/properties/dom/document-class-name");
                }
                String normalizedName;
                if (name.equalsIgnoreCase(HONOUR_ALL_SCHEMALOCATIONS)) {
                    normalizedName = HONOUR_ALL_SCHEMALOCATIONS;
                } else if (name.equals(NAMESPACE_GROWTH)) {
                    normalizedName = NAMESPACE_GROWTH;
                } else if (name.equals(TOLERATE_DUPLICATES)) {
                    normalizedName = TOLERATE_DUPLICATES;
                } else {
                    normalizedName = name.toLowerCase(Locale.ENGLISH);
                }
                try {
                    return this.fConfiguration.getFeature(normalizedName) ? Boolean.TRUE : Boolean.FALSE;
                } catch (XMLConfigurationException e2) {
                    try {
                        return this.fConfiguration.getProperty(normalizedName);
                    } catch (XMLConfigurationException e3) {
                        throw newFeatureNotFoundError(name);
                    }
                }
            }
        }
    }

    public boolean canSetParameter(String name, Object value) {
        boolean z = false;
        if (value == null) {
            return true;
        }
        if (value instanceof Boolean) {
            boolean state = ((Boolean) value).booleanValue();
            if (name.equalsIgnoreCase(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY) || name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM)) {
                if (!state) {
                    z = true;
                }
                return z;
            } else if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS)) {
                if (state) {
                    return true;
                }
                return false;
            } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS) || name.equalsIgnoreCase(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING) || name.equalsIgnoreCase(Constants.DOM_COMMENTS) || name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_DISALLOW_DOCTYPE) || name.equalsIgnoreCase(Constants.DOM_ENTITIES) || name.equalsIgnoreCase(Constants.DOM_INFOSET) || name.equalsIgnoreCase("namespaces") || name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS) || name.equalsIgnoreCase(Constants.DOM_VALIDATE) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE) || name.equalsIgnoreCase(Constants.DOM_XMLDECL)) {
                return true;
            } else {
                try {
                    String normalizedName;
                    if (name.equalsIgnoreCase(HONOUR_ALL_SCHEMALOCATIONS)) {
                        normalizedName = HONOUR_ALL_SCHEMALOCATIONS;
                    } else if (name.equalsIgnoreCase(NAMESPACE_GROWTH)) {
                        normalizedName = NAMESPACE_GROWTH;
                    } else if (name.equalsIgnoreCase(TOLERATE_DUPLICATES)) {
                        normalizedName = TOLERATE_DUPLICATES;
                    } else {
                        normalizedName = name.toLowerCase(Locale.ENGLISH);
                    }
                    this.fConfiguration.getFeature(normalizedName);
                    return true;
                } catch (XMLConfigurationException e) {
                    return false;
                }
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
            if ((value instanceof DOMErrorHandler) || value == null) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            if ((value instanceof LSResourceResolver) || value == null) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
            if (((value instanceof String) && (value.equals(Constants.NS_XMLSCHEMA) || value.equals(Constants.NS_DTD))) || value == null) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
            if ((value instanceof String) || value == null) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/dom/document-class-name")) {
            return true;
        } else {
            try {
                this.fConfiguration.getProperty(name.toLowerCase(Locale.ENGLISH));
                return true;
            } catch (XMLConfigurationException e2) {
                return false;
            }
        }
    }

    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList parameters = new ArrayList();
            parameters.add("namespaces");
            parameters.add(Constants.DOM_CDATA_SECTIONS);
            parameters.add(Constants.DOM_CANONICAL_FORM);
            parameters.add(Constants.DOM_NAMESPACE_DECLARATIONS);
            parameters.add(Constants.DOM_SPLIT_CDATA);
            parameters.add(Constants.DOM_ENTITIES);
            parameters.add(Constants.DOM_VALIDATE_IF_SCHEMA);
            parameters.add(Constants.DOM_VALIDATE);
            parameters.add(Constants.DOM_DATATYPE_NORMALIZATION);
            parameters.add(Constants.DOM_CHARSET_OVERRIDES_XML_ENCODING);
            parameters.add(Constants.DOM_CHECK_CHAR_NORMALIZATION);
            parameters.add(Constants.DOM_SUPPORTED_MEDIATYPES_ONLY);
            parameters.add(Constants.DOM_IGNORE_UNKNOWN_CHARACTER_DENORMALIZATIONS);
            parameters.add(Constants.DOM_NORMALIZE_CHARACTERS);
            parameters.add(Constants.DOM_WELLFORMED);
            parameters.add(Constants.DOM_INFOSET);
            parameters.add(Constants.DOM_DISALLOW_DOCTYPE);
            parameters.add(Constants.DOM_ELEMENT_CONTENT_WHITESPACE);
            parameters.add(Constants.DOM_COMMENTS);
            parameters.add(Constants.DOM_ERROR_HANDLER);
            parameters.add(Constants.DOM_RESOURCE_RESOLVER);
            parameters.add(Constants.DOM_SCHEMA_LOCATION);
            parameters.add(Constants.DOM_SCHEMA_TYPE);
            this.fRecognizedParameters = new DOMStringListImpl(parameters);
        }
        return this.fRecognizedParameters;
    }

    public Document parseURI(String uri) throws LSException {
        if (this.fBusy) {
            throw newInvalidStateError();
        }
        XMLInputSource source = new XMLInputSource(null, uri, null);
        try {
            this.currentThread = Thread.currentThread();
            this.fBusy = true;
            parse(source);
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                this.abortNow = false;
                Thread.interrupted();
            }
        } catch (Exception e) {
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                Thread.interrupted();
            }
            if (this.abortNow) {
                this.abortNow = false;
                restoreHandlers();
                return null;
            } else if (e != Abort.INSTANCE) {
                if (!((e instanceof XMLParseException) || this.fErrorHandler == null)) {
                    DOMErrorImpl error = new DOMErrorImpl();
                    error.fException = e;
                    error.fMessage = e.getMessage();
                    error.fSeverity = (short) 3;
                    this.fErrorHandler.getErrorHandler().handleError(error);
                }
                throw ((LSException) DOMUtil.createLSException((short) 81, e).fillInStackTrace());
            }
        }
        Document doc = getDocument();
        dropDocumentReferences();
        return doc;
    }

    public Document parse(LSInput is) throws LSException {
        XMLInputSource xmlInputSource = dom2xmlInputSource(is);
        if (this.fBusy) {
            throw newInvalidStateError();
        }
        try {
            this.currentThread = Thread.currentThread();
            this.fBusy = true;
            parse(xmlInputSource);
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                this.abortNow = false;
                Thread.interrupted();
            }
        } catch (Exception e) {
            this.fBusy = false;
            if (this.abortNow && this.currentThread.isInterrupted()) {
                Thread.interrupted();
            }
            if (this.abortNow) {
                this.abortNow = false;
                restoreHandlers();
                return null;
            } else if (e != Abort.INSTANCE) {
                if (!((e instanceof XMLParseException) || this.fErrorHandler == null)) {
                    DOMErrorImpl error = new DOMErrorImpl();
                    error.fException = e;
                    error.fMessage = e.getMessage();
                    error.fSeverity = (short) 3;
                    this.fErrorHandler.getErrorHandler().handleError(error);
                }
                throw ((LSException) DOMUtil.createLSException((short) 81, e).fillInStackTrace());
            }
        }
        Document doc = getDocument();
        dropDocumentReferences();
        return doc;
    }

    private void restoreHandlers() {
        this.fConfiguration.setDocumentHandler(this);
        this.fConfiguration.setDTDHandler(this);
        this.fConfiguration.setDTDContentModelHandler(this);
    }

    public Node parseWithContext(LSInput is, Node cnode, short action) throws DOMException, LSException {
        throw new DOMException((short) 9, "Not supported");
    }

    XMLInputSource dom2xmlInputSource(LSInput is) {
        if (is.getCharacterStream() != null) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getCharacterStream(), "UTF-16");
        }
        if (is.getByteStream() != null) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getByteStream(), is.getEncoding());
        }
        if (is.getStringData() != null && is.getStringData().length() > 0) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), new StringReader(is.getStringData()), "UTF-16");
        }
        if ((is.getSystemId() != null && is.getSystemId().length() > 0) || (is.getPublicId() != null && is.getPublicId().length() > 0)) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI());
        }
        if (this.fErrorHandler != null) {
            DOMErrorImpl error = new DOMErrorImpl();
            error.fType = "no-input-specified";
            error.fMessage = "no-input-specified";
            error.fSeverity = (short) 3;
            this.fErrorHandler.getErrorHandler().handleError(error);
        }
        throw new LSException((short) 81, "no-input-specified");
    }

    public boolean getAsync() {
        return false;
    }

    public boolean getBusy() {
        return this.fBusy;
    }

    public void abort() {
        if (this.fBusy) {
            this.fBusy = false;
            if (this.currentThread != null) {
                this.abortNow = true;
                if (this.abortHandler == null) {
                    this.abortHandler = new AbortHandler();
                }
                this.fConfiguration.setDocumentHandler(this.abortHandler);
                this.fConfiguration.setDTDHandler(this.abortHandler);
                this.fConfiguration.setDTDContentModelHandler(this.abortHandler);
                if (this.currentThread == Thread.currentThread()) {
                    throw Abort.INSTANCE;
                }
                this.currentThread.interrupt();
            }
        }
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) {
        if (!this.fNamespaceDeclarations && this.fNamespaceAware) {
            int i = attributes.getLength() - 1;
            while (i >= 0) {
                if (XMLSymbols.PREFIX_XMLNS == attributes.getPrefix(i) || XMLSymbols.PREFIX_XMLNS == attributes.getQName(i)) {
                    attributes.removeAttributeAt(i);
                }
                i--;
            }
        }
        super.startElement(element, attributes, augs);
    }

    private static DOMException newInvalidStateError() {
        throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
    }

    private static DOMException newFeatureNotSupportedError(String name) {
        return new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
    }

    private static DOMException newFeatureNotFoundError(String name) {
        return new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{name}));
    }

    private static DOMException newTypeMismatchError(String name) {
        return new DOMException((short) 17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "TYPE_MISMATCH_ERR", new Object[]{name}));
    }
}
