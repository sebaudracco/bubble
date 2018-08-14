package com.facebook.ads.internal.p059a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.ads.internal.p069m.C2012c;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;

public class C1877d {

    public interface C1876a {
        C1875c mo3690a();

        Collection<String> mo3692b();

        String mo3642c();
    }

    public static Collection<String> m5638a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean m5639a(Context context, C1876a c1876a, C2012c c2012c) {
        C1875c a = c1876a.mo3690a();
        if (a == null || a == C1875c.NONE) {
            return false;
        }
        Collection<String> b = c1876a.mo3692b();
        if (b == null || b.isEmpty()) {
            return false;
        }
        boolean z;
        for (String a2 : b) {
            if (C1877d.m5640a(context, a2)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z != (a == C1875c.INSTALLED)) {
            return false;
        }
        Object c = c1876a.mo3642c();
        if (TextUtils.isEmpty(c)) {
            return true;
        }
        c2012c.mo3711b(c, null);
        return true;
    }

    public static boolean m5640a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        } catch (RuntimeException e2) {
            return false;
        }
    }
}
