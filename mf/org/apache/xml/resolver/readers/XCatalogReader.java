package mf.org.apache.xml.resolver.readers;

import java.util.Vector;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogEntry;
import mf.org.apache.xml.resolver.CatalogException;
import mf.org.apache.xml.resolver.helpers.PublicId;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XCatalogReader extends SAXCatalogReader implements SAXCatalogParser {
    protected Catalog catalog = null;

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
        this.debug = catalog.getCatalogManager().debug;
    }

    public Catalog getCatalog() {
        return this.catalog;
    }

    public XCatalogReader(SAXParserFactory parserFactory, Catalog catalog) {
        super(parserFactory);
        setCatalog(catalog);
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        int entryType = -1;
        Vector entryArgs = new Vector();
        if (localName.equals("Base")) {
            entryType = Catalog.BASE;
            entryArgs.add(atts.getValue("HRef"));
            this.debug.message(4, "Base", atts.getValue("HRef"));
        } else if (localName.equals("Delegate")) {
            entryType = Catalog.DELEGATE_PUBLIC;
            entryArgs.add(atts.getValue("PublicID"));
            entryArgs.add(atts.getValue("HRef"));
            this.debug.message(4, "Delegate", PublicId.normalize(atts.getValue("PublicID")), atts.getValue("HRef"));
        } else if (localName.equals("Extend")) {
            entryType = Catalog.CATALOG;
            entryArgs.add(atts.getValue("HRef"));
            this.debug.message(4, "Extend", atts.getValue("HRef"));
        } else if (localName.equals("Map")) {
            entryType = Catalog.PUBLIC;
            entryArgs.add(atts.getValue("PublicID"));
            entryArgs.add(atts.getValue("HRef"));
            this.debug.message(4, "Map", PublicId.normalize(atts.getValue("PublicID")), atts.getValue("HRef"));
        } else if (localName.equals("Remap")) {
            entryType = Catalog.SYSTEM;
            entryArgs.add(atts.getValue("SystemID"));
            entryArgs.add(atts.getValue("HRef"));
            this.debug.message(4, "Remap", atts.getValue("SystemID"), atts.getValue("HRef"));
        } else if (!localName.equals("XCatalog")) {
            this.debug.message(1, "Invalid catalog entry type", localName);
        }
        if (entryType >= 0) {
            try {
                this.catalog.addEntry(new CatalogEntry(entryType, entryArgs));
            } catch (CatalogException cex) {
                if (cex.getExceptionType() == 3) {
                    this.debug.message(1, "Invalid catalog entry type", localName);
                } else if (cex.getExceptionType() == 2) {
                    this.debug.message(1, "Invalid catalog entry", localName);
                }
            }
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    public void processingInstruction(String target, String data) throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }
}
