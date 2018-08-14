package com.moat.analytics.mobile.vng;

import android.os.Handler;
import android.view.View;
import com.moat.analytics.mobile.vng.C3487g.C3486a;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

abstract class C3480c<PlayerOrIMAAd> extends b {
    static final MoatAdEventType[] f8874f = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    final Map<MoatAdEventType, Integer> f8875g;
    final Handler f8876h;
    Map<String, String> f8877i;
    WeakReference<PlayerOrIMAAd> f8878j;
    WeakReference<View> f8879k;
    private boolean f8880l;
    private Double f8881m;
    private final C3487g f8882n = new C3487g(C3475a.m11847a(), C3486a.VIDEO);
    private final String f8883o;

    class C34791 implements Runnable {
        final /* synthetic */ C3480c f8873a;

        C34791(C3480c c3480c) {
            this.f8873a = c3480c;
        }

        public void run() {
            try {
                C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "Shutting down.");
                this.f8873a.f8882n.m11875a();
            } catch (Exception e) {
                C3502m.m11942a(e);
            }
        }
    }

    C3480c(String str) {
        super(null, false, true);
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "Initializing.");
        this.f8883o = str;
        this.f8882n.m11876a(str);
        super.a(this.f8882n.f8892b);
        super.a(this.f8882n.f8891a);
        this.f8875g = new HashMap();
        this.f8876h = new Handler();
        this.f8880l = false;
        this.f8881m = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    }

    private void m11859b(MoatAdEvent moatAdEvent) {
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, String.format("Received event: %s", new Object[]{mo6529a(moatAdEvent).toString()}));
        C3511p.m11978a("[SUCCESS] ", a() + String.format(" Received event: %s", new Object[]{r0.toString()}));
        this.a.m11921a(this.f8882n.f8893c, r0);
        MoatAdEventType moatAdEventType = moatAdEvent.f8856d;
        if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.f8875g.put(moatAdEventType, Integer.valueOf(1));
            m11865h();
        }
    }

    private void mo6531j() {
        Map g = mo6543g();
        Integer num = (Integer) g.get("height");
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) g.get("width"), (Integer) g.get("duration")}));
        this.f8882n.m11877a(this.f8883o, this.f8877i, r3, num, r5);
        super.changeTargetView((View) this.f8879k.get());
        super.b();
    }

    JSONObject mo6529a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f8855c.doubleValue())) {
            try {
                moatAdEvent.f8855c = Double.valueOf(C3515s.m11985a());
            } catch (Exception e) {
                moatAdEvent.f8855c = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
            }
        }
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "adVolume before adjusting for player volume %f", new Object[]{moatAdEvent.f8855c}));
        moatAdEvent.f8855c = Double.valueOf(moatAdEvent.f8855c.doubleValue() * this.f8881m.doubleValue());
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "adVolume after adjusting for player volume %f", new Object[]{moatAdEvent.f8855c}));
        return new JSONObject(moatAdEvent.m11828a());
    }

    boolean m11862a(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public boolean mo6530a(Map<String, String> map, PlayerOrIMAAd playerOrIMAAd, View view) {
        boolean z = true;
        try {
            if (this.e) {
                C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "trackVideoAd already called");
                C3511p.m11978a("[ERROR] ", a() + " trackVideoAd can't be called twice");
                z = false;
            }
            if (map == null) {
                C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null adIds object. Not tracking.");
                C3511p.m11978a("[ERROR] ", a() + " trackVideoAd failed, received null adIds object");
                z = false;
            }
            if (view == null) {
                C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null video view instance");
            }
            if (playerOrIMAAd == null) {
                C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null ad instance. Not tracking.");
                C3511p.m11978a("[ERROR] ", a() + " trackVideoAd failed, received null ad instance");
                z = false;
            }
            if (z) {
                String str = "BaseVideoTracker";
                String str2 = "trackVideoAd tracking ids: %s | ad: %s | view: %s";
                Object[] objArr = new Object[3];
                objArr[0] = new JSONObject(map).toString();
                objArr[1] = playerOrIMAAd.toString();
                objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
                C3511p.m11976a(3, str, (Object) this, String.format(str2, objArr));
                String str3 = "[SUCCESS] ";
                StringBuilder append = new StringBuilder().append(a());
                str2 = " trackVideoAd succeeded with ids: %s | ad: %s | view: %s";
                objArr = new Object[3];
                objArr[0] = new JSONObject(map).toString();
                objArr[1] = playerOrIMAAd.toString();
                objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
                C3511p.m11978a(str3, append.append(String.format(str2, objArr)).toString());
                this.f8877i = map;
                this.f8878j = new WeakReference(playerOrIMAAd);
                this.f8879k = new WeakReference(view);
                mo6531j();
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
            z = false;
        }
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
        return z;
    }

    public void changeTargetView(View view) {
        C3511p.m11976a(3, "BaseVideoTracker", (Object) this, "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        this.f8879k = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            m11859b(moatAdEvent);
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }

    protected abstract Map<String, Object> mo6543g();

    void m11865h() {
        if (!this.f8880l) {
            this.f8876h.postDelayed(new C34791(this), 500);
            this.f8880l = true;
        }
    }

    boolean m11866i() {
        return this.f8875g.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f8875g.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f8875g.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void setPlayerVolume(Double d) {
        if (!d.equals(this.f8881m)) {
            C3511p.m11976a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f8881m = d;
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f8852a));
        }
    }

    public void stopTracking() {
        try {
            boolean c = super.c();
            C3511p.m11978a(c ? "[SUCCESS] " : "[ERROR] ", a() + " stopTracking " + (c ? "succeeded" : "failed") + " for " + e());
            m11865h();
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }
}
