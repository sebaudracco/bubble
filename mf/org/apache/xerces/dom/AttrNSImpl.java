package mf.org.apache.xerces.dom;

import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.w3c.dom.DOMException;

public class AttrNSImpl extends AttrImpl {
    static final long serialVersionUID = -781906615369795414L;
    static final String xmlURI = "http://www.w3.org/XML/1998/namespace";
    static final String xmlnsURI = "http://www.w3.org/2000/xmlns/";
    protected String localName;
    protected String namespaceURI;

    protected AttrNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName) {
        super(ownerDocument, qualifiedName);
        setName(namespaceURI, qualifiedName);
    }

    private void setName(String namespaceURI, String qname) {
        CoreDocumentImpl ownerDocument = ownerDocument();
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
        int colon1 = qname.indexOf(58);
        int colon2 = qname.lastIndexOf(58);
        ownerDocument.checkNamespaceWF(qname, colon1, colon2);
        if (colon1 < 0) {
            this.localName = qname;
            if (ownerDocument.errorChecking) {
                ownerDocument.checkQName(null, this.localName);
                if ((qname.equals(XMLConstants.XMLNS_ATTRIBUTE) && (namespaceURI == null || !namespaceURI.equals(NamespaceContext.XMLNS_URI))) || (namespaceURI != null && namespaceURI.equals(NamespaceContext.XMLNS_URI) && !qname.equals(XMLConstants.XMLNS_ATTRIBUTE))) {
                    throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                }
                return;
            }
            return;
        }
        String prefix = qname.substring(0, colon1);
        this.localName = qname.substring(colon2 + 1);
        ownerDocument.checkQName(prefix, this.localName);
        ownerDocument.checkDOMNSErr(prefix, namespaceURI);
    }

    public AttrNSImpl(CoreDocumentImpl ownerDocument, String namespaceURI, String qualifiedName, String localName) {
        super(ownerDocument, qualifiedName);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
    }

    protected AttrNSImpl(CoreDocumentImpl ownerDocument, String value) {
        super(ownerDocument, value);
    }

    void rename(String namespaceURI, String qualifiedName) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.name = qualifiedName;
        setName(namespaceURI, qualifiedName);
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
        if (ownerDocument().errorChecking) {
            if (isReadOnly()) {
                throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            } else if (!(prefix == null || prefix.length() == 0)) {
                if (!CoreDocumentImpl.isXMLName(prefix, ownerDocument().isXML11Version())) {
                    throw new DOMException((short) 5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                } else if (this.namespaceURI == null || prefix.indexOf(58) >= 0) {
                    throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                } else if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                    if (!this.namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                        throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    }
                } else if (prefix.equals("xml")) {
                    if (!this.namespaceURI.equals("http://www.w3.org/XML/1998/namespace")) {
                        throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                    }
                } else if (this.name.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
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

    public String getTypeName() {
        if (this.type == null) {
            return null;
        }
        if (this.type instanceof XSSimpleTypeDecl) {
            return ((XSSimpleTypeDecl) this.type).getName();
        }
        return (String) this.type;
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        if (this.type == null || !(this.type instanceof XSSimpleTypeDecl)) {
            return false;
        }
        return ((XSSimpleTypeDecl) this.type).isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }

    public String getTypeNamespace() {
        if (this.type == null) {
            return null;
        }
        if (this.type instanceof XSSimpleTypeDecl) {
            return ((XSSimpleTypeDecl) this.type).getNamespace();
        }
        return "http://www.w3.org/TR/REC-xml";
    }
}
