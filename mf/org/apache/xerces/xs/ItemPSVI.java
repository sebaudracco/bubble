package mf.org.apache.xerces.xs;

public interface ItemPSVI {
    public static final short VALIDATION_FULL = (short) 2;
    public static final short VALIDATION_NONE = (short) 0;
    public static final short VALIDATION_PARTIAL = (short) 1;
    public static final short VALIDITY_INVALID = (short) 1;
    public static final short VALIDITY_NOTKNOWN = (short) 0;
    public static final short VALIDITY_VALID = (short) 2;

    Object getActualNormalizedValue() throws XSException;

    short getActualNormalizedValueType() throws XSException;

    StringList getErrorCodes();

    StringList getErrorMessages();

    boolean getIsSchemaSpecified();

    ShortList getItemValueTypes() throws XSException;

    XSSimpleTypeDefinition getMemberTypeDefinition();

    String getSchemaDefault();

    String getSchemaNormalizedValue();

    XSValue getSchemaValue();

    XSTypeDefinition getTypeDefinition();

    short getValidationAttempted();

    String getValidationContext();

    short getValidity();
}
