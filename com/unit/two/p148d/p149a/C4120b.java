package com.unit.two.p148d.p149a;

public final class C4120b {
    private C4121c f9597a;
    private String f9598b;
    private int[] f9599c;
    private int f9600d;
    private boolean f9601e;
    private String f9602f;

    public C4120b(String str, C4121c c4121c) {
        this.f9597a = c4121c;
        this.f9598b = str;
    }

    public final C4120b m12718a(int i) {
        this.f9600d = 1;
        return this;
    }

    public final C4120b m12719a(String str) {
        this.f9602f = str;
        return this;
    }

    public final C4120b m12720a(boolean z) {
        this.f9601e = true;
        return this;
    }

    public final C4120b m12721a(int... iArr) {
        this.f9599c = iArr;
        return this;
    }

    public final boolean m12722a() {
        return this.f9601e;
    }

    public final int m12723b() {
        return this.f9600d;
    }

    public final C4121c m12724c() {
        return this.f9597a;
    }

    public final String m12725d() {
        return this.f9598b;
    }

    public final String m12726e() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f9599c != null && this.f9599c.length > 0) {
            for (int append : this.f9599c) {
                stringBuilder.append(',').append(append);
            }
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }

    public final String m12727f() {
        return this.f9602f;
    }
}
