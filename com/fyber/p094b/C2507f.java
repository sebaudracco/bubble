package com.fyber.p094b;

import android.app.Activity;
import android.content.SharedPreferences;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.fyber.Fyber;
import com.fyber.p086a.C2408a;
import com.fyber.utils.C2642b;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MediationConfigurationNetworkOperation */
public final class C2507f extends C2506l<String, Map<String, Map<String, Object>>> {
    private SharedPreferences f6230b;

    protected final /* synthetic */ Object mo3927a(Object obj) {
        return m7979b((String) obj);
    }

    public static Future<Map<String, Map<String, Object>>> m7978a(C2408a c2408a, Activity activity) {
        C2672t c = C2672t.m8534a(C2646d.m8469a("config"), c2408a).m8544c();
        if (Fyber.getConfigs().m7607h()) {
            return Fyber.getConfigs().m7599a(new C2507f(c, c2408a.m7594c(), activity));
        }
        return null;
    }

    private C2507f(C2672t c2672t, String str, Activity activity) {
        super(c2672t, str, C2642b.m8457d());
        this.f6230b = activity.getSharedPreferences("MediationConfigurationNetworkOperation", 0);
    }

    private Map<String, Map<String, Object>> m7979b(String str) {
        if (StringUtils.nullOrEmpty(str)) {
            FyberLogger.m8448d("MediationConfigurationNetworkOperation", "No configs from the server, fallback to cached version.");
            str = this.f6230b.getString("MediationConfigurationNetworkOperation", "");
            if (StringUtils.nullOrEmpty(str)) {
                FyberLogger.m8448d("MediationConfigurationNetworkOperation", "There were no cached version to use.");
            } else {
                FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Using cached json file.");
            }
        }
        return C2507f.m7980c(str);
    }

    protected final String b_() {
        return "MediationConfigurationNetworkOperation";
    }

    private static Map<String, Map<String, Object>> m7980c(String str) {
        FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Reading config file");
        Map<String, Map<String, Object>> hashMap = new HashMap();
        if (StringUtils.notNullNorEmpty(str)) {
            try {
                FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Parsing configurations");
                JSONArray jSONArray = new JSONObject(str).getJSONArray("adapters");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String toLowerCase = jSONObject.getString("name").toLowerCase(Locale.ENGLISH);
                    if (jSONObject.has(C1404b.f2107K)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(C1404b.f2107K);
                        Map hashMap2 = new HashMap(jSONObject2.length());
                        Iterator keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            String str2 = (String) keys.next();
                            hashMap2.put(str2, jSONObject2.get(str2));
                        }
                        hashMap.put(toLowerCase, hashMap2);
                    } else {
                        hashMap.put(toLowerCase, Collections.emptyMap());
                    }
                }
                FyberLogger.m8448d("MediationConfigurationNetworkOperation", "configuration loaded successfully");
            } catch (Exception e) {
                FyberLogger.m8450e("MediationConfigurationNetworkOperation", "A JSON error occurred while parsing the configuration file, there will be no mediation configurations.", e);
            }
        } else {
            FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Configuration was not found, there will be no mediation configurations.");
        }
        return hashMap;
    }

    protected final /* synthetic */ Object mo3928a(String str) {
        FyberLogger.m8448d("MediationConfigurationNetworkOperation", "The signature is valid, proceeding...");
        String str2 = "";
        if (!StringUtils.notNullNorEmpty(str)) {
            return str2;
        }
        if (this.f6230b.edit().putString("MediationConfigurationNetworkOperation", str).commit()) {
            FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Server Side Configuration has been saved successfully.");
            return str;
        }
        FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Failed to save Server Side Configuration.");
        return str;
    }

    protected final /* synthetic */ Object mo3926a(int i, String str, String str2) {
        if (!m7977a(str, str2)) {
            FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Invalid signature, those configs will not be used.");
        }
        return "";
    }

    protected final /* synthetic */ Object mo3901b(IOException iOException) {
        FyberLogger.m8448d("MediationConfigurationNetworkOperation", "Connection error - " + iOException.getMessage());
        return m7979b("");
    }
}
