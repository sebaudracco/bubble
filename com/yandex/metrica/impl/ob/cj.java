package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import com.yandex.metrica.impl.am;
import org.json.JSONException;
import org.json.JSONObject;

public class cj {
    private final String f12104a;
    private final String f12105b;
    private final String f12106c;
    private final Point f12107d;

    public cj(Context context) {
        String str;
        this.f12104a = Build.MANUFACTURER;
        this.f12105b = Build.MODEL;
        if (VERSION.SDK_INT > 8) {
            str = Build.SERIAL;
        } else {
            str = "";
        }
        this.f12106c = str;
        int i = am.m14597a(context).y;
        int i2 = am.m14597a(context).x;
        this.f12107d = new Point(Math.min(i, i2), Math.max(i, i2));
    }

    public cj(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f12104a = jSONObject.getString("manufacturer");
        this.f12105b = jSONObject.getString("model");
        this.f12106c = jSONObject.getString("serial");
        this.f12107d = new Point(jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    public JSONObject m15586a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("manufacturer", this.f12104a);
        jSONObject.put("model", this.f12105b);
        jSONObject.put("serial", this.f12106c);
        jSONObject.put("width", this.f12107d.x);
        jSONObject.put("height", this.f12107d.y);
        return jSONObject;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        cj cjVar = (cj) o;
        if (this.f12104a == null ? cjVar.f12104a != null : !this.f12104a.equals(cjVar.f12104a)) {
            return false;
        }
        if (this.f12105b == null ? cjVar.f12105b != null : !this.f12105b.equals(cjVar.f12105b)) {
            return false;
        }
        if (this.f12106c == null ? cjVar.f12106c != null : !this.f12106c.equals(cjVar.f12106c)) {
            return false;
        }
        if (this.f12107d != null) {
            return this.f12107d.equals(cjVar.f12107d);
        }
        if (cjVar.f12107d != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f12104a != null) {
            hashCode = this.f12104a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.f12105b != null) {
            hashCode = this.f12105b.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.f12106c != null) {
            hashCode = this.f12106c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.f12107d != null) {
            i = this.f12107d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "DeviceShapshot{mManufacturer='" + this.f12104a + '\'' + ", mModel='" + this.f12105b + '\'' + ", mSerial='" + this.f12106c + '\'' + ", mScreenSize=" + this.f12107d + '}';
    }
}
