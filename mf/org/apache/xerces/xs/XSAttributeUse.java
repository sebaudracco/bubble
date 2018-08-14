package mf.org.apache.xerces.xs;

public interface XSAttributeUse extends XSObject {
    Object getActualVC() throws XSException;

    short getActualVCType() throws XSException;

    XSObjectList getAnnotations();

    XSAttributeDeclaration getAttrDeclaration();

    short getConstraintType();

    String getConstraintValue();

    ShortList getItemValueTypes() throws XSException;

    boolean getRequired();

    XSValue getValueConstraintValue();
}
