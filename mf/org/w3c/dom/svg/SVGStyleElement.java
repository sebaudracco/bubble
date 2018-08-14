package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGStyleElement extends SVGElement {
    String getMedia();

    String getTitle();

    String getType();

    String getXMLspace();

    void setMedia(String str) throws DOMException;

    void setTitle(String str) throws DOMException;

    void setType(String str) throws DOMException;

    void setXMLspace(String str) throws DOMException;
}
