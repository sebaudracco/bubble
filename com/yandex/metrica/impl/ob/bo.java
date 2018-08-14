package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Closeable;

public class bo extends SQLiteOpenHelper implements Closeable {
    protected final bs f11964a;

    public bo(Context context, String str, bs bsVar) {
        super(context, str, null, bm.f11946b);
        this.f11964a = bsVar;
    }

    public void onCreate(SQLiteDatabase database) {
        this.f11964a.m15395b(database);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        this.f11964a.m15392a(database, oldVersion, newVersion);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.f11964a.m15391a(db);
    }
}
