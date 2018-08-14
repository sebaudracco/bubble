package com.fyber.requesters.p097a;

import com.fyber.utils.StringUtils;
import java.util.UUID;

/* compiled from: DefaultCustomizer */
public final class C2624e implements C2620d {
    public final void mo4002a(C2623c c2623c, C2634m c2634m) {
        String b = c2623c.m8413b();
        if (StringUtils.nullOrEmpty(b)) {
            b = UUID.randomUUID().toString();
            c2623c.m8417d(b);
        }
        c2634m.m8437c().m8545c(c2623c.f6527a).m8541a(true).m8543b(b).m8540a(c2623c.m8421g());
    }
}
