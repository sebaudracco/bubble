package com.unit.three.p141c;

import com.unit.three.p138b.C4053c;
import com.unit.three.p138b.C4072o;
import com.unit.three.p143e.C4090d;
import java.util.HashMap;
import java.util.Map;

public final class C4077d extends C4074a {
    private String f9434a;
    private String f9435b;

    public C4077d(String str, String str2) {
        this.f9434a = str;
        this.f9435b = str2;
    }

    final Map mo6918a() {
        Map hashMap = new HashMap();
        hashMap.put("ud", C4090d.m12634c(C4053c.m12503a().m12515b()));
        hashMap.put("isa", Integer.valueOf(0));
        hashMap.put("ex", this.f9434a);
        return hashMap;
    }

    public final String mo6919d() {
        Object[] objArr = new Object[2];
        objArr[0] = this.f9435b;
        boolean z = C4072o.f9431b;
        objArr[1] = "";
        return String.format("http://%s%s/v1/endian/excep/info", objArr);
    }
}
