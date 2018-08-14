package com.facebook.ads.internal.p062e;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.WorkerThread;
import com.mopub.common.Constants;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class C1967c extends C1966g {
    public static final C1965b f4562a = new C1965b(0, "event_id", "TEXT PRIMARY KEY");
    public static final C1965b f4563b = new C1965b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    public static final C1965b f4564c = new C1965b(2, "priority", "INTEGER");
    public static final C1965b f4565d = new C1965b(3, "type", "TEXT");
    public static final C1965b f4566e = new C1965b(4, "time", "REAL");
    public static final C1965b f4567f = new C1965b(5, "session_time", "REAL");
    public static final C1965b f4568g = new C1965b(6, "session_id", "TEXT");
    public static final C1965b f4569h = new C1965b(7, "data", "TEXT");
    public static final C1965b f4570i = new C1965b(8, "attempt", "INTEGER");
    public static final C1965b[] f4571j = new C1965b[]{f4562a, f4563b, f4564c, f4565d, f4566e, f4567f, f4568g, f4569h, f4570i};
    private static final String f4572l = C1966g.m6191a(Constants.VIDEO_TRACKING_EVENTS_KEY, f4571j);

    public C1967c(C1973d c1973d) {
        super(c1973d);
    }

    public String mo3701a() {
        return Constants.VIDEO_TRACKING_EVENTS_KEY;
    }

    @WorkerThread
    String m6201a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map) {
        String uuid = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(f4562a.f4559b, uuid);
        contentValues.put(f4563b.f4559b, str);
        contentValues.put(f4564c.f4559b, Integer.valueOf(i));
        contentValues.put(f4565d.f4559b, str2);
        contentValues.put(f4566e.f4559b, Double.valueOf(d));
        contentValues.put(f4567f.f4559b, Double.valueOf(d2));
        contentValues.put(f4568g.f4559b, str3);
        contentValues.put(f4569h.f4559b, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(f4570i.f4559b, Integer.valueOf(0));
        m6199f().insertOrThrow(Constants.VIDEO_TRACKING_EVENTS_KEY, null, contentValues);
        return uuid;
    }

    boolean m6202a(String str) {
        return m6199f().delete(Constants.VIDEO_TRACKING_EVENTS_KEY, new StringBuilder().append(f4562a.f4559b).append(" = ?").toString(), new String[]{str}) > 0;
    }

    public C1965b[] mo3702b() {
        return f4571j;
    }

    @WorkerThread
    Cursor mo3703c() {
        return m6199f().rawQuery("SELECT count(*) FROM events", null);
    }

    @WorkerThread
    Cursor m6205d() {
        return m6199f().rawQuery(f4572l, null);
    }
}
