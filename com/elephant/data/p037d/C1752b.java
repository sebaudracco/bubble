package com.elephant.data.p037d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.elephant.data.ElephantLib;
import com.elephant.data.p035a.p036a.C1715d;
import com.elephant.data.p035a.p036a.C1716e;
import com.elephant.data.p035a.p036a.C1719h;
import com.elephant.data.p037d.p038b.C1744a;
import com.elephant.data.p037d.p042a.C1742a;
import com.elephant.data.p039b.p040a.C1731a;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import com.elephant.data.p048g.p050b.C1812a;
import com.elephant.data.p048g.p050b.C1813b;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public final class C1752b implements Runnable {
    private Context f3630a;
    private String f3631b;
    private String f3632c;
    private int f3633d;
    private C1804d f3634e;
    private C1744a f3635f;
    private C1742a f3636g;

    static {
        String str = C1814b.hO;
    }

    public C1752b(Context context, C1744a c1744a, int i) {
        this.f3630a = context;
        this.f3635f = c1744a;
        this.f3633d = 1;
        this.f3634e = C1806f.m5221a(context).m5223a();
        if (this.f3635f != null) {
            this.f3632c = this.f3635f.f3601a;
        }
    }

    public C1752b(Context context, String str, int i) {
        this.f3630a = context;
        this.f3632c = str;
        this.f3633d = i;
        this.f3634e = C1806f.m5221a(context).m5223a();
    }

    private Map m5050a() {
        Map hashMap = new HashMap();
        try {
            Object obj;
            String str;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C1814b.hX, m5052b());
            JSONObject jSONObject2 = new JSONObject();
            if (this.f3635f == null || TextUtils.isEmpty(this.f3635f.f3604d)) {
                obj = "";
            } else {
                str = this.f3635f.f3604d;
            }
            Object obj2 = (this.f3635f == null || TextUtils.isEmpty(this.f3635f.f3603c)) ? "" : this.f3635f.f3603c;
            jSONObject2.put(C1814b.hY, obj);
            jSONObject2.put(C1814b.hZ, obj2);
            if (!(this.f3635f == null || TextUtils.isEmpty(this.f3635f.f3603c) || !this.f3634e.m5205k())) {
                String a = C1816d.m5281a(this.f3635f.f3603c, this.f3634e.m5206l());
                if (!TextUtils.isEmpty(a)) {
                    jSONObject2.put(C1814b.ia, C1816d.m5290b(a));
                }
            }
            jSONObject.put(C1814b.ib, jSONObject2);
            obj = C1810b.m5233a(jSONObject.toString().getBytes(C1814b.ic), C1814b.id);
            obj2 = jSONObject.toString();
            if (!TextUtils.isEmpty(obj)) {
                obj2 = C1816d.m5284a(obj.getBytes());
            }
            str = C1816d.m5290b(C1814b.ie + jSONObject.toString() + C1814b.ie);
            hashMap.put(C1814b.f3891if, obj2);
            hashMap.put(C1814b.ig, C1814b.ih);
            hashMap.put(C1814b.ii, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    private void m5051a(C1765c c1765c) {
        if (!TextUtils.isEmpty(c1765c.m5087d())) {
            if (TextUtils.isEmpty(this.f3632c) && !TextUtils.isEmpty(c1765c.m5087d()) && this.f3633d == 1) {
                C1813b.m5256a(this.f3630a, c1765c.m5087d(), this.f3636g.toString());
            }
            C1804d a = C1806f.m5221a(this.f3630a).m5223a();
            long d = C1816d.m5298d(this.f3630a, c1765c.m5087d());
            if (a.m5207m() > 0 && d > ((long) a.m5207m())) {
                return;
            }
            if (c1765c.m5088e() == 200 && this.f3633d == 0) {
                Object b = C1810b.m5236b(C1812a.m5247b(this.f3630a).getString(C1715d.f3511a + this.f3632c, null), C1814b.hW);
                if (!TextUtils.isEmpty(b)) {
                    C1719h.m4964a(this.f3630a, this.f3632c, new C1743a(b).m5022c());
                    C1812a.m5244a(this.f3630a, C1715d.f3511a + c1765c.m5087d());
                }
            } else if (c1765c.m5088e() != HttpStatus.SC_MULTIPLE_CHOICES || this.f3633d != 1) {
                System.currentTimeMillis();
                if (this.f3633d == 1 && this.f3634e.m5193a() && this.f3634e.m5200f() > 0) {
                    try {
                        Thread.sleep(this.f3634e.m5200f());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.currentTimeMillis();
                C1743a c1743a = new C1743a(c1765c.m5089f(), c1765c.m5087d(), c1765c.m5086c(), 0, 0);
                c1743a.m5020a(true);
                C1767d.m5094a(this.f3630a, c1743a, new C1715d(this.f3630a, c1765c.m5087d(), this.f3633d), false, c1765c.m5085b());
            }
        }
    }

    private JSONObject m5052b() {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(this.f3632c)) {
                this.f3632c = "";
            }
            jSONObject.put(C1814b.ij, this.f3631b);
            jSONObject.put(C1814b.ik, this.f3632c);
            jSONObject.put(C1814b.il, System.currentTimeMillis());
            jSONObject.put(C1814b.im, C1816d.m5294c(this.f3630a));
            jSONObject.put(C1814b.in, C1813b.m5258b(this.f3630a));
            jSONObject.put(C1814b.io, C1816d.m5302e(this.f3630a));
            jSONObject.put(C1814b.ip, C1816d.m5318i(this.f3630a));
            jSONObject.put(C1814b.iq, C1816d.m5332w(this.f3630a));
            jSONObject.put(C1814b.ir, ElephantLib.SDK_NAME);
            jSONObject.put(C1814b.is, "226");
            jSONObject.put(C1814b.it, C1814b.iu);
            jSONObject.put(C1814b.iv, C1816d.m5285a() ? 1 : 0);
            String str = C1814b.iw;
            if (!C1816d.m5322m(this.f3630a)) {
                i = 0;
            }
            jSONObject.put(str, i);
            jSONObject.put(C1814b.ix, 0);
            jSONObject.put(C1814b.iy, C1816d.m5299d());
            jSONObject.put(C1814b.iz, C1816d.m5295c(this.f3630a, this.f3632c));
            jSONObject.put(C1814b.iA, C1816d.m5278a(this.f3630a, this.f3632c));
            jSONObject.put(C1814b.iB, C1814b.iC);
            jSONObject.put(C1814b.iD, this.f3633d);
            jSONObject.put(C1814b.iE, String.valueOf(C1816d.m5321l(this.f3630a)));
            C1804d a = C1806f.m5221a(this.f3630a).m5223a();
            if (a.m5193a()) {
                jSONObject.put(C1814b.iF, a.m5195b());
            }
            jSONObject.put(C1814b.iG, String.valueOf(C1813b.m5269f(this.f3630a)));
            jSONObject.put(C1814b.iH, C1816d.m5298d(this.f3630a, this.f3632c));
            jSONObject.put(C1814b.iI, C1816d.m5301e());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public final void run() {
        try {
            SharedPreferences b = C1812a.m5247b(this.f3630a);
            Editor edit = b.edit();
            for (Entry entry : b.getAll().entrySet()) {
                try {
                    if (System.currentTimeMillis() - C1742a.m5013a(C1810b.m5236b(String.valueOf(entry.getValue()), C1814b.hV)).m5014a() >= 86400000) {
                        edit.remove((String) entry.getKey());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            edit.commit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f3631b = C1813b.m5252a(this.f3630a);
        if (!TextUtils.isEmpty(this.f3631b)) {
            Object obj = !TextUtils.isEmpty(this.f3632c) ? this.f3632c : (this.f3635f == null || TextUtils.isEmpty(this.f3635f.f3604d)) ? null : this.f3635f.f3604d;
            if (!TextUtils.isEmpty(obj)) {
                synchronized (obj.intern()) {
                    try {
                        String str;
                        JSONObject jSONObject;
                        JSONObject optJSONObject;
                        int i;
                        int optInt;
                        JSONArray jSONArray;
                        int i2;
                        C1765c c1765c;
                        Map a = m5050a();
                        if (this.f3633d == 0) {
                            obj = C1812a.m5247b(this.f3630a).getString(this.f3632c, null);
                            if (!TextUtils.isEmpty(obj)) {
                                String b2 = C1810b.m5236b(obj, C1814b.hP);
                                C1812a.m5244a(this.f3630a, this.f3632c);
                                str = b2;
                                if (TextUtils.isEmpty(str)) {
                                    jSONObject = new JSONObject(new C1716e().m4957a(C1731a.f3562e, a, C1816d.m5306f(this.f3630a)));
                                    optJSONObject = jSONObject.optJSONObject(C1814b.hQ);
                                    str = jSONObject.optString(C1814b.hR);
                                    i = -1;
                                    if (optJSONObject == null) {
                                        optInt = optJSONObject.optInt(C1814b.hS);
                                        i = optJSONObject.optInt(C1814b.hT);
                                    } else {
                                        optInt = 0;
                                    }
                                    if (TextUtils.isEmpty(str) && r3 == 200) {
                                        if (i == 1) {
                                            str = C1816d.m5280a(str);
                                        }
                                        if (i == 2) {
                                            str = C1810b.m5236b(C1816d.m5280a(str), C1814b.hU);
                                        }
                                        if (!TextUtils.isEmpty(str)) {
                                            str = new C1742a(System.currentTimeMillis(), str).toString();
                                        }
                                    } else {
                                        return;
                                    }
                                }
                                if (TextUtils.isEmpty(str)) {
                                    if (!TextUtils.isEmpty(this.f3632c) && this.f3633d == 1) {
                                        C1813b.m5256a(this.f3630a, this.f3632c, str);
                                    }
                                    this.f3636g = C1742a.m5013a(str);
                                    jSONArray = new JSONArray(this.f3636g.m5015b());
                                    for (i2 = 0; i2 < jSONArray.length(); i2++) {
                                        c1765c = new C1765c(jSONArray.optJSONObject(i2));
                                        if (!TextUtils.isEmpty(c1765c.m5086c())) {
                                            switch (c1765c.m5084a()) {
                                                case 0:
                                                    if (this.f3633d == 1) {
                                                        break;
                                                    }
                                                    new C1767d(this.f3630a, c1765c.m5086c()).m4951c();
                                                    break;
                                                case 1:
                                                    m5051a(c1765c);
                                                    break;
                                                case 2:
                                                    m5051a(c1765c);
                                                    break;
                                                case 3:
                                                    if (this.f3633d == 1) {
                                                        break;
                                                    }
                                                    C1816d.m5307f(this.f3630a, c1765c.m5086c());
                                                    break;
                                                default:
                                                    continue;
                                            }
                                        }
                                    }
                                } else {
                                    return;
                                }
                            }
                        }
                        str = null;
                        if (TextUtils.isEmpty(str)) {
                            jSONObject = new JSONObject(new C1716e().m4957a(C1731a.f3562e, a, C1816d.m5306f(this.f3630a)));
                            optJSONObject = jSONObject.optJSONObject(C1814b.hQ);
                            str = jSONObject.optString(C1814b.hR);
                            i = -1;
                            if (optJSONObject == null) {
                                optInt = 0;
                            } else {
                                optInt = optJSONObject.optInt(C1814b.hS);
                                i = optJSONObject.optInt(C1814b.hT);
                            }
                            if (TextUtils.isEmpty(str)) {
                            }
                            return;
                        }
                        if (TextUtils.isEmpty(str)) {
                            C1813b.m5256a(this.f3630a, this.f3632c, str);
                            this.f3636g = C1742a.m5013a(str);
                            jSONArray = new JSONArray(this.f3636g.m5015b());
                            for (i2 = 0; i2 < jSONArray.length(); i2++) {
                                c1765c = new C1765c(jSONArray.optJSONObject(i2));
                                if (!TextUtils.isEmpty(c1765c.m5086c())) {
                                    switch (c1765c.m5084a()) {
                                        case 0:
                                            if (this.f3633d == 1) {
                                                new C1767d(this.f3630a, c1765c.m5086c()).m4951c();
                                                break;
                                            }
                                            break;
                                        case 1:
                                            m5051a(c1765c);
                                            break;
                                        case 2:
                                            m5051a(c1765c);
                                            break;
                                        case 3:
                                            if (this.f3633d == 1) {
                                                C1816d.m5307f(this.f3630a, c1765c.m5086c());
                                                break;
                                            }
                                            break;
                                        default:
                                            continue;
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
            } else {
                return;
            }
        }
        return;
    }
}
