package mf.org.apache.xerces.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XML11DTDScannerImpl;
import mf.org.apache.xerces.impl.XML11DocumentScannerImpl;
import mf.org.apache.xerces.impl.XML11NSDocumentScannerImpl;
import mf.org.apache.xerces.impl.XMLDTDScannerImpl;
import mf.org.apache.xerces.impl.XMLDocumentScannerImpl;
import mf.org.apache.xerces.impl.XMLEntityHandler;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.XMLNSDocumentScannerImpl;
import mf.org.apache.xerces.impl.XMLVersionDetector;
import mf.org.apache.xerces.impl.dtd.XML11DTDProcessor;
import mf.org.apache.xerces.impl.dtd.XML11DTDValidator;
import mf.org.apache.xerces.impl.dtd.XML11NSDTDValidator;
import mf.org.apache.xerces.impl.dtd.XMLDTDProcessor;
import mf.org.apache.xerces.impl.dtd.XMLDTDValidator;
import mf.org.apache.xerces.impl.dtd.XMLNSDTDValidator;
import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.impl.xs.XSMessageFormatter;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDScanner;
import mf.org.apache.xerces.xni.parser.XMLDocumentScanner;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLPullParserConfiguration;

public class XML11Configuration extends ParserConfigurationSettings implements XMLPullParserConfiguration, XML11Configurable {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_PROCESSOR = "http://apache.org/xml/properties/internal/dtd-processor";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected static final String ROOT_ELEMENT_DECL = "http://apache.org/xml/properties/validation/schema/root-element-declaration";
    protected static final String ROOT_TYPE_DEF = "http://apache.org/xml/properties/validation/schema/root-type-definition";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "mf.org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    private boolean f11Initialized;
    protected final ArrayList fCommonComponents;
    protected final ArrayList fComponents;
    protected boolean fConfigUpdated;
    protected XMLDTDScanner fCurrentDTDScanner;
    protected DTDDVFactory fCurrentDVFactory;
    protected XMLDocumentScanner fCurrentScanner;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    protected final XMLDTDProcessor fDTDProcessor;
    protected final XMLDTDScanner fDTDScanner;
    protected final XMLDTDValidator fDTDValidator;
    protected final DTDDVFactory fDatatypeValidatorFactory;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    protected XMLInputSource fInputSource;
    protected XMLDocumentSource fLastComponent;
    protected Locale fLocale;
    protected final XMLNSDocumentScannerImpl fNamespaceScanner;
    protected XMLDTDValidator fNonNSDTDValidator;
    protected XMLDocumentScannerImpl fNonNSScanner;
    protected boolean fParseInProgress;
    protected XMLSchemaValidator fSchemaValidator;
    protected SymbolTable fSymbolTable;
    protected final ValidationManager fValidationManager;
    protected final XMLVersionDetector fVersionDetector;
    protected final ArrayList fXML11Components;
    protected XML11DTDProcessor fXML11DTDProcessor;
    protected XML11DTDScannerImpl fXML11DTDScanner;
    protected XML11DTDValidator fXML11DTDValidator;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected XML11DocumentScannerImpl fXML11DocScanner;
    protected XML11NSDTDValidator fXML11NSDTDValidator;
    protected XML11NSDocumentScannerImpl fXML11NSDocScanner;

    public XML11Configuration() {
        this(null, null, null);
    }

    public XML11Configuration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XML11Configuration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public XML11Configuration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(parentSettings);
        this.fParseInProgress = false;
        this.fConfigUpdated = false;
        this.fXML11DatatypeFactory = null;
        this.fXML11NSDocScanner = null;
        this.fXML11DocScanner = null;
        this.fXML11NSDTDValidator = null;
        this.fXML11DTDValidator = null;
        this.fXML11DTDScanner = null;
        this.fXML11DTDProcessor = null;
        this.f11Initialized = false;
        this.fComponents = new ArrayList();
        this.fXML11Components = new ArrayList();
        this.fCommonComponents = new ArrayList();
        this.fRecognizedFeatures = new ArrayList();
        this.fRecognizedProperties = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{CONTINUE_AFTER_FATAL_ERROR, LOAD_EXTERNAL_DTD, VALIDATION, NAMESPACES, NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SCHEMA_AUGMENT_PSVI, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, NAMESPACE_GROWTH, TOLERATE_DUPLICATES, IGNORE_XSI_TYPE, ID_IDREF_CHECKING, IDENTITY_CONSTRAINT_CHECKING, UNPARSED_ENTITY_CHECKING, USE_GRAMMAR_POOL_ONLY, XMLSCHEMA_VALIDATION, XMLSCHEMA_FULL_CHECKING, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, "http://apache.org/xml/features/internal/parser-settings"});
        this.fFeatures.put(VALIDATION, Boolean.FALSE);
        this.fFeatures.put(NAMESPACES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, Boolean.TRUE);
        this.fFeatures.put(CONTINUE_AFTER_FATAL_ERROR, Boolean.FALSE);
        this.fFeatures.put(LOAD_EXTERNAL_DTD, Boolean.TRUE);
        this.fFeatures.put(SCHEMA_ELEMENT_DEFAULT, Boolean.TRUE);
        this.fFeatures.put(NORMALIZE_DATA, Boolean.TRUE);
        this.fFeatures.put(SCHEMA_AUGMENT_PSVI, Boolean.TRUE);
        this.fFeatures.put("http://apache.org/xml/features/generate-synthetic-annotations", Boolean.FALSE);
        this.fFeatures.put(VALIDATE_ANNOTATIONS, Boolean.FALSE);
        this.fFeatures.put(HONOUR_ALL_SCHEMALOCATIONS, Boolean.FALSE);
        this.fFeatures.put(NAMESPACE_GROWTH, Boolean.FALSE);
        this.fFeatures.put(TOLERATE_DUPLICATES, Boolean.FALSE);
        this.fFeatures.put(IGNORE_XSI_TYPE, Boolean.FALSE);
        this.fFeatures.put(ID_IDREF_CHECKING, Boolean.TRUE);
        this.fFeatures.put(IDENTITY_CONSTRAINT_CHECKING, Boolean.TRUE);
        this.fFeatures.put(UNPARSED_ENTITY_CHECKING, Boolean.TRUE);
        this.fFeatures.put(USE_GRAMMAR_POOL_ONLY, Boolean.FALSE);
        this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", Boolean.TRUE);
        addRecognizedProperties(new String[]{"http://apache.org/xml/properties/internal/symbol-table", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, DOCUMENT_SCANNER, DTD_SCANNER, DTD_PROCESSOR, DTD_VALIDATOR, DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER, SCHEMA_VALIDATOR, XML_STRING, "http://apache.org/xml/properties/internal/grammar-pool", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, "http://apache.org/xml/properties/locale", ROOT_TYPE_DEF, ROOT_ELEMENT_DECL, SCHEMA_DV_FACTORY});
        if (symbolTable == null) {
            symbolTable = new SymbolTable();
        }
        this.fSymbolTable = symbolTable;
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        this.fGrammarPool = grammarPool;
        if (this.fGrammarPool != null) {
            this.fProperties.put("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarPool);
        }
        this.fEntityManager = new XMLEntityManager();
        this.fProperties.put(ENTITY_MANAGER, this.fEntityManager);
        addCommonComponent(this.fEntityManager);
        this.fErrorReporter = new XMLErrorReporter();
        this.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
        this.fProperties.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        addCommonComponent(this.fErrorReporter);
        this.fNamespaceScanner = new XMLNSDocumentScannerImpl();
        this.fProperties.put(DOCUMENT_SCANNER, this.fNamespaceScanner);
        addComponent(this.fNamespaceScanner);
        this.fDTDScanner = new XMLDTDScannerImpl();
        this.fProperties.put(DTD_SCANNER, this.fDTDScanner);
        addComponent((XMLComponent) this.fDTDScanner);
        this.fDTDProcessor = new XMLDTDProcessor();
        this.fProperties.put(DTD_PROCESSOR, this.fDTDProcessor);
        addComponent(this.fDTDProcessor);
        this.fDTDValidator = new XMLNSDTDValidator();
        this.fProperties.put(DTD_VALIDATOR, this.fDTDValidator);
        addComponent(this.fDTDValidator);
        this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
        this.fProperties.put(DATATYPE_VALIDATOR_FACTORY, this.fDatatypeValidatorFactory);
        this.fValidationManager = new ValidationManager();
        this.fProperties.put(VALIDATION_MANAGER, this.fValidationManager);
        this.fVersionDetector = new XMLVersionDetector();
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xmft = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            this.fErrorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
        }
        try {
            setLocale(Locale.getDefault());
        } catch (XNIException e) {
        }
        this.fConfigUpdated = false;
    }

    public void setInputSource(XMLInputSource inputSource) throws XMLConfigurationException, IOException {
        this.fInputSource = inputSource;
    }

    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
        if (this.fLastComponent != null) {
            this.fLastComponent.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fLastComponent);
            }
        }
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler handler) {
        this.fDTDContentModelHandler = handler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    public void setEntityResolver(XMLEntityResolver resolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", resolver);
    }

    public XMLEntityResolver getEntityResolver() {
        return (XMLEntityResolver) this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }

    public void setErrorHandler(XMLErrorHandler errorHandler) {
        this.fProperties.put(ERROR_HANDLER, errorHandler);
    }

    public XMLErrorHandler getErrorHandler() {
        return (XMLErrorHandler) this.fProperties.get(ERROR_HANDLER);
    }

    public void cleanup() {
        this.fEntityManager.closeReaders();
    }

    public void parse(XMLInputSource source) throws XNIException, IOException {
        if (this.fParseInProgress) {
            throw new XNIException("FWK005 parse may not be called while parsing.");
        }
        this.fParseInProgress = true;
        try {
            setInputSource(source);
            parse(true);
            this.fParseInProgress = false;
            cleanup();
        } catch (XNIException ex) {
            throw ex;
        } catch (IOException ex2) {
            throw ex2;
        } catch (RuntimeException ex3) {
            throw ex3;
        } catch (Exception ex4) {
            throw new XNIException(ex4);
        } catch (Throwable th) {
            this.fParseInProgress = false;
            cleanup();
        }
    }

    public boolean parse(boolean complete) throws XNIException, IOException {
        boolean z = false;
        if (this.fInputSource != null) {
            try {
                this.fValidationManager.reset();
                this.fVersionDetector.reset(this);
                resetCommon();
                short version = this.fVersionDetector.determineDocVersion(this.fInputSource);
                if (version == (short) 1) {
                    configurePipeline();
                    reset();
                } else {
                    if (version == (short) 2) {
                        initXML11Components();
                        configureXML11Pipeline();
                        resetXML11();
                    }
                    return z;
                }
                this.fConfigUpdated = false;
                this.fVersionDetector.startDocumentParsing((XMLEntityHandler) this.fCurrentScanner, version);
                this.fInputSource = null;
            } catch (XNIException ex) {
                throw ex;
            } catch (IOException ex2) {
                throw ex2;
            } catch (RuntimeException ex3) {
                throw ex3;
            } catch (Exception ex4) {
                throw new XNIException(ex4);
            }
        }
        try {
            z = this.fCurrentScanner.scanDocument(complete);
            return z;
        } catch (XNIException ex5) {
            throw ex5;
        } catch (IOException ex22) {
            throw ex22;
        } catch (RuntimeException ex32) {
            throw ex32;
        } catch (Exception ex42) {
            throw new XNIException(ex42);
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://apache.org/xml/features/internal/parser-settings")) {
            return this.fConfigUpdated;
        }
        return super.getFeature(featureId);
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        int i;
        this.fConfigUpdated = true;
        int count = this.fComponents.size();
        for (i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).setFeature(featureId, state);
        }
        count = this.fCommonComponents.size();
        for (i = 0; i < count; i++) {
            ((XMLComponent) this.fCommonComponents.get(i)).setFeature(featureId, state);
        }
        count = this.fXML11Components.size();
        for (i = 0; i < count; i++) {
            try {
                ((XMLComponent) this.fXML11Components.get(i)).setFeature(featureId, state);
            } catch (Exception e) {
            }
        }
        super.setFeature(featureId, state);
    }

    public Object getProperty(String propertyId) throws XMLConfigurationException {
        if ("http://apache.org/xml/properties/locale".equals(propertyId)) {
            return getLocale();
        }
        return super.getProperty(propertyId);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        int i;
        this.fConfigUpdated = true;
        if ("http://apache.org/xml/properties/locale".equals(propertyId)) {
            setLocale((Locale) value);
        }
        int count = this.fComponents.size();
        for (i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).setProperty(propertyId, value);
        }
        count = this.fCommonComponents.size();
        for (i = 0; i < count; i++) {
            ((XMLComponent) this.fCommonComponents.get(i)).setProperty(propertyId, value);
        }
        count = this.fXML11Components.size();
        for (i = 0; i < count; i++) {
            try {
                ((XMLComponent) this.fXML11Components.get(i)).setProperty(propertyId, value);
            } catch (Exception e) {
            }
        }
        super.setProperty(propertyId, value);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    protected void reset() throws XNIException {
        int count = this.fComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fComponents.get(i)).reset(this);
        }
    }

    protected void resetCommon() throws XNIException {
        int count = this.fCommonComponents.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fCommonComponents.get(i)).reset(this);
        }
    }

    protected void resetXML11() throws XNIException {
        int count = this.fXML11Components.size();
        for (int i = 0; i < count; i++) {
            ((XMLComponent) this.fXML11Components.get(i)).reset(this);
        }
    }

    protected void configureXML11Pipeline() {
        if (this.fCurrentDVFactory != this.fXML11DatatypeFactory) {
            this.fCurrentDVFactory = this.fXML11DatatypeFactory;
            setProperty(DATATYPE_VALIDATOR_FACTORY, this.fCurrentDVFactory);
        }
        if (this.fCurrentDTDScanner != this.fXML11DTDScanner) {
            this.fCurrentDTDScanner = this.fXML11DTDScanner;
            setProperty(DTD_SCANNER, this.fCurrentDTDScanner);
            setProperty(DTD_PROCESSOR, this.fXML11DTDProcessor);
        }
        this.fXML11DTDScanner.setDTDHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDSource(this.fXML11DTDScanner);
        this.fXML11DTDProcessor.setDTDHandler(this.fDTDHandler);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.setDTDSource(this.fXML11DTDProcessor);
        }
        this.fXML11DTDScanner.setDTDContentModelHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDContentModelSource(this.fXML11DTDScanner);
        this.fXML11DTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.setDTDContentModelSource(this.fXML11DTDProcessor);
        }
        if (this.fFeatures.get(NAMESPACES) == Boolean.TRUE) {
            if (this.fCurrentScanner != this.fXML11NSDocScanner) {
                this.fCurrentScanner = this.fXML11NSDocScanner;
                setProperty(DOCUMENT_SCANNER, this.fXML11NSDocScanner);
                setProperty(DTD_VALIDATOR, this.fXML11NSDTDValidator);
            }
            this.fXML11NSDocScanner.setDTDValidator(this.fXML11NSDTDValidator);
            this.fXML11NSDocScanner.setDocumentHandler(this.fXML11NSDTDValidator);
            this.fXML11NSDTDValidator.setDocumentSource(this.fXML11NSDocScanner);
            this.fXML11NSDTDValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fXML11NSDTDValidator);
            }
            this.fLastComponent = this.fXML11NSDTDValidator;
        } else {
            if (this.fXML11DocScanner == null) {
                this.fXML11DocScanner = new XML11DocumentScannerImpl();
                addXML11Component(this.fXML11DocScanner);
                this.fXML11DTDValidator = new XML11DTDValidator();
                addXML11Component(this.fXML11DTDValidator);
            }
            if (this.fCurrentScanner != this.fXML11DocScanner) {
                this.fCurrentScanner = this.fXML11DocScanner;
                setProperty(DOCUMENT_SCANNER, this.fXML11DocScanner);
                setProperty(DTD_VALIDATOR, this.fXML11DTDValidator);
            }
            this.fXML11DocScanner.setDocumentHandler(this.fXML11DTDValidator);
            this.fXML11DTDValidator.setDocumentSource(this.fXML11DocScanner);
            this.fXML11DTDValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fXML11DTDValidator);
            }
            this.fLastComponent = this.fXML11DTDValidator;
        }
        if (this.fFeatures.get(XMLSCHEMA_VALIDATION) == Boolean.TRUE) {
            if (this.fSchemaValidator == null) {
                this.fSchemaValidator = new XMLSchemaValidator();
                setProperty(SCHEMA_VALIDATOR, this.fSchemaValidator);
                addCommonComponent(this.fSchemaValidator);
                this.fSchemaValidator.reset(this);
                if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                    this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
                }
            }
            this.fLastComponent.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentSource(this.fLastComponent);
            this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fSchemaValidator);
            }
            this.fLastComponent = this.fSchemaValidator;
        }
    }

    protected void configurePipeline() {
        if (this.fCurrentDVFactory != this.fDatatypeValidatorFactory) {
            this.fCurrentDVFactory = this.fDatatypeValidatorFactory;
            setProperty(DATATYPE_VALIDATOR_FACTORY, this.fCurrentDVFactory);
        }
        if (this.fCurrentDTDScanner != this.fDTDScanner) {
            this.fCurrentDTDScanner = this.fDTDScanner;
            setProperty(DTD_SCANNER, this.fCurrentDTDScanner);
            setProperty(DTD_PROCESSOR, this.fDTDProcessor);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDHandler(this.fDTDHandler);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.setDTDSource(this.fDTDProcessor);
        }
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDContentModelSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.setDTDContentModelSource(this.fDTDProcessor);
        }
        if (this.fFeatures.get(NAMESPACES) == Boolean.TRUE) {
            if (this.fCurrentScanner != this.fNamespaceScanner) {
                this.fCurrentScanner = this.fNamespaceScanner;
                setProperty(DOCUMENT_SCANNER, this.fNamespaceScanner);
                setProperty(DTD_VALIDATOR, this.fDTDValidator);
            }
            this.fNamespaceScanner.setDTDValidator(this.fDTDValidator);
            this.fNamespaceScanner.setDocumentHandler(this.fDTDValidator);
            this.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
            this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fDTDValidator);
            }
            this.fLastComponent = this.fDTDValidator;
        } else {
            if (this.fNonNSScanner == null) {
                this.fNonNSScanner = new XMLDocumentScannerImpl();
                this.fNonNSDTDValidator = new XMLDTDValidator();
                addComponent(this.fNonNSScanner);
                addComponent(this.fNonNSDTDValidator);
            }
            if (this.fCurrentScanner != this.fNonNSScanner) {
                this.fCurrentScanner = this.fNonNSScanner;
                setProperty(DOCUMENT_SCANNER, this.fNonNSScanner);
                setProperty(DTD_VALIDATOR, this.fNonNSDTDValidator);
            }
            this.fNonNSScanner.setDocumentHandler(this.fNonNSDTDValidator);
            this.fNonNSDTDValidator.setDocumentSource(this.fNonNSScanner);
            this.fNonNSDTDValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fNonNSDTDValidator);
            }
            this.fLastComponent = this.fNonNSDTDValidator;
        }
        if (this.fFeatures.get(XMLSCHEMA_VALIDATION) == Boolean.TRUE) {
            if (this.fSchemaValidator == null) {
                this.fSchemaValidator = new XMLSchemaValidator();
                setProperty(SCHEMA_VALIDATOR, this.fSchemaValidator);
                addCommonComponent(this.fSchemaValidator);
                this.fSchemaValidator.reset(this);
                if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                    this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
                }
            }
            this.fLastComponent.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentSource(this.fLastComponent);
            this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fSchemaValidator);
            }
            this.fLastComponent = this.fSchemaValidator;
        }
    }

    protected void checkFeature(String featureId) throws XMLConfigurationException {
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
            int suffixLength = featureId.length() - Constants.XERCES_FEATURE_PREFIX.length();
            if (suffixLength != Constants.DYNAMIC_VALIDATION_FEATURE.length() || !featureId.endsWith(Constants.DYNAMIC_VALIDATION_FEATURE)) {
                if (suffixLength == Constants.DEFAULT_ATTRIBUTE_VALUES_FEATURE.length() && featureId.endsWith(Constants.DEFAULT_ATTRIBUTE_VALUES_FEATURE)) {
                    throw new XMLConfigurationException((short) 1, featureId);
                } else if (suffixLength == Constants.VALIDATE_CONTENT_MODELS_FEATURE.length() && featureId.endsWith(Constants.VALIDATE_CONTENT_MODELS_FEATURE)) {
                    throw new XMLConfigurationException((short) 1, featureId);
                } else if (suffixLength != Constants.LOAD_DTD_GRAMMAR_FEATURE.length() || !featureId.endsWith(Constants.LOAD_DTD_GRAMMAR_FEATURE)) {
                    if (suffixLength != Constants.LOAD_EXTERNAL_DTD_FEATURE.length() || !featureId.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
                        if (suffixLength == Constants.VALIDATE_DATATYPES_FEATURE.length() && featureId.endsWith(Constants.VALIDATE_DATATYPES_FEATURE)) {
                            throw new XMLConfigurationException((short) 1, featureId);
                        } else if (suffixLength != Constants.SCHEMA_VALIDATION_FEATURE.length() || !featureId.endsWith(Constants.SCHEMA_VALIDATION_FEATURE)) {
                            if (suffixLength != Constants.SCHEMA_FULL_CHECKING.length() || !featureId.endsWith(Constants.SCHEMA_FULL_CHECKING)) {
                                if (suffixLength != Constants.SCHEMA_NORMALIZED_VALUE.length() || !featureId.endsWith(Constants.SCHEMA_NORMALIZED_VALUE)) {
                                    if (suffixLength != Constants.SCHEMA_ELEMENT_DEFAULT.length() || !featureId.endsWith(Constants.SCHEMA_ELEMENT_DEFAULT)) {
                                        if (suffixLength == Constants.PARSER_SETTINGS.length() && featureId.endsWith(Constants.PARSER_SETTINGS)) {
                                            throw new XMLConfigurationException((short) 1, featureId);
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        super.checkFeature(featureId);
    }

    protected void checkProperty(String propertyId) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength != Constants.DTD_SCANNER_PROPERTY.length() || !propertyId.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
                if (suffixLength != Constants.SCHEMA_LOCATION.length() || !propertyId.endsWith(Constants.SCHEMA_LOCATION)) {
                    if (suffixLength == Constants.SCHEMA_NONS_LOCATION.length() && propertyId.endsWith(Constants.SCHEMA_NONS_LOCATION)) {
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (!propertyId.startsWith(Constants.JAXP_PROPERTY_PREFIX) || propertyId.length() - Constants.JAXP_PROPERTY_PREFIX.length() != Constants.SCHEMA_SOURCE.length() || !propertyId.endsWith(Constants.SCHEMA_SOURCE)) {
            if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX) && propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length() == Constants.XML_STRING_PROPERTY.length() && propertyId.endsWith(Constants.XML_STRING_PROPERTY)) {
                throw new XMLConfigurationException((short) 1, propertyId);
            }
            super.checkProperty(propertyId);
        }
    }

    protected void addComponent(XMLComponent component) {
        if (!this.fComponents.contains(component)) {
            this.fComponents.add(component);
            addRecognizedParamsAndSetDefaults(component);
        }
    }

    protected void addCommonComponent(XMLComponent component) {
        if (!this.fCommonComponents.contains(component)) {
            this.fCommonComponents.add(component);
            addRecognizedParamsAndSetDefaults(component);
        }
    }

    protected void addXML11Component(XMLComponent component) {
        if (!this.fXML11Components.contains(component)) {
            this.fXML11Components.add(component);
            addRecognizedParamsAndSetDefaults(component);
        }
    }

    protected void addRecognizedParamsAndSetDefaults(XMLComponent component) {
        String[] recognizedFeatures = component.getRecognizedFeatures();
        addRecognizedFeatures(recognizedFeatures);
        String[] recognizedProperties = component.getRecognizedProperties();
        addRecognizedProperties(recognizedProperties);
        if (recognizedFeatures != null) {
            for (String featureId : recognizedFeatures) {
                Boolean state = component.getFeatureDefault(featureId);
                if (!(state == null || this.fFeatures.containsKey(featureId))) {
                    this.fFeatures.put(featureId, state);
                    this.fConfigUpdated = true;
                }
            }
        }
        if (recognizedProperties != null) {
            for (String propertyId : recognizedProperties) {
                Object value = component.getPropertyDefault(propertyId);
                if (!(value == null || this.fProperties.containsKey(propertyId))) {
                    this.fProperties.put(propertyId, value);
                    this.fConfigUpdated = true;
                }
            }
        }
    }

    private void initXML11Components() {
        if (!this.f11Initialized) {
            this.fXML11DatatypeFactory = DTDDVFactory.getInstance(XML11_DATATYPE_VALIDATOR_FACTORY);
            this.fXML11DTDScanner = new XML11DTDScannerImpl();
            addXML11Component(this.fXML11DTDScanner);
            this.fXML11DTDProcessor = new XML11DTDProcessor();
            addXML11Component(this.fXML11DTDProcessor);
            this.fXML11NSDocScanner = new XML11NSDocumentScannerImpl();
            addXML11Component(this.fXML11NSDocScanner);
            this.fXML11NSDTDValidator = new XML11NSDTDValidator();
            addXML11Component(this.fXML11NSDTDValidator);
            this.f11Initialized = true;
        }
    }

    boolean getFeature0(String featureId) throws XMLConfigurationException {
        return super.getFeature(featureId);
    }
}
