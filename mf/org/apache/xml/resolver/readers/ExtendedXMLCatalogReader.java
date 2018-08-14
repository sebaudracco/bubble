package mf.org.apache.xml.resolver.readers;

import java.util.Vector;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogEntry;
import mf.org.apache.xml.resolver.CatalogException;
import mf.org.apache.xml.resolver.Resolver;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ExtendedXMLCatalogReader extends OASISXMLCatalogReader {
    public static final String extendedNamespaceName = "http://nwalsh.com/xcatalog/1.0";

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        boolean inExtension = inExtensionNamespace();
        super.startElement(namespaceURI, localName, qName, atts);
        int entryType = -1;
        Vector entryArgs = new Vector();
        if (namespaceURI != null && extendedNamespaceName.equals(namespaceURI) && !inExtension) {
            if (atts.getValue("xml:base") != null) {
                String baseURI = atts.getValue("xml:base");
                entryType = Catalog.BASE;
                entryArgs.add(baseURI);
                this.baseURIStack.push(baseURI);
                this.debug.message(4, "xml:base", baseURI);
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, entryArgs));
                } catch (CatalogException cex) {
                    if (cex.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry (base)", localName);
                    }
                }
                entryType = -1;
                entryArgs = new Vector();
            } else {
                this.baseURIStack.push(this.baseURIStack.peek());
            }
            if (localName.equals("uriSuffix")) {
                if (checkAttributes(atts, "suffix", "uri")) {
                    entryType = Resolver.URISUFFIX;
                    entryArgs.add(atts.getValue("suffix"));
                    entryArgs.add(atts.getValue("uri"));
                    this.debug.message(4, "uriSuffix", atts.getValue("suffix"), atts.getValue("uri"));
                }
            } else if (!localName.equals("systemSuffix")) {
                this.debug.message(1, "Invalid catalog entry type", localName);
            } else if (checkAttributes(atts, "suffix", "uri")) {
                entryType = Resolver.SYSTEMSUFFIX;
                entryArgs.add(atts.getValue("suffix"));
                entryArgs.add(atts.getValue("uri"));
                this.debug.message(4, "systemSuffix", atts.getValue("suffix"), atts.getValue("uri"));
            }
            if (entryType >= 0) {
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, entryArgs));
                } catch (CatalogException cex2) {
                    if (cex2.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex2.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry", localName);
                    }
                }
            }
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        super.endElement(namespaceURI, localName, qName);
        boolean inExtension = inExtensionNamespace();
        Vector entryArgs = new Vector();
        if (namespaceURI != null && extendedNamespaceName.equals(namespaceURI) && !inExtension) {
            String baseURI = (String) this.baseURIStack.peek();
            if (!baseURI.equals((String) this.baseURIStack.pop())) {
                int entryType = Catalog.BASE;
                entryArgs.add(baseURI);
                this.debug.message(4, "(reset) xml:base", baseURI);
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, entryArgs));
                } catch (CatalogException cex) {
                    if (cex.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry (rbase)", localName);
                    }
                }
            }
        }
    }
}
