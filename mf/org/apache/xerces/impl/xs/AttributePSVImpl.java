package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class AttributePSVImpl implements AttributePSVI {
    protected XSAttributeDeclaration fDeclaration = null;
    protected String[] fErrors = null;
    protected boolean fSpecified = false;
    protected XSTypeDefinition fTypeDecl = null;
    protected short fValidationAttempted = (short) 0;
    protected String fValidationContext = null;
    protected short fValidity = (short) 0;
    protected ValidatedInfo fValue = new ValidatedInfo();

    public String getSchemaDefault() {
        return this.fDeclaration == null ? null : this.fDeclaration.getConstraintValue();
    }

    public String getSchemaNormalizedValue() {
        return this.fValue.getNormalizedValue();
    }

    public boolean getIsSchemaSpecified() {
        return this.fSpecified;
    }

    public short getValidationAttempted() {
        return this.fValidationAttempted;
    }

    public short getValidity() {
        return this.fValidity;
    }

    public StringList getErrorCodes() {
        if (this.fErrors == null || this.fErrors.length == 0) {
            return StringListImpl.EMPTY_LIST;
        }
        return new PSVIErrorList(this.fErrors, true);
    }

    public StringList getErrorMessages() {
        if (this.fErrors == null || this.fErrors.length == 0) {
            return StringListImpl.EMPTY_LIST;
        }
        return new PSVIErrorList(this.fErrors, false);
    }

    public String getValidationContext() {
        return this.fValidationContext;
    }

    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }

    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fValue.getMemberTypeDefinition();
    }

    public XSAttributeDeclaration getAttributeDeclaration() {
        return this.fDeclaration;
    }

    public Object getActualNormalizedValue() {
        return this.fValue.getActualValue();
    }

    public short getActualNormalizedValueType() {
        return this.fValue.getActualValueType();
    }

    public ShortList getItemValueTypes() {
        return this.fValue.getListValueTypes();
    }

    public XSValue getSchemaValue() {
        return this.fValue;
    }

    public void reset() {
        this.fValue.reset();
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
    }
}
