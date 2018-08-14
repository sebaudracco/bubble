package com.facebook.ads.internal.p062e;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.mopub.common.Constants;
import com.stripe.android.net.StripeApiHandler;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class C1976h extends C1966g {
    public static final C1965b f4609a = new C1965b(0, "token_id", "TEXT PRIMARY KEY");
    public static final C1965b f4610b = new C1965b(1, SchemaSymbols.ATTVAL_TOKEN, "TEXT");
    public static final C1965b[] f4611c = new C1965b[]{f4609a, f4610b};
    private static final String f4612d = C1976h.class.getSimpleName();
    private static final String f4613e = C1966g.m6191a(StripeApiHandler.TOKENS, f4611c);
    private static final String f4614f = C1966g.m6192a(StripeApiHandler.TOKENS, f4611c, f4610b);
    private static final String f4615g = ("DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + f4609a.f4559b + " = " + Constants.VIDEO_TRACKING_EVENTS_KEY + "." + C1967c.f4563b.f4559b + ")");

    public C1976h(C1973d c1973d) {
        super(c1973d);
    }

    public String mo3701a() {
        return StripeApiHandler.TOKENS;
    }

    @WorkerThread
    String m6232a(String str) {
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid token.");
        }
        Cursor rawQuery;
        try {
            rawQuery = m6199f().rawQuery(f4614f, new String[]{str});
            try {
                String string = rawQuery.moveToNext() ? rawQuery.getString(f4609a.f4558a) : null;
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                    ContentValues contentValues = new ContentValues(2);
                    contentValues.put(f4609a.f4559b, string);
                    contentValues.put(f4610b.f4559b, str);
                    m6199f().insertOrThrow(StripeApiHandler.TOKENS, null, contentValues);
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                } else if (rawQuery != null) {
                    rawQuery.close();
                }
                return string;
            } catch (Throwable th2) {
                th = th2;
                if (rawQuery != null) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    public C1965b[] mo3702b() {
        return f4611c;
    }

    @WorkerThread
    Cursor mo3703c() {
        return m6199f().rawQuery(f4613e, null);
    }

    @WorkerThread
    public void m6235d() {
        try {
            m6199f().execSQL(f4615g);
        } catch (SQLException e) {
        }
    }
}
