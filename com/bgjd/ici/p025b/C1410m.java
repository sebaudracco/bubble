package com.bgjd.ici.p025b;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class C1410m extends SQLiteOpenHelper {
    private C1395h f2173a = null;

    public C1410m(C1395h c1395h) {
        super(c1395h.getContext(), c1395h.getDatabasePath(), null, c1395h.getDatabaseVersion());
        this.f2173a = c1395h;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (String execSQL : this.f2173a.getDatabseCreateTables()) {
            sQLiteDatabase.execSQL(execSQL);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (String str : this.f2173a.getDatabaseTableNames()) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        }
        onCreate(sQLiteDatabase);
    }
}
