package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.coremedia.iso.boxes.UserBox;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.loopj.android.http.AsyncHttpClient;
import com.yandex.metrica.C4292c.C4291a;
import com.yandex.metrica.C4292c.C4291a.C4278a;
import com.yandex.metrica.C4292c.C4291a.C4280c;
import com.yandex.metrica.C4292c.C4291a.C4288d;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4285a;
import com.yandex.metrica.C4292c.C4291a.C4288d.C4286b;
import com.yandex.metrica.C4292c.C4291a.C4289e;
import com.yandex.metrica.C4292c.C4291a.C4290f;
import com.yandex.metrica.impl.C4305a.C4304a;
import com.yandex.metrica.impl.ak.C4323a;
import com.yandex.metrica.impl.ap.C4327b;
import com.yandex.metrica.impl.ob.C4277d;
import com.yandex.metrica.impl.ob.C4392b;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.bn;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.ct;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.eg;
import com.yandex.metrica.impl.ob.eh;
import com.yandex.metrica.impl.utils.C4521e;
import com.yandex.metrica.impl.utils.C4523f;
import com.yandex.metrica.impl.utils.C4529j;
import com.yandex.metrica.impl.utils.C4533m;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import mf.org.apache.xerces.impl.Constants;
import org.json.JSONException;
import org.json.JSONObject;

class at extends C4338l {
    C4291a f11675m;
    ba f11676n;
    bn f11677o;
    C4503t f11678p;
    List<Long> f11679q;
    int f11680r = 0;
    int f11681s = -1;
    private C4341c f11682t;
    private final C4523f f11683u = new C4523f();
    private boolean f11684v;

    static class C4336a {
        C4336a() {
        }

        at mo6994a(C4503t c4503t) {
            return new at(c4503t);
        }
    }

    static final class C4340b {
        final C4288d f11687a;
        final C4304a f11688b;
        final boolean f11689c;

        C4340b(C4288d c4288d, C4304a c4304a, boolean z) {
            this.f11687a = c4288d;
            this.f11688b = c4304a;
            this.f11689c = z;
        }
    }

    static final class C4341c {
        final List<C4288d> f11690a;
        final List<Long> f11691b;
        final JSONObject f11692c;

        C4341c(List<C4288d> list, List<Long> list2, JSONObject jSONObject) {
            this.f11690a = list;
            this.f11691b = list2;
            this.f11692c = jSONObject;
        }
    }

    public at(C4503t c4503t) {
        this.f11678p = c4503t;
        this.f11677o = c4503t.m16165i();
        this.f11676n = c4503t.mo7111h();
        this.f11680r = C4392b.m15176b(1, ap.m14640a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(C4533m.m16293a())));
    }

    void m14680v() {
        Builder buildUpon = Uri.parse(this.f11676n.m14795C()).buildUpon();
        buildUpon.path("report");
        buildUpon.appendQueryParameter("deviceid", bi.m14963c(this.c.m14824c(), this.f11676n.m14824c()));
        buildUpon.appendQueryParameter(UserBox.TYPE, bi.m14963c(this.c.m14819b(), this.f11676n.m14819b()));
        buildUpon.appendQueryParameter("analytics_sdk_version", bi.m14963c(this.c.m14839h(), this.f11676n.m14839h()));
        buildUpon.appendQueryParameter("client_analytics_sdk_version", bi.m14963c(this.c.m14841i(), this.f11676n.m14841i()));
        buildUpon.appendQueryParameter("app_version_name", bi.m14963c(this.c.m14867x(), this.f11676n.m14867x()));
        buildUpon.appendQueryParameter("app_build_number", bi.m14963c(this.c.m14869z(), this.f11676n.m14869z()));
        buildUpon.appendQueryParameter("os_version", bi.m14963c(this.c.m14857q(), this.f11676n.m14857q()));
        if (this.c.m14859r() > 0) {
            buildUpon.appendQueryParameter("os_api_level", String.valueOf(this.c.m14859r()));
        }
        if (!TextUtils.isEmpty(this.c.m14845k())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_number", this.c.m14845k());
        }
        if (!TextUtils.isEmpty(this.c.m14847l())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_type", this.c.m14847l());
        }
        if (!TextUtils.isEmpty(this.c.m14806N())) {
            buildUpon.appendQueryParameter("app_debuggable", this.c.m14806N());
        }
        buildUpon.appendQueryParameter(Constants.LOCALE_PROPERTY, bi.m14963c(this.c.m14866w(), this.f11676n.m14866w()));
        buildUpon.appendQueryParameter("is_rooted", bi.m14963c(this.c.m14798F(), this.f11676n.m14798F()));
        buildUpon.appendQueryParameter("app_framework", bi.m14963c(this.c.m14829d(), this.f11676n.m14829d()));
        buildUpon.appendQueryParameter(this.f11676n.m14843j() >= 200 ? "api_key_128" : "api_key", m14683y());
        buildUpon.appendQueryParameter("app_id", this.f11678p.mo7113l().m16105b());
        buildUpon.appendQueryParameter("app_platform", this.f11676n.m14849m());
        buildUpon.appendQueryParameter("protocol_version", this.f11676n.m14835f());
        buildUpon.appendQueryParameter("model", this.f11676n.m14855p());
        buildUpon.appendQueryParameter("manufacturer", this.f11676n.m14853o());
        buildUpon.appendQueryParameter("screen_width", String.valueOf(this.f11676n.m14861s()));
        buildUpon.appendQueryParameter("screen_height", String.valueOf(this.f11676n.m14863t()));
        buildUpon.appendQueryParameter("screen_dpi", String.valueOf(this.f11676n.m14864u()));
        buildUpon.appendQueryParameter("scalefactor", String.valueOf(this.f11676n.m14865v()));
        buildUpon.appendQueryParameter("device_type", this.f11676n.m14799G());
        buildUpon.appendQueryParameter("android_id", this.f11676n.m14851n());
        Object a = this.f11676n.m14808a(this.f11678p.mo7114m());
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter("adv_id", a);
        }
        a = this.f11676n.m14868y();
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter("clids_set", a);
        }
        m14569a(buildUpon.build().toString());
    }

    C4291a m14669a(C4341c c4341c, C4280c[] c4280cArr) {
        C4291a c4291a = new C4291a();
        m14671a(c4291a);
        c4291a.f11516c = (C4288d[]) c4341c.f11690a.toArray(new C4288d[c4341c.f11690a.size()]);
        c4291a.f11517d = m14667a(c4341c.f11692c);
        c4291a.f11518e = c4280cArr;
        this.f11680r += C4392b.m15189g(8);
        return c4291a;
    }

    void m14671a(final C4291a c4291a) {
        ef.m15896a(this.f11678p.mo7114m()).mo7086a(new eh(this) {
            final /* synthetic */ at f11686b;

            public void mo7010a(eg egVar) {
                int i = 0;
                C4291a c4291a = c4291a;
                Collection c = egVar.m15903c();
                if (!bk.m14987a(c)) {
                    c4291a.f11519f = new String[c.size()];
                    for (int i2 = 0; i2 < c.size(); i2++) {
                        String str = (String) c.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            c4291a.f11519f[i2] = str;
                            at atVar = this.f11686b;
                            atVar.f11680r += C4392b.m15180b(c4291a.f11519f[i2]);
                            atVar = this.f11686b;
                            atVar.f11680r += C4392b.m15189g(9);
                        }
                    }
                }
                C4291a c4291a2 = c4291a;
                Collection a = egVar.m15901a();
                if (!bk.m14987a(a)) {
                    c4291a2.f11520g = new C4289e[a.size()];
                    while (i < a.size()) {
                        c4291a2.f11520g[i] = ap.m14638a((ee) a.get(i));
                        atVar = this.f11686b;
                        atVar.f11680r += C4392b.m15179b(c4291a2.f11520g[i]);
                        atVar = this.f11686b;
                        atVar.f11680r += C4392b.m15189g(10);
                        i++;
                    }
                }
            }
        });
    }

    public boolean mo6996b() {
        if (!this.f11676n.m14805M()) {
            return false;
        }
        this.f11679q = null;
        this.f11684v = this.f11678p.m16139H();
        C4280c[] w = m14681w();
        this.f11682t = mo7006x();
        if (this.f11682t.f11690a.isEmpty()) {
            return false;
        }
        this.f11675m = m14669a(this.f11682t, w);
        m14680v();
        this.f11679q = this.f11682t.f11691b;
        return true;
    }

    public void mo6999e() {
        Closeable gZIPOutputStream;
        Throwable th;
        super.mo6999e();
        this.f11675m.f11515b = ap.m14640a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(C4533m.m16293a()));
        byte[] a = C4277d.m14315a(this.f11675m);
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        Closeable closeable = null;
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(a, 0, a.length);
                gZIPOutputStream.finish();
                m14571a(byteArrayOutputStream.toByteArray());
                m14572b(AsyncHttpClient.ENCODING_GZIP);
                bk.m14980a(byteArrayOutputStream);
                bk.m14980a(gZIPOutputStream);
            } catch (Exception e) {
                try {
                    m14571a(a);
                    m14572b(HTTP.IDENTITY_CODING);
                    bk.m14980a(byteArrayOutputStream);
                    bk.m14980a(gZIPOutputStream);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    closeable = gZIPOutputStream;
                    th = th3;
                    bk.m14980a(byteArrayOutputStream);
                    bk.m14980a(closeable);
                    throw th;
                }
            }
        } catch (Exception e2) {
            gZIPOutputStream = null;
            m14571a(a);
            m14572b(HTTP.IDENTITY_CODING);
            bk.m14980a(byteArrayOutputStream);
            bk.m14980a(gZIPOutputStream);
        } catch (Throwable th4) {
            th = th4;
            bk.m14980a(byteArrayOutputStream);
            bk.m14980a(closeable);
            throw th;
        }
    }

    C4280c[] m14681w() {
        C4280c[] a = ap.m14644a(this.f11678p.mo7114m());
        if (a != null) {
            for (C4277d b : a) {
                this.f11680r = C4392b.m15179b(b) + this.f11680r;
            }
        }
        return a;
    }

    private static C4278a[] m14667a(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length <= 0) {
            return null;
        }
        C4278a[] c4278aArr = new C4278a[length];
        Iterator keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                C4278a c4278a = new C4278a();
                c4278a.f11449b = str;
                c4278a.f11450c = jSONObject.getString(str);
                c4278aArr[i] = c4278a;
            } catch (JSONException e) {
            }
            i++;
        }
        return c4278aArr;
    }

    public boolean mo6997c() {
        int i = 1;
        int i2 = 0;
        this.k = m14583k() == 200;
        int i3;
        if (m14583k() == HttpStatus.SC_BAD_REQUEST) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (!this.k && r0 == 0) {
            i = 0;
        }
        if (i != 0) {
            C4288d[] c4288dArr = this.f11675m.f11516c;
            while (i2 < c4288dArr.length) {
                C4288d c4288d = c4288dArr[i2];
                this.f11677o.m15350a(((Long) this.f11679q.get(i2)).longValue(), ap.m14641a(c4288d.f11504c.f11496d).m15299a(), c4288d.f11505d.length);
                ap.m14643a();
                i2++;
            }
            this.f11677o.m15345a(this.f11678p.m16141a().m15287c());
        }
        return this.k;
    }

    cq mo6998d() {
        return new ct().m15616a(m14580h());
    }

    public void mo7000f() {
        if (this.k) {
            C4529j p = this.f11678p.mo7116p();
            if (p.m16247b()) {
                for (int i = 0; i < this.f11682t.f11690a.size(); i++) {
                    p.m16282a((C4288d) this.f11682t.f11690a.get(i), "Event sent");
                }
            }
        }
        this.f11682t = null;
    }

    public boolean mo7001o() {
        int i;
        int i2;
        int i3 = 1;
        if (m14590r()) {
            i = 0;
        } else {
            i = 1;
        }
        if (m14589q() < 3) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        i &= i2;
        if (HttpStatus.SC_BAD_REQUEST == m14583k()) {
            i3 = 0;
        }
        return i & i3;
    }

    public long mo7002p() {
        return (m14589q() + 1) % 3 != 0 ? C4323a.f11631a : C4323a.f11632b;
    }

    protected C4341c mo7006x() {
        JSONObject jSONObject;
        Cursor cursor;
        Throwable th;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        Cursor z;
        try {
            z = mo7007z();
            JSONObject jSONObject3 = jSONObject2;
            C4304a c4304a = null;
            jSONObject = jSONObject3;
            while (z.moveToNext()) {
                try {
                    ContentValues contentValues = new ContentValues();
                    C4521e.m16255a(z, contentValues);
                    long longValue = contentValues.getAsLong("id").longValue();
                    bl a = bl.m15298a(contentValues.getAsInteger("type"));
                    if (!mo7005a(longValue)) {
                        C4290f a2 = ap.m14639a(contentValues);
                        C4286b a3 = ap.m14636a(this.f11676n.m14866w(), ap.m14634a(a), a2);
                        this.f11680r += C4392b.m15181c(1, Long.MAX_VALUE);
                        this.f11680r += C4392b.m15176b(2, (C4277d) a3);
                        if (this.f11680r >= 250880) {
                            break;
                        }
                        C4340b a4 = mo7004a(longValue, a3);
                        if (a4 == null) {
                            continue;
                        } else {
                            if (c4304a != null) {
                                if (!c4304a.equals(a4.f11688b)) {
                                    break;
                                }
                            } else {
                                c4304a = a4.f11688b;
                            }
                            arrayList2.add(Long.valueOf(longValue));
                            arrayList.add(a4.f11687a);
                            try {
                                jSONObject = new JSONObject(a4.f11688b.f11564a);
                            } catch (JSONException e) {
                            }
                            if (a4.f11689c) {
                                break;
                            }
                        }
                    }
                } catch (Exception e2) {
                    cursor = z;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bk.m14978a(z);
        } catch (Exception e3) {
            jSONObject = jSONObject2;
            cursor = null;
            bk.m14978a(cursor);
            return new C4341c(arrayList, arrayList2, jSONObject);
        } catch (Throwable th3) {
            z = null;
            th = th3;
            bk.m14978a(z);
            throw th;
        }
        return new C4341c(arrayList, arrayList2, jSONObject);
    }

    private static int m14666a(C4304a c4304a) {
        try {
            C4278a[] a = m14667a(new JSONObject(c4304a.f11564a));
            if (a == null) {
                return 0;
            }
            int i = 0;
            int i2 = 0;
            while (i < a.length) {
                int b = C4392b.m15176b(7, a[i]) + i2;
                i++;
                i2 = b;
            }
            return i2;
        } catch (JSONException e) {
            return 0;
        }
    }

    protected C4340b mo7004a(long j, C4286b c4286b) {
        Cursor a;
        boolean z;
        C4304a c4304a;
        Throwable th;
        C4288d c4288d = new C4288d();
        c4288d.f11503b = j;
        c4288d.f11504c = c4286b;
        Cursor cursor = null;
        C4304a c4304a2 = null;
        boolean z2 = false;
        try {
            a = mo7003a(j, ap.m14641a(c4286b.f11496d));
            try {
                C4304a c4304a3;
                List arrayList = new ArrayList();
                while (a.moveToNext()) {
                    try {
                        C4277d e;
                        ContentValues contentValues = new ContentValues();
                        C4521e.m16255a(a, contentValues);
                        C4327b f = C4327b.m14603a(contentValues.getAsInteger("type").intValue(), this.f11684v).m14610b(contentValues.getAsInteger("custom_type")).m14607a(contentValues.getAsString("name")).m14611b(contentValues.getAsString(FirebaseAnalytics$Param.VALUE)).m14605a(contentValues.getAsLong("time").longValue()).m14604a(contentValues.getAsInteger("number").intValue()).m14619e(contentValues.getAsString("cell_info")).m14614c(contentValues.getAsString("location_info")).m14616d(contentValues.getAsString("wifi_network_info")).m14622g(contentValues.getAsString("error_environment")).m14623h(contentValues.getAsString("user_info")).m14609b(contentValues.getAsInteger("truncated").intValue()).m14613c(contentValues.getAsInteger("connection_type").intValue()).m14624i(contentValues.getAsString("cellular_connection_type")).m14621f(contentValues.getAsString("wifi_access_point"));
                        if (f.mo6992c() != null) {
                            e = f.m14618e();
                        } else {
                            e = null;
                        }
                        if (e != null) {
                            c4304a3 = new C4304a(contentValues.getAsString("app_environment"), contentValues.getAsLong("app_environment_revision").longValue());
                            if (c4304a2 != null) {
                                if (!c4304a2.equals(c4304a3)) {
                                    z2 = true;
                                    c4304a3 = c4304a2;
                                    break;
                                }
                            }
                            try {
                                if (this.f11681s < 0) {
                                    this.f11681s = m14666a(c4304a3);
                                    this.f11680r += this.f11681s;
                                    c4304a2 = c4304a3;
                                } else {
                                    c4304a2 = c4304a3;
                                }
                            } catch (Exception e2) {
                                z = z2;
                                c4304a = c4304a3;
                                cursor = a;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            Object a2 = this.f11683u.m16264a(e.f11487f, 245760);
                            if (!e.f11487f.equals(a2)) {
                                e.f11487f = a2;
                                e.f11492k = (e.f11487f.length - a2.length) + e.f11492k;
                            }
                            this.f11680r += C4392b.m15176b(3, e);
                            if (this.f11680r >= 250880) {
                                break;
                            }
                            arrayList.add(e);
                        }
                    } catch (Exception e3) {
                        cursor = a;
                        C4304a c4304a4 = c4304a2;
                        z = false;
                        c4304a = c4304a4;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                }
                c4304a3 = c4304a2;
                if (arrayList.size() > 0) {
                    c4288d.f11505d = (C4285a[]) arrayList.toArray(new C4285a[arrayList.size()]);
                    bk.m14978a(a);
                    z = z2;
                    c4304a = c4304a3;
                    return new C4340b(c4288d, c4304a, z);
                }
                bk.m14978a(a);
                return null;
            } catch (Exception e4) {
                cursor = a;
                z = false;
                c4304a = null;
                bk.m14978a(cursor);
                return new C4340b(c4288d, c4304a, z);
            } catch (Throwable th222) {
                th = th222;
            }
        } catch (Exception e5) {
            c4304a = null;
            z = false;
            bk.m14978a(cursor);
            return new C4340b(c4288d, c4304a, z);
        } catch (Throwable th3) {
            th = th3;
            a = null;
            bk.m14978a(a);
            throw th;
        }
    }

    protected String m14683y() {
        return this.f11676n.m14807a();
    }

    protected Cursor mo7007z() {
        return this.f11677o.m15348a(this.b);
    }

    protected Cursor mo7003a(long j, bl blVar) {
        return this.f11677o.m15355b(j, blVar);
    }

    protected boolean mo7005a(long j) {
        return -2 == j;
    }

    public static C4336a m14665A() {
        return new C4336a();
    }
}
