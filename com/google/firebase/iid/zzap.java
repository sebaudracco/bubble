package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

final class zzap {
    private static final long zzct = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzcu;
    private final String zzcv;

    private zzap(String str, String str2, long j) {
        this.zzcu = str;
        this.zzcv = str2;
        this.timestamp = j;
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SchemaSymbols.ATTVAL_TOKEN, str);
            jSONObject.put("appVersion", str2);
            jSONObject.put("timestamp", j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to encode token: ").append(valueOf).toString());
            return null;
        }
    }

    static zzap zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith("{")) {
            return new zzap(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzap(jSONObject.getString(SchemaSymbols.ATTVAL_TOKEN), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to parse token: ").append(valueOf).toString());
            return null;
        }
    }

    final boolean zzj(String str) {
        return System.currentTimeMillis() > this.timestamp + zzct || !str.equals(this.zzcv);
    }
}
