package mf.org.apache.xml.resolver;

import com.mobfox.sdk.utils.Utils;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.resolver.helpers.FileURL;
import mf.org.apache.xml.resolver.helpers.PublicId;
import mf.org.apache.xml.resolver.readers.CatalogReader;
import mf.org.apache.xml.resolver.readers.OASISXMLCatalogReader;
import mf.org.apache.xml.resolver.readers.SAXCatalogReader;
import mf.org.apache.xml.resolver.readers.TR9401CatalogReader;

public class Catalog {
    public static final int BASE = CatalogEntry.addEntryType("BASE", 1);
    public static final int CATALOG = CatalogEntry.addEntryType("CATALOG", 1);
    public static final int DELEGATE_PUBLIC = CatalogEntry.addEntryType("DELEGATE_PUBLIC", 2);
    public static final int DELEGATE_SYSTEM = CatalogEntry.addEntryType("DELEGATE_SYSTEM", 2);
    public static final int DELEGATE_URI = CatalogEntry.addEntryType("DELEGATE_URI", 2);
    public static final int DOCTYPE = CatalogEntry.addEntryType("DOCTYPE", 2);
    public static final int DOCUMENT = CatalogEntry.addEntryType("DOCUMENT", 1);
    public static final int DTDDECL = CatalogEntry.addEntryType("DTDDECL", 2);
    public static final int ENTITY = CatalogEntry.addEntryType(SchemaSymbols.ATTVAL_ENTITY, 2);
    public static final int LINKTYPE = CatalogEntry.addEntryType("LINKTYPE", 2);
    public static final int NOTATION = CatalogEntry.addEntryType(SchemaSymbols.ATTVAL_NOTATION, 2);
    public static final int OVERRIDE = CatalogEntry.addEntryType("OVERRIDE", 1);
    public static final int PUBLIC = CatalogEntry.addEntryType("PUBLIC", 2);
    public static final int REWRITE_SYSTEM = CatalogEntry.addEntryType("REWRITE_SYSTEM", 2);
    public static final int REWRITE_URI = CatalogEntry.addEntryType("REWRITE_URI", 2);
    public static final int SGMLDECL = CatalogEntry.addEntryType("SGMLDECL", 1);
    public static final int SYSTEM = CatalogEntry.addEntryType("SYSTEM", 2);
    public static final int SYSTEM_SUFFIX = CatalogEntry.addEntryType("SYSTEM_SUFFIX", 2);
    public static final int URI = CatalogEntry.addEntryType("URI", 2);
    public static final int URI_SUFFIX = CatalogEntry.addEntryType("URI_SUFFIX", 2);
    protected URL base;
    protected URL catalogCwd;
    protected Vector catalogEntries = new Vector();
    protected Vector catalogFiles = new Vector();
    protected CatalogManager catalogManager = CatalogManager.getStaticManager();
    protected Vector catalogs = new Vector();
    protected boolean default_override = true;
    protected Vector localCatalogFiles = new Vector();
    protected Vector localDelegate = new Vector();
    protected Vector readerArr = new Vector();
    protected Hashtable readerMap = new Hashtable();

    public Catalog(CatalogManager manager) {
        this.catalogManager = manager;
    }

    public CatalogManager getCatalogManager() {
        return this.catalogManager;
    }

    public void setCatalogManager(CatalogManager manager) {
        this.catalogManager = manager;
    }

    public void setupReaders() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);
        SAXCatalogReader saxReader = new SAXCatalogReader(spf);
        saxReader.setCatalogParser(null, "XCatalog", "org.apache.xml.resolver.readers.XCatalogReader");
        saxReader.setCatalogParser(OASISXMLCatalogReader.namespaceName, "catalog", "org.apache.xml.resolver.readers.OASISXMLCatalogReader");
        addReader("application/xml", saxReader);
        addReader(HTTP.PLAIN_TEXT_TYPE, new TR9401CatalogReader());
    }

    public void addReader(String mimeType, CatalogReader reader) {
        if (this.readerMap.containsKey(mimeType)) {
            this.readerArr.set(((Integer) this.readerMap.get(mimeType)).intValue(), reader);
            return;
        }
        this.readerArr.add(reader);
        this.readerMap.put(mimeType, new Integer(this.readerArr.size() - 1));
    }

    protected void copyReaders(Catalog newCatalog) {
        int count;
        Vector mapArr = new Vector(this.readerMap.size());
        for (count = 0; count < this.readerMap.size(); count++) {
            mapArr.add(null);
        }
        Enumeration en = this.readerMap.keys();
        while (en.hasMoreElements()) {
            String mimeType = (String) en.nextElement();
            mapArr.set(((Integer) this.readerMap.get(mimeType)).intValue(), mimeType);
        }
        for (count = 0; count < mapArr.size(); count++) {
            mimeType = (String) mapArr.get(count);
            newCatalog.addReader(mimeType, (CatalogReader) this.readerArr.get(((Integer) this.readerMap.get(mimeType)).intValue()));
        }
    }

    protected Catalog newCatalog() {
        Catalog c;
        String catalogClass = getClass().getName();
        try {
            c = (Catalog) Class.forName(catalogClass).newInstance();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (ClassNotFoundException e) {
            this.catalogManager.debug.message(1, "Class Not Found Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (IllegalAccessException e2) {
            this.catalogManager.debug.message(1, "Illegal Access Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (InstantiationException e3) {
            this.catalogManager.debug.message(1, "Instantiation Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (ClassCastException e4) {
            this.catalogManager.debug.message(1, "Class Cast Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        } catch (Exception e5) {
            this.catalogManager.debug.message(1, "Other Exception: " + catalogClass);
            c = new Catalog();
            c.setCatalogManager(this.catalogManager);
            copyReaders(c);
            return c;
        }
    }

    public String getCurrentBase() {
        return this.base.toString();
    }

    public String getDefaultOverride() {
        if (this.default_override) {
            return "yes";
        }
        return "no";
    }

    public void loadSystemCatalogs() throws MalformedURLException, IOException {
        Vector catalogs = this.catalogManager.getCatalogFiles();
        if (catalogs != null) {
            for (int count = 0; count < catalogs.size(); count++) {
                this.catalogFiles.addElement(catalogs.elementAt(count));
            }
        }
        if (this.catalogFiles.size() > 0) {
            String catfile = (String) this.catalogFiles.lastElement();
            this.catalogFiles.removeElement(catfile);
            parseCatalog(catfile);
        }
    }

    public synchronized void parseCatalog(String fileName) throws MalformedURLException, IOException {
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse catalog: " + fileName);
        this.catalogFiles.addElement(fileName);
        parsePendingCatalogs();
    }

    public synchronized void parseCatalog(String mimeType, InputStream is) throws IOException, CatalogException {
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse " + mimeType + " catalog on input stream");
        CatalogReader reader = null;
        if (this.readerMap.containsKey(mimeType)) {
            reader = (CatalogReader) this.readerArr.get(((Integer) this.readerMap.get(mimeType)).intValue());
        }
        if (reader == null) {
            String msg = "No CatalogReader for MIME type: " + mimeType;
            this.catalogManager.debug.message(2, msg);
            throw new CatalogException(6, msg);
        }
        reader.readCatalog(this, is);
        parsePendingCatalogs();
    }

    public synchronized void parseCatalog(URL aUrl) throws IOException {
        DataInputStream dataInputStream;
        this.catalogCwd = aUrl;
        this.base = aUrl;
        this.default_override = this.catalogManager.getPreferPublic();
        this.catalogManager.debug.message(4, "Parse catalog: " + aUrl.toString());
        boolean parsed = false;
        int count = 0;
        DataInputStream inStream = null;
        while (!parsed && count < this.readerArr.size()) {
            CatalogReader reader = (CatalogReader) this.readerArr.get(count);
            try {
                InputStream inStream2 = new DataInputStream(aUrl.openStream());
                try {
                    reader.readCatalog(this, inStream2);
                    parsed = true;
                } catch (CatalogException ce) {
                    if (ce.getExceptionType() == 7) {
                        break;
                    }
                }
                try {
                    inStream2.close();
                } catch (IOException e) {
                }
                count++;
                InputStream inStream3 = inStream2;
            } catch (FileNotFoundException e2) {
                dataInputStream = inStream;
            }
        }
        dataInputStream = inStream;
        if (parsed) {
            parsePendingCatalogs();
        }
    }

    protected synchronized void parsePendingCatalogs() throws MalformedURLException, IOException {
        Vector newQueue;
        Enumeration q;
        int curCat;
        Enumeration e;
        if (!this.localCatalogFiles.isEmpty()) {
            newQueue = new Vector();
            q = this.localCatalogFiles.elements();
            while (q.hasMoreElements()) {
                newQueue.addElement(q.nextElement());
            }
            for (curCat = 0; curCat < this.catalogFiles.size(); curCat++) {
                newQueue.addElement((String) this.catalogFiles.elementAt(curCat));
            }
            this.catalogFiles = newQueue;
            this.localCatalogFiles.clear();
        }
        if (this.catalogFiles.isEmpty() && !this.localDelegate.isEmpty()) {
            e = this.localDelegate.elements();
            while (e.hasMoreElements()) {
                this.catalogEntries.addElement(e.nextElement());
            }
            this.localDelegate.clear();
        }
        while (!this.catalogFiles.isEmpty()) {
            String catfile = (String) this.catalogFiles.elementAt(0);
            try {
                this.catalogFiles.remove(0);
            } catch (ArrayIndexOutOfBoundsException e2) {
            }
            if (this.catalogEntries.size() == 0 && this.catalogs.size() == 0) {
                try {
                    parseCatalogFile(catfile);
                } catch (CatalogException ce) {
                    System.out.println("FIXME: " + ce.toString());
                }
            } else {
                this.catalogs.addElement(catfile);
            }
            if (!this.localCatalogFiles.isEmpty()) {
                newQueue = new Vector();
                q = this.localCatalogFiles.elements();
                while (q.hasMoreElements()) {
                    newQueue.addElement(q.nextElement());
                }
                for (curCat = 0; curCat < this.catalogFiles.size(); curCat++) {
                    newQueue.addElement((String) this.catalogFiles.elementAt(curCat));
                }
                this.catalogFiles = newQueue;
                this.localCatalogFiles.clear();
            }
            if (!this.localDelegate.isEmpty()) {
                e = this.localDelegate.elements();
                while (e.hasMoreElements()) {
                    this.catalogEntries.addElement(e.nextElement());
                }
                this.localDelegate.clear();
            }
        }
        this.catalogFiles.clear();
    }

    protected synchronized void parseCatalogFile(String fileName) throws MalformedURLException, IOException, CatalogException {
        DataInputStream dataInputStream;
        try {
            this.catalogCwd = FileURL.makeURL("basename");
        } catch (MalformedURLException e) {
            this.catalogManager.debug.message(1, "Malformed URL on cwd", System.getProperty("user.dir").replace('\\', '/'));
            this.catalogCwd = null;
        }
        try {
            this.base = new URL(this.catalogCwd, fixSlashes(fileName));
        } catch (MalformedURLException e2) {
            try {
                this.base = new URL("file:" + fixSlashes(fileName));
            } catch (MalformedURLException e3) {
                this.catalogManager.debug.message(1, "Malformed URL on catalog filename", fixSlashes(fileName));
                this.base = null;
            }
        }
        this.catalogManager.debug.message(2, "Loading catalog", fileName);
        this.catalogManager.debug.message(4, "Default BASE", this.base.toString());
        fileName = this.base.toString();
        boolean parsed = false;
        boolean notFound = false;
        int count = 0;
        DataInputStream inStream = null;
        while (!parsed && count < this.readerArr.size()) {
            CatalogReader reader = (CatalogReader) this.readerArr.get(count);
            notFound = false;
            try {
                InputStream inStream2 = new DataInputStream(this.base.openStream());
                try {
                    reader.readCatalog(this, inStream2);
                    parsed = true;
                } catch (CatalogException ce) {
                    if (ce.getExceptionType() == 7) {
                        break;
                    }
                }
                try {
                    inStream2.close();
                } catch (IOException e4) {
                }
                count++;
                InputStream inStream3 = inStream2;
            } catch (FileNotFoundException e5) {
                notFound = true;
                dataInputStream = inStream;
            }
        }
        dataInputStream = inStream;
        if (!parsed) {
            if (notFound) {
                this.catalogManager.debug.message(3, "Catalog does not exist", fileName);
            } else {
                this.catalogManager.debug.message(1, "Failed to parse catalog", fileName);
            }
        }
    }

    public void addEntry(CatalogEntry entry) {
        int type = entry.getEntryType();
        if (type == BASE) {
            URL newbase;
            String value = entry.getEntryArg(0);
            if (this.base == null) {
                this.catalogManager.debug.message(5, "BASE CUR", "null");
            } else {
                this.catalogManager.debug.message(5, "BASE CUR", this.base.toString());
            }
            this.catalogManager.debug.message(4, "BASE STR", value);
            try {
                value = fixSlashes(value);
                newbase = new URL(this.base, value);
            } catch (MalformedURLException e) {
                try {
                    newbase = new URL("file:" + value);
                } catch (MalformedURLException e2) {
                    this.catalogManager.debug.message(1, "Malformed URL on base", value);
                    newbase = null;
                }
            }
            if (newbase != null) {
                this.base = newbase;
            }
            this.catalogManager.debug.message(5, "BASE NEW", this.base.toString());
        } else if (type == CATALOG) {
            fsi = makeAbsolute(entry.getEntryArg(0));
            this.catalogManager.debug.message(4, "CATALOG", fsi);
            this.localCatalogFiles.addElement(fsi);
        } else if (type == PUBLIC) {
            String publicid = PublicId.normalize(entry.getEntryArg(0));
            systemid = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, publicid);
            entry.setEntryArg(1, systemid);
            this.catalogManager.debug.message(4, "PUBLIC", publicid, systemid);
            this.catalogEntries.addElement(entry);
        } else if (type == SYSTEM) {
            systemid = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "SYSTEM", systemid, fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == URI) {
            String uri = normalizeURI(entry.getEntryArg(0));
            String altURI = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, altURI);
            this.catalogManager.debug.message(4, "URI", uri, altURI);
            this.catalogEntries.addElement(entry);
        } else if (type == DOCUMENT) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(0)));
            entry.setEntryArg(0, fsi);
            this.catalogManager.debug.message(4, "DOCUMENT", fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == OVERRIDE) {
            this.catalogManager.debug.message(4, "OVERRIDE", entry.getEntryArg(0));
            this.catalogEntries.addElement(entry);
        } else if (type == SGMLDECL) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(0)));
            entry.setEntryArg(0, fsi);
            this.catalogManager.debug.message(4, "SGMLDECL", fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == DELEGATE_PUBLIC) {
            String ppi = PublicId.normalize(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, ppi);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_PUBLIC", ppi, fsi);
            addDelegate(entry);
        } else if (type == DELEGATE_SYSTEM) {
            psi = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, psi);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_SYSTEM", psi, fsi);
            addDelegate(entry);
        } else if (type == DELEGATE_URI) {
            pui = normalizeURI(entry.getEntryArg(0));
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DELEGATE_URI", pui, fsi);
            addDelegate(entry);
        } else if (type == REWRITE_SYSTEM) {
            psi = normalizeURI(entry.getEntryArg(0));
            String rpx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, psi);
            entry.setEntryArg(1, rpx);
            this.catalogManager.debug.message(4, "REWRITE_SYSTEM", psi, rpx);
            this.catalogEntries.addElement(entry);
        } else if (type == REWRITE_URI) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "REWRITE_URI", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == SYSTEM_SUFFIX) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "SYSTEM_SUFFIX", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == URI_SUFFIX) {
            pui = normalizeURI(entry.getEntryArg(0));
            upx = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(0, pui);
            entry.setEntryArg(1, upx);
            this.catalogManager.debug.message(4, "URI_SUFFIX", pui, upx);
            this.catalogEntries.addElement(entry);
        } else if (type == DOCTYPE) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DOCTYPE", entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == DTDDECL) {
            String fpi = PublicId.normalize(entry.getEntryArg(0));
            entry.setEntryArg(0, fpi);
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "DTDDECL", fpi, fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == ENTITY) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, SchemaSymbols.ATTVAL_ENTITY, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == LINKTYPE) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, "LINKTYPE", entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else if (type == NOTATION) {
            fsi = makeAbsolute(normalizeURI(entry.getEntryArg(1)));
            entry.setEntryArg(1, fsi);
            this.catalogManager.debug.message(4, SchemaSymbols.ATTVAL_NOTATION, entry.getEntryArg(0), fsi);
            this.catalogEntries.addElement(entry);
        } else {
            this.catalogEntries.addElement(entry);
        }
    }

    public void unknownEntry(Vector strings) {
        if (strings != null && strings.size() > 0) {
            this.catalogManager.debug.message(2, "Unrecognized token parsing catalog", (String) strings.elementAt(0));
        }
    }

    public void parseAllCatalogs() throws MalformedURLException, IOException {
        Catalog c;
        for (int catPos = 0; catPos < this.catalogs.size(); catPos++) {
            try {
                c = (Catalog) this.catalogs.elementAt(catPos);
            } catch (ClassCastException e) {
                String catfile = (String) this.catalogs.elementAt(catPos);
                c = newCatalog();
                c.parseCatalog(catfile);
                this.catalogs.setElementAt(c, catPos);
                c.parseAllCatalogs();
            }
        }
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e2 = (CatalogEntry) en.nextElement();
            if (e2.getEntryType() == DELEGATE_PUBLIC || e2.getEntryType() == DELEGATE_SYSTEM || e2.getEntryType() == DELEGATE_URI) {
                newCatalog().parseCatalog(e2.getEntryArg(1));
            }
        }
    }

    public String resolveDoctype(String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveDoctype(" + entityName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(DOCTYPE, entityName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == DOCTYPE && e.getEntryArg(0).equals(entityName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(DOCTYPE, entityName, publicId, systemId);
    }

    public String resolveDocument() throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveDocument");
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DOCUMENT) {
                return e.getEntryArg(0);
            }
        }
        return resolveSubordinateCatalogs(DOCUMENT, null, null, null);
    }

    public String resolveEntity(String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveEntity(" + entityName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(ENTITY, entityName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == ENTITY && e.getEntryArg(0).equals(entityName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(ENTITY, entityName, publicId, systemId);
    }

    public String resolveNotation(String notationName, String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolveNotation(" + notationName + "," + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        if (publicId != null) {
            resolved = resolveLocalPublic(NOTATION, notationName, publicId, systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        boolean over = this.default_override;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == OVERRIDE) {
                over = e.getEntryArg(0).equalsIgnoreCase("YES");
            } else if (e.getEntryType() == NOTATION && e.getEntryArg(0).equals(notationName)) {
                if (over || systemId == null) {
                    return e.getEntryArg(1);
                }
            }
        }
        return resolveSubordinateCatalogs(NOTATION, notationName, publicId, systemId);
    }

    public String resolvePublic(String publicId, String systemId) throws MalformedURLException, IOException {
        String resolved;
        this.catalogManager.debug.message(3, "resolvePublic(" + publicId + "," + systemId + ")");
        systemId = normalizeURI(systemId);
        if (publicId != null && publicId.startsWith("urn:publicid:")) {
            publicId = PublicId.decodeURN(publicId);
        }
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            systemId = PublicId.decodeURN(systemId);
            if (publicId == null || publicId.equals(systemId)) {
                publicId = systemId;
                systemId = null;
            } else {
                this.catalogManager.debug.message(1, "urn:publicid: system identifier differs from public identifier; using public identifier");
                systemId = null;
            }
        }
        if (systemId != null) {
            resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        resolved = resolveLocalPublic(PUBLIC, null, publicId, systemId);
        if (resolved != null) {
            return resolved;
        }
        return resolveSubordinateCatalogs(PUBLIC, null, publicId, systemId);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized java.lang.String resolveLocalPublic(int r15, java.lang.String r16, java.lang.String r17, java.lang.String r18) throws java.net.MalformedURLException, java.io.IOException {
        /*
        r14 = this;
        monitor-enter(r14);
        r17 = mf.org.apache.xml.resolver.helpers.PublicId.normalize(r17);	 Catch:{ all -> 0x00fb }
        if (r18 == 0) goto L_0x0011;
    L_0x0007:
        r0 = r18;
        r9 = r14.resolveLocalSystem(r0);	 Catch:{ all -> 0x00fb }
        if (r9 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r14);
        return r9;
    L_0x0011:
        r7 = r14.default_override;	 Catch:{ all -> 0x00fb }
        r10 = r14.catalogEntries;	 Catch:{ all -> 0x00fb }
        r5 = r10.elements();	 Catch:{ all -> 0x00fb }
    L_0x0019:
        r10 = r5.hasMoreElements();	 Catch:{ all -> 0x00fb }
        if (r10 != 0) goto L_0x006e;
    L_0x001f:
        r7 = r14.default_override;	 Catch:{ all -> 0x00fb }
        r10 = r14.catalogEntries;	 Catch:{ all -> 0x00fb }
        r5 = r10.elements();	 Catch:{ all -> 0x00fb }
        r2 = new java.util.Vector;	 Catch:{ all -> 0x00fb }
        r2.<init>();	 Catch:{ all -> 0x00fb }
    L_0x002c:
        r10 = r5.hasMoreElements();	 Catch:{ all -> 0x00fb }
        if (r10 != 0) goto L_0x00a9;
    L_0x0032:
        r10 = r2.size();	 Catch:{ all -> 0x00fb }
        if (r10 <= 0) goto L_0x0129;
    L_0x0038:
        r6 = r2.elements();	 Catch:{ all -> 0x00fb }
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00fb }
        r10 = r10.debug;	 Catch:{ all -> 0x00fb }
        r10 = r10.getDebug();	 Catch:{ all -> 0x00fb }
        r11 = 1;
        if (r10 <= r11) goto L_0x0058;
    L_0x0047:
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00fb }
        r10 = r10.debug;	 Catch:{ all -> 0x00fb }
        r11 = 2;
        r12 = "Switching to delegated catalog(s):";
        r10.message(r11, r12);	 Catch:{ all -> 0x00fb }
    L_0x0052:
        r10 = r6.hasMoreElements();	 Catch:{ all -> 0x00fb }
        if (r10 != 0) goto L_0x00fe;
    L_0x0058:
        r1 = r14.newCatalog();	 Catch:{ all -> 0x00fb }
        r6 = r2.elements();	 Catch:{ all -> 0x00fb }
    L_0x0060:
        r10 = r6.hasMoreElements();	 Catch:{ all -> 0x00fb }
        if (r10 != 0) goto L_0x011e;
    L_0x0066:
        r10 = 0;
        r0 = r17;
        r9 = r1.resolvePublic(r0, r10);	 Catch:{ all -> 0x00fb }
        goto L_0x000f;
    L_0x006e:
        r4 = r5.nextElement();	 Catch:{ all -> 0x00fb }
        r4 = (mf.org.apache.xml.resolver.CatalogEntry) r4;	 Catch:{ all -> 0x00fb }
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00fb }
        r11 = OVERRIDE;	 Catch:{ all -> 0x00fb }
        if (r10 != r11) goto L_0x0089;
    L_0x007c:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        r11 = "YES";
        r7 = r10.equalsIgnoreCase(r11);	 Catch:{ all -> 0x00fb }
        goto L_0x0019;
    L_0x0089:
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00fb }
        r11 = PUBLIC;	 Catch:{ all -> 0x00fb }
        if (r10 != r11) goto L_0x0019;
    L_0x0091:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        r0 = r17;
        r10 = r10.equals(r0);	 Catch:{ all -> 0x00fb }
        if (r10 == 0) goto L_0x0019;
    L_0x009e:
        if (r7 != 0) goto L_0x00a2;
    L_0x00a0:
        if (r18 != 0) goto L_0x0019;
    L_0x00a2:
        r10 = 1;
        r9 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        goto L_0x000f;
    L_0x00a9:
        r4 = r5.nextElement();	 Catch:{ all -> 0x00fb }
        r4 = (mf.org.apache.xml.resolver.CatalogEntry) r4;	 Catch:{ all -> 0x00fb }
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00fb }
        r11 = OVERRIDE;	 Catch:{ all -> 0x00fb }
        if (r10 != r11) goto L_0x00c5;
    L_0x00b7:
        r10 = 0;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        r11 = "YES";
        r7 = r10.equalsIgnoreCase(r11);	 Catch:{ all -> 0x00fb }
        goto L_0x002c;
    L_0x00c5:
        r10 = r4.getEntryType();	 Catch:{ all -> 0x00fb }
        r11 = DELEGATE_PUBLIC;	 Catch:{ all -> 0x00fb }
        if (r10 != r11) goto L_0x002c;
    L_0x00cd:
        if (r7 != 0) goto L_0x00d1;
    L_0x00cf:
        if (r18 != 0) goto L_0x002c;
    L_0x00d1:
        r10 = 0;
        r8 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        r10 = r8.length();	 Catch:{ all -> 0x00fb }
        r11 = r17.length();	 Catch:{ all -> 0x00fb }
        if (r10 > r11) goto L_0x002c;
    L_0x00e0:
        r10 = 0;
        r11 = r8.length();	 Catch:{ all -> 0x00fb }
        r0 = r17;
        r10 = r0.substring(r10, r11);	 Catch:{ all -> 0x00fb }
        r10 = r8.equals(r10);	 Catch:{ all -> 0x00fb }
        if (r10 == 0) goto L_0x002c;
    L_0x00f1:
        r10 = 1;
        r10 = r4.getEntryArg(r10);	 Catch:{ all -> 0x00fb }
        r2.addElement(r10);	 Catch:{ all -> 0x00fb }
        goto L_0x002c;
    L_0x00fb:
        r10 = move-exception;
        monitor-exit(r14);
        throw r10;
    L_0x00fe:
        r3 = r6.nextElement();	 Catch:{ all -> 0x00fb }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00fb }
        r10 = r14.catalogManager;	 Catch:{ all -> 0x00fb }
        r10 = r10.debug;	 Catch:{ all -> 0x00fb }
        r11 = 2;
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fb }
        r13 = "\t";
        r12.<init>(r13);	 Catch:{ all -> 0x00fb }
        r12 = r12.append(r3);	 Catch:{ all -> 0x00fb }
        r12 = r12.toString();	 Catch:{ all -> 0x00fb }
        r10.message(r11, r12);	 Catch:{ all -> 0x00fb }
        goto L_0x0052;
    L_0x011e:
        r3 = r6.nextElement();	 Catch:{ all -> 0x00fb }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x00fb }
        r1.parseCatalog(r3);	 Catch:{ all -> 0x00fb }
        goto L_0x0060;
    L_0x0129:
        r9 = 0;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.resolver.Catalog.resolveLocalPublic(int, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public String resolveSystem(String systemId) throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveSystem(" + systemId + ")");
        systemId = normalizeURI(systemId);
        if (systemId != null && systemId.startsWith("urn:publicid:")) {
            return resolvePublic(PublicId.decodeURN(systemId), null);
        }
        if (systemId != null) {
            String resolved = resolveLocalSystem(systemId);
            if (resolved != null) {
                return resolved;
            }
        }
        return resolveSubordinateCatalogs(SYSTEM, null, null, systemId);
    }

    protected String resolveLocalSystem(String systemId) throws MalformedURLException, IOException {
        String p;
        boolean windows = System.getProperty("os.name").indexOf("Windows") >= 0;
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM && (e.getEntryArg(0).equals(systemId) || (windows && e.getEntryArg(0).equalsIgnoreCase(systemId)))) {
                return e.getEntryArg(1);
            }
        }
        en = this.catalogEntries.elements();
        String startString = null;
        String prefix = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == REWRITE_SYSTEM) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length()) {
                    if (p.equals(systemId.substring(0, p.length())) && (startString == null || p.length() > startString.length())) {
                        startString = p;
                        prefix = e.getEntryArg(1);
                    }
                }
            }
        }
        if (prefix != null) {
            return new StringBuilder(String.valueOf(prefix)).append(systemId.substring(startString.length())).toString();
        }
        en = this.catalogEntries.elements();
        String suffixString = null;
        String suffixURI = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == SYSTEM_SUFFIX) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length() && systemId.endsWith(p)) {
                    if (suffixString == null || p.length() > suffixString.length()) {
                        suffixString = p;
                        suffixURI = e.getEntryArg(1);
                    }
                }
            }
        }
        if (suffixURI != null) {
            return suffixURI;
        }
        en = this.catalogEntries.elements();
        Vector delCats = new Vector();
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DELEGATE_SYSTEM) {
                p = e.getEntryArg(0);
                if (p.length() <= systemId.length()) {
                    if (p.equals(systemId.substring(0, p.length()))) {
                        delCats.addElement(e.getEntryArg(1));
                    }
                }
            }
        }
        if (delCats.size() <= 0) {
            return null;
        }
        Enumeration enCats = delCats.elements();
        if (this.catalogManager.debug.getDebug() > 1) {
            this.catalogManager.debug.message(2, "Switching to delegated catalog(s):");
            while (enCats.hasMoreElements()) {
                this.catalogManager.debug.message(2, new StringBuilder(Utils.FILE_SEPARATOR).append((String) enCats.nextElement()).toString());
            }
        }
        Catalog dcat = newCatalog();
        enCats = delCats.elements();
        while (enCats.hasMoreElements()) {
            dcat.parseCatalog((String) enCats.nextElement());
        }
        return dcat.resolveSystem(systemId);
    }

    public String resolveURI(String uri) throws MalformedURLException, IOException {
        this.catalogManager.debug.message(3, "resolveURI(" + uri + ")");
        uri = normalizeURI(uri);
        if (uri != null && uri.startsWith("urn:publicid:")) {
            return resolvePublic(PublicId.decodeURN(uri), null);
        }
        if (uri != null) {
            String resolved = resolveLocalURI(uri);
            if (resolved != null) {
                return resolved;
            }
        }
        return resolveSubordinateCatalogs(URI, null, null, uri);
    }

    protected String resolveLocalURI(String uri) throws MalformedURLException, IOException {
        Enumeration en = this.catalogEntries.elements();
        while (en.hasMoreElements()) {
            CatalogEntry e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == URI && e.getEntryArg(0).equals(uri)) {
                return e.getEntryArg(1);
            }
        }
        en = this.catalogEntries.elements();
        String startString = null;
        String prefix = null;
        while (en.hasMoreElements()) {
            String p;
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == REWRITE_URI) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length()) {
                    if (p.equals(uri.substring(0, p.length())) && (startString == null || p.length() > startString.length())) {
                        startString = p;
                        prefix = e.getEntryArg(1);
                    }
                }
            }
        }
        if (prefix != null) {
            return new StringBuilder(String.valueOf(prefix)).append(uri.substring(startString.length())).toString();
        }
        en = this.catalogEntries.elements();
        String suffixString = null;
        String suffixURI = null;
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == URI_SUFFIX) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length() && uri.endsWith(p)) {
                    if (suffixString == null || p.length() > suffixString.length()) {
                        suffixString = p;
                        suffixURI = e.getEntryArg(1);
                    }
                }
            }
        }
        if (suffixURI != null) {
            return suffixURI;
        }
        en = this.catalogEntries.elements();
        Vector delCats = new Vector();
        while (en.hasMoreElements()) {
            e = (CatalogEntry) en.nextElement();
            if (e.getEntryType() == DELEGATE_URI) {
                p = e.getEntryArg(0);
                if (p.length() <= uri.length()) {
                    if (p.equals(uri.substring(0, p.length()))) {
                        delCats.addElement(e.getEntryArg(1));
                    }
                }
            }
        }
        if (delCats.size() <= 0) {
            return null;
        }
        Enumeration enCats = delCats.elements();
        if (this.catalogManager.debug.getDebug() > 1) {
            this.catalogManager.debug.message(2, "Switching to delegated catalog(s):");
            while (enCats.hasMoreElements()) {
                this.catalogManager.debug.message(2, new StringBuilder(Utils.FILE_SEPARATOR).append((String) enCats.nextElement()).toString());
            }
        }
        Catalog dcat = newCatalog();
        enCats = delCats.elements();
        while (enCats.hasMoreElements()) {
            dcat.parseCatalog((String) enCats.nextElement());
        }
        return dcat.resolveURI(uri);
    }

    protected synchronized String resolveSubordinateCatalogs(int entityType, String entityName, String publicId, String systemId) throws MalformedURLException, IOException {
        String catfile;
        String resolved;
        for (int catPos = 0; catPos < this.catalogs.size(); catPos++) {
            Catalog c;
            try {
                c = (Catalog) this.catalogs.elementAt(catPos);
            } catch (ClassCastException e) {
                catfile = (String) this.catalogs.elementAt(catPos);
                c = newCatalog();
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
            resolved = null;
            if (entityType == DOCTYPE) {
                resolved = c.resolveDoctype(entityName, publicId, systemId);
            } else if (entityType == DOCUMENT) {
                resolved = c.resolveDocument();
            } else if (entityType == ENTITY) {
                resolved = c.resolveEntity(entityName, publicId, systemId);
            } else if (entityType == NOTATION) {
                resolved = c.resolveNotation(entityName, publicId, systemId);
            } else if (entityType == PUBLIC) {
                resolved = c.resolvePublic(publicId, systemId);
            } else if (entityType == SYSTEM) {
                resolved = c.resolveSystem(systemId);
            } else if (entityType == URI) {
                resolved = c.resolveURI(systemId);
            }
            if (resolved != null) {
                break;
            }
        }
        resolved = null;
        return resolved;
    }

    protected String fixSlashes(String sysid) {
        return sysid.replace('\\', '/');
    }

    protected String makeAbsolute(String sysid) {
        URL local = null;
        sysid = fixSlashes(sysid);
        try {
            local = new URL(this.base, sysid);
        } catch (MalformedURLException e) {
            this.catalogManager.debug.message(1, "Malformed URL on system identifier", sysid);
        }
        if (local != null) {
            return local.toString();
        }
        return sysid;
    }

    protected String normalizeURI(String uriref) {
        String newRef = "";
        if (uriref == null) {
            return null;
        }
        try {
            byte[] bytes = uriref.getBytes("UTF-8");
            for (int count = 0; count < bytes.length; count++) {
                int ch = bytes[count] & 255;
                if (ch <= 32 || ch > 127 || ch == 34 || ch == 60 || ch == 62 || ch == 92 || ch == 94 || ch == 96 || ch == 123 || ch == 124 || ch == 125 || ch == 127) {
                    newRef = new StringBuilder(String.valueOf(newRef)).append(encodedByte(ch)).toString();
                } else {
                    newRef = new StringBuilder(String.valueOf(newRef)).append((char) bytes[count]).toString();
                }
            }
            return newRef;
        } catch (UnsupportedEncodingException e) {
            this.catalogManager.debug.message(1, "UTF-8 is an unsupported encoding!?");
            return uriref;
        }
    }

    protected String encodedByte(int b) {
        String hex = Integer.toHexString(b).toUpperCase();
        if (hex.length() < 2) {
            return "%0" + hex;
        }
        return "%" + hex;
    }

    protected void addDelegate(CatalogEntry entry) {
        int pos = 0;
        String partial = entry.getEntryArg(0);
        Enumeration local = this.localDelegate.elements();
        while (local.hasMoreElements()) {
            String dp = ((CatalogEntry) local.nextElement()).getEntryArg(0);
            if (!dp.equals(partial)) {
                if (dp.length() > partial.length()) {
                    pos++;
                }
                if (dp.length() < partial.length()) {
                    break;
                }
            }
            return;
        }
        if (this.localDelegate.size() == 0) {
            this.localDelegate.addElement(entry);
        } else {
            this.localDelegate.insertElementAt(entry, pos);
        }
    }
}
