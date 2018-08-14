package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.appnext.base.p019a.p022c.C1028c;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.vungle.publisher.dp.C1592a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public class iz extends dp<Integer> {
    @Inject
    C1641a f3024a;
    String f3025b;
    String f3026c;
    String f3027d;

    @Singleton
    /* compiled from: vungle */
    public static class C1641a extends C1592a<iz, Integer> {
        @Inject
        Provider<iz> f3023a;

        protected /* synthetic */ Object[] mo2960b(int i) {
            return m4218c(i);
        }

        protected /* synthetic */ dp[] mo2962d(int i) {
            return m4214a(i);
        }

        protected /* synthetic */ dp g_() {
            return m4208a();
        }

        @Inject
        C1641a() {
        }

        public iz m4210a(String str, String str2, String str3) {
            iz a = m4208a();
            a.f3027d = str;
            a.f3025b = str2;
            a.f3026c = str3;
            return a;
        }

        public List<iz> m4212a(String str, JSONObject jSONObject) {
            List<iz> arrayList = new ArrayList();
            try {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    arrayList.add(m4210a(str, str2, jSONObject.getString(str2)));
                }
            } catch (Throwable e) {
                Logger.e(Logger.DATABASE_TAG, "could not create template replacement list", e);
            }
            return arrayList;
        }

        protected List<iz> m4211a(String str) {
            String str2 = "ad_id = ?";
            return mo2959a("ad_id = ?", new String[]{str});
        }

        public b m4215b(String str) {
            return new b(m4211a(str));
        }

        protected String mo2961c() {
            return "template_replacements";
        }

        protected iz m4209a(iz izVar, Cursor cursor, boolean z) {
            izVar.f3027d = ce.e(cursor, "ad_id");
            izVar.f3025b = ce.e(cursor, C1028c.gv);
            izVar.f3026c = ce.e(cursor, FirebaseAnalytics$Param.VALUE);
            return izVar;
        }

        protected iz m4208a() {
            return (iz) this.f3023a.get();
        }

        protected iz[] m4214a(int i) {
            return new iz[i];
        }

        protected Integer[] m4218c(int i) {
            return new Integer[i];
        }
    }

    protected /* synthetic */ C1592a b_() {
        return m4221a();
    }

    @Inject
    iz() {
    }

    protected C1641a m4221a() {
        return this.f3024a;
    }

    protected String mo2966c() {
        return "template_replacements";
    }

    public String m4223e() {
        return this.f3025b;
    }

    public String m4224h() {
        return this.f3026c;
    }

    protected ContentValues mo2964a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(h_(), (Integer) this.u);
            contentValues.put("ad_id", this.f3027d);
        }
        contentValues.put(C1028c.gv, this.f3025b);
        contentValues.put(FirebaseAnalytics$Param.VALUE, this.f3026c);
        return contentValues;
    }
}
