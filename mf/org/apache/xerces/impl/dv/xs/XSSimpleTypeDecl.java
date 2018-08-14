package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigInteger;
import java.util.AbstractList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import mf.org.apache.xerces.impl.dv.DatatypeException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xpath.regex.RegularExpression;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.util.ObjectListImpl;
import mf.org.apache.xerces.impl.xs.util.ShortListImpl;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSFacet;
import mf.org.apache.xerces.xs.XSMultiValueFacet;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.datatypes.ObjectList;
import mf.org.w3c.dom.TypeInfo;

public class XSSimpleTypeDecl implements XSSimpleType, TypeInfo {
    public static final short ANYATOMICTYPE_DT = (short) 49;
    static final String ANY_TYPE = "anyType";
    public static final short DAYTIMEDURATION_DT = (short) 47;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_LIST = 8;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_UNION = 4;
    protected static final short DV_ANYATOMICTYPE = (short) 29;
    protected static final short DV_ANYSIMPLETYPE = (short) 0;
    protected static final short DV_ANYURI = (short) 17;
    protected static final short DV_BASE64BINARY = (short) 16;
    protected static final short DV_BOOLEAN = (short) 2;
    protected static final short DV_DATE = (short) 9;
    protected static final short DV_DATETIME = (short) 7;
    protected static final short DV_DAYTIMEDURATION = (short) 28;
    protected static final short DV_DECIMAL = (short) 3;
    protected static final short DV_DOUBLE = (short) 5;
    protected static final short DV_DURATION = (short) 6;
    protected static final short DV_ENTITY = (short) 23;
    protected static final short DV_FLOAT = (short) 4;
    protected static final short DV_GDAY = (short) 13;
    protected static final short DV_GMONTH = (short) 14;
    protected static final short DV_GMONTHDAY = (short) 12;
    protected static final short DV_GYEAR = (short) 11;
    protected static final short DV_GYEARMONTH = (short) 10;
    protected static final short DV_HEXBINARY = (short) 15;
    protected static final short DV_ID = (short) 21;
    protected static final short DV_IDREF = (short) 22;
    protected static final short DV_INTEGER = (short) 24;
    protected static final short DV_LIST = (short) 25;
    protected static final short DV_NOTATION = (short) 20;
    protected static final short DV_PRECISIONDECIMAL = (short) 19;
    protected static final short DV_QNAME = (short) 18;
    protected static final short DV_STRING = (short) 1;
    protected static final short DV_TIME = (short) 8;
    protected static final short DV_UNION = (short) 26;
    protected static final short DV_YEARMONTHDURATION = (short) 27;
    static final short NORMALIZE_FULL = (short) 2;
    static final short NORMALIZE_NONE = (short) 0;
    static final short NORMALIZE_TRIM = (short) 1;
    public static final short PRECISIONDECIMAL_DT = (short) 48;
    static final short SPECIAL_PATTERN_NAME = (short) 2;
    static final short SPECIAL_PATTERN_NCNAME = (short) 3;
    static final short SPECIAL_PATTERN_NMTOKEN = (short) 1;
    static final short SPECIAL_PATTERN_NONE = (short) 0;
    static final String[] SPECIAL_PATTERN_STRING = new String[]{"NONE", SchemaSymbols.ATTVAL_NMTOKEN, SchemaSymbols.ATTVAL_NAME, SchemaSymbols.ATTVAL_NCNAME};
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String[] WS_FACET_STRING = new String[]{SchemaSymbols.ATTVAL_PRESERVE, SchemaSymbols.ATTVAL_REPLACE, SchemaSymbols.ATTVAL_COLLAPSE};
    public static final short YEARMONTHDURATION_DT = (short) 46;
    static final XSSimpleTypeDecl fAnyAtomicType = new XSSimpleTypeDecl(fAnySimpleType, "anyAtomicType", (short) 29, (short) 0, false, true, false, true, (short) 49);
    static final XSSimpleTypeDecl fAnySimpleType = new XSSimpleTypeDecl(null, SchemaSymbols.ATTVAL_ANYSIMPLETYPE, (short) 0, (short) 0, false, true, false, true, (short) 1);
    static final short[] fDVNormalizeType;
    static final ValidationContext fDummyContext = new C46382();
    static final ValidationContext fEmptyContext = new C46371();
    private static final TypeValidator[] gDVs = new TypeValidator[]{new AnySimpleDV(), new StringDV(), new BooleanDV(), new DecimalDV(), new FloatDV(), new DoubleDV(), new DurationDV(), new DateTimeDV(), new TimeDV(), new DateDV(), new YearMonthDV(), new YearDV(), new MonthDayDV(), new DayDV(), new MonthDV(), new HexBinaryDV(), new Base64BinaryDV(), new AnyURIDV(), new QNameDV(), new PrecisionDecimalDV(), new QNameDV(), new IDDV(), new IDREFDV(), new EntityDV(), new IntegerDV(), new ListDV(), new UnionDV(), new YearMonthDurationDV(), new DayTimeDurationDV(), new AnyAtomicDV()};
    public XSObjectList enumerationAnnotations;
    private ObjectList fActualEnumeration;
    private XSObjectList fAnnotations;
    private boolean fAnonymous;
    private XSSimpleTypeDecl fBase;
    private boolean fBounded;
    private short fBuiltInKind;
    private TypeValidator[] fDVs;
    private ValidatedInfo[] fEnumeration;
    private ObjectList fEnumerationItemTypeList;
    private int fEnumerationSize;
    private ShortList fEnumerationTypeList;
    private XSObjectListImpl fFacets;
    private short fFacetsDefined;
    private short fFinalSet;
    private boolean fFinite;
    private short fFixedFacet;
    private int fFractionDigits;
    private boolean fIsImmutable;
    private XSSimpleTypeDecl fItemType;
    private int fLength;
    private StringList fLexicalEnumeration;
    private StringList fLexicalPattern;
    private Object fMaxExclusive;
    private Object fMaxInclusive;
    private int fMaxLength;
    private XSSimpleTypeDecl[] fMemberTypes;
    private Object fMinExclusive;
    private Object fMinInclusive;
    private int fMinLength;
    private XSObjectListImpl fMultiValueFacets;
    private XSNamespaceItem fNamespaceItem;
    private boolean fNumeric;
    private short fOrdered;
    private Vector fPattern;
    private Vector fPatternStr;
    private short fPatternType;
    private String fTargetNamespace;
    private int fTotalDigits;
    private String fTypeName;
    private short fValidationDV;
    private short fVariety;
    private short fWhiteSpace;
    public XSAnnotation fractionDigitsAnnotation;
    public XSAnnotation lengthAnnotation;
    public XSAnnotation maxExclusiveAnnotation;
    public XSAnnotation maxInclusiveAnnotation;
    public XSAnnotation maxLengthAnnotation;
    public XSAnnotation minExclusiveAnnotation;
    public XSAnnotation minInclusiveAnnotation;
    public XSAnnotation minLengthAnnotation;
    public XSObjectListImpl patternAnnotations;
    public XSAnnotation totalDigitsAnnotation;
    public XSAnnotation whiteSpaceAnnotation;

    class C46371 implements ValidationContext {
        C46371() {
        }

        public boolean needFacetChecking() {
            return true;
        }

        public boolean needExtraChecking() {
            return false;
        }

        public boolean needToNormalize() {
            return true;
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return false;
        }

        public boolean isEntityUnparsed(String name) {
            return false;
        }

        public boolean isIdDeclared(String name) {
            return false;
        }

        public void addId(String name) {
        }

        public void addIdRef(String name) {
        }

        public String getSymbol(String symbol) {
            return symbol.intern();
        }

        public String getURI(String prefix) {
            return null;
        }

        public Locale getLocale() {
            return Locale.getDefault();
        }
    }

    class C46382 implements ValidationContext {
        C46382() {
        }

        public boolean needFacetChecking() {
            return true;
        }

        public boolean needExtraChecking() {
            return false;
        }

        public boolean needToNormalize() {
            return false;
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return false;
        }

        public boolean isEntityUnparsed(String name) {
            return false;
        }

        public boolean isIdDeclared(String name) {
            return false;
        }

        public void addId(String name) {
        }

        public void addIdRef(String name) {
        }

        public String getSymbol(String symbol) {
            return symbol.intern();
        }

        public String getURI(String prefix) {
            return null;
        }

        public Locale getLocale() {
            return Locale.getDefault();
        }
    }

    private static abstract class AbstractObjectList extends AbstractList implements ObjectList {
        private AbstractObjectList() {
        }

        public Object get(int index) {
            if (index >= 0 && index < getLength()) {
                return item(index);
            }
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        public int size() {
            return getLength();
        }
    }

    class C46393 extends AbstractObjectList {
        C46393() {
            super();
        }

        public int getLength() {
            return XSSimpleTypeDecl.this.fEnumeration != null ? XSSimpleTypeDecl.this.fEnumerationSize : 0;
        }

        public boolean contains(Object item) {
            if (XSSimpleTypeDecl.this.fEnumeration == null) {
                return false;
            }
            for (int i = 0; i < XSSimpleTypeDecl.this.fEnumerationSize; i++) {
                if (XSSimpleTypeDecl.this.fEnumeration[i].getActualValue().equals(item)) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= getLength()) {
                return null;
            }
            return XSSimpleTypeDecl.this.fEnumeration[index].getActualValue();
        }
    }

    class C46404 extends AbstractObjectList {
        C46404() {
            super();
        }

        public int getLength() {
            return XSSimpleTypeDecl.this.fEnumeration != null ? XSSimpleTypeDecl.this.fEnumerationSize : 0;
        }

        public boolean contains(Object item) {
            if (XSSimpleTypeDecl.this.fEnumeration == null || !(item instanceof ShortList)) {
                return false;
            }
            for (int i = 0; i < XSSimpleTypeDecl.this.fEnumerationSize; i++) {
                if (XSSimpleTypeDecl.this.fEnumeration[i].itemValueTypes == item) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= getLength()) {
                return null;
            }
            return XSSimpleTypeDecl.this.fEnumeration[index].itemValueTypes;
        }
    }

    static final class ValidationContextImpl implements ValidationContext {
        final ValidationContext fExternal;
        NamespaceContext fNSContext;

        ValidationContextImpl(ValidationContext external) {
            this.fExternal = external;
        }

        void setNSContext(NamespaceContext nsContext) {
            this.fNSContext = nsContext;
        }

        public boolean needFacetChecking() {
            return this.fExternal.needFacetChecking();
        }

        public boolean needExtraChecking() {
            return this.fExternal.needExtraChecking();
        }

        public boolean needToNormalize() {
            return this.fExternal.needToNormalize();
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return this.fExternal.isEntityDeclared(name);
        }

        public boolean isEntityUnparsed(String name) {
            return this.fExternal.isEntityUnparsed(name);
        }

        public boolean isIdDeclared(String name) {
            return this.fExternal.isIdDeclared(name);
        }

        public void addId(String name) {
            this.fExternal.addId(name);
        }

        public void addIdRef(String name) {
            this.fExternal.addIdRef(name);
        }

        public String getSymbol(String symbol) {
            return this.fExternal.getSymbol(symbol);
        }

        public String getURI(String prefix) {
            if (this.fNSContext == null) {
                return this.fExternal.getURI(prefix);
            }
            return this.fNSContext.getURI(prefix);
        }

        public Locale getLocale() {
            return this.fExternal.getLocale();
        }
    }

    private static final class XSFacetImpl implements XSFacet {
        final XSObjectList annotations;
        Object avalue;
        final boolean fixed;
        final int ivalue;
        final short kind;
        final String svalue;

        public XSFacetImpl(short kind, String svalue, int ivalue, Object avalue, boolean fixed, XSAnnotation annotation) {
            this.kind = kind;
            this.svalue = svalue;
            this.ivalue = ivalue;
            this.avalue = avalue;
            this.fixed = fixed;
            if (annotation != null) {
                this.annotations = new XSObjectListImpl();
                ((XSObjectListImpl) this.annotations).addXSObject(annotation);
                return;
            }
            this.annotations = XSObjectListImpl.EMPTY_LIST;
        }

        public XSAnnotation getAnnotation() {
            return (XSAnnotation) this.annotations.item(0);
        }

        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        public short getFacetKind() {
            return this.kind;
        }

        public String getLexicalFacetValue() {
            return this.svalue;
        }

        public Object getActualFacetValue() {
            if (this.avalue == null) {
                if (this.kind == (short) 16) {
                    this.avalue = this.svalue;
                } else {
                    this.avalue = BigInteger.valueOf((long) this.ivalue);
                }
            }
            return this.avalue;
        }

        public int getIntFacetValue() {
            return this.ivalue;
        }

        public boolean getFixed() {
            return this.fixed;
        }

        public String getName() {
            return null;
        }

        public String getNamespace() {
            return null;
        }

        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        public short getType() {
            return (short) 13;
        }
    }

    private static final class XSMVFacetImpl implements XSMultiValueFacet {
        final XSObjectList annotations;
        final ObjectList avalues;
        final short kind;
        final StringList svalues;

        public XSMVFacetImpl(short kind, StringList svalues, ObjectList avalues, XSObjectList annotations) {
            this.kind = kind;
            this.svalues = svalues;
            this.avalues = avalues;
            if (annotations == null) {
                annotations = XSObjectListImpl.EMPTY_LIST;
            }
            this.annotations = annotations;
        }

        public short getFacetKind() {
            return this.kind;
        }

        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        public StringList getLexicalFacetValues() {
            return this.svalues;
        }

        public ObjectList getEnumerationValues() {
            return this.avalues;
        }

        public String getName() {
            return null;
        }

        public String getNamespace() {
            return null;
        }

        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        public short getType() {
            return (short) 14;
        }
    }

    static {
        r0 = new short[30];
        fDVNormalizeType = r0;
    }

    protected static TypeValidator[] getGDVs() {
        return (TypeValidator[]) gDVs.clone();
    }

    protected void setDVs(TypeValidator[] dvs) {
        this.fDVs = dvs;
    }

    public XSSimpleTypeDecl() {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, short validateDV, short ordered, boolean bounded, boolean finite, boolean numeric, boolean isImmutable, short builtInKind) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fIsImmutable = isImmutable;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = "http://www.w3.org/2001/XMLSchema";
        this.fVariety = (short) 1;
        this.fValidationDV = validateDV;
        this.fFacetsDefined = (short) 16;
        if (validateDV == (short) 0 || validateDV == (short) 29 || validateDV == (short) 1) {
            this.fWhiteSpace = (short) 0;
        } else {
            this.fWhiteSpace = (short) 2;
            this.fFixedFacet = (short) 16;
        }
        this.fOrdered = ordered;
        this.fBounded = bounded;
        this.fFinite = finite;
        this.fNumeric = numeric;
        this.fAnnotations = null;
        this.fBuiltInKind = builtInKind;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, String uri, short finalSet, boolean isImmutable, XSObjectList annotations, short builtInKind) {
        this(base, name, uri, finalSet, isImmutable, annotations);
        this.fBuiltInKind = builtInKind;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, String uri, short finalSet, boolean isImmutable, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case (short) 2:
                this.fItemType = this.fBase.fItemType;
                break;
            case (short) 3:
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fEnumerationSize = this.fBase.fEnumerationSize;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        this.lengthAnnotation = this.fBase.lengthAnnotation;
        this.minLengthAnnotation = this.fBase.minLengthAnnotation;
        this.maxLengthAnnotation = this.fBase.maxLengthAnnotation;
        this.patternAnnotations = this.fBase.patternAnnotations;
        this.enumerationAnnotations = this.fBase.enumerationAnnotations;
        this.whiteSpaceAnnotation = this.fBase.whiteSpaceAnnotation;
        this.maxExclusiveAnnotation = this.fBase.maxExclusiveAnnotation;
        this.maxInclusiveAnnotation = this.fBase.maxInclusiveAnnotation;
        this.minExclusiveAnnotation = this.fBase.minExclusiveAnnotation;
        this.minInclusiveAnnotation = this.fBase.minInclusiveAnnotation;
        this.totalDigitsAnnotation = this.fBase.totalDigitsAnnotation;
        this.fractionDigitsAnnotation = this.fBase.fractionDigitsAnnotation;
        calcFundamentalFacets();
        this.fIsImmutable = isImmutable;
        this.fBuiltInKind = base.fBuiltInKind;
    }

    protected XSSimpleTypeDecl(String name, String uri, short finalSet, XSSimpleTypeDecl itemType, boolean isImmutable, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = (short) 2;
        this.fItemType = itemType;
        this.fValidationDV = (short) 25;
        this.fFacetsDefined = (short) 16;
        this.fFixedFacet = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fIsImmutable = isImmutable;
        this.fBuiltInKind = (short) 44;
    }

    protected XSSimpleTypeDecl(String name, String uri, short finalSet, XSSimpleTypeDecl[] memberTypes, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = (short) 0;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = (short) 0;
        this.fFixedFacet = (short) 0;
        this.fWhiteSpace = (short) 0;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = (short) 0;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = (short) 3;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = (short) 26;
        this.fFacetsDefined = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fIsImmutable = false;
        this.fBuiltInKind = (short) 45;
    }

    protected XSSimpleTypeDecl setRestrictionValues(XSSimpleTypeDecl base, String name, String uri, short finalSet, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = base;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case (short) 2:
                this.fItemType = this.fBase.fItemType;
                break;
            case (short) 3:
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fEnumerationSize = this.fBase.fEnumerationSize;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        calcFundamentalFacets();
        this.fBuiltInKind = base.fBuiltInKind;
        return this;
    }

    protected XSSimpleTypeDecl setListValues(String name, String uri, short finalSet, XSSimpleTypeDecl itemType, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = (short) 2;
        this.fItemType = itemType;
        this.fValidationDV = (short) 25;
        this.fFacetsDefined = (short) 16;
        this.fFixedFacet = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 44;
        return this;
    }

    protected XSSimpleTypeDecl setUnionValues(String name, String uri, short finalSet, XSSimpleTypeDecl[] memberTypes, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = (short) 3;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = (short) 26;
        this.fFacetsDefined = (short) 16;
        this.fWhiteSpace = (short) 2;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 45;
        return this;
    }

    public short getType() {
        return (short) 3;
    }

    public short getTypeCategory() {
        return (short) 16;
    }

    public String getName() {
        return getAnonymous() ? null : this.fTypeName;
    }

    public String getTypeName() {
        return this.fTypeName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public short getFinal() {
        return this.fFinalSet;
    }

    public boolean isFinal(short derivation) {
        return (this.fFinalSet & derivation) != 0;
    }

    public XSTypeDefinition getBaseType() {
        return this.fBase;
    }

    public boolean getAnonymous() {
        return this.fAnonymous || this.fTypeName == null;
    }

    public short getVariety() {
        return this.fValidationDV == (short) 0 ? (short) 0 : this.fVariety;
    }

    public boolean isIDType() {
        switch (this.fVariety) {
            case (short) 1:
                if (this.fValidationDV != (short) 21) {
                    return false;
                }
                return true;
            case (short) 2:
                return this.fItemType.isIDType();
            case (short) 3:
                for (XSSimpleTypeDecl isIDType : this.fMemberTypes) {
                    if (isIDType.isIDType()) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public short getWhitespace() throws DatatypeException {
        if (this.fVariety != (short) 3) {
            return this.fWhiteSpace;
        }
        throw new DatatypeException("dt-whitespace", new Object[]{this.fTypeName});
    }

    public short getPrimitiveKind() {
        if (this.fVariety != (short) 1 || this.fValidationDV == (short) 0) {
            return (short) 0;
        }
        if (this.fValidationDV == (short) 21 || this.fValidationDV == (short) 22 || this.fValidationDV == (short) 23) {
            return (short) 1;
        }
        if (this.fValidationDV == (short) 24) {
            return (short) 3;
        }
        return this.fValidationDV;
    }

    public short getBuiltInKind() {
        return this.fBuiltInKind;
    }

    public XSSimpleTypeDefinition getPrimitiveType() {
        if (this.fVariety != (short) 1 || this.fValidationDV == (short) 0) {
            return null;
        }
        XSSimpleTypeDecl pri = this;
        while (pri.fBase != fAnySimpleType) {
            pri = pri.fBase;
        }
        return pri;
    }

    public XSSimpleTypeDefinition getItemType() {
        if (this.fVariety == (short) 2) {
            return this.fItemType;
        }
        return null;
    }

    public XSObjectList getMemberTypes() {
        if (this.fVariety == (short) 3) {
            return new XSObjectListImpl(this.fMemberTypes, this.fMemberTypes.length);
        }
        return XSObjectListImpl.EMPTY_LIST;
    }

    public void applyFacets(XSFacets facets, short presentFacet, short fixedFacet, ValidationContext context) throws InvalidDatatypeFacetException {
        if (context == null) {
            context = fEmptyContext;
        }
        applyFacets(facets, presentFacet, fixedFacet, (short) 0, context);
    }

    void applyFacets1(XSFacets facets, short presentFacet, short fixedFacet) {
        try {
            applyFacets(facets, presentFacet, fixedFacet, (short) 0, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
    }

    void applyFacets1(XSFacets facets, short presentFacet, short fixedFacet, short patternType) {
        try {
            applyFacets(facets, presentFacet, fixedFacet, patternType, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
    }

    void applyFacets(XSFacets facets, short presentFacet, short fixedFacet, short patternType, ValidationContext context) throws InvalidDatatypeFacetException {
        if (!this.fIsImmutable) {
            int i;
            int result;
            ValidatedInfo tempInfo = new ValidatedInfo();
            this.fFacetsDefined = (short) 0;
            this.fFixedFacet = (short) 0;
            short allowedFacet = this.fDVs[this.fValidationDV].getAllowedFacets();
            if ((presentFacet & 1) != 0) {
                if ((allowedFacet & 1) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"length", this.fTypeName});
                } else {
                    this.fLength = facets.length;
                    this.lengthAnnotation = facets.lengthAnnotation;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 1);
                    if ((fixedFacet & 1) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 1);
                    }
                }
            }
            if ((presentFacet & 2) != 0) {
                if ((allowedFacet & 2) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"minLength", this.fTypeName});
                } else {
                    this.fMinLength = facets.minLength;
                    this.minLengthAnnotation = facets.minLengthAnnotation;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 2);
                    if ((fixedFacet & 2) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 2);
                    }
                }
            }
            if ((presentFacet & 4) != 0) {
                if ((allowedFacet & 4) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"maxLength", this.fTypeName});
                } else {
                    this.fMaxLength = facets.maxLength;
                    this.maxLengthAnnotation = facets.maxLengthAnnotation;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 4);
                    if ((fixedFacet & 4) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 4);
                    }
                }
            }
            if ((presentFacet & 8) != 0) {
                if ((allowedFacet & 8) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"pattern", this.fTypeName});
                } else {
                    this.patternAnnotations = facets.patternAnnotations;
                    RegularExpression regex = null;
                    try {
                        regex = new RegularExpression(facets.pattern, "X", context.getLocale());
                    } catch (Exception e) {
                        reportError("InvalidRegex", new Object[]{facets.pattern, e.getLocalizedMessage()});
                    }
                    if (regex != null) {
                        this.fPattern = new Vector();
                        this.fPattern.addElement(regex);
                        this.fPatternStr = new Vector();
                        this.fPatternStr.addElement(facets.pattern);
                        this.fFacetsDefined = (short) (this.fFacetsDefined | 8);
                        if ((fixedFacet & 8) != 0) {
                            this.fFixedFacet = (short) (this.fFixedFacet | 8);
                        }
                    }
                }
            }
            if ((presentFacet & 16) != 0) {
                if ((allowedFacet & 16) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"whiteSpace", this.fTypeName});
                } else {
                    this.fWhiteSpace = facets.whiteSpace;
                    this.whiteSpaceAnnotation = facets.whiteSpaceAnnotation;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 16);
                    if ((fixedFacet & 16) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 16);
                    }
                }
            }
            if ((presentFacet & 2048) != 0) {
                if ((allowedFacet & 2048) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"enumeration", this.fTypeName});
                } else {
                    Vector enumVals = facets.enumeration;
                    int size = enumVals.size();
                    this.fEnumeration = new ValidatedInfo[size];
                    Vector enumNSDecls = facets.enumNSDecls;
                    ValidationContextImpl ctx = new ValidationContextImpl(context);
                    this.enumerationAnnotations = facets.enumAnnotations;
                    this.fEnumerationSize = 0;
                    for (i = 0; i < size; i++) {
                        if (enumNSDecls != null) {
                            ctx.setNSContext((NamespaceContext) enumNSDecls.elementAt(i));
                        }
                        try {
                            ValidatedInfo info = getActualEnumValue((String) enumVals.elementAt(i), ctx, null);
                            ValidatedInfo[] validatedInfoArr = this.fEnumeration;
                            int i2 = this.fEnumerationSize;
                            this.fEnumerationSize = i2 + 1;
                            validatedInfoArr[i2] = info;
                        } catch (InvalidDatatypeValueException e2) {
                            reportError("enumeration-valid-restriction", new Object[]{enumVals.elementAt(i), getBaseType().getName()});
                        }
                    }
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 2048);
                    if ((fixedFacet & 2048) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 2048);
                    }
                }
            }
            if ((presentFacet & 32) != 0) {
                if ((allowedFacet & 32) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"maxInclusive", this.fTypeName});
                } else {
                    this.maxInclusiveAnnotation = facets.maxInclusiveAnnotation;
                    try {
                        this.fMaxInclusive = this.fBase.getActualValue(facets.maxInclusive, context, tempInfo, true);
                        this.fFacetsDefined = (short) (this.fFacetsDefined | 32);
                        if ((fixedFacet & 32) != 0) {
                            this.fFixedFacet = (short) (this.fFixedFacet | 32);
                        }
                    } catch (InvalidDatatypeValueException ide) {
                        reportError(ide.getKey(), ide.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.maxInclusive, "maxInclusive", this.fBase.getName()});
                    }
                    if (!((this.fBase.fFacetsDefined & 32) == 0 || (this.fBase.fFixedFacet & 32) == 0 || this.fDVs[this.fValidationDV].compare(this.fMaxInclusive, this.fBase.fMaxInclusive) == 0)) {
                        reportError("FixedFacetValue", new Object[]{"maxInclusive", this.fMaxInclusive, this.fBase.fMaxInclusive, this.fTypeName});
                    }
                    try {
                        this.fBase.validate(context, tempInfo);
                    } catch (InvalidDatatypeValueException ide2) {
                        reportError(ide2.getKey(), ide2.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.maxInclusive, "maxInclusive", this.fBase.getName()});
                    }
                }
            }
            boolean needCheckBase = true;
            if ((presentFacet & 64) != 0) {
                if ((allowedFacet & 64) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"maxExclusive", this.fTypeName});
                } else {
                    this.maxExclusiveAnnotation = facets.maxExclusiveAnnotation;
                    try {
                        this.fMaxExclusive = this.fBase.getActualValue(facets.maxExclusive, context, tempInfo, true);
                        this.fFacetsDefined = (short) (this.fFacetsDefined | 64);
                        if ((fixedFacet & 64) != 0) {
                            this.fFixedFacet = (short) (this.fFixedFacet | 64);
                        }
                    } catch (InvalidDatatypeValueException ide22) {
                        reportError(ide22.getKey(), ide22.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.maxExclusive, "maxExclusive", this.fBase.getName()});
                    }
                    if ((this.fBase.fFacetsDefined & 64) != 0) {
                        result = this.fDVs[this.fValidationDV].compare(this.fMaxExclusive, this.fBase.fMaxExclusive);
                        if (!((this.fBase.fFixedFacet & 64) == 0 || result == 0)) {
                            reportError("FixedFacetValue", new Object[]{"maxExclusive", facets.maxExclusive, this.fBase.fMaxExclusive, this.fTypeName});
                        }
                        if (result == 0) {
                            needCheckBase = false;
                        }
                    }
                    if (needCheckBase) {
                        try {
                            this.fBase.validate(context, tempInfo);
                        } catch (InvalidDatatypeValueException ide222) {
                            reportError(ide222.getKey(), ide222.getArgs());
                            reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.maxExclusive, "maxExclusive", this.fBase.getName()});
                        }
                    } else if ((this.fBase.fFacetsDefined & 32) != 0 && this.fDVs[this.fValidationDV].compare(this.fMaxExclusive, this.fBase.fMaxInclusive) > 0) {
                        reportError("maxExclusive-valid-restriction.2", new Object[]{facets.maxExclusive, this.fBase.fMaxInclusive});
                    }
                }
            }
            needCheckBase = true;
            if ((presentFacet & 128) != 0) {
                if ((allowedFacet & 128) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"minExclusive", this.fTypeName});
                } else {
                    this.minExclusiveAnnotation = facets.minExclusiveAnnotation;
                    try {
                        this.fMinExclusive = this.fBase.getActualValue(facets.minExclusive, context, tempInfo, true);
                        this.fFacetsDefined = (short) (this.fFacetsDefined | 128);
                        if ((fixedFacet & 128) != 0) {
                            this.fFixedFacet = (short) (this.fFixedFacet | 128);
                        }
                    } catch (InvalidDatatypeValueException ide2222) {
                        reportError(ide2222.getKey(), ide2222.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.minExclusive, "minExclusive", this.fBase.getName()});
                    }
                    if ((this.fBase.fFacetsDefined & 128) != 0) {
                        result = this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fBase.fMinExclusive);
                        if (!((this.fBase.fFixedFacet & 128) == 0 || result == 0)) {
                            reportError("FixedFacetValue", new Object[]{"minExclusive", facets.minExclusive, this.fBase.fMinExclusive, this.fTypeName});
                        }
                        if (result == 0) {
                            needCheckBase = false;
                        }
                    }
                    if (needCheckBase) {
                        try {
                            this.fBase.validate(context, tempInfo);
                        } catch (InvalidDatatypeValueException ide22222) {
                            reportError(ide22222.getKey(), ide22222.getArgs());
                            reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.minExclusive, "minExclusive", this.fBase.getName()});
                        }
                    } else if ((this.fBase.fFacetsDefined & 256) != 0 && this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fBase.fMinInclusive) < 0) {
                        reportError("minExclusive-valid-restriction.3", new Object[]{facets.minExclusive, this.fBase.fMinInclusive});
                    }
                }
            }
            if ((presentFacet & 256) != 0) {
                if ((allowedFacet & 256) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"minInclusive", this.fTypeName});
                } else {
                    this.minInclusiveAnnotation = facets.minInclusiveAnnotation;
                    try {
                        this.fMinInclusive = this.fBase.getActualValue(facets.minInclusive, context, tempInfo, true);
                        this.fFacetsDefined = (short) (this.fFacetsDefined | 256);
                        if ((fixedFacet & 256) != 0) {
                            this.fFixedFacet = (short) (this.fFixedFacet | 256);
                        }
                    } catch (InvalidDatatypeValueException ide222222) {
                        reportError(ide222222.getKey(), ide222222.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.minInclusive, "minInclusive", this.fBase.getName()});
                    }
                    if (!((this.fBase.fFacetsDefined & 256) == 0 || (this.fBase.fFixedFacet & 256) == 0 || this.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fBase.fMinInclusive) == 0)) {
                        reportError("FixedFacetValue", new Object[]{"minInclusive", facets.minInclusive, this.fBase.fMinInclusive, this.fTypeName});
                    }
                    try {
                        this.fBase.validate(context, tempInfo);
                    } catch (InvalidDatatypeValueException ide2222222) {
                        reportError(ide2222222.getKey(), ide2222222.getArgs());
                        reportError("FacetValueFromBase", new Object[]{this.fTypeName, facets.minInclusive, "minInclusive", this.fBase.getName()});
                    }
                }
            }
            if ((presentFacet & 512) != 0) {
                if ((allowedFacet & 512) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"totalDigits", this.fTypeName});
                } else {
                    this.totalDigitsAnnotation = facets.totalDigitsAnnotation;
                    this.fTotalDigits = facets.totalDigits;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 512);
                    if ((fixedFacet & 512) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 512);
                    }
                }
            }
            if ((presentFacet & 1024) != 0) {
                if ((allowedFacet & 1024) == 0) {
                    reportError("cos-applicable-facets", new Object[]{"fractionDigits", this.fTypeName});
                } else {
                    this.fFractionDigits = facets.fractionDigits;
                    this.fractionDigitsAnnotation = facets.fractionDigitsAnnotation;
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 1024);
                    if ((fixedFacet & 1024) != 0) {
                        this.fFixedFacet = (short) (this.fFixedFacet | 1024);
                    }
                }
            }
            if (patternType != (short) 0) {
                this.fPatternType = patternType;
            }
            if (this.fFacetsDefined != (short) 0) {
                if (!((this.fFacetsDefined & 2) == 0 || (this.fFacetsDefined & 4) == 0 || this.fMinLength <= this.fMaxLength)) {
                    reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(this.fMinLength), Integer.toString(this.fMaxLength), this.fTypeName});
                }
                if (!((this.fFacetsDefined & 64) == 0 || (this.fFacetsDefined & 32) == 0)) {
                    reportError("maxInclusive-maxExclusive", new Object[]{this.fMaxInclusive, this.fMaxExclusive, this.fTypeName});
                }
                if (!((this.fFacetsDefined & 128) == 0 || (this.fFacetsDefined & 256) == 0)) {
                    reportError("minInclusive-minExclusive", new Object[]{this.fMinInclusive, this.fMinExclusive, this.fTypeName});
                }
                if (!((this.fFacetsDefined & 32) == 0 || (this.fFacetsDefined & 256) == 0)) {
                    result = this.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxInclusive);
                    if (!(result == -1 || result == 0)) {
                        reportError("minInclusive-less-than-equal-to-maxInclusive", new Object[]{this.fMinInclusive, this.fMaxInclusive, this.fTypeName});
                    }
                }
                if (!((this.fFacetsDefined & 64) == 0 || (this.fFacetsDefined & 128) == 0)) {
                    result = this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxExclusive);
                    if (!(result == -1 || result == 0)) {
                        reportError("minExclusive-less-than-equal-to-maxExclusive", new Object[]{this.fMinExclusive, this.fMaxExclusive, this.fTypeName});
                    }
                }
                if (!((this.fFacetsDefined & 32) == 0 || (this.fFacetsDefined & 128) == 0 || this.fDVs[this.fValidationDV].compare(this.fMinExclusive, this.fMaxInclusive) == -1)) {
                    reportError("minExclusive-less-than-maxInclusive", new Object[]{this.fMinExclusive, this.fMaxInclusive, this.fTypeName});
                }
                if (!((this.fFacetsDefined & 64) == 0 || (this.fFacetsDefined & 256) == 0 || this.fDVs[this.fValidationDV].compare(this.fMinInclusive, this.fMaxExclusive) == -1)) {
                    reportError("minInclusive-less-than-maxExclusive", new Object[]{this.fMinInclusive, this.fMaxExclusive, this.fTypeName});
                }
                if (!((this.fFacetsDefined & 1024) == 0 || (this.fFacetsDefined & 512) == 0 || this.fFractionDigits <= this.fTotalDigits)) {
                    reportError("fractionDigits-totalDigits", new Object[]{Integer.toString(this.fFractionDigits), Integer.toString(this.fTotalDigits), this.fTypeName});
                }
                if ((this.fFacetsDefined & 1) != 0) {
                    if ((this.fBase.fFacetsDefined & 2) != 0 && this.fLength < this.fBase.fMinLength) {
                        reportError("length-minLength-maxLength.1.1", new Object[]{this.fTypeName, Integer.toString(this.fLength), Integer.toString(this.fBase.fMinLength)});
                    }
                    if ((this.fBase.fFacetsDefined & 4) != 0 && this.fLength > this.fBase.fMaxLength) {
                        reportError("length-minLength-maxLength.2.1", new Object[]{this.fTypeName, Integer.toString(this.fLength), Integer.toString(this.fBase.fMaxLength)});
                    }
                    if (!((this.fBase.fFacetsDefined & 1) == 0 || this.fLength == this.fBase.fLength)) {
                        reportError("length-valid-restriction", new Object[]{Integer.toString(this.fLength), Integer.toString(this.fBase.fLength), this.fTypeName});
                    }
                }
                if (!((this.fBase.fFacetsDefined & 1) == 0 && (this.fFacetsDefined & 1) == 0)) {
                    if ((this.fFacetsDefined & 2) != 0) {
                        if (this.fBase.fLength < this.fMinLength) {
                            reportError("length-minLength-maxLength.1.1", new Object[]{this.fTypeName, Integer.toString(this.fBase.fLength), Integer.toString(this.fMinLength)});
                        }
                        if ((this.fBase.fFacetsDefined & 2) == 0) {
                            reportError("length-minLength-maxLength.1.2.a", new Object[]{this.fTypeName});
                        }
                        if (this.fMinLength != this.fBase.fMinLength) {
                            reportError("length-minLength-maxLength.1.2.b", new Object[]{this.fTypeName, Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength)});
                        }
                    }
                    if ((this.fFacetsDefined & 4) != 0) {
                        if (this.fBase.fLength > this.fMaxLength) {
                            reportError("length-minLength-maxLength.2.1", new Object[]{this.fTypeName, Integer.toString(this.fBase.fLength), Integer.toString(this.fMaxLength)});
                        }
                        if ((this.fBase.fFacetsDefined & 4) == 0) {
                            reportError("length-minLength-maxLength.2.2.a", new Object[]{this.fTypeName});
                        }
                        if (this.fMaxLength != this.fBase.fMaxLength) {
                            reportError("length-minLength-maxLength.2.2.b", new Object[]{this.fTypeName, Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fBase.fMaxLength)});
                        }
                    }
                }
                if ((this.fFacetsDefined & 2) != 0) {
                    if ((this.fBase.fFacetsDefined & 4) != 0) {
                        if (this.fMinLength > this.fBase.fMaxLength) {
                            reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                        }
                    } else if ((this.fBase.fFacetsDefined & 2) != 0) {
                        if (!((this.fBase.fFixedFacet & 2) == 0 || this.fMinLength == this.fBase.fMinLength)) {
                            reportError("FixedFacetValue", new Object[]{"minLength", Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength), this.fTypeName});
                        }
                        if (this.fMinLength < this.fBase.fMinLength) {
                            reportError("minLength-valid-restriction", new Object[]{Integer.toString(this.fMinLength), Integer.toString(this.fBase.fMinLength), this.fTypeName});
                        }
                    }
                }
                if (!((this.fFacetsDefined & 4) == 0 || (this.fBase.fFacetsDefined & 2) == 0 || this.fMaxLength >= this.fBase.fMinLength)) {
                    reportError("minLength-less-than-equal-to-maxLength", new Object[]{Integer.toString(this.fBase.fMinLength), Integer.toString(this.fMaxLength)});
                }
                if (!((this.fFacetsDefined & 4) == 0 || (this.fBase.fFacetsDefined & 4) == 0)) {
                    if (!((this.fBase.fFixedFacet & 4) == 0 || this.fMaxLength == this.fBase.fMaxLength)) {
                        reportError("FixedFacetValue", new Object[]{"maxLength", Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                    }
                    if (this.fMaxLength > this.fBase.fMaxLength) {
                        reportError("maxLength-valid-restriction", new Object[]{Integer.toString(this.fMaxLength), Integer.toString(this.fBase.fMaxLength), this.fTypeName});
                    }
                }
                if (!((this.fFacetsDefined & 512) == 0 || (this.fBase.fFacetsDefined & 512) == 0)) {
                    if (!((this.fBase.fFixedFacet & 512) == 0 || this.fTotalDigits == this.fBase.fTotalDigits)) {
                        reportError("FixedFacetValue", new Object[]{"totalDigits", Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits), this.fTypeName});
                    }
                    if (this.fTotalDigits > this.fBase.fTotalDigits) {
                        reportError("totalDigits-valid-restriction", new Object[]{Integer.toString(this.fTotalDigits), Integer.toString(this.fBase.fTotalDigits), this.fTypeName});
                    }
                }
                if (!((this.fFacetsDefined & 1024) == 0 || (this.fBase.fFacetsDefined & 512) == 0 || this.fFractionDigits <= this.fBase.fTotalDigits)) {
                    reportError("fractionDigits-totalDigits", new Object[]{Integer.toString(this.fFractionDigits), Integer.toString(this.fTotalDigits), this.fTypeName});
                }
                if ((this.fFacetsDefined & 1024) != 0) {
                    if ((this.fBase.fFacetsDefined & 1024) != 0) {
                        if (!((this.fBase.fFixedFacet & 1024) == 0 || this.fFractionDigits == this.fBase.fFractionDigits) || (this.fValidationDV == (short) 24 && this.fFractionDigits != 0)) {
                            reportError("FixedFacetValue", new Object[]{"fractionDigits", Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits), this.fTypeName});
                        }
                        if (this.fFractionDigits > this.fBase.fFractionDigits) {
                            reportError("fractionDigits-valid-restriction", new Object[]{Integer.toString(this.fFractionDigits), Integer.toString(this.fBase.fFractionDigits), this.fTypeName});
                        }
                    } else if (this.fValidationDV == (short) 24 && this.fFractionDigits != 0) {
                        reportError("FixedFacetValue", new Object[]{"fractionDigits", Integer.toString(this.fFractionDigits), SchemaSymbols.ATTVAL_FALSE_0, this.fTypeName});
                    }
                }
                if (!((this.fFacetsDefined & 16) == 0 || (this.fBase.fFacetsDefined & 16) == 0)) {
                    if (!((this.fBase.fFixedFacet & 16) == 0 || this.fWhiteSpace == this.fBase.fWhiteSpace)) {
                        String[] strArr = new Object[4];
                        strArr[0] = "whiteSpace";
                        strArr[1] = whiteSpaceValue(this.fWhiteSpace);
                        strArr[2] = whiteSpaceValue(this.fBase.fWhiteSpace);
                        strArr[3] = this.fTypeName;
                        reportError("FixedFacetValue", strArr);
                    }
                    if (this.fWhiteSpace == (short) 0 && this.fBase.fWhiteSpace == (short) 2) {
                        reportError("whiteSpace-valid-restriction.1", new Object[]{this.fTypeName, SchemaSymbols.ATTVAL_PRESERVE});
                    }
                    if (this.fWhiteSpace == (short) 1 && this.fBase.fWhiteSpace == (short) 2) {
                        reportError("whiteSpace-valid-restriction.1", new Object[]{this.fTypeName, SchemaSymbols.ATTVAL_REPLACE});
                    }
                    if (this.fWhiteSpace == (short) 0 && this.fBase.fWhiteSpace == (short) 1) {
                        reportError("whiteSpace-valid-restriction.2", new Object[]{this.fTypeName});
                    }
                }
            }
            if ((this.fFacetsDefined & 1) == 0 && (this.fBase.fFacetsDefined & 1) != 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 1);
                this.fLength = this.fBase.fLength;
                this.lengthAnnotation = this.fBase.lengthAnnotation;
            }
            if ((this.fFacetsDefined & 2) == 0 && (this.fBase.fFacetsDefined & 2) != 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 2);
                this.fMinLength = this.fBase.fMinLength;
                this.minLengthAnnotation = this.fBase.minLengthAnnotation;
            }
            if ((this.fFacetsDefined & 4) == 0 && (this.fBase.fFacetsDefined & 4) != 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 4);
                this.fMaxLength = this.fBase.fMaxLength;
                this.maxLengthAnnotation = this.fBase.maxLengthAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 8) != 0) {
                if ((this.fFacetsDefined & 8) == 0) {
                    this.fFacetsDefined = (short) (this.fFacetsDefined | 8);
                    this.fPattern = this.fBase.fPattern;
                    this.fPatternStr = this.fBase.fPatternStr;
                    this.patternAnnotations = this.fBase.patternAnnotations;
                } else {
                    for (i = this.fBase.fPattern.size() - 1; i >= 0; i--) {
                        this.fPattern.addElement(this.fBase.fPattern.elementAt(i));
                        this.fPatternStr.addElement(this.fBase.fPatternStr.elementAt(i));
                    }
                    if (this.fBase.patternAnnotations != null) {
                        if (this.patternAnnotations != null) {
                            for (i = this.fBase.patternAnnotations.getLength() - 1; i >= 0; i--) {
                                this.patternAnnotations.addXSObject(this.fBase.patternAnnotations.item(i));
                            }
                        } else {
                            this.patternAnnotations = this.fBase.patternAnnotations;
                        }
                    }
                }
            }
            if ((this.fFacetsDefined & 16) == 0 && (this.fBase.fFacetsDefined & 16) != 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 16);
                this.fWhiteSpace = this.fBase.fWhiteSpace;
                this.whiteSpaceAnnotation = this.fBase.whiteSpaceAnnotation;
            }
            if ((this.fFacetsDefined & 2048) == 0 && (this.fBase.fFacetsDefined & 2048) != 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 2048);
                this.fEnumeration = this.fBase.fEnumeration;
                this.fEnumerationSize = this.fBase.fEnumerationSize;
                this.enumerationAnnotations = this.fBase.enumerationAnnotations;
            }
            if ((this.fBase.fFacetsDefined & 64) != 0 && (this.fFacetsDefined & 64) == 0 && (this.fFacetsDefined & 32) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 64);
                this.fMaxExclusive = this.fBase.fMaxExclusive;
                this.maxExclusiveAnnotation = this.fBase.maxExclusiveAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 32) != 0 && (this.fFacetsDefined & 64) == 0 && (this.fFacetsDefined & 32) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 32);
                this.fMaxInclusive = this.fBase.fMaxInclusive;
                this.maxInclusiveAnnotation = this.fBase.maxInclusiveAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 128) != 0 && (this.fFacetsDefined & 128) == 0 && (this.fFacetsDefined & 256) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 128);
                this.fMinExclusive = this.fBase.fMinExclusive;
                this.minExclusiveAnnotation = this.fBase.minExclusiveAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 256) != 0 && (this.fFacetsDefined & 128) == 0 && (this.fFacetsDefined & 256) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 256);
                this.fMinInclusive = this.fBase.fMinInclusive;
                this.minInclusiveAnnotation = this.fBase.minInclusiveAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 512) != 0 && (this.fFacetsDefined & 512) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 512);
                this.fTotalDigits = this.fBase.fTotalDigits;
                this.totalDigitsAnnotation = this.fBase.totalDigitsAnnotation;
            }
            if ((this.fBase.fFacetsDefined & 1024) != 0 && (this.fFacetsDefined & 1024) == 0) {
                this.fFacetsDefined = (short) (this.fFacetsDefined | 1024);
                this.fFractionDigits = this.fBase.fFractionDigits;
                this.fractionDigitsAnnotation = this.fBase.fractionDigitsAnnotation;
            }
            if (this.fPatternType == (short) 0 && this.fBase.fPatternType != (short) 0) {
                this.fPatternType = this.fBase.fPatternType;
            }
            this.fFixedFacet = (short) (this.fFixedFacet | this.fBase.fFixedFacet);
            calcFundamentalFacets();
        }
    }

    public Object validate(String content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        Object ob = getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return ob;
    }

    protected ValidatedInfo getActualEnumValue(String lexical, ValidationContext ctx, ValidatedInfo info) throws InvalidDatatypeValueException {
        return this.fBase.validateWithInfo(lexical, ctx, info);
    }

    public ValidatedInfo validateWithInfo(String content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return validatedInfo;
    }

    public Object validate(Object content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        Object ob = getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return ob;
    }

    public void validate(ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (!(!context.needFacetChecking() || this.fFacetsDefined == (short) 0 || this.fFacetsDefined == (short) 16)) {
            checkFacets(validatedInfo);
        }
        if (context.needExtraChecking()) {
            checkExtraRules(context, validatedInfo);
        }
    }

    private void checkFacets(ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        Object ob = validatedInfo.actualValue;
        String content = validatedInfo.normalizedValue;
        short type = validatedInfo.actualValueType;
        ShortList itemType = validatedInfo.itemValueTypes;
        if (!(this.fValidationDV == (short) 18 || this.fValidationDV == (short) 20)) {
            int length = this.fDVs[this.fValidationDV].getDataLength(ob);
            if ((this.fFacetsDefined & 4) != 0 && length > this.fMaxLength) {
                throw new InvalidDatatypeValueException("cvc-maxLength-valid", new Object[]{content, Integer.toString(length), Integer.toString(this.fMaxLength), this.fTypeName});
            } else if ((this.fFacetsDefined & 2) != 0 && length < this.fMinLength) {
                throw new InvalidDatatypeValueException("cvc-minLength-valid", new Object[]{content, Integer.toString(length), Integer.toString(this.fMinLength), this.fTypeName});
            } else if (!((this.fFacetsDefined & 1) == 0 || length == this.fLength)) {
                throw new InvalidDatatypeValueException("cvc-length-valid", new Object[]{content, Integer.toString(length), Integer.toString(this.fLength), this.fTypeName});
            }
        }
        if ((this.fFacetsDefined & 2048) != 0) {
            boolean present = false;
            int enumSize = this.fEnumerationSize;
            short primitiveType1 = convertToPrimitiveKind(type);
            int i = 0;
            while (i < enumSize) {
                short primitiveType2 = convertToPrimitiveKind(this.fEnumeration[i].actualValueType);
                if ((primitiveType1 == primitiveType2 || ((primitiveType1 == (short) 1 && primitiveType2 == (short) 2) || (primitiveType1 == (short) 2 && primitiveType2 == (short) 1))) && this.fEnumeration[i].actualValue.equals(ob)) {
                    if (primitiveType1 != (short) 44 && primitiveType1 != (short) 43) {
                        present = true;
                        break;
                    }
                    ShortList enumItemType = this.fEnumeration[i].itemValueTypes;
                    int typeList1Length = itemType != null ? itemType.getLength() : 0;
                    if (typeList1Length == (enumItemType != null ? enumItemType.getLength() : 0)) {
                        int j = 0;
                        while (j < typeList1Length) {
                            short primitiveItem1 = convertToPrimitiveKind(itemType.item(j));
                            short primitiveItem2 = convertToPrimitiveKind(enumItemType.item(j));
                            if (primitiveItem1 != primitiveItem2 && ((primitiveItem1 != (short) 1 || primitiveItem2 != (short) 2) && (primitiveItem1 != (short) 2 || primitiveItem2 != (short) 1))) {
                                break;
                            }
                            j++;
                        }
                        if (j == typeList1Length) {
                            present = true;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            if (!present) {
                appendEnumString(new StringBuffer());
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{content, new StringBuffer().toString()});
            }
        }
        if ((this.fFacetsDefined & 1024) != 0 && this.fDVs[this.fValidationDV].getFractionDigits(ob) > this.fFractionDigits) {
            throw new InvalidDatatypeValueException("cvc-fractionDigits-valid", new Object[]{content, Integer.toString(this.fDVs[this.fValidationDV].getFractionDigits(ob)), Integer.toString(this.fFractionDigits)});
        } else if ((this.fFacetsDefined & 512) == 0 || this.fDVs[this.fValidationDV].getTotalDigits(ob) <= this.fTotalDigits) {
            int compare;
            if ((this.fFacetsDefined & 32) != 0) {
                compare = this.fDVs[this.fValidationDV].compare(ob, this.fMaxInclusive);
                if (!(compare == -1 || compare == 0)) {
                    throw new InvalidDatatypeValueException("cvc-maxInclusive-valid", new Object[]{content, this.fMaxInclusive, this.fTypeName});
                }
            }
            if ((this.fFacetsDefined & 64) != 0) {
                if (this.fDVs[this.fValidationDV].compare(ob, this.fMaxExclusive) != -1) {
                    throw new InvalidDatatypeValueException("cvc-maxExclusive-valid", new Object[]{content, this.fMaxExclusive, this.fTypeName});
                }
            }
            if ((this.fFacetsDefined & 256) != 0) {
                compare = this.fDVs[this.fValidationDV].compare(ob, this.fMinInclusive);
                if (!(compare == 1 || compare == 0)) {
                    throw new InvalidDatatypeValueException("cvc-minInclusive-valid", new Object[]{content, this.fMinInclusive, this.fTypeName});
                }
            }
            if ((this.fFacetsDefined & 128) != 0) {
                if (this.fDVs[this.fValidationDV].compare(ob, this.fMinExclusive) != 1) {
                    throw new InvalidDatatypeValueException("cvc-minExclusive-valid", new Object[]{content, this.fMinExclusive, this.fTypeName});
                }
            }
        } else {
            throw new InvalidDatatypeValueException("cvc-totalDigits-valid", new Object[]{content, Integer.toString(this.fDVs[this.fValidationDV].getTotalDigits(ob)), Integer.toString(this.fTotalDigits)});
        }
    }

    private void checkExtraRules(ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        ListData ob = validatedInfo.actualValue;
        if (this.fVariety == (short) 1) {
            this.fDVs[this.fValidationDV].checkExtraRules(ob, context);
        } else if (this.fVariety == (short) 2) {
            ListData values = ob;
            XSSimpleType memberType = validatedInfo.memberType;
            int len = values.getLength();
            try {
                int i;
                if (this.fItemType.fVariety == (short) 3) {
                    XSSimpleTypeDecl[] memberTypes = validatedInfo.memberTypes;
                    for (i = len - 1; i >= 0; i--) {
                        validatedInfo.actualValue = values.item(i);
                        validatedInfo.memberType = memberTypes[i];
                        this.fItemType.checkExtraRules(context, validatedInfo);
                    }
                } else {
                    for (i = len - 1; i >= 0; i--) {
                        validatedInfo.actualValue = values.item(i);
                        this.fItemType.checkExtraRules(context, validatedInfo);
                    }
                }
                validatedInfo.actualValue = values;
                validatedInfo.memberType = memberType;
            } catch (Throwable th) {
                validatedInfo.actualValue = values;
                validatedInfo.memberType = memberType;
            }
        } else {
            ((XSSimpleTypeDecl) validatedInfo.memberType).checkExtraRules(context, validatedInfo);
        }
    }

    private Object getActualValue(Object content, ValidationContext context, ValidatedInfo validatedInfo, boolean needNormalize) throws InvalidDatatypeValueException {
        String nvalue;
        if (needNormalize) {
            nvalue = normalize(content, this.fWhiteSpace);
        } else {
            nvalue = content.toString();
        }
        if ((this.fFacetsDefined & 8) != 0) {
            int idx = this.fPattern.size() - 1;
            while (idx >= 0) {
                if (((RegularExpression) this.fPattern.elementAt(idx)).matches(nvalue)) {
                    idx--;
                } else {
                    throw new InvalidDatatypeValueException("cvc-pattern-valid", new Object[]{content, this.fPatternStr.elementAt(idx), this.fTypeName});
                }
            }
        }
        if (this.fVariety == (short) 1) {
            if (this.fPatternType != (short) 0) {
                boolean seenErr = false;
                if (this.fPatternType == (short) 1) {
                    seenErr = !XMLChar.isValidNmtoken(nvalue);
                } else if (this.fPatternType == (short) 2) {
                    seenErr = !XMLChar.isValidName(nvalue);
                } else if (this.fPatternType == (short) 3) {
                    seenErr = !XMLChar.isValidNCName(nvalue);
                }
                if (seenErr) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{nvalue, SPECIAL_PATTERN_STRING[this.fPatternType]});
                }
            }
            validatedInfo.normalizedValue = nvalue;
            Object avalue = this.fDVs[this.fValidationDV].getActualValue(nvalue, context);
            validatedInfo.actualValue = avalue;
            validatedInfo.actualValueType = this.fBuiltInKind;
            validatedInfo.actualType = this;
            return avalue;
        } else if (this.fVariety == (short) 2) {
            StringTokenizer stringTokenizer = new StringTokenizer(nvalue, " ");
            int countOfTokens = stringTokenizer.countTokens();
            Object[] avalue2 = new Object[countOfTokens];
            boolean isUnion = this.fItemType.getVariety() == (short) 3;
            short[] itemTypes = new short[(isUnion ? countOfTokens : 1)];
            if (!isUnion) {
                itemTypes[0] = this.fItemType.fBuiltInKind;
            }
            XSSimpleTypeDecl[] memberTypes = new XSSimpleTypeDecl[countOfTokens];
            for (i = 0; i < countOfTokens; i++) {
                avalue2[i] = this.fItemType.getActualValue(stringTokenizer.nextToken(), context, validatedInfo, false);
                if (!(!context.needFacetChecking() || this.fItemType.fFacetsDefined == (short) 0 || this.fItemType.fFacetsDefined == (short) 16)) {
                    this.fItemType.checkFacets(validatedInfo);
                }
                memberTypes[i] = (XSSimpleTypeDecl) validatedInfo.memberType;
                if (isUnion) {
                    itemTypes[i] = memberTypes[i].fBuiltInKind;
                }
            }
            ListData listData = new ListData(avalue2);
            validatedInfo.actualValue = listData;
            validatedInfo.actualValueType = isUnion ? (short) 43 : (short) 44;
            validatedInfo.memberType = null;
            validatedInfo.memberTypes = memberTypes;
            validatedInfo.itemValueTypes = new ShortListImpl(itemTypes, itemTypes.length);
            validatedInfo.normalizedValue = nvalue;
            validatedInfo.actualType = this;
            return listData;
        } else {
            Object _content;
            if (this.fMemberTypes.length <= 1 || content == null) {
                _content = content;
            } else {
                _content = content.toString();
            }
            i = 0;
            while (i < this.fMemberTypes.length) {
                try {
                    Object aValue = this.fMemberTypes[i].getActualValue(_content, context, validatedInfo, true);
                    if (!(!context.needFacetChecking() || this.fMemberTypes[i].fFacetsDefined == (short) 0 || this.fMemberTypes[i].fFacetsDefined == (short) 16)) {
                        this.fMemberTypes[i].checkFacets(validatedInfo);
                    }
                    validatedInfo.memberType = this.fMemberTypes[i];
                    validatedInfo.actualType = this;
                    return aValue;
                } catch (InvalidDatatypeValueException e) {
                    i++;
                }
            }
            StringBuffer typesBuffer = new StringBuffer();
            for (i = 0; i < this.fMemberTypes.length; i++) {
                if (i != 0) {
                    typesBuffer.append(" | ");
                }
                XSSimpleTypeDecl decl = this.fMemberTypes[i];
                if (decl.fTargetNamespace != null) {
                    typesBuffer.append('{');
                    typesBuffer.append(decl.fTargetNamespace);
                    typesBuffer.append('}');
                }
                typesBuffer.append(decl.fTypeName);
                if (decl.fEnumeration != null) {
                    typesBuffer.append(" : ");
                    decl.appendEnumString(typesBuffer);
                }
            }
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{content, this.fTypeName, typesBuffer.toString()});
        }
    }

    public boolean isEqual(Object value1, Object value2) {
        if (value1 == null) {
            return false;
        }
        return value1.equals(value2);
    }

    public boolean isIdentical(Object value1, Object value2) {
        if (value1 == null) {
            return false;
        }
        return this.fDVs[this.fValidationDV].isIdentical(value1, value2);
    }

    public static String normalize(String content, short ws) {
        int len = content == null ? 0 : content.length();
        if (len == 0 || ws == (short) 0) {
            return content;
        }
        StringBuffer sb = new StringBuffer();
        int i;
        char ch;
        if (ws == (short) 1) {
            for (i = 0; i < len; i++) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == '\r') {
                    sb.append(' ');
                } else {
                    sb.append(ch);
                }
            }
        } else {
            boolean isLeading = true;
            i = 0;
            while (i < len) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == '\r' || ch == ' ') {
                    while (i < len - 1) {
                        ch = content.charAt(i + 1);
                        if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                            break;
                        }
                        i++;
                    }
                    if (i < len - 1 && !isLeading) {
                        sb.append(' ');
                    }
                } else {
                    sb.append(ch);
                    isLeading = false;
                }
                i++;
            }
        }
        return sb.toString();
    }

    protected String normalize(Object content, short ws) {
        if (content == null) {
            return null;
        }
        if ((this.fFacetsDefined & 8) == 0) {
            short norm_type = fDVNormalizeType[this.fValidationDV];
            if (norm_type == (short) 0) {
                return content.toString();
            }
            if (norm_type == (short) 1) {
                return XMLChar.trim(content.toString());
            }
        }
        if (!(content instanceof StringBuffer)) {
            return normalize(content.toString(), ws);
        }
        StringBuffer sb = (StringBuffer) content;
        int len = sb.length();
        if (len == 0) {
            return "";
        }
        if (ws == (short) 0) {
            return sb.toString();
        }
        int i;
        char ch;
        if (ws == (short) 1) {
            for (i = 0; i < len; i++) {
                ch = sb.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == '\r') {
                    sb.setCharAt(i, ' ');
                }
            }
        } else {
            boolean isLeading = true;
            i = 0;
            int j = 0;
            while (i < len) {
                int j2;
                ch = sb.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == '\r' || ch == ' ') {
                    while (i < len - 1) {
                        ch = sb.charAt(i + 1);
                        if (ch != '\t' && ch != '\n' && ch != '\r' && ch != ' ') {
                            break;
                        }
                        i++;
                    }
                    if (i >= len - 1 || isLeading) {
                        j2 = j;
                    } else {
                        j2 = j + 1;
                        sb.setCharAt(j, ' ');
                    }
                } else {
                    j2 = j + 1;
                    sb.setCharAt(j, ch);
                    isLeading = false;
                }
                i++;
                j = j2;
            }
            sb.setLength(j);
        }
        return sb.toString();
    }

    void reportError(String key, Object[] args) throws InvalidDatatypeFacetException {
        throw new InvalidDatatypeFacetException(key, args);
    }

    private String whiteSpaceValue(short ws) {
        return WS_FACET_STRING[ws];
    }

    public short getOrdered() {
        return this.fOrdered;
    }

    public boolean getBounded() {
        return this.fBounded;
    }

    public boolean getFinite() {
        return this.fFinite;
    }

    public boolean getNumeric() {
        return this.fNumeric;
    }

    public boolean isDefinedFacet(short facetName) {
        if (this.fValidationDV == (short) 0 || this.fValidationDV == (short) 29) {
            return false;
        }
        if ((this.fFacetsDefined & facetName) != 0) {
            return true;
        }
        if (this.fPatternType != (short) 0) {
            if (facetName != (short) 8) {
                return false;
            }
            return true;
        } else if (this.fValidationDV != (short) 24) {
            return false;
        } else {
            if (facetName == (short) 8 || facetName == XSSimpleTypeDefinition.FACET_FRACTIONDIGITS) {
                return true;
            }
            return false;
        }
    }

    public short getDefinedFacets() {
        if (this.fValidationDV == (short) 0 || this.fValidationDV == (short) 29) {
            return (short) 0;
        }
        if (this.fPatternType != (short) 0) {
            return (short) (this.fFacetsDefined | 8);
        }
        if (this.fValidationDV == (short) 24) {
            return (short) ((this.fFacetsDefined | 8) | 1024);
        }
        return this.fFacetsDefined;
    }

    public boolean isFixedFacet(short facetName) {
        if ((this.fFixedFacet & facetName) != 0) {
            return true;
        }
        if (this.fValidationDV != (short) 24) {
            return false;
        }
        if (facetName != XSSimpleTypeDefinition.FACET_FRACTIONDIGITS) {
            return false;
        }
        return true;
    }

    public short getFixedFacets() {
        if (this.fValidationDV == (short) 24) {
            return (short) (this.fFixedFacet | 1024);
        }
        return this.fFixedFacet;
    }

    public String getLexicalFacetValue(short facetName) {
        switch (facetName) {
            case (short) 1:
                if (this.fLength != -1) {
                    return Integer.toString(this.fLength);
                }
                return null;
            case (short) 2:
                if (this.fMinLength != -1) {
                    return Integer.toString(this.fMinLength);
                }
                return null;
            case (short) 4:
                if (this.fMaxLength != -1) {
                    return Integer.toString(this.fMaxLength);
                }
                return null;
            case (short) 16:
                if (this.fValidationDV == (short) 0 || this.fValidationDV == (short) 29) {
                    return null;
                }
                return WS_FACET_STRING[this.fWhiteSpace];
            case (short) 32:
                if (this.fMaxInclusive != null) {
                    return this.fMaxInclusive.toString();
                }
                return null;
            case (short) 64:
                if (this.fMaxExclusive != null) {
                    return this.fMaxExclusive.toString();
                }
                return null;
            case (short) 128:
                if (this.fMinExclusive != null) {
                    return this.fMinExclusive.toString();
                }
                return null;
            case (short) 256:
                if (this.fMinInclusive != null) {
                    return this.fMinInclusive.toString();
                }
                return null;
            case (short) 512:
                if (this.fTotalDigits != -1) {
                    return Integer.toString(this.fTotalDigits);
                }
                return null;
            case (short) 1024:
                if (this.fValidationDV == (short) 24) {
                    return SchemaSymbols.ATTVAL_FALSE_0;
                }
                return this.fFractionDigits != -1 ? Integer.toString(this.fFractionDigits) : null;
            default:
                return null;
        }
    }

    public StringList getLexicalEnumeration() {
        if (this.fLexicalEnumeration == null) {
            if (this.fEnumeration == null) {
                return StringListImpl.EMPTY_LIST;
            }
            int size = this.fEnumerationSize;
            String[] strs = new String[size];
            for (int i = 0; i < size; i++) {
                strs[i] = this.fEnumeration[i].normalizedValue;
            }
            this.fLexicalEnumeration = new StringListImpl(strs, size);
        }
        return this.fLexicalEnumeration;
    }

    public ObjectList getActualEnumeration() {
        if (this.fActualEnumeration == null) {
            this.fActualEnumeration = new C46393();
        }
        return this.fActualEnumeration;
    }

    public ObjectList getEnumerationItemTypeList() {
        if (this.fEnumerationItemTypeList == null) {
            if (this.fEnumeration == null) {
                return null;
            }
            this.fEnumerationItemTypeList = new C46404();
        }
        return this.fEnumerationItemTypeList;
    }

    public ShortList getEnumerationTypeList() {
        if (this.fEnumerationTypeList == null) {
            if (this.fEnumeration == null) {
                return ShortListImpl.EMPTY_LIST;
            }
            short[] list = new short[this.fEnumerationSize];
            for (int i = 0; i < this.fEnumerationSize; i++) {
                list[i] = this.fEnumeration[i].actualValueType;
            }
            this.fEnumerationTypeList = new ShortListImpl(list, this.fEnumerationSize);
        }
        return this.fEnumerationTypeList;
    }

    public StringList getLexicalPattern() {
        if (this.fPatternType == (short) 0 && this.fValidationDV != (short) 24 && this.fPatternStr == null) {
            return StringListImpl.EMPTY_LIST;
        }
        if (this.fLexicalPattern == null) {
            String[] strs;
            int size = this.fPatternStr == null ? 0 : this.fPatternStr.size();
            if (this.fPatternType == (short) 1) {
                strs = new String[(size + 1)];
                strs[size] = "\\c+";
            } else if (this.fPatternType == (short) 2) {
                strs = new String[(size + 1)];
                strs[size] = "\\i\\c*";
            } else if (this.fPatternType == (short) 3) {
                strs = new String[(size + 2)];
                strs[size] = "\\i\\c*";
                strs[size + 1] = "[\\i-[:]][\\c-[:]]*";
            } else if (this.fValidationDV == (short) 24) {
                strs = new String[(size + 1)];
                strs[size] = "[\\-+]?[0-9]+";
            } else {
                strs = new String[size];
            }
            for (int i = 0; i < size; i++) {
                strs[i] = (String) this.fPatternStr.elementAt(i);
            }
            this.fLexicalPattern = new StringListImpl(strs, strs.length);
        }
        return this.fLexicalPattern;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    private void calcFundamentalFacets() {
        setOrdered();
        setNumeric();
        setBounded();
        setCardinality();
    }

    private void setOrdered() {
        if (this.fVariety == (short) 1) {
            this.fOrdered = this.fBase.fOrdered;
        } else if (this.fVariety == (short) 2) {
            this.fOrdered = (short) 0;
        } else if (this.fVariety != (short) 3) {
        } else {
            if (this.fMemberTypes.length == 0) {
                this.fOrdered = (short) 1;
                return;
            }
            boolean commonAnc;
            boolean allFalse;
            short ancestorId = getPrimitiveDV(this.fMemberTypes[0].fValidationDV);
            if (ancestorId != (short) 0) {
                commonAnc = true;
            } else {
                commonAnc = false;
            }
            if (this.fMemberTypes[0].fOrdered == (short) 0) {
                allFalse = true;
            } else {
                allFalse = false;
            }
            for (int i = 1; i < this.fMemberTypes.length && (commonAnc || allFalse); i++) {
                if (commonAnc) {
                    if (ancestorId == getPrimitiveDV(this.fMemberTypes[i].fValidationDV)) {
                        commonAnc = true;
                    } else {
                        commonAnc = false;
                    }
                }
                if (allFalse) {
                    if (this.fMemberTypes[i].fOrdered == (short) 0) {
                        allFalse = true;
                    } else {
                        allFalse = false;
                    }
                }
            }
            if (commonAnc) {
                this.fOrdered = this.fMemberTypes[0].fOrdered;
            } else if (allFalse) {
                this.fOrdered = (short) 0;
            } else {
                this.fOrdered = (short) 1;
            }
        }
    }

    private void setNumeric() {
        if (this.fVariety == (short) 1) {
            this.fNumeric = this.fBase.fNumeric;
        } else if (this.fVariety == (short) 2) {
            this.fNumeric = false;
        } else if (this.fVariety == (short) 3) {
            XSSimpleType[] memberTypes = this.fMemberTypes;
            int i = 0;
            while (i < memberTypes.length) {
                if (memberTypes[i].getNumeric()) {
                    i++;
                } else {
                    this.fNumeric = false;
                    return;
                }
            }
            this.fNumeric = true;
        }
    }

    private void setBounded() {
        if (this.fVariety == (short) 1) {
            if (((this.fFacetsDefined & 256) == 0 && (this.fFacetsDefined & 128) == 0) || ((this.fFacetsDefined & 32) == 0 && (this.fFacetsDefined & 64) == 0)) {
                this.fBounded = false;
            } else {
                this.fBounded = true;
            }
        } else if (this.fVariety == (short) 2) {
            if ((this.fFacetsDefined & 1) == 0 && ((this.fFacetsDefined & 2) == 0 || (this.fFacetsDefined & 4) == 0)) {
                this.fBounded = false;
            } else {
                this.fBounded = true;
            }
        } else if (this.fVariety == (short) 3) {
            XSSimpleTypeDecl[] memberTypes = this.fMemberTypes;
            short ancestorId = (short) 0;
            if (memberTypes.length > 0) {
                ancestorId = getPrimitiveDV(memberTypes[0].fValidationDV);
            }
            int i = 0;
            while (i < memberTypes.length) {
                if (memberTypes[i].getBounded() && ancestorId == getPrimitiveDV(memberTypes[i].fValidationDV)) {
                    i++;
                } else {
                    this.fBounded = false;
                    return;
                }
            }
            this.fBounded = true;
        }
    }

    private boolean specialCardinalityCheck() {
        if (this.fBase.fValidationDV == (short) 9 || this.fBase.fValidationDV == (short) 10 || this.fBase.fValidationDV == (short) 11 || this.fBase.fValidationDV == (short) 12 || this.fBase.fValidationDV == (short) 13 || this.fBase.fValidationDV == (short) 14) {
            return true;
        }
        return false;
    }

    private void setCardinality() {
        if (this.fVariety == (short) 1) {
            if (this.fBase.fFinite) {
                this.fFinite = true;
            } else if ((this.fFacetsDefined & 1) != 0 || (this.fFacetsDefined & 4) != 0 || (this.fFacetsDefined & 512) != 0) {
                this.fFinite = true;
            } else if (((this.fFacetsDefined & 256) == 0 && (this.fFacetsDefined & 128) == 0) || ((this.fFacetsDefined & 32) == 0 && (this.fFacetsDefined & 64) == 0)) {
                this.fFinite = false;
            } else if ((this.fFacetsDefined & 1024) != 0 || specialCardinalityCheck()) {
                this.fFinite = true;
            } else {
                this.fFinite = false;
            }
        } else if (this.fVariety == (short) 2) {
            if ((this.fFacetsDefined & 1) == 0 && ((this.fFacetsDefined & 2) == 0 || (this.fFacetsDefined & 4) == 0)) {
                this.fFinite = false;
            } else {
                this.fFinite = true;
            }
        } else if (this.fVariety == (short) 3) {
            XSSimpleType[] memberTypes = this.fMemberTypes;
            int i = 0;
            while (i < memberTypes.length) {
                if (memberTypes[i].getFinite()) {
                    i++;
                } else {
                    this.fFinite = false;
                    return;
                }
            }
            this.fFinite = true;
        }
    }

    private short getPrimitiveDV(short validationDV) {
        if (validationDV == (short) 21 || validationDV == (short) 22 || validationDV == (short) 23) {
            return (short) 1;
        }
        if (validationDV == (short) 24) {
            return (short) 3;
        }
        return validationDV;
    }

    public boolean derivedFromType(XSTypeDefinition ancestor, short derivation) {
        if (ancestor == null) {
            return false;
        }
        while (ancestor instanceof XSSimpleTypeDelegate) {
            ancestor = ((XSSimpleTypeDelegate) ancestor).type;
        }
        if (ancestor.getBaseType() == ancestor) {
            return true;
        }
        XSTypeDefinition type = this;
        while (type != ancestor && type != fAnySimpleType) {
            type = type.getBaseType();
        }
        if (type == ancestor) {
            return true;
        }
        return false;
    }

    public boolean derivedFrom(String ancestorNS, String ancestorName, short derivation) {
        if (ancestorName == null) {
            return false;
        }
        if ("http://www.w3.org/2001/XMLSchema".equals(ancestorNS) && "anyType".equals(ancestorName)) {
            return true;
        }
        XSTypeDefinition type = this;
        while (true) {
            if ((!ancestorName.equals(type.getName()) || (!(ancestorNS == null && type.getNamespace() == null) && (ancestorNS == null || !ancestorNS.equals(type.getNamespace())))) && type != fAnySimpleType) {
                type = type.getBaseType();
            }
        }
        if (type != fAnySimpleType) {
            return true;
        }
        return false;
    }

    public boolean isDOMDerivedFrom(String ancestorNS, String ancestorName, int derivationMethod) {
        if (ancestorName == null) {
            return false;
        }
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(ancestorNS) && "anyType".equals(ancestorName) && ((derivationMethod & 1) != 0 || derivationMethod == 0)) {
            return true;
        }
        if ((derivationMethod & 1) != 0 && isDerivedByRestriction(ancestorNS, ancestorName, this)) {
            return true;
        }
        if ((derivationMethod & 8) != 0 && isDerivedByList(ancestorNS, ancestorName, this)) {
            return true;
        }
        if ((derivationMethod & 4) != 0 && isDerivedByUnion(ancestorNS, ancestorName, this)) {
            return true;
        }
        if (((derivationMethod & 2) == 0 || (derivationMethod & 1) != 0 || (derivationMethod & 8) != 0 || (derivationMethod & 4) != 0) && (derivationMethod & 2) == 0 && (derivationMethod & 1) == 0 && (derivationMethod & 8) == 0 && (derivationMethod & 4) == 0) {
            return isDerivedByAny(ancestorNS, ancestorName, this);
        }
        return false;
    }

    private boolean isDerivedByAny(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        boolean derivedFrom = false;
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) {
                derivedFrom = true;
                break;
            } else if (isDerivedByRestriction(ancestorNS, ancestorName, type)) {
                return true;
            } else {
                if (isDerivedByList(ancestorNS, ancestorName, type)) {
                    return true;
                }
                if (isDerivedByUnion(ancestorNS, ancestorName, type)) {
                    return true;
                }
                oldType = type;
                if (((XSSimpleTypeDecl) type).getVariety() == (short) 0 || ((XSSimpleTypeDecl) type).getVariety() == (short) 1) {
                    type = type.getBaseType();
                } else if (((XSSimpleTypeDecl) type).getVariety() == (short) 3) {
                    if (0 < ((XSSimpleTypeDecl) type).getMemberTypes().getLength()) {
                        return isDerivedByAny(ancestorNS, ancestorName, (XSTypeDefinition) ((XSSimpleTypeDecl) type).getMemberTypes().item(0));
                    }
                } else if (((XSSimpleTypeDecl) type).getVariety() == (short) 2) {
                    type = ((XSSimpleTypeDecl) type).getItemType();
                }
            }
        }
        return derivedFrom;
    }

    private boolean isDerivedByRestriction(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS != null && ancestorNS.equals(type.getNamespace())) || (type.getNamespace() == null && ancestorNS == null))) {
                return true;
            }
            oldType = type;
            type = type.getBaseType();
        }
        return false;
    }

    private boolean isDerivedByList(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        if (type != null && ((XSSimpleTypeDefinition) type).getVariety() == (short) 2) {
            XSTypeDefinition itemType = ((XSSimpleTypeDefinition) type).getItemType();
            if (itemType != null && isDerivedByRestriction(ancestorNS, ancestorName, itemType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDerivedByUnion(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        if (type != null && ((XSSimpleTypeDefinition) type).getVariety() == (short) 3) {
            XSObjectList memberTypes = ((XSSimpleTypeDefinition) type).getMemberTypes();
            int i = 0;
            while (i < memberTypes.getLength()) {
                if (memberTypes.item(i) != null && isDerivedByRestriction(ancestorNS, ancestorName, (XSSimpleTypeDefinition) memberTypes.item(i))) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public void reset() {
        if (!this.fIsImmutable) {
            this.fItemType = null;
            this.fMemberTypes = null;
            this.fTypeName = null;
            this.fTargetNamespace = null;
            this.fFinalSet = (short) 0;
            this.fBase = null;
            this.fVariety = (short) -1;
            this.fValidationDV = (short) -1;
            this.fFacetsDefined = (short) 0;
            this.fFixedFacet = (short) 0;
            this.fWhiteSpace = (short) 0;
            this.fLength = -1;
            this.fMinLength = -1;
            this.fMaxLength = -1;
            this.fTotalDigits = -1;
            this.fFractionDigits = -1;
            this.fPattern = null;
            this.fPatternStr = null;
            this.fEnumeration = null;
            this.fLexicalPattern = null;
            this.fLexicalEnumeration = null;
            this.fActualEnumeration = null;
            this.fEnumerationTypeList = null;
            this.fEnumerationItemTypeList = null;
            this.fMaxInclusive = null;
            this.fMaxExclusive = null;
            this.fMinExclusive = null;
            this.fMinInclusive = null;
            this.lengthAnnotation = null;
            this.minLengthAnnotation = null;
            this.maxLengthAnnotation = null;
            this.whiteSpaceAnnotation = null;
            this.totalDigitsAnnotation = null;
            this.fractionDigitsAnnotation = null;
            this.patternAnnotations = null;
            this.enumerationAnnotations = null;
            this.maxInclusiveAnnotation = null;
            this.maxExclusiveAnnotation = null;
            this.minInclusiveAnnotation = null;
            this.minExclusiveAnnotation = null;
            this.fPatternType = (short) 0;
            this.fAnnotations = null;
            this.fFacets = null;
        }
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    public void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }

    public String toString() {
        return this.fTargetNamespace + "," + this.fTypeName;
    }

    public XSObjectList getFacets() {
        if (this.fFacets == null && (this.fFacetsDefined != (short) 0 || this.fValidationDV == (short) 24)) {
            XSFacetImpl[] facets = new XSFacetImpl[10];
            int count = 0;
            if (!((this.fFacetsDefined & 16) == 0 || this.fValidationDV == (short) 0 || this.fValidationDV == (short) 29)) {
                facets[0] = new XSFacetImpl((short) 16, WS_FACET_STRING[this.fWhiteSpace], 0, null, (this.fFixedFacet & 16) != 0, this.whiteSpaceAnnotation);
                count = 0 + 1;
            }
            if (this.fLength != -1) {
                facets[count] = new XSFacetImpl((short) 1, Integer.toString(this.fLength), this.fLength, null, (this.fFixedFacet & 1) != 0, this.lengthAnnotation);
                count++;
            }
            if (this.fMinLength != -1) {
                facets[count] = new XSFacetImpl((short) 2, Integer.toString(this.fMinLength), this.fMinLength, null, (this.fFixedFacet & 2) != 0, this.minLengthAnnotation);
                count++;
            }
            if (this.fMaxLength != -1) {
                facets[count] = new XSFacetImpl((short) 4, Integer.toString(this.fMaxLength), this.fMaxLength, null, (this.fFixedFacet & 4) != 0, this.maxLengthAnnotation);
                count++;
            }
            if (this.fTotalDigits != -1) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_TOTALDIGITS, Integer.toString(this.fTotalDigits), this.fTotalDigits, null, (this.fFixedFacet & 512) != 0, this.totalDigitsAnnotation);
                count++;
            }
            if (this.fValidationDV == (short) 24) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_FRACTIONDIGITS, SchemaSymbols.ATTVAL_FALSE_0, 0, null, true, this.fractionDigitsAnnotation);
                count++;
            } else if (this.fFractionDigits != -1) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_FRACTIONDIGITS, Integer.toString(this.fFractionDigits), this.fFractionDigits, null, (this.fFixedFacet & 1024) != 0, this.fractionDigitsAnnotation);
                count++;
            }
            if (this.fMaxInclusive != null) {
                facets[count] = new XSFacetImpl((short) 32, this.fMaxInclusive.toString(), 0, this.fMaxInclusive, (this.fFixedFacet & 32) != 0, this.maxInclusiveAnnotation);
                count++;
            }
            if (this.fMaxExclusive != null) {
                facets[count] = new XSFacetImpl((short) 64, this.fMaxExclusive.toString(), 0, this.fMaxExclusive, (this.fFixedFacet & 64) != 0, this.maxExclusiveAnnotation);
                count++;
            }
            if (this.fMinExclusive != null) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_MINEXCLUSIVE, this.fMinExclusive.toString(), 0, this.fMinExclusive, (this.fFixedFacet & 128) != 0, this.minExclusiveAnnotation);
                count++;
            }
            if (this.fMinInclusive != null) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_MININCLUSIVE, this.fMinInclusive.toString(), 0, this.fMinInclusive, (this.fFixedFacet & 256) != 0, this.minInclusiveAnnotation);
                count++;
            }
            this.fFacets = count > 0 ? new XSObjectListImpl(facets, count) : XSObjectListImpl.EMPTY_LIST;
        }
        if (this.fFacets != null) {
            return this.fFacets;
        }
        return XSObjectListImpl.EMPTY_LIST;
    }

    public XSObject getFacet(int facetType) {
        XSObjectList list;
        int i;
        if (facetType == 2048 || facetType == 8) {
            list = getMultiValueFacets();
            for (i = 0; i < list.getLength(); i++) {
                XSMultiValueFacet f = (XSMultiValueFacet) list.item(i);
                if (f.getFacetKind() == facetType) {
                    return f;
                }
            }
        } else {
            list = getFacets();
            for (i = 0; i < list.getLength(); i++) {
                XSFacet f2 = (XSFacet) list.item(i);
                if (f2.getFacetKind() == facetType) {
                    return f2;
                }
            }
        }
        return null;
    }

    public XSObjectList getMultiValueFacets() {
        if (this.fMultiValueFacets == null && !((this.fFacetsDefined & 2048) == 0 && (this.fFacetsDefined & 8) == 0 && this.fPatternType == (short) 0 && this.fValidationDV != (short) 24)) {
            XSMVFacetImpl[] facets = new XSMVFacetImpl[2];
            int count = 0;
            if (!((this.fFacetsDefined & 8) == 0 && this.fPatternType == (short) 0 && this.fValidationDV != (short) 24)) {
                facets[0] = new XSMVFacetImpl((short) 8, getLexicalPattern(), null, this.patternAnnotations);
                count = 0 + 1;
            }
            if (this.fEnumeration != null) {
                facets[count] = new XSMVFacetImpl(XSSimpleTypeDefinition.FACET_ENUMERATION, getLexicalEnumeration(), new ObjectListImpl(this.fEnumeration, this.fEnumerationSize), this.enumerationAnnotations);
                count++;
            }
            this.fMultiValueFacets = new XSObjectListImpl(facets, count);
        }
        return this.fMultiValueFacets != null ? this.fMultiValueFacets : XSObjectListImpl.EMPTY_LIST;
    }

    public Object getMinInclusiveValue() {
        return this.fMinInclusive;
    }

    public Object getMinExclusiveValue() {
        return this.fMinExclusive;
    }

    public Object getMaxInclusiveValue() {
        return this.fMaxInclusive;
    }

    public Object getMaxExclusiveValue() {
        return this.fMaxExclusive;
    }

    public void setAnonymous(boolean anon) {
        this.fAnonymous = anon;
    }

    public String getTypeNamespace() {
        return getNamespace();
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }

    private short convertToPrimitiveKind(short valueType) {
        if (valueType <= (short) 20) {
            return valueType;
        }
        if (valueType <= (short) 29) {
            return (short) 2;
        }
        if (valueType <= (short) 42) {
            return (short) 4;
        }
        return valueType;
    }

    private void appendEnumString(StringBuffer sb) {
        sb.append('[');
        for (int i = 0; i < this.fEnumerationSize; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.fEnumeration[i].actualValue);
        }
        sb.append(']');
    }
}
