package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.view.View;
import com.moat.analytics.mobile.mpub.C3412a.C3411d;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

abstract class C3414b extends d {
    static final MoatAdEventType[] f8661 = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    final Map<MoatAdEventType, Integer> f8662;
    private final String f8663;
    WeakReference<View> f8664;
    private boolean f8665;
    private Map<String, String> f8666;
    private Double f8667;
    private VideoTrackerListener f8668;
    private final Set<MoatAdEventType> f8669;
    private final C3412a f8670;
    final Handler f8671;

    class C34135 implements Runnable {
        private /* synthetic */ C3414b f8660;

        C34135(C3414b c3414b) {
            this.f8660 = c3414b;
        }

        public final void run() {
            try {
                C3412a.m11642(3, "BaseVideoTracker", this, "Shutting down.");
                C3412a ˊ = this.f8660.f8670;
                C3412a.m11642(3, "GlobalWebView", ˊ, "Cleaning up");
                ˊ.f8658.m11720();
                ˊ.f8658 = null;
                ˊ.f8656.destroy();
                ˊ.f8656 = null;
                this.f8660.f8668 = null;
            } catch (Exception e) {
                C3442o.m11756(e);
            }
        }
    }

    abstract Map<String, Object> mo6524() throws C3442o;

    C3414b(String str) {
        super(null, false, true);
        C3412a.m11642(3, "BaseVideoTracker", this, "Initializing.");
        this.f8663 = str;
        this.f8670 = new C3412a(C3416c.m11664(), C3411d.f8653);
        this.ˎ = this.f8670.f8658;
        try {
            super.ॱ(this.f8670.f8656);
        } catch (C3442o e) {
            this.ॱ = e;
        }
        this.f8662 = new HashMap();
        this.f8669 = new HashSet();
        this.f8671 = new Handler();
        this.f8665 = false;
        this.f8667 = Double.valueOf(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE);
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f8668 = videoTrackerListener;
    }

    public void removeVideoListener() {
        this.f8668 = null;
    }

    @CallSuper
    public boolean mo6518(Map<String, String> map, View view) {
        try {
            ˎ();
            ॱ();
            if (view == null) {
                C3412a.m11642(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.f8666 = map;
            this.f8664 = new WeakReference(view);
            m11655();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), C3412a.m11641(view)});
            C3412a.m11642(3, "BaseVideoTracker", this, format);
            C3412a.m11639("[SUCCESS] ", ˋ() + " " + format);
            if (this.ˊ == null) {
                return true;
            }
            this.ˊ.onTrackingStarted(ʻ());
            return true;
        } catch (Exception e) {
            ॱ("trackVideoAd", e);
            return false;
        }
    }

    public void changeTargetView(View view) {
        C3412a.m11642(3, "BaseVideoTracker", this, "changing view to " + C3412a.m11641(view));
        this.f8664 = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    void mo6519(List<String> list) throws C3442o {
        if (this.f8666 == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.ˋ(list);
            return;
        }
        throw new C3442o(TextUtils.join(" and ", list));
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            m11656();
            if (this.f8668 != null) {
                this.f8668 = null;
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        Object obj = null;
        try {
            C3412a.m11642(3, "BaseVideoTracker", this, String.format("Received event: %s", new Object[]{mo6517(moatAdEvent).toString()}));
            C3412a.m11639("[SUCCESS] ", ˋ() + String.format(" Received event: %s", new Object[]{r2.toString()}));
            if (ˊ() && this.ˎ != null) {
                this.ˎ.m11728(this.f8670.f8655, r2);
                if (!this.f8669.contains(moatAdEvent.f8643)) {
                    this.f8669.add(moatAdEvent.f8643);
                    if (this.f8668 != null) {
                        this.f8668.onVideoEventReported(moatAdEvent.f8643);
                    }
                }
            }
            MoatAdEventType moatAdEventType = moatAdEvent.f8643;
            if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
                obj = 1;
            }
            if (obj != null) {
                this.f8662.put(moatAdEventType, Integer.valueOf(1));
                if (this.ˎ != null) {
                    this.ˎ.m11722((d) this);
                }
                m11656();
            }
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    final Double m11658() {
        return this.f8667;
    }

    final void m11655() throws C3442o {
        super.changeTargetView((View) this.f8664.get());
        super.ˏ();
        Map ᐝ = mo6524();
        Integer num = (Integer) ᐝ.get("height");
        C3412a.m11642(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) ᐝ.get("width"), (Integer) ᐝ.get("duration")}));
        this.f8670.m11647(this.f8663, this.f8666, r3, num, r5);
    }

    JSONObject mo6517(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f8641.doubleValue())) {
            moatAdEvent.f8641 = this.f8667;
        }
        return new JSONObject(moatAdEvent.m11635());
    }

    final void m11656() {
        if (!this.f8665) {
            this.f8665 = true;
            this.f8671.postDelayed(new C34135(this), 500);
        }
    }

    final boolean m11653() {
        return this.f8662.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f8662.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f8662.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    static boolean m11649(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public void setPlayerVolume(Double d) {
        Double valueOf = Double.valueOf(this.f8667.doubleValue() * C3450r.m11783());
        if (!d.equals(this.f8667)) {
            C3412a.m11642(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f8667 = d;
            if (!valueOf.equals(Double.valueOf(this.f8667.doubleValue() * C3450r.m11783()))) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f8638, this.f8667));
            }
        }
    }

    final Double m11651() {
        return Double.valueOf(this.f8667.doubleValue() * C3450r.m11783());
    }
}
