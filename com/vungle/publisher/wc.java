package com.vungle.publisher;

/* compiled from: vungle */
public abstract class wc {
    String f11199a;
    Integer f11200b;
    String f11201c;
    Long f11202d;
    wu f11203e;
    C4238m f11204f;
    String f11205g;
    Integer f11206h;
    String f11207i;
    String f11208j;
    String f11209k;
    protected String f11210l;

    public String m14030a() {
        return this.f11201c;
    }

    public String m14031b() {
        return yx.m14180a(this.f11201c, "ad_token");
    }

    public Long m14032c() {
        return this.f11202d;
    }

    public C4238m m14033d() {
        return this.f11204f;
    }

    public wu m14034e() {
        return this.f11203e;
    }

    public String m14035f() {
        return this.f11205g;
    }

    public String m14036g() {
        return this.f11210l;
    }

    public String m14037h() {
        return this.f11207i;
    }

    public Integer m14038i() {
        return this.f11206h;
    }

    public Long m14039j() {
        return this.f11200b == null ? null : Long.valueOf(ze.m14197a((long) this.f11200b.intValue(), 1000));
    }

    public boolean m14040k() {
        return this.f11202d.longValue() * 1000 < System.currentTimeMillis();
    }

    public String m14041l() {
        return this.f11208j;
    }

    public String m14042m() {
        return this.f11209k;
    }
}
