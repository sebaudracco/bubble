package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.ct.C1594a;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.hp.a;
import com.vungle.publisher.log.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class cn$a<A extends cn, R extends wc> extends C1592a<A, String> {
    @Inject
    qg f2766a;
    @Inject
    zl f2767b;
    @Inject
    C1594a f2768c;

    protected abstract m mo2990a();

    protected /* synthetic */ Object[] mo2960b(int i) {
        return m3553a(i);
    }

    public A mo2989a(R r) {
        cn cnVar = (cn) g_();
        cnVar.u = r.g();
        cnVar.t = String.class;
        cnVar.e = mo2990a();
        cnVar.m = r.c();
        m3545a(cnVar, (wc) r);
        return cnVar;
    }

    protected A m3545a(A a, R r) {
        a.c = r.a();
        a.d = r.b();
        a.j = r.h();
        a.r = r.f();
        return a;
    }

    public int mo2995b(A a, R r) {
        a.m = r.c();
        return a.f_();
    }

    protected int m3554b() {
        Logger.d(Logger.DATABASE_TAG, "deleting " + mo2990a() + " records without pending reports in status " + c.g);
        return this.d.getWritableDatabase().delete("ad", cn.b + " AND " + "status" + " = ?", new String[]{r0.toString()});
    }

    public boolean m3551a(cn cnVar) {
        if (!m3536b("id = ? AND " + cn.b + " AND ((" + "expiration_timestamp_seconds" + " IS NULL OR " + "expiration_timestamp_seconds" + " <= ?) OR " + "status" + " != ?)", new String[]{(String) cnVar.c_(), Long.toString(this.f2767b.m4931a() / 1000), c.e.toString()})) {
            return false;
        }
        Logger.d(Logger.DATABASE_TAG, "deleting ad after successful report");
        if (cnVar.q() > 0) {
            return true;
        }
        return false;
    }

    int m3541a(c cVar, c cVar2) {
        if (cVar2 != c.e && cVar == c.e) {
            return -1;
        }
        if (cVar2 != c.e || cVar == c.e) {
            return 0;
        }
        return 1;
    }

    void m3550a(int i, String str) {
        if (i > 0) {
            Logger.d(Logger.DATABASE_TAG, "ad availability increased by " + i);
            this.f2766a.m4568a(new am(str));
        } else if (i < 0) {
            Logger.d(Logger.DATABASE_TAG, "ad availability decreased by " + i);
            this.f2766a.m4568a(new ag(str));
        }
    }

    protected int m3543a(List<? extends cn> list, c cVar) {
        String[] strArr = new String[1];
        Map hashMap = new HashMap();
        int i = 0;
        for (cn cnVar : list) {
            strArr[0] = (String) cnVar.c_();
            int a = m3541a(cnVar.g(), cVar);
            cnVar.a(cVar);
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", cVar.toString());
            String str = "id IN ( ? )";
            Logger.d(Logger.DATABASE_TAG, "updating status of ads " + strArr[0] + " to " + cVar);
            if (this.d.getWritableDatabase().updateWithOnConflict(mo2961c(), contentValues, "id IN ( ? )", strArr, 3) > 0) {
                i++;
                for (ct ctVar : this.f2768c.m3593a(strArr[0])) {
                    String str2 = ctVar.f2782b;
                    if (hashMap.containsKey(str2)) {
                        hashMap.put(str2, Integer.valueOf(((Integer) hashMap.get(str2)).intValue() + a));
                    } else {
                        hashMap.put(str2, Integer.valueOf(a));
                    }
                }
            }
            i = i;
        }
        for (Entry entry : hashMap.entrySet()) {
            m3550a(((Integer) entry.getValue()).intValue(), (String) entry.getKey());
        }
        return i;
    }

    public boolean m3552a(String str, String str2, c... cVarArr) {
        String[] a = yz.a(cVarArr);
        hp a2 = new a().a("ad").b("id != ? ").b(" AND ad_token_hash = ? ").b(" AND status IN (" + ce.a(a.length) + ")").c(str).c(str2).a(a).a();
        Logger.v(Logger.DATABASE_TAG, "built query: " + a2.a());
        int count = this.d.m3510a(a2).getCount();
        Logger.d(Logger.DATABASE_TAG, "No. of record found = " + count + " for : " + a2.a());
        if (count > 0) {
            return true;
        }
        return false;
    }

    protected A mo2988a(A a, Cursor cursor, boolean z) {
        a.c = ce.e(cursor, "ad_token");
        a.d = ce.e(cursor, "ad_token_hash");
        a.j = ce.e(cursor, "advertising_app_vungle_id");
        a.r = ce.e(cursor, "campaign_id");
        a.u = ce.e(cursor, "id");
        a.g = ce.d(cursor, "insert_timestamp_millis").longValue();
        a.f = (c) ce.a(cursor, "status", c.class);
        a.e = (m) ce.a(cursor, "type", m.class);
        a.h = ce.d(cursor, "update_timestamp_millis").longValue();
        a.i = ce.d(cursor, "failed_timestamp_millis").longValue();
        a.l = ce.a(cursor, "delete_local_content_attempts", 0);
        a.m = ce.d(cursor, "expiration_timestamp_seconds");
        a.o = ce.a(cursor, "prepare_retry_count", 0);
        a.p = this.f2767b.m4931a();
        return a;
    }

    protected String mo2961c() {
        return "ad";
    }

    protected String[] m3553a(int i) {
        return new String[i];
    }
}
