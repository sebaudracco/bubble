package com.facebook.ads.internal.p062e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class C1974e extends SQLiteOpenHelper {
    private final C1973d f4600a;

    public C1974e(Context context, C1973d c1973d) {
        super(context, "ads.db", null, 4);
        if (c1973d == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.f4600a = c1973d;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (C1966g a : this.f4600a.m6224c()) {
            a.m6195a(sQLiteDatabase);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (C1966g c1966g : this.f4600a.m6224c()) {
            c1966g.m6196b(sQLiteDatabase);
            c1966g.m6195a(sQLiteDatabase);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            C1965b c1965b = C1967c.f4570i;
            sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN " + c1965b.f4559b + " " + c1965b.f4560c + " DEFAULT 0");
        }
    }
}
