package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzd;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzbbb {
    private static volatile boolean zzdqr = false;
    private static final Class<?> zzdqs = zzacq();
    static final zzbbb zzdqt = new zzbbb(true);
    private final Map<zzbbc, zzd<?, ?>> zzdqu;

    zzbbb() {
        this.zzdqu = new HashMap();
    }

    private zzbbb(boolean z) {
        this.zzdqu = Collections.emptyMap();
    }

    private static Class<?> zzacq() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzbbb zzacr() {
        return zzbba.zzacp();
    }

    public final <ContainingType extends zzbcu> zzd<ContainingType, ?> zza(ContainingType containingType, int i) {
        return (zzd) this.zzdqu.get(new zzbbc(containingType, i));
    }
}
