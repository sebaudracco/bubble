package mf.org.apache.xerces.xs;

import mf.org.w3c.dom.ls.LSInput;

public interface XSImplementation {
    LSInputList createLSInputList(LSInput[] lSInputArr);

    StringList createStringList(String[] strArr);

    XSLoader createXSLoader(StringList stringList) throws XSException;

    StringList getRecognizedVersions();
}
