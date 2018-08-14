package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.ei.a;
import com.vungle.publisher.ei.b;
import com.vungle.publisher.log.Logger;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
protected abstract class jm$a<A extends cn, W extends jm<A>, R extends wc> extends C1592a<W, Integer> {
    @Inject
    m$a f2816e;

    protected jm$a() {
    }

    protected /* synthetic */ Object[] mo2960b(int i) {
        return m3702c(i);
    }

    protected W m3695a(A a, R r) {
        if (r == null) {
            return null;
        }
        jm jmVar = (jm) g_();
        jmVar.s = a;
        jmVar.o = r.g();
        jmVar.p = r.d();
        jmVar.q = a.a;
        return jmVar;
    }

    protected List<W> m3699a(b bVar, String str, boolean z) {
        String str2 = "ad_id = ? AND type = ?";
        return mo2959a("ad_id = ? AND type = ?", new String[]{str, bVar.toString()});
    }

    protected W m3698a(String str, b bVar, boolean z) throws SQLException {
        jm jmVar = (jm) g_();
        jmVar.o = str;
        jmVar.r = bVar;
        return m3697a(jmVar, z);
    }

    protected W m3697a(W w, boolean z) throws SQLException {
        Throwable th;
        W w2 = null;
        Integer num = (Integer) w.u;
        b bVar = w.r;
        Cursor query;
        try {
            String str;
            String str2 = w.o;
            String str3;
            if (num != null) {
                str3 = "id: " + num;
                Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                query = this.d.getReadableDatabase().query("viewable", null, "id = ?", new String[]{String.valueOf(num)}, null, null, null);
                str = str3;
            } else if (str2 == null) {
                Logger.w(Logger.DATABASE_TAG, "unable to fetch " + bVar + ": no " + "id" + " or " + "ad_id");
                str = null;
                query = null;
            } else {
                str3 = "ad_id " + str2;
                Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                query = this.d.getReadableDatabase().query("viewable", null, "ad_id = ? AND type = ?", new String[]{str2, String.valueOf(bVar)}, null, null, null);
                str = str3;
            }
            if (query != null) {
                try {
                    int count = query.getCount();
                    switch (count) {
                        case 0:
                            Logger.v(Logger.DATABASE_TAG, "no " + bVar + " found for " + str);
                            break;
                        case 1:
                            Logger.d(Logger.DATABASE_TAG, "have " + bVar + " for " + str);
                            query.moveToFirst();
                            w2 = mo2978a((jm) w, query, z);
                            break;
                        default:
                            throw new SQLException(count + " " + bVar + " records for " + str);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            Logger.v(Logger.DATABASE_TAG, "fetched " + w2);
            return w2;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    protected W mo2978a(W w, Cursor cursor, boolean z) {
        w.u = ce.c(cursor, "id");
        w.o = ce.e(cursor, "ad_id");
        w.q = (a) ce.a(cursor, "status", a.class);
        w.r = (b) ce.a(cursor, "type", b.class);
        w.p = this.f2816e.m4355a(ce.e(cursor, "ad_type"));
        return w;
    }

    protected String mo2961c() {
        return "viewable";
    }

    protected Integer[] m3702c(int i) {
        return new Integer[i];
    }
}
