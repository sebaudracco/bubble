package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class XSElementDecl implements XSElementDeclaration {
    private static final short ABSTRACT = (short) 8;
    private static final short CONSTRAINT_MASK = (short) 3;
    static final int INITIAL_SIZE = 2;
    private static final short NILLABLE = (short) 4;
    public static final short SCOPE_ABSENT = (short) 0;
    public static final short SCOPE_GLOBAL = (short) 1;
    public static final short SCOPE_LOCAL = (short) 2;
    public XSObjectList fAnnotations = null;
    public short fBlock = (short) 0;
    public ValidatedInfo fDefault = null;
    private String fDescription = null;
    XSComplexTypeDecl fEnclosingCT = null;
    public short fFinal = (short) 0;
    int fIDCPos = 0;
    IdentityConstraint[] fIDConstraints = new IdentityConstraint[2];
    short fMiscFlags = (short) 0;
    public String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    public short fScope = (short) 0;
    public XSElementDecl fSubGroup = null;
    public String fTargetNamespace = null;
    public XSTypeDefinition fType = null;
    public QName fUnresolvedTypeName = null;

    public void setConstraintType(short constraintType) {
        this.fMiscFlags = (short) (this.fMiscFlags ^ (this.fMiscFlags & 3));
        this.fMiscFlags = (short) (this.fMiscFlags | (constraintType & 3));
    }

    public void setIsNillable() {
        this.fMiscFlags = (short) (this.fMiscFlags | 4);
    }

    public void setIsAbstract() {
        this.fMiscFlags = (short) (this.fMiscFlags | 8);
    }

    public void setIsGlobal() {
        this.fScope = (short) 1;
    }

    public void setIsLocal(XSComplexTypeDecl enclosingCT) {
        this.fScope = (short) 2;
        this.fEnclosingCT = enclosingCT;
    }

    public void addIDConstraint(IdentityConstraint idc) {
        if (this.fIDCPos == this.fIDConstraints.length) {
            this.fIDConstraints = resize(this.fIDConstraints, this.fIDCPos * 2);
        }
        IdentityConstraint[] identityConstraintArr = this.fIDConstraints;
        int i = this.fIDCPos;
        this.fIDCPos = i + 1;
        identityConstraintArr[i] = idc;
    }

    public IdentityConstraint[] getIDConstraints() {
        if (this.fIDCPos == 0) {
            return null;
        }
        if (this.fIDCPos < this.fIDConstraints.length) {
            this.fIDConstraints = resize(this.fIDConstraints, this.fIDCPos);
        }
        return this.fIDConstraints;
    }

    static final IdentityConstraint[] resize(IdentityConstraint[] oldArray, int newSize) {
        IdentityConstraint[] newArray = new IdentityConstraint[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    public String toString() {
        if (this.fDescription == null) {
            if (this.fTargetNamespace != null) {
                StringBuffer buffer = new StringBuffer(((this.fName != null ? this.fName.length() : 4) + this.fTargetNamespace.length()) + 3);
                buffer.append('\"');
                buffer.append(this.fTargetNamespace);
                buffer.append('\"');
                buffer.append(':');
                buffer.append(this.fName);
                this.fDescription = buffer.toString();
            } else {
                this.fDescription = this.fName;
            }
        }
        return this.fDescription;
    }

    public int hashCode() {
        int code = this.fName.hashCode();
        if (this.fTargetNamespace != null) {
            return (code << 16) + this.fTargetNamespace.hashCode();
        }
        return code;
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public void reset() {
        this.fScope = (short) 0;
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fUnresolvedTypeName = null;
        this.fMiscFlags = (short) 0;
        this.fBlock = (short) 0;
        this.fFinal = (short) 0;
        this.fDefault = null;
        this.fAnnotations = null;
        this.fSubGroup = null;
        for (int i = 0; i < this.fIDCPos; i++) {
            this.fIDConstraints[i] = null;
        }
        this.fIDCPos = 0;
    }

    public short getType() {
        return (short) 2;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSTypeDefinition getTypeDefinition() {
        return this.fType;
    }

    public short getScope() {
        return this.fScope;
    }

    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }

    public short getConstraintType() {
        return (short) (this.fMiscFlags & 3);
    }

    public String getConstraintValue() {
        if (getConstraintType() == (short) 0) {
            return null;
        }
        return this.fDefault.stringValue();
    }

    public boolean getNillable() {
        return (this.fMiscFlags & 4) != 0;
    }

    public XSNamedMap getIdentityConstraints() {
        return new XSNamedMapImpl(this.fIDConstraints, this.fIDCPos);
    }

    public XSElementDeclaration getSubstitutionGroupAffiliation() {
        return this.fSubGroup;
    }

    public boolean isSubstitutionGroupExclusion(short exclusion) {
        return (this.fFinal & exclusion) != 0;
    }

    public short getSubstitutionGroupExclusions() {
        return this.fFinal;
    }

    public boolean isDisallowedSubstitution(short disallowed) {
        return (this.fBlock & disallowed) != 0;
    }

    public short getDisallowedSubstitutions() {
        return this.fBlock;
    }

    public boolean getAbstract() {
        return (this.fMiscFlags & 8) != 0;
    }

    public XSAnnotation getAnnotation() {
        return this.fAnnotations != null ? (XSAnnotation) this.fAnnotations.item(0) : null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
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
