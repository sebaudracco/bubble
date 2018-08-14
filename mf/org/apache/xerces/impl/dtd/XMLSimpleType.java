package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.impl.dv.DatatypeValidator;

public class XMLSimpleType {
    public static final short DEFAULT_TYPE_DEFAULT = (short) 3;
    public static final short DEFAULT_TYPE_FIXED = (short) 1;
    public static final short DEFAULT_TYPE_IMPLIED = (short) 0;
    public static final short DEFAULT_TYPE_REQUIRED = (short) 2;
    public static final short TYPE_CDATA = (short) 0;
    public static final short TYPE_ENTITY = (short) 1;
    public static final short TYPE_ENUMERATION = (short) 2;
    public static final short TYPE_ID = (short) 3;
    public static final short TYPE_IDREF = (short) 4;
    public static final short TYPE_NAMED = (short) 7;
    public static final short TYPE_NMTOKEN = (short) 5;
    public static final short TYPE_NOTATION = (short) 6;
    public DatatypeValidator datatypeValidator;
    public short defaultType;
    public String defaultValue;
    public String[] enumeration;
    public boolean list;
    public String name;
    public String nonNormalizedDefaultValue;
    public short type;

    public void setValues(short type, String name, String[] enumeration, boolean list, short defaultType, String defaultValue, String nonNormalizedDefaultValue, DatatypeValidator datatypeValidator) {
        this.type = type;
        this.name = name;
        if (enumeration == null || enumeration.length <= 0) {
            this.enumeration = null;
        } else {
            this.enumeration = new String[enumeration.length];
            System.arraycopy(enumeration, 0, this.enumeration, 0, this.enumeration.length);
        }
        this.list = list;
        this.defaultType = defaultType;
        this.defaultValue = defaultValue;
        this.nonNormalizedDefaultValue = nonNormalizedDefaultValue;
        this.datatypeValidator = datatypeValidator;
    }

    public void setValues(XMLSimpleType simpleType) {
        this.type = simpleType.type;
        this.name = simpleType.name;
        if (simpleType.enumeration == null || simpleType.enumeration.length <= 0) {
            this.enumeration = null;
        } else {
            this.enumeration = new String[simpleType.enumeration.length];
            System.arraycopy(simpleType.enumeration, 0, this.enumeration, 0, this.enumeration.length);
        }
        this.list = simpleType.list;
        this.defaultType = simpleType.defaultType;
        this.defaultValue = simpleType.defaultValue;
        this.nonNormalizedDefaultValue = simpleType.nonNormalizedDefaultValue;
        this.datatypeValidator = simpleType.datatypeValidator;
    }

    public void clear() {
        this.type = (short) -1;
        this.name = null;
        this.enumeration = null;
        this.list = false;
        this.defaultType = (short) -1;
        this.defaultValue = null;
        this.nonNormalizedDefaultValue = null;
        this.datatypeValidator = null;
    }
}
