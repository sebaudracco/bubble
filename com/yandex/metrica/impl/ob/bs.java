package com.yandex.metrica.impl.ob;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.yandex.metrica.impl.ob.bm.C4394l;

public class bs {
    private final C4394l f11983a;
    private final C4394l f11984b;
    private final SparseArray<C4394l> f11985c;
    private final bt f11986d;

    public bs(C4394l c4394l, C4394l c4394l2, SparseArray<C4394l> sparseArray, bt btVar) {
        this.f11983a = c4394l;
        this.f11984b = c4394l2;
        this.f11985c = sparseArray;
        this.f11986d = btVar;
    }

    public void m15391a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (this.f11986d != null && !this.f11986d.mo7057a(sQLiteDatabase)) {
                m15393a(sQLiteDatabase, this.f11983a, this.f11984b);
            }
        } catch (Exception e) {
        }
    }

    public void m15395b(SQLiteDatabase sQLiteDatabase) {
        m15394a(this.f11983a, sQLiteDatabase);
    }

    void m15394a(C4394l c4394l, SQLiteDatabase sQLiteDatabase) {
        try {
            c4394l.mo7056a(sQLiteDatabase);
        } catch (Exception e) {
        }
    }

    public void m15392a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        int i3;
        int i4 = 1;
        if (i2 > i) {
            int i5 = i + 1;
            while (i5 <= i2) {
                try {
                    C4394l c4394l = (C4394l) this.f11985c.get(i5);
                    if (c4394l != null) {
                        c4394l.mo7056a(sQLiteDatabase);
                    }
                    i5++;
                } catch (Exception e) {
                    i3 = 1;
                }
            }
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (this.f11986d.mo7057a(sQLiteDatabase)) {
            i4 = 0;
        }
        if ((i3 | i4) != 0) {
            m15393a(sQLiteDatabase, this.f11983a, this.f11984b);
        }
    }

    void m15393a(SQLiteDatabase sQLiteDatabase, C4394l c4394l, C4394l c4394l2) {
        try {
            c4394l2.mo7056a(sQLiteDatabase);
        } catch (Exception e) {
        }
        m15394a(c4394l, sQLiteDatabase);
    }
}
