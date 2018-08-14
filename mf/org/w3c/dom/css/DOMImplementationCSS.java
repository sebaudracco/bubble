package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DOMImplementation;

public interface DOMImplementationCSS extends DOMImplementation {
    CSSStyleSheet createCSSStyleSheet(String str, String str2) throws DOMException;
}
