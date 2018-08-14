package com.vungle.publisher.env;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.vungle.publisher.VunglePubBase;
import com.vungle.publisher.as;
import com.vungle.publisher.bw;
import com.vungle.publisher.bw.b;
import com.vungle.publisher.cf;
import com.vungle.publisher.ct.C1594a;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.lp;
import com.vungle.publisher.qg;
import com.vungle.publisher.ql;
import com.vungle.publisher.s;
import com.vungle.publisher.ta;
import com.vungle.publisher.yy;
import com.vungle.publisher.zl;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class C1614r {
    public final AtomicBoolean f2918a = new AtomicBoolean();
    public final AtomicBoolean f2919b = new AtomicBoolean();
    @Inject
    zl f2920c;
    @Inject
    cf f2921d;
    @Inject
    i f2922e;
    @Inject
    qg f2923f;
    @Inject
    lp f2924g;
    @Inject
    ta f2925h;
    @Inject
    bw f2926i;
    @Inject
    C1612k f2927j;
    @Inject
    C1613o f2928k;
    @Inject
    SharedPreferences f2929l;
    @Inject
    C1594a f2930m;
    @Inject
    protected n f2931n;
    @Inject
    protected WrapperFramework f2932o;
    @Inject
    protected String f2933p;
    private ConcurrentLinkedQueue<String> f2934q = new ConcurrentLinkedQueue();
    private String f2935r;
    private ConcurrentHashMap<String, String> f2936s = new ConcurrentHashMap();
    private String f2937t;
    private String f2938u;
    private ConcurrentHashMap<String, Long> f2939v = new ConcurrentHashMap();
    private final AtomicBoolean f2940w = new AtomicBoolean();

    @Inject
    C1614r() {
    }

    public boolean m3945a() {
        boolean z = !TextUtils.isEmpty(yy.a("com.vungle.debug"));
        if (z) {
            Logger.d(Logger.AD_TAG, "in debug mode");
        } else {
            Logger.v(Logger.AD_TAG, "not in debug mode");
        }
        return z;
    }

    public void m3944a(boolean z) {
        this.f2940w.set(z);
    }

    public boolean m3946a(boolean z, boolean z2) {
        return this.f2940w.compareAndSet(z, z2);
    }

    public boolean m3949b() {
        return this.f2929l.getBoolean("IsVgAppInstalled", false);
    }

    public void m3948b(boolean z) {
        this.f2929l.edit().putBoolean("IsVgAppInstalled", z).apply();
    }

    public void m3951c() {
        Logger.d(Logger.AD_TAG, "onDeveloperActivityResume()");
        m3957g();
        m3952d();
    }

    public void m3952d() {
        for (s sVar : this.f2928k.m3936f()) {
            if (this.f2930m.m3598b(sVar.a).size() > 0) {
                Logger.d(Logger.AD_TAG, "Refreshing ad availability on placement: " + sVar.a);
                this.f2923f.m4568a(new as(sVar.a));
            }
        }
    }

    public void m3954e() {
        Logger.d(Logger.AD_TAG, "onAdActivityResume()");
        m3957g();
        this.f2927j.m3914f();
    }

    public void m3956f() {
        Logger.d(Logger.AD_TAG, "onAdActivityDestroy()");
        this.f2927j.m3907a(false);
    }

    public void m3957g() {
        m3961k();
        this.f2922e.n();
    }

    public void m3958h() {
        Logger.d(Logger.AD_TAG, "onDeveloperActivityPause()");
        m3960j();
    }

    public void m3959i() {
        Logger.d(Logger.AD_TAG, "onAdActivityPause()");
        this.f2927j.m3905a(m3960j());
    }

    long m3960j() {
        long a = this.f2920c.m4931a();
        m3962l();
        return a;
    }

    void m3961k() {
        this.f2924g.m4351a();
        this.f2925h.m4650a();
        this.f2921d.m3505a();
    }

    void m3962l() {
        this.f2924g.m4352b();
        this.f2925h.m4651b();
        this.f2921d.m3506b();
    }

    public String m3963m() {
        return (String) this.f2934q.poll();
    }

    public void m3942a(String str) {
        this.f2934q.add(str);
    }

    public boolean m3964n() {
        return this.f2934q.isEmpty();
    }

    public void m3947b(String str) {
        this.f2935r = str;
    }

    public String m3965o() {
        return this.f2935r;
    }

    public long m3966p() {
        long parseLong = Long.parseLong(this.f2929l.getString("VgLoggingBatchId", SchemaSymbols.ATTVAL_FALSE_0));
        this.f2929l.edit().putString("VgLoggingBatchId", String.valueOf(1 + parseLong)).apply();
        return parseLong;
    }

    public String m3967q() {
        if (this.f2937t == null) {
            if (this.f2929l.contains("VgDeviceGuid")) {
                this.f2937t = this.f2929l.getString("VgDeviceGuid", null);
            } else {
                this.f2937t = UUID.randomUUID().toString();
                this.f2929l.edit().putString("VgDeviceGuid", this.f2937t).apply();
            }
        }
        return this.f2937t;
    }

    public String m3968r() {
        String str = this.f2938u;
        if (str != null) {
            return str;
        }
        Object obj;
        StringBuilder append = new StringBuilder().append(VunglePubBase.VERSION);
        WrapperFramework wrapperFramework = this.f2932o;
        String str2 = this.f2933p;
        Object obj2 = (wrapperFramework == null || wrapperFramework.equals(WrapperFramework.none)) ? null : 1;
        if (str2 == null || "".equals(str2)) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!(obj2 == null && obj == null)) {
            append.append(';');
            if (obj2 != null) {
                append.append(wrapperFramework);
            }
            if (obj != null) {
                append.append('/');
                append.append(str2);
            }
        }
        str = append.toString();
        this.f2938u = str;
        return str;
    }

    public void m3943a(String str, long j) {
        if (this.f2929l != null) {
            if (this.f2939v == null) {
                Logger.e(Logger.AD_TAG, "Should never happen as we initialize SleepMap after initComplete");
            }
            this.f2939v.put(str, Long.valueOf(this.f2920c.m4931a() + j));
            m3940s();
            this.f2926i.m3479b(C1615s.m3969a(this, str), b.s, j);
            Logger.d(Logger.AD_TAG, "sleepTest: saveSleepMap: " + this.f2939v.toString());
        }
    }

    /* synthetic */ void m3955e(String str) {
        this.f2939v.remove(str);
        m3940s();
        Logger.d(Logger.AD_TAG, "sleepTest: saveSleepMap: remove: " + str + ":" + this.f2939v.toString());
        if (this.f2928k.m3935e().equals(str)) {
            Logger.d(Logger.AD_TAG, "sleepTest: sending SleepWakeupEvent for auto-cache");
            this.f2923f.m4568a(new ql(str));
        }
    }

    public long m3950c(String str) {
        long longValue;
        if (this.f2939v.get(str) != null) {
            longValue = ((Long) this.f2939v.get(str)).longValue() - this.f2920c.m4931a();
        } else {
            longValue = 0;
        }
        Logger.d(Logger.AD_TAG, "sleepTest: getSleepTimeRemaining: " + longValue);
        return Math.max(0, longValue);
    }

    public ConcurrentHashMap<String, Long> m3941a(List<String> list) {
        try {
            if (this.f2929l != null) {
                String string = this.f2929l.getString("VgPlacementSleepMap", new JSONObject().toString());
                Logger.d(Logger.AD_TAG, "sleepTest: loadPlacementSleepMap: " + this.f2939v.toString());
                Logger.d(Logger.AD_TAG, "sleepTest: loadPlacementSleepMap sharedPref: " + string);
                JSONObject jSONObject = new JSONObject(string);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    Object obj;
                    string = (String) keys.next();
                    long longValue = ((Long) jSONObject.get(string)).longValue() - this.f2920c.m4931a();
                    if (list == null || list.isEmpty() || (list.contains(string) && longValue >= 1)) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        this.f2939v.remove(string);
                    } else {
                        this.f2939v.put(string, Long.valueOf(((Long) jSONObject.get(string)).longValue()));
                        this.f2926i.m3479b(C1616t.m3970a(this, string), b.s, longValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Logger.d(Logger.AD_TAG, "sleepTest: loadPlacementSleepMap: afterLoad " + this.f2939v.toString());
        m3940s();
        return this.f2939v;
    }

    /* synthetic */ void m3953d(String str) {
        this.f2939v.remove(str);
        m3940s();
        Logger.d(Logger.AD_TAG, "sleepTest: saveSleepMap: remove: " + str + ":" + this.f2939v.toString());
        if (this.f2928k.m3935e().equals(str)) {
            Logger.d(Logger.AD_TAG, "sleepTest: sending SleepWakeupEvent for auto-cache");
            this.f2923f.m4568a(new ql(str));
        }
    }

    private void m3940s() {
        String jSONObject = new JSONObject(this.f2939v).toString();
        Editor edit = this.f2929l.edit();
        edit.remove("VgPlacementSleepMap").commit();
        edit.putString("VgPlacementSleepMap", jSONObject);
        edit.apply();
    }
}
