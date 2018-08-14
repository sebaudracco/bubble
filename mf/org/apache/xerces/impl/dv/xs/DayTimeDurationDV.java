package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.Duration;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

class DayTimeDurationDV extends DurationDV {
    DayTimeDurationDV() {
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content, 2);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "dayTimeDuration"});
        }
    }

    protected Duration getDuration(DateTimeData date) {
        BigInteger valueOf;
        BigInteger valueOf2;
        BigInteger valueOf3;
        BigDecimal bigDecimal;
        boolean z = true;
        int sign = 1;
        if (date.day < 0 || date.hour < 0 || date.minute < 0 || date.second < 0.0d) {
            sign = -1;
        }
        DatatypeFactory datatypeFactory = datatypeFactory;
        if (sign != 1) {
            z = false;
        }
        if (date.day != Integer.MIN_VALUE) {
            valueOf = BigInteger.valueOf((long) (date.day * sign));
        } else {
            valueOf = null;
        }
        if (date.hour != Integer.MIN_VALUE) {
            valueOf2 = BigInteger.valueOf((long) (date.hour * sign));
        } else {
            valueOf2 = null;
        }
        if (date.minute != Integer.MIN_VALUE) {
            valueOf3 = BigInteger.valueOf((long) (date.minute * sign));
        } else {
            valueOf3 = null;
        }
        if (date.second != -2.147483648E9d) {
            bigDecimal = new BigDecimal(String.valueOf(((double) sign) * date.second));
        } else {
            bigDecimal = null;
        }
        return datatypeFactory.newDuration(z, null, null, valueOf, valueOf2, valueOf3, bigDecimal);
    }
}
