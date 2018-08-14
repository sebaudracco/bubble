package mf.org.apache.xml.resolver.readers;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
    private ContentHandler ch = null;
    private EntityResolver er = null;

    public void setEntityResolver(EntityResolver er) {
        this.er = er;
    }

    public void setContentHandler(ContentHandler ch) {
        this.ch = ch;
    }

    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
        InputSource inputSource = null;
        if (this.er != null) {
            try {
                inputSource = this.er.resolveEntity(publicId, systemId);
            } catch (IOException e) {
                System.out.println("resolveEntity threw IOException!");
            }
        }
        return inputSource;
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.ch != null) {
            this.ch.characters(ch, start, length);
        }
    }

    public void endDocument() throws SAXException {
        if (this.ch != null) {
            this.ch.endDocument();
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (this.ch != null) {
            this.ch.endElement(namespaceURI, localName, qName);
        }
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        if (this.ch != null) {
            this.ch.endPrefixMapping(prefix);
        }
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        if (this.ch != null) {
            this.ch.ignorableWhitespace(ch, start, length);
        }
    }

    public void processingInstruction(String target, String data) throws SAXException {
        if (this.ch != null) {
            this.ch.processingInstruction(target, data);
        }
    }

    public void setDocumentLocator(Locator locator) {
        if (this.ch != null) {
            this.ch.setDocumentLocator(locator);
        }
    }

    public void skippedEntity(String name) throws SAXException {
        if (this.ch != null) {
            this.ch.skippedEntity(name);
        }
    }

    public void startDocument() throws SAXException {
        if (this.ch != null) {
            this.ch.startDocument();
        }
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (this.ch != null) {
            this.ch.startElement(namespaceURI, localName, qName, atts);
        }
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (this.ch != null) {
            this.ch.startPrefixMapping(prefix, uri);
        }
    }
}
