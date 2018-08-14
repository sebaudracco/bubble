package com.yandex.metrica.impl.ob;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.ob.cl.C4432a;
import com.yandex.metrica.impl.ob.cl.C4433b;
import com.yandex.metrica.impl.utils.C4520d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ag extends af {
    protected ag(C4503t c4503t) {
        super(c4503t);
    }

    public boolean mo7043a(C4372h c4372h) {
        C4503t a = m15143a();
        if (a.m16134C().m15425d() && a.m16133B()) {
            ca I = a.m16140I();
            Collection b = m15149b();
            try {
                Object c = m15150c();
                if (C4520d.m16253a(b, c)) {
                    a.m16174r();
                } else {
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = c.iterator();
                    while (it.hasNext()) {
                        jSONArray.put(((cm) it.next()).m15602a());
                    }
                    a.m16162g(new C4372h(c4372h).m15053a(C4510a.EVENT_TYPE_APP_FEATURES.m16188a()).mo7032c(new JSONObject().put(C1404b.f2119W, jSONArray).toString()));
                    I.m15469c(jSONArray.toString());
                }
            } catch (JSONException e) {
            }
        }
        return false;
    }

    HashSet<cm> m15149b() {
        Object b = m15143a().m16140I().m15466b();
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            HashSet<cm> hashSet = new HashSet();
            JSONArray jSONArray = new JSONArray(b);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(new cm(jSONArray.getJSONObject(i)));
            }
            return hashSet;
        } catch (JSONException e) {
            return null;
        }
    }

    ArrayList<cm> m15150c() {
        try {
            cl c4432a;
            C4503t a = m15143a();
            PackageInfo packageInfo = a.mo7114m().getPackageManager().getPackageInfo(a.mo7114m().getPackageName(), 16384);
            ArrayList<cm> arrayList = new ArrayList();
            if (bk.m14985a(24)) {
                c4432a = new C4432a();
            } else {
                c4432a = new C4433b();
            }
            if (packageInfo.reqFeatures == null) {
                return arrayList;
            }
            for (FeatureInfo b : packageInfo.reqFeatures) {
                arrayList.add(c4432a.m15598b(b));
            }
            return arrayList;
        } catch (Exception e) {
            return null;
        }
    }
}
