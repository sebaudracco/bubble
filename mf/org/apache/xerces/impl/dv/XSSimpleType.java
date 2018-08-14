package mf.org.apache.xerces.impl.dv;

import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;

public interface XSSimpleType extends XSSimpleTypeDefinition {
    public static final short PRIMITIVE_ANYURI = (short) 17;
    public static final short PRIMITIVE_BASE64BINARY = (short) 16;
    public static final short PRIMITIVE_BOOLEAN = (short) 2;
    public static final short PRIMITIVE_DATE = (short) 9;
    public static final short PRIMITIVE_DATETIME = (short) 7;
    public static final short PRIMITIVE_DECIMAL = (short) 3;
    public static final short PRIMITIVE_DOUBLE = (short) 5;
    public static final short PRIMITIVE_DURATION = (short) 6;
    public static final short PRIMITIVE_FLOAT = (short) 4;
    public static final short PRIMITIVE_GDAY = (short) 13;
    public static final short PRIMITIVE_GMONTH = (short) 14;
    public static final short PRIMITIVE_GMONTHDAY = (short) 12;
    public static final short PRIMITIVE_GYEAR = (short) 11;
    public static final short PRIMITIVE_GYEARMONTH = (short) 10;
    public static final short PRIMITIVE_HEXBINARY = (short) 15;
    public static final short PRIMITIVE_NOTATION = (short) 20;
    public static final short PRIMITIVE_PRECISIONDECIMAL = (short) 19;
    public static final short PRIMITIVE_QNAME = (short) 18;
    public static final short PRIMITIVE_STRING = (short) 1;
    public static final short PRIMITIVE_TIME = (short) 8;
    public static final short WS_COLLAPSE = (short) 2;
    public static final short WS_PRESERVE = (short) 0;
    public static final short WS_REPLACE = (short) 1;

    void applyFacets(XSFacets xSFacets, short s, short s2, ValidationContext validationContext) throws InvalidDatatypeFacetException;

    short getPrimitiveKind();

    short getWhitespace() throws DatatypeException;

    boolean isEqual(Object obj, Object obj2);

    boolean isIDType();

    Object validate(Object obj, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException;

    Object validate(String str, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException;

    void validate(ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException;
}
