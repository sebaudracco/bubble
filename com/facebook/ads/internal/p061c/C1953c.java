package com.facebook.ads.internal.p061c;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class C1953c {

    public static class C1952a {
        public String f4529a;
        public String f4530b;
        public boolean f4531c;

        public C1952a(String str, String str2, boolean z) {
            this.f4529a = str;
            this.f4530b = str2;
            this.f4531c = z;
        }
    }

    public static C1952a m6158a(ContentResolver contentResolver) {
        Cursor query;
        C1952a c1952a;
        Throwable th;
        try {
            String[] strArr = new String[]{"aid", "androidid", "limit_tracking"};
            query = contentResolver.query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        c1952a = new C1952a(query.getString(query.getColumnIndex("aid")), query.getString(query.getColumnIndex("androidid")), Boolean.valueOf(query.getString(query.getColumnIndex("limit_tracking"))).booleanValue());
                        if (query != null) {
                            query.close();
                        }
                        return c1952a;
                    }
                } catch (Exception e) {
                    try {
                        c1952a = new C1952a(null, null, false);
                        if (query != null) {
                            query.close();
                        }
                        return c1952a;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            c1952a = new C1952a(null, null, false);
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            query = null;
            c1952a = new C1952a(null, null, false);
            if (query != null) {
                query.close();
            }
            return c1952a;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return c1952a;
    }
}
