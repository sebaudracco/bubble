package com.fyber.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.UUID;

/* compiled from: UserId */
public final class C2683w {
    public static synchronized String m8583a(Context context) {
        String string;
        synchronized (C2683w.class) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("FyberPreferences", 0);
            string = sharedPreferences.getString("STATE_GENERATED_USERID_KEY", null);
            if (string == null) {
                String uuid = UUID.randomUUID().toString();
                string = C2669q.m8528a(uuid);
                if (string == null || string.equals("nosha1")) {
                    string = uuid;
                }
                Editor edit = sharedPreferences.edit();
                edit.putString("STATE_GENERATED_USERID_KEY", string);
                edit.commit();
            }
        }
        return string;
    }
}
