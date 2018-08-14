package com.core42matters.android.registrar;

import android.content.Context;
import android.text.TextUtils;
import java.security.SignatureException;

public final class AppId {
    private final String id;
    private final String key;

    AppId(String id, String key) {
        this.id = id;
        this.key = key;
    }

    AppId(Context context) throws AppIdMissingException {
        this(getId(context), getKey(context));
    }

    public AppId(Context context, String id) {
        this(id, getKey(context));
    }

    public String getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    private static String getId(Context context) throws AppIdMissingException {
        String appId = null;
        try {
            appId = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("42:appid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appId != null && !TextUtils.equals("YOUR_APP_ID", appId)) {
            return appId;
        }
        throw new AppIdMissingException(context.getPackageName());
    }

    private static String getKey(Context context) {
        return HashUtils.sha1Base64(PackageUtils.packageInfo(context).signatures[0].toByteArray());
    }

    public String sign(String raw) throws SignatureException {
        return HashUtils.hmacsha256(raw, this.key);
    }
}
