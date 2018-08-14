package com.fyber.requesters.p097a.p098a;

import com.fyber.ads.AdFormat;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;

/* compiled from: InterstitialParamsValidator */
public final class C2606i implements C2595e<C2580a>, C2596p<C2580a> {
    public final /* synthetic */ boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        C2580a c2580a = (C2580a) c2579k;
        if (C2606i.m8359a(c2580a)) {
            return C2606i.m8360a((C2580a) c2602f.m8344c(), c2580a);
        }
        return true;
    }

    private static boolean m8360a(C2580a c2580a, C2580a c2580a2) {
        String str = (String) c2580a.mo3970a("creative_type");
        String str2 = (String) c2580a.mo3970a("tpn_placement_id");
        String str3 = (String) c2580a2.mo3970a("creative_type");
        String str4 = (String) c2580a2.mo3970a("tpn_placement_id");
        FyberLogger.m8448d("InterstitialParamsValidator", "Checking query parameter: creative_type");
        if (StringUtils.equals(str, str3)) {
            FyberLogger.m8448d("InterstitialParamsValidator", "Checking query parameter: tpn_placement_id");
            if (StringUtils.equals(str2, str4)) {
                FyberLogger.m8448d("InterstitialParamsValidator", "Query parameters match, proceeding");
                return true;
            }
            FyberLogger.m8448d("InterstitialParamsValidator", String.format("Query param %s does not match - cached value = %s, current value = %s", new Object[]{"tpn_placement_id", str2, str4}));
            return false;
        }
        FyberLogger.m8448d("InterstitialParamsValidator", String.format("Query param %s does not match - cached value = %s, current value = %s", new Object[]{"creative_type", str, str3}));
        return false;
    }

    private static boolean m8359a(C2580a c2580a) {
        return ((AdFormat) c2580a.mo3971a("AD_FORMAT", AdFormat.class, AdFormat.UNKNOWN)) == AdFormat.INTERSTITIAL;
    }
}
