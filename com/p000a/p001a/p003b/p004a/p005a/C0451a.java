package com.p000a.p001a.p003b.p004a.p005a;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class C0451a {
    private static final TimeZone f16a = TimeZone.getTimeZone("UTC");

    private static int m15a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    private static int m16a(String str, int i, int i2) {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3;
        int i4 = 0;
        if (i < i2) {
            i3 = i + 1;
            i4 = Character.digit(str.charAt(i), 10);
            if (i4 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -i4;
        } else {
            i3 = i;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            i3 = Character.digit(str.charAt(i3), 10);
            if (i3 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - i3;
            i3 = i5;
        }
        return -i4;
    }

    public static Date m17a(String str, ParsePosition parsePosition) {
        String substring;
        Throwable th;
        String message;
        ParseException parseException;
        try {
            int index = parsePosition.getIndex();
            int i = index + 4;
            int a = C0451a.m16a(str, index, i);
            index = C0451a.m18a(str, i, '-') ? i + 1 : i;
            i = index + 2;
            int a2 = C0451a.m16a(str, index, i);
            index = C0451a.m18a(str, i, '-') ? i + 1 : i;
            i = index + 2;
            int a3 = C0451a.m16a(str, index, i);
            boolean a4 = C0451a.m18a(str, i, 'T');
            if (a4 || str.length() > i) {
                int a5;
                int i2;
                int i3;
                if (a4) {
                    index = i + 1;
                    i = index + 2;
                    a5 = C0451a.m16a(str, index, i);
                    index = C0451a.m18a(str, i, ':') ? i + 1 : i;
                    i = index + 2;
                    index = C0451a.m16a(str, index, i);
                    if (C0451a.m18a(str, i, ':')) {
                        i++;
                    }
                    if (str.length() > i) {
                        char charAt = str.charAt(i);
                        if (!(charAt == 'Z' || charAt == '+' || charAt == '-')) {
                            i2 = i + 2;
                            i = C0451a.m16a(str, i, i2);
                            if (i > 59 && i < 63) {
                                i = 59;
                            }
                            if (C0451a.m18a(str, i2, '.')) {
                                i3 = i2 + 1;
                                i2 = C0451a.m15a(str, i3 + 1);
                                int min = Math.min(i2, i3 + 3);
                                int a6 = C0451a.m16a(str, i3, min);
                                switch (min - i3) {
                                    case 1:
                                        a6 *= 100;
                                        break;
                                    case 2:
                                        a6 *= 10;
                                        break;
                                }
                                i3 = a5;
                                a5 = i;
                                i = i2;
                                i2 = index;
                                index = a6;
                            } else {
                                i3 = a5;
                                a5 = i;
                                i = i2;
                                i2 = index;
                                index = 0;
                            }
                        }
                    }
                    i2 = index;
                    i3 = a5;
                    index = 0;
                    a5 = 0;
                } else {
                    index = 0;
                    a5 = 0;
                    i2 = 0;
                    i3 = 0;
                }
                if (str.length() <= i) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                TimeZone timeZone;
                char charAt2 = str.charAt(i);
                if (charAt2 == 'Z') {
                    timeZone = f16a;
                    i++;
                } else if (charAt2 == '+' || charAt2 == '-') {
                    substring = str.substring(i);
                    if (substring.length() < 5) {
                        substring = substring + "00";
                    }
                    i += substring.length();
                    if ("+0000".equals(substring) || "+00:00".equals(substring)) {
                        timeZone = f16a;
                    } else {
                        String str2 = "GMT" + substring;
                        timeZone = TimeZone.getTimeZone(str2);
                        String id = timeZone.getID();
                        if (!(id.equals(str2) || id.replace(":", "").equals(str2))) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str2 + " given, resolves to " + timeZone.getID());
                        }
                    }
                } else {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                }
                Calendar gregorianCalendar = new GregorianCalendar(timeZone);
                gregorianCalendar.setLenient(false);
                gregorianCalendar.set(1, a);
                gregorianCalendar.set(2, a2 - 1);
                gregorianCalendar.set(5, a3);
                gregorianCalendar.set(11, i3);
                gregorianCalendar.set(12, i2);
                gregorianCalendar.set(13, a5);
                gregorianCalendar.set(14, index);
                parsePosition.setIndex(i);
                return gregorianCalendar.getTime();
            }
            Calendar gregorianCalendar2 = new GregorianCalendar(a, a2 - 1, a3);
            parsePosition.setIndex(i);
            return gregorianCalendar2.getTime();
        } catch (Throwable e) {
            th = e;
            substring = str == null ? null : '\"' + str + "'";
            message = th.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + th.getClass().getName() + ")";
            }
            parseException = new ParseException("Failed to parse date [" + substring + "]: " + message, parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        } catch (Throwable e2) {
            th = e2;
            if (str == null) {
            }
            message = th.getMessage();
            message = "(" + th.getClass().getName() + ")";
            parseException = new ParseException("Failed to parse date [" + substring + "]: " + message, parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        } catch (Throwable e22) {
            th = e22;
            if (str == null) {
            }
            message = th.getMessage();
            message = "(" + th.getClass().getName() + ")";
            parseException = new ParseException("Failed to parse date [" + substring + "]: " + message, parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        }
    }

    private static boolean m18a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }
}
