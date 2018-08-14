package com.bgjd.ici.plugin;

import android.content.Context;
import com.bgjd.ici.MarketBeaconService;
import com.bgjd.ici.p025b.C1394a;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p030e.C1482g;
import com.bgjd.ici.p030e.C1485h.C1484a;
import com.bgjd.ici.p030e.C1485h.C1484a.C1483a;
import com.bgjd.ici.p031f.C1492e;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class C1526h implements C1517a {
    private static final String f2510a = "FKLBST";
    private Class<?> f2511b = null;
    private Object f2512c;
    private C1395h f2513d;
    private String f2514e = "";

    class C15251 implements InvocationHandler {
        final /* synthetic */ C1526h f2509a;

        C15251(C1526h c1526h) {
            this.f2509a = c1526h;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            C1434d c1435a = new C1435a(new C1410m(this.f2509a.f2513d));
            c1435a.open();
            if (c1435a.IsConnected()) {
                int i;
                C1492e c1492e = (C1492e) c1435a.getMapper(C1492e.class, C1482g.class);
                if (method.getName().contains("onConfigRequest")) {
                    C1394a G = this.f2509a.f2513d.mo2179G();
                    if (G != null) {
                        G.mo2329a(this.f2509a.f2513d.getContext());
                    }
                    i = 1;
                } else if (method.getName().contains("onConfigResponse")) {
                    i = 2;
                } else {
                    i = 0;
                }
                if (i > 0) {
                    String str = C1526h.f2510a;
                    String[] strArr = new String[1];
                    strArr[0] = method.getName() + " : " + (objArr[0] != null ? objArr[0].toString() : "");
                    C1402i.m2901b(str, strArr);
                    c1492e.m3187a(new C1482g(i, this.f2509a.f2514e, objArr[0] != null ? objArr[0].toString() : ""));
                }
            }
            c1435a.close();
            return null;
        }
    }

    public C1526h(C1395h c1395h) {
        this.f2513d = c1395h;
    }

    public void mo2343a(String str) {
        this.f2514e = str;
    }

    public void mo2342a() {
        try {
            C1520d b = C1532j.m3310b();
            if (b.mo2360b(this.f2514e)) {
                Object a = b.mo2357a(this.f2514e);
                this.f2512c = a.getClass().getMethod(C1483a.m3115k(), new Class[]{Context.class}).invoke(a, new Object[]{this.f2513d.getContext()});
                this.f2511b = this.f2512c.getClass();
                Class loadClass = MarketBeaconService.f2015a.loadClass(C1484a.m3120a());
                Method method = this.f2511b.getMethod(C1483a.m3116l(), new Class[]{loadClass});
                a = Proxy.newProxyInstance(loadClass.getClassLoader(), new Class[]{loadClass}, new C15251(this));
                method.invoke(this.f2512c, new Object[]{a});
                this.f2511b.getMethod(C1483a.m3106b(), new Class[0]).invoke(this.f2512c, new Object[0]);
            }
        } catch (Throwable e) {
            C1402i.m2898a(f2510a, e, e.getMessage());
        } catch (Throwable e2) {
            C1402i.m2898a(f2510a, e2, e2.getMessage());
        } catch (Throwable e22) {
            C1402i.m2898a(f2510a, e22, e22.getMessage());
        } catch (Throwable e222) {
            C1402i.m2898a(f2510a, e222, e222.getMessage());
        } catch (Throwable e2222) {
            C1402i.m2898a(f2510a, e2222, e2222.getMessage());
        } catch (Throwable e22222) {
            C1402i.m2898a(f2510a, e22222, e22222.getMessage());
        }
    }

    public void mo2344b() {
        if (this.f2512c != null) {
            try {
                this.f2511b.getMethod(C1483a.m3117m(), new Class[0]).invoke(this.f2512c, new Object[0]);
            } catch (Throwable e) {
                C1402i.m2898a(f2510a, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2510a, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2510a, e22, e22.getMessage());
            }
        }
    }

    public void mo2345c() {
        if (this.f2512c != null) {
            try {
                this.f2511b.getMethod(C1483a.m3107c(), new Class[0]).invoke(this.f2512c, new Object[0]);
            } catch (Throwable e) {
                C1402i.m2898a(f2510a, e, e.getMessage());
            } catch (Throwable e2) {
                C1402i.m2898a(f2510a, e2, e2.getMessage());
            } catch (Throwable e22) {
                C1402i.m2898a(f2510a, e22, e22.getMessage());
            }
        }
    }
}
