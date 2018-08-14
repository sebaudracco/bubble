package com.fyber.p094b;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.ads.Ad;
import com.fyber.ads.C2410a;
import com.fyber.ads.internal.C2423a;
import com.fyber.ads.internal.C2425d;
import com.fyber.p094b.C2484h.C2482a;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.p097a.C2588f;
import com.fyber.requesters.p097a.C2623c;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

/* compiled from: AdFetchOperation */
public abstract class C2481a<V, A extends Ad<A, ?>, P extends C2410a<A>> implements Runnable {
    protected final C2588f<P, V> f6215a;
    protected final C2623c f6216b;
    protected WeakReference<Context> f6217c;
    protected boolean f6218d = false;

    /* compiled from: AdFetchOperation */
    public static abstract class C2475a<T extends C2481a, U extends C2475a> {
        protected C2588f f6204a;
        private C2623c f6205b;

        protected abstract U mo3908a();

        public final U m7859a(C2588f c2588f) {
            this.f6204a = c2588f;
            return mo3908a();
        }

        public final U m7858a(C2623c c2623c) {
            this.f6205b = c2623c;
            return mo3908a();
        }
    }

    protected abstract int mo3909a();

    @NonNull
    protected abstract C2505e<P> mo3910a(P p);

    protected abstract C2482a<? extends C2479c, ? extends C2482a<?, ?>> mo3911a(@NonNull C2423a c2423a);

    protected abstract Future<P> mo3912a(C2623c c2623c);

    protected abstract void mo3913a(C2425d c2425d);

    protected abstract void mo3914b();

    protected abstract void mo3915b(P p);

    protected C2481a(C2475a c2475a) {
        this.f6215a = c2475a.f6204a;
        this.f6216b = c2475a.f6205b;
    }

    public final void m7886a(Context context) {
        this.f6217c = new WeakReference(context);
        Fyber.getConfigs().m7600a((Runnable) this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r2 = 0;
        r0 = r10.f6216b;
        r3 = r0.m8413b();
        r0 = r10.f6218d;
        if (r0 == 0) goto L_0x0020;
    L_0x000b:
        r0 = com.fyber.mediation.C2573a.f6454a;
        r0 = r0.m8214a();
        if (r0 == 0) goto L_0x0020;
    L_0x0013:
        r1 = r0.isDone();
        if (r1 != 0) goto L_0x0020;
    L_0x0019:
        r4 = 60;
        r1 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x003f, ExecutionException -> 0x012f, TimeoutException -> 0x0132 }
        r0.get(r4, r1);	 Catch:{ InterruptedException -> 0x003f, ExecutionException -> 0x012f, TimeoutException -> 0x0132 }
    L_0x0020:
        r0 = r10.f6216b;
        r0 = r10.mo3912a(r0);
        r4 = r10.mo3909a();
        r6 = (long) r4;
        r1 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r0 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r0 = (com.fyber.ads.C2410a) r0;	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        if (r0 != 0) goto L_0x004a;
    L_0x0035:
        r0 = "Error when retrieving backend response";
        r1 = "server";
        r10.m7881a(r3, r0, r1);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
    L_0x003e:
        return;
    L_0x003f:
        r0 = move-exception;
    L_0x0040:
        r0 = "Adapter start timeout";
        r1 = "adapter_start_timeout";
        r10.m7881a(r3, r0, r1);
        goto L_0x003e;
    L_0x004a:
        r1 = r10.f6216b;	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r5 = "CACHE_CONFIG";
        r6 = r0.m7620c();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r1.m8402a(r5, r6);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r5 = r0.m7611a();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r1 = r0.m7619b();	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x00d7 }
        if (r1 == 0) goto L_0x0135;
    L_0x0060:
        r1 = r0.m7619b();	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x00d7 }
        r1 = r1.isEmpty();	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x00d7 }
        if (r1 != 0) goto L_0x0135;
    L_0x006a:
        r1 = r10.mo3910a(r0);	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x00d7 }
        r6 = (long) r5;
        r0 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x0126 }
        r0 = r1.get(r6, r0);	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x0126 }
        r0 = (com.fyber.ads.C2410a) r0;	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x0126 }
    L_0x0077:
        if (r0 == 0) goto L_0x00b9;
    L_0x0079:
        r6 = r0.m7624g();	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        if (r6 == 0) goto L_0x00b9;
    L_0x007f:
        r10.m7877a(r3, r0);	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        goto L_0x003e;
    L_0x0083:
        r0 = move-exception;
    L_0x0084:
        r1 = r0.getCause();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r1 = r1 instanceof com.fyber.exceptions.C2565a;	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        if (r1 == 0) goto L_0x00cb;
    L_0x008c:
        r1 = r0.getMessage();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r0 = r0.getCause();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r0 = (com.fyber.exceptions.C2565a) r0;	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r0 = r0.m8200a();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r10.m7881a(r3, r1, r0);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        goto L_0x003e;
    L_0x009e:
        r0 = move-exception;
    L_0x009f:
        r1 = r0.getCause();
        r1 = r1 instanceof com.fyber.exceptions.C2565a;
        if (r1 == 0) goto L_0x011a;
    L_0x00a7:
        r1 = r0.getMessage();
        r0 = r0.getCause();
        r0 = (com.fyber.exceptions.C2565a) r0;
        r0 = r0.m8200a();
        r10.m7881a(r3, r1, r0);
        goto L_0x003e;
    L_0x00b9:
        r6 = com.fyber.ads.internal.C2423a.ValidationNoFill;	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        r7 = 0;
        r10.m7878a(r3, r6, r7);	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        r6 = com.fyber.ads.internal.C2425d.READY_TO_CHECK_OFFERS;	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        r10.mo3913a(r6);	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        r10.mo3914b();	 Catch:{ InterruptedException -> 0x0083, ExecutionException -> 0x00c9, TimeoutException -> 0x012a }
        goto L_0x003e;
    L_0x00c9:
        r0 = move-exception;
        goto L_0x0084;
    L_0x00cb:
        r0 = r0.getMessage();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        r1 = 0;
        r10.m7881a(r3, r0, r1);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        goto L_0x003e;
    L_0x00d5:
        r0 = move-exception;
        goto L_0x009f;
    L_0x00d7:
        r0 = move-exception;
        r1 = r2;
        r0 = r2;
    L_0x00da:
        r6 = r0.isDone();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        if (r6 != 0) goto L_0x00e9;
    L_0x00e0:
        r6 = r0.isCancelled();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        if (r6 != 0) goto L_0x00e9;
    L_0x00e6:
        r0.m7971a();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
    L_0x00e9:
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r8 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ TimeoutException -> 0x0117, InterruptedException -> 0x009e, ExecutionException -> 0x00d5 }
        r0 = r0.get(r6, r8);	 Catch:{ TimeoutException -> 0x0117, InterruptedException -> 0x009e, ExecutionException -> 0x00d5 }
        r0 = (com.fyber.ads.C2410a) r0;	 Catch:{ TimeoutException -> 0x0117, InterruptedException -> 0x009e, ExecutionException -> 0x00d5 }
        if (r0 == 0) goto L_0x00fe;
    L_0x00f5:
        r1 = r0.m7624g();	 Catch:{ TimeoutException -> 0x0123, InterruptedException -> 0x009e, ExecutionException -> 0x00d5 }
        if (r1 == 0) goto L_0x00fe;
    L_0x00fb:
        r10.m7877a(r3, r0);	 Catch:{ TimeoutException -> 0x0123, InterruptedException -> 0x009e, ExecutionException -> 0x00d5 }
    L_0x00fe:
        if (r0 == 0) goto L_0x003e;
    L_0x0100:
        r0 = r0.m7624g();	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        if (r0 != 0) goto L_0x003e;
    L_0x0106:
        r0 = "validation";
        r10.m7880a(r3, r0, r5);	 Catch:{ InterruptedException -> 0x009e, ExecutionException -> 0x00d5, TimeoutException -> 0x010e }
        goto L_0x003e;
    L_0x010e:
        r0 = move-exception;
        r0 = "backend";
        r10.m7880a(r3, r0, r4);
        goto L_0x003e;
    L_0x0117:
        r0 = move-exception;
    L_0x0118:
        r0 = r1;
        goto L_0x00fe;
    L_0x011a:
        r0 = r0.getMessage();
        r10.m7881a(r3, r0, r2);
        goto L_0x003e;
    L_0x0123:
        r1 = move-exception;
        r1 = r0;
        goto L_0x0118;
    L_0x0126:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        goto L_0x00da;
    L_0x012a:
        r6 = move-exception;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x00da;
    L_0x012f:
        r0 = move-exception;
        goto L_0x0040;
    L_0x0132:
        r0 = move-exception;
        goto L_0x0040;
    L_0x0135:
        r0 = r2;
        r1 = r2;
        goto L_0x0077;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fyber.b.a.run():void");
    }

    private void m7877a(String str, P p) {
        m7878a(str, C2423a.ValidationFill, null);
        mo3913a(C2425d.READY_TO_SHOW_OFFERS);
        mo3915b(p);
    }

    private void m7881a(String str, String str2, String str3) {
        FyberLogger.m8448d("AdFetchOperation", "An error occurred while retrieving an ad " + str2);
        m7878a(str, C2423a.ValidationError, str3);
        this.f6215a.m8282a(RequestError.UNKNOWN_ERROR);
        mo3913a(C2425d.READY_TO_CHECK_OFFERS);
    }

    private void m7880a(String str, String str2, int i) {
        FyberLogger.m8448d("AdFetchOperation", "A timeout occurred while retrieving an ad");
        m7879a(str, C2423a.ValidationTimeout, str2, Collections.singletonMap("timeout_value", String.valueOf(i)));
        this.f6215a.m8282a(RequestError.ERROR_REQUESTING_ADS);
        mo3913a(C2425d.READY_TO_CHECK_OFFERS);
    }

    private void m7878a(String str, @NonNull C2423a c2423a, String str2) {
        m7879a(str, c2423a, str2, null);
    }

    private void m7879a(String str, @NonNull C2423a c2423a, String str2, Map<String, String> map) {
        ((C2482a) ((C2482a) mo3911a(c2423a).m7861a((Map) map)).m7860a(str2)).m7891b(str).mo3907b();
    }
}
