package mf.org.apache.xerces.xni.parser;

import java.io.IOException;
import mf.org.apache.xerces.xni.XNIException;

public interface XMLDocumentScanner extends XMLDocumentSource {
    boolean scanDocument(boolean z) throws IOException, XNIException;

    void setInputSource(XMLInputSource xMLInputSource) throws IOException;
}
