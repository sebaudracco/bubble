package com.bgjd.ici.p025b;

import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p027g.C1415b;
import com.bgjd.ici.p027g.C1494a;
import com.bgjd.ici.p027g.C1495c;
import com.bgjd.ici.p027g.C1497d;
import com.bgjd.ici.p027g.C1505f;
import java.net.URI;

public class C1430x implements C1400e {
    private C1397l f2216a = null;
    private C1395h f2217b = null;
    private C1387g f2218c = null;

    public C1430x(C1387g c1387g) {
        this.f2218c = c1387g;
    }

    public void mo2316a(String str) {
        try {
            this.f2217b.mo2203a(new JSONObject(str));
            if (this.f2218c != null) {
                this.f2218c.mo2168a(1, "Successfully Executed.");
            }
        } catch (Throwable e) {
            this.f2217b.mo2209a(e.getMessage(), e);
            if (this.f2218c != null) {
                this.f2218c.mo2168a(2, e.getMessage());
            }
        }
    }

    public void mo2317b(String str) {
        this.f2217b.mo2204a(str);
        if (this.f2218c != null) {
            this.f2218c.mo2168a(2, str);
        }
    }

    public String mo2314a() {
        return this.f2216a.mo2296a().toString();
    }

    public void mo2315a(C1397l c1397l) {
        this.f2216a = c1397l;
        this.f2217b = c1397l.m2854c();
    }

    public C1494a mo2312a(C1415b c1415b) {
        String w = this.f2217b.mo2292w();
        String scheme = URI.create(this.f2217b.mo2292w()).getScheme();
        int i = -1;
        switch (scheme.hashCode()) {
            case 118039:
                if (scheme.equals("wss")) {
                    i = 2;
                    break;
                }
                break;
            case 3213448:
                if (scheme.equals("http")) {
                    i = 0;
                    break;
                }
                break;
            case 99617003:
                if (scheme.equals("https")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return new C1495c(URI.create(String.format("%s/api/v1/%s", new Object[]{w, this.f2216a.mo2297b()})), c1415b);
            case 1:
                return new C1497d(URI.create(String.format("%s/api/v1/%s", new Object[]{w, this.f2216a.mo2297b()})), c1415b);
            case 2:
                return new C1505f(URI.create(String.format("%s/%s", new Object[]{w, this.f2216a.mo2297b()})), c1415b);
            default:
                return new C1505f(URI.create(String.format("%s/%s", new Object[]{w, this.f2216a.mo2297b()})), c1415b);
        }
    }

    public C1494a mo2313a(String str, C1415b c1415b) {
        String host = URI.create(this.f2217b.mo2292w()).getHost();
        int i = -1;
        switch (str.hashCode()) {
            case 118039:
                if (str.equals("wss")) {
                    i = 2;
                    break;
                }
                break;
            case 3213448:
                if (str.equals("http")) {
                    i = 0;
                    break;
                }
                break;
            case 99617003:
                if (str.equals("https")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return new C1495c(URI.create(String.format("http://%s/api/v1/%s", new Object[]{host, this.f2216a.mo2297b()})), c1415b);
            case 1:
                return new C1497d(URI.create(String.format("https://%s/api/v1/%s", new Object[]{host, this.f2216a.mo2297b()})), c1415b);
            case 2:
                return new C1505f(URI.create(String.format("wss://%s/%s", new Object[]{host, this.f2216a.mo2297b()})), c1415b);
            default:
                return new C1505f(URI.create(String.format("ws://%s/%s", new Object[]{host, this.f2216a.mo2297b()})), c1415b);
        }
    }
}
