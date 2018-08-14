package mf.org.apache.xerces.jaxp.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.Duration;
import mf.javax.xml.datatype.XMLGregorianCalendar;

public class DatatypeFactoryImpl extends DatatypeFactory {
    public Duration newDuration(String lexicalRepresentation) {
        return new DurationImpl(lexicalRepresentation);
    }

    public Duration newDuration(long durationInMilliseconds) {
        return new DurationImpl(durationInMilliseconds);
    }

    public Duration newDuration(boolean isPositive, BigInteger years, BigInteger months, BigInteger days, BigInteger hours, BigInteger minutes, BigDecimal seconds) {
        return new DurationImpl(isPositive, years, months, days, hours, minutes, seconds);
    }

    public XMLGregorianCalendar newXMLGregorianCalendar() {
        return new XMLGregorianCalendarImpl();
    }

    public XMLGregorianCalendar newXMLGregorianCalendar(String lexicalRepresentation) {
        return new XMLGregorianCalendarImpl(lexicalRepresentation);
    }

    public XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar cal) {
        return new XMLGregorianCalendarImpl(cal);
    }

    public XMLGregorianCalendar newXMLGregorianCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone) {
        return XMLGregorianCalendarImpl.createDateTime(year, month, day, hour, minute, second, millisecond, timezone);
    }

    public XMLGregorianCalendar newXMLGregorianCalendar(BigInteger year, int month, int day, int hour, int minute, int second, BigDecimal fractionalSecond, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, hour, minute, second, fractionalSecond, timezone);
    }
}
