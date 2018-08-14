package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.DOMErrorHandlerWrapper;
import mf.org.apache.xerces.util.MessageFormatter;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMStringList;
import mf.org.w3c.dom.ls.LSResourceResolver;

public class DOMConfigurationImpl extends ParserConfigurationSettings implements XMLParserConfiguration, DOMConfiguration {
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    protected static final short CDATA = (short) 8;
    protected static final short COMMENTS = (short) 32;
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DTD_VALIDATOR_FACTORY_PROPERTY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final short DTNORMALIZATION = (short) 2;
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final short ENTITIES = (short) 4;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final short INFOSET_FALSE_PARAMS = (short) 14;
    protected static final short INFOSET_MASK = (short) 815;
    protected static final short INFOSET_TRUE_PARAMS = (short) 801;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final short NAMESPACES = (short) 1;
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final short NSDECL = (short) 512;
    protected static final short PSVI = (short) 128;
    protected static final String SCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String SEND_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final short SPLITCDATA = (short) 16;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final short VALIDATE = (short) 64;
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final short WELLFORMED = (short) 256;
    protected static final String XERCES_NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String XERCES_VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "mf.org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected ArrayList fComponents;
    protected DTDDVFactory fCurrentDVFactory;
    protected DTDDVFactory fDatatypeValidatorFactory;
    XMLDocumentHandler fDocumentHandler;
    protected final DOMErrorHandlerWrapper fErrorHandlerWrapper;
    protected XMLErrorReporter fErrorReporter;
    protected Locale fLocale;
    private DOMStringList fRecognizedParameters;
    private String fSchemaLocation;
    protected SymbolTable fSymbolTable;
    protected ValidationManager fValidationManager;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected short features;

    protected DOMConfigurationImpl() {
        this(null, null);
    }

    protected DOMConfigurationImpl(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    protected DOMConfigurationImpl(SymbolTable symbolTable, XMLComponentManager parentSettings) {
        super(parentSettings);
        this.features = (short) 0;
        this.fErrorHandlerWrapper = new DOMErrorHandlerWrapper();
        this.fSchemaLocation = null;
        this.fRecognizedFeatures = new ArrayList();
        this.fRecognizedProperties = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{XERCES_VALIDATION, XERCES_NAMESPACES, SCHEMA, SCHEMA_FULL_CHECKING, DYNAMIC_VALIDATION, NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SEND_PSVI, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, USE_GRAMMAR_POOL_ONLY, DISALLOW_DOCTYPE_DECL_FEATURE, BALANCE_SYNTAX_TREES, WARN_ON_DUPLICATE_ATTDEF, "http://apache.org/xml/features/internal/parser-settings", NAMESPACE_GROWTH, TOLERATE_DUPLICATES});
        setFeature(XERCES_VALIDATION, false);
        setFeature(SCHEMA, false);
        setFeature(SCHEMA_FULL_CHECKING, false);
        setFeature(DYNAMIC_VALIDATION, false);
        setFeature(NORMALIZE_DATA, false);
        setFeature(SCHEMA_ELEMENT_DEFAULT, false);
        setFeature(XERCES_NAMESPACES, true);
        setFeature(SEND_PSVI, true);
        setFeature("http://apache.org/xml/features/generate-synthetic-annotations", false);
        setFeature(VALIDATE_ANNOTATIONS, false);
        setFeature(HONOUR_ALL_SCHEMALOCATIONS, false);
        setFeature(USE_GRAMMAR_POOL_ONLY, false);
        setFeature(DISALLOW_DOCTYPE_DECL_FEATURE, false);
        setFeature(BALANCE_SYNTAX_TREES, false);
        setFeature(WARN_ON_DUPLICATE_ATTDEF, false);
        setFeature("http://apache.org/xml/features/internal/parser-settings", true);
        setFeature(NAMESPACE_GROWTH, false);
        setFeature(TOLERATE_DUPLICATES, false);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, VALIDATION_MANAGER, "http://apache.org/xml/properties/internal/grammar-pool", SECURITY_MANAGER, "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, DTD_VALIDATOR_PROPERTY, DTD_VALIDATOR_FACTORY_PROPERTY, SCHEMA_DV_FACTORY});
        this.features = (short) (this.features | 1);
        this.features = (short) (this.features | 4);
        this.features = (short) (this.features | 32);
        this.features = (short) (this.features | 8);
        this.features = (short) (this.features | 16);
        this.features = (short) (this.features | 256);
        this.features = (short) (this.features | 512);
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        }
        this.fSymbolTable = symbolTable;
        this.fComponents = new ArrayList();
        setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        this.fErrorReporter = new XMLErrorReporter();
        setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        addComponent(this.fErrorReporter);
        this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
        this.fXML11DatatypeFactory = DTDDVFactory.getInstance(XML11_DATATYPE_VALIDATOR_FACTORY);
        this.fCurrentDVFactory = this.fDatatypeValidatorFactory;
        setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, this.fCurrentDVFactory);
        XMLEntityManager manager = new XMLEntityManager();
        setProperty(ENTITY_MANAGER, manager);
        addComponent(manager);
        this.fValidationManager = createValidationManager();
        setProperty(VALIDATION_MANAGER, this.fValidationManager);
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xmft = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            this.fErrorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
        }
        if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            MessageFormatter xmft2 = null;
            try {
                xmft2 = (MessageFormatter) ObjectFactory.newInstance("mf.org.apache.xerces.impl.xs.XSMessageFormatter", ObjectFactory.findClassLoader(), true);
            } catch (Exception e) {
            }
            if (xmft2 != null) {
                this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, xmft2);
            }
        }
        try {
            setLocale(Locale.getDefault());
        } catch (XNIException e2) {
        }
    }

    public void parse(XMLInputSource inputSource) throws XNIException, IOException {
    }

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
    }

    public XMLDTDHandler getDTDHandler() {
        return null;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler handler) {
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return null;
    }

    public void setEntityResolver(XMLEntityResolver resolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", resolver);
    }

    public XMLEntityResolver getEntityResolver() {
        return (XMLEntityResolver) this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }

    public void setErrorHandler(XMLErrorHandler errorHandler) {
        if (errorHandler != null) {
            this.fProperties.put(ERROR_HANDLER, errorHandler);
        }
    }

    public XMLErrorHandler getErrorHandler() {
        return (XMLErrorHandler) this.fProperties.get(ERROR_HANDLER);
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://apache.org/xml/features/internal/parser-settings")) {
            return true;
        }
        return super.getFeature(featureId);
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        super.setFeature(featureId, state);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        super.setProperty(propertyId, value);
    }

    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public void setParameter(String name, Object value) throws DOMException {
        boolean found = true;
        if (value instanceof Boolean) {
            boolean state = ((Boolean) value).booleanValue();
            if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
                int i;
                if (state) {
                    i = this.features | 32;
                } else {
                    i = this.features & -33;
                }
                this.features = (short) i;
            } else if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
                setFeature(NORMALIZE_DATA, state);
                this.features = (short) (state ? this.features | 2 : this.features & -3);
                if (state) {
                    this.features = (short) (this.features | 64);
                }
            } else if (name.equalsIgnoreCase("namespaces")) {
                this.features = (short) (state ? this.features | 1 : this.features & -2);
            } else if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                this.features = (short) (state ? this.features | 8 : this.features & -9);
            } else if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                this.features = (short) (state ? this.features | 4 : this.features & -5);
            } else if (name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA)) {
                this.features = (short) (state ? this.features | 16 : this.features & -17);
            } else if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
                this.features = (short) (state ? this.features | 64 : this.features & -65);
            } else if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED)) {
                this.features = (short) (state ? this.features | 256 : this.features & -257);
            } else if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                this.features = (short) (state ? this.features | 512 : this.features & -513);
            } else if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                if (state) {
                    this.features = (short) (this.features | 801);
                    this.features = (short) (this.features & -15);
                    setFeature(NORMALIZE_DATA, false);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION)) {
                if (state) {
                    throw newFeatureNotSupportedError(name);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
                if (!state) {
                    throw newFeatureNotSupportedError(name);
                }
            } else if (name.equalsIgnoreCase(SEND_PSVI)) {
                if (!state) {
                    throw newFeatureNotSupportedError(name);
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_PSVI)) {
                this.features = (short) (state ? this.features | 128 : this.features & -129);
            } else {
                found = false;
            }
        }
        if (!found || !(value instanceof Boolean)) {
            if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
                if ((value instanceof DOMErrorHandler) || value == null) {
                    this.fErrorHandlerWrapper.setErrorHandler((DOMErrorHandler) value);
                    setErrorHandler(this.fErrorHandlerWrapper);
                    return;
                }
                throw newTypeMismatchError(name);
            } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                if ((value instanceof LSResourceResolver) || value == null) {
                    try {
                        setEntityResolver(new DOMEntityResolverWrapper((LSResourceResolver) value));
                        return;
                    } catch (XMLConfigurationException e) {
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
                        setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
                    } catch (XMLConfigurationException e2) {
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
                        setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", locations.toArray(new String[locations.size()]));
                        return;
                    }
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", new String[]{(String) value});
                }
            } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
                if (!(value instanceof String) && value != null) {
                    throw newTypeMismatchError(name);
                } else if (value == null) {
                    try {
                        setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
                    } catch (XMLConfigurationException e3) {
                    }
                } else if (value.equals(Constants.NS_XMLSCHEMA)) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_XMLSCHEMA);
                } else if (value.equals(Constants.NS_DTD)) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", Constants.NS_DTD);
                }
            } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
                if ((value instanceof XMLEntityResolver) || value == null) {
                    try {
                        setEntityResolver((XMLEntityResolver) value);
                        return;
                    } catch (XMLConfigurationException e4) {
                        return;
                    }
                }
                throw newTypeMismatchError(name);
            } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                if (value instanceof SymbolTable) {
                    setProperty("http://apache.org/xml/properties/internal/symbol-table", value);
                    return;
                }
                throw newTypeMismatchError(name);
            } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
                if ((value instanceof XMLGrammarPool) || value == null) {
                    setProperty("http://apache.org/xml/properties/internal/grammar-pool", value);
                    return;
                }
                throw newTypeMismatchError(name);
            } else if (!name.equalsIgnoreCase(SECURITY_MANAGER)) {
                throw newFeatureNotFoundError(name);
            } else if ((value instanceof SecurityManager) || value == null) {
                setProperty(SECURITY_MANAGER, value);
            } else {
                throw newTypeMismatchError(name);
            }
        }
    }

    public Object getParameter(String name) throws DOMException {
        if (name.equalsIgnoreCase(Constants.DOM_COMMENTS)) {
            if ((this.features & 32) != 0) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } else if (name.equalsIgnoreCase("namespaces")) {
            return (this.features & 1) != 0 ? Boolean.TRUE : Boolean.FALSE;
        } else {
            if (name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION)) {
                return (this.features & 2) != 0 ? Boolean.TRUE : Boolean.FALSE;
            } else {
                if (name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS)) {
                    return (this.features & 8) != 0 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    if (name.equalsIgnoreCase(Constants.DOM_ENTITIES)) {
                        return (this.features & 4) != 0 ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        if (name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA)) {
                            return (this.features & 16) != 0 ? Boolean.TRUE : Boolean.FALSE;
                        } else {
                            if (name.equalsIgnoreCase(Constants.DOM_VALIDATE)) {
                                return (this.features & 64) != 0 ? Boolean.TRUE : Boolean.FALSE;
                            } else {
                                if (name.equalsIgnoreCase(Constants.DOM_WELLFORMED)) {
                                    return (this.features & 256) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                } else {
                                    if (name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                                        return (this.features & 512) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                    } else {
                                        if (name.equalsIgnoreCase(Constants.DOM_INFOSET)) {
                                            return (this.features & 815) == 801 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            if (name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION)) {
                                                return Boolean.FALSE;
                                            }
                                            if (name.equalsIgnoreCase(SEND_PSVI)) {
                                                return Boolean.TRUE;
                                            }
                                            if (name.equalsIgnoreCase(Constants.DOM_PSVI)) {
                                                return (this.features & 128) != 0 ? Boolean.TRUE : Boolean.FALSE;
                                            } else {
                                                if (name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE)) {
                                                    return Boolean.TRUE;
                                                }
                                                if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
                                                    return this.fErrorHandlerWrapper.getErrorHandler();
                                                }
                                                if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                                                    XMLEntityResolver entityResolver = getEntityResolver();
                                                    if (entityResolver == null || !(entityResolver instanceof DOMEntityResolverWrapper)) {
                                                        return null;
                                                    }
                                                    return ((DOMEntityResolverWrapper) entityResolver).getEntityResolver();
                                                } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
                                                    return getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
                                                } else {
                                                    if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
                                                        return this.fSchemaLocation;
                                                    }
                                                    if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
                                                        return getEntityResolver();
                                                    }
                                                    if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                                                        return getProperty("http://apache.org/xml/properties/internal/symbol-table");
                                                    }
                                                    if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
                                                        return getProperty("http://apache.org/xml/properties/internal/grammar-pool");
                                                    }
                                                    if (name.equalsIgnoreCase(SECURITY_MANAGER)) {
                                                        return getProperty(SECURITY_MANAGER);
                                                    }
                                                    throw newFeatureNotFoundError(name);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
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
            if (name.equalsIgnoreCase(Constants.DOM_COMMENTS) || name.equalsIgnoreCase(Constants.DOM_DATATYPE_NORMALIZATION) || name.equalsIgnoreCase(Constants.DOM_CDATA_SECTIONS) || name.equalsIgnoreCase(Constants.DOM_ENTITIES) || name.equalsIgnoreCase(Constants.DOM_SPLIT_CDATA) || name.equalsIgnoreCase("namespaces") || name.equalsIgnoreCase(Constants.DOM_VALIDATE) || name.equalsIgnoreCase(Constants.DOM_WELLFORMED) || name.equalsIgnoreCase(Constants.DOM_INFOSET) || name.equalsIgnoreCase(Constants.DOM_NAMESPACE_DECLARATIONS)) {
                return true;
            }
            if (name.equalsIgnoreCase(Constants.DOM_NORMALIZE_CHARACTERS) || name.equalsIgnoreCase(Constants.DOM_CANONICAL_FORM) || name.equalsIgnoreCase(Constants.DOM_VALIDATE_IF_SCHEMA) || name.equalsIgnoreCase(Constants.DOM_CHECK_CHAR_NORMALIZATION)) {
                if (!value.equals(Boolean.TRUE)) {
                    z = true;
                }
                return z;
            } else if (!name.equalsIgnoreCase(Constants.DOM_ELEMENT_CONTENT_WHITESPACE) && !name.equalsIgnoreCase(SEND_PSVI)) {
                return false;
            } else {
                if (value.equals(Boolean.TRUE)) {
                    return true;
                }
                return false;
            }
        } else if (name.equalsIgnoreCase(Constants.DOM_ERROR_HANDLER)) {
            if (value instanceof DOMErrorHandler) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            if (value instanceof LSResourceResolver) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_LOCATION)) {
            if (value instanceof String) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase(Constants.DOM_SCHEMA_TYPE)) {
            if ((value instanceof String) && (value.equals(Constants.NS_XMLSCHEMA) || value.equals(Constants.NS_DTD))) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
            if (value instanceof XMLEntityResolver) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
            if (value instanceof SymbolTable) {
                return true;
            }
            return false;
        } else if (name.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
            if (value instanceof XMLGrammarPool) {
                return true;
            }
            return false;
        } else if (!name.equalsIgnoreCase(SECURITY_MANAGER)) {
            return false;
        } else {
            if (value instanceof SecurityManager) {
                return true;
            }
            return false;
        }
    }

    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList parameters = new ArrayList();
            parameters.add(Constants.DOM_COMMENTS);
            parameters.add(Constants.DOM_DATATYPE_NORMALIZATION);
            parameters.add(Constants.DOM_CDATA_SECTIONS);
            parameters.add(Constants.DOM_ENTITIES);
            parameters.add(Constants.DOM_SPLIT_CDATA);
            parameters.add("namespaces");
            parameters.add(Constants.DOM_VALIDATE);
            parameters.add(Constants.DOM_INFOSET);
            parameters.add(Constants.DOM_NORMALIZE_CHARACTERS);
            parameters.add(Constants.DOM_CANONICAL_FORM);
            parameters.add(Constants.DOM_VALIDATE_IF_SCHEMA);
            parameters.add(Constants.DOM_CHECK_CHAR_NORMALIZATION);
            parameters.add(Constants.DOM_WELLFORMED);
            parameters.add(Constants.DOM_NAMESPACE_DECLARATIONS);
            parameters.add(Constants.DOM_ELEMENT_CONTENT_WHITESPACE);
            parameters.add(Constants.DOM_ERROR_HANDLER);
            parameters.add(Constants.DOM_SCHEMA_TYPE);
            parameters.add(Constants.DOM_SCHEMA_LOCATION);
            parameters.add(Constants.DOM_RESOURCE_RESOLVER);
            parameters.add("http://apache.org/xml/properties/internal/entity-resolver");
            parameters.add("http://apache.org/xml/properties/internal/grammar-pool");
            parameters.add(SECURITY_MANAGER);
            parameters.add("http://apache.org/xml/properties/internal/symbol-table");
            parameters.add(SEND_PSVI);
            this.fRecognizedParameters = new DOMStringListImpl(parameters);
        }
        return this.fRecognizedParameters;
    }

    protected void reset() throws XNIException {
        if (this.fValidationManager != null) {
            this.fValidationManager.reset();
        }
        int count = this.fComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).reset(this);
        }
    }

    protected void checkProperty(String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX) && propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length() == Constants.XML_STRING_PROPERTY.length() && propertyId.endsWith(Constants.XML_STRING_PROPERTY)) {
            throw new XMLConfigurationException((short) 1, propertyId);
        }
        super.checkProperty(propertyId);
    }

    protected void addComponent(XMLComponent component) {
        if (!this.fComponents.contains(component)) {
            this.fComponents.add(component);
            addRecognizedFeatures(component.getRecognizedFeatures());
            addRecognizedProperties(component.getRecognizedProperties());
        }
    }

    protected ValidationManager createValidationManager() {
        return new ValidationManager();
    }

    protected final void setDTDValidatorFactory(String version) {
        if ("1.1".equals(version)) {
            if (this.fCurrentDVFactory != this.fXML11DatatypeFactory) {
                this.fCurrentDVFactory = this.fXML11DatatypeFactory;
                setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, this.fCurrentDVFactory);
            }
        } else if (this.fCurrentDVFactory != this.fDatatypeValidatorFactory) {
            this.fCurrentDVFactory = this.fDatatypeValidatorFactory;
            setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, this.fCurrentDVFactory);
        }
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
