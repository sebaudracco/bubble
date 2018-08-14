package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import com.coremedia.iso.boxes.MetaBox;
import com.coremedia.iso.boxes.UserBox;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ADCCrashReportManager {
    static boolean f308a = false;
    static final int f309b = 256;
    private static final String f310d = "fatalLog.txt";
    private static final String f311e = "ad_cache_report.txt";
    private static final String f312f = "com.adcolony.sdk";
    private static final String f313m = "com.adcolony.crashreports";
    private static final String f314n = "current.crash";
    private boolean f315c = false;
    private String f316g;
    private String f317h;
    private UncaughtExceptionHandler f318i;
    private List<C0770s> f319j;
    private JSONArray f320k;
    private JSONObject f321l;

    private class C0572a implements UncaughtExceptionHandler {
        final /* synthetic */ ADCCrashReportManager f307a;

        private C0572a(ADCCrashReportManager aDCCrashReportManager) {
            this.f307a = aDCCrashReportManager;
        }

        public void uncaughtException(Thread thread, Throwable exception) {
            new C0595a().m622a("Caught exception.").m624a(aa.f478b);
            this.f307a.m506a(exception);
            this.f307a.f318i.uncaughtException(thread, exception);
        }
    }

    public native void initNativeCrashReporter(byte[] bArr);

    ADCCrashReportManager() {
    }

    synchronized void m505a() {
        if (!this.f315c) {
            new C0595a().m622a("Configuring Crash Reporter").m624a(aa.f480d);
            if (f308a) {
                this.f318i = Thread.getDefaultUncaughtExceptionHandler();
                UncaughtExceptionHandler c0572a = new C0572a();
                new C0595a().m622a("adding exception handler.").m624a(aa.f478b);
                Thread.setDefaultUncaughtExceptionHandler(c0572a);
                try {
                    this.f317h = m503k();
                    initNativeCrashReporter(this.f317h.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    new C0595a().m622a(e.getMessage()).m624a(aa.f484h);
                    this.f315c = false;
                }
            }
            this.f316g = C0594a.m605a().m1285o().m785e() + f310d;
            this.f319j = new ArrayList();
            this.f320k = C0802y.m1469b();
            m511d();
            this.f315c = true;
        }
    }

    synchronized void m508b() {
        if (f308a) {
            m509c();
            m502j();
            m501i();
        }
    }

    synchronized void m509c() {
        synchronized (this) {
            try {
                boolean a = C0594a.m605a().m1280j().m1408a(this.f316g);
                boolean a2 = C0594a.m605a().m1280j().m1408a(this.f317h);
                if (a) {
                    StringBuilder a3 = C0594a.m605a().m1280j().m1401a(this.f316g, false);
                    JSONArray g = C0802y.m1481g(C0802y.m1454a(a3.toString()), "crashList");
                    for (int i = 0; i < g.length(); i++) {
                        JSONObject jSONObject = g.getJSONObject(i);
                        new C0595a().m622a("Log read from disk: ").m622a(jSONObject.toString()).m624a(aa.f478b);
                        this.f319j.add(new C0770s().m1384a(jSONObject));
                    }
                    new C0595a().m622a("Contents of crash Reporting file: ").m622a(a3.toString()).m624a(aa.f478b);
                } else {
                    new C0595a().m622a("Java Crash log doesn't exist.").m624a(aa.f478b);
                }
                if (a2) {
                    this.f319j.add(new C0770s().m1384a(m497a(C0594a.m605a().m1280j().m1409b(this.f317h, true))));
                } else {
                    new C0595a().m622a("Native Crash log doesn't exist.").m624a(aa.f478b);
                }
            } catch (Exception e) {
                new C0595a().m622a("Exception occurred when retrieving logs. Exception Msg: ").m622a(e.getMessage()).m624a(aa.f484h);
            }
        }
    }

    synchronized void m511d() {
        this.f321l = C0802y.m1453a();
        try {
            String str = C0594a.m605a().m1285o().m785e() + f311e;
            if (C0594a.m605a().m1280j().m1408a(str)) {
                this.f321l = C0802y.m1475c(str);
            }
        } catch (Exception e) {
            new C0595a().m622a("Exception occurred when retrieving ad-cache log. Exception Msg: ").m622a(e.getMessage()).m624a(aa.f484h);
        }
    }

    JSONArray m512e() {
        return this.f320k;
    }

    List<C0770s> m513f() {
        return this.f319j;
    }

    synchronized void m506a(Throwable th) {
        String str = null;
        synchronized (this) {
            new C0595a().m622a("Writing crash log...").m624a(aa.f478b);
            if (th != null) {
                String str2 = "";
                JSONArray b = C0802y.m1469b();
                StackTraceElement a = m495a(th.getStackTrace(), b);
                if (a == null) {
                    Throwable cause = th.getCause();
                    if (cause != null) {
                        b = C0802y.m1469b();
                        a = m495a(cause.getStackTrace(), b);
                        if (a != null) {
                            str = cause.getMessage();
                        }
                    }
                    a = null;
                } else {
                    str = th.getMessage();
                }
                if (!(a == null || str == null)) {
                    String className = a.getClassName();
                    String methodName = a.getMethodName();
                    int lineNumber = a.getLineNumber();
                    JSONObject a2 = C0802y.m1453a();
                    C0802y.m1462a(a2, "timestamp", Long.toString(System.currentTimeMillis()));
                    C0802y.m1462a(a2, "message", str);
                    C0802y.m1462a(a2, "sourceFile", className);
                    C0802y.m1472b(a2, "lineNumber", lineNumber);
                    C0802y.m1462a(a2, "methodName", methodName);
                    C0802y.m1463a(a2, "stackTrace", b);
                    m498d(a2);
                    new C0595a().m622a("saving to disk...").m624a(aa.f478b);
                    m510c(a2);
                    m500h();
                }
                new C0595a().m622a("..printing stacktrace").m624a(aa.f478b);
                th.printStackTrace();
            }
        }
    }

    synchronized void m514g() {
        if (f308a) {
            String c;
            JSONObject a = C0802y.m1453a();
            C0673c s = C0594a.m605a().m1289s();
            if (s != null) {
                AdColonyInterstitial adColonyInterstitial = (AdColonyInterstitial) C0594a.m605a().m1283m().m1155c().get(s.m1053b());
                String b = adColonyInterstitial == null ? "" : adColonyInterstitial.m569b();
                c = adColonyInterstitial == null ? "" : adColonyInterstitial.m573c();
                C0802y.m1465a(a, "isAdActive", true);
                C0802y.m1462a(a, "activeAdId", b);
                C0802y.m1462a(a, "active_creative_ad_id", c);
            } else {
                C0802y.m1465a(a, "isAdActive", false);
                C0802y.m1462a(a, "activeAdId", "");
                C0802y.m1462a(a, "active_creative_ad_id", "");
            }
            try {
                c = C0594a.m605a().m1285o().m785e() + "422de421e0f4e019426b9abfd780746bc40740eb";
                if (C0594a.m605a().m1280j().m1408a(c)) {
                    JSONObject c2 = C0802y.m1475c(c);
                    JSONArray a2 = m504a(c2);
                    JSONArray b2 = m507b(c2);
                    C0802y.m1472b(a, "adCacheSize", a2.length());
                    C0802y.m1463a(a, "listOfCachedAds", a2);
                    C0802y.m1463a(a, "listOfCreativeAdIds", b2);
                }
            } catch (Exception e) {
                new C0595a().m622a("Exception occurred in FileSystem: ").m622a(e.toString()).m624a(aa.f482f);
            }
            if (m499e(a)) {
                C0802y.m1482h(this.f321l, C0594a.m605a().m1285o().m785e() + f311e);
                new C0595a().m622a("CrashReport AdCache=").m622a(this.f321l.toString()).m624a(aa.f478b);
                this.f321l = a;
            }
        }
    }

    JSONArray m504a(JSONObject jSONObject) {
        JSONArray b = C0802y.m1469b();
        JSONArray g = C0802y.m1481g(C0802y.m1480f(jSONObject, "app"), "zones");
        for (int i = 0; i < g.length(); i++) {
            JSONArray g2 = C0802y.m1481g(C0802y.m1476d(g, i), "ads");
            for (int i2 = 0; i2 < g2.length(); i2++) {
                JSONObject f = C0802y.m1480f(C0802y.m1476d(g2, i2), "legacy");
                JSONObject f2 = C0802y.m1480f(C0802y.m1476d(g2, i2), "aurora");
                if (f.has(UserBox.TYPE)) {
                    C0802y.m1458a(b, C0802y.m1468b(f, UserBox.TYPE));
                } else {
                    C0802y.m1458a(b, C0802y.m1468b(f2, UserBox.TYPE));
                }
            }
        }
        return b;
    }

    JSONArray m507b(JSONObject jSONObject) {
        JSONArray b = C0802y.m1469b();
        JSONArray g = C0802y.m1481g(C0802y.m1480f(jSONObject, "app"), "zones");
        for (int i = 0; i < g.length(); i++) {
            JSONArray g2 = C0802y.m1481g(C0802y.m1476d(g, i), "ads");
            for (int i2 = 0; i2 < g2.length(); i2++) {
                JSONObject f = C0802y.m1480f(C0802y.m1476d(g2, i2), "legacy");
                C0802y.m1480f(C0802y.m1476d(g2, i2), "aurora");
                JSONObject f2 = C0802y.m1480f(f, MetaBox.TYPE);
                f = C0802y.m1480f(f, MetaBox.TYPE);
                if (f2.has("creative_id")) {
                    C0802y.m1458a(b, C0802y.m1468b(f2, "creative_id"));
                } else {
                    C0802y.m1458a(b, C0802y.m1468b(f, "creative_id"));
                }
            }
        }
        return b;
    }

    synchronized void m510c(JSONObject jSONObject) {
        if (this.f320k == null) {
            this.f320k = C0802y.m1469b();
        } else if (this.f320k.length() == 256) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 1; i < this.f320k.length(); i++) {
                jSONArray.put(C0802y.m1467b(this.f320k, i));
            }
            this.f320k = jSONArray;
        }
        this.f320k.put(jSONObject);
    }

    private void m500h() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1463a(a, "crashList", this.f320k);
        new C0595a().m622a("saving object to ").m622a(this.f316g).m624a(aa.f478b);
        C0802y.m1482h(a, this.f316g);
    }

    private void m498d(JSONObject jSONObject) {
        if (this.f321l != null) {
            String b = C0802y.m1468b(this.f321l, "activeAdId");
            boolean d = C0802y.m1477d(this.f321l, "isAdActive");
            int c = C0802y.m1473c(this.f321l, "adCacheSize");
            JSONArray g = C0802y.m1481g(this.f321l, "listOfCachedAds");
            String b2 = C0802y.m1468b(this.f321l, "active_creative_ad_id");
            JSONArray g2 = C0802y.m1481g(this.f321l, "listOfCreativeAdIds");
            C0802y.m1465a(jSONObject, "isAdActive", d);
            C0802y.m1462a(jSONObject, "activeAdId", b);
            C0802y.m1472b(jSONObject, "adCacheSize", c);
            C0802y.m1463a(jSONObject, "listOfCachedAds", g);
            C0802y.m1462a(jSONObject, "active_creative_ad_id", b2);
            C0802y.m1463a(jSONObject, "listOfCreativeAdIds", g2);
        }
    }

    private void m501i() {
        this.f319j = new ArrayList();
        this.f320k = C0802y.m1469b();
        try {
            C0594a.m605a().m1280j().m1407a(new File(this.f316g));
            C0594a.m605a().m1280j().m1407a(new File(this.f317h));
        } catch (Exception e) {
            new C0595a().m622a("Unable to delete log file.").m624a(aa.f482f);
        }
    }

    private void m502j() {
        for (int i = 0; i < this.f319j.size(); i++) {
            C0770s c0770s = (C0770s) this.f319j.get(i);
            new C0595a().m622a("Writing a crash log to adc-instruments").m624a(aa.f478b);
            ac.m657a(c0770s);
        }
    }

    private synchronized JSONObject m497a(List<String> list) {
        JSONObject jSONObject;
        try {
            JSONObject a = C0802y.m1453a();
            JSONArray b = C0802y.m1469b();
            JSONArray b2 = C0802y.m1469b();
            int i = 0;
            Object obj = null;
            Object obj2 = null;
            while (i < list.size()) {
                String str;
                String str2;
                Object obj3;
                String str3 = (String) list.get(i);
                int indexOf = str3.indexOf(58);
                if (indexOf < 0 || indexOf >= str3.length()) {
                    str = null;
                    str2 = null;
                } else {
                    str2 = str3.substring(0, indexOf);
                    str = str3.substring(indexOf + 1).trim();
                }
                if (str2 != null && str2.equals("signalMessage")) {
                    C0802y.m1462a(a, "message", str);
                    obj3 = obj;
                    obj = obj2;
                } else if (str2 != null && str2.equals("date")) {
                    C0802y.m1462a(a, "timestamp", str);
                    obj3 = obj;
                    obj = obj2;
                } else if (str2 != null && str2.equals("threadState")) {
                    obj3 = obj;
                    int i2 = 1;
                } else if (str2 != null && str2.equals("backtrace")) {
                    int i3 = 1;
                    obj = null;
                } else if (obj2 != null) {
                    C0802y.m1458a(b, str3);
                    obj3 = obj;
                    obj = obj2;
                } else if (obj != null) {
                    C0802y.m1458a(b2, str3);
                    obj3 = obj;
                    obj = obj2;
                } else {
                    if (str2 != null) {
                        C0802y.m1462a(a, str2, str);
                    }
                    obj3 = obj;
                    obj = obj2;
                }
                i++;
                obj2 = obj;
                obj = obj3;
            }
            C0802y.m1463a(a, "threadState", b);
            C0802y.m1463a(a, "stackTrace", b2);
            m498d(a);
            jSONObject = a;
        } catch (Exception e) {
            new C0595a().m622a("Error occurred while parsing native crash report.").m624a(aa.f484h);
            jSONObject = C0802y.m1453a();
            long currentTimeMillis = System.currentTimeMillis();
            C0802y.m1462a(jSONObject, "message", "An error occurred while paring the native crash report.");
            C0802y.m1462a(jSONObject, "timestamp", Long.toString(currentTimeMillis));
        }
        return jSONObject;
    }

    private String m503k() {
        return C0594a.m605a().m1285o().m785e() + f313m + "." + f314n;
    }

    private boolean m499e(JSONObject jSONObject) {
        if (!this.f321l.has("isAdActive") || !this.f321l.has("activeAdId") || !this.f321l.has("adCacheSize") || !this.f321l.has("listOfCachedAds")) {
            return true;
        }
        boolean z = C0802y.m1477d(this.f321l, "isAdActive") != C0802y.m1477d(jSONObject, "isAdActive");
        boolean z2;
        if (C0802y.m1468b(this.f321l, "activeAdId").equals(C0802y.m1468b(jSONObject, "activeAdId"))) {
            z2 = false;
        } else {
            z2 = true;
        }
        boolean z3;
        if (C0802y.m1473c(this.f321l, "adCacheSize") != C0802y.m1473c(jSONObject, "adCacheSize")) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z4;
        if (C0802y.m1481g(this.f321l, "listOfCachedAds").equals(C0802y.m1481g(jSONObject, "listOfCachedAds"))) {
            z4 = false;
        } else {
            z4 = true;
        }
        if (z || r3 || r4 || r5) {
            return true;
        }
        return false;
    }

    private StackTraceElement m495a(StackTraceElement[] stackTraceElementArr, JSONArray jSONArray) {
        StackTraceElement stackTraceElement = null;
        int i = 0;
        while (i < stackTraceElementArr.length) {
            StackTraceElement stackTraceElement2 = stackTraceElementArr[i];
            jSONArray.put(stackTraceElement2.toString());
            String className = stackTraceElement2.getClassName();
            new C0595a().m622a("CRASH - classname=").m622a(className).m624a(aa.f478b);
            if (!className.contains(f312f) || stackTraceElement != null) {
                stackTraceElement2 = stackTraceElement;
            }
            i++;
            stackTraceElement = stackTraceElement2;
        }
        return stackTraceElement;
    }
}
