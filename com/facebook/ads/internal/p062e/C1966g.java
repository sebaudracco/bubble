package com.facebook.ads.internal.p062e;

import android.database.sqlite.SQLiteDatabase;

public abstract class C1966g {
    protected final C1973d f4561k;

    protected C1966g(C1973d c1973d) {
        this.f4561k = c1973d;
    }

    public static String m6191a(String str, C1965b[] c1965bArr) {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (int i = 0; i < c1965bArr.length - 1; i++) {
            stringBuilder.append(c1965bArr[i].f4559b);
            stringBuilder.append(", ");
        }
        stringBuilder.append(c1965bArr[c1965bArr.length - 1].f4559b);
        stringBuilder.append(" FROM ");
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static String m6192a(String str, C1965b[] c1965bArr, C1965b c1965b) {
        StringBuilder stringBuilder = new StringBuilder(C1966g.m6191a(str, c1965bArr));
        stringBuilder.append(" WHERE ");
        stringBuilder.append(c1965b.f4559b);
        stringBuilder.append(" = ?");
        return stringBuilder.toString();
    }

    private String mo3703c() {
        C1965b[] b = mo3702b();
        if (b.length < 1) {
            return null;
        }
        String str = "";
        for (int i = 0; i < b.length - 1; i++) {
            str = str + b[i].m6190a() + ", ";
        }
        return str + b[b.length - 1].m6190a();
    }

    public abstract String mo3701a();

    public void m6195a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE " + mo3701a() + " (" + mo3703c() + ")");
    }

    public void m6196b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + mo3701a());
    }

    public abstract C1965b[] mo3702b();

    public void m6198e() {
    }

    protected SQLiteDatabase m6199f() {
        return this.f4561k.m6218a();
    }
}
