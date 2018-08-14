package com.unit.three.p141c;

import com.unit.three.p138b.C4072o;
import java.util.Map;

public final class C4076c extends C4074a {
    private String f9433a;

    public C4076c(String str) {
        this.f9433a = str;
    }

    final Map mo6918a() {
        return null;
    }

    public final String mo6919d() {
        Object[] objArr = new Object[2];
        objArr[0] = this.f9433a;
        boolean z = C4072o.f9431b;
        objArr[1] = "";
        return String.format("http://%s%s/v1/b/u/info", objArr);
    }
}
