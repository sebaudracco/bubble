package com.google.android.gms.internal.ads;

final class zzbam {
    private final byte[] buffer;
    private final zzbav zzdpv;

    private zzbam(int i) {
        this.buffer = new byte[i];
        this.zzdpv = zzbav.zzq(this.buffer);
    }

    public final zzbah zzabi() {
        this.zzdpv.zzacl();
        return new zzbao(this.buffer);
    }

    public final zzbav zzabj() {
        return this.zzdpv;
    }
}
