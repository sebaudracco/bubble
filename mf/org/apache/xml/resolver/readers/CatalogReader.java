package mf.org.apache.xml.resolver.readers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import mf.org.apache.xml.resolver.Catalog;
import mf.org.apache.xml.resolver.CatalogException;

public interface CatalogReader {
    void readCatalog(Catalog catalog, InputStream inputStream) throws IOException, CatalogException;

    void readCatalog(Catalog catalog, String str) throws MalformedURLException, IOException, CatalogException;
}
