package mf.org.apache.xml.resolver.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.TransformerException;
import mf.javax.xml.transform.URIResolver;
import mf.javax.xml.transform.sax.SAXSource;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.FileURL;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class CatalogResolver implements EntityResolver, URIResolver {
    private Catalog catalog = null;
    private CatalogManager catalogManager = CatalogManager.getStaticManager();
    public boolean namespaceAware = true;
    public boolean validating = false;

    public CatalogResolver() {
        initializeCatalogs(false);
    }

    public CatalogResolver(boolean privateCatalog) {
        initializeCatalogs(privateCatalog);
    }

    public CatalogResolver(CatalogManager manager) {
        boolean z = false;
        this.catalogManager = manager;
        if (!this.catalogManager.getUseStaticCatalog()) {
            z = true;
        }
        initializeCatalogs(z);
    }

    private void initializeCatalogs(boolean privateCatalog) {
        this.catalog = this.catalogManager.getCatalog();
    }

    public Catalog getCatalog() {
        return this.catalog;
    }

    public String getResolvedEntity(String publicId, String systemId) {
        String resolved = null;
        if (this.catalog == null) {
            this.catalogManager.debug.message(1, "Catalog resolution attempted with null catalog; ignored");
            return null;
        }
        if (systemId != null) {
            try {
                resolved = this.catalog.resolveSystem(systemId);
            } catch (MalformedURLException e) {
                this.catalogManager.debug.message(1, "Malformed URL exception trying to resolve", publicId);
                resolved = null;
            } catch (IOException e2) {
                this.catalogManager.debug.message(1, "I/O exception trying to resolve", publicId);
                resolved = null;
            }
        }
        if (resolved == null) {
            if (publicId != null) {
                try {
                    resolved = this.catalog.resolvePublic(publicId, systemId);
                } catch (MalformedURLException e3) {
                    this.catalogManager.debug.message(1, "Malformed URL exception trying to resolve", publicId);
                } catch (IOException e4) {
                    this.catalogManager.debug.message(1, "I/O exception trying to resolve", publicId);
                }
            }
            if (resolved != null) {
                this.catalogManager.debug.message(2, "Resolved public", publicId, resolved);
            }
        } else {
            this.catalogManager.debug.message(2, "Resolved system", systemId, resolved);
        }
        return resolved;
    }

    public InputSource resolveEntity(String publicId, String systemId) {
        String resolved = getResolvedEntity(publicId, systemId);
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

    public Source resolve(String href, String base) throws TransformerException {
        Throwable mue;
        String absBase;
        String uri = href;
        int hashPos = href.indexOf("#");
        if (hashPos >= 0) {
            uri = href.substring(0, hashPos);
            String fragment = href.substring(hashPos + 1);
        }
        String result = null;
        try {
            result = this.catalog.resolveURI(href);
        } catch (Exception e) {
        }
        if (result == null) {
            if (base == null) {
                try {
                    URL url = new URL(uri);
                    try {
                        result = url.toString();
                    } catch (MalformedURLException e2) {
                        mue = e2;
                        absBase = makeAbsolute(base);
                        if (!absBase.equals(base)) {
                            return resolve(href, absBase);
                        }
                        throw new TransformerException("Malformed URL " + href + "(base " + base + ")", mue);
                    }
                } catch (MalformedURLException e3) {
                    mue = e3;
                    absBase = makeAbsolute(base);
                    if (!absBase.equals(base)) {
                        return resolve(href, absBase);
                    }
                    throw new TransformerException("Malformed URL " + href + "(base " + base + ")", mue);
                }
            }
            URL baseURL = new URL(base);
            result = (href.length() == 0 ? baseURL : new URL(baseURL, uri)).toString();
        }
        this.catalogManager.debug.message(2, "Resolved URI", href, result);
        SAXSource source = new SAXSource();
        source.setInputSource(new InputSource(result));
        setEntityResolver(source);
        return source;
    }

    private void setEntityResolver(SAXSource source) throws TransformerException {
        XMLReader reader = source.getXMLReader();
        if (reader == null) {
            SAXParserFactory spFactory = SAXParserFactory.newInstance();
            spFactory.setNamespaceAware(true);
            try {
                reader = spFactory.newSAXParser().getXMLReader();
            } catch (Throwable ex) {
                throw new TransformerException(ex);
            } catch (Throwable ex2) {
                throw new TransformerException(ex2);
            }
        }
        reader.setEntityResolver(this);
        source.setXMLReader(reader);
    }

    private String makeAbsolute(String uri) {
        if (uri == null) {
            uri = "";
        }
        try {
            uri = new URL(uri).toString();
        } catch (MalformedURLException e) {
            try {
                uri = FileURL.makeURL(uri).toString();
            } catch (MalformedURLException e2) {
            }
        }
        return uri;
    }
}
