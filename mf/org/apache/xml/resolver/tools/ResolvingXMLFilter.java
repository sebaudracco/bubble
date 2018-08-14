package mf.org.apache.xml.resolver.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.FileURL;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

public class ResolvingXMLFilter extends XMLFilterImpl {
    public static boolean suppressExplanation = false;
    private boolean allowXMLCatalogPI;
    private URL baseURL;
    private CatalogManager catalogManager;
    private CatalogResolver catalogResolver;
    private boolean oasisXMLCatalogPI;
    private CatalogResolver piCatalogResolver;

    public ResolvingXMLFilter() {
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }

    public ResolvingXMLFilter(XMLReader parent) {
        super(parent);
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }

    public ResolvingXMLFilter(CatalogManager manager) {
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogManager = manager;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }

    public ResolvingXMLFilter(XMLReader parent, CatalogManager manager) {
        super(parent);
        this.catalogManager = CatalogManager.getStaticManager();
        this.catalogResolver = null;
        this.piCatalogResolver = null;
        this.allowXMLCatalogPI = false;
        this.oasisXMLCatalogPI = false;
        this.baseURL = null;
        this.catalogManager = manager;
        this.catalogResolver = new CatalogResolver(this.catalogManager);
    }

    public Catalog getCatalog() {
        return this.catalogResolver.getCatalog();
    }

    public void parse(InputSource input) throws IOException, SAXException {
        this.allowXMLCatalogPI = true;
        setupBaseURI(input.getSystemId());
        try {
            super.parse(input);
        } catch (InternalError ie) {
            explain(input.getSystemId());
            throw ie;
        }
    }

    public void parse(String systemId) throws IOException, SAXException {
        this.allowXMLCatalogPI = true;
        setupBaseURI(systemId);
        try {
            super.parse(systemId);
        } catch (InternalError ie) {
            explain(systemId);
            throw ie;
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

    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.notationDecl(name, publicId, systemId);
    }

    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.allowXMLCatalogPI = false;
        super.startElement(uri, localName, qName, atts);
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
                return;
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
                return;
            } else {
                this.catalogManager.debug.message(4, "PI oasis-xml-catalog ignored: " + pidata);
                return;
            }
        }
        super.processingInstruction(target, pidata);
    }

    private void setupBaseURI(String systemId) {
        URL cwd;
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
            System.out.println("XMLReader probably encountered bad URI in " + systemId);
            System.out.println("For example, replace '/some/uri' with 'file:/some/uri'.");
        }
        suppressExplanation = true;
    }
}
