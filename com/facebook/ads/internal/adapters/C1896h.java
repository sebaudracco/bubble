package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.ArrayList;
import java.util.List;

public enum C1896h {
    ANBANNER(C1904k.class, C1895g.AN, AdPlacementType.BANNER),
    ANINTERSTITIAL(C1920m.class, C1895g.AN, AdPlacementType.INTERSTITIAL),
    ADMOBNATIVE(C1892e.class, C1895g.ADMOB, AdPlacementType.NATIVE),
    ANNATIVE(C1926o.class, C1895g.AN, AdPlacementType.NATIVE),
    ANINSTREAMVIDEO(C1914l.class, C1895g.AN, AdPlacementType.INSTREAM),
    ANREWARDEDVIDEO(C1928p.class, C1895g.AN, AdPlacementType.REWARDED_VIDEO),
    INMOBINATIVE(C1935t.class, C1895g.INMOBI, AdPlacementType.NATIVE),
    YAHOONATIVE(C1930q.class, C1895g.YAHOO, AdPlacementType.NATIVE);
    
    private static List<C1896h> f4253m;
    public Class<?> f4255i;
    public String f4256j;
    public C1895g f4257k;
    public AdPlacementType f4258l;

    private C1896h(Class<?> cls, C1895g c1895g, AdPlacementType adPlacementType) {
        this.f4255i = cls;
        this.f4257k = c1895g;
        this.f4258l = adPlacementType;
    }

    public static List<C1896h> m5822a() {
        if (f4253m == null) {
            synchronized (C1896h.class) {
                f4253m = new ArrayList();
                f4253m.add(ANBANNER);
                f4253m.add(ANINTERSTITIAL);
                f4253m.add(ANNATIVE);
                f4253m.add(ANINSTREAMVIDEO);
                f4253m.add(ANREWARDEDVIDEO);
                if (C1941z.m6122a(C1895g.YAHOO)) {
                    f4253m.add(YAHOONATIVE);
                }
                if (C1941z.m6122a(C1895g.INMOBI)) {
                    f4253m.add(INMOBINATIVE);
                }
                if (C1941z.m6122a(C1895g.ADMOB)) {
                    f4253m.add(ADMOBNATIVE);
                }
            }
        }
        return f4253m;
    }
}
