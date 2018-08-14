package com.inmobi.rendering.mraid;

import android.os.SystemClock;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.C3276n;
import com.loopj.android.http.AsyncHttpClient;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: MraidJsFetcher */
public class C3229g {
    private static final String f8112a = C3229g.class.getSimpleName();
    private String f8113b;
    private int f8114c;
    private int f8115d;
    private NetworkRequest f8116e;

    /* compiled from: MraidJsFetcher */
    class C32281 implements Runnable {
        final /* synthetic */ C3229g f8111a;

        C32281(C3229g c3229g) {
            this.f8111a = c3229g;
        }

        public void run() {
            int i = 0;
            while (i <= this.f8111a.f8114c) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Attempting to get MRAID Js.");
                long elapsedRealtime = SystemClock.elapsedRealtime();
                C3143c a = new C3144d(this.f8111a.f8116e).m10357a();
                try {
                    C3276n.m10977a().m10979a(this.f8111a.f8116e.m9772t());
                    C3276n.m10977a().m10981b(a.m10356f());
                    C3276n.m10977a().m10988g(SystemClock.elapsedRealtime() - elapsedRealtime);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Error in setting request-response data size. " + e.getMessage());
                }
                if (a.m10351a()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Getting MRAID Js from server failed.");
                    i++;
                    if (i <= this.f8111a.f8114c) {
                        try {
                            Thread.sleep((long) (this.f8111a.f8115d * 1000));
                        } catch (Throwable e2) {
                            Logger.m10360a(InternalLogLevel.INTERNAL, C3229g.f8112a, "MRAID Js client interrupted while sleeping.", e2);
                        }
                    } else {
                        return;
                    }
                }
                C3227f c3227f = new C3227f();
                List list = (List) a.m10354d().get("Content-Encoding");
                if (list == null || !((String) list.get(0)).equals(AsyncHttpClient.ENCODING_GZIP)) {
                    c3227f.m10774a(a.m10352b());
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Getting MRAID Js from server succeeded.");
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put("url", this.f8111a.f8113b);
                        hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                        hashMap.put("payloadSize", Long.valueOf(a.m10356f() + this.f8111a.f8116e.m9772t()));
                        C3135c.m10255a().m10280a("ads", "MraidFetchLatency", hashMap);
                        return;
                    } catch (Exception e3) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Error in submitting telemetry event : (" + e3.getMessage() + ")");
                        return;
                    }
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Response is GZIP-compressed, uncompressing it");
                byte[] a2 = C3155d.m10408a(a.m10353c());
                if (a2 != null) {
                    try {
                        c3227f.m10774a(new String(a2, "UTF-8"));
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Getting MRAID Js from server succeeded.");
                        try {
                            hashMap = new HashMap();
                            hashMap.put("url", this.f8111a.f8113b);
                            hashMap.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                            hashMap.put("payloadSize", Long.valueOf(a.m10356f() + this.f8111a.f8116e.m9772t()));
                            C3135c.m10255a().m10280a("ads", "MraidFetchLatency", hashMap);
                            return;
                        } catch (Exception e32) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Error in submitting telemetry event : (" + e32.getMessage() + ")");
                            return;
                        }
                    } catch (UnsupportedEncodingException e4) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, "Failed to get MRAID JS");
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3229g.f8112a, e4.getMessage());
                        return;
                    }
                }
                return;
            }
        }
    }

    public C3229g(String str, int i, int i2) {
        this.f8113b = str;
        this.f8114c = i;
        this.f8115d = i2;
    }

    public void m10782a() {
        if (this.f8113b == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8112a, "MRAID Js Url provided is invalid.");
            return;
        }
        this.f8116e = new NetworkRequest(RequestType.GET, this.f8113b, false, null, false);
        Map hashMap = new HashMap();
        hashMap.put("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
        this.f8116e.m9756c(hashMap);
        new Thread(new C32281(this)).start();
    }
}
