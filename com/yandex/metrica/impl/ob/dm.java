package com.yandex.metrica.impl.ob;

import java.util.Random;

public class dm {
    private int f12226a;
    private int f12227b;
    private Random f12228c;
    private int f12229d;

    public dm(int i) {
        if (i <= 0 || i > 31) {
            this.f12226a = 31;
        } else {
            this.f12226a = i;
        }
        this.f12228c = new Random();
    }

    public int m15733a() {
        if (this.f12227b < this.f12226a) {
            this.f12227b++;
            this.f12229d = 1 << this.f12227b;
        }
        return this.f12228c.nextInt(this.f12229d);
    }
}
