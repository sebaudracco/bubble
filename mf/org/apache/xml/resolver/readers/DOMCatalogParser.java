package mf.org.apache.xml.resolver.readers;

import mf.org.apache.xml.resolver.Catalog;
import mf.org.w3c.dom.Node;

public interface DOMCatalogParser {
    void parseCatalogEntry(Catalog catalog, Node node);
}
