package com.inmobi.commons.core.configs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.inmobi.commons.core.configs.C3123d.C3118a;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse;
import com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.commons.p112a.C3106b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* compiled from: ConfigComponent */
public class C3121b {
    private static final String f7648a = C3121b.class.getSimpleName();
    private static final Object f7649b = new Object();
    private static Map<C3045a, ArrayList<WeakReference<C2911b>>> f7650c;
    private static C3128g f7651d;
    private static volatile C3121b f7652e;
    private static C3120c f7653f;
    private HandlerThread f7654g;
    private C3119a f7655h;
    private boolean f7656i = false;

    /* compiled from: ConfigComponent */
    public interface C2911b {
        void mo6102a(C3045a c3045a);
    }

    /* compiled from: ConfigComponent */
    static final class C3119a extends Handler implements C3118a {
        private List<C3045a> f7644a = new ArrayList();
        private Map<String, Map<String, C3045a>> f7645b = new HashMap();
        private Map<String, C3045a> f7646c = new HashMap();
        private ExecutorService f7647d;

        C3119a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    C3045a c3045a = (C3045a) message.obj;
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Fetch requested for config:" + c3045a.mo6231a() + ". IsAlreadyScheduled:" + m10173a(c3045a.mo6231a()));
                    if (m10173a(c3045a.mo6231a())) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config fetching already in progress:" + c3045a.mo6231a());
                        return;
                    }
                    this.f7644a.add(c3045a);
                    if (!hasMessages(2)) {
                        sendEmptyMessage(2);
                        return;
                    }
                    return;
                case 2:
                    sendEmptyMessageDelayed(3, (long) (C3121b.f7651d.m10242g() * 1000));
                    return;
                case 3:
                    m10172a(this.f7644a);
                    this.f7644a.clear();
                    if (this.f7647d == null || this.f7647d.isShutdown()) {
                        this.f7647d = Executors.newFixedThreadPool(1);
                        sendEmptyMessage(4);
                        return;
                    }
                    return;
                case 4:
                    Entry entry;
                    if (this.f7645b.isEmpty()) {
                        entry = null;
                    } else {
                        entry = (Entry) this.f7645b.entrySet().iterator().next();
                    }
                    if (entry != null) {
                        this.f7646c = (Map) entry.getValue();
                        this.f7645b.remove(entry.getKey());
                        m10171a((String) entry.getKey(), this.f7646c);
                        return;
                    }
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config fetching stopping as no more configs left to fetch.");
                    sendEmptyMessage(5);
                    return;
                case 5:
                    m10174a();
                    return;
                default:
                    return;
            }
        }

        public void m10174a() {
            if (this.f7647d != null && !this.f7647d.isShutdown()) {
                this.f7646c = null;
                this.f7645b.clear();
                removeMessages(3);
                this.f7647d.shutdownNow();
            }
        }

        private boolean m10173a(String str) {
            boolean z;
            if (this.f7645b.get(C3121b.f7651d.m10236b(str)) == null || !((Map) this.f7645b.get(C3121b.f7651d.m10236b(str))).containsKey(str)) {
                z = false;
            } else {
                z = true;
            }
            if (this.f7646c == null || !this.f7646c.containsKey(str)) {
                return z;
            }
            return true;
        }

        private void m10171a(String str, Map<String, C3045a> map) {
            int f = C3121b.f7651d.m10241f();
            Map<String, C3045a> map2 = map;
            this.f7647d.execute(new C3123d(this, new C3124e(map2, new C3169d(C3121b.f7651d.m9709r().m10168a()), str, C3121b.f7651d.m10240e(), f)));
        }

        private void m10172a(List<C3045a> list) {
            for (int i = 0; i < list.size(); i++) {
                C3045a c3045a = (C3045a) list.get(i);
                HashMap hashMap = (HashMap) this.f7645b.get(C3121b.f7651d.m10236b(c3045a.mo6231a()));
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(c3045a.mo6231a(), c3045a);
                this.f7645b.put(C3121b.f7651d.m10236b(c3045a.mo6231a()), hashMap);
            }
        }

        public void mo6260a(ConfigResponse configResponse) {
            C3122c c3122c = new C3122c();
            if (configResponse.m10160d()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config fetching failed:" + configResponse.m10156a().mo6231a() + ", Error code:" + configResponse.m10159c().m10153a());
            } else if (configResponse.m10158b() == ConfigResponseStatus.NOT_MODIFIED) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config not modified status from server:" + configResponse.m10156a().mo6231a());
                c3122c.m10196a(configResponse.m10156a().mo6231a(), System.currentTimeMillis());
            } else {
                c3122c.m10195a(configResponse.m10156a());
                try {
                    Map hashMap = new HashMap();
                    hashMap.put("configName", configResponse.m10156a().mo6231a());
                    hashMap.put("latency", String.valueOf(Integer.MAX_VALUE));
                    C3135c.m10255a().m10280a("root", "ConfigFetched", hashMap);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                }
                try {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config cached successfully:" + configResponse.m10156a().mo6231a());
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3121b.f7648a, "Config cached successfully:" + configResponse.m10156a().mo6233b().toString());
                    C3121b.m10183b(configResponse.m10156a());
                } catch (JSONException e2) {
                }
            }
        }

        public void mo6261b() {
            sendEmptyMessage(4);
        }
    }

    /* compiled from: ConfigComponent */
    static class C3120c implements C2911b {
        C3120c() {
        }

        public void mo6102a(C3045a c3045a) {
            C3121b.f7651d = (C3128g) c3045a;
        }
    }

    public static C3121b m10178a() {
        C3121b c3121b = f7652e;
        if (c3121b == null) {
            synchronized (f7649b) {
                c3121b = f7652e;
                if (c3121b == null) {
                    c3121b = new C3121b();
                    f7652e = c3121b;
                }
            }
        }
        return c3121b;
    }

    private C3121b() {
        f7650c = new HashMap();
        this.f7654g = new HandlerThread("ConfigBootstrapHandler");
        this.f7654g.start();
        this.f7655h = new C3119a(this.f7654g.getLooper());
        f7651d = new C3128g();
    }

    public synchronized void m10191b() {
        if (!this.f7656i) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Starting config component.");
            this.f7656i = true;
            C3135c.m10255a().m10281a("root", f7651d.m10245j());
            if (f7653f == null) {
                f7653f = new C3120c();
                m10190a(f7651d, f7653f);
            }
            m10189g();
        }
    }

    public synchronized void m10192c() {
        if (this.f7656i) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Stopping config component.");
            this.f7656i = false;
            this.f7655h.sendEmptyMessage(5);
        }
    }

    private void m10184b(C3045a c3045a, C2911b c2911b) {
        ArrayList arrayList;
        ArrayList arrayList2 = (ArrayList) f7650c.get(c3045a);
        if (arrayList2 == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = arrayList2;
        }
        arrayList.add(c2911b == null ? null : new WeakReference(c2911b));
        f7650c.put(c3045a, arrayList);
    }

    private void m10189g() {
        for (Entry key : f7650c.entrySet()) {
            C3045a c3045a = (C3045a) key.getKey();
            m10185c(c3045a);
            C3121b.m10183b(c3045a);
        }
    }

    private static void m10183b(C3045a c3045a) {
        ArrayList arrayList = (ArrayList) f7650c.get(c3045a);
        if (arrayList != null) {
            int i = 0;
            while (i < arrayList.size()) {
                if (!(arrayList.get(i) == null || ((WeakReference) arrayList.get(i)).get() == null)) {
                    ((C2911b) ((WeakReference) arrayList.get(i)).get()).mo6102a(c3045a);
                }
                i++;
            }
        }
    }

    public final synchronized void m10190a(C3045a c3045a, C2911b c2911b) {
        if (this.f7656i) {
            m10184b(c3045a, c2911b);
            m10185c(c3045a);
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Config component not yet started, config can't be fetched. Requested type:" + c3045a.mo6231a());
        }
    }

    public final void m10193d() {
        String a = f7651d.m10244i().m10231a();
        String b = f7651d.m10244i().m10232b();
        if (a.trim().length() != 0 && C3121b.m10182a(C3106b.m10098c(), a.trim())) {
            Logger.m10359a(InternalLogLevel.DEBUG, f7648a, "A newer version (version " + a + ") of the InMobi SDK is available! " + "You are currently on an older version (Version " + C3106b.m10098c() + "). Please download the latest InMobi SDK from " + b);
        }
    }

    private final synchronized void m10185c(C3045a c3045a) {
        C3122c c3122c = new C3122c();
        if (c3122c.m10197a(f7651d.mo6231a())) {
            c3122c.m10199b(f7651d);
            if (m10181a(c3122c.m10198b(f7651d.mo6231a()), f7651d.m10233a(f7651d.mo6231a()))) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "RootConfig expired. Fetching root.");
                m10186d(f7651d.mo6235d());
            }
            if (c3122c.m10197a(c3045a.mo6231a())) {
                c3122c.m10199b(c3045a);
                if (m10181a(c3122c.m10198b(c3045a.mo6231a()), f7651d.m10233a(c3045a.mo6231a()))) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Requested config expired. Returning currently cached and fetching. Config type:" + c3045a.mo6231a());
                    m10186d(c3045a.mo6235d());
                } else {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Serving config from cache. Config:" + c3045a.mo6231a());
                }
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "Requested config not present. Returning default and fetching. Config type:" + c3045a.mo6231a());
                m10186d(c3045a.mo6235d());
            }
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7648a, "RootConfig not available. Fetching root and returning defaults for config type:" + c3045a.mo6231a());
            m10186d(f7651d.mo6235d());
        }
    }

    private boolean m10181a(long j, long j2) {
        if (System.currentTimeMillis() - j > 1000 * j2) {
            return true;
        }
        return false;
    }

    private void m10186d(C3045a c3045a) {
        Message obtainMessage = this.f7655h.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = c3045a;
        this.f7655h.sendMessage(obtainMessage);
    }

    public static boolean m10182a(String str, String str2) {
        boolean z = true;
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < split.length) {
            try {
                if (Integer.valueOf(split[i]).intValue() < 0) {
                    return false;
                }
                i++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        for (String valueOf : split2) {
            if (Integer.valueOf(valueOf).intValue() < 0) {
                return false;
            }
        }
        i = split.length < split2.length ? split.length : split2.length;
        int i2 = 0;
        while (i2 < i) {
            if (split[i2].equals(split2[i2])) {
                i2++;
            } else {
                boolean z2;
                if (Integer.valueOf(split[i2]).intValue() < Integer.valueOf(split2[i2]).intValue()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                return z2;
            }
        }
        if (split.length >= split2.length) {
            z = false;
        }
        return z;
    }
}
