package mf.org.apache.xerces.xni.parser;

import java.io.IOException;
import java.util.Locale;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;

public interface XMLParserConfiguration extends XMLComponentManager {
    void addRecognizedFeatures(String[] strArr);

    void addRecognizedProperties(String[] strArr);

    XMLDTDContentModelHandler getDTDContentModelHandler();

    XMLDTDHandler getDTDHandler();

    XMLDocumentHandler getDocumentHandler();

    XMLEntityResolver getEntityResolver();

    XMLErrorHandler getErrorHandler();

    boolean getFeature(String str) throws XMLConfigurationException;

    Locale getLocale();

    Object getProperty(String str) throws XMLConfigurationException;

    void parse(XMLInputSource xMLInputSource) throws XNIException, IOException;

    void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler);

    void setDTDHandler(XMLDTDHandler xMLDTDHandler);

    void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler);

    void setEntityResolver(XMLEntityResolver xMLEntityResolver);

    void setErrorHandler(XMLErrorHandler xMLErrorHandler);

    void setFeature(String str, boolean z) throws XMLConfigurationException;

    void setLocale(Locale locale) throws XNIException;

    void setProperty(String str, Object obj) throws XMLConfigurationException;
}
