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
import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
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

public class XML11NonValidatingConfiguration extends ParserConfigurationSettings implements XMLPullParserConfiguration, XML11Configurable {
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "mf.org.apache.xerces.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    private boolean f11Initialized;
    protected ArrayList fCommonComponents;
    protected ArrayList fComponents;
    protected boolean fConfigUpdated;
    protected XMLDTDScanner fCurrentDTDScanner;
    protected DTDDVFactory fCurrentDVFactory;
    protected XMLDocumentScanner fCurrentScanner;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDScanner fDTDScanner;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    protected XMLInputSource fInputSource;
    protected XMLDocumentSource fLastComponent;
    protected Locale fLocale;
    protected XMLLocator fLocator;
    protected XMLNSDocumentScannerImpl fNamespaceScanner;
    protected XMLDocumentScannerImpl fNonNSScanner;
    protected boolean fParseInProgress;
    protected SymbolTable fSymbolTable;
    protected ValidationManager fValidationManager;
    protected XMLVersionDetector fVersionDetector;
    protected ArrayList fXML11Components;
    protected XML11DTDScannerImpl fXML11DTDScanner;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected XML11DocumentScannerImpl fXML11DocScanner;
    protected XML11NSDocumentScannerImpl fXML11NSDocScanner;

    public XML11NonValidatingConfiguration() {
        this(null, null, null);
    }

    public XML11NonValidatingConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XML11NonValidatingConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public XML11NonValidatingConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(parentSettings);
        this.fXML11Components = null;
        this.fCommonComponents = null;
        this.fParseInProgress = false;
        this.fConfigUpdated = false;
        this.fXML11DatatypeFactory = null;
        this.fXML11NSDocScanner = null;
        this.fXML11DocScanner = null;
        this.fXML11DTDScanner = null;
        this.f11Initialized = false;
        this.fComponents = new ArrayList();
        this.fXML11Components = new ArrayList();
        this.fCommonComponents = new ArrayList();
        this.fRecognizedFeatures = new ArrayList();
        this.fRecognizedProperties = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{CONTINUE_AFTER_FATAL_ERROR, VALIDATION, NAMESPACES, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, "http://apache.org/xml/features/internal/parser-settings"});
        this.fFeatures.put(VALIDATION, Boolean.FALSE);
        this.fFeatures.put(NAMESPACES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, Boolean.TRUE);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, Boolean.TRUE);
        this.fFeatures.put(CONTINUE_AFTER_FATAL_ERROR, Boolean.FALSE);
        this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", Boolean.TRUE);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, DOCUMENT_SCANNER, DTD_SCANNER, DTD_VALIDATOR, DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER, XML_STRING, "http://apache.org/xml/properties/internal/grammar-pool"});
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

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        int i;
        this.fConfigUpdated = true;
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
        }
        this.fXML11DTDScanner.setDTDHandler(this.fDTDHandler);
        this.fXML11DTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fFeatures.get(NAMESPACES) == Boolean.TRUE) {
            if (this.fCurrentScanner != this.fXML11NSDocScanner) {
                this.fCurrentScanner = this.fXML11NSDocScanner;
                setProperty(DOCUMENT_SCANNER, this.fXML11NSDocScanner);
            }
            this.fXML11NSDocScanner.setDTDValidator(null);
            this.fXML11NSDocScanner.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fXML11NSDocScanner);
            }
            this.fLastComponent = this.fXML11NSDocScanner;
            return;
        }
        if (this.fXML11DocScanner == null) {
            this.fXML11DocScanner = new XML11DocumentScannerImpl();
            addXML11Component(this.fXML11DocScanner);
        }
        if (this.fCurrentScanner != this.fXML11DocScanner) {
            this.fCurrentScanner = this.fXML11DocScanner;
            setProperty(DOCUMENT_SCANNER, this.fXML11DocScanner);
        }
        this.fXML11DocScanner.setDocumentHandler(this.fDocumentHandler);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.setDocumentSource(this.fXML11DocScanner);
        }
        this.fLastComponent = this.fXML11DocScanner;
    }

    protected void configurePipeline() {
        if (this.fCurrentDVFactory != this.fDatatypeValidatorFactory) {
            this.fCurrentDVFactory = this.fDatatypeValidatorFactory;
            setProperty(DATATYPE_VALIDATOR_FACTORY, this.fCurrentDVFactory);
        }
        if (this.fCurrentDTDScanner != this.fDTDScanner) {
            this.fCurrentDTDScanner = this.fDTDScanner;
            setProperty(DTD_SCANNER, this.fCurrentDTDScanner);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDHandler);
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fFeatures.get(NAMESPACES) == Boolean.TRUE) {
            if (this.fCurrentScanner != this.fNamespaceScanner) {
                this.fCurrentScanner = this.fNamespaceScanner;
                setProperty(DOCUMENT_SCANNER, this.fNamespaceScanner);
            }
            this.fNamespaceScanner.setDTDValidator(null);
            this.fNamespaceScanner.setDocumentHandler(this.fDocumentHandler);
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.setDocumentSource(this.fNamespaceScanner);
            }
            this.fLastComponent = this.fNamespaceScanner;
            return;
        }
        if (this.fNonNSScanner == null) {
            this.fNonNSScanner = new XMLDocumentScannerImpl();
            addComponent(this.fNonNSScanner);
        }
        if (this.fCurrentScanner != this.fNonNSScanner) {
            this.fCurrentScanner = this.fNonNSScanner;
            setProperty(DOCUMENT_SCANNER, this.fNonNSScanner);
        }
        this.fNonNSScanner.setDocumentHandler(this.fDocumentHandler);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.setDocumentSource(this.fNonNSScanner);
        }
        this.fLastComponent = this.fNonNSScanner;
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
                        } else if (suffixLength == Constants.PARSER_SETTINGS.length() && featureId.endsWith(Constants.PARSER_SETTINGS)) {
                            throw new XMLConfigurationException((short) 1, featureId);
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
        if (!propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX) || propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length() != Constants.DTD_SCANNER_PROPERTY.length() || !propertyId.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
            if (!propertyId.startsWith(Constants.JAXP_PROPERTY_PREFIX) || propertyId.length() - Constants.JAXP_PROPERTY_PREFIX.length() != Constants.SCHEMA_SOURCE.length() || !propertyId.endsWith(Constants.SCHEMA_SOURCE)) {
                if (propertyId.startsWith(Constants.SAX_PROPERTY_PREFIX) && propertyId.length() - Constants.SAX_PROPERTY_PREFIX.length() == Constants.XML_STRING_PROPERTY.length() && propertyId.endsWith(Constants.XML_STRING_PROPERTY)) {
                    throw new XMLConfigurationException((short) 1, propertyId);
                }
                super.checkProperty(propertyId);
            }
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
            this.fXML11NSDocScanner = new XML11NSDocumentScannerImpl();
            addXML11Component(this.fXML11NSDocScanner);
            this.f11Initialized = true;
        }
    }
}
