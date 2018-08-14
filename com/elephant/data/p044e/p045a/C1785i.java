package com.elephant.data.p044e.p045a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.elephant.data.p035a.C1720a;
import com.elephant.data.p041c.C1733b;
import com.elephant.data.p041c.C1736e;
import com.elephant.data.p041c.C1738g;
import com.elephant.data.p041c.C1739h;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.C1821i;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public final class C1785i {
    private static C1785i f3746b;
    private static String f3747k = C1814b.eH;
    private static final String f3748l = C1814b.eI;
    private static final String f3749m = C1814b.eJ;
    private static final String f3750n = C1814b.eK;
    private static final String f3751o = C1814b.eL;
    public C1783g f3752a;
    private Context f3753c;
    private volatile boolean f3754d = true;
    private C1739h f3755e;
    private C1736e f3756f;
    private C1791o f3757g;
    private Object f3758h;
    private C1783g f3759i;
    private long f3760j = 0;
    private SharedPreferences f3761p;
    private BroadcastReceiver f3762q = new C1788l(this);

    static {
        String str = C1814b.eG;
    }

    private C1785i(Context context) {
        if (context == null) {
            throw new NullPointerException(C1814b.eM);
        }
        this.f3753c = context;
        f3746b = this;
        this.f3755e = new C1739h();
        this.f3756f = new C1736e(this.f3753c);
        this.f3757g = new C1791o();
        this.f3758h = new Object();
        this.f3761p = this.f3753c.getSharedPreferences(f3747k + this.f3753c.getPackageName(), 0);
        this.f3761p.edit();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f3748l);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f3753c.registerReceiver(this.f3762q, intentFilter);
    }

    static /* synthetic */ long m5136a(C1785i c1785i, long j) {
        return j;
    }

    public static synchronized C1785i m5137a(Context context) {
        C1785i c1785i;
        synchronized (C1785i.class) {
            if (f3746b == null && context != null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    f3746b = new C1785i(applicationContext);
                } else {
                    f3746b = new C1785i(context);
                }
            }
            c1785i = f3746b;
        }
        return c1785i;
    }

    private synchronized void m5140a(C1783g c1783g) {
        if (c1783g != null) {
            if (this.f3753c != null) {
                C1720a.m4970b(this.f3753c).m5127a(c1783g);
            }
        }
    }

    static /* synthetic */ void m5141a(C1785i c1785i, C1783g c1783g) {
        if (C1816d.m5326q(c1785i.f3753c) >= 0) {
            c1785i.f3755e.m5009a(c1783g);
        } else {
            c1785i.f3756f.m5007b(c1783g);
        }
    }

    private synchronized void m5144a(Runnable runnable) {
        Runnable c1733b = new C1733b();
        c1733b.m4992a(runnable);
        try {
            C1821i.m5346a(c1733b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void m5145a(boolean z, C1783g c1783g, boolean z2, C1738g c1738g) {
        if (!(this.f3759i == null || this.f3759i.f3744j == null || c1783g == null || c1783g.f3744j == null || !this.f3759i.f3744j.equals(c1783g.f3744j))) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f3760j > 5000) {
                this.f3760j = currentTimeMillis;
            }
        }
        this.f3759i = c1783g;
        this.f3756f.m5006a(c1783g, new C1787k(this, c1738g, c1783g, z, z2));
    }

    private synchronized boolean m5148a(HashSet hashSet) {
        boolean z;
        synchronized (this.f3758h) {
            LinkedList a = this.f3756f.m5004a(hashSet);
            if (a.isEmpty()) {
                a.addAll(this.f3756f.m5003a());
            }
            if (a.isEmpty()) {
                z = false;
            } else {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    C1783g c1783g = (C1783g) it.next();
                    if (C1816d.m5326q(this.f3753c) >= 0) {
                        this.f3755e.m5009a(c1783g);
                    }
                }
                z = true;
            }
        }
        return z;
    }

    private synchronized void m5151b(boolean z, boolean z2, String str, String str2) {
        try {
            m5144a(new C1786j(this, str2, str, z2, z));
        } catch (OutOfMemoryError e) {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private synchronized String m5158e() {
        return String.valueOf(Math.abs(System.nanoTime()));
    }

    private synchronized void m5159f() {
        boolean z = this.f3754d;
    }

    static /* synthetic */ void m5160f(C1785i c1785i) {
        if (c1785i.m5162g()) {
            c1785i.m5163a(true);
        }
    }

    private synchronized boolean m5162g() {
        boolean z;
        synchronized (this.f3758h) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f3760j > 5000) {
                this.f3760j = currentTimeMillis;
                LinkedList a = this.f3756f.m5003a();
                if (!a.isEmpty()) {
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        C1783g c1783g = (C1783g) it.next();
                        if (C1816d.m5326q(this.f3753c) >= 0) {
                            this.f3755e.m5009a(c1783g);
                        }
                    }
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public final synchronized void m5163a(boolean z) {
        try {
            if (this.f3753c != null) {
                if (C1816d.m5323n(this.f3753c)) {
                    if (this.f3754d) {
                        this.f3754d = false;
                        this.f3757g.m5166a();
                    }
                } else if (!z) {
                    C1783g a = this.f3755e.m5008a();
                    while (a != null) {
                        this.f3756f.m5007b(a);
                        a = this.f3755e.m5008a();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public final synchronized void m5164a(boolean z, boolean z2, String str, String str2) {
        if (str2 != null) {
            m5151b(z, z2, str, str2);
        }
    }
}
