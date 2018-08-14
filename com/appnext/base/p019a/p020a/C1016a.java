package com.appnext.base.p019a.p020a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1023b;
import com.appnext.base.p023b.C1043d;

public class C1016a {
    private static C1016a fW;
    private static C1023b fX;
    private Integer fV = Integer.valueOf(0);
    private SQLiteDatabase fY;

    public enum C1015a {
        Global,
        DatabaseOrDiskFull
    }

    private C1016a(Context context) {
        fX = C1023b.m2067f(context);
    }

    public static synchronized void m2060g(Context context) {
        synchronized (C1016a.class) {
            if (fW == null) {
                fW = new C1016a(context.getApplicationContext());
            }
        }
    }

    public static C1016a aT() {
        if (fW == null) {
            synchronized (C1016a.class) {
                if (fW == null) {
                    fW = new C1016a(C1043d.getContext().getApplicationContext());
                }
            }
        }
        return fW;
    }

    public synchronized SQLiteDatabase aU() {
        this.fV = Integer.valueOf(this.fV.intValue() + 1);
        if (this.fV.intValue() == 1) {
            this.fY = fX.getWritableDatabase();
        }
        return this.fY;
    }

    public synchronized void aV() {
        this.fV = Integer.valueOf(this.fV.intValue() - 1);
        if (this.fV.intValue() == 0) {
            this.fY.close();
        }
    }

    public void m2061a(C1015a c1015a, Throwable th) {
        switch (c1015a) {
            case DatabaseOrDiskFull:
            case Global:
                C1061b.m2191a(th);
                return;
            default:
                return;
        }
    }
}
