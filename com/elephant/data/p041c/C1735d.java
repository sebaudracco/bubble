package com.elephant.data.p041c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.elephant.data.p048g.C1814b;

public final class C1735d extends SQLiteOpenHelper {
    private static String f3574a = C1814b.gf;

    public C1735d(Context context) {
        super(context, f3574a, null, 1);
    }

    public final int m4994a(String str, ContentValues contentValues, String str2, String[] strArr) {
        int i = 0;
        try {
            i = getWritableDatabase().update(str, contentValues, str2, strArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public final int m4995a(String str, String str2, String[] strArr) {
        int i = 0;
        try {
            i = getWritableDatabase().delete(str, str2, strArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public final long m4996a(String str, ContentValues contentValues) {
        long j = 0;
        try {
            j = getWritableDatabase().insert(str, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public final Cursor m4997a(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        try {
            try {
                return getWritableDatabase().query(str, strArr, str2, strArr2, null, null, str3);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL(C1732a.f3571i);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
