package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class XSAttributeDecl implements XSAttributeDeclaration {
    public static final short SCOPE_ABSENT = (short) 0;
    public static final short SCOPE_GLOBAL = (short) 1;
    public static final short SCOPE_LOCAL = (short) 2;
    XSObjectList fAnnotations = null;
    short fConstraintType = (short) 0;
    ValidatedInfo fDefault = null;
    XSComplexTypeDecl fEnclosingCT = null;
    String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    short fScope = (short) 0;
    String fTargetNamespace = null;
    XSSimpleType fType = null;
    public QName fUnresolvedTypeName = null;

    public void setValues(String name, String targetNamespace, XSSimpleType simpleType, short constraintType, short scope, ValidatedInfo valInfo, XSComplexTypeDecl enclosingCT, XSObjectList annotations) {
        this.fName = name;
        this.fTargetNamespace = targetNamespace;
        this.fType = simpleType;
        this.fConstraintType = constraintType;
        this.fScope = scope;
        this.fDefault = valInfo;
        this.fEnclosingCT = enclosingCT;
        this.fAnnotations = annotations;
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fUnresolvedTypeName = null;
        this.fConstraintType = (short) 0;
        this.fScope = (short) 0;
        this.fDefault = null;
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 1;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSSimpleTypeDefinition getTypeDefinition() {
        return this.fType;
    }

    public short getScope() {
        return this.fScope;
    }

    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }

    public short getConstraintType() {
        return this.fConstraintType;
    }

    public String getConstraintValue() {
        if (getConstraintType() == (short) 0) {
            return null;
        }
        return this.fDefault.stringValue();
    }

    public XSAnnotation getAnnotation() {
        return this.fAnnotations != null ? (XSAnnotation) this.fAnnotations.item(0) : null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public ValidatedInfo getValInfo() {
        return this.fDefault;
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }

    public Object getActualVC() {
        if (getConstraintType() == (short) 0) {
            return null;
        }
        return this.fDefault.actualValue;
    }

    public short getActualVCType() {
        if (getConstraintType() == (short) 0) {
            return (short) 45;
        }
        return this.fDefault.actualValueType;
    }

    public ShortList getItemValueTypes() {
        if (getConstraintType() == (short) 0) {
            return null;
        }
        return this.fDefault.itemValueTypes;
    }

    public XSValue getValueConstraintValue() {
        return this.fDefault;
    }
}
