package mf.org.apache.xerces.impl.xs.traversers;

import java.util.Stack;
import java.util.Vector;
import mf.org.apache.xerces.impl.validation.ValidationState;
import mf.org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.impl.xs.opti.SchemaDOM;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;

class XSDocumentInfo {
    protected Stack SchemaNamespaceSupportStack = new Stack();
    protected XSAnnotationInfo fAnnotations = null;
    protected boolean fAreLocalAttributesQualified;
    protected boolean fAreLocalElementsQualified;
    protected XSAttributeChecker fAttrChecker;
    protected short fBlockDefault;
    protected short fFinalDefault;
    Vector fImportedNS = new Vector();
    protected boolean fIsChameleonSchema;
    protected SchemaNamespaceSupport fNamespaceSupport;
    protected SchemaNamespaceSupport fNamespaceSupportRoot;
    private Vector fReportedTNS = null;
    protected Object[] fSchemaAttrs;
    protected Element fSchemaElement;
    SymbolTable fSymbolTable = null;
    String fTargetNamespace;
    protected ValidationState fValidationContext = new ValidationState();

    XSDocumentInfo(Element schemaRoot, XSAttributeChecker attrChecker, SymbolTable symbolTable) throws XMLSchemaException {
        boolean z = true;
        this.fSchemaElement = schemaRoot;
        this.fNamespaceSupport = new SchemaNamespaceSupport(schemaRoot, symbolTable);
        this.fNamespaceSupport.reset();
        this.fIsChameleonSchema = false;
        this.fSymbolTable = symbolTable;
        this.fAttrChecker = attrChecker;
        if (schemaRoot != null) {
            this.fSchemaAttrs = attrChecker.checkAttributes(schemaRoot, true, this);
            if (this.fSchemaAttrs == null) {
                throw new XMLSchemaException(null, null);
            }
            boolean z2;
            if (((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.fAreLocalAttributesQualified = z2;
            if (((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() != 1) {
                z = false;
            }
            this.fAreLocalElementsQualified = z;
            this.fBlockDefault = ((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
            this.fFinalDefault = ((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
            this.fTargetNamespace = (String) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
            if (this.fTargetNamespace != null) {
                this.fTargetNamespace = symbolTable.addSymbol(this.fTargetNamespace);
            }
            this.fNamespaceSupportRoot = new SchemaNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setSymbolTable(symbolTable);
        }
    }

    void backupNSSupport(SchemaNamespaceSupport nsSupport) {
        this.SchemaNamespaceSupportStack.push(this.fNamespaceSupport);
        if (nsSupport == null) {
            nsSupport = this.fNamespaceSupportRoot;
        }
        this.fNamespaceSupport = new SchemaNamespaceSupport(nsSupport);
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }

    void restoreNSSupport() {
        this.fNamespaceSupport = (SchemaNamespaceSupport) this.SchemaNamespaceSupportStack.pop();
        this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (this.fTargetNamespace == null) {
            buf.append("no targetNamspace");
        } else {
            buf.append("targetNamespace is ");
            buf.append(this.fTargetNamespace);
        }
        Document doc = this.fSchemaElement != null ? this.fSchemaElement.getOwnerDocument() : null;
        if (doc instanceof SchemaDOM) {
            String documentURI = doc.getDocumentURI();
            if (documentURI != null && documentURI.length() > 0) {
                buf.append(" :: schemaLocation is ");
                buf.append(documentURI);
            }
        }
        return buf.toString();
    }

    public void addAllowedNS(String namespace) {
        Vector vector = this.fImportedNS;
        if (namespace == null) {
            namespace = "";
        }
        vector.addElement(namespace);
    }

    public boolean isAllowedNS(String namespace) {
        Vector vector = this.fImportedNS;
        if (namespace == null) {
            namespace = "";
        }
        return vector.contains(namespace);
    }

    final boolean needReportTNSError(String uri) {
        if (this.fReportedTNS == null) {
            this.fReportedTNS = new Vector();
        } else if (this.fReportedTNS.contains(uri)) {
            return false;
        }
        this.fReportedTNS.addElement(uri);
        return true;
    }

    Object[] getSchemaAttrs() {
        return this.fSchemaAttrs;
    }

    void returnSchemaAttrs() {
        this.fAttrChecker.returnAttrArray(this.fSchemaAttrs, null);
        this.fSchemaAttrs = null;
    }

    void addAnnotation(XSAnnotationInfo info) {
        info.next = this.fAnnotations;
        this.fAnnotations = info;
    }

    XSAnnotationInfo getAnnotations() {
        return this.fAnnotations;
    }

    void removeAnnotations() {
        this.fAnnotations = null;
    }
}
