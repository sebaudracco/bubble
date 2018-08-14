package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGLangSpace {
    String getXMLlang();

    String getXMLspace();

    void setXMLlang(String str) throws DOMException;

    void setXMLspace(String str) throws DOMException;
}
