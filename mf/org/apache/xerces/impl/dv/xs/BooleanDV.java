package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class BooleanDV extends TypeValidator {
    public short getAllowedFacets() {
        return (short) 24;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (SchemaSymbols.ATTVAL_FALSE.equals(content) || SchemaSymbols.ATTVAL_FALSE_0.equals(content)) {
            return Boolean.FALSE;
        }
        if (SchemaSymbols.ATTVAL_TRUE.equals(content) || SchemaSymbols.ATTVAL_TRUE_1.equals(content)) {
            return Boolean.TRUE;
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_BOOLEAN});
    }
}
