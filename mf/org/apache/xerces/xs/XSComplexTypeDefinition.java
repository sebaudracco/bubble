package mf.org.apache.xerces.xs;

public interface XSComplexTypeDefinition extends XSTypeDefinition {
    public static final short CONTENTTYPE_ELEMENT = (short) 2;
    public static final short CONTENTTYPE_EMPTY = (short) 0;
    public static final short CONTENTTYPE_MIXED = (short) 3;
    public static final short CONTENTTYPE_SIMPLE = (short) 1;

    boolean getAbstract();

    XSObjectList getAnnotations();

    XSObjectList getAttributeUses();

    XSWildcard getAttributeWildcard();

    short getContentType();

    short getDerivationMethod();

    XSParticle getParticle();

    short getProhibitedSubstitutions();

    XSSimpleTypeDefinition getSimpleType();

    boolean isProhibitedSubstitution(short s);
}
