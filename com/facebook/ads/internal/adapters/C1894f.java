package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.p056q.p057a.C2131t;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class C1894f {
    private static final Set<C1896h> f4237a = new HashSet();
    private static final Map<AdPlacementType, String> f4238b = new ConcurrentHashMap();

    static {
        for (C1896h c1896h : C1896h.m5822a()) {
            Class cls;
            switch (c1896h.f4258l) {
                case BANNER:
                    cls = BannerAdapter.class;
                    break;
                case INTERSTITIAL:
                    cls = InterstitialAdapter.class;
                    break;
                case NATIVE:
                    cls = ab.class;
                    break;
                case INSTREAM:
                    cls = C1913u.class;
                    break;
                case REWARDED_VIDEO:
                    cls = ae.class;
                    break;
                default:
                    cls = null;
                    break;
            }
            if (cls != null) {
                Class cls2 = c1896h.f4255i;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName(c1896h.f4256j);
                    } catch (ClassNotFoundException e) {
                    }
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    f4237a.add(c1896h);
                }
            }
        }
    }

    public static AdAdapter m5817a(C1895g c1895g, AdPlacementType adPlacementType) {
        try {
            C1896h b = C1894f.m5820b(c1895g, adPlacementType);
            if (b != null && f4237a.contains(b)) {
                Class cls = b.f4255i;
                if (cls == null) {
                    cls = Class.forName(b.f4256j);
                }
                return (AdAdapter) cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static AdAdapter m5818a(String str, AdPlacementType adPlacementType) {
        return C1894f.m5817a(C1895g.m5821a(str), adPlacementType);
    }

    public static String m5819a(AdPlacementType adPlacementType) {
        if (f4238b.containsKey(adPlacementType)) {
            return (String) f4238b.get(adPlacementType);
        }
        Set hashSet = new HashSet();
        for (C1896h c1896h : f4237a) {
            if (c1896h.f4258l == adPlacementType) {
                hashSet.add(c1896h.f4257k.toString());
            }
        }
        String a = C2131t.m6827a(hashSet, ",");
        f4238b.put(adPlacementType, a);
        return a;
    }

    private static C1896h m5820b(C1895g c1895g, AdPlacementType adPlacementType) {
        for (C1896h c1896h : f4237a) {
            if (c1896h.f4257k == c1895g && c1896h.f4258l == adPlacementType) {
                return c1896h;
            }
        }
        return null;
    }
}
