package com.yandex.metrica.impl.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Build.VERSION;

public class C4521e {
    public static void m16255a(Cursor cursor, ContentValues contentValues) {
        if (VERSION.SDK_INT >= 11) {
            String[] columnNames = cursor.getColumnNames();
            int length = columnNames.length;
            for (int i = 0; i < length; i++) {
                switch (cursor.getType(i)) {
                    case 0:
                        contentValues.put(columnNames[i], cursor.getString(i));
                        break;
                    case 1:
                        contentValues.put(columnNames[i], Integer.valueOf(cursor.getInt(i)));
                        break;
                    case 2:
                        contentValues.put(columnNames[i], Float.valueOf(cursor.getFloat(i)));
                        break;
                    case 3:
                        contentValues.put(columnNames[i], cursor.getString(i));
                        break;
                    case 4:
                        contentValues.put(columnNames[i], cursor.getBlob(i));
                        break;
                    default:
                        contentValues.put(columnNames[i], cursor.getString(i));
                        break;
                }
            }
            return;
        }
        DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
    }

    public static String m16254a(int i) {
        int i2;
        StringBuilder stringBuilder = new StringBuilder();
        for (i2 = 0; i2 < i; i2++) {
            stringBuilder.append("?,");
        }
        i2 = stringBuilder.length();
        stringBuilder.replace(i2 - 1, i2, "");
        return stringBuilder.toString();
    }
}
