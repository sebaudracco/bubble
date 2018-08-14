package com.appnext.base.p019a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p022c.C1025a;
import com.appnext.base.p019a.p022c.C1027b;
import com.appnext.base.p019a.p022c.C1028c;
import com.appnext.base.p019a.p022c.C1030f;
import com.appnext.base.p019a.p022c.C1032g;

public class C1023b extends SQLiteOpenHelper {
    private static final String fS = "db467";
    private static final int fT = 8;
    private static volatile C1023b fU;

    public static C1023b m2067f(Context context) {
        if (fU == null) {
            synchronized (C1023b.class) {
                if (fU == null) {
                    fU = new C1023b(context.getApplicationContext());
                }
            }
        }
        return fU;
    }

    private C1023b(Context context) {
        super(context, fS, null, 8);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        m2068a(sQLiteDatabase);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(C1025a.bl());
            sQLiteDatabase.execSQL(C1027b.bl());
            sQLiteDatabase.execSQL(C1030f.bl());
            sQLiteDatabase.execSQL(C1032g.bl());
            sQLiteDatabase.execSQL(C1028c.bl());
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    public void m2068a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS category_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS collected_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_data_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS times_location_table");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config_table");
            onCreate(sQLiteDatabase);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }
}
