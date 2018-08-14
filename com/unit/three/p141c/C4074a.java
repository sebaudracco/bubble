package com.unit.three.p141c;

import com.unit.three.p138b.C4053c;
import com.unit.three.p138b.C4072o;
import com.unit.three.p143e.C4090d;
import com.unit.three.p143e.C4091e;
import com.unit.three.p143e.p144a.C4086b;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public abstract class C4074a implements C4073e {
    abstract Map mo6918a();

    public final void m12555b() {
        C4091e.m12655a(new C4075b(this));
    }

    public final Map m12556c() {
        Map hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("phead", C4078f.m12565a(C4053c.m12503a().m12515b()));
            if (mo6918a() != null) {
                for (Entry entry : mo6918a().entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            Object a = C4086b.m12608a(jSONObject.toString(), "30a161c4b1bde4eea");
            if (a != null) {
                a = C4090d.m12624a(a.getBytes());
            }
            hashMap.put("data", a);
            boolean z = C4072o.f9430a;
            hashMap.put("pkey", "zz2017");
            hashMap.put("sign", C4090d.m12638d("30a161c4b1bde4eea" + jSONObject.toString() + "30a161c4b1bde4eea"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
