package com.bgjd.ici.p030e;

import android.database.Cursor;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1403a;
import com.bgjd.ici.p025b.ac;
import com.mobfox.sdk.networking.RequestParams;

public class C1476a {
    private int f2343a = 0;
    private String f2344b;
    private String f2345c;
    private String f2346d;
    private int f2347e;
    private int f2348f;
    private String f2349g;
    private int f2350h;
    private int f2351i;
    private int f2352j;
    private String f2353k;
    private double f2354l;
    private long f2355m = 0;
    private long f2356n = 0;
    private long f2357o = 0;
    private long f2358p = 0;
    private String f2359q = "";
    private int f2360r = 0;
    private int f2361s = 0;
    private int f2362t = 0;

    public C1476a(Cursor cursor) {
        this.f2343a = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        this.f2344b = cursor.getString(cursor.getColumnIndexOrThrow("beacon_uid"));
        this.f2345c = cursor.getString(cursor.getColumnIndexOrThrow("major"));
        this.f2346d = cursor.getString(cursor.getColumnIndexOrThrow("minor"));
        this.f2347e = cursor.getInt(cursor.getColumnIndexOrThrow("rssi"));
        this.f2348f = cursor.getInt(cursor.getColumnIndexOrThrow("power"));
        this.f2349g = cursor.getString(cursor.getColumnIndexOrThrow("bluetooth_address"));
        this.f2350h = cursor.getInt(cursor.getColumnIndexOrThrow("beacon_type"));
        this.f2351i = cursor.getInt(cursor.getColumnIndexOrThrow("service_uuid"));
        this.f2352j = cursor.getInt(cursor.getColumnIndexOrThrow("manufacturer"));
        this.f2353k = cursor.getString(cursor.getColumnIndexOrThrow("bluetooth_name"));
        this.f2354l = cursor.getDouble(cursor.getColumnIndexOrThrow("distance"));
        this.f2355m = cursor.getLong(cursor.getColumnIndexOrThrow("telemetry_version"));
        this.f2356n = cursor.getLong(cursor.getColumnIndexOrThrow("battery_milli_volts"));
        this.f2357o = cursor.getLong(cursor.getColumnIndexOrThrow("pdu_count"));
        this.f2358p = cursor.getLong(cursor.getColumnIndexOrThrow("up_time"));
        this.f2359q = cursor.getString(cursor.getColumnIndexOrThrow("url"));
        this.f2360r = cursor.getInt(cursor.getColumnIndexOrThrow("bid"));
        this.f2361s = cursor.getInt(cursor.getColumnIndexOrThrow("pid"));
        this.f2362t = cursor.getInt(cursor.getColumnIndexOrThrow("lid"));
    }

    public C1476a(String str, String str2, String str3, int i, int i2, String str4, int i3, int i4, int i5, String str5, double d, long j, long j2, long j3, long j4, String str6) {
        this.f2344b = str;
        this.f2345c = str2;
        this.f2346d = str3;
        this.f2347e = i;
        this.f2348f = i2;
        this.f2349g = str4;
        this.f2350h = i3;
        this.f2351i = i4;
        this.f2352j = i5;
        this.f2353k = str5 != null ? ac.m2876e(str5) : "";
        this.f2354l = d;
        this.f2355m = j;
        this.f2356n = j2;
        this.f2357o = j3;
        this.f2358p = j4;
        this.f2359q = str6 != null ? ac.m2876e(str6) : "";
    }

    public C1476a(String str, String str2, String str3, int i, int i2, String str4, int i3, int i4, int i5, String str5, double d, long j, long j2, long j3, long j4, String str6, int i6, int i7, int i8) {
        this.f2344b = str;
        this.f2345c = str2;
        this.f2346d = str3;
        this.f2347e = i;
        this.f2348f = i2;
        this.f2349g = str4;
        this.f2350h = i3;
        this.f2351i = i4;
        this.f2352j = i5;
        this.f2353k = str5 != null ? ac.m2876e(str5) : "";
        this.f2354l = d;
        this.f2355m = j;
        this.f2356n = j2;
        this.f2357o = j3;
        this.f2358p = j4;
        this.f2359q = str6 != null ? ac.m2876e(str6) : "";
        this.f2360r = i6;
        this.f2361s = i7;
        this.f2362t = i8;
    }

    public String m3064a() {
        return String.format("INSERT OR IGNORE INTO %s (beacon_uid,major,minor,rssi,power,bluetooth_address,beacon_type,service_uuid,manufacturer,bluetooth_name,distance,telemetry_version,battery_milli_volts,pdu_count,up_time,url,bid,pid,lid,status) VALUES('%s','%s','%s',%s,%s,'%s',%s,%s,%s,'%s',%s,%s,%s,%s,%s,'%s',%s,%s,%s,0)", new Object[]{C1403a.f2095y, this.f2344b, this.f2345c, this.f2346d, Integer.valueOf(this.f2347e), Integer.valueOf(this.f2348f), this.f2349g, Integer.valueOf(this.f2350h), Integer.valueOf(this.f2351i), Integer.valueOf(this.f2352j), this.f2353k, Double.valueOf(this.f2354l), Long.valueOf(this.f2355m), Long.valueOf(this.f2356n), Long.valueOf(this.f2357o), Long.valueOf(this.f2358p), this.f2359q, Integer.valueOf(this.f2360r), Integer.valueOf(this.f2361s), Integer.valueOf(this.f2362t)});
    }

    public String toString() {
        return m3064a();
    }

    public JSONObject m3065b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f2344b);
            jSONObject.put("b", this.f2345c);
            jSONObject.put("c", this.f2346d);
            jSONObject.put("d", this.f2347e);
            jSONObject.put("e", this.f2348f);
            jSONObject.put("f", this.f2349g);
            jSONObject.put("g", this.f2350h);
            jSONObject.put(RequestParams.f9035H, this.f2351i);
            jSONObject.put(RequestParams.IP, this.f2352j);
            jSONObject.put("j", this.f2353k);
            jSONObject.put("k", this.f2354l);
            jSONObject.put("l", this.f2355m);
            jSONObject.put(RequestParams.f9036M, this.f2356n);
            jSONObject.put("n", this.f2357o);
            jSONObject.put("o", this.f2358p);
            jSONObject.put("p", this.f2359q);
            jSONObject.put("q", this.f2360r);
            jSONObject.put("r", this.f2361s);
            jSONObject.put("s", this.f2362t);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public int m3066c() {
        return this.f2343a;
    }
}
