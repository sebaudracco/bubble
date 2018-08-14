package mf.org.apache.xerces.impl.dv;

public interface DatatypeValidator {
    void validate(String str, ValidationContext validationContext) throws InvalidDatatypeValueException;
}
