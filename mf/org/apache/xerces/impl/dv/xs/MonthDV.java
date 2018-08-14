package mf.org.apache.xerces.impl.dv.xs;

import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class MonthDV extends AbstractDateTimeDV {
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_MONTH});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        date.year = 2000;
        date.day = 1;
        if (str.charAt(0) == '-' && str.charAt(1) == '-') {
            int stop = 4;
            date.month = parseInt(str, 2, 4);
            if (str.length() >= 6 && str.charAt(4) == '-' && str.charAt(5) == '-') {
                stop = 4 + 2;
            }
            if (stop < len) {
                if (isNextCharUTCSign(str, stop, len)) {
                    getTimeZone(str, date, stop, len);
                } else {
                    throw new SchemaDateTimeException("Error in month parsing: " + str);
                }
            }
            validateDateTime(date);
            saveUnnormalized(date);
            if (!(date.utc == 0 || date.utc == 90)) {
                normalize(date);
            }
            date.position = 1;
            return date;
        }
        throw new SchemaDateTimeException("Invalid format for gMonth: " + str);
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(5);
        message.append('-');
        message.append('-');
        append(message, date.month, 2);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        int i;
        DatatypeFactory datatypeFactory = datatypeFactory;
        int i2 = date.unNormMonth;
        if (date.hasTimeZone()) {
            i = (date.timezoneHr * 60) + date.timezoneMin;
        } else {
            i = Integer.MIN_VALUE;
        }
        return datatypeFactory.newXMLGregorianCalendar(Integer.MIN_VALUE, i2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i);
    }
}
