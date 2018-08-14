package mf.org.apache.xerces.impl.xs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Vector;
import javax.xml.namespace.QName;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.RevalidationHandler;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.DatatypeException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.validation.ConfigurableValidationState;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.validation.ValidationState;
import mf.org.apache.xerces.impl.xs.identity.Field;
import mf.org.apache.xerces.impl.xs.identity.FieldActivator;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.identity.KeyRef;
import mf.org.apache.xerces.impl.xs.identity.Selector;
import mf.org.apache.xerces.impl.xs.identity.Selector.Matcher;
import mf.org.apache.xerces.impl.xs.identity.UniqueOrKey;
import mf.org.apache.xerces.impl.xs.identity.ValueStore;
import mf.org.apache.xerces.impl.xs.identity.XPathMatcher;
import mf.org.apache.xerces.impl.xs.models.CMBuilder;
import mf.org.apache.xerces.impl.xs.models.CMNodeFactory;
import mf.org.apache.xerces.impl.xs.models.XSCMValidator;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.IntStack;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public class XMLSchemaValidator implements XMLComponent, XMLDocumentFilter, FieldActivator, RevalidationHandler, XSElementDeclHelper {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    private static final int BUFFER_SIZE = 20;
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_NORMALIZATION = false;
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    private static final Hashtable EMPTY_TABLE = new Hashtable();
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS = new Boolean[17];
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    protected static final int ID_CONSTRAINT_NUM = 1;
    protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    static final int INC_STACK_SIZE = 8;
    static final int INITIAL_STACK_SIZE = 8;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS = new Object[11];
    private static final String[] RECOGNIZED_FEATURES = new String[]{VALIDATION, SCHEMA_VALIDATION, DYNAMIC_VALIDATION, SCHEMA_FULL_CHECKING, ALLOW_JAVA_ENCODINGS, CONTINUE_AFTER_FATAL_ERROR, STANDARD_URI_CONFORMANT_FEATURE, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, USE_GRAMMAR_POOL_ONLY, IGNORE_XSI_TYPE, ID_IDREF_CHECKING, IDENTITY_CONSTRAINT_CHECKING, UNPARSED_ENTITY_CHECKING, NAMESPACE_GROWTH, TOLERATE_DUPLICATES};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", VALIDATION_MANAGER, SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", ROOT_TYPE_DEF, ROOT_ELEMENT_DECL, SCHEMA_DV_FACTORY};
    protected static final String ROOT_ELEMENT_DECL = "http://apache.org/xml/properties/validation/schema/root-element-declaration";
    protected static final String ROOT_TYPE_DEF = "http://apache.org/xml/properties/validation/schema/root-type-definition";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    static final XSAttributeDecl XSI_NIL = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NIL);
    static final XSAttributeDecl XSI_NONAMESPACESCHEMALOCATION = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
    static final XSAttributeDecl XSI_SCHEMALOCATION = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
    static final XSAttributeDecl XSI_TYPE = SchemaGrammar.SG_XSI.getGlobalAttributeDecl(SchemaSymbols.XSI_TYPE);
    private boolean fAppendBuffer = true;
    protected boolean fAugPSVI = true;
    protected final AugmentationsImpl fAugmentations = new AugmentationsImpl();
    private final StringBuffer fBuffer = new StringBuffer();
    private final CMBuilder fCMBuilder = new CMBuilder(this.nodeFactory);
    private XSCMValidator[] fCMStack = new XSCMValidator[8];
    private int[][] fCMStateStack = new int[8][];
    private int[] fCurrCMState;
    private XSCMValidator fCurrentCM;
    private XSElementDecl fCurrentElemDecl;
    protected ElementPSVImpl fCurrentPSVI = new ElementPSVImpl();
    private XSTypeDefinition fCurrentType;
    protected XMLString fDefaultValue;
    protected boolean fDoValidation = false;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected boolean fDynamicValidation = false;
    private XSElementDecl[] fElemDeclStack = new XSElementDecl[8];
    private int fElementDepth;
    private final XMLString fEmptyXMLStr = new XMLString(null, 0, -1);
    protected boolean fEntityRef = false;
    protected XMLEntityResolver fEntityResolver;
    protected final Hashtable fExpandedLocationPairs = new Hashtable();
    protected String fExternalNoNamespaceSchema = null;
    protected String fExternalSchemas = null;
    private boolean fFirstChunk = true;
    protected boolean fFullChecking = false;
    private final XSGrammarBucket fGrammarBucket = new XSGrammarBucket();
    protected XMLGrammarPool fGrammarPool;
    private boolean fIDCChecking;
    protected boolean fIdConstraint = false;
    private int fIgnoreXSITypeDepth;
    protected boolean fInCDATA = false;
    protected Object fJaxpSchemaSource = null;
    protected final Hashtable fLocationPairs = new Hashtable();
    private XMLLocator fLocator;
    protected XPathMatcherStack fMatcherStack = new XPathMatcherStack();
    private int fNFullValidationDepth;
    private int fNNoneValidationDepth;
    protected boolean fNamespaceGrowth = false;
    private boolean fNil;
    private boolean[] fNilStack = new boolean[8];
    protected boolean fNormalizeData = true;
    private final XMLString fNormalizedStr = new XMLString();
    private XSNotationDecl fNotation;
    private XSNotationDecl[] fNotationStack = new XSNotationDecl[8];
    private final XSSimpleType fQNameDV = ((XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME));
    private QName fRootElementDeclQName = null;
    private XSElementDecl fRootElementDeclaration = null;
    private XSTypeDefinition fRootTypeDefinition = null;
    private QName fRootTypeQName = null;
    private boolean fSawCharacters = false;
    private boolean fSawText = false;
    private boolean[] fSawTextStack = new boolean[8];
    protected boolean fSchemaDynamicValidation = false;
    protected boolean fSchemaElementDefault = true;
    private final XMLSchemaLoader fSchemaLoader = new XMLSchemaLoader(this.fXSIErrorReporter.fErrorReporter, this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder);
    private String fSchemaType = null;
    private int fSkipValidationDepth;
    private ValidationState fState4ApplyDefault = new ValidationState();
    private ValidationState fState4XsiType = new ValidationState();
    private boolean fStrictAssess = true;
    private boolean[] fStrictAssessStack = new boolean[8];
    private boolean[] fStringContent = new boolean[8];
    private boolean fSubElement;
    private boolean[] fSubElementStack = new boolean[8];
    private final SubstitutionGroupHandler fSubGroupHandler = new SubstitutionGroupHandler(this);
    protected SymbolTable fSymbolTable;
    private final mf.org.apache.xerces.xni.QName fTempQName = new mf.org.apache.xerces.xni.QName();
    private boolean fTrailing = false;
    private XSTypeDefinition[] fTypeStack = new XSTypeDefinition[8];
    private boolean fUnionType = false;
    protected final ArrayList fUnparsedLocations = new ArrayList();
    protected boolean fUseGrammarPoolOnly = false;
    private ValidatedInfo fValidatedInfo = new ValidatedInfo();
    protected ValidationManager fValidationManager = null;
    private String fValidationRoot;
    protected ConfigurableValidationState fValidationState = new ConfigurableValidationState();
    protected ValueStoreCache fValueStoreCache = new ValueStoreCache();
    private short fWhiteSpace = (short) -1;
    protected final XSDDescription fXSDDescription = new XSDDescription();
    protected final XSIErrorReporter fXSIErrorReporter = new XSIErrorReporter();
    private final CMNodeFactory nodeFactory = new CMNodeFactory();

    protected abstract class ValueStoreBase implements ValueStore {
        protected int fFieldCount = 0;
        protected Field[] fFields = null;
        protected IdentityConstraint fIdentityConstraint;
        private ShortList fItemValueType = null;
        public Vector fItemValueTypes = null;
        private int fItemValueTypesLength = 0;
        protected ShortList[] fLocalItemValueTypes = null;
        protected short[] fLocalValueTypes = null;
        protected Object[] fLocalValues = null;
        final StringBuffer fTempBuffer = new StringBuffer();
        private boolean fUseItemValueTypeVector = false;
        private boolean fUseValueTypeVector = false;
        private short fValueType = (short) 0;
        public ShortVector fValueTypes = null;
        private int fValueTypesLength = 0;
        public final Vector fValues = new Vector();
        protected int fValuesCount;

        protected ValueStoreBase(IdentityConstraint identityConstraint) {
            this.fIdentityConstraint = identityConstraint;
            this.fFieldCount = this.fIdentityConstraint.getFieldCount();
            this.fFields = new Field[this.fFieldCount];
            this.fLocalValues = new Object[this.fFieldCount];
            this.fLocalValueTypes = new short[this.fFieldCount];
            this.fLocalItemValueTypes = new ShortList[this.fFieldCount];
            for (int i = 0; i < this.fFieldCount; i++) {
                this.fFields[i] = this.fIdentityConstraint.getFieldAt(i);
            }
        }

        public void clear() {
            this.fValuesCount = 0;
            this.fUseValueTypeVector = false;
            this.fValueTypesLength = 0;
            this.fValueType = (short) 0;
            this.fUseItemValueTypeVector = false;
            this.fItemValueTypesLength = 0;
            this.fItemValueType = null;
            this.fValues.setSize(0);
            if (this.fValueTypes != null) {
                this.fValueTypes.clear();
            }
            if (this.fItemValueTypes != null) {
                this.fItemValueTypes.setSize(0);
            }
        }

        public void append(ValueStoreBase newVal) {
            for (int i = 0; i < newVal.fValues.size(); i++) {
                this.fValues.addElement(newVal.fValues.elementAt(i));
            }
        }

        public void startValueScope() {
            this.fValuesCount = 0;
            for (int i = 0; i < this.fFieldCount; i++) {
                this.fLocalValues[i] = null;
                this.fLocalValueTypes[i] = (short) 0;
                this.fLocalItemValueTypes[i] = null;
            }
        }

        public void endValueScope() {
            String eName;
            String cName;
            if (this.fValuesCount == 0) {
                if (this.fIdentityConstraint.getCategory() == (short) 1) {
                    eName = this.fIdentityConstraint.getElementName();
                    cName = this.fIdentityConstraint.getIdentityConstraintName();
                    XMLSchemaValidator.this.reportSchemaError("AbsentKeyValue", new Object[]{eName, cName});
                }
            } else if (this.fValuesCount != this.fFieldCount && this.fIdentityConstraint.getCategory() == (short) 1) {
                UniqueOrKey key = this.fIdentityConstraint;
                eName = this.fIdentityConstraint.getElementName();
                cName = key.getIdentityConstraintName();
                XMLSchemaValidator.this.reportSchemaError("KeyNotEnoughValues", new Object[]{eName, cName});
            }
        }

        public void endDocumentFragment() {
        }

        public void endDocument() {
        }

        public void reportError(String key, Object[] args) {
            XMLSchemaValidator.this.reportSchemaError(key, args);
        }

        public void addValue(Field field, boolean mayMatch, Object actualValue, short valueType, ShortList itemValueType) {
            int i = this.fFieldCount - 1;
            while (i > -1 && this.fFields[i] != field) {
                i--;
            }
            if (i == -1) {
                String eName = this.fIdentityConstraint.getElementName();
                String cName = this.fIdentityConstraint.getIdentityConstraintName();
                XMLSchemaValidator.this.reportSchemaError("UnknownField", new Object[]{field.toString(), eName, cName});
                return;
            }
            if (mayMatch) {
                this.fValuesCount++;
            } else {
                cName = this.fIdentityConstraint.getIdentityConstraintName();
                XMLSchemaValidator.this.reportSchemaError("FieldMultipleMatch", new Object[]{field.toString(), cName});
            }
            this.fLocalValues[i] = actualValue;
            this.fLocalValueTypes[i] = valueType;
            this.fLocalItemValueTypes[i] = itemValueType;
            if (this.fValuesCount == this.fFieldCount) {
                checkDuplicateValues();
                for (i = 0; i < this.fFieldCount; i++) {
                    this.fValues.addElement(this.fLocalValues[i]);
                    addValueType(this.fLocalValueTypes[i]);
                    addItemValueType(this.fLocalItemValueTypes[i]);
                }
            }
        }

        public boolean contains() {
            int size = this.fValues.size();
            int i = 0;
            while (i < size) {
                int next = i + this.fFieldCount;
                int j = 0;
                while (j < this.fFieldCount) {
                    Object value1 = this.fLocalValues[j];
                    Object value2 = this.fValues.elementAt(i);
                    short valueType1 = this.fLocalValueTypes[j];
                    short valueType2 = getValueTypeAt(i);
                    if (value1 != null && value2 != null && valueType1 == valueType2 && value1.equals(value2)) {
                        if (valueType1 == (short) 44 || valueType1 == (short) 43) {
                            ShortList list1 = this.fLocalItemValueTypes[j];
                            ShortList list2 = getItemValueTypeAt(i);
                            if (!(list1 == null || list2 == null || !list1.equals(list2))) {
                            }
                        }
                        i++;
                        j++;
                    }
                    i = next;
                }
                return true;
            }
            return false;
        }

        public int contains(ValueStoreBase vsb) {
            Vector values = vsb.fValues;
            int size1 = values.size();
            int i;
            if (this.fFieldCount <= 1) {
                i = 0;
                while (i < size1) {
                    short val = vsb.getValueTypeAt(i);
                    if (!valueTypeContains(val) || !this.fValues.contains(values.elementAt(i))) {
                        return i;
                    }
                    if (val == (short) 44 || val == (short) 43) {
                        if (!itemValueTypeContains(vsb.getItemValueTypeAt(i))) {
                            return i;
                        }
                    }
                    i++;
                }
            } else {
                int size2 = this.fValues.size();
                i = 0;
                while (i < size1) {
                    int j = 0;
                    while (j < size2) {
                        int k = 0;
                        while (k < this.fFieldCount) {
                            Object value1 = values.elementAt(i + k);
                            Object value2 = this.fValues.elementAt(j + k);
                            short valueType1 = vsb.getValueTypeAt(i + k);
                            short valueType2 = getValueTypeAt(j + k);
                            if (value1 == value2 || (valueType1 == valueType2 && value1 != null && value1.equals(value2))) {
                                if (valueType1 == (short) 44 || valueType1 == (short) 43) {
                                    ShortList list1 = vsb.getItemValueTypeAt(i + k);
                                    ShortList list2 = getItemValueTypeAt(j + k);
                                    if (!(list1 == null || list2 == null || !list1.equals(list2))) {
                                    }
                                }
                                k++;
                            }
                            j += this.fFieldCount;
                        }
                        i += this.fFieldCount;
                    }
                    return i;
                }
            }
            return -1;
        }

        protected void checkDuplicateValues() {
        }

        protected String toString(Object[] values) {
            int size = values.length;
            if (size == 0) {
                return "";
            }
            this.fTempBuffer.setLength(0);
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    this.fTempBuffer.append(',');
                }
                this.fTempBuffer.append(values[i]);
            }
            return this.fTempBuffer.toString();
        }

        protected String toString(Vector values, int start, int length) {
            if (length == 0) {
                return "";
            }
            if (length == 1) {
                return String.valueOf(values.elementAt(start));
            }
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    str.append(',');
                }
                str.append(values.elementAt(start + i));
            }
            return str.toString();
        }

        public String toString() {
            String s = super.toString();
            int index1 = s.lastIndexOf(36);
            if (index1 != -1) {
                s = s.substring(index1 + 1);
            }
            int index2 = s.lastIndexOf(46);
            if (index2 != -1) {
                s = s.substring(index2 + 1);
            }
            return new StringBuilder(String.valueOf(s)).append('[').append(this.fIdentityConstraint).append(']').toString();
        }

        private void addValueType(short type) {
            if (this.fUseValueTypeVector) {
                this.fValueTypes.add(type);
                return;
            }
            int i = this.fValueTypesLength;
            this.fValueTypesLength = i + 1;
            if (i == 0) {
                this.fValueType = type;
            } else if (this.fValueType != type) {
                this.fUseValueTypeVector = true;
                if (this.fValueTypes == null) {
                    this.fValueTypes = new ShortVector(this.fValueTypesLength * 2);
                }
                for (int i2 = 1; i2 < this.fValueTypesLength; i2++) {
                    this.fValueTypes.add(this.fValueType);
                }
                this.fValueTypes.add(type);
            }
        }

        private short getValueTypeAt(int index) {
            if (this.fUseValueTypeVector) {
                return this.fValueTypes.valueAt(index);
            }
            return this.fValueType;
        }

        private boolean valueTypeContains(short value) {
            if (this.fUseValueTypeVector) {
                return this.fValueTypes.contains(value);
            }
            return this.fValueType == value;
        }

        private void addItemValueType(ShortList itemValueType) {
            if (this.fUseItemValueTypeVector) {
                this.fItemValueTypes.add(itemValueType);
                return;
            }
            int i = this.fItemValueTypesLength;
            this.fItemValueTypesLength = i + 1;
            if (i == 0) {
                this.fItemValueType = itemValueType;
            } else if (this.fItemValueType == itemValueType) {
            } else {
                if (this.fItemValueType == null || !this.fItemValueType.equals(itemValueType)) {
                    this.fUseItemValueTypeVector = true;
                    if (this.fItemValueTypes == null) {
                        this.fItemValueTypes = new Vector(this.fItemValueTypesLength * 2);
                    }
                    for (int i2 = 1; i2 < this.fItemValueTypesLength; i2++) {
                        this.fItemValueTypes.add(this.fItemValueType);
                    }
                    this.fItemValueTypes.add(itemValueType);
                }
            }
        }

        private ShortList getItemValueTypeAt(int index) {
            if (this.fUseItemValueTypeVector) {
                return (ShortList) this.fItemValueTypes.elementAt(index);
            }
            return this.fItemValueType;
        }

        private boolean itemValueTypeContains(ShortList value) {
            if (this.fUseItemValueTypeVector) {
                return this.fItemValueTypes.contains(value);
            }
            return this.fItemValueType == value || (this.fItemValueType != null && this.fItemValueType.equals(value));
        }
    }

    protected class KeyRefValueStore extends ValueStoreBase {
        protected ValueStoreBase fKeyValueStore;

        public KeyRefValueStore(KeyRef keyRef, KeyValueStore keyValueStore) {
            super(keyRef);
            this.fKeyValueStore = keyValueStore;
        }

        public void endDocumentFragment() {
            super.endDocumentFragment();
            this.fKeyValueStore = (ValueStoreBase) XMLSchemaValidator.this.fValueStoreCache.fGlobalIDConstraintMap.get(((KeyRef) this.fIdentityConstraint).getKey());
            if (this.fKeyValueStore == null) {
                String value = this.fIdentityConstraint.toString();
                XMLSchemaValidator.this.reportSchemaError("KeyRefOutOfScope", new Object[]{value});
                return;
            }
            int errorIndex = this.fKeyValueStore.contains(this);
            if (errorIndex != -1) {
                String values = toString(this.fValues, errorIndex, this.fFieldCount);
                String element = this.fIdentityConstraint.getElementName();
                String name = this.fIdentityConstraint.getName();
                XMLSchemaValidator.this.reportSchemaError("KeyNotFound", new Object[]{name, values, element});
            }
        }

        public void endDocument() {
            super.endDocument();
        }
    }

    protected class KeyValueStore extends ValueStoreBase {
        public KeyValueStore(UniqueOrKey key) {
            super(key);
        }

        protected void checkDuplicateValues() {
            if (contains()) {
                String value = toString(this.fLocalValues);
                String eName = this.fIdentityConstraint.getElementName();
                String cName = this.fIdentityConstraint.getIdentityConstraintName();
                XMLSchemaValidator.this.reportSchemaError("DuplicateKey", new Object[]{value, eName, cName});
            }
        }
    }

    protected static final class LocalIDKey {
        public int fDepth;
        public IdentityConstraint fId;

        public LocalIDKey(IdentityConstraint id, int depth) {
            this.fId = id;
            this.fDepth = depth;
        }

        public int hashCode() {
            return this.fId.hashCode() + this.fDepth;
        }

        public boolean equals(Object localIDKey) {
            if (!(localIDKey instanceof LocalIDKey)) {
                return false;
            }
            LocalIDKey lIDKey = (LocalIDKey) localIDKey;
            if (lIDKey.fId == this.fId && lIDKey.fDepth == this.fDepth) {
                return true;
            }
            return false;
        }
    }

    protected static final class ShortVector {
        private short[] fData;
        private int fLength;

        public ShortVector(int initialCapacity) {
            this.fData = new short[initialCapacity];
        }

        public int length() {
            return this.fLength;
        }

        public void add(short value) {
            ensureCapacity(this.fLength + 1);
            short[] sArr = this.fData;
            int i = this.fLength;
            this.fLength = i + 1;
            sArr[i] = value;
        }

        public short valueAt(int position) {
            return this.fData[position];
        }

        public void clear() {
            this.fLength = 0;
        }

        public boolean contains(short value) {
            for (int i = 0; i < this.fLength; i++) {
                if (this.fData[i] == value) {
                    return true;
                }
            }
            return false;
        }

        private void ensureCapacity(int size) {
            if (this.fData == null) {
                this.fData = new short[8];
            } else if (this.fData.length <= size) {
                short[] newdata = new short[(this.fData.length * 2)];
                System.arraycopy(this.fData, 0, newdata, 0, this.fData.length);
                this.fData = newdata;
            }
        }
    }

    protected class UniqueValueStore extends ValueStoreBase {
        public UniqueValueStore(UniqueOrKey unique) {
            super(unique);
        }

        protected void checkDuplicateValues() {
            if (contains()) {
                String value = toString(this.fLocalValues);
                String eName = this.fIdentityConstraint.getElementName();
                String cName = this.fIdentityConstraint.getIdentityConstraintName();
                XMLSchemaValidator.this.reportSchemaError("DuplicateUnique", new Object[]{value, eName, cName});
            }
        }
    }

    protected class ValueStoreCache {
        protected final HashMap fGlobalIDConstraintMap = new HashMap();
        protected final Stack fGlobalMapStack = new Stack();
        protected final HashMap fIdentityConstraint2ValueStoreMap = new HashMap();
        final LocalIDKey fLocalId = new LocalIDKey();
        protected final ArrayList fValueStores = new ArrayList();

        public void startDocument() {
            this.fValueStores.clear();
            this.fIdentityConstraint2ValueStoreMap.clear();
            this.fGlobalIDConstraintMap.clear();
            this.fGlobalMapStack.removeAllElements();
        }

        public void startElement() {
            if (this.fGlobalIDConstraintMap.size() > 0) {
                this.fGlobalMapStack.push(this.fGlobalIDConstraintMap.clone());
            } else {
                this.fGlobalMapStack.push(null);
            }
            this.fGlobalIDConstraintMap.clear();
        }

        public void endElement() {
            if (!this.fGlobalMapStack.isEmpty()) {
                HashMap oldMap = (HashMap) this.fGlobalMapStack.pop();
                if (oldMap != null) {
                    for (Entry entry : oldMap.entrySet()) {
                        IdentityConstraint id = (IdentityConstraint) entry.getKey();
                        ValueStoreBase oldVal = (ValueStoreBase) entry.getValue();
                        if (oldVal != null) {
                            ValueStoreBase currVal = (ValueStoreBase) this.fGlobalIDConstraintMap.get(id);
                            if (currVal == null) {
                                this.fGlobalIDConstraintMap.put(id, oldVal);
                            } else if (currVal != oldVal) {
                                currVal.append(oldVal);
                            }
                        }
                    }
                }
            }
        }

        public void initValueStoresFor(XSElementDecl eDecl, FieldActivator activator) {
            IdentityConstraint[] icArray = eDecl.fIDConstraints;
            int icCount = eDecl.fIDCPos;
            for (int i = 0; i < icCount; i++) {
                LocalIDKey toHash;
                switch (icArray[i].getCategory()) {
                    case (short) 1:
                        UniqueOrKey key = icArray[i];
                        toHash = new LocalIDKey(key, XMLSchemaValidator.this.fElementDepth);
                        KeyValueStore keyValueStore = (KeyValueStore) this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (keyValueStore == null) {
                            keyValueStore = new KeyValueStore(key);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, keyValueStore);
                        } else {
                            keyValueStore.clear();
                        }
                        this.fValueStores.add(keyValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(icArray[i]);
                        break;
                    case (short) 2:
                        KeyRef keyRef = icArray[i];
                        toHash = new LocalIDKey(keyRef, XMLSchemaValidator.this.fElementDepth);
                        KeyRefValueStore keyRefValueStore = (KeyRefValueStore) this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (keyRefValueStore == null) {
                            keyRefValueStore = new KeyRefValueStore(keyRef, null);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, keyRefValueStore);
                        } else {
                            keyRefValueStore.clear();
                        }
                        this.fValueStores.add(keyRefValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(icArray[i]);
                        break;
                    case (short) 3:
                        UniqueOrKey unique = icArray[i];
                        toHash = new LocalIDKey(unique, XMLSchemaValidator.this.fElementDepth);
                        UniqueValueStore uniqueValueStore = (UniqueValueStore) this.fIdentityConstraint2ValueStoreMap.get(toHash);
                        if (uniqueValueStore == null) {
                            uniqueValueStore = new UniqueValueStore(unique);
                            this.fIdentityConstraint2ValueStoreMap.put(toHash, uniqueValueStore);
                        } else {
                            uniqueValueStore.clear();
                        }
                        this.fValueStores.add(uniqueValueStore);
                        XMLSchemaValidator.this.activateSelectorFor(icArray[i]);
                        break;
                    default:
                        break;
                }
            }
        }

        public ValueStoreBase getValueStoreFor(IdentityConstraint id, int initialDepth) {
            this.fLocalId.fDepth = initialDepth;
            this.fLocalId.fId = id;
            return (ValueStoreBase) this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
        }

        public ValueStoreBase getGlobalValueStoreFor(IdentityConstraint id) {
            return (ValueStoreBase) this.fGlobalIDConstraintMap.get(id);
        }

        public void transplant(IdentityConstraint id, int initialDepth) {
            this.fLocalId.fDepth = initialDepth;
            this.fLocalId.fId = id;
            ValueStoreBase newVals = (ValueStoreBase) this.fIdentityConstraint2ValueStoreMap.get(this.fLocalId);
            if (id.getCategory() != (short) 2) {
                ValueStoreBase currVals = (ValueStoreBase) this.fGlobalIDConstraintMap.get(id);
                if (currVals != null) {
                    currVals.append(newVals);
                    this.fGlobalIDConstraintMap.put(id, currVals);
                    return;
                }
                this.fGlobalIDConstraintMap.put(id, newVals);
            }
        }

        public void endDocument() {
            int count = this.fValueStores.size();
            for (int i = 0; i < count; i++) {
                ((ValueStoreBase) this.fValueStores.get(i)).endDocument();
            }
        }

        public String toString() {
            String s = super.toString();
            int index1 = s.lastIndexOf(36);
            if (index1 != -1) {
                return s.substring(index1 + 1);
            }
            int index2 = s.lastIndexOf(46);
            if (index2 != -1) {
                return s.substring(index2 + 1);
            }
            return s;
        }
    }

    protected static class XPathMatcherStack {
        protected IntStack fContextStack = new IntStack();
        protected XPathMatcher[] fMatchers = new XPathMatcher[4];
        protected int fMatchersCount;

        public void clear() {
            for (int i = 0; i < this.fMatchersCount; i++) {
                this.fMatchers[i] = null;
            }
            this.fMatchersCount = 0;
            this.fContextStack.clear();
        }

        public int size() {
            return this.fContextStack.size();
        }

        public int getMatcherCount() {
            return this.fMatchersCount;
        }

        public void addMatcher(XPathMatcher matcher) {
            ensureMatcherCapacity();
            XPathMatcher[] xPathMatcherArr = this.fMatchers;
            int i = this.fMatchersCount;
            this.fMatchersCount = i + 1;
            xPathMatcherArr[i] = matcher;
        }

        public XPathMatcher getMatcherAt(int index) {
            return this.fMatchers[index];
        }

        public void pushContext() {
            this.fContextStack.push(this.fMatchersCount);
        }

        public void popContext() {
            this.fMatchersCount = this.fContextStack.pop();
        }

        private void ensureMatcherCapacity() {
            if (this.fMatchersCount == this.fMatchers.length) {
                XPathMatcher[] array = new XPathMatcher[(this.fMatchers.length * 2)];
                System.arraycopy(this.fMatchers, 0, array, 0, this.fMatchers.length);
                this.fMatchers = array;
            }
        }
    }

    protected final class XSIErrorReporter {
        int[] fContext = new int[8];
        int fContextCount;
        XMLErrorReporter fErrorReporter;
        Vector fErrors = new Vector();

        protected XSIErrorReporter() {
        }

        public void reset(XMLErrorReporter errorReporter) {
            this.fErrorReporter = errorReporter;
            this.fErrors.removeAllElements();
            this.fContextCount = 0;
        }

        public void pushContext() {
            if (XMLSchemaValidator.this.fAugPSVI) {
                if (this.fContextCount == this.fContext.length) {
                    int[] newArray = new int[(this.fContextCount + 8)];
                    System.arraycopy(this.fContext, 0, newArray, 0, this.fContextCount);
                    this.fContext = newArray;
                }
                int[] iArr = this.fContext;
                int i = this.fContextCount;
                this.fContextCount = i + 1;
                iArr[i] = this.fErrors.size();
            }
        }

        public String[] popContext() {
            String[] strArr = null;
            if (XMLSchemaValidator.this.fAugPSVI) {
                int[] iArr = this.fContext;
                int i = this.fContextCount - 1;
                this.fContextCount = i;
                int contextPos = iArr[i];
                int size = this.fErrors.size() - contextPos;
                if (size != 0) {
                    strArr = new String[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        strArr[i2] = (String) this.fErrors.elementAt(contextPos + i2);
                    }
                    this.fErrors.setSize(contextPos);
                }
            }
            return strArr;
        }

        public String[] mergeContext() {
            String[] strArr = null;
            if (XMLSchemaValidator.this.fAugPSVI) {
                int[] iArr = this.fContext;
                int i = this.fContextCount - 1;
                this.fContextCount = i;
                int contextPos = iArr[i];
                int size = this.fErrors.size() - contextPos;
                if (size != 0) {
                    strArr = new String[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        strArr[i2] = (String) this.fErrors.elementAt(contextPos + i2);
                    }
                }
            }
            return strArr;
        }

        public void reportError(String domain, String key, Object[] arguments, short severity) throws XNIException {
            String message = this.fErrorReporter.reportError(domain, key, arguments, severity);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.addElement(key);
                this.fErrors.addElement(message);
            }
        }

        public void reportError(XMLLocator location, String domain, String key, Object[] arguments, short severity) throws XNIException {
            String message = this.fErrorReporter.reportError(location, domain, key, arguments, severity);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.addElement(key);
                this.fErrors.addElement(message);
            }
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
        if (propertyId.equals(ROOT_TYPE_DEF)) {
            if (value == null) {
                this.fRootTypeQName = null;
                this.fRootTypeDefinition = null;
            } else if (value instanceof QName) {
                this.fRootTypeQName = (QName) value;
                this.fRootTypeDefinition = null;
            } else {
                this.fRootTypeDefinition = (XSTypeDefinition) value;
                this.fRootTypeQName = null;
            }
        } else if (!propertyId.equals(ROOT_ELEMENT_DECL)) {
        } else {
            if (value == null) {
                this.fRootElementDeclQName = null;
                this.fRootElementDeclaration = null;
            } else if (value instanceof QName) {
                this.fRootElementDeclQName = (QName) value;
                this.fRootElementDeclaration = null;
            } else {
                this.fRootElementDeclaration = (XSElementDecl) value;
                this.fRootElementDeclQName = null;
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

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void setDocumentSource(XMLDocumentSource source) {
        this.fDocumentSource = source;
    }

    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        this.fValidationState.setNamespaceSupport(namespaceContext);
        this.fState4XsiType.setNamespaceSupport(namespaceContext);
        this.fState4ApplyDefault.setNamespaceSupport(namespaceContext);
        this.fLocator = locator;
        handleStartDocument(locator, encoding);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(locator, encoding, namespaceContext, augs);
        }
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(version, encoding, standalone, augs);
        }
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.doctypeDecl(rootElement, publicId, systemId, augs);
        }
    }

    public void startElement(mf.org.apache.xerces.xni.QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        Augmentations modifiedAugs = handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(element, attributes, modifiedAugs);
        }
    }

    public void emptyElement(mf.org.apache.xerces.xni.QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        Augmentations modifiedAugs = handleStartElement(element, attributes, augs);
        this.fDefaultValue = null;
        if (this.fElementDepth != -2) {
            modifiedAugs = handleEndElement(element, modifiedAugs);
        }
        if (this.fDocumentHandler == null) {
            return;
        }
        if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
            this.fDocumentHandler.emptyElement(element, attributes, modifiedAugs);
            return;
        }
        this.fDocumentHandler.startElement(element, attributes, modifiedAugs);
        this.fDocumentHandler.characters(this.fDefaultValue, null);
        this.fDocumentHandler.endElement(element, modifiedAugs);
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        text = handleCharacters(text);
        if (this.fDocumentHandler == null) {
            return;
        }
        if (!this.fNormalizeData || !this.fUnionType) {
            this.fDocumentHandler.characters(text, augs);
        } else if (augs != null) {
            this.fDocumentHandler.characters(this.fEmptyXMLStr, augs);
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        handleIgnorableWhitespace(text);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
        }
    }

    public void endElement(mf.org.apache.xerces.xni.QName element, Augmentations augs) throws XNIException {
        this.fDefaultValue = null;
        Augmentations modifiedAugs = handleEndElement(element, augs);
        if (this.fDocumentHandler == null) {
            return;
        }
        if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
            this.fDocumentHandler.endElement(element, modifiedAugs);
            return;
        }
        this.fDocumentHandler.characters(this.fDefaultValue, null);
        this.fDocumentHandler.endElement(element, modifiedAugs);
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        this.fInCDATA = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(augs);
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        this.fInCDATA = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(augs);
        }
    }

    public void endDocument(Augmentations augs) throws XNIException {
        handleEndDocument();
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument(augs);
        }
        this.fLocator = null;
    }

    public boolean characterData(String data, Augmentations augs) {
        boolean z;
        boolean z2 = false;
        if (this.fSawText || data.length() > 0) {
            z = true;
        } else {
            z = false;
        }
        this.fSawText = z;
        if (this.fNormalizeData && this.fWhiteSpace != (short) -1 && this.fWhiteSpace != (short) 0) {
            if (this.fWhiteSpace == (short) 2) {
                z2 = true;
            }
            normalizeWhitespace(data, z2);
            this.fBuffer.append(this.fNormalizedStr.ch, this.fNormalizedStr.offset, this.fNormalizedStr.length);
        } else if (this.fAppendBuffer) {
            this.fBuffer.append(data);
        }
        if (this.fCurrentType == null || this.fCurrentType.getTypeCategory() != (short) 15 || this.fCurrentType.fContentType != (short) 2) {
            return true;
        }
        int i = 0;
        while (i < data.length()) {
            if (XMLChar.isSpace(data.charAt(i))) {
                i++;
            } else {
                this.fSawCharacters = true;
                return false;
            }
        }
        return true;
    }

    public void elementDefault(String data) {
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        this.fEntityRef = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, augs);
        }
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.textDecl(version, encoding, augs);
        }
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(text, augs);
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, augs);
        }
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        this.fEntityRef = false;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }

    public XMLSchemaValidator() {
        this.fState4XsiType.setExtraChecking(false);
        this.fState4ApplyDefault.setFacetChecking(false);
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        boolean parser_settings;
        this.fIdConstraint = false;
        this.fLocationPairs.clear();
        this.fExpandedLocationPairs.clear();
        this.fValidationState.resetIDTables();
        this.fSchemaLoader.reset(componentManager);
        this.fCurrentElemDecl = null;
        this.fCurrentCM = null;
        this.fCurrCMState = null;
        this.fSkipValidationDepth = -1;
        this.fNFullValidationDepth = -1;
        this.fNNoneValidationDepth = -1;
        this.fElementDepth = -1;
        this.fSubElement = false;
        this.fSchemaDynamicValidation = false;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fMatcherStack.clear();
        this.fXSIErrorReporter.reset((XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter"));
        try {
            parser_settings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            parser_settings = true;
        }
        if (parser_settings) {
            boolean ignoreXSIType;
            int i;
            this.nodeFactory.reset(componentManager);
            SymbolTable symbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            if (symbolTable != this.fSymbolTable) {
                this.fSymbolTable = symbolTable;
            }
            try {
                this.fNamespaceGrowth = componentManager.getFeature(NAMESPACE_GROWTH);
            } catch (XMLConfigurationException e2) {
                this.fNamespaceGrowth = false;
            }
            try {
                this.fDynamicValidation = componentManager.getFeature(DYNAMIC_VALIDATION);
            } catch (XMLConfigurationException e3) {
                this.fDynamicValidation = false;
            }
            if (this.fDynamicValidation) {
                this.fDoValidation = true;
            } else {
                try {
                    this.fDoValidation = componentManager.getFeature(VALIDATION);
                } catch (XMLConfigurationException e4) {
                    this.fDoValidation = false;
                }
            }
            if (this.fDoValidation) {
                try {
                    this.fDoValidation = componentManager.getFeature(SCHEMA_VALIDATION);
                } catch (XMLConfigurationException e5) {
                }
            }
            try {
                this.fFullChecking = componentManager.getFeature(SCHEMA_FULL_CHECKING);
            } catch (XMLConfigurationException e6) {
                this.fFullChecking = false;
            }
            try {
                this.fNormalizeData = componentManager.getFeature(NORMALIZE_DATA);
            } catch (XMLConfigurationException e7) {
                this.fNormalizeData = false;
            }
            try {
                this.fSchemaElementDefault = componentManager.getFeature(SCHEMA_ELEMENT_DEFAULT);
            } catch (XMLConfigurationException e8) {
                this.fSchemaElementDefault = false;
            }
            try {
                this.fAugPSVI = componentManager.getFeature(SCHEMA_AUGMENT_PSVI);
            } catch (XMLConfigurationException e9) {
                this.fAugPSVI = true;
            }
            try {
                this.fSchemaType = (String) componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
            } catch (XMLConfigurationException e10) {
                this.fSchemaType = null;
            }
            try {
                this.fUseGrammarPoolOnly = componentManager.getFeature(USE_GRAMMAR_POOL_ONLY);
            } catch (XMLConfigurationException e11) {
                this.fUseGrammarPoolOnly = false;
            }
            this.fEntityResolver = (XMLEntityResolver) componentManager.getProperty(ENTITY_MANAGER);
            this.fValidationManager = (ValidationManager) componentManager.getProperty(VALIDATION_MANAGER);
            this.fValidationManager.addValidationState(this.fValidationState);
            this.fValidationState.setSymbolTable(this.fSymbolTable);
            try {
                Object rootType = componentManager.getProperty(ROOT_TYPE_DEF);
                if (rootType == null) {
                    this.fRootTypeQName = null;
                    this.fRootTypeDefinition = null;
                } else if (rootType instanceof QName) {
                    this.fRootTypeQName = (QName) rootType;
                    this.fRootTypeDefinition = null;
                } else {
                    this.fRootTypeDefinition = (XSTypeDefinition) rootType;
                    this.fRootTypeQName = null;
                }
            } catch (XMLConfigurationException e12) {
                this.fRootTypeQName = null;
                this.fRootTypeDefinition = null;
            }
            try {
                Object rootDecl = componentManager.getProperty(ROOT_ELEMENT_DECL);
                if (rootDecl == null) {
                    this.fRootElementDeclQName = null;
                    this.fRootElementDeclaration = null;
                } else if (rootDecl instanceof QName) {
                    this.fRootElementDeclQName = (QName) rootDecl;
                    this.fRootElementDeclaration = null;
                } else {
                    this.fRootElementDeclaration = (XSElementDecl) rootDecl;
                    this.fRootElementDeclQName = null;
                }
            } catch (XMLConfigurationException e13) {
                this.fRootElementDeclQName = null;
                this.fRootElementDeclaration = null;
            }
            try {
                ignoreXSIType = componentManager.getFeature(IGNORE_XSI_TYPE);
            } catch (XMLConfigurationException e14) {
                ignoreXSIType = false;
            }
            if (ignoreXSIType) {
                i = 0;
            } else {
                i = -1;
            }
            this.fIgnoreXSITypeDepth = i;
            try {
                this.fIDCChecking = componentManager.getFeature(IDENTITY_CONSTRAINT_CHECKING);
            } catch (XMLConfigurationException e15) {
                this.fIDCChecking = true;
            }
            try {
                this.fValidationState.setIdIdrefChecking(componentManager.getFeature(ID_IDREF_CHECKING));
            } catch (XMLConfigurationException e16) {
                this.fValidationState.setIdIdrefChecking(true);
            }
            try {
                this.fValidationState.setUnparsedEntityChecking(componentManager.getFeature(UNPARSED_ENTITY_CHECKING));
            } catch (XMLConfigurationException e17) {
                this.fValidationState.setUnparsedEntityChecking(true);
            }
            try {
                this.fExternalSchemas = (String) componentManager.getProperty(SCHEMA_LOCATION);
                this.fExternalNoNamespaceSchema = (String) componentManager.getProperty(SCHEMA_NONS_LOCATION);
            } catch (XMLConfigurationException e18) {
                this.fExternalSchemas = null;
                this.fExternalNoNamespaceSchema = null;
            }
            XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
            try {
                this.fJaxpSchemaSource = componentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource");
            } catch (XMLConfigurationException e19) {
                this.fJaxpSchemaSource = null;
            }
            try {
                this.fGrammarPool = (XMLGrammarPool) componentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
            } catch (XMLConfigurationException e20) {
                this.fGrammarPool = null;
            }
            this.fState4XsiType.setSymbolTable(symbolTable);
            this.fState4ApplyDefault.setSymbolTable(symbolTable);
            return;
        }
        this.fValidationManager.addValidationState(this.fValidationState);
        this.nodeFactory.reset();
        XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
    }

    public void startValueScopeFor(IdentityConstraint identityConstraint, int initialDepth) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, initialDepth).startValueScope();
    }

    public XPathMatcher activateField(Field field, int initialDepth) {
        XPathMatcher matcher = field.createMatcher(this.fValueStoreCache.getValueStoreFor(field.getIdentityConstraint(), initialDepth));
        this.fMatcherStack.addMatcher(matcher);
        matcher.startDocumentFragment();
        return matcher;
    }

    public void endValueScopeFor(IdentityConstraint identityConstraint, int initialDepth) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, initialDepth).endValueScope();
    }

    private void activateSelectorFor(IdentityConstraint ic) {
        Selector selector = ic.getSelector();
        XMLSchemaValidator activator = this;
        if (selector != null) {
            XPathMatcher matcher = selector.createMatcher(activator, this.fElementDepth);
            this.fMatcherStack.addMatcher(matcher);
            matcher.startDocumentFragment();
        }
    }

    public XSElementDecl getGlobalElementDecl(mf.org.apache.xerces.xni.QName element) {
        SchemaGrammar sGrammar = findSchemaGrammar((short) 5, element.uri, null, element, null);
        if (sGrammar != null) {
            return sGrammar.getGlobalElementDecl(element.localpart);
        }
        return null;
    }

    void ensureStackCapacity() {
        if (this.fElementDepth == this.fElemDeclStack.length) {
            int newSize = this.fElementDepth + 8;
            boolean[] newArrayB = new boolean[newSize];
            System.arraycopy(this.fSubElementStack, 0, newArrayB, 0, this.fElementDepth);
            this.fSubElementStack = newArrayB;
            XSElementDecl[] newArrayE = new XSElementDecl[newSize];
            System.arraycopy(this.fElemDeclStack, 0, newArrayE, 0, this.fElementDepth);
            this.fElemDeclStack = newArrayE;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fNilStack, 0, newArrayB, 0, this.fElementDepth);
            this.fNilStack = newArrayB;
            XSNotationDecl[] newArrayN = new XSNotationDecl[newSize];
            System.arraycopy(this.fNotationStack, 0, newArrayN, 0, this.fElementDepth);
            this.fNotationStack = newArrayN;
            XSTypeDefinition[] newArrayT = new XSTypeDefinition[newSize];
            System.arraycopy(this.fTypeStack, 0, newArrayT, 0, this.fElementDepth);
            this.fTypeStack = newArrayT;
            XSCMValidator[] newArrayC = new XSCMValidator[newSize];
            System.arraycopy(this.fCMStack, 0, newArrayC, 0, this.fElementDepth);
            this.fCMStack = newArrayC;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fSawTextStack, 0, newArrayB, 0, this.fElementDepth);
            this.fSawTextStack = newArrayB;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fStringContent, 0, newArrayB, 0, this.fElementDepth);
            this.fStringContent = newArrayB;
            newArrayB = new boolean[newSize];
            System.arraycopy(this.fStrictAssessStack, 0, newArrayB, 0, this.fElementDepth);
            this.fStrictAssessStack = newArrayB;
            int[][] newArrayIA = new int[newSize][];
            System.arraycopy(this.fCMStateStack, 0, newArrayIA, 0, this.fElementDepth);
            this.fCMStateStack = newArrayIA;
        }
    }

    void handleStartDocument(XMLLocator locator, String encoding) {
        if (this.fIDCChecking) {
            this.fValueStoreCache.startDocument();
        }
        if (this.fAugPSVI) {
            this.fCurrentPSVI.fGrammars = null;
            this.fCurrentPSVI.fSchemaInformation = null;
        }
    }

    void handleEndDocument() {
        if (this.fIDCChecking) {
            this.fValueStoreCache.endDocument();
        }
    }

    XMLString handleCharacters(XMLString text) {
        boolean z = false;
        if (this.fSkipValidationDepth >= 0) {
            return text;
        }
        boolean z2;
        if (this.fSawText || text.length > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.fSawText = z2;
        if (!(!this.fNormalizeData || this.fWhiteSpace == (short) -1 || this.fWhiteSpace == (short) 0)) {
            if (this.fWhiteSpace == (short) 2) {
                z = true;
            }
            normalizeWhitespace(text, z);
            text = this.fNormalizedStr;
        }
        if (this.fAppendBuffer) {
            this.fBuffer.append(text.ch, text.offset, text.length);
        }
        if (this.fCurrentType != null && this.fCurrentType.getTypeCategory() == (short) 15 && this.fCurrentType.fContentType == (short) 2) {
            for (int i = text.offset; i < text.offset + text.length; i++) {
                if (!XMLChar.isSpace(text.ch[i])) {
                    this.fSawCharacters = true;
                    break;
                }
            }
        }
        return text;
    }

    private void normalizeWhitespace(XMLString value, boolean collapse) {
        XMLString xMLString;
        boolean skipSpace = collapse;
        boolean sawNonWS = false;
        boolean leading = false;
        boolean trailing = false;
        int size = value.offset + value.length;
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < value.length + 1) {
            this.fNormalizedStr.ch = new char[(value.length + 1)];
        }
        this.fNormalizedStr.offset = 1;
        this.fNormalizedStr.length = 1;
        for (int i = value.offset; i < size; i++) {
            char c = value.ch[i];
            char[] cArr;
            XMLString xMLString2;
            int i2;
            if (XMLChar.isSpace(c)) {
                if (!skipSpace) {
                    cArr = this.fNormalizedStr.ch;
                    xMLString2 = this.fNormalizedStr;
                    i2 = xMLString2.length;
                    xMLString2.length = i2 + 1;
                    cArr[i2] = ' ';
                    skipSpace = collapse;
                }
                if (!sawNonWS) {
                    leading = true;
                }
            } else {
                cArr = this.fNormalizedStr.ch;
                xMLString2 = this.fNormalizedStr;
                i2 = xMLString2.length;
                xMLString2.length = i2 + 1;
                cArr[i2] = c;
                skipSpace = false;
                sawNonWS = true;
            }
        }
        if (skipSpace) {
            if (this.fNormalizedStr.length > 1) {
                xMLString = this.fNormalizedStr;
                xMLString.length--;
                trailing = true;
            } else if (leading && !this.fFirstChunk) {
                trailing = true;
            }
        }
        if (this.fNormalizedStr.length > 1 && !this.fFirstChunk && this.fWhiteSpace == (short) 2) {
            if (this.fTrailing) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            } else if (leading) {
                this.fNormalizedStr.offset = 0;
                this.fNormalizedStr.ch[0] = ' ';
            }
        }
        xMLString = this.fNormalizedStr;
        xMLString.length -= this.fNormalizedStr.offset;
        this.fTrailing = trailing;
        if (trailing || sawNonWS) {
            this.fFirstChunk = false;
        }
    }

    private void normalizeWhitespace(String value, boolean collapse) {
        boolean skipSpace = collapse;
        int size = value.length();
        if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < size) {
            this.fNormalizedStr.ch = new char[size];
        }
        this.fNormalizedStr.offset = 0;
        this.fNormalizedStr.length = 0;
        for (int i = 0; i < size; i++) {
            char c = value.charAt(i);
            char[] cArr;
            XMLString xMLString;
            int i2;
            if (!XMLChar.isSpace(c)) {
                cArr = this.fNormalizedStr.ch;
                xMLString = this.fNormalizedStr;
                i2 = xMLString.length;
                xMLString.length = i2 + 1;
                cArr[i2] = c;
                skipSpace = false;
            } else if (!skipSpace) {
                cArr = this.fNormalizedStr.ch;
                xMLString = this.fNormalizedStr;
                i2 = xMLString.length;
                xMLString.length = i2 + 1;
                cArr[i2] = ' ';
                skipSpace = collapse;
            }
        }
        if (skipSpace && this.fNormalizedStr.length != 0) {
            XMLString xMLString2 = this.fNormalizedStr;
            xMLString2.length--;
        }
    }

    void handleIgnorableWhitespace(XMLString text) {
        if (this.fSkipValidationDepth < 0) {
        }
    }

    Augmentations handleStartElement(mf.org.apache.xerces.xni.QName element, XMLAttributes attributes, Augmentations augs) {
        if (this.fElementDepth == -1 && this.fValidationManager.isGrammarFound() && this.fSchemaType == null) {
            this.fSchemaDynamicValidation = true;
        }
        if (!this.fUseGrammarPoolOnly) {
            storeLocations(attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_SCHEMALOCATION), attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION));
        }
        if (this.fSkipValidationDepth >= 0) {
            this.fElementDepth++;
            if (this.fAugPSVI) {
                augs = getEmptyAugs(augs);
            }
            return augs;
        }
        int count;
        XSWildcardDecl decl = null;
        if (this.fCurrentCM != null) {
            decl = this.fCurrentCM.oneTransition(element, this.fCurrCMState, this.fSubGroupHandler);
            if (this.fCurrCMState[0] == -1) {
                int[] occurenceInfo;
                int maxOccurs;
                if (this.fCurrentType.fParticle != null) {
                    Vector next = this.fCurrentCM.whatCanGoHere(this.fCurrCMState);
                    if (next.size() > 0) {
                        String expected = expectedStr(next);
                        occurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                        if (occurenceInfo != null) {
                            int minOccurs = occurenceInfo[0];
                            maxOccurs = occurenceInfo[1];
                            count = occurenceInfo[2];
                            if (count < minOccurs) {
                                if (minOccurs - count > 1) {
                                    reportSchemaError("cvc-complex-type.2.4.h", new Object[]{element.rawname, this.fCurrentCM.getTermName(occurenceInfo[3]), Integer.toString(minOccurs), Integer.toString(minOccurs - count)});
                                } else {
                                    reportSchemaError("cvc-complex-type.2.4.g", new Object[]{element.rawname, this.fCurrentCM.getTermName(occurenceInfo[3]), Integer.toString(minOccurs)});
                                }
                            } else if (count < maxOccurs || maxOccurs == -1) {
                                reportSchemaError("cvc-complex-type.2.4.a", new Object[]{element.rawname, expected});
                            } else {
                                reportSchemaError("cvc-complex-type.2.4.e", new Object[]{element.rawname, expected, Integer.toString(maxOccurs)});
                            }
                        } else {
                            reportSchemaError("cvc-complex-type.2.4.a", new Object[]{element.rawname, expected});
                        }
                    }
                }
                occurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                if (occurenceInfo != null) {
                    maxOccurs = occurenceInfo[1];
                    if (occurenceInfo[2] < maxOccurs || maxOccurs == -1) {
                        reportSchemaError("cvc-complex-type.2.4.d", new Object[]{element.rawname});
                    } else {
                        reportSchemaError("cvc-complex-type.2.4.f", new Object[]{element.rawname, Integer.toString(maxOccurs)});
                    }
                } else {
                    reportSchemaError("cvc-complex-type.2.4.d", new Object[]{element.rawname});
                }
            }
        }
        if (this.fElementDepth != -1) {
            ensureStackCapacity();
            this.fSubElementStack[this.fElementDepth] = true;
            this.fSubElement = false;
            this.fElemDeclStack[this.fElementDepth] = this.fCurrentElemDecl;
            this.fNilStack[this.fElementDepth] = this.fNil;
            this.fNotationStack[this.fElementDepth] = this.fNotation;
            this.fTypeStack[this.fElementDepth] = this.fCurrentType;
            this.fStrictAssessStack[this.fElementDepth] = this.fStrictAssess;
            this.fCMStack[this.fElementDepth] = this.fCurrentCM;
            this.fCMStateStack[this.fElementDepth] = this.fCurrCMState;
            this.fSawTextStack[this.fElementDepth] = this.fSawText;
            this.fStringContent[this.fElementDepth] = this.fSawCharacters;
        }
        this.fElementDepth++;
        this.fCurrentElemDecl = null;
        XSWildcardDecl wildcard = null;
        this.fCurrentType = null;
        this.fStrictAssess = true;
        this.fNil = false;
        this.fNotation = null;
        this.fBuffer.setLength(0);
        this.fSawText = false;
        this.fSawCharacters = false;
        if (decl != null) {
            if (decl instanceof XSElementDecl) {
                this.fCurrentElemDecl = (XSElementDecl) decl;
            } else {
                wildcard = decl;
            }
        }
        if (wildcard == null || wildcard.fProcessContents != (short) 2) {
            if (this.fElementDepth == 0) {
                if (this.fRootElementDeclaration != null) {
                    this.fCurrentElemDecl = this.fRootElementDeclaration;
                    checkElementMatchesRootElementDecl(this.fCurrentElemDecl, element);
                } else if (this.fRootElementDeclQName != null) {
                    processRootElementDeclQName(this.fRootElementDeclQName, element);
                } else if (this.fRootTypeDefinition != null) {
                    this.fCurrentType = this.fRootTypeDefinition;
                } else if (this.fRootTypeQName != null) {
                    processRootTypeQName(this.fRootTypeQName);
                }
            }
            if (this.fCurrentType == null) {
                if (this.fCurrentElemDecl == null) {
                    SchemaGrammar sGrammar = findSchemaGrammar((short) 5, element.uri, null, element, attributes);
                    if (sGrammar != null) {
                        this.fCurrentElemDecl = sGrammar.getGlobalElementDecl(element.localpart);
                    }
                }
                if (this.fCurrentElemDecl != null) {
                    this.fCurrentType = this.fCurrentElemDecl.fType;
                }
            }
            if (this.fElementDepth == this.fIgnoreXSITypeDepth && this.fCurrentElemDecl == null) {
                this.fIgnoreXSITypeDepth++;
            }
            String xsiType = null;
            if (this.fElementDepth >= this.fIgnoreXSITypeDepth) {
                xsiType = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_TYPE);
            }
            if (this.fCurrentType == null && xsiType == null) {
                if (this.fElementDepth == 0) {
                    if (!this.fDynamicValidation && !this.fSchemaDynamicValidation) {
                        this.fXSIErrorReporter.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "cvc-elt.1.a", new Object[]{element.rawname}, (short) 1);
                    } else if (this.fDocumentSource != null) {
                        this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
                        if (this.fDocumentHandler != null) {
                            this.fDocumentHandler.setDocumentSource(this.fDocumentSource);
                        }
                        this.fElementDepth = -2;
                        return augs;
                    } else {
                        this.fSkipValidationDepth = this.fElementDepth;
                        if (this.fAugPSVI) {
                            augs = getEmptyAugs(augs);
                        }
                        return augs;
                    }
                } else if (wildcard != null && wildcard.fProcessContents == (short) 1) {
                    reportSchemaError("cvc-complex-type.2.4.c", new Object[]{element.rawname});
                }
                this.fCurrentType = SchemaGrammar.fAnyType;
                this.fStrictAssess = false;
                this.fNFullValidationDepth = this.fElementDepth;
                this.fAppendBuffer = false;
                this.fXSIErrorReporter.pushContext();
            } else {
                this.fXSIErrorReporter.pushContext();
                if (xsiType != null) {
                    XSTypeDefinition oldType = this.fCurrentType;
                    this.fCurrentType = getAndCheckXsiType(element, xsiType, attributes);
                    if (this.fCurrentType == null) {
                        if (oldType == null) {
                            this.fCurrentType = SchemaGrammar.fAnyType;
                        } else {
                            this.fCurrentType = oldType;
                        }
                    }
                }
                this.fNNoneValidationDepth = this.fElementDepth;
                if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getConstraintType() == (short) 2) {
                    this.fAppendBuffer = true;
                } else if (this.fCurrentType.getTypeCategory() == (short) 16) {
                    this.fAppendBuffer = true;
                } else {
                    this.fAppendBuffer = ((XSComplexTypeDecl) this.fCurrentType).fContentType == (short) 1;
                }
            }
            if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.getAbstract()) {
                reportSchemaError("cvc-elt.2", new Object[]{element.rawname});
            }
            if (this.fElementDepth == 0) {
                this.fValidationRoot = element.rawname;
            }
            if (this.fNormalizeData) {
                this.fFirstChunk = true;
                this.fTrailing = false;
                this.fUnionType = false;
                this.fWhiteSpace = (short) -1;
            }
            if (this.fCurrentType.getTypeCategory() == (short) 15) {
                XSComplexTypeDecl ctype = (XSComplexTypeDecl) this.fCurrentType;
                if (ctype.getAbstract()) {
                    reportSchemaError("cvc-type.2", new Object[]{element.rawname});
                }
                if (this.fNormalizeData && ctype.fContentType == (short) 1) {
                    if (ctype.fXSSimpleType.getVariety() == (short) 3) {
                        this.fUnionType = true;
                    } else {
                        try {
                            this.fWhiteSpace = ctype.fXSSimpleType.getWhitespace();
                        } catch (DatatypeException e) {
                        }
                    }
                }
            } else if (this.fNormalizeData) {
                XSSimpleType dv = this.fCurrentType;
                if (dv.getVariety() == (short) 3) {
                    this.fUnionType = true;
                } else {
                    try {
                        this.fWhiteSpace = dv.getWhitespace();
                    } catch (DatatypeException e2) {
                    }
                }
            }
            this.fCurrentCM = null;
            if (this.fCurrentType.getTypeCategory() == (short) 15) {
                this.fCurrentCM = ((XSComplexTypeDecl) this.fCurrentType).getContentModel(this.fCMBuilder);
            }
            this.fCurrCMState = null;
            if (this.fCurrentCM != null) {
                this.fCurrCMState = this.fCurrentCM.startContentModel();
            }
            String xsiNil = attributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NIL);
            if (!(xsiNil == null || this.fCurrentElemDecl == null)) {
                this.fNil = getXsiNil(element, xsiNil);
            }
            XSAttributeGroupDecl attrGrp = null;
            if (this.fCurrentType.getTypeCategory() == (short) 15) {
                attrGrp = ((XSComplexTypeDecl) this.fCurrentType).getAttrGrp();
            }
            if (this.fIDCChecking) {
                this.fValueStoreCache.startElement();
                this.fMatcherStack.pushContext();
                if (this.fCurrentElemDecl != null && this.fCurrentElemDecl.fIDCPos > 0) {
                    this.fIdConstraint = true;
                    this.fValueStoreCache.initValueStoresFor(this.fCurrentElemDecl, this);
                }
            }
            processAttributes(element, attributes, attrGrp);
            if (attrGrp != null) {
                addDefaultAttributes(element, attributes, attrGrp);
            }
            count = this.fMatcherStack.getMatcherCount();
            for (int i = 0; i < count; i++) {
                this.fMatcherStack.getMatcherAt(i).startElement(element, attributes);
            }
            if (this.fAugPSVI) {
                augs = getEmptyAugs(augs);
                this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
                this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
                this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
                this.fCurrentPSVI.fNotation = this.fNotation;
                this.fCurrentPSVI.fNil = this.fNil;
            }
            return augs;
        }
        this.fSkipValidationDepth = this.fElementDepth;
        if (this.fAugPSVI) {
            augs = getEmptyAugs(augs);
        }
        return augs;
    }

    Augmentations handleEndElement(mf.org.apache.xerces.xni.QName element, Augmentations augs) {
        if (this.fSkipValidationDepth >= 0) {
            if (this.fSkipValidationDepth != this.fElementDepth || this.fSkipValidationDepth <= 0) {
                this.fElementDepth--;
            } else {
                this.fNFullValidationDepth = this.fSkipValidationDepth - 1;
                this.fSkipValidationDepth = -1;
                this.fElementDepth--;
                this.fSubElement = this.fSubElementStack[this.fElementDepth];
                this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
                this.fNil = this.fNilStack[this.fElementDepth];
                this.fNotation = this.fNotationStack[this.fElementDepth];
                this.fCurrentType = this.fTypeStack[this.fElementDepth];
                this.fCurrentCM = this.fCMStack[this.fElementDepth];
                this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
                this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
                this.fSawText = this.fSawTextStack[this.fElementDepth];
                this.fSawCharacters = this.fStringContent[this.fElementDepth];
            }
            if (this.fElementDepth == -1 && this.fFullChecking && !this.fUseGrammarPoolOnly) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            if (this.fAugPSVI) {
                augs = getEmptyAugs(augs);
            }
            return augs;
        }
        processElementContent(element);
        if (this.fIDCChecking) {
            int i;
            XPathMatcher matcher;
            Matcher selMatcher;
            IdentityConstraint id;
            int oldCount = this.fMatcherStack.getMatcherCount();
            for (i = oldCount - 1; i >= 0; i--) {
                matcher = this.fMatcherStack.getMatcherAt(i);
                if (this.fCurrentElemDecl == null) {
                    matcher.endElement(element, this.fCurrentType, false, this.fValidatedInfo.actualValue, this.fValidatedInfo.actualValueType, this.fValidatedInfo.itemValueTypes);
                } else {
                    Object obj;
                    short s;
                    ShortList shortList;
                    XSTypeDefinition xSTypeDefinition = this.fCurrentType;
                    boolean nillable = this.fCurrentElemDecl.getNillable();
                    if (this.fDefaultValue == null) {
                        obj = this.fValidatedInfo.actualValue;
                    } else {
                        obj = this.fCurrentElemDecl.fDefault.actualValue;
                    }
                    if (this.fDefaultValue == null) {
                        s = this.fValidatedInfo.actualValueType;
                    } else {
                        s = this.fCurrentElemDecl.fDefault.actualValueType;
                    }
                    if (this.fDefaultValue == null) {
                        shortList = this.fValidatedInfo.itemValueTypes;
                    } else {
                        shortList = this.fCurrentElemDecl.fDefault.itemValueTypes;
                    }
                    matcher.endElement(element, xSTypeDefinition, nillable, obj, s, shortList);
                }
            }
            if (this.fMatcherStack.size() > 0) {
                this.fMatcherStack.popContext();
            }
            int newCount = this.fMatcherStack.getMatcherCount();
            for (i = oldCount - 1; i >= newCount; i--) {
                matcher = this.fMatcherStack.getMatcherAt(i);
                if (matcher instanceof Matcher) {
                    selMatcher = (Matcher) matcher;
                    id = selMatcher.getIdentityConstraint();
                    if (!(id == null || id.getCategory() == (short) 2)) {
                        this.fValueStoreCache.transplant(id, selMatcher.getInitialDepth());
                    }
                }
            }
            for (i = oldCount - 1; i >= newCount; i--) {
                matcher = this.fMatcherStack.getMatcherAt(i);
                if (matcher instanceof Matcher) {
                    selMatcher = (Matcher) matcher;
                    id = selMatcher.getIdentityConstraint();
                    if (id != null && id.getCategory() == (short) 2) {
                        ValueStoreBase values = this.fValueStoreCache.getValueStoreFor(id, selMatcher.getInitialDepth());
                        if (values != null) {
                            values.endDocumentFragment();
                        }
                    }
                }
            }
            this.fValueStoreCache.endElement();
        }
        if (this.fElementDepth < this.fIgnoreXSITypeDepth) {
            this.fIgnoreXSITypeDepth--;
        }
        if (this.fElementDepth == 0) {
            String invIdRef = this.fValidationState.checkIDRefID();
            this.fValidationState.resetIDTables();
            if (invIdRef != null) {
                reportSchemaError("cvc-id.1", new Object[]{invIdRef});
            }
            if (this.fFullChecking && !this.fUseGrammarPoolOnly) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            SchemaGrammar[] grammars = this.fGrammarBucket.getGrammars();
            if (this.fGrammarPool != null) {
                for (SchemaGrammar immutable : grammars) {
                    immutable.setImmutable(true);
                }
                this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", grammars);
            }
            augs = endElementPSVI(true, grammars, augs);
        } else {
            augs = endElementPSVI(false, null, augs);
            this.fElementDepth--;
            this.fSubElement = this.fSubElementStack[this.fElementDepth];
            this.fCurrentElemDecl = this.fElemDeclStack[this.fElementDepth];
            this.fNil = this.fNilStack[this.fElementDepth];
            this.fNotation = this.fNotationStack[this.fElementDepth];
            this.fCurrentType = this.fTypeStack[this.fElementDepth];
            this.fCurrentCM = this.fCMStack[this.fElementDepth];
            this.fStrictAssess = this.fStrictAssessStack[this.fElementDepth];
            this.fCurrCMState = this.fCMStateStack[this.fElementDepth];
            this.fSawText = this.fSawTextStack[this.fElementDepth];
            this.fSawCharacters = this.fStringContent[this.fElementDepth];
            this.fWhiteSpace = (short) -1;
            this.fAppendBuffer = false;
            this.fUnionType = false;
        }
        return augs;
    }

    final Augmentations endElementPSVI(boolean root, SchemaGrammar[] grammars, Augmentations augs) {
        short s = (short) 2;
        if (this.fAugPSVI) {
            augs = getEmptyAugs(augs);
            this.fCurrentPSVI.fDeclaration = this.fCurrentElemDecl;
            this.fCurrentPSVI.fTypeDecl = this.fCurrentType;
            this.fCurrentPSVI.fNotation = this.fNotation;
            this.fCurrentPSVI.fValidationContext = this.fValidationRoot;
            this.fCurrentPSVI.fNil = this.fNil;
            if (this.fElementDepth > this.fNFullValidationDepth) {
                this.fCurrentPSVI.fValidationAttempted = (short) 2;
            } else if (this.fElementDepth > this.fNNoneValidationDepth) {
                this.fCurrentPSVI.fValidationAttempted = (short) 0;
            } else {
                this.fCurrentPSVI.fValidationAttempted = (short) 1;
            }
            if (this.fNFullValidationDepth == this.fElementDepth) {
                this.fNFullValidationDepth = this.fElementDepth - 1;
            }
            if (this.fNNoneValidationDepth == this.fElementDepth) {
                this.fNNoneValidationDepth = this.fElementDepth - 1;
            }
            if (this.fDefaultValue != null) {
                this.fCurrentPSVI.fSpecified = true;
            }
            this.fCurrentPSVI.fValue.copyFrom(this.fValidatedInfo);
            if (this.fStrictAssess) {
                String[] errors = this.fXSIErrorReporter.mergeContext();
                this.fCurrentPSVI.fErrors = errors;
                ElementPSVImpl elementPSVImpl = this.fCurrentPSVI;
                if (errors != null) {
                    s = (short) 1;
                }
                elementPSVImpl.fValidity = s;
            } else {
                this.fCurrentPSVI.fValidity = (short) 0;
                this.fXSIErrorReporter.popContext();
            }
            if (root) {
                this.fCurrentPSVI.fGrammars = grammars;
                this.fCurrentPSVI.fSchemaInformation = null;
            }
        }
        return augs;
    }

    Augmentations getEmptyAugs(Augmentations augs) {
        if (augs == null) {
            augs = this.fAugmentations;
            augs.removeAllItems();
        }
        augs.putItem(Constants.ELEMENT_PSVI, this.fCurrentPSVI);
        this.fCurrentPSVI.reset();
        return augs;
    }

    void storeLocations(String sLocation, String nsLocation) {
        if (sLocation != null) {
            String str;
            Hashtable hashtable = this.fLocationPairs;
            if (this.fLocator == null) {
                str = null;
            } else {
                str = this.fLocator.getExpandedSystemId();
            }
            if (!XMLSchemaLoader.tokenizeSchemaLocationStr(sLocation, hashtable, str)) {
                this.fXSIErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "SchemaLocation", new Object[]{sLocation}, (short) 0);
            }
        }
        if (nsLocation != null) {
            LocationArray la = (LocationArray) this.fLocationPairs.get(XMLSymbols.EMPTY_STRING);
            if (la == null) {
                la = new LocationArray();
                this.fLocationPairs.put(XMLSymbols.EMPTY_STRING, la);
            }
            if (this.fLocator != null) {
                try {
                    nsLocation = XMLEntityManager.expandSystemId(nsLocation, this.fLocator.getExpandedSystemId(), false);
                } catch (MalformedURIException e) {
                }
            }
            la.addLocation(nsLocation);
        }
    }

    SchemaGrammar findSchemaGrammar(short contextType, String namespace, mf.org.apache.xerces.xni.QName enclosingElement, mf.org.apache.xerces.xni.QName triggeringComponent, XMLAttributes attributes) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(namespace);
        if (grammar == null) {
            this.fXSDDescription.setNamespace(namespace);
            if (this.fGrammarPool != null) {
                grammar = (SchemaGrammar) this.fGrammarPool.retrieveGrammar(this.fXSDDescription);
                if (!(grammar == null || this.fGrammarBucket.putGrammar(grammar, true, this.fNamespaceGrowth))) {
                    this.fXSIErrorReporter.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "GrammarConflict", null, (short) 0);
                    grammar = null;
                }
            }
        }
        if (!this.fUseGrammarPoolOnly && (grammar == null || (this.fNamespaceGrowth && !hasSchemaComponent(grammar, contextType, triggeringComponent)))) {
            this.fXSDDescription.reset();
            this.fXSDDescription.fContextType = contextType;
            this.fXSDDescription.setNamespace(namespace);
            this.fXSDDescription.fEnclosedElementName = enclosingElement;
            this.fXSDDescription.fTriggeringComponent = triggeringComponent;
            this.fXSDDescription.fAttributes = attributes;
            if (this.fLocator != null) {
                this.fXSDDescription.setBaseSystemId(this.fLocator.getExpandedSystemId());
            }
            Hashtable locationPairs = this.fLocationPairs;
            if (namespace == null) {
                namespace = XMLSymbols.EMPTY_STRING;
            }
            Object locationArray = locationPairs.get(namespace);
            if (locationArray != null) {
                String[] temp = ((LocationArray) locationArray).getLocationArray();
                if (temp.length != 0) {
                    setLocationHints(this.fXSDDescription, temp, grammar);
                }
            }
            if (grammar == null || this.fXSDDescription.fLocationHints != null) {
                boolean toParseSchema = true;
                if (grammar != null) {
                    locationPairs = EMPTY_TABLE;
                }
                try {
                    XMLInputSource xis = XMLSchemaLoader.resolveDocument(this.fXSDDescription, locationPairs, this.fEntityResolver);
                    if (grammar != null && this.fNamespaceGrowth) {
                        try {
                            if (grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xis.getSystemId(), xis.getBaseSystemId(), false))) {
                                toParseSchema = false;
                            }
                        } catch (MalformedURIException e) {
                        }
                    }
                    if (toParseSchema) {
                        grammar = this.fSchemaLoader.loadSchema(this.fXSDDescription, xis, this.fLocationPairs);
                    }
                } catch (Exception ex) {
                    String[] locationHints = this.fXSDDescription.getLocationHints();
                    XMLErrorReporter xMLErrorReporter = this.fXSIErrorReporter.fErrorReporter;
                    String str = XSMessageFormatter.SCHEMA_DOMAIN;
                    String str2 = "schema_reference.4";
                    Object[] objArr = new Object[1];
                    objArr[0] = locationHints != null ? locationHints[0] : XMLSymbols.EMPTY_STRING;
                    xMLErrorReporter.reportError(str, str2, objArr, (short) 0, ex);
                }
            }
        }
        return grammar;
    }

    private boolean hasSchemaComponent(SchemaGrammar grammar, short contextType, mf.org.apache.xerces.xni.QName triggeringComponent) {
        if (!(grammar == null || triggeringComponent == null)) {
            String localName = triggeringComponent.localpart;
            if (localName != null && localName.length() > 0) {
                switch (contextType) {
                    case (short) 5:
                        if (grammar.getElementDeclaration(localName) == null) {
                            return false;
                        }
                        return true;
                    case (short) 6:
                        if (grammar.getAttributeDeclaration(localName) == null) {
                            return false;
                        }
                        return true;
                    case (short) 7:
                        if (grammar.getTypeDefinition(localName) == null) {
                            return false;
                        }
                        return true;
                }
            }
        }
        return false;
    }

    private void setLocationHints(XSDDescription desc, String[] locations, SchemaGrammar grammar) {
        int length = locations.length;
        if (grammar == null) {
            this.fXSDDescription.fLocationHints = new String[length];
            System.arraycopy(locations, 0, this.fXSDDescription.fLocationHints, 0, length);
            return;
        }
        setLocationHints(desc, locations, grammar.getDocumentLocations());
    }

    private void setLocationHints(XSDDescription desc, String[] locations, StringList docLocations) {
        int length = locations.length;
        String[] hints = new String[length];
        int i = 0;
        int counter = 0;
        while (i < length) {
            int counter2;
            if (docLocations.contains(locations[i])) {
                counter2 = counter;
            } else {
                counter2 = counter + 1;
                hints[counter] = locations[i];
            }
            i++;
            counter = counter2;
        }
        if (counter <= 0) {
            return;
        }
        if (counter == length) {
            this.fXSDDescription.fLocationHints = hints;
            return;
        }
        this.fXSDDescription.fLocationHints = new String[counter];
        System.arraycopy(hints, 0, this.fXSDDescription.fLocationHints, 0, counter);
    }

    XSTypeDefinition getAndCheckXsiType(mf.org.apache.xerces.xni.QName element, String xsiType, XMLAttributes attributes) {
        try {
            mf.org.apache.xerces.xni.QName typeName = (mf.org.apache.xerces.xni.QName) this.fQNameDV.validate(xsiType, this.fValidationState, null);
            XSTypeDefinition type = null;
            if (typeName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
                type = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(typeName.localpart);
            }
            if (type == null) {
                SchemaGrammar grammar = findSchemaGrammar((short) 7, typeName.uri, element, typeName, attributes);
                if (grammar != null) {
                    type = grammar.getGlobalTypeDecl(typeName.localpart);
                }
            }
            if (type == null) {
                reportSchemaError("cvc-elt.4.2", new Object[]{element.rawname, xsiType});
                return null;
            } else if (this.fCurrentType == null) {
                return type;
            } else {
                short block = (short) 0;
                if (this.fCurrentElemDecl != null) {
                    block = this.fCurrentElemDecl.fBlock;
                }
                if (this.fCurrentType.getTypeCategory() == (short) 15) {
                    block = (short) (((XSComplexTypeDecl) this.fCurrentType).fBlock | block);
                }
                if (XSConstraints.checkTypeDerivationOk(type, this.fCurrentType, block)) {
                    return type;
                }
                reportSchemaError("cvc-elt.4.3", new Object[]{element.rawname, xsiType, this.fCurrentType.getName()});
                return type;
            }
        } catch (InvalidDatatypeValueException e) {
            reportSchemaError(e.getKey(), e.getArgs());
            reportSchemaError("cvc-elt.4.1", new Object[]{element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_TYPE, xsiType});
            return null;
        }
    }

    boolean getXsiNil(mf.org.apache.xerces.xni.QName element, String xsiNil) {
        if (this.fCurrentElemDecl == null || this.fCurrentElemDecl.getNillable()) {
            String value = XMLChar.trim(xsiNil);
            if (value.equals(SchemaSymbols.ATTVAL_TRUE) || value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                if (this.fCurrentElemDecl == null || this.fCurrentElemDecl.getConstraintType() != (short) 2) {
                    return true;
                }
                reportSchemaError("cvc-elt.3.2.2", new Object[]{element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
                return true;
            }
        }
        reportSchemaError("cvc-elt.3.1", new Object[]{element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void processAttributes(mf.org.apache.xerces.xni.QName r29, mf.org.apache.xerces.xni.XMLAttributes r30, mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl r31) {
        /*
        r28 = this;
        r27 = 0;
        r16 = r30.getLength();
        r19 = 0;
        r8 = 0;
        r0 = r28;
        r2 = r0.fCurrentType;
        if (r2 == 0) goto L_0x005f;
    L_0x000f:
        r0 = r28;
        r2 = r0.fCurrentType;
        r2 = r2.getTypeCategory();
        r3 = 16;
        if (r2 == r3) goto L_0x005f;
    L_0x001b:
        r24 = 0;
    L_0x001d:
        r17 = 0;
        r26 = 0;
        r18 = 0;
        if (r24 != 0) goto L_0x0033;
    L_0x0025:
        r17 = r31.getAttributeUses();
        r26 = r17.getLength();
        r0 = r31;
        r0 = r0.fAttributeWC;
        r18 = r0;
    L_0x0033:
        r5 = 0;
    L_0x0034:
        r0 = r16;
        if (r5 < r0) goto L_0x0062;
    L_0x0038:
        if (r24 != 0) goto L_0x005e;
    L_0x003a:
        r0 = r31;
        r2 = r0.fIDAttrName;
        if (r2 == 0) goto L_0x005e;
    L_0x0040:
        if (r27 == 0) goto L_0x005e;
    L_0x0042:
        r2 = "cvc-complex-type.5.2";
        r3 = 3;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r29;
        r7 = r0.rawname;
        r3[r4] = r7;
        r4 = 1;
        r3[r4] = r27;
        r4 = 2;
        r0 = r31;
        r7 = r0.fIDAttrName;
        r3[r4] = r7;
        r0 = r28;
        r0.reportSchemaError(r2, r3);
    L_0x005e:
        return;
    L_0x005f:
        r24 = 1;
        goto L_0x001d;
    L_0x0062:
        r0 = r28;
        r2 = r0.fTempQName;
        r0 = r30;
        r0.getName(r5, r2);
        r0 = r28;
        r2 = r0.fAugPSVI;
        if (r2 != 0) goto L_0x0077;
    L_0x0071:
        r0 = r28;
        r2 = r0.fIdConstraint;
        if (r2 == 0) goto L_0x0093;
    L_0x0077:
        r0 = r30;
        r19 = r0.getAugmentations(r5);
        r2 = "ATTRIBUTE_PSVI";
        r0 = r19;
        r8 = r0.getItem(r2);
        r8 = (mf.org.apache.xerces.impl.xs.AttributePSVImpl) r8;
        if (r8 == 0) goto L_0x00ba;
    L_0x008a:
        r8.reset();
    L_0x008d:
        r0 = r28;
        r2 = r0.fValidationRoot;
        r8.fValidationContext = r2;
    L_0x0093:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.uri;
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.URI_XSI;
        if (r2 != r3) goto L_0x00ef;
    L_0x009d:
        r6 = 0;
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.localpart;
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.XSI_TYPE;
        if (r2 != r3) goto L_0x00c8;
    L_0x00a8:
        r6 = XSI_TYPE;
    L_0x00aa:
        if (r6 == 0) goto L_0x00ef;
    L_0x00ac:
        r7 = 0;
        r2 = r28;
        r3 = r29;
        r4 = r30;
        r2.processOneAttribute(r3, r4, r5, r6, r7, r8);
    L_0x00b6:
        r5 = r5 + 1;
        goto L_0x0034;
    L_0x00ba:
        r8 = new mf.org.apache.xerces.impl.xs.AttributePSVImpl;
        r8.<init>();
        r2 = "ATTRIBUTE_PSVI";
        r0 = r19;
        r0.putItem(r2, r8);
        goto L_0x008d;
    L_0x00c8:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.localpart;
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.XSI_NIL;
        if (r2 != r3) goto L_0x00d5;
    L_0x00d2:
        r6 = XSI_NIL;
        goto L_0x00aa;
    L_0x00d5:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.localpart;
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.XSI_SCHEMALOCATION;
        if (r2 != r3) goto L_0x00e2;
    L_0x00df:
        r6 = XSI_SCHEMALOCATION;
        goto L_0x00aa;
    L_0x00e2:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.localpart;
        r3 = mf.org.apache.xerces.impl.xs.SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION;
        if (r2 != r3) goto L_0x00aa;
    L_0x00ec:
        r6 = XSI_NONAMESPACESCHEMALOCATION;
        goto L_0x00aa;
    L_0x00ef:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.rawname;
        r3 = mf.org.apache.xerces.util.XMLSymbols.PREFIX_XMLNS;
        if (r2 == r3) goto L_0x00b6;
    L_0x00f9:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.rawname;
        r3 = "xmlns:";
        r2 = r2.startsWith(r3);
        if (r2 != 0) goto L_0x00b6;
    L_0x0108:
        if (r24 == 0) goto L_0x0126;
    L_0x010a:
        r2 = "cvc-type.3.1.1";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r29;
        r7 = r0.rawname;
        r3[r4] = r7;
        r4 = 1;
        r0 = r28;
        r7 = r0.fTempQName;
        r7 = r7.rawname;
        r3[r4] = r7;
        r0 = r28;
        r0.reportSchemaError(r2, r3);
        goto L_0x00b6;
    L_0x0126:
        r21 = 0;
        r23 = 0;
    L_0x012a:
        r0 = r23;
        r1 = r26;
        if (r0 < r1) goto L_0x0167;
    L_0x0130:
        if (r21 != 0) goto L_0x0193;
    L_0x0132:
        if (r18 == 0) goto L_0x0142;
    L_0x0134:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.uri;
        r0 = r18;
        r2 = r0.allowNamespace(r2);
        if (r2 != 0) goto L_0x0193;
    L_0x0142:
        r2 = "cvc-complex-type.3.2.2";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r29;
        r7 = r0.rawname;
        r3[r4] = r7;
        r4 = 1;
        r0 = r28;
        r7 = r0.fTempQName;
        r7 = r7.rawname;
        r3[r4] = r7;
        r0 = r28;
        r0.reportSchemaError(r2, r3);
        r0 = r28;
        r2 = r0.fElementDepth;
        r0 = r28;
        r0.fNFullValidationDepth = r2;
        goto L_0x00b6;
    L_0x0167:
        r0 = r17;
        r1 = r23;
        r25 = r0.item(r1);
        r25 = (mf.org.apache.xerces.impl.xs.XSAttributeUseImpl) r25;
        r0 = r25;
        r2 = r0.fAttrDecl;
        r2 = r2.fName;
        r0 = r28;
        r3 = r0.fTempQName;
        r3 = r3.localpart;
        if (r2 != r3) goto L_0x0190;
    L_0x017f:
        r0 = r25;
        r2 = r0.fAttrDecl;
        r2 = r2.fTargetNamespace;
        r0 = r28;
        r3 = r0.fTempQName;
        r3 = r3.uri;
        if (r2 != r3) goto L_0x0190;
    L_0x018d:
        r21 = r25;
        goto L_0x0130;
    L_0x0190:
        r23 = r23 + 1;
        goto L_0x012a;
    L_0x0193:
        r20 = 0;
        if (r21 == 0) goto L_0x01aa;
    L_0x0197:
        r0 = r21;
        r13 = r0.fAttrDecl;
    L_0x019b:
        r9 = r28;
        r10 = r29;
        r11 = r30;
        r12 = r5;
        r14 = r21;
        r15 = r8;
        r9.processOneAttribute(r10, r11, r12, r13, r14, r15);
        goto L_0x00b6;
    L_0x01aa:
        r0 = r18;
        r2 = r0.fProcessContents;
        r3 = 2;
        if (r2 == r3) goto L_0x00b6;
    L_0x01b1:
        r10 = 6;
        r0 = r28;
        r2 = r0.fTempQName;
        r11 = r2.uri;
        r0 = r28;
        r13 = r0.fTempQName;
        r9 = r28;
        r12 = r29;
        r14 = r30;
        r22 = r9.findSchemaGrammar(r10, r11, r12, r13, r14);
        if (r22 == 0) goto L_0x0230;
    L_0x01c8:
        r0 = r28;
        r2 = r0.fTempQName;
        r2 = r2.localpart;
        r0 = r22;
        r13 = r0.getGlobalAttributeDecl(r2);
    L_0x01d4:
        if (r13 != 0) goto L_0x01fa;
    L_0x01d6:
        r0 = r18;
        r2 = r0.fProcessContents;
        r3 = 1;
        if (r2 != r3) goto L_0x00b6;
    L_0x01dd:
        r2 = "cvc-complex-type.3.2.2";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r29;
        r7 = r0.rawname;
        r3[r4] = r7;
        r4 = 1;
        r0 = r28;
        r7 = r0.fTempQName;
        r7 = r7.rawname;
        r3[r4] = r7;
        r0 = r28;
        r0.reportSchemaError(r2, r3);
        goto L_0x00b6;
    L_0x01fa:
        r2 = r13.fType;
        r2 = r2.getTypeCategory();
        r3 = 16;
        if (r2 != r3) goto L_0x019b;
    L_0x0204:
        r2 = r13.fType;
        r2 = r2.isIDType();
        if (r2 == 0) goto L_0x019b;
    L_0x020c:
        if (r27 == 0) goto L_0x022a;
    L_0x020e:
        r2 = "cvc-complex-type.5.1";
        r3 = 3;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r0 = r29;
        r7 = r0.rawname;
        r3[r4] = r7;
        r4 = 1;
        r7 = r13.fName;
        r3[r4] = r7;
        r4 = 2;
        r3[r4] = r27;
        r0 = r28;
        r0.reportSchemaError(r2, r3);
        goto L_0x019b;
    L_0x022a:
        r0 = r13.fName;
        r27 = r0;
        goto L_0x019b;
    L_0x0230:
        r13 = r20;
        goto L_0x01d4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.XMLSchemaValidator.processAttributes(mf.org.apache.xerces.xni.QName, mf.org.apache.xerces.xni.XMLAttributes, mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl):void");
    }

    void processOneAttribute(mf.org.apache.xerces.xni.QName element, XMLAttributes attributes, int index, XSAttributeDecl currDecl, XSAttributeUseImpl currUse, AttributePSVImpl attrPSVI) {
        String attrValue = attributes.getValue(index);
        this.fXSIErrorReporter.pushContext();
        XSSimpleType attDV = currDecl.fType;
        Object actualValue = null;
        try {
            actualValue = attDV.validate(attrValue, this.fValidationState, this.fValidatedInfo);
            if (this.fNormalizeData) {
                attributes.setValue(index, this.fValidatedInfo.normalizedValue);
            }
            if (attDV.getVariety() == (short) 1 && attDV.getPrimitiveKind() == (short) 20) {
                mf.org.apache.xerces.xni.QName qName = (mf.org.apache.xerces.xni.QName) actualValue;
                SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
                if (grammar != null) {
                    this.fNotation = grammar.getGlobalNotationDecl(qName.localpart);
                }
            }
        } catch (InvalidDatatypeValueException idve) {
            reportSchemaError(idve.getKey(), idve.getArgs());
            String str = "cvc-attribute.3";
            Object[] objArr = new Object[4];
            objArr[0] = element.rawname;
            objArr[1] = this.fTempQName.rawname;
            objArr[2] = attrValue;
            objArr[3] = attDV instanceof XSSimpleTypeDecl ? ((XSSimpleTypeDecl) attDV).getTypeName() : attDV.getName();
            reportSchemaError(str, objArr);
        }
        if (!(actualValue == null || currDecl.getConstraintType() != (short) 2 || (ValidatedInfo.isComparable(this.fValidatedInfo, currDecl.fDefault) && actualValue.equals(currDecl.fDefault.actualValue)))) {
            reportSchemaError("cvc-attribute.4", new Object[]{element.rawname, this.fTempQName.rawname, attrValue, currDecl.fDefault.stringValue()});
        }
        if (!(actualValue == null || currUse == null || currUse.fConstraintType != (short) 2 || (ValidatedInfo.isComparable(this.fValidatedInfo, currUse.fDefault) && actualValue.equals(currUse.fDefault.actualValue)))) {
            reportSchemaError("cvc-complex-type.3.1", new Object[]{element.rawname, this.fTempQName.rawname, attrValue, currUse.fDefault.stringValue()});
        }
        if (this.fIdConstraint) {
            attrPSVI.fValue.copyFrom(this.fValidatedInfo);
        }
        if (this.fAugPSVI) {
            attrPSVI.fDeclaration = currDecl;
            attrPSVI.fTypeDecl = attDV;
            attrPSVI.fValue.copyFrom(this.fValidatedInfo);
            attrPSVI.fValidationAttempted = (short) 2;
            this.fNNoneValidationDepth = this.fElementDepth;
            String[] errors = this.fXSIErrorReporter.mergeContext();
            attrPSVI.fErrors = errors;
            attrPSVI.fValidity = errors == null ? (short) 2 : (short) 1;
        }
    }

    void addDefaultAttributes(mf.org.apache.xerces.xni.QName element, XMLAttributes attributes, XSAttributeGroupDecl attrGrp) {
        XSObjectList attrUses = attrGrp.getAttributeUses();
        int useCount = attrUses.getLength();
        for (int i = 0; i < useCount; i++) {
            XSAttributeUseImpl currUse = (XSAttributeUseImpl) attrUses.item(i);
            XSAttributeDecl currDecl = currUse.fAttrDecl;
            short constType = currUse.fConstraintType;
            ValidatedInfo defaultValue = currUse.fDefault;
            if (constType == (short) 0) {
                constType = currDecl.getConstraintType();
                defaultValue = currDecl.fDefault;
            }
            boolean isSpecified = attributes.getValue(currDecl.fTargetNamespace, currDecl.fName) != null;
            if (currUse.fUse == (short) 1 && !isSpecified) {
                reportSchemaError("cvc-complex-type.4", new Object[]{element.rawname, currDecl.fName});
            }
            if (!(isSpecified || constType == (short) 0)) {
                int attrIndex;
                mf.org.apache.xerces.xni.QName attName = new mf.org.apache.xerces.xni.QName(null, currDecl.fName, currDecl.fName, currDecl.fTargetNamespace);
                String normalized = defaultValue != null ? defaultValue.stringValue() : "";
                if (attributes instanceof XMLAttributesImpl) {
                    XMLAttributesImpl attrs = (XMLAttributesImpl) attributes;
                    attrIndex = attrs.getLength();
                    attrs.addAttributeNS(attName, "CDATA", normalized);
                } else {
                    attrIndex = attributes.addAttribute(attName, "CDATA", normalized);
                }
                if (this.fAugPSVI) {
                    Augmentations augs = attributes.getAugmentations(attrIndex);
                    AttributePSVImpl attrPSVI = new AttributePSVImpl();
                    augs.putItem(Constants.ATTRIBUTE_PSVI, attrPSVI);
                    attrPSVI.fDeclaration = currDecl;
                    attrPSVI.fTypeDecl = currDecl.fType;
                    attrPSVI.fValue.copyFrom(defaultValue);
                    attrPSVI.fValidationContext = this.fValidationRoot;
                    attrPSVI.fValidity = (short) 2;
                    attrPSVI.fValidationAttempted = (short) 2;
                    attrPSVI.fSpecified = true;
                }
            }
        }
    }

    void processElementContent(mf.org.apache.xerces.xni.QName element) {
        int bufLen;
        String content;
        if (!(this.fCurrentElemDecl == null || this.fCurrentElemDecl.fDefault == null || this.fSawText || this.fSubElement || this.fNil)) {
            String strv = this.fCurrentElemDecl.fDefault.stringValue();
            bufLen = strv.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < bufLen) {
                this.fNormalizedStr.ch = new char[bufLen];
            }
            strv.getChars(0, bufLen, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = bufLen;
            this.fDefaultValue = this.fNormalizedStr;
        }
        this.fValidatedInfo.normalizedValue = null;
        if (this.fNil && (this.fSubElement || this.fSawText)) {
            reportSchemaError("cvc-elt.3.2.1", new Object[]{element.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
        }
        this.fValidatedInfo.reset();
        if (this.fCurrentElemDecl == null || this.fCurrentElemDecl.getConstraintType() == (short) 0 || this.fSubElement || this.fSawText || this.fNil) {
            Object actualValue = elementLocallyValidType(element, this.fBuffer);
            if (!(this.fCurrentElemDecl == null || this.fCurrentElemDecl.getConstraintType() != (short) 2 || this.fNil)) {
                content = this.fBuffer.toString();
                if (this.fSubElement) {
                    reportSchemaError("cvc-elt.5.2.2.1", new Object[]{element.rawname});
                }
                if (this.fCurrentType.getTypeCategory() == (short) 15) {
                    XSComplexTypeDecl ctype = this.fCurrentType;
                    if (ctype.fContentType == (short) 3) {
                        if (!this.fCurrentElemDecl.fDefault.normalizedValue.equals(content)) {
                            reportSchemaError("cvc-elt.5.2.2.2.1", new Object[]{element.rawname, content, this.fCurrentElemDecl.fDefault.normalizedValue});
                        }
                    } else if (!(ctype.fContentType != (short) 1 || actualValue == null || (ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) && actualValue.equals(this.fCurrentElemDecl.fDefault.actualValue)))) {
                        reportSchemaError("cvc-elt.5.2.2.2.2", new Object[]{element.rawname, content, this.fCurrentElemDecl.fDefault.stringValue()});
                    }
                } else if (!(this.fCurrentType.getTypeCategory() != (short) 16 || actualValue == null || (ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) && actualValue.equals(this.fCurrentElemDecl.fDefault.actualValue)))) {
                    reportSchemaError("cvc-elt.5.2.2.2.2", new Object[]{element.rawname, content, this.fCurrentElemDecl.fDefault.stringValue()});
                }
            }
        } else {
            if (this.fCurrentType != this.fCurrentElemDecl.fType && XSConstraints.ElementDefaultValidImmediate(this.fCurrentType, this.fCurrentElemDecl.fDefault.stringValue(), this.fState4XsiType, null) == null) {
                reportSchemaError("cvc-elt.5.1.1", new Object[]{element.rawname, this.fCurrentType.getName(), this.fCurrentElemDecl.fDefault.stringValue()});
            }
            elementLocallyValidType(element, this.fCurrentElemDecl.fDefault.stringValue());
        }
        if (this.fDefaultValue == null && this.fNormalizeData && this.fDocumentHandler != null && this.fUnionType) {
            content = this.fValidatedInfo.normalizedValue;
            if (content == null) {
                content = this.fBuffer.toString();
            }
            bufLen = content.length();
            if (this.fNormalizedStr.ch == null || this.fNormalizedStr.ch.length < bufLen) {
                this.fNormalizedStr.ch = new char[bufLen];
            }
            content.getChars(0, bufLen, this.fNormalizedStr.ch, 0);
            this.fNormalizedStr.offset = 0;
            this.fNormalizedStr.length = bufLen;
            this.fDocumentHandler.characters(this.fNormalizedStr, null);
        }
    }

    Object elementLocallyValidType(mf.org.apache.xerces.xni.QName element, Object textContent) {
        if (this.fCurrentType == null) {
            return null;
        }
        if (this.fCurrentType.getTypeCategory() != (short) 16) {
            return elementLocallyValidComplexType(element, textContent);
        }
        if (this.fSubElement) {
            reportSchemaError("cvc-type.3.1.2", new Object[]{element.rawname});
        }
        if (this.fNil) {
            return null;
        }
        XSSimpleType dv = this.fCurrentType;
        try {
            if (!this.fNormalizeData || this.fUnionType) {
                this.fValidationState.setNormalizationRequired(true);
            }
            return dv.validate(textContent, this.fValidationState, this.fValidatedInfo);
        } catch (InvalidDatatypeValueException e) {
            reportSchemaError(e.getKey(), e.getArgs());
            reportSchemaError("cvc-type.3.1.3", new Object[]{element.rawname, textContent});
            return null;
        }
    }

    Object elementLocallyValidComplexType(mf.org.apache.xerces.xni.QName element, Object textContent) {
        Object obj = null;
        XSComplexTypeDecl ctype = this.fCurrentType;
        if (!this.fNil) {
            if (ctype.fContentType == (short) 0 && (this.fSubElement || this.fSawText)) {
                reportSchemaError("cvc-complex-type.2.1", new Object[]{element.rawname});
            } else if (ctype.fContentType == (short) 1) {
                if (this.fSubElement) {
                    reportSchemaError("cvc-complex-type.2.2", new Object[]{element.rawname});
                }
                XSSimpleType dv = ctype.fXSSimpleType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    obj = dv.validate(textContent, this.fValidationState, this.fValidatedInfo);
                } catch (InvalidDatatypeValueException e) {
                    reportSchemaError(e.getKey(), e.getArgs());
                    reportSchemaError("cvc-complex-type.2.2", new Object[]{element.rawname});
                }
            } else if (ctype.fContentType == (short) 2 && this.fSawCharacters) {
                reportSchemaError("cvc-complex-type.2.3", new Object[]{element.rawname});
            }
            if ((ctype.fContentType == (short) 2 || ctype.fContentType == (short) 3) && this.fCurrCMState[0] >= 0 && !this.fCurrentCM.endContentModel(this.fCurrCMState)) {
                String expected = expectedStr(this.fCurrentCM.whatCanGoHere(this.fCurrCMState));
                int[] occurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                if (occurenceInfo != null) {
                    int minOccurs = occurenceInfo[0];
                    int count = occurenceInfo[2];
                    if (count < minOccurs) {
                        if (minOccurs - count > 1) {
                            reportSchemaError("cvc-complex-type.2.4.j", new Object[]{element.rawname, this.fCurrentCM.getTermName(occurenceInfo[3]), Integer.toString(minOccurs), Integer.toString(minOccurs - count)});
                        } else {
                            reportSchemaError("cvc-complex-type.2.4.i", new Object[]{element.rawname, this.fCurrentCM.getTermName(occurenceInfo[3]), Integer.toString(minOccurs)});
                        }
                    } else {
                        reportSchemaError("cvc-complex-type.2.4.b", new Object[]{element.rawname, expected});
                    }
                } else {
                    reportSchemaError("cvc-complex-type.2.4.b", new Object[]{element.rawname, expected});
                }
            }
        }
        return obj;
    }

    void processRootTypeQName(QName rootTypeQName) {
        String rootTypeNamespace = rootTypeQName.getNamespaceURI();
        if (rootTypeNamespace != null && rootTypeNamespace.equals("")) {
            rootTypeNamespace = null;
        }
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(rootTypeNamespace)) {
            this.fCurrentType = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(rootTypeQName.getLocalPart());
        } else {
            SchemaGrammar grammarForRootType = findSchemaGrammar((short) 5, rootTypeNamespace, null, null, null);
            if (grammarForRootType != null) {
                this.fCurrentType = grammarForRootType.getGlobalTypeDecl(rootTypeQName.getLocalPart());
            }
        }
        if (this.fCurrentType == null) {
            String typeName;
            if (rootTypeQName.getPrefix().equals("")) {
                typeName = rootTypeQName.getLocalPart();
            } else {
                typeName = rootTypeQName.getPrefix() + ":" + rootTypeQName.getLocalPart();
            }
            reportSchemaError("cvc-type.1", new Object[]{typeName});
        }
    }

    void processRootElementDeclQName(QName rootElementDeclQName, mf.org.apache.xerces.xni.QName element) {
        String rootElementDeclNamespace = rootElementDeclQName.getNamespaceURI();
        if (rootElementDeclNamespace != null && rootElementDeclNamespace.equals("")) {
            rootElementDeclNamespace = null;
        }
        SchemaGrammar grammarForRootElement = findSchemaGrammar((short) 5, rootElementDeclNamespace, null, null, null);
        if (grammarForRootElement != null) {
            this.fCurrentElemDecl = grammarForRootElement.getGlobalElementDecl(rootElementDeclQName.getLocalPart());
        }
        if (this.fCurrentElemDecl == null) {
            String declName;
            if (rootElementDeclQName.getPrefix().equals("")) {
                declName = rootElementDeclQName.getLocalPart();
            } else {
                declName = rootElementDeclQName.getPrefix() + ":" + rootElementDeclQName.getLocalPart();
            }
            reportSchemaError("cvc-elt.1.a", new Object[]{declName});
            return;
        }
        checkElementMatchesRootElementDecl(this.fCurrentElemDecl, element);
    }

    void checkElementMatchesRootElementDecl(XSElementDecl rootElementDecl, mf.org.apache.xerces.xni.QName element) {
        if (element.localpart != rootElementDecl.fName || element.uri != rootElementDecl.fTargetNamespace) {
            reportSchemaError("cvc-elt.1.b", new Object[]{element.rawname, rootElementDecl.fName});
        }
    }

    void reportSchemaError(String key, Object[] arguments) {
        if (this.fDoValidation) {
            this.fXSIErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, key, arguments, (short) 1);
        }
    }

    private String expectedStr(Vector expected) {
        StringBuffer ret = new StringBuffer("{");
        int size = expected.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                ret.append(", ");
            }
            ret.append(expected.elementAt(i).toString());
        }
        ret.append('}');
        return ret.toString();
    }
}
