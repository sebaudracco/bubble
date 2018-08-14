package com.unit.two.p150e;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import com.unit.two.p146b.C4094a;
import com.unit.two.p147c.C4096a;
import com.unit.two.p147c.C4102g;
import com.unit.two.p147c.C4104i;
import com.unit.two.p151f.C4138e;
import com.unit.two.p151f.C4140g;
import com.unit.two.p151f.C4143j;
import com.unit.two.p151f.C4144k;
import com.unit.two.p151f.C4145l;
import com.unit.two.p151f.p152a.C4133b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class C4128a extends C4094a {
    private static final String f9615A = C4096a.ey;
    private static final String f9616B = C4096a.ez;
    private static final String f9617C = C4096a.eA;
    private static final String f9618D = C4096a.eB;
    private static final String f9619E = C4096a.eC;
    private static final String f9620F = C4096a.eD;
    private static final String f9621G = C4096a.eE;
    private static final String f9622H = C4096a.eF;
    private static final String f9623I = C4096a.eG;
    private static final String f9624J = C4096a.eH;
    private static final String f9625K = C4096a.eI;
    private static final String f9626L = C4096a.eJ;
    private static final String f9627M = C4096a.eK;
    private static Handler f9628e = new Handler();
    private static final String f9629f = C4096a.ed;
    private static final String f9630g = C4096a.ee;
    private static final String f9631h = C4096a.ef;
    private static final String f9632i = C4096a.eg;
    private static final String f9633j = C4096a.eh;
    private static final String f9634k = C4096a.ei;
    private static final String f9635l = C4096a.ej;
    private static final String f9636m = C4096a.ek;
    private static final String f9637n = C4096a.el;
    private static final String f9638o = C4096a.em;
    private static final String f9639p = C4096a.en;
    private static final String f9640q = C4096a.eo;
    private static final String f9641r = C4096a.ep;
    private static final String f9642s = C4096a.eq;
    private static final String f9643t = C4096a.er;
    private static final String f9644u = C4096a.es;
    private static final String f9645v = C4096a.et;
    private static final String f9646w = C4096a.eu;
    private static final String f9647x = C4096a.ev;
    private static final String f9648y = C4096a.ew;
    private static final String f9649z = C4096a.ex;
    private Context f9650a;
    private C4129d f9651b;
    private String f9652c;
    private String f9653d = "";

    static {
        String str = C4096a.ec;
    }

    private C4128a(Context context, C4129d c4129d, String str, String str2) {
        this.f9650a = context.getApplicationContext();
        this.f9651b = c4129d;
        this.f9652c = null;
        this.f9653d = null;
    }

    public static void m12748a(Context context) {
        new C4128a(context, new C4130b(context), null, null).m12667b();
    }

    private String m12749b(Context context) {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(f9629f, 1);
            jSONObject2.put(f9630g, String.valueOf(System.currentTimeMillis()));
            jSONObject2.put(f9631h, C4144k.m12801b(context));
            jSONObject2.put(f9632i, C4144k.m12805c(context));
            jSONObject2.put(f9633j, C4145l.m12822a(context));
            jSONObject2.put(f9634k, C4145l.m12850i(context));
            jSONObject2.put(f9635l, C4145l.m12838e(context));
            jSONObject2.put(f9636m, C4138e.m12770a(context));
            jSONObject2.put(f9637n, C4145l.m12841f(context));
            jSONObject2.put(f9638o, C4145l.m12834d());
            jSONObject2.put(f9639p, VERSION.RELEASE);
            jSONObject2.put(f9640q, String.valueOf(VERSION.SDK_INT));
            jSONObject2.put(f9641r, C4145l.m12835d(context));
            jSONObject2.put(f9642s, String.valueOf(C4145l.m12852j(context)));
            jSONObject2.put(f9643t, C4145l.m12854k(context));
            jSONObject2.put(f9644u, C4102g.f9558b);
            jSONObject2.put(f9645v, 131);
            boolean z = C4102g.f9560d;
            jSONObject2.put(f9646w, C4145l.m12859p(context));
            jSONObject2.put(f9647x, C4145l.m12855l(context));
            jSONObject2.put(f9648y, String.valueOf(C4145l.m12837e()));
            jSONObject2.put(f9649z, C4145l.m12856m(context));
            jSONObject2.put(f9615A, C4145l.m12857n(context));
            jSONObject2.put(f9616B, C4145l.m12840f());
            jSONObject2.put(f9617C, C4145l.m12843g());
            jSONObject2.put(f9618D, C4145l.m12847h());
            jSONObject2.put(f9619E, C4145l.m12849i());
            jSONObject2.put(f9623I, C4145l.m12832c() ? 1 : 0);
            String str = f9624J;
            if (!C4145l.m12858o(context)) {
                i = 0;
            }
            jSONObject2.put(str, i);
            jSONObject2.put(f9625K, 1);
            jSONObject2.put(f9620F, C4145l.m12844g(context));
            jSONObject2.put(f9622H, C4096a.eS);
            jSONObject2.put(f9621G, this.f9652c);
            jSONObject2.put(f9626L, 1);
            jSONObject2.put(f9627M, this.f9653d);
            boolean z2 = C4102g.f9560d;
            jSONObject.put(C4096a.eV, jSONObject2);
        } catch (Exception e) {
        }
        return jSONObject.toString();
    }

    protected final /* synthetic */ Object mo6923a() {
        Context context = this.f9650a;
        Map hashMap = new HashMap();
        String b = m12749b(context);
        String a = C4145l.m12821a();
        String b2 = C4145l.m12828b();
        String a2 = C4145l.m12826a(C4133b.m12759a(b, a).getBytes());
        hashMap.put(C4096a.eL, C4096a.eM);
        hashMap.put(C4096a.eN, a2);
        hashMap.put(C4096a.eO, b2);
        hashMap.put(C4096a.eP, C4145l.m12831c(a + b + a));
        boolean z = C4102g.f9560d;
        return new C4140g(C4104i.f9564b).m12775a(C4143j.f9685b).m12781c(C4145l.m12825a(hashMap, 1)).m12779b(C4145l.m12822a(this.f9650a)).m12778a();
    }
}
