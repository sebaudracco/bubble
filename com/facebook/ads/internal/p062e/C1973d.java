package com.facebook.ads.internal.p062e;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import com.facebook.ads.internal.p062e.C1969f.C1975a;
import com.mopub.common.Constants;
import com.stripe.android.net.StripeApiHandler;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class C1973d {
    private static final String f4586a = ("SELECT tokens." + C1976h.f4609a.f4559b + ", " + StripeApiHandler.TOKENS + "." + C1976h.f4610b.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4562a.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4564c.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4565d.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4566e.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4567f.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4568g.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4569h.f4559b + ", " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4570i.f4559b + " FROM " + Constants.VIDEO_TRACKING_EVENTS_KEY + " JOIN " + StripeApiHandler.TOKENS + " ON " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4563b.f4559b + " = " + StripeApiHandler.TOKENS + "." + C1976h.f4609a.f4559b + " ORDER BY " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4566e.f4559b + " ASC");
    private static final int f4587b = Runtime.getRuntime().availableProcessors();
    private static final int f4588c = Math.max(2, Math.min(f4587b - 1, 4));
    private static final int f4589d = ((f4587b * 2) + 1);
    private static final ThreadFactory f4590e = new C19681();
    private static final BlockingQueue<Runnable> f4591f = new LinkedBlockingQueue(128);
    private static final Executor f4592g;
    private static final ReentrantReadWriteLock f4593h = new ReentrantReadWriteLock();
    private static final Lock f4594i = f4593h.readLock();
    private static final Lock f4595j = f4593h.writeLock();
    private final Context f4596k;
    private final C1976h f4597l = new C1976h(this);
    private final C1967c f4598m = new C1967c(this);
    private SQLiteOpenHelper f4599n;

    static class C19681 implements ThreadFactory {
        private final AtomicInteger f4573a = new AtomicInteger(1);

        C19681() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "DatabaseTask #" + this.f4573a.getAndIncrement());
        }
    }

    private static class C1972a<T> extends AsyncTask<Void, Void, T> {
        private final C1969f<T> f4583a;
        private final C1964a<T> f4584b;
        private C1975a f4585c;

        C1972a(C1969f<T> c1969f, C1964a<T> c1964a) {
            this.f4583a = c1969f;
            this.f4584b = c1964a;
        }

        protected T m6211a(Void... voidArr) {
            T t = null;
            try {
                t = this.f4583a.mo3704b();
                this.f4585c = this.f4583a.m6208c();
                return t;
            } catch (SQLiteException e) {
                this.f4585c = C1975a.f4601a;
                return t;
            }
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6211a((Void[]) objArr);
        }

        protected void onPostExecute(T t) {
            if (this.f4585c == null) {
                this.f4584b.mo3707a(t);
            } else {
                this.f4584b.mo3706a(this.f4585c.m6229a(), this.f4585c.m6230b());
            }
            this.f4584b.m6187a();
        }
    }

    static {
        Executor threadPoolExecutor = new ThreadPoolExecutor(f4588c, f4589d, 30, TimeUnit.SECONDS, f4591f, f4590e);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f4592g = threadPoolExecutor;
    }

    public C1973d(Context context) {
        this.f4596k = context;
    }

    private synchronized SQLiteDatabase m6216i() {
        if (this.f4599n == null) {
            this.f4599n = new C1974e(this.f4596k, this);
        }
        return this.f4599n.getWritableDatabase();
    }

    @WorkerThread
    public Cursor m6217a(int i) {
        f4594i.lock();
        try {
            Cursor rawQuery = m6218a().rawQuery(f4586a + " LIMIT " + String.valueOf(i), null);
            return rawQuery;
        } finally {
            f4594i.unlock();
        }
    }

    public SQLiteDatabase m6218a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return m6216i();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    public <T> AsyncTask m6219a(C1969f<T> c1969f, C1964a<T> c1964a) {
        return C2110d.m6770a(f4592g, new C1972a(c1969f, c1964a), new Void[0]);
    }

    public AsyncTask m6220a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map, C1964a<String> c1964a) {
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final double d3 = d;
        final double d4 = d2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        return m6219a(new C1970i<String>(this) {
            final /* synthetic */ C1973d f4582h;

            @Nullable
            public String m6209a() {
                Exception e;
                SQLiteDatabase sQLiteDatabase;
                Throwable th;
                if (TextUtils.isEmpty(str4)) {
                    return null;
                }
                C1973d.f4595j.lock();
                SQLiteDatabase a;
                try {
                    a = this.f4582h.m6218a();
                    try {
                        a.beginTransaction();
                        String a2 = this.f4582h.f4598m.m6201a(this.f4582h.f4597l.m6232a(str4), i2, str5, d3, d4, str6, map2);
                        a.setTransactionSuccessful();
                        if (a != null) {
                            try {
                                if (a.inTransaction()) {
                                    a.endTransaction();
                                }
                            } catch (Exception e2) {
                                C2150a.m6888a(e2, this.f4582h.f4596k);
                            }
                        }
                        C1973d.f4595j.unlock();
                        return a2;
                    } catch (Exception e3) {
                        e = e3;
                        sQLiteDatabase = a;
                        try {
                            m6206a(C1975a.DATABASE_INSERT);
                            C2150a.m6888a(e, this.f4582h.f4596k);
                            if (sQLiteDatabase != null) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Exception e4) {
                                    C2150a.m6888a(e4, this.f4582h.f4596k);
                                }
                            }
                            C1973d.f4595j.unlock();
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            a = sQLiteDatabase;
                            if (a != null) {
                                try {
                                    if (a.inTransaction()) {
                                        a.endTransaction();
                                    }
                                } catch (Exception e22) {
                                    C2150a.m6888a(e22, this.f4582h.f4596k);
                                }
                            }
                            C1973d.f4595j.unlock();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (a != null) {
                            if (a.inTransaction()) {
                                a.endTransaction();
                            }
                        }
                        C1973d.f4595j.unlock();
                        throw th;
                    }
                } catch (Exception e5) {
                    e4 = e5;
                    sQLiteDatabase = null;
                    m6206a(C1975a.DATABASE_INSERT);
                    C2150a.m6888a(e4, this.f4582h.f4596k);
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    C1973d.f4595j.unlock();
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    a = null;
                    if (a != null) {
                        if (a.inTransaction()) {
                            a.endTransaction();
                        }
                    }
                    C1973d.f4595j.unlock();
                    throw th;
                }
            }

            @Nullable
            public /* synthetic */ Object mo3704b() {
                return m6209a();
            }
        }, c1964a);
    }

    @WorkerThread
    public boolean m6221a(String str) {
        boolean z = true;
        f4595j.lock();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE ").append(Constants.VIDEO_TRACKING_EVENTS_KEY).append(" SET ").append(C1967c.f4570i.f4559b).append("=").append(C1967c.f4570i.f4559b).append("+1").append(" WHERE ").append(C1967c.f4562a.f4559b).append("=?");
            m6218a().execSQL(stringBuilder.toString(), new String[]{str});
        } catch (SQLiteException e) {
            z = false;
        }
        f4595j.unlock();
        return z;
    }

    public synchronized void m6222b() {
        for (C1966g e : m6224c()) {
            e.m6198e();
        }
        if (this.f4599n != null) {
            this.f4599n.close();
            this.f4599n = null;
        }
    }

    @WorkerThread
    public boolean m6223b(String str) {
        f4595j.lock();
        try {
            boolean a = this.f4598m.m6202a(str);
            return a;
        } finally {
            f4595j.unlock();
        }
    }

    public C1966g[] m6224c() {
        return new C1966g[]{this.f4597l, this.f4598m};
    }

    public Cursor m6225d() {
        f4594i.lock();
        try {
            Cursor c = this.f4598m.mo3703c();
            return c;
        } finally {
            f4594i.unlock();
        }
    }

    @WorkerThread
    public Cursor m6226e() {
        f4594i.lock();
        try {
            Cursor d = this.f4598m.m6205d();
            return d;
        } finally {
            f4594i.unlock();
        }
    }

    @WorkerThread
    public Cursor m6227f() {
        f4594i.lock();
        try {
            Cursor c = this.f4597l.mo3703c();
            return c;
        } finally {
            f4594i.unlock();
        }
    }

    @WorkerThread
    public void m6228g() {
        f4595j.lock();
        try {
            this.f4597l.m6235d();
        } finally {
            f4595j.unlock();
        }
    }
}
