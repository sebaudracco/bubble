package mf.org.w3c.dom.svg;

public interface SVGPathSeg {
    public static final short PATHSEG_ARC_ABS = (short) 10;
    public static final short PATHSEG_ARC_REL = (short) 11;
    public static final short PATHSEG_CLOSEPATH = (short) 1;
    public static final short PATHSEG_CURVETO_CUBIC_ABS = (short) 6;
    public static final short PATHSEG_CURVETO_CUBIC_REL = (short) 7;
    public static final short PATHSEG_CURVETO_CUBIC_SMOOTH_ABS = (short) 16;
    public static final short PATHSEG_CURVETO_CUBIC_SMOOTH_REL = (short) 17;
    public static final short PATHSEG_CURVETO_QUADRATIC_ABS = (short) 8;
    public static final short PATHSEG_CURVETO_QUADRATIC_REL = (short) 9;
    public static final short PATHSEG_CURVETO_QUADRATIC_SMOOTH_ABS = (short) 18;
    public static final short PATHSEG_CURVETO_QUADRATIC_SMOOTH_REL = (short) 19;
    public static final short PATHSEG_LINETO_ABS = (short) 4;
    public static final short PATHSEG_LINETO_HORIZONTAL_ABS = (short) 12;
    public static final short PATHSEG_LINETO_HORIZONTAL_REL = (short) 13;
    public static final short PATHSEG_LINETO_REL = (short) 5;
    public static final short PATHSEG_LINETO_VERTICAL_ABS = (short) 14;
    public static final short PATHSEG_LINETO_VERTICAL_REL = (short) 15;
    public static final short PATHSEG_MOVETO_ABS = (short) 2;
    public static final short PATHSEG_MOVETO_REL = (short) 3;
    public static final short PATHSEG_UNKNOWN = (short) 0;

    short getPathSegType();

    String getPathSegTypeAsLetter();
}
