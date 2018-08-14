package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSValue;

public class XSAttributeUseImpl implements XSAttributeUse {
    public XSObjectList fAnnotations = null;
    public XSAttributeDecl fAttrDecl = null;
    public short fConstraintType = (short) 0;
    public ValidatedInfo fDefault = null;
    public short fUse = (short) 0;

    public void reset() {
        this.fDefault = null;
        this.fAttrDecl = null;
        this.fUse = (short) 0;
        this.fConstraintType = (short) 0;
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 4;
    }

    public String getName() {
        return null;
    }

    public String getNamespace() {
        return null;
    }

    public boolean getRequired() {
        return this.fUse == (short) 1;
    }

    public XSAttributeDeclaration getAttrDeclaration() {
        return this.fAttrDecl;
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

    public XSNamespaceItem getNamespaceItem() {
        return null;
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

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }
}
