package com.bgjd.ici.plugin;

import android.content.Context;
import android.content.Intent;
import com.bgjd.ici.MarketBeaconService;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p030e.C1482g;
import com.bgjd.ici.p030e.C1485h;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.bgjd.ici.p030e.C1485h.C1484a.C1483a;
import com.bgjd.ici.p031f.C1492e;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class C1524f extends C1523g {
    private static final String f2508i = "PLGDWLR";

    class C15221 implements InvocationHandler {
        final /* synthetic */ C1524f f2498a;

        C15221(C1524f c1524f) {
            this.f2498a = c1524f;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.getName().contains("onVirtualBeaconsResponse")) {
                C1434d c1435a = new C1435a(new C1410m(this.f2498a.b));
                c1435a.open();
                if (c1435a.IsConnected()) {
                    ((C1492e) c1435a.getMapper(C1492e.class, C1482g.class)).m3187a(new C1482g(3, this.f2498a.c, objArr[0] != null ? objArr[0].toString() : ""));
                }
                c1435a.close();
                String str = C1524f.f2508i;
                String[] strArr = new String[1];
                strArr[0] = method.getName() + " : " + (objArr[0] != null ? objArr[0].toString() : "null");
                C1402i.m2901b(str, strArr);
            }
            return null;
        }
    }

    public C1524f(String str, C1485h c1485h, C1395h c1395h) {
        super(str, c1485h, c1395h);
    }

    public boolean mo2346a() {
        try {
            if (this.g.mo2360b(this.c)) {
                Object a = this.g.mo2357a(this.c);
                a.getClass().getMethod(C1483a.m3106b(), new Class[0]).invoke(a, new Object[0]);
                return true;
            }
        } catch (Throwable e) {
            this.g.mo2362d(this.c);
            C1402i.m2898a(f2508i, e, e.getMessage());
        } catch (Throwable e2) {
            this.g.mo2362d(this.c);
            C1402i.m2898a(f2508i, e2, e2.getMessage());
        } catch (Throwable e22) {
            this.g.mo2362d(this.c);
            C1402i.m2898a(f2508i, e22, e22.getMessage());
        } catch (Throwable e222) {
            this.g.mo2362d(this.c);
            C1402i.m2898a(f2508i, e222, e222.getMessage());
        }
        return false;
    }

    public void mo2347b() {
        if (this.g.mo2360b(this.c)) {
            Object a = this.g.mo2357a(this.c);
            try {
                a.getClass().getMethod(C1483a.m3107c(), new Class[0]).invoke(a, new Object[0]);
                C1402i.m2901b(f2508i, this.c + " Stopped...");
            } catch (Throwable e) {
                C1402i.m2898a(f2508i, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2508i, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2508i, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2508i, e222, e222.getMessage());
            }
        }
    }

    public String mo2349d() {
        return null;
    }

    public void mo2350e() {
        if (this.g.mo2360b(this.c)) {
            Object a = this.g.mo2357a(this.c);
            Class cls = a.getClass();
            try {
                cls.getMethod(C1483a.m3105a(), new Class[]{Context.class, Intent.class}).invoke(a, new Object[]{this.b.getContext(), this.b.mo2177E()});
            } catch (Throwable e) {
                C1402i.m2898a(f2508i, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2508i, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2508i, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2508i, e222, e222.getMessage());
            }
        }
    }

    public boolean mo2351f() {
        if (this.a != null && m3287i()) {
            this.d = this.a.m3136f();
            this.c = this.a.m3127b();
            try {
                if (!this.g.mo2360b(this.c)) {
                    Class loadClass = this.h.loadClass(C1484a.m3121a(this.c));
                    Object newInstance = loadClass.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.b.getContext()});
                    loadClass.getMethod(C1483a.m3108d(), new Class[]{String.class}).invoke(newInstance, new Object[]{C1484a.m3122b()});
                    loadClass.getMethod(C1483a.m3109e(), new Class[]{String.class}).invoke(newInstance, new Object[]{C1484a.m3123c()});
                    loadClass.getMethod(C1483a.m3110f(), new Class[]{String.class}).invoke(newInstance, new Object[]{C1403a.f2072b});
                    Object newProxyInstance = Proxy.newProxyInstance(MarketBeaconService.f2015a.loadClass(C1484a.m3120a()).getClassLoader(), new Class[]{r4}, new C15221(this));
                    loadClass.getMethod(C1483a.m3116l(), new Class[]{r4}).invoke(newInstance, new Object[]{newProxyInstance});
                    if (((Boolean) loadClass.getMethod(C1483a.m3111g(), new Class[0]).invoke(newInstance, new Object[0])).booleanValue()) {
                        if (!this.g.mo2360b(this.c)) {
                            this.g.mo2359a(this.c, newInstance);
                        }
                        return true;
                    }
                }
            } catch (Throwable e) {
                C1402i.m2898a(f2508i, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2508i, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2508i, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2508i, e222, e222.getMessage());
            } catch (Throwable e2222) {
                C1402i.m2898a(f2508i, e2222, e2222.getMessage());
            } catch (Throwable e22222) {
                C1402i.m2898a(f2508i, e22222, e22222.getMessage());
            }
        }
        return false;
    }

    public void mo2348c() {
        mo2347b();
        mo2346a();
    }

    public boolean mo2353h() {
        if (this.g.mo2360b(this.c)) {
            Object a = this.g.mo2357a(this.c);
            try {
                boolean booleanValue = ((Boolean) a.getClass().getMethod(C1483a.m3119o(), new Class[0]).invoke(a, new Object[0])).booleanValue();
                C1402i.m2901b(f2508i, this.c + " isRunning : " + booleanValue);
                return booleanValue;
            } catch (Throwable e) {
                C1402i.m2898a(f2508i, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2508i, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2508i, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2508i, e222, e222.getMessage());
            }
        }
        return false;
    }

    public boolean mo2352g() {
        if (this.g.mo2360b(this.c)) {
            Object a = this.g.mo2357a(this.c);
            try {
                boolean booleanValue = ((Boolean) a.getClass().getMethod(C1483a.m3111g(), new Class[0]).invoke(a, new Object[0])).booleanValue();
                C1402i.m2901b(f2508i, this.c + " is dependency valid : " + booleanValue);
                return booleanValue;
            } catch (Throwable e) {
                C1402i.m2898a(f2508i, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2508i, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2508i, e22, e22.getMessage());
            } catch (Throwable e222) {
                C1402i.m2898a(f2508i, e222, e222.getMessage());
            }
        }
        return false;
    }
}
