package mf.org.apache.xerces.impl.dv.dtd;

import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.util.XMLChar;

public class IDREFDatatypeValidator implements DatatypeValidator {
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (context.useNamespaces()) {
            if (!XMLChar.isValidNCName(content)) {
                throw new InvalidDatatypeValueException("IDREFInvalidWithNamespaces", new Object[]{content});
            }
        } else if (!XMLChar.isValidName(content)) {
            throw new InvalidDatatypeValueException("IDREFInvalid", new Object[]{content});
        }
        context.addIdRef(content);
    }
}
