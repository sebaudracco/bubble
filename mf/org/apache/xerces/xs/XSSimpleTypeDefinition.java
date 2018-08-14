package mf.org.apache.xerces.xs;

public interface XSSimpleTypeDefinition extends XSTypeDefinition {
    public static final short FACET_ENUMERATION = (short) 2048;
    public static final short FACET_FRACTIONDIGITS = (short) 1024;
    public static final short FACET_LENGTH = (short) 1;
    public static final short FACET_MAXEXCLUSIVE = (short) 64;
    public static final short FACET_MAXINCLUSIVE = (short) 32;
    public static final short FACET_MAXLENGTH = (short) 4;
    public static final short FACET_MINEXCLUSIVE = (short) 128;
    public static final short FACET_MININCLUSIVE = (short) 256;
    public static final short FACET_MINLENGTH = (short) 2;
    public static final short FACET_NONE = (short) 0;
    public static final short FACET_PATTERN = (short) 8;
    public static final short FACET_TOTALDIGITS = (short) 512;
    public static final short FACET_WHITESPACE = (short) 16;
    public static final short ORDERED_FALSE = (short) 0;
    public static final short ORDERED_PARTIAL = (short) 1;
    public static final short ORDERED_TOTAL = (short) 2;
    public static final short VARIETY_ABSENT = (short) 0;
    public static final short VARIETY_ATOMIC = (short) 1;
    public static final short VARIETY_LIST = (short) 2;
    public static final short VARIETY_UNION = (short) 3;

    XSObjectList getAnnotations();

    boolean getBounded();

    short getBuiltInKind();

    short getDefinedFacets();

    XSObject getFacet(int i);

    XSObjectList getFacets();

    boolean getFinite();

    short getFixedFacets();

    XSSimpleTypeDefinition getItemType();

    StringList getLexicalEnumeration();

    String getLexicalFacetValue(short s);

    StringList getLexicalPattern();

    XSObjectList getMemberTypes();

    XSObjectList getMultiValueFacets();

    boolean getNumeric();

    short getOrdered();

    XSSimpleTypeDefinition getPrimitiveType();

    short getVariety();

    boolean isDefinedFacet(short s);

    boolean isFixedFacet(short s);
}
