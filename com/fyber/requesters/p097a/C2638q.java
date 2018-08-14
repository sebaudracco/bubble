package com.fyber.requesters.p097a;

import com.fyber.utils.StringUtils;

/* compiled from: VirtualCurrencyCustomizer */
public final class C2638q implements C2620d {
    public final void mo4002a(C2623c c2623c, C2634m c2634m) {
        String str = (String) c2623c.mo3970a("CURRENCY_ID");
        c2634m.m8437c().m8539a("ltid", (String) c2623c.mo3970a("TRANSACTION_ID"));
        if (StringUtils.notNullNorEmpty(str)) {
            c2634m.m8437c().m8539a("currency_id", str);
        }
    }
}
