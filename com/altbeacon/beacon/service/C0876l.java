package com.altbeacon.beacon.service;

import com.altbeacon.beacon.Beacon;
import com.altbeacon.beacon.p013c.C0835d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class C0876l {
    private static final C0876l f1765a = new C0876l();
    private static final SimpleDateFormat f1766b = new SimpleDateFormat("HH:mm:ss.SSS");
    private ArrayList<C0875a> f1767c;
    private long f1768d = 0;
    private boolean f1769e;
    private boolean f1770f;
    private boolean f1771g;
    private C0875a f1772h;

    public static class C0875a {
        public long f1759a = 0;
        public long f1760b;
        public Date f1761c;
        public Date f1762d;
        public Date f1763e;
        public Date f1764f;
    }

    private C0876l() {
        m1815d();
    }

    public static C0876l m1807a() {
        return f1765a;
    }

    private String m1808a(Date date) {
        String str = "";
        if (date != null) {
            synchronized (f1766b) {
                str = f1766b.format(date);
            }
        }
        return str;
    }

    private void m1809a(C0875a c0875a, boolean z) {
        if (z) {
            C0835d.m1657a("Stats", "sample start time, sample stop time, first detection time, last detection time, max millis between detections, detection count", new Object[0]);
        }
        C0835d.m1657a("Stats", "%s, %s, %s, %s, %s, %s", m1808a(c0875a.f1763e), m1808a(c0875a.f1764f), m1808a(c0875a.f1761c), m1808a(c0875a.f1762d), Long.valueOf(c0875a.f1760b), Long.valueOf(c0875a.f1759a));
    }

    private void m1810e() {
        C0835d.m1657a("Stats", "--- Stats for %s samples", Integer.valueOf(this.f1767c.size()));
        Iterator it = this.f1767c.iterator();
        boolean z = true;
        while (it.hasNext()) {
            m1809a((C0875a) it.next(), z);
            z = false;
        }
    }

    private void m1811f() {
        if (this.f1772h == null || (this.f1768d > 0 && new Date().getTime() - this.f1772h.f1763e.getTime() >= this.f1768d)) {
            m1814c();
        }
    }

    public void m1812a(Beacon beacon) {
        m1811f();
        C0875a c0875a = this.f1772h;
        c0875a.f1759a++;
        if (this.f1772h.f1761c == null) {
            this.f1772h.f1761c = new Date();
        }
        if (this.f1772h.f1762d != null) {
            long time = new Date().getTime() - this.f1772h.f1762d.getTime();
            if (time > this.f1772h.f1760b) {
                this.f1772h.f1760b = time;
            }
        }
        this.f1772h.f1762d = new Date();
    }

    public boolean m1813b() {
        return this.f1771g;
    }

    public void m1814c() {
        Date date = new Date();
        if (this.f1772h != null) {
            date = new Date(this.f1772h.f1763e.getTime() + this.f1768d);
            this.f1772h.f1764f = date;
            if (!this.f1770f && this.f1769e) {
                m1809a(this.f1772h, true);
            }
        }
        this.f1772h = new C0875a();
        this.f1772h.f1763e = date;
        this.f1767c.add(this.f1772h);
        if (this.f1770f) {
            m1810e();
        }
    }

    public void m1815d() {
        this.f1767c = new ArrayList();
        m1814c();
    }
}
