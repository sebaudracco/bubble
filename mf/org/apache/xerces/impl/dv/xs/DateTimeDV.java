package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigInteger;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class DateTimeDV extends AbstractDateTimeDV {
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_DATETIME});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        int end = indexOf(str, 0, len, 'T');
        int dateEnd = getDate(str, 0, end, date);
        getTime(str, end + 1, len, date);
        if (dateEnd != end) {
            throw new RuntimeException(new StringBuilder(String.valueOf(str)).append(" is an invalid dateTime dataype value. ").append("Invalid character(s) seprating date and time values.").toString());
        }
        validateDateTime(date);
        saveUnnormalized(date);
        if (!(date.utc == 0 || date.utc == 90)) {
            normalize(date);
        }
        return date;
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        return datatypeFactory.newXMLGregorianCalendar(BigInteger.valueOf((long) date.unNormYear), date.unNormMonth, date.unNormDay, date.unNormHour, date.unNormMinute, (int) date.unNormSecond, date.unNormSecond != 0.0d ? getFractionalSecondsAsBigDecimal(date) : null, date.hasTimeZone() ? (date.timezoneHr * 60) + date.timezoneMin : Integer.MIN_VALUE);
    }
}
