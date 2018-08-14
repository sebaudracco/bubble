package com.yandex.metrica.impl.ob;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

public class ch {
    private final String f12092a;
    private final cj f12093b;
    private final long f12094c;
    private final boolean f12095d;
    private final long f12096e;

    public ch(JSONObject jSONObject, long j) throws JSONException {
        this.f12092a = jSONObject.getString("device_id");
        if (jSONObject.has("device_snapshot_key")) {
            this.f12093b = new cj(jSONObject.getString("device_snapshot_key"));
        } else {
            this.f12093b = null;
        }
        this.f12094c = jSONObject.optLong("last_elections_time", -1);
        this.f12095d = m15556f();
        this.f12096e = j;
    }

    public ch(String str, cj cjVar, long j) {
        this.f12092a = str;
        this.f12093b = cjVar;
        this.f12094c = j;
        this.f12095d = m15556f();
        this.f12096e = -1;
    }

    public String m15557a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_id", this.f12092a);
        if (this.f12093b != null) {
            jSONObject.put("device_snapshot_key", this.f12093b.m15586a());
        }
        jSONObject.put("last_elections_time", this.f12094c);
        return jSONObject.toString();
    }

    public boolean m15559b() {
        if (this.f12096e <= -1) {
            return false;
        }
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTimeInMillis(this.f12096e);
        if (instance.get(1) == 1970) {
            return true;
        }
        return false;
    }

    public String m15560c() {
        return this.f12092a;
    }

    public cj m15561d() {
        return this.f12093b;
    }

    public boolean m15562e() {
        return this.f12095d;
    }

    private boolean m15556f() {
        if (this.f12094c <= -1 || System.currentTimeMillis() - this.f12094c >= 604800000) {
            return false;
        }
        return true;
    }

    public boolean m15558a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ch chVar = (ch) obj;
        if (this.f12095d != chVar.f12095d) {
            return false;
        }
        if (!this.f12092a.equals(chVar.f12092a)) {
            return false;
        }
        if (this.f12093b != null) {
            return this.f12093b.equals(chVar.f12093b);
        }
        if (chVar.f12093b != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.f12092a.hashCode() * 31;
        if (this.f12093b != null) {
            hashCode = this.f12093b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f12095d) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "Credentials{mFresh=" + this.f12095d + ", mLastElectionsTime=" + this.f12094c + ", mDeviceSnapshot=" + this.f12093b + ", mDeviceID='" + this.f12092a + '\'' + '}';
    }
}
