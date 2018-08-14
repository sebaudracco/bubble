package mf.org.apache.xml.resolver.readers;

import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogEntry;
import mf.org.apache.xml.resolver.CatalogException;
import mf.org.apache.xml.resolver.helpers.PublicId;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class OASISXMLCatalogReader extends SAXCatalogReader implements SAXCatalogParser {
    public static final String namespaceName = "urn:oasis:names:tc:entity:xmlns:xml:catalog";
    public static final String tr9401NamespaceName = "urn:oasis:names:tc:entity:xmlns:tr9401:catalog";
    protected Stack baseURIStack = new Stack();
    protected Catalog catalog = null;
    protected Stack namespaceStack = new Stack();
    protected Stack overrideStack = new Stack();

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
        this.debug = catalog.getCatalogManager().debug;
    }

    public Catalog getCatalog() {
        return this.catalog;
    }

    public OASISXMLCatalogReader(SAXParserFactory parserFactory, Catalog catalog) {
        super(parserFactory);
        setCatalog(catalog);
    }

    protected boolean inExtensionNamespace() {
        boolean inExtension = false;
        Enumeration elements = this.namespaceStack.elements();
        while (!inExtension && elements.hasMoreElements()) {
            String ns = (String) elements.nextElement();
            if (ns == null) {
                inExtension = true;
            } else {
                inExtension = (ns.equals(tr9401NamespaceName) || ns.equals(namespaceName)) ? false : true;
            }
        }
        return inExtension;
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void startDocument() throws SAXException {
        this.baseURIStack.push(this.catalog.getCurrentBase());
        this.overrideStack.push(this.catalog.getDefaultOverride());
    }

    public void endDocument() throws SAXException {
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        int entryType = -1;
        Vector vector = new Vector();
        this.namespaceStack.push(namespaceURI);
        boolean inExtension = inExtensionNamespace();
        if (!(namespaceURI == null || !namespaceName.equals(namespaceURI) || inExtension)) {
            if (atts.getValue("xml:base") != null) {
                String baseURI = atts.getValue("xml:base");
                entryType = Catalog.BASE;
                vector.add(baseURI);
                this.baseURIStack.push(baseURI);
                this.debug.message(4, "xml:base", baseURI);
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, vector));
                } catch (CatalogException cex) {
                    if (cex.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry (base)", localName);
                    }
                }
                entryType = -1;
                vector = new Vector();
            } else {
                this.baseURIStack.push(this.baseURIStack.peek());
            }
            if (localName.equals("catalog") || localName.equals("group")) {
                if (atts.getValue("prefer") != null) {
                    String override = atts.getValue("prefer");
                    if (override.equals(HeaderConstants.PUBLIC)) {
                        override = "yes";
                    } else if (override.equals("system")) {
                        override = "no";
                    } else {
                        this.debug.message(1, "Invalid prefer: must be 'system' or 'public'", localName);
                        override = this.catalog.getDefaultOverride();
                    }
                    entryType = Catalog.OVERRIDE;
                    vector.add(override);
                    this.overrideStack.push(override);
                    this.debug.message(4, "override", override);
                    try {
                        this.catalog.addEntry(new CatalogEntry(entryType, vector));
                    } catch (CatalogException cex2) {
                        if (cex2.getExceptionType() == 3) {
                            this.debug.message(1, "Invalid catalog entry type", localName);
                        } else if (cex2.getExceptionType() == 2) {
                            this.debug.message(1, "Invalid catalog entry (override)", localName);
                        }
                    }
                    entryType = -1;
                    vector = new Vector();
                    if (localName.equals("delegatePublic")) {
                        if (checkAttributes(atts, "publicIdStartString", "catalog")) {
                            entryType = Catalog.DELEGATE_PUBLIC;
                            vector.add(atts.getValue("publicIdStartString"));
                            vector.add(atts.getValue("catalog"));
                            this.debug.message(4, "delegatePublic", PublicId.normalize(atts.getValue("publicIdStartString")), atts.getValue("catalog"));
                        }
                    } else if (localName.equals("delegateSystem")) {
                        if (checkAttributes(atts, "systemIdStartString", "catalog")) {
                            entryType = Catalog.DELEGATE_SYSTEM;
                            vector.add(atts.getValue("systemIdStartString"));
                            vector.add(atts.getValue("catalog"));
                            this.debug.message(4, "delegateSystem", atts.getValue("systemIdStartString"), atts.getValue("catalog"));
                        }
                    } else if (localName.equals("delegateURI")) {
                        if (checkAttributes(atts, "uriStartString", "catalog")) {
                            entryType = Catalog.DELEGATE_URI;
                            vector.add(atts.getValue("uriStartString"));
                            vector.add(atts.getValue("catalog"));
                            this.debug.message(4, "delegateURI", atts.getValue("uriStartString"), atts.getValue("catalog"));
                        }
                    } else if (localName.equals("rewriteSystem")) {
                        if (checkAttributes(atts, "systemIdStartString", "rewritePrefix")) {
                            entryType = Catalog.REWRITE_SYSTEM;
                            vector.add(atts.getValue("systemIdStartString"));
                            vector.add(atts.getValue("rewritePrefix"));
                            this.debug.message(4, "rewriteSystem", atts.getValue("systemIdStartString"), atts.getValue("rewritePrefix"));
                        }
                    } else if (localName.equals("systemSuffix")) {
                        if (checkAttributes(atts, "systemIdSuffix", "uri")) {
                            entryType = Catalog.SYSTEM_SUFFIX;
                            vector.add(atts.getValue("systemIdSuffix"));
                            vector.add(atts.getValue("uri"));
                            this.debug.message(4, "systemSuffix", atts.getValue("systemIdSuffix"), atts.getValue("uri"));
                        }
                    } else if (localName.equals("rewriteURI")) {
                        if (checkAttributes(atts, "uriStartString", "rewritePrefix")) {
                            entryType = Catalog.REWRITE_URI;
                            vector.add(atts.getValue("uriStartString"));
                            vector.add(atts.getValue("rewritePrefix"));
                            this.debug.message(4, "rewriteURI", atts.getValue("uriStartString"), atts.getValue("rewritePrefix"));
                        }
                    } else if (localName.equals("uriSuffix")) {
                        if (checkAttributes(atts, "uriSuffix", "uri")) {
                            entryType = Catalog.URI_SUFFIX;
                            vector.add(atts.getValue("uriSuffix"));
                            vector.add(atts.getValue("uri"));
                            this.debug.message(4, "uriSuffix", atts.getValue("uriSuffix"), atts.getValue("uri"));
                        }
                    } else if (localName.equals("nextCatalog")) {
                        if (checkAttributes(atts, "catalog")) {
                            entryType = Catalog.CATALOG;
                            vector.add(atts.getValue("catalog"));
                            this.debug.message(4, "nextCatalog", atts.getValue("catalog"));
                        }
                    } else if (localName.equals(HeaderConstants.PUBLIC)) {
                        if (checkAttributes(atts, "publicId", "uri")) {
                            entryType = Catalog.PUBLIC;
                            vector.add(atts.getValue("publicId"));
                            vector.add(atts.getValue("uri"));
                            this.debug.message(4, HeaderConstants.PUBLIC, PublicId.normalize(atts.getValue("publicId")), atts.getValue("uri"));
                        }
                    } else if (localName.equals("system")) {
                        if (checkAttributes(atts, "systemId", "uri")) {
                            entryType = Catalog.SYSTEM;
                            vector.add(atts.getValue("systemId"));
                            vector.add(atts.getValue("uri"));
                            this.debug.message(4, "system", atts.getValue("systemId"), atts.getValue("uri"));
                        }
                    } else if (localName.equals("uri")) {
                        if (checkAttributes(atts, "name", "uri")) {
                            entryType = Catalog.URI;
                            vector.add(atts.getValue("name"));
                            vector.add(atts.getValue("uri"));
                            this.debug.message(4, "uri", atts.getValue("name"), atts.getValue("uri"));
                        }
                    } else if (!(localName.equals("catalog") || localName.equals("group"))) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    }
                    if (entryType >= 0) {
                        try {
                            this.catalog.addEntry(new CatalogEntry(entryType, vector));
                        } catch (CatalogException cex22) {
                            if (cex22.getExceptionType() == 3) {
                                this.debug.message(1, "Invalid catalog entry type", localName);
                            } else if (cex22.getExceptionType() == 2) {
                                this.debug.message(1, "Invalid catalog entry", localName);
                            }
                        }
                    }
                }
            }
            this.overrideStack.push(this.overrideStack.peek());
            if (localName.equals("delegatePublic")) {
                if (checkAttributes(atts, "publicIdStartString", "catalog")) {
                    entryType = Catalog.DELEGATE_PUBLIC;
                    vector.add(atts.getValue("publicIdStartString"));
                    vector.add(atts.getValue("catalog"));
                    this.debug.message(4, "delegatePublic", PublicId.normalize(atts.getValue("publicIdStartString")), atts.getValue("catalog"));
                }
            } else if (localName.equals("delegateSystem")) {
                if (checkAttributes(atts, "systemIdStartString", "catalog")) {
                    entryType = Catalog.DELEGATE_SYSTEM;
                    vector.add(atts.getValue("systemIdStartString"));
                    vector.add(atts.getValue("catalog"));
                    this.debug.message(4, "delegateSystem", atts.getValue("systemIdStartString"), atts.getValue("catalog"));
                }
            } else if (localName.equals("delegateURI")) {
                if (checkAttributes(atts, "uriStartString", "catalog")) {
                    entryType = Catalog.DELEGATE_URI;
                    vector.add(atts.getValue("uriStartString"));
                    vector.add(atts.getValue("catalog"));
                    this.debug.message(4, "delegateURI", atts.getValue("uriStartString"), atts.getValue("catalog"));
                }
            } else if (localName.equals("rewriteSystem")) {
                if (checkAttributes(atts, "systemIdStartString", "rewritePrefix")) {
                    entryType = Catalog.REWRITE_SYSTEM;
                    vector.add(atts.getValue("systemIdStartString"));
                    vector.add(atts.getValue("rewritePrefix"));
                    this.debug.message(4, "rewriteSystem", atts.getValue("systemIdStartString"), atts.getValue("rewritePrefix"));
                }
            } else if (localName.equals("systemSuffix")) {
                if (checkAttributes(atts, "systemIdSuffix", "uri")) {
                    entryType = Catalog.SYSTEM_SUFFIX;
                    vector.add(atts.getValue("systemIdSuffix"));
                    vector.add(atts.getValue("uri"));
                    this.debug.message(4, "systemSuffix", atts.getValue("systemIdSuffix"), atts.getValue("uri"));
                }
            } else if (localName.equals("rewriteURI")) {
                if (checkAttributes(atts, "uriStartString", "rewritePrefix")) {
                    entryType = Catalog.REWRITE_URI;
                    vector.add(atts.getValue("uriStartString"));
                    vector.add(atts.getValue("rewritePrefix"));
                    this.debug.message(4, "rewriteURI", atts.getValue("uriStartString"), atts.getValue("rewritePrefix"));
                }
            } else if (localName.equals("uriSuffix")) {
                if (checkAttributes(atts, "uriSuffix", "uri")) {
                    entryType = Catalog.URI_SUFFIX;
                    vector.add(atts.getValue("uriSuffix"));
                    vector.add(atts.getValue("uri"));
                    this.debug.message(4, "uriSuffix", atts.getValue("uriSuffix"), atts.getValue("uri"));
                }
            } else if (localName.equals("nextCatalog")) {
                if (checkAttributes(atts, "catalog")) {
                    entryType = Catalog.CATALOG;
                    vector.add(atts.getValue("catalog"));
                    this.debug.message(4, "nextCatalog", atts.getValue("catalog"));
                }
            } else if (localName.equals(HeaderConstants.PUBLIC)) {
                if (checkAttributes(atts, "publicId", "uri")) {
                    entryType = Catalog.PUBLIC;
                    vector.add(atts.getValue("publicId"));
                    vector.add(atts.getValue("uri"));
                    this.debug.message(4, HeaderConstants.PUBLIC, PublicId.normalize(atts.getValue("publicId")), atts.getValue("uri"));
                }
            } else if (localName.equals("system")) {
                if (checkAttributes(atts, "systemId", "uri")) {
                    entryType = Catalog.SYSTEM;
                    vector.add(atts.getValue("systemId"));
                    vector.add(atts.getValue("uri"));
                    this.debug.message(4, "system", atts.getValue("systemId"), atts.getValue("uri"));
                }
            } else if (localName.equals("uri")) {
                if (checkAttributes(atts, "name", "uri")) {
                    entryType = Catalog.URI;
                    vector.add(atts.getValue("name"));
                    vector.add(atts.getValue("uri"));
                    this.debug.message(4, "uri", atts.getValue("name"), atts.getValue("uri"));
                }
            } else {
                this.debug.message(1, "Invalid catalog entry type", localName);
            }
            if (entryType >= 0) {
                this.catalog.addEntry(new CatalogEntry(entryType, vector));
            }
        }
        if (namespaceURI != null && tr9401NamespaceName.equals(namespaceURI) && !inExtension) {
            if (atts.getValue("xml:base") != null) {
                baseURI = atts.getValue("xml:base");
                entryType = Catalog.BASE;
                vector.add(baseURI);
                this.baseURIStack.push(baseURI);
                this.debug.message(4, "xml:base", baseURI);
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, vector));
                } catch (CatalogException cex222) {
                    if (cex222.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex222.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry (base)", localName);
                    }
                }
                entryType = -1;
                vector = new Vector();
            } else {
                this.baseURIStack.push(this.baseURIStack.peek());
            }
            if (localName.equals("doctype")) {
                entryType = Catalog.DOCTYPE;
                vector.add(atts.getValue("name"));
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("document")) {
                entryType = Catalog.DOCUMENT;
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("dtddecl")) {
                entryType = Catalog.DTDDECL;
                vector.add(atts.getValue("publicId"));
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("entity")) {
                entryType = Catalog.ENTITY;
                vector.add(atts.getValue("name"));
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("linktype")) {
                entryType = Catalog.LINKTYPE;
                vector.add(atts.getValue("name"));
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("notation")) {
                entryType = Catalog.NOTATION;
                vector.add(atts.getValue("name"));
                vector.add(atts.getValue("uri"));
            } else if (localName.equals("sgmldecl")) {
                entryType = Catalog.SGMLDECL;
                vector.add(atts.getValue("uri"));
            } else {
                this.debug.message(1, "Invalid catalog entry type", localName);
            }
            if (entryType >= 0) {
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, vector));
                } catch (CatalogException cex2222) {
                    if (cex2222.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex2222.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry", localName);
                    }
                }
            }
        }
    }

    public boolean checkAttributes(Attributes atts, String attName) {
        if (atts.getValue(attName) != null) {
            return true;
        }
        this.debug.message(1, "Error: required attribute " + attName + " missing.");
        return false;
    }

    public boolean checkAttributes(Attributes atts, String attName1, String attName2) {
        return checkAttributes(atts, attName1) && checkAttributes(atts, attName2);
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        int entryType;
        Vector entryArgs = new Vector();
        boolean inExtension = inExtensionNamespace();
        if (!(namespaceURI == null || inExtension || (!namespaceName.equals(namespaceURI) && !tr9401NamespaceName.equals(namespaceURI)))) {
            String baseURI = (String) this.baseURIStack.peek();
            if (!baseURI.equals((String) this.baseURIStack.pop())) {
                entryType = Catalog.BASE;
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
        if (namespaceURI != null && namespaceName.equals(namespaceURI) && !inExtension && (localName.equals("catalog") || localName.equals("group"))) {
            String override = (String) this.overrideStack.peek();
            if (!override.equals((String) this.overrideStack.pop())) {
                entryType = Catalog.OVERRIDE;
                entryArgs.add(override);
                this.overrideStack.push(override);
                this.debug.message(4, "(reset) override", override);
                try {
                    this.catalog.addEntry(new CatalogEntry(entryType, entryArgs));
                } catch (CatalogException cex2) {
                    if (cex2.getExceptionType() == 3) {
                        this.debug.message(1, "Invalid catalog entry type", localName);
                    } else if (cex2.getExceptionType() == 2) {
                        this.debug.message(1, "Invalid catalog entry (roverride)", localName);
                    }
                }
            }
        }
        this.namespaceStack.pop();
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
