package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.vungle.publisher.dp.a;
import java.util.List;

/* compiled from: vungle */
public abstract class da<P extends cy<?, P, ?>> extends dp<Integer> {
    P f9916a;
    jf f9917b;
    long f9918c;
    String f9919d;
    private String f9920e;

    /* compiled from: vungle */
    protected static abstract class C4170a<P extends cy<?, P, E>, E extends da<P>> extends a<E, Integer> {
        protected abstract jf m13024a(Cursor cursor);

        protected /* synthetic */ Object[] m13027b(int i) {
            return m13026a(i);
        }

        protected C4170a() {
        }

        protected E m13021a(P p, jf jfVar, Object obj) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            } else if (jfVar == null) {
                throw new IllegalArgumentException("null event");
            } else {
                da daVar = (da) g_();
                daVar.f9916a = p;
                daVar.f9917b = jfVar;
                daVar.f9919d = obj == null ? null : obj.toString();
                return daVar;
            }
        }

        protected List<E> m13025a(P p) {
            if (p == null) {
                throw new IllegalArgumentException("null ad_play");
            }
            if (((Integer) p.c_()) == null) {
                throw new IllegalArgumentException("null play_id");
            }
            List<E> a = a("play_id = ?", new String[]{((Integer) p.c_()).toString()}, "insert_timestamp_millis ASC");
            for (E e : a) {
                e.f9916a = p;
            }
            return a;
        }

        protected E m13022a(E e, Cursor cursor, boolean z) {
            e.f9917b = m13024a(cursor);
            e.u = ce.m12921c(cursor, "id");
            e.f9918c = ce.m12923d(cursor, "insert_timestamp_millis").longValue();
            e.f9919d = ce.m12925e(cursor, FirebaseAnalytics$Param.VALUE);
            return e;
        }

        protected String m13028c() {
            return "ad_report_event";
        }

        protected Integer[] m13026a(int i) {
            return new Integer[i];
        }
    }

    protected da() {
    }

    public jf m13030a() {
        return this.f9917b;
    }

    public long m13032e() {
        return this.f9918c;
    }

    public Integer m13033h() {
        return this.f9916a == null ? null : (Integer) this.f9916a.c_();
    }

    public String m13034i() {
        return this.f9919d;
    }

    protected String m13031c() {
        return "ad_report_event";
    }

    protected ContentValues m13029a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            this.f9918c = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put("play_id", m13033h());
            contentValues.put(NotificationCompat.CATEGORY_EVENT, this.f9917b.toString());
            contentValues.put(FirebaseAnalytics$Param.VALUE, this.f9919d);
        }
        return contentValues;
    }

    public String toString() {
        String str = this.f9920e;
        if (str != null) {
            return str;
        }
        str = super.toString();
        this.f9920e = str;
        return str;
    }

    protected StringBuilder m13035p() {
        StringBuilder p = super.p();
        a(p, "play_id", m13033h());
        a(p, NotificationCompat.CATEGORY_EVENT, this.f9917b);
        a(p, "insert_timestamp_millis", Long.valueOf(this.f9918c));
        a(p, FirebaseAnalytics$Param.VALUE, this.f9919d);
        return p;
    }
}
