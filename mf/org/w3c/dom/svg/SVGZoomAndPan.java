package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGZoomAndPan {
    public static final short SVG_ZOOMANDPAN_DISABLE = (short) 1;
    public static final short SVG_ZOOMANDPAN_MAGNIFY = (short) 2;
    public static final short SVG_ZOOMANDPAN_UNKNOWN = (short) 0;

    short getZoomAndPan();

    void setZoomAndPan(short s) throws DOMException;
}
