package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

@zzadh
public final class zzamd {
    private static final zzamf<Map<String, ?>> zzctr = new zzame();

    @NonNull
    public static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        List<String> arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }
}
