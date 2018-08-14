package mf.org.apache.xerces.xinclude;

import android.support.v4.internal.view.SupportMenu;
import com.bgjd.ici.p025b.C1408j.C1404b;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;
import java.util.StringTokenizer;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.HTTPInputSource;
import mf.org.apache.xerces.util.IntStack;
import mf.org.apache.xerces.util.ParserConfigurationSettings;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLLocatorWrapper;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDFilter;
import mf.org.apache.xerces.xni.parser.XMLDTDSource;
import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.apache.xerces.xpointer.XPointerHandler;
import mf.org.apache.xerces.xpointer.XPointerProcessor;

public class XIncludeHandler implements XMLComponent, XMLDocumentFilter, XMLDTDFilter {
    protected static final String ALLOW_UE_AND_NOTATION_EVENTS = "http://xml.org/sax/features/allow-dtd-events-after-endDTD";
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    public static final String CURRENT_BASE_URI = "currentBaseURI";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS = new Boolean[]{Boolean.TRUE, Boolean.TRUE, Boolean.TRUE};
    public static final String HTTP_ACCEPT = "Accept";
    public static final String HTTP_ACCEPT_LANGUAGE = "Accept-Language";
    private static final int INITIAL_SIZE = 8;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final QName NEW_NS_ATTR_QNAME = new QName(XMLSymbols.PREFIX_XMLNS, "", XMLSymbols.PREFIX_XMLNS + ":", NamespaceContext.XMLNS_URI);
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_FEATURES = new String[]{ALLOW_UE_AND_NOTATION_EVENTS, XINCLUDE_FIXUP_BASE_URIS, XINCLUDE_FIXUP_LANGUAGE};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", SECURITY_MANAGER, BUFFER_SIZE};
    protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final int STATE_EXPECT_FALLBACK = 3;
    private static final int STATE_IGNORE = 2;
    private static final int STATE_NORMAL_PROCESSING = 1;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    public static final String XINCLUDE_ATTR_ACCEPT = C1404b.f2108L.intern();
    public static final String XINCLUDE_ATTR_ACCEPT_LANGUAGE = "accept-language".intern();
    public static final String XINCLUDE_ATTR_ENCODING = OutputKeys.ENCODING.intern();
    public static final String XINCLUDE_ATTR_HREF = "href".intern();
    public static final String XINCLUDE_ATTR_PARSE = "parse".intern();
    private static final String XINCLUDE_BASE = "base".intern();
    public static final String XINCLUDE_DEFAULT_CONFIGURATION = "mf.org.apache.xerces.parsers.XIncludeParserConfiguration";
    public static final String XINCLUDE_FALLBACK = "fallback".intern();
    protected static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
    protected static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
    public static final String XINCLUDE_INCLUDE = "include".intern();
    public static final String XINCLUDE_INCLUDED = "[included]".intern();
    private static final String XINCLUDE_LANG = "lang".intern();
    public static final String XINCLUDE_NS_URI = "http://www.w3.org/2001/XInclude".intern();
    public static final String XINCLUDE_PARSE_TEXT = "text".intern();
    public static final String XINCLUDE_PARSE_XML = "xml".intern();
    private static final QName XML_BASE_QNAME = new QName(XMLSymbols.PREFIX_XML, XINCLUDE_BASE, (XMLSymbols.PREFIX_XML + ":" + XINCLUDE_BASE).intern(), NamespaceContext.XML_URI);
    private static final QName XML_LANG_QNAME = new QName(XMLSymbols.PREFIX_XML, XINCLUDE_LANG, (XMLSymbols.PREFIX_XML + ":" + XINCLUDE_LANG).intern(), NamespaceContext.XML_URI);
    public static final String XPOINTER = "xpointer";
    private static final char[] gAfterEscaping1 = new char[128];
    private static final char[] gAfterEscaping2 = new char[128];
    private static final char[] gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final boolean[] gNeedEscaping = new boolean[128];
    protected final Stack fBaseURI;
    protected final IntStack fBaseURIScope;
    protected int fBufferSize = 2048;
    protected XMLParserConfiguration fChildConfig;
    protected final XMLResourceIdentifier fCurrentBaseURI;
    protected String fCurrentLanguage;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDSource fDTDSource;
    private int fDepth = 0;
    protected XMLLocator fDocLocation;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected XMLEntityResolver fEntityResolver;
    protected XMLErrorReporter fErrorReporter;
    protected final Stack fExpandedSystemID;
    private boolean fFixupBaseURIs = true;
    private boolean fFixupLanguage = true;
    boolean fHasIncludeReportedContent;
    protected String fHrefFromParent;
    private boolean fInDTD;
    private boolean fIsXML11;
    protected final IntStack fLanguageScope;
    protected final Stack fLanguageStack;
    protected final Stack fLiteralSystemID;
    protected XIncludeNamespaceSupport fNamespaceContext;
    private boolean fNeedCopyFeatures = true;
    private final ArrayList fNotations;
    protected String fParentRelativeURI;
    protected XIncludeHandler fParentXIncludeHandler;
    private int fResultDepth;
    private boolean[] fSawFallback = new boolean[8];
    private boolean[] fSawInclude = new boolean[8];
    protected SecurityManager fSecurityManager;
    private boolean fSeenRootElement;
    private boolean fSendUEAndNotationEvents;
    protected ParserConfigurationSettings fSettings;
    private int[] fState = new int[8];
    protected SymbolTable fSymbolTable;
    private final ArrayList fUnparsedEntities;
    protected XIncludeTextReader fXInclude10TextReader;
    protected XIncludeTextReader fXInclude11TextReader;
    protected XMLParserConfiguration fXIncludeChildConfig;
    protected XMLLocatorWrapper fXIncludeLocator = new XMLLocatorWrapper();
    protected XIncludeMessageFormatter fXIncludeMessageFormatter = new XIncludeMessageFormatter();
    protected XMLParserConfiguration fXPointerChildConfig;
    protected XPointerProcessor fXPtrProcessor = null;

    protected static class Notation {
        public Augmentations augmentations;
        public String baseURI;
        public String expandedSystemId;
        public String name;
        public String publicId;
        public String systemId;

        protected Notation() {
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Notation)) {
                return false;
            }
            return this.name.equals(((Notation) obj).name);
        }

        public boolean isDuplicate(Object obj) {
            if (obj == null || !(obj instanceof Notation)) {
                return false;
            }
            Notation other = (Notation) obj;
            if (this.name.equals(other.name) && isEqual(this.publicId, other.publicId) && isEqual(this.expandedSystemId, other.expandedSystemId)) {
                return true;
            }
            return false;
        }

        private boolean isEqual(String one, String two) {
            return one == two || (one != null && one.equals(two));
        }
    }

    protected static class UnparsedEntity {
        public Augmentations augmentations;
        public String baseURI;
        public String expandedSystemId;
        public String name;
        public String notation;
        public String publicId;
        public String systemId;

        protected UnparsedEntity() {
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof UnparsedEntity)) {
                return false;
            }
            return this.name.equals(((UnparsedEntity) obj).name);
        }

        public boolean isDuplicate(Object obj) {
            if (obj == null || !(obj instanceof UnparsedEntity)) {
                return false;
            }
            UnparsedEntity other = (UnparsedEntity) obj;
            if (this.name.equals(other.name) && isEqual(this.publicId, other.publicId) && isEqual(this.expandedSystemId, other.expandedSystemId) && isEqual(this.notation, other.notation)) {
                return true;
            }
            return false;
        }

        private boolean isEqual(String one, String two) {
            return one == two || (one != null && one.equals(two));
        }
    }

    static {
        Object[] objArr = new Object[4];
        objArr[3] = new Integer(2048);
        PROPERTY_DEFAULTS = objArr;
        for (char ch : new char[]{' ', '<', '>', '\"', '{', '}', '|', '\\', '^', '`'}) {
            gNeedEscaping[ch] = true;
            gAfterEscaping1[ch] = gHexChs[ch >> 4];
            gAfterEscaping2[ch] = gHexChs[ch & 15];
        }
    }

    public XIncludeHandler() {
        this.fSawFallback[this.fDepth] = false;
        this.fSawInclude[this.fDepth] = false;
        this.fState[this.fDepth] = 1;
        this.fNotations = new ArrayList();
        this.fUnparsedEntities = new ArrayList();
        this.fBaseURIScope = new IntStack();
        this.fBaseURI = new Stack();
        this.fLiteralSystemID = new Stack();
        this.fExpandedSystemID = new Stack();
        this.fCurrentBaseURI = new XMLResourceIdentifierImpl();
        this.fLanguageScope = new IntStack();
        this.fLanguageStack = new Stack();
        this.fCurrentLanguage = null;
    }

    public void reset(XMLComponentManager componentManager) throws XNIException {
        int i;
        int i2;
        this.fNamespaceContext = null;
        this.fDepth = 0;
        if (isRootDocument()) {
            i = 0;
        } else {
            i = this.fParentXIncludeHandler.getResultDepth();
        }
        this.fResultDepth = i;
        this.fNotations.clear();
        this.fUnparsedEntities.clear();
        this.fParentRelativeURI = null;
        this.fIsXML11 = false;
        this.fInDTD = false;
        this.fSeenRootElement = false;
        this.fBaseURIScope.clear();
        this.fBaseURI.clear();
        this.fLiteralSystemID.clear();
        this.fExpandedSystemID.clear();
        this.fLanguageScope.clear();
        this.fLanguageStack.clear();
        for (i2 = 0; i2 < this.fState.length; i2++) {
            this.fState[i2] = 1;
        }
        for (i2 = 0; i2 < this.fSawFallback.length; i2++) {
            this.fSawFallback[i2] = false;
        }
        for (i2 = 0; i2 < this.fSawInclude.length; i2++) {
            this.fSawInclude[i2] = false;
        }
        try {
            if (!componentManager.getFeature(PARSER_SETTINGS)) {
                return;
            }
        } catch (XMLConfigurationException e) {
        }
        this.fNeedCopyFeatures = true;
        try {
            this.fSendUEAndNotationEvents = componentManager.getFeature(ALLOW_UE_AND_NOTATION_EVENTS);
            if (this.fChildConfig != null) {
                this.fChildConfig.setFeature(ALLOW_UE_AND_NOTATION_EVENTS, this.fSendUEAndNotationEvents);
            }
        } catch (XMLConfigurationException e2) {
        }
        try {
            this.fFixupBaseURIs = componentManager.getFeature(XINCLUDE_FIXUP_BASE_URIS);
            if (this.fChildConfig != null) {
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
            }
        } catch (XMLConfigurationException e3) {
            this.fFixupBaseURIs = true;
        }
        try {
            this.fFixupLanguage = componentManager.getFeature(XINCLUDE_FIXUP_LANGUAGE);
            if (this.fChildConfig != null) {
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
            }
        } catch (XMLConfigurationException e4) {
            this.fFixupLanguage = true;
        }
        try {
            SymbolTable value = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            if (value != null) {
                this.fSymbolTable = value;
                if (this.fChildConfig != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", value);
                }
            }
        } catch (XMLConfigurationException e5) {
            this.fSymbolTable = null;
        }
        try {
            XMLErrorReporter value2 = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            if (value2 != null) {
                setErrorReporter(value2);
                if (this.fChildConfig != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", value2);
                }
            }
        } catch (XMLConfigurationException e6) {
            this.fErrorReporter = null;
        }
        try {
            XMLEntityResolver value3 = (XMLEntityResolver) componentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (value3 != null) {
                this.fEntityResolver = value3;
                if (this.fChildConfig != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", value3);
                }
            }
        } catch (XMLConfigurationException e7) {
            this.fEntityResolver = null;
        }
        try {
            SecurityManager value4 = (SecurityManager) componentManager.getProperty(SECURITY_MANAGER);
            if (value4 != null) {
                this.fSecurityManager = value4;
                if (this.fChildConfig != null) {
                    this.fChildConfig.setProperty(SECURITY_MANAGER, value4);
                }
            }
        } catch (XMLConfigurationException e8) {
            this.fSecurityManager = null;
        }
        try {
            Integer value5 = (Integer) componentManager.getProperty(BUFFER_SIZE);
            if (value5 == null || value5.intValue() <= 0) {
                this.fBufferSize = ((Integer) getPropertyDefault(BUFFER_SIZE)).intValue();
                if (this.fXInclude10TextReader != null) {
                    this.fXInclude10TextReader.setBufferSize(this.fBufferSize);
                }
                if (this.fXInclude11TextReader != null) {
                    this.fXInclude11TextReader.setBufferSize(this.fBufferSize);
                }
                this.fSettings = new ParserConfigurationSettings();
                copyFeatures(componentManager, this.fSettings);
                try {
                    if (componentManager.getFeature(SCHEMA_VALIDATION)) {
                        this.fSettings.setFeature(SCHEMA_VALIDATION, false);
                        if (Constants.NS_XMLSCHEMA.equals(componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))) {
                            this.fSettings.setFeature(VALIDATION, false);
                        } else if (componentManager.getFeature(VALIDATION)) {
                            this.fSettings.setFeature(DYNAMIC_VALIDATION, true);
                        }
                    }
                } catch (XMLConfigurationException e9) {
                    return;
                }
            }
            this.fBufferSize = value5.intValue();
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(BUFFER_SIZE, value5);
            }
            if (this.fXInclude10TextReader != null) {
                this.fXInclude10TextReader.setBufferSize(this.fBufferSize);
            }
            if (this.fXInclude11TextReader != null) {
                this.fXInclude11TextReader.setBufferSize(this.fBufferSize);
            }
            this.fSettings = new ParserConfigurationSettings();
            copyFeatures(componentManager, this.fSettings);
            if (componentManager.getFeature(SCHEMA_VALIDATION)) {
                this.fSettings.setFeature(SCHEMA_VALIDATION, false);
                if (Constants.NS_XMLSCHEMA.equals(componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage"))) {
                    this.fSettings.setFeature(VALIDATION, false);
                } else if (componentManager.getFeature(VALIDATION)) {
                    this.fSettings.setFeature(DYNAMIC_VALIDATION, true);
                }
            }
        } catch (XMLConfigurationException e10) {
            this.fBufferSize = ((Integer) getPropertyDefault(BUFFER_SIZE)).intValue();
        }
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        if (featureId.equals(ALLOW_UE_AND_NOTATION_EVENTS)) {
            this.fSendUEAndNotationEvents = state;
        }
        if (this.fSettings != null) {
            this.fNeedCopyFeatures = true;
            this.fSettings.setFeature(featureId, state);
        }
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.equals("http://apache.org/xml/properties/internal/symbol-table")) {
            this.fSymbolTable = (SymbolTable) value;
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(propertyId, value);
            }
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/error-reporter")) {
            setErrorReporter((XMLErrorReporter) value);
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(propertyId, value);
            }
        } else if (propertyId.equals("http://apache.org/xml/properties/internal/entity-resolver")) {
            this.fEntityResolver = (XMLEntityResolver) value;
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(propertyId, value);
            }
        } else if (propertyId.equals(SECURITY_MANAGER)) {
            this.fSecurityManager = (SecurityManager) value;
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(propertyId, value);
            }
        } else if (propertyId.equals(BUFFER_SIZE)) {
            Integer bufferSize = (Integer) value;
            if (this.fChildConfig != null) {
                this.fChildConfig.setProperty(propertyId, value);
            }
            if (bufferSize != null && bufferSize.intValue() > 0) {
                this.fBufferSize = bufferSize.intValue();
                if (this.fXInclude10TextReader != null) {
                    this.fXInclude10TextReader.setBufferSize(this.fBufferSize);
                }
                if (this.fXInclude11TextReader != null) {
                    this.fXInclude11TextReader.setBufferSize(this.fBufferSize);
                }
            }
        }
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    public void setDocumentHandler(XMLDocumentHandler handler) {
        if (this.fDocumentHandler != handler) {
            this.fDocumentHandler = handler;
            if (this.fXIncludeChildConfig != null) {
                this.fXIncludeChildConfig.setDocumentHandler(handler);
            }
            if (this.fXPointerChildConfig != null) {
                this.fXPointerChildConfig.setDocumentHandler(handler);
            }
        }
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        this.fErrorReporter.setDocumentLocator(locator);
        if (!(namespaceContext instanceof XIncludeNamespaceSupport)) {
            reportFatalError("IncompatibleNamespaceContext");
        }
        this.fNamespaceContext = (XIncludeNamespaceSupport) namespaceContext;
        this.fDocLocation = locator;
        this.fXIncludeLocator.setLocator(this.fDocLocation);
        setupCurrentBaseURI(locator);
        saveBaseURI();
        if (augs == null) {
            augs = new AugmentationsImpl();
        }
        augs.putItem(CURRENT_BASE_URI, this.fCurrentBaseURI);
        if (!isRootDocument()) {
            this.fParentXIncludeHandler.fHasIncludeReportedContent = true;
            if (this.fParentXIncludeHandler.searchForRecursiveIncludes(this.fCurrentBaseURI.getExpandedSystemId())) {
                reportFatalError("RecursiveInclude", new Object[]{this.fCurrentBaseURI.getExpandedSystemId()});
            }
        }
        this.fCurrentLanguage = XMLSymbols.EMPTY_STRING;
        saveLanguage(this.fCurrentLanguage);
        if (isRootDocument() && this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(this.fXIncludeLocator, encoding, namespaceContext, augs);
        }
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        this.fIsXML11 = "1.1".equals(version);
        if (isRootDocument() && this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(version, encoding, standalone, augs);
        }
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
        if (isRootDocument() && this.fDocumentHandler != null) {
            this.fDocumentHandler.doctypeDecl(rootElement, publicId, systemId, augs);
        }
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.comment(text, augs);
            }
        } else if (this.fDocumentHandler != null && getState() == 1) {
            this.fDepth++;
            this.fDocumentHandler.comment(text, modifyAugmentations(augs));
            this.fDepth--;
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.processingInstruction(target, data, augs);
            }
        } else if (this.fDocumentHandler != null && getState() == 1) {
            this.fDepth++;
            this.fDocumentHandler.processingInstruction(target, data, modifyAugmentations(augs));
            this.fDepth--;
        }
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.fDepth++;
        int lastState = getState(this.fDepth - 1);
        if (lastState == 3 && getState(this.fDepth - 2) == 3) {
            setState(2);
        } else {
            setState(lastState);
        }
        processXMLBaseAttributes(attributes);
        if (this.fFixupLanguage) {
            processXMLLangAttributes(attributes);
        }
        if (isIncludeElement(element)) {
            if (handleIncludeElement(attributes)) {
                setState(2);
            } else {
                setState(3);
            }
        } else if (isFallbackElement(element)) {
            handleFallbackElement();
        } else if (hasXIncludeNamespace(element)) {
            if (getSawInclude(this.fDepth - 1)) {
                reportFatalError("IncludeChild", new Object[]{element.rawname});
            }
            if (getSawFallback(this.fDepth - 1)) {
                reportFatalError("FallbackChild", new Object[]{element.rawname});
            }
            if (getState() == 1) {
                r2 = this.fResultDepth;
                this.fResultDepth = r2 + 1;
                if (r2 == 0) {
                    checkMultipleRootElements();
                }
                if (this.fDocumentHandler != null) {
                    augs = modifyAugmentations(augs);
                    this.fDocumentHandler.startElement(element, processAttributes(attributes), augs);
                }
            }
        } else if (getState() == 1) {
            r2 = this.fResultDepth;
            this.fResultDepth = r2 + 1;
            if (r2 == 0) {
                checkMultipleRootElements();
            }
            if (this.fDocumentHandler != null) {
                augs = modifyAugmentations(augs);
                this.fDocumentHandler.startElement(element, processAttributes(attributes), augs);
            }
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.fDepth++;
        int lastState = getState(this.fDepth - 1);
        if (lastState == 3 && getState(this.fDepth - 2) == 3) {
            setState(2);
        } else {
            setState(lastState);
        }
        processXMLBaseAttributes(attributes);
        if (this.fFixupLanguage) {
            processXMLLangAttributes(attributes);
        }
        if (isIncludeElement(element)) {
            if (handleIncludeElement(attributes)) {
                setState(2);
            } else {
                reportFatalError("NoFallback");
            }
        } else if (isFallbackElement(element)) {
            handleFallbackElement();
        } else if (hasXIncludeNamespace(element)) {
            if (getSawInclude(this.fDepth - 1)) {
                reportFatalError("IncludeChild", new Object[]{element.rawname});
            }
            if (getSawFallback(this.fDepth - 1)) {
                reportFatalError("FallbackChild", new Object[]{element.rawname});
            }
            if (getState() == 1) {
                if (this.fResultDepth == 0) {
                    checkMultipleRootElements();
                }
                if (this.fDocumentHandler != null) {
                    augs = modifyAugmentations(augs);
                    this.fDocumentHandler.emptyElement(element, processAttributes(attributes), augs);
                }
            }
        } else if (getState() == 1) {
            if (this.fResultDepth == 0) {
                checkMultipleRootElements();
            }
            if (this.fDocumentHandler != null) {
                augs = modifyAugmentations(augs);
                this.fDocumentHandler.emptyElement(element, processAttributes(attributes), augs);
            }
        }
        setSawFallback(this.fDepth + 1, false);
        setSawInclude(this.fDepth, false);
        if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek()) {
            restoreBaseURI();
        }
        this.fDepth--;
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        if (isIncludeElement(element) && getState() == 3 && !getSawFallback(this.fDepth + 1)) {
            reportFatalError("NoFallback");
        }
        if (isFallbackElement(element)) {
            if (getState() == 1) {
                setState(2);
            }
        } else if (getState() == 1) {
            this.fResultDepth--;
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.endElement(element, augs);
            }
        }
        setSawFallback(this.fDepth + 1, false);
        setSawInclude(this.fDepth, false);
        if (this.fBaseURIScope.size() > 0 && this.fDepth == this.fBaseURIScope.peek()) {
            restoreBaseURI();
        }
        if (this.fLanguageScope.size() > 0 && this.fDepth == this.fLanguageScope.peek()) {
            this.fCurrentLanguage = restoreLanguage();
        }
        this.fDepth--;
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier resId, String encoding, Augmentations augs) throws XNIException {
        if (getState() != 1) {
            return;
        }
        if (this.fResultDepth == 0) {
            if (augs != null && Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED))) {
                reportFatalError("UnexpandedEntityReferenceIllegal");
            }
        } else if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(name, resId, encoding, augs);
        }
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null && getState() == 1) {
            this.fDocumentHandler.textDecl(version, encoding, augs);
        }
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (getState() != 1) {
            return;
        }
        if (this.fResultDepth == 0) {
            checkWhitespace(text);
        } else if (this.fDocumentHandler != null) {
            this.fDepth++;
            this.fDocumentHandler.characters(text, modifyAugmentations(augs));
            this.fDepth--;
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0) {
            this.fDocumentHandler.startCDATA(augs);
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null && getState() == 1 && this.fResultDepth != 0) {
            this.fDocumentHandler.endCDATA(augs);
        }
    }

    public void endDocument(Augmentations augs) throws XNIException {
        if (isRootDocument()) {
            if (!this.fSeenRootElement) {
                reportFatalError("RootElementRequired");
            }
            if (this.fDocumentHandler != null) {
                this.fDocumentHandler.endDocument(augs);
            }
        }
    }

    public void setDocumentSource(XMLDocumentSource source) {
        this.fDocumentSource = source;
    }

    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augmentations);
        }
    }

    public void elementDecl(String name, String contentModel, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, augmentations);
        }
    }

    public void endAttlist(Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(augmentations);
        }
    }

    public void endConditional(Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endConditional(augmentations);
        }
    }

    public void endDTD(Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endDTD(augmentations);
        }
        this.fInDTD = false;
    }

    public void endExternalSubset(Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endExternalSubset(augmentations);
        }
    }

    public void endParameterEntity(String name, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endParameterEntity(name, augmentations);
        }
    }

    public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.externalEntityDecl(name, identifier, augmentations);
        }
    }

    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    public void ignoredCharacters(XMLString text, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.ignoredCharacters(text, augmentations);
        }
    }

    public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.internalEntityDecl(name, text, nonNormalizedText, augmentations);
        }
    }

    public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
        addNotation(name, identifier, augmentations);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.notationDecl(name, identifier, augmentations);
        }
    }

    public void setDTDSource(XMLDTDSource source) {
        this.fDTDSource = source;
    }

    public void startAttlist(String elementName, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elementName, augmentations);
        }
    }

    public void startConditional(short type, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startConditional(type, augmentations);
        }
    }

    public void startDTD(XMLLocator locator, Augmentations augmentations) throws XNIException {
        this.fInDTD = true;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(locator, augmentations);
        }
    }

    public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startExternalSubset(identifier, augmentations);
        }
    }

    public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augmentations) throws XNIException {
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, augmentations);
        }
    }

    public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) throws XNIException {
        addUnparsedEntity(name, identifier, notation, augmentations);
        if (this.fDTDHandler != null) {
            this.fDTDHandler.unparsedEntityDecl(name, identifier, notation, augmentations);
        }
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDHandler(XMLDTDHandler handler) {
        this.fDTDHandler = handler;
    }

    private void setErrorReporter(XMLErrorReporter reporter) {
        this.fErrorReporter = reporter;
        if (this.fErrorReporter != null) {
            this.fErrorReporter.putMessageFormatter(XIncludeMessageFormatter.XINCLUDE_DOMAIN, this.fXIncludeMessageFormatter);
            if (this.fDocLocation != null) {
                this.fErrorReporter.setDocumentLocator(this.fDocLocation);
            }
        }
    }

    protected void handleFallbackElement() {
        if (!getSawInclude(this.fDepth - 1)) {
            if (getState() != 2) {
                reportFatalError("FallbackParent");
            } else {
                return;
            }
        }
        setSawInclude(this.fDepth, false);
        this.fNamespaceContext.setContextInvalid();
        if (getSawFallback(this.fDepth)) {
            reportFatalError("MultipleFallbacks");
        } else {
            setSawFallback(this.fDepth, true);
        }
        if (getState() == 3) {
            setState(1);
        }
    }

    protected boolean handleIncludeElement(XMLAttributes attributes) throws XNIException {
        URI uri;
        XIncludeTextReader textReader;
        String parserName;
        XIncludeHandler newHandler;
        String newHref;
        URI hrefURI;
        if (getSawInclude(this.fDepth - 1)) {
            reportFatalError("IncludeChild", new Object[]{XINCLUDE_INCLUDE});
        }
        if (getState() == 2) {
            return true;
        }
        XMLInputSource includedSource;
        XPointerHandler newHandler2;
        setSawInclude(this.fDepth, true);
        this.fNamespaceContext.setContextInvalid();
        String href = attributes.getValue(XINCLUDE_ATTR_HREF);
        String parse = attributes.getValue(XINCLUDE_ATTR_PARSE);
        String xpointer = attributes.getValue(XPOINTER);
        String accept = attributes.getValue(XINCLUDE_ATTR_ACCEPT);
        String acceptLanguage = attributes.getValue(XINCLUDE_ATTR_ACCEPT_LANGUAGE);
        if (parse == null) {
            parse = XINCLUDE_PARSE_XML;
        }
        if (href == null) {
            href = XMLSymbols.EMPTY_STRING;
        }
        if (href.length() == 0 && XINCLUDE_PARSE_XML.equals(parse)) {
            if (xpointer == null) {
                reportFatalError("XpointerMissing");
            } else {
                String reason = this.fXIncludeMessageFormatter.formatMessage(this.fErrorReporter != null ? this.fErrorReporter.getLocale() : null, "XPointerStreamability", null);
                reportResourceError("XMLResourceError", new Object[]{href, reason});
                return false;
            }
        }
        try {
            URI uri2 = new URI(href, true);
            try {
                if (uri2.getFragment() != null) {
                    reportFatalError("HrefFragmentIdentifierIllegal", new Object[]{href});
                    uri = uri2;
                    if (!(accept == null || isValidInHTTPHeader(accept))) {
                        reportFatalError("AcceptMalformed", null);
                        accept = null;
                    }
                    if (!(acceptLanguage == null || isValidInHTTPHeader(acceptLanguage))) {
                        reportFatalError("AcceptLanguageMalformed", null);
                        acceptLanguage = null;
                    }
                    includedSource = null;
                    if (this.fEntityResolver != null) {
                        try {
                            includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                            if (!(includedSource == null || (includedSource instanceof HTTPInputSource) || ((accept == null && acceptLanguage == null) || includedSource.getCharacterStream() != null || includedSource.getByteStream() != null))) {
                                includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                            }
                        } catch (Exception e) {
                            reportResourceError("XMLResourceError", new Object[]{href, e.getMessage()}, e);
                            return false;
                        }
                    }
                    if (includedSource == null) {
                        if (accept == null || acceptLanguage != null) {
                            includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                        } else {
                            XMLInputSource xMLInputSource = new XMLInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId());
                        }
                    }
                    if (parse.equals(XINCLUDE_PARSE_XML)) {
                        if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                            reportFatalError("InvalidParseValue", new Object[]{parse});
                        } else {
                            includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                            textReader = null;
                            try {
                                this.fHasIncludeReportedContent = false;
                                if (this.fIsXML11) {
                                    if (this.fXInclude10TextReader != null) {
                                        this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                                    } else {
                                        this.fXInclude10TextReader.setInputSource(includedSource);
                                    }
                                    textReader = this.fXInclude10TextReader;
                                } else {
                                    if (this.fXInclude11TextReader != null) {
                                        this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                                    } else {
                                        this.fXInclude11TextReader.setInputSource(includedSource);
                                    }
                                    textReader = this.fXInclude11TextReader;
                                }
                                textReader.setErrorReporter(this.fErrorReporter);
                                textReader.parse();
                                if (textReader != null) {
                                    try {
                                        textReader.close();
                                    } catch (Exception e2) {
                                        reportResourceError("TextResourceError", new Object[]{href, e2.getMessage()}, e2);
                                        return false;
                                    }
                                }
                            } catch (Exception ex) {
                                this.fErrorReporter.reportError(ex.getDomain(), ex.getKey(), ex.getArguments(), (short) 2, ex);
                                if (textReader != null) {
                                    try {
                                        textReader.close();
                                    } catch (Exception e22) {
                                        reportResourceError("TextResourceError", new Object[]{href, e22.getMessage()}, e22);
                                        return false;
                                    }
                                }
                            } catch (Exception e222) {
                                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e222);
                                if (textReader != null) {
                                    try {
                                        textReader.close();
                                    } catch (Exception e2222) {
                                        reportResourceError("TextResourceError", new Object[]{href, e2222.getMessage()}, e2222);
                                        return false;
                                    }
                                }
                            } catch (Exception e22222) {
                                if (this.fHasIncludeReportedContent) {
                                    throw new XNIException(e22222);
                                }
                                reportResourceError("TextResourceError", new Object[]{href, e22222.getMessage()}, e22222);
                                if (textReader != null) {
                                    try {
                                        textReader.close();
                                    } catch (Exception e222222) {
                                        reportResourceError("TextResourceError", new Object[]{href, e222222.getMessage()}, e222222);
                                        return false;
                                    }
                                }
                                return false;
                            } catch (Throwable th) {
                                if (textReader != null) {
                                    try {
                                        textReader.close();
                                    } catch (Exception e2222222) {
                                        reportResourceError("TextResourceError", new Object[]{href, e2222222.getMessage()}, e2222222);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    if ((xpointer != null && this.fXPointerChildConfig == null) || (xpointer == null && this.fXIncludeChildConfig == null)) {
                        parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                        if (xpointer != null) {
                            parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                        }
                        this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                        if (this.fSymbolTable != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                        }
                        if (this.fErrorReporter != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                        }
                        if (this.fEntityResolver != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                        }
                        this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                        this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                        this.fNeedCopyFeatures = true;
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                        this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                        this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                        if (xpointer == null) {
                            newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                            this.fXPtrProcessor = newHandler2;
                            ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                            ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                            ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                            if (this.fErrorReporter != null) {
                                ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                            }
                            newHandler2.setParent(this);
                            newHandler2.setHref(href);
                            newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                            newHandler2.setDocumentHandler(getDocumentHandler());
                            this.fXPointerChildConfig = this.fChildConfig;
                        } else {
                            newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                            newHandler.setParent(this);
                            newHandler.setHref(href);
                            newHandler.setXIncludeLocator(this.fXIncludeLocator);
                            newHandler.setDocumentHandler(getDocumentHandler());
                            this.fXIncludeChildConfig = this.fChildConfig;
                        }
                    }
                    if (xpointer == null) {
                        this.fChildConfig = this.fXPointerChildConfig;
                        try {
                            this.fXPtrProcessor.parseXPointer(xpointer);
                        } catch (XNIException ex2) {
                            reportResourceError("XMLResourceError", new Object[]{href, ex2.getMessage()});
                            return false;
                        }
                    }
                    this.fChildConfig = this.fXIncludeChildConfig;
                    if (this.fNeedCopyFeatures) {
                        copyFeatures(this.fSettings, this.fChildConfig);
                    }
                    this.fNeedCopyFeatures = false;
                    try {
                        this.fHasIncludeReportedContent = false;
                        this.fNamespaceContext.pushScope();
                        this.fChildConfig.parse(includedSource);
                        this.fXIncludeLocator.setLocator(this.fDocLocation);
                        if (this.fErrorReporter != null) {
                            this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                        }
                        if (xpointer != null || this.fXPtrProcessor.isXPointerResolved()) {
                            this.fNamespaceContext.popScope();
                        } else {
                            reason = this.fXIncludeMessageFormatter.formatMessage(this.fErrorReporter != null ? this.fErrorReporter.getLocale() : null, "XPointerResolutionUnsuccessful", null);
                            reportResourceError("XMLResourceError", new Object[]{href, reason});
                            return false;
                        }
                    } catch (XNIException e3) {
                        this.fXIncludeLocator.setLocator(this.fDocLocation);
                        if (this.fErrorReporter != null) {
                            this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                        }
                        reportFatalError("XMLParseError", new Object[]{href});
                    } catch (Exception e22222222) {
                        this.fXIncludeLocator.setLocator(this.fDocLocation);
                        if (this.fErrorReporter != null) {
                            this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                        }
                        if (this.fHasIncludeReportedContent) {
                            throw new XNIException(e22222222);
                        }
                        reportResourceError("XMLResourceError", new Object[]{href, e22222222.getMessage()}, e22222222);
                        return false;
                    } finally {
                        this.fNamespaceContext.popScope();
                    }
                    return true;
                }
            } catch (MalformedURIException e4) {
                newHref = escapeHref(href);
                if (href == newHref) {
                    href = newHref;
                    try {
                        try {
                            if (new URI(href, true).getFragment() != null) {
                                reportFatalError("HrefFragmentIdentifierIllegal", new Object[]{href});
                            }
                        } catch (MalformedURIException e5) {
                            reportFatalError("HrefSyntacticallyInvalid", new Object[]{href});
                            reportFatalError("AcceptMalformed", null);
                            accept = null;
                            reportFatalError("AcceptLanguageMalformed", null);
                            acceptLanguage = null;
                            includedSource = null;
                            if (this.fEntityResolver != null) {
                                includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                                includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                            }
                            if (includedSource == null) {
                                if (accept == null) {
                                }
                                includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                            }
                            if (parse.equals(XINCLUDE_PARSE_XML)) {
                                parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                                if (xpointer != null) {
                                    parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                                }
                                this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                                if (this.fSymbolTable != null) {
                                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                                }
                                if (this.fErrorReporter != null) {
                                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                                }
                                if (this.fEntityResolver != null) {
                                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                                }
                                this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                                this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                                this.fNeedCopyFeatures = true;
                                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                                this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                                this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                                if (xpointer == null) {
                                    newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                                    this.fXPtrProcessor = newHandler2;
                                    ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                                    if (this.fFixupBaseURIs) {
                                    }
                                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                                    if (this.fFixupLanguage) {
                                    }
                                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                                    if (this.fErrorReporter != null) {
                                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                                    }
                                    newHandler2.setParent(this);
                                    newHandler2.setHref(href);
                                    newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                                    newHandler2.setDocumentHandler(getDocumentHandler());
                                    this.fXPointerChildConfig = this.fChildConfig;
                                } else {
                                    newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                                    newHandler.setParent(this);
                                    newHandler.setHref(href);
                                    newHandler.setXIncludeLocator(this.fXIncludeLocator);
                                    newHandler.setDocumentHandler(getDocumentHandler());
                                    this.fXIncludeChildConfig = this.fChildConfig;
                                }
                                if (xpointer == null) {
                                    this.fChildConfig = this.fXPointerChildConfig;
                                    this.fXPtrProcessor.parseXPointer(xpointer);
                                } else {
                                    this.fChildConfig = this.fXIncludeChildConfig;
                                }
                                if (this.fNeedCopyFeatures) {
                                    copyFeatures(this.fSettings, this.fChildConfig);
                                }
                                this.fNeedCopyFeatures = false;
                                this.fHasIncludeReportedContent = false;
                                this.fNamespaceContext.pushScope();
                                this.fChildConfig.parse(includedSource);
                                this.fXIncludeLocator.setLocator(this.fDocLocation);
                                if (this.fErrorReporter != null) {
                                    this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                                }
                                if (xpointer != null) {
                                }
                                this.fNamespaceContext.popScope();
                            } else {
                                if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                                    includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                                    textReader = null;
                                    this.fHasIncludeReportedContent = false;
                                    if (this.fIsXML11) {
                                        if (this.fXInclude10TextReader != null) {
                                            this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                                        } else {
                                            this.fXInclude10TextReader.setInputSource(includedSource);
                                        }
                                        textReader = this.fXInclude10TextReader;
                                    } else {
                                        if (this.fXInclude11TextReader != null) {
                                            this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                                        } else {
                                            this.fXInclude11TextReader.setInputSource(includedSource);
                                        }
                                        textReader = this.fXInclude11TextReader;
                                    }
                                    textReader.setErrorReporter(this.fErrorReporter);
                                    textReader.parse();
                                    if (textReader != null) {
                                        textReader.close();
                                    }
                                } else {
                                    reportFatalError("InvalidParseValue", new Object[]{parse});
                                }
                            }
                            return true;
                        }
                    } catch (MalformedURIException e6) {
                        uri = hrefURI;
                        reportFatalError("HrefSyntacticallyInvalid", new Object[]{href});
                        reportFatalError("AcceptMalformed", null);
                        accept = null;
                        reportFatalError("AcceptLanguageMalformed", null);
                        acceptLanguage = null;
                        includedSource = null;
                        if (this.fEntityResolver != null) {
                            includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                            includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                        }
                        if (includedSource == null) {
                            if (accept == null) {
                            }
                            includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                        }
                        if (parse.equals(XINCLUDE_PARSE_XML)) {
                            if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                                reportFatalError("InvalidParseValue", new Object[]{parse});
                            } else {
                                includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                                textReader = null;
                                this.fHasIncludeReportedContent = false;
                                if (this.fIsXML11) {
                                    if (this.fXInclude11TextReader != null) {
                                        this.fXInclude11TextReader.setInputSource(includedSource);
                                    } else {
                                        this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                                    }
                                    textReader = this.fXInclude11TextReader;
                                } else {
                                    if (this.fXInclude10TextReader != null) {
                                        this.fXInclude10TextReader.setInputSource(includedSource);
                                    } else {
                                        this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                                    }
                                    textReader = this.fXInclude10TextReader;
                                }
                                textReader.setErrorReporter(this.fErrorReporter);
                                textReader.parse();
                                if (textReader != null) {
                                    textReader.close();
                                }
                            }
                        } else {
                            parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                            if (xpointer != null) {
                                parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                            }
                            this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                            if (this.fSymbolTable != null) {
                                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                            }
                            if (this.fErrorReporter != null) {
                                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                            }
                            if (this.fEntityResolver != null) {
                                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                            }
                            this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                            this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                            this.fNeedCopyFeatures = true;
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                            this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                            this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                            if (xpointer == null) {
                                newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                                newHandler.setParent(this);
                                newHandler.setHref(href);
                                newHandler.setXIncludeLocator(this.fXIncludeLocator);
                                newHandler.setDocumentHandler(getDocumentHandler());
                                this.fXIncludeChildConfig = this.fChildConfig;
                            } else {
                                newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                                this.fXPtrProcessor = newHandler2;
                                ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                                if (this.fFixupBaseURIs) {
                                }
                                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                                if (this.fFixupLanguage) {
                                }
                                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                                if (this.fErrorReporter != null) {
                                    ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                                }
                                newHandler2.setParent(this);
                                newHandler2.setHref(href);
                                newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                                newHandler2.setDocumentHandler(getDocumentHandler());
                                this.fXPointerChildConfig = this.fChildConfig;
                            }
                            if (xpointer == null) {
                                this.fChildConfig = this.fXIncludeChildConfig;
                            } else {
                                this.fChildConfig = this.fXPointerChildConfig;
                                this.fXPtrProcessor.parseXPointer(xpointer);
                            }
                            if (this.fNeedCopyFeatures) {
                                copyFeatures(this.fSettings, this.fChildConfig);
                            }
                            this.fNeedCopyFeatures = false;
                            this.fHasIncludeReportedContent = false;
                            this.fNamespaceContext.pushScope();
                            this.fChildConfig.parse(includedSource);
                            this.fXIncludeLocator.setLocator(this.fDocLocation);
                            if (this.fErrorReporter != null) {
                                this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                            }
                            if (xpointer != null) {
                            }
                            this.fNamespaceContext.popScope();
                        }
                        return true;
                    }
                    reportFatalError("AcceptMalformed", null);
                    accept = null;
                    reportFatalError("AcceptLanguageMalformed", null);
                    acceptLanguage = null;
                    includedSource = null;
                    if (this.fEntityResolver != null) {
                        includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                        includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                    }
                    if (includedSource == null) {
                        if (accept == null) {
                        }
                        includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                    }
                    if (parse.equals(XINCLUDE_PARSE_XML)) {
                        if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                            reportFatalError("InvalidParseValue", new Object[]{parse});
                        } else {
                            includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                            textReader = null;
                            this.fHasIncludeReportedContent = false;
                            if (this.fIsXML11) {
                                if (this.fXInclude11TextReader != null) {
                                    this.fXInclude11TextReader.setInputSource(includedSource);
                                } else {
                                    this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                                }
                                textReader = this.fXInclude11TextReader;
                            } else {
                                if (this.fXInclude10TextReader != null) {
                                    this.fXInclude10TextReader.setInputSource(includedSource);
                                } else {
                                    this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                                }
                                textReader = this.fXInclude10TextReader;
                            }
                            textReader.setErrorReporter(this.fErrorReporter);
                            textReader.parse();
                            if (textReader != null) {
                                textReader.close();
                            }
                        }
                    } else {
                        parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                        if (xpointer != null) {
                            parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                        }
                        this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                        if (this.fSymbolTable != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                        }
                        if (this.fErrorReporter != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                        }
                        if (this.fEntityResolver != null) {
                            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                        }
                        this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                        this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                        this.fNeedCopyFeatures = true;
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                        this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                        this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                        if (xpointer == null) {
                            newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                            newHandler.setParent(this);
                            newHandler.setHref(href);
                            newHandler.setXIncludeLocator(this.fXIncludeLocator);
                            newHandler.setDocumentHandler(getDocumentHandler());
                            this.fXIncludeChildConfig = this.fChildConfig;
                        } else {
                            newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                            this.fXPtrProcessor = newHandler2;
                            ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                            if (this.fFixupBaseURIs) {
                            }
                            ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                            if (this.fFixupLanguage) {
                            }
                            ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                            if (this.fErrorReporter != null) {
                                ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                            }
                            newHandler2.setParent(this);
                            newHandler2.setHref(href);
                            newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                            newHandler2.setDocumentHandler(getDocumentHandler());
                            this.fXPointerChildConfig = this.fChildConfig;
                        }
                        if (xpointer == null) {
                            this.fChildConfig = this.fXIncludeChildConfig;
                        } else {
                            this.fChildConfig = this.fXPointerChildConfig;
                            this.fXPtrProcessor.parseXPointer(xpointer);
                        }
                        if (this.fNeedCopyFeatures) {
                            copyFeatures(this.fSettings, this.fChildConfig);
                        }
                        this.fNeedCopyFeatures = false;
                        this.fHasIncludeReportedContent = false;
                        this.fNamespaceContext.pushScope();
                        this.fChildConfig.parse(includedSource);
                        this.fXIncludeLocator.setLocator(this.fDocLocation);
                        if (this.fErrorReporter != null) {
                            this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                        }
                        if (xpointer != null) {
                        }
                        this.fNamespaceContext.popScope();
                    }
                    return true;
                }
                reportFatalError("HrefSyntacticallyInvalid", new Object[]{href});
                uri = hrefURI;
                reportFatalError("AcceptMalformed", null);
                accept = null;
                reportFatalError("AcceptLanguageMalformed", null);
                acceptLanguage = null;
                includedSource = null;
                if (this.fEntityResolver != null) {
                    includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                    includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                }
                if (includedSource == null) {
                    if (accept == null) {
                    }
                    includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                }
                if (parse.equals(XINCLUDE_PARSE_XML)) {
                    parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                    if (xpointer != null) {
                        parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                    }
                    this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                    if (this.fSymbolTable != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                    }
                    if (this.fErrorReporter != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                    }
                    if (this.fEntityResolver != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                    }
                    this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                    this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                    this.fNeedCopyFeatures = true;
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                    this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                    this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                    if (xpointer == null) {
                        newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                        this.fXPtrProcessor = newHandler2;
                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                        if (this.fFixupBaseURIs) {
                        }
                        ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                        if (this.fFixupLanguage) {
                        }
                        ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                        if (this.fErrorReporter != null) {
                            ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                        }
                        newHandler2.setParent(this);
                        newHandler2.setHref(href);
                        newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                        newHandler2.setDocumentHandler(getDocumentHandler());
                        this.fXPointerChildConfig = this.fChildConfig;
                    } else {
                        newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                        newHandler.setParent(this);
                        newHandler.setHref(href);
                        newHandler.setXIncludeLocator(this.fXIncludeLocator);
                        newHandler.setDocumentHandler(getDocumentHandler());
                        this.fXIncludeChildConfig = this.fChildConfig;
                    }
                    if (xpointer == null) {
                        this.fChildConfig = this.fXPointerChildConfig;
                        this.fXPtrProcessor.parseXPointer(xpointer);
                    } else {
                        this.fChildConfig = this.fXIncludeChildConfig;
                    }
                    if (this.fNeedCopyFeatures) {
                        copyFeatures(this.fSettings, this.fChildConfig);
                    }
                    this.fNeedCopyFeatures = false;
                    this.fHasIncludeReportedContent = false;
                    this.fNamespaceContext.pushScope();
                    this.fChildConfig.parse(includedSource);
                    this.fXIncludeLocator.setLocator(this.fDocLocation);
                    if (this.fErrorReporter != null) {
                        this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                    }
                    if (xpointer != null) {
                    }
                    this.fNamespaceContext.popScope();
                } else {
                    if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                        includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                        textReader = null;
                        this.fHasIncludeReportedContent = false;
                        if (this.fIsXML11) {
                            if (this.fXInclude10TextReader != null) {
                                this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                            } else {
                                this.fXInclude10TextReader.setInputSource(includedSource);
                            }
                            textReader = this.fXInclude10TextReader;
                        } else {
                            if (this.fXInclude11TextReader != null) {
                                this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                            } else {
                                this.fXInclude11TextReader.setInputSource(includedSource);
                            }
                            textReader = this.fXInclude11TextReader;
                        }
                        textReader.setErrorReporter(this.fErrorReporter);
                        textReader.parse();
                        if (textReader != null) {
                            textReader.close();
                        }
                    } else {
                        reportFatalError("InvalidParseValue", new Object[]{parse});
                    }
                }
                return true;
            }
        } catch (MalformedURIException e7) {
            hrefURI = null;
            newHref = escapeHref(href);
            if (href == newHref) {
                reportFatalError("HrefSyntacticallyInvalid", new Object[]{href});
                uri = hrefURI;
                reportFatalError("AcceptMalformed", null);
                accept = null;
                reportFatalError("AcceptLanguageMalformed", null);
                acceptLanguage = null;
                includedSource = null;
                if (this.fEntityResolver != null) {
                    includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                    includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
                }
                if (includedSource == null) {
                    if (accept == null) {
                    }
                    includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
                }
                if (parse.equals(XINCLUDE_PARSE_XML)) {
                    parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                    if (xpointer != null) {
                        parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                    }
                    this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                    if (this.fSymbolTable != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                    }
                    if (this.fErrorReporter != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                    }
                    if (this.fEntityResolver != null) {
                        this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                    }
                    this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                    this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                    this.fNeedCopyFeatures = true;
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                    this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                    this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                    if (xpointer == null) {
                        newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                        this.fXPtrProcessor = newHandler2;
                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                        if (this.fFixupBaseURIs) {
                        }
                        ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                        if (this.fFixupLanguage) {
                        }
                        ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                        if (this.fErrorReporter != null) {
                            ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                        }
                        newHandler2.setParent(this);
                        newHandler2.setHref(href);
                        newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                        newHandler2.setDocumentHandler(getDocumentHandler());
                        this.fXPointerChildConfig = this.fChildConfig;
                    } else {
                        newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                        newHandler.setParent(this);
                        newHandler.setHref(href);
                        newHandler.setXIncludeLocator(this.fXIncludeLocator);
                        newHandler.setDocumentHandler(getDocumentHandler());
                        this.fXIncludeChildConfig = this.fChildConfig;
                    }
                    if (xpointer == null) {
                        this.fChildConfig = this.fXPointerChildConfig;
                        this.fXPtrProcessor.parseXPointer(xpointer);
                    } else {
                        this.fChildConfig = this.fXIncludeChildConfig;
                    }
                    if (this.fNeedCopyFeatures) {
                        copyFeatures(this.fSettings, this.fChildConfig);
                    }
                    this.fNeedCopyFeatures = false;
                    this.fHasIncludeReportedContent = false;
                    this.fNamespaceContext.pushScope();
                    this.fChildConfig.parse(includedSource);
                    this.fXIncludeLocator.setLocator(this.fDocLocation);
                    if (this.fErrorReporter != null) {
                        this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                    }
                    if (xpointer != null) {
                    }
                    this.fNamespaceContext.popScope();
                } else {
                    if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                        includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                        textReader = null;
                        this.fHasIncludeReportedContent = false;
                        if (this.fIsXML11) {
                            if (this.fXInclude10TextReader != null) {
                                this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                            } else {
                                this.fXInclude10TextReader.setInputSource(includedSource);
                            }
                            textReader = this.fXInclude10TextReader;
                        } else {
                            if (this.fXInclude11TextReader != null) {
                                this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                            } else {
                                this.fXInclude11TextReader.setInputSource(includedSource);
                            }
                            textReader = this.fXInclude11TextReader;
                        }
                        textReader.setErrorReporter(this.fErrorReporter);
                        textReader.parse();
                        if (textReader != null) {
                            textReader.close();
                        }
                    } else {
                        reportFatalError("InvalidParseValue", new Object[]{parse});
                    }
                }
                return true;
            }
            href = newHref;
            if (new URI(href, true).getFragment() != null) {
                reportFatalError("HrefFragmentIdentifierIllegal", new Object[]{href});
            }
            reportFatalError("AcceptMalformed", null);
            accept = null;
            reportFatalError("AcceptLanguageMalformed", null);
            acceptLanguage = null;
            includedSource = null;
            if (this.fEntityResolver != null) {
                includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
                includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
            }
            if (includedSource == null) {
                if (accept == null) {
                }
                includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
            }
            if (parse.equals(XINCLUDE_PARSE_XML)) {
                if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                    reportFatalError("InvalidParseValue", new Object[]{parse});
                } else {
                    includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                    textReader = null;
                    this.fHasIncludeReportedContent = false;
                    if (this.fIsXML11) {
                        if (this.fXInclude11TextReader != null) {
                            this.fXInclude11TextReader.setInputSource(includedSource);
                        } else {
                            this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                        }
                        textReader = this.fXInclude11TextReader;
                    } else {
                        if (this.fXInclude10TextReader != null) {
                            this.fXInclude10TextReader.setInputSource(includedSource);
                        } else {
                            this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                        }
                        textReader = this.fXInclude10TextReader;
                    }
                    textReader.setErrorReporter(this.fErrorReporter);
                    textReader.parse();
                    if (textReader != null) {
                        textReader.close();
                    }
                }
            } else {
                parserName = XINCLUDE_DEFAULT_CONFIGURATION;
                if (xpointer != null) {
                    parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
                }
                this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
                if (this.fSymbolTable != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
                }
                if (this.fErrorReporter != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                }
                if (this.fEntityResolver != null) {
                    this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
                }
                this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
                this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
                this.fNeedCopyFeatures = true;
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
                this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
                if (xpointer == null) {
                    newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                    newHandler.setParent(this);
                    newHandler.setHref(href);
                    newHandler.setXIncludeLocator(this.fXIncludeLocator);
                    newHandler.setDocumentHandler(getDocumentHandler());
                    this.fXIncludeChildConfig = this.fChildConfig;
                } else {
                    newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                    this.fXPtrProcessor = newHandler2;
                    ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                    if (this.fFixupBaseURIs) {
                    }
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                    if (this.fFixupLanguage) {
                    }
                    ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                    if (this.fErrorReporter != null) {
                        ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                    }
                    newHandler2.setParent(this);
                    newHandler2.setHref(href);
                    newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                    newHandler2.setDocumentHandler(getDocumentHandler());
                    this.fXPointerChildConfig = this.fChildConfig;
                }
                if (xpointer == null) {
                    this.fChildConfig = this.fXIncludeChildConfig;
                } else {
                    this.fChildConfig = this.fXPointerChildConfig;
                    this.fXPtrProcessor.parseXPointer(xpointer);
                }
                if (this.fNeedCopyFeatures) {
                    copyFeatures(this.fSettings, this.fChildConfig);
                }
                this.fNeedCopyFeatures = false;
                this.fHasIncludeReportedContent = false;
                this.fNamespaceContext.pushScope();
                this.fChildConfig.parse(includedSource);
                this.fXIncludeLocator.setLocator(this.fDocLocation);
                if (this.fErrorReporter != null) {
                    this.fErrorReporter.setDocumentLocator(this.fDocLocation);
                }
                if (xpointer != null) {
                }
                this.fNamespaceContext.popScope();
            }
            return true;
        }
        uri = hrefURI;
        reportFatalError("AcceptMalformed", null);
        accept = null;
        reportFatalError("AcceptLanguageMalformed", null);
        acceptLanguage = null;
        includedSource = null;
        if (this.fEntityResolver != null) {
            includedSource = this.fEntityResolver.resolveEntity(new XMLResourceIdentifierImpl(null, href, this.fCurrentBaseURI.getExpandedSystemId(), XMLEntityManager.expandSystemId(href, this.fCurrentBaseURI.getExpandedSystemId(), false)));
            includedSource = createInputSource(includedSource.getPublicId(), includedSource.getSystemId(), includedSource.getBaseSystemId(), accept, acceptLanguage);
        }
        if (includedSource == null) {
            if (accept == null) {
            }
            includedSource = createInputSource(null, href, this.fCurrentBaseURI.getExpandedSystemId(), accept, acceptLanguage);
        }
        if (parse.equals(XINCLUDE_PARSE_XML)) {
            if (parse.equals(XINCLUDE_PARSE_TEXT)) {
                reportFatalError("InvalidParseValue", new Object[]{parse});
            } else {
                includedSource.setEncoding(attributes.getValue(XINCLUDE_ATTR_ENCODING));
                textReader = null;
                this.fHasIncludeReportedContent = false;
                if (this.fIsXML11) {
                    if (this.fXInclude11TextReader != null) {
                        this.fXInclude11TextReader.setInputSource(includedSource);
                    } else {
                        this.fXInclude11TextReader = new XInclude11TextReader(includedSource, this, this.fBufferSize);
                    }
                    textReader = this.fXInclude11TextReader;
                } else {
                    if (this.fXInclude10TextReader != null) {
                        this.fXInclude10TextReader.setInputSource(includedSource);
                    } else {
                        this.fXInclude10TextReader = new XIncludeTextReader(includedSource, this, this.fBufferSize);
                    }
                    textReader = this.fXInclude10TextReader;
                }
                textReader.setErrorReporter(this.fErrorReporter);
                textReader.parse();
                if (textReader != null) {
                    textReader.close();
                }
            }
        } else {
            parserName = XINCLUDE_DEFAULT_CONFIGURATION;
            if (xpointer != null) {
                parserName = "mf.org.apache.xerces.parsers.XPointerParserConfiguration";
            }
            this.fChildConfig = (XMLParserConfiguration) ObjectFactory.newInstance(parserName, ObjectFactory.findClassLoader(), true);
            if (this.fSymbolTable != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
            }
            if (this.fErrorReporter != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
            }
            if (this.fEntityResolver != null) {
                this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fEntityResolver);
            }
            this.fChildConfig.setProperty(SECURITY_MANAGER, this.fSecurityManager);
            this.fChildConfig.setProperty(BUFFER_SIZE, new Integer(this.fBufferSize));
            this.fNeedCopyFeatures = true;
            this.fChildConfig.setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
            this.fChildConfig.setFeature(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs);
            this.fChildConfig.setFeature(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage);
            if (xpointer == null) {
                newHandler = (XIncludeHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xinclude-handler");
                newHandler.setParent(this);
                newHandler.setHref(href);
                newHandler.setXIncludeLocator(this.fXIncludeLocator);
                newHandler.setDocumentHandler(getDocumentHandler());
                this.fXIncludeChildConfig = this.fChildConfig;
            } else {
                newHandler2 = (XPointerHandler) this.fChildConfig.getProperty("http://apache.org/xml/properties/internal/xpointer-handler");
                this.fXPtrProcessor = newHandler2;
                ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/namespace-context", this.fNamespaceContext);
                if (this.fFixupBaseURIs) {
                }
                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_BASE_URIS, this.fFixupBaseURIs ? Boolean.TRUE : Boolean.FALSE);
                if (this.fFixupLanguage) {
                }
                ((XPointerHandler) this.fXPtrProcessor).setProperty(XINCLUDE_FIXUP_LANGUAGE, this.fFixupLanguage ? Boolean.TRUE : Boolean.FALSE);
                if (this.fErrorReporter != null) {
                    ((XPointerHandler) this.fXPtrProcessor).setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
                }
                newHandler2.setParent(this);
                newHandler2.setHref(href);
                newHandler2.setXIncludeLocator(this.fXIncludeLocator);
                newHandler2.setDocumentHandler(getDocumentHandler());
                this.fXPointerChildConfig = this.fChildConfig;
            }
            if (xpointer == null) {
                this.fChildConfig = this.fXIncludeChildConfig;
            } else {
                this.fChildConfig = this.fXPointerChildConfig;
                this.fXPtrProcessor.parseXPointer(xpointer);
            }
            if (this.fNeedCopyFeatures) {
                copyFeatures(this.fSettings, this.fChildConfig);
            }
            this.fNeedCopyFeatures = false;
            this.fHasIncludeReportedContent = false;
            this.fNamespaceContext.pushScope();
            this.fChildConfig.parse(includedSource);
            this.fXIncludeLocator.setLocator(this.fDocLocation);
            if (this.fErrorReporter != null) {
                this.fErrorReporter.setDocumentLocator(this.fDocLocation);
            }
            if (xpointer != null) {
            }
            this.fNamespaceContext.popScope();
        }
        return true;
    }

    protected boolean hasXIncludeNamespace(QName element) {
        return element.uri == XINCLUDE_NS_URI || this.fNamespaceContext.getURI(element.prefix) == XINCLUDE_NS_URI;
    }

    protected boolean isIncludeElement(QName element) {
        return element.localpart.equals(XINCLUDE_INCLUDE) && hasXIncludeNamespace(element);
    }

    protected boolean isFallbackElement(QName element) {
        return element.localpart.equals(XINCLUDE_FALLBACK) && hasXIncludeNamespace(element);
    }

    protected boolean sameBaseURIAsIncludeParent() {
        String parentBaseURI = getIncludeParentBaseURI();
        return parentBaseURI != null && parentBaseURI.equals(this.fCurrentBaseURI.getExpandedSystemId());
    }

    protected boolean sameLanguageAsIncludeParent() {
        String parentLanguage = getIncludeParentLanguage();
        return parentLanguage != null && parentLanguage.equalsIgnoreCase(this.fCurrentLanguage);
    }

    protected void setupCurrentBaseURI(XMLLocator locator) {
        this.fCurrentBaseURI.setBaseSystemId(locator.getBaseSystemId());
        if (locator.getLiteralSystemId() != null) {
            this.fCurrentBaseURI.setLiteralSystemId(locator.getLiteralSystemId());
        } else {
            this.fCurrentBaseURI.setLiteralSystemId(this.fHrefFromParent);
        }
        String expandedSystemId = locator.getExpandedSystemId();
        if (expandedSystemId == null) {
            try {
                expandedSystemId = XMLEntityManager.expandSystemId(this.fCurrentBaseURI.getLiteralSystemId(), this.fCurrentBaseURI.getBaseSystemId(), false);
                if (expandedSystemId == null) {
                    expandedSystemId = this.fCurrentBaseURI.getLiteralSystemId();
                }
            } catch (MalformedURIException e) {
                reportFatalError("ExpandedSystemId");
            }
        }
        this.fCurrentBaseURI.setExpandedSystemId(expandedSystemId);
    }

    protected boolean searchForRecursiveIncludes(String includedSysId) {
        if (includedSysId.equals(this.fCurrentBaseURI.getExpandedSystemId())) {
            return true;
        }
        if (this.fParentXIncludeHandler == null) {
            return false;
        }
        return this.fParentXIncludeHandler.searchForRecursiveIncludes(includedSysId);
    }

    protected boolean isTopLevelIncludedItem() {
        return isTopLevelIncludedItemViaInclude() || isTopLevelIncludedItemViaFallback();
    }

    protected boolean isTopLevelIncludedItemViaInclude() {
        return this.fDepth == 1 && !isRootDocument();
    }

    protected boolean isTopLevelIncludedItemViaFallback() {
        return getSawFallback(this.fDepth - 1);
    }

    protected XMLAttributes processAttributes(XMLAttributes attributes) {
        if (isTopLevelIncludedItem()) {
            String uri;
            if (this.fFixupBaseURIs && !sameBaseURIAsIncludeParent()) {
                if (attributes == null) {
                    attributes = new XMLAttributesImpl();
                }
                try {
                    uri = getRelativeBaseURI();
                } catch (MalformedURIException e) {
                    uri = this.fCurrentBaseURI.getExpandedSystemId();
                }
                attributes.setSpecified(attributes.addAttribute(XML_BASE_QNAME, XMLSymbols.fCDATASymbol, uri), true);
            }
            if (this.fFixupLanguage && !sameLanguageAsIncludeParent()) {
                if (attributes == null) {
                    attributes = new XMLAttributesImpl();
                }
                attributes.setSpecified(attributes.addAttribute(XML_LANG_QNAME, XMLSymbols.fCDATASymbol, this.fCurrentLanguage), true);
            }
            Enumeration inscopeNS = this.fNamespaceContext.getAllPrefixes();
            while (inscopeNS.hasMoreElements()) {
                String prefix = (String) inscopeNS.nextElement();
                String parentURI = this.fNamespaceContext.getURIFromIncludeParent(prefix);
                uri = this.fNamespaceContext.getURI(prefix);
                if (!(parentURI == uri || attributes == null)) {
                    QName ns;
                    if (prefix == XMLSymbols.EMPTY_STRING) {
                        if (attributes.getValue(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS) == null) {
                            if (attributes == null) {
                                attributes = new XMLAttributesImpl();
                            }
                            ns = (QName) NEW_NS_ATTR_QNAME.clone();
                            ns.prefix = null;
                            ns.localpart = XMLSymbols.PREFIX_XMLNS;
                            ns.rawname = XMLSymbols.PREFIX_XMLNS;
                            attributes.setSpecified(attributes.addAttribute(ns, XMLSymbols.fCDATASymbol, uri != null ? uri : XMLSymbols.EMPTY_STRING), true);
                            this.fNamespaceContext.declarePrefix(prefix, uri);
                        }
                    } else if (attributes.getValue(NamespaceContext.XMLNS_URI, prefix) == null) {
                        String addSymbol;
                        if (attributes == null) {
                            attributes = new XMLAttributesImpl();
                        }
                        ns = (QName) NEW_NS_ATTR_QNAME.clone();
                        ns.localpart = prefix;
                        ns.rawname += prefix;
                        if (this.fSymbolTable != null) {
                            addSymbol = this.fSymbolTable.addSymbol(ns.rawname);
                        } else {
                            addSymbol = ns.rawname.intern();
                        }
                        ns.rawname = addSymbol;
                        attributes.setSpecified(attributes.addAttribute(ns, XMLSymbols.fCDATASymbol, uri != null ? uri : XMLSymbols.EMPTY_STRING), true);
                        this.fNamespaceContext.declarePrefix(prefix, uri);
                    }
                }
            }
        }
        if (attributes != null) {
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String type = attributes.getType(i);
                String value = attributes.getValue(i);
                if (type == XMLSymbols.fENTITYSymbol) {
                    checkUnparsedEntity(value);
                }
                if (type == XMLSymbols.fENTITIESSymbol) {
                    StringTokenizer st = new StringTokenizer(value);
                    while (st.hasMoreTokens()) {
                        checkUnparsedEntity(st.nextToken());
                    }
                } else if (type == XMLSymbols.fNOTATIONSymbol) {
                    checkNotation(value);
                }
            }
        }
        return attributes;
    }

    protected String getRelativeBaseURI() throws MalformedURIException {
        int includeParentDepth = getIncludeParentDepth();
        String relativeURI = getRelativeURI(includeParentDepth);
        if (isRootDocument()) {
            return relativeURI;
        }
        if (relativeURI.length() == 0) {
            relativeURI = this.fCurrentBaseURI.getLiteralSystemId();
        }
        if (includeParentDepth != 0) {
            return relativeURI;
        }
        if (this.fParentRelativeURI == null) {
            this.fParentRelativeURI = this.fParentXIncludeHandler.getRelativeBaseURI();
        }
        if (this.fParentRelativeURI.length() == 0) {
            return relativeURI;
        }
        URI base = new URI(this.fParentRelativeURI, true);
        URI uri = new URI(base, relativeURI);
        if (!isEqual(base.getScheme(), uri.getScheme())) {
            return relativeURI;
        }
        if (!isEqual(base.getAuthority(), uri.getAuthority())) {
            return uri.getSchemeSpecificPart();
        }
        String literalPath = uri.getPath();
        String literalQuery = uri.getQueryString();
        String literalFragment = uri.getFragment();
        if (literalQuery == null && literalFragment == null) {
            return literalPath;
        }
        StringBuffer buffer = new StringBuffer();
        if (literalPath != null) {
            buffer.append(literalPath);
        }
        if (literalQuery != null) {
            buffer.append('?');
            buffer.append(literalQuery);
        }
        if (literalFragment != null) {
            buffer.append('#');
            buffer.append(literalFragment);
        }
        return buffer.toString();
    }

    private String getIncludeParentBaseURI() {
        int depth = getIncludeParentDepth();
        if (isRootDocument() || depth != 0) {
            return getBaseURI(depth);
        }
        return this.fParentXIncludeHandler.getIncludeParentBaseURI();
    }

    private String getIncludeParentLanguage() {
        int depth = getIncludeParentDepth();
        if (isRootDocument() || depth != 0) {
            return getLanguage(depth);
        }
        return this.fParentXIncludeHandler.getIncludeParentLanguage();
    }

    private int getIncludeParentDepth() {
        int i = this.fDepth - 1;
        while (i >= 0) {
            if (!getSawInclude(i) && !getSawFallback(i)) {
                return i;
            }
            i--;
        }
        return 0;
    }

    private int getResultDepth() {
        return this.fResultDepth;
    }

    protected Augmentations modifyAugmentations(Augmentations augs) {
        return modifyAugmentations(augs, false);
    }

    protected Augmentations modifyAugmentations(Augmentations augs, boolean force) {
        if (force || isTopLevelIncludedItem()) {
            if (augs == null) {
                augs = new AugmentationsImpl();
            }
            augs.putItem(XINCLUDE_INCLUDED, Boolean.TRUE);
        }
        return augs;
    }

    protected int getState(int depth) {
        return this.fState[depth];
    }

    protected int getState() {
        return this.fState[this.fDepth];
    }

    protected void setState(int state) {
        if (this.fDepth >= this.fState.length) {
            int[] newarray = new int[(this.fDepth * 2)];
            System.arraycopy(this.fState, 0, newarray, 0, this.fState.length);
            this.fState = newarray;
        }
        this.fState[this.fDepth] = state;
    }

    protected void setSawFallback(int depth, boolean val) {
        if (depth >= this.fSawFallback.length) {
            boolean[] newarray = new boolean[(depth * 2)];
            System.arraycopy(this.fSawFallback, 0, newarray, 0, this.fSawFallback.length);
            this.fSawFallback = newarray;
        }
        this.fSawFallback[depth] = val;
    }

    protected boolean getSawFallback(int depth) {
        if (depth >= this.fSawFallback.length) {
            return false;
        }
        return this.fSawFallback[depth];
    }

    protected void setSawInclude(int depth, boolean val) {
        if (depth >= this.fSawInclude.length) {
            boolean[] newarray = new boolean[(depth * 2)];
            System.arraycopy(this.fSawInclude, 0, newarray, 0, this.fSawInclude.length);
            this.fSawInclude = newarray;
        }
        this.fSawInclude[depth] = val;
    }

    protected boolean getSawInclude(int depth) {
        if (depth >= this.fSawInclude.length) {
            return false;
        }
        return this.fSawInclude[depth];
    }

    protected void reportResourceError(String key) {
        reportResourceError(key, null);
    }

    protected void reportResourceError(String key, Object[] args) {
        reportResourceError(key, args, null);
    }

    protected void reportResourceError(String key, Object[] args, Exception exception) {
        reportError(key, args, (short) 0, exception);
    }

    protected void reportFatalError(String key) {
        reportFatalError(key, null);
    }

    protected void reportFatalError(String key, Object[] args) {
        reportFatalError(key, args, null);
    }

    protected void reportFatalError(String key, Object[] args, Exception exception) {
        reportError(key, args, (short) 2, exception);
    }

    private void reportError(String key, Object[] args, short severity, Exception exception) {
        if (this.fErrorReporter != null) {
            this.fErrorReporter.reportError(XIncludeMessageFormatter.XINCLUDE_DOMAIN, key, args, severity, exception);
        }
    }

    protected void setParent(XIncludeHandler parent) {
        this.fParentXIncludeHandler = parent;
    }

    protected void setHref(String href) {
        this.fHrefFromParent = href;
    }

    protected void setXIncludeLocator(XMLLocatorWrapper locator) {
        this.fXIncludeLocator = locator;
    }

    protected boolean isRootDocument() {
        return this.fParentXIncludeHandler == null;
    }

    protected void addUnparsedEntity(String name, XMLResourceIdentifier identifier, String notation, Augmentations augmentations) {
        UnparsedEntity ent = new UnparsedEntity();
        ent.name = name;
        ent.systemId = identifier.getLiteralSystemId();
        ent.publicId = identifier.getPublicId();
        ent.baseURI = identifier.getBaseSystemId();
        ent.expandedSystemId = identifier.getExpandedSystemId();
        ent.notation = notation;
        ent.augmentations = augmentations;
        this.fUnparsedEntities.add(ent);
    }

    protected void addNotation(String name, XMLResourceIdentifier identifier, Augmentations augmentations) {
        Notation not = new Notation();
        not.name = name;
        not.systemId = identifier.getLiteralSystemId();
        not.publicId = identifier.getPublicId();
        not.baseURI = identifier.getBaseSystemId();
        not.expandedSystemId = identifier.getExpandedSystemId();
        not.augmentations = augmentations;
        this.fNotations.add(not);
    }

    protected void checkUnparsedEntity(String entName) {
        UnparsedEntity ent = new UnparsedEntity();
        ent.name = entName;
        int index = this.fUnparsedEntities.indexOf(ent);
        if (index != -1) {
            ent = (UnparsedEntity) this.fUnparsedEntities.get(index);
            checkNotation(ent.notation);
            checkAndSendUnparsedEntity(ent);
        }
    }

    protected void checkNotation(String notName) {
        Notation not = new Notation();
        not.name = notName;
        int index = this.fNotations.indexOf(not);
        if (index != -1) {
            checkAndSendNotation((Notation) this.fNotations.get(index));
        }
    }

    protected void checkAndSendUnparsedEntity(UnparsedEntity ent) {
        if (isRootDocument()) {
            int index = this.fUnparsedEntities.indexOf(ent);
            if (index == -1) {
                XMLResourceIdentifier id = new XMLResourceIdentifierImpl(ent.publicId, ent.systemId, ent.baseURI, ent.expandedSystemId);
                addUnparsedEntity(ent.name, id, ent.notation, ent.augmentations);
                if (this.fSendUEAndNotationEvents && this.fDTDHandler != null) {
                    this.fDTDHandler.unparsedEntityDecl(ent.name, id, ent.notation, ent.augmentations);
                    return;
                }
                return;
            } else if (!ent.isDuplicate((UnparsedEntity) this.fUnparsedEntities.get(index))) {
                reportFatalError("NonDuplicateUnparsedEntity", new Object[]{ent.name});
                return;
            } else {
                return;
            }
        }
        this.fParentXIncludeHandler.checkAndSendUnparsedEntity(ent);
    }

    protected void checkAndSendNotation(Notation not) {
        if (isRootDocument()) {
            int index = this.fNotations.indexOf(not);
            if (index == -1) {
                XMLResourceIdentifier id = new XMLResourceIdentifierImpl(not.publicId, not.systemId, not.baseURI, not.expandedSystemId);
                addNotation(not.name, id, not.augmentations);
                if (this.fSendUEAndNotationEvents && this.fDTDHandler != null) {
                    this.fDTDHandler.notationDecl(not.name, id, not.augmentations);
                    return;
                }
                return;
            } else if (!not.isDuplicate((Notation) this.fNotations.get(index))) {
                reportFatalError("NonDuplicateNotation", new Object[]{not.name});
                return;
            } else {
                return;
            }
        }
        this.fParentXIncludeHandler.checkAndSendNotation(not);
    }

    private void checkWhitespace(XMLString value) {
        int end = value.offset + value.length;
        int i = value.offset;
        while (i < end) {
            if (XMLChar.isSpace(value.ch[i])) {
                i++;
            } else {
                reportFatalError("ContentIllegalAtTopLevel");
                return;
            }
        }
    }

    private void checkMultipleRootElements() {
        if (getRootElementProcessed()) {
            reportFatalError("MultipleRootElements");
        }
        setRootElementProcessed(true);
    }

    private void setRootElementProcessed(boolean seenRoot) {
        if (isRootDocument()) {
            this.fSeenRootElement = seenRoot;
        } else {
            this.fParentXIncludeHandler.setRootElementProcessed(seenRoot);
        }
    }

    private boolean getRootElementProcessed() {
        return isRootDocument() ? this.fSeenRootElement : this.fParentXIncludeHandler.getRootElementProcessed();
    }

    protected void copyFeatures(XMLComponentManager from, ParserConfigurationSettings to) {
        copyFeatures1(Constants.getXercesFeatures(), Constants.XERCES_FEATURE_PREFIX, from, to);
        copyFeatures1(Constants.getSAXFeatures(), Constants.SAX_FEATURE_PREFIX, from, to);
    }

    protected void copyFeatures(XMLComponentManager from, XMLParserConfiguration to) {
        copyFeatures1(Constants.getXercesFeatures(), Constants.XERCES_FEATURE_PREFIX, from, to);
        copyFeatures1(Constants.getSAXFeatures(), Constants.SAX_FEATURE_PREFIX, from, to);
    }

    private void copyFeatures1(Enumeration features, String featurePrefix, XMLComponentManager from, ParserConfigurationSettings to) {
        while (features.hasMoreElements()) {
            String featureId = new StringBuilder(String.valueOf(featurePrefix)).append((String) features.nextElement()).toString();
            to.addRecognizedFeatures(new String[]{featureId});
            try {
                to.setFeature(featureId, from.getFeature(featureId));
            } catch (XMLConfigurationException e) {
            }
        }
    }

    private void copyFeatures1(Enumeration features, String featurePrefix, XMLComponentManager from, XMLParserConfiguration to) {
        while (features.hasMoreElements()) {
            String featureId = new StringBuilder(String.valueOf(featurePrefix)).append((String) features.nextElement()).toString();
            try {
                to.setFeature(featureId, from.getFeature(featureId));
            } catch (XMLConfigurationException e) {
            }
        }
    }

    protected void saveBaseURI() {
        this.fBaseURIScope.push(this.fDepth);
        this.fBaseURI.push(this.fCurrentBaseURI.getBaseSystemId());
        this.fLiteralSystemID.push(this.fCurrentBaseURI.getLiteralSystemId());
        this.fExpandedSystemID.push(this.fCurrentBaseURI.getExpandedSystemId());
    }

    protected void restoreBaseURI() {
        this.fBaseURI.pop();
        this.fLiteralSystemID.pop();
        this.fExpandedSystemID.pop();
        this.fBaseURIScope.pop();
        this.fCurrentBaseURI.setBaseSystemId((String) this.fBaseURI.peek());
        this.fCurrentBaseURI.setLiteralSystemId((String) this.fLiteralSystemID.peek());
        this.fCurrentBaseURI.setExpandedSystemId((String) this.fExpandedSystemID.peek());
    }

    protected void saveLanguage(String language) {
        this.fLanguageScope.push(this.fDepth);
        this.fLanguageStack.push(language);
    }

    public String restoreLanguage() {
        this.fLanguageStack.pop();
        this.fLanguageScope.pop();
        return (String) this.fLanguageStack.peek();
    }

    public String getBaseURI(int depth) {
        return (String) this.fExpandedSystemID.elementAt(scopeOfBaseURI(depth));
    }

    public String getLanguage(int depth) {
        return (String) this.fLanguageStack.elementAt(scopeOfLanguage(depth));
    }

    public String getRelativeURI(int depth) throws MalformedURIException {
        int start = scopeOfBaseURI(depth) + 1;
        if (start == this.fBaseURIScope.size()) {
            return "";
        }
        URI uri = new URI("file", (String) this.fLiteralSystemID.elementAt(start));
        int i = start + 1;
        while (i < this.fBaseURIScope.size()) {
            i++;
            uri = new URI(uri, (String) this.fLiteralSystemID.elementAt(i));
        }
        return uri.getPath();
    }

    private int scopeOfBaseURI(int depth) {
        for (int i = this.fBaseURIScope.size() - 1; i >= 0; i--) {
            if (this.fBaseURIScope.elementAt(i) <= depth) {
                return i;
            }
        }
        return -1;
    }

    private int scopeOfLanguage(int depth) {
        for (int i = this.fLanguageScope.size() - 1; i >= 0; i--) {
            if (this.fLanguageScope.elementAt(i) <= depth) {
                return i;
            }
        }
        return -1;
    }

    protected void processXMLBaseAttributes(XMLAttributes attributes) {
        String baseURIValue = attributes.getValue(NamespaceContext.XML_URI, "base");
        if (baseURIValue != null) {
            try {
                String expandedValue = XMLEntityManager.expandSystemId(baseURIValue, this.fCurrentBaseURI.getExpandedSystemId(), false);
                this.fCurrentBaseURI.setLiteralSystemId(baseURIValue);
                this.fCurrentBaseURI.setBaseSystemId(this.fCurrentBaseURI.getExpandedSystemId());
                this.fCurrentBaseURI.setExpandedSystemId(expandedValue);
                saveBaseURI();
            } catch (MalformedURIException e) {
            }
        }
    }

    protected void processXMLLangAttributes(XMLAttributes attributes) {
        String language = attributes.getValue(NamespaceContext.XML_URI, "lang");
        if (language != null) {
            this.fCurrentLanguage = language;
            saveLanguage(this.fCurrentLanguage);
        }
    }

    private boolean isValidInHTTPHeader(String value) {
        for (int i = value.length() - 1; i >= 0; i--) {
            char ch = value.charAt(i);
            if (ch < ' ' || ch > '~') {
                return false;
            }
        }
        return true;
    }

    private XMLInputSource createInputSource(String publicId, String systemId, String baseSystemId, String accept, String acceptLanguage) {
        HTTPInputSource httpSource = new HTTPInputSource(publicId, systemId, baseSystemId);
        if (accept != null && accept.length() > 0) {
            httpSource.setHTTPRequestProperty("Accept", accept);
        }
        if (acceptLanguage != null && acceptLanguage.length() > 0) {
            httpSource.setHTTPRequestProperty("Accept-Language", acceptLanguage);
        }
        return httpSource;
    }

    private boolean isEqual(String one, String two) {
        return one == two || (one != null && one.equals(two));
    }

    private String escapeHref(String href) {
        int len = href.length();
        StringBuffer buffer = new StringBuffer(len * 3);
        int i = 0;
        while (i < len) {
            int ch = href.charAt(i);
            if (ch > 126) {
                break;
            } else if (ch < 32) {
                return href;
            } else {
                if (gNeedEscaping[ch]) {
                    buffer.append('%');
                    buffer.append(gAfterEscaping1[ch]);
                    buffer.append(gAfterEscaping2[ch]);
                } else {
                    buffer.append((char) ch);
                }
                i++;
            }
        }
        if (i < len) {
            int j = i;
            while (j < len) {
                ch = href.charAt(j);
                if ((ch < 32 || ch > 126) && ((ch < 160 || ch > 55295) && ((ch < 63744 || ch > 64975) && (ch < 65008 || ch > 65519)))) {
                    if (!XMLChar.isHighSurrogate(ch)) {
                        return href;
                    }
                    j++;
                    if (j >= len) {
                        return href;
                    }
                    int ch2 = href.charAt(j);
                    if (!XMLChar.isLowSurrogate(ch2)) {
                        return href;
                    }
                    ch2 = XMLChar.supplemental((char) ch, (char) ch2);
                    if (ch2 >= 983040 || (SupportMenu.USER_MASK & ch2) > 65533) {
                        return href;
                    }
                }
                j++;
            }
            try {
                for (byte b : href.substring(i).getBytes("UTF-8")) {
                    if (b < (byte) 0) {
                        ch = b + 256;
                        buffer.append('%');
                        buffer.append(gHexChs[ch >> 4]);
                        buffer.append(gHexChs[ch & 15]);
                    } else if (gNeedEscaping[b]) {
                        buffer.append('%');
                        buffer.append(gAfterEscaping1[b]);
                        buffer.append(gAfterEscaping2[b]);
                    } else {
                        buffer.append((char) b);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                return href;
            }
        }
        if (buffer.length() != len) {
            return buffer.toString();
        }
        return href;
    }
}
