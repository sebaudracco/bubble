package mf.org.apache.xerces.stax;

import java.util.Iterator;
import mf.javax.xml.namespace.NamespaceContext;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.XMLEventFactory;
import mf.javax.xml.stream.events.Attribute;
import mf.javax.xml.stream.events.Characters;
import mf.javax.xml.stream.events.Comment;
import mf.javax.xml.stream.events.DTD;
import mf.javax.xml.stream.events.EndDocument;
import mf.javax.xml.stream.events.EndElement;
import mf.javax.xml.stream.events.EntityDeclaration;
import mf.javax.xml.stream.events.EntityReference;
import mf.javax.xml.stream.events.Namespace;
import mf.javax.xml.stream.events.ProcessingInstruction;
import mf.javax.xml.stream.events.StartDocument;
import mf.javax.xml.stream.events.StartElement;
import mf.org.apache.xerces.stax.events.AttributeImpl;
import mf.org.apache.xerces.stax.events.CharactersImpl;
import mf.org.apache.xerces.stax.events.CommentImpl;
import mf.org.apache.xerces.stax.events.DTDImpl;
import mf.org.apache.xerces.stax.events.EndDocumentImpl;
import mf.org.apache.xerces.stax.events.EndElementImpl;
import mf.org.apache.xerces.stax.events.EntityReferenceImpl;
import mf.org.apache.xerces.stax.events.NamespaceImpl;
import mf.org.apache.xerces.stax.events.ProcessingInstructionImpl;
import mf.org.apache.xerces.stax.events.StartDocumentImpl;
import mf.org.apache.xerces.stax.events.StartElementImpl;

public final class XMLEventFactoryImpl extends XMLEventFactory {
    private Location fLocation;

    public void setLocation(Location location) {
        this.fLocation = location;
    }

    public Attribute createAttribute(String prefix, String namespaceURI, String localName, String value) {
        return createAttribute(new QName(namespaceURI, localName, prefix), value);
    }

    public Attribute createAttribute(String localName, String value) {
        return createAttribute(new QName(localName), value);
    }

    public Attribute createAttribute(QName name, String value) {
        return new AttributeImpl(name, value, "CDATA", true, this.fLocation);
    }

    public Namespace createNamespace(String namespaceURI) {
        return createNamespace("", namespaceURI);
    }

    public Namespace createNamespace(String prefix, String namespaceUri) {
        return new NamespaceImpl(prefix, namespaceUri, this.fLocation);
    }

    public StartElement createStartElement(QName name, Iterator attributes, Iterator namespaces) {
        return createStartElement(name, attributes, namespaces, null);
    }

    public StartElement createStartElement(String prefix, String namespaceUri, String localName) {
        return createStartElement(new QName(namespaceUri, localName, prefix), null, null);
    }

    public StartElement createStartElement(String prefix, String namespaceUri, String localName, Iterator attributes, Iterator namespaces) {
        return createStartElement(new QName(namespaceUri, localName, prefix), attributes, namespaces);
    }

    public StartElement createStartElement(String prefix, String namespaceUri, String localName, Iterator attributes, Iterator namespaces, NamespaceContext context) {
        return createStartElement(new QName(namespaceUri, localName, prefix), attributes, namespaces, context);
    }

    private StartElement createStartElement(QName name, Iterator attributes, Iterator namespaces, NamespaceContext context) {
        return new StartElementImpl(name, attributes, namespaces, context, this.fLocation);
    }

    public EndElement createEndElement(QName name, Iterator namespaces) {
        return new EndElementImpl(name, namespaces, this.fLocation);
    }

    public EndElement createEndElement(String prefix, String namespaceUri, String localName) {
        return createEndElement(new QName(namespaceUri, localName, prefix), null);
    }

    public EndElement createEndElement(String prefix, String namespaceUri, String localName, Iterator namespaces) {
        return createEndElement(new QName(namespaceUri, localName, prefix), namespaces);
    }

    public Characters createCharacters(String content) {
        return new CharactersImpl(content, 4, this.fLocation);
    }

    public Characters createCData(String content) {
        return new CharactersImpl(content, 12, this.fLocation);
    }

    public Characters createSpace(String content) {
        return createCharacters(content);
    }

    public Characters createIgnorableSpace(String content) {
        return new CharactersImpl(content, 6, this.fLocation);
    }

    public StartDocument createStartDocument() {
        return createStartDocument(null, null);
    }

    public StartDocument createStartDocument(String encoding, String version, boolean standalone) {
        return new StartDocumentImpl(encoding, encoding != null, standalone, true, version, this.fLocation);
    }

    public StartDocument createStartDocument(String encoding, String version) {
        return new StartDocumentImpl(encoding, encoding != null, false, false, version, this.fLocation);
    }

    public StartDocument createStartDocument(String encoding) {
        return createStartDocument(encoding, null);
    }

    public EndDocument createEndDocument() {
        return new EndDocumentImpl(this.fLocation);
    }

    public EntityReference createEntityReference(String name, EntityDeclaration declaration) {
        return new EntityReferenceImpl(name, declaration, this.fLocation);
    }

    public Comment createComment(String text) {
        return new CommentImpl(text, this.fLocation);
    }

    public ProcessingInstruction createProcessingInstruction(String target, String data) {
        return new ProcessingInstructionImpl(target, data, this.fLocation);
    }

    public DTD createDTD(String dtd) {
        return new DTDImpl(dtd, this.fLocation);
    }
}
