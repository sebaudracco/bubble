package com.vungle.publisher;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.widget.VideoView;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.moat.analytics.mobile.vng.MoatAnalytics;
import com.moat.analytics.mobile.vng.MoatFactory;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.ji.a;
import com.vungle.publisher.log.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class zf {
    @Inject
    Context f3499a;
    @Inject
    C1613o f3500b;
    private AtomicBoolean f3501c = new AtomicBoolean();
    private ReactiveVideoTracker f3502d;
    private HashMap<String, String> f3503e;

    public void m4923a() {
        if (!this.f3500b.m3937g() || VERSION.SDK_INT < 16) {
            Logger.d("VungleMoatAnalytics", "Moat not initialized.");
            return;
        }
        MoatOptions moatOptions = new MoatOptions();
        moatOptions.disableAdIdCollection = true;
        moatOptions.disableLocationServices = true;
        moatOptions.loggingEnabled = true;
        MoatAnalytics.getInstance().start(moatOptions, (Application) this.f3499a);
        this.f3500b.m3932c(true);
    }

    public void m4926a(jh<?, ?, ?> jhVar, String str) {
        List a = jhVar.m3841a(a.c);
        if (!this.f3500b.m3937g() || !this.f3500b.m3938h() || a == null || a.size() <= 0) {
            this.f3502d = null;
            this.f3501c.set(false);
            Logger.v("VungleMoatAnalytics", "Moat tracking is not enabled. Ignoring - createVideoTracker.");
            return;
        }
        m4927a(jhVar, str, (String) a.get(0));
        this.f3502d = (ReactiveVideoTracker) MoatFactory.create().createCustomTracker(new ReactiveVideoTrackerPlugin("vunglenativevideo893259554489"));
    }

    public void m4927a(jh<?, ?, ?> jhVar, String str, String str2) {
        Object obj = null;
        this.f3503e = new HashMap();
        if (zk.a(str2)) {
            this.f3503e.put("zMoatVASTIDs", str2);
            return;
        }
        Object f;
        String n;
        int indexOf;
        int indexOf2;
        Object substring;
        HashMap hashMap;
        String str3;
        HashMap hashMap2;
        String str4;
        String str5;
        String e = jhVar.e();
        if (e != null) {
            try {
                f = ra.f(new JSONObject(e.substring(3)), "app_id");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            n = jhVar.n();
            indexOf = n.indexOf(124);
            if (indexOf == -1) {
                indexOf2 = n.indexOf(124, indexOf + 1);
                substring = n.substring(0, indexOf);
                if (indexOf2 != -1) {
                    obj = n.substring(indexOf + 1, indexOf2);
                }
            } else {
                substring = null;
            }
            hashMap = this.f3503e;
            str3 = "level1";
            if (!zk.a(f)) {
                f = C1404b.f2123a;
            }
            hashMap.put(str3, f);
            hashMap2 = this.f3503e;
            n = "level2";
            if (!zk.a(substring)) {
                substring = C1404b.f2123a;
            }
            hashMap2.put(n, substring);
            hashMap2 = this.f3503e;
            str4 = "level3";
            if (!zk.a(obj)) {
                obj = C1404b.f2123a;
            }
            hashMap2.put(str4, obj);
            hashMap2 = this.f3503e;
            str5 = "level4";
            if (!zk.a(str)) {
                str = C1404b.f2123a;
            }
            hashMap2.put(str5, str);
        }
        f = null;
        n = jhVar.n();
        indexOf = n.indexOf(124);
        if (indexOf == -1) {
            substring = null;
        } else {
            indexOf2 = n.indexOf(124, indexOf + 1);
            substring = n.substring(0, indexOf);
            if (indexOf2 != -1) {
                obj = n.substring(indexOf + 1, indexOf2);
            }
        }
        hashMap = this.f3503e;
        str3 = "level1";
        if (zk.a(f)) {
            f = C1404b.f2123a;
        }
        hashMap.put(str3, f);
        hashMap2 = this.f3503e;
        n = "level2";
        if (zk.a(substring)) {
            substring = C1404b.f2123a;
        }
        hashMap2.put(n, substring);
        hashMap2 = this.f3503e;
        str4 = "level3";
        if (zk.a(obj)) {
            obj = C1404b.f2123a;
        }
        hashMap2.put(str4, obj);
        hashMap2 = this.f3503e;
        str5 = "level4";
        if (zk.a(str)) {
            str = C1404b.f2123a;
        }
        hashMap2.put(str5, str);
    }

    public void m4924a(int i, VideoView videoView) {
        if (this.f3502d == null) {
            Logger.v("VungleMoatAnalytics", "Moat tracking is not enabled. Ignoring - startTracking.");
        } else if (this.f3501c.compareAndSet(false, true)) {
            this.f3502d.trackVideoAd(this.f3503e, Integer.valueOf(i), videoView);
        }
    }

    public void m4925a(MoatAdEventType moatAdEventType, int i) {
        if (this.f3502d == null || !this.f3501c.get()) {
            Logger.v("VungleMoatAnalytics", "Moat tracking is not enabled. Ignoring - dispatchEvent: " + moatAdEventType.toString());
        } else {
            this.f3502d.dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(i)));
        }
    }

    public void m4929b() {
        if (this.f3502d == null) {
            Logger.v("VungleMoatAnalytics", "Moat tracking is not enabled. Ignoring - stopTracking.");
        } else if (this.f3501c.compareAndSet(true, false)) {
            this.f3502d.stopTracking();
            this.f3502d = null;
            Logger.d("VungleMoatAnalytics", "Stopped Moat video tracker");
        }
    }

    public void m4928a(Double d) {
        if (this.f3502d == null || !this.f3501c.get()) {
            Logger.v("VungleMoatAnalytics", "Moat tracking is not enabled. Ignoring - setPlayerVolume.");
        } else {
            this.f3502d.setPlayerVolume(d);
        }
    }
}
