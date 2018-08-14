package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGColorProfileRule extends SVGCSSRule, SVGRenderingIntent {
    String getName();

    short getRenderingIntent();

    String getSrc();

    void setName(String str) throws DOMException;

    void setRenderingIntent(short s) throws DOMException;

    void setSrc(String str) throws DOMException;
}
