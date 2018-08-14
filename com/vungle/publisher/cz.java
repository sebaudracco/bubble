package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.co.C1593a;
import com.vungle.publisher.ct.C1594a;
import com.vungle.publisher.cy.a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.fg.C1621a;
import com.vungle.publisher.gv.C1628a;
import com.vungle.publisher.ic.C1635a;
import com.vungle.publisher.ki.C1647a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class cz<T extends cz<T, P, E, A>, P extends cy<T, P, E>, E extends da<P>, A extends cn> extends dp<Integer> {
    protected A f2792a;
    protected String f2793b;
    protected String f2794c;
    protected Long f2795d;
    protected boolean f2796e;
    protected String f2797f;
    protected String f2798g;
    protected c f2799h;
    protected Long f2800i;
    protected Integer f2801j;
    protected Long f2802k;
    protected Long f2803l;
    protected List<P> f2804m;
    protected List<co> f2805n;
    Long f2806o;
    protected String f2807p;
    protected int f2808q;
    @Inject
    zl f2809r;

    /* compiled from: vungle */
    public static abstract class C1595a<T extends cz<T, P, E, A>, P extends cy<T, P, E>, E extends da<P>, A extends cn, R extends wc> extends C1592a<T, Integer> {
        @Inject
        C1593a f2784a;
        @Inject
        C1594a f2785b;

        protected abstract cn$a<A, R> mo2991a();

        protected abstract a<T, P, E> mo2992d();

        public abstract m mo2993e();

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m3623a(i);
        }

        protected C1595a() {
        }

        public T mo2997a(A a) {
            cz czVar = (cz) g_();
            czVar.f2792a = a;
            czVar.f2799h = c.a;
            if (a != null) {
                czVar.f2807p = a.j;
            }
            mo2999a(czVar, (cn) a, false);
            Logger.d(Logger.DATABASE_TAG, "creating new " + czVar.m3575B());
            return czVar;
        }

        protected List<T> m3630f() {
            return m3622a(m3529a("status = ? AND ad_id IN (SELECT id FROM ad WHERE type = ?)", new String[]{c.d.toString(), mo2993e().toString()}, "insert_timestamp_millis ASC"), true);
        }

        public T m3624b(A a) {
            T a2 = m3617a((cn) a, false);
            if (a2 != null) {
                return a2;
            }
            a2 = mo2997a((cn) a);
            a2.d_();
            return a2;
        }

        protected T m3616a(A a, String str, String[] strArr, boolean z) {
            if (a == null) {
                throw new IllegalArgumentException("null ad");
            }
            String str2 = (String) a.c_();
            if (str2 == null) {
                throw new IllegalArgumentException("null ad_id");
            }
            String[] strArr2 = new String[(strArr.length + 1)];
            strArr2[0] = str2;
            for (int i = 0; i < strArr.length; i++) {
                strArr2[i + 1] = strArr[i];
            }
            List a2 = m3529a("ad_id = ? AND " + str, strArr2, "insert_timestamp_millis DESC");
            String str3 = "ad_id = ? AND " + str + ", with params: " + zk.a(strArr2);
            int size = a2.size();
            switch (size) {
                case 0:
                    return null;
                case 1:
                    T a3 = mo2999a((cz) a2.get(0), (cn) a, z);
                    Logger.d(Logger.DATABASE_TAG, "fetched " + a3.m3575B());
                    return a3;
                default:
                    Logger.w(Logger.DATABASE_TAG, size + " " + "ad_report" + " records for " + str3);
                    return null;
            }
        }

        public T m3626c(A a) {
            return m3617a((cn) a, false);
        }

        public T m3617a(A a, boolean z) {
            String str = "status = ?";
            return m3616a(a, "status = ?", new String[]{c.a.toString()}, z);
        }

        protected T mo2998a(T t, Cursor cursor, boolean z) {
            t.u = ce.c(cursor, "id");
            t.m3646a(ce.e(cursor, "ad_id"));
            t.f2794c = ce.e(cursor, "incentivized_publisher_app_user_id");
            t.f2796e = ce.a(cursor, "is_incentivized").booleanValue();
            t.f2795d = ce.d(cursor, "insert_timestamp_millis");
            t.f2797f = ce.e(cursor, "placement_reference_id");
            t.f2798g = ce.e(cursor, "ad_token");
            t.f2799h = (c) ce.a(cursor, "status", c.class);
            t.f2800i = ce.d(cursor, "update_timestamp_millis");
            t.f2801j = ce.c(cursor, "video_duration_millis");
            t.f2802k = ce.d(cursor, "view_end_millis");
            t.f2803l = ce.d(cursor, "view_start_millis");
            t.f2807p = ce.e(cursor, "app_id");
            t.f2808q = ce.c(cursor, "ordinal_view_count").intValue();
            return t;
        }

        protected List<T> m3622a(List<T> list, boolean z) {
            for (T a : list) {
                mo2999a((cz) a, (cn) null, z);
            }
            return list;
        }

        protected T mo2999a(T t, A a, boolean z) {
            if (a == null) {
                t.f2792a = (cn) mo2991a().m3526a((Object) t.m3654h(), false);
            } else {
                t.f2792a = a;
                List a2 = this.f2785b.m3593a((String) a.c_());
                if (a2 != null && a2.size() == 1) {
                    t.f2797f = ((ct) a2.get(0)).f2782b;
                }
            }
            if (z) {
                t.f2804m = mo2992d().a(t, z);
                t.f2805n = this.f2784a.m3565a((Integer) t.u);
            }
            return t;
        }

        protected Integer[] m3623a(int i) {
            return new Integer[i];
        }

        protected String mo2961c() {
            return "ad_report";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class C1596b {
        @Inject
        ci f2786a;
        @Inject
        C1621a f2787b;
        @Inject
        C1635a f2788c;
        @Inject
        C1647a f2789d;
        @Inject
        C1628a f2790e;
        Map<m, C1595a<?, ?, ?, ?, ?>> f2791f;

        @Inject
        C1596b() {
        }

        public Map<m, C1595a<?, ?, ?, ?, ?>> m3633a() {
            if (this.f2791f != null) {
                return this.f2791f;
            }
            this.f2791f = new HashMap();
            this.f2791f.put(m.a, this.f2787b);
            this.f2791f.put(m.b, this.f2788c);
            this.f2791f.put(m.c, this.f2789d);
            this.f2791f.put(m.d, this.f2790e);
            return this.f2791f;
        }

        public Collection<C1595a<?, ?, ?, ?, ?>> m3637b() {
            return m3633a().values();
        }

        public <A extends cn> eg m3632a(dr<A> drVar) {
            Object b = m3635b(drVar.m_());
            try {
                return (eg) b;
            } catch (Exception e) {
                throw new IllegalArgumentException("ad report type is not cacheable " + b);
            }
        }

        public <A extends cn> eg m3636b(dr<A> drVar) {
            cn m_ = drVar.m_();
            try {
                return (eg) m3631a(m_);
            } catch (Exception e) {
                throw new IllegalArgumentException("ad type is not cacheable " + m_);
            }
        }

        public <A extends cn> void m3634a(dr<A> drVar, Long l) {
            m3636b((dr) drVar).a_(l);
        }

        public List<cz<?, ?, ?, ?>> m3638c() {
            List<cz<?, ?, ?, ?>> arrayList = new ArrayList();
            for (C1595a f : m3637b()) {
                for (cz add : f.m3630f()) {
                    arrayList.add(add);
                }
            }
            return arrayList;
        }

        public int m3639d() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", c.d.toString());
            return this.f2786a.getWritableDatabase().update("ad_report", contentValues, "status = ?", new String[]{c.c.toString()});
        }

        public <A extends cn> cz<?, ?, ?, A> m3631a(A a) {
            return (cz) new 1(this, a).a(a);
        }

        public <A extends cn> cz<?, ?, ?, A> m3635b(A a) {
            return (cz) new 2(this, a).a(a);
        }
    }

    protected abstract a<T, P, E> mo2994a();

    protected cz() {
    }

    protected String mo2966c() {
        return "ad_report";
    }

    public List<co> m3653e() {
        return this.f2805n;
    }

    protected String m3654h() {
        return this.f2792a == null ? this.f2793b : (String) this.f2792a.c_();
    }

    protected void m3646a(String str) {
        this.f2793b = str;
    }

    public A m3655i() {
        return this.f2792a;
    }

    public boolean m3656m() {
        return this.f2796e;
    }

    public void m3650b(boolean z) {
        this.f2796e = z;
    }

    public String m3657n() {
        return this.f2794c;
    }

    public void m3649b(String str) {
        this.f2794c = str;
    }

    public String m3659r() {
        return this.f2797f;
    }

    public void m3643a(c cVar) {
        Logger.d(Logger.REPORT_TAG, "setting ad_report status " + cVar + " for " + m3575B());
        this.f2799h = cVar;
    }

    public Integer m3660s() {
        return this.f2801j;
    }

    protected void m3644a(Integer num) {
        Logger.d(Logger.REPORT_TAG, "setting video duration millis " + num + " for " + m3575B());
        this.f2801j = num;
    }

    public void m3647b(Integer num) {
        m3644a(num);
        f_();
    }

    public int m3661t() {
        if (this.f2803l == null) {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view start millis was null");
            return -1;
        } else if (this.f2802k != null) {
            return (int) (this.f2802k.longValue() - this.f2803l.longValue());
        } else {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view end millis was null");
            return -1;
        }
    }

    public void m3645a(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad end millis " + l + " for " + m3575B());
        this.f2802k = l;
    }

    public void m3648b(Long l) {
        m3645a(l);
        f_();
    }

    public Long m3662u() {
        return this.f2803l;
    }

    public void m3652c(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad start millis " + l + " for " + m3575B());
        this.f2803l = l;
    }

    public String m3663v() {
        return this.f2807p;
    }

    public void m3642a(int i) {
        this.f2808q = i;
    }

    public int m3664w() {
        return this.f2808q;
    }

    public P m3665x() {
        List z = m3667z();
        P a = mo2994a().a(this);
        a.d_();
        z.add(a);
        return a;
    }

    public P[] m3666y() {
        List z = m3667z();
        return (cy[]) z.toArray(mo2994a().d(z.size()));
    }

    protected List<P> m3667z() {
        List<P> list = this.f2804m;
        if (list != null) {
            return list;
        }
        list = mo2994a().a(this, false);
        this.f2804m = list;
        return list;
    }

    protected ContentValues mo2964a(boolean z) {
        long a = this.f2809r.m4931a();
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_id", m3654h());
            Long valueOf = Long.valueOf(a);
            this.f2795d = valueOf;
            contentValues.put("insert_timestamp_millis", valueOf);
        }
        contentValues.put("incentivized_publisher_app_user_id", this.f2794c);
        contentValues.put("is_incentivized", Boolean.valueOf(this.f2796e));
        contentValues.put("placement_reference_id", this.f2797f);
        contentValues.put("ad_token", this.f2798g);
        contentValues.put("status", zk.a(this.f2799h));
        Long valueOf2 = Long.valueOf(a);
        this.f2800i = valueOf2;
        contentValues.put("update_timestamp_millis", valueOf2);
        contentValues.put("video_duration_millis", this.f2801j);
        contentValues.put("view_end_millis", this.f2802k);
        contentValues.put("view_start_millis", this.f2803l);
        contentValues.put("app_id", this.f2807p);
        contentValues.put("ordinal_view_count", Integer.valueOf(this.f2808q));
        return contentValues;
    }

    public StringBuilder mo2976p() {
        StringBuilder p = super.mo2976p();
        dp.m3572a(p, "ad_id", m3654h());
        dp.m3572a(p, "insert_timestamp_millis", this.f2795d);
        dp.m3572a(p, "incentivized_publisher_app_user_id", this.f2794c);
        dp.m3572a(p, "is_incentivized", Boolean.valueOf(this.f2796e));
        dp.m3572a(p, "placement_reference_id", this.f2797f);
        dp.m3572a(p, "ad_token", this.f2798g);
        dp.m3572a(p, "status", this.f2799h);
        dp.m3572a(p, "update_timestamp_millis", this.f2800i);
        dp.m3572a(p, "video_duration_millis", this.f2801j);
        dp.m3572a(p, "view_end_millis", this.f2802k);
        dp.m3572a(p, "view_start_millis", this.f2803l);
        dp.m3572a(p, "plays", this.f2804m == null ? "not fetched" : Integer.valueOf(this.f2804m.size()));
        dp.m3572a(p, "ordinal_view_count", Integer.valueOf(this.f2808q));
        return p;
    }
}
