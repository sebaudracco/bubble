package mf.org.apache.xerces.dom;

import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;

public class ElementNSImpl extends ElementImpl {
    static final long serialVersionUID = -9142310625494392642L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    protected String localName;
    protected String namespaceURI;
    transient XSTypeDefinition type;

    protected ElementNSImpl() {
    }

    protected ElementNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName) throws DOMException {
        super(ownerDocument, qualifiedName);
        setName(namespaceURI, qualifiedName);
    }

    private void setName(String namespaceURI, String qname) {
        this.namespaceURI = namespaceURI;
        if (namespaceURI != null) {
            String str;
            if (namespaceURI.length() == 0) {
                str = null;
            } else {
                str = namespaceURI;
            }
            this.namespaceURI = str;
        }
        if (qname == null) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
        int colon1 = qname.indexOf(58);
        int colon2 = qname.lastIndexOf(58);
        this.ownerDocument.checkNamespaceWF(qname, colon1, colon2);
        if (colon1 < 0) {
            this.localName = qname;
            if (this.ownerDocument.errorChecking) {
                this.ownerDocument.checkQName(null, this.localName);
                if ((qname.equals(XMLConstants.XMLNS_ATTRIBUTE) && (namespaceURI == null || !namespaceURI.equals(NamespaceContext.XMLNS_URI))) || (namespaceURI != null && namespaceURI.equals(NamespaceContext.XMLNS_URI) && !qname.equals(XMLConstants.XMLNS_ATTRIBUTE))) {
                    throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                }
                return;
            }
            return;
        }
        String prefix = qname.substring(0, colon1);
        this.localName = qname.substring(colon2 + 1);
        if (!this.ownerDocument.errorChecking) {
            return;
        }
        if (namespaceURI == null || (prefix.equals("xml") && !namespaceURI.equals(NamespaceContext.XML_URI))) {
            throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
        }
        this.ownerDocument.checkQName(prefix, this.localName);
        this.ownerDocument.checkDOMNSErr(prefix, namespaceURI);
    }

    protected ElementNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName, String localName) throws DOMException {
        super(ownerDocument, qualifiedName);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }

    protected ElementNSImpl(CoreDocumentImpl ownerDocument, String value) {
        super(ownerDocument, value);
    }

    void rename(String namespaceURI, String qualifiedName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.name = qualifiedName;
        setName(namespaceURI, qualifiedName);
        reconcileDefaultAttributes();
    }

    public String getNamespaceURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.namespaceURI;
    }

    public String getPrefix() {
        if (needsSyncData()) {
            synchronizeData();
        }
        int index = this.name.indexOf(58);
        return index < 0 ? null : this.name.substring(0, index);
    }

    public void setPrefix(String prefix) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (!(prefix == null || prefix.length() == 0)) {
                if (!CoreDocumentImpl.isXMLName(prefix, this.ownerDocument.isXML11Version())) {
                    throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                } else if (this.namespaceURI == null || prefix.indexOf(58) >= 0) {
                    throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                } else if (prefix.equals("xml") && !this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                    throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                }
            }
        }
        if (prefix == null || prefix.length() == 0) {
            this.name = this.localName;
        } else {
            this.name = new StringBuilder(String.valueOf(prefix)).append(":").append(this.localName).toString();
        }
    }

    public String getLocalName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.localName;
    }

    protected Attr getXMLBaseAttribute() {
        return (Attr) this.attributes.getNamedItemNS("http://www.w3.org/XML/1998/namespace", "base");
    }

    public String getTypeName() {
        if (this.type != null) {
            if (this.type instanceof XSSimpleTypeDecl) {
                return ((XSSimpleTypeDecl) this.type).getTypeName();
            }
            if (this.type instanceof XSComplexTypeDecl) {
                return ((XSComplexTypeDecl) this.type).getTypeName();
            }
        }
        return null;
    }

    public String getTypeNamespace() {
        if (this.type != null) {
            return this.type.getNamespace();
        }
        return null;
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.type != null) {
            if (this.type instanceof XSSimpleTypeDecl) {
                return ((XSSimpleTypeDecl) this.type).isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
            }
            if (this.type instanceof XSComplexTypeDecl) {
                return ((XSComplexTypeDecl) this.type).isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
            }
        }
        return false;
    }

    public void setType(XSTypeDefinition type) {
        this.type = type;
    }
}
