package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.ct.C1594a;
import com.vungle.publisher.er.C1617a;
import com.vungle.publisher.hk.C1630a;
import com.vungle.publisher.hp.a;
import com.vungle.publisher.jt.C1644a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: vungle */
public abstract class eb<A extends cn, R extends wc, F extends cn$a<A, R>> {
    @Inject
    ci f2842a;
    @Inject
    C1604c f2843b;
    @Inject
    zl f2844c;
    @Inject
    C1630a f2845d;
    @Inject
    C1594a f2846e;
    cn$a<A, R> f2847f;

    @Singleton
    /* compiled from: vungle */
    public static class C1603b {
        @Inject
        ci f2831a;
        @Inject
        m$a f2832b;
        @Inject
        C1594a f2833c;
        @Inject
        cn$b f2834d;
        @Inject
        C1644a f2835e;
        @Inject
        C1617a f2836f;
        @Inject
        C1604c f2837g;

        @Inject
        C1603b() {
        }

        private eb<?, ?, ?>[] m3738d() {
            return new eb[]{this.f2834d.m3559a(m.c).d(), this.f2834d.m3559a(m.a).d()};
        }

        public int m3739a() {
            return new 1(this).a();
        }

        public int m3743b() {
            return new 2(this).a();
        }

        public int m3740a(List<s> list) {
            Logger.v(Logger.PREPARE_TAG, "Mediator cleanUpInactivePlacements");
            return new 3(this, list).a();
        }

        public dr<?> m3741a(String str) {
            return m3742a(str, c.e, c.d);
        }

        public dr<?> m3744b(String str) {
            return m3742a(str, c.e);
        }

        public dr<?> m3745c(String str) {
            return m3742a(str, c.f);
        }

        public Long m3746c() {
            Throwable th;
            Long l = null;
            Cursor a;
            try {
                String[] strArr = new String[]{c.f.toString(), c.g.toString()};
                a = this.f2831a.m3510a(new a().a("ad").b("status NOT IN (" + ce.a(strArr.length) + ")").b(" ORDER BY expiration_timestamp_seconds ASC").b(" LIMIT ?").a(strArr).c(SchemaSymbols.ATTVAL_TRUE_1).a());
                try {
                    if (a.moveToFirst()) {
                        Long d = ce.d(a, "expiration_timestamp_seconds");
                        if (d == null) {
                            Logger.w(Logger.PREPARE_TAG, "next ad expiration time seconds is null");
                        } else {
                            l = Long.valueOf(d.longValue() * 1000);
                            Logger.d(Logger.PREPARE_TAG, "next ad expiration time millis " + l);
                        }
                    }
                    if (a != null) {
                        a.close();
                    }
                    return l;
                } catch (Throwable th2) {
                    th = th2;
                    if (a != null) {
                        a.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a = null;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        }

        <A extends cn, R extends wc> dr<?> m3742a(String str, c... cVarArr) {
            Throwable e;
            String[] a = yz.a(cVarArr);
            Cursor cursor;
            try {
                dr<?> drVar;
                String[] a2 = yz.a(this.f2832b.m4356a());
                cursor = null;
                for (ct ctVar : this.f2833c.m3598b(str)) {
                    try {
                        hp a3 = this.f2837g.m3747a(ctVar.f2781a, a, a2);
                        cursor = this.f2831a.m3510a(a3);
                        int count = cursor.getCount();
                        if (count == 0) {
                            Logger.d(Logger.PREPARE_TAG, "no record found for " + a3.a());
                        } else if (count != 1) {
                            throw new SQLException("fetched " + count);
                        } else if (cursor.moveToFirst()) {
                            cn$a n_ = this.f2834d.m3559a(this.f2832b.m4354a(cursor, "type")).n_();
                            drVar = (dr) n_.mo2988a((cn) n_.g_(), cursor, true);
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                drVar = null;
                if (cursor == null) {
                    return drVar;
                }
                cursor.close();
                return drVar;
            } catch (Exception e3) {
                e = e3;
                cursor = null;
                try {
                    Logger.e(Logger.PREPARE_TAG, "could not get next ad by status", e);
                    if (cursor == null) {
                        return null;
                    }
                    cursor.close();
                    return null;
                } catch (Throwable th) {
                    e = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw e;
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class C1604c {
        @Inject
        ci f2838a;
        @Inject
        m$a f2839b;
        @Inject
        C1594a f2840c;
        @Inject
        zl f2841d;

        @Inject
        C1604c() {
        }

        hp m3747a(String str, String[] strArr, String[] strArr2) {
            hp a = new a().a("ad").b("id = ? ").b(" AND status IN (" + ce.a(strArr.length) + ")").b(" AND type IN (" + ce.a(strArr2.length) + ")").b(" AND expiration_timestamp_seconds > ?").b(" ORDER BY received_timestamp_millis ASC").b(" LIMIT ?").c(str).a(strArr).a(strArr2).a(new String[]{Long.toString(this.f2841d.m4931a() / 1000), SchemaSymbols.ATTVAL_TRUE_1}).a();
            Logger.v(Logger.PREPARE_TAG, "built query: " + a.a());
            return a;
        }
    }

    public int m3750a(List<A> list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        Logger.d(Logger.PREPARE_TAG, "deleting " + list.size() + " local ad(s)");
        this.f2847f.m3543a((List) list, c.g);
        Logger.d(Logger.PREPARE_TAG, "deleted local files " + m3748c(list).size() + " local ad(s)");
        return this.f2847f.m3554b();
    }

    private List<A> m3748c(List<A> list) {
        List<A> arrayList = new ArrayList();
        for (A a : list) {
            int i = a.l;
            a.l = i + 1;
            if (a.a().m3552a((String) a.c_(), a.m(), c.e, c.d)) {
                Logger.v(Logger.PREPARE_TAG, "Not deleting files of " + a.B() + " for Matching ad_token_hash: " + a.m());
            } else if (a.b() || i >= 3) {
                arrayList.add(a);
            } else {
                Logger.w(Logger.PREPARE_TAG, "unable to delete files for " + a.B() + " attempt " + i);
                a.f_();
            }
        }
        return arrayList;
    }

    protected int m3749a() {
        Logger.d(Logger.PREPARE_TAG, "deleting expired " + this.f2847f.mo2990a() + " " + "ad" + " records without pending reports");
        String str = "type = ? AND expiration_timestamp_seconds <= ?";
        return m3750a(this.f2847f.m3529a("type = ? AND expiration_timestamp_seconds <= ?", new String[]{r0.toString(), String.valueOf(this.f2844c.m4931a() / 1000)}, null));
    }

    protected int m3751b() {
        String str = "type = ?";
        List a = this.f2847f.m3529a("type = ?", new String[]{this.f2847f.mo2990a().toString()}, null);
        Logger.d(Logger.PREPARE_TAG, "deleting " + a.size() + " " + this.f2847f.mo2990a() + " " + "ad" + " records");
        return m3750a(a);
    }

    public int m3752b(List<s> list) {
        Logger.v(Logger.PREPARE_TAG, "cleanUpInactivePlacements");
        List arrayList = new ArrayList(list.size());
        for (s sVar : list) {
            arrayList.add(sVar.a);
        }
        List<hk> b = this.f2845d.m3535b(arrayList);
        Logger.v(Logger.PREPARE_TAG, "inActivePlacements: size " + b.size());
        List<ct> arrayList2 = new ArrayList();
        List arrayList3 = new ArrayList();
        for (hk c_ : b) {
            arrayList2.addAll(this.f2846e.m3598b((String) c_.c_()));
        }
        Logger.v(Logger.PREPARE_TAG, "adPlacementsForInactivePlacement: size " + arrayList2.size());
        for (ct ctVar : arrayList2) {
            arrayList3.add(ctVar.f2781a);
        }
        int a = m3750a(this.f2847f.m3539c(arrayList3));
        int a2 = this.f2845d.mo2957a((List) b);
        Logger.v(Logger.PREPARE_TAG, "num of ads cleaned: " + a);
        Logger.v(Logger.PREPARE_TAG, " for num of placements cleaned: " + a2);
        return a2;
    }
}
