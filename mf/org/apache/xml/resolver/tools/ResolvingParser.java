package mf.org.apache.xml.resolver.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import mf.javax.xml.parsers.SAXParser;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.FileURL;
import org.xml.sax.AttributeList;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;

public class ResolvingParser implements Parser, DTDHandler, DocumentHandler, EntityResolver {
    public static boolean namespaceAware = true;
    public static boolean suppressExplanation = false;
    public static boolean validating = false;
    private boolean allowXMLCatalogPI = false;
    private URL baseURL = null;
    private CatalogManager catalogManager = CatalogManager.getStaticManager();
    private CatalogResolver catalogResolver = null;
    private DocumentHandler documentHandler = null;
    private DTDHandler dtdHandler = null;
    private boolean oasisXMLCatalogPI = false;
    private Parser parser = null;
    private CatalogResolver piCatalogResolver = null;
    private SAXParser saxParser = null;

    public ResolvingParser() {
        initParser();
    }

    public ResolvingParser(CatalogManager manager) {
        this.catalogManager = manager;
        initParser();
    }

    private void initParser() {
        this.catalogResolver = new CatalogResolver(this.catalogManager);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(namespaceAware);
        spf.setValidating(validating);
        try {
            this.saxParser = spf.newSAXParser();
            this.parser = this.saxParser.getParser();
            this.documentHandler = null;
            this.dtdHandler = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Catalog getCatalog() {
        return this.catalogResolver.getCatalog();
    }

    public void parse(InputSource input) throws IOException, SAXException {
        setupParse(input.getSystemId());
        try {
            this.parser.parse(input);
        } catch (InternalError ie) {
            explain(input.getSystemId());
            throw ie;
        }
    }

    public void parse(String systemId) throws IOException, SAXException {
        setupParse(systemId);
        try {
            this.parser.parse(systemId);
        } catch (InternalError ie) {
            explain(systemId);
            throw ie;
        }
    }

    public void setDocumentHandler(DocumentHandler handler) {
        this.documentHandler = handler;
    }

    public void setDTDHandler(DTDHandler handler) {
        this.dtdHandler = handler;
    }

    public void setEntityResolver(EntityResolver resolver) {
    }

    public void setErrorHandler(ErrorHandler handler) {
        this.parser.setErrorHandler(handler);
    }

    public void setLocale(Locale locale) throws SAXException {
        this.parser.setLocale(locale);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.documentHandler != null) {
            this.documentHandler.characters(ch, start, length);
        }
    }

    public void endDocument() throws SAXException {
        if (this.documentHandler != null) {
            this.documentHandler.endDocument();
        }
    }

    public void endElement(String name) throws SAXException {
        if (this.documentHandler != null) {
            this.documentHandler.endElement(name);
        }
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        if (this.documentHandler != null) {
            this.documentHandler.ignorableWhitespace(ch, start, length);
        }
    }

    public void processingInstruction(String target, String pidata) throws SAXException {
        if (target.equals("oasis-xml-catalog")) {
            URL catalog = null;
            String data = pidata;
            int pos = data.indexOf("catalog=");
            if (pos >= 0) {
                data = data.substring(pos + 8);
                if (data.length() > 1) {
                    String quote = data.substring(0, 1);
                    data = data.substring(1);
                    pos = data.indexOf(quote);
                    if (pos >= 0) {
                        data = data.substring(0, pos);
                        try {
                            catalog = this.baseURL != null ? new URL(this.baseURL, data) : new URL(data);
                        } catch (MalformedURLException e) {
                        }
                    }
                }
            }
            if (!this.allowXMLCatalogPI) {
                this.catalogManager.debug.message(3, "PI oasis-xml-catalog occurred in an invalid place: " + pidata);
            } else if (this.catalogManager.getAllowOasisXMLCatalogPI()) {
                this.catalogManager.debug.message(4, "oasis-xml-catalog PI", pidata);
                if (catalog != null) {
                    try {
                        this.catalogManager.debug.message(4, "oasis-xml-catalog", catalog.toString());
                        this.oasisXMLCatalogPI = true;
                        if (this.piCatalogResolver == null) {
                            this.piCatalogResolver = new CatalogResolver(true);
                        }
                        this.piCatalogResolver.getCatalog().parseCatalog(catalog.toString());
                        return;
                    } catch (Exception e2) {
                        this.catalogManager.debug.message(3, "Exception parsing oasis-xml-catalog: " + catalog.toString());
                        return;
                    }
                }
                this.catalogManager.debug.message(3, "PI oasis-xml-catalog unparseable: " + pidata);
            } else {
                this.catalogManager.debug.message(4, "PI oasis-xml-catalog ignored: " + pidata);
            }
        } else if (this.documentHandler != null) {
            this.documentHandler.processingInstruction(target, pidata);
        }
    }

    public void setDocumentLocator(Locator locator) {
        if (this.documentHandler != null) {
            this.documentHandler.setDocumentLocator(locator);
        }
    }

    public void startDocument() throws SAXException {
        if (this.documentHandler != null) {
            this.documentHandler.startDocument();
        }
    }

    public void startElement(String name, AttributeList atts) throws SAXException {
        this.allowXMLCatalogPI = false;
        if (this.documentHandler != null) {
            this.documentHandler.startElement(name, atts);
        }
    }

    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        this.allowXMLCatalogPI = false;
        if (this.dtdHandler != null) {
            this.dtdHandler.notationDecl(name, publicId, systemId);
        }
    }

    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        this.allowXMLCatalogPI = false;
        if (this.dtdHandler != null) {
            this.dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }

    public InputSource resolveEntity(String publicId, String systemId) {
        this.allowXMLCatalogPI = false;
        String resolved = this.catalogResolver.getResolvedEntity(publicId, systemId);
        if (resolved == null && this.piCatalogResolver != null) {
            resolved = this.piCatalogResolver.getResolvedEntity(publicId, systemId);
        }
        if (resolved == null) {
            return null;
        }
        try {
            InputSource iSource = new InputSource(resolved);
            iSource.setPublicId(publicId);
            iSource.setByteStream(new URL(resolved).openStream());
            return iSource;
        } catch (Exception e) {
            this.catalogManager.debug.message(1, "Failed to create InputSource (" + e.toString() + ")", resolved);
            return null;
        }
    }

    private void setupParse(String systemId) {
        URL cwd;
        this.allowXMLCatalogPI = true;
        this.parser.setEntityResolver(this);
        this.parser.setDocumentHandler(this);
        this.parser.setDTDHandler(this);
        try {
            cwd = FileURL.makeURL("basename");
        } catch (MalformedURLException e) {
            cwd = null;
        }
        try {
            this.baseURL = new URL(systemId);
        } catch (MalformedURLException e2) {
            if (cwd != null) {
                try {
                    this.baseURL = new URL(cwd, systemId);
                    return;
                } catch (MalformedURLException e3) {
                    this.baseURL = null;
                    return;
                }
            }
            this.baseURL = null;
        }
    }

    private void explain(String systemId) {
        if (!suppressExplanation) {
            System.out.println("Parser probably encountered bad URI in " + systemId);
            System.out.println("For example, replace '/some/uri' with 'file:/some/uri'.");
        }
    }
}
