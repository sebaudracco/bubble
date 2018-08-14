package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.appnext.base.p023b.C1048i;
import com.yandex.metrica.C4292c.C4291a.C4279b;
import com.yandex.metrica.C4292c.C4291a.C4280c;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a.C4281a;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a.C4284b;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a.C4284b.C4282a;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a.C4284b.C4283b;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4286b;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4287c;
import com.yandex.metrica.C4292c.C4291a.C4289e;
import com.yandex.metrica.C4292c.C4291a.C4290f;
import com.yandex.metrica.C4293d;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.bm.C4363a;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.utils.C4525g;
import com.yandex.metrica.impl.utils.C4525g.C4524a;
import com.yandex.metrica.impl.utils.C4535n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ap {
    private static Map<bl, Integer> f11662a;
    private static SparseArray<bl> f11663b;

    static class C4327b {
        private static final Map<C4510a, Class<?>> f11645p;
        private static final Map<C4510a, Integer> f11646q;
        protected String f11647a;
        protected String f11648b;
        protected int f11649c;
        protected long f11650d;
        protected String f11651e;
        protected String f11652f;
        protected String f11653g;
        protected Integer f11654h;
        protected Integer f11655i;
        protected String f11656j;
        protected String f11657k;
        protected int f11658l;
        protected int f11659m;
        protected String f11660n;
        protected String f11661o;

        static {
            Map hashMap = new HashMap();
            hashMap.put(C4510a.EVENT_TYPE_REGULAR, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_REFERRER_DEPRECATED, C4332f.class);
            hashMap.put(C4510a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, C4327b.class);
            hashMap.put(C4510a.EVENT_TYPE_ALIVE, C4327b.class);
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, C4332f.class);
            hashMap.put(C4510a.EVENT_TYPE_NATIVE_CRASH, C4334h.class);
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_USER, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_IDENTITY, C4333g.class);
            hashMap.put(C4510a.EVENT_TYPE_STATBOX, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_SET_USER_INFO, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_REPORT_USER_INFO, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_START, C4327b.class);
            hashMap.put(C4510a.EVENT_TYPE_CUSTOM_EVENT, C4330c.class);
            hashMap.put(C4510a.EVENT_TYPE_APP_OPEN, C4328e.class);
            hashMap.put(C4510a.EVENT_TYPE_PERMISSIONS, C4329a.class);
            hashMap.put(C4510a.EVENT_TYPE_APP_FEATURES, C4329a.class);
            f11645p = Collections.unmodifiableMap(hashMap);
            hashMap = new HashMap();
            hashMap.put(C4510a.EVENT_TYPE_INIT, Integer.valueOf(1));
            hashMap.put(C4510a.EVENT_TYPE_REGULAR, Integer.valueOf(4));
            hashMap.put(C4510a.EVENT_TYPE_REFERRER_DEPRECATED, Integer.valueOf(5));
            hashMap.put(C4510a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, Integer.valueOf(2));
            hashMap.put(C4510a.EVENT_TYPE_ALIVE, Integer.valueOf(7));
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, Integer.valueOf(3));
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_UNHANDLED, Integer.valueOf(3));
            hashMap.put(C4510a.EVENT_TYPE_NATIVE_CRASH, Integer.valueOf(3));
            hashMap.put(C4510a.EVENT_TYPE_EXCEPTION_USER, Integer.valueOf(6));
            hashMap.put(C4510a.EVENT_TYPE_IDENTITY, Integer.valueOf(8));
            hashMap.put(C4510a.EVENT_TYPE_STATBOX, Integer.valueOf(11));
            hashMap.put(C4510a.EVENT_TYPE_SET_USER_INFO, Integer.valueOf(12));
            hashMap.put(C4510a.EVENT_TYPE_REPORT_USER_INFO, Integer.valueOf(12));
            hashMap.put(C4510a.EVENT_TYPE_FIRST_ACTIVATION, Integer.valueOf(13));
            hashMap.put(C4510a.EVENT_TYPE_START, Integer.valueOf(2));
            hashMap.put(C4510a.EVENT_TYPE_APP_OPEN, Integer.valueOf(16));
            hashMap.put(C4510a.EVENT_TYPE_APP_UPDATE, Integer.valueOf(17));
            hashMap.put(C4510a.EVENT_TYPE_PERMISSIONS, Integer.valueOf(18));
            hashMap.put(C4510a.EVENT_TYPE_APP_FEATURES, Integer.valueOf(19));
            f11646q = Collections.unmodifiableMap(hashMap);
        }

        static C4327b m14603a(int i, boolean z) {
            Class cls;
            C4327b c4327b;
            C4510a a = C4510a.m16187a(i);
            switch (a) {
                case EVENT_TYPE_INIT:
                case EVENT_TYPE_FIRST_ACTIVATION:
                case EVENT_TYPE_APP_UPDATE:
                    if (!z) {
                        cls = C4331d.class;
                        break;
                    }
                    cls = C4328e.class;
                    break;
                default:
                    cls = (Class) f11645p.get(a);
                    break;
            }
            Integer num = (Integer) f11646q.get(a);
            try {
                c4327b = (C4327b) cls.newInstance();
            } catch (Exception e) {
                c4327b = new C4327b();
            }
            return c4327b.m14606a(num);
        }

        C4327b m14607a(String str) {
            this.f11647a = str;
            return this;
        }

        C4327b m14611b(String str) {
            this.f11648b = str;
            return this;
        }

        C4327b m14604a(int i) {
            this.f11649c = i;
            return this;
        }

        C4327b m14605a(long j) {
            this.f11650d = j;
            return this;
        }

        C4327b m14614c(String str) {
            this.f11651e = str;
            return this;
        }

        C4327b m14616d(String str) {
            this.f11653g = str;
            return this;
        }

        C4327b m14619e(String str) {
            this.f11652f = str;
            return this;
        }

        C4327b m14606a(Integer num) {
            this.f11654h = num;
            return this;
        }

        C4327b m14621f(String str) {
            this.f11661o = str;
            return this;
        }

        C4327b m14610b(Integer num) {
            this.f11655i = num;
            return this;
        }

        C4327b m14622g(String str) {
            this.f11656j = str;
            return this;
        }

        C4327b m14623h(String str) {
            this.f11657k = str;
            return this;
        }

        C4327b m14609b(int i) {
            this.f11658l = i;
            return this;
        }

        C4327b m14613c(int i) {
            this.f11659m = i;
            return this;
        }

        C4327b m14624i(String str) {
            this.f11660n = str;
            return this;
        }

        protected String mo6990a() {
            return "";
        }

        protected byte[] mo6991b() {
            return new byte[0];
        }

        protected Integer mo6992c() {
            return this.f11654h;
        }

        protected String m14617d() {
            return this.f11656j;
        }

        C4285a m14618e() {
            C4285a c4285a = new C4285a();
            C4284b a = ap.m14635a(this.f11659m, this.f11660n, this.f11653g, this.f11652f, this.f11661o);
            C4279b d = ap.m14648d(this.f11651e);
            C4281a e = ap.m14649e(this.f11657k);
            if (a != null) {
                c4285a.f11489h = a;
            }
            if (d != null) {
                c4285a.f11488g = d;
            }
            if (mo6990a() != null) {
                c4285a.f11486e = mo6990a();
            }
            if (mo6991b() != null) {
                c4285a.f11487f = mo6991b();
            }
            if (m14617d() != null) {
                c4285a.f11490i = m14617d();
            }
            if (e != null) {
                c4285a.f11491j = e;
            }
            c4285a.f11485d = mo6992c().intValue();
            c4285a.f11483b = (long) this.f11649c;
            c4285a.f11484c = this.f11650d;
            c4285a.f11492k = this.f11658l;
            c4285a.f11493l = mo6993f();
            return c4285a;
        }

        protected int mo6993f() {
            return 0;
        }
    }

    static class C4328e extends C4327b {
        C4328e() {
        }

        protected String mo6990a() {
            return this.a;
        }

        protected byte[] mo6991b() {
            if (this.b != null) {
                return bi.m14964c(this.b);
            }
            return super.mo6991b();
        }
    }

    static class C4329a extends C4328e {
        C4329a() {
        }

        protected String mo6990a() {
            return "";
        }
    }

    static class C4330c extends C4328e {
        C4330c() {
        }

        protected Integer mo6992c() {
            return this.i;
        }
    }

    static class C4331d extends C4327b {
        C4331d() {
        }

        protected String mo6990a() {
            return this.a;
        }
    }

    static class C4332f extends C4327b {
        C4332f() {
        }

        protected byte[] mo6991b() {
            return bi.m14964c(this.a);
        }
    }

    static class C4333g extends C4327b {
        C4333g() {
        }

        protected byte[] mo6991b() {
            return Base64.decode(this.b, 0);
        }

        public int mo6993f() {
            return 1;
        }
    }

    static class C4334h extends C4327b {
        C4334h() {
        }

        protected byte[] mo6991b() {
            return bi.m14964c(C4514r.m16225c(this.b));
        }
    }

    static {
        Map hashMap = new HashMap();
        hashMap.put(bl.f11933a, Integer.valueOf(0));
        hashMap.put(bl.BACKGROUND, Integer.valueOf(1));
        f11662a = Collections.unmodifiableMap(hashMap);
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(0, bl.f11933a);
        sparseArray.put(1, bl.BACKGROUND);
        f11663b = sparseArray;
    }

    public static C4290f m14639a(ContentValues contentValues) {
        return m14640a(contentValues.getAsLong("start_time"), contentValues.getAsLong("server_time_offset"));
    }

    public static C4289e m14638a(ee eeVar) {
        C4289e c4289e = new C4289e();
        if (eeVar.m15891a() != null) {
            c4289e.f11507b = eeVar.m15891a().intValue();
        }
        if (eeVar.m15892b() != null) {
            c4289e.f11508c = eeVar.m15892b().intValue();
        }
        if (!TextUtils.isEmpty(eeVar.m15894d())) {
            c4289e.f11509d = eeVar.m15894d();
        }
        c4289e.f11510e = eeVar.m15893c();
        if (!TextUtils.isEmpty(eeVar.m15895e())) {
            c4289e.f11511f = eeVar.m15895e();
        }
        return c4289e;
    }

    public static bl m14641a(int i) {
        return (bl) f11663b.get(i);
    }

    public static List<C4287c> m14642a(String str) {
        try {
            List<C4287c> arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(m14637a(jSONArray.getJSONObject(i)));
                } catch (Exception e) {
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return new ArrayList();
        }
    }

    public static C4287c m14637a(JSONObject jSONObject) throws JSONException {
        C4287c c4287c;
        try {
            c4287c = new C4287c();
            c4287c.f11498b = jSONObject.getString("mac");
            c4287c.f11499c = jSONObject.getInt("signal_strength");
            c4287c.f11500d = jSONObject.getString("ssid");
            c4287c.f11501e = jSONObject.optBoolean("is_connected");
            return c4287c;
        } catch (Exception e) {
            c4287c = new C4287c();
            c4287c.f11498b = jSONObject.getString("mac");
            return c4287c;
        }
    }

    static C4283b m14646b(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                C4283b c4283b = new C4283b();
                c4283b.f11475b = jSONObject.optString("ssid");
                switch (jSONObject.optInt("state", -1)) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                        c4283b.f11476c = 1;
                        return c4283b;
                    case 3:
                        c4283b.f11476c = 2;
                        return c4283b;
                    default:
                        return c4283b;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static C4290f m14640a(Long l, Long l2) {
        long longValue = l.longValue();
        C4290f c4290f = new C4290f();
        c4290f.f11512b = longValue;
        c4290f.f11513c = ((GregorianCalendar) GregorianCalendar.getInstance()).getTimeZone().getOffset(longValue * 1000) / 1000;
        if (l2 != null) {
            c4290f.f11514d = l2.longValue();
        }
        return c4290f;
    }

    public static C4286b m14636a(String str, int i, C4290f c4290f) {
        C4286b c4286b = new C4286b();
        c4286b.f11494b = c4290f;
        c4286b.f11495c = str;
        c4286b.f11496d = i;
        return c4286b;
    }

    static int m14634a(bl blVar) {
        return ((Integer) f11662a.get(blVar)).intValue();
    }

    public static C4282a[] m14647c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONArray(str);
                C4282a[] c4282aArr = new C4282a[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        c4282aArr[i] = m14645b(jSONObject);
                    }
                }
                return c4282aArr;
            }
            return null;
        } catch (JSONException e) {
            try {
                return new C4282a[]{m14645b(new JSONObject(str))};
            } catch (Exception e2) {
            }
        }
    }

    static C4282a m14645b(JSONObject jSONObject) {
        C4282a c4282a = new C4282a();
        if (jSONObject.has("signal_strength")) {
            int optInt = jSONObject.optInt("signal_strength");
            if (optInt != -1) {
                c4282a.f11467c = optInt;
            }
        }
        if (jSONObject.has("cell_id")) {
            c4282a.f11466b = jSONObject.optInt("cell_id");
        }
        if (jSONObject.has("lac")) {
            c4282a.f11468d = jSONObject.optInt("lac");
        }
        if (jSONObject.has("country_code")) {
            c4282a.f11469e = jSONObject.optInt("country_code");
        }
        if (jSONObject.has("operator_id")) {
            c4282a.f11470f = jSONObject.optInt("operator_id");
        }
        if (jSONObject.has("operator_name")) {
            c4282a.f11471g = jSONObject.optString("operator_name");
        }
        if (jSONObject.has("is_connected")) {
            c4282a.f11472h = jSONObject.optBoolean("is_connected");
        }
        c4282a.f11473i = jSONObject.optInt("cell_type", 0);
        if (jSONObject.has("pci")) {
            c4282a.f11474j = jSONObject.optInt("pci");
        }
        return c4282a;
    }

    public static C4279b m14648d(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                C4524a c4524a = new C4524a(str);
                C4279b c4279b = new C4279b();
                c4279b.f11452c = c4524a.getDouble("lon");
                c4279b.f11451b = c4524a.getDouble(C1048i.ko);
                if (c4524a.m16268b("altitude")) {
                    c4279b.f11457h = c4524a.getInt("altitude");
                }
                if (c4524a.m16268b("direction")) {
                    c4279b.f11455f = c4524a.getInt("direction");
                }
                if (c4524a.m16268b("precision")) {
                    c4279b.f11454e = c4524a.getInt("precision");
                }
                if (c4524a.m16268b("speed")) {
                    c4279b.f11456g = c4524a.getInt("speed");
                }
                if (c4524a.m16268b("timestamp")) {
                    c4279b.f11453d = c4524a.getLong("timestamp") / 1000;
                }
                if (!c4524a.m16268b("provider")) {
                    return c4279b;
                }
                String a = c4524a.m16267a("provider");
                if ("gps".equals(a)) {
                    c4279b.f11458i = 1;
                    return c4279b;
                } else if (!"network".equals(a)) {
                    return c4279b;
                } else {
                    c4279b.f11458i = 2;
                    return c4279b;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static C4284b m14635a(int i, String str, String str2, String str3, String str4) {
        C4284b c4284b = new C4284b();
        c4284b.f11479d = i;
        if (str != null) {
            c4284b.f11480e = str;
        }
        C4282a[] c = m14647c(str3);
        List a = m14642a(str2);
        if (c != null) {
            c4284b.f11477b = c;
        }
        if (a != null) {
            c4284b.f11478c = (C4287c[]) a.toArray(new C4287c[a.size()]);
        }
        if (!TextUtils.isEmpty(str4)) {
            c4284b.f11481f = m14646b(str4);
        }
        return c4284b;
    }

    public static C4281a m14649e(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                C4293d a = C4535n.m16294a(str);
                C4281a c4281a = new C4281a();
                c4281a.f11462b = a.m14369a();
                if (!TextUtils.isEmpty(a.m14372b())) {
                    c4281a.f11463c = a.m14372b();
                }
                if (bk.m14988a(a.m14374c())) {
                    return c4281a;
                }
                c4281a.f11464d = C4525g.m16271a(a.m14374c());
                return c4281a;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void m14643a() {
    }

    public static C4280c[] m14644a(Context context) {
        Collection b = bm.m14994a(context).m15000b();
        if (bk.m14987a(b)) {
            return null;
        }
        C4280c[] c4280cArr = new C4280c[b.size()];
        for (int i = 0; i < b.size(); i++) {
            C4280c c4280c = new C4280c();
            C4363a c4363a = (C4363a) b.get(i);
            c4280c.f11460b = c4363a.f11816a;
            c4280c.f11461c = c4363a.f11817b;
            c4280cArr[i] = c4280c;
        }
        return c4280cArr;
    }
}
