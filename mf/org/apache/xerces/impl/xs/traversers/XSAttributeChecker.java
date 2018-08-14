package mf.org.apache.xerces.impl.xs.traversers;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAttributeDecl;
import mf.org.apache.xerces.impl.xs.XSGrammarBucket;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XIntPool;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;

public class XSAttributeChecker {
    public static final int ATTIDX_ABSTRACT;
    public static final int ATTIDX_AFORMDEFAULT;
    public static final int ATTIDX_BASE;
    public static final int ATTIDX_BLOCK;
    public static final int ATTIDX_BLOCKDEFAULT;
    private static int ATTIDX_COUNT = 0;
    public static final int ATTIDX_DEFAULT;
    public static final int ATTIDX_EFORMDEFAULT;
    public static final int ATTIDX_ENUMNSDECLS;
    public static final int ATTIDX_FINAL;
    public static final int ATTIDX_FINALDEFAULT;
    public static final int ATTIDX_FIXED;
    public static final int ATTIDX_FORM;
    public static final int ATTIDX_FROMDEFAULT;
    public static final int ATTIDX_ID;
    public static final int ATTIDX_ISRETURNED;
    public static final int ATTIDX_ITEMTYPE;
    public static final int ATTIDX_MAXOCCURS;
    public static final int ATTIDX_MEMBERTYPES;
    public static final int ATTIDX_MINOCCURS;
    public static final int ATTIDX_MIXED;
    public static final int ATTIDX_NAME;
    public static final int ATTIDX_NAMESPACE;
    public static final int ATTIDX_NAMESPACE_LIST;
    public static final int ATTIDX_NILLABLE;
    public static final int ATTIDX_NONSCHEMA;
    public static final int ATTIDX_PROCESSCONTENTS;
    public static final int ATTIDX_PUBLIC;
    public static final int ATTIDX_REF;
    public static final int ATTIDX_REFER;
    public static final int ATTIDX_SCHEMALOCATION;
    public static final int ATTIDX_SOURCE;
    public static final int ATTIDX_SUBSGROUP;
    public static final int ATTIDX_SYSTEM;
    public static final int ATTIDX_TARGETNAMESPACE;
    public static final int ATTIDX_TYPE;
    public static final int ATTIDX_USE;
    public static final int ATTIDX_VALUE;
    public static final int ATTIDX_VERSION;
    public static final int ATTIDX_XML_LANG;
    public static final int ATTIDX_XPATH;
    private static final String ATTRIBUTE_N = "attribute_n";
    private static final String ATTRIBUTE_R = "attribute_r";
    protected static final int DT_ANYURI = 0;
    protected static final int DT_BLOCK = -1;
    protected static final int DT_BLOCK1 = -2;
    protected static final int DT_BOOLEAN = -15;
    protected static final int DT_COUNT = 9;
    protected static final int DT_FINAL = -3;
    protected static final int DT_FINAL1 = -4;
    protected static final int DT_FINAL2 = -5;
    protected static final int DT_FORM = -6;
    protected static final int DT_ID = 1;
    protected static final int DT_LANGUAGE = 8;
    protected static final int DT_MAXOCCURS = -7;
    protected static final int DT_MAXOCCURS1 = -8;
    protected static final int DT_MEMBERTYPES = -9;
    protected static final int DT_MINOCCURS1 = -10;
    protected static final int DT_NAMESPACE = -11;
    protected static final int DT_NCNAME = 5;
    protected static final int DT_NONNEGINT = -16;
    protected static final int DT_POSINT = -17;
    protected static final int DT_PROCESSCONTENTS = -12;
    protected static final int DT_QNAME = 2;
    protected static final int DT_STRING = 3;
    protected static final int DT_TOKEN = 4;
    protected static final int DT_USE = -13;
    protected static final int DT_WHITESPACE = -14;
    protected static final int DT_XPATH = 6;
    protected static final int DT_XPATH1 = 7;
    private static final String ELEMENT_N = "element_n";
    private static final String ELEMENT_R = "element_r";
    static final int INC_POOL_SIZE = 10;
    static final int INIT_POOL_SIZE = 10;
    private static final XInt INT_ANY_ANY = fXIntPool.getXInt(1);
    private static final XInt INT_ANY_LAX = fXIntPool.getXInt(3);
    private static final XInt INT_ANY_LIST = fXIntPool.getXInt(3);
    private static final XInt INT_ANY_NOT = fXIntPool.getXInt(2);
    private static final XInt INT_ANY_SKIP = fXIntPool.getXInt(2);
    private static final XInt INT_ANY_STRICT = fXIntPool.getXInt(1);
    private static final XInt INT_EMPTY_SET = fXIntPool.getXInt(0);
    private static final XInt INT_QUALIFIED = fXIntPool.getXInt(1);
    private static final XInt INT_UNBOUNDED = fXIntPool.getXInt(-1);
    private static final XInt INT_UNQUALIFIED = fXIntPool.getXInt(0);
    private static final XInt INT_USE_OPTIONAL = fXIntPool.getXInt(0);
    private static final XInt INT_USE_PROHIBITED = fXIntPool.getXInt(2);
    private static final XInt INT_USE_REQUIRED = fXIntPool.getXInt(1);
    private static final XInt INT_WS_COLLAPSE = fXIntPool.getXInt(2);
    private static final XInt INT_WS_PRESERVE = fXIntPool.getXInt(0);
    private static final XInt INT_WS_REPLACE = fXIntPool.getXInt(1);
    private static final Hashtable fEleAttrsMapG = new Hashtable(29);
    private static final Hashtable fEleAttrsMapL = new Hashtable(79);
    private static final XSSimpleType[] fExtraDVs = new XSSimpleType[9];
    private static boolean[] fSeenTemp = new boolean[ATTIDX_COUNT];
    private static Object[] fTempArray = new Object[ATTIDX_COUNT];
    private static final XIntPool fXIntPool = new XIntPool();
    Object[][] fArrayPool = ((Object[][]) Array.newInstance(Object.class, new int[]{10, ATTIDX_COUNT}));
    protected Vector fNamespaceList = new Vector();
    protected Hashtable fNonSchemaAttrs = new Hashtable();
    int fPoolPos = 0;
    protected XSDHandler fSchemaHandler = null;
    protected boolean[] fSeen = new boolean[ATTIDX_COUNT];
    protected SymbolTable fSymbolTable = null;

    static {
        ATTIDX_COUNT = 0;
        int i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_ABSTRACT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_AFORMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_BASE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_BLOCK = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_BLOCKDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_DEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_EFORMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_FINAL = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_FINALDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_FIXED = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_FORM = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_ID = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_ITEMTYPE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_MAXOCCURS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_MEMBERTYPES = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_MINOCCURS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_MIXED = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_NAME = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_NAMESPACE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_NAMESPACE_LIST = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_NILLABLE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_NONSCHEMA = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_PROCESSCONTENTS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_PUBLIC = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_REF = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_REFER = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_SCHEMALOCATION = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_SOURCE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_SUBSGROUP = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_SYSTEM = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_TARGETNAMESPACE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_TYPE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_USE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_VALUE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_ENUMNSDECLS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_VERSION = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_XML_LANG = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_XPATH = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_FROMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + 1;
        ATTIDX_ISRETURNED = i;
        SchemaGrammar grammar = SchemaGrammar.SG_SchemaNS;
        fExtraDVs[0] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ANYURI);
        fExtraDVs[1] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ID);
        fExtraDVs[2] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
        fExtraDVs[3] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_STRING);
        fExtraDVs[4] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_TOKEN);
        fExtraDVs[5] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_NCNAME);
        fExtraDVs[6] = fExtraDVs[3];
        fExtraDVs[6] = fExtraDVs[3];
        fExtraDVs[8] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_LANGUAGE);
        int attCount = 0 + 1;
        int ATT_ABSTRACT_D = 0;
        int attCount2 = attCount + 1;
        int ATT_ATTRIBUTE_FD_D = attCount;
        attCount = attCount2 + 1;
        int ATT_BASE_R = attCount2;
        attCount2 = attCount + 1;
        int ATT_BASE_N = attCount;
        attCount = attCount2 + 1;
        int ATT_BLOCK_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_BLOCK1_N = attCount;
        attCount = attCount2 + 1;
        int ATT_BLOCK_D_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_DEFAULT_N = attCount;
        attCount = attCount2 + 1;
        int ATT_ELEMENT_FD_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_FINAL_N = attCount;
        attCount = attCount2 + 1;
        int ATT_FINAL1_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_FINAL_D_D = attCount;
        attCount = attCount2 + 1;
        int ATT_FIXED_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_FIXED_D = attCount;
        attCount = attCount2 + 1;
        int ATT_FORM_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_ID_N = attCount;
        attCount = attCount2 + 1;
        int ATT_ITEMTYPE_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_MAXOCCURS_D = attCount;
        attCount = attCount2 + 1;
        int ATT_MAXOCCURS1_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_MEMBER_T_N = attCount;
        attCount = attCount2 + 1;
        int ATT_MINOCCURS_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_MINOCCURS1_D = attCount;
        attCount = attCount2 + 1;
        int ATT_MIXED_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_MIXED_N = attCount;
        attCount = attCount2 + 1;
        int ATT_NAME_R = attCount2;
        attCount2 = attCount + 1;
        int ATT_NAMESPACE_D = attCount;
        attCount = attCount2 + 1;
        int ATT_NAMESPACE_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_NILLABLE_D = attCount;
        attCount = attCount2 + 1;
        int ATT_PROCESS_C_D = attCount2;
        attCount2 = attCount + 1;
        int ATT_PUBLIC_R = attCount;
        attCount = attCount2 + 1;
        int ATT_REF_R = attCount2;
        attCount2 = attCount + 1;
        int ATT_REFER_R = attCount;
        attCount = attCount2 + 1;
        int ATT_SCHEMA_L_R = attCount2;
        attCount2 = attCount + 1;
        int ATT_SCHEMA_L_N = attCount;
        attCount = attCount2 + 1;
        int ATT_SOURCE_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_SUBSTITUTION_G_N = attCount;
        attCount = attCount2 + 1;
        int ATT_SYSTEM_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_TARGET_N_N = attCount;
        attCount = attCount2 + 1;
        int ATT_TYPE_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_USE_D = attCount;
        attCount = attCount2 + 1;
        int ATT_VALUE_NNI_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_VALUE_PI_N = attCount;
        attCount = attCount2 + 1;
        int ATT_VALUE_STR_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_VALUE_WS_N = attCount;
        attCount = attCount2 + 1;
        int ATT_VERSION_N = attCount2;
        attCount2 = attCount + 1;
        int ATT_XML_LANG = attCount;
        attCount = attCount2 + 1;
        int ATT_XPATH_R = attCount2;
        int ATT_XPATH1_R = attCount;
        OneAttr[] allAttrs = new OneAttr[(attCount + 1)];
        allAttrs[ATT_ABSTRACT_D] = new OneAttr(SchemaSymbols.ATT_ABSTRACT, DT_BOOLEAN, ATTIDX_ABSTRACT, Boolean.FALSE);
        allAttrs[ATT_ATTRIBUTE_FD_D] = new OneAttr(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, DT_FORM, ATTIDX_AFORMDEFAULT, INT_UNQUALIFIED);
        allAttrs[ATT_BASE_R] = new OneAttr(SchemaSymbols.ATT_BASE, 2, ATTIDX_BASE, null);
        allAttrs[ATT_BASE_N] = new OneAttr(SchemaSymbols.ATT_BASE, 2, ATTIDX_BASE, null);
        allAttrs[ATT_BLOCK_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, -1, ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK1_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, -2, ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK_D_D] = new OneAttr(SchemaSymbols.ATT_BLOCKDEFAULT, -1, ATTIDX_BLOCKDEFAULT, INT_EMPTY_SET);
        allAttrs[ATT_DEFAULT_N] = new OneAttr(SchemaSymbols.ATT_DEFAULT, 3, ATTIDX_DEFAULT, null);
        allAttrs[ATT_ELEMENT_FD_D] = new OneAttr(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, DT_FORM, ATTIDX_EFORMDEFAULT, INT_UNQUALIFIED);
        allAttrs[ATT_FINAL_N] = new OneAttr(SchemaSymbols.ATT_FINAL, -3, ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL1_N] = new OneAttr(SchemaSymbols.ATT_FINAL, -4, ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL_D_D] = new OneAttr(SchemaSymbols.ATT_FINALDEFAULT, -5, ATTIDX_FINALDEFAULT, INT_EMPTY_SET);
        allAttrs[ATT_FIXED_N] = new OneAttr(SchemaSymbols.ATT_FIXED, 3, ATTIDX_FIXED, null);
        allAttrs[ATT_FIXED_D] = new OneAttr(SchemaSymbols.ATT_FIXED, DT_BOOLEAN, ATTIDX_FIXED, Boolean.FALSE);
        allAttrs[ATT_FORM_N] = new OneAttr(SchemaSymbols.ATT_FORM, DT_FORM, ATTIDX_FORM, null);
        allAttrs[ATT_ID_N] = new OneAttr(SchemaSymbols.ATT_ID, 1, ATTIDX_ID, null);
        allAttrs[ATT_ITEMTYPE_N] = new OneAttr(SchemaSymbols.ATT_ITEMTYPE, 2, ATTIDX_ITEMTYPE, null);
        allAttrs[ATT_MAXOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, DT_MAXOCCURS, ATTIDX_MAXOCCURS, fXIntPool.getXInt(1));
        allAttrs[ATT_MAXOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, DT_MAXOCCURS1, ATTIDX_MAXOCCURS, fXIntPool.getXInt(1));
        allAttrs[ATT_MEMBER_T_N] = new OneAttr(SchemaSymbols.ATT_MEMBERTYPES, DT_MEMBERTYPES, ATTIDX_MEMBERTYPES, null);
        allAttrs[ATT_MINOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, DT_NONNEGINT, ATTIDX_MINOCCURS, fXIntPool.getXInt(1));
        allAttrs[ATT_MINOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, -10, ATTIDX_MINOCCURS, fXIntPool.getXInt(1));
        allAttrs[ATT_MIXED_D] = new OneAttr(SchemaSymbols.ATT_MIXED, DT_BOOLEAN, ATTIDX_MIXED, Boolean.FALSE);
        allAttrs[ATT_MIXED_N] = new OneAttr(SchemaSymbols.ATT_MIXED, DT_BOOLEAN, ATTIDX_MIXED, null);
        allAttrs[ATT_NAME_R] = new OneAttr(SchemaSymbols.ATT_NAME, 5, ATTIDX_NAME, null);
        allAttrs[ATT_NAMESPACE_D] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, DT_NAMESPACE, ATTIDX_NAMESPACE, INT_ANY_ANY);
        allAttrs[ATT_NAMESPACE_N] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, 0, ATTIDX_NAMESPACE, null);
        allAttrs[ATT_NILLABLE_D] = new OneAttr(SchemaSymbols.ATT_NILLABLE, DT_BOOLEAN, ATTIDX_NILLABLE, Boolean.FALSE);
        allAttrs[ATT_PROCESS_C_D] = new OneAttr(SchemaSymbols.ATT_PROCESSCONTENTS, DT_PROCESSCONTENTS, ATTIDX_PROCESSCONTENTS, INT_ANY_STRICT);
        allAttrs[ATT_PUBLIC_R] = new OneAttr(SchemaSymbols.ATT_PUBLIC, 4, ATTIDX_PUBLIC, null);
        allAttrs[ATT_REF_R] = new OneAttr(SchemaSymbols.ATT_REF, 2, ATTIDX_REF, null);
        allAttrs[ATT_REFER_R] = new OneAttr(SchemaSymbols.ATT_REFER, 2, ATTIDX_REFER, null);
        allAttrs[ATT_SCHEMA_L_R] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SCHEMA_L_N] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, 0, ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SOURCE_N] = new OneAttr(SchemaSymbols.ATT_SOURCE, 0, ATTIDX_SOURCE, null);
        allAttrs[ATT_SUBSTITUTION_G_N] = new OneAttr(SchemaSymbols.ATT_SUBSTITUTIONGROUP, 2, ATTIDX_SUBSGROUP, null);
        allAttrs[ATT_SYSTEM_N] = new OneAttr(SchemaSymbols.ATT_SYSTEM, 0, ATTIDX_SYSTEM, null);
        allAttrs[ATT_TARGET_N_N] = new OneAttr(SchemaSymbols.ATT_TARGETNAMESPACE, 0, ATTIDX_TARGETNAMESPACE, null);
        allAttrs[ATT_TYPE_N] = new OneAttr(SchemaSymbols.ATT_TYPE, 2, ATTIDX_TYPE, null);
        allAttrs[ATT_USE_D] = new OneAttr(SchemaSymbols.ATT_USE, DT_USE, ATTIDX_USE, INT_USE_OPTIONAL);
        allAttrs[ATT_VALUE_NNI_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_NONNEGINT, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_PI_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_POSINT, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_STR_N] = new OneAttr(SchemaSymbols.ATT_VALUE, 3, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_WS_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_WHITESPACE, ATTIDX_VALUE, null);
        allAttrs[ATT_VERSION_N] = new OneAttr(SchemaSymbols.ATT_VERSION, 4, ATTIDX_VERSION, null);
        allAttrs[ATT_XML_LANG] = new OneAttr(SchemaSymbols.ATT_XML_LANG, 8, ATTIDX_XML_LANG, null);
        allAttrs[ATT_XPATH_R] = new OneAttr(SchemaSymbols.ATT_XPATH, 6, ATTIDX_XPATH, null);
        allAttrs[ATT_XPATH1_R] = new OneAttr(SchemaSymbols.ATT_XPATH, 7, ATTIDX_XPATH, null);
        Container attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTE, attrList);
        attrList = Container.getContainer(7);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        fEleAttrsMapL.put(ATTRIBUTE_N, attrList);
        attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        fEleAttrsMapL.put(ATTRIBUTE_R, attrList);
        attrList = Container.getContainer(10);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_SUBSTITUTIONGROUP, allAttrs[ATT_SUBSTITUTION_G_N]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ELEMENT, attrList);
        attrList = Container.getContainer(10);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapL.put(ELEMENT_N, attrList);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(ELEMENT_R, attrList);
        attrList = Container.getContainer(6);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK1_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_COMPLEXTYPE, attrList);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_PUBLIC, allAttrs[ATT_PUBLIC_R]);
        attrList.put(SchemaSymbols.ATT_SYSTEM, allAttrs[ATT_SYSTEM_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_NOTATION, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXTYPE, attrList);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLECONTENT, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_RESTRICTION, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_R]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_EXTENSION, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANYATTRIBUTE, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXCONTENT, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_GROUP, attrList);
        attrList = Container.getContainer(4);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_GROUP, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS1_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS1_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ALL, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_CHOICE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SEQUENCE, attrList);
        attrList = Container.getContainer(5);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANY, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_UNIQUE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_KEY, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_REFER, allAttrs[ATT_REFER_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_KEYREF, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SELECTOR, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH1_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_FIELD, attrList);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ANNOTATION, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANNOTATION, attrList);
        attrList = Container.getContainer(1);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_APPINFO, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_APPINFO, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        attrList.put(SchemaSymbols.ATT_XML_LANG, allAttrs[ATT_XML_LANG]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_DOCUMENTATION, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_DOCUMENTATION, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_SIMPLETYPE, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLETYPE, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_ITEMTYPE, allAttrs[ATT_ITEMTYPE_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_LIST, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MEMBERTYPES, allAttrs[ATT_MEMBER_T_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_UNION, attrList);
        attrList = Container.getContainer(8);
        attrList.put(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, allAttrs[ATT_ATTRIBUTE_FD_D]);
        attrList.put(SchemaSymbols.ATT_BLOCKDEFAULT, allAttrs[ATT_BLOCK_D_D]);
        attrList.put(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, allAttrs[ATT_ELEMENT_FD_D]);
        attrList.put(SchemaSymbols.ATT_FINALDEFAULT, allAttrs[ATT_FINAL_D_D]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_TARGETNAMESPACE, allAttrs[ATT_TARGET_N_N]);
        attrList.put(SchemaSymbols.ATT_VERSION, allAttrs[ATT_VERSION_N]);
        attrList.put(SchemaSymbols.ATT_XML_LANG, allAttrs[ATT_XML_LANG]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_SCHEMA, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_INCLUDE, attrList);
        fEleAttrsMapG.put(SchemaSymbols.ELT_REDEFINE, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_IMPORT, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_NNI_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_LENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MINLENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXLENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_FRACTIONDIGITS, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_PI_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_TOTALDIGITS, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_PATTERN, attrList);
        attrList = Container.getContainer(2);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ENUMERATION, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_WS_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_WHITESPACE, attrList);
        attrList = Container.getContainer(3);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXINCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXEXCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MININCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MINEXCLUSIVE, attrList);
    }

    public XSAttributeChecker(XSDHandler schemaHandler) {
        this.fSchemaHandler = schemaHandler;
    }

    public void reset(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fNonSchemaAttrs.clear();
    }

    public Object[] checkAttributes(Element element, boolean isGlobal, XSDocumentInfo schemaDoc) {
        return checkAttributes(element, isGlobal, schemaDoc, false);
    }

    public Object[] checkAttributes(Element element, boolean isGlobal, XSDocumentInfo schemaDoc, boolean enumAsQName) {
        if (element == null) {
            return null;
        }
        Attr[] attrs = DOMUtil.getAttrs(element);
        resolveNamespace(element, attrs, schemaDoc.fNamespaceSupport);
        String uri = DOMUtil.getNamespaceURI(element);
        String elName = DOMUtil.getLocalName(element);
        if (!SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(uri)) {
            reportSchemaError("s4s-elt-schema-ns", new Object[]{elName}, element);
        }
        Hashtable eleAttrsMap = fEleAttrsMapG;
        String lookupName = elName;
        if (!isGlobal) {
            eleAttrsMap = fEleAttrsMapL;
            if (elName.equals(SchemaSymbols.ELT_ELEMENT)) {
                lookupName = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ELEMENT_R : ELEMENT_N;
            } else if (elName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                lookupName = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ATTRIBUTE_R : ATTRIBUTE_N;
            }
        }
        Container attrList = (Container) eleAttrsMap.get(lookupName);
        if (attrList == null) {
            reportSchemaError("s4s-elt-invalid", new Object[]{elName}, element);
            return null;
        }
        Object[] attrValues = getAvailableArray();
        long fromDefault = 0;
        System.arraycopy(fSeenTemp, 0, this.fSeen, 0, ATTIDX_COUNT);
        int length = attrs.length;
        int i = 0;
        while (i < length) {
            OneAttr oneAttr;
            Attr sattr = attrs[i];
            String attrName = sattr.getName();
            String attrURI = DOMUtil.getNamespaceURI(sattr);
            String attrVal = DOMUtil.getValue(sattr);
            if (attrName.startsWith("xml")) {
                if (!(XMLConstants.XMLNS_ATTRIBUTE.equals(DOMUtil.getPrefix(sattr)) || XMLConstants.XMLNS_ATTRIBUTE.equals(attrName))) {
                    if (SchemaSymbols.ATT_XML_LANG.equals(attrName) && (SchemaSymbols.ELT_SCHEMA.equals(elName) || SchemaSymbols.ELT_DOCUMENTATION.equals(elName))) {
                        attrURI = null;
                    }
                }
                i++;
            }
            if (attrURI == null || attrURI.length() == 0) {
                oneAttr = attrList.get(attrName);
                if (oneAttr == null) {
                    reportSchemaError("s4s-att-not-allowed", new Object[]{elName, attrName}, element);
                } else {
                    this.fSeen[oneAttr.valueIndex] = true;
                    try {
                        if (oneAttr.dvIndex < 0) {
                            attrValues[oneAttr.valueIndex] = validate(attrValues, attrName, attrVal, oneAttr.dvIndex, schemaDoc);
                            attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                        } else if (oneAttr.dvIndex == 3 || oneAttr.dvIndex == 6 || oneAttr.dvIndex == 7) {
                            attrValues[oneAttr.valueIndex] = attrVal;
                            if (elName.equals(SchemaSymbols.ELT_ENUMERATION) && enumAsQName) {
                                attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                            }
                        } else {
                            Object avalue = fExtraDVs[oneAttr.dvIndex].validate(attrVal, schemaDoc.fValidationContext, null);
                            if (oneAttr.dvIndex == 2) {
                                QName qname = (QName) avalue;
                                if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                                    qname.uri = schemaDoc.fTargetNamespace;
                                }
                            }
                            attrValues[oneAttr.valueIndex] = avalue;
                            attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                        }
                    } catch (InvalidDatatypeValueException ide) {
                        reportSchemaError("s4s-att-invalid-value", new Object[]{elName, attrName, ide.getMessage()}, element);
                        if (oneAttr.dfltValue != null) {
                            attrValues[oneAttr.valueIndex] = oneAttr.dfltValue;
                        }
                    }
                }
                i++;
            } else {
                if (attrURI.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
                    reportSchemaError("s4s-att-not-allowed", new Object[]{elName, attrName}, element);
                } else {
                    if (attrValues[ATTIDX_NONSCHEMA] == null) {
                        attrValues[ATTIDX_NONSCHEMA] = new Vector(4, 2);
                    }
                    ((Vector) attrValues[ATTIDX_NONSCHEMA]).addElement(attrName);
                    ((Vector) attrValues[ATTIDX_NONSCHEMA]).addElement(attrVal);
                }
                i++;
            }
        }
        OneAttr[] reqAttrs = attrList.values;
        for (OneAttr oneAttr2 : reqAttrs) {
            if (!(oneAttr2.dfltValue == null || this.fSeen[oneAttr2.valueIndex])) {
                attrValues[oneAttr2.valueIndex] = oneAttr2.dfltValue;
                fromDefault |= (long) (1 << oneAttr2.valueIndex);
            }
        }
        attrValues[ATTIDX_FROMDEFAULT] = new Long(fromDefault);
        if (attrValues[ATTIDX_MAXOCCURS] == null) {
            return attrValues;
        }
        int min = ((XInt) attrValues[ATTIDX_MINOCCURS]).intValue();
        int max = ((XInt) attrValues[ATTIDX_MAXOCCURS]).intValue();
        if (max == -1 || min <= max) {
            return attrValues;
        }
        reportSchemaError("p-props-correct.2.1", new Object[]{elName, attrValues[ATTIDX_MINOCCURS], attrValues[ATTIDX_MAXOCCURS]}, element);
        attrValues[ATTIDX_MINOCCURS] = attrValues[ATTIDX_MAXOCCURS];
        return attrValues;
    }

    private Object validate(Object[] attrValues, String attr, String ivalue, int dvIndex, XSDocumentInfo schemaDoc) throws InvalidDatatypeValueException {
        if (ivalue == null) {
            return null;
        }
        String value = XMLChar.trim(ivalue);
        Object retValue;
        String token;
        StringTokenizer t;
        int choice;
        switch (dvIndex) {
            case DT_POSINT /*-17*/:
                try {
                    if (value.length() > 0 && value.charAt(0) == '+') {
                        value = value.substring(1);
                    }
                    retValue = fXIntPool.getXInt(Integer.parseInt(value));
                    if (((XInt) retValue).intValue() > 0) {
                        return retValue;
                    }
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{value, SchemaSymbols.ATTVAL_POSITIVEINTEGER});
                } catch (NumberFormatException e) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{value, SchemaSymbols.ATTVAL_POSITIVEINTEGER});
                }
            case DT_NONNEGINT /*-16*/:
                try {
                    if (value.length() > 0 && value.charAt(0) == '+') {
                        value = value.substring(1);
                    }
                    retValue = fXIntPool.getXInt(Integer.parseInt(value));
                    if (((XInt) retValue).intValue() >= 0) {
                        return retValue;
                    }
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{value, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER});
                } catch (NumberFormatException e2) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{value, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER});
                }
            case DT_BOOLEAN /*-15*/:
                if (value.equals(SchemaSymbols.ATTVAL_FALSE) || value.equals(SchemaSymbols.ATTVAL_FALSE_0)) {
                    return Boolean.FALSE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_TRUE) || value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return Boolean.TRUE;
                }
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{value, SchemaSymbols.ATTVAL_BOOLEAN});
            case DT_WHITESPACE /*-14*/:
                if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                    return INT_WS_PRESERVE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_REPLACE)) {
                    return INT_WS_REPLACE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_COLLAPSE)) {
                    return INT_WS_COLLAPSE;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(preserve | replace | collapse)"});
            case DT_USE /*-13*/:
                if (value.equals(SchemaSymbols.ATTVAL_OPTIONAL)) {
                    return INT_USE_OPTIONAL;
                }
                if (value.equals(SchemaSymbols.ATTVAL_REQUIRED)) {
                    return INT_USE_REQUIRED;
                }
                if (value.equals(SchemaSymbols.ATTVAL_PROHIBITED)) {
                    return INT_USE_PROHIBITED;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(optional | prohibited | required)"});
            case DT_PROCESSCONTENTS /*-12*/:
                if (value.equals(SchemaSymbols.ATTVAL_STRICT)) {
                    return INT_ANY_STRICT;
                }
                if (value.equals(SchemaSymbols.ATTVAL_LAX)) {
                    return INT_ANY_LAX;
                }
                if (value.equals("skip")) {
                    return INT_ANY_SKIP;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(lax | skip | strict)"});
            case DT_NAMESPACE /*-11*/:
                if (value.equals(SchemaSymbols.ATTVAL_TWOPOUNDANY)) {
                    return INT_ANY_ANY;
                }
                if (value.equals(SchemaSymbols.ATTVAL_TWOPOUNDOTHER)) {
                    retValue = INT_ANY_NOT;
                    attrValues[ATTIDX_NAMESPACE_LIST] = new String[]{schemaDoc.fTargetNamespace, null};
                    return retValue;
                }
                retValue = INT_ANY_LIST;
                this.fNamespaceList.removeAllElements();
                StringTokenizer stringTokenizer = new StringTokenizer(value, " \n\t\r");
                while (stringTokenizer.hasMoreTokens()) {
                    try {
                        String tempNamespace;
                        token = stringTokenizer.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL)) {
                            tempNamespace = null;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_TWOPOUNDTARGETNS)) {
                                tempNamespace = schemaDoc.fTargetNamespace;
                            } else {
                                fExtraDVs[0].validate(token, schemaDoc.fValidationContext, null);
                                tempNamespace = this.fSymbolTable.addSymbol(token);
                            }
                        }
                        if (!this.fNamespaceList.contains(tempNamespace)) {
                            this.fNamespaceList.addElement(tempNamespace);
                        }
                    } catch (InvalidDatatypeValueException e3) {
                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "((##any | ##other) | List of (anyURI | (##targetNamespace | ##local)) )"});
                    }
                }
                String[] list = new String[this.fNamespaceList.size()];
                this.fNamespaceList.copyInto(list);
                attrValues[ATTIDX_NAMESPACE_LIST] = list;
                return retValue;
            case -10:
                if (value.equals(SchemaSymbols.ATTVAL_FALSE_0)) {
                    return fXIntPool.getXInt(0);
                }
                if (value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return fXIntPool.getXInt(1);
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(0 | 1)"});
            case DT_MEMBERTYPES /*-9*/:
                Vector memberType = new Vector();
                try {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        QName qname = (QName) fExtraDVs[2].validate(token, schemaDoc.fValidationContext, null);
                        if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                            qname.uri = schemaDoc.fTargetNamespace;
                        }
                        memberType.addElement(qname);
                    }
                    return memberType;
                } catch (InvalidDatatypeValueException e4) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", new Object[]{value, "(List of QName)"});
                }
            case DT_MAXOCCURS1 /*-8*/:
                if (value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return fXIntPool.getXInt(1);
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(1)"});
            case DT_MAXOCCURS /*-7*/:
                if (value.equals(SchemaSymbols.ATTVAL_UNBOUNDED)) {
                    return INT_UNBOUNDED;
                }
                try {
                    return validate(attrValues, attr, value, DT_NONNEGINT, schemaDoc);
                } catch (NumberFormatException e5) {
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "(nonNegativeInteger | unbounded)"});
                }
            case DT_FORM /*-6*/:
                if (value.equals(SchemaSymbols.ATTVAL_QUALIFIED)) {
                    return INT_QUALIFIED;
                }
                if (value.equals(SchemaSymbols.ATTVAL_UNQUALIFIED)) {
                    return INT_UNQUALIFIED;
                }
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", new Object[]{value, "(qualified | unqualified)"});
            case -5:
                choice = 0;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= 1;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= 2;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_LIST)) {
                                    choice |= 16;
                                } else {
                                    if (token.equals(SchemaSymbols.ATTVAL_UNION)) {
                                        choice |= 8;
                                    } else {
                                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "(#all | List of (extension | restriction | list | union))"});
                                    }
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case -4:
                choice = 0;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_LIST)) {
                            choice |= 16;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_UNION)) {
                                choice |= 8;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                    choice |= 2;
                                } else {
                                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "(#all | List of (list | union | restriction))"});
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case -3:
            case -2:
                choice = 0;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= 1;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= 2;
                            } else {
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "(#all | List of (extension | restriction))"});
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case -1:
                choice = 0;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= 1;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= 2;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_SUBSTITUTION)) {
                                    choice |= 4;
                                } else {
                                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", new Object[]{value, "(#all | List of (extension | restriction | substitution))"});
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            default:
                return null;
        }
    }

    void reportSchemaError(String key, Object[] args, Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }

    public void checkNonSchemaAttributes(XSGrammarBucket grammarBucket) {
        for (Entry entry : this.fNonSchemaAttrs.entrySet()) {
            String attrRName = (String) entry.getKey();
            String attrURI = attrRName.substring(0, attrRName.indexOf(44));
            String attrLocal = attrRName.substring(attrRName.indexOf(44) + 1);
            SchemaGrammar sGrammar = grammarBucket.getGrammar(attrURI);
            if (sGrammar != null) {
                XSAttributeDecl attrDecl = sGrammar.getGlobalAttributeDecl(attrLocal);
                if (attrDecl != null) {
                    XSSimpleType dv = (XSSimpleType) attrDecl.getTypeDefinition();
                    if (dv != null) {
                        Vector values = (Vector) entry.getValue();
                        String attrName = (String) values.elementAt(0);
                        int count = values.size();
                        for (int i = 1; i < count; i += 2) {
                            String elName = (String) values.elementAt(i);
                            try {
                                dv.validate((String) values.elementAt(i + 1), null, null);
                            } catch (InvalidDatatypeValueException ide) {
                                reportSchemaError("s4s-att-invalid-value", new Object[]{elName, attrName, ide.getMessage()}, null);
                            }
                        }
                    }
                }
            }
        }
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

    protected Object[] getAvailableArray() {
        if (this.fArrayPool.length == this.fPoolPos) {
            this.fArrayPool = new Object[(this.fPoolPos + 10)][];
            for (int i = this.fPoolPos; i < this.fArrayPool.length; i++) {
                this.fArrayPool[i] = new Object[ATTIDX_COUNT];
            }
        }
        Object[] retArray = this.fArrayPool[this.fPoolPos];
        Object[][] objArr = this.fArrayPool;
        int i2 = this.fPoolPos;
        this.fPoolPos = i2 + 1;
        objArr[i2] = null;
        System.arraycopy(fTempArray, 0, retArray, 0, ATTIDX_COUNT - 1);
        retArray[ATTIDX_ISRETURNED] = Boolean.FALSE;
        return retArray;
    }

    public void returnAttrArray(Object[] attrArray, XSDocumentInfo schemaDoc) {
        if (schemaDoc != null) {
            schemaDoc.fNamespaceSupport.popContext();
        }
        if (this.fPoolPos != 0 && attrArray != null && attrArray.length == ATTIDX_COUNT && !((Boolean) attrArray[ATTIDX_ISRETURNED]).booleanValue()) {
            attrArray[ATTIDX_ISRETURNED] = Boolean.TRUE;
            if (attrArray[ATTIDX_NONSCHEMA] != null) {
                ((Vector) attrArray[ATTIDX_NONSCHEMA]).clear();
            }
            Object[][] objArr = this.fArrayPool;
            int i = this.fPoolPos - 1;
            this.fPoolPos = i;
            objArr[i] = attrArray;
        }
    }

    public void resolveNamespace(Element element, Attr[] attrs, SchemaNamespaceSupport nsSupport) {
        nsSupport.pushContext();
        for (Attr sattr : attrs) {
            String rawname = DOMUtil.getName(sattr);
            String prefix = null;
            if (rawname.equals(XMLSymbols.PREFIX_XMLNS)) {
                prefix = XMLSymbols.EMPTY_STRING;
            } else if (rawname.startsWith("xmlns:")) {
                prefix = this.fSymbolTable.addSymbol(DOMUtil.getLocalName(sattr));
            }
            if (prefix != null) {
                String uri = this.fSymbolTable.addSymbol(DOMUtil.getValue(sattr));
                if (uri.length() == 0) {
                    uri = null;
                }
                nsSupport.declarePrefix(prefix, uri);
            }
        }
    }
}
