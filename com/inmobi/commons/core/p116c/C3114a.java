package com.inmobi.commons.core.p116c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.inmobi.commons.p112a.C3106b;

/* compiled from: DbHelper */
public class C3114a extends SQLiteOpenHelper {
    public static final String f7621a = ("com.im_" + C3106b.m10098c() + ".db");

    public C3114a(Context context) {
        super(context, f7621a, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
