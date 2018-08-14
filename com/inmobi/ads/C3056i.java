package com.inmobi.ads;

import com.inmobi.ads.C3046c.C3038b;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.utilities.uid.C3169d;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: AdStoreRequest */
final class C3056i {
    private String f7440a;
    private int f7441b;
    private int f7442c;
    private long f7443d;
    private String f7444e;
    private String f7445f;
    private Map<String, String> f7446g;
    private String f7447h;
    private String f7448i;
    private C3038b f7449j;
    private Map<String, String> f7450k;
    private C3169d f7451l;
    private MonetizationContext f7452m;

    C3056i() {
    }

    public String m9816a() {
        return this.f7440a;
    }

    public void m9817a(int i) {
        this.f7441b = i;
    }

    public int m9824b() {
        return this.f7441b;
    }

    public void m9822a(String str) {
        this.f7440a = str;
    }

    public int m9828c() {
        return this.f7442c;
    }

    public void m9825b(int i) {
        this.f7442c = i;
    }

    public long m9830d() {
        return this.f7443d;
    }

    public void m9818a(long j) {
        this.f7443d = j;
    }

    public String m9832e() {
        return this.f7444e;
    }

    public void m9826b(String str) {
        this.f7444e = str;
    }

    public Map<String, String> m9834f() {
        return this.f7446g;
    }

    public void m9823a(Map<String, String> map) {
        this.f7446g = map;
    }

    public C3038b m9835g() {
        return this.f7449j;
    }

    public void m9820a(C3038b c3038b) {
        this.f7449j = c3038b;
    }

    public C3169d m9836h() {
        return this.f7451l;
    }

    public void m9821a(C3169d c3169d) {
        this.f7451l = c3169d;
    }

    public String m9837i() {
        return this.f7447h;
    }

    public void m9829c(String str) {
        this.f7447h = str;
    }

    public String m9838j() {
        return this.f7448i;
    }

    public void m9831d(String str) {
        this.f7448i = str;
    }

    public Map<String, String> m9839k() {
        return this.f7450k;
    }

    public void m9827b(Map<String, String> map) {
        this.f7450k = map;
    }

    public String m9840l() {
        return this.f7445f;
    }

    public void m9833e(String str) {
        this.f7445f = str;
    }

    public String m9841m() {
        if (this.f7450k != null) {
            return (String) this.f7450k.get("preload-request");
        }
        return SchemaSymbols.ATTVAL_FALSE_0;
    }

    public MonetizationContext m9842n() {
        return this.f7452m;
    }

    public void m9819a(MonetizationContext monetizationContext) {
        this.f7452m = monetizationContext;
    }
}
