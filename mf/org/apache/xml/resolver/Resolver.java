package mf.org.apache.xml.resolver;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Vector;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.readers.OASISXMLCatalogReader;
import mf.org.apache.xml.resolver.readers.SAXCatalogReader;
import mf.org.apache.xml.resolver.readers.TR9401CatalogReader;

public class Resolver extends Catalog {
    public static final int RESOLVER = CatalogEntry.addEntryType("RESOLVER", 1);
    public static final int SYSTEMREVERSE = CatalogEntry.addEntryType("SYSTEMREVERSE", 1);
    public static final int SYSTEMSUFFIX = CatalogEntry.addEntryType("SYSTEMSUFFIX", 2);
    public static final int URISUFFIX = CatalogEntry.addEntryType("URISUFFIX", 2);

    public void setupReaders() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);
        SAXCatalogReader saxReader = new SAXCatalogReader(spf);
        saxReader.setCatalogParser(null, "XCatalog", "org.apache.xml.resolver.readers.XCatalogReader");
        saxReader.setCatalogParser(OASISXMLCatalogReader.namespaceName, "catalog", "org.apache.xml.resolver.readers.ExtendedXMLCatalogReader");
        addReader("application/xml", saxReader);
        addReader(HTTP.PLAIN_TEXT_TYPE, new TR9401CatalogReader());
    }

    public void addEntry(CatalogEntry entry) {
        int type = entry.getEntryType();
        String suffix;
        String fsi;
        if (type == URISUFFIX) {
            suffix = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "URISUFFIX", suffix, fsi);
        } else if (type == SYSTEMSUFFIX) {
            suffix = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "SYSTEMSUFFIX", suffix, fsi);
        }
        super.addEntry(entry);
    }

    public String resolveURI(String uri) throws MalformedURLException, IOException {
        String resolved = super.resolveURI(uri);
        if (resolved != null) {
            return resolved;
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == RESOLVER) {
                resolved = resolveExternalSystem(uri, e.getEntryArg(0));
                if (resolved != null) {
                    return resolved;
                }
            } else if (e.getEntryType() == URISUFFIX) {
                String suffix = e.getEntryArg(0);
                String result = e.getEntryArg(1);
                if (suffix.length() <= uri.length() && uri.substring(uri.length() - suffix.length()).equals(suffix)) {
                    return result;
                }
            } else {
                continue;
            }
        }
        return resolveSubordinateCatalogs(Catalog.URI, null, null, uri);
    }

    public String resolveSystem(String systemId) throws MalformedURLException, IOException {
        String resolved = super.resolveSystem(systemId);
        if (resolved != null) {
            return resolved;
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == RESOLVER) {
                resolved = resolveExternalSystem(systemId, e.getEntryArg(0));
                if (resolved != null) {
                    return resolved;
                }
            } else if (e.getEntryType() == SYSTEMSUFFIX) {
                String suffix = e.getEntryArg(0);
                String result = e.getEntryArg(1);
                if (suffix.length() <= systemId.length() && systemId.substring(systemId.length() - suffix.length()).equals(suffix)) {
                    return result;
                }
            } else {
                continue;
            }
        }
        return resolveSubordinateCatalogs(Catalog.SYSTEM, null, null, systemId);
    }

    public String resolvePublic(String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved = super.resolvePublic(publicId, systemId);
        if (resolved != null) {
            return resolved;
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == RESOLVER) {
                if (systemId != null) {
                    resolved = resolveExternalSystem(systemId, e.getEntryArg(0));
                    if (resolved != null) {
                        return resolved;
                    }
                }
                resolved = resolveExternalPublic(publicId, e.getEntryArg(0));
                if (resolved != null) {
                    return resolved;
                }
            }
        }
        return resolveSubordinateCatalogs(Catalog.PUBLIC, null, publicId, systemId);
    }

    protected String resolveExternalSystem(String systemId, String resolver) throws MalformedURLException, IOException {
        Resolver r = queryResolver(resolver, "i2l", systemId, null);
        if (r != null) {
            return r.resolveSystem(systemId);
        }
        return null;
    }

    protected String resolveExternalPublic(String publicId, String resolver) throws MalformedURLException, IOException {
        Resolver r = queryResolver(resolver, "fpi2l", publicId, null);
        if (r != null) {
            return r.resolvePublic(publicId, null);
        }
        return null;
    }

    protected Resolver queryResolver(String resolver, String command, String arg1, String arg2) {
        String RFC2483 = new StringBuilder(String.valueOf(resolver)).append("?command=").append(command).append("&format=tr9401&uri=").append(arg1).append("&uri2=").append(arg2).toString();
        try {
            URLConnection urlCon = new URL(RFC2483).openConnection();
            urlCon.setUseCaches(false);
            Resolver r = (Resolver) newCatalog();
            String cType = urlCon.getContentType();
            if (cType.indexOf(";") > 0) {
                cType = cType.substring(0, cType.indexOf(";"));
            }
            r.parseCatalog(cType, urlCon.getInputStream());
            return r;
        } catch (CatalogException cex) {
            if (cex.getExceptionType() == 6) {
                this.catalogManager.debug.message(1, "Unparseable catalog: " + RFC2483);
            } else if (cex.getExceptionType() == 5) {
                this.catalogManager.debug.message(1, "Unknown catalog format: " + RFC2483);
            }
            return null;
        } catch (MalformedURLException e) {
            this.catalogManager.debug.message(1, "Malformed resolver URL: " + RFC2483);
            return null;
        } catch (IOException e2) {
            this.catalogManager.debug.message(1, "I/O Exception opening resolver: " + RFC2483);
            return null;
        }
    }

    private Vector appendVector(Vector vec, Vector appvec) {
        if (appvec != null) {
            for (int count = 0; count < appvec.size(); count++) {
                vec.addElement(appvec.elementAt(count));
            }
        }
        return vec;
    }

    public Vector resolveAllSystemReverse(String systemId) throws MalformedURLException, IOException {
        Vector resolved = new Vector();
        if (systemId != null) {
            resolved = appendVector(resolved, resolveLocalSystemReverse(systemId));
        }
        return appendVector(resolved, resolveAllSubordinateCatalogs(SYSTEMREVERSE, null, null, systemId));
    }

    public String resolveSystemReverse(String systemId) throws MalformedURLException, IOException {
        Vector resolved = resolveAllSystemReverse(systemId);
        if (resolved == null || resolved.size() <= 0) {
            return null;
        }
        return (String) resolved.elementAt(0);
    }

    public Vector resolveAllSystem(String systemId) throws MalformedURLException, IOException {
        Vector resolutions = new Vector();
        if (systemId != null) {
            resolutions = appendVector(resolutions, resolveAllLocalSystem(systemId));
        }
        resolutions = appendVector(resolutions, resolveAllSubordinateCatalogs(SYSTEM, null, null, systemId));
        return resolutions.size() > 0 ? resolutions : null;
    }

    private Vector resolveAllLocalSystem(String systemId) {
        Vector map = new Vector();
        boolean windows;
        if (System.getProperty("os.name").indexOf("Windows") >= 0) {
            windows = true;
        } else {
            windows = false;
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM && (e.getEntryArg(0).equals(systemId) || (windows && e.getEntryArg(0).equalsIgnoreCase(systemId)))) {
                map.addElement(e.getEntryArg(1));
            }
        }
        if (map.size() == 0) {
            return null;
        }
        return map;
    }

    private Vector resolveLocalSystemReverse(String systemId) {
        Vector map = new Vector();
        boolean windows;
        if (System.getProperty("os.name").indexOf("Windows") >= 0) {
            windows = true;
        } else {
            windows = false;
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM && (e.getEntryArg(1).equals(systemId) || (windows && e.getEntryArg(1).equalsIgnoreCase(systemId)))) {
                map.addElement(e.getEntryArg(0));
            }
        }
        if (map.size() == 0) {
            return null;
        }
        return map;
    }

    private synchronized Vector resolveAllSubordinateCatalogs(int entityType, String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String catfile;
        Vector vector;
        Resolver c;
        Vector resolutions = new Vector();
        for (int catPos = 0; catPos < this.catalogs.size(); catPos++) {
            try {
                c = (Resolver) this.catalogs.elementAt(catPos);
            } catch (ClassCastException e) {
                catfile = (String) this.catalogs.elementAt(catPos);
                c = (Resolver) newCatalog();
                try {
                    c.parseCatalog(catfile);
                } catch (MalformedURLException e2) {
                    this.catalogManager.debug.message(1, "Malformed Catalog URL", catfile);
                } catch (FileNotFoundException e3) {
                    this.catalogManager.debug.message(1, "Failed to load catalog, file not found", catfile);
                } catch (IOException e4) {
                    this.catalogManager.debug.message(1, "Failed to load catalog, I/O error", catfile);
                }
                this.catalogs.setElementAt(c, catPos);
            }
            String resolved;
            if (entityType == DOCTYPE) {
                resolved = c.resolveDoctype(entityName, publicId, systemId);
                if (resolved != null) {
                    resolutions.addElement(resolved);
                    vector = resolutions;
                    break;
                }
            } else if (entityType == DOCUMENT) {
                resolved = c.resolveDocument();
                if (resolved != null) {
                    resolutions.addElement(resolved);
                    vector = resolutions;
                    break;
                }
            } else if (entityType == ENTITY) {
                resolved = c.resolveEntity(entityName, publicId, systemId);
                if (resolved != null) {
                    resolutions.addElement(resolved);
                    vector = resolutions;
                    break;
                }
            } else if (entityType == NOTATION) {
                resolved = c.resolveNotation(entityName, publicId, systemId);
                if (resolved != null) {
                    resolutions.addElement(resolved);
                    vector = resolutions;
                    break;
                }
            } else if (entityType == PUBLIC) {
                resolved = c.resolvePublic(publicId, systemId);
                if (resolved != null) {
                    resolutions.addElement(resolved);
                    vector = resolutions;
                    break;
                }
            } else if (entityType == SYSTEM) {
                resolutions = appendVector(resolutions, c.resolveAllSystem(systemId));
                break;
            } else if (entityType == SYSTEMREVERSE) {
                resolutions = appendVector(resolutions, c.resolveAllSystemReverse(systemId));
            }
        }
        if (resolutions != null) {
            vector = resolutions;
        } else {
            vector = null;
        }
        return vector;
    }
}
