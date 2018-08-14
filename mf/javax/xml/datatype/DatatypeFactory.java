package mf.javax.xml.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public abstract class DatatypeFactory {
    public static final String DATATYPEFACTORY_IMPLEMENTATION_CLASS = new String("com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl");
    public static final String DATATYPEFACTORY_PROPERTY = "javax.xml.datatype.DatatypeFactory";
    private static final Pattern XDTSCHEMA_DTD = Pattern.compile("[^YM]*[DT].*");
    private static final Pattern XDTSCHEMA_YMD = Pattern.compile("[^DT]*");

    public abstract Duration newDuration(long j);

    public abstract Duration newDuration(String str);

    public abstract Duration newDuration(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigDecimal bigDecimal);

    public abstract XMLGregorianCalendar newXMLGregorianCalendar();

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(String str);

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(BigInteger bigInteger, int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, int i6);

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar gregorianCalendar);

    protected DatatypeFactory() {
    }

    public static DatatypeFactory newInstance() throws DatatypeConfigurationException {
        try {
            return (DatatypeFactory) FactoryFinder.find(DATATYPEFACTORY_PROPERTY, DATATYPEFACTORY_IMPLEMENTATION_CLASS);
        } catch (ConfigurationError e) {
            throw new DatatypeConfigurationException(e.getMessage(), e.getException());
        }
    }

    public static DatatypeFactory newInstance(String factoryClassName, ClassLoader classLoader) throws DatatypeConfigurationException {
        try {
            return (DatatypeFactory) FactoryFinder.newInstance(factoryClassName, classLoader, false);
        } catch (ConfigurationError e) {
            throw new DatatypeConfigurationException(e.getMessage(), e.getException());
        }
    }

    public Duration newDuration(boolean isPositive, int years, int months, int days, int hours, int minutes, int seconds) {
        return newDuration(isPositive, years != Integer.MIN_VALUE ? BigInteger.valueOf((long) years) : null, months != Integer.MIN_VALUE ? BigInteger.valueOf((long) months) : null, days != Integer.MIN_VALUE ? BigInteger.valueOf((long) days) : null, hours != Integer.MIN_VALUE ? BigInteger.valueOf((long) hours) : null, minutes != Integer.MIN_VALUE ? BigInteger.valueOf((long) minutes) : null, seconds != Integer.MIN_VALUE ? BigDecimal.valueOf((long) seconds) : null);
    }

    public Duration newDurationDayTime(String lexicalRepresentation) {
        if (lexicalRepresentation == null) {
            throw new NullPointerException("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"null\"");
        } else if (XDTSCHEMA_DTD.matcher(lexicalRepresentation).matches()) {
            return newDuration(lexicalRepresentation);
        } else {
            throw new IllegalArgumentException("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"" + lexicalRepresentation + "\", data model requires years and months only.");
        }
    }

    public Duration newDurationDayTime(long durationInMilliseconds) {
        return newDuration(durationInMilliseconds);
    }

    public Duration newDurationDayTime(boolean isPositive, BigInteger day, BigInteger hour, BigInteger minute, BigInteger second) {
        return newDuration(isPositive, null, null, day, hour, minute, second != null ? new BigDecimal(second) : null);
    }

    public Duration newDurationDayTime(boolean isPositive, int day, int hour, int minute, int second) {
        return newDurationDayTime(isPositive, BigInteger.valueOf((long) day), BigInteger.valueOf((long) hour), BigInteger.valueOf((long) minute), BigInteger.valueOf((long) second));
    }

    public Duration newDurationYearMonth(String lexicalRepresentation) {
        if (lexicalRepresentation == null) {
            throw new NullPointerException("Trying to create an xdt:yearMonthDuration with an invalid lexical representation of \"null\"");
        } else if (XDTSCHEMA_YMD.matcher(lexicalRepresentation).matches()) {
            return newDuration(lexicalRepresentation);
        } else {
            throw new IllegalArgumentException("Trying to create an xdt:yearMonthDuration with an invalid lexical representation of \"" + lexicalRepresentation + "\", data model requires days and times only.");
        }
    }

    public Duration newDurationYearMonth(long durationInMilliseconds) {
        Duration fullDuration = newDuration(durationInMilliseconds);
        boolean isPositive = fullDuration.getSign() != -1;
        BigInteger years = (BigInteger) fullDuration.getField(DatatypeConstants.YEARS);
        if (years == null) {
            years = BigInteger.ZERO;
        }
        BigInteger months = (BigInteger) fullDuration.getField(DatatypeConstants.MONTHS);
        if (months == null) {
            months = BigInteger.ZERO;
        }
        return newDurationYearMonth(isPositive, years, months);
    }

    public Duration newDurationYearMonth(boolean isPositive, BigInteger year, BigInteger month) {
        return newDuration(isPositive, year, month, null, null, null, null);
    }

    public Duration newDurationYearMonth(boolean isPositive, int year, int month) {
        return newDurationYearMonth(isPositive, BigInteger.valueOf((long) year), BigInteger.valueOf((long) month));
    }

    public XMLGregorianCalendar newXMLGregorianCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone) {
        BigInteger realYear = year != Integer.MIN_VALUE ? BigInteger.valueOf((long) year) : null;
        BigDecimal realMillisecond = null;
        if (millisecond != Integer.MIN_VALUE) {
            if (millisecond < 0 || millisecond > 1000) {
                throw new IllegalArgumentException("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone)with invalid millisecond: " + millisecond);
            }
            realMillisecond = BigDecimal.valueOf((long) millisecond).movePointLeft(3);
        }
        return newXMLGregorianCalendar(realYear, month, day, hour, minute, second, realMillisecond, timezone);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarDate(int year, int month, int day, int timezone) {
        return newXMLGregorianCalendar(year, month, day, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, timezone);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int hours, int minutes, int seconds, int timezone) {
        return newXMLGregorianCalendar(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, hours, minutes, seconds, Integer.MIN_VALUE, timezone);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int hours, int minutes, int seconds, BigDecimal fractionalSecond, int timezone) {
        return newXMLGregorianCalendar(null, Integer.MIN_VALUE, Integer.MIN_VALUE, hours, minutes, seconds, fractionalSecond, timezone);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int hours, int minutes, int seconds, int milliseconds, int timezone) {
        BigDecimal realMilliseconds = null;
        if (milliseconds != Integer.MIN_VALUE) {
            if (milliseconds < 0 || milliseconds > 1000) {
                throw new IllegalArgumentException("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendarTime(int hours, int minutes, int seconds, int milliseconds, int timezone)with invalid milliseconds: " + milliseconds);
            }
            realMilliseconds = BigDecimal.valueOf((long) milliseconds).movePointLeft(3);
        }
        return newXMLGregorianCalendarTime(hours, minutes, seconds, realMilliseconds, timezone);
    }
}
