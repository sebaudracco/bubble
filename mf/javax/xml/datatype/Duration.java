package mf.javax.xml.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import mf.javax.xml.datatype.DatatypeConstants.Field;
import mf.javax.xml.namespace.QName;

public abstract class Duration {
    private static final boolean DEBUG = true;

    public abstract Duration add(Duration duration);

    public abstract void addTo(Calendar calendar);

    public abstract int compare(Duration duration);

    public abstract Number getField(Field field);

    public abstract int getSign();

    public abstract int hashCode();

    public abstract boolean isSet(Field field);

    public abstract Duration multiply(BigDecimal bigDecimal);

    public abstract Duration negate();

    public abstract Duration normalizeWith(Calendar calendar);

    public QName getXMLSchemaType() {
        boolean yearSet = isSet(DatatypeConstants.YEARS);
        boolean monthSet = isSet(DatatypeConstants.MONTHS);
        boolean daySet = isSet(DatatypeConstants.DAYS);
        boolean hourSet = isSet(DatatypeConstants.HOURS);
        boolean minuteSet = isSet(DatatypeConstants.MINUTES);
        boolean secondSet = isSet(DatatypeConstants.SECONDS);
        if (yearSet && monthSet && daySet && hourSet && minuteSet && secondSet) {
            return DatatypeConstants.DURATION;
        }
        if (!yearSet && !monthSet && daySet && hourSet && minuteSet && secondSet) {
            return DatatypeConstants.DURATION_DAYTIME;
        }
        if (yearSet && monthSet && !daySet && !hourSet && !minuteSet && !secondSet) {
            return DatatypeConstants.DURATION_YEARMONTH;
        }
        throw new IllegalStateException("javax.xml.datatype.Duration#getXMLSchemaType(): this Duration does not match one of the XML Schema date/time datatypes: year set = " + yearSet + " month set = " + monthSet + " day set = " + daySet + " hour set = " + hourSet + " minute set = " + minuteSet + " second set = " + secondSet);
    }

    public int getYears() {
        return getField(DatatypeConstants.YEARS).intValue();
    }

    public int getMonths() {
        return getField(DatatypeConstants.MONTHS).intValue();
    }

    public int getDays() {
        return getField(DatatypeConstants.DAYS).intValue();
    }

    public int getHours() {
        return getField(DatatypeConstants.HOURS).intValue();
    }

    public int getMinutes() {
        return getField(DatatypeConstants.MINUTES).intValue();
    }

    public int getSeconds() {
        return getField(DatatypeConstants.SECONDS).intValue();
    }

    public long getTimeInMillis(Calendar startInstant) {
        Calendar cal = (Calendar) startInstant.clone();
        addTo(cal);
        return getCalendarTimeInMillis(cal) - getCalendarTimeInMillis(startInstant);
    }

    public long getTimeInMillis(Date startInstant) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(startInstant);
        addTo(cal);
        return getCalendarTimeInMillis(cal) - startInstant.getTime();
    }

    public void addTo(Date date) {
        if (date == null) {
            throw new NullPointerException("Cannot call " + getClass().getName() + "#addTo(Date date) with date == null.");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        addTo(cal);
        date.setTime(getCalendarTimeInMillis(cal));
    }

    public Duration subtract(Duration rhs) {
        return add(rhs.negate());
    }

    public Duration multiply(int factor) {
        return multiply(new BigDecimal(String.valueOf(factor)));
    }

    public boolean isLongerThan(Duration duration) {
        return compare(duration) == 1;
    }

    public boolean isShorterThan(Duration duration) {
        return compare(duration) == -1;
    }

    public boolean equals(Object duration) {
        if (duration != null && (duration instanceof Duration) && compare((Duration) duration) == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (getSign() < 0) {
            buf.append('-');
        }
        buf.append('P');
        BigInteger years = (BigInteger) getField(DatatypeConstants.YEARS);
        if (years != null) {
            buf.append(years + "Y");
        }
        BigInteger months = (BigInteger) getField(DatatypeConstants.MONTHS);
        if (months != null) {
            buf.append(months + "M");
        }
        BigInteger days = (BigInteger) getField(DatatypeConstants.DAYS);
        if (days != null) {
            buf.append(days + "D");
        }
        BigInteger hours = (BigInteger) getField(DatatypeConstants.HOURS);
        BigInteger minutes = (BigInteger) getField(DatatypeConstants.MINUTES);
        BigDecimal seconds = (BigDecimal) getField(DatatypeConstants.SECONDS);
        if (!(hours == null && minutes == null && seconds == null)) {
            buf.append('T');
            if (hours != null) {
                buf.append(hours + "H");
            }
            if (minutes != null) {
                buf.append(minutes + "M");
            }
            if (seconds != null) {
                buf.append(toString(seconds) + "S");
            }
        }
        return buf.toString();
    }

    private String toString(BigDecimal bd) {
        String intString = bd.unscaledValue().toString();
        int scale = bd.scale();
        if (scale == 0) {
            return intString;
        }
        int insertionPoint = intString.length() - scale;
        if (insertionPoint == 0) {
            return "0." + intString;
        }
        StringBuffer buf;
        if (insertionPoint > 0) {
            buf = new StringBuffer(intString);
            buf.insert(insertionPoint, '.');
        } else {
            buf = new StringBuffer((3 - insertionPoint) + intString.length());
            buf.append("0.");
            for (int i = 0; i < (-insertionPoint); i++) {
                buf.append('0');
            }
            buf.append(intString);
        }
        return buf.toString();
    }

    private static long getCalendarTimeInMillis(Calendar cal) {
        return cal.getTime().getTime();
    }
}
