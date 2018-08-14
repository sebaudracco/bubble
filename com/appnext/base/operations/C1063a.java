package com.appnext.base.operations;

import android.os.Bundle;
import android.util.Pair;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p019a.p022c.C1026d;
import com.appnext.base.p023b.C1040b;
import com.appnext.base.p023b.C1042c.C1041a;
import com.appnext.base.p023b.C1046g;
import com.appnext.base.p023b.C1048i;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class C1063a {
    private final String TAG = C1063a.class.getSimpleName();
    protected C1021c gP;

    private static class C1062a implements Runnable {
        private Map<String, String> gQ;
        private boolean gR;
        private long gS;
        private int gT;
        private String gk;

        public C1062a(String str, Map<String, String> map, boolean z, long j, int i) {
            this.gk = str;
            this.gQ = new HashMap(map);
            this.gR = z;
            this.gS = j;
            this.gT = i;
        }

        public void run() {
            try {
                if (!m2192a(this.gk, Long.valueOf(this.gS)) && !C1040b.m2127a(this.gk, this.gQ) && this.gT != 3 && this.gR) {
                    C1046g.cw().m2142b(new C1062a(this.gk, this.gQ, this.gR, this.gS, this.gT + 1), (long) m2193d(this.gT));
                }
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }

        private boolean m2192a(String str, Long l) {
            try {
                return !l.equals(Long.valueOf(C1048i.cy().getLong(new StringBuilder().append(str).append(C1048i.kf).toString(), 0)));
            } catch (Throwable th) {
                C1061b.m2191a(th);
                return true;
            }
        }

        private int m2193d(int i) {
            switch (i) {
                case 2:
                    return 300000;
                default:
                    return 0;
            }
        }
    }

    public abstract void bB();

    public abstract void bC();

    protected abstract List<C1020b> getData();

    public C1063a(C1021c c1021c, Bundle bundle) {
        this.gP = c1021c;
    }

    protected long m2197b(List<C1020b> list) {
        long j = -1;
        try {
            JSONArray a = C1040b.m2125a((List) list, true);
            if (a != null && a.length() > 0) {
                j = bA().mo2022a(a);
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return j;
    }

    protected void bs() {
        try {
            C1048i.cy().putLong(this.gP.getKey() + C1048i.ke, System.currentTimeMillis());
            List data = getData();
            if (!(data == null || data.isEmpty())) {
                m2197b(data);
            }
            C1040b.au(this.gP.getKey());
            if (bz()) {
                Map bu = bu();
                if (!(bu == null || bu.isEmpty())) {
                    m2194a(bu);
                }
            }
            by();
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
    }

    private void m2194a(Map<String, String> map) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        int nextInt = new Random().nextInt(20000) + 10000;
        String key = this.gP.getKey();
        C1048i.cy().putLong(key + C1048i.kf, valueOf.longValue());
        C1046g.cw().m2142b(new C1062a(key, map, bv(), valueOf.longValue(), 1), (long) nextInt);
    }

    protected List<C1020b> bt() {
        return bA().ae(this.gP.getKey());
    }

    protected Map<String, String> bu() {
        List bt = bt();
        if (bt == null || bt.isEmpty()) {
            return null;
        }
        List<C1020b> c = mo2031c(bt);
        if (c == null || c.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (C1020b c1020b : c) {
            Pair pair = new Pair(c1020b.aX(), c1020b.getType());
            if (hashMap.containsKey(pair)) {
                ((JSONArray) hashMap.get(pair)).put(m2195d(c1020b));
            } else {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(m2195d(c1020b));
                hashMap.put(pair, jSONArray);
            }
        }
        HashMap c2 = mo2036c(hashMap);
        hashMap = new HashMap();
        List arrayList = new ArrayList();
        for (Entry entry : c2.entrySet()) {
            String str = (String) ((Pair) entry.getKey()).second;
            hashMap.put(str, ((JSONArray) entry.getValue()).toString());
            arrayList.add(str);
        }
        C1040b.at(this.gP.getKey());
        m2196d(arrayList);
        C1040b.as(this.gP.getKey());
        return hashMap;
    }

    protected HashMap<Pair<String, String>, JSONArray> mo2036c(HashMap<Pair<String, String>, JSONArray> hashMap) {
        return hashMap;
    }

    protected boolean bv() {
        return true;
    }

    protected C1021c bw() {
        return this.gP;
    }

    protected List<C1020b> mo2031c(List<C1020b> list) {
        return list;
    }

    protected boolean bx() {
        return true;
    }

    private JSONObject m2195d(C1020b c1020b) {
        return C1040b.m2126a(c1020b.aY(), c1020b.aZ(), C1041a.valueOf(c1020b.getDataType()));
    }

    private void by() {
        if (bx()) {
            C1066d.bG().m2200a(this);
        }
    }

    protected boolean bz() {
        return C1040b.m2130c(this.gP);
    }

    protected C1026d bA() {
        return C1017a.aM().aP();
    }

    private void m2196d(List<String> list) {
        if (list != null && !list.isEmpty()) {
            C1026d bA = bA();
            for (String ac : list) {
                bA.ac(ac);
            }
        }
    }

    public boolean hasPermission() {
        return true;
    }

    protected C1041a bD() {
        return C1041a.String;
    }

    protected Date getDate() {
        return new Date();
    }
}
