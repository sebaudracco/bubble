package com.fyber.ads.videos.p093a;

import android.support.annotation.NonNull;
import com.fyber.ads.Ad;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.C2419b;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.Offer;
import com.fyber.ads.videos.C2472a;
import com.fyber.ads.videos.C2473b;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.p094b.p100d.C2503a.C2502a;
import com.fyber.requesters.p097a.C2623c;
import java.util.List;

/* compiled from: RewardedVideoAdRequestResponse */
public final class C2448b extends C2410a<C2472a> implements C2419b<C2473b> {
    private C2473b f6129e;

    public final /* bridge */ /* synthetic */ void mo3847a(Object obj) {
        this.f6129e = (C2473b) obj;
    }

    public C2448b(C2623c c2623c, List<Offer> list) {
        super(c2623c, list);
    }

    protected final C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3849b(@NonNull C2423a c2423a) {
        return new C2502a(c2423a);
    }

    protected final /* synthetic */ Ad mo3851h() {
        return new C2472a(m7621d(), this);
    }
}
