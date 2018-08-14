package mf.javax.xml.transform.sax;

import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.Templates;
import mf.javax.xml.transform.TransformerConfigurationException;
import mf.javax.xml.transform.TransformerFactory;
import org.xml.sax.XMLFilter;

public abstract class SAXTransformerFactory extends TransformerFactory {
    public static final String FEATURE = "http://javax.xml.transform.sax.SAXTransformerFactory/feature";
    public static final String FEATURE_XMLFILTER = "http://javax.xml.transform.sax.SAXTransformerFactory/feature/xmlfilter";

    public abstract TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException;

    public abstract TransformerHandler newTransformerHandler() throws TransformerConfigurationException;

    public abstract TransformerHandler newTransformerHandler(Source source) throws TransformerConfigurationException;

    public abstract TransformerHandler newTransformerHandler(Templates templates) throws TransformerConfigurationException;

    public abstract XMLFilter newXMLFilter(Source source) throws TransformerConfigurationException;

    public abstract XMLFilter newXMLFilter(Templates templates) throws TransformerConfigurationException;

    protected SAXTransformerFactory() {
    }
}
