package com.facebook.ads.internal.p065h;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class C1989c {
    private List<C1987a> f4649a = new ArrayList();
    private int f4650b = 0;
    private C1990d f4651c;
    @Nullable
    private String f4652d;

    public C1989c(C1990d c1990d, @Nullable String str) {
        this.f4651c = c1990d;
        this.f4652d = str;
    }

    public C1990d m6286a() {
        return this.f4651c;
    }

    public void m6287a(C1987a c1987a) {
        this.f4649a.add(c1987a);
    }

    @Nullable
    public String m6288b() {
        return this.f4652d;
    }

    public int m6289c() {
        return this.f4649a.size();
    }

    public C1987a m6290d() {
        if (this.f4650b >= this.f4649a.size()) {
            return null;
        }
        this.f4650b++;
        return (C1987a) this.f4649a.get(this.f4650b - 1);
    }
}
