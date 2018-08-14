package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

abstract class C3372f<PlayerOrIMAAd> {
    protected static final MoatAdEventType[] f8521b = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    protected boolean f8522a;
    protected final Map<MoatAdEventType, Integer> f8523c = new HashMap();
    protected final Handler f8524d = new Handler();
    protected Map<String, String> f8525e;
    protected WeakReference<PlayerOrIMAAd> f8526f;
    protected WeakReference<View> f8527g;
    protected final C3371a f8528h;
    protected final ao f8529i;
    private boolean f8530j;
    private WeakReference<Context> f8531k;
    private ac f8532l;

    public C3372f(String str, C3371a c3371a, ao aoVar) {
        this.f8529i = aoVar;
        this.f8528h = c3371a;
        m11492a("Initializing.");
        this.f8532l = new ac(str, aoVar, c3371a);
        this.f8531k = new WeakReference(c3371a.mo6499c());
        this.f8530j = false;
        this.f8522a = false;
    }

    private int m11485a(AudioManager audioManager) {
        return audioManager.getStreamVolume(3);
    }

    private MoatAdEvent m11486a(Map<String, Object> map) {
        return new MoatAdEvent(MoatAdEventType.fromString((String) map.get("type")), map.containsKey("playHead") ? (Integer) map.get("playHead") : MoatAdEvent.TIME_UNAVAILABLE, map.containsKey(MoatAdEvent.EVENT_AD_VOLUME) ? (Double) map.get(MoatAdEvent.EVENT_AD_VOLUME) : MoatAdEvent.VOLUME_UNAVAILABLE);
    }

    private void m11489b(MoatAdEvent moatAdEvent) {
        m11492a(String.format("Received event: %s", new Object[]{mo6472a(moatAdEvent).toString()}));
        this.f8532l.m11477a(r0);
        MoatAdEventType moatAdEventType = moatAdEvent.eventType;
        if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.f8523c.put(moatAdEventType, Integer.valueOf(1));
            m11496c();
        }
    }

    protected abstract Map<String, Object> mo6474a();

    protected JSONObject mo6472a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.adVolume.doubleValue())) {
            try {
                moatAdEvent.adVolume = Double.valueOf(m11497d());
            } catch (Exception e) {
                moatAdEvent.adVolume = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
            }
        }
        return new JSONObject(moatAdEvent.toMap());
    }

    protected void m11492a(String str) {
        if (this.f8529i.mo6482b() || this.f8522a) {
            Log.d("MoatVideoTracker", str);
        }
    }

    protected boolean m11493a(Integer num, Integer num2) {
        return ((double) (num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public boolean mo6475a(Map<String, String> map, PlayerOrIMAAd playerOrIMAAd, View view) {
        boolean z = true;
        boolean z2 = false;
        if (map == null) {
            try {
                m11492a("trackVideoAd received null adIds object. Not tracking.");
                z = false;
            } catch (Exception e) {
                C3376a.m11557a(e);
            }
        }
        if (view == null) {
            m11492a("trackVideoAd received null video view instance");
        }
        if (playerOrIMAAd == null) {
            m11492a("trackVideoAd received null ad instance. Not tracking.");
            z = false;
        }
        if (z) {
            String str = "trackVideoAd tracking ids: %s | ad: %s | view: %s";
            Object[] objArr = new Object[3];
            objArr[0] = new JSONObject(map).toString();
            objArr[1] = playerOrIMAAd.toString();
            objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
            m11492a(String.format(str, objArr));
            this.f8525e = map;
            this.f8526f = new WeakReference(playerOrIMAAd);
            this.f8527g = new WeakReference(view);
            mo6473b();
        }
        z2 = z;
        m11492a("Attempt to start tracking ad was " + (z2 ? "" : "un") + "successful.");
        return z2;
    }

    protected void mo6473b() {
        Map a = mo6474a();
        Integer num = (Integer) a.get("height");
        m11492a(String.format("Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) a.get("width"), (Integer) a.get("duration")}));
        this.f8532l.m11475a((View) this.f8527g.get(), this.f8525e, r3, num, r5);
    }

    protected void m11496c() {
        if (!this.f8530j) {
            this.f8524d.postDelayed(new C3383g(this), 500);
            this.f8530j = true;
        }
    }

    public void changeTargetView(View view) {
        if (this.f8529i.mo6482b()) {
            Log.d("MoatVideoTracker", "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        }
        this.f8527g = new WeakReference(view);
        this.f8532l.m11474a(view);
    }

    protected double m11497d() {
        AudioManager audioManager = (AudioManager) ((Context) this.f8531k.get()).getSystemService("audio");
        return ((double) m11485a(audioManager)) / ((double) audioManager.getStreamMaxVolume(3));
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            m11489b(moatAdEvent);
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }

    public void dispatchEvent(Map<String, Object> map) {
        try {
            m11489b(m11486a((Map) map));
        } catch (Exception e) {
            C3376a.m11557a(e);
        }
    }

    protected boolean m11498e() {
        return this.f8523c.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f8523c.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f8523c.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void setDebug(boolean z) {
        this.f8522a = z;
    }
}
