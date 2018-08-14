package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGPreserveAspectRatio {
    public static final short SVG_MEETORSLICE_MEET = (short) 1;
    public static final short SVG_MEETORSLICE_SLICE = (short) 2;
    public static final short SVG_MEETORSLICE_UNKNOWN = (short) 0;
    public static final short SVG_PRESERVEASPECTRATIO_NONE = (short) 1;
    public static final short SVG_PRESERVEASPECTRATIO_UNKNOWN = (short) 0;
    public static final short SVG_PRESERVEASPECTRATIO_XMAXYMAX = (short) 10;
    public static final short SVG_PRESERVEASPECTRATIO_XMAXYMID = (short) 7;
    public static final short SVG_PRESERVEASPECTRATIO_XMAXYMIN = (short) 4;
    public static final short SVG_PRESERVEASPECTRATIO_XMIDYMAX = (short) 9;
    public static final short SVG_PRESERVEASPECTRATIO_XMIDYMID = (short) 6;
    public static final short SVG_PRESERVEASPECTRATIO_XMIDYMIN = (short) 3;
    public static final short SVG_PRESERVEASPECTRATIO_XMINYMAX = (short) 8;
    public static final short SVG_PRESERVEASPECTRATIO_XMINYMID = (short) 5;
    public static final short SVG_PRESERVEASPECTRATIO_XMINYMIN = (short) 2;

    short getAlign();

    short getMeetOrSlice();

    void setAlign(short s) throws DOMException;

    void setMeetOrSlice(short s) throws DOMException;
}
