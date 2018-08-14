package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.xs.ElementPSVI;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class ElementPSVImpl implements ElementPSVI {
    protected XSElementDeclaration fDeclaration = null;
    protected String[] fErrors = null;
    protected SchemaGrammar[] fGrammars = null;
    protected boolean fNil = false;
    protected XSNotationDeclaration fNotation = null;
    protected XSModel fSchemaInformation = null;
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

    public boolean getNil() {
        return this.fNil;
    }

    public XSNotationDeclaration getNotation() {
        return this.fNotation;
    }

    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }

    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fValue.getMemberTypeDefinition();
    }

    public XSElementDeclaration getElementDeclaration() {
        return this.fDeclaration;
    }

    public synchronized XSModel getSchemaInformation() {
        if (this.fSchemaInformation == null && this.fGrammars != null) {
            this.fSchemaInformation = new XSModelImpl(this.fGrammars);
        }
        return this.fSchemaInformation;
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
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
        this.fValue.reset();
    }

    public void copySchemaInformationTo(ElementPSVImpl target) {
        target.fGrammars = this.fGrammars;
        target.fSchemaInformation = this.fSchemaInformation;
    }
}
