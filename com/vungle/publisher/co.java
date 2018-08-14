package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class co extends dp<Integer> {
    @Inject
    C1593a f2777a;
    private String f2778b;
    private Integer f2779c;

    @Singleton
    /* compiled from: vungle */
    public static class C1593a extends C1592a<co, Integer> {
        @Inject
        Provider<co> f2773a;

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m3570c(i);
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m3567a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3561a();
        }

        @Inject
        C1593a() {
        }

        public co m3563a(Integer num, String str) {
            co a = m3561a();
            a.f2778b = str;
            a.f2779c = num;
            return a;
        }

        protected String mo2961c() {
            return "errors";
        }

        protected co m3562a(co coVar, Cursor cursor, boolean z) {
            coVar.f2778b = ce.e(cursor, "code");
            coVar.f2779c = ce.c(cursor, "report_id");
            return coVar;
        }

        public List<co> m3565a(Integer num) {
            return mo2959a("report_id = ?", new String[]{num.toString()});
        }

        protected co m3561a() {
            return (co) this.f2773a.get();
        }

        protected co[] m3567a(int i) {
            return new co[i];
        }

        protected Integer[] m3570c(int i) {
            return new Integer[i];
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3586a();
    }

    @Inject
    co() {
    }

    protected C1593a m3586a() {
        return this.f2777a;
    }

    protected String mo2966c() {
        return "errors";
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(h_(), (Integer) this.u);
            contentValues.put("report_id", this.f2779c);
        }
        contentValues.put("code", this.f2778b);
        return contentValues;
    }

    public String toString() {
        return this.f2778b;
    }
}
