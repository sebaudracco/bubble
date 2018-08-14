package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Element;

public interface SVGElement extends Element {
    String getId();

    SVGSVGElement getOwnerSVGElement();

    SVGElement getViewportElement();

    String getXMLbase();

    void setId(String str) throws DOMException;

    void setXMLbase(String str) throws DOMException;
}
