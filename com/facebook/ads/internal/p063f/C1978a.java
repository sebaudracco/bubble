package com.facebook.ads.internal.p063f;

import com.appnext.base.p019a.p022c.C1028c;
import com.facebook.ads.internal.p056q.p057a.C2122m;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.HashMap;
import java.util.Map;

public class C1978a extends C1977d {
    public C1978a(String str, String str2) {
        super(C2122m.m6805b(), C2122m.m6806c(), C1978a.m6241a(str, str2));
    }

    private static Map<String, String> m6241a(String str, String str2) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C1028c.gv, str);
        hashMap.put(FirebaseAnalytics$Param.VALUE, str2);
        return hashMap;
    }

    public String mo3705a() {
        return "client_response";
    }
}
