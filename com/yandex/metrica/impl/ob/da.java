package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class da {
    private Context f12130a;

    public da(Context context) {
        this.f12130a = context;
    }

    public void m15627a() {
        SharedPreferences a = dl.m15731a(this.f12130a, "_bidoptpreferences");
        if (a.getAll().size() > 0) {
            Object string = a.getString(di.f12201c.m15728a(), null);
            di diVar = new di(this.f12130a);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(diVar.m15712a(null))) {
                diVar.m15723j(string).m15646k();
                a.edit().remove(di.f12201c.m15728a()).apply();
            }
            Map all = a.getAll();
            if (all.size() > 0) {
                for (String str : m15625a(all, di.f12202d.m15728a())) {
                    Object string2 = a.getString(new dk(di.f12202d.m15728a(), str).m15730b(), null);
                    di diVar2 = new di(this.f12130a, str);
                    if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(diVar2.m15713b(null))) {
                        diVar2.m15722i(string2).m15646k();
                    }
                }
            }
            a.edit().clear().apply();
        }
    }

    private static List<String> m15625a(Map<String, ?> map, String str) {
        List<String> arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2.replace(str, ""));
            }
        }
        return arrayList;
    }

    public void m15628b() {
        bq d = bp.m15358a(this.f12130a).m15367d();
        SharedPreferences a = dl.m15731a(this.f12130a, "_startupserviceinfopreferences");
        cd cdVar = new cd(d, null);
        Object string = a.getString(di.f12201c.m15728a(), null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(cdVar.m15505a(null))) {
            cdVar.m15529k(string).m15415h();
            a.edit().remove(di.f12201c.m15728a()).apply();
        }
        cdVar = new cd(d, this.f12130a.getPackageName());
        boolean z = a.getBoolean(di.f12203e.m15728a(), false);
        if (z) {
            cdVar.m15518e(z).m15415h();
        }
        cdVar = new cd(d, null);
        string = a.getString(di.f12204f.m15728a(), null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(cdVar.m15524h(null))) {
            cdVar.m15525i(string).m15415h();
        }
        m15626a(d, this.f12130a.getPackageName());
        for (String a2 : m15625a(a.getAll(), di.f12202d.m15728a())) {
            m15626a(d, a2);
        }
    }

    private void m15626a(bq bqVar, String str) {
        cd cdVar = new cd(bqVar, str);
        di diVar = new di(this.f12130a, str);
        Object b = diVar.m15713b(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15527j(b);
        }
        b = diVar.m15711a();
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15539s(b);
        }
        b = diVar.m15716d(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15538p(b);
        }
        b = diVar.m15719f(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15536n(b);
        }
        b = diVar.m15720g(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15533m(b);
        }
        b = diVar.m15715c(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15537o(b);
        }
        long a = diVar.m15710a(-1);
        if (a != -1) {
            cdVar.m15507b(a);
        }
        b = diVar.m15717e(null);
        if (!TextUtils.isEmpty(b)) {
            cdVar.m15531l(b);
        }
        cdVar.m15415h();
        diVar.m15714b();
    }
}
