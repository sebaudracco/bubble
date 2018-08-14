package mf.org.apache.xerces.jaxp.datatype;

import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import mf.javax.xml.datatype.DatatypeConstants;
import mf.javax.xml.datatype.Duration;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.javax.xml.namespace.QName;
import mf.org.apache.xerces.util.DatatypeMessageFormatter;

class XMLGregorianCalendarImpl extends XMLGregorianCalendar implements Serializable, Cloneable {
    private static final BigInteger BILLION_B = BigInteger.valueOf(1000000000);
    private static final int BILLION_I = 1000000000;
    private static final int DAY = 2;
    private static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1);
    private static final BigDecimal DECIMAL_SIXTY = BigDecimal.valueOf(60);
    private static final BigDecimal DECIMAL_ZERO = BigDecimal.valueOf(0);
    private static final String[] FIELD_NAME = new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second", "Millisecond", "Timezone"};
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger FOUR_HUNDRED = BigInteger.valueOf(400);
    private static final int HOUR = 3;
    private static final BigInteger HUNDRED = BigInteger.valueOf(100);
    public static final XMLGregorianCalendar LEAP_YEAR_DEFAULT = createDateTime((int) HttpStatus.SC_BAD_REQUEST, 1, 1, 0, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final int[] MAX_FIELD_VALUE = new int[]{Integer.MAX_VALUE, 12, 31, 24, 59, 60, 999, DatatypeConstants.MIN_TIMEZONE_OFFSET};
    private static final int MILLISECOND = 6;
    private static final int MINUTE = 4;
    private static final int[] MIN_FIELD_VALUE;
    private static final int MONTH = 1;
    private static final Date PURE_GREGORIAN_CHANGE = new Date(Long.MIN_VALUE);
    private static final int SECOND = 5;
    private static final BigInteger SIXTY = BigInteger.valueOf(60);
    private static final int TIMEZONE = 7;
    private static final BigInteger TWELVE = BigInteger.valueOf(12);
    private static final BigInteger TWENTY_FOUR = BigInteger.valueOf(24);
    private static final int YEAR = 0;
    private static final long serialVersionUID = 3905403108073447394L;
    private int day = Integer.MIN_VALUE;
    private BigInteger eon = null;
    private BigDecimal fractionalSecond = null;
    private int hour = Integer.MIN_VALUE;
    private int minute = Integer.MIN_VALUE;
    private int month = Integer.MIN_VALUE;
    private int orig_day = Integer.MIN_VALUE;
    private BigInteger orig_eon;
    private BigDecimal orig_fracSeconds;
    private int orig_hour = Integer.MIN_VALUE;
    private int orig_minute = Integer.MIN_VALUE;
    private int orig_month = Integer.MIN_VALUE;
    private int orig_second = Integer.MIN_VALUE;
    private int orig_timezone = Integer.MIN_VALUE;
    private int orig_year = Integer.MIN_VALUE;
    private int second = Integer.MIN_VALUE;
    private int timezone = Integer.MIN_VALUE;
    private int year = Integer.MIN_VALUE;

    private static class DaysInMonth {
        private static final int[] table;

        private DaysInMonth() {
        }

        static {
            int[] iArr = new int[13];
            iArr[1] = 31;
            iArr[2] = 28;
            iArr[3] = 31;
            iArr[4] = 30;
            iArr[5] = 31;
            iArr[6] = 30;
            iArr[7] = 31;
            iArr[8] = 31;
            iArr[9] = 30;
            iArr[10] = 31;
            iArr[11] = 30;
            iArr[12] = 31;
            table = iArr;
        }
    }

    private final class Parser {
        private int fidx;
        private final int flen;
        private final String format;
        private final String value;
        private int vidx;
        private final int vlen;

        private Parser(String format, String value) {
            this.format = format;
            this.value = value;
            this.flen = format.length();
            this.vlen = value.length();
        }

        public void parse() throws IllegalArgumentException {
            while (this.fidx < this.flen) {
                String str = this.format;
                int i = this.fidx;
                this.fidx = i + 1;
                char fch = str.charAt(i);
                if (fch != '%') {
                    skip(fch);
                } else {
                    str = this.format;
                    i = this.fidx;
                    this.fidx = i + 1;
                    switch (str.charAt(i)) {
                        case 'D':
                            XMLGregorianCalendarImpl.this.setDay(parseInt(2, 2));
                            break;
                        case 'M':
                            XMLGregorianCalendarImpl.this.setMonth(parseInt(2, 2));
                            break;
                        case 'Y':
                            parseYear();
                            break;
                        case 'h':
                            XMLGregorianCalendarImpl.this.setHour(parseInt(2, 2));
                            break;
                        case 'm':
                            XMLGregorianCalendarImpl.this.setMinute(parseInt(2, 2));
                            break;
                        case 's':
                            XMLGregorianCalendarImpl.this.setSecond(parseInt(2, 2));
                            if (peek() != '.') {
                                break;
                            }
                            XMLGregorianCalendarImpl.this.setFractionalSecond(parseBigDecimal());
                            break;
                        case 'z':
                            char vch = peek();
                            if (vch != 'Z') {
                                if (vch != '+' && vch != '-') {
                                    break;
                                }
                                this.vidx++;
                                int h = parseInt(2, 2);
                                skip(':');
                                XMLGregorianCalendarImpl.this.setTimezone((vch == '+' ? 1 : -1) * ((h * 60) + parseInt(2, 2)));
                                break;
                            }
                            this.vidx++;
                            XMLGregorianCalendarImpl.this.setTimezone(0);
                            break;
                        default:
                            throw new InternalError();
                    }
                }
            }
            if (this.vidx != this.vlen) {
                throw new IllegalArgumentException(this.value);
            }
        }

        private char peek() throws IllegalArgumentException {
            if (this.vidx == this.vlen) {
                return '￿';
            }
            return this.value.charAt(this.vidx);
        }

        private char read() throws IllegalArgumentException {
            if (this.vidx == this.vlen) {
                throw new IllegalArgumentException(this.value);
            }
            String str = this.value;
            int i = this.vidx;
            this.vidx = i + 1;
            return str.charAt(i);
        }

        private void skip(char ch) throws IllegalArgumentException {
            if (read() != ch) {
                throw new IllegalArgumentException(this.value);
            }
        }

        private void parseYear() throws IllegalArgumentException {
            int vstart = this.vidx;
            int sign = 0;
            if (peek() == '-') {
                this.vidx++;
                sign = 1;
            }
            while (XMLGregorianCalendarImpl.isDigit(peek())) {
                this.vidx++;
            }
            int digits = (this.vidx - vstart) - sign;
            if (digits < 4) {
                throw new IllegalArgumentException(this.value);
            }
            String yearString = this.value.substring(vstart, this.vidx);
            if (digits < 10) {
                XMLGregorianCalendarImpl.this.setYear(Integer.parseInt(yearString));
            } else {
                XMLGregorianCalendarImpl.this.setYear(new BigInteger(yearString));
            }
        }

        private int parseInt(int minDigits, int maxDigits) throws IllegalArgumentException {
            int vstart = this.vidx;
            while (XMLGregorianCalendarImpl.isDigit(peek()) && this.vidx - vstart < maxDigits) {
                this.vidx++;
            }
            if (this.vidx - vstart >= minDigits) {
                return Integer.parseInt(this.value.substring(vstart, this.vidx));
            }
            throw new IllegalArgumentException(this.value);
        }

        private BigDecimal parseBigDecimal() throws IllegalArgumentException {
            int vstart = this.vidx;
            if (peek() == '.') {
                this.vidx++;
                while (XMLGregorianCalendarImpl.isDigit(peek())) {
                    this.vidx++;
                }
                return new BigDecimal(this.value.substring(vstart, this.vidx));
            }
            throw new IllegalArgumentException(this.value);
        }
    }

    static {
        r0 = new int[8];
        MIN_FIELD_VALUE = r0;
    }

    protected XMLGregorianCalendarImpl(String lexicalRepresentation) throws IllegalArgumentException {
        String format;
        String lexRep = lexicalRepresentation;
        int lexRepLength = lexRep.length();
        if (lexRep.indexOf(84) != -1) {
            format = "%Y-%M-%DT%h:%m:%s%z";
        } else if (lexRepLength >= 3 && lexRep.charAt(2) == ':') {
            format = "%h:%m:%s%z";
        } else if (!lexRep.startsWith("--")) {
            int countSeparator = 0;
            if (lexRep.indexOf(58) != -1) {
                lexRepLength -= 6;
            }
            for (int i = 1; i < lexRepLength; i++) {
                if (lexRep.charAt(i) == '-') {
                    countSeparator++;
                }
            }
            if (countSeparator == 0) {
                format = "%Y%z";
            } else if (countSeparator == 1) {
                format = "%Y-%M%z";
            } else {
                format = "%Y-%M-%D%z";
            }
        } else if (lexRepLength >= 3 && lexRep.charAt(2) == '-') {
            format = "---%D%z";
        } else if (lexRepLength == 4 || (lexRepLength >= 6 && (lexRep.charAt(4) == '+' || (lexRep.charAt(4) == '-' && (lexRep.charAt(5) == '-' || lexRepLength == 10))))) {
            try {
                new Parser("--%M--%z", lexRep).parse();
                if (isValid()) {
                    save();
                    return;
                } else {
                    throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", new Object[]{lexicalRepresentation}));
                }
            } catch (IllegalArgumentException e) {
                format = "--%M%z";
            }
        } else {
            format = "--%M-%D%z";
        }
        new Parser(format, lexRep).parse();
        if (isValid()) {
            save();
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", new Object[]{lexicalRepresentation}));
        }
    }

    private void save() {
        this.orig_eon = this.eon;
        this.orig_year = this.year;
        this.orig_month = this.month;
        this.orig_day = this.day;
        this.orig_hour = this.hour;
        this.orig_minute = this.minute;
        this.orig_second = this.second;
        this.orig_fracSeconds = this.fractionalSecond;
        this.orig_timezone = this.timezone;
    }

    protected XMLGregorianCalendarImpl(BigInteger year, int month, int day, int hour, int minute, int second, BigDecimal fractionalSecond, int timezone) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setTime(hour, minute, second, fractionalSecond);
        setTimezone(timezone);
        if (isValid()) {
            save();
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-fractional", new Object[]{year, new Integer(month), new Integer(day), new Integer(hour), new Integer(minute), new Integer(second), fractionalSecond, new Integer(timezone)}));
        }
    }

    private XMLGregorianCalendarImpl(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone) {
        setYear(year);
        setMonth(month);
        setDay(day);
        setTime(hour, minute, second);
        setTimezone(timezone);
        BigDecimal realMilliseconds = null;
        if (millisecond != Integer.MIN_VALUE) {
            realMilliseconds = BigDecimal.valueOf((long) millisecond, 3);
        }
        setFractionalSecond(realMilliseconds);
        if (isValid()) {
            save();
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-milli", new Object[]{new Integer(year), new Integer(month), new Integer(day), new Integer(hour), new Integer(minute), new Integer(second), new Integer(millisecond), new Integer(timezone)}));
        }
    }

    public XMLGregorianCalendarImpl(GregorianCalendar cal) {
        int year = cal.get(1);
        if (cal.get(0) == 0) {
            year = -year;
        }
        setYear(year);
        setMonth(cal.get(2) + 1);
        setDay(cal.get(5));
        setTime(cal.get(11), cal.get(12), cal.get(13), cal.get(14));
        setTimezone((cal.get(15) + cal.get(16)) / 60000);
        save();
    }

    public static XMLGregorianCalendar createDateTime(BigInteger year, int month, int day, int hours, int minutes, int seconds, BigDecimal fractionalSecond, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, hours, minutes, seconds, fractionalSecond, timezone);
    }

    public static XMLGregorianCalendar createDateTime(int year, int month, int day, int hour, int minute, int second) {
        return new XMLGregorianCalendarImpl(year, month, day, hour, minute, second, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static XMLGregorianCalendar createDateTime(int year, int month, int day, int hours, int minutes, int seconds, int milliseconds, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, hours, minutes, seconds, milliseconds, timezone);
    }

    public static XMLGregorianCalendar createDate(int year, int month, int day, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, int timezone) {
        return new XMLGregorianCalendarImpl(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, hours, minutes, seconds, Integer.MIN_VALUE, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, BigDecimal fractionalSecond, int timezone) {
        return new XMLGregorianCalendarImpl(null, Integer.MIN_VALUE, Integer.MIN_VALUE, hours, minutes, seconds, fractionalSecond, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, int milliseconds, int timezone) {
        return new XMLGregorianCalendarImpl(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, hours, minutes, seconds, milliseconds, timezone);
    }

    public BigInteger getEon() {
        return this.eon;
    }

    public int getYear() {
        return this.year;
    }

    public BigInteger getEonAndYear() {
        if (this.year != Integer.MIN_VALUE && this.eon != null) {
            return this.eon.add(BigInteger.valueOf((long) this.year));
        }
        if (this.year == Integer.MIN_VALUE || this.eon != null) {
            return null;
        }
        return BigInteger.valueOf((long) this.year);
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getTimezone() {
        return this.timezone;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    private BigDecimal getSeconds() {
        if (this.second == Integer.MIN_VALUE) {
            return DECIMAL_ZERO;
        }
        BigDecimal result = BigDecimal.valueOf((long) this.second);
        if (this.fractionalSecond != null) {
            return result.add(this.fractionalSecond);
        }
        return result;
    }

    public int getMillisecond() {
        if (this.fractionalSecond == null) {
            return Integer.MIN_VALUE;
        }
        return this.fractionalSecond.movePointRight(3).intValue();
    }

    public BigDecimal getFractionalSecond() {
        return this.fractionalSecond;
    }

    public void setYear(BigInteger year) {
        if (year == null) {
            this.eon = null;
            this.year = Integer.MIN_VALUE;
            return;
        }
        BigInteger temp = year.remainder(BILLION_B);
        this.year = temp.intValue();
        setEon(year.subtract(temp));
    }

    public void setYear(int year) {
        if (year == Integer.MIN_VALUE) {
            this.year = Integer.MIN_VALUE;
            this.eon = null;
        } else if (Math.abs(year) < BILLION_I) {
            this.year = year;
            this.eon = null;
        } else {
            BigInteger theYear = BigInteger.valueOf((long) year);
            BigInteger remainder = theYear.remainder(BILLION_B);
            this.year = remainder.intValue();
            setEon(theYear.subtract(remainder));
        }
    }

    private void setEon(BigInteger eon) {
        if (eon == null || eon.compareTo(BigInteger.ZERO) != 0) {
            this.eon = eon;
        } else {
            this.eon = null;
        }
    }

    public void setMonth(int month) {
        checkFieldValueConstraint(1, month);
        this.month = month;
    }

    public void setDay(int day) {
        checkFieldValueConstraint(2, day);
        this.day = day;
    }

    public void setTimezone(int offset) {
        checkFieldValueConstraint(7, offset);
        this.timezone = offset;
    }

    public void setTime(int hour, int minute, int second) {
        setTime(hour, minute, second, null);
    }

    private void checkFieldValueConstraint(int field, int value) throws IllegalArgumentException {
        if ((value < MIN_FIELD_VALUE[field] && value != Integer.MIN_VALUE) || value > MAX_FIELD_VALUE[field]) {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFieldValue", new Object[]{new Integer(value), FIELD_NAME[field]}));
        }
    }

    public void setHour(int hour) {
        checkFieldValueConstraint(3, hour);
        this.hour = hour;
    }

    public void setMinute(int minute) {
        checkFieldValueConstraint(4, minute);
        this.minute = minute;
    }

    public void setSecond(int second) {
        checkFieldValueConstraint(5, second);
        this.second = second;
    }

    public void setTime(int hour, int minute, int second, BigDecimal fractional) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setFractionalSecond(fractional);
    }

    public void setTime(int hour, int minute, int second, int millisecond) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setMillisecond(millisecond);
    }

    public int compare(XMLGregorianCalendar rhs) {
        XMLGregorianCalendar Q = rhs;
        XMLGregorianCalendar P;
        if (getTimezone() == Q.getTimezone()) {
            return internalCompare(P, Q);
        }
        if (getTimezone() != Integer.MIN_VALUE && Q.getTimezone() != Integer.MIN_VALUE) {
            return internalCompare((XMLGregorianCalendarImpl) normalize(), (XMLGregorianCalendarImpl) Q.normalize());
        }
        int result;
        if (getTimezone() != Integer.MIN_VALUE) {
            if (getTimezone() != 0) {
                P = (XMLGregorianCalendarImpl) normalize();
            }
            result = internalCompare(P, normalizeToTimezone(Q, DatatypeConstants.MIN_TIMEZONE_OFFSET));
            if (result == -1) {
                return result;
            }
            result = internalCompare(P, normalizeToTimezone(Q, DatatypeConstants.MAX_TIMEZONE_OFFSET));
            if (result == 1) {
                return result;
            }
            return 2;
        }
        if (Q.getTimezone() != 0) {
            Q = (XMLGregorianCalendarImpl) normalizeToTimezone(Q, Q.getTimezone());
        }
        result = internalCompare(normalizeToTimezone(P, DatatypeConstants.MAX_TIMEZONE_OFFSET), Q);
        if (result == -1) {
            return result;
        }
        result = internalCompare(normalizeToTimezone(P, DatatypeConstants.MIN_TIMEZONE_OFFSET), Q);
        if (result == 1) {
            return result;
        }
        return 2;
    }

    public XMLGregorianCalendar normalize() {
        XMLGregorianCalendar normalized = normalizeToTimezone(this, this.timezone);
        if (getTimezone() == Integer.MIN_VALUE) {
            normalized.setTimezone(Integer.MIN_VALUE);
        }
        if (getMillisecond() == Integer.MIN_VALUE) {
            normalized.setMillisecond(Integer.MIN_VALUE);
        }
        return normalized;
    }

    private XMLGregorianCalendar normalizeToTimezone(XMLGregorianCalendar cal, int timezone) {
        boolean z;
        XMLGregorianCalendar result = (XMLGregorianCalendar) cal.clone();
        int minutes = -timezone;
        if (minutes >= 0) {
            z = true;
        } else {
            z = false;
        }
        result.add(new DurationImpl(z, 0, 0, 0, 0, minutes < 0 ? -minutes : minutes, 0));
        result.setTimezone(0);
        return result;
    }

    private static int internalCompare(XMLGregorianCalendar P, XMLGregorianCalendar Q) {
        int result;
        if (P.getEon() == Q.getEon()) {
            result = compareField(P.getYear(), Q.getYear());
            if (result != 0) {
                return result;
            }
        }
        result = compareField(P.getEonAndYear(), Q.getEonAndYear());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getMonth(), Q.getMonth());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getDay(), Q.getDay());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getHour(), Q.getHour());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getMinute(), Q.getMinute());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getSecond(), Q.getSecond());
        if (result != 0) {
            return result;
        }
        return compareField(P.getFractionalSecond(), Q.getFractionalSecond());
    }

    private static int compareField(int Pfield, int Qfield) {
        if (Pfield == Qfield) {
            return 0;
        }
        if (Pfield == Integer.MIN_VALUE || Qfield == Integer.MIN_VALUE) {
            return 2;
        }
        return Pfield < Qfield ? -1 : 1;
    }

    private static int compareField(BigInteger Pfield, BigInteger Qfield) {
        if (Pfield == null) {
            if (Qfield == null) {
                return 0;
            }
            return 2;
        } else if (Qfield != null) {
            return Pfield.compareTo(Qfield);
        } else {
            return 2;
        }
    }

    private static int compareField(BigDecimal Pfield, BigDecimal Qfield) {
        if (Pfield == Qfield) {
            return 0;
        }
        if (Pfield == null) {
            Pfield = DECIMAL_ZERO;
        }
        if (Qfield == null) {
            Qfield = DECIMAL_ZERO;
        }
        return Pfield.compareTo(Qfield);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XMLGregorianCalendar)) {
            return false;
        }
        if (compare((XMLGregorianCalendar) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int timezone = getTimezone();
        if (timezone == Integer.MIN_VALUE) {
            timezone = 0;
        }
        XMLGregorianCalendar gc = this;
        if (timezone != 0) {
            gc = normalizeToTimezone(this, getTimezone());
        }
        return ((((gc.getYear() + gc.getMonth()) + gc.getDay()) + gc.getHour()) + gc.getMinute()) + gc.getSecond();
    }

    public static XMLGregorianCalendar parse(String lexicalRepresentation) {
        return new XMLGregorianCalendarImpl(lexicalRepresentation);
    }

    public String toXMLFormat() {
        QName typekind = getXMLSchemaType();
        String formatString = null;
        if (typekind == DatatypeConstants.DATETIME) {
            formatString = "%Y-%M-%DT%h:%m:%s%z";
        } else if (typekind == DatatypeConstants.DATE) {
            formatString = "%Y-%M-%D%z";
        } else if (typekind == DatatypeConstants.TIME) {
            formatString = "%h:%m:%s%z";
        } else if (typekind == DatatypeConstants.GMONTH) {
            formatString = "--%M--%z";
        } else if (typekind == DatatypeConstants.GDAY) {
            formatString = "---%D%z";
        } else if (typekind == DatatypeConstants.GYEAR) {
            formatString = "%Y%z";
        } else if (typekind == DatatypeConstants.GYEARMONTH) {
            formatString = "%Y-%M%z";
        } else if (typekind == DatatypeConstants.GMONTHDAY) {
            formatString = "--%M-%D%z";
        }
        return format(formatString);
    }

    public QName getXMLSchemaType() {
        if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour != Integer.MIN_VALUE && this.minute != Integer.MIN_VALUE && this.second != Integer.MIN_VALUE) {
            return DatatypeConstants.DATETIME;
        }
        if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.DATE;
        }
        if (this.year == Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour != Integer.MIN_VALUE && this.minute != Integer.MIN_VALUE && this.second != Integer.MIN_VALUE) {
            return DatatypeConstants.TIME;
        }
        if (this.year != Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.GYEARMONTH;
        }
        if (this.year == Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.GMONTHDAY;
        }
        if (this.year != Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.GYEAR;
        }
        if (this.year == Integer.MIN_VALUE && this.month != Integer.MIN_VALUE && this.day == Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.GMONTH;
        }
        if (this.year == Integer.MIN_VALUE && this.month == Integer.MIN_VALUE && this.day != Integer.MIN_VALUE && this.hour == Integer.MIN_VALUE && this.minute == Integer.MIN_VALUE && this.second == Integer.MIN_VALUE) {
            return DatatypeConstants.GDAY;
        }
        throw new IllegalStateException(new StringBuilder(String.valueOf(getClass().getName())).append("#getXMLSchemaType() :").append(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCFields", null)).toString());
    }

    public boolean isValid() {
        if (!(this.month == Integer.MIN_VALUE || this.day == Integer.MIN_VALUE)) {
            if (this.year != Integer.MIN_VALUE) {
                if (this.eon == null) {
                    if (this.day > maximumDayInMonthFor(this.year, this.month)) {
                        return false;
                    }
                } else if (this.day > maximumDayInMonthFor(getEonAndYear(), this.month)) {
                    return false;
                }
            } else if (this.day > maximumDayInMonthFor(2000, this.month)) {
                return false;
            }
        }
        if (this.hour == 24) {
            if (this.minute != 0 || this.second != 0) {
                return false;
            }
            if (!(this.fractionalSecond == null || this.fractionalSecond.compareTo(DECIMAL_ZERO) == 0)) {
                return false;
            }
        }
        if (this.eon == null && this.year == 0) {
            return false;
        }
        return true;
    }

    public void add(Duration duration) {
        BigDecimal startSeconds;
        BigInteger tempDays;
        boolean[] fieldUndefined = new boolean[6];
        int signum = duration.getSign();
        int startMonth = getMonth();
        if (startMonth == Integer.MIN_VALUE) {
            startMonth = MIN_FIELD_VALUE[1];
            fieldUndefined[1] = true;
        }
        BigInteger temp = BigInteger.valueOf((long) startMonth).add(sanitize(duration.getField(DatatypeConstants.MONTHS), signum));
        setMonth(temp.subtract(BigInteger.ONE).mod(TWELVE).intValue() + 1);
        BigInteger carry = new BigDecimal(temp.subtract(BigInteger.ONE)).divide(new BigDecimal(TWELVE), 3).toBigInteger();
        BigInteger startYear = getEonAndYear();
        if (startYear == null) {
            fieldUndefined[0] = true;
            startYear = BigInteger.ZERO;
        }
        setYear(startYear.add(sanitize(duration.getField(DatatypeConstants.YEARS), signum)).add(carry));
        if (getSecond() == Integer.MIN_VALUE) {
            fieldUndefined[5] = true;
            startSeconds = DECIMAL_ZERO;
        } else {
            startSeconds = getSeconds();
        }
        BigDecimal tempBD = startSeconds.add(DurationImpl.sanitize((BigDecimal) duration.getField(DatatypeConstants.SECONDS), signum));
        BigDecimal fQuotient = new BigDecimal(new BigDecimal(tempBD.toBigInteger()).divide(DECIMAL_SIXTY, 3).toBigInteger());
        BigDecimal endSeconds = tempBD.subtract(fQuotient.multiply(DECIMAL_SIXTY));
        carry = fQuotient.toBigInteger();
        setSecond(endSeconds.intValue());
        BigDecimal tempFracSeconds = endSeconds.subtract(new BigDecimal(BigInteger.valueOf((long) getSecond())));
        if (tempFracSeconds.compareTo(DECIMAL_ZERO) < 0) {
            setFractionalSecond(DECIMAL_ONE.add(tempFracSeconds));
            if (getSecond() == 0) {
                setSecond(59);
                carry = carry.subtract(BigInteger.ONE);
            } else {
                setSecond(getSecond() - 1);
            }
        } else {
            setFractionalSecond(tempFracSeconds);
        }
        int startMinutes = getMinute();
        if (startMinutes == Integer.MIN_VALUE) {
            fieldUndefined[4] = true;
            startMinutes = MIN_FIELD_VALUE[4];
        }
        temp = BigInteger.valueOf((long) startMinutes).add(sanitize(duration.getField(DatatypeConstants.MINUTES), signum)).add(carry);
        setMinute(temp.mod(SIXTY).intValue());
        carry = new BigDecimal(temp).divide(DECIMAL_SIXTY, 3).toBigInteger();
        int startHours = getHour();
        if (startHours == Integer.MIN_VALUE) {
            fieldUndefined[3] = true;
            startHours = MIN_FIELD_VALUE[3];
        }
        temp = BigInteger.valueOf((long) startHours).add(sanitize(duration.getField(DatatypeConstants.HOURS), signum)).add(carry);
        setHour(temp.mod(TWENTY_FOUR).intValue());
        carry = new BigDecimal(temp).divide(new BigDecimal(TWENTY_FOUR), 3).toBigInteger();
        int startDay = getDay();
        if (startDay == Integer.MIN_VALUE) {
            fieldUndefined[2] = true;
            startDay = MIN_FIELD_VALUE[2];
        }
        BigInteger dDays = sanitize(duration.getField(DatatypeConstants.DAYS), signum);
        int maxDayInMonth = maximumDayInMonthFor(getEonAndYear(), getMonth());
        if (startDay > maxDayInMonth) {
            tempDays = BigInteger.valueOf((long) maxDayInMonth);
        } else if (startDay < 1) {
            tempDays = BigInteger.ONE;
        } else {
            tempDays = BigInteger.valueOf((long) startDay);
        }
        BigInteger endDays = tempDays.add(dDays).add(carry);
        while (true) {
            int monthCarry;
            int quotient;
            if (endDays.compareTo(BigInteger.ONE) >= 0) {
                if (endDays.compareTo(BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear(), getMonth()))) <= 0) {
                    break;
                }
                endDays = endDays.add(BigInteger.valueOf((long) (-maximumDayInMonthFor(getEonAndYear(), getMonth()))));
                monthCarry = 1;
            } else {
                BigInteger mdimf;
                if (this.month >= 2) {
                    mdimf = BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear(), getMonth() - 1));
                } else {
                    mdimf = BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear().subtract(BigInteger.valueOf(1)), 12));
                }
                endDays = endDays.add(mdimf);
                monthCarry = -1;
            }
            int intTemp = getMonth() + monthCarry;
            int endMonth = (intTemp - 1) % 12;
            if (endMonth < 0) {
                endMonth = (endMonth + 12) + 1;
                quotient = BigDecimal.valueOf((long) (intTemp - 1)).divide(new BigDecimal(TWELVE), 0).intValue();
            } else {
                quotient = (intTemp - 1) / 12;
                endMonth++;
            }
            setMonth(endMonth);
            if (quotient != 0) {
                setYear(getEonAndYear().add(BigInteger.valueOf((long) quotient)));
            }
        }
        setDay(endDays.intValue());
        for (int i = 0; i <= 5; i++) {
            if (fieldUndefined[i]) {
                switch (i) {
                    case 0:
                        setYear(Integer.MIN_VALUE);
                        break;
                    case 1:
                        setMonth(Integer.MIN_VALUE);
                        break;
                    case 2:
                        setDay(Integer.MIN_VALUE);
                        break;
                    case 3:
                        setHour(Integer.MIN_VALUE);
                        break;
                    case 4:
                        setMinute(Integer.MIN_VALUE);
                        break;
                    case 5:
                        setSecond(Integer.MIN_VALUE);
                        setFractionalSecond(null);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int maximumDayInMonthFor(BigInteger year, int month) {
        if (month != 2) {
            return DaysInMonth.table[month];
        }
        if (year.mod(FOUR_HUNDRED).equals(BigInteger.ZERO) || (!year.mod(HUNDRED).equals(BigInteger.ZERO) && year.mod(FOUR).equals(BigInteger.ZERO))) {
            return 29;
        }
        return DaysInMonth.table[month];
    }

    private static int maximumDayInMonthFor(int year, int month) {
        if (month != 2) {
            return DaysInMonth.table[month];
        }
        if (year % HttpStatus.SC_BAD_REQUEST == 0 || (year % 100 != 0 && year % 4 == 0)) {
            return 29;
        }
        return DaysInMonth.table[2];
    }

    public GregorianCalendar toGregorianCalendar() {
        GregorianCalendar result = new GregorianCalendar(getTimeZone(Integer.MIN_VALUE), Locale.getDefault());
        result.clear();
        result.setGregorianChange(PURE_GREGORIAN_CHANGE);
        if (this.year != Integer.MIN_VALUE) {
            if (this.eon == null) {
                result.set(0, this.year < 0 ? 0 : 1);
                result.set(1, Math.abs(this.year));
            } else {
                int i;
                BigInteger eonAndYear = getEonAndYear();
                if (eonAndYear.signum() == -1) {
                    i = 0;
                } else {
                    i = 1;
                }
                result.set(0, i);
                result.set(1, eonAndYear.abs().intValue());
            }
        }
        if (this.month != Integer.MIN_VALUE) {
            result.set(2, this.month - 1);
        }
        if (this.day != Integer.MIN_VALUE) {
            result.set(5, this.day);
        }
        if (this.hour != Integer.MIN_VALUE) {
            result.set(11, this.hour);
        }
        if (this.minute != Integer.MIN_VALUE) {
            result.set(12, this.minute);
        }
        if (this.second != Integer.MIN_VALUE) {
            result.set(13, this.second);
        }
        if (this.fractionalSecond != null) {
            result.set(14, getMillisecond());
        }
        return result;
    }

    public GregorianCalendar toGregorianCalendar(TimeZone timezone, Locale aLocale, XMLGregorianCalendar defaults) {
        TimeZone tz = timezone;
        if (tz == null) {
            int defaultZoneoffset = Integer.MIN_VALUE;
            if (defaults != null) {
                defaultZoneoffset = defaults.getTimezone();
            }
            tz = getTimeZone(defaultZoneoffset);
        }
        if (aLocale == null) {
            aLocale = Locale.getDefault();
        }
        GregorianCalendar result = new GregorianCalendar(tz, aLocale);
        result.clear();
        result.setGregorianChange(PURE_GREGORIAN_CHANGE);
        if (this.year != Integer.MIN_VALUE) {
            if (this.eon == null) {
                result.set(0, this.year < 0 ? 0 : 1);
                result.set(1, Math.abs(this.year));
            } else {
                BigInteger eonAndYear = getEonAndYear();
                result.set(0, eonAndYear.signum() == -1 ? 0 : 1);
                result.set(1, eonAndYear.abs().intValue());
            }
        } else if (defaults != null) {
            int defaultYear = defaults.getYear();
            if (defaultYear != Integer.MIN_VALUE) {
                if (defaults.getEon() == null) {
                    result.set(0, defaultYear < 0 ? 0 : 1);
                    result.set(1, Math.abs(defaultYear));
                } else {
                    BigInteger defaultEonAndYear = defaults.getEonAndYear();
                    result.set(0, defaultEonAndYear.signum() == -1 ? 0 : 1);
                    result.set(1, defaultEonAndYear.abs().intValue());
                }
            }
        }
        if (this.month != Integer.MIN_VALUE) {
            result.set(2, this.month - 1);
        } else {
            int defaultMonth = defaults != null ? defaults.getMonth() : Integer.MIN_VALUE;
            if (defaultMonth != Integer.MIN_VALUE) {
                result.set(2, defaultMonth - 1);
            }
        }
        if (this.day != Integer.MIN_VALUE) {
            result.set(5, this.day);
        } else {
            int defaultDay = defaults != null ? defaults.getDay() : Integer.MIN_VALUE;
            if (defaultDay != Integer.MIN_VALUE) {
                result.set(5, defaultDay);
            }
        }
        if (this.hour != Integer.MIN_VALUE) {
            result.set(11, this.hour);
        } else {
            int defaultHour = defaults != null ? defaults.getHour() : Integer.MIN_VALUE;
            if (defaultHour != Integer.MIN_VALUE) {
                result.set(11, defaultHour);
            }
        }
        if (this.minute != Integer.MIN_VALUE) {
            result.set(12, this.minute);
        } else {
            int defaultMinute = defaults != null ? defaults.getMinute() : Integer.MIN_VALUE;
            if (defaultMinute != Integer.MIN_VALUE) {
                result.set(12, defaultMinute);
            }
        }
        if (this.second != Integer.MIN_VALUE) {
            result.set(13, this.second);
        } else {
            int defaultSecond = defaults != null ? defaults.getSecond() : Integer.MIN_VALUE;
            if (defaultSecond != Integer.MIN_VALUE) {
                result.set(13, defaultSecond);
            }
        }
        if (this.fractionalSecond != null) {
            result.set(14, getMillisecond());
        } else {
            if ((defaults != null ? defaults.getFractionalSecond() : null) != null) {
                result.set(14, defaults.getMillisecond());
            }
        }
        return result;
    }

    public TimeZone getTimeZone(int defaultZoneoffset) {
        int zoneoffset = getTimezone();
        if (zoneoffset == Integer.MIN_VALUE) {
            zoneoffset = defaultZoneoffset;
        }
        if (zoneoffset == Integer.MIN_VALUE) {
            return TimeZone.getDefault();
        }
        char sign = zoneoffset < 0 ? '-' : '+';
        if (sign == '-') {
            zoneoffset = -zoneoffset;
        }
        int hour = zoneoffset / 60;
        int minutes = zoneoffset - (hour * 60);
        StringBuffer customTimezoneId = new StringBuffer(8);
        customTimezoneId.append("GMT");
        customTimezoneId.append(sign);
        customTimezoneId.append(hour);
        if (minutes != 0) {
            if (minutes < 10) {
                customTimezoneId.append('0');
            }
            customTimezoneId.append(minutes);
        }
        return TimeZone.getTimeZone(customTimezoneId.toString());
    }

    public Object clone() {
        return new XMLGregorianCalendarImpl(getEonAndYear(), this.month, this.day, this.hour, this.minute, this.second, this.fractionalSecond, this.timezone);
    }

    public void clear() {
        this.eon = null;
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.fractionalSecond = null;
    }

    public void setMillisecond(int millisecond) {
        if (millisecond == Integer.MIN_VALUE) {
            this.fractionalSecond = null;
            return;
        }
        checkFieldValueConstraint(6, millisecond);
        this.fractionalSecond = BigDecimal.valueOf((long) millisecond, 3);
    }

    public void setFractionalSecond(BigDecimal fractional) {
        if (fractional == null || (fractional.compareTo(DECIMAL_ZERO) >= 0 && fractional.compareTo(DECIMAL_ONE) <= 0)) {
            this.fractionalSecond = fractional;
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFractional", new Object[]{fractional}));
        }
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private String format(String format) {
        StringBuffer buf = new StringBuffer();
        int flen = format.length();
        int fidx = 0;
        while (fidx < flen) {
            int fidx2 = fidx + 1;
            char fch = format.charAt(fidx);
            if (fch != '%') {
                buf.append(fch);
                fidx = fidx2;
            } else {
                fidx = fidx2 + 1;
                switch (format.charAt(fidx2)) {
                    case 'D':
                        printNumber(buf, getDay(), 2);
                        break;
                    case 'M':
                        printNumber(buf, getMonth(), 2);
                        break;
                    case 'Y':
                        if (this.eon != null) {
                            printNumber(buf, getEonAndYear(), 4);
                            break;
                        }
                        int absYear = this.year;
                        if (absYear < 0) {
                            buf.append('-');
                            absYear = -this.year;
                        }
                        printNumber(buf, absYear, 4);
                        break;
                    case 'h':
                        printNumber(buf, getHour(), 2);
                        break;
                    case 'm':
                        printNumber(buf, getMinute(), 2);
                        break;
                    case 's':
                        printNumber(buf, getSecond(), 2);
                        if (getFractionalSecond() == null) {
                            break;
                        }
                        String frac = toString(getFractionalSecond());
                        buf.append(frac.substring(1, frac.length()));
                        break;
                    case 'z':
                        int offset = getTimezone();
                        if (offset != 0) {
                            if (offset != Integer.MIN_VALUE) {
                                if (offset < 0) {
                                    buf.append('-');
                                    offset *= -1;
                                } else {
                                    buf.append('+');
                                }
                                printNumber(buf, offset / 60, 2);
                                buf.append(':');
                                printNumber(buf, offset % 60, 2);
                                break;
                            }
                            break;
                        }
                        buf.append('Z');
                        break;
                    default:
                        throw new InternalError();
                }
            }
        }
        return buf.toString();
    }

    private void printNumber(StringBuffer out, int number, int nDigits) {
        String s = String.valueOf(number);
        for (int i = s.length(); i < nDigits; i++) {
            out.append('0');
        }
        out.append(s);
    }

    private void printNumber(StringBuffer out, BigInteger number, int nDigits) {
        String s = number.toString();
        for (int i = s.length(); i < nDigits; i++) {
            out.append('0');
        }
        out.append(s);
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

    static BigInteger sanitize(Number value, int signum) {
        if (signum == 0 || value == null) {
            return BigInteger.ZERO;
        }
        return signum < 0 ? ((BigInteger) value).negate() : (BigInteger) value;
    }

    public void reset() {
        this.eon = this.orig_eon;
        this.year = this.orig_year;
        this.month = this.orig_month;
        this.day = this.orig_day;
        this.hour = this.orig_hour;
        this.minute = this.orig_minute;
        this.second = this.orig_second;
        this.fractionalSecond = this.orig_fracSeconds;
        this.timezone = this.orig_timezone;
    }

    private Object writeReplace() throws IOException {
        return new SerializedXMLGregorianCalendar(toXMLFormat());
    }
}
