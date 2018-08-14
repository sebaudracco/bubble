package com.fyber.requesters.p097a.p098a;

import com.fyber.requesters.p097a.C2579k;

/* compiled from: CachedResponse */
public final class C2602f<T, R extends C2579k> {
    private T f6490a;
    private long f6491b;
    private R f6492c;
    private int f6493d = -1;
    private int f6494e = 0;
    private int f6495f = 0;
    private boolean f6496g = true;

    public C2602f(T t) {
        this.f6490a = t;
        this.f6491b = System.currentTimeMillis();
    }

    public final T m8341a() {
        return this.f6490a;
    }

    public final long m8342b() {
        return this.f6491b;
    }

    public final C2602f<T, R> m8340a(R r) {
        this.f6492c = r;
        return this;
    }

    public final R m8344c() {
        return this.f6492c;
    }

    public final C2602f<T, R> m8339a(int i) {
        this.f6493d = i;
        return this;
    }

    public final int m8345d() {
        return this.f6493d;
    }

    public final int m8346e() {
        return this.f6494e;
    }

    public final C2602f<T, R> m8347f() {
        this.f6494e++;
        return this;
    }

    public final int m8348g() {
        return this.f6495f;
    }

    public final C2602f<T, R> m8343b(int i) {
        this.f6495f = i;
        return this;
    }

    public final C2602f<T, R> m8349h() {
        this.f6496g = false;
        return this;
    }

    public final boolean m8350i() {
        return this.f6496g;
    }
}
