package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzami extends zzajx {
    private final String zzag;
    private final zzanf zzctw;

    public zzami(Context context, String str, String str2) {
        this(str2, zzbv.zzek().zzm(context, str));
    }

    private zzami(String str, String str2) {
        this.zzctw = new zzanf(str2);
        this.zzag = str;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        this.zzctw.zzcz(this.zzag);
    }
}
