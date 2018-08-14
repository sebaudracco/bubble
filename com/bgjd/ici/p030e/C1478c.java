package com.bgjd.ici.p030e;

import android.database.Cursor;

public class C1478c {
    private int f2371a = 0;
    private String f2372b = "";

    public C1478c(Cursor cursor) {
        this.f2371a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2372b = cursor.getString(cursor.getColumnIndexOrThrow("beacon_layout"));
    }

    public C1478c(String str) {
        this.f2372b = str;
    }

    public int m3075a() {
        return this.f2371a;
    }

    public String m3076b() {
        return this.f2372b;
    }
}
