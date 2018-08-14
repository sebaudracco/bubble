package com.elephant.data.p044e.p045a;

import android.content.ContentValues;
import android.database.Cursor;
import com.elephant.data.p041c.C1732a;
import com.elephant.data.p048g.C1814b;

public final class C1783g extends C1779c {
    public int f3735a;
    public int f3736b = 0;
    public String f3737c;
    public int f3738d = 0;
    public int f3739e = 0;
    public String f3740f;
    public int f3741g = 3;
    public C1783g f3742h;
    public boolean f3743i;
    public String f3744j;

    public C1783g() {
        String str = C1814b.fc;
        this.f3743i = false;
    }

    public final ContentValues m5133a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C1732a.f3565c, Integer.valueOf(this.f3735a));
        contentValues.put(C1732a.f3569g, Boolean.valueOf(this.f3743i));
        contentValues.put(C1732a.f3564b, this.f3737c);
        if (this.f3740f != null && this.f3740f.getBytes().length < 2048) {
            contentValues.put(C1732a.f3566d, this.f3740f);
        }
        contentValues.put(C1732a.f3570h, this.f3744j);
        return contentValues;
    }

    public final void m5134a(Cursor cursor) {
        boolean z = true;
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(C1732a.f3565c);
            if (columnIndex != -1) {
                this.f3735a = cursor.getInt(columnIndex);
            }
            columnIndex = cursor.getColumnIndex(C1732a.f3564b);
            if (columnIndex != -1) {
                this.f3737c = cursor.getString(columnIndex);
            }
            columnIndex = cursor.getColumnIndex(C1732a.f3566d);
            if (columnIndex != -1) {
                this.f3740f = cursor.getString(columnIndex);
            }
            columnIndex = cursor.getColumnIndex(C1732a.f3568f);
            if (columnIndex != -1) {
                this.f3741g = cursor.getInt(columnIndex);
            }
            columnIndex = cursor.getColumnIndex(C1732a.f3567e);
            if (columnIndex != -1) {
                cursor.getString(columnIndex);
            }
            columnIndex = cursor.getColumnIndex(C1732a.f3569g);
            if (columnIndex != -1) {
                if (cursor.getInt(columnIndex) != 1) {
                    z = false;
                }
                this.f3743i = z;
            }
            int columnIndex2 = cursor.getColumnIndex(C1732a.f3570h);
            if (columnIndex2 != -1) {
                this.f3744j = cursor.getString(columnIndex2);
            }
        }
    }
}
