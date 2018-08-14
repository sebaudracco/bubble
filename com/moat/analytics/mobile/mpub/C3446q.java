package com.moat.analytics.mobile.mpub;

import android.graphics.Rect;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

final class C3446q extends d implements NativeDisplayTracker {
    private final Map<String, String> f8782;
    private final Set<NativeDisplayTracker$MoatUserInteractionType> f8783 = new HashSet();

    C3446q(View view, Map<String, String> map) {
        super(view, true, false);
        C3412a.m11642(3, "NativeDisplayTracker", this, "Initializing.");
        this.f8782 = map;
        String str;
        String str2;
        if (view == null) {
            str = "Target view is null";
            str2 = "NativeDisplayTracker initialization not successful, " + str;
            C3412a.m11642(3, "NativeDisplayTracker", this, str2);
            C3412a.m11639("[ERROR] ", str2);
            this.ॱ = new C3442o(str);
        } else if (map == null || map.isEmpty()) {
            str = "NativeDisplayTracker initialization not successful, " + "AdIds is null or empty";
            C3412a.m11642(3, "NativeDisplayTracker", this, str);
            C3412a.m11639("[ERROR] ", str);
            this.ॱ = new C3442o("AdIds is null or empty");
        } else {
            C3412a c3412a = ((C3419f) MoatAnalytics.getInstance()).f8688;
            if (c3412a == null) {
                str = "prepareNativeDisplayTracking was not called successfully";
                str2 = "NativeDisplayTracker initialization not successful, " + str;
                C3412a.m11642(3, "NativeDisplayTracker", this, str2);
                C3412a.m11639("[ERROR] ", str2);
                this.ॱ = new C3442o(str);
                return;
            }
            this.ˎ = c3412a.f8658;
            try {
                super.ॱ(c3412a.f8656);
                if (this.ˎ != null) {
                    this.ˎ.m11726(m11766());
                }
                C3412a.m11639("[SUCCESS] ", "NativeDisplayTracker created for " + ʻ() + ", with adIds:" + map.toString());
            } catch (C3442o e) {
                this.ॱ = e;
            }
        }
    }

    final String m11768() {
        return "NativeDisplayTracker";
    }

    public final void reportUserInteractionEvent(NativeDisplayTracker$MoatUserInteractionType nativeDisplayTracker$MoatUserInteractionType) {
        try {
            C3412a.m11642(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + nativeDisplayTracker$MoatUserInteractionType.name());
            if (!this.f8783.contains(nativeDisplayTracker$MoatUserInteractionType)) {
                this.f8783.add(nativeDisplayTracker$MoatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.ˋ);
                jSONObject.accumulate(NotificationCompat.CATEGORY_EVENT, nativeDisplayTracker$MoatUserInteractionType.name().toLowerCase());
                if (this.ˎ != null) {
                    this.ˎ.m11727(jSONObject.toString());
                }
            }
        } catch (Exception e) {
            C3412a.m11643("NativeDisplayTracker", this, "Got JSON exception");
            C3442o.m11756(e);
        } catch (Exception e2) {
            C3442o.m11756(e2);
        }
    }

    private String m11766() {
        int i = 0;
        String str = "";
        try {
            Map map = this.f8782;
            Map linkedHashMap = new LinkedHashMap();
            for (int i2 = 0; i2 < 8; i2++) {
                String str2 = "moatClientLevel" + i2;
                if (map.containsKey(str2)) {
                    linkedHashMap.put(str2, map.get(str2));
                }
            }
            while (i < 8) {
                String str3 = "moatClientSlicer" + i;
                if (map.containsKey(str3)) {
                    linkedHashMap.put(str3, map.get(str3));
                }
                i++;
            }
            for (String str4 : map.keySet()) {
                if (!linkedHashMap.containsKey(str4)) {
                    linkedHashMap.put(str4, (String) map.get(str4));
                }
            }
            String str42 = new JSONObject(linkedHashMap).toString();
            C3412a.m11642(3, "NativeDisplayTracker", this, "Parsed ad ids = " + str42);
            return "{\"adIds\":" + str42 + ", \"adKey\":\"" + this.ˋ + "\", \"adSize\":" + m11767() + "}";
        } catch (Exception e) {
            C3442o.m11756(e);
            return str;
        }
    }

    private String m11767() {
        try {
            Rect ˋ = C3465u.m11813(super.ʼ());
            int width = ˋ.width();
            int height = ˋ.height();
            Map hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C3442o.m11756(e);
            return null;
        }
    }
}
