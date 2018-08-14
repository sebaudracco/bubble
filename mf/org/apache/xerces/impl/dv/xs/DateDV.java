package mf.org.apache.xerces.impl.dv.xs;

import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

public class DateDV extends DateTimeDV {
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "date"});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        parseTimeZone(str, getDate(str, 0, len, date), len, date);
        validateDateTime(date);
        saveUnnormalized(date);
        if (!(date.utc == 0 || date.utc == 90)) {
            normalize(date);
        }
        return date;
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(25);
        append(message, date.year, 4);
        message.append('-');
        append(message, date.month, 2);
        message.append('-');
        append(message, date.day, 2);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        int i;
        DatatypeFactory datatypeFactory = datatypeFactory;
        int i2 = date.unNormYear;
        int i3 = date.unNormMonth;
        int i4 = date.unNormDay;
        if (date.hasTimeZone()) {
            i = (date.timezoneHr * 60) + date.timezoneMin;
        } else {
            i = Integer.MIN_VALUE;
        }
        return datatypeFactory.newXMLGregorianCalendar(i2, i3, i4, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i);
    }
}
