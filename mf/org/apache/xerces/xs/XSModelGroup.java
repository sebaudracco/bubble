package mf.org.apache.xerces.xs;

public interface XSModelGroup extends XSTerm {
    public static final short COMPOSITOR_ALL = (short) 3;
    public static final short COMPOSITOR_CHOICE = (short) 2;
    public static final short COMPOSITOR_SEQUENCE = (short) 1;

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getCompositor();

    XSObjectList getParticles();
}
