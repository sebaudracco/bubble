package mf.org.apache.html.dom;

import mf.org.apache.xerces.dom.DOMImplementationImpl;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.html.HTMLDOMImplementation;
import mf.org.w3c.dom.html.HTMLDocument;

public class HTMLDOMImplementationImpl extends DOMImplementationImpl implements HTMLDOMImplementation {
    private static final HTMLDOMImplementation _instance = new HTMLDOMImplementationImpl();

    private HTMLDOMImplementationImpl() {
    }

    public final HTMLDocument createHTMLDocument(String title) throws DOMException {
        if (title == null) {
            throw new NullPointerException("HTM014 Argument 'title' is null.");
        }
        HTMLDocument doc = new HTMLDocumentImpl();
        doc.setTitle(title);
        return doc;
    }

    public static HTMLDOMImplementation getHTMLDOMImplementation() {
        return _instance;
    }
}
