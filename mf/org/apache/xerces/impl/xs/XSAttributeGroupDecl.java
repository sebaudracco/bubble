package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSAttributeGroupDefinition;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSWildcard;

public class XSAttributeGroupDecl implements XSAttributeGroupDefinition {
    private static final int INITIAL_SIZE = 5;
    public XSObjectList fAnnotations;
    int fAttrUseNum = 0;
    protected XSObjectListImpl fAttrUses = null;
    XSAttributeUseImpl[] fAttributeUses = new XSAttributeUseImpl[5];
    public XSWildcardDecl fAttributeWC = null;
    public String fIDAttrName = null;
    public String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    public String fTargetNamespace = null;

    public String addAttributeUse(XSAttributeUseImpl attrUse) {
        if (attrUse.fUse != (short) 2 && attrUse.fAttrDecl.fType.isIDType()) {
            if (this.fIDAttrName != null) {
                return this.fIDAttrName;
            }
            this.fIDAttrName = attrUse.fAttrDecl.fName;
        }
        if (this.fAttrUseNum == this.fAttributeUses.length) {
            this.fAttributeUses = resize(this.fAttributeUses, this.fAttrUseNum * 2);
        }
        XSAttributeUseImpl[] xSAttributeUseImplArr = this.fAttributeUses;
        int i = this.fAttrUseNum;
        this.fAttrUseNum = i + 1;
        xSAttributeUseImplArr[i] = attrUse;
        return null;
    }

    public void replaceAttributeUse(XSAttributeUse oldUse, XSAttributeUseImpl newUse) {
        for (int i = 0; i < this.fAttrUseNum; i++) {
            if (this.fAttributeUses[i] == oldUse) {
                this.fAttributeUses[i] = newUse;
            }
        }
    }

    public XSAttributeUse getAttributeUse(String namespace, String name) {
        int i = 0;
        while (i < this.fAttrUseNum) {
            if (this.fAttributeUses[i].fAttrDecl.fTargetNamespace == namespace && this.fAttributeUses[i].fAttrDecl.fName == name) {
                return this.fAttributeUses[i];
            }
            i++;
        }
        return null;
    }

    public XSAttributeUse getAttributeUseNoProhibited(String namespace, String name) {
        int i = 0;
        while (i < this.fAttrUseNum) {
            if (this.fAttributeUses[i].fAttrDecl.fTargetNamespace == namespace && this.fAttributeUses[i].fAttrDecl.fName == name && this.fAttributeUses[i].fUse != (short) 2) {
                return this.fAttributeUses[i];
            }
            i++;
        }
        return null;
    }

    public void removeProhibitedAttrs() {
        if (this.fAttrUseNum != 0) {
            int count = 0;
            XSAttributeUseImpl[] uses = new XSAttributeUseImpl[this.fAttrUseNum];
            for (int i = 0; i < this.fAttrUseNum; i++) {
                if (this.fAttributeUses[i].fUse != (short) 2) {
                    int count2 = count + 1;
                    uses[count] = this.fAttributeUses[i];
                    count = count2;
                }
            }
            this.fAttributeUses = uses;
            this.fAttrUseNum = count;
        }
    }

    public Object[] validRestrictionOf(String typeName, XSAttributeGroupDecl baseGroup) {
        int i;
        for (i = 0; i < this.fAttrUseNum; i++) {
            XSAttributeDecl baseAttrDecl;
            XSAttributeUseImpl attrUse = this.fAttributeUses[i];
            XSAttributeDecl attrDecl = attrUse.fAttrDecl;
            XSAttributeUseImpl baseAttrUse = (XSAttributeUseImpl) baseGroup.getAttributeUse(attrDecl.fTargetNamespace, attrDecl.fName);
            Object[] errorArgs;
            if (baseAttrUse != null) {
                if (baseAttrUse.getRequired() && !attrUse.getRequired()) {
                    errorArgs = new Object[4];
                    errorArgs[0] = typeName;
                    errorArgs[1] = attrDecl.fName;
                    errorArgs[2] = attrUse.fUse == (short) 0 ? SchemaSymbols.ATTVAL_OPTIONAL : SchemaSymbols.ATTVAL_PROHIBITED;
                    errorArgs[3] = "derivation-ok-restriction.2.1.1";
                    return errorArgs;
                } else if (attrUse.fUse == (short) 2) {
                    continue;
                } else {
                    baseAttrDecl = baseAttrUse.fAttrDecl;
                    if (XSConstraints.checkSimpleDerivationOk(attrDecl.fType, baseAttrDecl.fType, baseAttrDecl.fType.getFinal())) {
                        int baseConsType = baseAttrUse.fConstraintType != (short) 0 ? baseAttrUse.fConstraintType : baseAttrDecl.getConstraintType();
                        int thisConstType = attrUse.fConstraintType != (short) 0 ? attrUse.fConstraintType : attrDecl.getConstraintType();
                        if (baseConsType != 2) {
                            continue;
                        } else if (thisConstType != 2) {
                            return new Object[]{typeName, attrDecl.fName, "derivation-ok-restriction.2.1.3.a"};
                        } else {
                            if (!(baseAttrUse.fDefault != null ? baseAttrUse.fDefault : baseAttrDecl.fDefault).actualValue.equals((attrUse.fDefault != null ? attrUse.fDefault : attrDecl.fDefault).actualValue)) {
                                return new Object[]{typeName, attrDecl.fName, (attrUse.fDefault != null ? attrUse.fDefault : attrDecl.fDefault).stringValue(), (baseAttrUse.fDefault != null ? baseAttrUse.fDefault : baseAttrDecl.fDefault).stringValue(), "derivation-ok-restriction.2.1.3.b"};
                            }
                        }
                    } else {
                        return new Object[]{typeName, attrDecl.fName, attrDecl.fType.getName(), baseAttrDecl.fType.getName(), "derivation-ok-restriction.2.1.2"};
                    }
                }
            } else if (baseGroup.fAttributeWC == null) {
                return new Object[]{typeName, attrDecl.fName, "derivation-ok-restriction.2.2.a"};
            } else if (!baseGroup.fAttributeWC.allowNamespace(attrDecl.fTargetNamespace)) {
                errorArgs = new Object[4];
                errorArgs[0] = typeName;
                errorArgs[1] = attrDecl.fName;
                errorArgs[2] = attrDecl.fTargetNamespace == null ? "" : attrDecl.fTargetNamespace;
                errorArgs[3] = "derivation-ok-restriction.2.2.b";
                return errorArgs;
            }
        }
        for (i = 0; i < baseGroup.fAttrUseNum; i++) {
            baseAttrUse = baseGroup.fAttributeUses[i];
            if (baseAttrUse.fUse == (short) 1) {
                baseAttrDecl = baseAttrUse.fAttrDecl;
                if (getAttributeUse(baseAttrDecl.fTargetNamespace, baseAttrDecl.fName) == null) {
                    return new Object[]{typeName, baseAttrUse.fAttrDecl.fName, "derivation-ok-restriction.3"};
                }
            }
        }
        if (this.fAttributeWC != null) {
            if (baseGroup.fAttributeWC == null) {
                return new Object[]{typeName, "derivation-ok-restriction.4.1"};
            } else if (!this.fAttributeWC.isSubsetOf(baseGroup.fAttributeWC)) {
                return new Object[]{typeName, "derivation-ok-restriction.4.2"};
            } else if (this.fAttributeWC.weakerProcessContents(baseGroup.fAttributeWC)) {
                return new Object[]{typeName, this.fAttributeWC.getProcessContentsAsString(), baseGroup.fAttributeWC.getProcessContentsAsString(), "derivation-ok-restriction.4.3"};
            }
        }
        return null;
    }

    static final XSAttributeUseImpl[] resize(XSAttributeUseImpl[] oldArray, int newSize) {
        XSAttributeUseImpl[] newArray = new XSAttributeUseImpl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        for (int i = 0; i < this.fAttrUseNum; i++) {
            this.fAttributeUses[i] = null;
        }
        this.fAttrUseNum = 0;
        this.fAttributeWC = null;
        this.fAnnotations = null;
        this.fIDAttrName = null;
    }

    public short getType() {
        return (short) 5;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSObjectList getAttributeUses() {
        if (this.fAttrUses == null) {
            this.fAttrUses = new XSObjectListImpl(this.fAttributeUses, this.fAttrUseNum);
        }
        return this.fAttrUses;
    }

    public XSWildcard getAttributeWildcard() {
        return this.fAttributeWC;
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
}
