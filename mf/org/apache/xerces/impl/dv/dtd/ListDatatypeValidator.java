package mf.org.apache.xerces.impl.dv.dtd;

import java.util.StringTokenizer;
import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

public class ListDatatypeValidator implements DatatypeValidator {
    final DatatypeValidator fItemValidator;

    public ListDatatypeValidator(DatatypeValidator itemDV) {
        this.fItemValidator = itemDV;
    }

    public void validate(String content, ValidationContext context) throws InvalidDatatypeValueException {
        StringTokenizer parsedList = new StringTokenizer(content, " ");
        if (parsedList.countTokens() == 0) {
            throw new InvalidDatatypeValueException("EmptyList", null);
        }
        while (parsedList.hasMoreTokens()) {
            this.fItemValidator.validate(parsedList.nextToken(), context);
        }
    }
}
