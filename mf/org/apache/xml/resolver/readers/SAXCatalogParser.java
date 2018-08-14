package mf.org.apache.xml.resolver.readers;

import mf.org.apache.xml.resolver.Catalog;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;

public interface SAXCatalogParser extends ContentHandler, DocumentHandler {
    void setCatalog(Catalog catalog);
}
