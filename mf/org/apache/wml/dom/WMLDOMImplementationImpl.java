package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLDOMImplementation;
import mf.org.apache.xerces.dom.CoreDocumentImpl;
import mf.org.apache.xerces.dom.DOMImplementationImpl;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DocumentType;

public class WMLDOMImplementationImpl extends DOMImplementationImpl implements WMLDOMImplementation {
    static final DOMImplementationImpl singleton = new WMLDOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    protected CoreDocumentImpl createDocument(DocumentType doctype) {
        return new WMLDocumentImpl(doctype);
    }
}
