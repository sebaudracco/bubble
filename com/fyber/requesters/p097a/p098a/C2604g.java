package com.fyber.requesters.p097a.p098a;

import com.fyber.requesters.p097a.p098a.C2600c.C2599a;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import org.json.JSONObject;

/* compiled from: ContainerCacheConfig */
public final class C2604g extends C2600c {
    private String[] f6499a;
    private String[] f6500b;

    /* compiled from: ContainerCacheConfig */
    public static class C2603a extends C2599a {
        private String[] f6497c = new String[0];
        private String[] f6498d = new String[0];

        private C2603a() {
        }

        public static C2603a m8351a(String str) {
            if (StringUtils.notNullNorEmpty(str)) {
                try {
                    return C2603a.m8352a(new JSONObject(str));
                } catch (Exception e) {
                    FyberLogger.m8450e("ContainerCacheConfig", "Couldn't parse json to retrieve container cache configuration", e);
                }
            }
            return new C2603a();
        }

        public static C2603a m8352a(JSONObject jSONObject) {
            C2599a c2603a = new C2603a();
            C2599a.m8334a(c2603a, jSONObject, "container_cache_configuration");
            JSONObject optJSONObject = jSONObject.optJSONObject("container_cache_configuration");
            if (optJSONObject != null) {
                c2603a.f6497c = C2599a.m8335a(optJSONObject, "query_white_list");
                c2603a.f6498d = C2599a.m8335a(optJSONObject, "user_data_white_list");
            }
            return c2603a;
        }

        public final C2604g m8355a() {
            return new C2604g();
        }
    }

    private C2604g(C2603a c2603a) {
        super(c2603a);
        this.f6499a = c2603a.f6497c;
        this.f6500b = c2603a.f6498d;
    }

    public final String[] m8356c() {
        return this.f6499a;
    }

    public final String[] m8357d() {
        return this.f6500b;
    }
}
