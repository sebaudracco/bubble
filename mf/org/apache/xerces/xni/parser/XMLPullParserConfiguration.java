package mf.org.apache.xerces.xni.parser;

import java.io.IOException;
import mf.org.apache.xerces.xni.XNIException;

public interface XMLPullParserConfiguration extends XMLParserConfiguration {
    void cleanup();

    boolean parse(boolean z) throws XNIException, IOException;

    void setInputSource(XMLInputSource xMLInputSource) throws XMLConfigurationException, IOException;
}
