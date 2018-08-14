package com.inmobi.commons.core.configs;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Config */
public abstract class C3045a {
    private C3117a f7373a = new C3117a();

    /* compiled from: Config */
    public static final class C3117a {
        private HashMap<String, Boolean> f7643a = new HashMap();

        public C3117a() {
            this.f7643a.put("O1", Boolean.valueOf(true));
            this.f7643a.put("SID", Boolean.valueOf(true));
            this.f7643a.put("LID", Boolean.valueOf(true));
            this.f7643a.put("UM5", Boolean.valueOf(true));
            this.f7643a.put("GPID", Boolean.valueOf(true));
            this.f7643a.put("IMID", Boolean.valueOf(true));
            this.f7643a.put("AIDL", Boolean.valueOf(true));
            this.f7643a.put("SHA1_IMEI", Boolean.valueOf(false));
            this.f7643a.put("MD5_IMEI", Boolean.valueOf(false));
        }

        public HashMap<String, Boolean> m10168a() {
            return this.f7643a;
        }
    }

    public abstract String mo6231a();

    public abstract boolean mo6234c();

    public abstract C3045a mo6235d();

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("includeIds");
        for (int i = 0; i < jSONObject2.length(); i++) {
            this.f7373a.f7643a.put("O1", Boolean.valueOf(jSONObject2.getBoolean("O1")));
            this.f7373a.f7643a.put("SID", Boolean.valueOf(jSONObject2.getBoolean("SID")));
            this.f7373a.f7643a.put("LID", Boolean.valueOf(jSONObject2.getBoolean("LID")));
            this.f7373a.f7643a.put("UM5", Boolean.valueOf(jSONObject2.getBoolean("UM5")));
            this.f7373a.f7643a.put("GPID", Boolean.valueOf(jSONObject2.getBoolean("GPID")));
            this.f7373a.f7643a.put("IMID", Boolean.valueOf(jSONObject2.getBoolean("IMID")));
            this.f7373a.f7643a.put("AIDL", Boolean.valueOf(jSONObject2.getBoolean("AIDL")));
            this.f7373a.f7643a.put("SHA1_IMEI", Boolean.valueOf(jSONObject2.optBoolean("SHA1_IMEI", false)));
            this.f7373a.f7643a.put("MD5_IMEI", Boolean.valueOf(jSONObject2.optBoolean("MD5_IMEI", false)));
        }
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("O1", this.f7373a.f7643a.get("O1"));
        jSONObject2.put("SID", this.f7373a.f7643a.get("SID"));
        jSONObject2.put("LID", this.f7373a.f7643a.get("LID"));
        jSONObject2.put("UM5", this.f7373a.f7643a.get("UM5"));
        jSONObject2.put("GPID", this.f7373a.f7643a.get("GPID"));
        jSONObject2.put("IMID", this.f7373a.f7643a.get("IMID"));
        jSONObject2.put("AIDL", this.f7373a.f7643a.get("AIDL"));
        jSONObject2.put("SHA1_IMEI", this.f7373a.f7643a.get("SHA1_IMEI"));
        jSONObject2.put("MD5_IMEI", this.f7373a.f7643a.get("MD5_IMEI"));
        jSONObject.put("includeIds", jSONObject2);
        return jSONObject;
    }

    public C3117a m9709r() {
        return this.f7373a;
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass() && mo6231a().equals(((C3045a) obj).mo6231a())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return mo6231a().hashCode();
    }
}
