package mf.org.apache.xerces.jaxp.validation;

import java.util.Iterator;
import java.util.NoSuchElementException;
import mf.javax.xml.namespace.NamespaceContext;
import mf.javax.xml.stream.XMLEventFactory;
import mf.javax.xml.stream.XMLEventWriter;
import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.stream.XMLStreamReader;
import mf.javax.xml.stream.events.Characters;
import mf.javax.xml.stream.events.Comment;
import mf.javax.xml.stream.events.DTD;
import mf.javax.xml.stream.events.EndDocument;
import mf.javax.xml.stream.events.EntityReference;
import mf.javax.xml.stream.events.ProcessingInstruction;
import mf.javax.xml.stream.events.StartDocument;
import mf.javax.xml.stream.events.XMLEvent;
import mf.javax.xml.transform.stax.StAXResult;
import mf.org.apache.xerces.util.JAXPNamespaceContextWrapper;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;

final class StAXEventResultBuilder implements StAXDocumentHandler {
    private static final Iterator EMPTY_COLLECTION_ITERATOR = new C46491();
    private final QName fAttrName = new QName();
    private final XMLEventFactory fEventFactory;
    private XMLEventWriter fEventWriter;
    private boolean fIgnoreChars;
    private boolean fInCDATA;
    private final JAXPNamespaceContextWrapper fNamespaceContext;
    private final StAXValidatorHelper fStAXValidatorHelper;

    class C46491 implements Iterator {
        C46491() {
        }

        public boolean hasNext() {
            return false;
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    final class AttributeIterator implements Iterator {
        XMLAttributes fAttributes;
        int fEnd;
        int fIndex = 0;

        AttributeIterator(XMLAttributes attributes, int length) {
            this.fAttributes = attributes;
            this.fEnd = length;
        }

        public boolean hasNext() {
            if (this.fIndex < this.fEnd) {
                return true;
            }
            this.fAttributes = null;
            return false;
        }

        public Object next() {
            if (hasNext()) {
                this.fAttributes.getName(this.fIndex, StAXEventResultBuilder.this.fAttrName);
                XMLEventFactory access$1 = StAXEventResultBuilder.this.fEventFactory;
                String str = StAXEventResultBuilder.this.fAttrName.prefix;
                String str2 = StAXEventResultBuilder.this.fAttrName.uri != null ? StAXEventResultBuilder.this.fAttrName.uri : "";
                String str3 = StAXEventResultBuilder.this.fAttrName.localpart;
                XMLAttributes xMLAttributes = this.fAttributes;
                int i = this.fIndex;
                this.fIndex = i + 1;
                return access$1.createAttribute(str, str2, str3, xMLAttributes.getValue(i));
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    final class NamespaceIterator implements Iterator {
        int fEnd;
        int fIndex = 0;
        NamespaceContext fNC;

        NamespaceIterator(int length) {
            this.fNC = StAXEventResultBuilder.this.fNamespaceContext.getNamespaceContext();
            this.fEnd = length;
        }

        public boolean hasNext() {
            if (this.fIndex < this.fEnd) {
                return true;
            }
            this.fNC = null;
            return false;
        }

        public Object next() {
            if (hasNext()) {
                JAXPNamespaceContextWrapper access$2 = StAXEventResultBuilder.this.fNamespaceContext;
                int i = this.fIndex;
                this.fIndex = i + 1;
                String prefix = access$2.getDeclaredPrefixAt(i);
                String uri = this.fNC.getNamespaceURI(prefix);
                XMLEventFactory access$1;
                if (prefix.length() == 0) {
                    access$1 = StAXEventResultBuilder.this.fEventFactory;
                    if (uri == null) {
                        uri = "";
                    }
                    return access$1.createNamespace(uri);
                }
                access$1 = StAXEventResultBuilder.this.fEventFactory;
                if (uri == null) {
                    uri = "";
                }
                return access$1.createNamespace(prefix, uri);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public StAXEventResultBuilder(StAXValidatorHelper helper, JAXPNamespaceContextWrapper context) {
        this.fStAXValidatorHelper = helper;
        this.fNamespaceContext = context;
        this.fEventFactory = XMLEventFactory.newInstance();
    }

    public void setStAXResult(StAXResult result) {
        this.fIgnoreChars = false;
        this.fInCDATA = false;
        this.fEventWriter = result != null ? result.getXMLEventWriter() : null;
    }

    public void startDocument(XMLStreamReader reader) throws XMLStreamException {
        String version = reader.getVersion();
        String encoding = reader.getCharacterEncodingScheme();
        boolean standalone = reader.standaloneSet();
        XMLEventWriter xMLEventWriter = this.fEventWriter;
        XMLEventFactory xMLEventFactory = this.fEventFactory;
        if (encoding == null) {
            encoding = "UTF-8";
        }
        if (version == null) {
            version = "1.0";
        }
        xMLEventWriter.add(xMLEventFactory.createStartDocument(encoding, version, standalone));
    }

    public void endDocument(XMLStreamReader reader) throws XMLStreamException {
        this.fEventWriter.add(this.fEventFactory.createEndDocument());
        this.fEventWriter.flush();
    }

    public void comment(XMLStreamReader reader) throws XMLStreamException {
        this.fEventWriter.add(this.fEventFactory.createComment(reader.getText()));
    }

    public void processingInstruction(XMLStreamReader reader) throws XMLStreamException {
        String data = reader.getPIData();
        XMLEventWriter xMLEventWriter = this.fEventWriter;
        XMLEventFactory xMLEventFactory = this.fEventFactory;
        String pITarget = reader.getPITarget();
        if (data == null) {
            data = "";
        }
        xMLEventWriter.add(xMLEventFactory.createProcessingInstruction(pITarget, data));
    }

    public void entityReference(XMLStreamReader reader) throws XMLStreamException {
        String name = reader.getLocalName();
        this.fEventWriter.add(this.fEventFactory.createEntityReference(name, this.fStAXValidatorHelper.getEntityDeclaration(name)));
    }

    public void startDocument(StartDocument event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void endDocument(EndDocument event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
        this.fEventWriter.flush();
    }

    public void doctypeDecl(DTD event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void characters(Characters event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void cdata(Characters event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void comment(Comment event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void processingInstruction(ProcessingInstruction event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void entityReference(EntityReference event) throws XMLStreamException {
        this.fEventWriter.add((XMLEvent) event);
    }

    public void setIgnoringCharacters(boolean ignore) {
        this.fIgnoreChars = ignore;
    }

    public void startDocument(XMLLocator locator, String encoding, mf.org.apache.xerces.xni.NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        try {
            int length = attributes.getLength();
            if (length == 0) {
                XMLEvent start = this.fStAXValidatorHelper.getCurrentEvent();
                if (start != null) {
                    this.fEventWriter.add(start);
                    return;
                }
            }
            this.fEventWriter.add(this.fEventFactory.createStartElement(element.prefix, element.uri != null ? element.uri : "", element.localpart, getAttributeIterator(attributes, length), getNamespaceIterator(), this.fNamespaceContext.getNamespaceContext()));
        } catch (Exception e) {
            throw new XNIException(e);
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (!this.fIgnoreChars) {
            try {
                if (this.fInCDATA) {
                    this.fEventWriter.add(this.fEventFactory.createCData(text.toString()));
                } else {
                    this.fEventWriter.add(this.fEventFactory.createCharacters(text.toString()));
                }
            } catch (Exception e) {
                throw new XNIException(e);
            }
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        characters(text, augs);
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        try {
            XMLEvent end = this.fStAXValidatorHelper.getCurrentEvent();
            if (end != null) {
                this.fEventWriter.add(end);
            } else {
                this.fEventWriter.add(this.fEventFactory.createEndElement(element.prefix, element.uri, element.localpart, getNamespaceIterator()));
            }
        } catch (Exception e) {
            throw new XNIException(e);
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        this.fInCDATA = true;
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        this.fInCDATA = false;
    }

    public void endDocument(Augmentations augs) throws XNIException {
    }

    public void setDocumentSource(XMLDocumentSource source) {
    }

    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    private Iterator getAttributeIterator(XMLAttributes attributes, int length) {
        return length > 0 ? new AttributeIterator(attributes, length) : EMPTY_COLLECTION_ITERATOR;
    }

    private Iterator getNamespaceIterator() {
        int length = this.fNamespaceContext.getDeclaredPrefixCount();
        return length > 0 ? new NamespaceIterator(length) : EMPTY_COLLECTION_ITERATOR;
    }
}
