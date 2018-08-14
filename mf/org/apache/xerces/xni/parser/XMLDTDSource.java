package mf.org.apache.xerces.xni.parser;

import mf.org.apache.xerces.xni.XMLDTDHandler;

public interface XMLDTDSource {
    XMLDTDHandler getDTDHandler();

    void setDTDHandler(XMLDTDHandler xMLDTDHandler);
}
