package com.elephant.data.p041c;

import com.elephant.data.p048g.C1814b;

public final class C1741j {
    private static final String f3585a = C1814b.fj;
    private StringBuilder f3586b = new StringBuilder();

    public C1741j(String str) {
        this.f3586b.append(f3585a).append(str).append(C1814b.fk);
    }

    public final C1741j m5011a(String str, String str2, boolean z, boolean z2, boolean z3) {
        this.f3586b.append(C1814b.fl).append(str).append(" ").append(str2);
        if (z) {
            this.f3586b.append(C1814b.fm);
        }
        if (z3) {
            this.f3586b.append(C1814b.fo);
        }
        return this;
    }

    public final String m5012a() {
        String str = C1814b.fp;
        int indexOf = this.f3586b.indexOf(str, f3585a.length());
        this.f3586b.append(C1814b.fq).replace(indexOf, str.length() + indexOf, C1814b.fr);
        return this.f3586b.toString();
    }
}
