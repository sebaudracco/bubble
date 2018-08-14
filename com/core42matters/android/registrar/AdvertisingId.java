package com.core42matters.android.registrar;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.security.SignatureException;

class AdvertisingId {
    String id;
    boolean isLimitAdTrackingEnabled;

    AdvertisingId(String id, boolean isLimitAdTrackingEnabled) {
        this.id = id;
        this.isLimitAdTrackingEnabled = isLimitAdTrackingEnabled;
    }

    String getHash(Context context) {
        if (TextUtils.isEmpty(this.id)) {
            throw new NullPointerException("No advertising id");
        }
        Logger.m3321v("advertising id: " + this.id);
        StringBuilder builder = new StringBuilder();
        builder.append("google advertising id\n");
        builder.append(context.getPackageName()).append('\n');
        try {
            return HashUtils.hmacsha1(builder.toString(), this.id);
        } catch (SignatureException e) {
            builder.append(this.id);
            return HashUtils.sha1(builder.toString());
        }
    }

    static AdvertisingId get(Context context) {
        try {
            Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
            return new AdvertisingId(info.getId(), info.isLimitAdTrackingEnabled());
        } catch (Exception e) {
            return null;
        } catch (NoClassDefFoundError e2) {
            return null;
        }
    }
}
