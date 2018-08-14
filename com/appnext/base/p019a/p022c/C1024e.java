package com.appnext.base.p019a.p022c;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import com.appnext.base.C1061b;
import com.appnext.base.p019a.p020a.C1016a;
import com.appnext.base.p019a.p020a.C1016a.C1015a;
import com.appnext.base.p019a.p021b.C1018d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class C1024e<MODEL extends C1018d> {
    private static final int gI = -1;
    private static final String gJ = " DESC";

    protected enum C1029a {
        Equals(" = ? "),
        GreaterThan(" >= ? "),
        LessThan(" <= ? ");
        
        private String mOperator;

        private C1029a(String str) {
            this.mOperator = str;
        }

        public String bo() {
            return this.mOperator;
        }
    }

    protected abstract MODEL mo2020b(Cursor cursor);

    protected abstract String[] bn();

    protected long m2074a(String str, ContentValues contentValues) {
        try {
            long insert = C1016a.aT().aU().insert(str, null, contentValues);
            C1016a.aT().aV();
            return insert;
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, th);
            return -1;
        }
    }

    protected long m2079b(String str, ContentValues contentValues) {
        try {
            long insertWithOnConflict = C1016a.aT().aU().insertWithOnConflict(str, null, contentValues, 5);
            C1016a.aT().aV();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, th);
            return -1;
        }
    }

    protected long m2073a(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        long j = -1;
        try {
            j = sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, th);
        }
        return j;
    }

    protected long m2078b(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        long j = -1;
        try {
            j = sQLiteDatabase.insert(str, null, contentValues);
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, th);
        }
        return j;
    }

    private ContentValues m2070b(JSONObject jSONObject) {
        try {
            ContentValues contentValues = new ContentValues();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                contentValues.put(str, jSONObject.getString(str));
            }
            return contentValues;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    protected long m2076a(String str, JSONObject jSONObject) {
        try {
            long insertWithOnConflict = C1016a.aT().aU().insertWithOnConflict(str, null, m2070b(jSONObject), 5);
            C1016a.aT().aV();
            return insertWithOnConflict;
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
            return -1;
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, th);
            return -1;
        }
    }

    protected long m2075a(String str, JSONArray jSONArray) {
        long j = -1;
        if (jSONArray != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                int length = jSONArray.length();
                sQLiteDatabase = C1016a.aT().aU();
                sQLiteDatabase.beginTransaction();
                int i = 0;
                while (i < length) {
                    i++;
                    j = m2073a(sQLiteDatabase, str, m2070b(jSONArray.getJSONObject(i)));
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    C1016a.aT().aV();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    C1016a.aT().aV();
                }
            }
        }
        return j;
    }

    protected long m2080b(String str, JSONArray jSONArray) {
        long j = -1;
        if (jSONArray != null) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                int length = jSONArray.length();
                sQLiteDatabase = C1016a.aT().aU();
                sQLiteDatabase.beginTransaction();
                int i = 0;
                while (i < length) {
                    i++;
                    j = m2078b(sQLiteDatabase, str, m2070b(jSONArray.getJSONObject(i)));
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    C1016a.aT().aV();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.endTransaction();
                    C1016a.aT().aV();
                }
            }
        }
        return j;
    }

    protected void delete(String str) {
        m2072a(str, null, null, null);
    }

    protected int m2072a(String str, String[] strArr, String[] strArr2, List<C1029a> list) {
        int delete;
        SQLiteFullException e;
        Throwable th;
        try {
            SQLiteDatabase aU = C1016a.aT().aU();
            String str2 = null;
            if (strArr != null) {
                str2 = m2069a(strArr, (List) list);
            }
            delete = aU.delete(str, str2, strArr2);
            try {
                C1016a.aT().aV();
            } catch (SQLiteFullException e2) {
                e = e2;
                C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
                return delete;
            } catch (Throwable th2) {
                th = th2;
                C1016a.aT().m2061a(C1015a.Global, new Exception(th.getMessage()));
                return delete;
            }
        } catch (SQLiteFullException e3) {
            SQLiteFullException sQLiteFullException = e3;
            delete = 0;
            e = sQLiteFullException;
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
            return delete;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            delete = 0;
            th = th4;
            C1016a.aT().m2061a(C1015a.Global, new Exception(th.getMessage()));
            return delete;
        }
        return delete;
    }

    protected void m2082f(String str, String str2) {
        try {
            C1016a.aT().aU().delete(str, str2, null);
            C1016a.aT().aV();
        } catch (SQLiteFullException e) {
            C1016a.aT().m2061a(C1015a.DatabaseOrDiskFull, new Exception(e.getMessage()));
        } catch (Throwable th) {
            C1016a.aT().m2061a(C1015a.Global, new Exception(th.getMessage()));
        }
    }

    protected List<MODEL> ag(String str) {
        try {
            List<MODEL> e = m2071e(C1016a.aT().aU().query(str, bn(), null, null, null, null, null));
            C1016a.aT().aV();
            return e;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    protected List<MODEL> m2077a(String str, String[] strArr, String[] strArr2, String str2, List<C1029a> list) {
        try {
            List<MODEL> e = m2071e(C1016a.aT().aU().query(str, bn(), m2069a(strArr, (List) list), strArr2, null, null, str2));
            C1016a.aT().aV();
            return e;
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    protected String ah(String str) {
        return str + gJ;
    }

    private List<MODEL> m2071e(Cursor cursor) {
        List<MODEL> arrayList = new ArrayList();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(mo2020b(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }

    private String m2069a(String[] strArr, List<C1029a> list) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    stringBuilder.append(" AND ");
                }
                stringBuilder.append(strArr[i]);
                stringBuilder.append(((C1029a) list.get(i)).bo());
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return stringBuilder.toString();
    }
}
