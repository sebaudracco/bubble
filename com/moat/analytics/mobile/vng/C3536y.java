package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

class C3536y extends C3480c<Object> implements ReactiveVideoTracker {
    private Integer f9017l;

    public C3536y(String str) {
        super(str);
        C3511p.m11978a("[SUCCESS] ", m12035a() + " created");
    }

    String m12035a() {
        return "ReactiveVideoTracker";
    }

    protected JSONObject mo6529a(MoatAdEvent moatAdEvent) {
        if (!(moatAdEvent.f8856d != MoatAdEventType.AD_EVT_COMPLETE || moatAdEvent.f8854b.equals(MoatAdEvent.f8852a) || m11862a(moatAdEvent.f8854b, this.f9017l))) {
            moatAdEvent.f8856d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo6529a(moatAdEvent);
    }

    protected Map<String, Object> mo6543g() {
        Object valueOf;
        Object valueOf2;
        Map<String, Object> hashMap = new HashMap();
        View view = (View) this.k.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f9017l);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        if (this.e) {
            C3511p.m11976a(3, "ReactiveVideoTracker", (Object) this, "trackVideoAd already called");
            C3511p.m11978a("[ERROR] ", m12035a() + " trackVideoAd can't be called twice");
            return false;
        } else if (num.intValue() < 1000) {
            C3511p.m11976a(3, "ReactiveVideoTracker", (Object) this, String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        } else {
            this.f9017l = num;
            return super.mo6530a(map, new Object(), view);
        }
    }
}
