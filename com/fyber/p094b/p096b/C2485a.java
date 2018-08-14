package com.fyber.p094b.p096b;

import android.support.annotation.NonNull;
import com.fyber.ads.internal.C2423a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2479c.C2476a;
import com.fyber.p094b.C2484h;
import com.fyber.p094b.C2484h.C2482a;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: BannerEventNetworkOperation */
public final class C2485a extends C2484h {

    /* compiled from: BannerEventNetworkOperation */
    public static class C2483a extends C2482a<C2485a, C2483a> {
        protected final /* bridge */ /* synthetic */ C2476a mo3897b() {
            return this;
        }

        public C2483a(@NonNull C2423a c2423a) {
            super(c2423a, "banner_tracking");
        }

        @NonNull
        protected final String mo3903d() {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }

        @NonNull
        protected final String mo3904e() {
            return "banner";
        }

        protected final /* synthetic */ C2479c mo3896a() {
            return new C2485a();
        }
    }

    private C2485a(C2483a c2483a) {
        super(c2483a);
    }

    protected final String b_() {
        return "BannerEventNetworkOperation";
    }
}
