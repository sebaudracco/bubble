package mf.org.apache.xerces.xni.parser;

import mf.org.apache.xerces.xni.XMLDocumentHandler;

public interface XMLDocumentSource {
    XMLDocumentHandler getDocumentHandler();

    void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler);
}
