package com.adcolony.sdk;

import com.adcolony.sdk.C0754p.C0739a;
import com.adcolony.sdk.aa.C0595a;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

class C0758q implements C0739a {
    private BlockingQueue<Runnable> f1371a = new LinkedBlockingQueue();
    private ThreadPoolExecutor f1372b = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS, this.f1371a);

    class C07551 implements ah {
        final /* synthetic */ C0758q f1368a;

        C07551(C0758q c0758q) {
            this.f1368a = c0758q;
        }

        public void mo1863a(af afVar) {
            this.f1368a.m1354a(new C0754p(afVar, this.f1368a));
        }
    }

    class C07562 implements ah {
        final /* synthetic */ C0758q f1369a;

        C07562(C0758q c0758q) {
            this.f1369a = c0758q;
        }

        public void mo1863a(af afVar) {
            this.f1369a.m1354a(new C0754p(afVar, this.f1369a));
        }
    }

    class C07573 implements ah {
        final /* synthetic */ C0758q f1370a;

        C07573(C0758q c0758q) {
            this.f1370a = c0758q;
        }

        public void mo1863a(af afVar) {
            this.f1370a.m1354a(new C0754p(afVar, this.f1370a));
        }
    }

    C0758q() {
    }

    void m1352a() {
        C0594a.m609a("WebServices.download", new C07551(this));
        C0594a.m609a("WebServices.get", new C07562(this));
        C0594a.m609a("WebServices.post", new C07573(this));
    }

    void m1354a(C0754p c0754p) {
        try {
            this.f1372b.execute(c0754p);
        } catch (RejectedExecutionException e) {
            new C0595a().m622a("RejectedExecutionException: ThreadPoolExecutor unable to execute download for url " + c0754p.f1353a).m624a(aa.f484h);
            mo1865a(c0754p, c0754p.m1348a(), null);
        }
    }

    void m1353a(int i) {
        this.f1372b.setCorePoolSize(i);
    }

    int m1356b() {
        return this.f1372b.getCorePoolSize();
    }

    public void mo1865a(C0754p c0754p, af afVar, Map<String, List<String>> map) {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "url", c0754p.f1353a);
        C0802y.m1465a(a, "success", c0754p.f1355c);
        C0802y.m1472b(a, "status", c0754p.f1357e);
        C0802y.m1462a(a, "body", c0754p.f1354b);
        C0802y.m1472b(a, "size", c0754p.f1356d);
        if (map != null) {
            JSONObject a2 = C0802y.m1453a();
            for (Entry entry : map.entrySet()) {
                String obj = ((List) entry.getValue()).toString();
                obj = obj.substring(1, obj.length() - 1);
                if (entry.getKey() != null) {
                    C0802y.m1462a(a2, (String) entry.getKey(), obj);
                }
            }
            C0802y.m1464a(a, "headers", a2);
        }
        afVar.m694a(a).m695b();
    }
}
