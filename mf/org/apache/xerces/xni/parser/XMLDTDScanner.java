package mf.org.apache.xerces.xni.parser;

import java.io.IOException;
import mf.org.apache.xerces.xni.XNIException;

public interface XMLDTDScanner extends XMLDTDSource, XMLDTDContentModelSource {
    boolean scanDTDExternalSubset(boolean z) throws IOException, XNIException;

    boolean scanDTDInternalSubset(boolean z, boolean z2, boolean z3) throws IOException, XNIException;

    void setInputSource(XMLInputSource xMLInputSource) throws IOException;
}
