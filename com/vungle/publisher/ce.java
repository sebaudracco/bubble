package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;

/* compiled from: vungle */
public final class ce {
    public static Boolean m12914a(Cursor cursor, String str) throws SQLException {
        Integer c = m12921c(cursor, str);
        if (c == null) {
            return null;
        }
        switch (c.intValue()) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return Boolean.TRUE;
            default:
                throw new SQLException("invalid boolean value " + c + " for column " + str);
        }
    }

    public static <E extends Enum<E>> E m12916a(Cursor cursor, String str, Class<E> cls) throws SQLException {
        try {
            return m12915a(cursor, cursor.getColumnIndexOrThrow(str), (Class) cls);
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static <E extends Enum<E>> E m12915a(Cursor cursor, int i, Class<E> cls) throws SQLException {
        String string = cursor.getString(i);
        try {
            return cursor.isNull(i) ? null : Enum.valueOf(cls, string);
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid enum: " + string);
        }
    }

    public static Float m12919b(Cursor cursor, String str) throws SQLException {
        try {
            return m12917a(cursor, cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static Float m12917a(Cursor cursor, int i) throws SQLException {
        return cursor.isNull(i) ? null : Float.valueOf(cursor.getFloat(i));
    }

    public static int m12913a(Cursor cursor, String str, int i) throws SQLException {
        try {
            return m12912a(cursor, cursor.getColumnIndexOrThrow(str), i);
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static int m12912a(Cursor cursor, int i, int i2) throws SQLException {
        Integer b = m12920b(cursor, i);
        return b == null ? i2 : b.intValue();
    }

    public static Integer m12921c(Cursor cursor, String str) throws SQLException {
        try {
            return m12920b(cursor, cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static Integer m12920b(Cursor cursor, int i) throws SQLException {
        return cursor.isNull(i) ? null : Integer.valueOf(cursor.getInt(i));
    }

    public static Long m12923d(Cursor cursor, String str) throws SQLException {
        try {
            return m12922c(cursor, cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static Long m12922c(Cursor cursor, int i) throws SQLException {
        return cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
    }

    public static String m12925e(Cursor cursor, String str) throws SQLException {
        try {
            return m12924d(cursor, cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static String m12924d(Cursor cursor, int i) throws SQLException {
        return cursor.getString(i);
    }

    public static String m12918a(int i) {
        int i2 = 1;
        if (i < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder((i * 2) - 1);
        stringBuilder.append("?");
        while (i2 < i) {
            stringBuilder.append(",?");
            i2++;
        }
        return stringBuilder.toString();
    }
}
