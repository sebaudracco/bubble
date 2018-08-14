package com.elephant.data.p041c;

import com.elephant.data.p044e.p045a.C1783g;

public final class C1739h {
    private C1783g f3580a;
    private C1783g f3581b;

    public final C1783g m5008a() {
        synchronized (this) {
            if (this.f3580a != null) {
                C1783g c1783g = this.f3580a;
                C1783g c1783g2 = c1783g.f3742h;
                this.f3580a = null;
                C1783g c1783g3 = c1783g;
                int i = 1;
                C1783g c1783g4 = null;
                while (c1783g2 != null && i < 30) {
                    if (c1783g3.f3735a == c1783g2.f3735a && c1783g3.f3741g == c1783g2.f3741g) {
                        c1783g3.f3742h = c1783g2;
                        i++;
                        c1783g3 = c1783g2;
                    } else if (this.f3580a == null) {
                        this.f3580a = c1783g2;
                    } else {
                        if (c1783g4 == null) {
                            this.f3580a.f3742h = c1783g2;
                        } else {
                            c1783g4.f3742h = c1783g2;
                        }
                        c1783g4 = c1783g2;
                    }
                    c1783g2 = c1783g2.f3742h;
                }
                if (this.f3580a == null) {
                    this.f3580a = c1783g2;
                } else if (this.f3580a != null && c1783g4 == null) {
                    this.f3580a.f3742h = c1783g2;
                }
                if (c1783g2 == null || c1783g3 == this.f3581b) {
                    this.f3581b = c1783g4;
                } else if (c1783g4 != null) {
                    c1783g4.f3742h = c1783g2;
                }
                if (this.f3581b != null) {
                    this.f3581b.f3742h = null;
                }
                c1783g3.f3742h = null;
                return c1783g;
            }
            return null;
        }
    }

    public final void m5009a(C1783g c1783g) {
        if (c1783g != null) {
            synchronized (this) {
                if (this.f3580a == null) {
                    this.f3580a = c1783g;
                }
                if (this.f3581b == null) {
                    this.f3581b = this.f3580a;
                }
                if (this.f3581b != c1783g) {
                    this.f3581b.f3742h = c1783g;
                }
                while (c1783g.f3742h != null) {
                    c1783g = c1783g.f3742h;
                }
                this.f3581b = c1783g;
                this.f3581b.f3742h = null;
            }
        }
    }
}
