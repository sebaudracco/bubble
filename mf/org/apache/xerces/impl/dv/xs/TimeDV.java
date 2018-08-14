package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

public class TimeDV extends AbstractDateTimeDV {
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "time"});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        date.year = 2000;
        date.month = 1;
        date.day = 15;
        getTime(str, 0, len, date);
        validateDateTime(date);
        saveUnnormalized(date);
        if (!(date.utc == 0 || date.utc == 90)) {
            normalize(date);
            date.day = 15;
        }
        date.position = 2;
        return date;
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(16);
        append(message, date.hour, 2);
        message.append(':');
        append(message, date.minute, 2);
        message.append(':');
        append(message, date.second);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        BigDecimal fractionalSecondsAsBigDecimal;
        int i;
        DatatypeFactory datatypeFactory = datatypeFactory;
        int i2 = date.unNormHour;
        int i3 = date.unNormMinute;
        int i4 = (int) date.unNormSecond;
        if (date.unNormSecond != 0.0d) {
            fractionalSecondsAsBigDecimal = getFractionalSecondsAsBigDecimal(date);
        } else {
            fractionalSecondsAsBigDecimal = null;
        }
        if (date.hasTimeZone()) {
            i = date.timezoneMin + (date.timezoneHr * 60);
        } else {
            i = Integer.MIN_VALUE;
        }
        return datatypeFactory.newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, i2, i3, i4, fractionalSecondsAsBigDecimal, i);
    }
}
