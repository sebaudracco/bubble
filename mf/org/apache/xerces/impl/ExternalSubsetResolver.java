package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.XMLDTDDescription;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public interface ExternalSubsetResolver extends XMLEntityResolver {
    XMLInputSource getExternalSubset(XMLDTDDescription xMLDTDDescription) throws XNIException, IOException;
}
