package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: NativeStrandTracker */
final class ah {
    private static final String f7101a = ah.class.getSimpleName();
    private C3001b f7102b;
    private String f7103c;
    private int f7104d;
    private C3000a f7105e;
    private Map<String, String> f7106f;
    private Map<String, Object> f7107g;

    /* compiled from: NativeStrandTracker */
    enum C3000a {
        TRACKER_EVENT_TYPE_UNKNOWN,
        TRACKER_EVENT_TYPE_LOAD,
        TRACKER_EVENT_TYPE_CLIENT_FILL,
        TRACKER_EVENT_TYPE_RENDER,
        TRACKER_EVENT_TYPE_PAGE_VIEW,
        TRACKER_EVENT_TYPE_CLICK,
        TRACKER_EVENT_TYPE_VIDEO_RENDER,
        TRACKER_EVENT_TYPE_PLAY,
        TRACKER_EVENT_TYPE_Q1,
        TRACKER_EVENT_TYPE_Q2,
        TRACKER_EVENT_TYPE_Q3,
        TRACKER_EVENT_TYPE_Q4,
        TRACKER_EVENT_TYPE_CREATIVE_VIEW,
        TRACKER_EVENT_TYPE_FULLSCREEN,
        TRACKER_EVENT_TYPE_EXIT_FULLSCREEN,
        TRACKER_EVENT_TYPE_MUTE,
        TRACKER_EVENT_TYPE_UNMUTE,
        TRACKER_EVENT_TYPE_PAUSE,
        TRACKER_EVENT_TYPE_RESUME,
        TRACKER_EVENT_TYPE_ERROR,
        TRACKER_EVENT_TYPE_CUSTOM_EVENT_VIDEO,
        TRACKER_EVENT_TYPE_IAS
    }

    /* compiled from: NativeStrandTracker */
    enum C3001b {
        TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED,
        TRACKER_TYPE_URL_PING,
        TRACKER_TYPE_URL_WEBVIEW_PING,
        TRACKER_TYPE_HTML_SCRIPT
    }

    public ah(String str, int i, C3000a c3000a, Map<String, String> map) {
        this(C3001b.TRACKER_TYPE_URL_PING, str, i, c3000a, map);
    }

    ah(C3001b c3001b, String str, int i, C3000a c3000a, Map<String, String> map) {
        this.f7102b = c3001b;
        this.f7103c = str.trim();
        this.f7104d = i;
        this.f7105e = c3000a;
        this.f7106f = map;
    }

    void m9294a(@NonNull Map<String, Object> map) {
        this.f7107g = new HashMap(map);
    }

    @NonNull
    Map<String, Object> m9293a() {
        return this.f7107g;
    }

    @Nullable
    static ah m9289a(JSONObject jSONObject) {
        try {
            m9292b(jSONObject.getString("type"));
            return new ah(jSONObject.getString("url"), jSONObject.optInt("eventId", -1), m9288a(jSONObject.getString("eventType")), new HashMap());
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7101a, "Error building tracker from JSONObject; " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return null;
        }
    }

    private static C3001b m9292b(String str) {
        if (str == null || str.length() == 0) {
            return C3001b.TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED;
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        Object obj = -1;
        switch (toLowerCase.hashCode()) {
            case -1918378017:
                if (toLowerCase.equals("html_script")) {
                    obj = 3;
                    break;
                }
                break;
            case -970292670:
                if (toLowerCase.equals("url_ping")) {
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
            case 2015859192:
                if (toLowerCase.equals("webview_ping")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C3001b.TRACKER_TYPE_URL_PING;
            case 3:
                return C3001b.TRACKER_TYPE_HTML_SCRIPT;
            case 4:
                return C3001b.TRACKER_TYPE_URL_WEBVIEW_PING;
            default:
                return C3001b.TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED;
        }
    }

    static C3000a m9288a(String str) {
        if (str == null || str.length() == 0) {
            return C3000a.TRACKER_EVENT_TYPE_UNKNOWN;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1638835128:
                if (str.equals("midpoint")) {
                    obj = 10;
                    break;
                }
                break;
            case -1337830390:
                if (str.equals("thirdQuartile")) {
                    obj = 11;
                    break;
                }
                break;
            case -934426579:
                if (str.equals("resume")) {
                    obj = 19;
                    break;
                }
                break;
            case -840405966:
                if (str.equals("unmute")) {
                    obj = 17;
                    break;
                }
                break;
            case -599445191:
                if (str.equals("complete")) {
                    obj = 12;
                    break;
                }
                break;
            case -284840886:
                if (str.equals("unknown")) {
                    obj = 1;
                    break;
                }
                break;
            case -174104201:
                if (str.equals("client_fill")) {
                    obj = 3;
                    break;
                }
                break;
            case -45894975:
                if (str.equals("IAS_VIEWABILITY")) {
                    obj = 21;
                    break;
                }
                break;
            case 3327206:
                if (str.equals(TrackLoadSettingsAtom.TYPE)) {
                    obj = 2;
                    break;
                }
                break;
            case 3363353:
                if (str.equals("mute")) {
                    obj = 16;
                    break;
                }
                break;
            case 94750088:
                if (str.equals("click")) {
                    obj = 7;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    obj = 20;
                    break;
                }
                break;
            case 106440182:
                if (str.equals("pause")) {
                    obj = 18;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    obj = 8;
                    break;
                }
                break;
            case 110066619:
                if (str.equals("fullscreen")) {
                    obj = 14;
                    break;
                }
                break;
            case 113951609:
                if (str.equals("exitFullscreen")) {
                    obj = 15;
                    break;
                }
                break;
            case 354294980:
                if (str.equals("VideoImpression")) {
                    obj = 5;
                    break;
                }
                break;
            case 560220243:
                if (str.equals("firstQuartile")) {
                    obj = 9;
                    break;
                }
                break;
            case 883937877:
                if (str.equals("page_view")) {
                    obj = 6;
                    break;
                }
                break;
            case 1778167540:
                if (str.equals("creativeView")) {
                    obj = 13;
                    break;
                }
                break;
            case 2114088489:
                if (str.equals("Impression")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C3000a.TRACKER_EVENT_TYPE_LOAD;
            case 3:
                return C3000a.TRACKER_EVENT_TYPE_CLIENT_FILL;
            case 4:
                return C3000a.TRACKER_EVENT_TYPE_RENDER;
            case 5:
                return C3000a.TRACKER_EVENT_TYPE_VIDEO_RENDER;
            case 6:
                return C3000a.TRACKER_EVENT_TYPE_PAGE_VIEW;
            case 7:
                return C3000a.TRACKER_EVENT_TYPE_CLICK;
            case 8:
                return C3000a.TRACKER_EVENT_TYPE_PLAY;
            case 9:
                return C3000a.TRACKER_EVENT_TYPE_Q1;
            case 10:
                return C3000a.TRACKER_EVENT_TYPE_Q2;
            case 11:
                return C3000a.TRACKER_EVENT_TYPE_Q3;
            case 12:
                return C3000a.TRACKER_EVENT_TYPE_Q4;
            case 13:
                return C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            case 14:
                return C3000a.TRACKER_EVENT_TYPE_FULLSCREEN;
            case 15:
                return C3000a.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN;
            case 16:
                return C3000a.TRACKER_EVENT_TYPE_MUTE;
            case 17:
                return C3000a.TRACKER_EVENT_TYPE_UNMUTE;
            case 18:
                return C3000a.TRACKER_EVENT_TYPE_PAUSE;
            case 19:
                return C3000a.TRACKER_EVENT_TYPE_RESUME;
            case 20:
                return C3000a.TRACKER_EVENT_TYPE_ERROR;
            case 21:
                return C3000a.TRACKER_EVENT_TYPE_IAS;
            default:
                return C3000a.TRACKER_EVENT_TYPE_UNKNOWN;
        }
    }

    private static String m9291a(C3001b c3001b) {
        switch (c3001b) {
            case TRACKER_TYPE_URL_PING:
                return "url_ping";
            case TRACKER_TYPE_HTML_SCRIPT:
                return "html_script";
            case TRACKER_TYPE_URL_WEBVIEW_PING:
                return "webview_ping";
            default:
                return "unknown";
        }
    }

    static String m9290a(C3000a c3000a) {
        switch (c3000a) {
            case TRACKER_EVENT_TYPE_LOAD:
                return TrackLoadSettingsAtom.TYPE;
            case TRACKER_EVENT_TYPE_CLIENT_FILL:
                return "client_fill";
            case TRACKER_EVENT_TYPE_RENDER:
                return "Impression";
            case TRACKER_EVENT_TYPE_VIDEO_RENDER:
                return "VideoImpression";
            case TRACKER_EVENT_TYPE_PAGE_VIEW:
                return "page_view";
            case TRACKER_EVENT_TYPE_CLICK:
                return "click";
            case TRACKER_EVENT_TYPE_PLAY:
                return "start";
            case TRACKER_EVENT_TYPE_Q1:
                return "firstQuartile";
            case TRACKER_EVENT_TYPE_Q2:
                return "midpoint";
            case TRACKER_EVENT_TYPE_Q3:
                return "thirdQuartile";
            case TRACKER_EVENT_TYPE_Q4:
                return "complete";
            case TRACKER_EVENT_TYPE_CREATIVE_VIEW:
                return "creativeView";
            case TRACKER_EVENT_TYPE_FULLSCREEN:
                return "fullscreen";
            case TRACKER_EVENT_TYPE_EXIT_FULLSCREEN:
                return "exitFullscreen";
            case TRACKER_EVENT_TYPE_MUTE:
                return "mute";
            case TRACKER_EVENT_TYPE_UNMUTE:
                return "unmute";
            case TRACKER_EVENT_TYPE_PAUSE:
                return "pause";
            case TRACKER_EVENT_TYPE_RESUME:
                return "resume";
            case TRACKER_EVENT_TYPE_ERROR:
                return "error";
            case TRACKER_EVENT_TYPE_IAS:
                return "IAS_VIEWABILITY";
            default:
                return "unknown";
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", m9291a(this.f7102b));
            jSONObject.put("url", this.f7103c);
            jSONObject.put("eventType", m9290a(this.f7105e));
            jSONObject.put("eventId", this.f7104d);
            jSONObject.put("extras", C3155d.m10403a(this.f7106f == null ? new HashMap() : this.f7106f, ","));
            return jSONObject.toString();
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7101a, "Error serializing an " + f7101a + " instance (" + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }

    public String m9295b() {
        return this.f7103c;
    }

    public C3000a m9297c() {
        return this.f7105e;
    }

    public Map<String, String> m9298d() {
        return this.f7106f;
    }

    public void m9296b(Map<String, String> map) {
        this.f7106f = map;
    }
}
