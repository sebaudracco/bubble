package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.NonNull;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class dp<I> implements gj<I> {
    Class<I> f2774t;
    protected I f2775u;
    @Inject
    protected ci f2776v;

    /* compiled from: vungle */
    protected static abstract class C1592a<T extends dp<I>, I> {
        @Inject
        protected ci f2765d;

        protected abstract T mo2958a(T t, Cursor cursor, boolean z);

        protected abstract I[] mo2960b(int i);

        protected abstract String mo2961c();

        protected abstract T[] mo2962d(int i);

        protected abstract T g_();

        protected C1592a() {
        }

        public int m3520a(I... iArr) {
            int i = 0;
            int length = iArr == null ? 0 : iArr.length;
            if (length == 0) {
                Logger.d(Logger.DATABASE_TAG, "no " + mo2961c() + " records requested for delete");
                return 0;
            }
            int i2;
            dp g_ = g_();
            boolean z = iArr instanceof String[];
            String[] strArr = z ? (String[]) iArr : new String[length];
            if (!z) {
                int length2 = iArr.length;
                i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    strArr[i2] = String.valueOf(iArr[i]);
                    i++;
                    i2 = i3;
                }
            }
            i2 = this.f2765d.getWritableDatabase().delete(mo2961c(), g_.h_() + " IN (" + ce.a(length) + ")", strArr);
            if (i2 == length) {
                Logger.d(Logger.DATABASE_TAG, "deleted " + i2 + " " + mo2961c() + " records by " + g_.h_() + " in " + zk.b(iArr));
                return i2;
            }
            Logger.w(Logger.DATABASE_TAG, "deleted " + i2 + " of " + length + " requested records by " + g_.h_() + " in " + zk.b(iArr));
            return i2;
        }

        public int mo2957a(List<T> list) {
            return m3532b(list == null ? null : (dp[]) list.toArray(mo2962d(list.size())));
        }

        public int m3532b(T... tArr) {
            int i = 0;
            Object[] objArr = null;
            if (tArr != null) {
                objArr = mo2960b(tArr.length);
                int length = tArr.length;
                int i2 = 0;
                while (i < length) {
                    int i3 = i2 + 1;
                    objArr[i2] = tArr[i].c_();
                    i++;
                    i2 = i3;
                }
            }
            return m3520a(objArr);
        }

        public List<T> mo2959a(String str, String[] strArr) {
            return m3529a(str, strArr, null);
        }

        public T m3524a(I i) {
            return m3526a((Object) i, false);
        }

        public T m3526a(I i, boolean z) {
            return m3525a((Object) i, null, null, z);
        }

        public T m3525a(I i, String str, String[] strArr, boolean z) {
            dp g_ = g_();
            g_.mo2969a((Object) i);
            return m3522a(g_, str, strArr, z);
        }

        protected T m3523a(T t, boolean z) {
            return m3522a((dp) t, null, null, z);
        }

        protected T m3522a(T t, String str, String[] strArr, boolean z) {
            if (t == null) {
                throw new IllegalArgumentException("null model");
            }
            String h_ = t.h_();
            if (h_ == null) {
                throw new IllegalArgumentException("null id name");
            }
            Object c_ = t.c_();
            if (c_ == null) {
                throw new IllegalArgumentException("null " + h_);
            }
            StringBuilder append = new StringBuilder().append(h_).append(" = ?");
            Iterable arrayList = new ArrayList();
            arrayList.add(String.valueOf(c_));
            if (str != null) {
                append.append(" AND ").append(str);
                if (strArr != null) {
                    arrayList.addAll(Arrays.asList(strArr));
                }
            }
            String stringBuilder = append.toString();
            List a = m3530a(stringBuilder, (String[]) arrayList.toArray(new String[arrayList.size()]), null, null);
            int size = a.size();
            switch (size) {
                case 0:
                    return null;
                case 1:
                    return (dp) a.get(0);
                default:
                    throw new SQLException(size + " " + mo2961c() + " records found for query: " + stringBuilder + ", parameters: " + zk.a(arrayList));
            }
        }

        protected List<T> m3529a(String str, String[] strArr, String str2) {
            return m3530a(str, strArr, str2, null);
        }

        protected List<T> m3530a(String str, String[] strArr, String str2, String str3) {
            Throwable th;
            Cursor cursor;
            try {
                String c = mo2961c();
                Logger.d(Logger.DATABASE_TAG, "fetching " + (str == null ? "all " + c + " records" : c + " records by " + str + " " + zk.b(strArr)));
                Cursor query = this.f2765d.getReadableDatabase().query(c, null, str, strArr, null, null, str2, str3);
                try {
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, (count == 0 ? "no " : "fetched " + count + " ") + c + " records by " + str + " " + zk.b(strArr));
                    List<T> b = m3534b(query);
                    if (query != null) {
                        query.close();
                    }
                    return b;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected boolean m3536b(String str, String[] strArr) {
            boolean z = false;
            Cursor cursor = null;
            try {
                cursor = this.f2765d.getWritableDatabase().rawQuery("SELECT EXISTS (SELECT 1 FROM " + mo2961c() + " WHERE " + str + " LIMIT 1)", strArr);
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        protected List<I> mo2968a(T... tArr) {
            List<I> arrayList = new ArrayList(tArr.length);
            for (dp d_ : tArr) {
                arrayList.add(d_.d_());
            }
            return arrayList;
        }

        protected List<T> m3534b(Cursor cursor) {
            return m3527a(cursor, false);
        }

        protected List<T> m3527a(Cursor cursor, boolean z) {
            List<T> arrayList = new ArrayList(cursor.getCount());
            while (cursor.moveToNext()) {
                arrayList.add(m3533b(g_(), cursor, z));
            }
            return arrayList;
        }

        protected final T m3533b(T t, Cursor cursor, boolean z) {
            mo2958a((dp) t, cursor, z);
            Logger.v(Logger.DATABASE_TAG, "fetched " + t);
            return t;
        }

        protected final List<T> m3535b(@NonNull List<String> list) {
            List<T> a = m3529a("id NOT IN (" + ce.a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null);
            Logger.v(Logger.DATABASE_TAG, "getAllExcept count: " + a.size());
            return a;
        }

        protected final List<T> m3539c(@NonNull List<String> list) {
            List<T> a = m3529a("id IN (" + ce.a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null);
            Logger.v(Logger.DATABASE_TAG, "getAll count: " + a.size());
            return a;
        }
    }

    protected abstract ContentValues mo2964a(boolean z);

    protected abstract <T extends dp<I>> C1592a<T, I> b_();

    protected abstract String mo2966c();

    static void m3572a(StringBuilder stringBuilder, String str, Object obj) {
        m3573a(stringBuilder, str, obj, false);
    }

    static void m3573a(StringBuilder stringBuilder, String str, Object obj, boolean z) {
        if (!z) {
            stringBuilder.append(", ");
        }
        stringBuilder.append(str).append(": ").append(obj);
    }

    public I c_() {
        return this.f2775u;
    }

    protected void mo2969a(I i) {
        this.f2775u = i;
    }

    protected String h_() {
        return "id";
    }

    protected boolean i_() {
        return true;
    }

    public I d_() {
        Object c_ = c_();
        if (!i_() || c_ == null) {
            Logger.d(Logger.DATABASE_TAG, "inserting " + this);
            long a = this.f2776v.m3509a(mo2966c(), null, mo2964a(true));
            if (this.f2774t == null || Integer.class.equals(this.f2774t)) {
                mo2969a(Integer.valueOf((int) a));
            } else if (Long.class.equals(this.f2774t)) {
                mo2969a(Long.valueOf(a));
            }
            Logger.d(Logger.DATABASE_TAG, "inserted " + c_());
            return c_();
        }
        throw new SQLException("attempt to insert with non-auto generated id " + m3575B());
    }

    public void m3574A() {
        m3580c(false);
    }

    public void m3580c(boolean z) {
        b_().m3523a(this, z);
    }

    public I e_() {
        if (c_() == null || b_().m3524a(c_()) == null) {
            return d_();
        }
        if (f_() == 1) {
            return c_();
        }
        return null;
    }

    public int f_() {
        String h_ = h_();
        Object c_ = c_();
        if (c_ == null) {
            throw new IllegalArgumentException("null " + h_);
        }
        String c = mo2966c();
        String str = h_ + " " + c_;
        int a = this.f2776v.m3508a(c, mo2964a(false), h_() + " = ?", new String[]{c_.toString()}, 3);
        switch (a) {
            case 0:
                Logger.d(Logger.DATABASE_TAG, "no " + c + " rows updated by " + str);
                break;
            case 1:
                Logger.d(Logger.DATABASE_TAG, "update successful " + m3575B());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "updated " + a + " " + c + " records for " + str);
                break;
        }
        return a;
    }

    protected int mo2977q() {
        return b_().m3520a(c_());
    }

    public String m3575B() {
        return j_().append('}').toString();
    }

    protected StringBuilder j_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{').append(m3576C()).append(":: ");
        m3573a(stringBuilder, h_(), c_(), true);
        return stringBuilder;
    }

    protected String m3576C() {
        return mo2966c();
    }

    public String toString() {
        return mo2976p().append('}').toString();
    }

    protected StringBuilder mo2976p() {
        return j_();
    }
}
