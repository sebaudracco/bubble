package com.vungle.publisher;

import android.database.Cursor;
import com.vungle.publisher.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class m$a {
    public m m4355a(String str) {
        if (str == null) {
            return null;
        }
        for (m mVar : m.values()) {
            if (mVar.toString().equals(str)) {
                return mVar;
            }
        }
        Logger.e(Logger.PROTOCOL_TAG, "unknown AdType: " + str, new RuntimeException());
        return null;
    }

    public m m4354a(Cursor cursor, String str) {
        if (cursor != null && cursor.getCount() == 1 && cursor.moveToFirst()) {
            return m4355a(ce.e(cursor, str));
        }
        Logger.e(Logger.PROTOCOL_TAG, "AdType not found", new RuntimeException());
        return null;
    }

    public m[] m4356a() {
        return new m[]{m.a, m.c, m.d};
    }
}
