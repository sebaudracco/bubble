package com.altbeacon.beacon;

import android.content.Context;
import android.os.Messenger;
import com.altbeacon.beacon.p013c.C0835d;
import com.altbeacon.beacon.p014d.C0839a;
import com.altbeacon.beacon.service.C0874k;
import com.altbeacon.beacon.service.StartRMData;
import com.altbeacon.beacon.service.p009a.C0859f;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class C0829b {
    protected static volatile C0829b f1630a = null;
    protected static String f1631e = "http://data.altbeacon.org/android-distance.json";
    protected static Class f1632f = C0874k.class;
    private static boolean f1633n = false;
    private static final Object f1634o = new Object();
    private static long f1635p = 10000;
    protected final Set<C0844f> f1636b = new CopyOnWriteArraySet();
    protected C0844f f1637c = null;
    protected final Set<C0843e> f1638d = new CopyOnWriteArraySet();
    private final Context f1639g;
    private Messenger f1640h = null;
    private final List<C0819c> f1641i = new CopyOnWriteArrayList();
    private C0859f f1642j;
    private boolean f1643k = false;
    private boolean f1644l = false;
    private Boolean f1645m = null;
    private long f1646q = 1100;
    private long f1647r = 0;
    private long f1648s = 10000;
    private long f1649t = 300000;

    protected C0829b(Context context) {
        this.f1639g = context.getApplicationContext();
        m1630c();
        this.f1641i.add(new C0820a());
    }

    public static long m1618a() {
        return f1635p;
    }

    public static C0829b m1619a(Context context) {
        C0829b c0829b = f1630a;
        if (c0829b == null) {
            synchronized (f1634o) {
                c0829b = f1630a;
                if (c0829b == null) {
                    c0829b = new C0829b(context);
                    f1630a = c0829b;
                }
            }
        }
        return c0829b;
    }

    public static String m1620i() {
        return f1631e;
    }

    public static Class m1621j() {
        return f1632f;
    }

    public static boolean m1622m() {
        return f1633n;
    }

    private String m1623n() {
        C0835d.m1657a("BeaconManager", "callback packageName: %s", this.f1639g.getPackageName());
        return this.f1639g.getPackageName();
    }

    private long m1624o() {
        return this.f1643k ? this.f1648s : this.f1646q;
    }

    private long m1625p() {
        return this.f1643k ? this.f1649t : this.f1647r;
    }

    public StartRMData m1626a(Region region) {
        return new StartRMData(region, m1623n(), m1624o(), m1625p(), this.f1643k);
    }

    public void m1627a(C0844f c0844f) {
        if (c0844f != null) {
            this.f1636b.add(c0844f);
        }
    }

    public void m1628a(boolean z) {
        this.f1645m = Boolean.valueOf(z);
    }

    public boolean m1629b() {
        return this.f1644l;
    }

    protected void m1630c() {
        C0839a c0839a = new C0839a(this.f1639g);
        String a = c0839a.m1677a();
        String b = c0839a.m1678b();
        int c = c0839a.m1679c();
        this.f1644l = c0839a.m1680d();
        C0835d.m1660b("BeaconManager", "BeaconManager started up on pid " + c + " named '" + a + "' for application package '" + b + "'.  isMainProcess=" + this.f1644l, new Object[0]);
    }

    public List<C0819c> m1631d() {
        return this.f1641i;
    }

    public boolean m1632e() {
        return this.f1640h != null;
    }

    public void m1633f() {
        this.f1636b.clear();
    }

    public Set<C0843e> m1634g() {
        return Collections.unmodifiableSet(this.f1638d);
    }

    public Set<C0844f> m1635h() {
        return Collections.unmodifiableSet(this.f1636b);
    }

    protected C0844f m1636k() {
        return this.f1637c;
    }

    public C0859f m1637l() {
        return this.f1642j;
    }
}
