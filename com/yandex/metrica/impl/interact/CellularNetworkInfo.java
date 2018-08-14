package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.impl.ob.dz;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import java.util.HashMap;
import java.util.Map.Entry;

public class CellularNetworkInfo {
    private String f11872a = "";

    class C43801 implements eh {
        final /* synthetic */ CellularNetworkInfo f11871a;

        C43801(CellularNetworkInfo cellularNetworkInfo) {
            this.f11871a = cellularNetworkInfo;
        }

        public void mo7010a(eg egVar) {
            Object valueOf;
            Object obj = null;
            dz b = egVar.m15902b();
            String g = b.m15850g();
            String f = b.m15849f();
            Integer c = b.m15846c();
            Integer b2 = b.m15845b();
            Integer e = b.m15848e();
            Integer d = b.m15847d();
            Integer a = b.m15843a();
            HashMap hashMap = new HashMap();
            hashMap.put("network_type", g);
            hashMap.put("operator_name", f);
            hashMap.put("country_code", b2 != null ? String.valueOf(b2) : null);
            g = "operator_id";
            if (c != null) {
                valueOf = String.valueOf(c);
            } else {
                valueOf = null;
            }
            hashMap.put(g, valueOf);
            g = "cell_id";
            if (e != null) {
                valueOf = String.valueOf(e);
            } else {
                valueOf = null;
            }
            hashMap.put(g, valueOf);
            g = "lac";
            if (d != null) {
                valueOf = String.valueOf(d);
            } else {
                valueOf = null;
            }
            hashMap.put(g, valueOf);
            String str = "signal_strength";
            if (a != null) {
                obj = String.valueOf(a);
            }
            hashMap.put(str, obj);
            StringBuilder stringBuilder = new StringBuilder();
            g = "";
            for (Entry entry : hashMap.entrySet()) {
                String str2 = (String) entry.getValue();
                if (TextUtils.isEmpty(str2)) {
                    str = g;
                } else {
                    stringBuilder.append(g);
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append(str2);
                    str = "&";
                }
                g = str;
            }
            this.f11871a.f11872a = stringBuilder.toString();
        }
    }

    public CellularNetworkInfo(Context context) {
        ef.m15896a(context).mo7086a(new C43801(this));
    }

    public String getCelluralInfo() {
        return this.f11872a;
    }
}
