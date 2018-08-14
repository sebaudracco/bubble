package com.areametrics.areametricssdk;

import android.os.Handler;

final class C1338a {
    C1337a f1847a;
    Handler f1848b;
    boolean f1849c = false;
    long f1850d = 0;

    interface C1337a {
        void mo2160a();
    }

    C1338a(C1337a c1337a) {
        this.f1847a = c1337a;
        this.f1848b = new Handler();
    }

    final void m2434a(long j) {
        this.f1850d += j;
        if (!this.f1849c && this.f1848b != null) {
            this.f1849c = true;
            this.f1848b.postDelayed(m2435b(j), j);
        }
    }

    final Runnable m2435b(final long j) {
        return new Runnable(this) {
            final /* synthetic */ C1338a f1846b;

            public final void run() {
                this.f1846b.f1850d -= j;
                if (this.f1846b.f1850d == 0) {
                    this.f1846b.f1849c = false;
                    if (this.f1846b.f1847a != null) {
                        this.f1846b.f1847a.mo2160a();
                    }
                } else if (this.f1846b.f1848b != null) {
                    this.f1846b.f1848b.postDelayed(this.f1846b.m2435b(this.f1846b.f1850d), this.f1846b.f1850d);
                }
            }
        };
    }
}
