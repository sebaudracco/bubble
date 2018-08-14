package mf.org.apache.xerces.impl.dv.dtd;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.util.XML11Char;

public class XML11NMTOKENDatatypeValidator extends NMTOKENDatatypeValidator {
    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (!XML11Char.isXML11ValidNmtoken(content)) {
            throw new InvalidDatatypeValueException("NMTOKENInvalid", new Object[]{content});
        }
    }
}
