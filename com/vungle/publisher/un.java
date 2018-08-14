package com.vungle.publisher;

import android.os.SystemClock;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public class un {
    int f11146a;
    private long f11147b;
    private int f11148c;

    public long m13980a() {
        return this.f11147b;
    }

    private void m13979a(long j) {
        this.f11147b = j;
    }

    public int m13981b() {
        return this.f11146a;
    }

    public int m13982c() {
        return this.f11148c;
    }

    void m13983d() {
        if (this.f11147b <= 0) {
            m13979a(SystemClock.elapsedRealtime());
        }
        this.f11146a++;
        this.f11148c++;
    }

    int m13984e() {
        int i = this.f11148c - 1;
        this.f11148c = i;
        if (i < 0) {
            Logger.m13635d(Logger.NETWORK_TAG, "Attempted to decrement softRetryCount < 0");
            this.f11148c = 0;
        }
        return this.f11148c;
    }

    public String toString() {
        return "{firstAttemptMillis: " + this.f11147b + ", hardRetryCount: " + this.f11146a + ", softRetryCount: " + this.f11148c + "}";
    }
}
