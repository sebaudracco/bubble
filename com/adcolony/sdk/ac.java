package com.adcolony.sdk;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

class ac {
    static boolean f499a = false;
    static final int f500b = 4000;
    static final int f501c = 4;
    static final int f502d = 3;
    static final int f503e = 2;
    static final int f504f = 1;
    static final int f505g = 0;
    static final int f506h = -1;
    static int f507i = 3;
    static JSONObject f508j = C0802y.m1453a();
    static int f509k = 1;
    static al f510l;
    private static ExecutorService f511m = null;
    private static final Queue<Runnable> f512n = new ConcurrentLinkedQueue();

    static class C05982 implements ah {
        C05982() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 0, C0802y.m1468b(afVar.m698c(), "message"), true);
        }
    }

    static class C05993 implements ah {
        C05993() {
        }

        public void mo1863a(af afVar) {
            ac.f507i = C0802y.m1473c(afVar.m698c(), FirebaseAnalytics$Param.LEVEL);
        }
    }

    static class C06004 implements ah {
        C06004() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 3, C0802y.m1468b(afVar.m698c(), "message"), false);
        }
    }

    static class C06015 implements ah {
        C06015() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 3, C0802y.m1468b(afVar.m698c(), "message"), true);
        }
    }

    static class C06026 implements ah {
        C06026() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 2, C0802y.m1468b(afVar.m698c(), "message"), false);
        }
    }

    static class C06037 implements ah {
        C06037() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 2, C0802y.m1468b(afVar.m698c(), "message"), true);
        }
    }

    static class C06048 implements ah {
        C06048() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 1, C0802y.m1468b(afVar.m698c(), "message"), false);
        }
    }

    static class C06059 implements ah {
        C06059() {
        }

        public void mo1863a(af afVar) {
            ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 1, C0802y.m1468b(afVar.m698c(), "message"), true);
        }
    }

    ac() {
    }

    static void m656a(int i, String str, boolean z) {
        m654a(0, i, str, z);
    }

    static void m654a(int i, int i2, String str, boolean z) {
        if (f511m == null || f511m.isShutdown()) {
            synchronized (f512n) {
                f512n.add(m661b(i, i2, str, z));
            }
            return;
        }
        f511m.submit(m661b(i, i2, str, z));
    }

    private static Runnable m661b(final int i, final int i2, final String str, final boolean z) {
        return new Runnable() {
            public void run() {
                ac.m655a(i, str, i2);
                for (int i = 0; i <= str.length() / 4000; i++) {
                    int i2 = i * 4000;
                    int i3 = (i + 1) * 4000;
                    if (i3 > str.length()) {
                        i3 = str.length();
                    }
                    if (i2 == 3 && ac.m660a(C0802y.m1480f(ac.f508j, Integer.toString(i)), 3, z)) {
                        Log.d("AdColony [TRACE]", str.substring(i2, i3));
                    } else if (i2 == 2 && ac.m660a(C0802y.m1480f(ac.f508j, Integer.toString(i)), 2, z)) {
                        Log.i("AdColony [INFO]", str.substring(i2, i3));
                    } else if (i2 == 1 && ac.m660a(C0802y.m1480f(ac.f508j, Integer.toString(i)), 1, z)) {
                        Log.w("AdColony [WARNING]", str.substring(i2, i3));
                    } else if (i2 == 0 && ac.m660a(C0802y.m1480f(ac.f508j, Integer.toString(i)), 0, z)) {
                        Log.e("AdColony [ERROR]", str.substring(i2, i3));
                    } else if (i2 == -1 && ac.f507i >= -1) {
                        Log.e("AdColony [FATAL]", str.substring(i2, i3));
                    }
                }
            }
        };
    }

    static boolean m660a(JSONObject jSONObject, int i, boolean z) {
        int c = C0802y.m1473c(jSONObject, "print_level");
        boolean d = C0802y.m1477d(jSONObject, "log_private");
        if (jSONObject.length() == 0) {
            c = f507i;
            d = f499a;
        }
        if ((!z || r1) && r2 != 4 && r2 >= i) {
            return true;
        }
        return false;
    }

    static void m653a() {
        if (f511m == null || f511m.isShutdown()) {
            f511m = Executors.newSingleThreadExecutor();
        }
        synchronized (f512n) {
            while (!f512n.isEmpty()) {
                f511m.submit((Runnable) f512n.poll());
            }
        }
    }

    static void m662b() {
        f511m.shutdown();
        try {
            if (!f511m.awaitTermination(1, TimeUnit.SECONDS)) {
                f511m.shutdownNow();
                if (!f511m.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("ADCLogManager: ScheduledExecutorService did not terminate");
                }
            }
        } catch (InterruptedException e) {
            f511m.shutdownNow();
            Thread.currentThread().interrupt();
        }
        f511m = null;
    }

    static boolean m659a(JSONObject jSONObject, int i) {
        int c = C0802y.m1473c(jSONObject, "send_level");
        if (jSONObject.length() == 0) {
            c = f509k;
        }
        if (c < i || c == 4) {
            return false;
        }
        return true;
    }

    static void m658a(HashMap<String, Object> hashMap) {
        try {
            f510l = new al(new C0803z(new URL("https://wd.adcolony.com/logs")), Executors.newSingleThreadScheduledExecutor(), hashMap);
            f510l.m721a(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static void m655a(int i, String str, int i2) {
        if (f510l != null) {
            if (i2 == 3 && m659a(C0802y.m1480f(f508j, Integer.toString(i)), 3)) {
                f510l.m728c(str);
            } else if (i2 == 2 && m659a(C0802y.m1480f(f508j, Integer.toString(i)), 2)) {
                f510l.m729d(str);
            } else if (i2 == 1 && m659a(C0802y.m1480f(f508j, Integer.toString(i)), 1)) {
                f510l.m730e(str);
            } else if (i2 == 0 && m659a(C0802y.m1480f(f508j, Integer.toString(i)), 0)) {
                f510l.m731f(str);
            }
        }
    }

    static void m657a(C0770s c0770s) {
        if (f510l != null && f509k != 4) {
            f510l.m723a(c0770s);
        }
    }

    static void m663c() {
        C0594a.m609a("Log.set_log_level", new C05993());
        C0594a.m609a("Log.public.trace", new C06004());
        C0594a.m609a("Log.private.trace", new C06015());
        C0594a.m609a("Log.public.info", new C06026());
        C0594a.m609a("Log.private.info", new C06037());
        C0594a.m609a("Log.public.warning", new C06048());
        C0594a.m609a("Log.private.warning", new C06059());
        C0594a.m609a("Log.public.error", new ah() {
            public void mo1863a(af afVar) {
                ac.m654a(C0802y.m1473c(afVar.m698c(), "module"), 0, C0802y.m1468b(afVar.m698c(), "message"), false);
            }
        });
        C0594a.m609a("Log.private.error", new C05982());
    }

    void m664a(JSONArray jSONArray) {
        f508j = m665b(jSONArray);
    }

    JSONObject m665b(JSONArray jSONArray) {
        JSONObject a = C0802y.m1453a();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject d = C0802y.m1476d(jSONArray, i);
            C0802y.m1464a(a, Integer.toString(C0802y.m1473c(d, "id")), d);
        }
        return a;
    }
}
