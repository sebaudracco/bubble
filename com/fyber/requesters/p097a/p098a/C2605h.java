package com.fyber.requesters.p097a.p098a;

import android.net.Uri;
import com.fyber.ads.AdFormat;
import com.fyber.ads.C2410a;
import com.fyber.requesters.p097a.C2579k;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;

/* compiled from: CustomPubParamsCacheValidator */
public final class C2605h implements C2595e<C2623c> {
    public final /* synthetic */ boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        C2623c c2623c = (C2623c) c2579k;
        if (!(c2602f.m8341a() instanceof C2410a)) {
            return true;
        }
        int i;
        if (((AdFormat) ((C2410a) c2602f.m8341a()).m7623f().mo3971a("AD_FORMAT", AdFormat.class, AdFormat.UNKNOWN)) == AdFormat.REWARDED_VIDEO) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0) {
            return true;
        }
        Uri parse = Uri.parse(c2623c.m8419e().m8435a());
        Uri parse2 = Uri.parse(((C2623c) c2602f.m8344c()).m8419e().m8435a());
        FyberLogger.m8448d("CustomPubParamsCacheValidator", "Checking custom pub parameters");
        i = 0;
        while (i <= 9) {
            String str = "pub" + i;
            if (StringUtils.equals(parse.getQueryParameter(str), parse2.getQueryParameter(str))) {
                i++;
            } else {
                FyberLogger.m8448d("CustomPubParamsCacheValidator", String.format("Custom pub param %s does not match - cached value = %s, current value = %s", new Object[]{str, parse2.getQueryParameter(str), parse.getQueryParameter(str)}));
                return false;
            }
        }
        FyberLogger.m8448d("CustomPubParamsCacheValidator", "Custom pub parameters match, proceeding");
        return true;
    }
}
