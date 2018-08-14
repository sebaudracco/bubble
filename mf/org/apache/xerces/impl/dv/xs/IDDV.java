package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.XMLChar;

public class IDDV extends TypeValidator {
    public short getAllowedFacets() {
        return (short) 2079;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (XMLChar.isValidNCName(content)) {
            return content;
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_NCNAME});
    }

    public void checkExtraRules(Object value, ValidationContext context) throws InvalidDatatypeValueException {
        String content = (String) value;
        if (context.isIdDeclared(content)) {
            throw new InvalidDatatypeValueException("cvc-id.2", new Object[]{content});
        } else {
            context.addId(content);
        }
    }
}
