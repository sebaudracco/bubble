package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;

public class BaseDVFactory extends SchemaDVFactory {
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static SymbolHash fBaseTypes = new SymbolHash(53);

    static {
        createBuiltInTypes(fBaseTypes);
    }

    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType) fBaseTypes.get(name);
    }

    public SymbolHash getBuiltInTypes() {
        return fBaseTypes.makeClone();
    }

    public XSSimpleType createTypeRestriction(String name, String targetNamespace, short finalSet, XSSimpleType base, XSObjectList annotations) {
        return new XSSimpleTypeDecl((XSSimpleTypeDecl) base, name, targetNamespace, finalSet, false, annotations);
    }

    public XSSimpleType createTypeList(String name, String targetNamespace, short finalSet, XSSimpleType itemType, XSObjectList annotations) {
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, (XSSimpleTypeDecl) itemType, false, annotations);
    }

    public XSSimpleType createTypeUnion(String name, String targetNamespace, short finalSet, XSSimpleType[] memberTypes, XSObjectList annotations) {
        int typeNum = memberTypes.length;
        XSSimpleTypeDecl[] mtypes = new XSSimpleTypeDecl[typeNum];
        System.arraycopy(memberTypes, 0, mtypes, 0, typeNum);
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, mtypes, annotations);
    }

    static void createBuiltInTypes(SymbolHash types) {
        String ANYSIMPLETYPE = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
        String ANYURI = SchemaSymbols.ATTVAL_ANYURI;
        String BASE64BINARY = SchemaSymbols.ATTVAL_BASE64BINARY;
        String BOOLEAN = SchemaSymbols.ATTVAL_BOOLEAN;
        String BYTE = SchemaSymbols.ATTVAL_BYTE;
        String DATE = "date";
        String DATETIME = SchemaSymbols.ATTVAL_DATETIME;
        String DAY = SchemaSymbols.ATTVAL_DAY;
        String DECIMAL = SchemaSymbols.ATTVAL_DECIMAL;
        String INT = SchemaSymbols.ATTVAL_INT;
        String INTEGER = SchemaSymbols.ATTVAL_INTEGER;
        String LONG = SchemaSymbols.ATTVAL_LONG;
        String NEGATIVEINTEGER = SchemaSymbols.ATTVAL_NEGATIVEINTEGER;
        String MONTH = SchemaSymbols.ATTVAL_MONTH;
        String MONTHDAY = SchemaSymbols.ATTVAL_MONTHDAY;
        String NONNEGATIVEINTEGER = SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER;
        String NONPOSITIVEINTEGER = SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER;
        String POSITIVEINTEGER = SchemaSymbols.ATTVAL_POSITIVEINTEGER;
        String SHORT = SchemaSymbols.ATTVAL_SHORT;
        String STRING = SchemaSymbols.ATTVAL_STRING;
        String TIME = "time";
        String UNSIGNEDBYTE = SchemaSymbols.ATTVAL_UNSIGNEDBYTE;
        String UNSIGNEDINT = SchemaSymbols.ATTVAL_UNSIGNEDINT;
        String UNSIGNEDLONG = SchemaSymbols.ATTVAL_UNSIGNEDLONG;
        String UNSIGNEDSHORT = SchemaSymbols.ATTVAL_UNSIGNEDSHORT;
        String YEAR = SchemaSymbols.ATTVAL_YEAR;
        String YEARMONTH = SchemaSymbols.ATTVAL_YEARMONTH;
        XSFacets facets = new XSFacets();
        XSSimpleTypeDecl anySimpleType = XSSimpleTypeDecl.fAnySimpleType;
        types.put(SchemaSymbols.ATTVAL_ANYSIMPLETYPE, anySimpleType);
        types.put(SchemaSymbols.ATTVAL_STRING, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_STRING, (short) 1, (short) 0, false, false, false, true, (short) 2));
        SymbolHash symbolHash = types;
        String str = SchemaSymbols.ATTVAL_BOOLEAN;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_BOOLEAN, (short) 2, (short) 0, false, true, false, true, (short) 3));
        XSSimpleTypeDecl decimalDV = new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_DECIMAL, (short) 3, (short) 2, false, false, true, true, (short) 4);
        types.put(SchemaSymbols.ATTVAL_DECIMAL, decimalDV);
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_ANYURI;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_ANYURI, (short) 17, (short) 0, false, false, false, true, (short) 18));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_BASE64BINARY;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_BASE64BINARY, (short) 16, (short) 0, false, false, false, true, (short) 17));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_DATETIME;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_DATETIME, (short) 7, (short) 1, false, false, false, true, (short) 8));
        symbolHash = types;
        str = "time";
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, "time", (short) 8, (short) 1, false, false, false, true, (short) 9));
        symbolHash = types;
        str = "date";
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, "date", (short) 9, (short) 1, false, false, false, true, (short) 10));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_YEARMONTH;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_YEARMONTH, (short) 10, (short) 1, false, false, false, true, (short) 11));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_YEAR;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_YEAR, (short) 11, (short) 1, false, false, false, true, (short) 12));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_MONTHDAY;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_MONTHDAY, (short) 12, (short) 1, false, false, false, true, (short) 13));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_DAY;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_DAY, (short) 13, (short) 1, false, false, false, true, (short) 14));
        symbolHash = types;
        str = SchemaSymbols.ATTVAL_MONTH;
        symbolHash.put(str, new XSSimpleTypeDecl(anySimpleType, SchemaSymbols.ATTVAL_MONTH, (short) 14, (short) 1, false, false, false, true, (short) 15));
        XSSimpleTypeDecl integerDV = new XSSimpleTypeDecl(decimalDV, SchemaSymbols.ATTVAL_INTEGER, (short) 24, (short) 2, false, false, true, true, (short) 30);
        types.put(SchemaSymbols.ATTVAL_INTEGER, integerDV);
        facets.maxInclusive = SchemaSymbols.ATTVAL_FALSE_0;
        XSSimpleTypeDecl nonPositiveDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 31);
        nonPositiveDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, nonPositiveDV);
        facets.maxInclusive = "-1";
        XSSimpleTypeDecl negativeDV = new XSSimpleTypeDecl(nonPositiveDV, SchemaSymbols.ATTVAL_NEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 32);
        negativeDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_NEGATIVEINTEGER, negativeDV);
        facets.maxInclusive = "9223372036854775807";
        facets.minInclusive = "-9223372036854775808";
        XSSimpleTypeDecl longDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_LONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 33);
        longDV.applyFacets1(facets, (short) 288, (short) 0);
        types.put(SchemaSymbols.ATTVAL_LONG, longDV);
        facets.maxInclusive = "2147483647";
        facets.minInclusive = "-2147483648";
        XSSimpleTypeDecl intDV = new XSSimpleTypeDecl(longDV, SchemaSymbols.ATTVAL_INT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 34);
        intDV.applyFacets1(facets, (short) 288, (short) 0);
        types.put(SchemaSymbols.ATTVAL_INT, intDV);
        facets.maxInclusive = "32767";
        facets.minInclusive = "-32768";
        XSSimpleTypeDecl shortDV = new XSSimpleTypeDecl(intDV, SchemaSymbols.ATTVAL_SHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 35);
        shortDV.applyFacets1(facets, (short) 288, (short) 0);
        types.put(SchemaSymbols.ATTVAL_SHORT, shortDV);
        facets.maxInclusive = "127";
        facets.minInclusive = "-128";
        XSSimpleTypeDecl byteDV = new XSSimpleTypeDecl(shortDV, SchemaSymbols.ATTVAL_BYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 36);
        byteDV.applyFacets1(facets, (short) 288, (short) 0);
        types.put(SchemaSymbols.ATTVAL_BYTE, byteDV);
        facets.minInclusive = SchemaSymbols.ATTVAL_FALSE_0;
        XSSimpleTypeDecl nonNegativeDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 37);
        nonNegativeDV.applyFacets1(facets, XSSimpleTypeDefinition.FACET_MININCLUSIVE, (short) 0);
        types.put(SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, nonNegativeDV);
        facets.maxInclusive = "18446744073709551615";
        XSSimpleTypeDecl unsignedLongDV = new XSSimpleTypeDecl(nonNegativeDV, SchemaSymbols.ATTVAL_UNSIGNEDLONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 38);
        unsignedLongDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_UNSIGNEDLONG, unsignedLongDV);
        facets.maxInclusive = "4294967295";
        XSSimpleTypeDecl unsignedIntDV = new XSSimpleTypeDecl(unsignedLongDV, SchemaSymbols.ATTVAL_UNSIGNEDINT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 39);
        unsignedIntDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_UNSIGNEDINT, unsignedIntDV);
        facets.maxInclusive = "65535";
        XSSimpleTypeDecl unsignedShortDV = new XSSimpleTypeDecl(unsignedIntDV, SchemaSymbols.ATTVAL_UNSIGNEDSHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 40);
        unsignedShortDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_UNSIGNEDSHORT, unsignedShortDV);
        facets.maxInclusive = "255";
        XSSimpleTypeDecl unsignedByteDV = new XSSimpleTypeDecl(unsignedShortDV, SchemaSymbols.ATTVAL_UNSIGNEDBYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 41);
        unsignedByteDV.applyFacets1(facets, (short) 32, (short) 0);
        types.put(SchemaSymbols.ATTVAL_UNSIGNEDBYTE, unsignedByteDV);
        facets.minInclusive = SchemaSymbols.ATTVAL_TRUE_1;
        XSSimpleTypeDecl positiveIntegerDV = new XSSimpleTypeDecl(nonNegativeDV, SchemaSymbols.ATTVAL_POSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 42);
        positiveIntegerDV.applyFacets1(facets, XSSimpleTypeDefinition.FACET_MININCLUSIVE, (short) 0);
        types.put(SchemaSymbols.ATTVAL_POSITIVEINTEGER, positiveIntegerDV);
    }
}
