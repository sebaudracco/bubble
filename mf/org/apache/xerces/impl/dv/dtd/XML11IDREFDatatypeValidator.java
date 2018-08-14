package mf.org.apache.xerces.impl.dv.dtd;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.util.XML11Char;

public class XML11IDREFDatatypeValidator extends IDREFDatatypeValidator {
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (context.useNamespaces()) {
            if (!XML11Char.isXML11ValidNCName(content)) {
                throw new InvalidDatatypeValueException("IDREFInvalidWithNamespaces", new Object[]{content});
            }
        } else if (!XML11Char.isXML11ValidName(content)) {
            throw new InvalidDatatypeValueException("IDREFInvalid", new Object[]{content});
        }
        context.addIdRef(content);
    }
}
