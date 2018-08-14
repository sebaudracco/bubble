package mf.org.apache.xerces.xs;

public interface XSElementDeclaration extends XSTerm {
    boolean getAbstract();

    Object getActualVC() throws XSException;

    short getActualVCType() throws XSException;

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getConstraintType();

    String getConstraintValue();

    short getDisallowedSubstitutions();

    XSComplexTypeDefinition getEnclosingCTDefinition();

    XSNamedMap getIdentityConstraints();

    ShortList getItemValueTypes() throws XSException;

    boolean getNillable();

    short getScope();

    XSElementDeclaration getSubstitutionGroupAffiliation();

    short getSubstitutionGroupExclusions();

    XSTypeDefinition getTypeDefinition();

    XSValue getValueConstraintValue();

    boolean isDisallowedSubstitution(short s);

    boolean isSubstitutionGroupExclusion(short s);
}
