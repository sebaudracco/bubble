package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C4305a.C4304a;
import com.yandex.metrica.impl.C4372h;
import com.yandex.metrica.impl.C4385k;
import com.yandex.metrica.impl.C4511p;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4533m;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class bn implements Closeable {
    private final ReentrantReadWriteLock f11953a = new ReentrantReadWriteLock();
    private final Lock f11954b = this.f11953a.readLock();
    private final Lock f11955c = this.f11953a.writeLock();
    private final bo f11956d;
    private final C4420a f11957e;
    private final Object f11958f = new Object();
    private final List<ContentValues> f11959g = new ArrayList(3);
    private ContentValues f11960h;
    private final Context f11961i;
    private final C4502u f11962j;
    private final AtomicLong f11963k = new AtomicLong();

    private class C4420a extends Thread {
        final /* synthetic */ bn f11950a;
        private final List<ContentValues> f11951b = new ArrayList();
        private C4502u f11952c;

        public C4420a(bn bnVar) {
            this.f11950a = bnVar;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (this) {
                        if (this.f11950a.m15343d()) {
                            wait();
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (this.f11950a.f11958f) {
                    this.f11951b.clear();
                    this.f11951b.addAll(this.f11950a.f11959g);
                    this.f11950a.f11959g.clear();
                    bn.m15331a(this.f11950a, this.f11950a.f11960h);
                    bn.m15332a(this.f11950a, this.f11951b);
                    this.f11950a.f11960h = null;
                }
                m15326b();
            }
        }

        synchronized void m15324a() {
            interrupt();
            this.f11952c = null;
        }

        synchronized void m15325a(C4502u c4502u) {
            this.f11952c = c4502u;
        }

        synchronized void m15326b() {
            if (!(this.f11952c == null || this.f11952c.mo7115o())) {
                this.f11952c.mo7110b();
            }
        }
    }

    static /* synthetic */ void m15332a(bn bnVar, List list) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (list != null && !list.isEmpty()) {
            bnVar.f11955c.lock();
            try {
                writableDatabase = bnVar.f11956d.getWritableDatabase();
                try {
                    if (bnVar.f11963k.get() % 10 == 0) {
                        bnVar.m15341c();
                    }
                    writableDatabase.beginTransaction();
                    for (ContentValues contentValues : list) {
                        writableDatabase.insertOrThrow("reports", null, contentValues);
                        bnVar.m15330a(contentValues, "Event saved to db");
                    }
                    writableDatabase.setTransactionSuccessful();
                    bnVar.f11963k.incrementAndGet();
                    bk.m14979a(writableDatabase);
                    bnVar.f11955c.unlock();
                } catch (Exception e) {
                    sQLiteDatabase = writableDatabase;
                    bk.m14979a(sQLiteDatabase);
                    bnVar.f11955c.unlock();
                } catch (Throwable th2) {
                    th = th2;
                    bk.m14979a(writableDatabase);
                    bnVar.f11955c.unlock();
                    throw th;
                }
            } catch (Exception e2) {
                bk.m14979a(sQLiteDatabase);
                bnVar.f11955c.unlock();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                writableDatabase = null;
                th = th4;
                bk.m14979a(writableDatabase);
                bnVar.f11955c.unlock();
                throw th;
            }
        }
    }

    public bn(C4502u c4502u, bo boVar) {
        this.f11956d = boVar;
        this.f11961i = c4502u.mo7114m();
        this.f11962j = c4502u;
        this.f11963k.set(m15336b());
        this.f11957e = new C4420a(this);
        this.f11957e.setName("DatabaseWorker [" + c4502u.mo7113l() + "]");
        this.f11957e.start();
        m15341c();
    }

    public void m15354a(C4502u c4502u) {
        this.f11957e.m15325a(c4502u);
    }

    public void m15351a(long j, bl blVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(j));
        contentValues.put("start_time", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("server_time_offset", Long.valueOf(C4533m.m16293a()));
        contentValues.put("type", Integer.valueOf(blVar.m15299a()));
        new C4385k(this.f11961i).m15111a(this.f11962j).m15110a(contentValues).m15112a();
        m15352a(contentValues);
    }

    public void m15353a(C4372h c4372h, bj bjVar, C4304a c4304a) {
        ContentValues contentValues = new ContentValues(20);
        contentValues.put("number", Long.valueOf(bjVar.m15295c()));
        contentValues.put("time", Long.valueOf(bjVar.m15297d()));
        contentValues.put("session_id", Long.valueOf(bjVar.m15290a()));
        contentValues.put("session_type", Integer.valueOf(bjVar.m15294b().m15299a()));
        new C4385k(this.f11961i).m15111a(this.f11962j).m15110a(contentValues).m15114a(c4372h, c4304a);
        m15356b(contentValues);
    }

    private static long m15327a(Cursor cursor) {
        try {
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                return j;
            }
            bk.m14978a(cursor);
            return 0;
        } finally {
            bk.m14978a(cursor);
        }
    }

    private long m15336b() {
        long a;
        this.f11954b.lock();
        try {
            a = m15327a(this.f11956d.getReadableDatabase().rawQuery("SELECT count() FROM reports", null));
        } catch (Exception e) {
            return 0;
        } finally {
            this.f11954b.unlock();
        }
        return a;
    }

    public void m15352a(ContentValues contentValues) {
        synchronized (this.f11958f) {
            this.f11960h = contentValues;
        }
        synchronized (this.f11957e) {
            this.f11957e.notifyAll();
        }
    }

    public void m15356b(ContentValues contentValues) {
        synchronized (this.f11958f) {
            this.f11959g.add(contentValues);
        }
        synchronized (this.f11957e) {
            this.f11957e.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m15345a(long[] r9) {
        /*
        r8 = this;
        r1 = 0;
        r0 = 0;
        r2 = r8.f11955c;
        r2.lock();
        r2 = com.yandex.metrica.impl.ob.bm.f11945a;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        if (r2 == 0) goto L_0x0051;
    L_0x000f:
        r2 = r8.f11954b;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r2.lock();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r2 = r8.f11956d;	 Catch:{ Exception -> 0x00cc, all -> 0x00c0 }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x00cc, all -> 0x00c0 }
        r2 = " SELECT DISTINCT id From sessions order by id asc ";
        r4 = 0;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00cc, all -> 0x00c0 }
        r2 = r3.rawQuery(r2, r4);	 Catch:{ Exception -> 0x00cc, all -> 0x00c0 }
        r4 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r4.<init>();	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r5 = "All sessions in db: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
    L_0x002f:
        r5 = r2.moveToNext();	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        if (r5 == 0) goto L_0x006a;
    L_0x0035:
        r5 = 0;
        r5 = r2.getString(r5);	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r5 = r4.append(r5);	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r6 = ", ";
        r5.append(r6);	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        goto L_0x002f;
    L_0x0045:
        r3 = move-exception;
    L_0x0046:
        r3 = r8.f11954b;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r3.unlock();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
    L_0x0051:
        r1 = r8.f11956d;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r1 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r2 = "sessions";
        r3 = com.yandex.metrica.impl.ob.bm.C4419z.f11944c;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r4 = com.yandex.metrica.impl.bk.m14989a(r9);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r0 = r1.delete(r2, r3, r4);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r1 = r8.f11955c;
        r1.unlock();
    L_0x0069:
        return r0;
    L_0x006a:
        r4 = " SELECT DISTINCT session_id From reports order by session_id asc ";
        r5 = 0;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r1 = r3.rawQuery(r4, r5);	 Catch:{ Exception -> 0x0045, all -> 0x00c6 }
        r3 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        r3.<init>();	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        r4 = "All sessions in reports db: ";
        r3.append(r4);	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
    L_0x007f:
        r4 = r1.moveToNext();	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        if (r4 == 0) goto L_0x00ad;
    L_0x0085:
        r4 = 0;
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        r4 = r3.append(r4);	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        r5 = ", ";
        r4.append(r5);	 Catch:{ Exception -> 0x0045, all -> 0x0095 }
        goto L_0x007f;
    L_0x0095:
        r3 = move-exception;
        r7 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r7;
    L_0x009a:
        r4 = r8.f11954b;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r4.unlock();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r3);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        throw r1;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
    L_0x00a6:
        r1 = move-exception;
        r1 = r8.f11955c;
        r1.unlock();
        goto L_0x0069;
    L_0x00ad:
        r3 = r8.f11954b;	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        r3.unlock();	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r2);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        com.yandex.metrica.impl.bk.m14978a(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x00b9 }
        goto L_0x0051;
    L_0x00b9:
        r0 = move-exception;
        r1 = r8.f11955c;
        r1.unlock();
        throw r0;
    L_0x00c0:
        r2 = move-exception;
        r3 = r1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x009a;
    L_0x00c6:
        r3 = move-exception;
        r7 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x009a;
    L_0x00cc:
        r2 = move-exception;
        r2 = r1;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bn.a(long[]):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m15341c() {
        /*
        r6 = this;
        r0 = r6.f11955c;	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r0.lock();	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r0 = r6.f11956d;	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r2 = r0.getPath();	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r2 = r1.length();	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r4 = 5242880; // 0x500000 float:7.34684E-39 double:2.590327E-317;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x002a;
    L_0x001f:
        r0 = r6.m15344a(r0);	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r1 = r6.f11963k;	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r0 = -r0;
        r2 = (long) r0;	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
        r1.addAndGet(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0037 }
    L_0x002a:
        r0 = r6.f11955c;
        r0.unlock();
    L_0x002f:
        return;
    L_0x0030:
        r0 = move-exception;
        r0 = r6.f11955c;
        r0.unlock();
        goto L_0x002f;
    L_0x0037:
        r0 = move-exception;
        r1 = r6.f11955c;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bn.c():void");
    }

    int m15344a(SQLiteDatabase sQLiteDatabase) {
        try {
            Integer[] numArr = new Integer[C4511p.f12559a.size()];
            Iterator it = C4511p.f12559a.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = i + 1;
                numArr[i] = Integer.valueOf(((C4510a) it.next()).m16188a());
                i = i2;
            }
            long currentTimeMillis = (System.currentTimeMillis() / 1000) - TimeUnit.DAYS.toSeconds(14);
            Object[] objArr = new Object[]{"type", TextUtils.join(",", numArr), "id", "reports", "session_id", "number", Integer.valueOf(10), Long.valueOf(currentTimeMillis)};
            return sQLiteDatabase.delete("reports", String.format("%1$s NOT IN (%2$s) AND (%3$s IN (SELECT %3$s FROM %4$s ORDER BY %5$s, %6$s LIMIT (SELECT count() FROM %4$s) / %7$s ) OR %5$s < %8$s )", objArr), null);
        } catch (Throwable th) {
            YandexMetrica.getReporter(this.f11961i, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("deleteExcessiveReports exception", th);
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m15350a(long r10, int r12, int r13) throws android.database.sqlite.SQLiteException {
        /*
        r9 = this;
        r0 = 0;
        if (r13 > 0) goto L_0x0004;
    L_0x0003:
        return;
    L_0x0004:
        r1 = r9.f11955c;
        r1.lock();
        r1 = r9.f11956d;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r2 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r1 = java.util.Locale.US;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r3 = "%1$s = %2$s AND %3$s = %4$s AND %5$s <= (SELECT %5$s FROM %6$s WHERE %1$s = %2$s AND %3$s = %4$s ORDER BY %5$s ASC LIMIT %7$s, 1)";
        r4 = 7;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 0;
        r6 = "session_id";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 1;
        r6 = java.lang.Long.toString(r10);	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 2;
        r6 = "session_type";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 3;
        r6 = java.lang.Integer.toString(r12);	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 4;
        r6 = "id";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 5;
        r6 = "reports";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r5 = 6;
        r6 = r13 + -1;
        r6 = java.lang.Integer.toString(r6);	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r3 = java.lang.String.format(r1, r3, r4);	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r1 = r9.f11962j;	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r1 = r1.mo7116p();	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        r1 = r1.m16247b();	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        if (r1 == 0) goto L_0x00c3;
    L_0x0056:
        r1 = r9.m15328a(r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00ad }
        if (r1 == 0) goto L_0x0089;
    L_0x005c:
        r4 = r1.getCount();	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        if (r4 <= 0) goto L_0x0089;
    L_0x0062:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        r4 = r1.getCount();	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        r0.<init>(r4);	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
    L_0x006b:
        r4 = r1.moveToNext();	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        if (r4 == 0) goto L_0x0089;
    L_0x0071:
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        r4.<init>();	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        android.database.DatabaseUtils.cursorRowToContentValues(r1, r4);	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        r0.add(r4);	 Catch:{ Exception -> 0x007d, all -> 0x00ba }
        goto L_0x006b;
    L_0x007d:
        r0 = move-exception;
        r0 = r1;
    L_0x007f:
        com.yandex.metrica.impl.bk.m14978a(r0);
        r0 = r9.f11955c;
        r0.unlock();
        goto L_0x0003;
    L_0x0089:
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x008c:
        r4 = "reports";
        r5 = 0;
        r2 = r2.delete(r4, r3, r5);	 Catch:{ Exception -> 0x00c1, all -> 0x00bc }
        if (r1 == 0) goto L_0x009c;
    L_0x0096:
        r3 = "Event removed from db";
        r9.m15333a(r1, r3);	 Catch:{ Exception -> 0x00c1, all -> 0x00bc }
    L_0x009c:
        r1 = r9.f11963k;	 Catch:{ Exception -> 0x00c1, all -> 0x00bc }
        r2 = -r2;
        r2 = (long) r2;	 Catch:{ Exception -> 0x00c1, all -> 0x00bc }
        r1.addAndGet(r2);	 Catch:{ Exception -> 0x00c1, all -> 0x00bc }
        com.yandex.metrica.impl.bk.m14978a(r0);
        r0 = r9.f11955c;
        r0.unlock();
        goto L_0x0003;
    L_0x00ad:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x00b1:
        com.yandex.metrica.impl.bk.m14978a(r1);
        r1 = r9.f11955c;
        r1.unlock();
        throw r0;
    L_0x00ba:
        r0 = move-exception;
        goto L_0x00b1;
    L_0x00bc:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00b1;
    L_0x00c1:
        r1 = move-exception;
        goto L_0x007f;
    L_0x00c3:
        r1 = r0;
        goto L_0x008c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bn.a(long, int, int):void");
    }

    private Cursor m15328a(String str) {
        try {
            return this.f11956d.getReadableDatabase().query("reports", null, str, null, null, null, null, null);
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor m15348a(Map<String, String> map) {
        Cursor query;
        this.f11954b.lock();
        try {
            query = this.f11956d.getReadableDatabase().query("sessions", null, m15329a("id >= ?", (Map) map), m15335a(new String[]{Long.toString(0)}, (Map) map), null, null, "id ASC", null);
        } catch (Exception e) {
            return null;
        } finally {
            this.f11954b.unlock();
        }
        return query;
    }

    public Cursor m15347a(long j, Map<String, String> map) {
        Cursor query;
        this.f11954b.lock();
        try {
            query = this.f11956d.getReadableDatabase().query("sessions", null, m15329a("id = ?", (Map) map), m15335a(new String[]{Long.toString(j)}, (Map) map), null, null, null, null);
        } catch (Exception e) {
            return null;
        } finally {
            this.f11954b.unlock();
        }
        return query;
    }

    public Cursor m15355b(long j, bl blVar) throws SQLiteException {
        Cursor query;
        this.f11954b.lock();
        try {
            query = this.f11956d.getReadableDatabase().query("reports", null, "session_id = ? AND session_type = ?", new String[]{Long.toString(j), Integer.toString(blVar.m15299a())}, null, null, "number ASC", null);
        } catch (Exception e) {
            return null;
        } finally {
            this.f11954b.unlock();
        }
        return query;
    }

    private void m15333a(List<ContentValues> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            m15330a((ContentValues) list.get(i), str);
        }
    }

    public List<ContentValues> m15349a(Long l) {
        Cursor cursor = null;
        List<ContentValues> arrayList = new ArrayList();
        this.f11954b.lock();
        try {
            SQLiteDatabase readableDatabase = this.f11956d.getReadableDatabase();
            String str = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id >= 0";
            if (l != null) {
                str = String.format(Locale.US, "SELECT DISTINCT report_request_parameters FROM sessions WHERE id = %s", new Object[]{l});
            }
            cursor = readableDatabase.rawQuery(str, null);
            while (cursor.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                arrayList.add(contentValues);
            }
        } catch (Exception e) {
            arrayList = new ArrayList();
        } finally {
            bk.m14978a(cursor);
            this.f11954b.unlock();
        }
        return arrayList;
    }

    public ContentValues m15357c(long j, bl blVar) {
        Throwable th;
        Cursor cursor = null;
        ContentValues contentValues = new ContentValues();
        this.f11954b.lock();
        Cursor rawQuery;
        try {
            rawQuery = this.f11956d.getReadableDatabase().rawQuery(String.format(Locale.US, "SELECT report_request_parameters FROM sessions WHERE id = %s AND type = %s ORDER BY id DESC LIMIT 1", new Object[]{Long.valueOf(j), Integer.valueOf(blVar.m15299a())}), null);
            try {
                ContentValues contentValues2;
                if (rawQuery.moveToNext()) {
                    contentValues2 = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues2);
                } else {
                    contentValues2 = contentValues;
                }
                bk.m14978a(rawQuery);
                this.f11954b.unlock();
                return contentValues2;
            } catch (Exception e) {
                cursor = rawQuery;
                bk.m14978a(cursor);
                this.f11954b.unlock();
                return contentValues;
            } catch (Throwable th2) {
                th = th2;
                bk.m14978a(rawQuery);
                this.f11954b.unlock();
                throw th;
            }
        } catch (Exception e2) {
            bk.m14978a(cursor);
            this.f11954b.unlock();
            return contentValues;
        } catch (Throwable th3) {
            rawQuery = null;
            th = th3;
            bk.m14978a(rawQuery);
            this.f11954b.unlock();
            throw th;
        }
    }

    private static String m15329a(String str, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str2 : map.keySet()) {
            stringBuilder.append(stringBuilder.length() > 0 ? " AND " : "");
            stringBuilder.append(str2 + " = ? ");
        }
        return bi.m14957a(stringBuilder.toString()) ? null : stringBuilder.toString();
    }

    private static String[] m15335a(String[] strArr, Map<String, String> map) {
        List arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        for (Entry value : map.entrySet()) {
            arrayList.add(value.getValue());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String m15339b(ContentValues contentValues, String str) {
        return bi.m14961b(contentValues.getAsString(str), "");
    }

    public long m15346a() {
        this.f11954b.lock();
        try {
            long j = this.f11963k.get();
            return j;
        } finally {
            this.f11954b.unlock();
        }
    }

    public void close() {
        this.f11959g.clear();
        this.f11957e.m15324a();
    }

    private boolean m15343d() {
        boolean z;
        synchronized (this.f11958f) {
            z = this.f11960h == null && this.f11959g.isEmpty();
        }
        return z;
    }

    private void m15330a(ContentValues contentValues, String str) {
        int intValue;
        Integer asInteger = contentValues.getAsInteger("type");
        if (asInteger != null) {
            intValue = asInteger.intValue();
        } else {
            intValue = -1;
        }
        if (C4511p.m16202b(intValue)) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append(": ");
            stringBuilder.append(m15339b(contentValues, "name"));
            Object b = m15339b(contentValues, FirebaseAnalytics$Param.VALUE);
            if (C4511p.m16206c(contentValues.getAsInteger("type").intValue()) && !TextUtils.isEmpty(b)) {
                stringBuilder.append(" with value ");
                stringBuilder.append(b);
            }
            this.f11962j.mo7116p().m16243a(stringBuilder.toString());
        }
    }

    static /* synthetic */ void m15331a(bn bnVar, ContentValues contentValues) {
        if (contentValues != null) {
            bnVar.f11955c.lock();
            try {
                bnVar.f11956d.getWritableDatabase().insertOrThrow("sessions", null, contentValues);
            } catch (Exception e) {
            } finally {
                bnVar.f11955c.unlock();
            }
        }
    }
}
