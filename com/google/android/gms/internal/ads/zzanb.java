package com.google.android.gms.internal.ads;

import android.util.JsonWriter;

final /* synthetic */ class zzanb implements zzand {
    private final byte[] zzcvd;

    zzanb(byte[] bArr) {
        this.zzcvd = bArr;
    }

    public final void zza(JsonWriter jsonWriter) {
        zzamy.zza(this.zzcvd, jsonWriter);
    }
}
