package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigInteger;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.Duration;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

class YearMonthDurationDV extends DurationDV {
    YearMonthDurationDV() {
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content, 1);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "yearMonthDuration"});
        }
    }

    protected Duration getDuration(DateTimeData date) {
        BigInteger valueOf;
        BigInteger valueOf2;
        boolean z = true;
        int sign = 1;
        if (date.year < 0 || date.month < 0) {
            sign = -1;
        }
        DatatypeFactory datatypeFactory = datatypeFactory;
        if (sign != 1) {
            z = false;
        }
        if (date.year != Integer.MIN_VALUE) {
            valueOf = BigInteger.valueOf((long) (date.year * sign));
        } else {
            valueOf = null;
        }
        if (date.month != Integer.MIN_VALUE) {
            valueOf2 = BigInteger.valueOf((long) (date.month * sign));
        } else {
            valueOf2 = null;
        }
        return datatypeFactory.newDuration(z, valueOf, valueOf2, null, null, null, null);
    }
}
