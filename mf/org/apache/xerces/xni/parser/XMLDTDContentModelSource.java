package mf.org.apache.xerces.xni.parser;

import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;

public interface XMLDTDContentModelSource {
    XMLDTDContentModelHandler getDTDContentModelHandler();

    void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler);
}
