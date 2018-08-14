package mf.org.apache.xerces.xs;

import mf.org.w3c.dom.DOMConfiguration;
import mf.org.w3c.dom.ls.LSInput;

public interface XSLoader {
    DOMConfiguration getConfig();

    XSModel load(LSInput lSInput);

    XSModel loadInputList(LSInputList lSInputList);

    XSModel loadURI(String str);

    XSModel loadURIList(StringList stringList);
}
