package mf.org.apache.xerces.impl.dv.xs;

import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class YearDV extends AbstractDateTimeDV {
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_YEAR});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        int i;
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        int start = 0;
        if (str.charAt(0) == '-') {
            start = 1;
        }
        int sign = findUTCSign(str, start, len);
        if (sign == -1) {
            i = len;
        } else {
            i = sign;
        }
        int length = i - start;
        if (length < 4) {
            throw new RuntimeException("Year must have 'CCYY' format");
        } else if (length <= 4 || str.charAt(start) != '0') {
            if (sign == -1) {
                date.year = parseIntYear(str, len);
            } else {
                date.year = parseIntYear(str, sign);
                getTimeZone(str, date, sign, len);
            }
            date.month = 1;
            date.day = 1;
            validateDateTime(date);
            saveUnnormalized(date);
            if (!(date.utc == 0 || date.utc == 90)) {
                normalize(date);
            }
            date.position = 0;
            return date;
        } else {
            throw new RuntimeException("Leading zeros are required if the year value would otherwise have fewer than four digits; otherwise they are forbidden");
        }
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(5);
        append(message, date.year, 4);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        int i;
        DatatypeFactory datatypeFactory = datatypeFactory;
        int i2 = date.unNormYear;
        if (date.hasTimeZone()) {
            i = (date.timezoneHr * 60) + date.timezoneMin;
        } else {
            i = Integer.MIN_VALUE;
        }
        return datatypeFactory.newXMLGregorianCalendar(i2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i);
    }
}
