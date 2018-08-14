package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGColorProfileElement extends SVGElement, SVGURIReference, SVGRenderingIntent {
    String getLocal();

    String getName();

    short getRenderingIntent();

    void setLocal(String str) throws DOMException;

    void setName(String str) throws DOMException;

    void setRenderingIntent(short s) throws DOMException;
}
