package com.google.android.gms.internal.ads;

import android.util.JsonWriter;
import java.util.Map;

final /* synthetic */ class zzana implements zzand {
    private final Map zzbjl;
    private final int zzcvc;

    zzana(int i, Map map) {
        this.zzcvc = i;
        this.zzbjl = map;
    }

    public final void zza(JsonWriter jsonWriter) {
        zzamy.zza(this.zzcvc, this.zzbjl, jsonWriter);
    }
}
