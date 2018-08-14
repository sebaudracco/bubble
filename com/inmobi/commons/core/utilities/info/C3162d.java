package com.inmobi.commons.core.utilities.info;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.C3168c;
import com.inmobi.commons.p112a.C3106b;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IdentityInfo */
public class C3162d {
    private static final String f7801a = C3162d.class.getSimpleName();

    private static String m10442b() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    private static String m10443c() {
        Calendar instance = Calendar.getInstance();
        return String.valueOf(instance.get(16) + instance.get(15));
    }

    public static Map<String, String> m10441a() {
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("mk-version", C3106b.m10092a());
            Boolean m = C3168c.m10513a().m10534m();
            if (m != null) {
                hashMap.put("u-id-adt", String.valueOf(m.booleanValue() ? 1 : 0));
            }
            hashMap.put("ts", C3162d.m10442b());
            hashMap.put("tz", C3162d.m10443c());
            hashMap.putAll(C3164f.m10487a().m10495d());
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7801a, "SDK encountered unexpected error in getting UID info; " + e.getMessage());
        }
        return hashMap;
    }
}
