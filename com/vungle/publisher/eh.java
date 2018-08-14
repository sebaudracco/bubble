package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import com.vungle.publisher.dp.a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: vungle */
public abstract class eh extends dp<Integer> {
    String f9994a;
    String f9995b;
    jf f9996c;

    /* compiled from: vungle */
    static abstract class C4172a<E extends eh, T extends wu, R extends wc> extends a<E, Integer> {
        abstract Map<jf, List<E>> m13105a(String str, T t);

        C4172a() {
        }

        protected /* synthetic */ Object[] m13112b(int i) {
            return m13110a(i);
        }

        void m13106a(String str) {
            Logger.v(Logger.DATABASE_TAG, "deleted " + this.d.getWritableDatabase().delete("event_tracking", "ad_id = ?", new String[]{str}) + " expired " + "event_tracking" + " records for adId: " + str);
        }

        Map<jf, List<E>> m13104a(R r) {
            String g = r.g();
            m13106a(g);
            Map a = m13105a(g, r.e());
            m13108a(a);
            return a;
        }

        void m13108a(Map<jf, List<E>> map) {
            if (map != null) {
                for (List<eh> list : map.values()) {
                    if (list != null) {
                        for (eh d_ : list) {
                            d_.d_();
                        }
                    }
                }
            }
        }

        Map<jf, List<E>> m13111b(String str) {
            Throwable th;
            Cursor cursor = null;
            if (str == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch event_tracking records by ad_id: " + str);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching event_tracking records by ad_id: " + str);
                Cursor query = this.d.getReadableDatabase().query("event_tracking", null, "ad_id = ?", new String[]{str}, null, null, null);
                try {
                    Map<jf, List<E>> map;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " " + "event_tracking" + " for " + "ad_id" + ": " + str);
                    if (count > 0) {
                        Map<jf, List<E>> hashMap = new HashMap();
                        while (query.moveToNext()) {
                            eh ehVar = (eh) g_();
                            b(ehVar, query, false);
                            m13109a((Map) hashMap, ehVar);
                        }
                        map = hashMap;
                    } else {
                        map = null;
                    }
                    if (query == null) {
                        return map;
                    }
                    query.close();
                    return map;
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
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        void m13107a(String str, Map<jf, List<E>> map, jf jfVar, List<String> list) {
            if (list != null && list.size() > 0) {
                List a = m13103a(str, jfVar, (List) list);
                if (a != null && !a.isEmpty()) {
                    map.put(jfVar, a);
                }
            }
        }

        void m13109a(Map<jf, List<E>> map, E e) {
            if (e != null) {
                jf jfVar = e.f9996c;
                List list = (List) map.get(jfVar);
                if (list == null) {
                    list = new ArrayList();
                    map.put(jfVar, list);
                }
                list.add(e);
            }
        }

        List<E> m13103a(String str, jf jfVar, List<String> list) {
            List<E> list2;
            if (list == null || list.size() <= 0) {
                list2 = null;
            } else {
                List<E> arrayList = new ArrayList();
                for (String a : list) {
                    eh a2 = m13102a(str, jfVar, a);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                list2 = arrayList;
            }
            return (list2 == null || list2.isEmpty()) ? null : list2;
        }

        E m13102a(String str, jf jfVar, String str2) {
            if (jfVar == null || str2 == null) {
                return null;
            }
            eh ehVar = (eh) g_();
            ehVar.f9995b = str;
            ehVar.f9996c = jfVar;
            ehVar.f9994a = str2;
            return ehVar;
        }

        protected E m13101a(E e, Cursor cursor, boolean z) {
            e.u = ce.m12921c(cursor, "id");
            e.f9995b = ce.m12925e(cursor, "ad_id");
            e.f9994a = ce.m12925e(cursor, "url");
            return e;
        }

        protected String m13113c() {
            return "event_tracking";
        }

        protected Integer[] m13110a(int i) {
            return new Integer[i];
        }
    }

    protected String m13116c() {
        return "event_tracking";
    }

    protected ContentValues m13114a(boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", (Integer) this.u);
        contentValues.put("ad_id", this.f9995b);
        contentValues.put(NotificationCompat.CATEGORY_EVENT, this.f9996c.toString());
        contentValues.put("url", this.f9994a);
        return contentValues;
    }

    public StringBuilder m13117p() {
        StringBuilder p = super.p();
        a(p, "ad_id", this.f9995b);
        a(p, NotificationCompat.CATEGORY_EVENT, this.f9996c);
        a(p, "url", this.f9994a);
        return p;
    }

    public String m13115a() {
        return this.f9994a;
    }
}
