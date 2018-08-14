package mf.org.apache.xml.resolver.tools;

import mf.javax.xml.parsers.SAXParserFactory;
import mf.org.apache.xml.resolver.CatalogManager;

public class ResolvingXMLReader extends ResolvingXMLFilter {
    public static boolean namespaceAware = true;
    public static boolean validating = false;

    public ResolvingXMLReader() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(namespaceAware);
        spf.setValidating(validating);
        try {
            setParent(spf.newSAXParser().getXMLReader());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResolvingXMLReader(CatalogManager manager) {
        super(manager);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(namespaceAware);
        spf.setValidating(validating);
        try {
            setParent(spf.newSAXParser().getXMLReader());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
