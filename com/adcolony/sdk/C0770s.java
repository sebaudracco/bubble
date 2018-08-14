package com.adcolony.sdk;

import com.adcolony.sdk.ab.C0596a;
import java.util.Date;
import org.json.JSONObject;

class C0770s extends ab {
    static final C0801x f1412a = new C0801x("adcolony_fatal_reports", "3.3.2", "Production");
    static final String f1413b = "sourceFile";
    static final String f1414c = "lineNumber";
    static final String f1415d = "methodName";
    static final String f1416e = "stackTrace";
    static final String f1417f = "isAdActive";
    static final String f1418g = "activeAdId";
    static final String f1419h = "active_creative_ad_id";
    static final String f1420i = "listOfCachedAds";
    static final String f1421j = "listOfCreativeAdIds";
    static final String f1422k = "adCacheSize";
    private JSONObject f1423p;

    private class C0769a extends C0596a {
        final /* synthetic */ C0770s f1411a;

        C0769a(C0770s c0770s) {
            this.f1411a = c0770s;
            this.b = new C0770s();
        }

        C0769a m1381a(JSONObject jSONObject) {
            ((C0770s) this.b).f1423p = jSONObject;
            return this;
        }

        C0596a mo1866a(Date date) {
            C0802y.m1462a(((C0770s) this.b).f1423p, "timestamp", ab.f488l.format(date));
            return super.mo1866a(date);
        }
    }

    C0770s() {
    }

    C0770s m1384a(JSONObject jSONObject) {
        C0769a c0769a = new C0769a(this);
        c0769a.m1381a(jSONObject);
        c0769a.m629a(C0802y.m1468b(jSONObject, "message"));
        try {
            c0769a.mo1866a(new Date(Long.parseLong(C0802y.m1468b(jSONObject, "timestamp"))));
        } catch (NumberFormatException e) {
        }
        c0769a.m628a(f1412a);
        c0769a.m627a(-1);
        return (C0770s) c0769a.m631a();
    }

    JSONObject m1385a() {
        return this.f1423p;
    }
}
