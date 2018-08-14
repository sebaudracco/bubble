package com.bgjd.ici.p029d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p025b.ac;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1486i;
import com.bgjd.ici.p031f.C1493f;

public class C1475r extends C1409k<JSONObject> {
    private static final String f2341f = "PROCS";
    private int f2342g;

    public /* synthetic */ Object mo2325d() {
        return m3063f();
    }

    public C1475r(C1395h c1395h) {
        super(c1395h);
        this.f2342g = 0;
        this.b = C1403a.f2089s;
        this.e = C1406b.f2159i;
    }

    public JSONObject m3063f() {
        Object jSONArray = new JSONArray();
        CharSequence packageName = this.a.getContext().getPackageName();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        C1402i.m2901b(f2341f, "Started...");
        if (!IsSandbox && isAccepted) {
            C1434d c1435a = new C1435a(new C1410m(this.a));
            c1435a.open();
            if (c1435a.IsConnected()) {
                C1486i c1486i;
                C1493f c1493f = (C1493f) c1435a.getMapper(C1493f.class, C1486i.class);
                for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getContext().getSystemService(C1404b.aw)).getRunningAppProcesses()) {
                    String str = runningAppProcessInfo.processName;
                    if (!str.contains(packageName)) {
                        str = ac.m2876e(str);
                        int i = runningAppProcessInfo.importance;
                        c1493f.m3191a(String.format("INSERT OR IGNORE INTO %s (processname,importance,status) VALUES('%s',%s,0)", new Object[]{C1403a.f2094x, str, Integer.valueOf(i)}));
                    }
                }
                C1436e a = c1493f.m3190a(1);
                while (a.read()) {
                    c1486i = (C1486i) a.fetch();
                    jSONArray.put(c1486i.m3139a());
                    this.c++;
                    this.f2342g = c1486i.m3140b();
                }
                a.close();
                if (this.c == 0) {
                    a = c1493f.m3190a(0);
                    while (a.read()) {
                        c1486i = (C1486i) a.fetch();
                        if (c1493f.m3192b(c1486i.m3140b()) > 0) {
                            jSONArray.put(c1486i.m3139a());
                            this.c++;
                            this.f2342g = c1486i.m3140b();
                        }
                    }
                    a.close();
                }
            }
            c1435a.close();
        } else if (!(IsSandbox || isAccepted)) {
            for (RunningAppProcessInfo runningAppProcessInfo2 : ((ActivityManager) this.a.getContext().getSystemService(C1404b.aw)).getRunningAppProcesses()) {
                if (!runningAppProcessInfo2.processName.contains(packageName)) {
                    this.c++;
                }
            }
            int declinedTransaction = this.a.getDeclinedTransaction("pr");
            if (declinedTransaction > 0) {
                if (declinedTransaction < this.c) {
                    declinedTransaction = this.c;
                } else {
                    declinedTransaction = 0;
                }
                this.c = declinedTransaction;
            }
            if (this.c > 0) {
                this.a.setDeclinedTransaction("pr", this.c);
            }
        }
        C1402i.m2901b(f2341f, "Completed Total count : " + this.c);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("a", jSONArray);
            jSONObject.put("b", jSONArray.length());
            jSONObject.put("c", this.f2342g);
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
