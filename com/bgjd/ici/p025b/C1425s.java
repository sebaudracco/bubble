package com.bgjd.ici.p025b;

import android.content.Context;
import android.content.Intent;

public class C1425s {
    private C1400e f2204a;

    public static class C1424a {
        private C1400e f2201a = null;
        private C1395h f2202b = null;
        private C1397l f2203c = null;

        public C1424a(Context context) {
            this.f2202b = new aa(context);
        }

        public C1424a m2939a(C1397l c1397l) {
            this.f2203c = c1397l;
            return this;
        }

        public C1424a m2941a(boolean z) {
            this.f2202b.mo2229d(z);
            return this;
        }

        public C1424a m2940a(String str) {
            this.f2202b.mo2228d(str);
            return this;
        }

        public C1424a m2937a(Intent intent) {
            this.f2202b.mo2202a(intent);
            return this;
        }

        public C1424a m2938a(C1400e c1400e) {
            this.f2201a = c1400e;
            return this;
        }

        public C1425s m2942a() {
            this.f2203c.m2853a(this.f2202b);
            this.f2201a.mo2315a(this.f2203c);
            return new C1425s();
        }
    }

    private C1425s(C1424a c1424a) {
        this.f2204a = null;
        this.f2204a = c1424a.f2201a;
    }

    public void m2943a() {
        final C1429w c1429w = new C1429w(this.f2204a);
        if (c1429w != null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ C1425s f2200b;

                public void run() {
                    c1429w.m2962b();
                }
            }).start();
        }
    }
}
