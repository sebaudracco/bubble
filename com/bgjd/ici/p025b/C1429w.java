package com.bgjd.ici.p025b;

import com.bgjd.ici.p027g.C1415b;
import com.bgjd.ici.p027g.C1494a;

public class C1429w implements C1415b {
    private C1494a f2212a = null;
    private C1400e f2213b = null;
    private String f2214c = "";
    private int f2215d = 0;

    public C1429w(C1400e c1400e) {
        this.f2212a = c1400e.mo2312a((C1415b) this);
        this.f2213b = c1400e;
    }

    public void m2962b() {
        if (this.f2214c.length() == 0) {
            this.f2214c = this.f2213b.mo2314a();
        }
        if (this.f2214c.length() > 10) {
            this.f2212a.mo2330a();
        }
    }

    public void mo2304a() {
        if (this.f2214c.length() > 0) {
            this.f2212a.mo2331a(this.f2214c);
        }
    }

    public void mo2307a(String str) {
        this.f2213b.mo2316a(str);
        this.f2212a.mo2333b();
        this.f2215d = 0;
    }

    public void mo2308a(byte[] bArr) {
    }

    public void mo2305a(int i, String str) {
        this.f2213b.mo2317b(String.format("Code %s : Error %s", new Object[]{Integer.valueOf(i), str}));
        this.f2212a.mo2333b();
        this.f2215d = 0;
    }

    public void mo2306a(Exception exception) {
        this.f2212a.mo2333b();
        if (this.f2215d >= 3) {
            this.f2215d = 0;
            this.f2213b.mo2317b(exception.getMessage());
            return;
        }
        this.f2215d++;
        this.f2212a = this.f2213b.mo2313a("https", this);
        m2962b();
    }
}
