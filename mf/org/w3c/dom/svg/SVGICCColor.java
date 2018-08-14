package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGICCColor {
    String getColorProfile();

    SVGNumberList getColors();

    void setColorProfile(String str) throws DOMException;
}
