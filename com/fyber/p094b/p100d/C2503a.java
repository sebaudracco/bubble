package com.fyber.p094b.p100d;

import android.support.annotation.NonNull;
import com.fyber.ads.internal.C2423a;
import com.fyber.p094b.C2479c;
import com.fyber.p094b.C2479c.C2476a;
import com.fyber.p094b.C2484h;
import com.fyber.p094b.C2484h.C2482a;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: VideoEventNetworkOperation */
public final class C2503a extends C2484h {

    /* compiled from: VideoEventNetworkOperation */
    public static class C2502a extends C2482a<C2503a, C2502a> {
        protected final /* bridge */ /* synthetic */ C2476a mo3897b() {
            return this;
        }

        public C2502a(@NonNull C2423a c2423a) {
            super(c2423a, "rewarded_video_tracking");
        }

        @NonNull
        protected final String mo3903d() {
            return SchemaSymbols.ATTVAL_TRUE_1;
        }

        @NonNull
        protected final String mo3904e() {
            return "video";
        }

        protected final /* synthetic */ C2479c mo3896a() {
            return new C2503a();
        }
    }

    private C2503a(C2502a c2502a) {
        super(c2502a);
    }

    protected final String b_() {
        return "VideoEventNetworkOperation";
    }
}
