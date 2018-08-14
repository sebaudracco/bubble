package mf.org.apache.xerces.xs;

public interface XSValue {
    Object getActualValue();

    short getActualValueType();

    ShortList getListValueTypes();

    XSSimpleTypeDefinition getMemberTypeDefinition();

    XSObjectList getMemberTypeDefinitions();

    String getNormalizedValue();

    XSSimpleTypeDefinition getTypeDefinition();
}
