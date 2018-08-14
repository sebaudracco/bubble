package mf.org.apache.xerces.impl.dv;

import mf.org.apache.xerces.impl.xs.util.ShortListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class ValidatedInfo implements XSValue {
    public XSSimpleType actualType;
    public Object actualValue;
    public short actualValueType;
    public ShortList itemValueTypes;
    public XSSimpleType memberType;
    public XSSimpleType[] memberTypes;
    public String normalizedValue;

    public void reset() {
        this.normalizedValue = null;
        this.actualValue = null;
        this.actualValueType = (short) 45;
        this.actualType = null;
        this.memberType = null;
        this.memberTypes = null;
        this.itemValueTypes = null;
    }

    public String stringValue() {
        if (this.actualValue == null) {
            return this.normalizedValue;
        }
        return this.actualValue.toString();
    }

    public static boolean isComparable(ValidatedInfo info1, ValidatedInfo info2) {
        short primitiveType1 = convertToPrimitiveKind(info1.actualValueType);
        short primitiveType2 = convertToPrimitiveKind(info2.actualValueType);
        if (primitiveType1 != primitiveType2) {
            if (primitiveType1 == (short) 1 && primitiveType2 == (short) 2) {
                return true;
            }
            if (primitiveType1 == (short) 2 && primitiveType2 == (short) 1) {
                return true;
            }
            return false;
        } else if (primitiveType1 != (short) 44 && primitiveType1 != (short) 43) {
            return true;
        } else {
            int typeList1Length;
            int typeList2Length;
            ShortList typeList1 = info1.itemValueTypes;
            ShortList typeList2 = info2.itemValueTypes;
            if (typeList1 != null) {
                typeList1Length = typeList1.getLength();
            } else {
                typeList1Length = 0;
            }
            if (typeList2 != null) {
                typeList2Length = typeList2.getLength();
            } else {
                typeList2Length = 0;
            }
            if (typeList1Length != typeList2Length) {
                return false;
            }
            for (int i = 0; i < typeList1Length; i++) {
                short primitiveItem1 = convertToPrimitiveKind(typeList1.item(i));
                short primitiveItem2 = convertToPrimitiveKind(typeList2.item(i));
                if (primitiveItem1 != primitiveItem2 && ((primitiveItem1 != (short) 1 || primitiveItem2 != (short) 2) && (primitiveItem1 != (short) 2 || primitiveItem2 != (short) 1))) {
                    return false;
                }
            }
            return true;
        }
    }

    private static short convertToPrimitiveKind(short valueType) {
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

    public Object getActualValue() {
        return this.actualValue;
    }

    public short getActualValueType() {
        return this.actualValueType;
    }

    public ShortList getListValueTypes() {
        return this.itemValueTypes == null ? ShortListImpl.EMPTY_LIST : this.itemValueTypes;
    }

    public XSObjectList getMemberTypeDefinitions() {
        if (this.memberTypes == null) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        return new XSObjectListImpl(this.memberTypes, this.memberTypes.length);
    }

    public String getNormalizedValue() {
        return this.normalizedValue;
    }

    public XSSimpleTypeDefinition getTypeDefinition() {
        return this.actualType;
    }

    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.memberType;
    }

    public void copyFrom(XSValue o) {
        if (o == null) {
            reset();
        } else if (o instanceof ValidatedInfo) {
            ValidatedInfo other = (ValidatedInfo) o;
            this.normalizedValue = other.normalizedValue;
            this.actualValue = other.actualValue;
            this.actualValueType = other.actualValueType;
            this.actualType = other.actualType;
            this.memberType = other.memberType;
            this.memberTypes = other.memberTypes;
            this.itemValueTypes = other.itemValueTypes;
        } else {
            this.normalizedValue = o.getNormalizedValue();
            this.actualValue = o.getActualValue();
            this.actualValueType = o.getActualValueType();
            this.actualType = (XSSimpleType) o.getTypeDefinition();
            this.memberType = (XSSimpleType) o.getMemberTypeDefinition();
            XSSimpleType realType = this.memberType == null ? this.actualType : this.memberType;
            if (realType == null || realType.getBuiltInKind() != (short) 43) {
                this.memberTypes = null;
            } else {
                XSObjectList members = o.getMemberTypeDefinitions();
                this.memberTypes = new XSSimpleType[members.getLength()];
                for (int i = 0; i < members.getLength(); i++) {
                    this.memberTypes[i] = (XSSimpleType) members.get(i);
                }
            }
            this.itemValueTypes = o.getListValueTypes();
        }
    }
}
