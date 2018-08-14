package mf.org.apache.xerces.impl.xs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.DOMStringListImpl;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl;
import mf.org.apache.xerces.impl.xs.models.CMBuilder;
import mf.org.apache.xerces.impl.xs.models.CMNodeFactory;
import mf.org.apache.xerces.impl.xs.traversers.XSDHandler;
import mf.org.apache.xerces.util.DOMEntityResolverWrapper;
import mf.org.apache.xerces.util.DOMErrorHandlerWrapper;
import mf.org.apache.xerces.util.DefaultErrorHandler;
import mf.org.apache.xerces.util.MessageFormatter;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarLoader;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.grammars.XSGrammar;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xs.LSInputList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSLoader;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMStringList;
import mf.org.w3c.dom.ls.LSInput;
import mf.org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;

public class XMLSchemaLoader implements XMLGrammarLoader, XMLComponent, XSElementDeclHelper, XSLoader, DOMConfiguration {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String[] RECOGNIZED_FEATURES = new String[]{SCHEMA_FULL_CHECKING, AUGMENT_PSVI, CONTINUE_AFTER_FATAL_ERROR, ALLOW_JAVA_ENCODINGS, STANDARD_URI_CONFORMANT_FEATURE, DISALLOW_DOCTYPE, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, NAMESPACE_GROWTH, TOLERATE_DUPLICATES};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{ENTITY_MANAGER, "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", ERROR_HANDLER, "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/grammar-pool", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, "http://java.sun.com/xml/jaxp/properties/schemaSource", SECURITY_MANAGER, "http://apache.org/xml/properties/locale", SCHEMA_DV_FACTORY};
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private CMBuilder fCMBuilder;
    private XSDeclarationPool fDeclPool;
    private SchemaDVFactory fDefaultSchemaDVFactory;
    private XMLEntityManager fEntityManager;
    private DOMErrorHandlerWrapper fErrorHandler;
    private XMLErrorReporter fErrorReporter;
    private String fExternalNoNSSchema;
    private String fExternalSchemas;
    private XSGrammarBucket fGrammarBucket;
    private XMLGrammarPool fGrammarPool;
    private boolean fIsCheckedFully;
    private WeakHashMap fJAXPCache;
    private boolean fJAXPProcessed;
    private Object fJAXPSource;
    private final ParserConfigurationSettings fLoaderConfig;
    private Locale fLocale;
    private DOMStringList fRecognizedParameters;
    private DOMEntityResolverWrapper fResourceResolver;
    private XSDHandler fSchemaHandler;
    private boolean fSettingsChanged;
    private SubstitutionGroupHandler fSubGroupHandler;
    private XMLEntityResolver fUserEntityResolver;
    private XSDDescription fXSDDescription;

    static class LocationArray {
        int length;
        String[] locations = new String[2];

        LocationArray() {
        }

        public void resize(int oldLength, int newLength) {
            String[] temp = new String[newLength];
            System.arraycopy(this.locations, 0, temp, 0, Math.min(oldLength, newLength));
            this.locations = temp;
            this.length = Math.min(oldLength, newLength);
        }

        public void addLocation(String location) {
            if (this.length >= this.locations.length) {
                resize(this.length, Math.max(1, this.length * 2));
            }
            String[] strArr = this.locations;
            int i = this.length;
            this.length = i + 1;
            strArr[i] = location;
        }

        public String[] getLocationArray() {
            if (this.length < this.locations.length) {
                resize(this.locations.length, this.length);
            }
            return this.locations;
        }

        public String getFirstLocation() {
            return this.length > 0 ? this.locations[0] : null;
        }

        public int getLength() {
            return this.length;
        }
    }

    public XMLSchemaLoader() {
        this(new SymbolTable(), null, new XMLEntityManager(), null, null, null);
    }

    public XMLSchemaLoader(SymbolTable symbolTable) {
        this(symbolTable, null, new XMLEntityManager(), null, null, null);
    }

    XMLSchemaLoader(XMLErrorReporter errorReporter, XSGrammarBucket grammarBucket, SubstitutionGroupHandler sHandler, CMBuilder builder) {
        this(null, errorReporter, null, grammarBucket, sHandler, builder);
    }

    XMLSchemaLoader(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityResolver, XSGrammarBucket grammarBucket, SubstitutionGroupHandler sHandler, CMBuilder builder) {
        this.fLoaderConfig = new ParserConfigurationSettings();
        this.fErrorReporter = new XMLErrorReporter();
        this.fEntityManager = null;
        this.fUserEntityResolver = null;
        this.fGrammarPool = null;
        this.fExternalSchemas = null;
        this.fExternalNoNSSchema = null;
        this.fJAXPSource = null;
        this.fIsCheckedFully = false;
        this.fJAXPProcessed = false;
        this.fSettingsChanged = true;
        this.fDeclPool = null;
        this.fXSDDescription = new XSDDescription();
        this.fLocale = Locale.getDefault();
        this.fRecognizedParameters = null;
        this.fErrorHandler = null;
        this.fResourceResolver = null;
        this.fLoaderConfig.addRecognizedFeatures(RECOGNIZED_FEATURES);
        this.fLoaderConfig.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (errorReporter == null) {
            errorReporter = new XMLErrorReporter();
            errorReporter.setLocale(this.fLocale);
            errorReporter.setProperty(ERROR_HANDLER, new DefaultErrorHandler());
        }
        this.fErrorReporter = errorReporter;
        if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        }
        this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        this.fEntityManager = entityResolver;
        if (this.fEntityManager != null) {
            this.fLoaderConfig.setProperty(ENTITY_MANAGER, this.fEntityManager);
        }
        this.fLoaderConfig.setFeature(AUGMENT_PSVI, true);
        if (grammarBucket == null) {
            grammarBucket = new XSGrammarBucket();
        }
        this.fGrammarBucket = grammarBucket;
        if (sHandler == null) {
            sHandler = new SubstitutionGroupHandler(this);
        }
        this.fSubGroupHandler = sHandler;
        CMNodeFactory nodeFactory = new CMNodeFactory();
        if (builder == null) {
            builder = new CMBuilder(nodeFactory);
        }
        this.fCMBuilder = builder;
        System.out.println("flag1");
        this.fSchemaHandler = new XSDHandler(this.fGrammarBucket);
        this.fJAXPCache = new WeakHashMap();
        this.fSettingsChanged = true;
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        return this.fLoaderConfig.getFeature(featureId);
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        if (featureId.equals(CONTINUE_AFTER_FATAL_ERROR)) {
            this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, state);
        } else if (featureId.equals("http://apache.org/xml/features/generate-synthetic-annotations")) {
            this.fSchemaHandler.setGenerateSyntheticAnnotations(state);
        }
        this.fLoaderConfig.setFeature(featureId, state);
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public Object getProperty(String propertyId) throws XMLConfigurationException {
        return this.fLoaderConfig.getProperty(propertyId);
    }

    public void setProperty(String propertyId, Object state) throws XMLConfigurationException {
        this.fSettingsChanged = true;
        this.fLoaderConfig.setProperty(propertyId, state);
        if (propertyId.equals("http://java.sun.com/xml/jaxp/properties/schemaSource")) {
            this.fJAXPSource = state;
            this.fJAXPProcessed = false;
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            this.fGrammarPool = (XMLGrammarPool) state;
        } else if (propertyId.equals(SCHEMA_LOCATION)) {
            this.fExternalSchemas = (String) state;
        } else if (propertyId.equals(SCHEMA_NONS_LOCATION)) {
            this.fExternalNoNSSchema = (String) state;
        } else if (propertyId.equals("http://apache.org/xml/properties/locale")) {
            setLocale((Locale) state);
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", state);
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            this.fErrorReporter = (XMLErrorReporter) state;
            if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
            }
        }
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
        this.fUserEntityResolver = entityResolver;
        this.fLoaderConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", entityResolver);
        this.fEntityManager.setProperty("http://apache.org/xml/properties/internal/entity-resolver", entityResolver);
    }

    public XMLEntityResolver getEntityResolver() {
        return this.fUserEntityResolver;
    }

    public void loadGrammar(XMLInputSource[] source) throws IOException, XNIException {
        for (XMLInputSource loadGrammar : source) {
            loadGrammar(loadGrammar);
        }
    }

    public Grammar loadGrammar(XMLInputSource source) throws IOException, XNIException {
        reset(this.fLoaderConfig);
        this.fSettingsChanged = false;
        XSDDescription desc = new XSDDescription();
        desc.fContextType = (short) 3;
        desc.setBaseSystemId(source.getBaseSystemId());
        desc.setLiteralSystemId(source.getSystemId());
        Hashtable locationPairs = new Hashtable();
        processExternalHints(this.fExternalSchemas, this.fExternalNoNSSchema, locationPairs, this.fErrorReporter);
        SchemaGrammar grammar = loadSchema(desc, source, locationPairs);
        if (!(grammar == null || this.fGrammarPool == null)) {
            this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", this.fGrammarBucket.getGrammars());
            if (this.fIsCheckedFully && this.fJAXPCache.get(grammar) != grammar) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
            }
        }
        return grammar;
    }

    SchemaGrammar loadSchema(XSDDescription desc, XMLInputSource source, Hashtable locationPairs) throws IOException, XNIException {
        if (!this.fJAXPProcessed) {
            processJAXPSchemaSource(locationPairs);
        }
        return this.fSchemaHandler.parseSchema(source, desc, locationPairs);
    }

    public static XMLInputSource resolveDocument(XSDDescription desc, Hashtable locationPairs, XMLEntityResolver entityResolver) throws IOException {
        String loc = null;
        if (desc.getContextType() == (short) 2 || desc.fromInstance()) {
            String ns;
            String namespace = desc.getTargetNamespace();
            if (namespace == null) {
                ns = XMLSymbols.EMPTY_STRING;
            } else {
                ns = namespace;
            }
            LocationArray tempLA = (LocationArray) locationPairs.get(ns);
            if (tempLA != null) {
                loc = tempLA.getFirstLocation();
            }
        }
        if (loc == null) {
            String[] hints = desc.getLocationHints();
            if (hints != null && hints.length > 0) {
                loc = hints[0];
            }
        }
        String expandedLoc = XMLEntityManager.expandSystemId(loc, desc.getBaseSystemId(), false);
        desc.setLiteralSystemId(loc);
        desc.setExpandedSystemId(expandedLoc);
        return entityResolver.resolveEntity(desc);
    }

    public static void processExternalHints(String sl, String nsl, Hashtable locations, XMLErrorReporter er) {
        if (sl != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION).fType.validate(sl, null, null);
                if (!tokenizeSchemaLocationStr(sl, locations, null)) {
                    er.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "SchemaLocation", new Object[]{sl}, (short) 0);
                }
            } catch (InvalidDatatypeValueException ex) {
                er.reportError(XSMessageFormatter.SCHEMA_DOMAIN, ex.getKey(), ex.getArgs(), (short) 0);
            }
        }
        if (nsl != null) {
            try {
                SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION).fType.validate(nsl, null, null);
                LocationArray la = (LocationArray) locations.get(XMLSymbols.EMPTY_STRING);
                if (la == null) {
                    la = new LocationArray();
                    locations.put(XMLSymbols.EMPTY_STRING, la);
                }
                la.addLocation(nsl);
            } catch (InvalidDatatypeValueException ex2) {
                er.reportError(XSMessageFormatter.SCHEMA_DOMAIN, ex2.getKey(), ex2.getArgs(), (short) 0);
            }
        }
    }

    public static boolean tokenizeSchemaLocationStr(String schemaStr, Hashtable locations, String base) {
        if (schemaStr != null) {
            StringTokenizer t = new StringTokenizer(schemaStr, " \n\t\r");
            while (t.hasMoreTokens()) {
                String namespace = t.nextToken();
                if (!t.hasMoreTokens()) {
                    return false;
                }
                String location = t.nextToken();
                LocationArray la = (LocationArray) locations.get(namespace);
                if (la == null) {
                    la = new LocationArray();
                    locations.put(namespace, la);
                }
                if (base != null) {
                    try {
                        location = XMLEntityManager.expandSystemId(location, base, false);
                    } catch (MalformedURIException e) {
                    }
                }
                la.addLocation(location);
            }
        }
        return true;
    }

    private void processJAXPSchemaSource(Hashtable locationPairs) throws IOException {
        this.fJAXPProcessed = true;
        if (this.fJAXPSource != null) {
            Class componentType = this.fJAXPSource.getClass().getComponentType();
            SchemaGrammar g;
            XMLInputSource xis;
            String sid;
            String[] strArr;
            if (componentType == null) {
                if ((this.fJAXPSource instanceof InputStream) || (this.fJAXPSource instanceof InputSource)) {
                    g = (SchemaGrammar) this.fJAXPCache.get(this.fJAXPSource);
                    if (g != null) {
                        this.fGrammarBucket.putGrammar(g);
                        return;
                    }
                }
                this.fXSDDescription.reset();
                xis = xsdToXMLInputSource(this.fJAXPSource);
                sid = xis.getSystemId();
                this.fXSDDescription.fContextType = (short) 3;
                if (sid != null) {
                    this.fXSDDescription.setBaseSystemId(xis.getBaseSystemId());
                    this.fXSDDescription.setLiteralSystemId(sid);
                    this.fXSDDescription.setExpandedSystemId(sid);
                    strArr = new String[]{sid};
                    this.fXSDDescription.fLocationHints = strArr;
                }
                g = loadSchema(this.fXSDDescription, xis, locationPairs);
                if (g != null) {
                    if ((this.fJAXPSource instanceof InputStream) || (this.fJAXPSource instanceof InputSource)) {
                        this.fJAXPCache.put(this.fJAXPSource, g);
                        if (this.fIsCheckedFully) {
                            XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
                        }
                    }
                    this.fGrammarBucket.putGrammar(g);
                }
            } else if (componentType == Object.class || componentType == String.class || componentType == File.class || componentType == InputStream.class || componentType == InputSource.class || File.class.isAssignableFrom(componentType) || InputStream.class.isAssignableFrom(componentType) || InputSource.class.isAssignableFrom(componentType) || componentType.isInterface()) {
                Object[] objArr = this.fJAXPSource;
                ArrayList jaxpSchemaSourceNamespaces = new ArrayList();
                int i = 0;
                while (i < objArr.length) {
                    if ((objArr[i] instanceof InputStream) || (objArr[i] instanceof InputSource)) {
                        g = (SchemaGrammar) this.fJAXPCache.get(objArr[i]);
                        if (g != null) {
                            this.fGrammarBucket.putGrammar(g);
                            i++;
                        }
                    }
                    this.fXSDDescription.reset();
                    xis = xsdToXMLInputSource(objArr[i]);
                    sid = xis.getSystemId();
                    this.fXSDDescription.fContextType = (short) 3;
                    if (sid != null) {
                        this.fXSDDescription.setBaseSystemId(xis.getBaseSystemId());
                        this.fXSDDescription.setLiteralSystemId(sid);
                        this.fXSDDescription.setExpandedSystemId(sid);
                        strArr = new String[]{sid};
                        this.fXSDDescription.fLocationHints = strArr;
                    }
                    SchemaGrammar grammar = this.fSchemaHandler.parseSchema(xis, this.fXSDDescription, locationPairs);
                    if (this.fIsCheckedFully) {
                        XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fErrorReporter);
                    }
                    if (grammar != null) {
                        String targetNamespace = grammar.getTargetNamespace();
                        if (jaxpSchemaSourceNamespaces.contains(targetNamespace)) {
                            throw new IllegalArgumentException(this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-ns", null));
                        }
                        jaxpSchemaSourceNamespaces.add(targetNamespace);
                        if ((objArr[i] instanceof InputStream) || (objArr[i] instanceof InputSource)) {
                            this.fJAXPCache.put(objArr[i], grammar);
                        }
                        this.fGrammarBucket.putGrammar(grammar);
                    } else {
                        continue;
                    }
                    i++;
                }
            } else {
                throw new XMLConfigurationException((short) 1, this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN).formatMessage(this.fErrorReporter.getLocale(), "jaxp12-schema-source-type.2", new Object[]{componentType.getName()}));
            }
        }
    }

    private XMLInputSource xsdToXMLInputSource(Object val) {
        if (val instanceof String) {
            String loc = (String) val;
            this.fXSDDescription.reset();
            this.fXSDDescription.setValues(null, loc, null, null);
            XMLInputSource xis = null;
            try {
                xis = this.fEntityManager.resolveEntity(this.fXSDDescription);
            } catch (IOException e) {
                this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.4", new Object[]{loc}, (short) 1);
            }
            if (xis == null) {
                return new XMLInputSource(null, loc, null);
            }
            return xis;
        } else if (val instanceof InputSource) {
            return saxToXMLInputSource((InputSource) val);
        } else {
            if (val instanceof InputStream) {
                return new XMLInputSource(null, null, null, (InputStream) val, null);
            }
            if (val instanceof File) {
                File file = (File) val;
                String escapedURI = FilePathToURI.filepath2URI(file.getAbsolutePath());
                InputStream is = null;
                try {
                    is = new BufferedInputStream(new FileInputStream(file));
                } catch (FileNotFoundException e2) {
                    this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.4", new Object[]{file.toString()}, (short) 1);
                }
                return new XMLInputSource(null, escapedURI, null, is, null);
            }
            MessageFormatter mf = this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN);
            Locale locale = this.fErrorReporter.getLocale();
            String str = "jaxp12-schema-source-type.1";
            Object[] objArr = new Object[1];
            objArr[0] = val != null ? val.getClass().getName() : "null";
            throw new XMLConfigurationException((short) 1, mf.formatMessage(locale, str, objArr));
        }
    }

    private static XMLInputSource saxToXMLInputSource(InputSource sis) {
        String publicId = sis.getPublicId();
        String systemId = sis.getSystemId();
        Reader charStream = sis.getCharacterStream();
        if (charStream != null) {
            return new XMLInputSource(publicId, systemId, null, charStream, null);
        }
        InputStream byteStream = sis.getByteStream();
        if (byteStream == null) {
            return new XMLInputSource(publicId, systemId, null);
        }
        return new XMLInputSource(publicId, systemId, null, byteStream, sis.getEncoding());
    }

    public Boolean getFeatureDefault(String featureId) {
        if (featureId.equals(AUGMENT_PSVI)) {
            return Boolean.TRUE;
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        return null;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fGrammarBucket.reset();
        this.fSubGroupHandler.reset();
        if (this.fSettingsChanged && parserSettingsUpdated(componentManager)) {
            boolean psvi;
            this.fEntityManager = (XMLEntityManager) componentManager.getProperty(ENTITY_MANAGER);
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            SchemaDVFactory dvFactory = null;
            try {
                dvFactory = (SchemaDVFactory) componentManager.getProperty(SCHEMA_DV_FACTORY);
            } catch (XMLConfigurationException e) {
            }
            if (dvFactory == null) {
                if (this.fDefaultSchemaDVFactory == null) {
                    this.fDefaultSchemaDVFactory = SchemaDVFactory.getInstance();
                }
                dvFactory = this.fDefaultSchemaDVFactory;
            }
            this.fSchemaHandler.setDVFactory(dvFactory);
            try {
                this.fExternalSchemas = (String) componentManager.getProperty(SCHEMA_LOCATION);
                this.fExternalNoNSSchema = (String) componentManager.getProperty(SCHEMA_NONS_LOCATION);
            } catch (XMLConfigurationException e2) {
                this.fExternalSchemas = null;
                this.fExternalNoNSSchema = null;
            }
            try {
                this.fJAXPSource = componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
                this.fJAXPProcessed = false;
            } catch (XMLConfigurationException e3) {
                this.fJAXPSource = null;
                this.fJAXPProcessed = false;
            }
            try {
                this.fGrammarPool = (XMLGrammarPool) componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
            } catch (XMLConfigurationException e4) {
                this.fGrammarPool = null;
            }
            initGrammarBucket();
            try {
                psvi = componentManager.getFeature(AUGMENT_PSVI);
            } catch (XMLConfigurationException e5) {
                psvi = false;
            }
            if (psvi) {
                this.fCMBuilder.setDeclPool(null);
                this.fSchemaHandler.setDeclPool(null);
            } else {
                this.fCMBuilder.setDeclPool(null);
                this.fSchemaHandler.setDeclPool(null);
            }
            if (dvFactory instanceof SchemaDVFactoryImpl) {
                ((SchemaDVFactoryImpl) dvFactory).setDeclPool(null);
            }
            try {
                this.fErrorReporter.setFeature(CONTINUE_AFTER_FATAL_ERROR, componentManager.getFeature(CONTINUE_AFTER_FATAL_ERROR));
            } catch (XMLConfigurationException e6) {
            }
            try {
                this.fIsCheckedFully = componentManager.getFeature(SCHEMA_FULL_CHECKING);
            } catch (XMLConfigurationException e7) {
                this.fIsCheckedFully = false;
            }
            try {
                this.fSchemaHandler.setGenerateSyntheticAnnotations(componentManager.getFeature("http://apache.org/xml/features/generate-synthetic-annotations"));
            } catch (XMLConfigurationException e8) {
                this.fSchemaHandler.setGenerateSyntheticAnnotations(false);
            }
            this.fSchemaHandler.reset(componentManager);
            return;
        }
        this.fJAXPProcessed = false;
        initGrammarBucket();
        if (this.fDeclPool != null) {
            this.fDeclPool.reset();
        }
    }

    private boolean parserSettingsUpdated(XMLComponentManager componentManager) {
        if (componentManager != this.fLoaderConfig) {
            try {
                return componentManager.getFeature(PARSER_SETTINGS);
            } catch (XMLConfigurationException e) {
            }
        }
        return true;
    }

    private void initGrammarBucket() {
        if (this.fGrammarPool != null) {
            int length;
            Grammar[] initialGrammars = this.fGrammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema");
            if (initialGrammars != null) {
                length = initialGrammars.length;
            } else {
                length = 0;
            }
            for (int i = 0; i < length; i++) {
                if (!this.fGrammarBucket.putGrammar((SchemaGrammar) initialGrammars[i], true)) {
                    this.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "GrammarConflict", null, (short) 0);
                }
            }
        }
    }

    public DOMConfiguration getConfig() {
        return this;
    }

    public XSModel load(LSInput is) {
        try {
            return ((XSGrammar) loadGrammar(dom2xmlInputSource(is))).toXSModel();
        } catch (Exception e) {
            reportDOMFatalError(e);
            return null;
        }
    }

    public XSModel loadInputList(LSInputList is) {
        int length = is.getLength();
        SchemaGrammar[] gs = new SchemaGrammar[length];
        int i = 0;
        while (i < length) {
            try {
                gs[i] = (SchemaGrammar) loadGrammar(dom2xmlInputSource(is.item(i)));
                i++;
            } catch (Exception e) {
                reportDOMFatalError(e);
                return null;
            }
        }
        return new XSModelImpl(gs);
    }

    public XSModel loadURI(String uri) {
        XSModel xSModel = null;
        try {
            xSModel = ((XSGrammar) loadGrammar(new XMLInputSource(null, uri, null))).toXSModel();
        } catch (Exception e) {
            reportDOMFatalError(e);
        }
        return xSModel;
    }

    public XSModel loadURIList(StringList uriList) {
        int length = uriList.getLength();
        SchemaGrammar[] gs = new SchemaGrammar[length];
        int i = 0;
        while (i < length) {
            try {
                gs[i] = (SchemaGrammar) loadGrammar(new XMLInputSource(null, uriList.item(i), null));
                i++;
            } catch (Exception e) {
                reportDOMFatalError(e);
                return null;
            }
        }
        return new XSModelImpl(gs);
    }

    void reportDOMFatalError(Exception e) {
        if (this.fErrorHandler != null) {
            DOMErrorImpl error = new DOMErrorImpl();
            error.fException = e;
            error.fMessage = e.getMessage();
            error.fSeverity = (short) 3;
            this.fErrorHandler.getErrorHandler().handleError(error);
        }
    }

    public boolean canSetParameter(String name, Object value) {
        if (value instanceof Boolean) {
            if (name.equals(Constants.DOM_VALIDATE) || name.equals(SCHEMA_FULL_CHECKING) || name.equals(VALIDATE_ANNOTATIONS) || name.equals(CONTINUE_AFTER_FATAL_ERROR) || name.equals(ALLOW_JAVA_ENCODINGS) || name.equals(STANDARD_URI_CONFORMANT_FEATURE) || name.equals("http://apache.org/xml/features/generate-synthetic-annotations") || name.equals(HONOUR_ALL_SCHEMALOCATIONS) || name.equals(NAMESPACE_GROWTH) || name.equals(TOLERATE_DUPLICATES)) {
                return true;
            }
            return false;
        } else if (name.equals(Constants.DOM_ERROR_HANDLER) || name.equals(Constants.DOM_RESOURCE_RESOLVER) || name.equals("http://apache.org/xml/properties/internal/symbol-table") || name.equals("http://apache.org/xml/properties/internal/error-reporter") || name.equals(ERROR_HANDLER) || name.equals("http://apache.org/xml/properties/internal/entity-resolver") || name.equals("http://apache.org/xml/properties/internal/grammar-pool") || name.equals(SCHEMA_LOCATION) || name.equals(SCHEMA_NONS_LOCATION) || name.equals("http://java.sun.com/xml/jaxp/properties/schemaSource") || name.equals(SCHEMA_DV_FACTORY)) {
            return true;
        } else {
            return false;
        }
    }

    public Object getParameter(String name) throws DOMException {
        if (name.equals(Constants.DOM_ERROR_HANDLER)) {
            if (this.fErrorHandler != null) {
                return this.fErrorHandler.getErrorHandler();
            }
            return null;
        } else if (!name.equals(Constants.DOM_RESOURCE_RESOLVER)) {
            try {
                return getFeature(name) ? Boolean.TRUE : Boolean.FALSE;
            } catch (Exception e) {
                try {
                    return getProperty(name);
                } catch (Exception e2) {
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
                }
            }
        } else if (this.fResourceResolver != null) {
            return this.fResourceResolver.getEntityResolver();
        } else {
            return null;
        }
    }

    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList v = new ArrayList();
            v.add(Constants.DOM_VALIDATE);
            v.add(Constants.DOM_ERROR_HANDLER);
            v.add(Constants.DOM_RESOURCE_RESOLVER);
            v.add("http://apache.org/xml/properties/internal/symbol-table");
            v.add("http://apache.org/xml/properties/internal/error-reporter");
            v.add(ERROR_HANDLER);
            v.add("http://apache.org/xml/properties/internal/entity-resolver");
            v.add("http://apache.org/xml/properties/internal/grammar-pool");
            v.add(SCHEMA_LOCATION);
            v.add(SCHEMA_NONS_LOCATION);
            v.add("http://java.sun.com/xml/jaxp/properties/schemaSource");
            v.add(SCHEMA_FULL_CHECKING);
            v.add(CONTINUE_AFTER_FATAL_ERROR);
            v.add(ALLOW_JAVA_ENCODINGS);
            v.add(STANDARD_URI_CONFORMANT_FEATURE);
            v.add(VALIDATE_ANNOTATIONS);
            v.add("http://apache.org/xml/features/generate-synthetic-annotations");
            v.add(HONOUR_ALL_SCHEMALOCATIONS);
            v.add(NAMESPACE_GROWTH);
            v.add(TOLERATE_DUPLICATES);
            this.fRecognizedParameters = new DOMStringListImpl(v);
        }
        return this.fRecognizedParameters;
    }

    public void setParameter(String name, Object value) throws DOMException {
        if (value instanceof Boolean) {
            boolean state = ((Boolean) value).booleanValue();
            if (!name.equals(Constants.DOM_VALIDATE) || !state) {
                try {
                    setFeature(name, state);
                } catch (Exception e) {
                    throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
                }
            }
        } else if (name.equals(Constants.DOM_ERROR_HANDLER)) {
            if (value instanceof DOMErrorHandler) {
                try {
                    this.fErrorHandler = new DOMErrorHandlerWrapper((DOMErrorHandler) value);
                    setErrorHandler(this.fErrorHandler);
                    return;
                } catch (XMLConfigurationException e2) {
                    return;
                }
            }
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
        } else if (!name.equals(Constants.DOM_RESOURCE_RESOLVER)) {
            try {
                setProperty(name, value);
            } catch (Exception e3) {
                throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
            }
        } else if (value instanceof LSResourceResolver) {
            try {
                this.fResourceResolver = new DOMEntityResolverWrapper((LSResourceResolver) value);
                setEntityResolver(this.fResourceResolver);
            } catch (XMLConfigurationException e4) {
            }
        } else {
            throw new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{name}));
        }
    }

    XMLInputSource dom2xmlInputSource(LSInput is) {
        if (is.getCharacterStream() != null) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getCharacterStream(), "UTF-16");
        }
        if (is.getByteStream() != null) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), is.getByteStream(), is.getEncoding());
        }
        if (is.getStringData() == null || is.getStringData().length() == 0) {
            return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI());
        }
        return new XMLInputSource(is.getPublicId(), is.getSystemId(), is.getBaseURI(), new StringReader(is.getStringData()), "UTF-16");
    }

    public XSElementDecl getGlobalElementDecl(QName element) {
        SchemaGrammar sGrammar = this.fGrammarBucket.getGrammar(element.uri);
        if (sGrammar != null) {
            return sGrammar.getGlobalElementDecl(element.localpart);
        }
        return null;
    }
}
