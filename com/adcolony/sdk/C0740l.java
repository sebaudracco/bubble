package com.adcolony.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.adcolony.sdk.C0754p.C0739a;
import com.adcolony.sdk.aa.C0595a;
import com.bgjd.ici.p025b.C1408j.C1407c.C1405a;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.integralads.avid.library.adcolony.BuildConfig;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.loopj.android.http.RequestParams;
import com.mopub.common.GpsHelper;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

class C0740l implements C0739a {
    private static volatile String f1266J = "";
    static final String f1267a = "026ae9c9824b3e483fa6c71fa88f57ae27816141";
    static final String f1268b = "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5";
    static String f1269f = "https://adc3-launch.adcolony.com/v4/launch";
    private af f1270A;
    private JSONObject f1271B;
    private HashMap<String, AdColonyZone> f1272C = new HashMap();
    private HashMap<Integer, bb> f1273D = new HashMap();
    private String f1274E;
    private String f1275F;
    private String f1276G;
    private String f1277H;
    private String f1278I = "";
    private boolean f1279K;
    private boolean f1280L;
    private boolean f1281M;
    private boolean f1282N;
    private boolean f1283O;
    private boolean f1284P;
    private boolean f1285Q;
    private boolean f1286R;
    private boolean f1287S;
    private boolean f1288T;
    private boolean f1289U;
    private int f1290V;
    private int f1291W = 1;
    private final int f1292X = 120;
    private ActivityLifecycleCallbacks f1293Y;
    private ExternalAvidAdSessionContext f1294Z;
    C0747n f1295c;
    av f1296d;
    aj f1297e;
    boolean f1298g;
    private C0742m f1299h;
    private ag f1300i;
    private C0758q f1301j;
    private ao f1302k;
    private C0690d f1303l;
    private C0753o f1304m;
    private C0789t f1305n;
    private at f1306o;
    private ar f1307p;
    private ADCCrashReportManager f1308q;
    private ac f1309r;
    private C0673c f1310s;
    private bc f1311t;
    private AdColonyInterstitial f1312u;
    private AdColonyRewardListener f1313v;
    private HashMap<String, AdColonyCustomMessageListener> f1314w = new HashMap();
    private AdColonyAppOptions f1315x;
    private af f1316y;
    private boolean f1317z;

    class C07281 implements ah {
        final /* synthetic */ C0740l f1249a;

        C07281(C0740l c0740l) {
            this.f1249a = c0740l;
        }

        public void mo1863a(af afVar) {
            this.f1249a.m1262a(afVar);
        }
    }

    class C07292 implements ah {
        final /* synthetic */ C0740l f1250a;

        C07292(C0740l c0740l) {
            this.f1250a = c0740l;
        }

        public void mo1863a(af afVar) {
            int c = C0802y.m1473c(afVar.m698c(), "number");
            JSONObject a = C0802y.m1453a();
            C0802y.m1463a(a, C1405a.f2149a, az.m874a(c));
            afVar.m694a(a).m695b();
        }
    }

    class C07313 implements ah {
        final /* synthetic */ C0740l f1253a;

        C07313(C0740l c0740l) {
            this.f1253a = c0740l;
        }

        public void mo1863a(final af afVar) {
            if (C0594a.m614d()) {
                az.f755b.execute(new Runnable(this) {
                    final /* synthetic */ C07313 f1252b;

                    public void run() {
                        this.f1252b.f1253a.m1261a(C0594a.m613c(), afVar);
                    }
                });
            }
        }
    }

    class C07324 implements ah {
        final /* synthetic */ C0740l f1254a;

        C07324(C0740l c0740l) {
            this.f1254a = c0740l;
        }

        public void mo1863a(af afVar) {
            this.f1254a.f1295c.f1336g = C0802y.m1468b(afVar.m698c(), "version");
            if (ac.f510l != null) {
                ac.f510l.m724a(this.f1254a.f1295c.f1336g);
            }
            new C0595a().m622a("Controller version: ").m622a(this.f1254a.f1295c.f1336g).m624a(aa.f480d);
        }
    }

    class C07335 implements Runnable {
        final /* synthetic */ C0740l f1255a;

        C07335(C0740l c0740l) {
            this.f1255a = c0740l;
        }

        public void run() {
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "url", C0740l.f1269f);
            C0802y.m1462a(a, FirebaseAnalytics$Param.CONTENT_TYPE, RequestParams.APPLICATION_JSON);
            C0802y.m1462a(a, FirebaseAnalytics$Param.CONTENT, this.f1255a.f1295c.m1309a(this.f1255a.f1295c).toString());
            new C0595a().m622a("Launch: ").m622a(this.f1255a.f1295c.m1309a(this.f1255a.f1295c).toString()).m624a(aa.f478b);
            new C0595a().m622a("Saving Launch to ").m622a(this.f1255a.f1307p.m787g()).m622a(C0740l.f1267a).m624a(aa.f480d);
            this.f1255a.f1301j.m1354a(new C0754p(new af("WebServices.post", 0, a), this.f1255a));
        }
    }

    class C07378 implements Runnable {
        final /* synthetic */ C0740l f1264a;

        class C07361 implements Runnable {
            final /* synthetic */ C07378 f1263a;

            C07361(C07378 c07378) {
                this.f1263a = c07378;
            }

            public void run() {
                if (C0594a.m605a().m1282l().m769g()) {
                    this.f1263a.f1264a.m1213F();
                }
            }
        }

        C07378(C0740l c0740l) {
            this.f1264a = c0740l;
        }

        public void run() {
            new Handler().postDelayed(new C07361(this), (long) (this.f1264a.f1291W * 1000));
        }
    }

    class C07389 implements Runnable {
        final /* synthetic */ C0740l f1265a;

        C07389(C0740l c0740l) {
            this.f1265a = c0740l;
        }

        public void run() {
            new C0595a().m622a("Loaded library. Success=" + this.f1265a.m1216I()).m624a(aa.f478b);
        }
    }

    C0740l() {
    }

    Activity m1250a() {
        return C0594a.m613c();
    }

    void m1252a(AdColonyAppOptions adColonyAppOptions, boolean z) {
        boolean z2 = true;
        this.f1281M = z;
        this.f1315x = adColonyAppOptions;
        this.f1308q = new ADCCrashReportManager();
        this.f1300i = new ag();
        this.f1299h = new C0742m();
        this.f1301j = new C0758q();
        this.f1301j.m1352a();
        this.f1302k = new ao();
        this.f1302k.m756a();
        this.f1303l = new C0690d();
        this.f1303l.m1146a();
        this.f1304m = new C0753o();
        this.f1305n = new C0789t();
        this.f1305n.m1402a();
        this.f1309r = new ac();
        ac acVar = this.f1309r;
        ac.m663c();
        this.f1307p = new ar();
        this.f1307p.m781a();
        this.f1306o = new at();
        this.f1306o.m806a();
        this.f1295c = new C0747n();
        this.f1295c.m1314e();
        this.f1297e = new aj();
        this.f1274E = this.f1297e.m716c();
        AdColony.m535a(C0594a.m613c(), adColonyAppOptions);
        if (!z) {
            this.f1285Q = new File(this.f1307p.m787g() + f1267a).exists();
            this.f1286R = new File(this.f1307p.m787g() + f1268b).exists();
            boolean z3 = this.f1285Q && this.f1286R && C0802y.m1468b(C0802y.m1475c(this.f1307p.m787g() + f1267a), "sdkVersion").equals(this.f1295c.m1305D());
            this.f1284P = z3;
            if (this.f1285Q) {
                this.f1271B = C0802y.m1475c(this.f1307p.m787g() + f1267a);
                m1225b(this.f1271B);
            }
            m1235e(this.f1284P);
            m1217J();
        }
        C0594a.m609a("Module.load", new C07281(this));
        C0594a.m609a("Module.unload", new ah(this) {
            final /* synthetic */ C0740l f1241a;

            {
                this.f1241a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1241a.m1237f(afVar);
            }
        });
        C0594a.m609a("AdColony.on_configured", new ah(this) {
            final /* synthetic */ C0740l f1242a;

            {
                this.f1242a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1242a.f1282N = true;
                if (this.f1242a.f1288T) {
                    JSONObject a = C0802y.m1453a();
                    JSONObject a2 = C0802y.m1453a();
                    C0802y.m1462a(a2, "app_version", az.m888b());
                    C0802y.m1464a(a, "app_bundle_info", a2);
                    new af("AdColony.on_update", 1, a).m695b();
                    this.f1242a.f1288T = false;
                }
                if (this.f1242a.f1289U) {
                    new af("AdColony.on_install", 1).m695b();
                }
                if (ac.f510l != null) {
                    ac.f510l.m727b(C0802y.m1468b(afVar.m698c(), "app_session_id"));
                }
                if (AdColonyEventTracker.m560b()) {
                    AdColonyEventTracker.m556a();
                }
                int a3 = C0802y.m1449a(afVar.m698c(), "concurrent_requests", 4);
                if (a3 != this.f1242a.f1301j.m1356b()) {
                    this.f1242a.f1301j.m1353a(a3);
                }
            }
        });
        C0594a.m609a("AdColony.get_app_info", new ah(this) {
            final /* synthetic */ C0740l f1243a;

            {
                this.f1243a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1243a.m1238g(afVar);
            }
        });
        C0594a.m609a("AdColony.v4vc_reward", new ah(this) {
            final /* synthetic */ C0740l f1244a;

            {
                this.f1244a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1244a.m1273d(afVar);
            }
        });
        C0594a.m609a("AdColony.zone_info", new ah(this) {
            final /* synthetic */ C0740l f1245a;

            {
                this.f1245a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1245a.m1274e(afVar);
            }
        });
        C0594a.m609a("AdColony.probe_launch_server", new ah(this) {
            final /* synthetic */ C0740l f1246a;

            {
                this.f1246a = r1;
            }

            public void mo1863a(af afVar) {
                this.f1246a.m1223a(true, true);
            }
        });
        C0594a.m609a("Crypto.sha1", new ah(this) {
            final /* synthetic */ C0740l f1247a;

            {
                this.f1247a = r1;
            }

            public void mo1863a(af afVar) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1462a(a, "sha1", az.m892c(C0802y.m1468b(afVar.m698c(), "data")));
                afVar.m694a(a).m695b();
            }
        });
        C0594a.m609a("Crypto.crc32", new ah(this) {
            final /* synthetic */ C0740l f1248a;

            {
                this.f1248a = r1;
            }

            public void mo1863a(af afVar) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1472b(a, "crc32", az.m887b(C0802y.m1468b(afVar.m698c(), "data")));
                afVar.m694a(a).m695b();
            }
        });
        C0594a.m609a("Crypto.uuid", new C07292(this));
        C0594a.m609a("Device.query_advertiser_info", new C07313(this));
        C0594a.m609a("AdColony.controller_version", new C07324(this));
        int a = az.m868a(this.f1307p);
        this.f1288T = a == 1;
        if (a != 2) {
            z2 = false;
        }
        this.f1289U = z2;
    }

    private boolean m1212E() {
        if (this.f1281M || !this.f1295c.m1313d().contains("arm") || ADCNative.nativeNeonSupported()) {
            return true;
        }
        new C0595a().m622a("ARM architechture without NEON support. Disabling AdColony.").m624a(aa.f483g);
        m1259a(true);
        return false;
    }

    private boolean m1235e(boolean z) {
        return m1223a(z, false);
    }

    String m1263b() {
        return this.f1278I;
    }

    JSONObject m1268c() {
        return this.f1271B;
    }

    private boolean m1223a(boolean z, boolean z2) {
        if (!C0594a.m614d()) {
            return false;
        }
        File file = new File(this.f1307p.m783c() + "/../lib/libImmEndpointWarpJ.so");
        this.f1295c.f1333d = file.exists();
        this.f1287S = z2;
        this.f1284P = z;
        if (z && !z2 && !m1216I()) {
            return false;
        }
        m1213F();
        return true;
    }

    private void m1213F() {
        new Thread(new C07335(this)).start();
    }

    private boolean m1222a(JSONObject jSONObject) {
        if (!this.f1284P) {
            new C0595a().m622a("Non-standard launch. Downloading new controller.").m624a(aa.f482f);
            return true;
        } else if (this.f1271B != null && C0802y.m1468b(C0802y.m1480f(this.f1271B, "controller"), "sha1").equals(C0802y.m1468b(C0802y.m1480f(jSONObject, "controller"), "sha1"))) {
            return false;
        } else {
            new C0595a().m622a("Controller sha1 does not match, downloading new controller.").m624a(aa.f482f);
            return true;
        }
    }

    private void m1237f(af afVar) {
        m1260a(C0802y.m1473c(afVar.m698c(), "id"));
    }

    void m1259a(boolean z) {
        this.f1281M = z;
    }

    private void m1238g(af afVar) {
        JSONObject jSONObject = this.f1315x.f394d;
        C0802y.m1462a(jSONObject, "app_id", this.f1315x.f391a);
        C0802y.m1463a(jSONObject, "zone_ids", this.f1315x.f393c);
        JSONObject a = C0802y.m1453a();
        C0802y.m1464a(a, "options", jSONObject);
        afVar.m694a(a).m695b();
    }

    boolean m1262a(final af afVar) {
        if (!C0594a.m614d()) {
            return false;
        }
        try {
            int c;
            if (afVar.m698c().has("id")) {
                c = C0802y.m1473c(afVar.m698c(), "id");
            } else {
                c = 0;
            }
            if (c <= 0) {
                c = this.f1300i.m709d();
            }
            m1260a(c);
            boolean d = C0802y.m1477d(afVar.m698c(), "is_webview");
            final boolean d2 = C0802y.m1477d(afVar.m698c(), "is_display_module");
            if (d) {
                az.m880a(new Runnable(this) {
                    final /* synthetic */ C0740l f1258c;

                    public void run() {
                        ai bbVar = new bb(C0594a.m613c().getApplicationContext(), this.f1258c.f1300i.m709d(), d2);
                        ((bb) bbVar).m992a(true, afVar);
                        this.f1258c.f1273D.put(Integer.valueOf(bbVar.mo1841a()), (bb) bbVar);
                    }
                });
            } else {
                final ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                newSingleThreadExecutor.submit(new Runnable(this) {
                    final /* synthetic */ C0740l f1262d;

                    public void run() {
                        JSONObject f = C0802y.m1480f(afVar.m698c(), "info");
                        C0740l a = C0594a.m605a();
                        if (c == 1 && a.m1271d() != null) {
                            C0802y.m1464a(f, "options", a.m1271d().m554d());
                        }
                        this.f1262d.f1300i.m701a(new ADCVMModule(C0594a.m613c(), c, C0802y.m1468b(afVar.m698c(), "filepath"), f, newSingleThreadExecutor));
                    }
                });
                JSONObject a = C0802y.m1453a();
                C0802y.m1465a(a, "success", true);
                C0802y.m1472b(a, "id", c);
                afVar.m694a(a).m695b();
            }
            return true;
        } catch (RuntimeException e) {
            new C0595a().m622a("Failed to create AdUnit file://").m622a(C0802y.m1468b(afVar.m698c(), "filepath")).m624a(aa.f484h);
            new C0595a().m622a(e.toString()).m624a(aa.f484h);
            e.printStackTrace();
            return false;
        }
    }

    void m1265b(af afVar) {
        this.f1316y = afVar;
    }

    void m1269c(af afVar) {
        this.f1270A = afVar;
    }

    private void m1214G() {
        int i = 120;
        if (C0594a.m605a().m1282l().m769g()) {
            this.f1290V++;
            if (this.f1291W * this.f1290V <= 120) {
                i = this.f1291W * this.f1290V;
            }
            this.f1291W = i;
            az.m880a(new C07378(this));
            return;
        }
        new C0595a().m622a("Max launch server download attempts hit, or AdColony is no longer active.").m624a(aa.f482f);
    }

    public void mo1865a(C0754p c0754p, af afVar, Map<String, List<String>> map) {
        if (c0754p.f1353a.equals(f1269f)) {
            if (c0754p.f1355c) {
                new C0595a().m622a("Launch: ").m622a(c0754p.f1354b).m624a(aa.f478b);
                JSONObject a = C0802y.m1454a(c0754p.f1354b);
                C0802y.m1462a(a, "sdkVersion", this.f1295c.m1305D());
                if (a != null) {
                    C0802y.m1482h(a, this.f1307p.m787g() + f1267a);
                    if (m1232c(a)) {
                        if (m1222a(a)) {
                            new C0595a().m622a("Controller missing or out of date. Downloading new controller.").m624a(aa.f480d);
                            JSONObject a2 = C0802y.m1453a();
                            C0802y.m1462a(a2, "url", this.f1275F);
                            C0802y.m1462a(a2, "filepath", this.f1307p.m787g() + f1268b);
                            this.f1301j.m1354a(new C0754p(new af("WebServices.download", 0, a2), this));
                        }
                        this.f1271B = a;
                        return;
                    } else if (!this.f1284P) {
                        new C0595a().m622a("Incomplete or disabled launch server response. Disabling AdColony until next launch.").m624a(aa.f483g);
                        m1259a(true);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            m1214G();
        } else if (!c0754p.f1353a.equals(this.f1275F)) {
        } else {
            if (!m1231c(this.f1276G)) {
                new C0595a().m622a("Downloaded controller sha1 does not match expected value, retrying.").m624a(aa.f481e);
                m1214G();
            } else if (!this.f1284P && !this.f1287S) {
                az.m880a(new C07389(this));
            }
        }
    }

    private boolean m1215H() {
        if (!this.f1283O) {
            try {
                System.loadLibrary("js");
                System.loadLibrary(BuildConfig.SDK_NAME);
            } catch (UnsatisfiedLinkError e) {
                m1259a(true);
                new C0595a().m622a("Expecting libadcolony.so in libs folder but it was not found. Disabling AdColony until next launch.").m624a(aa.f483g);
                return false;
            }
        }
        this.f1283O = true;
        return true;
    }

    private boolean m1216I() {
        if (!m1215H()) {
            return false;
        }
        this.f1308q.m505a();
        this.f1308q.m508b();
        this.f1300i.m702a();
        m1212E();
        return true;
    }

    private boolean m1231c(String str) {
        if (C0594a.m614d()) {
            File file = new File(C0594a.m613c().getFilesDir().getAbsolutePath() + "/adc3/" + f1268b);
            if (file.exists()) {
                return az.m883a(str, file);
            }
        }
        return false;
    }

    boolean m1261a(Context context, af afVar) {
        if (context == null) {
            return false;
        }
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (NoClassDefFoundError e) {
            new C0595a().m622a("Google Play Services ads dependencies are missing. Collecting Android ID instead of Advertising ID.").m624a(aa.f481e);
            return false;
        } catch (NoSuchMethodError e2) {
            new C0595a().m622a("Google Play Services is out of date, please update to GPS 4.0+. Collecting Android ID instead of Advertising ID.").m624a(aa.f481e);
        } catch (Exception e3) {
            e3.printStackTrace();
            if (Build.MANUFACTURER.equals("Amazon")) {
                return false;
            }
            new C0595a().m622a("Advertising ID is not available. Collecting Android ID instead of Advertising ID.").m624a(aa.f481e);
            return false;
        }
        if (info == null) {
            return false;
        }
        this.f1295c.f1330a = info.getId();
        ac.f510l.f561g.put(GpsHelper.ADVERTISING_ID_KEY, this.f1295c.f1330a);
        this.f1295c.f1332c = info.isLimitAdTrackingEnabled();
        this.f1295c.f1331b = true;
        if (afVar != null) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "advertiser_id", this.f1295c.m1312c());
            C0802y.m1465a(a, "limit_ad_tracking", this.f1295c.m1316g());
            afVar.m694a(a).m695b();
        }
        return true;
    }

    void m1251a(AdColonyAppOptions adColonyAppOptions) {
        synchronized (this.f1303l.m1155c()) {
            for (Entry value : this.f1303l.m1155c().entrySet()) {
                AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) value.getValue();
                AdColonyInterstitialListener listener = adColonyInterstitial.getListener();
                adColonyInterstitial.m566a(true);
                if (listener != null) {
                    listener.onExpiring(adColonyInterstitial);
                }
            }
            this.f1303l.m1155c().clear();
        }
        this.f1282N = false;
        m1260a(1);
        this.f1272C.clear();
        this.f1315x = adColonyAppOptions;
        this.f1300i.m702a();
        m1223a(true, true);
    }

    boolean m1260a(int i) {
        if (this.f1300i.m700a(i) == null) {
            return false;
        }
        if (this.f1273D.containsKey(Integer.valueOf(i))) {
            bb bbVar = (bb) this.f1273D.get(Integer.valueOf(i));
            if (bbVar.m1001g()) {
                bbVar.loadUrl("about:blank");
                bbVar.clearCache(true);
                bbVar.removeAllViews();
                bbVar.m991a(true);
            }
            this.f1273D.remove(Integer.valueOf(i));
        }
        if (this.f1270A != null) {
            this.f1270A.m695b();
            this.f1270A = null;
            this.f1317z = false;
        }
        new C0595a().m622a("Destroying module with id = ").m620a(i).m624a(aa.f480d);
        return true;
    }

    private void m1225b(JSONObject jSONObject) {
        if (!ADCVMModule.f338a) {
            JSONObject f = C0802y.m1480f(jSONObject, "logging");
            ac.f509k = C0802y.m1449a(f, "send_level", 1);
            ac.f507i = C0802y.m1449a(f, "print_level", 3);
            ADCCrashReportManager.f308a = C0802y.m1477d(f, "enable_crash_reporting");
            if (ADCCrashReportManager.f308a && m1215H()) {
                this.f1308q.m505a();
                this.f1308q.m508b();
            }
            this.f1309r.m664a(C0802y.m1481g(f, "modules"));
        }
        this.f1295c.m1310a(C0802y.m1480f(jSONObject, "metadata"));
        this.f1278I = C0802y.m1468b(C0802y.m1480f(jSONObject, "controller"), "version");
    }

    private boolean m1232c(JSONObject jSONObject) {
        if (jSONObject == null) {
            new C0595a().m622a("Launch response verification failed - response is null or unknown").m624a(aa.f480d);
            return false;
        }
        try {
            JSONObject f = C0802y.m1480f(jSONObject, "controller");
            this.f1275F = C0802y.m1468b(f, "url");
            this.f1276G = C0802y.m1468b(f, "sha1");
            this.f1277H = C0802y.m1468b(jSONObject, "status");
            f1266J = C0802y.m1468b(jSONObject, "pie");
            if (AdColonyEventTracker.m560b()) {
                AdColonyEventTracker.m556a();
            }
            m1225b(jSONObject);
        } catch (Exception e) {
            try {
                new File(this.f1307p.m787g() + f1267a).delete();
            } catch (Exception e2) {
            }
        }
        if (this.f1277H.equals("disable")) {
            try {
                new File(this.f1307p.m787g() + f1268b).delete();
            } catch (Exception e3) {
            }
            new C0595a().m622a("Launch server response with disabled status. Disabling AdColony until next launch.").m624a(aa.f482f);
            m1259a(true);
            return false;
        } else if (!this.f1275F.equals("") && !this.f1277H.equals("")) {
            return true;
        } else {
            new C0595a().m622a("Missing controller status or URL. Disabling AdColony until next launch.").m624a(aa.f483g);
            return false;
        }
    }

    boolean m1273d(final af afVar) {
        if (this.f1313v == null) {
            return false;
        }
        az.m880a(new Runnable(this) {
            final /* synthetic */ C0740l f1239b;

            public void run() {
                this.f1239b.f1313v.onReward(new AdColonyReward(afVar));
            }
        });
        return true;
    }

    void m1274e(af afVar) {
        if (this.f1281M) {
            new C0595a().m622a("AdColony is disabled. Ignoring zone_info message.").m624a(aa.f482f);
            return;
        }
        AdColonyZone adColonyZone;
        String b = C0802y.m1468b(afVar.m698c(), "zone_id");
        if (this.f1272C.containsKey(b)) {
            adColonyZone = (AdColonyZone) this.f1272C.get(b);
        } else {
            adColonyZone = new AdColonyZone(b);
            this.f1272C.put(b, adColonyZone);
        }
        adColonyZone.m600a(afVar);
    }

    private void m1217J() {
        if (C0594a.m614d() && this.f1293Y == null) {
            this.f1293Y = new ActivityLifecycleCallbacks(this) {
                final /* synthetic */ C0740l f1240a;

                {
                    this.f1240a = r1;
                }

                public void onActivityResumed(Activity activity) {
                    C0594a.f473b = true;
                    C0594a.m606a(activity);
                    if (C0594a.m614d() && this.f1240a.f1302k.m766e() && (C0594a.m613c() instanceof C0589b) && !((C0589b) C0594a.m613c()).f382g) {
                        new C0595a().m622a("Ignoring onActivityResumed").m624a(aa.f480d);
                        return;
                    }
                    new C0595a().m622a("onActivityResumed() Activity Lifecycle Callback").m624a(aa.f480d);
                    C0594a.m606a(activity);
                    if (this.f1240a.f1316y != null) {
                        this.f1240a.f1316y.m694a(this.f1240a.f1316y.m698c()).m695b();
                        this.f1240a.f1316y = null;
                    }
                    this.f1240a.f1280L = false;
                    this.f1240a.f1302k.m764d(true);
                    this.f1240a.f1302k.m765e(true);
                    this.f1240a.f1302k.m767f(false);
                    if (this.f1240a.f1298g && !this.f1240a.f1302k.m769g()) {
                        this.f1240a.f1302k.m758a(true);
                    }
                    this.f1240a.f1304m.m1340a();
                    if (ac.f510l == null || ac.f510l.f558d == null || ac.f510l.f558d.isShutdown()) {
                        AdColony.m535a(activity, C0594a.m605a().f1315x);
                    }
                }

                public void onActivityPaused(Activity activity) {
                    C0594a.f473b = false;
                    this.f1240a.f1302k.m764d(false);
                    this.f1240a.f1302k.m765e(true);
                    C0594a.m605a().f1295c.m1306E();
                }

                public void onActivityDestroyed(Activity activity) {
                }

                public void onActivityStarted(Activity activity) {
                }

                public void onActivityStopped(Activity activity) {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (!this.f1240a.f1302k.m769g()) {
                        this.f1240a.f1302k.m758a(true);
                    }
                    C0594a.m606a(activity);
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }
            };
            C0594a.m613c().getApplication().registerActivityLifecycleCallbacks(this.f1293Y);
        }
    }

    AdColonyAppOptions m1271d() {
        if (this.f1315x == null) {
            this.f1315x = new AdColonyAppOptions();
        }
        return this.f1315x;
    }

    boolean m1275e() {
        return this.f1315x != null;
    }

    void m1264b(@NonNull AdColonyAppOptions adColonyAppOptions) {
        this.f1315x = adColonyAppOptions;
    }

    HashMap<String, AdColonyZone> m1276f() {
        return this.f1272C;
    }

    void m1267b(boolean z) {
        this.f1280L = z;
    }

    boolean m1277g() {
        return this.f1280L;
    }

    boolean m1278h() {
        return this.f1281M;
    }

    AdColonyRewardListener m1279i() {
        return this.f1313v;
    }

    void m1254a(AdColonyRewardListener adColonyRewardListener) {
        this.f1313v = adColonyRewardListener;
    }

    C0789t m1280j() {
        if (this.f1305n == null) {
            this.f1305n = new C0789t();
            this.f1305n.m1402a();
        }
        return this.f1305n;
    }

    ADCCrashReportManager m1281k() {
        if (this.f1308q == null) {
            this.f1308q = new ADCCrashReportManager();
        }
        this.f1308q.m505a();
        return this.f1308q;
    }

    ao m1282l() {
        if (this.f1302k == null) {
            this.f1302k = new ao();
            this.f1302k.m756a();
        }
        return this.f1302k;
    }

    C0690d m1283m() {
        if (this.f1303l == null) {
            this.f1303l = new C0690d();
            this.f1303l.m1146a();
        }
        return this.f1303l;
    }

    C0747n m1284n() {
        if (this.f1295c == null) {
            this.f1295c = new C0747n();
            this.f1295c.m1314e();
        }
        return this.f1295c;
    }

    ar m1285o() {
        if (this.f1307p == null) {
            this.f1307p = new ar();
            this.f1307p.m781a();
        }
        return this.f1307p;
    }

    aj m1286p() {
        if (this.f1297e == null) {
            this.f1297e = new aj();
        }
        return this.f1297e;
    }

    ag m1287q() {
        if (this.f1300i == null) {
            this.f1300i = new ag();
            this.f1300i.m702a();
        }
        return this.f1300i;
    }

    C0753o m1288r() {
        if (this.f1304m == null) {
            this.f1304m = new C0753o();
        }
        return this.f1304m;
    }

    C0673c m1289s() {
        return this.f1310s;
    }

    void m1256a(C0673c c0673c) {
        this.f1310s = c0673c;
    }

    bc m1290t() {
        return this.f1311t;
    }

    void m1255a(bc bcVar) {
        this.f1311t = bcVar;
    }

    AdColonyInterstitial m1291u() {
        return this.f1312u;
    }

    void m1253a(AdColonyInterstitial adColonyInterstitial) {
        this.f1312u = adColonyInterstitial;
    }

    String m1292v() {
        return this.f1274E;
    }

    void m1258a(String str) {
        this.f1274E = str;
    }

    boolean m1293w() {
        return this.f1279K;
    }

    void m1270c(boolean z) {
        this.f1279K = z;
    }

    HashMap<Integer, bb> m1294x() {
        return this.f1273D;
    }

    boolean m1295y() {
        return this.f1317z;
    }

    void m1272d(boolean z) {
        this.f1317z = z;
    }

    void m1266b(@NonNull String str) {
        f1269f = str;
    }

    HashMap<String, AdColonyCustomMessageListener> m1296z() {
        return this.f1314w;
    }

    boolean m1247A() {
        return this.f1282N;
    }

    boolean m1248B() {
        return this.f1283O;
    }

    static String m1211C() {
        return f1266J;
    }

    ExternalAvidAdSessionContext m1249D() {
        if (this.f1294Z == null) {
            this.f1294Z = new ExternalAvidAdSessionContext("3.3.2", true);
        }
        return this.f1294Z;
    }
}
