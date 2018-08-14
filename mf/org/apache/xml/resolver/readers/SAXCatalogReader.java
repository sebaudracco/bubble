package mf.org.apache.xml.resolver.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Hashtable;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.parsers.ParserConfigurationException;
import mf.javax.xml.parsers.SAXParser;
import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogException;
import mf.org.apache.xml.resolver.CatalogManager;
import mf.org.apache.xml.resolver.helpers.Debug;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXCatalogReader implements CatalogReader, ContentHandler, DocumentHandler {
    private boolean abandonHope;
    private Catalog catalog;
    protected Debug debug;
    private ClassLoader loader;
    protected Hashtable namespaceMap;
    protected String parserClass;
    protected SAXParserFactory parserFactory;
    private SAXCatalogParser saxParser;

    public void setParserFactory(SAXParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public void setParserClass(String parserClass) {
        this.parserClass = parserClass;
    }

    public SAXParserFactory getParserFactory() {
        return this.parserFactory;
    }

    public String getParserClass() {
        return this.parserClass;
    }

    public void setClassLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public SAXCatalogReader() {
        this.parserFactory = null;
        this.parserClass = null;
        this.namespaceMap = new Hashtable();
        this.saxParser = null;
        this.abandonHope = false;
        this.loader = null;
        this.debug = CatalogManager.getStaticManager().debug;
        this.parserFactory = null;
        this.parserClass = null;
    }

    public SAXCatalogReader(SAXParserFactory parserFactory) {
        this.parserFactory = null;
        this.parserClass = null;
        this.namespaceMap = new Hashtable();
        this.saxParser = null;
        this.abandonHope = false;
        this.loader = null;
        this.debug = CatalogManager.getStaticManager().debug;
        this.parserFactory = parserFactory;
    }

    public SAXCatalogReader(String parserClass) {
        this.parserFactory = null;
        this.parserClass = null;
        this.namespaceMap = new Hashtable();
        this.saxParser = null;
        this.abandonHope = false;
        this.loader = null;
        this.debug = CatalogManager.getStaticManager().debug;
        this.parserClass = parserClass;
    }

    public void setCatalogParser(String namespaceURI, String rootElement, String parserClass) {
        this.namespaceMap.put("{" + (namespaceURI != null ? namespaceURI.trim() : "") + "}" + rootElement, parserClass);
    }

    public String getCatalogParser(String namespaceURI, String rootElement) {
        return (String) this.namespaceMap.get("{" + (namespaceURI != null ? namespaceURI.trim() : "") + "}" + rootElement);
    }

    public void readCatalog(Catalog catalog, String fileUrl) throws MalformedURLException, IOException, CatalogException {
        URL url;
        try {
            url = new URL(fileUrl);
        } catch (MalformedURLException e) {
            url = new URL("file:///" + fileUrl);
        }
        this.debug = catalog.getCatalogManager().debug;
        try {
            readCatalog(catalog, url.openConnection().getInputStream());
        } catch (FileNotFoundException e2) {
            catalog.getCatalogManager().debug.message(1, "Failed to load catalog, file not found", url.toString());
        }
    }

    public void readCatalog(Catalog catalog, InputStream is) throws IOException, CatalogException {
        if (this.parserFactory == null && this.parserClass == null) {
            this.debug.message(1, "Cannot read SAX catalog without a parser");
            throw new CatalogException(6);
        }
        this.debug = catalog.getCatalogManager().debug;
        EntityResolver bResolver = catalog.getCatalogManager().getBootstrapResolver();
        this.catalog = catalog;
        try {
            if (this.parserFactory != null) {
                SAXParser parser = this.parserFactory.newSAXParser();
                DefaultHandler spHandler = new SAXParserHandler();
                spHandler.setContentHandler(this);
                if (bResolver != null) {
                    spHandler.setEntityResolver(bResolver);
                }
                parser.parse(new InputSource(is), spHandler);
                return;
            }
            Parser parser2 = (Parser) Class.forName(this.parserClass, true, this.loader != null ? this.loader : getClass().getClassLoader()).newInstance();
            parser2.setDocumentHandler(this);
            if (bResolver != null) {
                parser2.setEntityResolver(bResolver);
            }
            parser2.parse(new InputSource(is));
        } catch (ClassNotFoundException e) {
            throw new CatalogException(6);
        } catch (IllegalAccessException e2) {
            throw new CatalogException(6);
        } catch (InstantiationException e3) {
            throw new CatalogException(6);
        } catch (ParserConfigurationException e4) {
            throw new CatalogException(5);
        } catch (Exception se) {
            Exception e5 = se.getException();
            UnknownHostException uhe = new UnknownHostException();
            FileNotFoundException fnfe = new FileNotFoundException();
            if (e5 != null) {
                if (e5.getClass() == uhe.getClass()) {
                    throw new CatalogException(7, e5.toString());
                } else if (e5.getClass() == fnfe.getClass()) {
                    throw new CatalogException(7, e5.toString());
                }
            }
            throw new CatalogException(se);
        }
    }

    public void setDocumentLocator(Locator locator) {
        if (this.saxParser != null) {
            this.saxParser.setDocumentLocator(locator);
        }
    }

    public void startDocument() throws SAXException {
        this.saxParser = null;
        this.abandonHope = false;
    }

    public void endDocument() throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.endDocument();
        }
    }

    public void startElement(String name, AttributeList atts) throws SAXException {
        if (!this.abandonHope) {
            if (this.saxParser == null) {
                String namespaceURI;
                String prefix = "";
                if (name.indexOf(58) > 0) {
                    prefix = name.substring(0, name.indexOf(58));
                }
                String localName = name;
                if (localName.indexOf(58) > 0) {
                    localName = localName.substring(localName.indexOf(58) + 1);
                }
                if (prefix.equals("")) {
                    namespaceURI = atts.getValue(XMLConstants.XMLNS_ATTRIBUTE);
                } else {
                    namespaceURI = atts.getValue("xmlns:" + prefix);
                }
                String saxParserClass = getCatalogParser(namespaceURI, localName);
                if (saxParserClass == null) {
                    this.abandonHope = true;
                    if (namespaceURI == null) {
                        this.debug.message(2, "No Catalog parser for " + name);
                        return;
                    } else {
                        this.debug.message(2, "No Catalog parser for {" + namespaceURI + "}" + name);
                        return;
                    }
                }
                try {
                    this.saxParser = (SAXCatalogParser) Class.forName(saxParserClass, true, this.loader != null ? this.loader : getClass().getClassLoader()).newInstance();
                    this.saxParser.setCatalog(this.catalog);
                    this.saxParser.startDocument();
                    this.saxParser.startElement(name, atts);
                    return;
                } catch (ClassNotFoundException cnfe) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, cnfe.toString());
                    return;
                } catch (InstantiationException ie) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, ie.toString());
                    return;
                } catch (IllegalAccessException iae) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, iae.toString());
                    return;
                } catch (ClassCastException cce) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, cce.toString());
                    return;
                }
            }
            this.saxParser.startElement(name, atts);
        }
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (!this.abandonHope) {
            if (this.saxParser == null) {
                String saxParserClass = getCatalogParser(namespaceURI, localName);
                if (saxParserClass == null) {
                    this.abandonHope = true;
                    if (namespaceURI == null) {
                        this.debug.message(2, "No Catalog parser for " + localName);
                        return;
                    } else {
                        this.debug.message(2, "No Catalog parser for {" + namespaceURI + "}" + localName);
                        return;
                    }
                }
                try {
                    this.saxParser = (SAXCatalogParser) Class.forName(saxParserClass, true, this.loader != null ? this.loader : getClass().getClassLoader()).newInstance();
                    this.saxParser.setCatalog(this.catalog);
                    this.saxParser.startDocument();
                    this.saxParser.startElement(namespaceURI, localName, qName, atts);
                    return;
                } catch (ClassNotFoundException cnfe) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, cnfe.toString());
                    return;
                } catch (InstantiationException ie) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, ie.toString());
                    return;
                } catch (IllegalAccessException iae) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, iae.toString());
                    return;
                } catch (ClassCastException cce) {
                    this.saxParser = null;
                    this.abandonHope = true;
                    this.debug.message(2, cce.toString());
                    return;
                }
            }
            this.saxParser.startElement(namespaceURI, localName, qName, atts);
        }
    }

    public void endElement(String name) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.endElement(name);
        }
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.endElement(namespaceURI, localName, qName);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.characters(ch, start, length);
        }
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.ignorableWhitespace(ch, start, length);
        }
    }

    public void processingInstruction(String target, String data) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.processingInstruction(target, data);
        }
    }

    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.startPrefixMapping(prefix, uri);
        }
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.endPrefixMapping(prefix);
        }
    }

    public void skippedEntity(String name) throws SAXException {
        if (this.saxParser != null) {
            this.saxParser.skippedEntity(name);
        }
    }
}
