package com.inmobi.rendering.p118a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.inmobi.ads.C3046c;
import com.inmobi.ads.C3046c.C3039c;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.configs.C3128g;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.network.WebViewNetworkTask;
import com.inmobi.commons.core.network.WebViewNetworkTask.NetworkTaskWebView;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.C3158f;
import com.inmobi.commons.core.utilities.C3158f.C2983b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3276n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@VisibleForTesting
/* compiled from: ClickManager */
public class C3213c implements C2911b {
    @VisibleForTesting
    public static C3039c f8037a;
    private static final String f8038b = C3213c.class.getSimpleName();
    private static C3213c f8039c;
    private static final Object f8040d = new Object();
    private static ExecutorService f8041e;
    private static C3206a f8042f;
    private static HandlerThread f8043g;
    private static List<C3194a> f8044h = new ArrayList();
    private static C3195b f8045i;
    private static AtomicBoolean f8046j = new AtomicBoolean(false);
    private static final Object f8047k = new Object();
    private boolean f8048l = false;
    private PowerManager f8049m;
    private long f8050n = 0;
    private final C3202d f8051o = new C32037(this);

    /* compiled from: ClickManager */
    class C32005 implements C2983b {
        final /* synthetic */ C3213c f8020a;

        C32005(C3213c c3213c) {
            this.f8020a = c3213c;
        }

        public void mo6163b(boolean z) {
            if (z) {
                this.f8020a.m10714b();
            }
        }
    }

    /* compiled from: ClickManager */
    class C32016 implements C2983b {
        final /* synthetic */ C3213c f8021a;

        C32016(C3213c c3213c) {
            this.f8021a = c3213c;
        }

        public void mo6163b(boolean z) {
            if (!z) {
                this.f8021a.m10714b();
            }
        }
    }

    /* compiled from: ClickManager */
    interface C3202d {
        void mo6263a(C3194a c3194a);

        void mo6264a(C3194a c3194a, ErrorCode errorCode);
    }

    /* compiled from: ClickManager */
    class C32037 implements C3202d {
        final /* synthetic */ C3213c f8022a;

        C32037(C3213c c3213c) {
            this.f8022a = c3213c;
        }

        public void mo6263a(C3194a c3194a) {
            if (c3194a != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Processing click (" + c3194a.f7998b + ") completed");
                C3213c.f8045i.m10675b(c3194a);
                try {
                    Map hashMap = new HashMap();
                    hashMap.put("url", c3194a.f7998b);
                    hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.f8022a.f8050n));
                    C3135c.m10255a().m10280a("ads", "PingLatency", hashMap);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                }
            }
        }

        public void mo6264a(C3194a c3194a, ErrorCode errorCode) {
            if (c3194a != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Pinging click (" + c3194a.f7998b + ") failed! Updating retry counts and timestamps ...");
                this.f8022a.m10703c(c3194a);
                this.f8022a.m10714b();
            }
        }
    }

    /* compiled from: ClickManager */
    final class C3206a extends Handler {
        final /* synthetic */ C3213c f8025a;

        /* compiled from: ClickManager */
        class C32041 implements C3202d {
            final /* synthetic */ C3206a f8023a;

            C32041(C3206a c3206a) {
                this.f8023a = c3206a;
            }

            public void mo6263a(C3194a c3194a) {
                this.f8023a.m10690b(c3194a);
            }

            public void mo6264a(C3194a c3194a, ErrorCode errorCode) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Pinging click (" + c3194a.f7998b + ") via HTTP failed ...");
                this.f8023a.f8025a.m10703c(c3194a);
                this.f8023a.m10692c(c3194a);
            }
        }

        /* compiled from: ClickManager */
        class C32052 implements C3202d {
            final /* synthetic */ C3206a f8024a;

            C32052(C3206a c3206a) {
                this.f8024a = c3206a;
            }

            public void mo6263a(C3194a c3194a) {
                this.f8024a.m10690b(c3194a);
            }

            public void mo6264a(C3194a c3194a, ErrorCode errorCode) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Pinging click (" + c3194a.f7998b + ") via WebView failed ...");
                this.f8024a.f8025a.m10703c(c3194a);
                this.f8024a.m10692c(c3194a);
            }
        }

        public C3206a(C3213c c3213c, Looper looper) {
            this.f8025a = c3213c;
            super(looper);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r7) {
            /*
            r6 = this;
            r1 = 2;
            r0 = r7.what;	 Catch:{ Exception -> 0x004e }
            switch(r0) {
                case 1: goto L_0x0007;
                case 2: goto L_0x00c7;
                case 3: goto L_0x0170;
                case 4: goto L_0x023c;
                case 5: goto L_0x0219;
                default: goto L_0x0006;
            };	 Catch:{ Exception -> 0x004e }
        L_0x0006:
            return;
        L_0x0007:
            r0 = new com.inmobi.commons.core.configs.g;	 Catch:{ Exception -> 0x004e }
            r0.<init>();	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.commons.core.configs.C3121b.m10178a();	 Catch:{ Exception -> 0x004e }
            r3 = 0;
            r2.m10190a(r0, r3);	 Catch:{ Exception -> 0x004e }
            r0 = r0.m10243h();	 Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0006;
        L_0x001a:
            r0 = com.inmobi.rendering.p118a.C3213c.f8045i;	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r2 = r2.m9638e();	 Catch:{ Exception -> 0x004e }
            r3 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r3 = r3.m9635b();	 Catch:{ Exception -> 0x004e }
            r0 = r0.m10671a(r2, r3);	 Catch:{ Exception -> 0x004e }
            com.inmobi.rendering.p118a.C3213c.f8044h = r0;	 Catch:{ Exception -> 0x004e }
            r0 = com.inmobi.rendering.p118a.C3213c.f8044h;	 Catch:{ Exception -> 0x004e }
            r0 = r0.isEmpty();	 Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x0085;
        L_0x003b:
            r0 = com.inmobi.rendering.p118a.C3213c.f8045i;	 Catch:{ Exception -> 0x004e }
            r0 = r0.m10673a();	 Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x0071;
        L_0x0045:
            r0 = com.inmobi.rendering.p118a.C3213c.f8046j;	 Catch:{ Exception -> 0x004e }
            r1 = 0;
            r0.set(r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x004e:
            r0 = move-exception;
            r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
            r2 = com.inmobi.rendering.p118a.C3213c.f8038b;
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "SDK encountered unexpected error in processing ping; ";
            r3 = r3.append(r4);
            r0 = r0.getMessage();
            r0 = r3.append(r0);
            r0 = r0.toString();
            com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);
            goto L_0x0006;
        L_0x0071:
            r0 = android.os.Message.obtain();	 Catch:{ Exception -> 0x004e }
            r1 = 1;
            r0.what = r1;	 Catch:{ Exception -> 0x004e }
            r1 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r1 = r1.m9635b();	 Catch:{ Exception -> 0x004e }
            r1 = r1 * 1000;
            r2 = (long) r1;	 Catch:{ Exception -> 0x004e }
            r6.sendMessageDelayed(r0, r2);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x0085:
            r0 = com.inmobi.rendering.p118a.C3213c.f8044h;	 Catch:{ Exception -> 0x004e }
            r2 = 0;
            r0 = r0.get(r2);	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r2 = android.os.Message.obtain();	 Catch:{ Exception -> 0x004e }
            r3 = r0.f8004h;	 Catch:{ Exception -> 0x004e }
            if (r3 == 0) goto L_0x0099;
        L_0x0098:
            r1 = 3;
        L_0x0099:
            r2.what = r1;	 Catch:{ Exception -> 0x004e }
            r2.obj = r0;	 Catch:{ Exception -> 0x004e }
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x004e }
            r0 = r0.f8000d;	 Catch:{ Exception -> 0x004e }
            r0 = r4 - r0;
            r3 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r3 = r3.m9635b();	 Catch:{ Exception -> 0x004e }
            r3 = r3 * 1000;
            r4 = (long) r3;	 Catch:{ Exception -> 0x004e }
            r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r3 >= 0) goto L_0x00c2;
        L_0x00b2:
            r3 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r3 = r3.m9635b();	 Catch:{ Exception -> 0x004e }
            r3 = r3 * 1000;
            r4 = (long) r3;	 Catch:{ Exception -> 0x004e }
            r0 = r4 - r0;
            r6.sendMessageDelayed(r2, r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x00c2:
            r6.sendMessage(r2);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x00c7:
            r0 = com.inmobi.commons.core.utilities.C3155d.m10406a();	 Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x00dc;
        L_0x00cd:
            r0 = com.inmobi.rendering.p118a.C3213c.f8046j;	 Catch:{ Exception -> 0x004e }
            r1 = 0;
            r0.set(r1);	 Catch:{ Exception -> 0x004e }
            r0 = r6.f8025a;	 Catch:{ Exception -> 0x004e }
            r0.m10716c();	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x00dc:
            r0 = r7.obj;	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r1 = r0.f8002f;	 Catch:{ Exception -> 0x004e }
            if (r1 != 0) goto L_0x00ea;
        L_0x00e4:
            r1 = 1;
            r6.m10688a(r0, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x00ea:
            r1 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r2 = r1.m9639f();	 Catch:{ Exception -> 0x004e }
            r1 = r0.m10668a(r2);	 Catch:{ Exception -> 0x004e }
            if (r1 == 0) goto L_0x00fc;
        L_0x00f6:
            r1 = 2;
            r6.m10688a(r0, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x00fc:
            r1 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r1 = r1.m9634a();	 Catch:{ Exception -> 0x004e }
            r2 = r0.f8002f;	 Catch:{ Exception -> 0x004e }
            r1 = r1 - r2;
            r1 = r1 + 1;
            if (r1 != 0) goto L_0x013e;
        L_0x0109:
            r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r3.<init>();	 Catch:{ Exception -> 0x004e }
            r4 = "Pinging click (";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = ") over HTTP";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ Exception -> 0x004e }
        L_0x012f:
            r1 = new com.inmobi.rendering.a.c$c;	 Catch:{ Exception -> 0x004e }
            r2 = new com.inmobi.rendering.a.c$a$1;	 Catch:{ Exception -> 0x004e }
            r2.<init>(r6);	 Catch:{ Exception -> 0x004e }
            r1.<init>(r2);	 Catch:{ Exception -> 0x004e }
            r1.m10695a(r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x013e:
            r2 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r3 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r4.<init>();	 Catch:{ Exception -> 0x004e }
            r5 = "Retry attempt #";
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x004e }
            r1 = r4.append(r1);	 Catch:{ Exception -> 0x004e }
            r4 = " for click (";
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = ") over HTTP";
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r1 = r1.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r2, r3, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x012f;
        L_0x0170:
            r0 = com.inmobi.commons.core.utilities.C3155d.m10406a();	 Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0185;
        L_0x0176:
            r0 = com.inmobi.rendering.p118a.C3213c.f8046j;	 Catch:{ Exception -> 0x004e }
            r1 = 0;
            r0.set(r1);	 Catch:{ Exception -> 0x004e }
            r0 = r6.f8025a;	 Catch:{ Exception -> 0x004e }
            r0.m10716c();	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x0185:
            r0 = r7.obj;	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r1 = r0.f8002f;	 Catch:{ Exception -> 0x004e }
            if (r1 != 0) goto L_0x0193;
        L_0x018d:
            r1 = 1;
            r6.m10688a(r0, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x0193:
            r1 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r2 = r1.m9639f();	 Catch:{ Exception -> 0x004e }
            r1 = r0.m10668a(r2);	 Catch:{ Exception -> 0x004e }
            if (r1 == 0) goto L_0x01a5;
        L_0x019f:
            r1 = 2;
            r6.m10688a(r0, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x01a5:
            r1 = com.inmobi.rendering.p118a.C3213c.f8037a;	 Catch:{ Exception -> 0x004e }
            r1 = r1.m9634a();	 Catch:{ Exception -> 0x004e }
            r2 = r0.f8002f;	 Catch:{ Exception -> 0x004e }
            r1 = r1 - r2;
            r1 = r1 + 1;
            if (r1 != 0) goto L_0x01e7;
        L_0x01b2:
            r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r3.<init>();	 Catch:{ Exception -> 0x004e }
            r4 = "Pinging click (";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = ") in WebView";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ Exception -> 0x004e }
        L_0x01d8:
            r1 = new com.inmobi.rendering.a.c$b;	 Catch:{ Exception -> 0x004e }
            r2 = new com.inmobi.rendering.a.c$a$2;	 Catch:{ Exception -> 0x004e }
            r2.<init>(r6);	 Catch:{ Exception -> 0x004e }
            r1.<init>(r2);	 Catch:{ Exception -> 0x004e }
            r1.m10694a(r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x01e7:
            r2 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r3 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r4.<init>();	 Catch:{ Exception -> 0x004e }
            r5 = "Retry attempt #";
            r4 = r4.append(r5);	 Catch:{ Exception -> 0x004e }
            r1 = r4.append(r1);	 Catch:{ Exception -> 0x004e }
            r4 = " for click (";
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = ") using WebView";
            r1 = r1.append(r4);	 Catch:{ Exception -> 0x004e }
            r1 = r1.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r2, r3, r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x01d8;
        L_0x0219:
            r0 = r7.obj;	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r1 = new java.util.HashMap;	 Catch:{ Exception -> 0x004e }
            r1.<init>();	 Catch:{ Exception -> 0x004e }
            r2 = "pingUrl";
            r0 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r1.put(r2, r0);	 Catch:{ Exception -> 0x004e }
            r0 = r7.arg1;	 Catch:{ Exception -> 0x004e }
            switch(r0) {
                case 1: goto L_0x029e;
                case 2: goto L_0x02a8;
                default: goto L_0x022f;
            };
        L_0x022f:
            r0 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ Exception -> 0x02b3 }
            r2 = "ads";
            r3 = "PingDiscarded";
            r0.m10280a(r2, r3, r1);	 Catch:{ Exception -> 0x02b3 }
        L_0x023c:
            r0 = r7.obj;	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r3.<init>();	 Catch:{ Exception -> 0x004e }
            r4 = "Processing click (";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = r0.f7998b;	 Catch:{ Exception -> 0x004e }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r4 = ") completed";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r3 = r3.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r3);	 Catch:{ Exception -> 0x004e }
            r1 = com.inmobi.rendering.p118a.C3213c.f8045i;	 Catch:{ Exception -> 0x004e }
            r1.m10675b(r0);	 Catch:{ Exception -> 0x004e }
            r1 = com.inmobi.rendering.p118a.C3213c.f8044h;	 Catch:{ Exception -> 0x004e }
            r1.remove(r0);	 Catch:{ Exception -> 0x004e }
            r0 = com.inmobi.rendering.p118a.C3213c.f8044h;	 Catch:{ Exception -> 0x004e }
            r0 = r0.isEmpty();	 Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x02ea;
        L_0x027e:
            r0 = com.inmobi.rendering.p118a.C3213c.f8045i;	 Catch:{ Exception -> 0x004e }
            r0 = r0.m10673a();	 Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x02de;
        L_0x0288:
            r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r1 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r2 = "Done processing all clicks!";
            com.inmobi.commons.core.utilities.Logger.m10359a(r0, r1, r2);	 Catch:{ Exception -> 0x004e }
            r0 = com.inmobi.rendering.p118a.C3213c.f8046j;	 Catch:{ Exception -> 0x004e }
            r1 = 0;
            r0.set(r1);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x029e:
            r0 = "errorCode";
            r2 = "MaxRetryCountReached";
            r1.put(r0, r2);	 Catch:{ Exception -> 0x004e }
            goto L_0x022f;
        L_0x02a8:
            r0 = "errorCode";
            r2 = "ExpiredClick";
            r1.put(r0, r2);	 Catch:{ Exception -> 0x004e }
            goto L_0x022f;
        L_0x02b3:
            r0 = move-exception;
            r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ Exception -> 0x004e }
            r2 = com.inmobi.rendering.p118a.C3213c.f8038b;	 Catch:{ Exception -> 0x004e }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004e }
            r3.<init>();	 Catch:{ Exception -> 0x004e }
            r4 = "Error in submitting telemetry event : (";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x004e }
            r0 = r0.getMessage();	 Catch:{ Exception -> 0x004e }
            r0 = r3.append(r0);	 Catch:{ Exception -> 0x004e }
            r3 = ")";
            r0 = r0.append(r3);	 Catch:{ Exception -> 0x004e }
            r0 = r0.toString();	 Catch:{ Exception -> 0x004e }
            com.inmobi.commons.core.utilities.Logger.m10359a(r1, r2, r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x023c;
        L_0x02de:
            r0 = android.os.Message.obtain();	 Catch:{ Exception -> 0x004e }
            r1 = 1;
            r0.what = r1;	 Catch:{ Exception -> 0x004e }
            r6.sendMessage(r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
        L_0x02ea:
            r0 = com.inmobi.rendering.p118a.C3213c.f8044h;	 Catch:{ Exception -> 0x004e }
            r1 = 0;
            r0 = r0.get(r1);	 Catch:{ Exception -> 0x004e }
            r0 = (com.inmobi.rendering.p118a.C3194a) r0;	 Catch:{ Exception -> 0x004e }
            r6.m10687a(r0);	 Catch:{ Exception -> 0x004e }
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.a.c.a.handleMessage(android.os.Message):void");
        }

        private void m10687a(C3194a c3194a) {
            Message obtain = Message.obtain();
            obtain.what = c3194a.f8004h ? 3 : 2;
            obtain.obj = c3194a;
            sendMessage(obtain);
        }

        private void m10688a(C3194a c3194a, int i) {
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.obj = c3194a;
            obtain.arg1 = i;
            sendMessage(obtain);
        }

        private void m10690b(C3194a c3194a) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.obj = c3194a;
            sendMessage(obtain);
        }

        private void m10692c(C3194a c3194a) {
            int indexOf = C3213c.f8044h.indexOf(c3194a);
            if (-1 != indexOf) {
                C3194a c3194a2 = (C3194a) C3213c.f8044h.get(indexOf == C3213c.f8044h.size() + -1 ? 0 : indexOf + 1);
                Message obtain = Message.obtain();
                obtain.what = c3194a2.f8004h ? 3 : 2;
                obtain.obj = c3194a2;
                if (System.currentTimeMillis() - c3194a2.f8000d < ((long) (C3213c.f8037a.m9635b() * 1000))) {
                    sendMessageDelayed(obtain, (long) (C3213c.f8037a.m9635b() * 1000));
                } else {
                    sendMessage(obtain);
                }
            }
        }
    }

    /* compiled from: ClickManager */
    static final class C3211b {
        private C3202d f8035a;

        public C3211b(C3202d c3202d) {
            this.f8035a = c3202d;
        }

        public void m10694a(final C3194a c3194a) {
            c3194a.f8003g.set(false);
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable(this) {
                final /* synthetic */ C3211b f8034c;

                /* compiled from: ClickManager */
                class C32091 extends WebViewClient {
                    AtomicBoolean f8029a;
                    boolean f8030b;
                    final /* synthetic */ C32101 f8031c;

                    C32091(C32101 c32101) {
                        this.f8031c = c32101;
                    }

                    public void onPageStarted(final WebView webView, String str, Bitmap bitmap) {
                        this.f8029a = new AtomicBoolean(false);
                        this.f8030b = false;
                        new Thread(new Runnable(this) {
                            final /* synthetic */ C32091 f8028b;

                            /* compiled from: ClickManager */
                            class C32071 implements Runnable {
                                final /* synthetic */ C32081 f8026a;

                                C32071(C32081 c32081) {
                                    this.f8026a = c32081;
                                }

                                public void run() {
                                    try {
                                        NetworkTaskWebView networkTaskWebView = (NetworkTaskWebView) webView;
                                        if (networkTaskWebView != null && !networkTaskWebView.f7739a) {
                                            webView.stopLoading();
                                        }
                                    } catch (Throwable th) {
                                        C3135c.m10255a().m10279a(new C3132b(th));
                                    }
                                }
                            }

                            public void run() {
                                try {
                                    Thread.sleep((long) (C3213c.f8037a.m9636c() * 1000));
                                } catch (InterruptedException e) {
                                }
                                if (!this.f8028b.f8029a.get()) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Pinging click (" + c3194a.f7998b + ") via WebView timed out!");
                                    c3194a.f8003g.set(true);
                                    handler.post(new C32071(this));
                                    this.f8028b.f8031c.f8034c.f8035a.mo6264a(c3194a, null);
                                }
                            }
                        }).start();
                    }

                    public void onPageFinished(WebView webView, String str) {
                        this.f8029a.set(true);
                        if (!this.f8030b && !c3194a.f8003g.get()) {
                            this.f8031c.f8034c.f8035a.mo6263a(c3194a);
                        }
                    }

                    @TargetApi(22)
                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        this.f8030b = true;
                        this.f8031c.f8034c.f8035a.mo6264a(c3194a, null);
                    }

                    @TargetApi(23)
                    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                        this.f8030b = true;
                        this.f8031c.f8034c.f8035a.mo6264a(c3194a, null);
                    }

                    @TargetApi(23)
                    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                        this.f8030b = true;
                        this.f8031c.f8034c.f8035a.mo6264a(c3194a, null);
                    }

                    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                        if (VERSION.SDK_INT < 21 || c3194a.f8005i || webResourceRequest.getUrl().toString().equals(c3194a.f7998b)) {
                            return false;
                        }
                        return true;
                    }

                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        if (c3194a.f8005i || str.equals(c3194a.f7998b)) {
                            return false;
                        }
                        return true;
                    }
                }

                public void run() {
                    NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, c3194a.f7998b, false, null);
                    Map b = C3213c.m10704d(c3194a);
                    if (!b.isEmpty()) {
                        networkRequest.m9756c(b);
                    }
                    new WebViewNetworkTask(networkRequest, new C32091(this)).m10335a();
                }
            });
        }
    }

    /* compiled from: ClickManager */
    static final class C3212c {
        private C3202d f8036a;

        public C3212c(C3202d c3202d) {
            this.f8036a = c3202d;
        }

        public void m10695a(C3194a c3194a) {
            try {
                NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, c3194a.f7998b, false, null);
                Map b = C3213c.m10704d(c3194a);
                if (!b.isEmpty()) {
                    networkRequest.m9756c(b);
                }
                networkRequest.m9751a(false);
                networkRequest.m9757d(c3194a.f7999c);
                networkRequest.m9754b(c3194a.f8005i);
                networkRequest.m9753b(C3213c.f8037a.m9636c() * 1000);
                networkRequest.m9755c(C3213c.f8037a.m9636c() * 1000);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                C3143c a = new C3144d(networkRequest).m10357a();
                try {
                    C3276n.m10977a().m10979a(networkRequest.m9772t());
                    C3276n.m10977a().m10981b(a.m10356f());
                    C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Error in setting request-response data size. " + e.getMessage());
                }
                if (a.m10351a()) {
                    ErrorCode a2 = a.m10355e().m10332a();
                    if (c3194a.f8005i || !(ErrorCode.HTTP_SEE_OTHER == a2 || ErrorCode.HTTP_MOVED_TEMP == a2)) {
                        this.f8036a.mo6264a(c3194a, a.m10355e().m10332a());
                        return;
                    } else {
                        this.f8036a.mo6263a(c3194a);
                        return;
                    }
                }
                this.f8036a.mo6263a(c3194a);
            } catch (Exception e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "SDK encountered unexpected error in executing ping over HTTP; " + e2.getMessage());
                this.f8036a.mo6264a(c3194a, new NetworkError(ErrorCode.UNKNOWN_ERROR, "Unknown error").m10332a());
            }
        }
    }

    public static C3213c m10698a() {
        C3213c c3213c = f8039c;
        if (c3213c == null) {
            synchronized (f8040d) {
                c3213c = f8039c;
                if (c3213c == null) {
                    c3213c = new C3213c();
                    f8039c = c3213c;
                }
            }
        }
        return c3213c;
    }

    public void mo6102a(C3045a c3045a) {
        f8037a = ((C3046c) c3045a).m9725j();
    }

    public void m10714b() {
        try {
            if (C3155d.m10406a()) {
                synchronized (f8047k) {
                    if (f8046j.compareAndSet(false, true)) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "Resume processing clicks ...");
                        if (f8043g == null) {
                            f8043g = new HandlerThread("pingHandlerThread");
                            f8043g.start();
                        }
                        if (f8042f == null) {
                            f8042f = new C3206a(this, f8043g.getLooper());
                        }
                        if (f8045i.m10673a()) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "Done processing all clicks!");
                            f8046j.set(false);
                            m10716c();
                        } else {
                            Message obtain = Message.obtain();
                            obtain.what = 1;
                            f8042f.sendMessage(obtain);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "SDK encountered unexpected error in starting the ping component; " + e.getMessage());
        }
    }

    public void m10713a(final String str, final boolean z) {
        new Thread(this) {
            final /* synthetic */ C3213c f8010c;

            public void run() {
                try {
                    C3045a c3128g = new C3128g();
                    C3121b.m10178a().m10190a(c3128g, null);
                    if (!c3128g.m10243h()) {
                        C3194a c3194a = new C3194a(str, z, false, C3213c.f8037a.m9634a() + 1);
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Received click (" + c3194a.f7998b + ") for pinging over HTTP");
                        this.f8010c.m10711a(c3194a);
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "SDK encountered unexpected error in pinging click; " + e.getMessage());
                }
            }
        }.start();
    }

    public void m10712a(final String str, final Map<String, String> map, final boolean z) {
        new Thread(this) {
            final /* synthetic */ C3213c f8014d;

            public void run() {
                try {
                    C3045a c3128g = new C3128g();
                    C3121b.m10178a().m10190a(c3128g, null);
                    if (!c3128g.m10243h()) {
                        C3194a c3194a = new C3194a(str, map, z, false, C3213c.f8037a.m9634a() + 1);
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Received click (" + c3194a.f7998b + ") for pinging over HTTP");
                        this.f8014d.m10711a(c3194a);
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "SDK encountered unexpected error in pinging click; " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        }.start();
    }

    public void m10715b(final String str, final boolean z) {
        new Thread(this) {
            final /* synthetic */ C3213c f8017c;

            public void run() {
                try {
                    C3045a c3128g = new C3128g();
                    C3121b.m10178a().m10190a(c3128g, null);
                    if (!c3128g.m10243h()) {
                        C3194a c3194a = new C3194a(str, z, true, C3213c.f8037a.m9634a() + 1);
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "Received click (" + c3194a.f7998b + ") for pinging in WebView");
                        this.f8017c.m10711a(c3194a);
                    }
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3213c.f8038b, "SDK encountered unexpected error in pinging click over WebView; " + e.getMessage());
                }
            }
        }.start();
    }

    public void m10716c() {
        try {
            f8046j.set(false);
            synchronized (f8047k) {
                if (!f8046j.get()) {
                    if (f8043g != null) {
                        f8043g.getLooper().quit();
                        f8043g.interrupt();
                        f8043g = null;
                        f8042f = null;
                    }
                    f8044h.clear();
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "SDK encountered unexpected error in stopping the ping component; " + e.getMessage());
        }
    }

    private void m10703c(C3194a c3194a) {
        if (c3194a.f8002f > 0) {
            c3194a.f8002f--;
            c3194a.f8000d = System.currentTimeMillis();
            f8045i.m10672a(c3194a);
        }
    }

    @VisibleForTesting
    protected void m10711a(final C3194a c3194a) {
        f8045i.m10674a(c3194a, f8037a.m9637d());
        if (C3155d.m10406a()) {
            f8041e.submit(new Runnable(this) {
                final /* synthetic */ C3213c f8019b;

                public void run() {
                    this.f8019b.f8050n = SystemClock.elapsedRealtime();
                    if (c3194a.f8004h) {
                        new C3211b(this.f8019b.f8051o).m10694a(c3194a);
                    } else {
                        new C3212c(this.f8019b.f8051o).m10695a(c3194a);
                    }
                }
            });
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "No network available. Saving click for later processing ...");
        f8046j.set(false);
        m10716c();
    }

    @VisibleForTesting
    public C3213c() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "Creating a new instance ...");
        m10717d();
    }

    @VisibleForTesting
    public void m10717d() {
        try {
            f8041e = Executors.newFixedThreadPool(5);
            f8043g = new HandlerThread("pingHandlerThread");
            f8043g.start();
            f8042f = new C3206a(this, f8043g.getLooper());
            C3045a c3046c = new C3046c();
            C3121b.m10178a().m10190a(c3046c, (C2911b) this);
            f8037a = c3046c.m9725j();
            f8045i = new C3195b();
            this.f8049m = (PowerManager) C3105a.m10078b().getSystemService("power");
            m10709i();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "SDK encountered unexpected error in initializing the ping component; " + e.getMessage());
        }
    }

    @TargetApi(23)
    private void m10709i() {
        C3158f.m10412a().m10418a("android.net.conn.CONNECTIVITY_CHANGE", new C32005(this));
        if (VERSION.SDK_INT >= 23) {
            C3158f.m10412a().m10418a("android.os.action.DEVICE_IDLE_MODE_CHANGED", new C32016(this));
        }
    }

    private static HashMap<String, String> m10704d(C3194a c3194a) {
        HashMap<String, String> hashMap = new HashMap();
        try {
            int a = (f8037a.m9634a() - c3194a.f8002f) + 1;
            if (a > 0) {
                hashMap.put("X-im-retry-count", String.valueOf(a));
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8038b, "Error in fetching retry count header.");
        }
        return hashMap;
    }
}
