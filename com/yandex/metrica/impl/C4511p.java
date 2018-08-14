package com.yandex.metrica.impl;

import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.utils.C4525g;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public final class C4511p {
    public static final EnumSet<C4510a> f12559a = EnumSet.of(C4510a.EVENT_TYPE_INIT, new C4510a[]{C4510a.EVENT_TYPE_INIT_BACKGROUND, C4510a.EVENT_TYPE_CUSTOM_EVENT, C4510a.EVENT_TYPE_FIRST_ACTIVATION, C4510a.EVENT_TYPE_REFERRER_RECEIVED, C4510a.EVENT_TYPE_REFERRER_DEPRECATED, C4510a.EVENT_TYPE_APP_UPDATE});
    private static final EnumSet<C4510a> f12560b = EnumSet.of(C4510a.EVENT_TYPE_UNDEFINED, new C4510a[]{C4510a.EVENT_TYPE_PURGE_BUFFER, C4510a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C4510a.EVENT_TYPE_REFERRER_RECEIVED, C4510a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, C4510a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, C4510a.EVENT_TYPE_REFERRER_DEPRECATED, C4510a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C4510a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, C4510a.EVENT_TYPE_ACTIVATION});
    private static final EnumSet<C4510a> f12561c = EnumSet.of(C4510a.EVENT_TYPE_SET_USER_INFO, new C4510a[]{C4510a.EVENT_TYPE_REPORT_USER_INFO, C4510a.EVENT_TYPE_IDENTITY, C4510a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C4510a.EVENT_TYPE_UNDEFINED, C4510a.EVENT_TYPE_INIT, C4510a.EVENT_TYPE_APP_UPDATE, C4510a.EVENT_TYPE_REFERRER_DEPRECATED, C4510a.EVENT_TYPE_ALIVE, C4510a.EVENT_TYPE_INIT_BACKGROUND, C4510a.EVENT_TYPE_STARTUP, C4510a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C4510a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED, C4510a.EVENT_TYPE_ACTIVATION});
    private static final EnumSet<C4510a> f12562d = EnumSet.of(C4510a.EVENT_TYPE_ACTIVITY_END, C4510a.EVENT_TYPE_SET_USER_INFO, C4510a.EVENT_TYPE_REPORT_USER_INFO);
    private static final EnumSet<C4510a> f12563e = EnumSet.of(C4510a.EVENT_TYPE_STARTUP, new C4510a[]{C4510a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS, C4510a.EVENT_TYPE_REFERRER_RECEIVED, C4510a.EVENT_TYPE_REFERRER_DEPRECATED, C4510a.EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED, C4510a.EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED, C4510a.EVENT_TYPE_UNDEFINED, C4510a.EVENT_TYPE_ALIVE, C4510a.EVENT_TYPE_INIT_BACKGROUND, C4510a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED, C4510a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED});
    private static final EnumSet<C4510a> f12564f = EnumSet.of(C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED, C4510a.EVENT_TYPE_EXCEPTION_USER, C4510a.EVENT_TYPE_NATIVE_CRASH, C4510a.EVENT_TYPE_REGULAR);
    private static final EnumSet<C4510a> f12565g = EnumSet.of(C4510a.EVENT_TYPE_REGULAR);

    public enum C4510a {
        EVENT_TYPE_UNDEFINED(-1, "Unrecognized action"),
        EVENT_TYPE_INIT(0, "First initialization event"),
        EVENT_TYPE_REGULAR(1, "Regular event"),
        EVENT_TYPE_ACTIVITY_START_DEPRECATED(2, "Start of interaction with UI"),
        EVENT_TYPE_ACTIVITY_END(3, "End of interaction with UI"),
        EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED(4, "Deprecated crash of App"),
        EVENT_TYPE_EXCEPTION_USER(5, "Error from developer"),
        EVENT_TYPE_REFERRER_DEPRECATED(6, "Deprecated sending referrer"),
        EVENT_TYPE_ALIVE(7, "App is still alive"),
        EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS(8, "Update collect apps"),
        EVENT_TYPE_SET_USER_INFO(9, "User info"),
        EVENT_TYPE_REPORT_USER_INFO(10, "Report user info"),
        EVENT_TYPE_PURGE_BUFFER(256, "Forcible buffer clearing"),
        EVENT_TYPE_SESSION_START_MANUALLY(512, "Manual start of session"),
        EVENT_TYPE_NATIVE_CRASH(768, "Native crash of App"),
        EVENT_TYPE_INIT_BACKGROUND(1280, "First initialization event in background mode"),
        EVENT_TYPE_STARTUP(1536, "Sending the startup due to lack of data"),
        EVENT_TYPE_IDENTITY(1792, "System identification"),
        EVENT_TYPE_STATBOX(2304, "Event with statistical data"),
        EVENT_TYPE_REFERRER_RECEIVED(4096, "Referrer received"),
        EVENT_TYPE_MIGRATE_EVENT_FORMAT_DEPRECATED(4352, "Migrate event format"),
        EVENT_TYPE_MIGRATE_TO_UUID_API_KEY_DEPRECATED(4608, "Migrate to uuid api key"),
        EVENT_TYPE_APP_ENVIRONMENT_UPDATED(5376, "App Environment Updated"),
        EVENT_TYPE_APP_ENVIRONMENT_CLEARED(5632, "App Environment Cleared"),
        EVENT_TYPE_EXCEPTION_UNHANDLED(5888, "Crash of App"),
        EVENT_TYPE_ACTIVATION(6144, "Activation of metrica"),
        EVENT_TYPE_FIRST_ACTIVATION(6145, "First activation of metrica"),
        EVENT_TYPE_START(6400, "Start of new session"),
        EVENT_TYPE_CUSTOM_EVENT(8192, "Custom event"),
        EVENT_TYPE_APP_OPEN(8208, "App open event"),
        EVENT_TYPE_APP_UPDATE(8224, "App update event"),
        EVENT_TYPE_PERMISSIONS(12288, "Permissions changed event"),
        EVENT_TYPE_APP_FEATURES(12289, "Features, required by application");
        
        static final SparseArray<C4510a> f12529H = null;
        private final int f12557I;
        private final String f12558J;

        static {
            f12529H = new SparseArray();
            C4510a[] values = C4510a.values();
            int length = values.length;
            int i;
            while (i < length) {
                C4510a c4510a = values[i];
                f12529H.put(c4510a.m16188a(), c4510a);
                i++;
            }
        }

        private C4510a(int i, String str) {
            this.f12557I = i;
            this.f12558J = str;
        }

        public int m16188a() {
            return this.f12557I;
        }

        public String m16189b() {
            return this.f12558J;
        }

        public static C4510a m16187a(int i) {
            C4510a c4510a = (C4510a) f12529H.get(i);
            return c4510a == null ? EVENT_TYPE_UNDEFINED : c4510a;
        }
    }

    public static boolean m16199a(C4510a c4510a) {
        return !f12560b.contains(c4510a);
    }

    public static boolean m16203b(C4510a c4510a) {
        return !f12561c.contains(c4510a);
    }

    public static boolean m16197a(int i) {
        return f12562d.contains(C4510a.m16187a(i));
    }

    public static boolean m16198a(C4372h c4372h) {
        return (c4372h.m15062c() == C4510a.EVENT_TYPE_SET_USER_INFO.m16188a() || c4372h.m15062c() == C4510a.EVENT_TYPE_REPORT_USER_INFO.m16188a()) && !TextUtils.isEmpty(c4372h.m15078l());
    }

    public static boolean m16207c(C4510a c4510a) {
        return !f12563e.contains(c4510a);
    }

    public static boolean m16202b(int i) {
        return f12564f.contains(C4510a.m16187a(i));
    }

    public static boolean m16206c(int i) {
        return f12565g.contains(C4510a.m16187a(i));
    }

    static C4372h m16194a(C4510a c4510a, String str) {
        return new C4373e(str, c4510a.m16189b(), c4510a.m16188a());
    }

    public static C4372h m16208d(C4510a c4510a) {
        return new C4373e(c4510a.m16189b(), c4510a.m16188a());
    }

    public static C4372h m16195a(String str) {
        return new C4373e(str, C4510a.EVENT_TYPE_REGULAR.m16188a());
    }

    static C4372h m16196a(String str, String str2) {
        return new C4373e(str2, str, C4510a.EVENT_TYPE_REGULAR.m16188a());
    }

    static C4372h m16201b(String str, String str2) {
        return new C4373e(str2, str, C4510a.EVENT_TYPE_EXCEPTION_USER.m16188a());
    }

    static C4372h m16200b(String str) {
        return new C4373e(str, C4510a.EVENT_TYPE_START.m16188a());
    }

    static C4372h m16204c(String str) {
        return new C4373e(str, C4510a.EVENT_TYPE_ACTIVITY_END.m16188a());
    }

    static C4372h m16205c(String str, String str2) {
        return new C4373e(str2, str, C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED.m16188a());
    }

    public static C4372h m16209d(String str) {
        return new C4373e("", str, C4510a.EVENT_TYPE_REFERRER_RECEIVED.m16188a());
    }

    static C4372h m16192a(Uri uri) {
        return C4511p.m16210e(uri.toString());
    }

    static C4372h m16210e(String str) {
        Map hashMap = new HashMap();
        hashMap.put("link", str);
        return new C4373e(C4525g.m16271a(hashMap), "", C4510a.EVENT_TYPE_APP_OPEN.m16188a());
    }

    public static C4372h m16193a(an anVar) {
        return new C4373e(anVar == null ? "" : anVar.m14601a(), C4510a.EVENT_TYPE_ACTIVATION.m16189b(), C4510a.EVENT_TYPE_ACTIVATION.m16188a());
    }

    static C4372h m16190a() {
        return new C4373e(C4510a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS.m16189b(), C4510a.EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS.m16188a());
    }

    static C4372h m16191a(int i, String str, String str2, Map<String, Object> map) {
        return new C4373e(str2, str, C4510a.EVENT_TYPE_CUSTOM_EVENT.m16188a(), i).m15069e(C4525g.m16271a((Map) map));
    }
}
