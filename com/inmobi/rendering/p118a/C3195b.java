package com.inmobi.rendering.p118a;

import android.content.ContentValues;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.p116c.C3115b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ClickDao */
public class C3195b {
    public static final String[] f8006a = new String[]{"id", "pending_attempts", "url", "ping_in_webview", "follow_redirect", "ts", "created_ts", "track_extras"};
    private static final String f8007b = C3195b.class.getSimpleName();

    public C3195b() {
        C3115b a = C3115b.m10131a();
        a.m10136a("click", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pending_attempts INTEGER NOT NULL, url TEXT NOT NULL, ping_in_webview TEXT NOT NULL, follow_redirect TEXT NOT NULL, ts TEXT NOT NULL, track_extras TEXT, created_ts TEXT NOT NULL)");
        a.m10140b();
    }

    public boolean m10673a() {
        return C3115b.m10131a().m10132a("click") == 0;
    }

    public synchronized boolean m10674a(C3194a c3194a, int i) {
        ContentValues c = m10676c(c3194a);
        C3115b a = C3115b.m10131a();
        if (a.m10132a("click") >= i) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("pingUrl", c3194a.f7998b);
                hashMap.put("errorCode", "MaxDbLimitBreach");
                C3135c.m10255a().m10280a("ads", "PingDiscarded", hashMap);
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8007b, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f8007b, "Pruning persistent store to remove the oldest entry ...");
            C3194a a2 = m10670a((ContentValues) a.m10134a("click", f8006a, "ts= (SELECT MIN(ts) FROM click LIMIT 1)", null, null, null, null, null).get(0));
            Logger.m10359a(InternalLogLevel.INTERNAL, f8007b, "Deleting click (" + a2.f7997a + ")");
            m10675b(a2);
        }
        a.m10137a("click", c);
        a.m10140b();
        return true;
    }

    public List<C3194a> m10671a(int i, int i2) {
        List<C3194a> arrayList = new ArrayList();
        C3115b a = C3115b.m10131a();
        if (a.m10132a("click") == 0) {
            return arrayList;
        }
        List<ContentValues> a2 = a.m10134a("click", f8006a, null, null, "ts", "ts < " + (System.currentTimeMillis() - ((long) i2)), "ts ASC ", -1 == i ? null : Integer.toString(i));
        a.m10140b();
        for (ContentValues a3 : a2) {
            arrayList.add(m10670a(a3));
        }
        return arrayList;
    }

    public void m10672a(C3194a c3194a) {
        C3115b a = C3115b.m10131a();
        a.m10138b("click", m10676c(c3194a), "id = ?", new String[]{String.valueOf(c3194a.f7997a)});
        a.m10140b();
    }

    public void m10675b(C3194a c3194a) {
        C3115b a = C3115b.m10131a();
        a.m10133a("click", "id = ?", new String[]{String.valueOf(c3194a.f7997a)});
        a.m10140b();
    }

    public C3194a m10670a(ContentValues contentValues) {
        int intValue = contentValues.getAsInteger("id").intValue();
        int intValue2 = contentValues.getAsInteger("pending_attempts").intValue();
        String asString = contentValues.getAsString("url");
        long longValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        long longValue2 = Long.valueOf(contentValues.getAsString("created_ts")).longValue();
        boolean booleanValue = Boolean.valueOf(contentValues.getAsString("follow_redirect")).booleanValue();
        boolean booleanValue2 = Boolean.valueOf(contentValues.getAsString("ping_in_webview")).booleanValue();
        String asString2 = contentValues.getAsString("track_extras");
        Map hashMap = new HashMap();
        if (asString2 != null) {
            try {
                hashMap.putAll(m10669a(new JSONObject(asString2)));
            } catch (JSONException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8007b, "JSONException in parsing extras (" + e.getMessage() + ")");
            } catch (Exception e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8007b, "Exception in parsing extras (" + e2.getMessage() + ")");
            }
        }
        return new C3194a(intValue, asString, hashMap, booleanValue, booleanValue2, intValue2, longValue, longValue2);
    }

    public ContentValues m10676c(C3194a c3194a) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(c3194a.f7997a));
        contentValues.put("url", c3194a.f7998b);
        contentValues.put("pending_attempts", Integer.valueOf(c3194a.f8002f));
        contentValues.put("ts", Long.toString(c3194a.f8000d));
        contentValues.put("created_ts", Long.toString(c3194a.f8001e));
        contentValues.put("follow_redirect", Boolean.toString(c3194a.f8005i));
        contentValues.put("ping_in_webview", Boolean.toString(c3194a.f8004h));
        if (c3194a.f7999c != null && c3194a.f7999c.size() > 0) {
            contentValues.put("track_extras", new JSONObject(c3194a.f7999c).toString());
        }
        return contentValues;
    }

    private Map<String, String> m10669a(JSONObject jSONObject) throws JSONException {
        Map<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, (String) jSONObject.get(str));
        }
        return hashMap;
    }
}
