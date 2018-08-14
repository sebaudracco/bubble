package com.yandex.metrica.impl.ob;

public class C4438do {
    protected int f12230a = 0;
    private final int f12231b;
    private boolean f12232c;

    public C4438do(int i) {
        this.f12231b = i;
    }

    public boolean m15735b() {
        return this.f12232c && this.f12230a < this.f12231b;
    }

    public void mo7074a() {
        this.f12230a++;
        this.f12232c = false;
    }

    public void m15736c() {
        this.f12232c = true;
    }
}
