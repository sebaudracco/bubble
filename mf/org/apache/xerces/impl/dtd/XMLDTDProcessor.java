package mf.org.apache.xerces.impl.dtd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDContentModelFilter;
import mf.org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import mf.org.apache.xerces.xni.parser.XMLDTDFilter;
import mf.org.apache.xerces.xni.parser.XMLDTDSource;

public class XMLDTDProcessor implements XMLComponent, XMLDTDFilter, XMLDTDContentModelFilter {
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS;
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS = new Object[4];
    private static final String[] RECOGNIZED_FEATURES = new String[]{VALIDATION, WARN_ON_DUPLICATE_ATTDEF, WARN_ON_UNDECLARED_ELEMDEF, NOTIFY_CHAR_REFS};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/grammar-pool", DTD_VALIDATOR};
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final int TOP_LEVEL_SCOPE = -1;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDContentModelSource fDTDContentModelSource;
    private String fDTDElementDeclName = null;
    private final ArrayList fDTDElementDecls = new ArrayList();
    protected DTDGrammar fDTDGrammar;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDSource fDTDSource;
    protected boolean fDTDValidation;
    private final XMLEntityDecl fEntityDecl = new XMLEntityDecl();
    protected XMLErrorReporter fErrorReporter;
    protected DTDGrammarBucket fGrammarBucket;
    protected XMLGrammarPool fGrammarPool;
    protected boolean fInDTDIgnore;
    protected Locale fLocale;
    private boolean fMixed;
    private final ArrayList fMixedElementTypes = new ArrayList();
    private final HashMap fNDataDeclNotations = new HashMap();
    private HashMap fNotationEnumVals;
    private boolean fPerformValidation;
    protected SymbolTable fSymbolTable;
    private HashMap fTableOfIDAttributeNames;
    private HashMap fTableOfNOTATIONAttributeNames;
    protected boolean fValidation;
    protected XMLDTDValidator fValidator;
    protected boolean fWarnDuplicateAttdef;
    protected boolean fWarnOnUndeclaredElemdef;

    static {
        Boolean[] boolArr = new Boolean[4];
        boolArr[1] = Boolean.FALSE;
        boolArr[2] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        boolean parser_settings;
        try {
            parser_settings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            parser_settings = true;
        }
        if (parser_settings) {
            try {
                this.fValidation = componentManager.getFeature(VALIDATION);
            } catch (XMLConfigurationException e2) {
                this.fValidation = false;
            }
            try {
                boolean z;
                if (componentManager.getFeature("http://apache.org/xml/features/validation/schema")) {
                    z = false;
                } else {
                    z = true;
                }
                this.fDTDValidation = z;
            } catch (XMLConfigurationException e3) {
                this.fDTDValidation = true;
            }
            try {
                this.fWarnDuplicateAttdef = componentManager.getFeature(WARN_ON_DUPLICATE_ATTDEF);
            } catch (XMLConfigurationException e4) {
                this.fWarnDuplicateAttdef = false;
            }
            try {
                this.fWarnOnUndeclaredElemdef = componentManager.getFeature(WARN_ON_UNDECLARED_ELEMDEF);
            } catch (XMLConfigurationException e5) {
                this.fWarnOnUndeclaredElemdef = false;
            }
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            this.fSymbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            try {
                this.fGrammarPool = (XMLGrammarPool) componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
            } catch (XMLConfigurationException e6) {
                this.fGrammarPool = null;
            }
            try {
                this.fValidator = (XMLDTDValidator) componentManager.getProperty(DTD_VALIDATOR);
            } catch (XMLConfigurationException e7) {
                this.fValidator = null;
            } catch (ClassCastException e8) {
                this.fValidator = null;
            }
            if (this.fValidator != null) {
                this.fGrammarBucket = this.fValidator.getGrammarBucket();
            } else {
                this.fGrammarBucket = null;
            }
            reset();
            return;
        }
        reset();
    }

    protected void reset() {
        this.fDTDGrammar = null;
        this.fInDTDIgnore = false;
        this.fNDataDeclNotations.clear();
        if (this.fValidation) {
            if (this.fNotationEnumVals == null) {
                this.fNotationEnumVals = new HashMap();
            }
            this.fNotationEnumVals.clear();
            this.fTableOfIDAttributeNames = new HashMap();
            this.fTableOfNOTATIONAttributeNames = new HashMap();
        }
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
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

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler dtdContentModelHandler) {
        this.fDTDContentModelHandler = dtdContentModelHandler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startExternalSubset(identifier, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startExternalSubset(identifier, augs);
        }
    }

    public void endExternalSubset(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endExternalSubset(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endExternalSubset(augs);
        }
    }

    protected static void checkStandaloneEntityRef(String name, DTDGrammar grammar, XMLEntityDecl tempEntityDecl, XMLErrorReporter errorReporter) throws XNIException {
        int entIndex = grammar.getEntityDeclIndex(name);
        if (entIndex > -1) {
            grammar.getEntityDecl(entIndex, tempEntityDecl);
            if (tempEntityDecl.inExternal) {
                errorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{name}, (short) 1);
            }
        }
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.comment(text, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(text, augs);
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.processingInstruction(target, data, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(target, data, augs);
        }
    }

    public void startDTD(XMLLocator locator, Augmentations augs) throws XNIException {
        this.fNDataDeclNotations.clear();
        this.fDTDElementDecls.clear();
        if (!this.fGrammarBucket.getActiveGrammar().isImmutable()) {
            this.fDTDGrammar = this.fGrammarBucket.getActiveGrammar();
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startDTD(locator, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(locator, augs);
        }
    }

    public void ignoredCharacters(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.ignoredCharacters(text, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.ignoredCharacters(text, augs);
        }
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.textDecl(version, encoding, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.textDecl(version, encoding, augs);
        }
    }

    public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fDTDGrammar != null && this.fGrammarBucket.getStandalone()) {
            checkStandaloneEntityRef(name, this.fDTDGrammar, this.fEntityDecl, this.fErrorReporter);
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startParameterEntity(name, identifier, encoding, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, augs);
        }
    }

    public void endParameterEntity(String name, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endParameterEntity(name, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endParameterEntity(name, augs);
        }
    }

    public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
        if (this.fValidation) {
            if (this.fDTDElementDecls.contains(name)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_ALREADY_DECLARED", new Object[]{name}, (short) 1);
            } else {
                this.fDTDElementDecls.add(name);
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.elementDecl(name, contentModel, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, augs);
        }
    }

    public void startAttlist(String elementName, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startAttlist(elementName, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elementName, augs);
        }
    }

    public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augs) throws XNIException {
        if (!(type == XMLSymbols.fCDATASymbol || defaultValue == null)) {
            normalizeDefaultAttrValue(defaultValue);
        }
        if (this.fValidation) {
            DTDGrammar grammar;
            int i;
            String str;
            boolean duplicateAttributeDef = false;
            if (this.fDTDGrammar != null) {
                grammar = this.fDTDGrammar;
            } else {
                grammar = this.fGrammarBucket.getActiveGrammar();
            }
            if (grammar.getAttributeDeclIndex(grammar.getElementDeclIndex(elementName), attributeName) != -1) {
                duplicateAttributeDef = true;
                if (this.fWarnDuplicateAttdef) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ATTRIBUTE_DEFINITION", new Object[]{elementName, attributeName}, (short) 0);
                }
            }
            if (type == XMLSymbols.fIDSymbol) {
                if (!(defaultValue == null || defaultValue.length == 0 || (defaultType != null && (defaultType == XMLSymbols.fIMPLIEDSymbol || defaultType == XMLSymbols.fREQUIREDSymbol)))) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IDDefaultTypeInvalid", new Object[]{attributeName}, (short) 1);
                }
                if (!this.fTableOfIDAttributeNames.containsKey(elementName)) {
                    this.fTableOfIDAttributeNames.put(elementName, attributeName);
                } else if (!duplicateAttributeDef) {
                    String previousIDAttributeName = (String) this.fTableOfIDAttributeNames.get(elementName);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_ID_ATTRIBUTE", new Object[]{elementName, previousIDAttributeName, attributeName}, (short) 1);
                }
            }
            if (type == XMLSymbols.fNOTATIONSymbol) {
                for (Object put : enumeration) {
                    this.fNotationEnumVals.put(put, attributeName);
                }
                if (!this.fTableOfNOTATIONAttributeNames.containsKey(elementName)) {
                    this.fTableOfNOTATIONAttributeNames.put(elementName, attributeName);
                } else if (!duplicateAttributeDef) {
                    String previousNOTATIONAttributeName = (String) this.fTableOfNOTATIONAttributeNames.get(elementName);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_NOTATION_ATTRIBUTE", new Object[]{elementName, previousNOTATIONAttributeName, attributeName}, (short) 1);
                }
            }
            if (type == XMLSymbols.fENUMERATIONSymbol || type == XMLSymbols.fNOTATIONSymbol) {
                for (i = 0; i < enumeration.length; i++) {
                    int j = i + 1;
                    while (j < enumeration.length) {
                        if (enumeration[i].equals(enumeration[j])) {
                            XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
                            String str2 = "http://www.w3.org/TR/1998/REC-xml-19980210";
                            if (type == XMLSymbols.fENUMERATIONSymbol) {
                                str = "MSG_DISTINCT_TOKENS_IN_ENUMERATION";
                            } else {
                                str = "MSG_DISTINCT_NOTATION_IN_ENUMERATION";
                            }
                            xMLErrorReporter.reportError(str2, str, new Object[]{elementName, enumeration[i], attributeName}, (short) 1);
                        } else {
                            j++;
                        }
                    }
                }
            }
            boolean ok = true;
            if (defaultValue != null && (defaultType == null || (defaultType != null && defaultType == XMLSymbols.fFIXEDSymbol))) {
                String value = defaultValue.toString();
                if (type == XMLSymbols.fNMTOKENSSymbol || type == XMLSymbols.fENTITIESSymbol || type == XMLSymbols.fIDREFSSymbol) {
                    StringTokenizer stringTokenizer = new StringTokenizer(value, " ");
                    if (stringTokenizer.hasMoreTokens()) {
                        do {
                            String nmtoken = stringTokenizer.nextToken();
                            if (type != XMLSymbols.fNMTOKENSSymbol) {
                                if ((type == XMLSymbols.fENTITIESSymbol || type == XMLSymbols.fIDREFSSymbol) && !isValidName(nmtoken)) {
                                    ok = false;
                                    break;
                                }
                            } else if (!isValidNmtoken(nmtoken)) {
                                ok = false;
                                break;
                            }
                        } while (stringTokenizer.hasMoreTokens());
                    }
                } else {
                    if (type == XMLSymbols.fENTITYSymbol || type == XMLSymbols.fIDSymbol || type == XMLSymbols.fIDREFSymbol || type == XMLSymbols.fNOTATIONSymbol) {
                        if (!isValidName(value)) {
                            ok = false;
                        }
                    } else if ((type == XMLSymbols.fNMTOKENSymbol || type == XMLSymbols.fENUMERATIONSymbol) && !isValidNmtoken(value)) {
                        ok = false;
                    }
                    if (type == XMLSymbols.fNOTATIONSymbol || type == XMLSymbols.fENUMERATIONSymbol) {
                        ok = false;
                        for (String str3 : enumeration) {
                            if (defaultValue.equals(str3)) {
                                ok = true;
                            }
                        }
                    }
                }
                if (!ok) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATT_DEFAULT_INVALID", new Object[]{attributeName, value}, (short) 1);
                }
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.attributeDecl(elementName, attributeName, type, enumeration, defaultType, defaultValue, nonNormalizedDefaultValue, augs);
        }
    }

    public void endAttlist(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endAttlist(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(augs);
        }
    }

    public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augs) throws XNIException {
        if ((this.fDTDGrammar != null ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar()).getEntityDeclIndex(name) == -1) {
            if (this.fDTDGrammar != null) {
                this.fDTDGrammar.internalEntityDecl(name, text, nonNormalizedText, augs);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(name, text, nonNormalizedText, augs);
            }
        }
    }

    public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        if ((this.fDTDGrammar != null ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar()).getEntityDeclIndex(name) == -1) {
            if (this.fDTDGrammar != null) {
                this.fDTDGrammar.externalEntityDecl(name, identifier, augs);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.externalEntityDecl(name, identifier, augs);
            }
        }
    }

    public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augs) throws XNIException {
        if (this.fValidation) {
            this.fNDataDeclNotations.put(name, notation);
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.unparsedEntityDecl(name, identifier, notation, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.unparsedEntityDecl(name, identifier, notation, augs);
        }
    }

    public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        if (this.fValidation) {
            if ((this.fDTDGrammar != null ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar()).getNotationDeclIndex(name) != -1) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "UniqueNotationName", new Object[]{name}, (short) 1);
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.notationDecl(name, identifier, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.notationDecl(name, identifier, augs);
        }
    }

    public void startConditional(short type, Augmentations augs) throws XNIException {
        boolean z = true;
        if (type != (short) 1) {
            z = false;
        }
        this.fInDTDIgnore = z;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startConditional(type, augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startConditional(type, augs);
        }
    }

    public void endConditional(Augmentations augs) throws XNIException {
        this.fInDTDIgnore = false;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endConditional(augs);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endConditional(augs);
        }
    }

    public void endDTD(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endDTD(augs);
            if (this.fGrammarPool != null) {
                this.fGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[]{this.fDTDGrammar});
            }
        }
        if (this.fValidation) {
            String attributeName;
            DTDGrammar grammar = this.fDTDGrammar != null ? this.fDTDGrammar : this.fGrammarBucket.getActiveGrammar();
            for (Entry entry : this.fNDataDeclNotations.entrySet()) {
                if (grammar.getNotationDeclIndex((String) entry.getValue()) == -1) {
                    String entity = (String) entry.getKey();
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_UNPARSED_ENTITYDECL", new Object[]{entity, (String) entry.getValue()}, (short) 1);
                }
            }
            for (Entry entry2 : this.fNotationEnumVals.entrySet()) {
                if (grammar.getNotationDeclIndex((String) entry2.getKey()) == -1) {
                    attributeName = (String) entry2.getValue();
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_NOTATIONTYPE_ATTRIBUTE", new Object[]{attributeName, (String) entry2.getKey()}, (short) 1);
                }
            }
            for (Entry entry22 : this.fTableOfNOTATIONAttributeNames.entrySet()) {
                if (grammar.getContentSpecType(grammar.getElementDeclIndex((String) entry22.getKey())) == (short) 1) {
                    attributeName = (String) entry22.getValue();
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "NoNotationOnEmptyElement", new Object[]{(String) entry22.getKey(), attributeName}, (short) 1);
                }
            }
            this.fTableOfIDAttributeNames = null;
            this.fTableOfNOTATIONAttributeNames = null;
            if (this.fWarnOnUndeclaredElemdef) {
                checkDeclaredElements(grammar);
            }
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endDTD(augs);
        }
    }

    public void setDTDSource(XMLDTDSource source) {
        this.fDTDSource = source;
    }

    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
    }

    public void setDTDContentModelSource(XMLDTDContentModelSource source) {
        this.fDTDContentModelSource = source;
    }

    public XMLDTDContentModelSource getDTDContentModelSource() {
        return this.fDTDContentModelSource;
    }

    public void startContentModel(String elementName, Augmentations augs) throws XNIException {
        if (this.fValidation) {
            this.fDTDElementDeclName = elementName;
            this.fMixedElementTypes.clear();
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startContentModel(elementName, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(elementName, augs);
        }
    }

    public void any(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.any(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.any(augs);
        }
    }

    public void empty(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.empty(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.empty(augs);
        }
    }

    public void startGroup(Augmentations augs) throws XNIException {
        this.fMixed = false;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.startGroup(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startGroup(augs);
        }
    }

    public void pcdata(Augmentations augs) {
        this.fMixed = true;
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.pcdata(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(augs);
        }
    }

    public void element(String elementName, Augmentations augs) throws XNIException {
        if (this.fMixed && this.fValidation) {
            if (this.fMixedElementTypes.contains(elementName)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "DuplicateTypeInMixedContent", new Object[]{this.fDTDElementDeclName, elementName}, (short) 1);
            } else {
                this.fMixedElementTypes.add(elementName);
            }
        }
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.element(elementName, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.element(elementName, augs);
        }
    }

    public void separator(short separator, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.separator(separator, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.separator(separator, augs);
        }
    }

    public void occurrence(short occurrence, Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.occurrence(occurrence, augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.occurrence(occurrence, augs);
        }
    }

    public void endGroup(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endGroup(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endGroup(augs);
        }
    }

    public void endContentModel(Augmentations augs) throws XNIException {
        if (this.fDTDGrammar != null) {
            this.fDTDGrammar.endContentModel(augs);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(augs);
        }
    }

    private boolean normalizeDefaultAttrValue(XMLString value) {
        boolean skipSpace = true;
        int current = value.offset;
        int end = value.offset + value.length;
        int i = value.offset;
        int current2 = current;
        while (i < end) {
            if (value.ch[i] != ' ') {
                if (current2 != i) {
                    value.ch[current2] = value.ch[i];
                }
                current = current2 + 1;
                skipSpace = false;
            } else if (skipSpace) {
                current = current2;
            } else {
                current = current2 + 1;
                value.ch[current2] = ' ';
                skipSpace = true;
            }
            i++;
            current2 = current;
        }
        if (current2 != end) {
            if (skipSpace) {
                current = current2 - 1;
            } else {
                current = current2;
            }
            value.length = current - value.offset;
            return true;
        }
        current = current2;
        return false;
    }

    protected boolean isValidNmtoken(String nmtoken) {
        return XMLChar.isValidNmtoken(nmtoken);
    }

    protected boolean isValidName(String name) {
        return XMLChar.isValidName(name);
    }

    private void checkDeclaredElements(DTDGrammar grammar) {
        int elementIndex = grammar.getFirstElementDeclIndex();
        XMLContentSpec contentSpec = new XMLContentSpec();
        while (elementIndex >= 0) {
            int type = grammar.getContentSpecType(elementIndex);
            if (type == 3 || type == 2) {
                checkDeclaredElements(grammar, elementIndex, grammar.getContentSpecIndex(elementIndex), contentSpec);
            }
            elementIndex = grammar.getNextElementDeclIndex(elementIndex);
        }
    }

    private void checkDeclaredElements(DTDGrammar grammar, int elementIndex, int contentSpecIndex, XMLContentSpec contentSpec) {
        grammar.getContentSpec(contentSpecIndex, contentSpec);
        if (contentSpec.type == (short) 0) {
            String value = contentSpec.value;
            if (value != null && grammar.getElementDeclIndex(value) == -1) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "UndeclaredElementInContentSpec", new Object[]{grammar.getElementDeclName(elementIndex).rawname, value}, (short) 0);
            }
        } else if (contentSpec.type == (short) 4 || contentSpec.type == (short) 5) {
            int leftNode = ((int[]) contentSpec.value)[0];
            int rightNode = ((int[]) contentSpec.otherValue)[0];
            checkDeclaredElements(grammar, elementIndex, leftNode, contentSpec);
            checkDeclaredElements(grammar, elementIndex, rightNode, contentSpec);
        } else if (contentSpec.type == (short) 2 || contentSpec.type == (short) 1 || contentSpec.type == (short) 3) {
            checkDeclaredElements(grammar, elementIndex, ((int[]) contentSpec.value)[0], contentSpec);
        }
    }
}
