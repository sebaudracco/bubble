package com.fyber.requesters.p097a.p098a;

import com.fyber.requesters.p097a.C2579k;
import com.fyber.utils.FyberLogger;

/* compiled from: CacheDisablerCacheValidator */
public final class C2601d implements C2595e {
    public final boolean mo3995a(C2602f c2602f, C2579k c2579k) {
        if (((Boolean) c2579k.mo3970a("DISABLE_CACHE")) == null) {
            return true;
        }
        FyberLogger.m8448d("CacheDisablerCacheValidator", "Cache disabled - the cache will not be used");
        return false;
    }
}
