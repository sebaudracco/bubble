package com.facebook.ads.internal.p065h;

import com.appnext.base.p019a.p022c.C1028c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1988b {
    public String f4644a;
    public String f4645b;
    public String f4646c;
    public Date f4647d;
    public boolean f4648e;

    public C1988b(String str, String str2, String str3, Date date) {
        this.f4644a = str;
        this.f4645b = str2;
        this.f4646c = str3;
        this.f4647d = date;
        boolean z = (str2 == null || str3 == null) ? false : true;
        this.f4648e = z;
    }

    public C1988b(JSONObject jSONObject) {
        this(jSONObject.optString("url"), jSONObject.optString(C1028c.gv), jSONObject.optString(FirebaseAnalytics$Param.VALUE), new Date(jSONObject.optLong("expiration") * 1000));
    }

    public static List<C1988b> m6283a(String str) {
        if (str == null) {
            return null;
        }
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        List<C1988b> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject;
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                C1988b c1988b = new C1988b(jSONObject);
                if (c1988b != null) {
                    arrayList.add(c1988b);
                }
            }
        }
        return arrayList;
    }

    public String m6284a() {
        Date date = this.f4647d;
        if (date == null) {
            date = new Date();
            date.setTime(date.getTime() + 3600000);
        }
        DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    public boolean m6285b() {
        return (this.f4645b == null || this.f4646c == null || this.f4644a == null) ? false : true;
    }
}
