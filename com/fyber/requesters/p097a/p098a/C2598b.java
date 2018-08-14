package com.fyber.requesters.p097a.p098a;

import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.NetworkBannerSize;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: BannerSizeValidator */
public final class C2598b implements C2595e, C2596p {
    public final boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        if (C2598b.m8329a(c2579k)) {
            return C2598b.m8331b(c2602f.m8344c(), c2579k);
        }
        return true;
    }

    public final boolean mo3996a(C2579k c2579k, C2579k c2579k2) {
        if (C2598b.m8329a(c2579k2) && !C2598b.m8331b(c2579k, c2579k2)) {
            return true;
        }
        return false;
    }

    private static boolean m8329a(C2579k c2579k) {
        return ((AdFormat) c2579k.mo3971a("AD_FORMAT", AdFormat.class, AdFormat.UNKNOWN)) == AdFormat.BANNER;
    }

    private static boolean m8331b(C2579k c2579k, C2579k c2579k2) {
        FyberLogger.m8448d("BannerSizeValidator", "Checking banner sizes...");
        Collection b = C2598b.m8330b(c2579k);
        List b2 = C2598b.m8330b(c2579k2);
        if (b.size() != b2.size()) {
            FyberLogger.m8448d("BannerSizeValidator", "The amount of sizes don't match for both requests - invalid");
            return false;
        }
        boolean containsAll = b2.containsAll(b);
        String str = "BannerSizeValidator";
        Locale locale = Locale.ENGLISH;
        String str2 = "Banner sizes %smatch for both requests - %s";
        Object[] objArr = new Object[2];
        objArr[0] = containsAll ? "" : "don't ";
        objArr[1] = containsAll ? "valid. Proceeding..." : "invalid";
        FyberLogger.m8448d(str, String.format(locale, str2, objArr));
        return containsAll;
    }

    private static List<NetworkBannerSize> m8330b(C2579k c2579k) {
        List<NetworkBannerSize> list = (List) c2579k.mo3970a("BANNER_SIZES");
        if (list == null) {
            return Collections.emptyList();
        }
        return list;
    }
}
