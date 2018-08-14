package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;

final class zzbn$zzb extends zzbn$zza {
    private final ByteBuffer zzga;
    private int zzgb;

    zzbn$zzb(ByteBuffer byteBuffer) {
        super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        this.zzga = byteBuffer;
        this.zzgb = byteBuffer.position();
    }

    public final void flush() {
        this.zzga.position(this.zzgb + zzai());
    }
}
