package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSDeclarationPool;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;

public abstract class BaseSchemaDVFactory extends SchemaDVFactory {
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    protected XSDeclarationPool fDeclPool = null;

    protected static void createBuiltInTypes(SymbolHash builtInTypes, XSSimpleTypeDecl baseAtomicType) {
        String ANYSIMPLETYPE = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
        String ANYURI = SchemaSymbols.ATTVAL_ANYURI;
        String BASE64BINARY = SchemaSymbols.ATTVAL_BASE64BINARY;
        String BOOLEAN = SchemaSymbols.ATTVAL_BOOLEAN;
        String BYTE = SchemaSymbols.ATTVAL_BYTE;
        String DATE = "date";
        String DATETIME = SchemaSymbols.ATTVAL_DATETIME;
        String DAY = SchemaSymbols.ATTVAL_DAY;
        String DECIMAL = SchemaSymbols.ATTVAL_DECIMAL;
        String DOUBLE = SchemaSymbols.ATTVAL_DOUBLE;
        String DURATION = "duration";
        String ENTITY = SchemaSymbols.ATTVAL_ENTITY;
        String ENTITIES = SchemaSymbols.ATTVAL_ENTITIES;
        String FLOAT = SchemaSymbols.ATTVAL_FLOAT;
        String HEXBINARY = SchemaSymbols.ATTVAL_HEXBINARY;
        String ID = SchemaSymbols.ATTVAL_ID;
        String IDREF = SchemaSymbols.ATTVAL_IDREF;
        String IDREFS = SchemaSymbols.ATTVAL_IDREFS;
        String INT = SchemaSymbols.ATTVAL_INT;
        String INTEGER = SchemaSymbols.ATTVAL_INTEGER;
        String LONG = SchemaSymbols.ATTVAL_LONG;
        String NAME = SchemaSymbols.ATTVAL_NAME;
        String NEGATIVEINTEGER = SchemaSymbols.ATTVAL_NEGATIVEINTEGER;
        String MONTH = SchemaSymbols.ATTVAL_MONTH;
        String MONTHDAY = SchemaSymbols.ATTVAL_MONTHDAY;
        String NCNAME = SchemaSymbols.ATTVAL_NCNAME;
        String NMTOKEN = SchemaSymbols.ATTVAL_NMTOKEN;
        String NMTOKENS = SchemaSymbols.ATTVAL_NMTOKENS;
        String LANGUAGE = SchemaSymbols.ATTVAL_LANGUAGE;
        String NONNEGATIVEINTEGER = SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER;
        String NONPOSITIVEINTEGER = SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER;
        String NORMALIZEDSTRING = SchemaSymbols.ATTVAL_NORMALIZEDSTRING;
        String NOTATION = SchemaSymbols.ATTVAL_NOTATION;
        String POSITIVEINTEGER = SchemaSymbols.ATTVAL_POSITIVEINTEGER;
        String QNAME = SchemaSymbols.ATTVAL_QNAME;
        String SHORT = SchemaSymbols.ATTVAL_SHORT;
        String STRING = SchemaSymbols.ATTVAL_STRING;
        String TIME = "time";
        String TOKEN = SchemaSymbols.ATTVAL_TOKEN;
        String UNSIGNEDBYTE = SchemaSymbols.ATTVAL_UNSIGNEDBYTE;
        String UNSIGNEDINT = SchemaSymbols.ATTVAL_UNSIGNEDINT;
        String UNSIGNEDLONG = SchemaSymbols.ATTVAL_UNSIGNEDLONG;
        String UNSIGNEDSHORT = SchemaSymbols.ATTVAL_UNSIGNEDSHORT;
        String YEAR = SchemaSymbols.ATTVAL_YEAR;
        String YEARMONTH = SchemaSymbols.ATTVAL_YEARMONTH;
        XSFacets facets = new XSFacets();
        builtInTypes.put(SchemaSymbols.ATTVAL_ANYSIMPLETYPE, XSSimpleTypeDecl.fAnySimpleType);
        XSSimpleTypeDecl stringDV = new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_STRING, (short) 1, (short) 0, false, false, false, true, (short) 2);
        builtInTypes.put(SchemaSymbols.ATTVAL_STRING, stringDV);
        SymbolHash symbolHash = builtInTypes;
        String str = SchemaSymbols.ATTVAL_BOOLEAN;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_BOOLEAN, (short) 2, (short) 0, false, true, false, true, (short) 3));
        XSSimpleTypeDecl decimalDV = new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_DECIMAL, (short) 3, (short) 2, false, false, true, true, (short) 4);
        builtInTypes.put(SchemaSymbols.ATTVAL_DECIMAL, decimalDV);
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_ANYURI;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_ANYURI, (short) 17, (short) 0, false, false, false, true, (short) 18));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_BASE64BINARY;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_BASE64BINARY, (short) 16, (short) 0, false, false, false, true, (short) 17));
        symbolHash = builtInTypes;
        str = "duration";
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, "duration", (short) 6, (short) 1, false, false, false, true, (short) 7));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_DATETIME;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_DATETIME, (short) 7, (short) 1, false, false, false, true, (short) 8));
        symbolHash = builtInTypes;
        str = "time";
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, "time", (short) 8, (short) 1, false, false, false, true, (short) 9));
        symbolHash = builtInTypes;
        str = "date";
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, "date", (short) 9, (short) 1, false, false, false, true, (short) 10));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_YEARMONTH;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_YEARMONTH, (short) 10, (short) 1, false, false, false, true, (short) 11));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_YEAR;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_YEAR, (short) 11, (short) 1, false, false, false, true, (short) 12));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_MONTHDAY;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_MONTHDAY, (short) 12, (short) 1, false, false, false, true, (short) 13));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_DAY;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_DAY, (short) 13, (short) 1, false, false, false, true, (short) 14));
        symbolHash = builtInTypes;
        str = SchemaSymbols.ATTVAL_MONTH;
        symbolHash.put(str, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_MONTH, (short) 14, (short) 1, false, false, false, true, (short) 15));
        XSSimpleTypeDecl integerDV = new XSSimpleTypeDecl(decimalDV, SchemaSymbols.ATTVAL_INTEGER, (short) 24, (short) 2, false, false, true, true, (short) 30);
        builtInTypes.put(SchemaSymbols.ATTVAL_INTEGER, integerDV);
        facets.maxInclusive = SchemaSymbols.ATTVAL_FALSE_0;
        XSSimpleTypeDecl nonPositiveDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 31);
        nonPositiveDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_NONPOSITIVEINTEGER, nonPositiveDV);
        facets.maxInclusive = "-1";
        XSSimpleTypeDecl negativeDV = new XSSimpleTypeDecl(nonPositiveDV, SchemaSymbols.ATTVAL_NEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 32);
        negativeDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_NEGATIVEINTEGER, negativeDV);
        facets.maxInclusive = "9223372036854775807";
        facets.minInclusive = "-9223372036854775808";
        XSSimpleTypeDecl longDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_LONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 33);
        longDV.applyFacets1(facets, (short) 288, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_LONG, longDV);
        facets.maxInclusive = "2147483647";
        facets.minInclusive = "-2147483648";
        XSSimpleTypeDecl intDV = new XSSimpleTypeDecl(longDV, SchemaSymbols.ATTVAL_INT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 34);
        intDV.applyFacets1(facets, (short) 288, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_INT, intDV);
        facets.maxInclusive = "32767";
        facets.minInclusive = "-32768";
        XSSimpleTypeDecl shortDV = new XSSimpleTypeDecl(intDV, SchemaSymbols.ATTVAL_SHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 35);
        shortDV.applyFacets1(facets, (short) 288, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_SHORT, shortDV);
        facets.maxInclusive = "127";
        facets.minInclusive = "-128";
        XSSimpleTypeDecl byteDV = new XSSimpleTypeDecl(shortDV, SchemaSymbols.ATTVAL_BYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 36);
        byteDV.applyFacets1(facets, (short) 288, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_BYTE, byteDV);
        facets.minInclusive = SchemaSymbols.ATTVAL_FALSE_0;
        XSSimpleTypeDecl nonNegativeDV = new XSSimpleTypeDecl(integerDV, SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 37);
        nonNegativeDV.applyFacets1(facets, XSSimpleTypeDefinition.FACET_MININCLUSIVE, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER, nonNegativeDV);
        facets.maxInclusive = "18446744073709551615";
        XSSimpleTypeDecl unsignedLongDV = new XSSimpleTypeDecl(nonNegativeDV, SchemaSymbols.ATTVAL_UNSIGNEDLONG, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 38);
        unsignedLongDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_UNSIGNEDLONG, unsignedLongDV);
        facets.maxInclusive = "4294967295";
        XSSimpleTypeDecl unsignedIntDV = new XSSimpleTypeDecl(unsignedLongDV, SchemaSymbols.ATTVAL_UNSIGNEDINT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 39);
        unsignedIntDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_UNSIGNEDINT, unsignedIntDV);
        facets.maxInclusive = "65535";
        XSSimpleTypeDecl unsignedShortDV = new XSSimpleTypeDecl(unsignedIntDV, SchemaSymbols.ATTVAL_UNSIGNEDSHORT, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 40);
        unsignedShortDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_UNSIGNEDSHORT, unsignedShortDV);
        facets.maxInclusive = "255";
        XSSimpleTypeDecl unsignedByteDV = new XSSimpleTypeDecl(unsignedShortDV, SchemaSymbols.ATTVAL_UNSIGNEDBYTE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 41);
        unsignedByteDV.applyFacets1(facets, (short) 32, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_UNSIGNEDBYTE, unsignedByteDV);
        facets.minInclusive = SchemaSymbols.ATTVAL_TRUE_1;
        XSSimpleTypeDecl positiveIntegerDV = new XSSimpleTypeDecl(nonNegativeDV, SchemaSymbols.ATTVAL_POSITIVEINTEGER, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 42);
        positiveIntegerDV.applyFacets1(facets, XSSimpleTypeDefinition.FACET_MININCLUSIVE, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_POSITIVEINTEGER, positiveIntegerDV);
        builtInTypes.put(SchemaSymbols.ATTVAL_FLOAT, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_FLOAT, (short) 4, (short) 1, true, true, true, true, (short) 5));
        builtInTypes.put(SchemaSymbols.ATTVAL_DOUBLE, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_DOUBLE, (short) 5, (short) 1, true, true, true, true, (short) 6));
        builtInTypes.put(SchemaSymbols.ATTVAL_HEXBINARY, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_HEXBINARY, (short) 15, (short) 0, false, false, false, true, (short) 16));
        builtInTypes.put(SchemaSymbols.ATTVAL_NOTATION, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_NOTATION, (short) 20, (short) 0, false, false, false, true, (short) 20));
        facets.whiteSpace = (short) 1;
        XSSimpleTypeDecl normalizedDV = new XSSimpleTypeDecl(stringDV, SchemaSymbols.ATTVAL_NORMALIZEDSTRING, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 21);
        normalizedDV.applyFacets1(facets, (short) 16, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_NORMALIZEDSTRING, normalizedDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl tokenDV = new XSSimpleTypeDecl(normalizedDV, SchemaSymbols.ATTVAL_TOKEN, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 22);
        tokenDV.applyFacets1(facets, (short) 16, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_TOKEN, tokenDV);
        facets.whiteSpace = (short) 2;
        facets.pattern = "([a-zA-Z]{1,8})(-[a-zA-Z0-9]{1,8})*";
        XSSimpleTypeDecl languageDV = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_LANGUAGE, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 23);
        languageDV.applyFacets1(facets, (short) 24, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_LANGUAGE, languageDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl nameDV = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_NAME, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 25);
        nameDV.applyFacets1(facets, (short) 16, (short) 0, (short) 2);
        builtInTypes.put(SchemaSymbols.ATTVAL_NAME, nameDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl ncnameDV = new XSSimpleTypeDecl(nameDV, SchemaSymbols.ATTVAL_NCNAME, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 26);
        ncnameDV.applyFacets1(facets, (short) 16, (short) 0, (short) 3);
        builtInTypes.put(SchemaSymbols.ATTVAL_NCNAME, ncnameDV);
        builtInTypes.put(SchemaSymbols.ATTVAL_QNAME, new XSSimpleTypeDecl(baseAtomicType, SchemaSymbols.ATTVAL_QNAME, (short) 18, (short) 0, false, false, false, true, (short) 19));
        builtInTypes.put(SchemaSymbols.ATTVAL_ID, new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_ID, (short) 21, (short) 0, false, false, false, true, (short) 27));
        XSSimpleTypeDecl idrefDV = new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_IDREF, (short) 22, (short) 0, false, false, false, true, (short) 28);
        builtInTypes.put(SchemaSymbols.ATTVAL_IDREF, idrefDV);
        facets.minLength = 1;
        XSSimpleTypeDecl idrefsDV = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short) 0, idrefDV, true, null), SchemaSymbols.ATTVAL_IDREFS, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null);
        idrefsDV.applyFacets1(facets, (short) 2, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_IDREFS, idrefsDV);
        XSSimpleTypeDecl entityDV = new XSSimpleTypeDecl(ncnameDV, SchemaSymbols.ATTVAL_ENTITY, (short) 23, (short) 0, false, false, false, true, (short) 29);
        builtInTypes.put(SchemaSymbols.ATTVAL_ENTITY, entityDV);
        facets.minLength = 1;
        XSSimpleTypeDecl entitiesDV = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short) 0, entityDV, true, null), SchemaSymbols.ATTVAL_ENTITIES, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null);
        entitiesDV.applyFacets1(facets, (short) 2, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_ENTITIES, entitiesDV);
        facets.whiteSpace = (short) 2;
        XSSimpleTypeDecl nmtokenDV = new XSSimpleTypeDecl(tokenDV, SchemaSymbols.ATTVAL_NMTOKEN, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null, (short) 24);
        nmtokenDV.applyFacets1(facets, (short) 16, (short) 0, (short) 1);
        builtInTypes.put(SchemaSymbols.ATTVAL_NMTOKEN, nmtokenDV);
        facets.minLength = 1;
        XSSimpleTypeDecl nmtokensDV = new XSSimpleTypeDecl(new XSSimpleTypeDecl(null, "http://www.w3.org/2001/XMLSchema", (short) 0, nmtokenDV, true, null), SchemaSymbols.ATTVAL_NMTOKENS, "http://www.w3.org/2001/XMLSchema", (short) 0, false, null);
        nmtokensDV.applyFacets1(facets, (short) 2, (short) 0);
        builtInTypes.put(SchemaSymbols.ATTVAL_NMTOKENS, nmtokensDV);
    }

    public XSSimpleType createTypeRestriction(String name, String targetNamespace, short finalSet, XSSimpleType base, XSObjectList annotations) {
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setRestrictionValues((XSSimpleTypeDecl) base, name, targetNamespace, finalSet, annotations);
        }
        return new XSSimpleTypeDecl((XSSimpleTypeDecl) base, name, targetNamespace, finalSet, false, annotations);
    }

    public XSSimpleType createTypeList(String name, String targetNamespace, short finalSet, XSSimpleType itemType, XSObjectList annotations) {
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setListValues(name, targetNamespace, finalSet, (XSSimpleTypeDecl) itemType, annotations);
        }
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, (XSSimpleTypeDecl) itemType, false, annotations);
    }

    public XSSimpleType createTypeUnion(String name, String targetNamespace, short finalSet, XSSimpleType[] memberTypes, XSObjectList annotations) {
        int typeNum = memberTypes.length;
        XSSimpleTypeDecl[] mtypes = new XSSimpleTypeDecl[typeNum];
        System.arraycopy(memberTypes, 0, mtypes, 0, typeNum);
        if (this.fDeclPool != null) {
            return this.fDeclPool.getSimpleTypeDecl().setUnionValues(name, targetNamespace, finalSet, mtypes, annotations);
        }
        return new XSSimpleTypeDecl(name, targetNamespace, finalSet, mtypes, annotations);
    }

    public void setDeclPool(XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }

    public XSSimpleTypeDecl newXSSimpleTypeDecl() {
        return new XSSimpleTypeDecl();
    }
}
