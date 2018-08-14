package mf.org.apache.xerces.impl.dv.dtd;

import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

public class ENTITYDatatypeValidator implements DatatypeValidator {
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (!context.isEntityUnparsed(content)) {
            throw new InvalidDatatypeValueException("ENTITYNotUnparsed", new Object[]{content});
        }
    }
}
