package mf.org.apache.xerces.jaxp.datatype;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import mf.javax.xml.datatype.DatatypeConstants;
import mf.javax.xml.datatype.DatatypeConstants.Field;
import mf.javax.xml.datatype.Duration;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.util.DatatypeMessageFormatter;

class DurationImpl extends Duration implements Serializable {
    private static final BigDecimal[] FACTORS;
    private static final Field[] FIELDS = new Field[]{DatatypeConstants.YEARS, DatatypeConstants.MONTHS, DatatypeConstants.DAYS, DatatypeConstants.HOURS, DatatypeConstants.MINUTES, DatatypeConstants.SECONDS};
    private static final XMLGregorianCalendar[] TEST_POINTS = new XMLGregorianCalendar[]{XMLGregorianCalendarImpl.parse("1696-09-01T00:00:00Z"), XMLGregorianCalendarImpl.parse("1697-02-01T00:00:00Z"), XMLGregorianCalendarImpl.parse("1903-03-01T00:00:00Z"), XMLGregorianCalendarImpl.parse("1903-07-01T00:00:00Z")};
    private static final BigDecimal ZERO = BigDecimal.valueOf(0);
    private static final long serialVersionUID = -2650025807136350131L;
    private final BigInteger days;
    private final BigInteger hours;
    private final BigInteger minutes;
    private final BigInteger months;
    private final BigDecimal seconds;
    private final int signum;
    private final BigInteger years;

    static {
        BigDecimal[] bigDecimalArr = new BigDecimal[5];
        bigDecimalArr[0] = BigDecimal.valueOf(12);
        bigDecimalArr[2] = BigDecimal.valueOf(24);
        bigDecimalArr[3] = BigDecimal.valueOf(60);
        bigDecimalArr[4] = BigDecimal.valueOf(60);
        FACTORS = bigDecimalArr;
    }

    public int getSign() {
        return this.signum;
    }

    private int calcSignum(boolean isPositive) {
        if ((this.years == null || this.years.signum() == 0) && ((this.months == null || this.months.signum() == 0) && ((this.days == null || this.days.signum() == 0) && ((this.hours == null || this.hours.signum() == 0) && ((this.minutes == null || this.minutes.signum() == 0) && (this.seconds == null || this.seconds.signum() == 0)))))) {
            return 0;
        }
        if (isPositive) {
            return 1;
        }
        return -1;
    }

    protected DurationImpl(boolean isPositive, BigInteger years, BigInteger months, BigInteger days, BigInteger hours, BigInteger minutes, BigDecimal seconds) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.signum = calcSignum(isPositive);
        if (years == null && months == null && days == null && hours == null && minutes == null && seconds == null) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "AllFieldsNull", null));
        }
        testNonNegative(years, DatatypeConstants.YEARS);
        testNonNegative(months, DatatypeConstants.MONTHS);
        testNonNegative(days, DatatypeConstants.DAYS);
        testNonNegative(hours, DatatypeConstants.HOURS);
        testNonNegative(minutes, DatatypeConstants.MINUTES);
        testNonNegative(seconds, DatatypeConstants.SECONDS);
    }

    private static void testNonNegative(BigInteger n, Field f) {
        if (n != null && n.signum() < 0) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "NegativeField", new Object[]{f.toString()}));
        }
    }

    private static void testNonNegative(BigDecimal n, Field f) {
        if (n != null && n.signum() < 0) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "NegativeField", new Object[]{f.toString()}));
        }
    }

    protected DurationImpl(boolean isPositive, int years, int months, int days, int hours, int minutes, int seconds) {
        this(isPositive, wrap(years), wrap(months), wrap(days), wrap(hours), wrap(minutes), seconds != 0 ? BigDecimal.valueOf((long) seconds) : null);
    }

    private static BigInteger wrap(int i) {
        if (i == Integer.MIN_VALUE) {
            return null;
        }
        return BigInteger.valueOf((long) i);
    }

    protected DurationImpl(long durationInMilliSeconds) {
        boolean is0x8000000000000000L = false;
        long l = durationInMilliSeconds;
        if (l > 0) {
            this.signum = 1;
        } else if (l < 0) {
            this.signum = -1;
            if (l == Long.MIN_VALUE) {
                l++;
                is0x8000000000000000L = true;
            }
            l *= -1;
        } else {
            this.signum = 0;
        }
        this.years = null;
        this.months = null;
        this.seconds = BigDecimal.valueOf((l % 60000) + ((long) (is0x8000000000000000L ? 1 : 0)), 3);
        l /= 60000;
        this.minutes = l == 0 ? null : BigInteger.valueOf(l % 60);
        l /= 60;
        this.hours = l == 0 ? null : BigInteger.valueOf(l % 24);
        l /= 24;
        this.days = l == 0 ? null : BigInteger.valueOf(l);
    }

    protected DurationImpl(String lexicalRepresentation) throws IllegalArgumentException {
        if (lexicalRepresentation == null) {
            throw new NullPointerException();
        }
        boolean positive;
        int i;
        String s = lexicalRepresentation;
        int[] idx = new int[1];
        int length = s.length();
        boolean timeRequired = false;
        idx[0] = 0;
        if (length == idx[0] || s.charAt(idx[0]) != '-') {
            positive = true;
        } else {
            idx[0] = idx[0] + 1;
            positive = false;
        }
        if (length != idx[0]) {
            i = idx[0];
            idx[0] = i + 1;
            if (s.charAt(i) != 'P') {
                throw new IllegalArgumentException(s);
            }
        }
        int dateLen = 0;
        String[] dateParts = new String[3];
        int[] datePartsIndex = new int[3];
        while (length != idx[0] && isDigit(s.charAt(idx[0])) && dateLen < 3) {
            datePartsIndex[dateLen] = idx[0];
            int dateLen2 = dateLen + 1;
            dateParts[dateLen] = parsePiece(s, idx);
            dateLen = dateLen2;
        }
        if (length != idx[0]) {
            i = idx[0];
            idx[0] = i + 1;
            if (s.charAt(i) == 'T') {
                timeRequired = true;
            } else {
                throw new IllegalArgumentException(s);
            }
        }
        int timeLen = 0;
        String[] timeParts = new String[3];
        int[] timePartsIndex = new int[3];
        while (length != idx[0] && isDigitOrPeriod(s.charAt(idx[0])) && timeLen < 3) {
            timePartsIndex[timeLen] = idx[0];
            int timeLen2 = timeLen + 1;
            timeParts[timeLen] = parsePiece(s, idx);
            timeLen = timeLen2;
        }
        if (timeRequired && timeLen == 0) {
            throw new IllegalArgumentException(s);
        } else if (length != idx[0]) {
            throw new IllegalArgumentException(s);
        } else if (dateLen == 0 && timeLen == 0) {
            throw new IllegalArgumentException(s);
        } else {
            organizeParts(s, dateParts, datePartsIndex, dateLen, "YMD");
            organizeParts(s, timeParts, timePartsIndex, timeLen, "HMS");
            this.years = parseBigInteger(s, dateParts[0], datePartsIndex[0]);
            this.months = parseBigInteger(s, dateParts[1], datePartsIndex[1]);
            this.days = parseBigInteger(s, dateParts[2], datePartsIndex[2]);
            this.hours = parseBigInteger(s, timeParts[0], timePartsIndex[0]);
            this.minutes = parseBigInteger(s, timeParts[1], timePartsIndex[1]);
            this.seconds = parseBigDecimal(s, timeParts[2], timePartsIndex[2]);
            this.signum = calcSignum(positive);
        }
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private static boolean isDigitOrPeriod(char ch) {
        return isDigit(ch) || ch == '.';
    }

    private static String parsePiece(String whole, int[] idx) throws IllegalArgumentException {
        int start = idx[0];
        while (idx[0] < whole.length() && isDigitOrPeriod(whole.charAt(idx[0]))) {
            idx[0] = idx[0] + 1;
        }
        if (idx[0] == whole.length()) {
            throw new IllegalArgumentException(whole);
        }
        idx[0] = idx[0] + 1;
        return whole.substring(start, idx[0]);
    }

    private static void organizeParts(String whole, String[] parts, int[] partsIndex, int len, String tokens) throws IllegalArgumentException {
        int idx = tokens.length();
        for (int i = len - 1; i >= 0; i--) {
            if (parts[i] == null) {
                throw new IllegalArgumentException(whole);
            }
            int nidx = tokens.lastIndexOf(parts[i].charAt(parts[i].length() - 1), idx - 1);
            if (nidx == -1) {
                throw new IllegalArgumentException(whole);
            }
            for (int j = nidx + 1; j < idx; j++) {
                parts[j] = null;
            }
            idx = nidx;
            parts[idx] = parts[i];
            partsIndex[idx] = partsIndex[i];
        }
        for (idx--; idx >= 0; idx--) {
            parts[idx] = null;
        }
    }

    private static BigInteger parseBigInteger(String whole, String part, int index) throws IllegalArgumentException {
        if (part == null) {
            return null;
        }
        return new BigInteger(part.substring(0, part.length() - 1));
    }

    private static BigDecimal parseBigDecimal(String whole, String part, int index) throws IllegalArgumentException {
        if (part == null) {
            return null;
        }
        return new BigDecimal(part.substring(0, part.length() - 1));
    }

    public int compare(Duration rhs) {
        BigInteger maxintAsBigInteger = BigInteger.valueOf(2147483647L);
        if (this.years != null && this.years.compareTo(maxintAsBigInteger) == 1) {
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.YEARS.toString()).toString(), this.years.toString()}));
        } else if (this.months != null && this.months.compareTo(maxintAsBigInteger) == 1) {
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.MONTHS.toString()).toString(), this.months.toString()}));
        } else if (this.days != null && this.days.compareTo(maxintAsBigInteger) == 1) {
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.DAYS.toString()).toString(), this.days.toString()}));
        } else if (this.hours != null && this.hours.compareTo(maxintAsBigInteger) == 1) {
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.HOURS.toString()).toString(), this.hours.toString()}));
        } else if (this.minutes != null && this.minutes.compareTo(maxintAsBigInteger) == 1) {
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.MINUTES.toString()).toString(), this.minutes.toString()}));
        } else if (this.seconds == null || this.seconds.toBigInteger().compareTo(maxintAsBigInteger) != 1) {
            BigInteger rhsYears = (BigInteger) rhs.getField(DatatypeConstants.YEARS);
            if (rhsYears == null || rhsYears.compareTo(maxintAsBigInteger) != 1) {
                BigInteger rhsMonths = (BigInteger) rhs.getField(DatatypeConstants.MONTHS);
                if (rhsMonths == null || rhsMonths.compareTo(maxintAsBigInteger) != 1) {
                    BigInteger rhsDays = (BigInteger) rhs.getField(DatatypeConstants.DAYS);
                    if (rhsDays == null || rhsDays.compareTo(maxintAsBigInteger) != 1) {
                        BigInteger rhsHours = (BigInteger) rhs.getField(DatatypeConstants.HOURS);
                        if (rhsHours == null || rhsHours.compareTo(maxintAsBigInteger) != 1) {
                            BigInteger rhsMinutes = (BigInteger) rhs.getField(DatatypeConstants.MINUTES);
                            if (rhsMinutes == null || rhsMinutes.compareTo(maxintAsBigInteger) != 1) {
                                BigDecimal rhsSecondsAsBigDecimal = (BigDecimal) rhs.getField(DatatypeConstants.SECONDS);
                                BigInteger rhsSeconds = null;
                                if (rhsSecondsAsBigDecimal != null) {
                                    rhsSeconds = rhsSecondsAsBigDecimal.toBigInteger();
                                }
                                if (rhsSeconds == null || rhsSeconds.compareTo(maxintAsBigInteger) != 1) {
                                    GregorianCalendar lhsCalendar = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
                                    lhsCalendar.add(1, getYears() * getSign());
                                    lhsCalendar.add(2, getMonths() * getSign());
                                    lhsCalendar.add(6, getDays() * getSign());
                                    lhsCalendar.add(11, getHours() * getSign());
                                    lhsCalendar.add(12, getMinutes() * getSign());
                                    lhsCalendar.add(13, getSeconds() * getSign());
                                    GregorianCalendar rhsCalendar = new GregorianCalendar(1970, 1, 1, 0, 0, 0);
                                    rhsCalendar.add(1, rhs.getYears() * rhs.getSign());
                                    rhsCalendar.add(2, rhs.getMonths() * rhs.getSign());
                                    rhsCalendar.add(6, rhs.getDays() * rhs.getSign());
                                    rhsCalendar.add(11, rhs.getHours() * rhs.getSign());
                                    rhsCalendar.add(12, rhs.getMinutes() * rhs.getSign());
                                    rhsCalendar.add(13, rhs.getSeconds() * rhs.getSign());
                                    if (lhsCalendar.equals(rhsCalendar)) {
                                        return 0;
                                    }
                                    return compareDates(this, rhs);
                                }
                                throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.SECONDS.toString()).toString(), rhsSeconds.toString()}));
                            }
                            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.MINUTES.toString()).toString(), rhsMinutes.toString()}));
                        }
                        throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.HOURS.toString()).toString(), rhsHours.toString()}));
                    }
                    throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.DAYS.toString()).toString(), rhsDays.toString()}));
                }
                throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.MONTHS.toString()).toString(), rhsMonths.toString()}));
            }
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", new Object[]{new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.YEARS.toString()).toString(), rhsYears.toString()}));
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = new StringBuilder(String.valueOf(getClass().getName())).append("#compare(Duration duration)").append(DatatypeConstants.SECONDS.toString()).toString();
            objArr[1] = toString(this.seconds);
            throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "TooLarge", objArr));
        }
    }

    private int compareDates(Duration duration1, Duration duration2) {
        XMLGregorianCalendar tempA = (XMLGregorianCalendar) TEST_POINTS[0].clone();
        XMLGregorianCalendar tempB = (XMLGregorianCalendar) TEST_POINTS[0].clone();
        tempA.add(duration1);
        tempB.add(duration2);
        int resultA = tempA.compare(tempB);
        if (resultA == 2) {
            return 2;
        }
        tempA = (XMLGregorianCalendar) TEST_POINTS[1].clone();
        tempB = (XMLGregorianCalendar) TEST_POINTS[1].clone();
        tempA.add(duration1);
        tempB.add(duration2);
        resultA = compareResults(resultA, tempA.compare(tempB));
        if (resultA == 2) {
            return 2;
        }
        tempA = (XMLGregorianCalendar) TEST_POINTS[2].clone();
        tempB = (XMLGregorianCalendar) TEST_POINTS[2].clone();
        tempA.add(duration1);
        tempB.add(duration2);
        resultA = compareResults(resultA, tempA.compare(tempB));
        if (resultA == 2) {
            return 2;
        }
        tempA = (XMLGregorianCalendar) TEST_POINTS[3].clone();
        tempB = (XMLGregorianCalendar) TEST_POINTS[3].clone();
        tempA.add(duration1);
        tempB.add(duration2);
        return compareResults(resultA, tempA.compare(tempB));
    }

    private int compareResults(int resultA, int resultB) {
        if (resultB == 2) {
            return 2;
        }
        if (resultA != resultB) {
            return 2;
        }
        return resultA;
    }

    public int hashCode() {
        Calendar cal = TEST_POINTS[0].toGregorianCalendar();
        addTo(cal);
        return (int) getCalendarTimeInMillis(cal);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (this.signum < 0) {
            buf.append('-');
        }
        buf.append('P');
        if (this.years != null) {
            buf.append(this.years).append('Y');
        }
        if (this.months != null) {
            buf.append(this.months).append('M');
        }
        if (this.days != null) {
            buf.append(this.days).append('D');
        }
        if (!(this.hours == null && this.minutes == null && this.seconds == null)) {
            buf.append('T');
            if (this.hours != null) {
                buf.append(this.hours).append('H');
            }
            if (this.minutes != null) {
                buf.append(this.minutes).append('M');
            }
            if (this.seconds != null) {
                buf.append(toString(this.seconds)).append('S');
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

    public boolean isSet(Field field) {
        if (field == null) {
            throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "FieldCannotBeNull", new Object[]{"javax.xml.datatype.Duration#isSet(DatatypeConstants.Field field)"}));
        } else if (field == DatatypeConstants.YEARS) {
            if (this.years != null) {
                return true;
            }
            return false;
        } else if (field == DatatypeConstants.MONTHS) {
            if (this.months == null) {
                return false;
            }
            return true;
        } else if (field == DatatypeConstants.DAYS) {
            if (this.days == null) {
                return false;
            }
            return true;
        } else if (field == DatatypeConstants.HOURS) {
            if (this.hours == null) {
                return false;
            }
            return true;
        } else if (field == DatatypeConstants.MINUTES) {
            if (this.minutes == null) {
                return false;
            }
            return true;
        } else if (field != DatatypeConstants.SECONDS) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "UnknownField", new Object[]{"javax.xml.datatype.Duration#isSet(DatatypeConstants.Field field)", field.toString()}));
        } else if (this.seconds == null) {
            return false;
        } else {
            return true;
        }
    }

    public Number getField(Field field) {
        if (field == null) {
            throw new NullPointerException(DatatypeMessageFormatter.formatMessage(null, "FieldCannotBeNull", new Object[]{"javax.xml.datatype.Duration#isSet(DatatypeConstants.Field field) "}));
        } else if (field == DatatypeConstants.YEARS) {
            return this.years;
        } else {
            if (field == DatatypeConstants.MONTHS) {
                return this.months;
            }
            if (field == DatatypeConstants.DAYS) {
                return this.days;
            }
            if (field == DatatypeConstants.HOURS) {
                return this.hours;
            }
            if (field == DatatypeConstants.MINUTES) {
                return this.minutes;
            }
            if (field == DatatypeConstants.SECONDS) {
                return this.seconds;
            }
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "UnknownField", new Object[]{"javax.xml.datatype.Duration#(getSet(DatatypeConstants.Field field)", field.toString()}));
        }
    }

    public int getYears() {
        return getInt(DatatypeConstants.YEARS);
    }

    public int getMonths() {
        return getInt(DatatypeConstants.MONTHS);
    }

    public int getDays() {
        return getInt(DatatypeConstants.DAYS);
    }

    public int getHours() {
        return getInt(DatatypeConstants.HOURS);
    }

    public int getMinutes() {
        return getInt(DatatypeConstants.MINUTES);
    }

    public int getSeconds() {
        return getInt(DatatypeConstants.SECONDS);
    }

    private int getInt(Field field) {
        Number n = getField(field);
        if (n == null) {
            return 0;
        }
        return n.intValue();
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

    public Duration normalizeWith(Calendar startTimeInstant) {
        boolean z = true;
        Calendar c = (Calendar) startTimeInstant.clone();
        c.add(1, getYears() * this.signum);
        c.add(2, getMonths() * this.signum);
        c.add(5, getDays() * this.signum);
        int days = (int) ((getCalendarTimeInMillis(c) - getCalendarTimeInMillis(startTimeInstant)) / 86400000);
        if (days < 0) {
            z = false;
        }
        return new DurationImpl(z, null, null, wrap(Math.abs(days)), (BigInteger) getField(DatatypeConstants.HOURS), (BigInteger) getField(DatatypeConstants.MINUTES), (BigDecimal) getField(DatatypeConstants.SECONDS));
    }

    public Duration multiply(int factor) {
        return multiply(BigDecimal.valueOf((long) factor));
    }

    public Duration multiply(BigDecimal factor) {
        BigDecimal bigDecimal;
        BigDecimal carry = ZERO;
        int factorSign = factor.signum();
        factor = factor.abs();
        BigDecimal[] buf = new BigDecimal[6];
        for (int i = 0; i < 5; i++) {
            BigDecimal bd = getFieldAsBigDecimal(FIELDS[i]).multiply(factor).add(carry);
            buf[i] = bd.setScale(0, 1);
            bd = bd.subtract(buf[i]);
            if (i != 1) {
                carry = bd.multiply(FACTORS[i]);
            } else if (bd.signum() != 0) {
                throw new IllegalStateException();
            } else {
                carry = ZERO;
            }
        }
        if (this.seconds != null) {
            buf[5] = this.seconds.multiply(factor).add(carry);
        } else {
            buf[5] = carry;
        }
        boolean z = this.signum * factorSign >= 0;
        BigInteger toBigInteger = toBigInteger(buf[0], this.years == null);
        BigInteger toBigInteger2 = toBigInteger(buf[1], this.months == null);
        BigInteger toBigInteger3 = toBigInteger(buf[2], this.days == null);
        BigInteger toBigInteger4 = toBigInteger(buf[3], this.hours == null);
        BigInteger toBigInteger5 = toBigInteger(buf[4], this.minutes == null);
        if (buf[5].signum() == 0 && this.seconds == null) {
            bigDecimal = null;
        } else {
            bigDecimal = buf[5];
        }
        return new DurationImpl(z, toBigInteger, toBigInteger2, toBigInteger3, toBigInteger4, toBigInteger5, bigDecimal);
    }

    private BigDecimal getFieldAsBigDecimal(Field f) {
        if (f != DatatypeConstants.SECONDS) {
            BigInteger bi = (BigInteger) getField(f);
            if (bi == null) {
                return ZERO;
            }
            return new BigDecimal(bi);
        } else if (this.seconds != null) {
            return this.seconds;
        } else {
            return ZERO;
        }
    }

    private static BigInteger toBigInteger(BigDecimal value, boolean canBeNull) {
        if (canBeNull && value.signum() == 0) {
            return null;
        }
        return value.unscaledValue();
    }

    public Duration add(Duration rhs) {
        BigDecimal[] buf = new BigDecimal[]{sanitize((BigInteger) getField(DatatypeConstants.YEARS), getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.YEARS), rhs.getSign())), sanitize((BigInteger) getField(DatatypeConstants.MONTHS), getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.MONTHS), rhs.getSign())), sanitize((BigInteger) getField(DatatypeConstants.DAYS), getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.DAYS), rhs.getSign())), sanitize((BigInteger) getField(DatatypeConstants.HOURS), getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.HOURS), rhs.getSign())), sanitize((BigInteger) getField(DatatypeConstants.MINUTES), getSign()).add(sanitize((BigInteger) rhs.getField(DatatypeConstants.MINUTES), rhs.getSign())), sanitize((BigDecimal) getField(DatatypeConstants.SECONDS), getSign()).add(sanitize((BigDecimal) rhs.getField(DatatypeConstants.SECONDS), rhs.getSign()))};
        alignSigns(buf, 0, 2);
        alignSigns(buf, 2, 6);
        int s = 0;
        for (int i = 0; i < 6; i++) {
            if (buf[i].signum() * s < 0) {
                throw new IllegalStateException();
            }
            if (s == 0) {
                s = buf[i].signum();
            }
        }
        boolean z = s >= 0;
        BigDecimal sanitize = sanitize(buf[0], s);
        boolean z2 = getField(DatatypeConstants.YEARS) == null && rhs.getField(DatatypeConstants.YEARS) == null;
        BigInteger toBigInteger = toBigInteger(sanitize, z2);
        BigDecimal sanitize2 = sanitize(buf[1], s);
        boolean z3 = getField(DatatypeConstants.MONTHS) == null && rhs.getField(DatatypeConstants.MONTHS) == null;
        BigInteger toBigInteger2 = toBigInteger(sanitize2, z3);
        BigDecimal sanitize3 = sanitize(buf[2], s);
        boolean z4 = getField(DatatypeConstants.DAYS) == null && rhs.getField(DatatypeConstants.DAYS) == null;
        BigInteger toBigInteger3 = toBigInteger(sanitize3, z4);
        BigDecimal sanitize4 = sanitize(buf[3], s);
        boolean z5 = getField(DatatypeConstants.HOURS) == null && rhs.getField(DatatypeConstants.HOURS) == null;
        BigInteger toBigInteger4 = toBigInteger(sanitize4, z5);
        BigDecimal sanitize5 = sanitize(buf[4], s);
        boolean z6 = getField(DatatypeConstants.MINUTES) == null && rhs.getField(DatatypeConstants.MINUTES) == null;
        BigInteger toBigInteger5 = toBigInteger(sanitize5, z6);
        if (buf[5].signum() == 0 && getField(DatatypeConstants.SECONDS) == null && rhs.getField(DatatypeConstants.SECONDS) == null) {
            sanitize5 = null;
        } else {
            sanitize5 = sanitize(buf[5], s);
        }
        return new DurationImpl(z, toBigInteger, toBigInteger2, toBigInteger3, toBigInteger4, toBigInteger5, sanitize5);
    }

    private static void alignSigns(BigDecimal[] buf, int start, int end) {
        boolean touched;
        do {
            touched = false;
            int s = 0;
            for (int i = start; i < end; i++) {
                if (buf[i].signum() * s < 0) {
                    touched = true;
                    BigDecimal borrow = buf[i].abs().divide(FACTORS[i - 1], 0);
                    if (buf[i].signum() > 0) {
                        borrow = borrow.negate();
                    }
                    buf[i - 1] = buf[i - 1].subtract(borrow);
                    buf[i] = buf[i].add(borrow.multiply(FACTORS[i - 1]));
                }
                if (buf[i].signum() != 0) {
                    s = buf[i].signum();
                }
            }
        } while (touched);
    }

    private static BigDecimal sanitize(BigInteger value, int signum) {
        if (signum == 0 || value == null) {
            return ZERO;
        }
        if (signum > 0) {
            return new BigDecimal(value);
        }
        return new BigDecimal(value.negate());
    }

    static BigDecimal sanitize(BigDecimal value, int signum) {
        if (signum == 0 || value == null) {
            return ZERO;
        }
        return signum <= 0 ? value.negate() : value;
    }

    public Duration subtract(Duration rhs) {
        return add(rhs.negate());
    }

    public Duration negate() {
        return new DurationImpl(this.signum <= 0, this.years, this.months, this.days, this.hours, this.minutes, this.seconds);
    }

    public int signum() {
        return this.signum;
    }

    public void addTo(Calendar calendar) {
        calendar.add(1, getYears() * this.signum);
        calendar.add(2, getMonths() * this.signum);
        calendar.add(5, getDays() * this.signum);
        calendar.add(10, getHours() * this.signum);
        calendar.add(12, getMinutes() * this.signum);
        calendar.add(13, getSeconds() * this.signum);
        if (this.seconds != null) {
            calendar.add(14, this.signum * this.seconds.subtract(this.seconds.setScale(0, 1)).movePointRight(3).intValue());
        }
    }

    public void addTo(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        addTo(cal);
        date.setTime(getCalendarTimeInMillis(cal));
    }

    private static long getCalendarTimeInMillis(Calendar cal) {
        return cal.getTime().getTime();
    }

    private Object writeReplace() throws IOException {
        return new SerializedDuration(toString());
    }
}
