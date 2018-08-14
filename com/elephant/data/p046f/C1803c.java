package com.elephant.data.p046f;

public final class C1803c {
    private String f3796a;
    private String[] f3797b;
    private int f3798c;
    private boolean f3799d;

    public C1803c(String str) {
        this.f3796a = str;
    }

    public final C1803c m5186a(int i) {
        this.f3798c = i;
        return this;
    }

    public final C1803c m5187a(boolean z) {
        this.f3799d = true;
        return this;
    }

    public final C1803c m5188a(String... strArr) {
        this.f3797b = strArr;
        return this;
    }

    public final boolean m5189a() {
        return this.f3799d;
    }

    public final int m5190b() {
        return this.f3798c;
    }

    public final String m5191c() {
        return this.f3796a;
    }

    public final String m5192d() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f3797b != null && this.f3797b.length > 0) {
            for (String append : this.f3797b) {
                stringBuilder.append(',').append(append);
            }
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }
}
