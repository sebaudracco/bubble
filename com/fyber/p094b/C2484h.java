package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.Offer;
import com.fyber.p094b.C2479c.C2476a;
import com.fyber.utils.C2657h;
import com.fyber.utils.FyberLogger;
import java.io.IOException;
import java.util.Locale;

/* compiled from: OfferEventNetworkOperation */
public abstract class C2484h extends C2479c {

    /* compiled from: OfferEventNetworkOperation */
    public static abstract class C2482a<T extends C2479c, U extends C2482a> extends C2476a<T, U> {
        protected String f6219e = "";

        protected abstract String mo3903d();

        protected abstract String mo3904e();

        protected C2482a(@NonNull C2423a c2423a, String str) {
            super(c2423a.toString(), str);
            this.b.m8539a("ad_format", mo3904e()).m8539a("rewarded", mo3903d());
        }

        public final T m7891b(String str) {
            this.b.m8543b(str);
            this.c.append(String.format(Locale.ENGLISH, " with request_id=%s", new Object[]{str}));
            return mo3898c();
        }

        public final T m7890a(Offer offer) {
            if (offer != null) {
                this.b.m8543b(offer.getRequestId()).m8545c(offer.getPlacementId()).m8539a("ad_id", offer.getAdId()).m8539a("provider_type", offer.getProviderType()).m8540a(offer.getProviderRequest().m8236c());
                this.c.append(String.format(Locale.ENGLISH, " for ad_id=%s and provider_type=%s ", new Object[]{offer.getAdId(), offer.getProviderType()})).append(this.f6219e);
            }
            return mo3898c();
        }
    }

    protected final /* synthetic */ Object mo3899a(C2657h c2657h) throws Exception {
        return mo3906b(c2657h);
    }

    protected final /* synthetic */ Object mo3901b(IOException iOException) {
        return mo3905a(iOException);
    }

    protected C2484h(C2482a c2482a) {
        super(c2482a);
    }

    protected final boolean a_() {
        FyberLogger.m8448d(b_(), this.a);
        return true;
    }

    public final void mo3907b() {
        if (Fyber.getConfigs().m7607h()) {
            Fyber.getConfigs().m7600a((Runnable) this);
        } else {
            FyberLogger.m8448d(b_(), "It appears that Fyber SDK has not been started yet.");
        }
    }

    protected final Void mo3905a(IOException iOException) {
        FyberLogger.m8449e(b_(), "An exception occurred when trying to send the tracking event: " + iOException);
        return null;
    }

    protected final Void mo3906b(C2657h c2657h) throws IOException {
        FyberLogger.m8448d(b_(), "Event communication successful - " + (c2657h.m8464b() == 200));
        return null;
    }
}
