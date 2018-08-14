package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.da.C4170a;
import com.vungle.publisher.dp.a;
import com.vungle.publisher.log.Logger;
import java.util.List;

/* compiled from: vungle */
public abstract class cy<T extends cz<T, P, E, ?>, P extends cy<T, P, E>, E extends da<P>> extends dp<Integer> {
    T f9903a;
    Integer f9904b;
    Long f9905c;
    List<E> f9906d;

    /* compiled from: vungle */
    protected static abstract class C4169a<T extends cz<T, P, E, ?>, P extends cy<T, P, E>, E extends da<P>> extends a<P, Integer> {
        protected abstract C4170a<P, E> m12989a();

        protected /* synthetic */ Object[] m12993b(int i) {
            return m12992a(i);
        }

        protected C4169a() {
        }

        protected P m12988a(T t) {
            cy cyVar = (cy) g_();
            cyVar.f9903a = t;
            return cyVar;
        }

        protected List<P> m12991a(T t, boolean z) {
            if (t == null) {
                throw new IllegalArgumentException("null ad_report");
            }
            if (((Integer) t.c_()) == null) {
                throw new IllegalArgumentException("null report_id");
            }
            List<P> a = a("report_id = ?", new String[]{((Integer) t.c_()).toString()}, "start_millis ASC", null);
            for (P p : a) {
                p.f9903a = t;
            }
            return a;
        }

        protected P m12987a(P p, Cursor cursor, boolean z) {
            p.u = ce.m12921c(cursor, "id");
            p.f9904b = ce.m12921c(cursor, "watched_millis");
            p.f9905c = ce.m12923d(cursor, "start_millis");
            if (z) {
                p.f9906d = m12989a().m13025a((cy) p);
            }
            return p;
        }

        protected String m12994c() {
            return "ad_play";
        }

        protected Integer[] m12992a(int i) {
            return new Integer[i];
        }
    }

    protected abstract C4170a<P, E> m12996a();

    protected cy() {
    }

    public void m12999a(Long l) {
        this.f9905c = l;
    }

    public void m12998a(Integer num) {
        String str = Logger.AD_TAG;
        if (this.f9904b == null || (num != null && num.intValue() > this.f9904b.intValue())) {
            Logger.d(Logger.AD_TAG, "setting watched millis " + num);
            this.f9904b = num;
            return;
        }
        Logger.w(Logger.AD_TAG, "ignoring decreased watched millis " + num);
    }

    public void m12997a(jf jfVar, Object obj) {
        String str = Logger.REPORT_TAG;
        List h = m13002h();
        if (h.size() >= 1000) {
            Logger.w(Logger.REPORT_TAG, "ignoring report event " + jfVar + " because the event buffer is full!");
            return;
        }
        Logger.d(Logger.REPORT_TAG, "adding report event " + jfVar + (obj == null ? "" : ", value " + obj + " for " + B()));
        da a = m12996a().m13021a(this, jfVar, obj);
        a.d_();
        h.add(a);
    }

    public E[] m13001e() {
        List h = m13002h();
        return (da[]) h.toArray(m12996a().d(h.size()));
    }

    List<E> m13002h() {
        List<E> list = this.f9906d;
        if (list != null) {
            return list;
        }
        list = m12996a().m13025a(this);
        this.f9906d = list;
        return list;
    }

    protected String m13000c() {
        return "ad_play";
    }

    public Integer m13003i() {
        return this.f9903a == null ? null : (Integer) this.f9903a.c_();
    }

    protected ContentValues m12995a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("report_id", m13003i());
        } else {
            contentValues.put("start_millis", this.f9905c);
            contentValues.put("watched_millis", this.f9904b);
        }
        return contentValues;
    }

    protected StringBuilder m13004p() {
        StringBuilder p = super.p();
        a(p, "report_id", m13003i());
        a(p, "start_millis", this.f9905c);
        a(p, "watched_millis", this.f9904b);
        return p;
    }
}
