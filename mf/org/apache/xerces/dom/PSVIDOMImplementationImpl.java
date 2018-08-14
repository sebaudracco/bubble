package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.impl.Constants;
import mf.org.w3c.dom.DOMImplementation;
import mf.org.w3c.dom.DocumentType;

public class PSVIDOMImplementationImpl extends DOMImplementationImpl {
    static final PSVIDOMImplementationImpl singleton = new PSVIDOMImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public boolean hasFeature(String feature, String version) {
        return super.hasFeature(feature, version) || feature.equalsIgnoreCase(Constants.DOM_PSVI);
    }

    protected CoreDocumentImpl createDocument(DocumentType doctype) {
        return new PSVIDocumentImpl(doctype);
    }
}
