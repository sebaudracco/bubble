package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.yandex.metrica.impl.bk;
import java.util.ArrayList;
import java.util.List;

public class br {
    private final bv f11981a;
    private String f11982b;

    public br(bo boVar, String str) {
        this.f11981a = new bx(boVar);
        this.f11982b = str;
    }

    public List<cw> m15389a() {
        Cursor query;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            SQLiteDatabase a = this.f11981a.mo7058a();
            try {
                query = a.query(this.f11982b, null, null, null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            List<cw> arrayList = new ArrayList();
                            do {
                                arrayList.add(new cw(query.getString(query.getColumnIndex("name")), query.getLong(query.getColumnIndex("granted")) == 1));
                            } while (query.moveToNext());
                            this.f11981a.mo7059a(a);
                            bk.m14978a(query);
                            return arrayList;
                        }
                    } catch (Exception e) {
                        sQLiteDatabase = a;
                        cursor = query;
                        this.f11981a.mo7059a(sQLiteDatabase);
                        bk.m14978a(cursor);
                        return null;
                    } catch (Throwable th2) {
                        sQLiteDatabase2 = a;
                        th = th2;
                        this.f11981a.mo7059a(sQLiteDatabase2);
                        bk.m14978a(query);
                        throw th;
                    }
                }
                this.f11981a.mo7059a(a);
                bk.m14978a(query);
                return null;
            } catch (Exception e2) {
                sQLiteDatabase = a;
                cursor = null;
                this.f11981a.mo7059a(sQLiteDatabase);
                bk.m14978a(cursor);
                return null;
            } catch (Throwable th22) {
                query = null;
                sQLiteDatabase2 = a;
                th = th22;
                this.f11981a.mo7059a(sQLiteDatabase2);
                bk.m14978a(query);
                throw th;
            }
        } catch (Exception e3) {
            cursor = null;
            sQLiteDatabase = null;
            this.f11981a.mo7059a(sQLiteDatabase);
            bk.m14978a(cursor);
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            this.f11981a.mo7059a(sQLiteDatabase2);
            bk.m14978a(query);
            throw th;
        }
    }

    public void m15390a(List<cw> list) {
        SQLiteDatabase a = this.f11981a.mo7058a();
        try {
            a.beginTransaction();
            a.execSQL("delete from permissions");
            for (cw cwVar : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", cwVar.m15620b());
                contentValues.put("granted", Integer.valueOf(cwVar.m15619a() ? 1 : 0));
                a.insert("permissions", null, contentValues);
            }
            a.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            a.endTransaction();
            this.f11981a.mo7059a(a);
        }
    }
}
