package mf.org.apache.xerces.jaxp;

import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;

class TeeXMLDocumentFilterImpl implements XMLDocumentFilter {
    private XMLDocumentHandler next;
    private XMLDocumentHandler side;
    private XMLDocumentSource source;

    TeeXMLDocumentFilterImpl() {
    }

    public XMLDocumentHandler getSide() {
        return this.side;
    }

    public void setSide(XMLDocumentHandler side) {
        this.side = side;
    }

    public XMLDocumentSource getDocumentSource() {
        return this.source;
    }

    public void setDocumentSource(XMLDocumentSource source) {
        this.source = source;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.next;
    }

    public void setDocumentHandler(XMLDocumentHandler handler) {
        this.next = handler;
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        this.side.characters(text, augs);
        this.next.characters(text, augs);
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        this.side.comment(text, augs);
        this.next.comment(text, augs);
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
        this.side.doctypeDecl(rootElement, publicId, systemId, augs);
        this.next.doctypeDecl(rootElement, publicId, systemId, augs);
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.side.emptyElement(element, attributes, augs);
        this.next.emptyElement(element, attributes, augs);
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        this.side.endCDATA(augs);
        this.next.endCDATA(augs);
    }

    public void endDocument(Augmentations augs) throws XNIException {
        this.side.endDocument(augs);
        this.next.endDocument(augs);
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        this.side.endElement(element, augs);
        this.next.endElement(element, augs);
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        this.side.endGeneralEntity(name, augs);
        this.next.endGeneralEntity(name, augs);
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        this.side.ignorableWhitespace(text, augs);
        this.next.ignorableWhitespace(text, augs);
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        this.side.processingInstruction(target, data, augs);
        this.next.processingInstruction(target, data, augs);
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        this.side.startCDATA(augs);
        this.next.startCDATA(augs);
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        this.side.startDocument(locator, encoding, namespaceContext, augs);
        this.next.startDocument(locator, encoding, namespaceContext, augs);
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        this.side.startElement(element, attributes, augs);
        this.next.startElement(element, attributes, augs);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        this.side.startGeneralEntity(name, identifier, encoding, augs);
        this.next.startGeneralEntity(name, identifier, encoding, augs);
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        this.side.textDecl(version, encoding, augs);
        this.next.textDecl(version, encoding, augs);
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        this.side.xmlDecl(version, encoding, standalone, augs);
        this.next.xmlDecl(version, encoding, standalone, augs);
    }
}
