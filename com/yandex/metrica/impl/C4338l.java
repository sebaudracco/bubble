package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.utils.C4525g.C4524a;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

abstract class C4338l extends ak {
    static final ContentValues f11672a = new ContentValues();
    final Map<String, String> f11673b = new LinkedHashMap();
    final ba f11674c = new ba();

    C4338l() {
    }

    C4338l m14662a(ContentValues contentValues) {
        this.f11673b.clear();
        for (Entry entry : contentValues.valueSet()) {
            this.f11673b.put(entry.getKey(), entry.getValue().toString());
        }
        m14664b(contentValues);
        return this;
    }

    void m14664b(ContentValues contentValues) {
        String asString = contentValues.getAsString("report_request_parameters");
        if (!bi.m14957a(asString)) {
            try {
                C4524a c4524a = new C4524a(asString);
                this.f11674c.m14822b(c4524a.m16267a("dId"));
                this.f11674c.m14815a(c4524a.m16267a("uId"));
                this.f11674c.m14834e(c4524a.m16267a("kitVer"));
                this.f11674c.m14836f(c4524a.m16267a("clientKitVer"));
                this.f11674c.m14838g(c4524a.m16267a("kitBuildNumber"));
                this.f11674c.m14840h(c4524a.m16267a("kitBuildType"));
                this.f11674c.m14846k(c4524a.m16267a("appVer"));
                this.f11674c.m14862s(c4524a.optString("app_debuggable", SchemaSymbols.ATTVAL_FALSE_0));
                this.f11674c.m14850m(c4524a.m16267a("appBuild"));
                this.f11674c.m14842i(c4524a.m16267a("osVer"));
                this.f11674c.m14810a(c4524a.optInt("osApiLev", -1));
                this.f11674c.m14844j(c4524a.m16267a("lang"));
                this.f11674c.m14858q(c4524a.m16267a("root"));
            } catch (Exception e) {
            }
        }
    }

    public String mo6995a() {
        return super.mo6995a() + " [" + this.f11673b.toString() + "]";
    }
}
