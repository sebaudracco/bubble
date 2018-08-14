package com.inmobi.commons.core.p115d;

import android.content.ContentValues;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.p114b.C3111b;
import com.inmobi.commons.core.p115d.C3137d.C3136a;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.C3158f;
import com.inmobi.commons.core.utilities.C3158f.C2983b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.commons.p112a.C3105a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryComponent */
public class C3135c implements C2911b {
    private static final String f7703a = C3135c.class.getSimpleName();
    private static final Object f7704b = new Object();
    private static volatile C3135c f7705c;
    private static boolean f7706d = false;
    private static final AtomicBoolean f7707e = new AtomicBoolean(false);
    private static Map<String, C3137d> f7708f;
    private static C3138e f7709h;
    private static final Random f7710o = new Random(System.currentTimeMillis());
    private List<C3110g> f7711g = new ArrayList();
    private HandlerThread f7712i;
    private C3134a f7713j;
    private Map<String, Integer> f7714k;
    private final Object f7715l = new Object();
    private final Object f7716m = new Object();
    private final Object f7717n = new Object();

    /* compiled from: TelemetryComponent */
    class C31331 implements C2983b {
        final /* synthetic */ C3135c f7690a;

        C31331(C3135c c3135c) {
            this.f7690a = c3135c;
        }

        public void mo6163b(boolean z) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Network status changed " + z);
            if (z && !C3135c.f7707e.get() && C3105a.m10087e()) {
                C3135c.m10255a().m10257a(60);
            }
            C3135c.f7707e.set(z);
        }
    }

    /* compiled from: TelemetryComponent */
    private final class C3134a extends Handler {
        final /* synthetic */ C3135c f7691a;
        private String f7692b;
        private String f7693c = null;
        private C3139f f7694d;
        private int f7695e;
        private int f7696f;
        private int f7697g;
        private AtomicBoolean f7698h = new AtomicBoolean(false);
        private int f7699i = 0;
        private int f7700j;
        private boolean f7701k;
        private List<C3110g> f7702l = new ArrayList();

        public C3134a(C3135c c3135c, Looper looper) {
            this.f7691a = c3135c;
            super(looper);
            m10251a();
        }

        private void m10251a() {
            this.f7695e = C3135c.f7709h.m10318j();
            this.f7692b = C3135c.f7709h.m10315g();
            this.f7696f = C3135c.f7709h.m10320l() * 1000;
            this.f7697g = C3135c.f7709h.m10316h() * 1000;
            this.f7700j = C3135c.f7709h.m10319k();
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    try {
                        this.f7691a.m10265c((C3110g) message.obj);
                        return;
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "SDK encountered unexpected error processing a telemetry event");
                        return;
                    }
                case 1:
                    try {
                        removeMessages(1);
                        if (!this.f7698h.compareAndSet(false, true)) {
                            return;
                        }
                        if (C3105a.m10087e() && C3155d.m10406a()) {
                            m10251a();
                            m10253b();
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "App not in foreground or No Network available ");
                        this.f7698h.set(false);
                        return;
                    } catch (Exception e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "SDK encountered unexpected error starting the telemetry component");
                        return;
                    }
                case 2:
                    try {
                        m10254c();
                        return;
                    } catch (Exception e3) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "SDK encountered unexpected error in sending telemetry events to the server");
                        return;
                    }
                case 3:
                    try {
                        this.f7698h.set(false);
                        sendEmptyMessageDelayed(1, (long) this.f7696f);
                        return;
                    } catch (Exception e4) {
                        return;
                    }
                case 4:
                    try {
                        C3135c.m10255a().m10276j();
                        C3135c.m10255a().m10273g();
                        C3135c.m10255a().m10275i();
                        return;
                    } catch (Exception e5) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "SDK encountered unexpected error de-initializing telemetry");
                        return;
                    }
                default:
                    return;
            }
        }

        private void m10253b() {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Begin reporting");
            this.f7694d = new C3139f();
            long e = C3135c.f7709h.m10313e();
            Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Telemetry Event TTL = " + e);
            this.f7694d.m10324a(e);
            List a = this.f7694d.m10325a();
            if (!a.isEmpty()) {
                this.f7701k = true;
                this.f7693c = m10252b(a);
            } else if ((this.f7693c == null || this.f7693c.length() == 0) && (this.f7693c == null || this.f7693c.equals(""))) {
                this.f7701k = false;
                if (this.f7702l.isEmpty()) {
                    this.f7702l = this.f7694d.m10326a(this.f7695e);
                }
                if (this.f7702l.isEmpty()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "No events to report");
                    sendEmptyMessage(3);
                    return;
                }
                this.f7693c = m10250a(this.f7702l);
            }
            sendEmptyMessage(2);
        }

        private void m10254c() {
            NetworkRequest networkRequest = new NetworkRequest(RequestType.POST, this.f7692b, true, new C3169d(C3135c.f7709h.m9709r().m10168a()));
            Map hashMap = new HashMap();
            if (this.f7701k) {
                hashMap.put("metric", this.f7693c);
            } else {
                hashMap.put("telemetry", this.f7693c);
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Telemetry Payload: " + this.f7693c);
            networkRequest.m9758e(hashMap);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            C3143c a = new C3144d(networkRequest).m10357a();
            if (a.m10351a()) {
                this.f7699i++;
                if (this.f7699i > this.f7700j) {
                    this.f7699i = 0;
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Unable to send telemetry events to server: " + a.m10352b() + " . And retry count exhausted. Will Discard Events");
                    this.f7702l.clear();
                    this.f7693c = null;
                    sendEmptyMessage(3);
                    return;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Unable to send telemetry events to server: " + a.m10352b() + ". Will retry");
                this.f7698h.set(false);
                sendEmptyMessageDelayed(1, (long) this.f7697g);
                return;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Successfully sent events to server: " + a.m10352b());
            this.f7693c = null;
            this.f7702l.clear();
            try {
                Map hashMap2 = new HashMap();
                hashMap2.put("url", this.f7692b);
                hashMap2.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                hashMap2.put("payloadSize", Long.valueOf(a.m10356f() + networkRequest.m9772t()));
                C3135c.m10255a().m10280a("root", "TelemetryLatency", hashMap2);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3135c.f7703a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
            if (this.f7694d.m10331c() > this.f7695e) {
                this.f7698h.set(false);
                sendEmptyMessage(1);
                return;
            }
            sendEmptyMessage(3);
        }

        private String m10250a(List<C3110g> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("eventId", ((C3110g) list.get(i)).m10121a());
                    jSONObject.put("eventType", ((C3110g) list.get(i)).m10124c());
                    if (!((C3110g) list.get(i)).m10125d().trim().isEmpty()) {
                        jSONObject.put("payload", ((C3110g) list.get(i)).m10125d());
                    }
                    jSONObject.put("component", ((C3110g) list.get(i)).m10123b());
                    jSONObject.put("ts", ((C3110g) list.get(i)).m10126e());
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return "";
                }
            }
            return jSONArray.toString();
        }

        private String m10252b(List<ContentValues> list) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < list.size()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("componentType", ((ContentValues) list.get(i)).getAsString("componentType"));
                    jSONObject.put("eventType", ((ContentValues) list.get(i)).getAsString("eventType"));
                    jSONObject.put("payload", ((ContentValues) list.get(i)).getAsString("payload"));
                    jSONArray.put(jSONObject);
                    i++;
                } catch (JSONException e) {
                    return "";
                }
            }
            return jSONArray.toString();
        }
    }

    public static C3135c m10255a() {
        C3135c c3135c = f7705c;
        if (c3135c == null) {
            synchronized (f7704b) {
                c3135c = f7705c;
                if (c3135c == null) {
                    c3135c = new C3135c();
                    f7705c = c3135c;
                }
            }
        }
        return c3135c;
    }

    private C3135c() {
        f7708f = new HashMap();
        this.f7714k = new HashMap();
        f7709h = new C3138e();
        f7707e.set(C3155d.m10406a());
        C3121b.m10178a().m10190a(f7709h, (C2911b) this);
        m10261a(f7709h.mo6231a(), f7709h.m10322n());
        C3158f.m10412a().m10418a("android.net.conn.CONNECTIVITY_CHANGE", new C31331(this));
    }

    public void mo6102a(C3045a c3045a) {
        f7709h = (C3138e) c3045a;
    }

    public final void m10281a(String str, JSONObject jSONObject) {
        m10261a(str, new C3137d(str, jSONObject, f7709h.m10322n()));
    }

    private void m10261a(String str, C3137d c3137d) {
        if (str == null || str.trim().equals("")) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Component type provided while registering is null or empty!");
            return;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "registerConfig == " + str);
        if (c3137d != null) {
            f7708f.put(str, c3137d);
        } else {
            f7708f.put(str, new C3137d(str, null, f7709h.m10322n()));
        }
    }

    @Nullable
    C3137d m10277a(String str) {
        if (str != null && !str.trim().equals("")) {
            return (C3137d) f7708f.get(str);
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Request null or empty Component type!");
        return null;
    }

    public void m10280a(String str, String str2, Map<String, Object> map) {
        try {
            C3110g c3110g = new C3110g(str, str2);
            if (!(map == null || map.isEmpty())) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Entry entry : map.entrySet()) {
                        jSONObject.put(entry.getKey().toString(), entry.getValue());
                    }
                    c3110g.m10122a(jSONObject.toString());
                } catch (JSONException e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Error forming JSON payload for " + str2 + " Error: " + e);
                }
            }
            C3135c.m10255a().m10279a(c3110g);
        } catch (Exception e2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
        }
    }

    public void m10279a(C3110g c3110g) {
        try {
            if (this.f7713j != null) {
                Message obtainMessage = this.f7713j.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = c3110g;
                this.f7713j.sendMessage(obtainMessage);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
        }
    }

    private void m10265c(C3110g c3110g) {
        C3137d f = m10270f(c3110g);
        if (f == null || !f.m10297b() || !f7709h.m10314f()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Telemetry service is not enabled or registered for component: " + c3110g.m10123b() + "|| type = " + c3110g.m10124c() + " Config :" + f);
        } else if (!(c3110g instanceof C3132b) || C3131a.m10248a()) {
            m10267d(c3110g);
            m10269e(c3110g);
        }
    }

    private void m10267d(C3110g c3110g) {
        C3136a g = m10272g(c3110g);
        if (g != null && g.m10289b()) {
            m10283b(c3110g);
        }
    }

    private void m10269e(C3110g c3110g) {
        if (c3110g instanceof C3111b) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Got a crash event, will save it right away!");
            new C3139f().m10327a(c3110g);
            return;
        }
        int i = f7709h.m10317i();
        f7709h.m10321m();
        synchronized (this.f7717n) {
            this.f7711g.add(c3110g);
        }
        if (this.f7711g.size() >= i) {
            m10273g();
            i = new C3139f().m10331c();
            int m = f7709h.m10321m();
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Current event count: " + i + " Upper cap: " + m);
            if (i > (m * 3) / 4) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Telemetry is more than 75% full. Begin reporting ");
                m10274h();
            }
        }
    }

    private void m10273g() {
        synchronized (this.f7717n) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Adding events " + this.f7711g.toString() + "to persistence");
            C3139f c3139f = new C3139f();
            int m = f7709h.m10321m();
            int c = c3139f.m10331c();
            if ((this.f7711g.size() + c) - m <= 0) {
                c3139f.m10329a(this.f7711g);
            } else {
                m -= c;
                if (m <= 0) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Persistence is full, won't add events");
                } else {
                    c3139f.m10329a(this.f7711g.subList(0, m));
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Persistence will overflow, will add " + m + " events to persistence");
                }
            }
            this.f7711g.clear();
        }
    }

    @Nullable
    private C3137d m10270f(C3110g c3110g) {
        return C3135c.m10255a().m10277a(c3110g.m10123b());
    }

    @Nullable
    private C3136a m10272g(C3110g c3110g) {
        C3137d f = m10270f(c3110g);
        if (f == null) {
            return null;
        }
        return f.m10291a(c3110g.m10124c());
    }

    private void m10274h() {
        m10257a(0);
    }

    private void m10257a(int i) {
        if (!C3105a.m10087e() || !C3155d.m10406a()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "App not in foreground or No Network available");
        } else if (this.f7713j == null) {
        } else {
            if (i > 0) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Begin reporting after " + i + " seconds");
                this.f7713j.sendEmptyMessageDelayed(1, (long) (i * 1000));
                return;
            }
            this.f7713j.sendEmptyMessage(1);
        }
    }

    public synchronized void m10282b() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "start called");
        synchronized (this.f7716m) {
            if (!f7706d) {
                f7706d = true;
                this.f7712i = new HandlerThread("telemetry");
                this.f7712i.start();
                this.f7713j = new C3134a(this, this.f7712i.getLooper());
            }
        }
        C3135c.m10255a().m10274h();
    }

    public synchronized void m10284c() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "stop called");
        if (this.f7713j != null) {
            this.f7713j.sendEmptyMessage(4);
        }
    }

    private void m10275i() {
        synchronized (this.f7716m) {
            if (this.f7712i != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Deiniting telemetry");
                this.f7712i.getLooper().quit();
                this.f7712i.interrupt();
                this.f7712i = null;
                this.f7713j = null;
                f7706d = false;
            }
        }
    }

    void m10283b(C3110g c3110g) {
        String b = c3110g.m10123b();
        String c = c3110g.m10124c();
        Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Metric collected: " + c + " - " + b);
        c = m10256a(b, c);
        synchronized (this.f7715l) {
            if (this.f7714k.containsKey(c)) {
                this.f7714k.put(c, Integer.valueOf(((Integer) this.f7714k.get(c)).intValue() + 1));
            } else {
                this.f7714k.put(c, Integer.valueOf(1));
            }
        }
    }

    private String m10256a(String str, String str2) {
        return str + "@$#$@" + str2;
    }

    private String[] m10263b(String str) {
        return str.split("\\@\\$\\#\\$\\@");
    }

    private void m10276j() {
        synchronized (this.f7715l) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Saving metric to persistence");
            C3139f c3139f = new C3139f();
            c3139f.m10330b();
            for (Entry entry : this.f7714k.entrySet()) {
                String[] b = m10263b((String) entry.getKey());
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("count", entry.getValue());
                    c3139f.m10328a(b[0], b[1], jSONObject.toString());
                } catch (JSONException e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7703a, "Error forming metric payload");
                }
            }
            this.f7714k.clear();
        }
    }
}
