package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.Nullable;
import com.inmobi.commons.core.p116c.C3115b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: AssetDao */
public class C3067l {
    public static final String[] f7480a = new String[]{"id", "pending_attempts", "url", "disk_uri", "ts", "created_ts", "ttl", "soft_ttl"};
    private static final String f7481b = C3067l.class.getSimpleName();
    private static C3067l f7482c;
    private static final Object f7483d = new Object();

    public static C3067l m9879a() {
        C3067l c3067l = f7482c;
        if (c3067l == null) {
            synchronized (f7483d) {
                c3067l = f7482c;
                if (c3067l == null) {
                    f7482c = new C3067l();
                    c3067l = f7482c;
                }
            }
        }
        return c3067l;
    }

    private C3067l() {
        C3115b a = C3115b.m10131a();
        a.m10136a("asset", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, disk_uri TEXT, ts TEXT NOT NULL, created_ts TEXT NOT NULL, ttl TEXT NOT NULL, soft_ttl TEXT NOT NULL)");
        a.m10140b();
    }

    public synchronized void m9883a(C3029b c3029b) {
        ContentValues d = m9890d(c3029b);
        C3115b a = C3115b.m10131a();
        a.m10137a("asset", d);
        a.m10140b();
    }

    public List<C3029b> m9882a(int i) {
        C3115b a = C3115b.m10131a();
        List<C3029b> arrayList = new ArrayList();
        int a2 = a.m10132a("asset");
        if (a2 >= i) {
            int i2 = a2 - ((int) (((double) i) * 0.7d));
            if (i2 > 0) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7481b, "Pruning persistent store to remove the older entries ...");
                List a3 = a.m10134a("asset", f7480a, null, null, null, null, "ts ASC ", null);
                if (a3 != null && a3.size() > 0) {
                    for (int i3 = 0; i3 < i2; i3++) {
                        arrayList.add(m9880a((ContentValues) a3.get(i3)));
                    }
                }
            }
        }
        a.m10140b();
        return arrayList;
    }

    public List<C3029b> m9886b(int i) {
        List<C3029b> arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        if (a.m10132a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.m10134a("asset", f7480a, null, null, "ts", "ts < " + (System.currentTimeMillis() - ((long) i)), "ts ASC ", null);
        a.m10140b();
        for (ContentValues a3 : a2) {
            arrayList.add(m9880a(a3));
        }
        return arrayList;
    }

    public List<C3029b> m9885b() {
        List<C3029b> arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        if (a.m10132a("asset") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.m10134a("asset", f7480a, "disk_uri IS NOT NULL", null, null, null, "created_ts DESC ", null);
        a.m10140b();
        for (ContentValues a3 : a2) {
            arrayList.add(m9880a(a3));
        }
        return arrayList;
    }

    @Nullable
    public C3029b m9881a(String str) {
        C3115b a = C3115b.m10131a();
        List a2 = a.m10134a("asset", f7480a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", SchemaSymbols.ATTVAL_TRUE_1);
        a.m10140b();
        if (a2.isEmpty()) {
            return null;
        }
        return m9880a((ContentValues) a2.get(0));
    }

    public C3029b m9884b(String str) {
        C3115b a = C3115b.m10131a();
        List a2 = a.m10134a("asset", f7480a, "url=? ", new String[]{str}, null, null, "created_ts DESC ", SchemaSymbols.ATTVAL_TRUE_1);
        a.m10140b();
        if (a2.isEmpty()) {
            return null;
        }
        return m9880a((ContentValues) a2.get(0));
    }

    public C3029b m9888c(String str) {
        C3115b a = C3115b.m10131a();
        List a2 = a.m10134a("asset", f7480a, "disk_uri=? ", new String[]{str}, null, null, "created_ts DESC ", SchemaSymbols.ATTVAL_TRUE_1);
        a.m10140b();
        if (a2.isEmpty()) {
            return null;
        }
        return m9880a((ContentValues) a2.get(0));
    }

    public void m9887b(C3029b c3029b) {
        C3115b a = C3115b.m10131a();
        a.m10138b("asset", m9890d(c3029b), "url = ?", new String[]{String.valueOf(c3029b.m9467b())});
        a.m10140b();
    }

    public void m9889c(C3029b c3029b) {
        C3115b a = C3115b.m10131a();
        a.m10133a("asset", "id = ?", new String[]{String.valueOf(c3029b.f7219b)});
        a.m10140b();
    }

    public C3029b m9880a(ContentValues contentValues) {
        return new C3029b(contentValues.getAsInteger("id").intValue(), contentValues.getAsString("url"), contentValues.getAsString("disk_uri"), contentValues.getAsInteger("pending_attempts").intValue(), Long.valueOf(contentValues.getAsString("ts")).longValue(), Long.valueOf(contentValues.getAsString("created_ts")).longValue(), Long.valueOf(contentValues.getAsString("ttl")).longValue(), Long.valueOf(contentValues.getAsString("soft_ttl")).longValue());
    }

    public ContentValues m9890d(C3029b c3029b) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(c3029b.f7219b));
        contentValues.put("url", c3029b.m9467b());
        contentValues.put("disk_uri", c3029b.f7221d);
        contentValues.put("pending_attempts", Integer.valueOf(c3029b.f7220c));
        contentValues.put("ts", Long.toString(c3029b.f7222e));
        contentValues.put("created_ts", Long.toString(c3029b.f7223f));
        contentValues.put("ttl", Long.toString(c3029b.f7224g));
        contentValues.put("soft_ttl", Long.toString(c3029b.f7225h));
        return contentValues;
    }
}
