package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.appnext.base.p019a.p022c.C1028c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.C4528i;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class bq {
    private final Map<String, Object> f11975a;
    private final Map<String, Object> f11976b;
    private final String f11977c;
    private final C4421a f11978d;
    private volatile boolean f11979e;
    private final bv f11980f;

    private class C4421a extends Thread {
        final /* synthetic */ bq f11974a;

        private C4421a(bq bqVar) {
            this.f11974a = bqVar;
        }

        public void run() {
            synchronized (this.f11974a.f11975a) {
                bq.m15375b(this.f11974a);
                this.f11974a.f11979e = true;
                this.f11974a.f11975a.notifyAll();
            }
            while (!isInterrupted()) {
                synchronized (this) {
                    if (this.f11974a.f11976b.size() == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            interrupt();
                        }
                    }
                    Map hashMap = new HashMap(this.f11974a.f11976b);
                    this.f11974a.f11976b.clear();
                }
                if (hashMap.size() > 0) {
                    bq.m15370a(this.f11974a, hashMap);
                    hashMap.clear();
                }
            }
        }
    }

    static /* synthetic */ void m15375b(bq bqVar) {
        SQLiteDatabase a;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        Cursor query;
        try {
            a = bqVar.f11980f.mo7058a();
            try {
                query = a.query(bqVar.m15381a(), new String[]{C1028c.gv, FirebaseAnalytics$Param.VALUE, "type"}, null, null, null, null, null);
                while (query.moveToNext()) {
                    try {
                        CharSequence string = query.getString(query.getColumnIndex(C1028c.gv));
                        Object string2 = query.getString(query.getColumnIndex(FirebaseAnalytics$Param.VALUE));
                        int i = query.getInt(query.getColumnIndex("type"));
                        if (!TextUtils.isEmpty(string)) {
                            switch (i) {
                                case 1:
                                    if (!SchemaSymbols.ATTVAL_TRUE.equals(string2)) {
                                        if (!SchemaSymbols.ATTVAL_FALSE.equals(string2)) {
                                            string2 = null;
                                            break;
                                        } else {
                                            string2 = Boolean.FALSE;
                                            break;
                                        }
                                    }
                                    string2 = Boolean.TRUE;
                                    break;
                                case 2:
                                    string2 = C4528i.m16278b(string2);
                                    break;
                                case 3:
                                    string2 = C4528i.m16277a(string2);
                                    break;
                                case 4:
                                    break;
                                default:
                                    string2 = null;
                                    break;
                            }
                            if (string2 != null) {
                                bqVar.f11975a.put(string, string2);
                            }
                        }
                    } catch (Exception e) {
                        cursor = query;
                    } catch (Throwable th2) {
                        sQLiteDatabase = a;
                        th = th2;
                    }
                }
                bk.m14978a(query);
                bqVar.f11980f.mo7059a(a);
            } catch (Exception e2) {
                bk.m14978a(cursor);
                bqVar.f11980f.mo7059a(a);
            } catch (Throwable th22) {
                query = null;
                sQLiteDatabase = a;
                th = th22;
                bk.m14978a(query);
                bqVar.f11980f.mo7059a(sQLiteDatabase);
                throw th;
            }
        } catch (Exception e3) {
            a = null;
            bk.m14978a(cursor);
            bqVar.f11980f.mo7059a(a);
        } catch (Throwable th3) {
            th = th3;
            query = null;
            bk.m14978a(query);
            bqVar.f11980f.mo7059a(sQLiteDatabase);
            throw th;
        }
    }

    static {
        bq.class.getSimpleName();
    }

    public bq(bo boVar, String str) {
        this(str, new bx(boVar));
    }

    protected bq(String str, bv bvVar) {
        this.f11975a = new HashMap();
        this.f11976b = new HashMap();
        this.f11980f = bvVar;
        this.f11977c = str;
        this.f11978d = new C4421a();
        this.f11978d.start();
    }

    String m15381a() {
        return this.f11977c;
    }

    public void m15388b() {
        synchronized (this.f11978d) {
            this.f11978d.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m15372a(android.content.ContentValues[] r10) {
        /*
        r9 = this;
        r0 = 0;
        r1 = 0;
        if (r10 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r2 = r9.f11980f;	 Catch:{ Exception -> 0x0040, all -> 0x0056 }
        r0 = r2.mo7058a();	 Catch:{ Exception -> 0x0040, all -> 0x0056 }
        r0.beginTransaction();	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r2 = r10.length;	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
    L_0x000f:
        if (r1 >= r2) goto L_0x004a;
    L_0x0011:
        r3 = r10[r1];	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r4 = "value";
        r4 = r3.getAsString(r4);	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        if (r4 != 0) goto L_0x0036;
    L_0x001c:
        r4 = "key";
        r3 = r3.getAsString(r4);	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r4 = r9.m15381a();	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r5 = "key = ?";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r7 = 0;
        r6[r7] = r3;	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r0.delete(r4, r5, r6);	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
    L_0x0033:
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0036:
        r4 = r9.m15381a();	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        r5 = 0;
        r6 = 5;
        r0.insertWithOnConflict(r4, r5, r3, r6);	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        goto L_0x0033;
    L_0x0040:
        r1 = move-exception;
        com.yandex.metrica.impl.bk.m14979a(r0);
        r1 = r9.f11980f;
        r1.mo7059a(r0);
        goto L_0x0004;
    L_0x004a:
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x0040, all -> 0x0063 }
        com.yandex.metrica.impl.bk.m14979a(r0);
        r1 = r9.f11980f;
        r1.mo7059a(r0);
        goto L_0x0004;
    L_0x0056:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x005a:
        com.yandex.metrica.impl.bk.m14979a(r1);
        r2 = r9.f11980f;
        r2.mo7059a(r1);
        throw r0;
    L_0x0063:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bq.a(android.content.ContentValues[]):void");
    }

    public String m15382a(String str, String str2) {
        Object b = m15374b(str);
        if (b instanceof String) {
            return (String) b;
        }
        return str2;
    }

    public int m15378a(String str, int i) {
        Object b = m15374b(str);
        if (b instanceof Integer) {
            return ((Integer) b).intValue();
        }
        return i;
    }

    public long m15379a(String str, long j) {
        Object b = m15374b(str);
        if (b instanceof Long) {
            return ((Long) b).longValue();
        }
        return j;
    }

    public boolean m15383a(String str, boolean z) {
        Object b = m15374b(str);
        if (b instanceof Boolean) {
            return ((Boolean) b).booleanValue();
        }
        return z;
    }

    public bq m15380a(String str) {
        synchronized (this.f11975a) {
            m15377c();
            this.f11975a.remove(str);
        }
        synchronized (this.f11978d) {
            this.f11976b.put(str, this);
            this.f11978d.notifyAll();
        }
        return this;
    }

    public synchronized bq m15386b(String str, String str2) {
        m15371a(str, (Object) str2);
        return this;
    }

    public bq m15385b(String str, long j) {
        m15371a(str, Long.valueOf(j));
        return this;
    }

    public synchronized bq m15384b(String str, int i) {
        m15371a(str, Integer.valueOf(i));
        return this;
    }

    public bq m15387b(String str, boolean z) {
        m15371a(str, Boolean.valueOf(z));
        return this;
    }

    private void m15371a(String str, Object obj) {
        synchronized (this.f11975a) {
            m15377c();
            this.f11975a.put(str, obj);
        }
        synchronized (this.f11978d) {
            this.f11976b.put(str, obj);
            this.f11978d.notifyAll();
        }
    }

    private Object m15374b(String str) {
        Object obj;
        synchronized (this.f11975a) {
            m15377c();
            obj = this.f11975a.get(str);
        }
        return obj;
    }

    private void m15377c() {
        if (!this.f11979e) {
            try {
                this.f11975a.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    static /* synthetic */ void m15370a(bq bqVar, Map map) {
        ContentValues[] contentValuesArr = new ContentValues[map.size()];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            ContentValues contentValues = new ContentValues();
            String str = (String) entry.getKey();
            bq value = entry.getValue();
            contentValues.put(C1028c.gv, str);
            if (value == bqVar) {
                contentValues.putNull(FirebaseAnalytics$Param.VALUE);
            } else if (value instanceof String) {
                contentValues.put(FirebaseAnalytics$Param.VALUE, (String) value);
                contentValues.put("type", Integer.valueOf(4));
            } else if (value instanceof Long) {
                contentValues.put(FirebaseAnalytics$Param.VALUE, (Long) value);
                contentValues.put("type", Integer.valueOf(3));
            } else if (value instanceof Integer) {
                contentValues.put(FirebaseAnalytics$Param.VALUE, (Integer) value);
                contentValues.put("type", Integer.valueOf(2));
            } else if (value instanceof Boolean) {
                contentValues.put(FirebaseAnalytics$Param.VALUE, String.valueOf(((Boolean) value).booleanValue()));
                contentValues.put("type", Integer.valueOf(1));
            } else if (value != null) {
                throw new UnsupportedOperationException();
            }
            contentValuesArr[i] = contentValues;
            i++;
        }
        bqVar.m15372a(contentValuesArr);
    }
}
