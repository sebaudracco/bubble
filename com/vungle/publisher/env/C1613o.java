package com.vungle.publisher.env;

import android.content.SharedPreferences;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.pj.C1668a;
import com.vungle.publisher.qi;
import com.vungle.publisher.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1613o {
    final Map<VungleAdEventListener, qi> f2905a = new HashMap();
    @Inject
    C1668a f2906b;
    @Inject
    SharedPreferences f2907c;
    private String f2908d = "isExceptionReportingEnabled";
    private boolean f2909e;
    private boolean f2910f;
    private boolean f2911g;
    private boolean f2912h;
    private int f2913i;
    private int f2914j;
    private String f2915k;
    private List<s> f2916l = new ArrayList();
    private ArrayList<String> f2917m = new ArrayList();

    @Inject
    C1613o() {
    }

    public void m3921a(VungleAdEventListener... vungleAdEventListenerArr) {
        if (vungleAdEventListenerArr != null) {
            for (VungleAdEventListener a : vungleAdEventListenerArr) {
                m3922a(a);
            }
        }
    }

    boolean m3922a(VungleAdEventListener vungleAdEventListener) {
        boolean z = false;
        String str = Logger.EVENT_TAG;
        if (vungleAdEventListener == null) {
            Logger.d(Logger.EVENT_TAG, "ignoring add null event listener");
        } else {
            if (!this.f2905a.containsKey(vungleAdEventListener)) {
                z = true;
            }
            if (z) {
                Logger.d(Logger.EVENT_TAG, "adding event listener " + vungleAdEventListener);
                qi a = this.f2906b.m4539a(vungleAdEventListener);
                this.f2905a.put(vungleAdEventListener, a);
                a.registerSticky();
            } else {
                Logger.d(Logger.EVENT_TAG, "already added event listener " + vungleAdEventListener);
            }
        }
        return z;
    }

    public void m3915a() {
        for (qi unregister : this.f2905a.values()) {
            unregister.unregister();
        }
        this.f2905a.clear();
    }

    public void m3928b(VungleAdEventListener... vungleAdEventListenerArr) {
        if (vungleAdEventListenerArr != null) {
            for (VungleAdEventListener b : vungleAdEventListenerArr) {
                m3930b(b);
            }
        }
    }

    boolean m3930b(VungleAdEventListener vungleAdEventListener) {
        qi qiVar = (qi) this.f2905a.remove(vungleAdEventListener);
        boolean z = qiVar != null;
        if (z) {
            Logger.d(Logger.CONFIG_TAG, "removing event listener " + vungleAdEventListener);
            qiVar.unregister();
        } else {
            Logger.d(Logger.CONFIG_TAG, "event listener not found for remove " + vungleAdEventListener);
        }
        return z;
    }

    public void m3933c(VungleAdEventListener... vungleAdEventListenerArr) {
        m3915a();
        m3921a(vungleAdEventListenerArr);
    }

    public boolean m3929b() {
        return this.f2909e;
    }

    public void m3920a(boolean z) {
        Logger.d(Logger.CONFIG_TAG, (z ? "enabling" : "disabling") + " call to willPlayAd");
        this.f2909e = z;
    }

    public int m3931c() {
        Logger.d(Logger.CONFIG_TAG, "willPlayAd response timeout config " + this.f2913i + " ms");
        return this.f2913i;
    }

    public void m3916a(int i) {
        Logger.d(Logger.CONFIG_TAG, "setting willPlayAd response timeout " + i + " ms");
        this.f2913i = i;
    }

    public void m3927b(boolean z) {
        Logger.d(Logger.CONFIG_TAG, "setting exception reporting enabled: " + z);
        this.f2907c.edit().putBoolean(this.f2908d, z).apply();
    }

    public void m3924b(int i) {
        this.f2914j = i;
    }

    public void m3918a(String str) {
        this.f2915k = str;
    }

    public void m3919a(List<s> list) {
        this.f2916l = list;
    }

    public s m3923b(String str) {
        if (str != null) {
            for (s sVar : this.f2916l) {
                if (str.equals(sVar.a)) {
                    return sVar;
                }
            }
        }
        return null;
    }

    public void m3926b(List<String> list) {
        this.f2917m.clear();
        this.f2917m.addAll(list);
    }

    public ArrayList<String> m3934d() {
        return this.f2917m;
    }

    public String m3935e() {
        for (s sVar : this.f2916l) {
            if (sVar.b) {
                return sVar.a;
            }
        }
        return null;
    }

    public List<s> m3936f() {
        return this.f2916l;
    }

    public void m3917a(Boolean bool) {
        this.f2910f = bool.booleanValue();
    }

    public boolean m3937g() {
        return this.f2910f;
    }

    public void m3932c(boolean z) {
        this.f2911g = z;
    }

    public boolean m3938h() {
        return this.f2911g;
    }

    public boolean m3939i() {
        return this.f2912h;
    }

    public void m3925b(Boolean bool) {
        this.f2912h = bool.booleanValue();
    }
}
