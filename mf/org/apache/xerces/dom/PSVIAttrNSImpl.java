package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSValue;

public class PSVIAttrNSImpl extends AttrNSImpl implements AttributePSVI {
    static final long serialVersionUID = -3241738699421018889L;
    protected XSAttributeDeclaration fDeclaration = null;
    protected StringList fErrorCodes = null;
    protected StringList fErrorMessages = null;
    protected boolean fSpecified = true;
    protected XSTypeDefinition fTypeDecl = null;
    protected short fValidationAttempted = (short) 0;
    protected String fValidationContext = null;
    protected short fValidity = (short) 0;
    protected ValidatedInfo fValue = new ValidatedInfo();

    public PSVIAttrNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName, String localName) {
        super(ownerDocument, namespaceURI, qualifiedName, localName);
    }

    public PSVIAttrNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName) {
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

    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }

    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fValue.getMemberTypeDefinition();
    }

    public XSAttributeDeclaration getAttributeDeclaration() {
        return this.fDeclaration;
    }

    public void setPSVI(AttributePSVI attr) {
        this.fDeclaration = attr.getAttributeDeclaration();
        this.fValidationContext = attr.getValidationContext();
        this.fValidity = attr.getValidity();
        this.fValidationAttempted = attr.getValidationAttempted();
        this.fErrorCodes = attr.getErrorCodes();
        this.fErrorMessages = attr.getErrorMessages();
        this.fValue.copyFrom(attr.getSchemaValue());
        this.fTypeDecl = attr.getTypeDefinition();
        this.fSpecified = attr.getIsSchemaSpecified();
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
