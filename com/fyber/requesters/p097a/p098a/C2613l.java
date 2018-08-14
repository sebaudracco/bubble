package com.fyber.requesters.p097a.p098a;

import com.fyber.requesters.p097a.p098a.C2600c.C2599a;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import org.json.JSONObject;

/* compiled from: NetworkCacheConfig */
public final class C2613l extends C2600c {

    /* compiled from: NetworkCacheConfig */
    public static class C2612a extends C2599a {
        protected C2612a() {
        }

        public static C2612a m8380a(String str) {
            if (StringUtils.notNullNorEmpty(str)) {
                try {
                    return C2612a.m8381a(new JSONObject(str));
                } catch (Exception e) {
                    FyberLogger.m8450e("CacheConfig", "Couldn't parse json to retrieve container cache configuration", e);
                }
            }
            return new C2612a();
        }

        public static C2612a m8381a(JSONObject jSONObject) {
            C2599a c2612a = new C2612a();
            C2599a.m8334a(c2612a, jSONObject, "network_cache_configuration");
            return c2612a;
        }

        public final C2613l m8382a() {
            return new C2613l(this);
        }
    }

    protected C2613l(C2612a c2612a) {
        super(c2612a);
    }
}
