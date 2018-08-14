package mf.org.apache.xerces.xni.grammars;

import java.io.IOException;
import java.util.Locale;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLErrorHandler;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public interface XMLGrammarLoader {
    XMLEntityResolver getEntityResolver();

    XMLErrorHandler getErrorHandler();

    boolean getFeature(String str) throws XMLConfigurationException;

    Locale getLocale();

    Object getProperty(String str) throws XMLConfigurationException;

    String[] getRecognizedFeatures();

    String[] getRecognizedProperties();

    Grammar loadGrammar(XMLInputSource xMLInputSource) throws IOException, XNIException;

    void setEntityResolver(XMLEntityResolver xMLEntityResolver);

    void setErrorHandler(XMLErrorHandler xMLErrorHandler);

    void setFeature(String str, boolean z) throws XMLConfigurationException;

    void setLocale(Locale locale);

    void setProperty(String str, Object obj) throws XMLConfigurationException;
}
