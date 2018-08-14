package com.vungle.publisher;

import com.vungle.publisher.wu.a;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class vu extends wu {
    protected Map<String, List<String>> f3419a;

    @Singleton
    /* compiled from: vungle */
    protected static class C1676a extends a<vu> {
        @Inject
        Provider<vu> f3418a;

        public /* synthetic */ wu m4759b(JSONObject jSONObject) throws JSONException {
            return m4757a(jSONObject);
        }

        protected /* synthetic */ Object m4760b() {
            return m4756a();
        }

        protected /* synthetic */ Object[] m4761b(int i) {
            return m4758a(i);
        }

        public /* synthetic */ Object m4762c(JSONObject jSONObject) throws JSONException {
            return m4757a(jSONObject);
        }

        @Inject
        C1676a() {
        }

        public vu m4757a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            vu a = m4756a();
            a.f3419a = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                List h = ra.h(jSONObject, str);
                if (!(str == null || h == null)) {
                    a.f3419a.put(str, h);
                }
            }
            return a;
        }

        protected vu[] m4758a(int i) {
            return new vu[i];
        }

        protected vu m4756a() {
            return (vu) this.f3418a.get();
        }
    }

    @Inject
    vu() {
    }

    public Collection<String> m4764c() {
        if (this.f3419a != null) {
            return this.f3419a.keySet();
        }
        return null;
    }

    public List<String> m4763a(String str) {
        if (this.f3419a != null) {
            return (List) this.f3419a.get(str);
        }
        return null;
    }
}
