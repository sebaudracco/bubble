package com.unit.three.p140d.p142a;

public final class C4080b {
    private C4081c f9450a;
    private String f9451b;
    private int[] f9452c;
    private int f9453d;
    private boolean f9454e;

    public C4080b(String str, C4081c c4081c) {
        this.f9450a = c4081c;
        this.f9451b = str;
    }

    public final C4080b m12586a(int i) {
        this.f9453d = 1;
        return this;
    }

    public final C4080b m12587a(boolean z) {
        this.f9454e = true;
        return this;
    }

    public final C4080b m12588a(int... iArr) {
        this.f9452c = iArr;
        return this;
    }

    public final boolean m12589a() {
        return this.f9454e;
    }

    public final int m12590b() {
        return this.f9453d;
    }

    public final C4081c m12591c() {
        return this.f9450a;
    }

    public final String m12592d() {
        return this.f9451b;
    }

    public final String m12593e() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f9452c != null && this.f9452c.length > 0) {
            for (int append : this.f9452c) {
                stringBuilder.append(',').append(append);
            }
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }
}
