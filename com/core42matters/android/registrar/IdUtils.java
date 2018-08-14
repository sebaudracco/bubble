package com.core42matters.android.registrar;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.UUID;

final class IdUtils {
    private IdUtils() {
        throw new AssertionError();
    }

    static String id(Context context) {
        return HashUtils.md5(Build.SERIAL);
    }

    static String createUDID(Context context) {
        try {
            return HashUtils.hmacsha1(UUID.randomUUID().toString(), id(context));
        } catch (Exception e) {
            return HashUtils.sha1(id(context) + '\n' + UUID.randomUUID().toString());
        }
    }

    static String getUDID(Context context) {
        return getUDID(context, new Store(context));
    }

    static synchronized String getUDID(Context context, Store store) {
        String udid;
        synchronized (IdUtils.class) {
            udid = null;
            try {
                udid = store.get("udid");
                if (udid == null || !udid.startsWith("mock:")) {
                    Logger.m3321v("loaded udid: " + udid);
                    if (TextUtils.isEmpty(udid)) {
                        udid = createUDID(context);
                        Logger.m3321v("created udid: " + udid);
                        store.put("udid", udid);
                    }
                } else {
                    udid = null;
                    if (TextUtils.isEmpty(udid)) {
                        udid = createUDID(context);
                        Logger.m3321v("created udid: " + udid);
                        store.put("udid", udid);
                    }
                }
            } catch (Exception e) {
                store.delete("udid");
            }
        }
        return udid;
    }
}
