package com.elephant.data.p046f;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.elephant.data.p039b.p040a.C1731a;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.p050b.C1813b;
import com.stepleaderdigital.reveal.Reveal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class C1804d {
    private boolean f3800a = false;
    private int f3801b;
    private int f3802c;
    private String f3803d;
    private Set f3804e;
    private int f3805f = 0;
    private long f3806g = 4;
    private long f3807h = Reveal.CHECK_DELAY;
    private int f3808i = 0;
    private long f3809j = 0;
    private long f3810k = 0;
    private String f3811l = C1814b.cE;
    private long f3812m = 0;
    private int f3813n = 1;
    private int f3814o = 30;
    private String f3815p = C1814b.cF;
    private int f3816q = 1;
    private String f3817r = C1814b.cG;
    private String f3818s = C1814b.cH;
    private List f3819t;
    private int f3820u = 5000;
    private int f3821v = 8;
    private List f3822w;
    private int f3823x = 8;

    static {
        String str = C1814b.cD;
    }

    public C1804d(String str) {
        JSONObject optJSONObject;
        Exception e;
        Object optString;
        String[] split;
        List arrayList;
        JSONObject jSONObject = null;
        if (str == null) {
            return;
        }
        if (!"".equals(str)) {
            JSONObject jSONObject2;
            JSONArray optJSONArray;
            String optString2;
            try {
                jSONObject2 = new JSONObject(str);
                try {
                    if (jSONObject2.optInt(C1814b.cI) == 200) {
                        this.f3805f = jSONObject2.optInt(C1814b.cJ);
                        try {
                            optJSONObject = jSONObject2.optJSONObject(C1814b.cK).optJSONObject(C1814b.cL + C1731a.f3559b);
                            this.f3801b = optJSONObject.optInt(C1814b.cM);
                            this.f3802c = optJSONObject.optInt(C1814b.cN);
                            optJSONArray = optJSONObject.optJSONArray(C1814b.cO);
                            if (optJSONArray.length() == 0) {
                                try {
                                    jSONObject = optJSONArray.getJSONObject(0);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                this.f3803d = C1731a.f3559b + C1814b.cP + this.f3801b + C1814b.cP + this.f3802c;
                                this.f3804e = new HashSet();
                                optString2 = jSONObject.optString(C1814b.cQ);
                                if (!(optString2 == null || "".equals(optString2))) {
                                    for (Object add : jSONObject.optString(C1814b.cR).split(C1814b.cS)) {
                                        this.f3804e.add(add);
                                    }
                                }
                                this.f3806g = jSONObject.optLong(C1814b.cT, this.f3806g);
                                this.f3807h = jSONObject.optLong(C1814b.cU, this.f3807h);
                                this.f3809j = jSONObject.optLong(C1814b.cV, this.f3809j);
                                this.f3810k = jSONObject.optLong(C1814b.cW, this.f3810k);
                                this.f3812m = jSONObject.optLong(C1814b.cX, this.f3812m);
                                this.f3813n = jSONObject.optInt(C1814b.cY, this.f3813n);
                                this.f3814o = jSONObject.optInt(C1814b.cZ, this.f3814o);
                                this.f3815p = jSONObject.optString(C1814b.da, this.f3815p);
                                this.f3816q = jSONObject.optInt(C1814b.db, this.f3816q);
                                try {
                                    this.f3808i = Integer.parseInt(jSONObject.optString(C1814b.dc, C1814b.dd));
                                } catch (Exception e3) {
                                }
                                this.f3811l = jSONObject.optString(C1814b.de, this.f3811l);
                                this.f3817r = jSONObject.optString(C1814b.df, this.f3817r);
                                this.f3818s = jSONObject.optString(C1814b.dg, this.f3818s);
                                this.f3820u = jSONObject.optInt(C1814b.dh, this.f3820u);
                                this.f3821v = jSONObject.optInt(C1814b.di, this.f3821v);
                                this.f3823x = jSONObject.optInt(C1814b.dj, this.f3823x);
                                try {
                                    optString = jSONObject.optString(C1814b.dk, null);
                                    if (!TextUtils.isEmpty(optString)) {
                                        split = optString.split(C1814b.dl);
                                        arrayList = new ArrayList();
                                        arrayList.addAll(Arrays.asList(split));
                                        this.f3819t = arrayList;
                                    }
                                } catch (Exception e4) {
                                }
                                try {
                                    optString = jSONObject.getString(C1814b.dm);
                                    if (!TextUtils.isEmpty(optString)) {
                                        split = optString.split(C1814b.dn);
                                        arrayList = new ArrayList();
                                        arrayList.addAll(Arrays.asList(split));
                                        this.f3822w = arrayList;
                                    }
                                } catch (Exception e5) {
                                }
                                this.f3800a = true;
                            }
                        } catch (Exception e6) {
                        }
                    }
                } catch (Exception e7) {
                    e2 = e7;
                    e2.printStackTrace();
                    optJSONObject = jSONObject2.optJSONObject(C1814b.cK).optJSONObject(C1814b.cL + C1731a.f3559b);
                    this.f3801b = optJSONObject.optInt(C1814b.cM);
                    this.f3802c = optJSONObject.optInt(C1814b.cN);
                    optJSONArray = optJSONObject.optJSONArray(C1814b.cO);
                    if (optJSONArray.length() == 0) {
                        jSONObject = optJSONArray.getJSONObject(0);
                        this.f3803d = C1731a.f3559b + C1814b.cP + this.f3801b + C1814b.cP + this.f3802c;
                        this.f3804e = new HashSet();
                        optString2 = jSONObject.optString(C1814b.cQ);
                        while (r0 < r4) {
                            this.f3804e.add(add);
                        }
                        this.f3806g = jSONObject.optLong(C1814b.cT, this.f3806g);
                        this.f3807h = jSONObject.optLong(C1814b.cU, this.f3807h);
                        this.f3809j = jSONObject.optLong(C1814b.cV, this.f3809j);
                        this.f3810k = jSONObject.optLong(C1814b.cW, this.f3810k);
                        this.f3812m = jSONObject.optLong(C1814b.cX, this.f3812m);
                        this.f3813n = jSONObject.optInt(C1814b.cY, this.f3813n);
                        this.f3814o = jSONObject.optInt(C1814b.cZ, this.f3814o);
                        this.f3815p = jSONObject.optString(C1814b.da, this.f3815p);
                        this.f3816q = jSONObject.optInt(C1814b.db, this.f3816q);
                        this.f3808i = Integer.parseInt(jSONObject.optString(C1814b.dc, C1814b.dd));
                        this.f3811l = jSONObject.optString(C1814b.de, this.f3811l);
                        this.f3817r = jSONObject.optString(C1814b.df, this.f3817r);
                        this.f3818s = jSONObject.optString(C1814b.dg, this.f3818s);
                        this.f3820u = jSONObject.optInt(C1814b.dh, this.f3820u);
                        this.f3821v = jSONObject.optInt(C1814b.di, this.f3821v);
                        this.f3823x = jSONObject.optInt(C1814b.dj, this.f3823x);
                        optString = jSONObject.optString(C1814b.dk, null);
                        if (TextUtils.isEmpty(optString)) {
                            split = optString.split(C1814b.dl);
                            arrayList = new ArrayList();
                            arrayList.addAll(Arrays.asList(split));
                            this.f3819t = arrayList;
                        }
                        optString = jSONObject.getString(C1814b.dm);
                        if (TextUtils.isEmpty(optString)) {
                            split = optString.split(C1814b.dn);
                            arrayList = new ArrayList();
                            arrayList.addAll(Arrays.asList(split));
                            this.f3822w = arrayList;
                        }
                        this.f3800a = true;
                    }
                }
            } catch (Exception e8) {
                e2 = e8;
                jSONObject2 = jSONObject;
                e2.printStackTrace();
                optJSONObject = jSONObject2.optJSONObject(C1814b.cK).optJSONObject(C1814b.cL + C1731a.f3559b);
                this.f3801b = optJSONObject.optInt(C1814b.cM);
                this.f3802c = optJSONObject.optInt(C1814b.cN);
                optJSONArray = optJSONObject.optJSONArray(C1814b.cO);
                if (optJSONArray.length() == 0) {
                    jSONObject = optJSONArray.getJSONObject(0);
                    this.f3803d = C1731a.f3559b + C1814b.cP + this.f3801b + C1814b.cP + this.f3802c;
                    this.f3804e = new HashSet();
                    optString2 = jSONObject.optString(C1814b.cQ);
                    while (r0 < r4) {
                        this.f3804e.add(add);
                    }
                    this.f3806g = jSONObject.optLong(C1814b.cT, this.f3806g);
                    this.f3807h = jSONObject.optLong(C1814b.cU, this.f3807h);
                    this.f3809j = jSONObject.optLong(C1814b.cV, this.f3809j);
                    this.f3810k = jSONObject.optLong(C1814b.cW, this.f3810k);
                    this.f3812m = jSONObject.optLong(C1814b.cX, this.f3812m);
                    this.f3813n = jSONObject.optInt(C1814b.cY, this.f3813n);
                    this.f3814o = jSONObject.optInt(C1814b.cZ, this.f3814o);
                    this.f3815p = jSONObject.optString(C1814b.da, this.f3815p);
                    this.f3816q = jSONObject.optInt(C1814b.db, this.f3816q);
                    this.f3808i = Integer.parseInt(jSONObject.optString(C1814b.dc, C1814b.dd));
                    this.f3811l = jSONObject.optString(C1814b.de, this.f3811l);
                    this.f3817r = jSONObject.optString(C1814b.df, this.f3817r);
                    this.f3818s = jSONObject.optString(C1814b.dg, this.f3818s);
                    this.f3820u = jSONObject.optInt(C1814b.dh, this.f3820u);
                    this.f3821v = jSONObject.optInt(C1814b.di, this.f3821v);
                    this.f3823x = jSONObject.optInt(C1814b.dj, this.f3823x);
                    optString = jSONObject.optString(C1814b.dk, null);
                    if (TextUtils.isEmpty(optString)) {
                        split = optString.split(C1814b.dl);
                        arrayList = new ArrayList();
                        arrayList.addAll(Arrays.asList(split));
                        this.f3819t = arrayList;
                    }
                    optString = jSONObject.getString(C1814b.dm);
                    if (TextUtils.isEmpty(optString)) {
                        split = optString.split(C1814b.dn);
                        arrayList = new ArrayList();
                        arrayList.addAll(Arrays.asList(split));
                        this.f3822w = arrayList;
                    }
                    this.f3800a = true;
                }
            }
        }
    }

    public final boolean m5193a() {
        return this.f3800a;
    }

    public final boolean m5194a(Context context) {
        return C1813b.m5270g(context) > this.f3813n;
    }

    public final String m5195b() {
        return this.f3803d;
    }

    public final boolean m5196b(Context context) {
        return C1813b.m5270g(context) >= this.f3813n;
    }

    public final long m5197c() {
        return this.f3807h;
    }

    public final long m5198d() {
        return this.f3806g;
    }

    public final int m5199e() {
        return this.f3808i;
    }

    public final long m5200f() {
        return this.f3809j;
    }

    public final long m5201g() {
        return this.f3810k;
    }

    public final boolean m5202h() {
        return C1814b.f3885do.equals(this.f3811l);
    }

    public final boolean m5203i() {
        return this.f3805f == 1;
    }

    public final boolean m5204j() {
        return SystemClock.elapsedRealtime() >= this.f3812m;
    }

    public final boolean m5205k() {
        if (TextUtils.isEmpty(this.f3815p)) {
            this.f3815p = C1814b.dp;
        }
        return C1814b.dq.equals(this.f3815p);
    }

    public final int m5206l() {
        return this.f3814o;
    }

    public final int m5207m() {
        return this.f3816q;
    }

    public final boolean m5208n() {
        if (TextUtils.isEmpty(this.f3817r)) {
            this.f3817r = C1814b.dr;
        }
        return C1814b.ds.equals(this.f3817r.trim());
    }

    public final boolean m5209o() {
        if (TextUtils.isEmpty(this.f3818s)) {
            this.f3818s = C1814b.dt;
        }
        return C1814b.du.equals(this.f3818s.trim());
    }

    public final int m5210p() {
        if (this.f3820u <= 0) {
            this.f3820u = 5000;
        }
        return this.f3820u;
    }

    public final int m5211q() {
        return this.f3821v;
    }

    public final int m5212r() {
        return this.f3823x;
    }

    public final List m5213s() {
        return this.f3819t;
    }

    public final List m5214t() {
        return this.f3822w;
    }
}
