package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGAltGlyphElement extends SVGTextPositioningElement, SVGURIReference {
    String getFormat();

    String getGlyphRef();

    void setFormat(String str) throws DOMException;

    void setGlyphRef(String str) throws DOMException;
}
