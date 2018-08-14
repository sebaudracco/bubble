package com.inmobi.commons.core.p116c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DbStore */
public final class C3115b {
    private static final String f7622a = C3115b.class.getSimpleName();
    private static volatile C3115b f7623b;
    private static final Object f7624c = new Object();
    private static final Object f7625d = new Object();
    private static int f7626e = 0;
    private SQLiteDatabase f7627f;

    private C3115b() {
        try {
            this.f7627f = new C3114a(C3105a.m10078b()).getWritableDatabase();
            f7623b = this;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7622a, "Problem while getting writable database connection.", e);
        }
    }

    public static synchronized C3115b m10131a() {
        C3115b c3115b;
        synchronized (C3115b.class) {
            synchronized (f7625d) {
                f7626e++;
            }
            c3115b = f7623b;
            if (c3115b == null) {
                synchronized (f7624c) {
                    c3115b = f7623b;
                    if (c3115b == null) {
                        f7623b = new C3115b();
                        c3115b = f7623b;
                    }
                }
            }
        }
        return c3115b;
    }

    public synchronized void m10135a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (!m10137a(str, contentValues)) {
                m10138b(str, contentValues, str2, strArr);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.insertOrUpdate() method; " + e.getMessage());
        }
    }

    public synchronized boolean m10137a(String str, ContentValues contentValues) {
        boolean z = false;
        synchronized (this) {
            try {
                if (this.f7627f.insertWithOnConflict(str, null, contentValues, 4) != -1) {
                    z = true;
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.insert() method; " + e.getMessage());
            }
        }
        return z;
    }

    public synchronized int m10133a(String str, String str2, String[] strArr) {
        int delete;
        try {
            delete = this.f7627f.delete(str, str2, strArr);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered an unexpected error in DbStore.delete() method; " + e.getMessage());
            delete = -1;
        }
        return delete;
    }

    public synchronized int m10138b(String str, ContentValues contentValues, String str2, String[] strArr) {
        int updateWithOnConflict;
        try {
            updateWithOnConflict = this.f7627f.updateWithOnConflict(str, contentValues, str2, strArr, 4);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered an unexpected error in DbStore.delete() method; " + e.getMessage());
            updateWithOnConflict = -1;
        }
        return updateWithOnConflict;
    }

    public synchronized List<ContentValues> m10134a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        List<ContentValues> arrayList;
        Cursor query;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        try {
            query = this.f7627f.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
            try {
                if (query.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(query, contentValues);
                        arrayList.add(contentValues);
                    } while (query.moveToNext());
                }
                query.close();
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.getRows() method; " + e.getMessage());
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.getRows() method; " + e.getMessage());
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized int m10132a(String str) {
        int i;
        try {
            Cursor rawQuery = this.f7627f.rawQuery("SELECT COUNT(*) FROM " + str + " ; ", null);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.getCount() method; " + e.getMessage());
            i = -1;
        }
        return i;
    }

    public synchronized int m10139b(String str, String str2, String[] strArr) {
        int i;
        try {
            Cursor rawQuery = this.f7627f.rawQuery("SELECT COUNT(*) FROM " + str + " WHERE " + str2 + " ; ", strArr);
            rawQuery.moveToFirst();
            i = rawQuery.getInt(0);
            rawQuery.close();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.getCount() method; " + e.getMessage());
            i = -1;
        }
        return i;
    }

    public synchronized void m10136a(String str, String str2) {
        try {
            this.f7627f.execSQL("CREATE TABLE IF NOT EXISTS " + str + str2 + ";");
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.createTableIfNotExists() method; " + e.getMessage());
        }
    }

    public synchronized void m10140b() {
        try {
            synchronized (f7625d) {
                f7626e--;
                if (f7626e == 0) {
                    this.f7627f.close();
                    f7623b = null;
                }
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7622a, "SDK encountered unexpected error in DbStore.close() method; " + e.getMessage());
        }
    }
}
