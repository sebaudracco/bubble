package com.vungle.publisher;

import com.vungle.publisher.bw.b;
import com.vungle.publisher.log.Logger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

/* compiled from: vungle */
public class tj extends tq {
    @Inject
    uq f3354a;
    @Inject
    bw f3355b;

    @Inject
    tj() {
    }

    public void m4672a(cn cnVar, jf jfVar, List<String> list) {
        Map hashMap = new HashMap();
        hashMap.put("%timestamp%", String.valueOf(System.currentTimeMillis()));
        m4673a(cnVar, jfVar, hashMap, (List) list);
    }

    public void m4674a(cn cnVar, jf jfVar, String... strArr) {
        if (strArr != null) {
            m4673a(cnVar, jfVar, null, Arrays.asList(strArr));
        }
    }

    void m4673a(cn cnVar, jf jfVar, Map<String, String> map, List<String> list) {
        if (list != null) {
            for (String str : list) {
                if (str != null) {
                    this.f3355b.m3474a(tk.m4675a(this, cnVar, jfVar, str, map), b.o);
                }
            }
        }
    }

    /* synthetic */ void m4671a(cn cnVar, jf jfVar, String str, Map map) {
        try {
            this.f3354a.m4717a(cnVar, jfVar, m4670a(str, map)).m4701d();
        } catch (Throwable e) {
            Logger.w(Logger.NETWORK_TAG, "error sending " + jfVar + " event", e);
        }
    }

    String m4670a(String str, Map<String, String> map) {
        if (map == null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            int i = -1;
            while (true) {
                int indexOf = stringBuilder.indexOf(str2, i);
                if (indexOf > 0) {
                    stringBuilder.replace(indexOf, str2.length() + indexOf, entry.getValue() == null ? "" : (String) entry.getValue());
                    i = indexOf;
                }
            }
        }
        return stringBuilder.toString();
    }
}
