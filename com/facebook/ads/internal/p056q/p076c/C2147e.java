package com.facebook.ads.internal.p056q.p076c;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p064g.C1985a;
import com.facebook.ads.internal.p071p.p072a.C2048a;
import com.facebook.ads.internal.p071p.p072a.C2060n;
import com.facebook.ads.internal.p071p.p072a.C2062p;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class C2147e extends AsyncTask<String, Void, C2148f> {
    private static final String f5129a = C2147e.class.getSimpleName();
    private static final Set<String> f5130b = new HashSet();
    private Context f5131c;
    private Map<String, String> f5132d;
    private Map<String, String> f5133e;
    private C2060n f5134f;
    private C2146a f5135g;

    public interface C2146a {
        void mo3774a();

        void mo3775a(C2148f c2148f);
    }

    static {
        f5130b.add("#");
        f5130b.add("null");
    }

    public C2147e(Context context) {
        this(context, null, null);
    }

    public C2147e(Context context, Map<String, String> map) {
        this(context, map, null);
    }

    public C2147e(Context context, Map<String, String> map, Map<String, String> map2) {
        Map map3 = null;
        this.f5131c = context;
        this.f5132d = map != null ? new HashMap(map) : null;
        if (map2 != null) {
            map3 = new HashMap(map2);
        }
        this.f5133e = map3;
    }

    private String m6872a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        return str + (str.contains("?") ? "&" : "?") + str2 + "=" + URLEncoder.encode(str3);
    }

    private boolean m6873a(String str) {
        C2048a a = C2145d.m6860a(this.f5131c);
        try {
            if (this.f5133e == null || this.f5133e.size() == 0) {
                this.f5134f = a.m6577a(str, null);
            } else {
                C2062p c2062p = new C2062p();
                c2062p.m6623a(this.f5133e);
                this.f5134f = a.m6587b(str, c2062p);
            }
            return this.f5134f != null && this.f5134f.m6616a() == 200;
        } catch (Throwable e) {
            Log.e(f5129a, "Error opening url: " + str, e);
            return false;
        }
    }

    private String m6874b(String str) {
        try {
            str = m6872a(str, "analog", C2119j.m6798a(C1985a.m6258a()));
        } catch (Exception e) {
        }
        return str;
    }

    protected C2148f m6875a(String... strArr) {
        Object obj = strArr[0];
        if (TextUtils.isEmpty(obj) || f5130b.contains(obj)) {
            return null;
        }
        String b = m6874b(obj);
        if (!(this.f5132d == null || this.f5132d.isEmpty())) {
            String str = b;
            for (Entry entry : this.f5132d.entrySet()) {
                str = m6872a(str, (String) entry.getKey(), (String) entry.getValue());
            }
            b = str;
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                return null;
            }
            if (m6873a(b)) {
                return new C2148f(this.f5134f);
            }
            i = i2;
        }
    }

    public void m6876a(C2146a c2146a) {
        this.f5135g = c2146a;
    }

    protected void m6877a(C2148f c2148f) {
        if (this.f5135g != null) {
            this.f5135g.mo3775a(c2148f);
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m6875a((String[]) objArr);
    }

    protected void onCancelled() {
        if (this.f5135g != null) {
            this.f5135g.mo3774a();
        }
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m6877a((C2148f) obj);
    }
}
