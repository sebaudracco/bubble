package com.facebook.ads.internal.p062e;

import android.support.annotation.Nullable;
import com.facebook.ads.AdError;

abstract class C1969f<T> {
    private C1975a f4574a;

    public enum C1975a {
        f4601a(9000, "An unknown error has occurred."),
        DATABASE_SELECT(AdError.MEDIATION_ERROR_CODE, "Failed to read from database."),
        DATABASE_INSERT(3002, "Failed to insert row into database."),
        DATABASE_UPDATE(3003, "Failed to update row in database."),
        DATABASE_DELETE(3004, "Failed to delete row from database.");
        
        private final int f4607f;
        private final String f4608g;

        private C1975a(int i, String str) {
            this.f4607f = i;
            this.f4608g = str;
        }

        public int m6229a() {
            return this.f4607f;
        }

        public String m6230b() {
            return this.f4608g;
        }
    }

    C1969f() {
    }

    protected void m6206a(C1975a c1975a) {
        this.f4574a = c1975a;
    }

    @Nullable
    abstract T mo3704b();

    public C1975a m6208c() {
        return this.f4574a;
    }
}
