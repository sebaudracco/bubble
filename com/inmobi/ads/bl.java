package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: VastCompanionAd */
public class bl {
    public static final ArrayList<String> f7278a = new ArrayList(Arrays.asList(new String[]{"image/jpeg", "image/png"}));
    private static final String f7279b = bl.class.getSimpleName();
    private int f7280c;
    private int f7281d;
    private List<C3033a> f7282e = new ArrayList();
    private List<ah> f7283f = new ArrayList();
    private String f7284g;

    /* compiled from: VastCompanionAd */
    static final class C3033a {
        C3032a f7276a;
        String f7277b;

        /* compiled from: VastCompanionAd */
        enum C3032a {
            CREATIVE_TYPE_UNKNOWN_OR_UNSUPPORTED,
            CREATIVE_TYPE_STATIC,
            CREATIVE_TYPE_HTML,
            CREATIVE_TYPE_IFRAME
        }

        public C3033a(C3032a c3032a, String str) {
            this.f7276a = c3032a;
            this.f7277b = str;
        }

        public static C3033a m9522a(JSONObject jSONObject) {
            try {
                return new C3033a(C3033a.m9521a(jSONObject.getString("type")), jSONObject.getString(FirebaseAnalytics$Param.CONTENT));
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, bl.f7279b, "Error building resource from JSONObject; " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return null;
            }
        }

        private static C3032a m9521a(String str) {
            if (str == null || str.trim().length() == 0) {
                return C3032a.CREATIVE_TYPE_UNKNOWN_OR_UNSUPPORTED;
            }
            String toLowerCase = str.toLowerCase(Locale.US);
            Object obj = -1;
            switch (toLowerCase.hashCode()) {
                case -1191214428:
                    if (toLowerCase.equals("iframe")) {
                        obj = 4;
                        break;
                    }
                    break;
                case -892481938:
                    if (toLowerCase.equals("static")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -284840886:
                    if (toLowerCase.equals("unknown")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3213227:
                    if (toLowerCase.equals("html")) {
                        obj = 3;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case 2:
                    return C3032a.CREATIVE_TYPE_STATIC;
                case 3:
                    return C3032a.CREATIVE_TYPE_HTML;
                case 4:
                    return C3032a.CREATIVE_TYPE_IFRAME;
                default:
                    return C3032a.CREATIVE_TYPE_UNKNOWN_OR_UNSUPPORTED;
            }
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", m9523a(this.f7276a));
                jSONObject.put(FirebaseAnalytics$Param.CONTENT, this.f7277b);
                return jSONObject.toString();
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, bl.f7279b, "Error serializing resource: " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return "";
            }
        }

        @NonNull
        private String m9523a(C3032a c3032a) {
            switch (c3032a) {
                case CREATIVE_TYPE_STATIC:
                    return "static";
                case CREATIVE_TYPE_HTML:
                    return "html";
                case CREATIVE_TYPE_IFRAME:
                    return "iframe";
                default:
                    return "unknown";
            }
        }
    }

    public bl(int i, int i2, String str) {
        this.f7280c = i;
        this.f7281d = i2;
        this.f7284g = str;
    }

    public int m9526a() {
        return this.f7280c;
    }

    public int m9531b() {
        return this.f7281d;
    }

    public List<C3033a> m9528a(C3032a c3032a) {
        List<C3033a> arrayList = new ArrayList();
        for (C3033a c3033a : this.f7282e) {
            if (c3033a.f7276a.equals(c3032a)) {
                arrayList.add(c3033a);
            }
        }
        return arrayList;
    }

    void m9530a(@NonNull C3033a c3033a) {
        this.f7282e.add(c3033a);
    }

    public List<ah> m9532c() {
        return this.f7283f;
    }

    public List<ah> m9527a(C3000a c3000a) {
        List<ah> arrayList = new ArrayList();
        for (ah ahVar : this.f7283f) {
            if (ahVar.m9297c().equals(c3000a)) {
                arrayList.add(ahVar);
            }
        }
        return arrayList;
    }

    void m9529a(@NonNull ah ahVar) {
        this.f7283f.add(ahVar);
    }

    public String m9533d() {
        return this.f7284g;
    }

    @Nullable
    public static bl m9524a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray;
            int i;
            bl blVar = new bl(jSONObject.getInt("width"), jSONObject.getInt("height"), jSONObject.has("clickThroughUrl") ? jSONObject.getString("clickThroughUrl") : null);
            try {
                jSONArray = new JSONArray(jSONObject.getString("trackers"));
                for (i = 0; i < jSONArray.length(); i++) {
                    ah a = ah.m9289a(new JSONObject(jSONArray.getString(i)));
                    if (a != null) {
                        blVar.m9529a(a);
                    }
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7279b, "Error getting trackers");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
            try {
                jSONArray = new JSONArray(jSONObject.getString("resources"));
                for (i = 0; i < jSONArray.length(); i++) {
                    C3033a a2 = C3033a.m9522a(new JSONObject(jSONArray.getString(i)));
                    if (a2 != null) {
                        blVar.m9530a(a2);
                    }
                }
                return blVar;
            } catch (Throwable e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7279b, "Error getting resource elements");
                C3135c.m10255a().m10279a(new C3132b(e2));
                return blVar;
            }
        } catch (Throwable e3) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7279b, "Error building companion from JSON: " + e3.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e3));
            return null;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.f7280c);
            jSONObject.put("height", this.f7281d);
            jSONObject.put("clickThroughUrl", this.f7284g);
            JSONArray jSONArray = new JSONArray();
            for (C3033a c3033a : this.f7282e) {
                jSONArray.put(c3033a.toString());
            }
            jSONObject.put("resources", jSONArray);
            jSONArray = new JSONArray();
            for (ah ahVar : this.f7283f) {
                jSONArray.put(ahVar.toString());
            }
            jSONObject.put("trackers", jSONArray);
            return jSONObject.toString();
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7279b, "Error serializing an " + f7279b + " instance: " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }
}
