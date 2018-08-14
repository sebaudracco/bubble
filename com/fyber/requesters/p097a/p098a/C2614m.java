package com.fyber.requesters.p097a.p098a;

import android.net.Uri;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: QueryParamsCacheValidator */
public final class C2614m implements C2595e<C2623c> {
    private static final List<String> f6519a = Arrays.asList(new String[]{"placement_id", C1404b.f2148z, "google_ad_id", "google_ad_id_limited_tracking_enabled", "android_id"});
    private static final List<String> f6520b = Arrays.asList(new String[]{"timestamp", "request_id"});

    public final /* synthetic */ boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        Object emptyList;
        Uri parse = Uri.parse(((C2623c) c2579k).m8419e().m8435a());
        Uri parse2 = Uri.parse(((C2623c) c2602f.m8344c()).m8419e().m8435a());
        C2604g c2604g = (C2604g) c2602f.m8344c().mo3970a("CACHE_CONFIG");
        if (c2604g == null || c2604g.m8356c() == null || c2604g.m8356c().length <= 0) {
            emptyList = Collections.emptyList();
        } else {
            List arrayList = new ArrayList(Arrays.asList(c2604g.m8356c()));
            arrayList.removeAll(f6520b);
            emptyList = arrayList;
        }
        if (emptyList.isEmpty()) {
            emptyList = f6519a;
        }
        FyberLogger.m8448d("QueryParamsCacheValidator", "Checking query parameters: " + TextUtils.join(", ", r0));
        for (String str : r0) {
            if (!StringUtils.equals(parse.getQueryParameter(str), parse2.getQueryParameter(str))) {
                FyberLogger.m8448d("QueryParamsCacheValidator", String.format("Query param %s does not match - cached value = %s, current value = %s", new Object[]{str, parse2.getQueryParameter(str), parse.getQueryParameter(str)}));
                return false;
            }
        }
        FyberLogger.m8448d("QueryParamsCacheValidator", "Query parameters match, proceeding");
        return true;
    }
}
