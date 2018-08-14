package com.google.android.gms.internal.ads;

@zzadh
public abstract class zzajx implements zzalc<zzanz> {
    private volatile Thread zzcqr;
    private boolean zzcqs = false;
    private final Runnable zzy = new zzajy(this);

    public zzajx(boolean z) {
    }

    public final void cancel() {
        onStop();
        if (this.zzcqr != null) {
            this.zzcqr.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzdn();

    public final /* synthetic */ Object zznt() {
        return this.zzcqs ? zzaki.zzc(this.zzy) : zzaki.zzb(this.zzy);
    }

    public final zzanz zzqo() {
        return this.zzcqs ? zzaki.zzc(this.zzy) : zzaki.zzb(this.zzy);
    }
}
