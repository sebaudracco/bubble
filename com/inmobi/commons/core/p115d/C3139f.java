package com.inmobi.commons.core.p115d;

import android.content.ContentValues;
import com.inmobi.commons.core.p116c.C3115b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TelemetryDao */
public class C3139f {
    private static final String f7735a = C3139f.class.getSimpleName();

    public C3139f() {
        C3115b a = C3115b.m10131a();
        a.m10136a("telemetry", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventId TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a.m10136a("metric", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL )");
        a.m10140b();
    }

    public void m10327a(C3110g c3110g) {
        C3115b a = C3115b.m10131a();
        a.m10137a("telemetry", c3110g.m10127f());
        a.m10140b();
    }

    public void m10328a(String str, String str2, String str3) {
        C3115b a = C3115b.m10131a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("componentType", str);
        contentValues.put("eventType", str2);
        contentValues.put("payload", str3);
        a.m10137a("metric", contentValues);
        a.m10140b();
    }

    public List<ContentValues> m10325a() {
        C3115b a = C3115b.m10131a();
        List<ContentValues> a2 = a.m10134a("metric", null, null, null, null, null, null, null);
        a.m10140b();
        m10330b();
        return a2;
    }

    public void m10330b() {
        C3115b a = C3115b.m10131a();
        a.m10133a("metric", null, null);
        a.m10140b();
    }

    public void m10329a(List<C3110g> list) {
        C3115b a = C3115b.m10131a();
        for (C3110g f : list) {
            a.m10137a("telemetry", f.m10127f());
        }
        a.m10140b();
    }

    public List<C3110g> m10326a(int i) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7735a, "Querying db for events");
        C3115b a = C3115b.m10131a();
        List<ContentValues> a2 = a.m10134a("telemetry", null, null, null, null, null, "ts ASC", String.valueOf(i));
        m10323b(a2);
        List<C3110g> arrayList = new ArrayList();
        a.m10140b();
        for (ContentValues a3 : a2) {
            arrayList.add(C3110g.m10120a(a3));
        }
        return arrayList;
    }

    private void m10323b(List<ContentValues> list) {
        if (!list.isEmpty()) {
            C3115b a = C3115b.m10131a();
            String str = "";
            int i = 0;
            while (i < list.size() - 1) {
                String str2 = str + ((ContentValues) list.get(i)).getAsString("id") + ",";
                i++;
                str = str2;
            }
            str = str + ((ContentValues) list.get(list.size() - 1)).getAsString("id");
            Logger.m10359a(InternalLogLevel.INTERNAL, f7735a, "Deleting events with id: " + str);
            int a2 = a.m10133a("telemetry", "id IN (" + str + ")", null);
            a.m10140b();
            Logger.m10359a(InternalLogLevel.INTERNAL, f7735a, "Deleted Count: " + a2);
        }
    }

    public int m10331c() {
        C3115b a = C3115b.m10131a();
        int a2 = a.m10132a("telemetry");
        a.m10140b();
        return a2;
    }

    public int m10324a(long j) {
        C3115b a = C3115b.m10131a();
        long currentTimeMillis = System.currentTimeMillis() - (1000 * j);
        int a2 = a.m10133a("telemetry", "ts<?", new String[]{String.valueOf(currentTimeMillis)});
        Logger.m10359a(InternalLogLevel.INTERNAL, f7735a, "Deleted " + a2 + " expired events from telemetry DB");
        a.m10140b();
        return a2;
    }
}
