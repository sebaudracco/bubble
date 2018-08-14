package mf.org.apache.xml.resolver;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.resolver.helpers.BootstrapResolver;
import mf.org.apache.xml.resolver.helpers.Debug;

public class CatalogManager {
    private static String pAllowPI = "xml.catalog.allowPI";
    private static String pClassname = "xml.catalog.className";
    private static String pFiles = "xml.catalog.files";
    private static String pIgnoreMissing = "xml.catalog.ignoreMissing";
    private static String pPrefer = "xml.catalog.prefer";
    private static String pStatic = "xml.catalog.staticCatalog";
    private static String pVerbosity = "xml.catalog.verbosity";
    private static Catalog staticCatalog = null;
    private static CatalogManager staticManager = new CatalogManager();
    private BootstrapResolver bResolver = new BootstrapResolver();
    private String catalogClassName;
    private String catalogFiles;
    public Debug debug;
    private String defaultCatalogFiles;
    private boolean defaultOasisXMLCatalogPI;
    private boolean defaultPreferPublic;
    private boolean defaultRelativeCatalogs;
    private boolean defaultUseStaticCatalog;
    private int defaultVerbosity;
    private boolean fromPropertiesFile;
    private boolean ignoreMissingProperties;
    private Boolean oasisXMLCatalogPI;
    private Boolean preferPublic;
    private String propertyFile;
    private URL propertyFileURI;
    private Boolean relativeCatalogs;
    private ResourceBundle resources;
    private Boolean useStaticCatalog;
    private Integer verbosity;

    public CatalogManager() {
        boolean z = (System.getProperty(pIgnoreMissing) == null && System.getProperty(pFiles) == null) ? false : true;
        this.ignoreMissingProperties = z;
        this.propertyFile = "CatalogManager.properties";
        this.propertyFileURI = null;
        this.defaultCatalogFiles = "./xcatalog";
        this.catalogFiles = null;
        this.fromPropertiesFile = false;
        this.defaultVerbosity = 1;
        this.verbosity = null;
        this.defaultPreferPublic = true;
        this.preferPublic = null;
        this.defaultUseStaticCatalog = true;
        this.useStaticCatalog = null;
        this.defaultOasisXMLCatalogPI = true;
        this.oasisXMLCatalogPI = null;
        this.defaultRelativeCatalogs = true;
        this.relativeCatalogs = null;
        this.catalogClassName = null;
        this.debug = null;
        this.debug = new Debug();
    }

    public CatalogManager(String propertyFile) {
        boolean z = (System.getProperty(pIgnoreMissing) == null && System.getProperty(pFiles) == null) ? false : true;
        this.ignoreMissingProperties = z;
        this.propertyFile = "CatalogManager.properties";
        this.propertyFileURI = null;
        this.defaultCatalogFiles = "./xcatalog";
        this.catalogFiles = null;
        this.fromPropertiesFile = false;
        this.defaultVerbosity = 1;
        this.verbosity = null;
        this.defaultPreferPublic = true;
        this.preferPublic = null;
        this.defaultUseStaticCatalog = true;
        this.useStaticCatalog = null;
        this.defaultOasisXMLCatalogPI = true;
        this.oasisXMLCatalogPI = null;
        this.defaultRelativeCatalogs = true;
        this.relativeCatalogs = null;
        this.catalogClassName = null;
        this.debug = null;
        this.propertyFile = propertyFile;
        this.debug = new Debug();
    }

    public void setBootstrapResolver(BootstrapResolver resolver) {
        this.bResolver = resolver;
    }

    public BootstrapResolver getBootstrapResolver() {
        return this.bResolver;
    }

    private synchronized void readProperties() {
        try {
            this.propertyFileURI = CatalogManager.class.getResource(new StringBuilder(BridgeUtil.SPLIT_MARK).append(this.propertyFile).toString());
            InputStream in = CatalogManager.class.getResourceAsStream(new StringBuilder(BridgeUtil.SPLIT_MARK).append(this.propertyFile).toString());
            if (in != null) {
                this.resources = new PropertyResourceBundle(in);
                if (this.verbosity == null) {
                    try {
                        int verb = Integer.parseInt(this.resources.getString("verbosity").trim());
                        this.debug.setDebug(verb);
                        this.verbosity = new Integer(verb);
                    } catch (Exception e) {
                    }
                }
            } else if (!this.ignoreMissingProperties) {
                System.err.println("Cannot find " + this.propertyFile);
                this.ignoreMissingProperties = true;
            }
        } catch (MissingResourceException e2) {
            if (!this.ignoreMissingProperties) {
                System.err.println("Cannot read " + this.propertyFile);
            }
        } catch (IOException e3) {
            if (!this.ignoreMissingProperties) {
                System.err.println("Failure trying to read " + this.propertyFile);
            }
        }
    }

    public static CatalogManager getStaticManager() {
        return staticManager;
    }

    public boolean getIgnoreMissingProperties() {
        return this.ignoreMissingProperties;
    }

    public void setIgnoreMissingProperties(boolean ignore) {
        this.ignoreMissingProperties = ignore;
    }

    public void ignoreMissingProperties(boolean ignore) {
        setIgnoreMissingProperties(ignore);
    }

    private int queryVerbosity() {
        String defaultVerbStr = Integer.toString(this.defaultVerbosity);
        String verbStr = System.getProperty(pVerbosity);
        if (verbStr == null) {
            if (this.resources == null) {
                readProperties();
            }
            if (this.resources != null) {
                try {
                    verbStr = this.resources.getString("verbosity");
                } catch (MissingResourceException e) {
                    verbStr = defaultVerbStr;
                }
            } else {
                verbStr = defaultVerbStr;
            }
        }
        int verb = this.defaultVerbosity;
        try {
            verb = Integer.parseInt(verbStr.trim());
        } catch (Exception e2) {
            System.err.println("Cannot parse verbosity: \"" + verbStr + "\"");
        }
        if (this.verbosity == null) {
            this.debug.setDebug(verb);
            this.verbosity = new Integer(verb);
        }
        return verb;
    }

    public int getVerbosity() {
        if (this.verbosity == null) {
            this.verbosity = new Integer(queryVerbosity());
        }
        return this.verbosity.intValue();
    }

    public void setVerbosity(int verbosity) {
        this.verbosity = new Integer(verbosity);
        this.debug.setDebug(verbosity);
    }

    public int verbosity() {
        return getVerbosity();
    }

    private boolean queryRelativeCatalogs() {
        if (this.resources == null) {
            readProperties();
        }
        if (this.resources == null) {
            return this.defaultRelativeCatalogs;
        }
        try {
            String allow = this.resources.getString("relative-catalogs");
            return allow.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE) || allow.equalsIgnoreCase("yes") || allow.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE_1);
        } catch (MissingResourceException e) {
            return this.defaultRelativeCatalogs;
        }
    }

    public boolean getRelativeCatalogs() {
        if (this.relativeCatalogs == null) {
            this.relativeCatalogs = new Boolean(queryRelativeCatalogs());
        }
        return this.relativeCatalogs.booleanValue();
    }

    public void setRelativeCatalogs(boolean relative) {
        this.relativeCatalogs = new Boolean(relative);
    }

    public boolean relativeCatalogs() {
        return getRelativeCatalogs();
    }

    private String queryCatalogFiles() {
        String catalogList = System.getProperty(pFiles);
        this.fromPropertiesFile = false;
        if (catalogList == null) {
            if (this.resources == null) {
                readProperties();
            }
            if (this.resources != null) {
                try {
                    catalogList = this.resources.getString("catalogs");
                    this.fromPropertiesFile = true;
                } catch (MissingResourceException e) {
                    System.err.println(this.propertyFile + ": catalogs not found.");
                    catalogList = null;
                }
            }
        }
        if (catalogList == null) {
            return this.defaultCatalogFiles;
        }
        return catalogList;
    }

    public Vector getCatalogFiles() {
        if (this.catalogFiles == null) {
            this.catalogFiles = queryCatalogFiles();
        }
        StringTokenizer files = new StringTokenizer(this.catalogFiles, ";");
        Vector catalogs = new Vector();
        while (files.hasMoreTokens()) {
            String catalogFile = files.nextToken();
            if (this.fromPropertiesFile && !relativeCatalogs()) {
                try {
                    URL absURI = new URL(this.propertyFileURI, catalogFile);
                    URL url;
                    try {
                        catalogFile = absURI.toString();
                        url = absURI;
                    } catch (MalformedURLException e) {
                        url = absURI;
                        catalogs.add(catalogFile);
                    }
                } catch (MalformedURLException e2) {
                    catalogs.add(catalogFile);
                }
            }
            catalogs.add(catalogFile);
        }
        return catalogs;
    }

    public void setCatalogFiles(String fileList) {
        this.catalogFiles = fileList;
        this.fromPropertiesFile = false;
    }

    public Vector catalogFiles() {
        return getCatalogFiles();
    }

    private boolean queryPreferPublic() {
        String prefer = System.getProperty(pPrefer);
        if (prefer == null) {
            if (this.resources == null) {
                readProperties();
            }
            if (this.resources == null) {
                return this.defaultPreferPublic;
            }
            try {
                prefer = this.resources.getString("prefer");
            } catch (MissingResourceException e) {
                return this.defaultPreferPublic;
            }
        }
        if (prefer == null) {
            return this.defaultPreferPublic;
        }
        return prefer.equalsIgnoreCase(HeaderConstants.PUBLIC);
    }

    public boolean getPreferPublic() {
        if (this.preferPublic == null) {
            this.preferPublic = new Boolean(queryPreferPublic());
        }
        return this.preferPublic.booleanValue();
    }

    public void setPreferPublic(boolean preferPublic) {
        this.preferPublic = new Boolean(preferPublic);
    }

    public boolean preferPublic() {
        return getPreferPublic();
    }

    private boolean queryUseStaticCatalog() {
        String staticCatalog = System.getProperty(pStatic);
        if (staticCatalog == null) {
            if (this.resources == null) {
                readProperties();
            }
            if (this.resources == null) {
                return this.defaultUseStaticCatalog;
            }
            try {
                staticCatalog = this.resources.getString("static-catalog");
            } catch (MissingResourceException e) {
                return this.defaultUseStaticCatalog;
            }
        }
        if (staticCatalog == null) {
            return this.defaultUseStaticCatalog;
        }
        return staticCatalog.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE) || staticCatalog.equalsIgnoreCase("yes") || staticCatalog.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE_1);
    }

    public boolean getUseStaticCatalog() {
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(queryUseStaticCatalog());
        }
        return this.useStaticCatalog.booleanValue();
    }

    public void setUseStaticCatalog(boolean useStatic) {
        this.useStaticCatalog = new Boolean(useStatic);
    }

    public boolean staticCatalog() {
        return getUseStaticCatalog();
    }

    public Catalog getPrivateCatalog() {
        Catalog catalog = staticCatalog;
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(getUseStaticCatalog());
        }
        if (catalog == null || !this.useStaticCatalog.booleanValue()) {
            try {
                String catalogClassName = getCatalogClassName();
                if (catalogClassName == null) {
                    catalog = new Catalog();
                } else {
                    try {
                        catalog = (Catalog) Class.forName(catalogClassName).newInstance();
                    } catch (ClassNotFoundException e) {
                        this.debug.message(1, "Catalog class named '" + catalogClassName + "' could not be found. Using default.");
                        catalog = new Catalog();
                    } catch (ClassCastException e2) {
                        this.debug.message(1, "Class named '" + catalogClassName + "' is not a Catalog. Using default.");
                        catalog = new Catalog();
                    }
                }
                catalog.setCatalogManager(this);
                catalog.setupReaders();
                catalog.loadSystemCatalogs();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.useStaticCatalog.booleanValue()) {
                staticCatalog = catalog;
            }
        }
        return catalog;
    }

    public Catalog getCatalog() {
        Catalog catalog = staticCatalog;
        if (this.useStaticCatalog == null) {
            this.useStaticCatalog = new Boolean(getUseStaticCatalog());
        }
        if (catalog == null || !this.useStaticCatalog.booleanValue()) {
            catalog = getPrivateCatalog();
            if (this.useStaticCatalog.booleanValue()) {
                staticCatalog = catalog;
            }
        }
        return catalog;
    }

    public boolean queryAllowOasisXMLCatalogPI() {
        String allow = System.getProperty(pAllowPI);
        if (allow == null) {
            if (this.resources == null) {
                readProperties();
            }
            if (this.resources == null) {
                return this.defaultOasisXMLCatalogPI;
            }
            try {
                allow = this.resources.getString("allow-oasis-xml-catalog-pi");
            } catch (MissingResourceException e) {
                return this.defaultOasisXMLCatalogPI;
            }
        }
        if (allow == null) {
            return this.defaultOasisXMLCatalogPI;
        }
        return allow.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE) || allow.equalsIgnoreCase("yes") || allow.equalsIgnoreCase(SchemaSymbols.ATTVAL_TRUE_1);
    }

    public boolean getAllowOasisXMLCatalogPI() {
        if (this.oasisXMLCatalogPI == null) {
            this.oasisXMLCatalogPI = new Boolean(queryAllowOasisXMLCatalogPI());
        }
        return this.oasisXMLCatalogPI.booleanValue();
    }

    public void setAllowOasisXMLCatalogPI(boolean allowPI) {
        this.oasisXMLCatalogPI = new Boolean(allowPI);
    }

    public boolean allowOasisXMLCatalogPI() {
        return getAllowOasisXMLCatalogPI();
    }

    public String queryCatalogClassName() {
        String className = System.getProperty(pClassname);
        if (className != null) {
            return className;
        }
        if (this.resources == null) {
            readProperties();
        }
        if (this.resources == null) {
            return null;
        }
        try {
            return this.resources.getString("catalog-class-name");
        } catch (MissingResourceException e) {
            return null;
        }
    }

    public String getCatalogClassName() {
        if (this.catalogClassName == null) {
            this.catalogClassName = queryCatalogClassName();
        }
        return this.catalogClassName;
    }

    public void setCatalogClassName(String className) {
        this.catalogClassName = className;
    }

    public String catalogClassName() {
        return getCatalogClassName();
    }
}
