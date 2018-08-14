package mf.org.apache.xml.resolver.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.TransformerException;
import mf.javax.xml.transform.URIResolver;
import mf.javax.xml.transform.sax.SAXSource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class BootstrapResolver implements EntityResolver, URIResolver {
    public static final String xCatalogPubId = "-//DTD XCatalog//EN";
    public static final String xmlCatalogPubId = "-//OASIS//DTD XML Catalogs V1.0//EN";
    public static final String xmlCatalogRNG = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.rng";
    public static final String xmlCatalogSysId = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.dtd";
    public static final String xmlCatalogXSD = "http://www.oasis-open.org/committees/entity/release/1.0/catalog.xsd";
    private Hashtable publicMap = new Hashtable();
    private Hashtable systemMap = new Hashtable();
    private Hashtable uriMap = new Hashtable();

    public BootstrapResolver() {
        URL url = getClass().getResource("/org/apache/xml/resolver/etc/catalog.dtd");
        if (url != null) {
            this.publicMap.put(xmlCatalogPubId, url.toString());
            this.systemMap.put(xmlCatalogSysId, url.toString());
        }
        url = getClass().getResource("/org/apache/xml/resolver/etc/catalog.rng");
        if (url != null) {
            this.uriMap.put(xmlCatalogRNG, url.toString());
        }
        url = getClass().getResource("/org/apache/xml/resolver/etc/catalog.xsd");
        if (url != null) {
            this.uriMap.put(xmlCatalogXSD, url.toString());
        }
        url = getClass().getResource("/org/apache/xml/resolver/etc/xcatalog.dtd");
        if (url != null) {
            this.publicMap.put(xCatalogPubId, url.toString());
        }
    }

    public InputSource resolveEntity(String publicId, String systemId) {
        String resolved = null;
        if (systemId != null && this.systemMap.containsKey(systemId)) {
            resolved = (String) this.systemMap.get(systemId);
        } else if (publicId != null && this.publicMap.containsKey(publicId)) {
            resolved = (String) this.publicMap.get(publicId);
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
        if (href != null && this.uriMap.containsKey(href)) {
            result = (String) this.uriMap.get(href);
        }
        if (result == null) {
            if (base == null) {
                try {
                    URL url = new URL(uri);
                    try {
                        result = url.toString();
                    } catch (MalformedURLException e) {
                        mue = e;
                        absBase = makeAbsolute(base);
                        if (!absBase.equals(base)) {
                            return resolve(href, absBase);
                        }
                        throw new TransformerException("Malformed URL " + href + "(base " + base + ")", mue);
                    }
                } catch (MalformedURLException e2) {
                    mue = e2;
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
        SAXSource source = new SAXSource();
        source.setInputSource(new InputSource(result));
        return source;
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
