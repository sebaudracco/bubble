package com.bgjd.ici.p029d;

import android.content.ContentResolver;
import android.database.Cursor;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p025b.C1426t;
import com.bgjd.ici.p025b.ac;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1479d;
import com.bgjd.ici.p031f.C1490c;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.List;

public class C1455f extends C1409k<JSONObject> {
    private static final String f2246f = "BRW";
    private JSONObject f2247g;
    private long f2248h;

    public /* synthetic */ Object mo2325d() {
        return m2997f();
    }

    public C1455f(C1395h c1395h) {
        super(c1395h);
        this.f2247g = null;
        this.f2248h = 0;
        this.b = "browsing";
        this.e = C1406b.f2152b;
    }

    public JSONObject m2997f() {
        int i;
        long currentTimeMillis;
        Object jSONArray;
        Object jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        C1402i.m2901b(f2246f, "Started...");
        if (ac.m2862a(this.a.getContext(), C1404b.ai)) {
            ContentResolver contentResolver = this.a.getContext().getContentResolver();
            List<C1426t> k = this.a.mo2269k();
            int browsingLimit = this.a.getBrowsingLimit();
            this.f2247g = this.a.getBrowserDate();
            if (IsSandbox || !isAccepted) {
                for (C1426t c1426t : k) {
                    String b = c1426t.m2945b();
                    int i2 = 0;
                    int i3 = 0;
                    Cursor query;
                    try {
                        query = contentResolver.query(c1426t.m2946c(), c1426t.m2944a(), String.format(" _id >= %d ", new Object[]{Integer.valueOf(0)}), null, null);
                        if (query != null) {
                            if (query.moveToFirst()) {
                                while (!query.isAfterLast()) {
                                    C1454e c1454e = new C1454e(query);
                                    c1454e.m2994a(b);
                                    if (c1454e.m2993a() == 1) {
                                        i2++;
                                    } else {
                                        i3++;
                                    }
                                    this.c++;
                                    query.moveToNext();
                                }
                            }
                            i = i3;
                            i3 = i2;
                            try {
                                query.close();
                            } catch (Exception e) {
                                i2 = i3;
                                i3 = i;
                                i = i3;
                                i3 = i2;
                                currentTimeMillis = System.currentTimeMillis();
                                jSONArray = new JSONArray();
                                jSONObject = new JSONObject();
                                jSONObject.put("a", (Object) "bookmarked.com");
                                jSONObject.put("b", (Object) "http://bookmarked.com");
                                jSONObject.put("c", 1);
                                jSONObject.put("d", i3);
                                jSONObject.put("e", i);
                                jSONArray.put(jSONObject);
                                this.f2247g.put(b, currentTimeMillis);
                                jSONObject2.put(b, jSONArray);
                            }
                        } else {
                            i = 0;
                            i3 = 0;
                        }
                    } catch (Exception e2) {
                    } catch (Throwable th) {
                        query.close();
                    }
                    try {
                        currentTimeMillis = System.currentTimeMillis();
                        jSONArray = new JSONArray();
                        jSONObject = new JSONObject();
                        jSONObject.put("a", (Object) "bookmarked.com");
                        jSONObject.put("b", (Object) "http://bookmarked.com");
                        jSONObject.put("c", 1);
                        jSONObject.put("d", i3);
                        jSONObject.put("e", i);
                        jSONArray.put(jSONObject);
                        this.f2247g.put(b, currentTimeMillis);
                        jSONObject2.put(b, jSONArray);
                    } catch (JSONException e3) {
                    }
                }
            } else {
                C1434d c1435a = new C1435a(new C1410m(this.a));
                c1435a.open();
                if (c1435a.IsConnected()) {
                    long j;
                    C1436e a;
                    C1479d c1479d;
                    C1490c c1490c = (C1490c) c1435a.getMapper(C1490c.class, C1479d.class);
                    for (C1426t c1426t2 : k) {
                        j = 0;
                        if (this.f2247g.has(c1426t2.m2945b())) {
                            try {
                                j = this.f2247g.getLong(c1426t2.m2945b());
                            } catch (JSONException e4) {
                            }
                        } else {
                            try {
                                this.f2247g.put(c1426t2.m2945b(), j);
                            } catch (JSONException e5) {
                            }
                        }
                        if (j > 0) {
                            Object jSONArray2 = new JSONArray();
                            a = c1490c.m3176a(1, c1426t2.m2945b(), j, browsingLimit);
                            while (a.read()) {
                                c1479d = (C1479d) a.fetch();
                                jSONArray2.put(c1479d.m3077a());
                                this.c++;
                                this.f2248h = (long) c1479d.m3079c();
                            }
                            try {
                                jSONObject2.put(c1426t2.m2945b(), jSONArray2);
                                if (this.f2248h == 0) {
                                    this.f2248h = j;
                                }
                                this.f2247g.put(c1426t2.m2945b(), this.f2248h);
                            } catch (JSONException e6) {
                            }
                            a.close();
                        }
                    }
                    if (this.c < 150) {
                        for (C1426t c1426t22 : k) {
                            String b2 = c1426t22.m2945b();
                            long j2 = 0;
                            if (this.c < 150) {
                                if (this.f2247g.has(b2)) {
                                    try {
                                        j = this.f2247g.getLong(b2);
                                    } catch (JSONException e7) {
                                        j = j2;
                                    }
                                } else {
                                    j = j2;
                                }
                                this.f2248h = 0;
                                Cursor query2;
                                try {
                                    query2 = contentResolver.query(c1426t22.m2946c(), c1426t22.m2944a(), String.format(" _id >= %d ", new Object[]{Long.valueOf(j)}), null, String.format(" _id ASC LIMIT %d ", new Object[]{Integer.valueOf(HttpStatus.SC_MULTIPLE_CHOICES)}));
                                    if (query2 != null) {
                                        if (query2.moveToFirst()) {
                                            while (!query2.isAfterLast()) {
                                                C1454e c1454e2 = new C1454e(query2);
                                                c1454e2.m2994a(b2);
                                                if (c1454e2.m2995b().length() > 0) {
                                                    c1490c.m3178a(c1454e2.m2995b());
                                                }
                                                query2.moveToNext();
                                            }
                                        }
                                        query2.close();
                                    }
                                } catch (Exception e8) {
                                } catch (Throwable th2) {
                                    query2.close();
                                }
                                a = c1490c.m3176a(0, c1426t22.m2945b(), j, browsingLimit - this.c);
                                Object jSONArray3 = new JSONArray();
                                while (a.read()) {
                                    c1479d = (C1479d) a.fetch();
                                    if (c1490c.m3174a(c1479d.m3080d()) > 0) {
                                        jSONArray3.put(c1479d.m3077a());
                                        this.c++;
                                        this.f2248h = (long) c1479d.m3079c();
                                    }
                                }
                                a.close();
                                try {
                                    jSONObject2.put(c1426t22.m2945b(), jSONArray3);
                                    if (this.f2248h == 0) {
                                        this.f2248h = j;
                                    }
                                    this.f2247g.put(c1426t22.m2945b(), this.f2248h);
                                } catch (JSONException e9) {
                                }
                            }
                        }
                    }
                }
                c1435a.close();
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        C1402i.m2901b(f2246f, "Completed Total count : " + this.c);
        if (!(IsSandbox || isAccepted)) {
            try {
                i = this.a.getDeclinedTransaction(C1406b.f2152b);
                if (i > 0) {
                    this.c = i < this.c ? this.c : 0;
                }
            } catch (JSONException e10) {
            }
        }
        jSONObject3.put("a", (Object) jSONObject2);
        jSONObject3.put("b", this.c);
        jSONObject3.put("c", this.f2247g);
        if (!(IsSandbox || isAccepted || this.c <= 0)) {
            this.a.setDeclinedTransaction(C1406b.f2152b, this.c);
        }
        this.a.setTransactionHistory(mo2298a(), Long.valueOf(mo2300c()));
        return jSONObject3;
    }
}
