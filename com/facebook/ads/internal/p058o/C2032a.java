package com.facebook.ads.internal.p058o;

import com.facebook.ads.internal.protocol.C2100c;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class C2032a {
    private static Map<String, Long> f4828a = new ConcurrentHashMap();
    private static Map<String, Long> f4829b = new ConcurrentHashMap();
    private static Map<String, String> f4830c = new ConcurrentHashMap();

    private static long m6502a(String str, C2100c c2100c) {
        if (f4828a.containsKey(str)) {
            return ((Long) f4828a.get(str)).longValue();
        }
        switch (c2100c) {
            case BANNER:
                return 15000;
            case INTERSTITIAL:
            case NATIVE:
                return -1000;
            default:
                return -1000;
        }
    }

    public static void m6503a(long j, C2033b c2033b) {
        f4828a.put(C2032a.m6508d(c2033b), Long.valueOf(j));
    }

    public static void m6504a(String str, C2033b c2033b) {
        f4830c.put(C2032a.m6508d(c2033b), str);
    }

    public static boolean m6505a(C2033b c2033b) {
        String d = C2032a.m6508d(c2033b);
        if (!f4829b.containsKey(d)) {
            return false;
        }
        return System.currentTimeMillis() - ((Long) f4829b.get(d)).longValue() < C2032a.m6502a(d, c2033b.m6511b());
    }

    public static void m6506b(C2033b c2033b) {
        f4829b.put(C2032a.m6508d(c2033b), Long.valueOf(System.currentTimeMillis()));
    }

    public static String m6507c(C2033b c2033b) {
        return (String) f4830c.get(C2032a.m6508d(c2033b));
    }

    private static String m6508d(C2033b c2033b) {
        int i = 0;
        String str = "%s:%s:%s:%d:%d:%d";
        Object[] objArr = new Object[6];
        objArr[0] = c2033b.m6510a();
        objArr[1] = c2033b.m6511b();
        objArr[2] = c2033b.f4833c;
        objArr[3] = Integer.valueOf(c2033b.m6512c() == null ? 0 : c2033b.m6512c().m6801a());
        if (c2033b.m6512c() != null) {
            i = c2033b.m6512c().m6802b();
        }
        objArr[4] = Integer.valueOf(i);
        objArr[5] = Integer.valueOf(c2033b.m6513d());
        return String.format(str, objArr);
    }
}
