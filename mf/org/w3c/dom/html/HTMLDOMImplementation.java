package mf.org.w3c.dom.html;

import mf.org.w3c.dom.DOMImplementation;

public interface HTMLDOMImplementation extends DOMImplementation {
    HTMLDocument createHTMLDocument(String str);
}
