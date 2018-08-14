package com.bgjd.ici.p029d;

import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.ac;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C1456g extends C1409k<JSONObject> {
    private static final String f2249f = "PROP";
    private int f2250g;

    public /* synthetic */ Object mo2325d() {
        return m3000f();
    }

    public C1456g(C1395h c1395h) {
        super(c1395h);
        this.f2250g = 0;
        this.b = C1403a.f2088r;
        this.e = C1406b.f2160j;
    }

    public JSONObject m3000f() {
        Object a;
        JSONObject jSONObject;
        String[] y = this.a.mo2294y();
        C1402i.m2901b(f2249f, "Started...");
        if (y != null) {
            a = m2998a(y);
        } else {
            a = null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject("{\"a\":{},\"b\":0,\"c\":0}");
            try {
                if (this.f2250g > 0) {
                    try {
                        if (this.f2250g > this.a.mo2187N()) {
                            this.c = this.f2250g;
                            jSONObject2.put("a", a);
                            jSONObject2.put("b", this.f2250g);
                            jSONObject2.put("c", System.currentTimeMillis() / 1000);
                            this.a.mo2213b(this.f2250g);
                        }
                    } catch (JSONException e) {
                    }
                }
                jSONObject = jSONObject2;
            } catch (JSONException e2) {
                jSONObject = jSONObject2;
            }
        } catch (JSONException e3) {
            jSONObject = null;
        }
        C1402i.m2901b(f2249f, "Completed Total count : " + this.c);
        return jSONObject;
    }

    private JSONObject m2998a(String[] strArr) {
        BufferedReader bufferedReader;
        Throwable th;
        int declinedTransaction;
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            try {
                String str = "\\[|\\]|\\s\\[";
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    String[] split = readLine.split(":");
                    if (split.length == 2) {
                        String replaceAll = split[0].replaceAll(str, "");
                        String replaceAll2 = split[1].replaceAll(str, "");
                        for (String matches : strArr) {
                            if (replaceAll.matches(matches) && !jSONObject.has(replaceAll)) {
                                if (!IsSandbox && isAccepted) {
                                    try {
                                        jSONObject.put(ac.m2876e(replaceAll), ac.m2876e(replaceAll2));
                                    } catch (JSONException e) {
                                    }
                                }
                                this.f2250g++;
                                break;
                            }
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (IOException e3) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                }
            }
            declinedTransaction = this.a.getDeclinedTransaction(C1406b.f2160j);
            if (declinedTransaction > 0) {
                if (declinedTransaction < this.f2250g) {
                    i = this.f2250g;
                }
                this.f2250g = i;
            }
            if (this.f2250g > 0) {
                this.a.setDeclinedTransaction(C1406b.f2160j, this.f2250g);
            }
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
            return jSONObject;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                }
            }
            throw th;
        }
        if (!(IsSandbox || isAccepted)) {
            declinedTransaction = this.a.getDeclinedTransaction(C1406b.f2160j);
            if (declinedTransaction > 0) {
                if (declinedTransaction < this.f2250g) {
                    i = this.f2250g;
                }
                this.f2250g = i;
            }
            if (this.f2250g > 0) {
                this.a.setDeclinedTransaction(C1406b.f2160j, this.f2250g);
            }
            this.a.setTransactionHistory(mo2298a(), Long.valueOf((long) mo2299b()));
        }
        return jSONObject;
    }
}
