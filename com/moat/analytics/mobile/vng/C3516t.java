package com.moat.analytics.mobile.vng;

import android.graphics.Rect;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class C3516t extends b implements NativeDisplayTracker {
    private final Map<String, String> f8975f;
    private final Set<NativeDisplayTracker$MoatUserInteractionType> f8976g = new HashSet();

    C3516t(View view, Map<String, String> map) {
        super(view, true, false);
        C3511p.m11976a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.f8975f = map;
        C3487g c3487g = ((C3500k) MoatAnalytics.getInstance()).f8943d;
        if (c3487g != null) {
            super.a(c3487g.f8892b);
            super.a(c3487g.f8891a);
        }
        m11993g();
        C3511p.m11978a("[SUCCESS] ", m11996a() + " created for " + e() + ", with adIds:" + map.toString());
    }

    private static String m11992a(Map<String, String> map) {
        int i = 0;
        Map linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < 8; i2++) {
            String str = "moatClientLevel" + i2;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        while (i < 8) {
            String str2 = "moatClientSlicer" + i;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
            i++;
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    private void m11993g() {
        if (this.a != null) {
            this.a.m11920a(m11994h());
        }
    }

    private String m11994h() {
        String str = "";
        try {
            String a = C3516t.m11992a(this.f8975f);
            C3511p.m11976a(3, "NativeDisplayTracker", (Object) this, "Parsed ad ids = " + a);
            str = "{\"adIds\":" + a + ", \"adKey\":\"" + this.b + "\", \"adSize\":" + m11995i() + "}";
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
        return str;
    }

    private String m11995i() {
        try {
            Rect a = C3539z.m12041a(super.d());
            int width = a.width();
            int height = a.height();
            Map hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C3502m.m11942a(e);
            return null;
        }
    }

    String m11996a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(NativeDisplayTracker$MoatUserInteractionType nativeDisplayTracker$MoatUserInteractionType) {
        try {
            C3511p.m11976a(3, "NativeDisplayTracker", (Object) this, "reportUserInteractionEvent:" + nativeDisplayTracker$MoatUserInteractionType.name());
            if (!this.f8976g.contains(nativeDisplayTracker$MoatUserInteractionType)) {
                this.f8976g.add(nativeDisplayTracker$MoatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.b);
                jSONObject.accumulate(NotificationCompat.CATEGORY_EVENT, nativeDisplayTracker$MoatUserInteractionType.name().toLowerCase());
                if (this.a != null) {
                    this.a.m11922b(jSONObject.toString());
                }
            }
        } catch (Exception e) {
            C3511p.m11979b(2, "NativeDisplayTracker", this, "Got JSON exception");
            C3502m.m11942a(e);
        } catch (Exception e2) {
            C3502m.m11942a(e2);
        }
    }
}
