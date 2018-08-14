package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.xs.ElementPSVI;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI {
    static final long serialVersionUID = 6815489624636016068L;
    protected XSElementDeclaration fDeclaration = null;
    protected StringList fErrorCodes = null;
    protected StringList fErrorMessages = null;
    protected boolean fNil = false;
    protected XSNotationDeclaration fNotation = null;
    protected XSModel fSchemaInformation = null;
    protected boolean fSpecified = true;
    protected XSTypeDefinition fTypeDecl = null;
    protected short fValidationAttempted = (short) 0;
    protected String fValidationContext = null;
    protected short fValidity = (short) 0;
    protected ValidatedInfo fValue = new ValidatedInfo();

    public PSVIElementNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName, String localName) {
        super(ownerDocument, namespaceURI, qualifiedName, localName);
    }

    public PSVIElementNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName) {
        super(ownerDocument, namespaceURI, qualifiedName);
    }

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
        if (this.fErrorCodes != null) {
            return this.fErrorCodes;
        }
        return StringListImpl.EMPTY_LIST;
    }

    public StringList getErrorMessages() {
        if (this.fErrorMessages != null) {
            return this.fErrorMessages;
        }
        return StringListImpl.EMPTY_LIST;
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

    public XSModel getSchemaInformation() {
        return this.fSchemaInformation;
    }

    public void setPSVI(ElementPSVI elem) {
        this.fDeclaration = elem.getElementDeclaration();
        this.fNotation = elem.getNotation();
        this.fValidationContext = elem.getValidationContext();
        this.fTypeDecl = elem.getTypeDefinition();
        this.fSchemaInformation = elem.getSchemaInformation();
        this.fValidity = elem.getValidity();
        this.fValidationAttempted = elem.getValidationAttempted();
        this.fErrorCodes = elem.getErrorCodes();
        this.fErrorMessages = elem.getErrorMessages();
        if ((this.fTypeDecl instanceof XSSimpleTypeDefinition) || ((this.fTypeDecl instanceof XSComplexTypeDefinition) && ((XSComplexTypeDefinition) this.fTypeDecl).getContentType() == (short) 1)) {
            this.fValue.copyFrom(elem.getSchemaValue());
        } else {
            this.fValue.reset();
        }
        this.fSpecified = elem.getIsSchemaSpecified();
        this.fNil = elem.getNil();
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException(getClass().getName());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(getClass().getName());
    }
}
