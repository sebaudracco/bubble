package com.facebook.ads.internal.p056q.p078e;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import com.facebook.ads.internal.p056q.p057a.C2134x;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C2152b {
    private static final String f5138a = C2152b.class.getSimpleName();

    public static Map<String, String> m6891a(Context context) {
        Map<String, String> hashMap = new HashMap();
        if (context == null) {
            Log.v(f5138a, "Null context in window interactive check.");
            return hashMap;
        }
        try {
            hashMap.put("kgr", String.valueOf(C2152b.m6893c(context)));
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    int i = window.getAttributes().flags;
                    hashMap.put("wt", Integer.toString(window.getAttributes().type));
                    hashMap.put("wfdkg", (4194304 & i) > 0 ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
                    hashMap.put("wfswl", (524288 & i) > 0 ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
                } else {
                    Log.v(f5138a, "Invalid window in window interactive check, assuming interactive.");
                }
            } else {
                Log.v(f5138a, "Invalid Activity context in window interactive check, assuming interactive.");
            }
        } catch (Throwable e) {
            Log.e(f5138a, "Exception in window info check", e);
            C2150a.m6888a(e, context);
        }
        return hashMap;
    }

    public static boolean m6892b(Context context) {
        return !C2134x.m6839b(C2152b.m6891a(context));
    }

    public static boolean m6893c(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }
}
