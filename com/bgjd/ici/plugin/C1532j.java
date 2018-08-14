package com.bgjd.ici.plugin;

import com.bgjd.ici.p025b.C1402i;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class C1532j implements C1520d {
    private static final String f2529a = "MKTPLGSET";
    private static C1532j f2530b = null;
    private static Map<String, Object> f2531c = null;

    private C1532j() {
        if (f2531c == null) {
            f2531c = new ConcurrentHashMap();
        }
    }

    public static C1532j m3310b() {
        if (f2530b == null) {
            f2530b = new C1532j();
        }
        return f2530b;
    }

    public void mo2359a(String str, Object obj) {
        f2531c.put(str, obj);
    }

    public void mo2362d(String str) {
        if (f2531c.containsKey(str)) {
            f2531c.remove(str);
        }
    }

    public Object mo2357a(String str) {
        if (f2531c.containsKey(str)) {
            return f2531c.get(str);
        }
        return null;
    }

    public boolean mo2360b(String str) {
        return f2531c.containsKey(str);
    }

    public ClassLoader mo2361c(String str) {
        try {
            return (ClassLoader) f2531c.get(str).getClass().getMethod("getLoader", new Class[0]).invoke(f2531c.get(str), new Object[0]);
        } catch (Throwable e) {
            C1402i.m2898a(f2529a, e, e.getMessage());
        } catch (Throwable e2) {
            C1402i.m2898a(f2529a, e2, e2.getMessage());
        } catch (Throwable e22) {
            C1402i.m2898a(f2529a, e22, e22.getMessage());
        } catch (Throwable e222) {
            C1402i.m2898a(f2529a, e222, e222.getMessage());
        }
        return null;
    }

    public Class<?> mo2356a(String str, String str2) {
        try {
            return mo2361c(str).loadClass(str2);
        } catch (Throwable e) {
            C1402i.m2898a(f2529a, e, e.getMessage());
            return null;
        } catch (Throwable e2) {
            C1402i.m2898a(f2529a, e2, e2.getMessage());
            return null;
        }
    }

    public Map<String, Object> mo2358a() {
        return f2531c;
    }
}
