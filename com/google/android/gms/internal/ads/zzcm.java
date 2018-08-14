package com.google.android.gms.internal.ads;

import java.util.HashMap;

public final class zzcm extends zzbh<Integer, Long> {
    public long zzri;
    public long zzrj;

    public zzcm() {
        this.zzri = -1;
        this.zzrj = -1;
    }

    public zzcm(String str) {
        this();
        zzj(str);
    }

    protected final void zzj(String str) {
        HashMap zzk = zzbh.zzk(str);
        if (zzk != null) {
            this.zzri = ((Long) zzk.get(Integer.valueOf(0))).longValue();
            this.zzrj = ((Long) zzk.get(Integer.valueOf(1))).longValue();
        }
    }

    protected final HashMap<Integer, Long> zzu() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), Long.valueOf(this.zzri));
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.zzrj));
        return hashMap;
    }
}
