package mf.org.apache.xml.resolver.readers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import mf.javax.xml.parsers.DocumentBuilderFactory;
import mf.javax.xml.parsers.ParserConfigurationException;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogException;
import mf.org.apache.xml.resolver.helpers.Namespaces;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMCatalogReader implements CatalogReader {
    protected Hashtable namespaceMap = new Hashtable();

    public void setCatalogParser(String namespaceURI, String rootElement, String parserClass) {
        if (namespaceURI == null) {
            this.namespaceMap.put(rootElement, parserClass);
        } else {
            this.namespaceMap.put("{" + namespaceURI + "}" + rootElement, parserClass);
        }
    }

    public String getCatalogParser(String namespaceURI, String rootElement) {
        if (namespaceURI == null) {
            return (String) this.namespaceMap.get(rootElement);
        }
        return (String) this.namespaceMap.get("{" + namespaceURI + "}" + rootElement);
    }

    public void readCatalog(Catalog catalog, InputStream is) throws IOException, CatalogException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(false);
        try {
            try {
                Element root = factory.newDocumentBuilder().parse(is).getDocumentElement();
                String namespaceURI = Namespaces.getNamespaceURI(root);
                String localName = Namespaces.getLocalName(root);
                String domParserClass = getCatalogParser(namespaceURI, localName);
                if (domParserClass != null) {
                    try {
                        DOMCatalogParser domParser = (DOMCatalogParser) Class.forName(domParserClass).newInstance();
                        for (Node node = root.getFirstChild(); node != null; node = node.getNextSibling()) {
                            domParser.parseCatalogEntry(catalog, node);
                        }
                    } catch (ClassNotFoundException e) {
                        catalog.getCatalogManager().debug.message(1, "Cannot load XML Catalog Parser class", domParserClass);
                        throw new CatalogException(6);
                    } catch (InstantiationException e2) {
                        catalog.getCatalogManager().debug.message(1, "Cannot instantiate XML Catalog Parser class", domParserClass);
                        throw new CatalogException(6);
                    } catch (IllegalAccessException e3) {
                        catalog.getCatalogManager().debug.message(1, "Cannot access XML Catalog Parser class", domParserClass);
                        throw new CatalogException(6);
                    } catch (ClassCastException e4) {
                        catalog.getCatalogManager().debug.message(1, "Cannot cast XML Catalog Parser class", domParserClass);
                        throw new CatalogException(6);
                    }
                } else if (namespaceURI == null) {
                    catalog.getCatalogManager().debug.message(1, "No Catalog parser for " + localName);
                } else {
                    catalog.getCatalogManager().debug.message(1, "No Catalog parser for {" + namespaceURI + "}" + localName);
                }
            } catch (SAXException e5) {
                throw new CatalogException(5);
            }
        } catch (ParserConfigurationException e6) {
            throw new CatalogException(6);
        }
    }

    public void readCatalog(Catalog catalog, String fileUrl) throws MalformedURLException, IOException, CatalogException {
        readCatalog(catalog, new URL(fileUrl).openConnection().getInputStream());
    }
}
