package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.log.Logger;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class df extends dp<Integer> {
    ej<?, ?> f2812a;
    String f2813b;
    Integer f2814c;
    @Inject
    C1598a f2815d;

    @Singleton
    /* compiled from: vungle */
    public static class C1598a extends C1592a<df, Integer> {
        @Inject
        Provider<df> f2811a;

        public /* bridge */ /* synthetic */ List mo2959a(String str, String[] strArr) {
            return super.mo2959a(str, strArr);
        }

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m3681c(i);
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m3677a(i);
        }

        protected /* synthetic */ dp g_() {
            return m3671a();
        }

        @Inject
        C1598a() {
        }

        public df m3674a(ej<?, ?> ejVar, File file, String str, int i) {
            df a = m3671a();
            a.f2812a = ejVar;
            a.f2813b = str;
            a.f2814c = Integer.valueOf(i);
            return a;
        }

        int m3669a(Integer num) {
            if (num == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            int delete = this.d.getWritableDatabase().delete("archive_entry", "viewable_id = ?", new String[]{String.valueOf(num)});
            Logger.d(Logger.DATABASE_TAG, "deleted " + delete + " " + "archive_entry" + " rows for " + "viewable_id" + " " + num);
            return delete;
        }

        df[] m3678a(ej<?, ?> ejVar) {
            Throwable th;
            if (ejVar == null) {
                throw new IllegalArgumentException("null archive");
            }
            Integer M = ejVar.M();
            if (M == null) {
                throw new IllegalArgumentException("null viewable_id");
            }
            Cursor query;
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching archive_entry records by viewable_id " + M);
                query = this.d.getReadableDatabase().query("archive_entry", null, "viewable_id = ?", new String[]{String.valueOf(M)}, null, null, null);
                try {
                    df[] dfVarArr = new df[query.getCount()];
                    int i = 0;
                    while (query.moveToNext()) {
                        df a = m3673a(m3671a(), (ej) ejVar, query);
                        dfVarArr[i] = a;
                        Logger.v(Logger.DATABASE_TAG, "fetched " + a);
                        i++;
                    }
                    if (query != null) {
                        query.close();
                    }
                    return dfVarArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        df m3673a(df dfVar, ej<?, ?> ejVar, Cursor cursor) {
            m3533b(dfVar, cursor, false);
            dfVar.f2812a = ejVar;
            return dfVar;
        }

        protected df m3672a(df dfVar, Cursor cursor, boolean z) {
            dfVar.u = ce.c(cursor, "id");
            dfVar.f2813b = ce.e(cursor, "relative_path");
            dfVar.f2814c = ce.c(cursor, "size");
            return dfVar;
        }

        protected String mo2961c() {
            return "archive_entry";
        }

        protected df[] m3677a(int i) {
            return new df[i];
        }

        protected Integer[] m3681c(int i) {
            return new Integer[i];
        }

        protected df m3671a() {
            return (df) this.f2811a.get();
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m3684a();
    }

    @Inject
    df() {
    }

    protected C1598a m3684a() {
        return this.f2815d;
    }

    protected String mo2966c() {
        return "archive_entry";
    }

    ej<?, ?> m3687e() {
        return this.f2812a;
    }

    Integer m3688h() {
        return this.f2812a == null ? null : this.f2812a.M();
    }

    File m3689i() {
        String n = m3691n();
        return n == null ? null : new File(n);
    }

    String m3690m() {
        return m3687e().m3783z();
    }

    String m3691n() {
        return qr.a(new Object[]{m3690m(), this.f2813b});
    }

    boolean m3693r() {
        return m3685b(false);
    }

    boolean m3685b(boolean z) {
        boolean z2 = false;
        String str = Logger.PREPARE_TAG;
        File i = m3689i();
        if (this.f2814c == null) {
            if (z) {
                z2 = i.exists();
                if (z2) {
                    Logger.v(Logger.PREPARE_TAG, i + " exists");
                } else {
                    Logger.i(Logger.PREPARE_TAG, i + " doesn't exist");
                }
            }
            Logger.w(Logger.PREPARE_TAG, i + " " + "size" + " is null");
            return z2;
        }
        int length = (int) i.length();
        int intValue = this.f2814c.intValue();
        if (length == intValue) {
            z2 = true;
        }
        if (z2) {
            Logger.v(Logger.PREPARE_TAG, i + " size verified " + length);
            return z2;
        }
        Logger.d(Logger.PREPARE_TAG, i + " size " + length + " doesn't match expected " + intValue);
        return i.exists();
    }

    public int f_() {
        if (this.u != null) {
            return super.f_();
        }
        Integer h = m3688h();
        Logger.d(Logger.DATABASE_TAG, "updating archive_entry by viewable_id " + h + ", " + "relative_path" + " " + this.f2813b);
        int updateWithOnConflict = this.v.getWritableDatabase().updateWithOnConflict("archive_entry", mo2964a(false), "viewable_id = ? AND relative_path = ?", new String[]{String.valueOf(h), r6}, 3);
        m3574A();
        return updateWithOnConflict;
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (this.u != null) {
            contentValues.put("id", (Integer) this.u);
        }
        contentValues.put("viewable_id", m3688h());
        contentValues.put("relative_path", this.f2813b);
        contentValues.put("size", this.f2814c);
        return contentValues;
    }

    protected StringBuilder mo2976p() {
        StringBuilder p = super.mo2976p();
        dp.m3572a(p, "viewable_id", m3688h());
        dp.m3572a(p, "relative_path", this.f2813b);
        dp.m3572a(p, "size", this.f2814c);
        return p;
    }
}
