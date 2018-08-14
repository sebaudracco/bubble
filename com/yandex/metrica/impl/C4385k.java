package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.appnext.base.p023b.C1048i;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.yandex.metrica.impl.C4305a.C4304a;
import com.yandex.metrica.impl.ob.C4502u;
import com.yandex.metrica.impl.ob.dy;
import com.yandex.metrica.impl.ob.dz;
import com.yandex.metrica.impl.ob.ea;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import org.json.JSONArray;
import org.json.JSONObject;

public final class C4385k {
    private Context f11878a;
    private ContentValues f11879b;
    private C4502u f11880c;

    class C43831 implements ea {
        final /* synthetic */ C4385k f11876a;

        C43831(C4385k c4385k) {
            this.f11876a = c4385k;
        }

        public void mo7033a(dz[] dzVarArr) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (dz dzVar : dzVarArr) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("cell_id", dzVar.m15848e());
                    jSONObject.putOpt("signal_strength", dzVar.m15843a());
                    jSONObject.putOpt("lac", dzVar.m15847d());
                    jSONObject.putOpt("country_code", dzVar.m15845b());
                    jSONObject.putOpt("operator_id", dzVar.m15846c());
                    jSONObject.putOpt("operator_name", dzVar.m15849f());
                    jSONObject.putOpt("is_connected", Boolean.valueOf(dzVar.m15851h()));
                    jSONObject.putOpt("cell_type", Integer.valueOf(dzVar.m15852i()));
                    jSONObject.putOpt("pci", dzVar.m15853j());
                    jSONArray.put(jSONObject);
                }
                this.f11876a.f11879b.put("cell_info", jSONArray.toString());
            } catch (Exception e) {
            }
        }
    }

    class C43842 implements eh {
        final /* synthetic */ C4385k f11877a;

        C43842(C4385k c4385k) {
            this.f11877a = c4385k;
        }

        public void mo7010a(eg egVar) {
            this.f11877a.f11879b.put("cellular_connection_type", egVar.m15902b().m15850g());
        }
    }

    public C4385k(Context context) {
        this.f11878a = context;
    }

    public C4385k m15110a(ContentValues contentValues) {
        this.f11879b = contentValues;
        return this;
    }

    public C4385k m15111a(C4502u c4502u) {
        this.f11880c = c4502u;
        return this;
    }

    Location m15115b() {
        if (!this.f11880c.mo7112j().m14280m()) {
            return null;
        }
        Location t = this.f11880c.mo7112j().m14287t();
        if (t != null) {
            return t;
        }
        t = C4543y.m16305a(this.f11878a).m16315c();
        if (t == null) {
            return C4543y.m16305a(this.f11878a).m16316d();
        }
        return t;
    }

    public void m15114a(C4372h c4372h, C4304a c4304a) {
        int i;
        Location b;
        Object obj;
        dy a;
        bm a2;
        JSONArray g;
        JSONArray a3;
        this.f11879b.put("name", c4372h.m15058a());
        this.f11879b.put(FirebaseAnalytics$Param.VALUE, c4372h.m15061b());
        this.f11879b.put("type", Integer.valueOf(c4372h.m15062c()));
        this.f11879b.put("custom_type", Integer.valueOf(c4372h.m15065d()));
        this.f11879b.put("error_environment", c4372h.m15075i());
        this.f11879b.put("user_info", c4372h.m15077k());
        this.f11879b.put("truncated", Integer.valueOf(c4372h.m15081o()));
        ContentValues contentValues = this.f11879b;
        String str = "connection_type";
        Context context = this.f11878a;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (al.m14592a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    i = 1;
                } else if (activeNetworkInfo.getType() == 0) {
                    i = 0;
                }
                contentValues.put(str, Integer.valueOf(i));
                this.f11879b.put("app_environment", c4304a.f11564a);
                this.f11879b.put("app_environment_revision", Long.valueOf(c4304a.f11565b));
                b = m15115b();
                if (b != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(C1048i.ko, b.getLatitude());
                        jSONObject.put("lon", b.getLongitude());
                        jSONObject.putOpt("timestamp", Long.valueOf(b.getTime()));
                        jSONObject.putOpt("precision", b.hasAccuracy() ? Float.valueOf(b.getAccuracy()) : null);
                        str = "direction";
                        if (b.hasBearing()) {
                            obj = null;
                        } else {
                            obj = Float.valueOf(b.getBearing());
                        }
                        jSONObject.putOpt(str, obj);
                        str = "speed";
                        if (b.hasSpeed()) {
                            obj = null;
                        } else {
                            obj = Float.valueOf(b.getSpeed());
                        }
                        jSONObject.putOpt(str, obj);
                        str = "altitude";
                        if (b.hasAltitude()) {
                            obj = null;
                        } else {
                            obj = Double.valueOf(b.getAltitude());
                        }
                        jSONObject.putOpt(str, obj);
                        jSONObject.putOpt("provider", bi.m14963c(b.getProvider(), null));
                        this.f11879b.put("location_info", jSONObject.toString());
                    } catch (Exception e) {
                    }
                }
                a = ef.m15896a(this.f11878a);
                a.mo7086a(new C43842(this));
                a.mo7085a(new C43831(this));
                a2 = bm.m14994a(this.f11878a);
                g = c4372h.m15073g();
                a3 = a2.m14998a();
                if (a3.length() <= g.length()) {
                    this.f11879b.put("wifi_network_info", a3.toString());
                } else {
                    this.f11879b.put("wifi_network_info", g.toString());
                }
                m15113a(a2);
            }
        }
        i = 2;
        contentValues.put(str, Integer.valueOf(i));
        this.f11879b.put("app_environment", c4304a.f11564a);
        this.f11879b.put("app_environment_revision", Long.valueOf(c4304a.f11565b));
        b = m15115b();
        if (b != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(C1048i.ko, b.getLatitude());
            jSONObject2.put("lon", b.getLongitude());
            jSONObject2.putOpt("timestamp", Long.valueOf(b.getTime()));
            if (b.hasAccuracy()) {
            }
            jSONObject2.putOpt("precision", b.hasAccuracy() ? Float.valueOf(b.getAccuracy()) : null);
            str = "direction";
            if (b.hasBearing()) {
                obj = null;
            } else {
                obj = Float.valueOf(b.getBearing());
            }
            jSONObject2.putOpt(str, obj);
            str = "speed";
            if (b.hasSpeed()) {
                obj = null;
            } else {
                obj = Float.valueOf(b.getSpeed());
            }
            jSONObject2.putOpt(str, obj);
            str = "altitude";
            if (b.hasAltitude()) {
                obj = null;
            } else {
                obj = Double.valueOf(b.getAltitude());
            }
            jSONObject2.putOpt(str, obj);
            jSONObject2.putOpt("provider", bi.m14963c(b.getProvider(), null));
            this.f11879b.put("location_info", jSONObject2.toString());
        }
        a = ef.m15896a(this.f11878a);
        a.mo7086a(new C43842(this));
        a.mo7085a(new C43831(this));
        a2 = bm.m14994a(this.f11878a);
        g = c4372h.m15073g();
        a3 = a2.m14998a();
        if (a3.length() <= g.length()) {
            this.f11879b.put("wifi_network_info", g.toString());
        } else {
            this.f11879b.put("wifi_network_info", a3.toString());
        }
        m15113a(a2);
    }

    void m15113a(bm bmVar) {
        CharSequence b = bmVar.m14999b(this.f11878a);
        if (!TextUtils.isEmpty(b)) {
            int c = bmVar.m15001c(this.f11878a);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ssid", b);
                jSONObject.put("state", c);
                this.f11879b.put("wifi_access_point", jSONObject.toString());
            } catch (Exception e) {
            }
        }
    }

    public void m15112a() {
        ba h = this.f11880c.mo7111h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("dId", h.m14824c());
            jSONObject.putOpt("uId", h.m14819b());
            jSONObject.putOpt("appVer", h.m14867x());
            jSONObject.putOpt("appBuild", h.m14869z());
            jSONObject.putOpt("kitVer", h.m14839h());
            jSONObject.putOpt("clientKitVer", h.m14841i());
            jSONObject.putOpt("kitBuildNumber", h.m14845k());
            jSONObject.putOpt("kitBuildType", h.m14847l());
            jSONObject.putOpt("osVer", h.m14857q());
            jSONObject.putOpt("osApiLev", Integer.valueOf(h.m14859r()));
            jSONObject.putOpt("lang", h.m14866w());
            jSONObject.putOpt("root", h.m14798F());
            jSONObject.putOpt("app_debuggable", h.m14806N());
        } catch (Exception e) {
            jSONObject = new JSONObject();
        }
        this.f11879b.put("report_request_parameters", jSONObject.toString());
    }
}
