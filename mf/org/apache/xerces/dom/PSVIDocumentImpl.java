package mf.org.apache.xerces.dom;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;

public class PSVIDocumentImpl extends DocumentImpl {
    static final long serialVersionUID = -8822220250676434522L;

    public PSVIDocumentImpl(DocumentType doctype) {
        super(doctype);
    }

    public Node cloneNode(boolean deep) {
        PSVIDocumentImpl newdoc = new PSVIDocumentImpl();
        callUserDataHandlers(this, newdoc, (short) 1);
        cloneNode(newdoc, deep);
        newdoc.mutationEvents = this.mutationEvents;
        return newdoc;
    }

    public DOMImplementation getImplementation() {
        return PSVIDOMImplementationImpl.getDOMImplementation();
    }

    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new PSVIElementNSImpl(this, namespaceURI, qualifiedName);
    }

    public Element createElementNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
        return new PSVIElementNSImpl(this, namespaceURI, qualifiedName, localpart);
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new PSVIAttrNSImpl(this, namespaceURI, qualifiedName);
    }

    public Attr createAttributeNS(String namespaceURI, String qualifiedName, String localName) throws DOMException {
        return new PSVIAttrNSImpl(this, namespaceURI, qualifiedName, localName);
    }

    public DOMConfiguration getDomConfig() {
        super.getDomConfig();
        return this.fConfiguration;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException(getClass().getName());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(getClass().getName());
    }
}
