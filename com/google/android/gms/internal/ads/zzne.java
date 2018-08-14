package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

final class zzne extends zzna<Float> {
    zzne(int i, String str, Float f) {
        super(i, str, f, null);
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return Float.valueOf(sharedPreferences.getFloat(getKey(), ((Float) zzja()).floatValue()));
    }

    public final /* synthetic */ void zza(Editor editor, Object obj) {
        editor.putFloat(getKey(), ((Float) obj).floatValue());
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return Float.valueOf((float) jSONObject.optDouble(getKey(), (double) ((Float) zzja()).floatValue()));
    }
}
