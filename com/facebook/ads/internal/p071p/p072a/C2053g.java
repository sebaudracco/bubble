package com.facebook.ads.internal.p071p.p072a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class C2053g implements C2052r {
    private void m6600a(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : (List) map.get(str)) {
                    mo3742a(str + ":" + str2);
                }
            }
        }
    }

    public void mo3741a(C2060n c2060n) {
        if (c2060n != null) {
            mo3742a("=== HTTP Response ===");
            mo3742a("Receive url: " + c2060n.m6617b());
            mo3742a("Status: " + c2060n.m6616a());
            m6600a(c2060n.m6618c());
            mo3742a("Content:\n" + c2060n.m6620e());
        }
    }

    public void mo3742a(String str) {
        System.out.println(str);
    }

    public void mo3743a(HttpURLConnection httpURLConnection, Object obj) {
        mo3742a("=== HTTP Request ===");
        mo3742a(httpURLConnection.getRequestMethod() + " " + httpURLConnection.getURL().toString());
        if (obj instanceof String) {
            mo3742a("Content: " + ((String) obj));
        }
        m6600a(httpURLConnection.getRequestProperties());
    }

    public boolean mo3744a() {
        return AdInternalSettings.isDebugBuild();
    }
}
