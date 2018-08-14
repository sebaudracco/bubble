package com.moat.analytics.mobile.mpub;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

final class C3467w extends C3414b implements ReactiveVideoTracker {
    private Integer f8850;

    public C3467w(String str) {
        super(str);
        C3412a.m11642(3, "ReactiveVideoTracker", this, "Initializing.");
        C3412a.m11639("[SUCCESS] ", "ReactiveVideoTracker created");
    }

    final String m11822() {
        return "ReactiveVideoTracker";
    }

    final Map<String, Object> mo6524() throws C3442o {
        Object valueOf;
        Object valueOf2;
        Map<String, Object> hashMap = new HashMap();
        View view = (View) this.ˊॱ.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f8850);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            ˎ();
            ॱ();
            this.f8850 = num;
            return super.mo6518(map, view);
        } catch (Exception e) {
            ॱ("trackVideoAd", e);
            return false;
        }
    }

    final JSONObject mo6517(MoatAdEvent moatAdEvent) {
        if (!(moatAdEvent.f8643 != MoatAdEventType.AD_EVT_COMPLETE || moatAdEvent.f8642.equals(MoatAdEvent.f8638) || C3414b.m11649(moatAdEvent.f8642, this.f8850))) {
            moatAdEvent.f8643 = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo6517(moatAdEvent);
    }

    final void mo6519(List<String> list) throws C3442o {
        if (this.f8850.intValue() < 1000) {
            throw new C3442o(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.f8850}));
        } else {
            super.mo6519(list);
        }
    }
}
