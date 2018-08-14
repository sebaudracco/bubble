package com.adcolony.sdk;

import android.util.Log;
import com.adcolony.sdk.ab.C0596a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class al {
    static final String f555a = "adcolony_android";
    static final String f556b = "adcolony_fatal_reports";
    C0803z f557c;
    ScheduledExecutorService f558d;
    List<ab> f559e = new ArrayList();
    List<ab> f560f = new ArrayList();
    HashMap<String, Object> f561g;
    private C0801x f562h = new C0801x(f555a, "3.3.2", "Production");
    private C0801x f563i = new C0801x(f556b, "3.3.2", "Production");

    class C06091 implements Runnable {
        final /* synthetic */ al f552a;

        C06091(al alVar) {
            this.f552a = alVar;
        }

        public void run() {
            try {
                this.f552a.m725b();
            } catch (Throwable e) {
                Log.e("ADCLogPOC", "RuntimeException thrown from {}#report. Exception was suppressed.", e);
            }
        }
    }

    al(C0803z c0803z, ScheduledExecutorService scheduledExecutorService, HashMap<String, Object> hashMap) {
        this.f557c = c0803z;
        this.f558d = scheduledExecutorService;
        this.f561g = hashMap;
    }

    synchronized void m724a(String str) {
        this.f561g.put("controllerVersion", str);
    }

    synchronized void m727b(String str) {
        this.f561g.put("sessionId", str);
    }

    synchronized void m721a(long j, TimeUnit timeUnit) {
        this.f558d.scheduleAtFixedRate(new C06091(this), j, j, timeUnit);
    }

    synchronized void m720a() {
        this.f558d.shutdown();
        try {
            if (!this.f558d.awaitTermination(1, TimeUnit.SECONDS)) {
                this.f558d.shutdownNow();
                if (!this.f558d.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println(getClass().getSimpleName() + ": ScheduledExecutorService did not terminate");
                }
            }
        } catch (InterruptedException e) {
            this.f558d.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    synchronized void m725b() {
        synchronized (this) {
            try {
                if (this.f559e.size() > 0) {
                    this.f557c.m1484a(m719a(this.f562h, this.f559e));
                    this.f559e.clear();
                }
                if (this.f560f.size() > 0) {
                    this.f557c.m1484a(m719a(this.f563i, this.f560f));
                    this.f560f.clear();
                }
            } catch (IOException e) {
            } catch (JSONException e2) {
            }
        }
    }

    synchronized void m728c(String str) {
        m722a(new C0596a().m627a(3).m628a(this.f562h).m629a(str).m631a());
    }

    synchronized void m729d(String str) {
        m722a(new C0596a().m627a(2).m628a(this.f562h).m629a(str).m631a());
    }

    synchronized void m730e(String str) {
        m722a(new C0596a().m627a(1).m628a(this.f562h).m629a(str).m631a());
    }

    synchronized void m731f(String str) {
        m722a(new C0596a().m627a(0).m628a(this.f562h).m629a(str).m631a());
    }

    void m723a(C0770s c0770s) {
        c0770s.m637a(this.f563i);
        c0770s.m636a(-1);
        m726b((ab) c0770s);
    }

    synchronized void m722a(final ab abVar) {
        if (!(this.f558d == null || this.f558d.isShutdown())) {
            this.f558d.submit(new Runnable(this) {
                final /* synthetic */ al f554b;

                public void run() {
                    this.f554b.f559e.add(abVar);
                }
            });
        }
    }

    synchronized void m726b(ab abVar) {
        this.f560f.add(abVar);
    }

    String m719a(C0801x c0801x, List<ab> list) throws IOException, JSONException {
        String c = C0594a.m605a().f1295c.m1312c();
        Object obj = this.f561g.get("advertiserId") != null ? (String) this.f561g.get("advertiserId") : "unknown";
        if (!(c == null || c.length() <= 0 || c.equals(obj))) {
            this.f561g.put("advertiserId", c);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(FirebaseAnalytics$Param.INDEX, c0801x.m1446b());
        jSONObject.put("environment", c0801x.m1448d());
        jSONObject.put("version", c0801x.m1447c());
        JSONArray jSONArray = new JSONArray();
        for (ab c2 : list) {
            jSONArray.put(m718c(c2));
        }
        jSONObject.put("logs", jSONArray);
        return jSONObject.toString();
    }

    private synchronized JSONObject m718c(ab abVar) throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject(this.f561g);
        jSONObject.put("environment", abVar.m642f().m1448d());
        jSONObject.put(FirebaseAnalytics$Param.LEVEL, abVar.m638b());
        jSONObject.put("message", abVar.m640d());
        jSONObject.put("clientTimestamp", abVar.m641e());
        JSONObject mediationInfo = C0594a.m605a().m1271d().getMediationInfo();
        JSONObject pluginInfo = C0594a.m605a().m1271d().getPluginInfo();
        double a = C0594a.m605a().m1284n().m1307a(C0594a.m613c());
        jSONObject.put("mediation_network", C0802y.m1468b(mediationInfo, "name"));
        jSONObject.put("mediation_network_version", C0802y.m1468b(mediationInfo, "version"));
        jSONObject.put("plugin", C0802y.m1468b(pluginInfo, "name"));
        jSONObject.put("plugin_version", C0802y.m1468b(pluginInfo, "version"));
        jSONObject.put("batteryInfo", a);
        if (abVar instanceof C0770s) {
            jSONObject = C0802y.m1456a(jSONObject, ((C0770s) abVar).m1385a());
            jSONObject.put("platform", "android");
        }
        return jSONObject;
    }
}
