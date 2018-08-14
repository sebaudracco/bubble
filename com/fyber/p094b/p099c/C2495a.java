package com.fyber.p094b.p099c;

import android.support.annotation.NonNull;
import com.fyber.ads.internal.C2423a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2479c.C2476a;
import com.fyber.p094b.C2484h;
import com.fyber.p094b.C2484h.C2482a;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: InterstitialEventNetworkOperation */
public final class C2495a extends C2484h {

    /* compiled from: InterstitialEventNetworkOperation */
    public static class C2494a extends C2482a<C2495a, C2494a> {
        protected final /* bridge */ /* synthetic */ C2476a mo3897b() {
            return this;
        }

        public C2494a(@NonNull C2423a c2423a) {
            super(c2423a, "interstitial_tracking");
        }

        @NonNull
        protected final String mo3903d() {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }

        @NonNull
        protected final String mo3904e() {
            return "interstitial";
        }

        protected final /* synthetic */ C2479c mo3896a() {
            return new C2495a();
        }
    }

    private C2495a(C2494a c2494a) {
        super(c2494a);
    }

    protected final String b_() {
        return "InterstitialEventNetworkOperation";
    }
}
