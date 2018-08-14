package mf.org.apache.xerces.impl.dtd;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;
import mf.org.apache.xerces.impl.XMLDTDScannerImpl;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.DefaultErrorHandler;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarLoader;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLDTDLoader extends XMLDTDProcessor implements XMLGrammarLoader {
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String[] LOADER_RECOGNIZED_FEATURES = new String[]{"http://xml.org/sax/features/validation", "http://apache.org/xml/features/validation/warn-on-duplicate-attdef", "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef", "http://apache.org/xml/features/scanner/notify-char-refs", STANDARD_URI_CONFORMANT_FEATURE, BALANCE_SYNTAX_TREES};
    private static final String[] LOADER_RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", "http://apache.org/xml/properties/internal/validator/dtd", LOCALE};
    public static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    private boolean fBalanceSyntaxTrees;
    protected XMLDTDScannerImpl fDTDScanner;
    protected XMLEntityManager fEntityManager;
    protected XMLEntityResolver fEntityResolver;
    protected Locale fLocale;
    private boolean fStrictURI;

    public XMLDTDLoader() {
        this(new SymbolTable());
    }

    public XMLDTDLoader(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public XMLDTDLoader(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null, new XMLEntityManager());
    }

    XMLDTDLoader(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLErrorReporter errorReporter, XMLEntityResolver entityResolver) {
        this.fStrictURI = false;
        this.fBalanceSyntaxTrees = false;
        this.fSymbolTable = symbolTable;
        this.fGrammarPool = grammarPool;
        if (errorReporter == null) {
            errorReporter = new XMLErrorReporter();
            errorReporter.setProperty(ERROR_HANDLER, new DefaultErrorHandler());
        }
        this.fErrorReporter = errorReporter;
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xmft = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
            this.fErrorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
        }
        this.fEntityResolver = entityResolver;
        if (this.fEntityResolver instanceof XMLEntityManager) {
            this.fEntityManager = (XMLEntityManager) this.fEntityResolver;
        } else {
            this.fEntityManager = new XMLEntityManager();
        }
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/error-reporter", errorReporter);
        this.fDTDScanner = createDTDScanner(this.fSymbolTable, this.fErrorReporter, this.fEntityManager);
        this.fDTDScanner.setDTDHandler(this);
        this.fDTDScanner.setDTDContentModelHandler(this);
        reset();
    }

    public String[] getRecognizedFeatures() {
        return (String[]) LOADER_RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        if (featureId.equals("http://xml.org/sax/features/validation")) {
            this.fValidation = state;
        } else if (featureId.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            this.fWarnDuplicateAttdef = state;
        } else if (featureId.equals("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef")) {
            this.fWarnOnUndeclaredElemdef = state;
        } else if (featureId.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
            this.fDTDScanner.setFeature(featureId, state);
        } else if (featureId.equals(STANDARD_URI_CONFORMANT_FEATURE)) {
            this.fStrictURI = state;
        } else if (featureId.equals(BALANCE_SYNTAX_TREES)) {
            this.fBalanceSyntaxTrees = state;
        } else {
            throw new XMLConfigurationException((short) 0, featureId);
        }
    }

    public String[] getRecognizedProperties() {
        return (String[]) LOADER_RECOGNIZED_PROPERTIES.clone();
    }

    public Object getProperty(String propertyId) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            return this.fSymbolTable;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            return this.fErrorReporter;
        }
        if (propertyId.equals(ERROR_HANDLER)) {
            return this.fErrorReporter.getErrorHandler();
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            return this.fEntityResolver;
        }
        if (propertyId.equals(LOCALE)) {
            return getLocale();
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            return this.fGrammarPool;
        }
        if (propertyId.equals("http://apache.org/xml/properties/internal/validator/dtd")) {
            return this.fValidator;
        }
        throw new XMLConfigurationException((short) 0, propertyId);
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            this.fSymbolTable = (SymbolTable) value;
            this.fDTDScanner.setProperty(propertyId, value);
            this.fEntityManager.setProperty(propertyId, value);
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            this.fErrorReporter = (XMLErrorReporter) value;
            if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
                XMLMessageFormatter xmft = new XMLMessageFormatter();
                this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xmft);
                this.fErrorReporter.putMessageFormatter(XMLMessageFormatter.XMLNS_DOMAIN, xmft);
            }
            this.fDTDScanner.setProperty(propertyId, value);
            this.fEntityManager.setProperty(propertyId, value);
        } else if (propertyId.equals(ERROR_HANDLER)) {
            this.fErrorReporter.setProperty(propertyId, value);
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver) value;
            this.fEntityManager.setProperty(propertyId, value);
        } else if (propertyId.equals(LOCALE)) {
            setLocale((Locale) value);
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            this.fGrammarPool = (XMLGrammarPool) value;
        } else {
            throw new XMLConfigurationException((short) 0, propertyId);
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (featureId.equals("http://xml.org/sax/features/validation")) {
            return this.fValidation;
        }
        if (featureId.equals("http://apache.org/xml/features/validation/warn-on-duplicate-attdef")) {
            return this.fWarnDuplicateAttdef;
        }
        if (featureId.equals("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef")) {
            return this.fWarnOnUndeclaredElemdef;
        }
        if (featureId.equals("http://apache.org/xml/features/scanner/notify-char-refs")) {
            return this.fDTDScanner.getFeature(featureId);
        }
        if (featureId.equals(STANDARD_URI_CONFORMANT_FEATURE)) {
            return this.fStrictURI;
        }
        if (featureId.equals(BALANCE_SYNTAX_TREES)) {
            return this.fBalanceSyntaxTrees;
        }
        throw new XMLConfigurationException((short) 0, featureId);
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public void setErrorHandler(XMLErrorHandler errorHandler) {
        this.fErrorReporter.setProperty(ERROR_HANDLER, errorHandler);
    }

    public XMLErrorHandler getErrorHandler() {
        return this.fErrorReporter.getErrorHandler();
    }

    public void setEntityResolver(XMLEntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", entityResolver);
    }

    public XMLEntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    public Grammar loadGrammar(XMLInputSource source) throws IOException, XNIException {
        reset();
        XMLDTDDescription desc = new XMLDTDDescription(source.getPublicId(), source.getSystemId(), source.getBaseSystemId(), XMLEntityManager.expandSystemId(source.getSystemId(), source.getBaseSystemId(), this.fStrictURI), null);
        if (this.fBalanceSyntaxTrees) {
            this.fDTDGrammar = new BalancedDTDGrammar(this.fSymbolTable, desc);
        } else {
            this.fDTDGrammar = new DTDGrammar(this.fSymbolTable, desc);
        }
        this.fGrammarBucket = new DTDGrammarBucket();
        this.fGrammarBucket.setStandalone(false);
        this.fGrammarBucket.setActiveGrammar(this.fDTDGrammar);
        try {
            this.fDTDScanner.setInputSource(source);
            this.fDTDScanner.scanDTDExternalSubset(true);
        } catch (EOFException e) {
        } finally {
            this.fEntityManager.closeReaders();
        }
        if (!(this.fDTDGrammar == null || this.fGrammarPool == null)) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[]{this.fDTDGrammar});
        }
        return this.fDTDGrammar;
    }

    public void loadGrammarWithContext(XMLDTDValidator validator, String rootName, String publicId, String systemId, String baseSystemId, String internalSubset) throws IOException, XNIException {
        DTDGrammarBucket grammarBucket = validator.getGrammarBucket();
        DTDGrammar activeGrammar = grammarBucket.getActiveGrammar();
        if (activeGrammar != null && !activeGrammar.isImmutable()) {
            this.fGrammarBucket = grammarBucket;
            this.fEntityManager.setScannerVersion(getScannerVersion());
            reset();
            if (internalSubset != null) {
                try {
                    StringBuffer buffer = new StringBuffer(internalSubset.length() + 2);
                    buffer.append(internalSubset).append("]>");
                    this.fEntityManager.startDocumentEntity(new XMLInputSource(null, baseSystemId, null, new StringReader(buffer.toString()), null));
                    this.fDTDScanner.scanDTDInternalSubset(true, false, systemId != null);
                } catch (EOFException e) {
                    this.fEntityManager.closeReaders();
                    return;
                } catch (Throwable th) {
                    this.fEntityManager.closeReaders();
                }
            }
            if (systemId != null) {
                this.fDTDScanner.setInputSource(this.fEntityManager.resolveEntity(new XMLDTDDescription(publicId, systemId, baseSystemId, null, rootName)));
                this.fDTDScanner.scanDTDExternalSubset(true);
            }
            this.fEntityManager.closeReaders();
        }
    }

    protected void reset() {
        super.reset();
        this.fDTDScanner.reset();
        this.fEntityManager.reset();
        this.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
    }

    protected XMLDTDScannerImpl createDTDScanner(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        return new XMLDTDScannerImpl(symbolTable, errorReporter, entityManager);
    }

    protected short getScannerVersion() {
        return (short) 1;
    }
}
