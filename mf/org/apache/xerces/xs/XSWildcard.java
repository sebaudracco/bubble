package mf.org.apache.xerces.xs;

public interface XSWildcard extends XSTerm {
    public static final short NSCONSTRAINT_ANY = (short) 1;
    public static final short NSCONSTRAINT_LIST = (short) 3;
    public static final short NSCONSTRAINT_NOT = (short) 2;
    public static final short PC_LAX = (short) 3;
    public static final short PC_SKIP = (short) 2;
    public static final short PC_STRICT = (short) 1;

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getConstraintType();

    StringList getNsConstraintList();

    short getProcessContents();
}
