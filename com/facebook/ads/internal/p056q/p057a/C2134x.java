package com.facebook.ads.internal.p056q.p057a;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2134x {
    private static final String f5085a = C2134x.class.getSimpleName();

    public static boolean m6837a(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static boolean m6838a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(f5085a, "Invalid Window info in window interactive check, assuming is not a Lockscreen.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        String str3 = (String) map.get("kgr");
        return str != null && str.equals(SchemaSymbols.ATTVAL_TRUE_1) && str2 != null && str2.equals(SchemaSymbols.ATTVAL_TRUE_1) && str3 != null && str3.equals(SchemaSymbols.ATTVAL_TRUE);
    }

    public static boolean m6839b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            Log.v(f5085a, "Invalid Window info in window interactive check, assuming not obstructed by Keyguard.");
            return false;
        }
        String str = (String) map.get("wfdkg");
        String str2 = (String) map.get("wfswl");
        if (str != null && str.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
            return false;
        }
        if (str2 != null && str2.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
            return false;
        }
        str = (String) map.get("kgr");
        boolean z = str != null && str.equals(SchemaSymbols.ATTVAL_TRUE);
        return z;
    }
}
