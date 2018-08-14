package com.yandex.metrica.impl;

import com.yandex.metrica.impl.ob.cq;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class ak {
    protected String f11633d;
    protected String f11634e;
    protected int f11635f = 1;
    protected byte[] f11636g;
    protected int f11637h;
    protected byte[] f11638i;
    protected Map<String, List<String>> f11639j;
    protected boolean f11640k = false;
    protected int f11641l = -1;

    static final class C4323a {
        static final long f11631a = TimeUnit.SECONDS.toMillis(5);
        static final long f11632b = TimeUnit.SECONDS.toMillis(15);
    }

    public abstract boolean mo6996b();

    public abstract boolean mo6997c();

    abstract cq mo6998d();

    public abstract boolean mo7001o();

    public void mo6999e() {
        this.f11641l++;
    }

    public void mo7000f() {
    }

    public void mo7026g() {
    }

    public String m14580h() {
        return this.f11633d;
    }

    public void m14569a(String str) {
        this.f11633d = str;
    }

    public int m14581i() {
        return this.f11635f;
    }

    public byte[] m14582j() {
        return this.f11636g;
    }

    public void m14571a(byte[] bArr) {
        this.f11635f = 2;
        this.f11636g = bArr;
    }

    public int m14583k() {
        return this.f11637h;
    }

    public void m14568a(int i) {
        this.f11637h = i;
    }

    public void m14573b(byte[] bArr) {
        this.f11638i = bArr;
    }

    Map<String, List<String>> m14584l() {
        return this.f11639j;
    }

    void m14570a(Map<String, List<String>> map) {
        this.f11639j = map;
    }

    public String m14585m() {
        return this.f11634e;
    }

    public void m14572b(String str) {
        this.f11634e = str;
    }

    public String mo6995a() {
        return getClass().getName();
    }

    public boolean mo7027n() {
        return false;
    }

    public long mo7002p() {
        return 0;
    }

    public int m14589q() {
        return this.f11641l;
    }

    public boolean m14590r() {
        return this.f11640k;
    }
}
