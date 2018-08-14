package com.yandex.metrica.impl.ob;

public class fw {
    private int f12433a;
    private int f12434b;
    private final int f12435c;
    private final float f12436d;

    public fw() {
        this(2500, 1, 1.0f);
    }

    public fw(int i, int i2, float f) {
        this.f12433a = i;
        this.f12435c = i2;
        this.f12436d = f;
    }

    public int m16078a() {
        return this.f12433a;
    }

    public void m16079a(fr frVar) throws fr {
        this.f12434b++;
        this.f12433a = (int) (((float) this.f12433a) + (((float) this.f12433a) * this.f12436d));
        if (!m16080b()) {
            throw frVar;
        }
    }

    protected boolean m16080b() {
        return this.f12434b <= this.f12435c;
    }
}
